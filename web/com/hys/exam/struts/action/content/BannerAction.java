package com.hys.exam.struts.action.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Port.Info;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.Advert;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.BannerManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.framework.web.action.BaseAction;

import net.sf.json.JSONObject;

/**
 * banner管理
 * 
 * @author weeho
 *
 */
public class BannerAction extends BaseAction {
	// 站点
	private SystemSiteManage localSystemSiteManage;
	// banner
	private BannerManage localBannerManage;

	private SystemUser systemUser;

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}

	public void setLocalBannerManage(BannerManage localBannerManage) {
		this.localBannerManage = localBannerManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String method = request.getParameter("method");
		if (method.equals("list")) {
			return list(mapping, form, request, response);
		}
		else {
			return list(mapping, form, request, response);
		}
	}

	// 根据id查询对象  测试：http://localhost:8080/qiantai/bannerManage.do?method=list&id=8
	protected String list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		if (id != null && id > 0) {
			Advert advert2 = localBannerManage.getAdvertById(id);
			String date = advert2.getCreatedate();
			String createdate = date.substring(0, 19);
			request.setAttribute("data", advert2);
			int count = localSystemSiteManage.getSystemSiteList().size();
			request.setAttribute("id", id);
			request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
			request.setAttribute("url", advert2.getUrl());
			request.setAttribute("target_url", advert2.getTarget_url());
			request.setAttribute("remark", advert2.getRemark());
			request.setAttribute("name", advert2.getName());
			request.setAttribute("type", advert2.getType());
			request.setAttribute("createdate", createdate);
			return "success";
		}else {
			return "fail";
		}
		
		

	}
}
