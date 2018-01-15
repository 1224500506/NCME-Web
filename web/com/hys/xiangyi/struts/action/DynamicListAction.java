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

public class DynamicListAction extends BaseAction {

    
    private FirstPageService firstPageService;

    public FirstPageService getFirstPageService() {
		return firstPageService;
	}

	public void setFirstPageService(FirstPageService firstPageService) {
		this.firstPageService = firstPageService;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
			Long id = Long.valueOf(request.getParameter("code"));
    		request.setAttribute("data", getEveryProvinceInformationByCode(id));
    		request.setAttribute("name", getEveryProvinceNameByCode(id));
    		request.setAttribute("code", id);
        return Constants.SUCCESS;
    }
	
	public List<Sentence> getEveryProvinceInformationByCode(Long code){
		return firstPageService.getEveryProvinceInformationByCode(code);
	}
	
	public String getEveryProvinceNameByCode(Long code){
		return firstPageService.getEveryProvinceNameByCode(code);
	}
	
}
