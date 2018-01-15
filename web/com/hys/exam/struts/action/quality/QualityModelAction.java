package com.hys.exam.struts.action.quality;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class QualityModelAction extends BaseAction {

	private QualityModelManage localQualityModelManage;
	
	public QualityModelManage getLocalQualityModelManage() {
		return localQualityModelManage;
	}

	public void setLocalQualityModelManage(QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	private ExamPropValFacade localExamPropValFacade;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mode = RequestUtil.getParameter(request, "mode");
		
		if(mode.equals("delete")){
			return del(mapping, form, request, response);
		}else if(mode.equals("edit")){
			return edit(mapping, form, request, response);
		}else if(mode.equals("add")){
			return add(mapping, form, request, response);
		}else if(mode.equals("total")){
			return total(mapping, form, request, response);
		}
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String pageNum = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		String captionName = request.getParameter("captionName");
		
		
		QualityModel qualityModel = new QualityModel();
		List<QualityModel> list = new ArrayList<QualityModel>();
		
		if (!StringUtil.checkNull(sname) && !pageNum.equals("3")) {	
			ModelType modelType = new ModelType();
			modelType.setName(sname);
			qualityModel.setType(modelType);
		}
		
		//取得类型列型列表
		ExamProp jobclass = new ExamProp();
		jobclass.setType(24);				
		List<ExamProp> jobclassList = localExamPropValFacade.getPropListByType(jobclass);


		
		if(pageNum.equals("1") && id==0L){					
			list = localQualityModelManage.getQualityModelList(qualityModel);
		} else if(pageNum.equals("2")){
			
			ModelType modelType = new ModelType();
			modelType.setId(id);
			qualityModel.setType(modelType);
			qualityModel.setName(sname);
			list = localQualityModelManage.getNextQualityModelList(qualityModel);		
			
		}else if(pageNum.equals("3")){
			Long parentID = ParamUtils.getLongParameter(request, "typeId", 0L);			
			qualityModel.setParentId(parentID);
			qualityModel.setId(id);
			qualityModel.setName(sname);
			list = localQualityModelManage.getNextQualityModelList(qualityModel);
			Long parentId = qualityModel.getId();
			List<PropUnit> zutilist = new ArrayList<PropUnit>();
			zutilist = localQualityModelManage.getZutiListByType();
			request.setAttribute("parentId", parentId);
			request.setAttribute("zutilist", zutilist);
			
		}else{}
		request.setAttribute("captionName", captionName);
		request.setAttribute("QM_list", list);
		request.setAttribute("sname", sname);
		request.setAttribute("typeID", id);
		return "list"+pageNum;
	}

	private String total(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		QualityModel qualityModel = new QualityModel();
		qualityModel.setId(id);
		List<QualityModel> list = new ArrayList<QualityModel>(); 
		list = localQualityModelManage.getQualityModelList(qualityModel);
		
		JSONObject result = new JSONObject();
		result.put("item", list.get(0));
		StrutsUtil.renderText(response, result.toString()); 
		
		return null;
	}

	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		String add_name = request.getParameter("name");	
		
		String param_zuti_ids = request.getParameter("zutiIds");
		List<PropUnit> subjectPropList = new ArrayList<PropUnit>();
		if(!StringUtil.checkNull(param_zuti_ids)){
			String[] str_zuti_ids = param_zuti_ids.split(",");
		
			for (int i=0; i<str_zuti_ids.length; i++) {
				PropUnit prop = new PropUnit();
				if (!StringUtils.checkNull(str_zuti_ids[i].trim())) {
					prop.setId(Long.parseLong(str_zuti_ids[i].trim()));
				}
				subjectPropList.add(prop);
			}
	}
		
		
		QualityModel qualityModel = new QualityModel();	

		qualityModel.setName(add_name);
		qualityModel.setId(id);
		qualityModel.setParentId(parentId);
		qualityModel.setSubjectPropList(subjectPropList);
	
	if(param_zuti_ids == ""){
		boolean flag = localQualityModelManage.addQualityModel(qualityModel);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
	}
	else{
		boolean flag = localQualityModelManage.addQualityModel(qualityModel);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
	}
		return null;
	}

	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String update_name = request.getParameter("name");
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		String param_zuti_ids = request.getParameter("zutiIds");
		
		
		
		QualityModel qualityModel = new QualityModel();
		
		qualityModel.setId(id);
		qualityModel.setName(update_name);
		qualityModel.setParentId(parentId);
		
		
		if(StringUtil.checkNull(param_zuti_ids)){
			boolean flag = localQualityModelManage.updateQualityModel(qualityModel);
			if(flag == true){
				StrutsUtil.renderText(response, "success");
			}else{
				StrutsUtil.renderText(response, "error");
			}
		}else{	
		
			String[] str_zuti_ids = param_zuti_ids.split(",");
			List<PropUnit> subjectPropList = new ArrayList<PropUnit>();
			for (int i=0; i<str_zuti_ids.length; i++) {
				PropUnit prop = new PropUnit();
				if (!StringUtils.checkNull(str_zuti_ids[i].trim())) {
					prop.setId(Long.parseLong(str_zuti_ids[i].trim()));
				}
				subjectPropList.add(prop);
			}
			qualityModel.setSubjectPropList(subjectPropList);
			
			boolean flag = localQualityModelManage.updateQualityModel(qualityModel);
			if(flag == true){
				StrutsUtil.renderText(response, "success");
			}else{
				StrutsUtil.renderText(response, "error");
			}
		}
		return null;
		
	}

	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		QualityModel qualityModel = new QualityModel();
		qualityModel.setId(id);
		
		boolean flag = localQualityModelManage.deleteQualityModel(qualityModel);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
}
