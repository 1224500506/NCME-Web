package com.hys.exam.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.SystemUserFaceteach;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

public class FaceEntryAction extends BaseAction{
	
    private CVSetManage localCVSetManage;
    
	private ExamPropValFacade localExamPropValFacade;
	//保存报名学习Service接口
	private LogStudyCVUnitManage localLogStudyCVUnitManage;
	 
	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
    
	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}

	public void setLocalLogStudyCVUnitManage(LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    SystemUser user = LogicUtil.getSystemUser(request);
	    if (user == null) {
            return "fail";
        }
	    String method = RequestUtil.getParameter(request, "method");
	    if (method.equals("faceEntry")) {
			return getFaceEntry(request,response,user);
		}
	    if (method.equals("printFaceEntry")) {
			return getPrintFaceEntry(request,response,user);
		}else {
			return getPrintFaceEntry(request,response,user);
		}

	}

	private String getPrintFaceEntry(HttpServletRequest request, HttpServletResponse response, SystemUser user) {
		String faceId = request.getParameter("fid");
        //查找cvSetId 
        CVSetScheduleFaceTeach face = localCVSetManage.getFace(Integer.valueOf(faceId));

        //查找报名表数据
        Long cvSetId =face.getCv_set_id().longValue();
        CVSet cvSet = localCVSetManage.findCVSetById(cvSetId);

        //查学科
		Long propId = 0L;
		if(user.getProp_Id() != null)
		{
			propId = Long.valueOf(user.getProp_Id());
		}
		ExamProp exam1 = localExamPropValFacade.getSysPropVal(propId);//三级学科
		Long xueke2Id = localExamPropValFacade.getParentPropId(propId);
		ExamProp exam2 = localExamPropValFacade.getSysPropVal(xueke2Id);//二级学科
		Long xueke1Id = localExamPropValFacade.getParentPropId(xueke2Id);
		ExamProp exam3 = localExamPropValFacade.getSysPropVal(xueke1Id);//一级学科
        request.setAttribute("faceteach", face);
        request.setAttribute("user", user);
        request.setAttribute("cvSet", cvSet);
        request.setAttribute("exam1", exam1);
		request.setAttribute("exam2", exam2);
		request.setAttribute("exam3", exam3);
		return "printFaceEntry";
	}

	private String getFaceEntry(HttpServletRequest request, HttpServletResponse response, SystemUser user) {
		String faceId = request.getParameter("id");
        //查找cvSetId 
        CVSetScheduleFaceTeach face = localCVSetManage.getFace(Integer.valueOf(faceId));
        //保存报名表
        SystemUserFaceteach faceteach = new SystemUserFaceteach();
        faceteach.setFid(Long.valueOf(faceId));
        faceteach.setUser_id(user.getId());
        faceteach.setEntry_time(new Date());
        localCVSetManage.saveFaceTeach(faceteach);
        
        //查找报名表数据
        Long cvSetId =face.getCv_set_id().longValue();
        CVSet cvSet = localCVSetManage.findCVSetById(cvSetId);
        //保存到我的学习
        LogStudyCVSet logStudy = new LogStudyCVSet();
        logStudy.setSystemUserId(user.getId());
        logStudy.setCvSetId(cvSetId);
        localLogStudyCVUnitManage.saveLogStudy(logStudy);
        //查学科
		Long propId = 0L;
		if(user.getProp_Id() != null)
		{
			propId = Long.valueOf(user.getProp_Id());
		}
		ExamProp exam1 = localExamPropValFacade.getSysPropVal(propId);//三级学科
		Long xueke2Id = localExamPropValFacade.getParentPropId(propId);
		ExamProp exam2 = localExamPropValFacade.getSysPropVal(xueke2Id);//二级学科
		Long xueke1Id = localExamPropValFacade.getParentPropId(xueke2Id);
		ExamProp exam3 = localExamPropValFacade.getSysPropVal(xueke1Id);//一级学科
        request.setAttribute("faceteach", face);
        request.setAttribute("user", user);
        request.setAttribute("cvSet", cvSet);
        request.setAttribute("exam1", exam1);
		request.setAttribute("exam2", exam2);
		request.setAttribute("exam3", exam3);
		return "faceEntry";
	}

}
