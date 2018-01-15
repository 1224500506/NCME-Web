package com.hys.auth.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.NewsModel;
import com.hys.auth.util.NewsUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.SystemMessageManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.MD5Util;


public class AJAXActionLogin extends BaseActionSupport {

	private SystemUserManage localSystemUserManage;
	
	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	//保存学习记录(项目)Service接口
	private LogStudyCVSetManage localLogStudyCVSetManage;
	
	// 项目信息
	private CVSetManage localCVSetManage;
	
	// 系统消息
    private SystemMessageManage localSystemMesageManage;
	    
	
	
	/**
	 * @author   张建国
	 * @time     2017-02-17
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 通过AJAX方式登录系统
	 */
	protected String actionExecute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		//增加静态登录状态判断
		String loginCheck = request.getParameter("loginCheck");
		if (StringUtils.isNotBlank(loginCheck)&&loginCheck.equals("afasdlkfjadls")){
			SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
			JSONObject json = new JSONObject();
			if(user != null){
				json.put("message", "loginHasLogin");
				//用户登录后显示其头像
				if(user.getUser_avatar()!=null){
					json.put("userimge", user.getUser_avatar());
					json.put("usersex", user.getSex());
					json.put("telphone", user.getTelphone());
					json.put("username", user.getAccountName());
				}else{
					json.put("userimge", 1);
					json.put("usersex", user.getSex());
				}
			}else{
				json.put("userimge", 1);
			}
    		StrutsUtil.renderText(response, json.toString());
            return null;
		}
        	String userData = request.getParameter("userData");
        	String userPass = request.getParameter("password"); 
        	//将验证码字符串全部转换成小写
			String yanzhengma = (request.getParameter("yzm")!=null)?request.getParameter("yzm").toLowerCase():null;
			Object rand = request.getSession().getAttribute("random");    		

			if(rand == null){
				//Session中验证码为空
				map.put("message", "checkNumberNull");
				StrutsUtil.renderText(response, JsonUtil.map2json(map));
				return null;
			}
	        String random = rand.toString().toLowerCase();
	        SystemUser item = new SystemUser();
	        SystemUser user;
	       if(yanzhengma != null && !yanzhengma.equals(random)){
	    	    //验证码错误
	    	    map.put("message", "checkNumberError");
				StrutsUtil.renderText(response, JsonUtil.map2json(map));
				return null;
	        }else{
	        	if(userData != null && !userData.equals("")){
	            	item.setAccountName(userData);
	            	if(localSystemUserManage.getListByItem(item) == null || localSystemUserManage.getListByItem(item).size() == 0){
	            		item.setAccountName("");
	            		item.setMobilPhone(userData);	
	            	}
	            	if(localSystemUserManage.getListByItem(item) == null || localSystemUserManage.getListByItem(item).size() == 0){
	            		item.setMobilPhone("");
	            		item.setEmail(userData);
	            	}
	            	if(localSystemUserManage.getListByItem(item) == null || localSystemUserManage.getListByItem(item).size() == 0){
	            		//不存在此用户
	            		map.put("message", "userNull");
	    				StrutsUtil.renderText(response, JsonUtil.map2json(map));
	    				return null;
	            	}
	            	item.setAccountPassword(MD5Util.string2MD5(userPass));
	            	List<SystemUser> userList = localSystemUserManage.getListByItem(item);
	            	if(userList == null || userList.size() == 0){
	            		//密码错误
	            		map.put("message", "passwordError");
	    				StrutsUtil.renderText(response, JsonUtil.map2json(map));
	    				return null;
	            	}
	            	user = userList.get(0);
	        		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,user);
	        		/**2017-03-31
	        		 * 程宏业 添加项目过期通知
	        		 * ***/
	        		/***begin****/
	        		NewsUtil newsUtil = new NewsUtil();
	        		NewsModel model = new NewsModel();
	        		model.setLocalCVSetManage(localCVSetManage);
	        		model.setLocalLogStudyCVSetManage(localLogStudyCVSetManage);
	        		model.setLocalSystemMesageManage(localSystemMesageManage);
	        		newsUtil.CheckStudyTime(request, model);
	        		/***end****/
	        		//登录成功
	        		map.put("message", "success");
					StrutsUtil.renderText(response, JsonUtil.map2json(map));
					return null;
		        }else{
		        	//用户名为空
		        	map.put("message", "userNameNull");
    				StrutsUtil.renderText(response, JsonUtil.map2json(map));
    				return null;
		        	
		        }
	        }
	}

	
	
  // Geting and Setting 
	
	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public SystemMessageManage getLocalSystemMesageManage() {
		return localSystemMesageManage;
	}

	public void setLocalSystemMesageManage(
			SystemMessageManage localSystemMesageManage) {
		this.localSystemMesageManage = localSystemMesageManage;
	}
	
	
	
}
