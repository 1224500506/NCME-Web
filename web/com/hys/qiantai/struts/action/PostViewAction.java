package com.hys.qiantai.struts.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.Sentence;
import com.hys.exam.service.local.ContentIssueManage;
import com.hys.exam.service.local.SentenceManage;
import com.hys.framework.web.action.BaseAction;

public class PostViewAction extends BaseAction {
	
		
	private ContentIssueManage localContentIssueManage;
	
	private SentenceManage localSentenceManage;
	
	public SentenceManage getLocalSentenceManage() {
		return localSentenceManage;
	}

	public void setLocalSentenceManage(SentenceManage localSentenceManage) {
		this.localSentenceManage = localSentenceManage;
	}
	public ContentIssueManage getLocalContentIssueManage() {
		return localContentIssueManage;
	}
	public void setLocalContentIssueManage(
			ContentIssueManage localContentIssueManage) {
		this.localContentIssueManage = localContentIssueManage;
	}
	
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Integer mode = ParamUtils.getIntParameter(request, "mode", 1);
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		String title = "";
		String content = "";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String deli_date = "";
		String source = "";
		if(mode == 1)
		{
            Sentence sentence = localSentenceManage.getSentenceById(id);
			title = sentence.getTitle();
			content = sentence.getContent();
			//如果为null，不能转换格式，否则报错
			if (sentence.getDeli_date() != null) {
				deli_date = format.format(sentence.getDeli_date());
			}
			source = sentence.getSource();
			mode=1;
		}
		else
		{
			Sentence sentence = localSentenceManage.getSentenceById(id);
			title = sentence.getTitle();
			content = sentence.getContent();
			title = sentence.getTitle();
			content = sentence.getContent();
			//如果为null，不能转换格式，否则报错
			if (sentence.getDeli_date() != null) {
				deli_date = format.format(sentence.getDeli_date());
			}
			source = sentence.getSource();
			mode=2;
		}
		request.setAttribute("mode",mode);
		request.setAttribute("id",id);
		request.setAttribute("title",title);
		request.setAttribute("content",content);
		request.setAttribute("deli_date", deli_date);
		request.setAttribute("source", source);
		request.setAttribute("mode", mode);
		return Constants.SUCCESS;
	}
	
	public List<Sentence> getSentenceList(HttpServletRequest request)
	{
		Sentence sentence = new Sentence();
		sentence.setType(1);
 		List<Sentence> list=localSentenceManage.getSentenceList(sentence);
 		List<Sentence> resultList;
		if(list.size() >= 10)
		{
			resultList = list.subList(0, 10);
		}
		else
		{
			resultList = list;
		}
 		return resultList;
	}
	
}
