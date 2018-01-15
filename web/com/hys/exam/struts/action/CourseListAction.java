package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 学科管理
 * @author iU
 *
 */
public class CourseListAction extends BaseAction {
    private ExamPropValFacade localExamPropValFacade;

    public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }

    public void setLocalExamPropValFacade(
            ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }

    protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // PageList pl = new PageList();

        // 取得查询条件
        String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
        String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
        String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");

        ExamProp prop = new ExamProp();
        // 取得一级学科
        prop.setType(1);
        prop.setName(sname);
        List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);

        List<ExamPropType> tlist = localExamPropValFacade.getExamPropTypeList();
        if (id != null && !id.equals("0")) {
            ExamProp subProp = localExamPropValFacade.getSysPropVal(Long.valueOf(id));
            request.setAttribute("subProp", subProp);
        }

        // request.setAttribute("parentid", "0");
        request.setAttribute("type", type);
        request.setAttribute("prop_val1", id);
        request.setAttribute("propList", list);
        request.setAttribute("ctype", tlist);
        request.setAttribute("sname", sname);
        request.setAttribute("url", request.getRequestURL().toString());

        return "SUCCESS";
    }
}
