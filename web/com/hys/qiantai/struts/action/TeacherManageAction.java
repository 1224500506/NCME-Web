package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

/**
 * 学科管理
 * @author iU
 *
 */
public class TeacherManageAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	
	private ExpertManageManage localExpertManageManage;
	
	private ExpertGroupManage localExpertGroupManage;
	
	private CVManage localCVManage;
	
	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	public ExpertGroupManage getLocalExpertGroupManage() {
		return localExpertGroupManage;
	}

	public void setLocalExpertGroupManage(ExpertGroupManage localExpertGroupManage) {
		this.localExpertGroupManage = localExpertGroupManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}

    protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String method = request.getParameter("mode");

        if (method != null && method.equals("view")) {
            return teacherDetail(request);
        }

        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setPersonage(1);

        // 取得专家目录
        Pager<ExpertInfo> pl = new Pager<ExpertInfo>();
        int currentPage = PageUtil.getPageIndex2(request);
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.ASCENDING);
        pl.setUrl("teacherManage/teacherManage.do");
        pl.setQueryString(request);

        localExpertManageManage.getExpertList(pl, expertInfo);
        request.setAttribute("info", expertInfo);
        request.setAttribute("pager", pl);
        return "SUCCESS";
	}

    public String teacherDetail(HttpServletRequest request) {
        String id = request.getParameter("id");
        ExpertInfo currentExpert = localExpertManageManage.getExpertInfo(Long
                .valueOf(id));

        List<CVSet> cvListByManager = new ArrayList<CVSet>();
        cvListByManager = localExpertManageManage.getCVSetFromExpert(
                currentExpert, Constants.TeacherType);

        ExamProp prop = new ExamProp();
        prop.setType(9);
        List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);

        request.setAttribute("expert", currentExpert);
        request.setAttribute("proplist", proplist);
        request.setAttribute("manageCVList", cvListByManager);
        request.setAttribute("teacherCVList", new ArrayList<CVSet>());
        request.setAttribute("brand1List", new ArrayList<CVSet>());
        request.setAttribute("brand2List", new ArrayList<CVSet>());
        return "view";
    }
}
