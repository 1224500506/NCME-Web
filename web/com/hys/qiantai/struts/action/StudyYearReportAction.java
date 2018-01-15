package com.hys.qiantai.struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.DoubleUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.service.local.impl.CVUnitVideoManageImpl;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCvSet;
import com.hys.qiantai.model.LogStudyStatMinutes;
import com.hys.qiantai.model.LogStudyStatistics;
import com.hys.qiantai.model.MyStudyInfo;
import com.hys.qiantai.model.StudyRecordInfo;

public class StudyYearReportAction extends BaseAction {

	CVSetEntityManage localCVSetEntity;
	private ExamPropValFacade localExamPropValFacade;
	private QualityModelManage localQualityModelManage;
	
    private CVUnitVideoManageImpl localCVUnitVideoManage;
	
	CVSetManage localCVSetManage;
	public ExamPropValFacade getLocalExamPropValFacade() {

		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	public CVSetEntityManage getLocalCVSetEntity() {
		return localCVSetEntity;
	}

	public void setLocalCVSetEntity(CVSetEntityManage localCVSetEntity) {
		this.localCVSetEntity = localCVSetEntity;
	}
	
	public QualityModelManage getLocalQualityModelManage() {
		return this.localQualityModelManage;
	}

	public void setLocalQualityModelManage(QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		SystemUser user = LogicUtil.getSystemUser(request);		
		if(user == null){
			return "fail";
		}
		
		int year = ParamUtils.getIntParameter(request, "year", 0);
		//get statistics data
		LogStudyStatistics sts = new LogStudyStatistics();
		sts.setUserId(user.getUserId());
		sts.setYear(year);
		localCVSetEntity.getLogStudyStatistics(sts);

		//get complete items
		LogStudyCvSet info  = new LogStudyCvSet();
		info.setsYear(year);
		info.setStatus(2);
		info.setSystem_user_id(user.getUserId());
		
		PageList<LogStudyCvSet> page = new PageList<LogStudyCvSet>();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = 4;//PageUtil.getPageSize(request);
		
		page.setObjectsPerPage(pageSize);
		page.setPageNumber(currentPage);
		
		List<Long> completeProjectNumList = getCompleteProjectNum(user.getUserId(),request.getServerName(),year);
		
		localCVSetEntity.getLogCVSetListFromUser(info, page,completeProjectNumList);

//		List<LogStudyCvSet> returnAllList = page.getList();
		
		
		/***
		 * 查询对应的课程进度更改对应的数据值
		 * 2017-2-22 
		 * 程宏业
		 */
		
		/***开始部分****/
		
		// 所有的课程
		Integer Allcourse = 0;
		// 已完成的课程
		Integer Finishcourse = 0;
		// 所有的任务点
		Integer Allpoints = 0;
		// 已完成任务点
		Integer Finishpoints = 0;
		
		Long Sum_second = 0L;

		Long Sum_second_all = 0L;
		
		
		List<CVUnit> unitList;
		
		List<CVUnit> unitLists = null;

		List<CVUnit> unitListsAll = new ArrayList<CVUnit>();
		
		List<com.hys.exam.model.LogStudyCVUnit> logUnitLists = null;
		
		List<com.hys.exam.model.LogStudyCVUnit> logUnitListsAll = new ArrayList<LogStudyCVUnit>();

		// 根据我所选择的项目id，用户ID,查询出对应项目的所有课程
		CVSet query = new CVSet();
		query.setServerName(request.getServerName());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		if (year == 0){
			query.setName("");
			query.setStart_date(new Date());
		}
		else{
			 // 指定一个日期  
		    Date sdate = dateFormat.parse(year+"-01-01");  
		    Date edate = dateFormat.parse(year+"-12-31");  
			query.setStart_date(sdate);
			query.setEnd_date(edate);			
		}
		query.setStatus(5);// 已发布
		query.setId(user.getUserId()); 
		if(user.getUserConfig()!=null){
			query.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
		}
		
		List<CVSet> cvSetlist = localCVSetManage.getCVSetListByTime(query);

		if (cvSetlist.size() > 0) {

			for (CVSet cvSet : cvSetlist) {
				// 全部课程
				List<CV> licv = null;
				Set<Long> listset = new HashSet<Long>();
				// 查询出所有的课程列表
				cvSet.setCvList(localCVSetManage.getCvList(cvSet.getId()));
				licv = localCVSetManage.getCvList(cvSet.getId());
				Allcourse += licv.size();
				// 查询出我的对应项目课程的完成情况
				
			
				//查询出所有带任务点的单元
				unitList = localLogStudyCVSetManage
						.queryCVUnitByProjectId("1",cvSet.getId());
				//查询出所有带任务点完成的状态
				List<com.hys.exam.model.LogStudyCVUnit> logUnitList = localLogStudyCVSetManage
						.searchLogStudyCVUnit("1",cvSet.getId(), user.getUserId());
				
				// 查询出所有单元
				unitLists = localLogStudyCVSetManage
						.queryCVUnitByProjectId(null,cvSet.getId());
				unitListsAll.addAll(unitLists);

				//查询出所有单元的完成情况
				logUnitLists = localLogStudyCVSetManage
						.searchLogStudyCVUnit("4",cvSet.getId(), user.getUserId());
				logUnitListsAll.addAll(logUnitLists);
				
				/***
				 * 统计学习单元总耗时
				 */
				
				/**方法开始*/
				  
				  List<com.hys.exam.model.LogStudyCVUnit> logUnitTimeList = localLogStudyCVSetManage.searchLogStudyCVUnit(null,cvSet.getId(),user.getUserId());
				  
				  for (com.hys.exam.model.LogStudyCVUnit logStudyCVUnit : logUnitTimeList) {
					  List<LogStudyCVUnitVideo> timelist = localCVUnitVideoManage.findListByUserId(new LogStudyCVUnitVideo(logStudyCVUnit.getId(),logStudyCVUnit.getSystemUserId(),null,null));
					  for (LogStudyCVUnitVideo logStudyCVUnitVideo : timelist) {
						 if(!StringUtils.isBlank(logStudyCVUnitVideo.getVideoPlayLength().toString())){
					      Sum_second += logStudyCVUnitVideo.getVideoPlayLength(); 		 
					      Sum_second_all += logStudyCVUnitVideo.getVideoLength();
						 }
						
					}
					  
					  
					  
				}
				
				/**方法结束**/
					

				if (unitList.size() == logUnitList.size() && unitList.size()>0) {
					listset.add(cvSet.getId());
				}
				Finishcourse += listset.size();
				Allpoints += unitList.size();

				Finishpoints += logUnitList.size();

			}

		}
		
		
		  // 全部课程
		   sts.setCompletedCVs(Allcourse);
		   // 已完成的课程
		   sts.setCvs(Finishcourse);
		   // 全部任务点
		   sts.setCompletedLogUnits(Allpoints);
		   // 已完成任务点
		   sts.setPoints(Finishpoints);
		   
		   // 学习耗时
		   sts.setTime_consuming(Sum_second/60);
		   // 视频总时长
		   sts.setTime_consuming_all(Sum_second_all/60);
		   
		   // 百分比、学习进度   
		   if((logUnitListsAll== null || logUnitListsAll.size()==0) ||(unitListsAll==null || unitListsAll.size()==0)){
			   sts.setPercentage(0);
		   }else{
			   
			  double perce = DoubleUtil.Format(((logUnitListsAll.size()+.0)/(unitListsAll.size()+.0))*100);
			   sts.setPercentage(perce);
		   }
		   
	
		/***结束部分****/	
		
		
		
		

		request.setAttribute("userInfo", user);
		request.setAttribute("stat", sts);
		request.setAttribute("pageStudyRecord", page);
		request.setAttribute("year", year);
		
		//get start date and end date for display
		if (year == 0){
			request.setAttribute("startDate", "");
			request.setAttribute("endDate", DateUtil.format(new Date(), "yyyy-MM-dd").toString());
		}
		else{
			request.setAttribute("startDate", year+"-01-01");
			request.setAttribute("endDate", year+"-12-31");
		}
		
		//YHQ 2017-02-22
		if (!StringUtils.checkNull(user.getJob_Id())) {
			QualityModel qualityModel = new QualityModel() ;
			qualityModel.setId(Long.parseLong(user.getJob_Id())) ;
			qualityModel.setParentId(user.getUserId()) ;
			if (year != 0) qualityModel.setLevel(Long.parseLong(year + "")) ;
			
			List<QualityModel> abilityLevelOneList = localQualityModelManage.getStudyYearReportAbilityList(qualityModel) ;			
			request.setAttribute("abilityLevelOneList", abilityLevelOneList);
		}

		return "success";
			
		
	}
	
	
	private List<Long> getCompleteProjectNum(Long userId,String ServerName,Integer year){
		List<Long> rCodeList = new ArrayList<Long>();
		List<LogStudyCvSet> list = localCVSetEntity.getLogStudyCvSet(userId,ServerName,year);
		for(LogStudyCvSet Log : list){
			List<CVUnit> unitList = localLogStudyCVSetManage.queryCVUnitByProjectId(null,Log.getCv_set_id());
			List<LogStudyCVUnit> fList = localLogStudyCVSetManage.searchLogStudyCVUnit("4",Log.getCv_set_id(),userId);
			if (unitList.size() == fList.size()) {
				//将已完成的项目id放到集合中返回
				rCodeList.add(Log.getCv_set_id());
			}
		}
		return rCodeList;
	}
	
	// Getting and Setting 
		  
	  
	  // 查看学习的状态与进度
	  
		private LogStudyCVSetManage localLogStudyCVSetManage;
		
		
		
		public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
			return localLogStudyCVSetManage;
		}

		public void setLocalLogStudyCVSetManage(
				LogStudyCVSetManage localLogStudyCVSetManage) {
			this.localLogStudyCVSetManage = localLogStudyCVSetManage;
		}

		public CVUnitVideoManageImpl getLocalCVUnitVideoManage() {
			return localCVUnitVideoManage;
		}

		public void setLocalCVUnitVideoManage(
				CVUnitVideoManageImpl localCVUnitVideoManage) {
			this.localCVUnitVideoManage = localCVUnitVideoManage;
		}
	  
	
	
	
	
	
	
	
	
}