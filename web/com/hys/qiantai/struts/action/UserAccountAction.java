package com.hys.qiantai.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.sessionfacade.AuthFacade;
import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.DateUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.FileLimitUtil;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.MD5Util;
import com.hys.exam.utils.StringUtils;
import com.hys.qiantai.struts.form.RegUserForm;

/**
 * 
 * @author Lee
 * @time 2016-12-05
 * User's Account Manage.
 *
 */
public class UserAccountAction  extends BaseActionSupport {
	
	private ExamPropValFacade localExamPropValFacade;
	private SystemUserManage localSystemUserManage;
	
	
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

	@Override
	public void setFacade(AuthFacade facade) {
		super.setFacade(facade);
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = RequestUtil.getParameter(request, "method");

		if (method.equals("update")){
			return updateUserInfo(mapping, form, request, response);
		}
		else if (method.equals("updatePass")){
			return updatePassword(request, response);
		}
		else if (method.equals("updateEmail")){
			return updateEmail(request, response);
		}
		else if (method.equals("updateMobile")){
			return updateMobile(request, response);
		}
		else if (method.equals("isDuplicate")){
			return isDuplicate(request, response);
		}else if(method.equals("checkHospitalName")){
			return checkHospitalName(request, response);
		}
		else
			return list(mapping, form, request, response);
		
	}
	
	

	/**
	 * @author user Lee
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 * Detail : Get the user's information and other info.
	 * Lee,2016-12-05
	 */
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemUser user =null;
/*		if(updateUser!=null){
			user = updateUser;
			
		}else{*/
			user = LogicUtil.getSystemUser(request);
		/*}*/
		if(user != null) {
			//Region1
			ExamProp prop = new ExamProp();
			prop.setType(20);
			List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);
			
			//Get Job
			//取得职称类型列表
			ExamProp jobclass = new ExamProp();
			jobclass.setType(24);
			List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);			
			
			//Get My Job
			ExamProp myJobClass = new ExamProp();
			myJobClass.setType(9);
			List<ExamProp> myJobClassList = localExamPropValFacade.getPropListByType(myJobClass);
			
			Integer workExtType = 0;
			for(ExamProp examProp: myJobClassList) {
				String userJobId = user.getJob_Id();
				String eId = String.valueOf(examProp.getId());
				if(userJobId != null && !userJobId.equals(eId)) {
					continue;
				}
				workExtType = examProp.getExt_type();					
			}
			myJobClass.setExt_type(workExtType);
			myJobClassList = localExamPropValFacade.getPropListByType(myJobClass);
			
			//Region2
			ExamPropQuery query  = new ExamPropQuery();	
			query.setSysPropId(user.getUserConfig().getUserProvinceId());
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			List<ExamProp> list = rprop.getReturnList();
			
			//Region3
			query  = new ExamPropQuery();
			query.setSysPropId(user.getUserConfig().getUserCityId());
			ExamReturnProp prop1 = localExamPropValFacade.getNextLevelProp(query);
			List<ExamProp> list1 = prop1.getReturnList();
			
			//Hospital
			ExamHospital host = new ExamHospital();
			List<ExamHospital> list2 = null ;
			if(user.getWork_unit_id() != null){//添加自己录入医院名称后，Work_unit_id为空情况--taoliang
				if(user.getUserConfig().getUserCountiesId() != null && !user.getUserConfig().getUserCountiesId().equals(0L))
				{
					//String userWorkID = String.valueOf(user.getWork_unit_id());
					host.setPropId(user.getUserConfig().getUserCountiesId());
					
					list2 = localExamPropValFacade.getHospitalListAll(host);
					String hospitalAddress = null;
					
					// get user hospitalAddress 
					if (list2 != null && list2.size() > 0)
					{
						for (ExamHospital item : list2){
							if (user.getWork_unit_id() == Integer.parseInt(item.getId().toString()))
							{
								hospitalAddress = item.getHospital_address();
								break;
							}
						}
						
						if (hospitalAddress != null)
						{
							request.setAttribute("hosAddress", hospitalAddress);
						}
						else request.setAttribute("hosAddress", null);
					}
					else request.setAttribute("hosAddress", null);
				}
			}
			
			//Xueke levels
			Long propId = 0L;
			if(user.getProp_Id() != null)
			{
				propId = Long.valueOf(user.getProp_Id());
			}
			
			Long xueke2Id = localExamPropValFacade.getParentPropId(propId);
			Long xueke1Id = localExamPropValFacade.getParentPropId(xueke2Id);
			
			//Xueke 1 level List 
			ExamProp query0  = new ExamProp();
			query0.setType(1);
			List<ExamProp> xueke1 = localExamPropValFacade.getPropListByType(query0);
			
			//Xueke 2 level List
			ExamPropQuery query1  = new ExamPropQuery();
			query1.setSysPropId(xueke1Id);
			ExamReturnProp propOne = localExamPropValFacade.getNextLevelProp(query1);
			List<ExamProp> xueke2 = propOne.getReturnList();
			
			//Xueke 3 level List
			ExamPropQuery query2  = new ExamPropQuery();
			query2.setSysPropId(xueke2Id);
			ExamReturnProp propTwo = localExamPropValFacade.getNextLevelProp(query2);
			List<ExamProp> xueke3 = propTwo.getReturnList();
			
			request.setAttribute("region1list", region1list);
			request.setAttribute("region2", list);
			request.setAttribute("region3", list1);
			//查每个区的医院
			request.setAttribute("hospital", list2);
			request.setAttribute("levelOne", xueke1Id);
			request.setAttribute("levelTwo", xueke2Id);
			request.setAttribute("xueke1", xueke1);
			request.setAttribute("xueke2", xueke2);
			request.setAttribute("xueke3", xueke3);
			request.setAttribute("userInfo", user);
			request.setAttribute("jobList", jobclassList);
			request.setAttribute("myJobList", myJobClassList);
			request.setAttribute("workExtType", String.valueOf(workExtType));
			request.setAttribute("userConfig", user.getUserConfig());
			request.setAttribute("hospitalAddress", user.getHospitalAddress());
			return "callback";
		}
		else 
		{
			return "fail";
		}
	}

	protected String updateUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RegUserForm regUserForm = (RegUserForm)form;		
		
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user == null) {
			return "fail";
		}
				
		SystemUser sysUser = new SystemUser();
		sysUser.setUserId(user.getUserId());
		sysUser.setRealName(regUserForm.getReal_name());
		sysUser.setAccountName(regUserForm.getAccount_name());
		sysUser.setSex(regUserForm.getSex());
		sysUser.setCertificateNo(regUserForm.getCertificate_no());
		sysUser.setEducation(regUserForm.getGrade());
		sysUser.setHealth_certificate(regUserForm.getWork_id());
		sysUser.setUserType(Constants.Student);
		sysUser.setProp_Id(String.valueOf(regUserForm.getXueke3()));
		sysUser.setJob_Id(String.valueOf(regUserForm.getWork_unit()));
		sysUser.setHospitalAddress(regUserForm.getHospital_region());
		Long fileName = Long.valueOf(user.getUserId()+""+DateUtils.getNowDateTime());
		if(!StringUtil.checkNull(regUserForm.getSelImage().getFileName())) {
//			String savefileName = FilesUtils.fileUpload(regUserForm.getSelImage(), request, Constants.UPLOAD_FILE_PATH_USER, fileName,"" ,"");
			
		     JSONObject jsonObject = FileLimitUtil.validate(regUserForm.getSelImage());
		     if(!((Integer)jsonObject.get("code")==1)){
		     	response.setCharacterEncoding("UTF-8");  
		     	response.setContentType("application/json; charset=utf-8");
		         response.getWriter().write(jsonObject.toString());
		         response.getWriter().flush();
		         response.getWriter().close();
		     	return null;
		     }
			String sourceKey = FilesUtils.fileUploadToCloud(regUserForm.getSelImage(), request,fileName);
			//String fileUploadStatus = FilesUtils.fileUploadToES(regUserForm.getSelImage(), request, fileName);//YHQ 2017-02-20 ESupload
			//String str = String.valueOf(fileName);
			//sysUser.setUser_image(Integer.valueOf(str));
			//用户头像，原user_image废弃，YHQ 2017-02-20
			sysUser.setUser_avatar(sourceKey) ;
		} else {
			//sysUser.setUser_image(user.getUser_image());
			sysUser.setUser_avatar(user.getUser_avatar()) ; //用户头像，原user_image废弃，YHQ 2017-02-20
		}
		
		Integer l_hos = regUserForm.getHospital_address();
		if(l_hos > 0) {
			sysUser.setWork_unit_id(regUserForm.getHospital_address());
			
			ExamHospital host = new ExamHospital();
			String hosLevel = String.valueOf(regUserForm.getHospital_address());
			host.setId(Long.valueOf(hosLevel));
			host.setHospital_address(regUserForm.getHospital_region());
			host.setRegionId(regUserForm.getRspropid());
			
			localExamPropValFacade.updateHospital(host);
		}else if(l_hos == -9){//-9为设置的默认值表示getHospital_address()没有被修改
			sysUser.setWork_unit_id(user.getWork_unit_id());
		}else {
			//医院表因现在有管理员自己维护，所以不对其做添加操作
			/*ExamHospital host = new ExamHospital();
			host.setName(regUserForm.getOther_hospital_name());
			host.setRegionId(regUserForm.getRspropid());
			String strH = String.valueOf(regUserForm.getHospital_level()==null?0:regUserForm.getHospital_level());
			host.setPropId(Long.valueOf(strH));
			host.setHospital_address(regUserForm.getHospital_region());
			
			Long hospitalId = localExamPropValFacade.addHospital(host);
			String strHosId = String.valueOf(hospitalId);
			sysUser.setWork_unit_id(Integer.valueOf(strHosId));	*/
			if(user.getWork_unit_id() != null){//如果是用户自己填写的单位则去掉原来跟单位的关联关系
				sysUser.setWork_unit_id(null);
			}
			sysUser.setOtherHospitalName(regUserForm.getOther_hospital_name());//用户存储用户自己选填的单位名称
		}
		
		SystemUserConfig sysUserConfig = new SystemUserConfig();
		sysUserConfig.setUserProvinceId(regUserForm.getRegion1());
		sysUserConfig.setUserCityId(regUserForm.getRegion2());
		sysUserConfig.setUserCountiesId(regUserForm.getRspropid());
		sysUser.setUserConfig(sysUserConfig);
		
		Integer userId = localSystemUserManage.update(sysUser);
		
		List<SystemUser> userList = localSystemUserManage.getListByItem(sysUser);
		
		if(userList != null && userList.size() > 0) {
			sysUser = userList.get(0);
    		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,sysUser);
		}
		
		request.setAttribute("userInfo", sysUser);
		
		HttpSession session = request.getSession() ;
		session.setAttribute(Constants.SESSION_TRAIN_ADMIN_USER, sysUser);
		response.sendRedirect("userAccount.do");
		return null;
	}
	
	/**
	 * @author Alisa
	 * @time 2017-01-11
	 * @param request
	 * @param response
	 * @return String
	 * Detail : Update User password.
	 */
	private String updatePassword(HttpServletRequest request,
			HttpServletResponse response) {
		
		SystemUser user = null;
		user = LogicUtil.getSystemUser(request);
		
		if (user == null) {
			StrutsUtil.renderText(response, "fail");
		}
		JSONObject result = new JSONObject();
		String password = request.getParameter("curPassword");
		String md5 = MD5Util.string2MD5(password);
		
		if(!user.getAccountPassword().equals(md5)){
			result.put("msg", "原密码错误，请重新输入！");
		}else{
			String md5_newPass = MD5Util.string2MD5(request.getParameter("newPassword"));
			user.setAccountPassword(md5_newPass);
			localSystemUserManage.updatePME(user);
			request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,user);
			result.put("success_msg", "修改密码成功！");
		}
		StrutsUtil.renderText(response, result.toString());
		return null;
	}
	/**
	 * @author Alisa
	 * @time 2017-01-11
	 * @param request
	 * @param response
	 * @return String
	 * Detail : Update User mobile phone number.
	 */
	private String updateMobile(HttpServletRequest request,
			HttpServletResponse response) {
		
		SystemUser user = null;
		user = LogicUtil.getSystemUser(request);
		
		if (user == null) {
			StrutsUtil.renderText(response, "fail");
		}
		JSONObject result = new JSONObject();
		String newMobile= request.getParameter("newMobile");
		user.setMobilPhone(newMobile);
		localSystemUserManage.updatePME(user);
		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,user);
		result.put("msg", "修改手机号码成功！");
		
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
	/**
	 * @author Alisa
	 * @time 2017-01-11
	 * @param request
	 * @param response
	 * @return String
	 * Detail : Update user's email.
	 */
	private String updateEmail(HttpServletRequest request,
			HttpServletResponse response) {
		
		SystemUser user = null;
		user = LogicUtil.getSystemUser(request);
		
		if (user == null) {
			StrutsUtil.renderText(response, "fail");
		}
		JSONObject result = new JSONObject();
		String newEmail = request.getParameter("newEmail");
		user.setEmail(newEmail);
		
		localSystemUserManage.updatePME(user);
		request.getSession().setAttribute(Constants.SESSION_TRAIN_ADMIN_USER,user);
		result.put("msg", "修改邮箱成功！");
	
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
	/**
	 * @author Tiger
	 * @time 2017-01-11
	 * @param request
	 * @param response
	 * @return String
	 * Detail : Check the account name is existing.
	 */
	private String isDuplicate(HttpServletRequest request,
			HttpServletResponse response) {
		
		SystemUser user = null;
		user = LogicUtil.getSystemUser(request);
		
		if (user == null) {
			StrutsUtil.renderText(response, "fail");
		}
		
		String userId = request.getParameter("userId");
		String accountName = request.getParameter("accountName");
		// Check user is existing.Tiger.
		SystemUser checkUser = new SystemUser();
		if(StringUtils.checkNull(userId))
		{
			checkUser.setUserId(user.getUserId());
			checkUser.setAccountName(accountName);
			
			List<SystemUser> checkList = localSystemUserManage.isDuplicate(checkUser);
					
			if(checkList != null && checkList.size() > 0)
			{
				StrutsUtil.renderText(response, "exist");	
			}
			else
			{
				StrutsUtil.renderText(response, "no");
			}
		}		
		return null;
	}
	
	private String checkHospitalName(HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONObject result = new JSONObject();
		String ttStr = request.getParameter("tt");
		if(!StringUtils.checkNull(ttStr))
		{
			ExamHospital host = new ExamHospital();
			host.setName(ttStr.trim());
			List<ExamHospital> hostList = localExamPropValFacade.getHospitalList(host);
			if(hostList != null && hostList.size() > 0){
				result.put("msg", "exist");
				StrutsUtil.renderText(response, result.toString());	
			}else{
				result.put("msg", "no");
				StrutsUtil.renderText(response, result.toString());
			}
		}		
		return null;
	}
}
