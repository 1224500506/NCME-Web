package com.hys.exam.struts.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetOrg;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetOrgManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.service.local.OrgManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.OrgForm;
import com.hys.exam.struts.form.SystemUserForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;


/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息管理
 * 
 * 说明:
 */
public class OrgManageAction extends AppBaseAction {
	//private SystemUserFacade systemUserFacade;
	
	private OrgManage localOrgManage;
	
	private CVSetOrgManage localCVSetOrgManage;
	
	private ExpertManageManage localExpertManageManage;
	
	private ExamPropValFacade localExamPropValFacade;
	
	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	public SystemUserManage getSystemUserManage() {
		return systemUserManage;
	}

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}

	private SystemUserManage systemUserManage ;

	public OrgManage getLocalOrgManage() {
		return localOrgManage;
	}

	public void setLocalOrgManage(OrgManage localOrgManage) {
		this.localOrgManage = localOrgManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	public CVSetOrgManage getLocalCVSetOrgManage() {
		return localCVSetOrgManage;
	}

	public void setLocalCVSetOrgManage(CVSetOrgManage localCVSetOrgManage) {
		this.localCVSetOrgManage = localCVSetOrgManage;
	}
	@Override
	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		if (method.equals("list")){

			return this.list(actionMapping, actionForm, request, response);
		}else if(method.equals("add")){
			
			return this.add(actionMapping, actionForm, request, response);
		
		}else if(method.equals("update")){
			
			return this.update(actionMapping, actionForm, request, response);
		
		}else if(method.equals("view")){
		
			return this.view(actionMapping, actionForm, request, response);		
		
		}else if (method.equals("getListByAjax")){
			return this.getlistByAjax(actionMapping, actionForm, request, response);
		}
		else {			
			return this.list(actionMapping, actionForm, request, response);
		}
	}
	
	private String getlistByAjax(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		OrgForm aform = (OrgForm)actionForm;
		PeixunOrg item = aform.getModel();
		List<PeixunOrg> data = localOrgManage.queryOrgList(item);				
		JSONObject result = new JSONObject();
		result.put("item", data);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	//查询
	protected String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		//OrgForm aform = (OrgForm)actionForm;
		//PeixunOrg item = aform.getModel();
		PeixunOrg item = new PeixunOrg();
		String typeList = request.getParameter("org_h");
		if(typeList != null)
		{
			item.setType(Integer.valueOf(typeList));	
		}
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		
		pl.setObjectsPerPage(4);
		pl.setPageNumber(currentPage);
		
		int totalSize = localOrgManage.getOrgListSize(item);
		pl.setFullListSize(totalSize);
		List<PeixunOrg> data = localOrgManage.queryOrgList(item,pl);
		pl.setList(data);
		
		
		List<CVSetOrg> orgList = new ArrayList<CVSetOrg>();
		
		for(PeixunOrg org : data)
		{
			
			//屏蔽写死的路径
			//org.setFilePath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/" +org.getId());
			//org.setPhotoPath("/" + Constants.UPLOAD_FILE_PATH_ORG + "/photo/" +org.getId());
			CVSetOrg newItem = new CVSetOrg();
			CVSet queryCVSet = new CVSet();
			
			ExpertInfo expert = new ExpertInfo();
			expert.setWorkUnit(org.getName());
			expert.setPersonage(1);
			List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
			teacherList = localExpertManageManage.getExpertList(expert);
			if (teacherList.size() > 6){
				teacherList.subList(0, 6);
			}
			newItem.setTeacherList(teacherList);
			
			newItem.setOrg(org);		
			
			List<PeixunOrg> oList = new ArrayList<PeixunOrg>();
			oList.add(org);
			queryCVSet.setOrganizationList(oList);			
			queryCVSet.setStatus(5);
			queryCVSet.setStart_date(new Date());
			queryCVSet.setForma(1);
			List<CVSet> tempList = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet);
			//屏蔽写死的路径
			/*for(CVSet cvset : tempList)
			{
				cvset.setFile_path("/" + Constants.UPLOAD_FILE_PATH_CVS + "/" + cvset.getId());
			}*/
			newItem.setProjectList(tempList);
			queryCVSet.setStatus(5);
			queryCVSet.setForma(3);
			tempList = localCVSetOrgManage.getCVSetOrgByOrgId(queryCVSet);
			//屏蔽写死的路径
			/*for(CVSet cvset : tempList)
			{
				cvset.setFile_path("/" + Constants.UPLOAD_FILE_PATH_CVS + "/" + cvset.getId());
			}*/
			newItem.setFaceList(tempList);
			orgList.add(newItem);
		}
		// request.setAttribute("item", item);
		request.setAttribute("typelist", typeList);
		request.setAttribute("orglist", orgList);
		request.setAttribute("pageList", pl);
		
		ExamProp prop = new ExamProp();
		prop.setType(Integer.valueOf(9));
		
		prop.setType(Integer.valueOf(20));
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);	
		
		request.setAttribute("region1list", region1list);
		
		return "list";
	}
	
	protected String add(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response)
	{
		OrgForm aform = (OrgForm)actionForm;		
		PeixunOrg item = aform.getModel();
		
		if(item.getName() != null){
			
			int result = localOrgManage.addPeixunOrg(item);
			
			if(result != 0)
			{
				StrutsUtil.renderText(response, "success");
			}
		}
		return null;
	}
	
	protected String view(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response)
	{
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		PeixunOrg data = localOrgManage.getItemById(id);

		JSONObject result = new JSONObject();
		result.put("item", data);
		StrutsUtil.renderText(response, result.toString());		
		
		return null;
	}
	
	
	protected String update(ActionMapping actionMappingm, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
	{
		OrgForm aform = (OrgForm)actionForm;
		PeixunOrg item = aform.getModel();
		if(item.getName() != "")
			localOrgManage.updatePeixunOrg(item);

		StrutsUtil.renderText(response, "success");	
		
		return null;
	}
		
}












