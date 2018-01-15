package com.hys.qiantai.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.StringUtils;

import com.hys.framework.web.action.BaseAction;


public class MyAbilityAction extends BaseAction {
	private QualityModelManage localQualityModelManage;
	private ExamPropValFacade localExamPropValFacade;		
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user == null) {
			return "fail";
		}
		
		if (!StringUtils.checkNull(user.getJob_Id())) {
			QualityModel qualityModel = new QualityModel() ;
			qualityModel.setId(Long.parseLong(user.getJob_Id())) ;
			qualityModel.setParentId(user.getUserId()) ;
			
			List<QualityModel> abilityLevelOneList = localQualityModelManage.getAbilityLevelOneList(qualityModel) ;
			for (QualityModel qmObj : abilityLevelOneList) {
				QualityModel qmEntity = new QualityModel() ;
				qmEntity.setId(qmObj.getId()) ;
				List<PropUnit> abilityLevelTwoList = localQualityModelManage.getAbilityLevelTwoList(qmEntity) ;
				qmObj.setSubjectPropList(abilityLevelTwoList) ;
			}
			request.setAttribute("abilityLevelOneList", abilityLevelOneList);
		}
		
		//Xueke levels
		Long propId = 0L;
		if(user.getProp_Id() != null) {
			propId = Long.valueOf(user.getProp_Id());
		}
		Long xueke2Id = localExamPropValFacade.getParentPropId(propId);
		Long xueke1Id = localExamPropValFacade.getParentPropId(xueke2Id);
		
		//Xueke 1 level List 
		ExamProp query0  = new ExamProp();
		query0.setType(1);
		List<ExamProp> xueke1 = localExamPropValFacade.getPropListByType(query0);
		
		//Xueke 2 level List
		ExamPropQuery query1  = new ExamPropQuery();
		query1.setSysPropId(xueke1Id);
		ExamReturnProp propOne = localExamPropValFacade.getNextLevelProp(query1);
		List<ExamProp> xueke2 = propOne.getReturnList();
		
		//Xueke 3 level List
		ExamPropQuery query2  = new ExamPropQuery();
		query2.setSysPropId(xueke2Id);
		ExamReturnProp propTwo = localExamPropValFacade.getNextLevelProp(query2);
		List<ExamProp> xueke3 = propTwo.getReturnList();
		
		String xueKeName = null ;
		for (ExamProp epObj : xueke3) {
			if (epObj != null && epObj.getId().equals(propId)) {
				xueKeName = epObj.getName() ;
				break;
			}
		}
		
		request.setAttribute("myYiyuan", user.getWorkUnit());//医院
		request.setAttribute("myZhicheng", user.getProfTitle());//职称
		request.setAttribute("myXueKe", xueKeName);//学科名称只取第三级		
		request.setAttribute("userInfo", user);
		return "list";
	}	
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public QualityModelManage getLocalQualityModelManage() {
		return this.localQualityModelManage;
	}

	public void setLocalQualityModelManage(QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}
}
