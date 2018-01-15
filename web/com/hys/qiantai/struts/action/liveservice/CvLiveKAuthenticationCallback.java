package com.hys.qiantai.struts.action.liveservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.transaction.annotation.Transactional;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLiveSignk;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.struts.form.CVForm;
import com.hys.exam.struts.form.CVUnitForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.struts.action.liveservice.comm.UserKeyHandleUtils;

import net.sf.json.JSONObject;

@SuppressWarnings("unused")
public class CvLiveKAuthenticationCallback extends BaseAction {

	private CvLiveManage localCvLiveManage;
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("===========展示互动回调K值验证进入==========");
		String returnK = request.getParameter("k");
		Long userId = 0L;
		userId = ParamUtils.getLongParameter(request, "uid", 0L);
		if(userId > 0L){
			userId = UserKeyHandleUtils.outputUserKey(userId);
		}
		System.out.println("展示互动回调K值为=============="+returnK);
		//根据k值校验用户权限块{
		List<CvLiveSignk> list = localCvLiveManage.getCvLiveSignkList(userId, returnK);
		if(list != null && list.size() > 0){
			CvLiveSignk cvls = new CvLiveSignk();
			cvls.setSignk_code(returnK);
			//K值认证通过后删除
			boolean isTrueOrfalse = localCvLiveManage.delCvLiveSignk(cvls);
			if(isTrueOrfalse){
				StrutsUtil.renderText(response, "pass");
			}else{
				StrutsUtil.renderText(response, "fail");
			}
		}else{
			StrutsUtil.renderText(response, "fail");
		}
        return null;
	}
	
	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}
	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}
}