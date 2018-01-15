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
import com.hys.exam.model.system.SystemRoleMenuProp;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.exam.service.local.SystemRoleManage;
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
public class SystemRoleAddAction extends AppBaseAction {
	private SystemMenuManage localSystemMenuManage;
	private SystemRoleManage localSystemRoleManage;

	public SystemMenuManage getLocalSystemMenuManage() {
		return localSystemMenuManage;
	}

	public void setLocalSystemMenuManage(SystemMenuManage localSystemMenuManage) {
		this.localSystemMenuManage = localSystemMenuManage;
	}

	public SystemRoleManage getLocalSystemRoleManage() {
		return localSystemRoleManage;
	}

	public void setLocalSystemRoleManage(SystemRoleManage localSystemRoleManage) {
		this.localSystemRoleManage = localSystemRoleManage;
	}

	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		
		if (method.equals("list")) {
			return list(actionMapping, actionForm, request, response);
		} else if (method.equals("add")) {
			return addMenutoRole(actionMapping, actionForm, request, response);
		}
		else {
			return list(actionMapping, actionForm, request, response);
		}
	}

	public String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String roleId = request.getParameter("roleId");
		Integer rId = null;
		if(roleId != null && !roleId.equals(""))
		{
			rId = Integer.valueOf(roleId);
		}
		List<SystemRoleMenuProp> roleMenuList = localSystemRoleManage.getRoleMenuList(rId);
		SystemMenu searchMenu = new SystemMenu();
		List<SystemMenu> menuList = localSystemMenuManage.getMenuList(searchMenu);
		String roleMenuListStr = "";
		for(SystemRoleMenuProp p : roleMenuList)
		{
			roleMenuListStr += p.getMenuId() + ",";
		}
		request.setAttribute("roleMenuList", roleMenuListStr);
		request.setAttribute("menuList", menuList);
		request.setAttribute("roleId", rId);
		return Constants.SUCCESS;
	}
	
	public String addMenutoRole(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String rId = request.getParameter("roleId");
		String menuList = request.getParameter("menuIds");
		Integer roleId = null;
		if(rId != null && !rId.equals(""))
		{
			roleId = Integer.valueOf(rId);
		}
		int result = localSystemRoleManage.setRoleMenuProp(roleId,menuList);
		if(result > 0)
		{
			request.setAttribute("msg", "授予资源成功!");
		}
		else
		{
			request.setAttribute("msg", "授予资源失败!");
		}
		return Constants.INPUT;
	}
}
