package com.hys.exam.struts.action.CVSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PropUnit;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.struts.form.CVForm;
import com.hys.exam.struts.form.CVUnitForm;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CVAction extends BaseAction {

	private CVManage localCVManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CV queryCV = new CV();
		
		String mode = ParamUtils.getParameter(request, "mode", "");
		String propIds = request.getParameter("propIds");
		String propNames = request.getParameter("propNames");
		String sname = request.getParameter("sname");
		String itemName = request.getParameter("item");
		
		if (mode.equals("add")) {
			return add(mapping,form,request,response);
		}else if(mode.equals("edit")){
			return edit(mapping,form,request,response);
		}else if(mode.equals("save")){
			return save(mapping,form,request,response);
		}else if(mode.equals("delete")){
			return delete(mapping,form,request,response);
		}else if(mode.equals("addUnion")){
			return addUnion(mapping,form,request,response);
		}else if(mode.equals("unionEdit")){
			return unionEdit(mapping,form,request,response);
		}else if(mode.equals("updateUnion")){
			return updateUnion(mapping,form,request,response);
		}else if(mode.equals("unitContentEdit")){
			return unitContentEdit(mapping,form,request,response);
		}else if(mode.equals("clone")){
			return courseClone(mapping,form,request,response);
		}else if(mode.equals("cloneCopy")){
			return cloneCopy(mapping,form,request,response);
		}else if(mode.equals("teacher")){
			return teacher(mapping,form,request,response);
		}else if(mode.equals("getCVInfoByAjax")){
			return getCVInfoByAjax(mapping,form,request,response);
		}else if(mode.equals("addUnionUpdate")){
			return addUnionUpdate(mapping,form,request,response);
		}else if(mode.equals("updateSave")){
			return updateSave(mapping,form,request,response);
		} else if (mode.equals("cloneUnitList")) {
			return cloneUnitList(mapping,form,request,response);
		} else if (mode.equals("swapUnit")) {
			return swapUnit(mapping,form,request,response);
		} else if(mode.equals("updatePoint")){
			return updatePoint(mapping,form,request,response);
		}
		
		if(!StringUtils.checkNull(sname))
			queryCV.setCreator(sname);
		
		if(!StringUtil.checkNull(propIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = propIds.split(",");
			Long[] PropIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) PropIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(PropIDS[i]);
				tempList.add(tempProp[i]);
			}
			queryCV.setCourseList(tempList);
		}

		if(!StringUtil.checkNull(itemName))
		{
			queryCV.setName(itemName);
		}

		List<CV> list = new ArrayList<CV>();
		list = localCVManage.getCVList(queryCV);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
		request.setAttribute("list", list);
		request.setAttribute("sname",sname);
		request.setAttribute("item", itemName);
		
		return "list";
	}


	private String updatePoint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Integer point = Integer.valueOf(ParamUtils.getIntParameter(request, "pointMode", 0));
		Integer state = Integer.valueOf(ParamUtils.getIntParameter(request, "stateMode", 0));
		Long id =ParamUtils.getLongParameter(request, "id", 0L);
		
		CVUnit cvUnit = new CVUnit();
		
		cvUnit.setId(id);
		cvUnit.setPoint(point);
		cvUnit.setState(state);
		
		localCVManage.updateCVUnit(cvUnit);
		
		return null;
	}


	private String swapUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id1 = ParamUtils.getLongParameter(request, "src_id", -1L);
		Long id2 = ParamUtils.getLongParameter(request, "target_id", -1L);
		
		if (id1 + id2 == -2) return null;
		
		if (id1 == id2) {
			CVUnit unit = new CVUnit();		
			unit.setId(id1);
			List<CVUnit> list = localCVManage.getCVUnitList(unit);
			localCVManage.deleteUnit(id1);
		} else {
			
			CVUnit unit1 = new CVUnit();
			
			CVUnit temp = new CVUnit();
			
			unit1.setId(id1);
			List<CVUnit> list = localCVManage.getCVUnitList(unit1);
			
			unit1 = list.get(0);
			
			
			CVUnit unit2 = new CVUnit();
			unit2.setId(id2);
			list = localCVManage.getCVUnitList(unit2);
			
			unit2 = list.get(0);
			temp = unit1;
			unit1=unit2;
			unit2=temp;
			unit1.setId(id2);
			unit2.setId(id1);
			
			localCVManage.swapCVUnit(unit1,unit2);
			
		}
		
		StrutsUtil.renderText(response, "success");
		
		return null;
	}


	private String cloneUnitList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		CV queryCV = new CV();
		queryCV.setId(id);
		
		int result = localCVManage.cloneCVUnitList(queryCV);
		
		StrutsUtil.renderText(response, "success");
		
		return null;
	}


	private String updateSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long Id = Long.valueOf(request.getParameter("id"));
		
		String propIds = request.getParameter("propIds");
		String teacher_ids = request.getParameter("teacherIds");
		String other_Teacher_ids = request.getParameter("otherTeacherIds");
		CV queryCV= new CV();
		CVForm simpleForm = (CVForm)form;
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		List<CV> cvList = new ArrayList<CV>();
		CVUnit cvUnit = new CVUnit(); 
		
		
		//Long Id = simpleForm.getId();
		String courseName = simpleForm.getName();
		String serial = simpleForm.getSerial_number();
		String brand = simpleForm.getBrand();
		String introduction = simpleForm.getIntroduction();
		String filePath=simpleForm.getFile_path();
		String announcement = simpleForm.getAnnouncement();
		String creator = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		
		
		
		if(!StringUtil.checkNull(propIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = propIds.split(",");
			Long[] PropIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) PropIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(PropIDS[i]);
				tempList.add(tempProp[i]);
			}
			queryCV.setCourseList(tempList);
		}
		
		if(!StringUtil.checkNull(other_Teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = other_Teacher_ids.split(",");
			Long[] other_TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) other_TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(other_TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setOtherTeacherList(tempList);
		}
		
		if(!StringUtil.checkNull(teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = teacher_ids.split(",");
			Long[] TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setTeacherList(tempList);
		}
		
		String file = simpleForm.getFile_path();
		/*if (file!= null && !file.getFileName().equals(""))
		{
			queryCV.setFile_path(FilesUtils.fileUpload(file, request, Constants.UPLOAD_FILE_PATH_CVS, Id, "", ""));
		}*/
		
		queryCV.setId(Id);
		queryCV.setName(courseName);
		queryCV.setSerial_number(serial);
		queryCV.setIntroduction(introduction);
		queryCV.setBrand(brand);
		queryCV.setAnnouncement(announcement);
		queryCV.setCreator(creator);

		cvUnit.setIsBound(0);
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		queryCV.setUnitList(cvUnitList);
		
		boolean flag=localCVManage.updateCV(queryCV);
		cvList = localCVManage.getCVList(queryCV);
		request.setAttribute("list", cvList);
		String path = request.getContextPath() ;
		response.sendRedirect(path +"/CVSet/CVManage.do?mode=list");
		return null;
	}


	private String addUnionUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long Id = Long.valueOf(request.getParameter("id"));
		
		CVUnitForm cvUnitForm = (CVUnitForm)form;
		CVUnit cvUnit = new CVUnit();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		CV queryCV = new CV();
		String name = cvUnitForm.getName();
		Integer state = cvUnitForm.getState();
		Integer point = cvUnitForm.getPoint();
		Integer type = cvUnitForm.getType();
		queryCV.setId(Id);
		cvUnit.setName(name);
		cvUnit.setState(state);
		cvUnit.setPoint(point);
		cvUnit.setIsBound(0);
		cvUnit.setType(type);
		cvUnitList.add(cvUnit);
		queryCV.setUnitList(cvUnitList);
		
		localCVManage.addUnionUpdate(queryCV);
		String path = request.getContextPath() ;
		response.sendRedirect(path +"/CVSet/CVManage.do?mode=edit&id="+Id);
		
		return null;
	}


	private String teacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
		teacherList = localCVManage.getTeacherList();
		JSONObject json = new JSONObject();
		json.put("result",teacherList);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}


	private String getCVInfoByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CV queryCV = new CV();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		List<CV> list = new ArrayList<CV>();
		
		if (id > 0) {
			queryCV.setId(id);
			list = localCVManage.getCVList(queryCV);
		}
		
		JSONObject jsonObj = new JSONObject();
		
		if (list != null && list.size() > 0)
			jsonObj.put("info", list.get(0));
		
		StrutsUtil.renderText(response, jsonObj.toString());
		
		return null;
	}


	private String cloneCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		CVUnit cvUnit = new CVUnit();
		CV queryCV = new CV();
		List<CVUnit> cvUnitCloneList = new ArrayList<CVUnit>();
		List<CV> cvCloneList = new ArrayList<CV>();
		queryCV.setId(id);
		queryCV.setUnitList(cvUnitCloneList);
		cvCloneList = localCVManage.getCloneCVList(queryCV);
		cvUnitCloneList = localCVManage.getCloneCVUnitList(queryCV);
		
		JSONObject json =new JSONObject();
		json.put("cloneUnit", cvUnitCloneList);
		json.put("cloneCV", cvCloneList);
		json.put("id", id);
		StrutsUtil.renderText(response, json.toString());		
		
		return null;
	}

	private String courseClone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CV queryCV = new CV();
		List<CV> list = new ArrayList<CV>();
		list = localCVManage.getCVList(queryCV);
		request.setAttribute("clone",list);
		return "clone";
	}

	private String unitContentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long Id = ParamUtils.getLongParameter(request, "id", -1L);
		CVUnit cvUnit = new CVUnit();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		cvUnit.setId(Id);
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		JSONObject Json = new JSONObject();
		Json.put("result", cvUnitList);
		StrutsUtil.renderText(response, Json.toString());
		return null;
	}

	private String updateUnion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String tmp_content = request.getParameter("content");
			Long Id  = Long.valueOf(request.getParameter("id"));
			
			String content = tmp_content.substring(3, tmp_content.length()-4);
			List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
			CVUnit cvUnit = new CVUnit();
			cvUnit.setId(Id);
			cvUnitList = localCVManage.getCVUnitList(cvUnit);
			cvUnit = cvUnitList.get(0);			
			cvUnit.setContent(content);
			
			localCVManage.updateUnion(cvUnit);
			
			StrutsUtil.renderText(response, "success");
			
		return null;
	}

	private String unionEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		Long Id = ParamUtils.getLongParameter(request, "id",0L);
		CVUnit cvUnit = new CVUnit();
		
		CV queryCV = new CV();
		if(Id >0 ){
			queryCV.setId(Id);
			cvUnitList = localCVManage.getCVUnitList(queryCV);
		}else{
			cvUnit.setIsBound(0);
			cvUnitList = localCVManage.getCVUnitList(cvUnit);
		}
		
		
		request.setAttribute("list", cvUnitList);
		
		return "unionEdit";
	}

	private String addUnion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("name");
		Integer type =Integer.valueOf(request.getParameter("type"));
		Integer point = Integer.valueOf(request.getParameter("point"));
		
		CVUnit cvUnit = new CVUnit();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		
		
		cvUnit.setName(name);
		
		cvUnit.setPoint(point);
		cvUnit.setIsBound(0);
		cvUnit.setType(type);
		
		boolean flag = localCVManage.addCVUnit(cvUnit);
		/*cvUnitList = localCVManage.getCVUnitList(cvUnit);
		
		request.setAttribute("unionList", cvUnitList);*/
		String path = request.getContextPath() ;
		response.sendRedirect(path +"/CVSet/CVManage.do?mode=add&view=2");
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		
		
		/*cvUnitList.add(cvUnit);
		CV queryCV = new CV();
		queryCV.setUnitList(cvUnitList);
		
		localCVManage.addCV(queryCV);*/
		
		return null;
	}

	private String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		CV queryCV = new CV();
		queryCV.setId(id);
		boolean flag=localCVManage.delete(queryCV);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	private String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String propIds = request.getParameter("propIds");
		String teacher_ids = request.getParameter("teacherIds");
		String other_Teacher_ids = request.getParameter("otherTeacherIds");

		CV queryCV= new CV();
		CVForm simpleForm = (CVForm)form;
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		List<CV> cvList = new ArrayList<CV>();
		CVUnit cvUnit = new CVUnit(); 
		
		
		Long Id = simpleForm.getId();
		String courseName = simpleForm.getName();
		String serial = simpleForm.getSerial_number();
		String brand = simpleForm.getBrand();
		String introduction = simpleForm.getIntroduction();
		String filePath=simpleForm.getFile_path();
		
		String announcement = simpleForm.getAnnouncement();
		String creator = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		
		FormFile formFile;
		
		if(!StringUtil.checkNull(propIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = propIds.split(",");
			Long[] PropIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) PropIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(PropIDS[i]);
				tempList.add(tempProp[i]);
			}
			queryCV.setCourseList(tempList);
		}
		
		if(!StringUtil.checkNull(other_Teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = other_Teacher_ids.split(",");
			Long[] other_TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) other_TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(other_TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setOtherTeacherList(tempList);
		}
		
		if(!StringUtil.checkNull(teacher_ids)){
			List<ExpertInfo> tempList = new ArrayList<ExpertInfo>();
			String[] IDS = teacher_ids.split(",");
			Long[] TeacherIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) TeacherIDS[i] = Long.valueOf(IDS[i].trim());
			ExpertInfo[] tempInfo = new ExpertInfo[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempInfo[i]=new ExpertInfo();
				tempInfo[i].setId(TeacherIDS[i]);
				tempList.add(tempInfo[i]);
			}
			queryCV.setTeacherList(tempList);
		}
		
		
		//queryCV.setId(Id);
		queryCV.setName(courseName);
		queryCV.setSerial_number(serial);
		queryCV.setBrand(brand);
		queryCV.setIntroduction(introduction);
		queryCV.setAnnouncement(announcement);
		queryCV.setCreator(creator);
/*		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		
		request.setAttribute("unionList", cvUnitList);*/
		cvUnit.setIsBound(0);
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		queryCV.setUnitList(cvUnitList);
		
		String file = simpleForm.getFile_path();
		/*if (file!= null && !file.getFileName().equals(""))
		{
			queryCV.setFile_path(FilesUtils.fileUpload(file, request, Constants.UPLOAD_FILE_PATH_CV, Id, "", ""));
		}
		*/
		boolean flag=localCVManage.addCV(queryCV);
		
		cvList = localCVManage.getCVList(queryCV);
		request.setAttribute("list", cvList);
		String path = request.getContextPath() ;
		response.sendRedirect(path +"/CVSet/CVManage.do?mode=list");
		return null;
	}

	private String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id =Long.valueOf(request.getParameter("id"));
		
		CV queryCV = new CV();
		List<CV> cvList = new ArrayList<CV>();
		queryCV.setId(id);
		cvList = localCVManage.getCVList(queryCV);
		
		List<PropUnit> courseList = new ArrayList<PropUnit>();
		String propIds = "";
		String propNames = "";
		if(cvList != null && cvList.size() > 0)
		{
			courseList = cvList.get(0).getCourseList();
			for (PropUnit prop:courseList) {
			propIds += prop.getId() + ",";
			propNames += prop.getName()+ ",";
			}
		}
		String teacherIds ="";
		String teacherNames = "";
		if(cvList != null && cvList.size() > 0){
			List<ExpertInfo> teacherList = cvList.get(0).getTeacherList();
			for(ExpertInfo teacher:teacherList){
				teacherIds += teacher.getId()+",";
				teacherNames += teacher.getName()+",";
			}
		}
		String otherTeacherIds = "";
		String otherTeacherNames ="";
		if(cvList != null && cvList.size() > 0){
			List<ExpertInfo> otherTeacherList = cvList.get(0).getOtherTeacherList();
			for(ExpertInfo other:otherTeacherList){
				otherTeacherIds +=other.getId()+",";
				otherTeacherNames +=other.getName()+",";
			}
		}
		
		List<CVUnit> cvUnitList = cvList.get(0).getUnitList();
		
		
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames",propNames);
		request.setAttribute("teacherIds",teacherIds);
		request.setAttribute("teacher",teacherNames);
		request.setAttribute("otherTeacher", otherTeacherNames);
		request.setAttribute("otherTeacherIds",otherTeacherIds);
		request.setAttribute("unionList",cvUnitList);
		request.setAttribute("info", cvList.get(0));
		request.setAttribute("brand", cvList.get(0).getBrand());
		//request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CV + "\\" + id);
		return "edit";
	}



	private String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
	
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		CVUnit cvUnit = new CVUnit(); 
		cvUnit.setIsBound(0);
		
		cvUnitList = localCVManage.getCVUnitList(cvUnit);
		
		request.setAttribute("unionList", cvUnitList);
		//request.setAttribute("view", ParamUtils.getParameter(request, "view"));
		
		return "add";
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}
	
}