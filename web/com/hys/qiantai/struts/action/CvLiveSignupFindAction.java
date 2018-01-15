package com.hys.qiantai.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CvLiveSignupManage;
import com.hys.exam.util.JsonUtil;

public class CvLiveSignupFindAction extends BaseActionSupport{
	private CvLiveSignupManage localCvLiveSignupManage;

	public CvLiveSignupManage getLocalCvLiveSignupManage() {
		return localCvLiveSignupManage;
	}

	public void setLocalCvLiveSignupManage(CvLiveSignupManage localCvLiveSignupManage) {
		this.localCvLiveSignupManage = localCvLiveSignupManage;
	}
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if (user.getId()!=null) {
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
		   Long cvsetId = Long.parseLong(str2);
		   Map<String,String> map = new HashMap<String,String>();
		   
		  Long userId=localCvLiveSignupManage.cvLiveSignupFind(cvsetId);
		  System.out.println(userId+"hhhhwiwiwwi");
		  
		   if (userId==null) {
			   map.put("message", "success");
				StrutsUtil.renderText(response, JsonUtil.map2json(map));	  
		}else{
			map.put("message", "errorMsg");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));

		}
		   }else{
			   Map<String,String> map = new HashMap<String,String>(); 
			   map.put("message", "errorMsg2");
				StrutsUtil.renderText(response, JsonUtil.map2json(map));
		   }
		return null;
	}
	
}
	
	
