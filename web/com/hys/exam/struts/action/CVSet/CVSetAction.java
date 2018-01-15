package com.hys.exam.struts.action.CVSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.UserImage;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.CVS_Form;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.exam.model.PropUnit;

public class CVSetAction extends BaseAction {

	private ExamPropValFacade localExamPropValFacade;
	
	private CVSetManage localCVSetManage;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		String mode = RequestUtil.getParameter(request, "mode");
		SystemUser user = LogicUtil.getSystemUser(request);
		
		if(mode.equals("get_CVS")){
			return get_CVS(mapping, form, request, response);
		} else if(mode.equals("CVS_add")){
			return add(mapping, form, request, response);
		} else if(mode.equals("del")){
			return del(mapping, form, request, response);
		} else if (mode.equals("updateState")) {
			return updateState(mapping, form, request, response);
		} else if(mode.equals("getByAjax")){
			return getByAjax(mapping, form, request, response);
		}else if(mode.equals("union")){
			return Union(mapping, form, request, response);
		}else if(mode.equals("pinyin")){
			return getHanyuPinyinString(mapping, form, request, response);
		}else if(mode.equals("myXiangMu")) {
			return myXiangMuList(mapping, form, request, response);
		}else if(mode.equals("myXueKe")){
			return myXueKeList(mapping, form, request, response);
		}else {}
		List<CVSet> list = new ArrayList<CVSet>();
		List<PropUnit> courseList = new ArrayList<PropUnit>();
		PropUnit searchProp = new PropUnit();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);		
		String xueke = request.getParameter("xueke");				
		String sign = request.getParameter("sign");
		int level = ParamUtils.getIntParameter(request, "level", 1);
		
		String creater = request.getParameter("creater");
		String CVSetName = request.getParameter("item_name");
		String CVSetSiteUrl = request.getParameter("sitelist");
		String CVSetLevel = ParamUtils.getParameter(request, "level_h", "-1");
		String CVSetCostSort = request.getParameter("cost_sort");
		String CVSetScoreSort = request.getParameter("score_sort");
		String CVSetRecentCreate = request.getParameter("recent_create");
		Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);	
		
		Date available_period = new Date();
		
		SystemSite systemSite = new SystemSite();
		systemSite.setDomainName(CVSetSiteUrl);
		
		List<SystemSite> siteList = new ArrayList<SystemSite>();
		siteList.add(systemSite);
		
		CVSet queryCVSet = new CVSet();	
		queryCVSet.setMaker(creater);
		if(mode.equals("qualify")){
			queryCVSet.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
			queryCVSet.setFlag(mode);
		}
		
		if(xueke != null && !xueke.equals(""))
		{			
			ExamPropQuery query  = new ExamPropQuery();
			
			List<ExamProp> list2;
			query.setSysPropId(Long.valueOf(xueke));
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			list2 = rprop.getReturnList();
			
			if(list2 != null && !list2.equals(""))
			{
				for (int i = 0; i < list2.size(); i++){
					
					List<ExamProp> list3;
					ExamPropQuery query2  = new ExamPropQuery();
					query2.setSysPropId(list2.get(i).getId());
					ExamReturnProp rprop2 = localExamPropValFacade.getNextLevelProp(query2);
					list3 = rprop2.getReturnList();
					for(ExamProp e : list3)
					{
						PropUnit pro = new PropUnit();
						pro.setId(e.getId());
						pro.setName(e.getName());
						courseList.add(pro);
					}
				}
			}
		}
		queryCVSet.setId(id);
		queryCVSet.setName(CVSetName);		
		queryCVSet.setCourseList(courseList);		
		queryCVSet.setStatus(1);		
		if(sign != null && sign.equals("0"))
		{
			queryCVSet.setSign("指南");
		}
		else if(sign != null && sign.equals("1"))
		{
			queryCVSet.setSign("公需科目");
		}
		queryCVSet.setStart_date(available_period);	
		queryCVSet.setCost_sort(CVSetCostSort);
		queryCVSet.setScore_sort(CVSetScoreSort);
		queryCVSet.setRecent_create(CVSetRecentCreate);
		if(CVSetLevel != null && !CVSetLevel.equals("-1"))
		{
			queryCVSet.setLevel(Integer.valueOf(CVSetLevel));	
		}
		
		list = localCVSetManage.getCVSetList(queryCVSet);
		
		for (CVSet cvSet:list) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}
		
		request.setAttribute("costSort", CVSetCostSort);
		request.setAttribute("scoreSort", CVSetScoreSort);
		request.setAttribute("ItemName", queryCVSet.getName());
		//request.setAttribute("xueke", queryCVSet.getName());
		request.setAttribute("creater", queryCVSet.getName());
		request.setAttribute("status", queryCVSet.getStatus());
		request.setAttribute("xueke", xueke);
		request.setAttribute("sign", sign);
		request.setAttribute("CVSet", list);
		request.setAttribute("level", CVSetLevel);
		
		return "list";
	}

	private String myXueKeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		List<CVSet> list = new ArrayList<CVSet>();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);		
		String xueke = request.getParameter("xueke");
		String creater = request.getParameter("creater");
		String CVSetName = request.getParameter("CVSetName");
		Integer CVSetStatus = ParamUtils.getIntParameter(request, "CVSetStatus", 0);		
		
		CVSet queryCVSet = new CVSet();		
		queryCVSet.setId(id);
		queryCVSet.setName(CVSetName);
		queryCVSet.setMaker(creater);
		//queryCVSet.setCourseList(xueke);
		
		queryCVSet.setStatus(CVSetStatus);
		
		list = localCVSetManage.getCVSetList(queryCVSet);
		
		for (CVSet cvSet:list) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}
		
		request.setAttribute("sname", queryCVSet.getName());
		//request.setAttribute("xueke", queryCVSet.getName());
		request.setAttribute("creater", queryCVSet.getName());
		request.setAttribute("status", queryCVSet.getStatus());
		request.setAttribute("CVSet", list);
		
		return "myXueKe";
	}

	private String myXiangMuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String xueke = request.getParameter("xueke");
		String creater = request.getParameter("creater");
		String CVSetName = request.getParameter("CVSetName");
		//Long CVSetStatus = ParamUtils.getLongParameter(request, "CVSetStatus", 0L);		
		
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();		
		
		queryCVSet.setName(CVSetName);
		queryCVSet.setMaker(creater);
		//queryCVSet.setCourseList(xueke);
		//queryCVSet.set
		//queryCVSet.setStatus(CVSetStatus);
		queryCVSet.setId(-1L);
		queryCVSet.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		list = localCVSetManage.getCVSetList(queryCVSet);
		
		for (CVSet cvSet:list) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}
		
		request.setAttribute("CVSet", list);
		request.setAttribute("sname", queryCVSet.getName());
		//request.setAttribute("xueke", queryCVSet.getName());
		request.setAttribute("creater", queryCVSet.getName());
		request.setAttribute("status", queryCVSet.getStatus());
		
		return "myXiangMu";
	}

	private String Union(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet  = new CVSet(); 
		queryCVSet.setId(id);
		list = localCVSetManage.getCVSetList(queryCVSet);
		request.setAttribute("list", list);
		JSONObject result = new JSONObject();
		result.put("CVS", list);
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}

	private String getByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet cvset = new CVSet();
		cvset.setId(id);
		list = localCVSetManage.getCVSetList(cvset);
		
		JSONObject result = new JSONObject();		
		result.put("list", list);
		StrutsUtil.renderText(response, result.toString());

		return null;
	}

	/*private String add_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String handle = RequestUtil.getParameter(request, "handle");
		if (handle.equals("first")){
			request.setAttribute("handle", "first")
		}else if(handle.equals("second"))
		return "add_page";
	}*/

	private String updateState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Integer state = ParamUtils.getIntParameter(request, "state", 0);
		//long[] opinion_types = ParamUtils.getLongParameters(request, "opinion_types", 0L);
		String opinionType = request.getParameter("opinionType");
		String opinion = ParamUtils.getParameter(request, "opinion", "");
		
		CVSet cvSet = new CVSet();
		cvSet.setId(id);
		List<CVSet> list = localCVSetManage.getCVSetList(cvSet);
		cvSet = list.get(0);
		cvSet.setOpinionType(opinionType);
		cvSet.setStatus(state);
		cvSet.setOpinion(opinion);
		
		localCVSetManage.updateCVSet(cvSet);
		
		StrutsUtil.renderText(response, "success");
		
		return null;
	}

	private String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long del_id = ParamUtils.getLongParameter(request, "id", 0L);	
		CVSet del_CVS = new CVSet();
		del_CVS.setId(del_id);
		boolean flag = localCVSetManage.deleteCVSet(del_CVS);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}

	private String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cvschedule_scheduleId = request.getParameter("cvscheduleIds"); 
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		CVS_Form cvs_form = (CVS_Form)form;
		CVSet cvset = new CVSet();	
		cvset.setId(id);
		cvset.setName(cvs_form.getName());
		cvset.setForma(cvs_form.getForma());
		
		if(!StringUtils.checkNull(cvschedule_scheduleId)){		
			CVSchedule cvsche = new CVSchedule();
			String[] cvscheduleIds =  request.getParameter("cvscheduleIds").split(",");
			List<CVSchedule> cvschedule = new ArrayList<CVSchedule>();
			for(String id_:cvscheduleIds){
				CVSchedule cvsc = new CVSchedule();
				cvsc.setSchedule_id(Long.parseLong(id_.trim()));
				cvschedule.add(cvsc);
			}
			cvset.setCVScheduleList(cvschedule);
		}
		//cvset.setUserImageList(userImageList);
		String[] userImages = cvs_form.getUserImage().split(",");
		List<UserImage> userImageList = new ArrayList<UserImage>();
		if(userImages != null && userImages.length > 0){
			for(String item:userImages){
				UserImage userImage = new UserImage();
				userImage.setId(Long.parseLong(item.trim()));
				userImageList.add(userImage);
			}
			cvset.setUserImageList(userImageList);
		}else{}
		
		
		
		//////
		cvset.setCode(cvs_form.getCode());
		//cvset.setManagerList
		String[] experts = cvs_form.getManager().split(",");
		List<ExpertInfo> mangerList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			mangerList.add(expertInfo);
		}
		cvset.setManagerList(mangerList);
		
		//cvset.setOtherTeacherList
		experts = cvs_form.getGeneralTeacher().split(",");
		List<ExpertInfo> otherTeacherList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			otherTeacherList.add(expertInfo);
		}
		cvset.setOtherTeacherList(otherTeacherList);
		//LessonTeacher
		experts = cvs_form.getLessonTeacher().split(",");
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		for(String expert:experts){
			ExpertInfo expertInfo = new ExpertInfo();
			expertInfo.setId(Long.parseLong(expert.trim()));
			teacherList.add(expertInfo);
		} 
		cvset.setTeacherList(teacherList);
		
		cvset.setIntroduction(cvs_form.getIntroduction());
		
		cvset.setAnnouncement(cvs_form.getAnnouncement());
		cvset.setKnowledge_needed(cvs_form.getKnowledge_needed());
		cvset.setKnowledge_base(cvs_form.getKnowledge_base());
		cvset.setReference(cvs_form.getReference());
		cvset.setReference_lately(cvs_form.getReference_lately());
		cvset.setMaker(request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString());
		
		String str_cvIds = ParamUtils.getParameter(request, "cvIds");
		String[] cvIds = str_cvIds.split(",");
		
		List<Long> cvIdsList = new ArrayList<Long>();
		for (String cvId:cvIds)
			cvIdsList.add(Long.parseLong(cvId.trim()));
		
		List<CVSchedule> scheduleList = new ArrayList<CVSchedule>();
		for (Long cvIdList:cvIdsList){
			CVSchedule schedule = new CVSchedule();
			schedule.setId(cvIdList);
			scheduleList.add(schedule);
		}
		cvset.setCVScheduleList(scheduleList);
		Long id_ = localCVSetManage.addCVSet(cvset);		
		if(id_ > 0){
			
			FormFile file = cvs_form.getCover();
			if (file!= null && !file.getFileName().equals(""))
			{
				cvset.setFile_path(FilesUtils.fileUpload(file, request, Constants.UPLOAD_FILE_PATH_CVS, id_, "", ""));
			}
		
			response.sendRedirect("CVSetManage.do");
		}
		else return null;
		return null;
	}

	private String get_CVS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		List<CVSet> View = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();	
		queryCVSet.setId(id);
		
		
		View = localCVSetManage.getCVSetList(queryCVSet);
		for (CVSet cvSet:View) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}
		request.setAttribute("View", View);
		
		List<UserImage> imageList = View.get(0).getUserImageList();
		String userImageNames = "";
		String userImageIds = "";
		if(imageList !=null && imageList.size() > 0 ){ 
			for (UserImage image:imageList) {
				userImageNames += image.getName() + ",";
				userImageIds += image.getId() + ",";
			}
		}else{}
		List<ExpertInfo> managerlist = View.get(0).getManagerList();
		String manager = "";
		String manager_id = "";
		for(ExpertInfo expertInfo: managerlist){
			manager += expertInfo.getName() + ",";
			manager_id += expertInfo.getId() + ",";
		}
		
		List<ExpertInfo> teacherlist = View.get(0).getTeacherList();
		String teacher = "";
		String teacher_id = "";
		for(ExpertInfo expertInfo: teacherlist){
			teacher += expertInfo.getName() + ",";
			teacher_id += expertInfo.getId() + ",";
		}
		
		List<ExpertInfo> otherTeacherlist = View.get(0).getOtherTeacherList();
		String otherTeacher = "";
		String otherTeacher_id = "";
		for(ExpertInfo expertInfo: otherTeacherlist){
			otherTeacher += expertInfo.getName() + ",";
			otherTeacher_id += expertInfo.getId() + ",";
		}
		
		List<CVSchedule> cvScheduleList = View.get(0).getCVScheduleList();
		String cvschedule = "";
		String cvschedule_id = "";
		String cvschedule_scheduleId = "";
		for(CVSchedule cvshe: cvScheduleList){
			cvschedule += cvshe.getName() + ",";
			cvschedule_id += cvshe.getId() + ",";
			cvschedule_scheduleId += cvshe.getSchedule_id() + ",";
		}
		
		List<PeixunOrg> orgList = new ArrayList<PeixunOrg>();
		for(CVSet cvset:View){		
			orgList = cvset.getOrganizationList();
			//orgList.add(cvset.getOrganizationList().get(0));
		}
		
		request.setAttribute("orgList", orgList);
		request.setAttribute("userImageNames", userImageNames);
		request.setAttribute("userImageIds", userImageIds);
		request.setAttribute("manager", manager);
		request.setAttribute("manager_id", manager_id);
		request.setAttribute("teacher", teacher);
		request.setAttribute("teacher_id", teacher_id);
		request.setAttribute("otherTeacher", otherTeacher);
		request.setAttribute("otherTeacher_id", otherTeacher_id);
		request.setAttribute("cvschedule", cvschedule);
		request.setAttribute("cvschedule_id", cvschedule_id);
		request.setAttribute("cvschedule_scheduleId", cvschedule_scheduleId);
		
		request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + id);
		return "edit_page";
	}
	
	private String getHanyuPinyinString(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String hanyuStr = ParamUtils.getParameter(request, "hanyuStr");
		String pinyinStr = "";
		
		for (char c:hanyuStr.toCharArray()) {
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
			if (pinyinArray != null) 	pinyinStr += pinyinArray[0].substring(0, 1).toUpperCase();
			else						pinyinStr += c;
		}
		
		StrutsUtil.renderText(response, pinyinStr);
		
		return null;
	}

}
