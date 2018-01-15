package com.hys.exam.struts.action.content;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.exam.service.local.EditionManage;
import com.hys.exam.struts.form.CE_Form;
import com.hys.framework.web.action.BaseAction;

public class EditionAction extends BaseAction {

	private EditionManage localEditionManage;
	
	public EditionManage getLocalEditionManage() {
		return localEditionManage;
	}

	public void setLocalEditionManage(EditionManage localEditionManage) {
		this.localEditionManage = localEditionManage;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// TODO Auto-generated method stub
		
		String mode = RequestUtil.getParameter(request, "mode");
		if (mode.equals("setorder")){
			return setorder(mapping, form, request, response);
		} else if(mode.equals("list")) {
			return list(mapping, form, request, response);			
		} else if(mode.equals("view")) {
			return view(mapping, form, request, response);
		} else if(mode.equals("edit")) {
			return edit(mapping, form, request, response);
		} else {
			return list(mapping, form, request, response);
		}
	}
	
	protected String setorder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String orderstr = ParamUtils.getParameter(request, "orderstr", "");
		
		boolean flag = localEditionManage.resortOrderNum(orderstr);
		if (flag)
			StrutsUtil.renderText(response, "success");
		else
			StrutsUtil.renderText(response, "fail");
		return null;
	}
	
	private String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form; 
		
		Edition edition = new Edition();
		
		List<Edition> list = new ArrayList<Edition>();
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		if(ceForm != null) {
			edition.setName(ceForm.getBookName());
			edition.setTitle(ceForm.getKindName());
			edition.setKind(ceForm.getTakeKind());
		}
		
		edition.setType(0);
		
		list = localEditionManage.getEditionList(edition);
		
		request.setAttribute("Edition", list);
		request.setAttribute("bName", edition.getName());
		request.setAttribute("kName", edition.getTitle());
		request.setAttribute("tKind", edition.getKind());
		
		return "success";
	}
	
	private String view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form;
		
		Edition edition = new Edition();
		
		List<Edition> list = new ArrayList<Edition>();
		List<CVSet> cvList = new ArrayList<CVSet>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		if(ceForm != null) {
			CVSet cvSet = new CVSet();
			//cvSet.setName(ceForm.getGroupIds());  //need only Course Name by change Lee
			cvSet.setDeli_man(ceForm.getCreateMan());
			cvSet.setName(ceForm.getEditionName());
			cvSet.setStatus(ceForm.getStatus());
			
			edition.setCvSet(cvSet);
		}
		edition.setId(id);
		edition.setType(1);
		
		list = localEditionManage.getEditionList(edition);
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getCvSetList().size() == 0)
				continue;
			cvList.add(list.get(i).getCvSetList().get(0));
		}
		request.setAttribute("list", cvList);
		if(ceForm != null) {
			//request.setAttribute("groupNames01", arg1);
			request.setAttribute("createMan", ceForm.getCreateMan());
			request.setAttribute("editionName", ceForm.getEditionName());
			request.setAttribute("status", ceForm.getStatus());
		}
		request.setAttribute("id", id);
		return "view";
	}
	private String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CE_Form ceForm = (CE_Form)form;
		
		Edition edition = new Edition();
		
		List<Edition> list = new ArrayList<Edition>();
		List<CVSet> cvList = new ArrayList<CVSet>();
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		if(ceForm != null) {
			CVSet cvSet = new CVSet();
			//cvSet.setName(ceForm.getGroupIds());  //need only Course Name by change Lee
			cvSet.setDeli_man(ceForm.getCreateMan());
			cvSet.setName(ceForm.getEditionName());
			cvSet.setStatus(ceForm.getStatus());
			
			edition.setCvSet(cvSet);
		}
		edition.setId(id);
		edition.setType(1);
		
		list = localEditionManage.getEditionList(edition);
		
		for(int i=0; i<list.size(); i++) {		
			if(list.get(i).getCvSetList().size() == 0)
				continue;
			cvList.add(list.get(i).getCvSetList().get(0));
		}
		request.setAttribute("list", cvList);
		if(ceForm != null) {
			//request.setAttribute("groupNames01", arg1);
			request.setAttribute("createMan", ceForm.getCreateMan());
			request.setAttribute("editionName", ceForm.getEditionName());
			request.setAttribute("status", ceForm.getStatus());
		}
		request.setAttribute("id", id);
		
		return "edit";
	}

}
