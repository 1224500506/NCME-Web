package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.DateUtils;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.LogStudyCvSet;
import com.hys.qiantai.model.MyStudyInfo;
import com.hys.qiantai.model.StudyRecordInfo;

public class StudyRecordManageAction extends BaseAction {

	CVSetEntityManage localCVSetEntity;
	private ExamPropValFacade localExamPropValFacade;
	
	CVSetManage localCVSetManage;
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
	public CVSetEntityManage getLocalCVSetEntity() {
		return localCVSetEntity;
	}

	public void setLocalCVSetEntity(CVSetEntityManage localCVSetEntity) {
		this.localCVSetEntity = localCVSetEntity;
	}
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		SystemUser user = LogicUtil.getSystemUser(request);		
		if(user == null)
		{
			return "fail";
		}
		
		LogStudyCvSet info  = new LogStudyCvSet();
		List<MyStudyInfo> list = new ArrayList<MyStudyInfo>();
		int year = ParamUtils.getIntParameter(request, "year", 0);
		int month = ParamUtils.getIntParameter(request, "month", 0);
		
		// 没选择年，不用月 2017-01-12 han
		if (year == 0)
			month = 0;
		info.setsYear(year);
		info.setsMonth(month);
		info.setSystem_user_id(user.getUserId());
		
		PageList<LogStudyCvSet> page = new PageList<LogStudyCvSet>();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		
		page.setObjectsPerPage(pageSize);
		page.setPageNumber(currentPage);
		
		localCVSetEntity.getLogCVSetListFromUser(info, page);

/*		//Get My Job
		ExamProp myJobClass = new ExamProp();
		myJobClass.setType(9);
		List<ExamProp> myJobClassList = localExamPropValFacade.getPropListByType(myJobClass);
		
		Integer workExtType = 0;
		for(ExamProp examProp: myJobClassList) {
			String userJobId = user.getJob_Id();
			if(userJobId != null)
			{
				String eId = String.valueOf(examProp.getId());
				if(!userJobId.equals(eId)) {
					continue;
				}
				workExtType = examProp.getExt_type();
				myJobClass.setExt_type(workExtType);
				myJobClassList = localExamPropValFacade.getPropListByType(myJobClass);
				request.setAttribute("myJobList", myJobClassList);
			}
								
		}
	
		
		//Hospital
		ExamHospital host = new ExamHospital();
		if(user.getWork_unit_id() != null)
		{
			String userWorkID = String.valueOf(user.getWork_unit_id());
			host.setId(Long.valueOf(userWorkID));
			List<ExamHospital> list2 = localExamPropValFacade.getHospitalList(host);
			request.setAttribute("hospital", list2);
		}*/
		
		request.setAttribute("userInfo", user);
		request.setAttribute("pageStudyRecord", page);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("data", list);
		
		
		return "success";
	}
}