package com.hys.xiangyi.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.XiangYiProvince;
import com.hys.exam.util.JsonUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.xiangyi.service.FirstPageService;

public class UserProvinceCodeAction extends BaseAction {

    
    private FirstPageService firstPageService;

    public FirstPageService getFirstPageService() {
		return firstPageService;
	}

	public void setFirstPageService(FirstPageService firstPageService) {
		this.firstPageService = firstPageService;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		Long codeLong = Long.valueOf(request.getParameter("code"));//地区编码
		SystemUser user = LogicUtil.getSystemUser(request);//获得user对象
		
		
		List<XiangYiProvince> list = firstPageService.getProvinceCode(user.getUserId());
		if(list!=null && list.size()>0){
			if(Long.valueOf(list.get(0).getProvinceCode()).equals(codeLong) || codeLong==111111111){
				map.put("message", "yes");
			}else{
				map.put("message", "no");
				map.put("pcode", Long.valueOf(list.get(0).getProvinceCode()));
			}
		}else{
			map.put("message", "null");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
    }
	

	
}
