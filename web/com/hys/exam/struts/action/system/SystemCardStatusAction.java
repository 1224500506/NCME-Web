package com.hys.exam.struts.action.system;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.StudyCourseVO;
import com.hys.exam.model.system.SystemAdminOrgan;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.utils.DBConfiger;

/**
*
* <p>Description: 卡状态管理</p>
* 状态,余额,绑定用户,解绑用户
* @author chenlaibin
* @version 1.0 2013-12-19
*/
public class SystemCardStatusAction extends AppBaseAction{

	private SystemCardManage systemCardManage;
	
	private SystemSiteManage systemSiteManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("updateStatus")){
			return this.updateStatus(mapping, form, request, response);
		}else if(method.equals("bindUser")){
			return this.bindUser(mapping, form, request, response);
		}else if(method.equals("unBindUser")){
			return this.unBindUser(mapping, form, request, response);
		}else if(method.equals("getBindUser")){
			return this.getBindUser(mapping, form, request, response);
		}else if(method.equals("viewDetail")){
			return this.viewDetail(mapping, form, request, response);
		}else if(method.equals("updateSelled")){
			return this.updateSelled(mapping, form, request, response);
		}else if(method.equals("updateSellStyle")){
			return this.updateSellStyle(mapping, form, request, response);
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		boolean isAll = true;
		String cardNumber = ParamUtils.getParameter(request, "cardNumber", "");
		String cardNumberStart = ParamUtils.getParameter(request, "cardNumberStart", "");
		String cardNumberEnd = ParamUtils.getParameter(request, "cardNumberEnd", "");
		Page<SystemCardQuery> page = this.createPage(request, "systemCard");
		this.systemCardManage.getSystemCardStatusList(page, isAll, cardNumber, cardNumberStart, cardNumberEnd);
		request.setAttribute("page", page);
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardNumberStart", cardNumberStart);
		request.setAttribute("cardNumberEnd", cardNumberEnd);
		return "list";
	}
	
	//更改卡状态/重置余额
	protected String updateStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		int count = 0;
		Integer status = ParamUtils.getIntParameter(request, "status", -100);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		Double balance = ParamUtils.getDoubleParameter(request, "balance", Constants.SYSTEM_CARD_DEFAULT_BALANCE);
		count = this.systemCardManage.updateSystemCard(cardIds, status, balance);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//更改销售方式
	protected String updateSellStyle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		int count = 0;
		Integer status = ParamUtils.getIntParameter(request, "status", -100);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		count = this.systemCardManage.updateSystemCardSellStyle(cardIds, status);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//更改售出状态
	protected String updateSelled(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		int count = 0;
		Integer status = ParamUtils.getIntParameter(request, "status", -100);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		count = this.systemCardManage.updateSystemCardSelled(cardIds, status);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//绑定用户
	protected String bindUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		String _userIds = ParamUtils.getParameter(request, "userIds");
		Long siteId = ParamUtils.getLongParameter(request, "siteId", Constants.AJPX_SYSTEM_SITE_ID);
		_userIds = _userIds.substring(0,_userIds.length()-1);
		String [] ids = _userIds.split(",");
		Long [] userIds = switchStringtoLongArray(ids);
		int count = this.systemCardManage.addSystemCardUserBind(cardId, userIds, siteId);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//解绑用户
	protected String unBindUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		String _userIds = ParamUtils.getParameter(request, "userIds");
		_userIds = _userIds.substring(0,_userIds.length()-1);
		String [] ids = _userIds.split(",");
		Long [] userIds = switchStringtoLongArray(ids);
		int count = this.systemCardManage.deleteSystemCardUserBind(cardId, userIds);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//得到绑定用户/未绑定用户
	@SuppressWarnings("static-access")
	protected String getBindUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		String userName = ParamUtils.getParameter(request, "name");
		String certificateNo = ParamUtils.getParameter(request, "certificateNo");
		Long isBind = ParamUtils.getLongParameter(request, "isBind", 0);
		String domainName = ParamUtils.getParameter(request, "domainName");
		Page<SystemUser> page = this.createPage(request, "iSystemUser");
		SystemUser user = new SystemUser();
		user.setRealName(userName);
		user.setCertificateNo(certificateNo);
		if("".equals(domainName) || domainName.equals(Constants.ANQUAN100_SITE)){
			this.systemCardManage.getSystemUserList(page, cardId, user, isBind==1?true:(isBind==-1));
		}else{	//获取其他平台
			String domainnames = DBConfiger.getInstance().getProperty("domainnames");
			String databases = DBConfiger.getInstance().getProperty("databases");
			String databaseName = "";
			if(StringUtils.isNotBlank(domainnames) && StringUtils.isNotBlank(databases) ){
				String [] domainnames_arr = domainnames.split(";");
				String [] databases_arr = databases.split(";");
				if(domainnames_arr.length == databases_arr.length){
					List<String> list = Arrays.asList(domainnames_arr);
					int index = list.indexOf(domainName);
					if(index >-1)
						databaseName = databases_arr[index];
					else 
						databaseName = "icme";
				}
				this.systemCardManage.getIcmeSystemUserList(page, databaseName, domainName, cardId, user, isBind==1?true:(isBind==-1));
			}
		}
		request.setAttribute("page", page);
		request.setAttribute("cardId", cardId);
		request.setAttribute("isBind", isBind);
		
		//得到站点列表
		SystemSite ss = new SystemSite();
		List<SystemSite> siteList = this.systemSiteManage.getListByItem(ss);
		request.setAttribute("siteList", siteList);
		
		Long siteId = Constants.AJPX_SYSTEM_SITE_ID;
		for(SystemSite site : siteList){
			if(site.getDomainName().equals(domainName)){
				siteId = site.getId();
			}
		}
		
		request.setAttribute("siteId", siteId);
		
		return "userBindList";
	}
	
	//查看学习卡详细
	protected String viewDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long cardId = ParamUtils.getLongParameter(request, "cardId", 0L);
		//卡信息
		SystemCardQuery card = this.systemCardManage.getSystemCardById(cardId);
		request.setAttribute("card", card);
		if(card != null){
			Long cardTypeId = card.getCardType();
			//卡类型信息
			SystemCardTypeQuery cardType = this.systemCardManage.getSystemCardTypeById(cardTypeId);
			request.setAttribute("cardType", cardType);
			
			//卡类型授权课程列表
			@SuppressWarnings("static-access")
			Page<StudyCourseVO> course_page = this.createPage(request, "iaStudyCourse");
			List<StudyCourseVO> course_list = this.systemCardManage.getStudyCourse(course_page, cardTypeId, true, "");
			request.setAttribute("course_list", course_list);
			
			//卡类型授权地区列表
			@SuppressWarnings("static-access")
			Page<SystemAdminOrgan> org_page = this.createPage(request, "iSystemOrg");
			List<SystemAdminOrgan> org_list = this.systemCardManage.getSystemOrgList(org_page, cardTypeId, true, "");
			request.setAttribute("org_list", org_list);
		}
		
		//卡用户
		SystemUser userInfo = this.systemCardManage.getSystemCardUserByCardId(cardId);
		request.setAttribute("userInfo", userInfo);
		
		return "viewDetail";
	}
	
	public SystemCardManage getSystemCardManage() {
		return systemCardManage;
	}

	public void setSystemCardManage(SystemCardManage systemCardManage) {
		this.systemCardManage = systemCardManage;
	}

	public SystemSiteManage getSystemSiteManage() {
		return systemSiteManage;
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}
}


