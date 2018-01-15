package com.hys.qiantai.struts.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.MessageUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.SystemMessageManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.SystemMessageModel;

/**
 * 
 * @author Tiger
 * @time 2017-1-12
 * 
 * Detail : Manage the user's message and system message.
 *
 */
public class MyMessageManageAction extends BaseAction {
	
	
	//保存学习记录(项目)Service接口
	private LogStudyCVSetManage localLogStudyCVSetManage;
	
	
	private CVSetManage localCVSetManage;
	
	private SystemUserManage userManage;
	
	
	
	
	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public void setUserManage(SystemUserManage userManage) {
		this.userManage = userManage;
	}

	SystemMessageManage localSystemMesageManage;
	
	public SystemMessageManage getLocalSystemMesageManage() {
		return localSystemMesageManage;
	}

	public void setLocalSystemMesageManage(
			SystemMessageManage localSystemMesageManage) {
		this.localSystemMesageManage = localSystemMesageManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getParameter("method");
		
		if(method != null && method.equals("readAll"))
		{
			return readAll(request, response);
		}
		if(method != null && method.equals("test"))
		{
			 return null;
		}
		
		
		
		else if(method != null && method.equals("deleteAll"))
		{
			return deleteAll(request, response);
		}
		else if(method != null && method.equals("deleteMessage"))
		{
			return deleteMessage(request, response);
		}
		
		else if(method != null && method.equals("saveMessage"))
		{
			 SaveMessage(request, response);
			 return null;
		}
		
		
		else
		{
			
			return list( mapping, form, request,response);
		}
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * Detail : Get the user's message list.
	 */
	public String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user == null)
		{
			return "fail";
		}
		
		PageList<SystemMessageModel> pList = new PageList<SystemMessageModel>();
		
		//paging code.
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = 5;
		String sort = ParamUtils.getParameter(request, "sort", "is_read,message_date");
		String dir = ParamUtils.getParameter(request, "dir", "desc");
		
		pList.setPageNumber(currentPage);
		pList.setObjectsPerPage(pageSize);
		pList.setSortCriterion(sort);
		if (dir.equals("asc"))
			pList.setSortDirection(SortOrderEnum.ASCENDING);
		else
			pList.setSortDirection(SortOrderEnum.DESCENDING);
		
		//Get user's systemMessage list from database.
		localSystemMesageManage.getUserMessageList(user,pList);
		//查询发件人系统管理员的信息
		SystemUser admin = userManage.getItemById(20L);
		
		request.setAttribute("messagePageList", pList);
		request.setAttribute("messageData", pList.getList());
		request.setAttribute("userInfo", user);
		request.setAttribute("admin", admin);
		return "success";
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-12
	 * @param request
	 * @param response
	 * @return
	 * Detail : Read all user's message.
	 */
	public String readAll(HttpServletRequest request, HttpServletResponse response)
	{
		String userId = request.getParameter("userId");
		String messageType = ParamUtils.getParameter(request, "messageType", "0");
		if(!StringUtils.checkNull(userId))
		{
			Boolean result = localSystemMesageManage.readAll(Long.valueOf(userId), Integer.valueOf(messageType));
			if(result)
			{
				StrutsUtil.renderText(response, "success");	
			}
			else
			{
				StrutsUtil.renderText(response, "fail");
			}
			
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-12
	 * @param request
	 * @param response
	 * @return
	 * Detail : Delete all user's message.
	 */
	public String deleteAll(HttpServletRequest request, HttpServletResponse response)
	{
		String userId = request.getParameter("userId");
		String messageType = ParamUtils.getParameter(request, "messageType", "0");
		if(!StringUtils.checkNull(userId))
		{
			//Delete message of message type.
			Boolean result = localSystemMesageManage.deleteAll(Long.valueOf(userId), Integer.valueOf(messageType));
			if(result)
			{
				StrutsUtil.renderText(response, "success");	
			}
			else
			{
				StrutsUtil.renderText(response, "fail");
			}
			
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-12
	 * @param request
	 * @param response
	 * @return
	 * Detail : delete user's message.
	 */
	public String deleteMessage(HttpServletRequest request, HttpServletResponse response)
	{
		String messageType = ParamUtils.getParameter(request, "messageType", "0");
		String messageId = request.getParameter("messageId");
		if(!StringUtils.checkNull(messageId))
		{
			//Delete message.
			Boolean result = localSystemMesageManage.deleteMessage(Long.valueOf(messageId), Integer.valueOf(messageType));
			if(result)
			{
				StrutsUtil.renderText(response, "success");	
			}
			else
			{
				StrutsUtil.renderText(response, "fail");
			}
			
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return *
	 * 
	* @Title: SaveMessage 
	* @Description: TODO(保存系统消息)
	* @author 程宏业 
	* @date 2017-2-16下午8:49:25 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	
	public void SaveMessage(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "Hi亲爱的 ";
		SystemMessageModel SystemMessage = new SystemMessageModel();
		SystemMessage.setSystem_user_id(Long.valueOf(request.getParameter("system_user_id")));
		SystemMessage.setMessage_content(message+request.getParameter("message_content")+MessageUtil.RegMessage);
		 localSystemMesageManage.SaveMessage(SystemMessage);
		
	}

	
	
	
	
	
}
