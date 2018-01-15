package com.hys.exam.struts.action.exam.paper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.exam.struts.form.OlemPaperForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：查询试卷列表
 * 
 * 说明:
 */
public class PaperListAction extends BaseAction {

	private ExamPaperFacade examPaperFacade ;
	
	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		OlemPaperForm paperForm = (OlemPaperForm) form;
		Long typeId = paperForm.getTypeId();
		
		String createDateFrom = request.getParameter("createDateFrom") == null ? "" : request.getParameter("createDateFrom"); 
		String createDateTo = request.getParameter("createDateTo") == null ? "" : request.getParameter("createDateTo"); 
		
		ExamPaperQuery query = new ExamPaperQuery();
		
		query.setName(paperForm.getName());
		query.setState(1);
		query.setType_id(typeId);
		query.setPaper_mode(paperForm.getPaperMode());
		
		if(null != paperForm.getName() && !"".equals(paperForm.getName())){
			request.setAttribute("name", paperForm.getName());
		}		
		if(null != paperForm.getCreateDate() && !"".equals(paperForm.getCreateDate())){
			query.setCreate_date(paperForm.getCreateDate());
			request.setAttribute("createDate", paperForm.getCreateDate());
		}
		if(null != paperForm.getPaperMode() && -1 != paperForm.getPaperMode()){
			query.setPaper_mode(paperForm.getPaperMode());
			request.setAttribute("paperMode", paperForm.getPaperMode());
		}
		
		Page<ExamPaper> page = PageUtil.getPageByRequest(request);
		query.setPageNo(page.getCurrentPage());
		query.setPageSize(page.getPageSize());
		
		List<ExamPaper> paperList = examPaperFacade.getExamPaperList(query);
		List<ExamPaper> new_paperList = new ArrayList<ExamPaper>();
		
		int totalCount = examPaperFacade.getExamPaperListSize(query);
		for (Iterator<ExamPaper> iterator = paperList.iterator(); iterator.hasNext();) {
			ExamPaper type = iterator.next();			
			if (!createDateFrom.equals(""))
				if (type.getCreate_date().substring(0, 10).compareTo(createDateFrom) < 0) {
					totalCount --;
					continue;
				}
			if (!createDateTo.equals(""))
				if (type.getCreate_date().substring(0, 10).compareTo(createDateTo) > 0) {
					totalCount --;
					continue;
				}
			type.setType_name(examPaperTypeFacade.getExamPaperTypeById(type.getType_id()).getName());
			new_paperList.add(type);
		}
		
		page.setList(new_paperList);
		page.setCount(totalCount);
		
		request.setAttribute("page", page);
		request.setAttribute("paperTypeId", typeId);
		request.setAttribute("createDateFrom", createDateFrom);
		request.setAttribute("createDateTo", createDateTo);
		request.setAttribute("paperMode", query.getPaper_mode());
		
		return Constants.SUCCESS ;
	}

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	
	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}