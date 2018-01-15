package com.hys.qiantai.struts.action.liveservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.CommDoPost;
import com.hys.exam.common.util.JsonToMap;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CvLiveStudy;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCVUnitVideoCensus;
import com.hys.qiantai.struts.action.liveservice.comm.CvLiveConstant;
import com.hys.qiantai.struts.action.liveservice.comm.UserKeyHandleUtils;
/**
 * 使用异步的方式进行初始化数据
 * @author taoliang
 *
 */
public class CvLiveRecordThread extends BaseAction implements Callable<String>{
	
	private String roomId;
	private CvLiveManage localCvLiveManage;
	private LogStudyCVUnitManage localLogStudyCVUnitManage;
	private SystemUserManage localSystemUserManage;
	
	public CvLiveRecordThread(String roomId, CvLiveManage localCvLiveManage, LogStudyCVUnitManage localLogStudyCVUnitManage,SystemUserManage localSystemUserManage) {
		this.roomId = roomId;
		this.localCvLiveManage = localCvLiveManage;
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
		this.localSystemUserManage = localSystemUserManage;
	}
	
	@Override
	public String call() {
		System.out.println("看好了，马上要进行初始化了。。。。。。。。。");
		try {
			System.out.println("沉睡一会儿后执行。。。。。。"+new Date());
			Thread.sleep(60000);
			this.initRecord();
		} catch (Exception e) {
			System.out.println("初始化异常！");
			e.printStackTrace();
			return "初始化异常！";
		}
		return null;
	}
	
	public void initRecord(){
		System.out.println("醒了开始执行。。。。。。"+new Date());
		//String url = "http://rkrc.gensee.com/integration/site/training/export/history";
		String url = CvLiveConstant.TRAINING_EXPORT_HISTORY;
		Map<String,String> map = new HashMap<String,String>();
		map.put("roomId", roomId);
		map.put("loginName", CvLiveConstant.LOGIN_NAME);
		map.put("password", CvLiveConstant.PASSWORD);
		String result;
		try {
			result = CommDoPost.sendRequestByPost(url, map);
			System.out.println(result);
			Map resultMap = JsonToMap.parseJSON2Map(result);
			String code = resultMap.get("code").toString();
			List list = (List) resultMap.get("list");
			for(int i=0;i<list.size();i++){
				CvLiveStudy study = new CvLiveStudy();
				Map<String, Object> studyMap = (Map<String, Object>) list.get(i);
				study = (CvLiveStudy) this.mapToObject(studyMap, CvLiveStudy.class);
				study.setUser_id(UserKeyHandleUtils.outputUserKey(Long.valueOf(studyMap.get("uid").toString())));
				
				if(study.getDevice() == 0){//过滤掉老师客户端的记录
					continue;
				}
				study.setClass_no(roomId);

				CvLiveStudyRef record = new CvLiveStudyRef();
				record.setClass_no(roomId);
				//int liveActualLength = 0;
				/*List<CvLiveStudyRef> reflist = localCvLiveManage.queryCvLiveStudyRef(record);
				if(reflist != null && reflist.size() > 0){
					record = reflist.get(0);
					Long TsecondLength = record.getEnd_time() - record.getStart_time();
					liveActualLength = Integer.valueOf(TsecondLength.toString());
					System.out.println("本场次老师直播时长："+liveActualLength);
				}*/
				//study.setLive_actual_length(liveActualLength);
				Long SsecondLength = (study.getLeaveTime()/1000) - (study.getJoinTime()/1000);
				System.out.println("本场次学生["+study.getNickname()+"]参会时长："+SsecondLength);
				study.setUseful_length(Integer.valueOf(SsecondLength.toString()));
				Long cvLiveStudyId = localCvLiveManage.saveCvLiveStudy(study);
				System.out.println("saveCvLiveStudy----id:"+cvLiveStudyId);
				//初始化log_study_cv_unit_video
				SystemUser item = new SystemUser();
				item.setUserId(study.getUser_id());
				SystemUser user = localSystemUserManage.queryUserForLive(item);
				if(user != null){
					LogStudyCVUnitVideo logstudy = localLogStudyCVUnitManage.getVideoLog(roomId, user.getUserId(), cvLiveStudyId);
					LogStudyCVUnitVideo videoLog = new  LogStudyCVUnitVideo();
	                videoLog.setLogStudyCvUnitId(logstudy.getLogStudyCvUnitId());
	                videoLog.setCvId(logstudy.getCvId());
	                videoLog.setCvUnitId(logstudy.getCvUnitId());
	                videoLog.setVideoLength(logstudy.getVideoLength());
	                videoLog.setVideoPlayLength(logstudy.getVideoPlayLength());
	                //videoLog.setStartDate(startDate);
	                videoLog.setSystemUserId(user.getUserId());
	                boolean saveRes = localLogStudyCVUnitManage.addLogStudyCVUnitVideo(videoLog) ;
	                if(saveRes){
	                	//同步信息到log_study_cv_unit_video_census
	                	CVSet cvset = localLogStudyCVUnitManage.getCVSetByCVId(logstudy.getCvId());
	                	List<CvLiveStudyRef> reflist = localCvLiveManage.queryCvLiveStudyRef(record);
	    				if(reflist != null && reflist.size() > 0){
	    					record = reflist.get(0);
	    				}
	                	LogStudyCVUnitVideoCensus log = new  LogStudyCVUnitVideoCensus(user.getUserId(),cvset.getId(),logstudy.getCvId(),logstudy.getCvUnitId()) ;
		                log.setVideoEchoLength(0L);
		                log.setVideoStartLength(0L);
		                log.setVideoEndLength(SsecondLength==null?0L:SsecondLength);
		                log.setVideoLength(record.getTotal_live_length()==null?0L:Long.valueOf(record.getTotal_live_length()));
		                localLogStudyCVUnitManage.saveLogStudyCVUnitVideoCensus(log) ;
	                }
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
        Object obj = beanClass.newInstance();  
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
        return obj;  
    }

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
