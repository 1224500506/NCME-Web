package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetEntity;


public class GetEntityInfosAjax extends BaseAction {
	private CVSetEntityManage localCVSetEntityDAO;
		
	
	public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String mode = request.getParameter("mode");
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long userId = ParamUtils.getLongParameter(request, "userId", 0L);
		Integer isFavorite = ParamUtils.getIntParameter(request, "favorite", 0);
		String content = request.getParameter("content");
		Double score = ParamUtils.getDoubleParameter(request, "score", 0);
		Integer status = ParamUtils.getIntParameter(request, "status", 0);
		
		CVSetEntity cvSetEntity= new CVSetEntity();
		cvSetEntity.setCvSetId(id);
		cvSetEntity.setUserId(userId);
		cvSetEntity.setFavorite(isFavorite);
		cvSetEntity.setContent(content);
		cvSetEntity.setScore(score);
		cvSetEntity.setStatus(status);
		
		localCVSetEntityDAO.addEntityInfos(cvSetEntity);
		JSONObject result = new JSONObject();
	
		//result.put("list", list);
		result.put("state","closed");
		StrutsUtil.renderText(response, result.toString());
		
		return null;
	}
}
