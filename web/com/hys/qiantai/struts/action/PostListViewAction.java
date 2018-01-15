package com.hys.qiantai.struts.action;

import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ContentIssue;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.Sentence;
import com.hys.exam.service.local.ContentIssueManage;
import com.hys.exam.service.local.SentenceManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.struts.form.IssueForm;
import com.hys.exam.struts.form.SentenceForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.VerificationCodeUtil;
import com.hys.framework.web.action.BaseAction;

public class PostListViewAction extends BaseAction {
	
		
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
		 Sentence sentence = new Sentence();
	        //设置分类
	        sentence.setType(mode);
	        //设置状态 已发布
	        sentence.setState(2);
	        //资讯
		if(mode == 1)
		{
	        sentence.setServerName(request.getServerName());

	        PageList<Sentence> pl = new PageList<Sentence>();

	        int currentPage = PageUtil.getPageIndex(request);

	        pl.setPageNumber(currentPage);
//	        pl.setSortCriterion("i.startdate");
	        pl.setSortDirection(SortOrderEnum.DESCENDING);

	        localSentenceManage.querySentencePageList(pl, sentence);
			request.setAttribute("mode", mode);
			request.setAttribute("list",pl);
		}
		else
		{
	 		List<Sentence> list=localSentenceManage.getSentenceList(sentence);
	 		request.setAttribute("mode", mode);
	 		request.setAttribute("list", list);
		}
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
