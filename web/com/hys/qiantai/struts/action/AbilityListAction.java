package com.hys.qiantai.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.framework.web.action.BaseAction;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.StringUtils;

public class AbilityListAction extends BaseAction {
	private QualityModelManage localQualityModelManage;
	private ExamPropValFacade localExamPropValFacade;
	
	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = ParamUtils.getParameter(request, "mode", "");
		SystemUser user = LogicUtil.getSystemUser(request);
		if (user == null || user.getUserId() == 0){
			return "LOGIN";
		}
		
		if (!StringUtils.checkNull(mode) && mode.equals("level2project")) { //大圈下1级关联的项目
			String id = ParamUtils.getParameter(request, "id", "");
			if (!StringUtils.checkNull(id)) {
				QualityModel qualityModel = new QualityModel() ;
				qualityModel.setId(Long.parseLong(id)) ;
				qualityModel.setParentId(user.getUserId()) ;
				String propStrSql = localQualityModelManage.getXunKeSql(Long.parseLong(user.getProp_Id()));
				if(propStrSql!=null){
					qualityModel.setName(propStrSql);
				}
				List<CVSet> abilityLevelTwoProjectList = localQualityModelManage.getAbilityLevelTwoProjectList(qualityModel) ;
				JSONObject result = new JSONObject();
				result.put("list", abilityLevelTwoProjectList);	
				result.put("size", 0) ;
				if (abilityLevelTwoProjectList != null && abilityLevelTwoProjectList.size() > 0) {
					result.put("size", abilityLevelTwoProjectList.size()) ;
				} 
		        StrutsUtil.renderText(response, result.toString());
		        return null;
			} else {
				JSONObject result = new JSONObject();
				result.put("size", 0) ;
				StrutsUtil.renderText(response, result.toString());
				return null;
			}
		}
		
		/*
		String region = null , hospitalLevel = null ;		
		if (user.getUserConfig() != null) {
			if (user.getUserConfig().getUserCountiesId() != null && !user.getUserConfig().getUserCountiesId().equals(0L)) region = "区县级" ;
			if (user.getUserConfig().getUserCityId()     != null && !user.getUserConfig().getUserCountiesId().equals(0L)) region = "地市级" ;
			if (user.getUserConfig().getUserProvinceId() != null && !user.getUserConfig().getUserCountiesId().equals(0L)) region = "省市级" ;
		}		
		if(user.getUserConfig() != null && user.getUserConfig().getUserCountiesId() != null && !user.getUserConfig().getUserCountiesId().equals(0L)) {
			if (user.getWork_unit_id() != null && user.getWork_unit_id() != 0) {
				ExamHospital host = new ExamHospital();				
                host.setPropId(user.getUserConfig().getUserCountiesId());				
                List<ExamHospital> list2 = localExamPropValFacade.getHospitalListAll(host);
			}
		}
		*/
				
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
		
		return "SUCCESS";
	}
	
	public QualityModelManage getLocalQualityModelManage() {
		return this.localQualityModelManage;
	}

	public void setLocalQualityModelManage(QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return this.localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

}
