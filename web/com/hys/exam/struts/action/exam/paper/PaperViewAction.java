package com.hys.exam.struts.action.exam.paper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：查询试卷详细信息
 * 
 * 说明:
 */
public class PaperViewAction extends BaseAction {

	private ExamPaperFacade examPaperFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long paperId = ParamUtils.getLongParameter(request, "paperId", 0) ;
				
		ExamPaper paper = examPaperFacade.getExamPaperById(paperId);
		
		List<ExamQuestion> quesList = paper.getExamQuestionList();
		
		request.setAttribute("result", Constants.RESULT);
		request.setAttribute("paper", paper);
		request.setAttribute("quesList", quesList);

		if (ParamUtils.getParameter(request, "handle").equals("view")) return Constants.SUCCESS_1;
		return Constants.SUCCESS_2;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
}