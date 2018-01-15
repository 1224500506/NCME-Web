package com.hys.exam.struts.action.quality;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.model.UserImage;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.service.local.StudyGuideManage;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class StudyGuideAction extends BaseAction {
	
	private StudyGuideManage localStudyGuideManage;
	
	private QualityModelManage localQualityModelManage;
	
	
	public StudyGuideManage getLocalStudyGuideManage() {
		return localStudyGuideManage;
	}

	public void setLocalStudyGuideManage(StudyGuideManage localStudyGuideManage) {
		this.localStudyGuideManage = localStudyGuideManage;
	}
	
	public QualityModelManage getLocalQualityModelManage() {
		return localQualityModelManage;
	}

	public void setLocalQualityModelManage(
			QualityModelManage localQualityModelManage) {
		this.localQualityModelManage = localQualityModelManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		String handle = ParamUtils.getParameter(request, "handle");
		if (handle.equals("add")) {			
			return addActionExecute(request, response);						
		
		} else if (handle.equals("delete")) {
			return deleteActionExecute(request, response);
						
		} else if (handle.equals("update")) {			
			return updateActionExecute(request, response);
						
		} else if (handle.equals("icdUpdate")) {
			return icdUpdateActionExecute(request, response);
						
		} else if (handle.equals("export")) {
			return exportActionExecute(request, response);
						
		} else {}
		
		switch (pageNum.charAt(0)) {
		case 1 :
		{
			PropUnit prop = new PropUnit();
			ModelType type = new ModelType();
			UserImage image = new UserImage();
			
			String typeName = ParamUtils.getParameter(request, "type");
			if (!StringUtils.checkNull(typeName)) {
				type.setName(typeName);
				image.setType(type);
			}
			String department = ParamUtils.getParameter(request, "department");
			if (!StringUtils.checkNull(department)) {
				prop.setName(department);
				List<PropUnit> departmentPropList = new ArrayList<PropUnit>();
				departmentPropList.add(prop);
				image.setDepartmentPropList(departmentPropList);				
			}
			String userImage = ParamUtils.getParameter(request, "userImage");
			if (!StringUtils.checkNull(userImage)) {
				image.setName(userImage);
			}
			
			queryGuide.setUserImage(image);
			
			List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);			
			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("type", typeName);
			request.setAttribute("department", department);
			request.setAttribute("userImage", userImage);
			break;
		}
		case 2 :
		{				
			Long typeId = ParamUtils.getLongParameter(request, "typeId", 0L);
			String name = ParamUtils.getParameter(request, "name");
			
			QualityModel queryQuality = new QualityModel();
				ModelType modelType = new ModelType();
				modelType.setId(typeId);
			queryQuality.setType(modelType);
			
			if (!name.equals("")) queryQuality.setName(name);
			
			List<QualityModel> list = localQualityModelManage.getNextQualityModelList(queryQuality);

			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("typeId", typeId);
			request.setAttribute("parentName", request.getParameter("parentName"));
			request.setAttribute("name", name);
			break;
		}
		case 3 :
		{
			Long qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
			String name = ParamUtils.getParameter(request, "name");
			
			QualityModel queryQuality = new QualityModel();
			queryQuality.setId(qualityId);
			if (!name.equals("")) queryQuality.setName(name);
			
			List<QualityModel> list = localQualityModelManage.getNextQualityModelList(queryQuality);
			
			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("typeId", ParamUtils.getParameter(request, "typeId"));
			request.setAttribute("qualityId", ParamUtils.getParameter(request, "qualityId"));	
			request.setAttribute("parentName", request.getParameter("parentName"));
			request.setAttribute("name", name);
			break;
		}
		case 4 :
		{
			Long qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
			Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
			String search_ctr = ParamUtils.getParameter(request, "search_ctr");
			String name = ParamUtils.getParameter(request, "name");			
			String icd_name = ParamUtils.getParameter(request, "icd_name");
			
			
			queryGuide.setParent_id(parentId);
			
			QualityModel quality = new QualityModel();
			quality.setId(qualityId);
			queryGuide.setQuality(quality);
			if (!name.equals("")) queryGuide.setName(name);
			
			List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);			
			
			List<PropUnit> icdPropList = new ArrayList<PropUnit>();
			for (StudyGuide guide:list) {
				if (guide.getIcdPropList() != null) { 					
					for (PropUnit prop:guide.getIcdPropList()) {
						
						if (search_ctr.equals("icd"))
							if (!StringUtils.contains(prop.getName().toLowerCase(), icd_name.toLowerCase()))
								continue;
						boolean flag = true;
						for (PropUnit icdProp:icdPropList) {
							if (prop.getId() == icdProp.getId()) {								
								flag = false;
								break;
							}
						}
						if (flag) icdPropList.add(prop);
					}
				}
			}
			
			List<StudyGuide> new_list = new ArrayList<StudyGuide>();
			for (PropUnit prop:icdPropList) {
				for (StudyGuide guide:list) {
					if (guide.getIcdPropList() == null) continue;
					int i = -1;
					for (i=0; i<guide.getIcdPropList().size(); i++) 
						if (guide.getIcdPropList().get(i).getId() == prop.getId()) break;
					if (i == guide.getIcdPropList().size()) continue;
					
					StudyGuide t_guide = new StudyGuide(guide);					
					List<PropUnit> propList = new ArrayList<PropUnit>();
					propList.add(prop);
					t_guide.setIcdPropList(propList);
					new_list.add(t_guide);
				}
			}
			
			
			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("typeId", ParamUtils.getParameter(request, "typeId"));
			request.setAttribute("qualityId", ParamUtils.getParameter(request, "qualityId"));
			request.setAttribute("new_list", new_list);
			request.setAttribute("new_list_totalSize", new_list.size());
			request.setAttribute("parentName", request.getParameter("parentName"));
			request.setAttribute("search_ctr", search_ctr);
			request.setAttribute("name", name);
			request.setAttribute("icd_name", icd_name);
			break;
		}
		case 5 :
		{
			Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
			String search_ctr = ParamUtils.getParameter(request, "search_ctr");
			String name = ParamUtils.getParameter(request, "name");			
			String icd_name = ParamUtils.getParameter(request, "icd_name");
			
			queryGuide.setId(parentId);			
			
			List<StudyGuide> parentlist = localStudyGuideManage.getStudyGuideList(queryGuide);
			
			queryGuide.setParent_id(parentId);
			if (!name.equals("")) queryGuide.setName(name);
			
			List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);			
			
			List<PropUnit> icdPropList = new ArrayList<PropUnit>();
			for (StudyGuide guide:list) {
				if (guide.getIcdPropList() != null) { 
					for (PropUnit prop:guide.getIcdPropList()) {
						if (search_ctr.equals("icd"))
							if (!StringUtils.contains(prop.getName().toLowerCase(), icd_name.toLowerCase()))
								continue;
						boolean flag = true;
						for (PropUnit icdProp:icdPropList) {
							if (prop.getId() == icdProp.getId()) {
								flag = false;
								break;
							}
						}
						if (flag) icdPropList.add(prop);
					}
				}
			}
			
			List<StudyGuide> new_list = new ArrayList<StudyGuide>();
			for (PropUnit prop:icdPropList) {
				for (StudyGuide guide:list) {
					if (guide.getIcdPropList() == null) continue;
					int i = -1;
					for (i=0; i<guide.getIcdPropList().size(); i++) 
						if (guide.getIcdPropList().get(i).getId() == prop.getId()) break;
					if (i == guide.getIcdPropList().size()) continue;
					
					StudyGuide t_guide = new StudyGuide(guide);					
					List<PropUnit> propList = new ArrayList<PropUnit>();
					propList.add(prop);
					t_guide.setIcdPropList(propList);
					new_list.add(t_guide);
				}
			}
			
			request.setAttribute("list", list);
			request.setAttribute("totalSize", list.size());
			request.setAttribute("icdPropList", parentlist.get(0).getIcdPropList());
			request.setAttribute("new_list", new_list);
			request.setAttribute("new_list_totalSize", new_list.size());
			request.setAttribute("parentId", ParamUtils.getParameter(request, "parentId"));
			request.setAttribute("parentName", request.getParameter("parentName"));
			request.setAttribute("search_ctr", search_ctr);
			request.setAttribute("name", name);
			request.setAttribute("icd_name", icd_name);
			break;
		}
		default:
			break;
		}
		return "list" + pageNum;
	}
	
	private String addActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		Long uiId = ParamUtils.getLongParameter(request, "uiId", 0);
		UserImage userImage = new UserImage();
		userImage.setId(uiId);
		
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		queryGuide.setUserImage(userImage);
		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0L);
		Long qualityId = ParamUtils.getLongParameter(request, "qualityId", 0L);
		String name = ParamUtils.getParameter(request, "name");
		Long level = ParamUtils.getLongParameter(request, "pageNum", 1L);
				
		queryGuide.setName(name);
		queryGuide.setLevel(level);
		queryGuide.setParent_id(parentId);
		
		QualityModel quality = new QualityModel();
		quality.setId(qualityId);
		queryGuide.setQuality(quality);
		
		localStudyGuideManage.addStudyGuide(queryGuide);
		
		StrutsUtil.renderText(response, "success");
		
		return null;
	}
	
	private String deleteActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		queryGuide.setId(id);
		if (localStudyGuideManage.deleteStudyGuide(queryGuide))
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		
		return null;
	}
	
	private String updateActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		queryGuide.setId(id);
		String name = ParamUtils.getParameter(request, "name");
		
		if (!StringUtils.checkNull(name)) queryGuide.setName(name);
		
		if (localStudyGuideManage.updateStudyGuide(queryGuide))
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		
		return null;
	}
	
	private String icdUpdateActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		
		int ctr = -1;
		String method = ParamUtils.getParameter(request, "method");
		if (method.equals("add")) 			ctr = 1;
		else if (method.equals("delete"))	ctr = 0;
		else {}
		
		String word = ParamUtils.getParameter(request, "word");
		String[] str_ids = word.split("_");
		Long guideId = Long.parseLong(str_ids[0].trim());
		Long propId = Long.parseLong(str_ids[1].trim());
		
		if (localStudyGuideManage.updateStudyGuideICDs(guideId, propId, ctr))
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, word);	
		
		return null;
	}
	
	private String exportActionExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=\"card.xls\"");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		String pageNum = ParamUtils.getParameter(request, "pageNum", "1");
		
		StudyGuide queryGuide = new StudyGuide();
		queryGuide.setLevel(Long.parseLong(pageNum));
		List<StudyGuide> list = localStudyGuideManage.getStudyGuideList(queryGuide);
		
		if(!Utils.isListEmpty(list)){
			createSheet(wwb, list);
		}
		
		wwb.write();
		wwb.close();
		os.close();
		response.flushBuffer();
		
		return null;
	}
	
	private void createSheet(WritableWorkbook wwb,List<StudyGuide> list) throws WriteException, RowsExceededException {
		
		String name = "人物";
		
		WritableSheet wsheet = wwb.createSheet("人物画像",wwb.getSheets().length);
		WritableFont wfc = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD);
		
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);  
		wcfFC.setAlignment(Alignment.CENTRE);
		
		
		Label label = new Label(0, 0, "序号", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(1, 0, name + "类型", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(2, 0, name + "科室", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(3, 0, name + "画像",wcfFC);
		wsheet.addCell(label);

		
		if(!Utils.isListEmpty(list)){
			for (int j = 0; j < list.size(); j++) {
				StudyGuide val = list.get(j);
				wfc = new WritableFont(WritableFont.createFont("宋体"),10);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(0, j+1, String.valueOf(val.getId()),wcfFC);
				wsheet.addCell(label);

				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(1, j+1, String.valueOf(val.getUserImage().getType().getName() + "人物类型"),wcfFC);
				wsheet.addCell(label);				
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);				
				String str_departmentList = "";
				for (int i=0; i<val.getUserImage().getDepartmentPropList().size(); i++) {
					str_departmentList += val.getUserImage().getDepartmentPropList().get(i).getName();
				}				
				label = new Label(2, j+1, str_departmentList, wcfFC);
				wsheet.addCell(label);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(3, j+1, val.getUserImage().getName(), wcfFC);
				wsheet.addCell(label);

			}
		}
		
	}
}
