package com.hys.exam.struts.action.exam.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：删除试卷信息
 * 
 * 说明:
 */
public class PaperDeleteAction extends BaseAction {

	private ExamPaperFacade examPaperFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long[] ids = {ParamUtils.getLongParameter(request, "id", 0)};
		if (ids != null && ids.length > 0 && ids[0] > 0) {
			examPaperFacade.deleteExamPaper(ids) ;
			StrutsUtil.renderText(response, "success");
			return null;
		}
		
		String typeId = ParamUtils.getParameter(request, "typeId", null) ;
		long[] paperIds = ParamUtils.getLongParameters(request, "paperId", 0) ;
		
		if(paperIds != null && paperIds.length > 0){
			Long[] newPaperIds = ArrayUtils.toObject(paperIds) ;
			examPaperFacade.deleteExamPaper(newPaperIds) ;
		}
				
		String path = request.getContextPath() ;
		String url = path + "/exam/paper/paperList.do?typeId=" + typeId ;
		response.sendRedirect(url) ;
		
		return null ;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
}