package com.hys.qiantai.struts.action;

import com.hys.auth.util.DoubleUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.filter.SensitivewordFilter;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CVSetUnitDiscuss;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CVSetUnitDiscussManage;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.DiplomaService;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetScoreLog;
import com.hys.qiantai.model.CvDiplomaEntity;
import com.hys.qiantai.model.LogStudyCvSet;
import com.mysql.fabric.xmlrpc.base.Data;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MyStudyManageAction extends BaseAction {

    private CVSetEntityManage localCVSetEntity;

    private CVSetManage localCVSetManage;
    //直播的
    private CvLiveManage localCvLiveManage;

    private ExamPropValFacade localExamPropValFacade;

    // 查看学习的状态与进度
    
	private LogStudyCVSetManage localLogStudyCVSetManage;
	
	
	/***
	 * 讨论次数
	 */
	private CVSetUnitDiscussManage UnitDiscussManage;
	
	/**
	 * 单元指标
	 */
	private CVUnitManage cvUnitManage;
	
	private DiplomaService  diplomaService;
	//授课教师
	private CVSetEntityManage localCVSetEntityDAO;	
	
	
    public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}

	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }

    public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }

    public CVSetEntityManage getLocalCVSetEntity() {
        return localCVSetEntity;
    }

    public void setLocalCVSetEntity(CVSetEntityManage localCVSetEntity) {
        this.localCVSetEntity = localCVSetEntity;
    }

    public CVSetManage getLocalCVSetManage() {
        return localCVSetManage;
    }

    public void setLocalCVSetManage(CVSetManage localCVSetManage) {
        this.localCVSetManage = localCVSetManage;
    }

    

	public void setDiplomaService(DiplomaService diplomaService) {
		this.diplomaService = diplomaService;
	}

	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

		
        SystemUser user = LogicUtil.getSystemUser(request);

        //When user didn't login.
        if (user == null) {
            return "fail";
        }

        // 申请学分操作	2017-01-13 han
        String mode = ParamUtils.getParameter(request, "mode", "");
        if (mode.equals("applycredit")) {
            Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);
            //学分申请返回状态
            String ret = "fail";
            try {
                LogStudyCvSet log = localCVSetEntity.getLogStudyCvSet(user.getUserId(), cvSetId);

                if (log != null) {
                    //判断是否通过
                    boolean b = localCVSetEntity.getIsPass(user.getUserId(), cvSetId);

                    if (b) {
                        //通过
                        log.setState(2);
                        log.setIs_apply_credit(2);
                        log.setApply_date(new Date());
                        //更新学习记录
                        if (localCVSetEntity.updateLogCVSet(log)) {
                            ret = "success";
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("error", e);
            }
            StrutsUtil.renderText(response, ret);
            return null;
        }
        if(mode.equals("del")){
        	// 删除学习记录
        /*	Map<String, String> map = new HashMap<String, String>();
        	try {*/
        		delMyStudyRecode(request, response, user);
        	/*	map.put("message", "success");
			} catch (Exception e) {
				// TODO: handle exception
				map.put("message", "fail");
			}
        	StrutsUtil.renderText(response, JsonUtil.map2json(map));*/
        }
        /*查看面授报名表*/
        if (mode.equals("face")) {
        	  Integer fid = ParamUtils.getIntParameter(request, "id", 0);
        	  getFaceEntry(request,response,user,fid);
		}
         /*项目评价*/
        if (mode.equals("comment")) {
      	  Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0);
      	  getComment(request,response,user,cvSetId);
		}
        /*保存评价的内容*/
        if (mode.equals("saveComment")) {
        	  Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0);
        	  saveComment(request,response,user,cvSetId);
  		}
       /* 查看评价*/
        if (mode.equals("checkComment")) {
        	  Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0);
        	  checkComment(request,response,user,cvSetId);
        	  return "checkComment";
		}
        if(mode.equals("tab2")){

            //查询我的已获得学分课程
            getMyStudyPerfectCV(request, response, user);

            request.setAttribute("userInfo", user);
            return "tab2";
        }
    	//查询我的学习中课程
        getMyStudyingCV(request, response, user);
        request.setAttribute("userInfo", user);
        return "success";
       
    }
    private String checkComment(HttpServletRequest request, HttpServletResponse response, SystemUser user, Long cvSetId) {
    	  Pager<CVSetScoreLog> pl = new Pager<CVSetScoreLog>();
          int currentPage;
		try {
			currentPage = PageUtil.getPageIndex2(request);
			pl.setPageOffset(currentPage);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}        
        
          pl.setUrl("myStudy.do");
          pl.setQueryString(request);
          
          CVSetScoreLog log = new CVSetScoreLog();
          log.setCvSetId(cvSetId);
          String grade = localCVSetManage.getGrade(cvSetId);
          double grad = Double.parseDouble(grade);
          double gr = (double)(Math.round(grad*100)/100.0);
          request.setAttribute("grade", gr);
          localCVSetManage.getCVSetScoreLogList(log, pl);
          //对项目评价过滤敏感词
          List<CVSetScoreLog> scoreList = pl.getList();
          SensitivewordFilter filter = new SensitivewordFilter();
          for (CVSetScoreLog scoreLog:scoreList) {
        	  String content =null;
        	  if (scoreLog.getScoreContent() != null) {
        	     content = filter.replaceSensitiveWord(scoreLog.getScoreContent(), 1, "*");
        		
			}
          	scoreLog.setLogDate(scoreLog.getScoreDate().toString());
          	scoreLog.setScoreContent(content);
          }
          pl.setList(scoreList);
          //----项目评价过滤敏感词汇end----
          {//用于初始化当前项目的学习状态 0.表示未开始学习 1.表示已经开始学习 2.表示已经结束学习
          	if(user != null){
  	        	LogStudyCVSet logStudyCVSet = new LogStudyCVSet();
  	        	logStudyCVSet.setCvSetId(cvSetId);
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
          request.setAttribute("pager", pl);
          request.setAttribute("comment",pl.getList());
          return "checkComment";
	}

	private void saveComment(HttpServletRequest request, HttpServletResponse response, SystemUser user, Long cvSetId) {

    	String score1 = request.getParameter("score1");
    	String score2 = request.getParameter("score2");
    	String score3 = request.getParameter("score3");
    	String score4 = request.getParameter("score4");
		String commentCont = request.getParameter("commentCont");
		//保存评价
 		CVSet query = new CVSet();
		query.setId(cvSetId);
		query.setCritiqueScore1(Double.valueOf(score1));
		query.setCritiqueScore2(Double.valueOf(score2));
		query.setCritiqueScore3(Double.valueOf(score3));
		query.setCritiqueScore4(Double.valueOf(score4));
		if (commentCont==null || commentCont =="") {
			query.setOpinion("此学员未发表评价内容");
		}else {
			query.setOpinion(commentCont);
		}
		localCVSetManage.updateCritiqueScoreLog(user.getUserId(), query);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success","success" );
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
	}

	private void getComment(HttpServletRequest request, HttpServletResponse response, SystemUser user,
			Long cvSetId) {
		CVSet cvSet = localCVSetManage.findCVSetById(cvSetId);
		
		Map<String,Object> map = new HashMap<String,Object>();
		String startDate = cvSet.getStart_date().toString();
		String endDate = cvSet.getEnd_date().toString();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("cvSet", cvSet);
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
	}

	/**
     * 查看面授报名表
     * @param request
     * @param response
     * @param user
     * @param fid
     */
    private void getFaceEntry(HttpServletRequest request, HttpServletResponse response, SystemUser user, Integer fid) {
    	//查找cvSetId 
        CVSetScheduleFaceTeach face = localCVSetManage.getFace(fid);
      //查找报名表数据
        Long cvSetId =face.getCv_set_id().longValue();
        CVSet cvSet = localCVSetManage.findCVSetById(cvSetId);
        //查学科
		Long propId = 0L;
		if(user.getProp_Id() != null)
		{
			propId = Long.valueOf(user.getProp_Id());
		}
		ExamProp exam1 = localExamPropValFacade.getSysPropVal(propId);//三级学科
		Long xueke2Id = localExamPropValFacade.getParentPropId(propId);
		ExamProp exam2 = localExamPropValFacade.getSysPropVal(xueke2Id);//二级学科
		Long xueke1Id = localExamPropValFacade.getParentPropId(xueke2Id);
		ExamProp exam3 = localExamPropValFacade.getSysPropVal(xueke1Id);//一级学科
		Map<String,Object> map = new HashMap<String,Object>();
		//转换时间
		String trainStartDate = face.getTrainStartTime();
		String trainEndDate = face.getTrainEndTime();
		map.put("faceteach", face);
		map.put("trainStartDate", trainStartDate);
		map.put("trainEndDate", trainEndDate);
		map.put("user", user);
		map.put("cvSet", cvSet);
		map.put("exam1", exam1);
		map.put("exam2", exam2);
		map.put("exam3", exam3);
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
	}
    
	/**
     * 查询我的学习中课程
     *
     * @param request
     * @param response
     * @param user
     * @throws Exception
     * @author Tiger
     * @time 2017-1-11
     * @update 2017-1-26
     */
    public void getMyStudyingCV(HttpServletRequest request, HttpServletResponse response, SystemUser user)
            throws Exception {
    	
    	Integer state = ParamUtils.getIntParameter(request, "state", 0);
    	
        LogStudyCvSet info = new LogStudyCvSet(); 
        info.setSystem_user_id(user.getUserId()); //使用人的ID
         //设置授课形式的1 远程项目
        info.setForma(1);
	
         //授课形式
        //Changed By Tiger.
        if(state != 0){
        	info.setState(state);	
        }
        
        //未申请 学分
        info.setIs_apply_credit(1);
        
        info.setServerName(request.getServerName());

        Pager<LogStudyCvSet> pl = new Pager<LogStudyCvSet>();
        int currentPage = PageUtil.getPageIndex2(request); //分页设置
        pl.setPageOffset(currentPage);
        pl.setPageSize(40);
        pl.setSortDirection(SortOrderEnum.DESCENDING);
        pl.setUrl("userInfo/MyStudyManage.do");
        pl.setQueryString(request);

        localCVSetEntity.getLogCVSetListFromUser2(info, pl, user);

        for (LogStudyCvSet cvSet : pl.getList()) {
            //项目负责人
            cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getCv_set_id(), 1));
            //所属机构
            cvSet.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet.getCv_set_id()));
        }
    
        
      //查询项目下的单元id集合
        for (LogStudyCvSet Log : pl.getList()) {
        	List<CVUnit> unitList = localLogStudyCVSetManage.queryCVUnitByProjectId(null,Log.getCv_set_id());
        	List<LogStudyCVUnit> logUnitList = localLogStudyCVSetManage.searchLogStudyCVUnit("5",Log.getCv_set_id(),user.getUserId());
        	// 已完成为2的列表
        	List<LogStudyCVUnit> fList = localLogStudyCVSetManage.searchLogStudyCVUnit("4",Log.getCv_set_id(),user.getUserId());
        	//判断是否评价过
        	CVSetScoreLog cssLog = new CVSetScoreLog() ; 
    		cssLog.setCvSetId(Log.getCv_set_id());
    		cssLog.setSystemUserId(user.getUserId());
    		boolean userIsExit = localCVSetManage.getCVSetScoreLogIsExist(cssLog);
    		Log.setCommentType(userIsExit);
        	//设置项目状态
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date now = new Date();
        	Date endDate = Log.getEnd_date();
	    	Date day1 =sdf.parse(sdf.format(now));
	    	Date day2 = sdf.parse(sdf.format(endDate));
        	if (differentDays(day1,day2) >  0) {
        		int statusStudy;
        		statusStudy = differentDays(now,endDate);
				Log.setStatusStudy(String.valueOf(statusStudy));
        	}else{
        		Log.setStatusStudy("0");
        	}
        	
        	//1.表示未开始学习  2.表示已经开始学习  3.表示已经结束学习
    		if(logUnitList!=null && logUnitList.size()==0){
    			//未开始
    			Log.setFstate(1);
    			Log.setPercentage(0);
    		}
    		if(unitList.size()>logUnitList.size() && logUnitList.size()>0){
    			//正在进行
    			Map<String, Integer> mapR = localCVSetEntity.doZBData(Log.getCv_set_id(), user.getUserId());
    			if(mapR!=null){
    				if(mapR.get("zbUnitsR")!=null&&mapR.get("zbUnitLogR")!=null){
    					Integer zbUnitsR = Integer.parseInt(mapR.get("zbUnitsR").toString());
    					Integer zbUnitLogR = Integer.parseInt(mapR.get("zbUnitLogR").toString());
    					if((unitList.size()-zbUnitsR)==(fList.size()-zbUnitLogR)){
    						Log.setPercentage(100);
    						Log.setFstate(3);
    					}else{
    						Log.setFstate(2);
    						Log.setState(1);
    						double  perce = DoubleUtil.Format(((fList.size()-zbUnitLogR+.0)/(unitList.size()-zbUnitsR+.0))*100);
    						Log.setPercentage(perce);
    					}
    				}
    			}else{
    				Log.setFstate(2);
    				Log.setState(1);
    				double  perce = DoubleUtil.Format(((fList.size()+.0)/(unitList.size()+.0))*100);
    				Log.setPercentage(perce);
    			}
    		}
    		if((unitList.size() == logUnitList.size()) && 
    				(unitList.size() > fList.size())){//添加单元对比相等情况----taoliang
    			//正在进行
    			Map<String, Integer> mapR = localCVSetEntity.doZBData(Log.getCv_set_id(), user.getUserId());
    			if(mapR!=null){
    				if(mapR.get("zbUnitsR")!=null&&mapR.get("zbUnitLogR")!=null){
    					Integer zbUnitsR = Integer.parseInt(mapR.get("zbUnitsR").toString());
    					Integer zbUnitLogR = Integer.parseInt(mapR.get("zbUnitLogR").toString());
    					if((unitList.size()-zbUnitsR)==(fList.size()-zbUnitLogR)){
    						Log.setPercentage(100);
    						Log.setFstate(3);
    					}else{
    						Log.setFstate(2);
    						Log.setState(1);
    						double  perce = DoubleUtil.Format(((fList.size()-zbUnitLogR+.0)/(unitList.size()-zbUnitsR+.0))*100);
    						Log.setPercentage(perce);
    					}
    				}
    			}else{
    				Log.setFstate(2);
    				Log.setState(1);
    				double  perce = DoubleUtil.Format(((fList.size()+.0)/(unitList.size()+.0))*100);
    				Log.setPercentage(perce);
    			}
    		}
			if (unitList.size() == fList.size()) {
				
				Log.setPercentage(100);
				Log.setFstate(3);
				
				
				/*
				// 已结束
				
				*//****
				 * 2017-03-08
				 * 程宏业
				 * 判断是否完成相应的指标
				 * ***//*
				CVSetUnitDiscuss discuss = new CVSetUnitDiscuss();
				boolean flag = true;
				for (LogStudyCVUnit logStudyCVUnit : logUnitList) {
					discuss.setCvsetId(logStudyCVUnit.getCvId());
					discuss.setCvUnitId(logStudyCVUnit.getCvUnitId());
					discuss.setSystemUserId(logStudyCVUnit.getSystemUserId());
					long count = UnitDiscussManage.CountCVSetUnitDiscuss(discuss);
					CVUnit cvunit = cvUnitManage.findCvunit(new CVUnit(
							logStudyCVUnit.getCvUnitId()));
					if (count < cvunit.getQuota() && cvunit.getPoint().equals(1)&& (!cvunit.getType().equals(1) && !cvunit.getType().equals(4))) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					Log.setPercentage(100);
					Log.setFstate(3);
				} else {
					Log.setFstate(2);
					Log.setPercentage(90);
					
				}
				
				
			**/}
		}
        request.setAttribute("state", state);
        request.setAttribute("pageMyStudy", pl);
        request.setAttribute("data", pl.getList());

//以下查找直播的内容*************************************************************************
        
        LogStudyCvSet sending = new LogStudyCvSet(); 
        sending.setSystem_user_id(user.getUserId()); //使用人的ID
         //设置授课形式的远程
        sending.setForma(1);
	
         //授课形式
        //Changed By Tiger.
        if(state != 0){
        	sending.setState(state);	
        }
        
        //未申请 学分
        sending.setIs_apply_credit(1);
        
        sending.setServerName(request.getServerName());

        Pager<LogStudyCvSet> send = new Pager<LogStudyCvSet>();
        localCVSetEntity.getLogCVSetListFromUser2(sending, send,user);

        for (LogStudyCvSet cvSet1 : send.getList()) {
            //项目负责人
            cvSet1.setManagerList(localCVSetManage.getManagerList(cvSet1.getCv_set_id(), 1));
            //所属机构
            cvSet1.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet1.getCv_set_id()));
        }
    
        
      //查询项目下的单元id集合
        for (LogStudyCvSet logStudy : send.getList()) {
        	List<CVUnit> unitList1 = localLogStudyCVSetManage.queryCVUnitByProjectId(null,logStudy.getCv_set_id());
        	List<LogStudyCVUnit> logUnitList1 = localLogStudyCVSetManage.searchLogStudyCVUnit("5",logStudy.getCv_set_id(),user.getUserId());
        	// 已完成为2的列表
        	List<LogStudyCVUnit> fList1 = localLogStudyCVSetManage.searchLogStudyCVUnit("4",logStudy.getCv_set_id(),user.getUserId());
        	//课程列表 cv_type = 2 直播
        	Integer cv_type =2;
            List<CV> cvList = localCVSetManage.getCvSending(logStudy.getCv_set_id(),cv_type);
        	//设置直播状态状态
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date now = new Date();
        	if (cvList.size() > 0) {
        		 for (CV cv : cvList) {
        			 if (cv.getId() != null) {
        				//查询直播时间
        	              CvLive live = localCvLiveManage.getCvLive(cv.getId());
        	                if (live != null) {
        	                	Date endDate = live.getInvalid_date();//失效时间
            	            	Date stateDate = live.getStart_date();//开始时间
            		   	    	Date day1 = sdf.parse(sdf.format(now));
            		   	    	Date day2 = sdf.parse(sdf.format(endDate));
            		   	    	Date day3 = sdf.parse(sdf.format(stateDate));
            					cv.setStartDate(stateDate);
            					cv.setEndDate(endDate);
            			           //查找授课教师
                				ExpertInfo expert = localCVSetEntityDAO.queryTeacherByCVId(cv.getId());
                				request.setAttribute("teacher", expert);
    	        	   	    	if (differentDays(day1,day3) >  0) { //未开始
    	        	   	    		cv.setType(0);
    		        	           	}else if(differentDays(day1,day3) <= 0 && differentDays(day1, day2)>= 0){ //直播中
    		        	           		cv.setType(1);
    		        	           	}else{ //已结束
    		        	           		cv.setType(2);
    		        	           	}
    	        				}
        	   
							}
        		 }
             	List<CV> newCVList = new ArrayList();
            	for(CV cv:cvList){
            		if(cv.getType()!=2){
            			newCVList.add(cv);
            		}
            	}
    	        logStudy.setCvList(newCVList);
			}

	        
        	//1.表示未开始学习  2.表示已经开始学习  3.表示已经结束学习
    		if(logUnitList1!=null && logUnitList1.size()==0){
    			//未开始
    			logStudy.setFstate(1);
    			logStudy.setPercentage(0);
    		}
    		if(unitList1.size()>logUnitList1.size() && logUnitList1.size()>0){
    			//正在进行
    			Map<String, Integer> mapR1 = localCVSetEntity.doZBData(logStudy.getCv_set_id(), user.getUserId());
    			if(mapR1!=null){
    				if(mapR1.get("zbUnitsR")!=null&&mapR1.get("zbUnitLogR")!=null){
    					Integer zbUnitsR1 = Integer.parseInt(mapR1.get("zbUnitsR").toString());
    					Integer zbUnitLogR1 = Integer.parseInt(mapR1.get("zbUnitLogR").toString());
    					if((unitList1.size()-zbUnitsR1)==(fList1.size()-zbUnitLogR1)){
    						logStudy.setPercentage(100);
    						logStudy.setFstate(3);
    					}else{
    						logStudy.setFstate(2);
    						logStudy.setState(1);
    						double  perce1 = DoubleUtil.Format(((fList1.size()-zbUnitLogR1+.0)/(unitList1.size()-zbUnitsR1+.0))*100);
    						logStudy.setPercentage(perce1);
    					}
    				}
    			}else{
    				logStudy.setFstate(2);
    				logStudy.setState(1);
    				double  perce1 = DoubleUtil.Format(((fList1.size()+.0)/(unitList1.size()+.0))*100);
    				logStudy.setPercentage(perce1);
    			}
    		}
    		if((unitList1.size() == logUnitList1.size()) && 
    				(unitList1.size() > fList1.size())){//添加单元对比相等情况
    			//正在进行
    			Map<String, Integer> mapR1 = localCVSetEntity.doZBData(logStudy.getCv_set_id(), user.getUserId());
    			if(mapR1!=null){
    				if(mapR1.get("zbUnitsR")!=null&&mapR1.get("zbUnitLogR")!=null){
    					Integer zbUnitsR1 = Integer.parseInt(mapR1.get("zbUnitsR").toString());
    					Integer zbUnitLogR1 = Integer.parseInt(mapR1.get("zbUnitLogR").toString());
    					if((unitList1.size()-zbUnitsR1)==(fList1.size()-zbUnitLogR1)){
    						logStudy.setPercentage(100);
    						logStudy.setFstate(3);
    					}else{
    						logStudy.setFstate(2);
    						logStudy.setState(1);
    						double  perce1 = DoubleUtil.Format(((fList1.size()-zbUnitLogR1+.0)/(unitList1.size()-zbUnitsR1+.0))*100);
    						logStudy.setPercentage(perce1);
    					}
    				}
    			}else{
    				logStudy.setFstate(2);
    				logStudy.setState(1);
    				double  perce1 = DoubleUtil.Format(((fList1.size()+.0)/(unitList1.size()+.0))*100);
    				logStudy.setPercentage(perce1);
    			}
    		}
			if (unitList1.size() == fList1.size()) {
				
				logStudy.setPercentage(100);
				logStudy.setFstate(3);
			}
			}
        request.setAttribute("pageMySend", send);
        request.setAttribute("sending", send.getList());
        
 //直播部分结束##########################################################      
        
//以下查找面授的内容*******************************************************************
        
        LogStudyCvSet info1 = new LogStudyCvSet(); 
        info1.setSystem_user_id(user.getUserId()); //使用人的ID
         //设置授课形式的3 面授
        info1.setForma(3);
	
         //授课形式
        //Changed By Tiger.
        if(state != 0){
        	info1.setState(state);	
        }
        
        //未申请 学分
        info1.setIs_apply_credit(1);
        
        info1.setServerName(request.getServerName());

        Pager<LogStudyCvSet> pl1 = new Pager<LogStudyCvSet>();
        localCVSetEntity.getLogCVSetListFromUser2(info1, pl1, user);

        for (LogStudyCvSet cvSet1 : pl1.getList()) {
            //项目负责人
            cvSet1.setManagerList(localCVSetManage.getManagerList(cvSet1.getCv_set_id(), 1));
            //所属机构
            cvSet1.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet1.getCv_set_id()));
        }
    
        
      //查询项目下的单元id集合
        for (LogStudyCvSet Log1 : pl1.getList()) {
        	List<CVUnit> unitList1 = localLogStudyCVSetManage.queryCVUnitByProjectId(null,Log1.getCv_set_id());
        	List<LogStudyCVUnit> logUnitList1 = localLogStudyCVSetManage.searchLogStudyCVUnit("5",Log1.getCv_set_id(),user.getUserId());
        	// 已完成为2的列表
        	List<LogStudyCVUnit> fList1 = localLogStudyCVSetManage.searchLogStudyCVUnit("4",Log1.getCv_set_id(),user.getUserId());
        	//查找我的面授项目
        	List<CVSetScheduleFaceTeach> faceTeach = localCVSetManage.searchFaceTeach(Log1.getCv_set_id(),user.getUserId());
        	//设置项目状态
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date now = new Date();
        	for (CVSetScheduleFaceTeach face :faceTeach) {
            	Date endDate = face.getTrain_endtime();
            	Date stateDate = face.getTrain_starttime();
    	    	Date day1 = sdf.parse(sdf.format(now));
    	    	Date day2 = sdf.parse(sdf.format(endDate));
    	    	Date day3 = sdf.parse(sdf.format(stateDate));
				
    	    	if (differentDays(day1,day3) >  0) { //未开始
    				face.setFaceType(0);
            	}else if(differentDays(day1,day3) <=  0 && differentDays(day1, day2)>= 0){ //面授中
            		face.setFaceType(1);
            	}else{
            		face.setFaceType(2); //已结束
            	}
			}	
        	Log1.setFaceList(faceTeach); //添加faceTeach
	  
        	//1.表示未开始学习  2.表示已经开始学习  3.表示已经结束学习
    		if(logUnitList1!=null && logUnitList1.size()==0){
    			//未开始
    			Log1.setFstate(1);
    			Log1.setPercentage(0);
    		}
    		if(unitList1.size()>logUnitList1.size() && logUnitList1.size()>0){
    			//正在进行
    			Map<String, Integer> mapR1 = localCVSetEntity.doZBData(Log1.getCv_set_id(), user.getUserId());
    			if(mapR1!=null){
    				if(mapR1.get("zbUnitsR")!=null&&mapR1.get("zbUnitLogR")!=null){
    					Integer zbUnitsR1 = Integer.parseInt(mapR1.get("zbUnitsR").toString());
    					Integer zbUnitLogR1 = Integer.parseInt(mapR1.get("zbUnitLogR").toString());
    					if((unitList1.size()-zbUnitsR1)==(fList1.size()-zbUnitLogR1)){
    						Log1.setPercentage(100);
    						Log1.setFstate(3);
    					}else{
    						Log1.setFstate(2);
    						Log1.setState(1);
    						double  perce1 = DoubleUtil.Format(((fList1.size()-zbUnitLogR1+.0)/(unitList1.size()-zbUnitsR1+.0))*100);
    						Log1.setPercentage(perce1);
    					}
    				}
    			}else{
    				Log1.setFstate(2);
    				Log1.setState(1);
    				double  perce1 = DoubleUtil.Format(((fList1.size()+.0)/(unitList1.size()+.0))*100);
    				Log1.setPercentage(perce1);
    			}
    		}
    		if((unitList1.size() == logUnitList1.size()) && 
    				(unitList1.size() > fList1.size())){//添加单元对比相等情况----taoliang
    			//正在进行
    			Map<String, Integer> mapR1 = localCVSetEntity.doZBData(Log1.getCv_set_id(), user.getUserId());
    			if(mapR1!=null){
    				if(mapR1.get("zbUnitsR")!=null&&mapR1.get("zbUnitLogR")!=null){
    					Integer zbUnitsR1 = Integer.parseInt(mapR1.get("zbUnitsR").toString());
    					Integer zbUnitLogR1 = Integer.parseInt(mapR1.get("zbUnitLogR").toString());
    					if((unitList1.size()-zbUnitsR1)==(fList1.size()-zbUnitLogR1)){
    						Log1.setPercentage(100);
    						Log1.setFstate(3);
    					}else{
    						Log1.setFstate(2);
    						Log1.setState(1);
    						double  perce1 = DoubleUtil.Format(((fList1.size()-zbUnitLogR1+.0)/(unitList1.size()-zbUnitsR1+.0))*100);
    						Log1.setPercentage(perce1);
    					}
    				}
    			}else{
    				Log1.setFstate(2);
    				Log1.setState(1);
    				double  perce1 = DoubleUtil.Format(((fList1.size()+.0)/(unitList1.size()+.0))*100);
    				Log1.setPercentage(perce1);
    			}
    		}
			if (unitList1.size() == fList1.size()) {
				
				Log1.setPercentage(100);
				Log1.setFstate(3);
			}
			}
        request.setAttribute("pageMyStudy", pl1);
        request.setAttribute("data1", pl1.getList());
        
        
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
             return year2-year1 ;
         }else {
             return day2-day1;
         }
	}

	/**
     * 查询我的已获得学分课程
     *
     * @param request
     * @param response
     * @param user
     * @throws Exception
     * @author Tiger
     * @time 2017-1-11
     */
    public void getMyStudyPerfectCV(HttpServletRequest request, HttpServletResponse response, SystemUser user)
            throws Exception {
        LogStudyCvSet info = new LogStudyCvSet();
        info.setSystem_user_id(user.getUserId());
        //已完成
        info.setState(2);
        //学分已申请
        info.setIs_apply_credit(2);
        info.setServerName(request.getServerName());

        Pager<LogStudyCvSet> pl = new Pager<LogStudyCvSet>();
        int currentPage = PageUtil.getPageIndex2(request);
        pl.setPageSize(40);
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.DESCENDING);
        pl.setUrl("userInfo/MyStudyManage.do");
        pl.setQueryString(request);

        localCVSetEntity.getLogCVSetListFromUser2(info, pl, user);

        for (LogStudyCvSet cvSet : pl.getList()) {
            //项目负责人
            cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getCv_set_id(), 1));
            //所属机构
            cvSet.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet.getCv_set_id()));
        }
        //过滤项目为0学分，但已经申请学分的项目---taoliang
        List<LogStudyCvSet> list = new ArrayList<LogStudyCvSet>();
        list.addAll(pl.getList());
        for(LogStudyCvSet ly : pl.getList()){
        	if(ly.getScore() < 1){
        		list.remove(ly);
        	}
        }
        pl.setList(list);
        request.setAttribute("pageMyStudyPerfect", pl);
        request.setAttribute("dataPerfect", pl.getList());

    }
    
    /**
     * 
     * 删除我的学习记录
     * 方法名：delMyStudyrecode
     * 创建人：程宏业
     * 时间：2017-2-28-上午11:07:35 
     * 手机:15210211487 void
     * @exception 
     * @since  1.0.0
     */
    public void delMyStudyRecode(HttpServletRequest request, HttpServletResponse response,SystemUser user){
    	
    	
    	
    	localLogStudyCVSetManage.delLogStudy(Long.parseLong(request.getParameter("cv_set_id").trim()), user.getUserId());
    	
    	
    }

	public CVSetUnitDiscussManage getUnitDiscussManage() {
		return UnitDiscussManage;
	}

	public void setUnitDiscussManage(CVSetUnitDiscussManage unitDiscussManage) {
		UnitDiscussManage = unitDiscussManage;
	}

    public CVUnitManage getCvUnitManage() {
        return cvUnitManage;
    }

    public void setCvUnitManage(CVUnitManage cvUnitManage) {
        this.cvUnitManage = cvUnitManage;
    }

	
    
  public static void main(String[] args) {
	System.out.println(3.0/4.0);
}
    
  
  
    
}
