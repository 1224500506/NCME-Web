package com.hys.auth.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.*;
import com.hys.exam.utils.CusAccessObjectUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.NewsModel;
import com.hys.auth.util.NewsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.utils.MD5Util;


public class LoginAction extends BaseActionSupport {

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
    private SystemLogManage systemLogManage;

	
	/**
	 * 用户登录
	 */
	protected String actionExecute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        	String userData = request.getParameter("userData");
        	String userPass = request.getParameter("password"); 
			String yanzhengma = (request.getParameter("yzm")!=null)?request.getParameter("yzm").toLowerCase():null;//将验证码字符串全部转换成小写
			Object rand = request.getSession().getAttribute("random");  
			if(rand == null)
			{
				request.setAttribute("inputMode",1);
				return "input";
			}
	        String random = rand.toString().toLowerCase();
	        SystemUser item = new SystemUser();
	        SystemUser user;
	        request.setAttribute("userData", userData);
    		request.setAttribute("userPass", userPass);
	        if(yanzhengma != null && !yanzhengma.equals(random)){
	            request.setAttribute("errorMessage", "验证码错误，请核实后重新输入!");
	            request.setAttribute("inputMode",3);
	            return "input";  
	        }else{
	        	if(userData != null && !userData.equals("")){
	        		if(isNumeric(userData) == true){
	        			item.setMobilPhone(userData);
	        		}else if(userData.indexOf("@")!= -1){
	        			item.setEmail(userData);
	        		}else{
	        			item.setAccountName(userData);
	        		}
	            	List<SystemUser> userList = localSystemUserManage.getListByItem(item);
		            if(userList==null||userList.size()==0){
	    	            request.setAttribute("errorMessage", "不存在此用户!");
	    	            request.setAttribute("inputMode",1);
	    	            return "input";  
	            	}else{
						user = userList.get(0);
	            		SystemConfigVO vo = localSystemUserManage.getLoginLimtVo();
	            		//做重置操作
	    	        	long  now = System.currentTimeMillis();
	    	        	Long last = user.getLoginErrorLastTime();

	            		String inputPwd=MD5Util.string2MD5(userPass);
	            		if(!inputPwd.equals(user.getAccountPassword())){
	            			Long first = user.getLoginErrorFirstTime();
	            			Integer loginErrorNum = user.getLoginErrorNum();
	            			//1 未错误过  2 重置过
	            			if(first == 0 && loginErrorNum ==0){
	            				user.setLoginErrorFirstTime(System.currentTimeMillis());
	            				user.setLoginErrorNum(loginErrorNum+1);
	            				if(vo.getLoginNum()==1){
	            					user.setLoginErrorLastTime(System.currentTimeMillis());
	            				}
	            			}else{
	            				if((now - first) > vo.getLockTime()*60*60*1000){//假设只有一次机会输入密码错误，那么first和last将会很近似，所以不做特殊处理，有误差，但可忽略
			    	        		//重置
		        					user.setLoginErrorFirstTime(0L);
		        					user.setLoginErrorLastTime(0L);
		            				user.setLoginErrorNum(0);
		            				localSystemUserManage.updateLoginErrors(user);
	            					user.setLoginErrorFirstTime(System.currentTimeMillis());
		            				user.setLoginErrorNum(1);
		            				if(vo.getLoginNum()==1){
		            					user.setLoginErrorLastTime(System.currentTimeMillis());
		            				}
	            				}else{
	            					if(loginErrorNum == vo.getLoginNum()){
	            						request.setAttribute("errorMessage", "用户被锁定，请"+vo.getLockTime()+"小时后再登录！");
	         		    	            request.setAttribute("inputMode",2);
	         		    	            return "input";
	            					}else{
	            						//最后一次登录错误
	            						if(loginErrorNum == (vo.getLoginNum()-1)){
	            							user.setLoginErrorLastTime(now);
	            						}
	            						user.setLoginErrorNum(loginErrorNum+1);
	            					}
	            					
	            				}
	            			}

	            			localSystemUserManage.updateLoginErrors(user);
	            			
		    	            request.setAttribute("errorMessage", "密码错误，请重新输入密码,您还有"+(vo.getLoginNum()-user.getLoginErrorNum())+"次机会！");
		    	            request.setAttribute("inputMode",2);
		    	            return "input";
	            		}else{//密码输入正确
	            			
	            			//1.已锁定，但是超过限制时间可以重置限制
	            	       	if( last != 0 && ((now - last) > vo.getLockTime()*60*60*1000)){
		    	        		//重置
	        					user.setLoginErrorFirstTime(0L);
	        					user.setLoginErrorLastTime(0L);
	            				user.setLoginErrorNum(0);
	            				localSystemUserManage.updateLoginErrors(user);
		    	        	}else if(last != 0 && ((now - last) <=vo.getLockTime()*60*60*1000)){
		    	        		request.setAttribute("errorMessage", "用户被锁定，请"+vo.getLockTime()+"小时后再登录！");
     		    	            request.setAttribute("inputMode",2);
     		    	            return "input";
		    	        	}/*else if(last == 0){
		    	        		//重置
	        					user.setLoginErrorFirstTime(0L);
	        					user.setLoginErrorLastTime(0L);
	            				user.setLoginErrorNum(0);
		    	        	}
	        				localSystemUserManage.updateLoginErrors(user);*/

	            		}
	            		if(2==user.getAccount_status()){
		    	            request.setAttribute("errorMessage", "用户被停用，请联系管理员！");
		    	            request.setAttribute("inputMode",2);
		    	            return "input";
	            		}
	            		if(4==user.getUserType()){
	            			request.setAttribute("errorMessage", "该用户为医疗机构用户，禁止登录！");
	            			request.setAttribute("inputMode",2);
	            			return "input";
	            		}
	            	}
	            
	        		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,user);
		            //登录成功后记录登录日志
					if (null != user) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SystemLog systemLog = new SystemLog();
						systemLog.setUser_id(String.valueOf(user.getUserId()));
						systemLog.setOperate_login_name(user.getRealName());
						systemLog.setOperate_platform("qiantai");
						systemLog.setRequest_url(request.getRequestURI());
						systemLog.setVisit_ip(CusAccessObjectUtil.getIpAddress(request));
						systemLog.setOperate_time(sdf.format(new Date()));
						systemLog.setCreate_time(sdf.format(new Date()));
						systemLog.setModule_name("登录");
						systemLog.setOperate_type("login");
						systemLog.setOperate_context("登录中国继续医学教育网成功！");
						try {
							systemLogManage.addSystemLog(systemLog);
						} catch (Exception e){
							logger.error("添加审计日志期间发生异常:" + e.getMessage());
						}
					}
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
	        		return Constants.SUCCESS;
		        }else{
		        	request.setAttribute("inputMode",1);
		        	return "input";
		        }
	        }
	}
	
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}


    // Getting and Setting 
	
	
	
	
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

	public SystemLogManage getSystemLogManage() {
		return systemLogManage;
	}

	public void setSystemLogManage(SystemLogManage systemLogManage) {
		this.systemLogManage = systemLogManage;
	}
}
