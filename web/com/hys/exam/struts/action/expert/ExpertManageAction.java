package com.hys.exam.struts.action.expert;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExpertInfoForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 专家管理
 * @author Han
 *
 */
public class ExpertManageAction extends BaseAction {
	
	private ExpertManageManage localExpertManageManage;
	
	private ExpertGroupManage localExpertGroupManage;
	
	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	public ExpertGroupManage getLocalExpertGroupManage() {
		return localExpertGroupManage;
	}

	public void setLocalExpertGroupManage(ExpertGroupManage localExpertGroupManage) {
		this.localExpertGroupManage = localExpertGroupManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = RequestUtil.getParameter(request, "mode");
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		else if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("detail")){
			return detail(mapping, form, request, response);
		}
		else if (method.equals("getsub")){
			return getsub(mapping, form, request, response);
		}else if(method.equals("search")){
			return search(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}
		
	}
	
	private String search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expertInfo = new ExpertInfo();
		
		//设置查询条件
		expertInfo.setName(eform.getName());
		expertInfo.setGroupId(eform.getGroupId());
		expertInfo.setSubGroupId(eform.getSubGroupId());
		expertInfo.setOffice(eform.getOffice());
		expertInfo.setJob(eform.getJob());
		expertInfo.setTerm(eform.getTerm());
		expertInfo.setWorkUnit(eform.getWorkUnit());
		expertInfo.setLockState(eform.getLockState());
		expertInfo.setPropIds(eform.getPropIds());
		expertInfo.setPropNames(eform.getPropNames());
		
		expertInfo.setGroupIds(eform.getGroupIds());
		expertInfo.setGroupNames(eform.getGroupNames());

		//取得专家目录
		List<ExpertInfo> list =  localExpertManageManage.getExpertList(expertInfo);
		
		for (ExpertInfo e: list){
			try{
			
			//取得学科
			ExpertInfo old = localExpertManageManage.getExpertInfo(e.getId());
			e.setProp(old.getProp());
			
			e.setGroup(old.getGroup());
			
	
			//取得职称
			if(e.getJob() != 0){
				ExamProp prop = localExamPropValFacade.getSysPropVal(e.getJob());
				e.setJobName(prop.getName());
			}
			}catch(Exception ex){;}
		}
		
		//取得查询条件的内容
		ExpertGroup group = new ExpertGroup();
		group.setParent(0L);
		List<ExpertGroup> grouplist = localExpertGroupManage.getExpertGroupList(group);
		
		ExamProp prop = new ExamProp();
		prop.setType(9);
		List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);

		group.setParent(expertInfo.getGroupId());
		List<ExpertGroup> sublist = localExpertGroupManage.getExpertGroupList(group);
		
		group.setParent(0L);
		group.setId(expertInfo.getGroupId());
		List<ExpertGroupTerm> termlist = localExpertGroupManage.getTermList(group);

		
		request.setAttribute("sublist", sublist);
		request.setAttribute("termlist", termlist);
		request.setAttribute("proplist", proplist);
		request.setAttribute("grouplist", grouplist);
		request.setAttribute("info", expertInfo);
		//request.setAttribute("extList", list);
		JSONObject result = new JSONObject();
		result.put("name", list);
		StrutsUtil.renderText(response, result.toString());
		return null;
	}

	/**
	 * 显示添加专家页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));
		ExpertInfo expertInfo = new ExpertInfo();
		
		//取得委员会目录
		ExpertGroup group = new ExpertGroup();
		group.setParent(0L);
		group.setLockState(1);
		List<ExpertGroup> grouplist = localExpertGroupManage.getExpertGroupList(group);
		
		//取得职称目录
		ExamProp prop = new ExamProp();
		prop.setType(9);
		List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);

		List<ExpertGroup> sublist = new ArrayList<ExpertGroup>();
		List<ExpertGroupTerm> termlist = new ArrayList<ExpertGroupTerm>();
		
		request.setAttribute("sublist", sublist);
		request.setAttribute("termlist", termlist);
		request.setAttribute("proplist", proplist);
		request.setAttribute("grouplist", grouplist);
		request.setAttribute("info", expertInfo);
		request.setAttribute("groupId", groupId);
		return "edit";
	}

	/**
	 * 显示修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long groupId = Long.valueOf(request.getParameter("gid") == null ? "0" : request.getParameter("gid"));
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expertInfo = new ExpertInfo();
		
		//取得专家信息
		expertInfo = localExpertManageManage.getExpertInfo(eform.getId());
	
		//取得委员会目录
		ExpertGroup group = new ExpertGroup();
		group.setParent(0L);
		group.setLockState(1);
		List<ExpertGroup> grouplist = localExpertGroupManage.getExpertGroupList(group);
		
		ExamProp prop = new ExamProp();
		prop.setType(9);
		List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);

		group.setParent(expertInfo.getGroupId());
		
		//取得所属委员会的所有学组目录
		List<ExpertGroup> sublist = localExpertGroupManage.getExpertGroupList(group);
		
		//取得所属委员会的所有届期目录
		group.setParent(0L);
		group.setId(expertInfo.getGroupId());
		List<ExpertGroupTerm> termlist = localExpertGroupManage.getTermList(group);

		request.setAttribute("sublist", sublist);
		request.setAttribute("termlist", termlist);
		request.setAttribute("proplist", proplist);
		request.setAttribute("grouplist", grouplist);
		request.setAttribute("info", expertInfo);
		request.setAttribute("groupId", groupId);
		return "edit";
	}

	/**
	 * 显示查看页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expertInfo = new ExpertInfo();
		
		expertInfo = localExpertManageManage.getExpertInfo(eform.getId());
		
/*		//取得委员会名称
		ExpertGroup group = localExpertGroupManage.getExpertGroup(expertInfo.getGroupId());
		if (group != null) expertInfo.setGroupName(group.getName());
		
		//取得学组名称
		group = localExpertGroupManage.getExpertGroup(expertInfo.getSubGroupId());
		if (group != null) expertInfo.setSubGroupName(group.getName());
		
		//取得届期
		ExpertGroupTerm term = new ExpertGroupTerm();
		term.setId(expertInfo.getTerm());
		term = localExpertGroupManage.getExpertGroupTerm(term);
		
		if (term != null) expertInfo.setTermStr(term.getStartDateStr() + " - " + term.getEndDateStr());
*/		
		//取得职称
		if (expertInfo.getJob() != 0){
			ExamProp prop = localExamPropValFacade.getSysPropVal(expertInfo.getJob());
			expertInfo.setJobName(prop.getName());
		}
		request.setAttribute("info", expertInfo);
		return "detail";
	}

	/**
	 * 保存专家信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gid = request.getParameter("gid");
		String sid = request.getParameter("sid");
		Long groupId = null;
		Long studyId = null;
		if(gid != null && !gid.equals(""))
			 groupId = Long.valueOf( gid);
		if(sid != null && !sid.equals(""))
			studyId = Long.valueOf(sid);
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expertInfo = new ExpertInfo();
		
		//设置专家信息
		if (eform.getId() != null && eform.getId() != 0 )
			expertInfo.setId(eform.getId());
		expertInfo.setBank(eform.getBank());
		expertInfo.setBankCard(eform.getBankCard());
		expertInfo.setBreakDate(DateUtil.parse(eform.getBreakDate(), "yyyy-MM-dd"));
		expertInfo.setClerkName(eform.getClerkName());
		expertInfo.setClerkPhone(eform.getClerkPhone());
		expertInfo.setCode(eform.getCode());
		expertInfo.setEmail(eform.getEmail());
//		expertInfo.setGroupId(eform.getGroupId());
		expertInfo.setIdentityNum(eform.getIdentityNum());
		expertInfo.setJob(eform.getJob());
		expertInfo.setLockState(eform.getLockState());
		expertInfo.setName(eform.getName());
		expertInfo.setOffice(eform.getOffice());
		expertInfo.setPhone1(eform.getPhone1());
		expertInfo.setPhone2(eform.getPhone2());
		expertInfo.setState(eform.getState());
		expertInfo.setSubGroupId(eform.getSubGroupId());
//		expertInfo.setTerm(eform.getTerm());
		expertInfo.setUserName(eform.getUserName());
		expertInfo.setWorkUnit(eform.getWorkUnit());
		expertInfo.setSummary(eform.getSummary());
		expertInfo.setIsNation(eform.getIsNation());
		expertInfo.setPropIds(eform.getPropIds());
		expertInfo.setGroupIds(eform.getGroupIds());

		//取得专家照片
		FormFile file = eform.getPhoto();
		if (!file.getFileName().equals("")) 
			expertInfo.setPhoto(file.getFileName());
		
		try{
		if (expertInfo.getId() == null)
			localExpertManageManage.addExpertInfo(expertInfo);
		else
			localExpertManageManage.updateExpertInfo(expertInfo);
		}catch(Exception e){
			if(groupId != null && studyId !=null)
			{
				request.setAttribute("gid", expertInfo.getGroupId());
				request.setAttribute("sid", expertInfo.getSubGroupId());
				response.sendRedirect("ExpertSubGroupMember.do?error=1&gid="+groupId.toString()+"&sid="+studyId.toString());
			}
			else
			{
				response.sendRedirect("ExpertManage.do?error=1");	
			}
			
			return null;
		}
		
		//保存照片文件
		if (!file.getFileName().equals("")) {
			String logoRealPathDir = request.getSession().getServletContext().getRealPath(Constants.UPLOAD_FILE_PATH_EXPERT);
			String fileName = logoRealPathDir + "/" + expertInfo.getId();
	        try {     
	        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
	            byte[] buffer = file.getFileData();   
	            outer.write(buffer);   
	            outer.close();   
	            file.destroy();      
	        } catch (Exception e) {     
	            e.printStackTrace();     
	        }
		}
		if(groupId != null && studyId !=null)
		{
			response.sendRedirect("ExpertSubGroupMember.do?gid="+groupId.toString()+"&sid="+studyId.toString());
		}
		else
		{
			response.sendRedirect("ExpertManage.do");
		}
		return null;
	}

	/**
	 * 删除专家信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expertInfo = new ExpertInfo();
		
		expertInfo.setId(eform.getId());
		
		boolean flag = localExpertManageManage.deleteExpertInfo(expertInfo);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}

	/**
	 * 取得被选委员会的学组和届期
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String getsub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long parentId = Long.valueOf(RequestUtil.getParameter(request, "gid"));
		ExpertGroup group = new ExpertGroup();
		group.setParent(parentId);
		group.setLockState(1);		
		
		//取得学组目录
		List<ExpertGroup> sublist = localExpertGroupManage.getExpertGroupList(group);
		
		//取得届期目录
		group.setParent(0L);
		group.setId(parentId);
		List<ExpertGroupTerm> termlist = localExpertGroupManage.getTermList(group);
		
		JSONObject result = new JSONObject();
		result.put("sublist", sublist);
		result.put("termlist", termlist);
		String resStr = result.toString();
		
		//以json形式输出
		StrutsUtil.renderText(response, resStr);
		return null;
	}

	/**
	 * 取得符合查询条件的专家目录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertInfoForm eform = (ExpertInfoForm)form;
		ExpertInfo expertInfo = new ExpertInfo();
		//设置查询条件
		expertInfo.setName(eform.getName());
		expertInfo.setGroupId(eform.getGroupId());
		expertInfo.setSubGroupId(eform.getSubGroupId());
		expertInfo.setOffice(eform.getOffice());
		expertInfo.setJob(eform.getJob());
		expertInfo.setTerm(eform.getTerm());
		expertInfo.setWorkUnit(eform.getWorkUnit());
		expertInfo.setLockState(eform.getLockState());
		expertInfo.setPropIds(eform.getPropIds());
		expertInfo.setPropNames(eform.getPropNames());
		expertInfo.setGroupIds(eform.getGroupIds());
		expertInfo.setGroupNames(eform.getGroupNames());
		//取得专家目录
		List<ExpertInfo> list =  localExpertManageManage.getExpertList(expertInfo);
		for (ExpertInfo e: list){
			try{
			//取得学科
			ExpertInfo old = localExpertManageManage.getExpertInfo(e.getId());
			e.setProp(old.getProp());
			e.setGroup(old.getGroup());
/*			//取得所属委员会
			if (e.getGroupId()!=0){
				ExpertGroup group = localExpertGroupManage.getExpertGroup(e.getGroupId());
				e.setGroupName(group.getName());
			}
			
			//取得学组
			ExpertGroup group = localExpertGroupManage.getExpertGroup(e.getSubGroupId());
			if (null !=group)
			e.setSubGroupName(group.getName());
			
			//取得届期
			ExpertGroupTerm term = new ExpertGroupTerm();
			term.setId(e.getTerm());
			term = localExpertGroupManage.getExpertGroupTerm(term);
			if (term!=null)
				e.setTermStr(term.getStartDateStr() + " - " + term.getEndDateStr());
*/			
			//取得职称
			if(e.getJob() != 0){
				ExamProp prop = localExamPropValFacade.getSysPropVal(e.getJob());
				e.setJobName(prop.getName());
			}
			}catch(Exception ex){;}
		}
		//取得查询条件的内容
		ExpertGroup group = new ExpertGroup();
		group.setParent(0L);
		List<ExpertGroup> grouplist = localExpertGroupManage.getExpertGroupList(group);
		ExamProp prop = new ExamProp();
		prop.setType(9);
		List<ExamProp> proplist = localExamPropValFacade.getPropListByType(prop);
		group.setParent(expertInfo.getGroupId());
		List<ExpertGroup> sublist = localExpertGroupManage.getExpertGroupList(group);
		group.setParent(0L);
		group.setId(expertInfo.getGroupId());
		List<ExpertGroupTerm> termlist = localExpertGroupManage.getTermList(group);		
		request.setAttribute("sublist", sublist);
		request.setAttribute("termlist", termlist);
		request.setAttribute("proplist", proplist);
		request.setAttribute("grouplist", grouplist);
		request.setAttribute("info", expertInfo);
		request.setAttribute("extList", list);
		return "list";
	}

}
