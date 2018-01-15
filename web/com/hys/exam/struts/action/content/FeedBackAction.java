package com.hys.exam.struts.action.content;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.Advert;
import com.hys.exam.model.Feedback;

import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.FeedbackManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.struts.form.BannerForm;
import com.hys.exam.struts.form.FeedbackForm;
import com.hys.exam.utils.FileLimitUtil;
import com.hys.exam.utils.FilesUtils;

import net.sf.json.JSONObject;

public class FeedBackAction extends AppBaseAction {
	// 反馈
	private FeedbackManage feedbackManage;

	public FeedbackManage getFeedbackManage() {
		return feedbackManage;
	}

	public void setFeedbackManage(FeedbackManage feedbackManage) {
		this.feedbackManage = feedbackManage;
	}
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String method = request.getParameter("method");
		if (method.equals("fileUp")) {
			return fileUp(mapping, form, request, response);
		} else {
			return fileUp(mapping, form, request, response);
		}
	}

	private String fileUp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO 首页-意见反馈
		boolean flag = false;
		String Agent = request.getHeader("User-Agent");
		StringTokenizer st = new StringTokenizer(Agent, ";");
		st.nextToken();
		// 得到用户的浏览器名
		String userbrowser = st.nextToken();
		// 得到用户的操作系统名
		String useros = st.nextToken();
		// 取得本机的信息也可以这样：
		// 操作系统信息
		System.getProperty("os.name"); 
		System.getProperty("os.version");
		System.getProperty("os.arch");
		// 瀏覽器：
		String header = request.getHeader("User-Agent");
		// ------------------------
		FeedbackForm fform = (FeedbackForm) form;

		Feedback feedback = fform.getModel();

		String creater = request.getParameter("creater");
		if (creater !=null && !"".equals(creater)) {
			feedback.setCreater(creater);
		}
		String content = request.getParameter("content");
		if (content !=null && !"".equals(content)) {
			feedback.setContent(content);
		}
		String tel = request.getParameter("telphone");
		if (tel != null && !"".equals(tel)) {
			feedback.setTelphone(tel);
		}
		String image = request.getParameter("image");
		if (image != null && !"".equals(image)) {
			feedback.setImage(image);
		}
		String email = request.getParameter("email");
		//其他的联系方式
		if (email !=null && !"".equals(email)) {
			feedback.setEmail(email);
		}
		/**
		 * 获取的，操作系统，操作系统版本，站点
		 */
		String system = System.getProperty("os.name");// 获取操作系统
		feedback.setSystem(system);
		String systemversion = System.getProperty("os.version");// 获取操作系统的版本
		feedback.setSystemversion(systemversion);
		StringBuffer url = request.getRequestURL();  
		String site = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
		feedback.setSite(site);//站点
	
		
		//标记未回复
		feedback.setState(1);
		//执行添加操作
		flag = feedbackManage.add(feedback);
	
		
		// 返回页面
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}

}
