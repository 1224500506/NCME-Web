package com.hys.exam.struts.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.exam.struts.action.AppBaseAction;

/**
 * 
 * 标题：用户管理-获取用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemMenuManageAction extends AppBaseAction {
	private SystemMenuManage localSystemMenuManage;

	public SystemMenuManage getLocalSystemMenuManage() {
		return localSystemMenuManage;
	}

	public void setLocalSystemMenuManage(SystemMenuManage localSystemMenuManage) {
		this.localSystemMenuManage = localSystemMenuManage;
	}

	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		
		if (method.equals("search")) {
			return search(actionMapping, actionForm, request, response);
		} else if (method.equals("add")) {
			return addMenu(actionMapping, actionForm, request, response);
		}
		else if (method.equals("update")) {
				return updateMenu(actionMapping, actionForm, request, response);
		}
		else if (method.equals("updateState")) {
			return updateState(actionMapping, actionForm, request, response);
	}
		else {
			return search(actionMapping, actionForm, request, response);
		}
	}

	/**
	 * 查询
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String search(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		SystemMenu searchMenu = new SystemMenu();
		searchMenu.setName(request.getParameter("menuName"));
		
		String system = request.getParameter("system_type");
		if(system != null && !system.equals("全部") && !system.equals(""))
		{
			searchMenu.setSystem_type(system);
		}
		searchMenu.setMenu_type(request.getParameter("menu_type"));
		String state = request.getParameter("state");
		if(state != null && !state.equals(""))
		{
			searchMenu.setState(Integer.valueOf(state));	
		}
		
		

		List<SystemMenu> menuList = localSystemMenuManage.getMenuList(searchMenu);
		request.setAttribute("page", menuList);
		request.setAttribute("menuName", searchMenu.getName());
		request.setAttribute("system_type", system);
		request.setAttribute("menu_type", searchMenu.getMenu_type());
		request.setAttribute("state", searchMenu.getState());
		request.setAttribute("totalCount", menuList.size());
		return Constants.SUCCESS;
	}
	
	/**
	 * 修改
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addMenu(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		SystemMenu menu = new SystemMenu();
		menu.setSystem_type(request.getParameter("systemType"));
		menu.setMenu_type(request.getParameter("menuType"));
		menu.setName(request.getParameter("menuName"));
		menu.setUrl(request.getParameter("menuUrl"));
		menu.setState(1);
		Boolean result = localSystemMenuManage.addMenu(menu);
		if(result)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	public String updateMenu(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		SystemMenu menu = new SystemMenu();
		String id = request.getParameter("id");
		if(id != null && !id.equals(""))
		{
			menu.setId(Integer.valueOf(id));
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		menu.setSystem_type(request.getParameter("systemType"));
		menu.setMenu_type(request.getParameter("menuType"));
		menu.setName(request.getParameter("menuName"));
		menu.setUrl(request.getParameter("menuUrl"));
		String state = request.getParameter("state");
		if(state != null && !state.equals(""))
		{
			menu.setState(Integer.valueOf(state));	
		}		
		Boolean result = localSystemMenuManage.updateMenu(menu);
		if(result)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	public String updateState(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String temid = request.getParameter("id");
		Integer id = null;
		if(temid != null && !temid.equals(""))
		{
			id = Integer.valueOf(temid);
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		String temstate = request.getParameter("oldState");
		Integer oldState = null;
		if(temstate != null && !temstate.equals(""))
		{
			oldState = Integer.valueOf(temstate);
			if(oldState == 1)
			{
				oldState = 2;
			}
			else
			{
				oldState = 1;
			}
		}		
		Boolean result = localSystemMenuManage.updateState(id,oldState);
		if(result)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
}
