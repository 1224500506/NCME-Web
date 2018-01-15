package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetEntity;

/**
 * 学科管理
 * @author iU
 *
 */
public class GetEntityInfos extends BaseAction {
	private CVSetEntityManage localCVSetEntityDAO;

	private CVSetManage localCVSetManage;	

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	
	public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO USER CODE
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		List<CVSet> list = new ArrayList<CVSet>();
		
		CVSet queryCVSet = new CVSet();	
		queryCVSet.setId(id);
		list = localCVSetManage.getCVSetList(queryCVSet);
		
		CVSetEntity cvSetEntity1 = new CVSetEntity();
		cvSetEntity1.setCvSetId(id);
		
		List<CVSetEntity> cvSetEntityList = localCVSetEntityDAO.getCVSetEntityInfo(cvSetEntity1);
		for (CVSetEntity cvSetEntity: cvSetEntityList) {
			for (int k=0; k<list.size(); k++) {
				CVSet cvSet = list.get(k);
				if(cvSetEntity.getCvSetId() != cvSet.getId())
					continue;
				cvSet.setFile_path("//" + Constants.UPLOAD_FILE_PATH_CVS + "//" + cvSet.getId());
				cvSet.getManagerList().get(0).setPhoto("//" + Constants.UPLOAD_FILE_PATH_EXPERT + "//" + cvSet.getManagerList().get(0).getPhoto());
				
				cvSetEntity.setName(cvSet.getName());
				cvSetEntity.setPhoto_url("//" + Constants.UPLOAD_FILE_PATH_USER + "//"+cvSetEntity.getUserId());
			}
		}
		//Get topic of users		
		
		JSONObject result = new JSONObject();
		
		result.put("list", cvSetEntityList);
		result.put("state","closed");
		StrutsUtil.renderText(response, result.toString());
		
		return null;
		
	}
}
