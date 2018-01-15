package com.hys.exam.struts.action.logstudy;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetUnitDiscuss;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemCard;
import com.hys.exam.model.SystemCardOrder;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.XiangYiProvince;
import com.hys.exam.model.system.SystemCardUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CVSetUnitDiscussManage;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.SystemCardOrderManage;
import com.hys.exam.service.local.SystemCardUserService;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCVUnitVideoCensus;
import com.hys.xiangyi.service.FirstPageService;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyAction.java
 *     
 *     Description       :   保存学习记录处理类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-16                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyAction extends BaseAction {

	//保存学习记录(项目)Service接口
	private LogStudyCVSetManage localLogStudyCVSetManage;
	
	//保存学习记录(单元)Service接口
	private LogStudyCVUnitManage localLogStudyCVUnitManage;

	// 项目Service 接口
	private CVSetManage cVSetManage;
	
	private CVSetUnitDiscussManage UnitDiscussManage;
	
	// 用户绑卡记录
	private SystemCardUserService systemCardUserService;
	
	// 订单管理
	private SystemCardOrderManage systemCardOrderManage;
	
	private FirstPageService firstPageService;
	
	private CVUnitManage cvUnitManage;
	
	private CVManage localCVManage;
	
	private SystemUserManage systemUserManage ;
	
	private CvLiveManage localCvLiveManage;
	
	public CVUnitManage getCvUnitManage() {
            return cvUnitManage;
        }

        public void setCvUnitManage(CVUnitManage cvUnitManage) {
            this.cvUnitManage = cvUnitManage;
        }
	
	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}

	public void setLocalLogStudyCVUnitManage(LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}

	public FirstPageService getFirstPageService() {
		return firstPageService;
	}

	public void setFirstPageService(FirstPageService firstPageService) {
		this.firstPageService = firstPageService;
	}
	
	
	
	public SystemCardUserService getSystemCardUserService() {
		return systemCardUserService;
	}

	public void setSystemCardUserService(SystemCardUserService systemCardUserService) {
		this.systemCardUserService = systemCardUserService;
	}

	
	
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

	/**
	 * @author   张建国
	 * @time     2017-01-16
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 学习记录主控制方法
	 */
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		//获取操作类别
		String mode = RequestUtil.getParameter(request, "mode");
		if(mode.equals("add")){
			return add(mapping, form, request, response);
		}else if(mode.equals("addUnit")){
			return addUnit(mapping, form, request, response);
		}else if(mode.equals("tongjiUnitStudy")){
			return tongjiUnitStudy(mapping, form, request, response);
		}else if(mode.equals("tongjiProjectStudyLog")){
			return tongjiProjectStudyLog(mapping, form, request, response);
		}else if(mode.equals("queryLogin")){
			return queryLogin(mapping, form, request, response);
		}else if(mode.equals("saveVideoStudyLog")){//YHQ，保存视频学习记录，2017-03-09
			return saveVideoStudyLog(mapping, form, request, response);
		}else if(mode.equals("saveCensusVideoStudyLog")){//YXT，保存视频实际学习时间记录，2017-07-13
			return saveCensusVideoStudyLog(mapping, form, request, response);
		}else if(mode.equals("queryVideoStudyLog")){//YHQ，查询视频学习记录，2017-03-10
			return queryVideoStudyLog(mapping, form, request, response);
		}else if(mode.equals("playSubmit")){// CHY 提交卡号和付款信息
			return playSubmit(mapping, form, request, response);
		}else if(mode.equals("CheckBind")){
			// CHY 检测是否需要付费和输入对应的卡号
			return CheckBindCost(mapping, form, request, response);
		}else if(mode.equals("initializeCVAndCVSetStudyLog")){//初始化课程和项目学习记录
			return initializeCVAndCVSetStudyLog(mapping, form, request, response);
		}else if(mode.equals("initializeDemo")){//初始化课程和项目学习记录
			return initializeDemo(mapping, form, request, response);
		}else if(mode.equals("isExistOtherCVForLiveProject")){//初始化课程和项目学习记录
			return isExistOtherCVForLiveProject(mapping, form, request, response);
		}else if(mode.equals("projectCensus")){//初始化课程和项目学习记录（根据项目）
			return projectCensus(mapping, form, request, response);
		}
		return "list";
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 保存学习记录(项目)
	 */
	private String add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化学习记录(项目)
		LogStudyCVSet cvs = new LogStudyCVSet();
		cvs.setCvSetId(cvsetId);
		cvs.setSystemUserId(user.getUserId());
		//根据单元id判断是否存在记录
		List<LogStudyCVSet> list = localLogStudyCVSetManage.searchLogStudyCVSet(cvs);
		if(list!=null && list.size()>0){
			//执行修改记录
			localLogStudyCVSetManage.updLogStudyCVSet(cvs);
		}else{
			//执行保存记录
			localLogStudyCVSetManage.addLogStudyCVSet(cvs);
		}
		map.put("costType", cVSetManage.getCVSetById(cvsetId,user).getCost_type().toString());//拿到该项目的费用类型-----taoliang
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 保存学习记录(单元)
	 */
	private String addUnit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//课程Id
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		//单元Id
		Long cvUnitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//扩展阅读的内容
		String content = ParamUtils.getAttribute(request, "content");
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化学习记录(单元)
		LogStudyCVUnit cvs = new LogStudyCVUnit();
		cvs.setLogStudyCvSetId(cvsetId);
		cvs.setCvId(cvId);
		cvs.setCvUnitId(cvUnitId);
		cvs.setSystemUserId(user.getUserId());
		//根据单元id判断是否存在记录
		List<LogStudyCVUnit> list = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs);
		if(list!=null && list.size()>0){
			//执行修改记录
			localLogStudyCVUnitManage.updLogStudyCVUnit(cvUnitId,user.getUserId());//YHQ,2017-07-24,添加userId
		}else{
			//执行保存记录
			localLogStudyCVUnitManage.addLogStudyCVUnit(cvs);
			if (content != null) {//针对扩展阅读
			 cvUnitManage.addContent(content,cvUnitId);
			 cvs.setStatus(2);
			 localLogStudyCVUnitManage.addLogStudyCVUnit(cvs);//更改学习状态
			}
		}
		map.put("message", "success");
                
        //YHQ 2017-03-09，begin
        map.put("logStudyId", "0");                
        cvs = new LogStudyCVUnit();
		cvs.setLogStudyCvSetId(cvsetId);
		cvs.setCvId(cvId);
		cvs.setCvUnitId(cvUnitId);
		cvs.setSystemUserId(user.getUserId());
        list = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs);
        if(list!=null && list.size()>0){
            map.put("logStudyId", list.get(0).getId().toString());
        }
        //YHQ 2017-03-09，end
        
        map.put("costType", cVSetManage.getCVSetById(cvsetId,user).getCost_type().toString());//拿到该项目的费用类型-----taoliang        
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-16
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 统计个人学习的单元记录
	 */
	private String tongjiUnitStudy(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,Object> map = new HashMap<String,Object>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//获取当前用户信息
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化学习记录(项目)
		LogStudyCVSet cvs = new LogStudyCVSet();
		cvs.setCvSetId(cvsetId);
		cvs.setSystemUserId(user.getUserId());
		Map<String,Object> resuMap = localLogStudyCVSetManage.tongjiUnitStudy(cvs);
		/**
		 * 程宏业
		 * 2017-03-07
		 * 判断页面显示的颜色
		 * 
		 */
	/****                                  方法开始                             ********/
		CVSetUnitDiscuss discuss = new CVSetUnitDiscuss();
		String unitId = "";
		String color = "";
		String cla = "";
		if(resuMap.get("unitIds")!=null && StringUtils.isNotBlank(resuMap.get("unitIds").toString())){
			String Arr[] = resuMap.get("unitIds").toString().split("_");
			
			try {
				for (String str : Arr) {
					discuss.setCvsetId(cvsetId);
					discuss.setCvUnitId(Long.parseLong(str));
					CVUnit cvu = new CVUnit();
					cvu.setId(Long.parseLong(str));
					CVUnit cvu1 = cvUnitManage.findCvunit(cvu);
					
					// 已完成
					 List<com.hys.exam.model.LogStudyCVUnit> logUnitList = localLogStudyCVSetManage.searchLogStudyCVUnit("2",cvu1.getId(),user.getUserId());
					 
					 //添加直播课程单元为学习单元过滤
					 LogStudyCVUnit cvStudyLog = new LogStudyCVUnit();
					 cvStudyLog.setCvUnitId(cvu1.getId());
					 cvStudyLog.setSystemUserId(user.getUserId());
					 List<LogStudyCVUnit> logUnitListForLive = localLogStudyCVUnitManage.queryLogStudyCVUnitForLiveFilter(cvStudyLog);
					 if(logUnitListForLive.size() < 1){
						 // 未完成
						 if(logUnitList.size()>0){
							 unitId += cvu1.getId() + "_";
								cla += "1_";
						 }else{
							 	unitId += cvu1.getId() + "_";
								cla += "0_";
								
						 }
					 }

				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			map.put("color", color);
			map.put("cla", cla);
			
		}
		map.put("message", "success");
		/****                                  方法结束                             ********/
		/****                                  方法结束                             ********/
		if(resuMap!=null && resuMap.get("unitIds")!=null){
			map.put("unitIds", unitId);
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @author  张建国
	 * @time    2017-02-14
	 * @param   ActionMapping
	 * @return  String
	 * 方法说明：统计当前项目学习进度
	 */
	private String tongjiProjectStudyLog(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		// 获取当前登录人信息
		SystemUser user = (SystemUser) request.getSession().getAttribute(
				Constants.SESSION_TRAIN_ADMIN_USER);
		if (user == null || user.getUserId() == 0L) {
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		// 查询项目下的单元id集合
		List<CVUnit> unitList = localLogStudyCVSetManage
				.queryCVUnitByProjectId(null, cvsetId);
		if (unitList == null || unitList.size() <= 0) {
			map.put("message", "noUnit");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		// 查询用户已经学过的单元
		List<LogStudyCVUnit> logUnitList = localLogStudyCVSetManage
				.searchLogStudyCVUnit("5", cvsetId, user.getUserId());
		List<LogStudyCVUnit> fList = localLogStudyCVSetManage.searchLogStudyCVUnit("4",cvsetId,user.getUserId());
		
		// 对项目状态进行标记
		// 1.表示未开始学习 2.表示已经开始学习 3.表示已经结束学习
		if (logUnitList != null && logUnitList.size() == 0) {
			// 未开始
			map.put("message", "1");
		}
		if (logUnitList.size()>0){
			// 正在进行
			map.put("message", "2");
		}
		if (unitList.size() == fList.size()) {
			// 已结束
			
			map.put("message", "3");
			/***
			 * 2017-03-08 程宏业 如果已经完成则需要继续判断是否完成相关的任务指标
			 */

			/** 方法开始 */
			/*CVSetUnitDiscuss discuss = new CVSetUnitDiscuss();
			boolean flag = true;
			for (LogStudyCVUnit logStudyCVUnit : logUnitList) {
				discuss.setCvsetId(logStudyCVUnit.getCvId());
				discuss.setCvUnitId(logStudyCVUnit.getCvUnitId());
				discuss.setSystemUserId(logStudyCVUnit.getSystemUserId());
				long count = UnitDiscussManage.CountCVSetUnitDiscuss(discuss);
				CVUnit cvunit = cvUnitManage.findCvunit(new CVUnit(
						logStudyCVUnit.getCvUnitId()));
				if (count < cvunit.getQuota()) {
					flag = false;
					break;
				}
			}
			*//********** 方法结束 **********************//*
			if (flag) {
				map.put("message", "3");
			} else {
				map.put("message", "2");
			}*/
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	//首页登录判断
	private String queryLogin(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		System.out.println(id);
		
		String sid = String.valueOf(id);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			map.put("id", sid);
			map.put("code", "111111111");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		List<XiangYiProvince> xyList = firstPageService.getProvinceByCode(user.getUserId());
		map.put("message", "success");
		map.put("id", sid);
		if(xyList!=null && xyList.size()>0){
			map.put("code", xyList.get(0).getProvinceCode());
		}else{
			map.put("code", "111111111");
		}
		if(cvsetId > 0){
			map.put("costType", cVSetManage.getCVSetById(cvsetId,user).getCost_type().toString());//拿到该项目的费用类型-----taoliang
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
        
        /**
	 * @author   杨红强
	 * @time     2017-03-09
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 保存视频学习记录，YHQ 2017-03-09
	 */
	private String saveVideoStudyLog(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();		
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
                                
		Long logStudyCvUnitId = ParamUtils.getLongParameter(request, "logStudyCvUnitId", 0L);
        Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
        Long cvUnitId = ParamUtils.getLongParameter(request, "cvUnitId", 0L);
        Long videoLength = ParamUtils.getLongParameter(request, "videoLength", 0L);
        Long videoPlayLength = ParamUtils.getLongParameter(request, "videoPlayLength", 0L);
        String startDate = request.getParameter("startDate") ;
        
        LogStudyCVUnitVideo videoLog = new  LogStudyCVUnitVideo() ;
        videoLog.setLogStudyCvUnitId(logStudyCvUnitId);
        videoLog.setCvId(cvId);
        videoLog.setCvUnitId(cvUnitId);
        videoLog.setVideoLength(videoLength);
        videoLog.setVideoPlayLength(videoPlayLength);
        videoLog.setStartDate(startDate);
        videoLog.setSystemUserId(user.getUserId());
        boolean saveRes = localLogStudyCVUnitManage.saveLogStudyCVUnitVideo(videoLog) ;
        
        map.put("isPass", "0");
        LogStudyCVUnit logStudyCVUnitObj = new LogStudyCVUnit() ;
        logStudyCVUnitObj.setSystemUserId(user.getId());
        logStudyCVUnitObj.setCvUnitId(cvUnitId);
        List<LogStudyCVUnit> logStudyCVUnitList = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(logStudyCVUnitObj) ;
        if (logStudyCVUnitList != null && logStudyCVUnitList.size() > 0) {
        	logStudyCVUnitObj = logStudyCVUnitList.get(0) ;
        	if (logStudyCVUnitObj != null) {
        		Integer logStudyStatus = logStudyCVUnitObj.getStatus() ;
        		if (logStudyStatus != null && logStudyStatus == 2) map.put("isPass", "1");
        	}
        }
                
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
    /**
	 * @author   yxt
	 * @time     2017-07-13
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 保存视频实际学习时间记录，YXT 2017-07-13
	 */
	private String saveCensusVideoStudyLog(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();		
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
                             
        Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);
        Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
        Long cvUnitId = ParamUtils.getLongParameter(request, "cvUnitId", 0L);
        Long videoEchoLength = ParamUtils.getLongParameter(request, "videoEchoLength", 0L);
        Long videoStartLength = ParamUtils.getLongParameter(request, "videoStartLength", 0L);
        Long videoEndLength = ParamUtils.getLongParameter(request, "videoEndLength", 0L);
        Long videoLength = ParamUtils.getLongParameter(request, "videoLength", 0L);
        
        LogStudyCVUnitVideoCensus log = new  LogStudyCVUnitVideoCensus(user.getUserId(),cvSetId,cvId,cvUnitId) ;
        log.setVideoEchoLength(videoEchoLength);
        log.setVideoStartLength(videoStartLength);
        log.setVideoEndLength(videoEndLength);
        log.setVideoLength(videoLength);
        boolean saveRes = localLogStudyCVUnitManage.saveLogStudyCVUnitVideoCensus(log) ;
        if(saveRes){
        	map.put("message", "success");
        }else{
        	map.put("message", "error");
        }
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
        
     /**
      * @author taoliang
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @desc 用于直播和点播课程学习记录维护
      */
	private String initializeCVAndCVSetStudyLog(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		//Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
        Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
        //Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		Map<String,Object> map = new HashMap<String,Object>();		
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		
		if(cvId < 1L){
			map.put("message", "cvId is null!");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		/*if(unitId < 1L){
			map.put("message", "unitId is null!");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}*/
    	
		CV cv = new CV();
    	cv.setId(cvId);
    	List<CV> cvList = localCVManage.queryCVForCommunal(cv);
    	
    	if(cvList == null){
    		map.put("message", "cv is missing!");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
    	CV cv1 = cvList.get(0);
    	
    	//课程学习表记录处理
    	localLogStudyCVUnitManage.updateCVForLogStudy(cv1, user);
    	//项目学习表记录处理
    	localLogStudyCVUnitManage.updateCVSetForLogStudy(cv1, user);
    	
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	
	private String initializeDemo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,Object> map = new HashMap<String,Object>();	
		int i=0;
		//首先获取所有会员的课程记录
		List<LogStudyCVSet> list = localLogStudyCVSetManage.getAllLogStudyCVSet();
		if(list.size() > 0){
			System.out.println("所有用户的项目学习记录共计："+list.size()+"条");
			for(LogStudyCVSet cvSetLog : list){
				SystemUser user = new SystemUser();
				user.setUserId(cvSetLog.getSystemUserId());
				user = systemUserManage.queryUserForLive(user);
				
				if(user == null){
					continue;
				}
				i++;
				System.out.println("NUM:"+i+"===用户["+user.getAccountName()+"]的项目["+cvSetLog.getCvSetId()+"]开始初始化");
				List<CV> cvList = cVSetManage.getCvList(cvSetLog.getCvSetId());
				if(cvList.size() > 0){
					for(CV cv : cvList){
						//课程学习表记录处理
				    	localLogStudyCVUnitManage.updateCVForLogStudy(cv, user);
				    	//项目学习表记录处理
				    	localLogStudyCVUnitManage.updateCVSetForLogStudy(cv, user);
					}
				}
			}
		}
		map.put("count", i);
		map.put("total", list.size());
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	public CVSetUnitDiscussManage getUnitDiscussManage() {
		return UnitDiscussManage;
	}

	public void setUnitDiscussManage(CVSetUnitDiscussManage unitDiscussManage) {
		UnitDiscussManage = unitDiscussManage;
	}
     
	
	/***
	 * 
	 * 判断用户是否有学习项目的权限以及是否需要支付项目的费用
	 * 方法名：CheckUserPass
	 * 创建人：程宏业
	 * 时间：2017-4-17-下午5:04:57  void
	 * @exception 
	 * @since  1.0.0
	 */
	public SystemCard CheckUserPass(SystemUser user){
			
		if(user !=null){
		
			// 如果用户不为空查询该用户有多少张卡
		List<SystemCard> list = systemCardUserService.findListByUserId(user.getUserId(),user.getId());
		if(list.size()>0){
			return list.get(0);
		}
	
	}
	return null;
}

	
/***
 * 
 * 判断项目需不需要绑卡
 * 方法名：CheckNeedBind
 * 创建人：程宏业
 * 时间：2017-4-18-下午2:06:30 
 * @param proid
 * @return List<SystemCardTypeCvSet>
 * @exception 
 * @since  1.0.0
 */
	public List<SystemCardTypeCvSet> CheckNeedBind(Long proid){
		List<SystemCardTypeCvSet> list = systemCardUserService.findListByProid(proid);
		
		return list;
	}
	
	

/***
 * 	
 * 
 * 方法名：Price
 * 创建人：程宏业
 * 时间：2017-4-17-下午8:22:44 
 * @return String
 * @exception 
 * @since  1.0.0
 */
public Double Cost(Long proid){
	
	if(proid!=null){
		List<CVSet> list = cVSetManage.toCostById(proid);
		if(list !=null && list.size()>0){
			return list.get(0).getCost();
		}
	}
	
	return null;
}

/***
 * 
 * 提交支付和卡号类型验证
 * 方法名：playSubmit
 * 创建人：程宏业
 * 时间：2017-4-18-下午2:18:42 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return String
 * @exception 
 * @since  1.0.0
 */
	public String playSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		SystemUser user = (SystemUser) request.getSession().getAttribute(
				Constants.SESSION_TRAIN_ADMIN_USER);
		// 项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		String cardNumber = request.getParameter("cardNumber");
		String cardPassword = request.getParameter("cardPassword");
		SystemCard card = null;

		// 目前输入卡号
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
							card = newcard;
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
			}
			/*// 通过卡号和密码查询学习卡
			SystemCard newcard = systemCardUserService
					.findCardByCardNumberAndpassword(cardNumber, cardPassword);
			if (newcard != null) {
				user.setId(cvsetId);
				List<SystemCard> findByProidNumber = systemCardUserService
						.findByProidNumber(cvsetId, cardNumber);
				if (findByProidNumber != null && findByProidNumber.size() > 0) {
					if(findByProidNumber.get(0).getStatus() != null && findByProidNumber.get(0).getStatus() == 2){
						map.put("message", "alreadyBind");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
					}
					card = findByProidNumber.get(0);
				}

				if (card != null) {
					//添加学习卡被禁用拦截
					if(newcard.getStatus() != null && -2 == newcard.getStatus()){
						map.put("message", "typedisable");
						StrutsUtil.renderText(response, JsonUtil.map2json(map));
						return null;
					}
					// 判断学习卡是否过期
					String startDateStr = DateUtils.DateToString(card
							.getUSEFUL_DATE());
					String endDateStr = DateUtils.DateToString(new Date());
					int time = DateUtils.compareDate(startDateStr, endDateStr,
							0);
					if (time > 0) {
						map.put("message", "time");
					} else if (!"".equals(cardNumber)
							&& cardNumber.equals(card.getCARD_NUMBER())) {

						// 获取卡号类型
						List<SystemCardTypeCvSet> cvsetlit = systemCardUserService
								.findListByCardType(Long.parseLong(card
										.getCARD_TYPE().toString()));
						if (cvsetlit != null && cvsetlit.size() > 0) {
							for (SystemCardTypeCvSet systemCardTypeCvSet : cvsetlit) {

								// 查询表中是否存在

								List<SystemCardOrder> orderlist = systemCardOrderManage
										.findByUidProid(user.getUserId(), Long
												.parseLong(systemCardTypeCvSet
														.getCV_SET_ID()
														.toString()),
												cardNumber);
								if (orderlist.size() > 0)
									// 如果存在跳出本次循环
									continue;
								SystemCardOrder cardOrder = new SystemCardOrder();
								cardOrder.setCARD_TYPE_ID(card.getCARD_TYPE());// 卡类型
								cardOrder.setUSER_ID(user.getUserId());// 用户id
								cardOrder.setUSEFUL_DATE(card.getUSEFUL_DATE());// 订单日期
								cardOrder.setPRICE(card.getFACE_VALUE()==null ? 0.00 : card.getFACE_VALUE());// 价格
								cardOrder.setAMOUNT(1);// 数量
								cardOrder
										.setPAY_DATE((new java.text.SimpleDateFormat(
												"yyyy-MM-dd hh:mm:ss"))
												.format(new Date()));// 支付日期
								cardOrder.setPAYMODE_CODE("绑卡");// 订单类型
																// 支付宝/微信/网银
								cardOrder.setORDER_NUMBER(System
										.currentTimeMillis() + "");
								cardOrder.setSTATUS(1);
								cardOrder.setORDER_TYPE(1);
								cardOrder.setCARD_NUMBER(card.getCARD_NUMBER());
								cardOrder.setCV_SET_ID(Long
										.parseLong(systemCardTypeCvSet
												.getCV_SET_ID().toString()));

								if (cVSetManage.toCostById(
										cardOrder.getCV_SET_ID()).size() > 0) {
									cardOrder.setITEM_NAME(cVSetManage
											.toCostById(
													cardOrder.getCV_SET_ID())
											.get(0).getName());
								} else {
									cardOrder.setITEM_NAME("");
								}
								systemCardOrderManage.AddCardOrder(cardOrder);

								// 用户卡绑定表中
								SystemCardUser systemuser = new SystemCardUser();
								systemuser.setBindDate2(DateUtil.getNowDate());
								systemuser.setUserId(user.getUserId());
								systemuser.setCardId(card.getID());
								systemuser.setSiteId(systemCardTypeCvSet
										.getCV_SET_ID());
								systemCardUserService
										.SaveBindUserinfor(systemuser);

							}
							// 更改绑卡状态
							card.setSTATUS(2);
							systemCardUserService.UpdateCard(card);

							// 添加成功
							map.put("message", "success");

						} else {
                            // 没有对应的卡类型
							map.put("message", "typeno");
						}

					} else {
						map.put("message", "error");
					}
				} else {
					map.put("message", "notbind");
				}

			} else {
				// 卡号或密码错误
				map.put("message", "notfind");
			}*/

		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}

/***
 * 
 *检测项目是否需要付费和绑定相关的卡号
 * 方法名：CheckBindCost
 * 创建人：程宏业
 * 时间：2017-4-18-下午4:11:21 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return String
 * @exception 
 * @since  1.0.0
 */
	public String CheckBindCost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SystemUser user = (SystemUser) request.getSession().getAttribute(
				Constants.SESSION_TRAIN_ADMIN_USER);
		//Double Costs = 0.0;
		Map<String, String> map = new HashMap<String, String>();
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		Long costType = 0L;
		String ct = request.getParameter("costType");
		if(ct != null && !ct.equals("")){
			costType = ParamUtils.getLongParameter(request, "costType", 0L);//0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
		}else{
			CVSet ccc = cVSetManage.getCVSetById(cvsetId,user);
			if(ccc==null){
				map.put("erro", "未查找到符合条件的项目！");
				StrutsUtil.renderText(response, JsonUtil.map2json(map));
				return null;
			}
			costType = Long.parseLong(ccc.getCost_type().toString());
		}
		//List<SystemCardOrder> list;
		Boolean order = false;
		//第一步先判断该项目是否为卡项目
		if(costType == 1L){
			//如果是，第二步判断卡类型里是否包含该项目
			List<SystemCardTypeCvSet> cvsetlist = systemCardUserService.findListByProid(cvsetId);
			if(cvsetlist.size()>0){ 
				 //list = systemCardOrderManage.findByUidProid2(user.getUserId(),cvsetId,"");
				order = systemCardOrderManage.findByUidProid2(user.getUserId(),cvsetId);
				 if(order){ // 表示已有订单或者已经绑过对应的卡
					 map.put("card","0"); 
				 }else{
					 map.put("card", "1");
				 } 
			 }else{//如果不包含则该项目非绑卡项目
				 map.put("card", "1");
			 }
		}else{
			// 插入对应的方法 程红叶2017-04-18
			List<SystemCardTypeCvSet> cvsetlist = systemCardUserService.findListByProid(cvsetId);
			//表示该项目需要绑卡
			 if(cvsetlist.size()>0){ // 判断是否已经支付该项目 
				 //list = systemCardOrderManage.findByUidProid2(user.getUserId(),cvsetId,"");
				 order = systemCardOrderManage.findByUidProid2(user.getUserId(),cvsetId);
				 if(order){ // 表示已有订单或者已经绑过对应的卡
					 map.put("card","0"); 
				 }else{
					 map.put("card", "1");
				 } 
			 }else{
				 map.put("card", "0"); 
			 }
		 
		}
		// 取出价格
       /* Costs = Cost(cvsetId);
		
		if (Costs > 0) {
			// 表示需要付费
			list = systemCardOrderManage.findByUidProid(user.getUserId(),
					cvsetId,"");
			if (list != null && list.size() > 0) {
				// 已经支付或绑卡
				map.put("cost", "0");
				map.put("card", "0");
			} else {
				// 首先判断学习项目是否需要收费如果需要收费再判断是否绑卡
				List<SystemCardTypeCvSet> cvsetlist = systemCardUserService
						.findListByProid(cvsetId);
				if (cvsetlist.size() > 0) {
					// 表示需要绑卡
					map.put("card", "1");
				} else {
					// 表示不需要绑卡
					map.put("card", "0");
				}
				// 需要收费

				map.put("cost", "1");
			}
		} else {
			// 不需要付费和绑卡
			map.put("cost", "0");
			map.put("card", "0");
		}
*/
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}

	
    /**
	 * @author   taoliang
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 初始化课程和项目学习记录
	 */
	private String queryVideoStudyLog(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,Object> map = new HashMap<String,Object>();		
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
	            
	            Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
	            Long cvUnitId = ParamUtils.getLongParameter(request, "cvUnitId", 0L);                
	            
	            LogStudyCVUnitVideo videoLog = new  LogStudyCVUnitVideo() ;               
	            videoLog.setCvId(cvId);
	            videoLog.setCvUnitId(cvUnitId);                
	            videoLog.setSystemUserId(user.getUserId());
	            videoLog = localLogStudyCVUnitManage.queryLogStudyCVUnitVideo(videoLog) ;
	            if (videoLog != null) {
	                map.put("videoLog", videoLog) ;
	            }		
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	
private String isExistOtherCVForLiveProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,Object> map = new HashMap<String,Object>();	
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);
		if(cvSetId < 1){
			map.put("message", "cvSetId is null!");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		
		boolean trueOrfalse = localCVManage.isExistOtherCVForLiveProject(cvSetId);
		if(trueOrfalse == true){
			CVUnit unit = cvUnitManage.getUnitForLive(cvSetId);
			if(unit != null){
				map.put("cvId", unit.getCvId());
				map.put("unitId", unit.getId());
				if(unit.getCvId() != null){
					List<CvLive> liveList = localCvLiveManage.queryCvLiveList(unit.getCvId());
					if(liveList.size() > 0){
						map.put("startDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(liveList.get(0).getStart_date()));
						map.put("endDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(liveList.get(0).getInvalid_date()));
					}
				}
			}
		}
		map.put("res", trueOrfalse);
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}


	//此方法用于根据项目去维护当前用户的学习记录（此版本因为历史原因，此处就不做和其他统计做兼容）------taoLiang
	private String projectCensus(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		Long cvSetId = ParamUtils.getLongParameter(request, "cvSetId", 0L);
		Map<String,Object> map = new HashMap<String,Object>();
		if(user != null  && user.getUserId() > 0L){//如果用户未登录则不需要进行项目记录维护
			//首先判断该项目是否被学过，没有被学则不用统计维护
			LogStudyCVSet logStudyCVSet = new LogStudyCVSet();
        	logStudyCVSet.setCvSetId(cvSetId);
        	logStudyCVSet.setSystemUserId(user.getUserId());
        	List<LogStudyCVSet> list = localLogStudyCVSetManage.searchLogStudyCVSet(logStudyCVSet);
        	if(list != null && list.size() > 0){
				List<CV> cvList = cVSetManage.getCvList(cvSetId);
				if(cvList != null && cvList.size() > 0){
					for(CV cv : cvList){
						//课程学习表记录处理
				    	localLogStudyCVUnitManage.updateCVForLogStudy(cv, user);
				    	//项目学习表记录处理
				    	localLogStudyCVUnitManage.updateCVSetForLogStudy(cv, user);
					}
				}
        	}
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}

	public SystemUserManage getSystemUserManage() {
		return systemUserManage;
	}

	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}

	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}

}
