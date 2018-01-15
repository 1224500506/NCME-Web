package com.hys.qiantai.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.utils.MD5Util;


public class LoginAjaxAction extends BaseActionSupport {

	private SystemUserManage localSystemUserManage;
	private CVSetEntityManage localCVSetEntity;
	
	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public CVSetEntityManage getLocalCVSetEntity() {
		return localCVSetEntity;
	}

	public void setLocalCVSetEntity(CVSetEntityManage localCVSetEntity) {
		this.localCVSetEntity = localCVSetEntity;
	}

	protected String actionExecute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
			//静态页面异步请求验证用户是否登录
			String validate = request.getParameter("validate");
			if(validate!=null&&validate.equals("isLogin")){
				SystemUser user = LogicUtil.getSystemUser(request);
				JSONObject json = new JSONObject();
				if(user != null){
					json.put("validate", true);
					//用户登录后显示其头像
					if(user.getUser_avatar()!=null){
						json.put("userimge", user.getUser_avatar());
						json.put("usersex", user.getSex());
					}else{
						json.put("userimge", 1);
						json.put("usersex", user.getSex());
					}
				}else{
					json.put("validate", false);
					json.put("userimge", 1);
					json.put("usersex", user.getSex());
				}
        		StrutsUtil.renderText(response, json.toString());
	            return null;
			}
			String method = request.getParameter("mode");
			Long cvsId = ParamUtils.getLongParameter(request, "id", 0L);
			Long userId =ParamUtils.getLongParameter(request, "userId", 0L);
			String content = request.getParameter("content");
			Integer favor = ParamUtils.getIntParameter(request, "favor", 0);
			Double score = ParamUtils.getDoubleParameter(request, "score", 0);
			if(method != null && method.equals("update")){
				localCVSetEntity.updateStatus(cvsId, userId);
				StrutsUtil.renderText(response, "success");
				return null;				
			}
			if(method != null && method.equals("favor")){
				
				localCVSetEntity.updatefavor(cvsId, userId,favor);
				StrutsUtil.renderText(response, "success");
				return null;				
			}
			if(method != null && method.equals("cont")){
				localCVSetEntity.updateContent(cvsId, userId, content);
				StrutsUtil.renderText(response, "success");
				return null;			
			}
			if(method != null && method.equals("score")){
				localCVSetEntity.updateScore(cvsId, userId,score);
				StrutsUtil.renderText(response, "success");
				return null;			
			}
			else{
				String userData = request.getParameter("userData");
	        	String userPass = request.getParameter("password"); 
				String yanzhengma = (request.getParameter("yzm")!=null)?request.getParameter("yzm").toLowerCase():null;//将验证码字符串全部转换成小写
				Object rand = request.getSession().getAttribute("random");
				
	    		JSONObject result = new JSONObject();
	  
				if(rand == null)
				{
					result.put("inputMode",1);
					StrutsUtil.renderText(response, result.toString());
					return null;
				}
		        String random = rand.toString().toLowerCase();
		        SystemUser item = new SystemUser();
		        SystemUser user;
		        
		       if(yanzhengma != null && !yanzhengma.equals(random)){
		    	   result.put("userData", userData);
		    	   result.put("userPass", userPass);
		    	   result.put("errorMessage", "验证码错误，请核实后重新输入!");
		    	   result.put("inputMode",3);
		    	   result.put("result", "fail");
		           	StrutsUtil.renderText(response, result.toString());
		    	   return null;  
		        }  
		        else{
		        	if(userData != null && !userData.equals(""))
		        	{
		            	item.setAccountName(userData);
		            	if(localSystemUserManage.getListByItem(item) == null || localSystemUserManage.getListByItem(item).size() == 0)
		            	{
		            		item.setAccountName("");
		            		item.setMobilPhone(userData);	
		            	}
		            	if(localSystemUserManage.getListByItem(item) == null || localSystemUserManage.getListByItem(item).size() == 0)
		            	{
		            		item.setMobilPhone("");
		            		item.setEmail(userData);
		            	}
		            	
		            	if(localSystemUserManage.getListByItem(item) == null || localSystemUserManage.getListByItem(item).size() == 0)
		            	{
		            		result.put("userData", userData);
		            		result.put("userPass", userPass);
		            		result.put("errorMessage", "不存在此用户!");
		            		result.put("inputMode",1);
		            		result.put("result", "fail");
		            		StrutsUtil.renderText(response, result.toString());
		    	            return null;  
		            	}
		            	item.setAccountPassword(MD5Util.string2MD5(userPass));
		            	List<SystemUser> userList = localSystemUserManage.getListByItem(item);
		            	if(userList == null || userList.size() == 0)
		            	{
		            		result.put("userData", userData);
		            		result.put("userPass", userPass);
		            		result.put("errorMessage", "密码错误，请重新输入密码！");
		            		result.put("inputMode",2);
		            		result.put("result", "fail");
		            		StrutsUtil.renderText(response, result.toString());
		            		
		    	            return null;  
		            	}
		            	user = userList.get(0);
		        		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,user);
		        		result.put("result", "success");
		        		StrutsUtil.renderText(response, result.toString());
		        		return null;
			        }
			        else
			        {
			        	result.put("inputMode",1);
			        	StrutsUtil.renderText(response, result.toString());
	    	            return null;  
			        }
		        }
			}
	}
}
