package com.hys.qiantai.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.sessionfacade.AuthFacade;
import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.StringUtils;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.service.local.SystemMessageManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.MD5Util;
import com.hys.qiantai.model.SystemMessageModel;
import com.hys.qiantai.struts.form.RegUserForm;
import com.hys.vcode.VCUtil;

/**
 * 
 * 标题：用户登录
 * 
 * 作者：Lee，2016 12 05
 * 
 * 描述：
 * 
 * 说明:
 */

public class RegisterAction  extends BaseActionSupport {
	
	private ExamPropValFacade localExamPropValFacade;
	private SystemUserManage localSystemUserManage;
	private SystemSiteManage systemSiteManage;
	//系统消息管理
	private SystemMessageManage systemMessageManage;
	
	
	public SystemSiteManage getSystemSiteManage() {
		return systemSiteManage;
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}
	
	public SystemMessageManage getSystemMessageManage() {
		return systemMessageManage;
	}

	public void setSystemMessageManage(SystemMessageManage systemMessageManage) {
		this.systemMessageManage = systemMessageManage;
	}

	@Override
	public void setFacade(AuthFacade facade) {
		// TODO Auto-generated method stub
		super.setFacade(facade);
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = RequestUtil.getParameter(request, "method");
		//注册用户
		if (method.equals("register")){
			return addUser(mapping, form, request, response);
		}else if(method.equals("getVerificationCode")){
			return getVerificationCode(mapping, form, request, response);
		}else if(method.equals("checkMobile")){
			return checkMobile(mapping, form, request, response);
		}else if(method.equals("checkIdCard")){
			return checkIdCard(mapping, form, request, response);
		}else if(method.equals("getPassword")){
			return getPassword(mapping, form, request, response);
		}else if(method.equals("updatePassword")){
			return updatePassword(mapping, form, request, response);
		}else if(method.equals("checkUserName")){
			return checkUserName(mapping, form, request, response);
		}else if(method.equals("code")){
			String code = RequestUtil.getParameter(request, "code");
			return checkUserCode(mapping, form, request, response,code);
		}else if(method.equals("checkIsUnique")){
			return checkIsUnique(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}
	}
	
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");

		ExamProp prop = new ExamProp();
		prop.setName(sname);
		prop.setType(Integer.valueOf(type));		
		
		//取得职称类型列表
		ExamProp jobclass = new ExamProp();
		jobclass.setType(24);
		/*
		 * 2017.6.22注释掉 (xuehong)
		 */
		//List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);
		//request.setAttribute("classList", jobclassList);
		
		//获取人物类型下有学科的人物类型 (xuehong)
		List<ExamProp> jobclassList = localExamPropValFacade.getNewPropListByType(jobclass);
		request.setAttribute("classList", jobclassList);
		
		prop.setType(20);
		/*
		 * 2017.6.22注释掉 (xuehong)
		 */
		//List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
		
		List<ExamProp> region1list = localExamPropValFacade.getNewPropListByType(prop);
		ExamHospital host = new ExamHospital();
		{
			host.setPropId(Long.valueOf(type));
			host.setName(sname);
			List<ExamHospital> list = localExamPropValFacade.getHospitalList(host);
			
			request.setAttribute("type", type);
			request.setAttribute("propList", list);
			request.setAttribute("region1list", region1list);
			request.setAttribute("sname", sname);
		}		
		return "callback";
	}

	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2016-12-31
	 * @throws   Exception
	 * 方法说明： 用户注册方法
	 */
	protected String addUser(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		RegUserForm regUserForm = (RegUserForm)form;
		SystemUser sysUser = new SystemUser();
		Map<String,String> map = new HashMap<String,String>();
		int isExist = localSystemUserManage.checkUserName(regUserForm.getAccount_name());
		System.out.println(regUserForm.getAccount_name());
		if(isExist>0){
			map.put("message", "errorMsg");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return  null;
		}
		sysUser = new SystemUser();
		sysUser.setRealName(regUserForm.getReal_name());
		sysUser.setAccountName(regUserForm.getAccount_name());
		sysUser.setAccountPassword(regUserForm.getAccount_password());
		sysUser.setSex(regUserForm.getSex());
		sysUser.setGrassroot(regUserForm.getGrassroot());
		sysUser.setCertificateNo(regUserForm.getCertificate_no());
		sysUser.setCertificateType(regUserForm.getCertificate_type());
		sysUser.setMobilPhone(regUserForm.getMobile_phone());
		sysUser.setEmail(regUserForm.getEmail());
		sysUser.setEducation(regUserForm.getGrade());
		sysUser.setHealth_certificate(regUserForm.getWork_id());
		//注册添加站点标识
		//SystemSite site = new SystemSite();
		//site.setDomainName(request.getServerName());
		//sysUser.setSiteId(systemSiteManage.getListByItem(site).get(0).getId());
		/** 程宏业
		 * 2017-02-23
		 * 
		 * ***/
		//默认设置为学员类型
		sysUser.setUserType(1);
		sysUser.setProp_Id(String.valueOf(regUserForm.getXueke3()));
		sysUser.setJob_Id(String.valueOf(regUserForm.getWork_unit()));
		//设置医院地址
		sysUser.setHospitalAddress(regUserForm.getHospital_region());
		
		Integer l_hos = regUserForm.getHospital_address();
		if(l_hos > 0) {
			sysUser.setWork_unit_id(regUserForm.getHospital_address());
			ExamHospital host = new ExamHospital();
			String hosLevel = String.valueOf(regUserForm.getHospital_address());
			host.setPropId(Long.valueOf(hosLevel));
			host.setHospital_address(regUserForm.getHospital_region());
			localExamPropValFacade.updateHospital(host);
		}
		else {
			//如果用户选择其他自己填写单位名称则不更新医院表---taoliang
			/*ExamHospital host = new ExamHospital();
			host.setName(regUserForm.getOther_hospital_name());
			host.setRegionId(regUserForm.getRspropid());
			String strH = String.valueOf(regUserForm.getHospital_level());
			host.setPropId(Long.valueOf(strH));
			host.setHospital_address(regUserForm.getHospital_region());
			Long hospitalId = localExamPropValFacade.addHospital(host);
			String strHosId = String.valueOf(hospitalId);
			sysUser.setWork_unit_id(Integer.valueOf(strHosId));*/
			sysUser.setOtherHospitalName(regUserForm.getOther_hospital_name());//用户存储用户自己选填的单位名称
		}
		SystemUserConfig sysUserConfig = new SystemUserConfig();
		sysUserConfig.setUserProvinceId(regUserForm.getRegion1());
		sysUserConfig.setUserCityId(regUserForm.getRegion2());
		sysUserConfig.setUserCountiesId(regUserForm.getRspropid());
		sysUser.setUserConfig(sysUserConfig);
		Integer userId = localSystemUserManage.insert(sysUser);
		if(userId > 0){
			//用户注册成功后向信息表中插入消息
			SystemMessageModel systemMessage = new SystemMessageModel();
			systemMessage.setSystem_user_id(userId.longValue());
			systemMessage.setMessage_content(" 恭喜您注册成功！");
			systemMessageManage.SaveMessage(systemMessage);
			map.put("message", "success");
			map.put("id", userId.toString());
		}else{
			map.put("message", "fail");
		}	
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws 验证执业医师号是否存在
	 */
	protected String checkUserCode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,String code) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		
				List<SystemUser> list = localSystemUserManage.checkUserCode(code);
				if(list.size()>0){
					map.put("message", "yes");
				}else{
					map.put("message", "no");
				}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2016-12-31
	 * @throws   Exception
	 * 方法说明： 通过AJAX向用户发送验证码
	 */
	protected String getVerificationCode(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//短信发送的内容
		StringBuffer content = new StringBuffer("欢迎注册中国继续医学教育网，您的手机动态验证码为：");
		Map<String,Object> map = new HashMap<String,Object>();
		//获取要发送的手机号码
		String mobile_phone = request.getParameter("mobile_phone");
		int yzm = (int)((Math.random()*9+1)*100000);
		content.append(yzm);
		content.append("，5分钟有效，请立即填写！【NCME】");
		//判断手机号码是否合法
		if(mobile_phone!=null && !"".equals(mobile_phone)){
			//调用发送短信接口
			String sendResult = VCUtil.sendSMS(mobile_phone, content.toString(), "135", "");
			if(sendResult!=null && !"".equals(sendResult)){
				if("success".equals(sendResult)){
					map.put("message", "success");
					map.put("yzm", yzm);
				}else{
					map.put("message", "fail");
				}
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2016-12-31
	 * @throws   Exception
	 * 方法说明： 找回密码发送验证码
	 */
	protected String getPassword(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//短信发送的内容
		StringBuffer content = new StringBuffer("欢迎使用中国继续医学教育网密码找回服务，您的手机动态验证码为：");
		Map<String,Object> map = new HashMap<String,Object>();
		//获取要发送的手机号码
		String mobile_phone = request.getParameter("mobile_phone");
		int yzm = (int)((Math.random()*9+1)*100000);
		content.append(yzm);
		content.append("，5分钟有效，请立即填写！【NCME】");
		//判断手机号码是否合法
		if(mobile_phone!=null && !"".equals(mobile_phone)){
			//调用发送短信接口
			String sendResult = VCUtil.sendSMS(mobile_phone, content.toString(), "135", "");
			if(sendResult!=null && !"".equals(sendResult)){
				if("success".equals(sendResult)){
					map.put("message", "success");
					map.put("yzm", yzm);
				}else{
					map.put("message", "fail");
				}
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2017-01-15
	 * @throws   Exception
	 * 方法说明： 检测该手机号是否已经注册
	 */
	protected String checkMobile(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取要检测的手机号码
		String mobile_phone = request.getParameter("mobile_phone");
		Map<String,Object> map = new HashMap<String,Object>();
		if(mobile_phone!=null && !"".equals(mobile_phone)){
			//根据手机号码查询是否存在
			int isExist = localSystemUserManage.checkMobile(mobile_phone);
			if(isExist==0){
				map.put("message", "success");
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	
	
	
	/**
	 * 
	 * (检测用户名是否可用)
	 * 方法名：checkUserName
	 * 创建人：程宏业
	 * 时间：2017-2-28-上午9:48:57 
	 * 手机:15210211487
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 * @exception 
	 * @since  1.0.0
	 */
	
	protected String checkUserName(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String account_name = request.getParameter("account_name");
		Map<String,Object> map = new HashMap<String,Object>();
		if(account_name!=null && !"".equals(account_name)){
			//根据用户名查询是否可用
			int isExist = localSystemUserManage.checkUserName(account_name);
			if(isExist==0){
				map.put("message", "success");
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	
	
	
	
	
	/**
	 * @param    ActionMapping
	 * @author   张建国
	 * @return   String
	 * @time     2017-01-23
	 * @throws   Exception
	 * 方法说明： 修改账户密码
	 */
	protected String updatePassword(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		//获取要检测的手机号码
		String phone = request.getParameter("mobile_phone");
		//获取设置的新密码
		String password  = request.getParameter("password");
		//监测手机号码是否为空
		if(phone==null || "".equals(phone)){
			map.put("message", "fail");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return  null;
		}
		//监测是否有可修改的账号
		SystemAccount st = localSystemUserManage.getSystemAccount(phone);
		if(st!=null && st.getAccountId()>0){
			if(password!=null && !"".equals(password)){
				st.setAccountPassword(MD5Util.string2MD5(password));
				//执行修改
				localSystemUserManage.updatePassword(st);
				map.put("message", "success");
			}else{
				map.put("message", "fail");
			}
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2017-01-15
	 * @throws   Exception
	 * 方法说明： 检测该身份证号码是否已经注册
	 */
	protected String checkIdCard(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取要检测的手机号码
		String idCard = request.getParameter("idCard");
		Map<String,Object> map = new HashMap<String,Object>();
		if(idCard!=null && !"".equals(idCard)){
			//根据手机号码查询是否存在
			int isExist = localSystemUserManage.checkIdCard(idCard);
			if(isExist==0){
				map.put("message", "success");
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}
	
	
	/**
	 * @return   String
	 * @time     2016-12-31
	 * @throws   Exception
	 * 方法说明： 生成验证码
	 */
	public String generateVerificationCode() throws Exception{
		int x;
        Random ne=new Random();
        x=ne.nextInt(9999-1000+1)+1000;
		return String.valueOf(x);
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @time     2017-01-15
	 * @throws   Exception
	 * 方法说明： 检测该邮箱是否已经注册
	 */
	protected String checkIsUnique(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取要检测的手机号码
		String email = request.getParameter("email");
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(email)){
			//根据手机号码查询是否存在
			int isExist = localSystemUserManage.checkEmail(email);
			if(isExist==0){
				map.put("message", "success");
			}else{
				map.put("message", "fail");
			}
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return  null;
	}

}
