package com.hys.exam.struts.action.CVSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.form.CVS_Form;
import com.hys.exam.utils.FilesUtils;
import com.hys.framework.web.action.BaseAction;

public class CVDistirbuteManageAction extends BaseAction {

	private CVSetManage localCVSetManage;
	
	private SystemSiteManage localSystemSiteManage;
	
	private ExamPropValFacade localExamPropValFacade;	

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public SystemSiteManage getLocalSystemSiteManage() {
		return localSystemSiteManage;
	}

	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		String mode = RequestUtil.getParameter(request, "method");		
		
		if(mode.equals("dist")){
			return dist(mapping, form, request, response);
		}else if(mode.equals("save")){
			return save(mapping, form, request, response);
		}else if(mode.equals("del")){
			return del(mapping, form, request, response);
		}
		else
		{
			return list(mapping, form, request, response);
		}
	}
	private String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		List<CVSet> list = new ArrayList<CVSet>();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);		
		
		String CVSetCourseIds = ParamUtils.getParameter(request, "groupIds");
		String CVSetCourseName = request.getParameter("groupNames"); 
		String CVSetMaker = request.getParameter("maker");
		String CVSetDeli_man = request.getParameter("deli_man");
		String CVSetName = request.getParameter("name");		
		String CVSetStatus = request.getParameter("cvState");
		String CVSetSign = request.getParameter("cvSign");
		String CVSetLevel = request.getParameter("cvLevel");
		String CVSetScore = request.getParameter("score");		
		String CVSetSiteUrl = request.getParameter("sitelist");
		
		PropUnit propUnit = new PropUnit();
		propUnit.setName(CVSetCourseIds);
		
		List<PropUnit> propUnitList = new ArrayList<PropUnit>();
		propUnitList.add(propUnit);
		
		SystemSite systemSite = new SystemSite();
		systemSite.setDomainName(CVSetSiteUrl);
		
		List<SystemSite> siteList = new ArrayList<SystemSite>();
		siteList.add(systemSite);
		
		
		CVSet queryCVSet = new CVSet();		
		queryCVSet.setId(id);
		queryCVSet.setCourseList(propUnitList);
		queryCVSet.setMaker(CVSetMaker);
		queryCVSet.setDeli_man(CVSetDeli_man);
		queryCVSet.setName(CVSetName);
		queryCVSet.setSiteList(siteList);
		if(CVSetStatus != null && !CVSetStatus.equals("-1"))
		{
			queryCVSet.setStatus(Integer.parseInt(CVSetStatus));	
		}	
		if(CVSetSign != null && !CVSetSign.equals("-1"))
		{
			queryCVSet.setSign(CVSetSign);
		}	
		if(CVSetLevel != null && !CVSetLevel.equals("-1"))
		{
			queryCVSet.setLevel(Integer.valueOf(CVSetLevel));	
		}
		if(CVSetScore != null && !CVSetScore.equals(""))
		{
			queryCVSet.setScore(Double.valueOf(CVSetScore));	
		}	
				
		list = localCVSetManage.getCVSetList(queryCVSet);
		
		request.setAttribute("groupIds", CVSetCourseIds);
		request.setAttribute("groupNames", CVSetCourseName);
		request.setAttribute("maker", queryCVSet.getMaker());
		request.setAttribute("deli_man", queryCVSet.getDeli_man());
		request.setAttribute("name", queryCVSet.getName());
		request.setAttribute("status", queryCVSet.getStatus());
		request.setAttribute("sign", queryCVSet.getSign());
		request.setAttribute("level", queryCVSet.getLevel());
		request.setAttribute("score", queryCVSet.getScore());
		request.setAttribute("sitelist", queryCVSet.getSiteList().get(0).getDomainName());
		
		request.setAttribute("CVSet", list);
		
		return "list";
	}
	private String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long del_id = ParamUtils.getLongParameter(request, "id", 0L);	
		CVSet del_CVS = new CVSet();
		del_CVS.setId(del_id);
		boolean flag = localCVSetManage.deleteCVSet(del_CVS);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}

	private String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	Long id = ParamUtils.getLongParameter(request, "cvId", 0L);
	SystemUser user = LogicUtil.getSystemUser(request);
		if(id == null)
		{
			request.setAttribute("msg", "fail");
		}
		else
		{
			CVSet currentCV = localCVSetManage.getCVSetById(id,user);
			String siteIds = request.getParameter("siteIds");
			String pId = request.getParameter("provinceId");
			String cId = request.getParameter("cityId");
			String serial = request.getParameter("serialNumber");
			String level = request.getParameter("level");
			String score = request.getParameter("score");
			String sign = request.getParameter("signStr");
			String cost = request.getParameter("cost");
			String breakDays = request.getParameter("break_days");
			String sdate = request.getParameter("start_date_submit");
			String edate = request.getParameter("end_date_submit");
			
			if(pId != null && !pId.equals(""))
			{
				currentCV.setProvinceId(Long.valueOf(pId));	
			}
			if(cId != null && !cId.equals(""))
			{
				currentCV.setCityId(Long.valueOf(cId));
			}
			if(serial != null && !serial.equals(""))
			{
				currentCV.setSerial_number(serial);	
			}
			if(level != null && !level.equals(""))
			{
				currentCV.setLevel(Integer.valueOf(level));
			}
			if(score != null && !score.equals(""))
			{
				currentCV.setScore(Double.valueOf(score));	
			}
			if(sign != null && !sign.equals(""))
			{
				currentCV.setSign(sign);
			}
			if(cost != null && !cost.equals(""))
			{
				currentCV.setCost(Double.valueOf(cost));	
			}
			if(breakDays != null && !breakDays.equals(""))
			{
				currentCV.setBreak_days(Long.parseLong(breakDays));
			}
			if(sdate != null && !sdate.equals(""))
			{
				Date startDate = DateUtils.parseDate(sdate);
				currentCV.setStart_date(startDate);
			}
			if(edate != null && !edate.equals(""))
			{
				Date endDate = DateUtils.parseDate(edate);
				currentCV.setEnd_date(endDate);
			}
			for(CVSchedule schedule :  currentCV.getCVScheduleList())
			{
				if(schedule.getId() != null)
				{
					String sId = "item_"+schedule.getId().toString() + "_start_submit";
					String eId = "item_"+schedule.getId().toString() + "_end_submit";
					String scheduleStartDate = request.getParameter(sId);
					String scheduleEndDate = request.getParameter(eId);
					
					if(scheduleStartDate != null && !scheduleStartDate.equals(""))
					{
						schedule.setStart_date(DateUtils.parseDate(scheduleStartDate));
					}
					if(scheduleEndDate != null && !scheduleEndDate.equals(""))
					{
						schedule.setEnd_date(DateUtils.parseDate(scheduleEndDate));
					}	
				}				
			}
			List<Object> saveParams = new ArrayList();
			saveParams.add(currentCV);
			saveParams.add(siteIds);
			int result = localCVSetManage.updateDistribute(saveParams);
			if(result > 0 )
			{
				request.setAttribute("msg", "success");
			}
			else
			{
				request.setAttribute("msg", "fail");
			}
			
		}
		return "save";
	}

	private String dist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SystemUser user = LogicUtil.getSystemUser(request);
		Long id = ParamUtils.getLongParameter(request, "cvId", 0L);
		CVSet view;	
		view = localCVSetManage.getCVSetById(id,user);
		
		SystemSite querySite = new SystemSite();
		List<SystemSite> systemSite = localSystemSiteManage.getListByItem(querySite);
		
		//取得职称列表
		ExamProp prop = new ExamProp();
				
		//取得省列表
		prop.setType(Integer.valueOf(20));
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		request.setAttribute("View", view);
		request.setAttribute("siteList", systemSite);
		request.setAttribute("region1list", region1list);
		return "distribute";
	}

}
