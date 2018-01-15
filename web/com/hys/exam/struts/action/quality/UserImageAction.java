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
import com.hys.exam.model.GeneralUserImage;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SpecialUserImage;
import com.hys.exam.model.UserImage;

import com.hys.exam.service.local.UserImageManage;


import com.hys.framework.web.action.BaseAction;

public class UserImageAction extends BaseAction{

	private UserImageManage localUserImageManage;
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long dutyId =ParamUtils.getLongParameter(request, "duty", -1L);
		
		Long pageId = ParamUtils.getLongParameter(request, "pageId",-1L) ;
		String returnStr = "";
		String method=RequestUtil.getParameter(request, "mode");
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		String sname = request.getParameter("sname");
		String propIds = request.getParameter("propIds"); 
		
		String departmentName = request.getParameter("propNames");
		
		if(method.equals("delete")){
			return del(mapping,form,request,response);
		}else if(method.equals("update")){
			return update(mapping,form,request,response);
		}else if(method.equals("generalImageAdd")){
			return getGeneralUserImageInfoByAjax(mapping,form,request,response);
		}else if(method.equals("userImageDuty")){
			return getUserImageDuty(mapping,form,request,response);
		}else if(method.equals("add")){
			return addUserImage(mapping,form,request,response);
		}if(method.equals("special")){
			return getSpecialUserImage(mapping,form,request,response);
		}else if(method.equals("uhaha")){
			return uhaha(mapping,form,request,response);
		}else if(method.equals("guide")){
			return getUserTypeByAjax(mapping,form,request,response);
		}else if(method.equals("guide_userImage")){
			return getUserImageByAjax(mapping,form,request,response);
		}else if(method.equals("xiangmu")){
			return xiangmu(mapping,form,request,response);
		}

		UserImage userImage = new UserImage();
		
		if(!StringUtil.checkNull(sname)) {
			ModelType modelType = new ModelType();
			modelType.setName(sname);
			userImage.setType(modelType);
		}
		
	
		if(id != -1 && pageId == -1)
		{
			pageId = id;
		}
		if(pageId != -1){
			id = pageId;
		}
		if (id != -1) {
			ModelType modelType = new ModelType();
			modelType.setId(id);
			
			//modelType.setName(sname);
			userImage.setName(sname);
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			if(!StringUtil.checkNull(propIds)){
				String[] IDS = propIds.split(",");
				Long[] departmentIDS = new Long[IDS.length];
				for(int i =0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
				String[] departmentNames = departmentName.split(",");
				PropUnit[] tempProp = new PropUnit[IDS.length];
				for(int i=0;i<IDS.length;i++)
				{
					tempProp[i] = new PropUnit();
					tempProp[i].setId(departmentIDS[i]);
					tempProp[i].setName(departmentNames[i]);
					tempList.add(tempProp[i]);
				}
				userImage.setDepartmentPropList(tempList);
			}
			if(dutyId != -1){
				GeneralUserImage  generalUserImage = new GeneralUserImage();
				SpecialUserImage specialUserImage = new SpecialUserImage();
				List<PropUnit> dutyPropList = new ArrayList<PropUnit>();
				PropUnit temp = new PropUnit();
				temp.setId(dutyId);
				dutyPropList.add(temp);
				generalUserImage.setDutyPropList(dutyPropList);
				specialUserImage.setDutyPropList(dutyPropList);
				userImage.setGeneralUserImage(generalUserImage);
				userImage.setSpecialUserImage(specialUserImage);
			}
			userImage.setType(modelType);
			returnStr = "list2";
		}
		
		
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		List<PropUnit> dutyList = localUserImageManage.getDutyList();
		request.setAttribute("dutyList", dutyList);
		request.setAttribute("list", list);
		request.setAttribute("sname",sname);
		request.setAttribute("pageId", pageId);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", request.getParameter("propNames"));
		request.setAttribute("propNames03", request.getParameter("propNames02"));
		request.setAttribute("dutyId", dutyId);
		request.setAttribute("id", id);
		
		if (returnStr.equals("")) returnStr = "list1";
		return returnStr;
	}


	private String xiangmu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = Long.valueOf(request.getParameter("id"));
		UserImage userImage = new UserImage();
		userImage.setId(id);
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result = new JSONObject();
		result.put("result",list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getUserImageByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = Long.valueOf(request.getParameter("id"));
		UserImage userImage = new UserImage();
		ModelType modelType = new ModelType();
		modelType.setId(id);
		userImage.setType(modelType);
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result = new JSONObject();
		result.put("result",list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getUserTypeByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserImage userImage = new UserImage();
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result = new JSONObject();
		result.put("userLeixing", list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getSpecialUserImage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<PropUnit> majorList = localUserImageManage.getMajorList();
		List<PropUnit> dutyList = localUserImageManage.getDutyList();
		JSONObject result = new JSONObject();
		result.put("major", majorList);
		result.put("duty", dutyList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getUserImageDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<PropUnit> dutyList = localUserImageManage.getDutyList();
		JSONObject result = new JSONObject();
		result.put("dutyList", dutyList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String getGeneralUserImageInfoByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<PropUnit>  HospitalList = localUserImageManage.getHospitalList();
		List<PropUnit> areaList = localUserImageManage.getAreaList();
		JSONObject result = new JSONObject();
		result.put("hospital", HospitalList);
		result.put("area", areaList);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}


	private String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long typeId = ParamUtils.getLongParameter(request, "typeId", -1L);
		
		Long id =ParamUtils.getLongParameter(request,"id",0L);
		String name = request.getParameter("name");
		
		String PropIds = request.getParameter("propIds");
		String areaIds = request.getParameter("areaIds");
		String hospitalIds = request.getParameter("hospitalIds");
		String dutyIds = request.getParameter("dutyIds");
		String majorIds = request.getParameter("majorIds");
		String dutyIds_spec = request.getParameter("dutyIds_special");
		UserImage userImage = new UserImage();
		GeneralUserImage generalUserImage = new GeneralUserImage();
		SpecialUserImage specialUserImage = new SpecialUserImage();
		
		userImage.setId(id);
		userImage.setName(name);
		
		if (typeId > -1L) {
			ModelType modelType = new ModelType();
			modelType.setId(typeId);
			userImage.setType(modelType);
		}
		
		if(!StringUtil.checkNull(PropIds)){
			String[] IDS = PropIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] departmentIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(departmentIDS[i]);
				tempList.add(tempProp[i]);
			}
			userImage.setDepartmentPropList(tempList);
		}
		
		if(!StringUtil.checkNull(areaIds)){
			String[] IDS = areaIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] areaIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) areaIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(areaIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setAreaPropList(tempList);
		}
		
		if(!StringUtil.checkNull(hospitalIds)){
			String[] IDS = hospitalIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] hospitalIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) hospitalIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(hospitalIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setHospitalPropList(tempList);
		}
		
		if(!StringUtil.checkNull(dutyIds)){
			String[] IDS = dutyIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] dutyIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setDutyPropList(tempList);
		}
		
		userImage.setGeneralUserImage(generalUserImage);
		
		if(!StringUtil.checkNull(majorIds)){
			String[] IDS = majorIds.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] majorIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) majorIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(majorIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setMajorPropList(tempList);
		}
		
		if(!StringUtil.checkNull(dutyIds_spec)){
			String[] IDS = dutyIds_spec.split(",");
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			Long[] dutyIds_specIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIds_specIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIds_specIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setDutyPropList(tempList);
		}
		
		userImage.setSpecialUserImage(specialUserImage);
		
		boolean flag = localUserImageManage.updateUserImage(userImage);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

	private String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		UserImage userImage = new UserImage();
		userImage.setId(id);
		
		boolean flag = localUserImageManage.deleteUserImage(userImage);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	public String addUserImage(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response){
	
		Long typeId = ParamUtils.getLongParameter(request, "typeId", -1L);
		String name = request.getParameter("name");
		
		String PropIds = request.getParameter("propIds");
		
		String areaIds = request.getParameter("areaIds");
		String hospitalIds = request.getParameter("hospitalIds");
		String dutyIds = request.getParameter("dutyIds");
		String majorIds = request.getParameter("majorIds");
		String dutyIds_spec = request.getParameter("dutyIds_special");
		UserImage userImage = new UserImage();
		
		userImage.setName(name);
		
		if (typeId > -1L) {
			ModelType modelType = new ModelType();
			modelType.setId(typeId);
			userImage.setType(modelType);
		}
		
		if(!StringUtil.checkNull(PropIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = PropIds.split(",");
			Long[] departmentIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) departmentIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(departmentIDS[i]);
				tempList.add(tempProp[i]);
			}
			userImage.setDepartmentPropList(tempList);
		}
		
		GeneralUserImage generalUserImage = new GeneralUserImage();
		SpecialUserImage specialUserImage = new SpecialUserImage();
		
		if(!StringUtil.checkNull(areaIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = areaIds.split(",");
			Long[] areaIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) areaIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(areaIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setAreaPropList(tempList);
		}		
		
		if(!StringUtil.checkNull(hospitalIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = hospitalIds.split(",");
			Long[] hospitalIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) hospitalIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(hospitalIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setHospitalPropList(tempList);
		}
		
		if(!StringUtil.checkNull(dutyIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = dutyIds.split(",");
			Long[] dutyIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIDS[i]);
				tempList.add(tempProp[i]);
			}
			generalUserImage.setDutyPropList(tempList);
		}
		
		if(!StringUtil.checkNull(majorIds)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = majorIds.split(",");
			Long[] majorIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) majorIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(majorIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setMajorPropList(tempList);
		}
		
		if(!StringUtil.checkNull(dutyIds_spec)){
			List<PropUnit> tempList = new ArrayList<PropUnit>();
			String[] IDS = dutyIds_spec.split(",");
			Long[] dutyIds_specIDS = new Long[IDS.length];
			for(int i=0;i<IDS.length;i++) dutyIds_specIDS[i] = Long.valueOf(IDS[i].trim());
			PropUnit[] tempProp = new PropUnit[IDS.length];
			for(int i=0;i<IDS.length;i++){
				tempProp[i] = new PropUnit();
				tempProp[i].setId(dutyIds_specIDS[i]);
				tempList.add(tempProp[i]);
			}
			specialUserImage.setDutyPropList(tempList);
		}
		
		userImage.setGeneralUserImage(generalUserImage);
		userImage.setSpecialUserImage(specialUserImage);
		
		boolean flag = localUserImageManage.addUserImage(userImage);
		if(flag == true){
			StrutsUtil.renderText(response, "success");
		}else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	private String uhaha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserImage userImage = new UserImage();
		Long typeId  = ParamUtils.getLongParameter(request, "typeId", -1L);
		ModelType modelType = new ModelType();
		modelType.setId(typeId);
		userImage.setType(modelType);
		List<UserImage> list = localUserImageManage.getUserImageList(userImage);
		JSONObject result_list = new JSONObject();
		result_list.put("update_display", list);
		StrutsUtil.renderText(response, result_list.toString());
		return null;
	}

	
	
	public UserImageManage getLocalUserImageManage() {
		return localUserImageManage;
	}

	public void setLocalUserImageManage(UserImageManage localUserImageManage) {
		this.localUserImageManage = localUserImageManage;
	}
}
