package com.hys.qiantai.struts.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CvLiveSignup;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CvLiveSignupManage;
import com.hys.exam.util.JsonUtil;

public class CvLiveSignupAction extends BaseActionSupport{
private CvLiveSignupManage localCvLiveSignupManage;

public CvLiveSignupManage getLocalCvLiveSignupManage() {
	return localCvLiveSignupManage;
}

public void setLocalCvLiveSignupManage(CvLiveSignupManage localCvLiveSignupManage) {
	this.localCvLiveSignupManage = localCvLiveSignupManage;
}

@Override
protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
/*	String userId = request.getParameter("userId");
	String cvId = request.getParameter("cvId");*/
	SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
	String  cvsetId1= request.getParameter("cvsetId");
	
	cvsetId1=cvsetId1.trim();
	String str2="";
	if(cvsetId1 != null && !"".equals(cvsetId1)){
	for(int i=0;i<cvsetId1.length();i++){
	if(cvsetId1.charAt(i)>=48 && cvsetId1.charAt(i)<=57){
	str2+=cvsetId1.charAt(i);
	}
	}
	}
	
	
  //String substr=cvsetId1.substring(cvsetId1.length()-4,cvsetId1.length());
	Long cvsetId = Long.parseLong(str2);
	Long userId=user.getId();
	System.out.println("用户ID"+userId);
	Map<String,String> map = new HashMap<String,String>();
	CvLiveSignup cvLiveSignup  =new CvLiveSignup();
	cvLiveSignup.setStartDate(new Date());
	cvLiveSignup.setCvsetid(cvsetId);
	cvLiveSignup.setUserId(userId);

	
	Long id2 = localCvLiveSignupManage.saveCvLiveSignup(cvLiveSignup);
	if(userId==0){
		map.put("message", "errorMsg");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		
	}else{
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
	}

	return null;
}

}
