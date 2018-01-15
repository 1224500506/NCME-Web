package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 取得ICD目录
 * @author Han
 *
 */
public class ExamIcdListAction extends BaseAction {
	
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
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		ExamICD prop = new ExamICD();
		{
			prop.setType(Integer.valueOf(type));
			prop.setName(sname);
			List<ExamICD> list = localExamPropValFacade.getIcdListByType(prop);
			
			request.setAttribute("type", type);
			request.setAttribute("propList", list);
			request.setAttribute("sname", sname);
		}		
		return "SUCCESS";
	}

}
