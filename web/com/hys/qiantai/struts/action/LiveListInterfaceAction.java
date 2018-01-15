package com.hys.qiantai.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.exam.model.CV;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

public class LiveListInterfaceAction extends BaseAction {
    @Override
    protected String actionExecute(ActionMapping arg0, ActionForm arg1,
            HttpServletRequest request, HttpServletResponse arg3) throws Exception {
        Pager<CV> pl = new Pager<CV>();

        String xueke = "直播入口";
        int currentPage = PageUtil.getPageIndex2(request);
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.DESCENDING);
        pl.setUrl("liveListInterface.do");
        pl.setQueryString(request);

        request.setAttribute("xueke", xueke);
        request.setAttribute("pager", pl);

        return "listIn";
    }
}
