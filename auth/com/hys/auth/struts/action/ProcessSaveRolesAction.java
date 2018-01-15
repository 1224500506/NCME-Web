package com.hys.auth.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysRoles;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.SystemRole;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;

/**
 * 保存角色
 * 
 * @author zdz
 * 
 */
public class ProcessSaveRolesAction extends BaseActionSupport {
	
	private SystemRoleFacade systemRoleFacade;
	public void setSystemRoleFacade(SystemRoleFacade systemRoleFacade) {
		this.systemRoleFacade = systemRoleFacade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleName = RequestUtil.getParameter(request, "name");
		String roleNameDesc = RequestUtil.getParameter(request, "nameDesc"); 
		Integer key = 0;
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(roleName) && StringUtils.isNotBlank(roleNameDesc)) {
			SystemRole tempRole = new SystemRole();
			List<SystemRole> list = systemRoleFacade.getListByItem(tempRole);
			for (SystemRole role : list) {
				if (role.getRoleName().equals(roleName) && role.getRoleName().equals(roleNameDesc)) {
					json.put("msg", "该角色标识已经存在！");
					StrutsUtil.renderText(response, json.toString());
					return null;
				}
			}
			SystemRole roles = new SystemRole();
			roles.setRoleName(roleName);
			roles.setRoleNameDesc(roleNameDesc);
			roles.setStatus(1);
			key = systemRoleFacade.save(roles);
		} else {
			json.put("msg", "请填写角色标识！");
			
		}	

		if (key > 0) {
			
			json.put("msg",  "角色[" + roleName + "]保存成功！");
			json.put("roleId",key);
			StrutsUtil.renderText(response, json.toString());
			return null;
		}
		return null;
	}

}
