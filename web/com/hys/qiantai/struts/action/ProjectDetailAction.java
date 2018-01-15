package com.hys.qiantai.struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.filter.SensitivewordFilter;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetScoreLog;
import com.hys.qiantai.model.LogStudyCvSet;

public class ProjectDetailAction extends BaseAction {
    private CVSetManage localCVSetManage;
	private CVSetEntityManage localCVSetEntityDAO;
	private CvLiveManage localCvLiveManage;
	private LogStudyCVSetManage localLogStudyCVSetManage;
	private LogStudyCVUnitManage localLogStudyCVUnitManage;

    public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}

	public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
        this.localCVSetManage = localCVSetManage;
    }

    @Override
    protected String actionExecute(ActionMapping arg0, ActionForm arg1,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String mode = ParamUtils.getParameter(request, "mode", "");
    	Long id = Long.parseLong(request.getParameter("id"));
    	SystemUser user = LogicUtil.getSystemUser(request);
    	
    	if (mode != null && mode.equals("isLogin")){//判断是否登录过
    		Map<String,Object> map = new HashMap<String,Object>();    		
    		if (user==null || user.getUserId() == 0){
    			map.put("message", "noLogin");    			
    		} else {
    			map.put("message", "isLogined");
    		}
    		StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
    	if (mode != null && mode.equals("scoreLogIsExist")){//判断是否评价过 YHQ 2017-02-17    		
    		Map<String,Object> map = new HashMap<String,Object>();
    		CVSetScoreLog cssLog = new CVSetScoreLog() ; 
    		cssLog.setCvSetId(id);
    		cssLog.setSystemUserId(user.getUserId());
    		boolean userIsExit = localCVSetManage.getCVSetScoreLogIsExist(cssLog);
    		map.put("message", userIsExit);
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
    	// 评价保存处理
    	if (mode.equals("critique")){    		    		
    		//返回信息
    		Map<String,Object> map = new HashMap<String,Object>();
    		
    		if (user==null || user.getUserId() == 0){
    			map.put("message", "noLogin");
    			StrutsUtil.renderText(response, JsonUtil.map2json(map));
    			return null;
    		}
    		
    		//已评价 YHQ 2017-02-14 1827---begin
    		CVSetScoreLog cssLog = new CVSetScoreLog() ; 
    		cssLog.setCvSetId(id);
    		cssLog.setSystemUserId(user.getUserId());
    		boolean userIsExit = localCVSetManage.getCVSetScoreLogIsExist(cssLog);
    		if (userIsExit) {
    			map.put("message", "isCritiqued");
    			StrutsUtil.renderText(response, JsonUtil.map2json(map));
    			return null;
    		}
    		//已评价 YHQ 2017-02-14 1827---over
    		
    		CVSet query = new CVSet();
    		query.setId(id);
    		query.setCritiqueScore1(Double.valueOf(request.getParameter("score1")));
    		query.setCritiqueScore2(Double.valueOf(request.getParameter("score2")));
    		query.setCritiqueScore3(Double.valueOf(request.getParameter("score3")));
    		query.setCritiqueScore4(Double.valueOf(request.getParameter("score4")));
    		query.setOpinion(request.getParameter("comment"));
    		localCVSetManage.updateCritiqueScoreLog(user.getUserId(), query);

    		map.put("message", "success");
    		map.put("scores", query);
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
        CVSet query = new CVSet();
        query.setServerName(request.getServerName());
        query.setStart_date(new Date());
        query.setStatus(5);// 已发布
        query.setId(NumberUtil.parseLong(request.getParameter("id")));
        if(user!=null&&user.getUserConfig()!=null){
        	query.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
			
		}
        CVSet cvSet = localCVSetManage.getCVSetById(query);

        if (cvSet != null) {
            //所属学科
            cvSet.setExamPropList(localCVSetManage.getExamPropList(cvSet.getId()));
            //项目负责人
            cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getId(), 1));
            //所属机构
            cvSet.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet.getId()));
            //授课教师
            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
            //项目的书籍推荐
            cvSet.setRefereeBookList(localCVSetManage.getRefereeBookByCvId(cvSet.getId()));
            //指南共识
            cvSet.setKnowledgeBaseList(localCVSetManage.getKnowledgebaseByCvId(id));
            //最新文献
            cvSet.setReferencelatelyList(localCVSetManage.getReferencelatelyByCvId(id));
            //课程列表
            List<CV> cvList = localCVSetManage.getCvList(cvSet.getId());
           
          //合并查询课程与单元信息
    		if(cvList!=null && cvList.size()>0){
    			try {
					int size=cvList.size();
					for(int i=0;i<size;i++){
						CV cv =  cvList.get(i);
						//根据课程信息查询单元信息
						List<CVUnit> unitList = null;
						if(cv.getCv_type() == 2){
							List<CvLive> livelist = localCvLiveManage.queryCvLiveList(cv.getId());
							if(livelist != null && livelist.size() > 0){
								cv.setStartDate(livelist.get(0).getStart_date());
								cv.setEndDate(livelist.get(0).getInvalid_date());
							}
							unitList = localCvLiveManage.getCvUnitForLive(cv.getId());
							if(user != null){
								if(unitList.size() > 0){
									for(CVUnit unit : unitList){
										//添加直播课程单元为学习单元过滤
										LogStudyCVUnit cvStudyLog = new LogStudyCVUnit();
										cvStudyLog.setCvUnitId(unit.getId());
										cvStudyLog.setSystemUserId(user.getUserId());
										List<LogStudyCVUnit> logUnitListForLive = localLogStudyCVUnitManage.queryLogStudyCVUnitForLiveFilter(cvStudyLog);
										if(logUnitListForLive.size() > 0){
											unit.setLogStatus(null);
										}else{
											LogStudyCVUnit logunit = new LogStudyCVUnit();
											logunit.setCvUnitId(unit.getId());
											logunit.setSystemUserId(user.getUserId());
											List<LogStudyCVUnit> logunitList = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(logunit);
											if(logunitList.size() > 0){
												if(logunitList.get(0).getStatus() == 1){
													unit.setLogStatus(1);
												}else if(logunitList.get(0).getStatus() == 2){
													unit.setLogStatus(2);
												}else{
													unit.setLogStatus(null);
												}
											}
										}
									}
								}
							}
						}else{
							if(user == null){
								unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
							}else{
								//taoliang----新的单元初始化方式
								unitList = localCVSetEntityDAO.queryUnitListByClassIdAndUserId(cv.getId(), user.getUserId());
							}
						}
						//查询素材信息SCP
						List<GroupClassInfo> groupClassInfo = localCVSetEntityDAO.queryGroupClassInfo(cv.getId());
						//截取地址中的类型循环存入
						for(int j=0;j<groupClassInfo.size();j++){
							String html = groupClassInfo.get(j).getClassContent();
							Document doc = Jsoup.parse(html);
							Elements allImg = doc.getElementsByTag("img") ;
							for (Element imgElt : allImg) {
								Attributes allProp = imgElt.attributes() ;
								String altAttr = allProp.get("alt") ;
								unitList.get(j).setContent(altAttr);
							}
						}
						cv.setUnitList(unitList);
 		//		cvList.add(cv);
						//#############################################
						//根据课程判断该课程是否为直播课程---taoliang
	    	            List<CvLive> liveList = localCvLiveManage.queryCvLiveList(cv.getId());
	    	            if(liveList != null && liveList.size() > 0){
	                		Date now = new Date();
	                		Date startDate = liveList.get(0).getStart_date();
	                		Date endDate = liveList.get(0).getInvalid_date();
	                		if(startDate.getTime() > now.getTime()){
	                			cv.setType(2);
	                		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
	                			cv.setType(1);
	                		}else if(endDate.getTime() < now.getTime()){
	                			cv.setType(3);
	                		}
	    	            }
	    	            
	    	            //获取项目费用类型情况
	    	            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
	    	            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			
    		}
            cvSet.setCvList(cvList);
        }

        /**
         * @author Han
         * @time	2017-02-07
         * 取得项目评论log列表
         */
        Pager<CVSetScoreLog> pl = new Pager<>();
        int currentPage = PageUtil.getPageIndex2(request);        
        pl.setPageOffset(currentPage);
        pl.setUrl("projectDetail.do");
        pl.setQueryString(request);
        
        /*PageList<CVSetScoreLog> pl = new PageList<CVSetScoreLog>();
        int currentPage = PageUtil.getPageIndex(request);
        pl.setPageNumber(currentPage);
        //pl.setObjectsPerPage(20);
*/        CVSetScoreLog log = new CVSetScoreLog();
        log.setCvSetId(id);
        
        localCVSetManage.getCVSetScoreLogList(log, pl);
        //对项目评价过滤敏感词
        List<CVSetScoreLog> scoreList = pl.getList();
        SensitivewordFilter filter = new SensitivewordFilter();
        for (CVSetScoreLog scoreLog:scoreList) {
        	String content = filter.replaceSensitiveWord(scoreLog.getScoreContent(), 1, "*");
        	scoreLog.setScoreContent(content);
        }
        pl.setList(scoreList);
        //----项目评价过滤敏感词汇end----
        {//用于初始化当前项目的学习状态 0.表示未开始学习 1.表示已经开始学习 2.表示已经结束学习
        	if(user != null){
	        	LogStudyCVSet logStudyCVSet = new LogStudyCVSet();
	        	logStudyCVSet.setCvSetId(id);
	        	logStudyCVSet.setSystemUserId(user.getUserId());
	        	List<LogStudyCVSet> list = localLogStudyCVSetManage.searchLogStudyCVSet(logStudyCVSet);
	        	if(list.size() > 0){
	        		request.setAttribute("logCVSetStatus", list.get(0).getState());
	        	}else{
	        		request.setAttribute("logCVSetStatus", 0);
	        	}
        	}else{
        		request.setAttribute("logCVSetStatus", 0);
        	}
        }
       //面授期数
        List<CVSetScheduleFaceTeach> face=new ArrayList<CVSetScheduleFaceTeach>();
        Long fid =NumberUtil.parseLong(request.getParameter("fid"));
        request.setAttribute("fid", fid);
        if (fid != 0) {
        	face = localCVSetManage.getMyFaceTeach(NumberUtil.parseLong(request.getParameter("id")),fid);
		}else{
		    face = localCVSetManage.getFaceTeach(NumberUtil.parseLong(request.getParameter("id")));
		}
        
        //判断面授项目报名是否截止
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date now = new Date();
        for (CVSetScheduleFaceTeach faceTeach : face) {
        	Date startTime = faceTeach.getTrain_starttime();//开始时间
        	Date endTime = faceTeach.getTrain_endtime(); //结束时间
        	Date day1 =sdf.parse(sdf.format(now));
	    	Date day2 = sdf.parse(sdf.format(startTime));
	        Date day3 = sdf.parse(sdf.format(endTime));
	    	if (differentDays(day1,day2) >  0) { //可以报名,未开始
	    		faceTeach.setFaceType(1);
        	}else if (differentDays(day1, day3)>=0 &&differentDays(day1, day2)<=0 ) { //面授中
        		faceTeach.setFaceType(2);
			}else {  //面授已结束
				faceTeach.setFaceType(3);
			}
		}
        request.setAttribute("pinglun", pl.getList());
        request.setAttribute("pager", pl);
        request.setAttribute("cvSet", cvSet);
        request.setAttribute("id", id);
        request.setAttribute("faceTeach", face);
        request.setAttribute("user", user);

        return "detail";
    }

	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}

	public void setLocalLogStudyCVUnitManage(
			LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}
	
	/**
	    * @author sunny
	    * 计算两个时间的天数
	    * @param date1
	    * @param date2
	    * @return 时间差
	    */
	    private int differentDays(Date date1, Date date2) {
	    	 Calendar cal1 = Calendar.getInstance();
	         cal1.setTime(date1);
	         
	         Calendar cal2 = Calendar.getInstance();
	         cal2.setTime(date2);
	         int day1= cal1.get(Calendar.DAY_OF_YEAR);
	         int day2 = cal2.get(Calendar.DAY_OF_YEAR);
	         
	         int year1 = cal1.get(Calendar.YEAR);
	         int year2 = cal2.get(Calendar.YEAR);
	         if(year1 != year2){
	        	 return year2 -year1;
	         }else{
	             System.out.println("判断day2 - day1 : " + (day2-day1));
	             return day2-day1;
	         }
		}

}
