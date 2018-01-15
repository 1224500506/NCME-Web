package com.hys.exam.struts.action.CVSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.UserImage;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CVSetOrgManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.CVS_Form;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;

public class CVSetOrgAction extends BaseAction {

	private CVSetManage localCVSetManage;
	private CVSetOrgManage localCVSetOrgManage;
	private ExpertManageManage localExpertManageManage;
	private ExamPropValFacade localExamPropValFacade;

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
	
	public CVSetOrgManage getLocalCVSetOrgManage() {
		return localCVSetOrgManage;
	}

	public void setLocalCVSetOrgManage(CVSetOrgManage localCVSetOrgManage) {
		this.localCVSetOrgManage = localCVSetOrgManage;
	}

	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		String mode = RequestUtil.getParameter(request, "mode");
		SystemUser user = LogicUtil.getSystemUser(request);
		
		if(mode.equals("get_CVS")){
			return get_CVS(mapping, form, request, response);
		}
		if(mode.equals("get_CVS_list")){
			return get_CVS_list(mapping, form, request, response);
		}
		if(mode.equals("get_CVS_teacherList")){
			return get_CVS_teacherList(mapping, form, request, response);
		}		
		return "list";
	}

	private String get_CVS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		PeixunOrg curOrg = localCVSetOrgManage.getCurrentOrgById(id);
		curOrg.setFilePath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/" +curOrg.getId());
		curOrg.setPhotoPath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/photo/" +curOrg.getId());
		ExpertInfo expert = new ExpertInfo();
		expert.setWorkUnit(curOrg.getName());
		expert.setPersonage(1);
		
		List<ExpertInfo> teacherList = localExpertManageManage.getExpertList(expert);
		
		List<CVSet> Org_CVSet = new ArrayList<CVSet>();
		List<CVSet> Org_CVSet1 = new ArrayList<CVSet>();
		List<CVSet> Org_CVSet2 = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();
		CVSet queryCVSet1 = new CVSet();
		CVSet queryCVSet2 = new CVSet();
		
		List<PeixunOrg> oList = new ArrayList<PeixunOrg>();
		PeixunOrg org = new PeixunOrg();
		org.setId(id);
		oList.add(org);
		queryCVSet.setId(id);
		queryCVSet.setStart_date(new Date());
		queryCVSet.setOrganizationList(oList);		
		queryCVSet.setStatus(5);
		
		queryCVSet1.setForma(1);
		queryCVSet1.setStatus(5);
		queryCVSet1.setOrganizationList(oList);
		queryCVSet1.setStart_date(new Date());
		
		queryCVSet2.setForma(3);
		queryCVSet2.setStatus(5);
		queryCVSet2.setStart_date(new Date());
		queryCVSet2.setOrganizationList(oList);
		
		Org_CVSet = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet);
		Org_CVSet1 = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet1);
		Org_CVSet2 = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet2);
				
		for (CVSet cvSet:Org_CVSet) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
			cvSet.setStudentNum(localCVSetManage.getStudentNum(cvSet));
		}
		for (CVSet cvSet:Org_CVSet1) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
			cvSet.setStudentNum(localCVSetManage.getStudentNum(cvSet));
		}
		for (CVSet cvSet:Org_CVSet2) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
			cvSet.setStudentNum(localCVSetManage.getStudentNum(cvSet));
		}
		for (ExpertInfo ex:teacherList) {
			ex.setPhoto("//" + Constants.UPLOAD_FILE_PATH_EXPERT + "//" + ex.getPhoto());
		}
		
		request.setAttribute("Org_CVSet1", Org_CVSet1);
		request.setAttribute("Org_CVSet2", Org_CVSet2);
		request.setAttribute("Org_Teacher", teacherList);
		request.setAttribute("org", curOrg);
		request.setAttribute("id", queryCVSet.getId());
			
		request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + id);
		return "edit_page";
	}
	private String get_CVS_list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SystemUser user = LogicUtil.getSystemUser(request);
		List<PropUnit> courseList = new ArrayList<PropUnit>();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String type = request.getParameter("type");
		String CVSetName = request.getParameter("item_name");
		String xueke = request.getParameter("xueke");
		String sign = request.getParameter("sign");		
		String CVSetCostSort = request.getParameter("cost_sort");
		String CVSetScoreSort = request.getParameter("score_sort");
		String CVSetRecentCreate = request.getParameter("recent_create");		
		String CVSetLevel = ParamUtils.getParameter(request, "level_h", "-1");
		String CVSetSiteUrl = request.getHeader("host");//request.getParameter("sitelist");
		PeixunOrg curOrg = localCVSetOrgManage.getCurrentOrgById(id);
		curOrg.setFilePath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/" +curOrg.getId());
		curOrg.setPhotoPath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/photo/" +curOrg.getId());
		if(xueke != null && !xueke.equals(""))
		{			
			ExamPropQuery query  = new ExamPropQuery();
			
			List<ExamProp> list2;
			query.setSysPropId(Long.valueOf(xueke));
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			list2 = rprop.getReturnList();
			
			if(list2 != null && !list2.equals(""))
			{
				for (int i = 0; i < list2.size(); i++){
					
					List<ExamProp> list3;
					ExamPropQuery query2  = new ExamPropQuery();
					query2.setSysPropId(list2.get(i).getId());
					ExamReturnProp rprop2 = localExamPropValFacade.getNextLevelProp(query2);
					list3 = rprop2.getReturnList();
					for(ExamProp e : list3)
					{
						PropUnit pro = new PropUnit();
						pro.setId(e.getId());
						pro.setName(e.getName());
						courseList.add(pro);
					}
				}
			}
			PropUnit pro1 = new PropUnit();
			pro1.setId(Long.valueOf(xueke));
			courseList.add(pro1);
		}					
		//List<CVSet> Org_CVSet = new ArrayList<CVSet>();
		List<CVSet> Org_CVSet1 = new ArrayList<CVSet>();
		List<CVSet> Org_CVSet2 = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();
		CVSet queryCVSet1 = new CVSet();
		CVSet queryCVSet2 = new CVSet();
		// if user login on site, search by site and address.
		SystemSite systemSite = new SystemSite();
		systemSite.setDomainName(CVSetSiteUrl);
		
		List<SystemSite> siteList = new ArrayList<SystemSite>();
		siteList.add(systemSite);
		queryCVSet1.setSiteList(siteList);
		queryCVSet2.setSiteList(siteList);
		
		if(user != null) {
			queryCVSet1.setProvinceId(user.getUserConfig().getUserProvinceId());
			queryCVSet1.setCityId(user.getUserConfig().getUserCityId());
			queryCVSet2.setProvinceId(user.getUserConfig().getUserProvinceId());
			queryCVSet2.setCityId(user.getUserConfig().getUserCityId());
		}
		
		List<PeixunOrg> oList = new ArrayList<PeixunOrg>();
		PeixunOrg org = new PeixunOrg();
		org.setId(id);
		oList.add(org);
		queryCVSet.setOrganizationList(oList);
		queryCVSet.setId(id);
		queryCVSet.setName(CVSetName);
		queryCVSet.setStatus(5);
		
		queryCVSet1.setName(CVSetName);
		queryCVSet1.setForma(1);
		queryCVSet1.setStatus(5);
		queryCVSet1.setOrganizationList(oList);
		queryCVSet1.setCourseList(courseList);		
		queryCVSet1.setStatus(5);
		queryCVSet1.setStart_date(new Date());
		if(sign != null && sign.equals("0"))
		{
			queryCVSet1.setSign("指南");
		}
		else if(sign != null && sign.equals("1"))
		{
			queryCVSet1.setSign("公需科目");
		}		
		queryCVSet1.setCost_sort(CVSetCostSort);
		queryCVSet1.setScore_sort(CVSetScoreSort);
		queryCVSet1.setRecent_create(CVSetRecentCreate);
		if(CVSetLevel != null && !CVSetLevel.equals("-1"))
		{
			queryCVSet1.setLevel(Integer.valueOf(CVSetLevel));	
		}
		
		queryCVSet2.setName(CVSetName);
		queryCVSet2.setForma(3);
		queryCVSet2.setOrganizationList(oList);
		queryCVSet2.setCourseList(courseList);		
		queryCVSet2.setStatus(5);	
		queryCVSet2.setStart_date(new Date());
		if(sign != null && sign.equals("0"))
		{
			queryCVSet2.setSign("指南");
		}
		else if(sign != null && sign.equals("1"))
		{
			queryCVSet2.setSign("公需科目");
		}		
		queryCVSet2.setCost_sort(CVSetCostSort);
		queryCVSet2.setScore_sort(CVSetScoreSort);
		queryCVSet2.setRecent_create(CVSetRecentCreate);
		if(CVSetLevel != null && !CVSetLevel.equals("-1"))
		{
			queryCVSet2.setLevel(Integer.valueOf(CVSetLevel));	
		}
				
		PageList projectPg1 = new PageList();
		PageList projectPg2 = new PageList();
		
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		
		projectPg1.setObjectsPerPage(pageSize);
		projectPg1.setPageNumber(currentPage);
		
		projectPg2.setObjectsPerPage(pageSize);
		projectPg2.setPageNumber(currentPage);
		
			
		
		int Org_CVSetSize1 = localCVSetOrgManage.getCVSetOrgByOrgIdSize(queryCVSet1);
		projectPg1.setFullListSize(Org_CVSetSize1);
		Org_CVSet1 = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet1, projectPg1);
		
		int Org_CVSetSize2 = localCVSetOrgManage.getCVSetOrgByOrgIdSize(queryCVSet2);
		projectPg2.setFullListSize(Org_CVSetSize2);
		Org_CVSet2 = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet2, projectPg2);
	
		for (CVSet cvSet:Org_CVSet1) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
			cvSet.setStudentNum(localCVSetManage.getStudentNum(cvSet));
		}
		for (CVSet cvSet:Org_CVSet2) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
			cvSet.setStudentNum(localCVSetManage.getStudentNum(cvSet));
		}
		if(type.equals("1")){
			request.setAttribute("Org_CVSet", Org_CVSet1);
			request.setAttribute("pageOrgCVSet", projectPg1);
		}
		if(type.equals("2")){
			request.setAttribute("Org_CVSet", Org_CVSet2);
			request.setAttribute("pageOrgCVSet", projectPg2);
		}
		request.setAttribute("org", curOrg);
		request.setAttribute("id", queryCVSet.getId());
		request.setAttribute("type", type);
		request.setAttribute("ItemName", queryCVSet.getName());
		request.setAttribute("costSort", CVSetCostSort);
		request.setAttribute("scoreSort", CVSetScoreSort);
		request.setAttribute("status", queryCVSet.getStatus());
		request.setAttribute("xueke", xueke);
		request.setAttribute("sign", sign);		
		request.setAttribute("level", CVSetLevel);
		
		request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + id);
		return "project_page";
	}
	private String get_CVS_teacherList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		PeixunOrg curOrg = localCVSetOrgManage.getCurrentOrgById(id);
		curOrg.setFilePath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/" +curOrg.getId());
		curOrg.setPhotoPath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/photo/" +curOrg.getId());
		ExpertInfo expert = new ExpertInfo();
		expert.setWorkUnit(curOrg.getName());
		expert.setPersonage(1);
		
		ExamProp prop = new ExamProp();	
		prop.setType(1);
		List<ExamProp> propList = localExamPropValFacade.getPropListByType(prop);
		
		//取得专家目录
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		
		pl.setObjectsPerPage(pageSize);
		pl.setPageNumber(currentPage);
		int totalSize = localExpertManageManage.getExpertListSize(expert);
		pl.setFullListSize(totalSize);
		
		List<ExpertInfo> teacherList =  localExpertManageManage.getExpertList(expert,pl);
		
		ExamProp unit  = new ExamProp();
		unit.setType(9);
		List<ExamProp> unitlist = localExamPropValFacade.getPropListByType(unit);
		
		request.setAttribute("org", curOrg);
		request.setAttribute("Org_Teacher", teacherList);
		request.setAttribute("pageTeacher", pl);
		request.setAttribute("unitList", unitlist);
		request.setAttribute("id", curOrg.getId());
		request.setAttribute("propList", propList);
		return "teacher_page";
	}
}
