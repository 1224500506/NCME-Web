package com.hys.qiantai.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CV;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

public class VrListAction extends BaseAction {
    private CVManage localCVManage;

    public void setLocalCVManage(CVManage localCVManage) {
        this.localCVManage = localCVManage;
    }

    @Override
    protected String actionExecute(ActionMapping arg0, ActionForm arg1,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Pager<CV> pl = new Pager<CV>();

        //
        String xueke = request.getParameter("xueke");
        int currentPage = PageUtil.getPageIndex2(request);
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.ASCENDING);
        pl.setUrl("vrList.do");
        pl.setQueryString(request);

        CV queryCv = new CV();
        queryCv.setServerName(request.getServerName());
        queryCv.setBrand("3");// 1 病例 2 直播 3 vr
        // 已发布
        queryCv.setStatus(5);

        // 设置学科
        if (xueke != null && !"".equals(xueke) && !"全部".equals(xueke)) {
            queryCv.setPropName(xueke);
        }
        
        SystemUser user = LogicUtil.getSystemUser(request);
		if(user!=null){
			if(user.getUserConfig()!=null){
				queryCv.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
				
			}
		}

        localCVManage.queryCVPageList2(pl, queryCv);

        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
        }

        request.setAttribute("xueke", xueke);
        request.setAttribute("pager", pl);

        return "list";
    }
}
