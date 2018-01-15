package com.hys.exam.struts.action;

import java.io.IOException;
import java.io.InputStream;
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
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.SystemApplication;
import com.hys.exam.model.SystemClient;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.SystemApplicationFacade;
import com.hys.exam.sessionfacade.local.SystemClientFacade;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.exam.struts.form.SystemUserForm;


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
public class SystemUserAction extends AppBaseAction {
	//private SystemUserFacade systemUserFacade;
	private SystemClientFacade systemClientFacade;
	private SystemApplicationFacade systemApplicationFacade;
	private SystemRoleFacade systemRoleFacade;
	
	private SystemUserManage systemUserManage ;

	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	@Override
	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String method = RequestUtil.getParameter(request, "method");
		if (method.equals("list")){

			return this.list(actionMapping, actionForm, request, response);
		}else if (method.equals("add")){
			
			return this.add(actionMapping, actionForm, request, response);
		}else if (method.equals("save")){
			
			return this.save(actionMapping, actionForm, request, response);
		}else if (method.equals("insert")){
			
			return this.insert(actionMapping, actionForm, request, response);
		}else if (method.equals("edit")){
			
			return this.edit(actionMapping, actionForm, request, response);
		}else if (method.equals("update")){
			
			return this.update(actionMapping, actionForm, request, response);
		}else if (method.equals("delete")){
			
			return this.delete(actionMapping, actionForm, request, response);
		}else if (method.equals("view")){
			
			return this.view(actionMapping, actionForm, request, response);
		}else if (method.equals("info")){
			
			return this.info(actionMapping, actionForm, request, response);
		}else if (method.equals("setState")){
			
			return this.setState(actionMapping, actionForm, request, response);
		}else if (method.equals("setPass")){
			
			return this.setPass(actionMapping, actionForm, request, response);
		}else {
			
			return this.list(actionMapping, actionForm, request, response);
		}
	}
	
	//页面常用数据
	protected void referenceData(HttpServletRequest request){
		List<SystemClient> clients = systemClientFacade.getListByItem(new SystemClient());
		List<SystemApplication> applications = systemApplicationFacade.getListByItem(new SystemApplication());
		List<SystemRole> roles = systemRoleFacade.getListByItem(new SystemRole());
		
		request.setAttribute("clients", clients );
		request.setAttribute("applications", applications );
		request.setAttribute("roles", roles );
	}
	
	//查询
	protected String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		String searchIds =  request.getParameter("searchUnitIds");
		item.setWorkUnit(searchIds);
		String searchNames = request.getParameter("searchUnitNames");
		//Page<SystemUser> page = PageUtil.getPageByRequest(request);
		@SuppressWarnings("static-access")
		Page<SystemUser> page = this.createPage(request, "systemUser");
		systemUserManage.querySystemUserList(page, item);

		//取得职称列表
		ExamProp prop = new ExamProp();
		prop.setType(Integer.valueOf(9));
		List<ExamProp> joblist = localExamPropValFacade.getPropListByType(prop);
		
		//取得省列表
		prop.setType(Integer.valueOf(20));
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		item.setWorkUnit(searchNames);
		request.setAttribute("page", page);
		request.setAttribute("item", item);
		request.setAttribute("joblist", joblist);
		request.setAttribute("region1list", region1list);
		request.setAttribute("searchUnitIds", searchIds);
		request.setAttribute("searchUnitNames", searchNames);
		referenceData(request);

		return "list";
	}
	
	//新增页面
	protected String add(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.saveToken(request) ;//设置token
		
		referenceData(request);
		
		return "add";
	}

	//保存
	protected String save(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
	//	item.setStatus(1);//正常
		if(this.isTokenValid(request,true)){
			systemUserManage.save(item);
				request.setAttribute("meg", "新增成功");
		} else {
				request.setAttribute("meg", "重复提交");
		}
		aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
	}
	
	protected String insert(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		item.setStatus(1);
		
		if (item != null) {
			systemUserManage.insert(item);
			StrutsUtil.renderText(response, "新增成功");
		} else {
			StrutsUtil.renderText(response, "重复提交");
		}
		return null;
/*		aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
*/	}
	
	protected String setPass(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String userId = request.getParameter("userId");
		String newPass = request.getParameter("newPass");
		String conPass = request.getParameter("conPass");
		
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser item = aform.getModel();
		
		if (item != null) {
			systemUserManage.insert(item);
			request.setAttribute("meg", "新增成功");
		} else {
			request.setAttribute("meg", "重复提交");
		}

		aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
	}

	//编辑页面
	protected String edit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "userId", 0L);
		SystemUser item = systemUserManage.getItemById(id);
		request.setAttribute("item", item);
		
		referenceData(request);
		
		return "edit";
	}

	//查看页面
	protected String view(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int rtype = ParamUtils.getIntParameter(request, "rtype", 0);
		SystemUser item = systemUserManage.getItemById(id);
		
		SystemUserConfig config = this.systemUserManage.getSystemUserConfigByUserId(id);

		if (rtype == 0){
			request.setAttribute("item", item);
			request.setAttribute("config", config);
			return "view";
		}
		else{
			JSONObject result = new JSONObject();
			result.put("item", item);
			result.put("config", config);
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
	}
	
	protected String info(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		SystemUser item = systemUserManage.getItemById(id);

		JSONObject result = new JSONObject();
		result.put("item", item);
		if (item.getRegDate() != null) result.put("regDate", item.getRegDate().toString());
		
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	
	//更新
	protected String update(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUserForm aform = (SystemUserForm)actionForm;
		SystemUser newitem = aform.getModel();
		SystemUser item = systemUserManage.getItemById(newitem.getUserId());
			if (item.getUserId()!=null && item.getUserId()>0){
				item.setRealName(newitem.getRealName());
				item.setDeptName(newitem.getDeptName());
				item.setMobilPhone(newitem.getMobilPhone());
				systemUserManage.update(item);
				StrutsUtil.renderText(response, "修改成功");
			}else StrutsUtil.renderText(response, "修改失败");
			return null;
/*			aform.setModel(new SystemUser());
		return this.list(actionMapping, aform, request, response);
*/		//	return "list";
	}

	//修改状态
	protected String setState(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		int state = ParamUtils.getIntParameter(request, "state", 0);
	 	SystemUser item = systemUserManage.getItemById(id);
			if (item.getUserId()!=null && item.getUserId()>0){
				item.setStatus(state);
				systemUserManage.update(item);
				StrutsUtil.renderText(response, "success");
			}
		return null;
	}

	//删除 
	protected String delete(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long[] selIdArr = ParamUtils.getLongParameters(request, "selId", 0L);
		if(selIdArr!=null && selIdArr.length>0){
			for (long id : selIdArr ){
				systemUserManage.delete(id,"id");
			}

		}
		return this.list(actionMapping, actionForm, request, response);
	}


	public void setSystemClientFacade(SystemClientFacade systemClientFacade) {
		this.systemClientFacade = systemClientFacade;
	}

	public void setSystemApplicationFacade(
			SystemApplicationFacade systemApplicationFacade) {
		this.systemApplicationFacade = systemApplicationFacade;
	}

	public void setSystemRoleFacade(SystemRoleFacade systemRoleFacade) {
		this.systemRoleFacade = systemRoleFacade;
	}

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}

}
