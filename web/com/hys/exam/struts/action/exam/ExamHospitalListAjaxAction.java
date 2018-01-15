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

import com.hys.auth.util.ParamUtils;
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


public class ExamHospitalListAjaxAction extends BaseAction {
	
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
		String method = request.getParameter("method");
		
		//get Hospital Address by HospitalId . Alisa.
		
		if (method != null && method.equals("getHospitalAddress")) { 
			String hosId = request.getParameter("hospId");
			return getHospitalAddress(Long.valueOf(hosId), response);
		}
		
		// get HospiitalList by hospitalType and name
		
		String type = request.getParameter("parentId") == null ? "1" : request.getParameter("parentId");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		
		ExamProp prop = new ExamProp();
		prop.setType(20);
		List<ExamProp> region1list = localExamPropValFacade.getPropListByType(prop);

		ExamHospital host = new ExamHospital();
		{
			host.setPropId(Long.valueOf(type));
			host.setName(sname);
			List<ExamHospital> list = localExamPropValFacade.getHospitalListAll(host);

			JSONObject result = new JSONObject();
		
			result.put("list", list);
			result.put("state","closed");
			StrutsUtil.renderText(response, result.toString());
		}

		return null;
	}
	
	/**
	 * @author Tiger.
	 * @time 2017-1-13
	 * @param hospitalId
	 * @return String
	 * @Description : Get the hospital address by id.
	 */
	public String getHospitalAddress(Long hospitalId, HttpServletResponse response)
	{
		ExamHospital hospital = new ExamHospital();
		hospital.setId(hospitalId);
		
		//Get the hospital by id.
		String result = null;
		List<ExamHospital> list = localExamPropValFacade.getHospitalListAll(hospital);
		if (list != null && list.size() > 0)
		{
			if (list.get(0).getHospital_address() == null)
				result = "";
			else 
				result = list.get(0).getHospital_address();
		}
		StrutsUtil.renderText(response, result);
		return null;
	}
}
