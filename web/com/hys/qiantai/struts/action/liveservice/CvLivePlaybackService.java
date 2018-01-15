package com.hys.qiantai.struts.action.liveservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hys.exam.common.util.CommDoPost;
import com.hys.exam.common.util.JsonToMap;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveCoursewareStudy;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCVUnitVideoCensus;
import com.hys.qiantai.struts.action.liveservice.comm.CvLiveConstant;
import com.hys.qiantai.struts.action.liveservice.comm.UserKeyHandleUtils;
/**
 * 同步直播回放记录业务类
 * @author yxt
 */
@SuppressWarnings("unchecked")
public class CvLivePlaybackService {
	
	private CvLiveManage localCvLiveManage;

	private LogStudyCVUnitManage logStudyCVUnitManage;
	
	private SystemUserManage systemUserManage;

	private CVSetEntityManage cVSetEntityManage;
	
	private CVUnitManage cvUnitManage;
	
	public CvLivePlaybackService(){
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		if(localCvLiveManage == null){
			localCvLiveManage = (CvLiveManage) applicationContext.getBean("localCvLiveManage");
		}
		if(logStudyCVUnitManage == null){
			logStudyCVUnitManage = (LogStudyCVUnitManage) applicationContext.getBean("localLogStudyCVUnitManage");
		}
		if(systemUserManage == null){
			systemUserManage = (SystemUserManage) applicationContext.getBean("systemUserManage");
		}
		if(cVSetEntityManage == null){
			cVSetEntityManage = (CVSetEntityManage) applicationContext.getBean("localCVSetEntity");
		}
		if(cvUnitManage == null){
			cvUnitManage = (CVUnitManage) applicationContext.getBean("cvUnitManage");
		}
	}
	
	
	public void cvLivePlaybackService(Integer page){
		Map<String,String> map = new HashMap<String,String>();
		map.put("loginName", CvLiveConstant.LOGIN_NAME);
		map.put("password", CvLiveConstant.PASSWORD);
		map.put("date", dateToString(new Date()));
		map.put("pageNo", page.toString());
		String result;
		try {
			result = CommDoPost.sendRequestByPost(CvLiveConstant.TRAINING_COURSEWARE_LIST_STUDY_LOG, map);
			Map<String, Object> resultMap = JsonToMap.parseJSON2Map(result);
			if("SUCCESS".equals(codeMessage(resultMap.get("code")))){
				doDataForCvLiveCoursewareStudy(resultMap);
				Map<String,Object> m = (Map<String, Object>) resultMap.get("page");
				int pageNo = Integer.parseInt(m.get("pageNo").toString());
				int totalPages = Integer.parseInt(m.get("totalPages").toString());
				if(pageNo<totalPages){
					cvLivePlaybackService(pageNo++);
				}
			}else{
				System.out.println(CvLiveConstant.TRAINING_COURSEWARE_LIST_STUDY_LOG + " 接口返回CODE：" + codeMessage(resultMap.get("code")));
			}				
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	 * resultMap--接口返回数据
	 */
	private void doDataForCvLiveCoursewareStudy(Map<String, Object> resultMap){
		for(String key:resultMap.keySet()){
			System.out.println(key+":"+resultMap.get(key));
		}
		List<Map<String,Object>> list = (List<Map<String, Object>>) resultMap.get("list");
		if(list.size()>0){
			List<CvLiveCoursewareStudy> listC = new ArrayList<CvLiveCoursewareStudy>();
			for(Map<String,Object> m:list){//处理接口数据
				if(Integer.parseInt(m.get("duration").toString())>0){
					CvLiveCoursewareStudy c = new CvLiveCoursewareStudy();
					c.setUserId(UserKeyHandleUtils.outputUserKey(Long.valueOf(m.get("uid").toString())));
					c.setCoursewareId(m.get("id").toString());
					c.setUsername(m.get("name").toString());
					c.setJoinTime(Long.valueOf(m.get("startTime").toString()));
					c.setLeaveTime(Long.valueOf(m.get("leaveTime").toString()));
					c.setDuration(m.get("duration").toString());
					c.setIp(m.get("ip").toString());
					c.setDevice(Integer.parseInt(m.get("device").toString()));
					c.setRecordDate(new Date(Long.valueOf(m.get("startTime").toString())));
					listC.add(c);
				}
			}
			Map<String,Object> mapRecord = new HashMap<String, Object>();
			for(int i=0;i<listC.size();i++){//筛选出需要的数据
				String coursewareId = listC.get(i).getCoursewareId();
				Long uid = listC.get(i).getUserId();
				long d = 0;
				if(mapRecord.get(coursewareId + uid)!=null){
					d = Long.valueOf(mapRecord.get(coursewareId + uid).toString());
				}else{
					List<CvLiveCoursewareStudy> qlist = localCvLiveManage.queryCvLiveCoursewareStudy(coursewareId, uid);
					if(qlist.size()>0){
						d = qlist.get(0).getRecordDate().getTime();
					}
					mapRecord.put(coursewareId + uid, d);
				}
				for(int j=0;j<listC.size();j++){
					if(coursewareId.equals(listC.get(j).getCoursewareId())&&uid==listC.get(j).getUserId()
							&&listC.get(j).getRecordDate().getTime()<=d){
						listC.remove(j);
						if(j<=i){
							i--;
						}
						j--;
					}
				}
			}
			for(CvLiveCoursewareStudy c:listC){//同步统计学习时间数据
				long cvSetId = 0;
				long cvId = 0;
				long cvUnitId = 0;
				long systemUserId = 0;
				long videoLength = 0;
				SystemUser user = new SystemUser();
				user.setUserId(c.getUserId());
				List<SystemUser> userList = systemUserManage.getListByItem(user);
				if(userList.size()>0){
					systemUserId = userList.get(0).getUserId();
				}
				List<CvLiveCourseware> listCc = localCvLiveManage.queryCvLiveCoursewareByCoursewareId(c.getCoursewareId());
				if(listCc.size()>0){
					cvId = listCc.get(0).getCv_id();
					videoLength = Long.valueOf(Math.round((double)listCc.get(0).getDuration()/1000));
				}
				CVSet cvSet = cVSetEntityManage.queryCVSetListByCvId(cvId);
				if(cvSet!=null){
					cvSetId = cvSet.getId();
				}
				List<CvLiveRefUnit> listCu = localCvLiveManage.queryCvLiveRefUnitByCoursewareId(c.getCoursewareId());
				if(listCu.size()>0){
					cvUnitId = listCu.get(0).getUnit_id();
				}
				LogStudyCVUnitVideoCensus logC = new LogStudyCVUnitVideoCensus();
				logC.setCvSetId(cvSetId);
				logC.setCvId(cvId);
				logC.setCvUnitId(cvUnitId);
				logC.setSystemUserId(systemUserId);
				logC.setVideoEchoLength(0l);
				logC.setVideoStartLength(0l);
				logC.setVideoEndLength(Long.valueOf(Math.round((double)Long.valueOf(c.getDuration())/1000)));
				logC.setVideoLength(videoLength);
				Boolean b = logStudyCVUnitManage.saveLogStudyCVUnitVideoCensus(logC);
				if(b){
					System.out.println("logStudyCVUnitManage.saveLogStudyCVUnitVideoCensus():新增成功!");
				}else{
					System.out.println("logStudyCVUnitManage.saveLogStudyCVUnitVideoCensus():新增失败!");
				}
			}
			Map<String,CvLiveCoursewareStudy> mapRecordAddUp = new HashMap<String, CvLiveCoursewareStudy>();
			for(CvLiveCoursewareStudy c:listC){//累计有效数据并筛选出最近一次数据的记录
				String coursewareId = c.getCoursewareId();
				Long uid = c.getUserId();
				if(mapRecordAddUp.get(coursewareId + uid)!=null){
					CvLiveCoursewareStudy clsA = mapRecordAddUp.get(coursewareId + uid);
					if(clsA.getRecordDate().getTime()>=c.getRecordDate().getTime()){
						Long duration = Long.valueOf(clsA.getDuration())+Long.valueOf(c.getDuration());
						clsA.setDuration(duration.toString());
						mapRecordAddUp.put(coursewareId + uid, clsA);
					}else{
						Long duration = Long.valueOf(c.getDuration())+Long.valueOf(clsA.getDuration());
						c.setDuration(duration.toString());
						mapRecordAddUp.put(coursewareId + uid, c);
					}
				}else{
					mapRecordAddUp.put(coursewareId + uid, c);
				}
			}
			for(String key:mapRecordAddUp.keySet()){//新增或叠加数据记录
				CvLiveCoursewareStudy clc = (CvLiveCoursewareStudy)mapRecordAddUp.get(key);
				long videoLength = 0;
				List<CvLiveCourseware> listCc = localCvLiveManage.queryCvLiveCoursewareByCoursewareId(clc.getCoursewareId());
				if(listCc.size()>0){
					videoLength = Long.valueOf(listCc.get(0).getDuration());
					long cvSetId = 0;
					long cvId = 0;
					long cvUnitId = 0;
					long systemUserId = 0;
					SystemUser user = new SystemUser();
					user.setUserId(clc.getUserId());
					List<CvLiveRefUnit> listCu = localCvLiveManage.queryCvLiveRefUnitByCoursewareId(clc.getCoursewareId());
					List<SystemUser> userList = systemUserManage.getListByItem(user);
					if(listCu.size()>0){
						cvUnitId = listCu.get(0).getUnit_id();
					}
					if(userList.size()>0){
						systemUserId = userList.get(0).getUserId();
					}
					cvId = listCc.get(0).getCv_id();
					CVSet cvSet = cVSetEntityManage.queryCVSetListByCvId(cvId);
					if(cvSet!=null){
						cvSetId = cvSet.getId();
					}
					CVUnit cvu = new CVUnit();
					cvu.setId(cvUnitId);
					CVUnit cvUnit = cvUnitManage.findCvunit(cvu);
					List<CvLiveCoursewareStudy> listClc = localCvLiveManage.queryCvLiveCoursewareStudy(key);
					long a = Long.valueOf(clc.getDuration());
					double quota = 0;
					if(cvUnit!=null&&cvUnit.getQuota()!=null){
						quota = cvUnit.getQuota()/100;
					}
					if(listClc.size()>0){
						long b = Long.valueOf(listClc.get(0).getDuration());
						Long duration = a + b;
						double c = (double)b/videoLength;
						double d = (double)duration/videoLength;
						if(c<quota&&d>=quota){
							updLogStudyCVUnit(cvSetId, cvId, cvUnitId, systemUserId, videoLength);
						}
						clc.setDuration(duration.toString());
						Boolean bb = localCvLiveManage.updateCvLiveCoursewareStudy(key, clc);
						if(bb){
							System.out.println("localCvLiveManage.updateCvLiveCoursewareStudy():更新成功!");
						}else{
							System.out.println("localCvLiveManage.updateCvLiveCoursewareStudy():更新失败!");
						}
					}else{
						double e = (double)a/videoLength;
						if(e>=quota){
							updLogStudyCVUnit(cvSetId, cvId, cvUnitId, systemUserId, videoLength);
						}
						Boolean bb = localCvLiveManage.saveCvLiveCoursewareStudy(clc);
						if(bb){
							System.out.println("localCvLiveManage.saveCvLiveCoursewareStudy():新增成功!");
						}else{
							System.out.println("localCvLiveManage.saveCvLiveCoursewareStudy():新增失败!");
						}
					}
				}
			}
		}
	}
	
	/**
	 * cvSetId--项目ID, cvId--课程ID, cvUnitId--单元ID, systemUserId--用户ID, videoLength--视频总长(毫秒)
	 */
	private void updLogStudyCVUnit(Long cvSetId, Long cvId, Long cvUnitId, Long systemUserId, long videoLength){
		LogStudyCVUnit lscu = new LogStudyCVUnit();
		lscu.setCvUnitId(cvUnitId);
		lscu.setSystemUserId(systemUserId);
		List<LogStudyCVUnit> listLscu = logStudyCVUnitManage.queryLogStudyCVUnitByUnitId(lscu);
		if(listLscu.size()>0){
			if(listLscu.get(0).getStatus()==1){
				LogStudyCVUnitVideo lscuv = new LogStudyCVUnitVideo();
				lscuv.setLogStudyCvUnitId(listLscu.get(0).getId());
				lscuv.setCvId(cvId);
				lscuv.setCvUnitId(cvUnitId);
				lscuv.setSystemUserId(systemUserId);
				lscuv.setVideoLength(videoLength/1000);
				lscuv.setVideoPlayLength(videoLength/1000);
				Boolean b = logStudyCVUnitManage.addLogStudyCVUnitVideo(lscuv);
				if(b){
					System.out.println("logStudyCVUnitManage.addLogStudyCVUnitVideo():更新成功!");
				}else{
					System.out.println("logStudyCVUnitManage.addLogStudyCVUnitVideo():更新失败!");
				}
			}
		}else{
			LogStudyCVUnit log = new LogStudyCVUnit();
			log.setLogStudyCvSetId(cvSetId);
			log.setCvId(cvId);
			log.setCvUnitId(cvUnitId);
			log.setSystemUserId(systemUserId);
			logStudyCVUnitManage.addLogStudyCVUnit(log);
			LogStudyCVUnit lscuu = new LogStudyCVUnit();
			lscuu.setCvUnitId(cvUnitId);
			lscuu.setSystemUserId(systemUserId);
			List<LogStudyCVUnit> listLscuu = logStudyCVUnitManage.queryLogStudyCVUnitByUnitId(lscuu);
			if(listLscuu.size()>0){
				System.out.println("logStudyCVUnitManage.queryLogStudyCVUnitByUnitId:新增成功!");
				LogStudyCVUnitVideo videoLog = new LogStudyCVUnitVideo();
				videoLog.setLogStudyCvUnitId(listLscuu.get(0).getId());
				videoLog.setCvId(cvId);
				videoLog.setCvUnitId(cvUnitId);
				videoLog.setSystemUserId(systemUserId);
				videoLog.setVideoLength(videoLength/1000);
				videoLog.setVideoPlayLength(videoLength/1000);
				Boolean b = logStudyCVUnitManage.addLogStudyCVUnitVideo(videoLog);
				if(b){
					System.out.println("logStudyCVUnitManage.addLogStudyCVUnitVideo():新增成功!");
				}else{
					System.out.println("logStudyCVUnitManage.addLogStudyCVUnitVideo():新增失败!");
				}
			}else{
				System.out.println("logStudyCVUnitManage.queryLogStudyCVUnitByUnitId:新增失败!");
			}
		}
	}
	
	private static String dateToString(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
	private static String codeMessage(Object code){
		String m = "";
		int c = -1;
		try{
			c = Integer.parseInt(code.toString());
		}catch(Exception e){
			return "解析CODE错误";
		}
		switch (c) {
			case 0:
				m = "SUCCESS";
				break;
			case -1:
				m = "FAIL";
				break;
			case 101:
				m = "参数错误";
				break;
			case 102:
				m = "参数转换错误";
				break;
			case 200:
				m = "认证失败";
				break;
			case 201:
				m = "口令过期";
				break;
			case 300:
				m = "系统错误";
				break;
			case 500:
				m = "业务错误";
				break;
			case 501:
				m = "业务错误-数据不存在";
				break;
			case 502:
				m = "业务错误-重复数据";
				break;
			case 600:
				m = "接口被禁用,请联系管理员";
				break;
			default:
				m = "未定义的CODE";
		}
		return m;
	}
	
}