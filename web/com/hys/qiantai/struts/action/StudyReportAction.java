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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.DoubleUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.service.local.impl.CVUnitVideoManageImpl;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.LogStudyCvSet;
import com.hys.qiantai.model.LogStudyStatMinutes;
import com.hys.qiantai.model.LogStudyStatistics;

/**
 * 
 * @author Han
 * @date 2017-01-12
 * @description 
 */
public class StudyReportAction extends BaseAction {


	private CVUnitVideoManageImpl localCVUnitVideoManage;

	public CVUnitVideoManageImpl getLocalCVUnitVideoManage() {
		return localCVUnitVideoManage;
	}

	public void setLocalCVUnitVideoManage(
			CVUnitVideoManageImpl localCVUnitVideoManage) {
		this.localCVUnitVideoManage = localCVUnitVideoManage;
	}


	CVSetEntityManage localCVSetEntity;
	private ExamPropValFacade localExamPropValFacade;
	private QualityModelManage localQualityModelManage;

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
		
		LogStudyStatistics sts = new LogStudyStatistics();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		if (!id.equals(0L)){
			LogStudyCvSet cvSets = localCVSetEntity.getCVSetEntityInfoById(id);
			sts.setCvSetId(cvSets.getId());
		}
		Long proid = ParamUtils.getLongParameter(request, "proid", 0L);
		if (!proid.equals(0L)){
			CVSet query = new CVSet();
			query.setServerName(request.getServerName());
			query.setStart_date(new Date());
			query.setStatus(5);// 已发布
			query.setId(proid);
			CVSet cvSet = localCVSetManage.getCVSetById(query);
			request.setAttribute("cvSet", cvSet);
		}
		
		if(id.equals(0L)&&proid.equals(0L)){
			int year = ParamUtils.getIntParameter(request, "year", -1);
			if(year!=-1){
				sts.setYear(year);
			}else{
				sts.setYear(0);
			}
			//get complete items
			LogStudyCvSet info  = new LogStudyCvSet();
			info.setsYear(sts.getYear());
			info.setStatus(2);
			info.setSystem_user_id(user.getUserId());
			PageList<LogStudyCvSet> page = new PageList<LogStudyCvSet>();
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = 4;
			page.setObjectsPerPage(pageSize);
			page.setPageNumber(currentPage);
			List<Long> completeProjectNumList = getCompleteProjectNum(user.getUserId(),request.getServerName(),sts.getYear());
			localCVSetEntity.getLogCVSetListFromUser(info,page,completeProjectNumList);
			request.setAttribute("pageStudyRecord", page);
			request.setAttribute("year", sts.getYear());
			//get start date and end date for display
			if (sts.getYear() == 0){
				request.setAttribute("startDate", "");
				request.setAttribute("endDate", DateUtil.format(new Date(), "yyyy-MM-dd").toString());
			}else{
				request.setAttribute("startDate", sts.getYear()+"-01-01");
				request.setAttribute("endDate", sts.getYear()+"-12-31");
			}
			//YHQ 2017-02-22
			if (!StringUtils.checkNull(user.getJob_Id())) {
				QualityModel qualityModel = new QualityModel() ;
				qualityModel.setId(Long.parseLong(user.getJob_Id())) ;
				qualityModel.setParentId(user.getUserId()) ;
				if (sts.getYear() != 0) qualityModel.setLevel(Long.parseLong(sts.getYear().toString())) ;
				List<QualityModel> abilityLevelOneList = localQualityModelManage.getStudyYearReportAbilityList(qualityModel) ;			
				request.setAttribute("abilityLevelOneList", abilityLevelOneList);
			}
		}
		
		sts.setUserId(user.getUserId());
		localCVSetEntity.getLogStudyStatisticsHcharts(sts);//YXT 2017-07-13 统计视频实际 
		//ready chart info
		if(sts.getMinutes()!=null){
			Map<String,List<Integer>> xAsixMap = new HashMap<String,List<Integer>>();
			Map<String,List<Integer>> yAsixMap = new HashMap<String,List<Integer>>();
			for(LogStudyStatMinutes item: sts.getMinutes()){
				String d = item.getD();
				Long m = item.getM();
				String[] dd = d.split("-");
				if(dd.length==3){
					List<Integer> ox = xAsixMap.get(dd[0]+"-"+dd[1]);
					List<Integer> oy = yAsixMap.get(dd[0]+"-"+dd[1]);
					if(ox!=null&&oy!=null){
						ox.add(Integer.parseInt(dd[2]));
						oy.add(Integer.parseInt(m.toString()));
						xAsixMap.put(dd[0]+"-"+dd[1], ox);
						yAsixMap.put(dd[0]+"-"+dd[1], oy);
					}else{
						List<Integer> oxx = new ArrayList<Integer>();
						List<Integer> oyy = new ArrayList<Integer>();
						oxx.add(Integer.parseInt(dd[2]));
						oyy.add(Integer.parseInt(m.toString()));
						xAsixMap.put(dd[0]+"-"+dd[1], oxx);
						yAsixMap.put(dd[0]+"-"+dd[1], oyy);
					}
				}
			}
			request.setAttribute("xAsixMap", JSONObject.fromObject(xAsixMap));
			request.setAttribute("yAsixMap", JSONObject.fromObject(yAsixMap));
		}
		// 百分比、学习进度   
		if((sts.getCompletedLogUnits()==null||sts.getCompletedLogUnits()==0)||(sts.getPoints()==null||sts.getPoints()==0)){
			sts.setPercentage(0);
		}else{
			double perce = DoubleUtil.Format(((sts.getCompletedLogUnits()+.0)/(sts.getPoints()+.0))*100);
			sts.setPercentage(perce);
		}
		request.setAttribute("userInfo", user);
		request.setAttribute("stat", sts);
		return "success";
	}

	private List<Long> getCompleteProjectNum(Long userId,String ServerName,Integer year){
		List<Long> rCodeList = new ArrayList<Long>();
		List<LogStudyCvSet> list = localCVSetEntity.getLogStudyCvSet(userId,ServerName,year);
		if(list!=null){
			for(LogStudyCvSet Log : list){
				if(Log.getState()==2){
					rCodeList.add(Log.getCv_set_id());
				}
			}
		}
		return rCodeList;
	}

	/**
	 * 
	 * @Title: NewMethod 
	 * @Description: 统计课程报表
	 * @author 程宏业 
	 * @date 2017-2-21上午10:41:51 
	 * @param @param mapping
	 * @param @param form
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */


	//学习统计报告方法

	public String  NewMethod(
			HttpServletRequest request, HttpServletResponse response){

		Map<String,Object> map = new HashMap<String,Object>();
		// 系统当前用户
		SystemUser user = LogicUtil.getSystemUser(request);		


		// 根据我所选择的项目id，用户ID,查询出对应项目的所有课程

		CVSet query = new CVSet();
		query.setServerName(request.getServerName());
		query.setStart_date(new Date());
		query.setStatus(5);// 已发布
		query.setId(NumberUtil.parseLong(request.getParameter("id")));
		CVSet cvSet = localCVSetManage.getCVSetById(query);

		if (cvSet != null) {
			//查询出所有的课程列表
			cvSet.setCvList(localCVSetManage.getCvList(cvSet.getId()));
			List<CV> licv =  localCVSetManage.getCvList(cvSet.getId());
			map.put("所有课程表", licv.size());
		}
		// 查询出我的对应项目课程的完成情况
		Set<Long> listset = new HashSet<Long>();
		List<CVUnit> unitList;
		unitList = localLogStudyCVSetManage.queryCVUnitByProjectId(null,cvSet.getId());
		List<com.hys.exam.model.LogStudyCVUnit> logUnitList = localLogStudyCVSetManage.searchLogStudyCVUnit("1",cvSet.getId(),user.getUserId());
		if(unitList.size()==logUnitList.size()){
			listset.add(cvSet.getId());
		}
		map.put("课程表已完成情况", listset.size());

		// 查询出全部课程对应的任务点
		Integer Allpoint = unitList.size();
		map.put("所有的任务点", Allpoint);

		// 查询出已完成的任务点
		Integer Finishpoint = logUnitList.size();	  
		map.put("已完成任务", Finishpoint);

		// map to json

		JSONObject jsonObject = JSONObject.fromObject(map);
		StrutsUtil.renderText(response, jsonObject.toString()); 

		return "success";

	}




	// getting and setting 


	// 查看学习的状态与进度

	private LogStudyCVSetManage localLogStudyCVSetManage;



	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}










}