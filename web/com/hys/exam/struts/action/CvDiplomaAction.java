package com.hys.exam.struts.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.DiplomaService;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CvDiplomaEntity;

public class CvDiplomaAction extends BaseAction {
	
	private CvLiveManage localCvLiveManage;

	private DiplomaService diplomaService;
	
	private CVSetManage localCVSetManage;

	private SystemUserManage localSystemUserManage;

	private ExamPropValFacade localExamPropValFacade;

	private LogStudyCVUnitManage logStudyManage;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String method = RequestUtil.getParameter(request, "method");

		if (method.equals("save")) {
			addDiplomaEntity(request, response);
			return null;
		} else if (method.equals("find")) {

			return getlist(request, response);

		} else if (method.equals("QueryDiplomaByIdCard")) {

			return QueryDiplomaByIdCard(request, response);
	

		} else if (method.equals("QueryDiplomaByIdCard2")) {

			return QueryDiplomaByIdCard2(request, response);
		}

		else {
			return getCVDip(request, response);

		}

	}
	/**
	 * 
	 * (申请证书) 方法名：addDiplomaEntity 创建人：程宏业 时间：2017-3-1-上午10:57:43 手机:15210211487
	 * 
	 * @param req
	 * @param rep
	 * @throws Exception
	 *             void
	 * @exception @since
	 *                1.0.0
	 */

	public void addDiplomaEntity(HttpServletRequest req, HttpServletResponse rep)

			throws Exception {
		Long userid = Long.parseLong(req.getParameter("system_user_id").trim());
		CvDiplomaEntity t = new CvDiplomaEntity();
		// t.setApply_time(req.getParameter("apply_time").substring(0, 19));
		t.setApply_time(DateUtil.format(new Date()));
		t.setBegin_time(req.getParameter("begin_time").substring(0, 19));
		t.setEnd_time(req.getParameter("end_time").substring(0, 19));
		t.setId_number(req.getParameter("id_number"));
		t.setIs_delete(req.getParameter("is_delete"));
		t.setItem_grade(req.getParameter("item_grade"));
		t.setItem_type(req.getParameter("item_type"));
		if (t.getItem_type().equals("远程")) {
			t.setDiploma_number(createSerial("XFYC", req.getParameter("apply_time").substring(0, 4), 6));
		} else if (t.getItem_type().equals("面授")) {
			t.setDiploma_number(createSerial("XFMS", req.getParameter("apply_time").substring(0, 4), 6));
		} else {
			t.setDiploma_number(createSerial("XFHH", req.getParameter("apply_time").substring(0, 4), 6));
		}
		t.setSystem_user_name(req.getParameter("system_user_name"));
		t.setItem_number(req.getParameter("item_number"));
		t.setItem_name(req.getParameter("item_name"));
		t.setItem_score(req.getParameter("item_score"));
		t.setSystem_user_id(userid);
		t.setPath_url(getUrl(req) + "?method=findT&diploma_number=" + t.getDiploma_number());
		diplomaService.addDiplomaEntity(t);

	}

	/**
	 * 
	 * (根据证书编号查询证书) 方法名：getlist 创建人：程宏业 时间：2017-3-1-上午10:58:38 手机:15210211487
	 * 
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 *             String
	 * @exception @since
	 *                1.0.0
	 */
	public String getlist(HttpServletRequest req, HttpServletResponse rep) throws Exception {
		
		Long userid = Long.parseLong(req.getParameter("system_user_id").trim());
		//String item_number = req.getParameter("ITEM_NUMBER").trim();
		CvDiplomaEntity t = new CvDiplomaEntity();
		t.setDiploma_number(req.getParameter("diploma_number"));
		t.setItem_number(req.getParameter("item_number"));
		t.setSystem_user_id(userid);
		String item_number = t.getItem_number();

		if (diplomaService.findListByItemNumber(t).size() > 0) {
			CvDiplomaEntity dip = diplomaService.findListByItemNumber(t).get(0);
			LogStudyCVUnit cvUnit = new LogStudyCVUnit();
			cvUnit.setSystemUserId(userid);
			Long cvSetId =(long) localCVSetManage.findCvSetId(item_number);
			cvUnit.setLogStudyCvSetId(cvSetId);
			List<LogStudyCVUnit> logList = logStudyManage.queryLogStudyCVUnitByCVId(cvUnit);
			if (logList != null && logList.size() > 0) {
				dip.setBegin_time(logList.get(logList.size() - 1).getLastUpdateDate());
				dip.setEnd_time(logList.get(0).getLastUpdateDate());
			}
			String bean = beanToJson(dip);
			StrutsUtil.renderText(rep, bean);
		}

		return null;

	}

	// 证书扫二维码跳转的页面
	public String getCVDip(HttpServletRequest req, HttpServletResponse rep) throws Exception {
		// Long userid =
		// Long.parseLong(req.getParameter("system_user_id").trim());
		// Long cvSetId = Long.parseLong(req.getParameter("cvSetId").trim());
		Long userid = ParamUtils.getLongParameter(req, "system_user_id", 0L);
		Long cvSetId = ParamUtils.getLongParameter(req, "cvSetId", 0L);
		List<CvDiplomaEntity> diploma = null;
		CvDiplomaEntity t = new CvDiplomaEntity();
		t.setDiploma_number(req.getParameter("diploma_number"));
		if (diplomaService.findListByItemNumber(t).size() > 0) {
			diploma = diplomaService.findListByItemNumber(t);
			for (CvDiplomaEntity cvDip : diploma) {
				LogStudyCVUnit cvUnit = new LogStudyCVUnit();
				cvUnit.setSystemUserId(userid);
				cvUnit.setLogStudyCvSetId(cvSetId);
				List<LogStudyCVUnit> logList = logStudyManage.queryLogStudyCVUnitByCVId(cvUnit);
				if (logList != null && logList.size() > 0) {
					cvDip.setBegin_time(logList.get(logList.size() - 1).getLastUpdateDate());
					cvDip.setEnd_time(logList.get(0).getLastUpdateDate());
				}
			}
		}

		String lastUpdateDate = req.getParameter("lastUpdateDate");
		req.setAttribute("diploma", diploma);
		req.setAttribute("lastUpdateDate", lastUpdateDate);

		return "success";
	}

	/**
	 * 
	 * (通过身份证查询用户证书) 方法名：QueryDiplomaByIdCard 创建人：程宏业 时间：2017-3-1-上午11:02:38
	 * 手机:15210211487
	 * 
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 *             String
	 * @exception @since
	 *                1.0.0
	 */
	public String QueryDiplomaByIdCard(HttpServletRequest req, HttpServletResponse rep) throws Exception {

		Double score = 0.0;
		Map<String, Object> map = new HashMap<String, Object>();
		CvDiplomaEntity diploma = new CvDiplomaEntity();
		// 身份证号
		
		String idCard = req.getParameter("idCard").trim();
		System.out.println(idCard);
		String beginT = req.getParameter("begintime");
		String endT = req.getParameter("endtime");
		SystemUser user = localSystemUserManage.getUserByIdCard(idCard);
		
		if (user != null) {
			
			diploma.setBegin_time(beginT + "-01-01 00:00:00");
			diploma.setEnd_time(endT + "-12-31 24:00:00");
			diploma.setSystem_user_id(user.getId());
			List<CvDiplomaEntity> list = diplomaService.findListByUidTime(diploma);
			for (CvDiplomaEntity cvDiplomaEntity : list) {
				if (cvDiplomaEntity.getItem_score() != null) {
					score += Double.valueOf(cvDiplomaEntity.getItem_score());
				}
			}
			SystemUser item = localSystemUserManage.getItemById(user.getId());

			String workType = "";
			if (item.getJob_Id() != null) {
				// 取得职称列表
				ExamProp ep = localExamPropValFacade.getSysPropVal(Long.valueOf(item.getJob_Id()));

				// 取得职称类型列表
				ExamProp jobclass = new ExamProp();
				jobclass.setType(24);
				List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);

				for (ExamProp examProp : jobclassList) {
					if (ep.getExt_type() != null && ep.getExt_type() == Integer.valueOf(examProp.getCode())) {
						workType = examProp.getName();
					}
				}
			}

			user.setWorkUnit(workType);
			user.setEmail(score.toString());
			map.put("user", user);
			map.put("list", list);
			map.put("code", 0);
			map.put("item", item);
		
			String bean = JSONObject.fromObject(map).toString();
			StrutsUtil.renderText(rep, bean);
		} else {
			map.put("code", 1);
			String bean = JSONObject.fromObject(map).toString();
			StrutsUtil.renderText(rep, bean);
		}
		return null;

	}

	/**
	 * 
	 * (通过身份证查询用户证书) 方法名：QueryDiplomaByIdCard 创建人：程宏业 时间：2017-3-1-上午11:02:38
	 * 手机:15210211487
	 * 
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 *             String
	 * @exception @since
	 *                1.0.0
	 */
	public String QueryDiplomaByIdCard2(HttpServletRequest req, HttpServletResponse rep) throws Exception {

		Double score = 0.0;
		Map<String, Object> map = new HashMap<String, Object>();
		CvDiplomaEntity diploma = new CvDiplomaEntity();
		// 身份证号
		String idCard = req.getParameter("idCard").trim();
		String beginT = req.getParameter("begintime");
		String endT = req.getParameter("endtime");
		SystemUser user = localSystemUserManage.getUserByIdCard(idCard);
		if (user != null) {
			diploma.setBegin_time(beginT + "-01-01 00:00:00");
			diploma.setEnd_time(endT + "-12-31 24:00:00");
			diploma.setSystem_user_id(user.getId());
			List<CvDiplomaEntity> list = diplomaService.findListByUidTime(diploma);
			for (CvDiplomaEntity cvDiplomaEntity : list) {
				if (cvDiplomaEntity.getItem_score() != null) {
					score += Double.valueOf(cvDiplomaEntity.getItem_score());
				}
			}
			SystemUser item = localSystemUserManage.getItemById(user.getId());

			String workType = "";
			if (item.getJob_Id() != null) {
				// 取得职称列表
				ExamProp ep = localExamPropValFacade.getSysPropVal(Long.valueOf(item.getJob_Id()));

				// 取得职称类型列表
				ExamProp jobclass = new ExamProp();
				jobclass.setType(24);
				List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);

				for (ExamProp examProp : jobclassList) {
					if (ep.getExt_type() != null && ep.getExt_type() == Integer.valueOf(examProp.getCode())) {
						workType = examProp.getName();
					}
				}
			}

			user.setWorkUnit(workType);
			user.setEmail(score.toString());
			map.put("user", user);
			map.put("list", list);
			System.out.println(list);
			map.put("code", 0);
			map.put("item", item);
			String bean = JSONObject.fromObject(map).toString();
			StrutsUtil.renderText(rep, bean);
		} else {
			map.put("code", 1);
			String bean = JSONObject.fromObject(map).toString();
			StrutsUtil.renderText(rep, bean);
		}
		return null;

	}

	// ben to json
	public static String beanToJson(Object bean) {

		JSONObject json = JSONObject.fromObject(bean);

		return json.toString();

	}

	// 获取项目URL路径
	public String getUrl(HttpServletRequest req) {

		return req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + req.getServletPath();
	}

	// service getting and setting

	public DiplomaService getDiplomaService() {
		return diplomaService;
	}

	public void setDiplomaService(DiplomaService diplomaService) {
		this.diplomaService = diplomaService;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public void setLogStudyManage(LogStudyCVUnitManage logStudyManage) {
		this.logStudyManage = logStudyManage;
	}
	
	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	// 生产随机账号
	public static synchronized String createSerial(String sysFlg, String str, int randomCount) {
		safeSleep(1);
		return sysFlg + str + RandomStringUtils.randomNumeric(randomCount);
	}

	public static void safeSleep(long millis) {

		try {
			Thread.sleep(millis);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 字符串日期相互转化

	public Date StoD(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {

		System.out.println("2017-02-26 01:32:12".length());
		System.out.println(createSerial("XFMS", "1234", 6));
	}

}
