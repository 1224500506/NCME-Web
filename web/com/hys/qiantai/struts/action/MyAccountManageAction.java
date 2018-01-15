package com.hys.qiantai.struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.*;
import com.hys.exam.utils.CusAccessObjectUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemCard;
import com.hys.exam.model.SystemCardOrder;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemCardUser;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.util.JsonUtil;
import com.hys.framework.web.action.BaseAction;

public class MyAccountManageAction extends BaseAction {
private ExamPropValFacade localExamPropValFacade;
	

//用户绑卡记录
 private SystemCardUserService systemCardUserService;

 private  SystemLogManage systemLogManage;
 
//订单管理
	private SystemCardOrderManage systemCardOrderManage;
// 项目管理类型
	
	private CVSetManage cVSetManage;
	
	private SystemCardManage systemCardManage;
	private CVSetManage localCVSetManage;
	private LogStudyCVSetManage localLogStudyCVSetManage;

	
	public CVSetManage getcVSetManage() {
		return cVSetManage;
	}

	public void setcVSetManage(CVSetManage cVSetManage) {
		this.cVSetManage = cVSetManage;
	}

	public SystemCardOrderManage getSystemCardOrderManage() {
		return systemCardOrderManage;
	}

	public void setSystemCardOrderManage(SystemCardOrderManage systemCardOrderManage) {
		this.systemCardOrderManage = systemCardOrderManage;
	}

	public SystemCardUserService getSystemCardUserService() {
	return systemCardUserService;
}

public void setSystemCardUserService(SystemCardUserService systemCardUserService) {
	this.systemCardUserService = systemCardUserService;
}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		SystemUser user = LogicUtil.getSystemUser(request);
		String mode = RequestUtil.getParameter(request, "mode");
		List<SystemCardOrder> list =null;
		List<SystemCardOrder> list2 = null;
		SystemCardOrder carorder = new SystemCardOrder();
		carorder.setUSEFUL_DATE(DateUtil.getNowDate());
		carorder.setUSER_ID(user.getUserId());
		if(user == null)
		{
			return "fail";
		}
		if(mode.equals("addCard")){
			return addCard(mapping, form, request, response);
		}else if(mode.equals("view")){
			
			return viewCard(mapping, form, request, response);
			
		}else{
			carorder.setSTATUS(1);
			 try {
			//使用中	 
		    list = systemCardOrderManage.findAllList(carorder);
		    	carorder.setSTATUS(0);
		     list2 = systemCardOrderManage.findAllList(carorder);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		  
		}
		
		request.setAttribute("userInfo", user);	
		request.setAttribute("cardlist", carListModify(list));	
		request.setAttribute("cardlist2", carListModify(list2));
		
		return "success";
	}
	
	
	
	/***
	 * 
	 * 添加学习卡
	 * 方法名：addCard
	 * 创建人：程宏业
	 * 时间：2017-4-19-下午5:49:51 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	private String addCard(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> map = new HashMap<String, String>();
		// 获取用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		// 学习卡卡号
		String cardNumber = request.getParameter("cardNumber");
		String cardPassword = request.getParameter("cardPassword");

		if (user != null) {
			try {
				if (!"".equals(cardNumber) && cardNumber != null) {

					SystemCard newcard = systemCardUserService.findCardByCardNumberAndpassword(cardNumber,cardPassword);

					if (newcard != null) {
						//判断学习卡类型是否为空卡，如果为空卡，返回 该卡未绑定项目，无法添加成功
						if(Constants.SystemCard_TYPE_NULL==newcard.getCARD_TYPE()){
							map.put("message", "typeno");
							StrutsUtil.renderText(response, JsonUtil.map2json(map));
							return null;
						}
						//添加学习卡被禁用拦截
						if(newcard.getStatus() != null && -2 == newcard.getStatus()){
							map.put("message", "typedisable");
							StrutsUtil.renderText(response, JsonUtil.map2json(map));
							return null;
						}
						if (1 != newcard.getISNOT_BIND() && 1==newcard.getStatus() && newcard.getUSEFUL_DATE() != null) {				
							SystemCard card = newcard;
							// 判断学习卡是否过期
							String startDateStr = DateUtils.DateToString(card.getUSEFUL_DATE());
							String endDateStr = DateUtils.DateToString(new Date());
							int time = DateUtils.compareDate(startDateStr,endDateStr, 0);
							if (time > 0) {
								// 项目已经过期
								map.put("message", "time");
							} else {
								// 卡号存在且没有过期

								// 获取卡号类型匹配卡类型
								List<SystemCardTypeCvSet> cvsetlit = systemCardUserService.findListByCardType(Long.parseLong(card.getCARD_TYPE().toString()));
									// 卡类型有关联的项目
									for (SystemCardTypeCvSet systemCardTypeCvSet : cvsetlit) {

										// 查询表中是否存在
										List<SystemCardOrder> orderlist = systemCardOrderManage.findByUidProid(user.getUserId(),Long.parseLong(systemCardTypeCvSet.getCV_SET_ID().toString()),card.getCARD_NUMBER());
										if (orderlist != null && orderlist.size() > 0)											
											continue; // 如果存在跳出本次循环
										
										SystemCardOrder cardOrder = new SystemCardOrder();
										cardOrder.setCARD_TYPE_ID(card.getCARD_TYPE());// 卡类型
										cardOrder.setUSER_ID(user.getUserId());// 用户id
										cardOrder.setUSEFUL_DATE(card.getUSEFUL_DATE());// 订单日期
										
										//YHQ，2017-05-18，支付金额————卡有cost放钱、卡类型有钱：应该取卡类型的钱！！！需要改的就改吧？
										Integer cardMoney = card.getFACE_VALUE() ;
										if (cardMoney == null) cardMoney = card.getCOST() ;
										if (cardMoney == null) cardMoney = 0 ;
										
										cardOrder.setPRICE(cardMoney + 0.0);// 价格
										
										
										cardOrder.setAMOUNT(1);// 数量
										cardOrder.setPAY_DATE((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));// 支付日期
										cardOrder.setPAYMODE_CODE("绑卡");// 订单类型
																		// 支付宝/微信/网银
										cardOrder.setORDER_NUMBER(System.currentTimeMillis() + "");
										cardOrder.setSTATUS(1);
										cardOrder.setORDER_TYPE(1);
										cardOrder.setCARD_NUMBER(card.getCARD_NUMBER());
										cardOrder.setCV_SET_ID(Long.parseLong(systemCardTypeCvSet.getCV_SET_ID().toString()));

										if (cVSetManage.toCostById(cardOrder.getCV_SET_ID()).size() > 0) {
											cardOrder.setITEM_NAME(cVSetManage.toCostById(cardOrder.getCV_SET_ID()).get(0).getName());
										} else {
											cardOrder.setITEM_NAME("");
										}
										systemCardOrderManage.AddCardOrder(cardOrder);

										// 用户卡绑定表中
										SystemCardUser systemuser = new SystemCardUser();
										systemuser.setBindDate2(DateUtil.getNowDate());
										systemuser.setUserId(user.getUserId());
										systemuser.setCardId(card.getID());
										systemuser.setSiteId(systemCardTypeCvSet.getCV_SET_ID());
										systemCardUserService.SaveBindUserinfor(systemuser);
									}
									// 更改绑卡状态
									// map.put("message","typeno" );
									card.setSTATUS(2);
									systemCardUserService.UpdateCard(card);

									// 添加成功
									map.put("message", "success");
							}
						} else {
							// 卡状态不可用
							map.put("message", "noenable");
						}
					} else {
						// 卡不存在
						map.put("message", "notfind");
					}

				}
			} catch (Exception e) {
				map.put("message", "Exception");
				e.printStackTrace();
			} finally {
				if (null != user) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SystemLog systemLog = new SystemLog();
					systemLog.setUser_id(String.valueOf(user.getUserId()));
					systemLog.setOperate_login_name(user.getRealName());
					systemLog.setOperate_platform("qiantai");
					systemLog.setRequest_url(request.getRequestURI());
					systemLog.setVisit_ip(CusAccessObjectUtil.getIpAddress(request));
					systemLog.setOperate_time(sdf.format(new Date()));
					systemLog.setCreate_time(sdf.format(new Date()));
					systemLog.setModule_name("我的学习卡");
					systemLog.setOperate_type("qt_bindCard");
					if(map.get("message").equals("success")){
						systemLog.setOperate_context("绑定学习卡成功！");
					}else {
						systemLog.setOperate_context("绑定学习卡失败！");
					}
					try {
						systemLogManage.addSystemLog(systemLog);
					} catch (Exception e){
						logger.error("添加审计日志期间发生异常:" + e.getMessage());
					};
				}
			}
		}

		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;

	}
	
	
	
	private String viewCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		List<SystemCardTypeQuery> sysCQList = new ArrayList<SystemCardTypeQuery>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(
				Constants.SESSION_TRAIN_ADMIN_USER);
		// 学习卡卡号
		String cardNumber = request.getParameter("cardNumber");
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0L);
		
		if(user!=null){
			SystemCardTypeQuery queryParam = new SystemCardTypeQuery();
	    	SystemCardOrder queryParam1 = new SystemCardOrder();
		    List<SystemCardOrder> sysCOList = new ArrayList<SystemCardOrder>();
    		queryParam.setId(Long.parseLong(cardTypeId.toString()));
    		sysCQList = systemCardManage.getSystemCardTypeListByID(queryParam);
    		if(sysCQList != null && sysCQList.size() > 0){
    			for(SystemCardTypeQuery sysQ : sysCQList){
    				queryParam1.setCARD_TYPE_ID(Integer.parseInt(cardTypeId.toString()));
    				queryParam1.setCARD_NUMBER(cardNumber);
    				queryParam1.setUSER_ID(user.getUserId());
    				queryParam1.setCV_SET_ID(sysQ.getCvSetId());
    				sysCOList = systemCardOrderManage.getListForUseCvSet(queryParam1);
    				// 查询用户已经学过的单元
    				List<LogStudyCVUnit> logUnitList = localLogStudyCVSetManage
    						.searchLogStudyCVUnit("5", sysQ.getCvSetId(), user.getUserId());
	    			if(sysCOList != null && sysCOList.size() > 0){//如果在订单表里拿到了该项目表明已被使用卡
	    				sysQ.setPayDate(sysCOList.get(0).getPAY_DATE());
	    				// 0.表示已使用 1.表示未使用
		    			if (logUnitList != null && logUnitList.size() == 0) {
		    				sysQ.setUse_status(0);//表示卡未使用
		    			}else{
		    				sysQ.setUse_status(1);//表示卡已使用
		    			}
	    			}
    			}
    		}
		}
		map.put("list", sysCQList);	
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	public List<SystemCardOrder> carListModify(List<SystemCardOrder> list){
		if(list != null && list.size() > 0){
	    	SystemCardTypeQuery queryParam = new SystemCardTypeQuery();
	    	SystemCardOrder queryParam1 = new SystemCardOrder();
	    	for(SystemCardOrder syso : list){
	    		List<SystemCardOrder> useCOlist = new ArrayList<SystemCardOrder>();//已使用过卡的项目集合
	    		List<SystemCardTypeQuery> sysCQList = new ArrayList<SystemCardTypeQuery>();
			    List<SystemCardOrder> sysCOList = new ArrayList<SystemCardOrder>();
	    		queryParam.setId(Long.parseLong(syso.getCARD_TYPE_ID().toString()));
	    		sysCQList = systemCardManage.getSystemCardTypeListByID(queryParam);
	    		syso.setCvSetTotal(sysCQList==null?0:sysCQList.size());
	    		
	    		if(sysCQList != null && sysCQList.size() > 0){
	    			for(SystemCardTypeQuery sysQ : sysCQList){
		    			queryParam1.setCARD_TYPE_ID(syso.getCARD_TYPE_ID());
		    			queryParam1.setCARD_NUMBER(syso.getCARD_NUMBER());
		    			queryParam1.setUSER_ID(syso.getUSER_ID());
		    			queryParam1.setCV_SET_ID(sysQ.getCvSetId());
		    			sysCOList = systemCardOrderManage.getListForUseCvSet(queryParam1);
		    			if(sysCOList == null || sysCOList.size() <= 0){
		    				SystemCard card = systemCardUserService.findCardByCardNum(syso.getCARD_NUMBER());
		    				// 查询表中是否存在
		    				List<SystemCardOrder> orderlist = systemCardOrderManage.findByUidProid(syso.getUSER_ID(),Long.parseLong(sysQ.getCvSetId().toString()),syso.getCARD_NUMBER());
		    				if (orderlist != null && orderlist.size() > 0)											
		    					continue; // 如果存在跳出本次循环
		    				//将卡类型对应的修改过的项目进行维护
		    				maintainOrderCVset(syso, sysQ, card);
		    			}
		    			// 查询用户已经学过的单元
	    				List<LogStudyCVUnit> logUnitList = localLogStudyCVSetManage
	    						.searchLogStudyCVUnit("5", sysQ.getCvSetId(), syso.getUSER_ID());
	    				if (logUnitList != null && logUnitList.size() > 0) {
	    					useCOlist.addAll(sysCOList);//如果绑过卡，且在学习中
	    				}
	    			}
	    			syso.setCvSetUseTotal(useCOlist==null?0:useCOlist.size());
	    		}else{
	    			syso.setCvSetUseTotal(0);
	    		}
	    	}
	    	//对相同的卡对象进行去重操作
	    	for(int i = 0;i<list.size()-1;i++){
	    		for(int j = list.size()-1;j>i;j--){
	    			if(list.get(j).getCARD_NUMBER().equals(list.get(i).getCARD_NUMBER())){
	    				list.remove(j);
	    			}
	    		}
	    	}
	    }
		return list;
	}
	
	
	public void maintainOrderCVset(SystemCardOrder syso, SystemCardTypeQuery sysQ, SystemCard card){
		
		SystemCardOrder cardOrder = new SystemCardOrder();
		cardOrder.setCARD_TYPE_ID(card.getCARD_TYPE());// 卡类型
		cardOrder.setUSER_ID(syso.getUSER_ID());// 用户id
		cardOrder.setUSEFUL_DATE(card.getUSEFUL_DATE());// 订单日期
		
		//YHQ，2017-05-18，支付金额————卡有cost放钱、卡类型有钱：应该取卡类型的钱！！！需要改的就改吧？
		Integer cardMoney = card.getFACE_VALUE() ;
		if (cardMoney == null) cardMoney = card.getCOST() ;
		if (cardMoney == null) cardMoney = 0 ;
		
		cardOrder.setPRICE(cardMoney + 0.0);// 价格
		
		
		cardOrder.setAMOUNT(1);// 数量
		cardOrder.setPAY_DATE((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));// 支付日期
		cardOrder.setPAYMODE_CODE("绑卡");// 订单类型
										// 支付宝/微信/网银
		cardOrder.setORDER_NUMBER(System.currentTimeMillis() + "");
		cardOrder.setSTATUS(1);
		cardOrder.setORDER_TYPE(1);
		cardOrder.setCARD_NUMBER(card.getCARD_NUMBER());
		cardOrder.setCV_SET_ID(sysQ.getCvSetId());

		if (cVSetManage.toCostById(cardOrder.getCV_SET_ID()).size() > 0) {
			cardOrder.setITEM_NAME(cVSetManage.toCostById(cardOrder.getCV_SET_ID()).get(0).getName());
		} else {
			cardOrder.setITEM_NAME("");
		}
		systemCardOrderManage.AddCardOrder(cardOrder);

		// 用户卡绑定表中
		SystemCardUser systemuser = new SystemCardUser();
		systemuser.setBindDate2(DateUtil.getNowDate());
		systemuser.setUserId(syso.getUSER_ID());
		systemuser.setCardId(card.getID());
		systemuser.setSiteId(sysQ.getCvSetId());
		systemCardUserService.SaveBindUserinfor(systemuser);
	}

	public SystemCardManage getSystemCardManage() {
		return systemCardManage;
	}

	public void setSystemCardManage(SystemCardManage systemCardManage) {
		this.systemCardManage = systemCardManage;
	}

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

	public SystemLogManage getSystemLogManage() {
		return systemLogManage;
	}

	public void setSystemLogManage(SystemLogManage systemLogManage) {
		this.systemLogManage = systemLogManage;
	}
}
