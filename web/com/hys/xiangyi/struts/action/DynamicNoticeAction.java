package com.hys.xiangyi.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.XiangYiProvince;
import com.hys.framework.web.action.BaseAction;
import com.hys.xiangyi.service.FirstPageService;

public class DynamicNoticeAction extends BaseAction {

    
    private FirstPageService firstPageService;

    public FirstPageService getFirstPageService() {
		return firstPageService;
	}

	public void setFirstPageService(FirstPageService firstPageService) {
		this.firstPageService = firstPageService;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
			Long id = Long.valueOf(request.getParameter("id"));
    		request.setAttribute("data", getDynamicNoticeById(id));
        return Constants.SUCCESS;
    }
	
	public Sentence getDynamicNoticeById(Long id){
		return firstPageService.getDynamicNoticeById(id);
	}
	
}
