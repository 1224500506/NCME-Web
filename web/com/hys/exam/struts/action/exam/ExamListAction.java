package com.hys.exam.struts.action.exam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;

/**
 * 
 * 标题：考试列表
 * 
 * 作者：陈明凯 2013-3-8
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamListAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		String curTypeId = request.getParameter("curTypeId");
		String typeName = request.getParameter("typeNames");
		String queryExamName = request.getParameter("queryExamName");
		
			Integer currentPage = PageUtil.getPageIndex(request);

			ExamExaminationQuery query = new ExamExaminationQuery();
			if (curTypeId != null && !curTypeId.equals("")) {
				query.setExam_type_id(curTypeId);
			}
			query.setName(queryExamName);
			query.setCreate_time(request.getParameter("queryCreateTime"));
			Integer examState = ParamUtils.getIntParameter(request, "examState", -1);
			if(!examState.equals(-1))
			{
				query.setState(examState);
			}
			Integer examType = ParamUtils.getIntParameter(request, "examType", -1);
			if(!examType.equals(-1))
			{
				query.setExam_type(examType);
			}
			List<ExamExamination> examList = examExaminationFacade
					.getExaminationList(query);

			int size = examExaminationFacade.getExaminationListSize(query);

			Page<ExamExamination> page = new Page<ExamExamination>();
			page.setCurrentPage(currentPage);
			page.setCount(size);
			page.setList(examList);

			request.setAttribute("ExamList", page);
			request.setAttribute("curTypeId", curTypeId);
			request.setAttribute("typeNames", typeName);
			request.setAttribute("queryExamName", queryExamName);
			request.setAttribute("examType", examType);
			request.setAttribute("examState", examState);
			saveToken(request);// 设置token

		return Constants.SUCCESS;
	}
}
