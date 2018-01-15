package com.hys.exam.struts.action.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.service.local.SentenceManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.struts.form.SentenceForm;
import com.hys.framework.web.action.BaseAction;

public class SentenceAction extends BaseAction {

	private SystemSiteManage localSystemSiteManage;
	
	private SentenceManage localSentenceManage;
	
	public SentenceManage getLocalSentenceManage() {
		return localSentenceManage;
	}

	public void setLocalSentenceManage(SentenceManage localSentenceManage) {
		this.localSentenceManage = localSentenceManage;
	}

	public SystemSiteManage getLocalSystemSiteManage() {
		return localSystemSiteManage;
	}

	public void setLocalSystemSiteManage(SystemSiteManage localSystemSiteManage) {
		this.localSystemSiteManage = localSystemSiteManage;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = ParamUtils.getParameter(request, "method");
		
		if(method.equals("list")){
			return list(mapping, form, request, response);
		}else if(method.equals("add")){
			return  add(mapping, form, request, response);
		}else if(method.equals("search")){
			return  list(mapping, form, request, response);
		}else if(method.equals("save")){
			return  save(mapping, form, request, response);
		}else if(method.equals("updateMenu")){
			return  updateMenu(mapping, form, request, response);
		}else if(method.equals("delete")){
			return  delete(mapping, form, request, response);
		}else if(method.equals("update")){
			return  updateSentence(mapping, form, request, response);
		}else if(method.equals("updateState")){
			return  updateState(mapping, form, request, response);
		}else{
			return list(mapping, form, request, response);
		}
	}
	 
	private String updateMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 
/*			Sentence sentence = new Sentence();
			String id = request.getParameter("id");
			if(id != null && !id.equals(""))
			{
				sentence.setId(Long.parseLong(id));
			}
			else
			{
				StrutsUtil.renderText(response, "fail");
				return null;
			}
			sentence.setType(Integer.parseInt(request.getParameter("type")));
			sentence.setTitle(request.getParameter("title"));
			 
			String state = request.getParameter("state");
			if(state != null && !state.equals(""))
			{
				menu.setState(Integer.valueOf(state));	
			}		
			Boolean result = localSentenceManage.updateMenu(menu);
			if(result)
			{
				StrutsUtil.renderText(response, "success");
			}
			else
			{
				StrutsUtil.renderText(response, "fail");
			}
*/			return null;
		 
	 
	}

	private String updateSentence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		Sentence sentence = new Sentence();
		sentence.setId(id);
		List<Sentence> list=localSentenceManage.getSentenceList(sentence);
				
		request.setAttribute("title", list.get(0).getTitle());
		/*request.setAttribute("id", sentence.getId());
		request.setAttribute("title", sentence.getTitle());
		request.setAttribute("type", sentence.getType());
		request.setAttribute("content", sentence.getContent());
		*/
		/*List<Sentence> list=localSentenceManage.getSentenceList(sentence);
		request.setAttribute("list", list);*/
		return "add";
	}

	private String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		
		SentenceForm  aform = (SentenceForm)form;
		Sentence sentence = aform.getModel();
 		List<Sentence> list=localSentenceManage.getSentenceList(sentence);
 		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
 		request.setAttribute("list", list);
		return "success";
	}

	private String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	{
		
		request.setAttribute("siteList", localSystemSiteManage.getSystemSiteList());
		return "add";
	}
	
	private String save(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)	{
		
		SentenceForm  aform = (SentenceForm)form;
		Sentence sentence = aform.getModel();
		
		long[] site_array = ParamUtils.getLongParameters(request, "site", -1L);
		
		List<SystemSite> systemSiteList = new ArrayList<SystemSite>();
		
		if(site_array != null) {
			for(int i=0; i<site_array.length; i++) {
				SystemSite systemSite = new SystemSite();
				systemSite.setId(site_array[i]);
				systemSiteList.add(systemSite);			
			}
			
			sentence.setSiteList(systemSiteList);
		}
		sentence.setState(1);
		
		if(localSentenceManage.addSentence(sentence)) {
			Sentence stc = new Sentence();
			List<Sentence> list=localSentenceManage.getSentenceList(stc);

	 		request.setAttribute("list", list);
		}
		
		try {
			response.sendRedirect("sentenceManage.do?method=list");
		} catch (IOException e) {
				e.printStackTrace();
		}	
		return null;
	}

	private String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	{
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		if (id > 0)
			localSentenceManage.deleteSentenceById(id);
		StrutsUtil.renderText(response, "success");
		return null;
	}

	private String updateState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	{
		
		Long id = ParamUtils.getLongParameter(request, "id", -1L);
		int state = Integer.parseInt(ParamUtils.getParameter(request, "state", "-1").toString());

		if (id > 0 && state > 0)
			localSentenceManage.updateState(id, state);
		StrutsUtil.renderText(response, "success");
	
		return null;
	}
}
