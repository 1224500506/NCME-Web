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
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * @author ABoy
 * @created 2017/01/26
 *
 */

public class starCourseListAction extends BaseAction {

	private CVManage localCVManage;

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}

	public CVManage getLocalCVManage() {
        return localCVManage;
    }
	private CVSetEntityManage localCVSetEntityDAO;
    

    public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}


	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
		Pager<CV> pg = new Pager<CV>();

		pg.setPageOffset(PageUtil.getPageIndex2(request));
		pg.setSortDirection(SortOrderEnum.ASCENDING);
		pg.setUrl("starCourseList.do");
		pg.setQueryString(request);

        CV queryCv = new CV();
        
        queryCv.setEdtionId(4); 
        queryCv.setServerName(request.getServerName());
        queryCv.setStatus(5);  //已发布
        
        SystemUser user = LogicUtil.getSystemUser(request);
		if(user!=null){
			if(user.getUserConfig()!=null){
				queryCv.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
				
			}
		}
        
        String xueke = request.getParameter("xueke");
        if(xueke != null && !"".equals(xueke) && !"全部".equals(xueke)){
            queryCv.setPropName(xueke);
        }
        
        localCVManage.queryCVPager(pg, queryCv);

        for (CV cv : pg.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
            //获取项目费用类型情况
            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
        }
     
        request.setAttribute("pager", pg);
        request.setAttribute("xueke", xueke);
        
        return "list";
	}
}
