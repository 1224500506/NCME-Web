package com.hys.exam.struts.action.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;


public class ExamHospitalLevelAjaxAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type") == null ? "23" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		ExamProp prop = new ExamProp();
		prop.setName(sname);
		prop.setType(Integer.valueOf(type));
		
		List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
		
		// 医院级别的是取得所属医院数
		if (prop.getType() == 23){
			
			for (ExamProp item: list){
				ExamHospital host = new ExamHospital();
				host.setPropId(item.getId());
				List<ExamHospital> hostlist = localExamPropValFacade.getHospitalList(host);
				if (hostlist!=null)
					item.setExt_type(hostlist.size());
				else
					item.setExt_type(0);
			}
		}
		JSONObject result = new JSONObject();
	
		result.put("list", list);
		result.put("state","closed");
		StrutsUtil.renderText(response, result.toString());

		return null;
	}
}
