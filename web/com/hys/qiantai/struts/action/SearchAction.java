package com.hys.qiantai.struts.action;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.common.util.DateUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.OrgManage;
import com.hys.exam.struts.form.CaseCaseForm;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.ExpertGroupModel;

/**
 * 
 * 标题：Search
 * 
 * 作者：B.sky 2017-01-25
 * 
 * 描述：
 * 
 * 说明: get Data of XiangMu, MingShi and Expert
 */
public class SearchAction extends BaseAction {
	private static int ALL_TAB = 1; // 全部
	private static int XiangMu_TAB = 2; // 项目
	private static int MingShi_TAB = 3; // 名师
	private static int ORG_TAB = 4; // 机构
	private static int EXPERT_TAB = 5; // 专委会
	
	private CVSetManage localCVSetManage; // 项目
	
	private ExpertManageManage localExpertManageManage; // 名师
	
	private ExpertGroupManage localExpertgroupManage; // 专委会
	
	private OrgManage localOrgManage;//机构
	
	private LogStudyCVSetManage localLogStudyCVSetManage;
	
	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}

	public void setLocalOrgManage(OrgManage localOrgManage) {
		this.localOrgManage = localOrgManage;
	}
	
	public void setLocalLogStudyCVSetManage(LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//当前登录人
			Long userProvinceId=null;
				SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
				if(user!=null){
					if(user.getUserConfig()!=null){
						userProvinceId=user.getUserConfig().getUserProvinceId();
						
					}
				}
		/*
		 * Follow value of mode
		 * xiangmu --> get XiangMu when click next button
		 * mingshi --> get MingShi when click next button
		 * expert --> get Expert when click next button
		 * null --> get All
		 */
		return searchResultList(mapping, form, request, response,userProvinceId);
		
	}
	
	/**
	 * @author B.Sky
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return string type
	 * @throws FrameworkException 
	 * @Description get Search List
	 */
	private String searchResultList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,Long proviceId) throws FrameworkException {
		
		String strSearch = request.getParameter("search") !=null ? request.getParameter("search"):null;
		String mode = request.getParameter("mode")==null ? null:request.getParameter("mode");
		try {
			strSearch = URLDecoder.decode(strSearch, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 项目
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(8);
		pl.setSortCriterion("id");
		pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		CVSet queryCVSet = new CVSet();
		queryCVSet.setName(strSearch);
		queryCVSet.setStatus(5);
		//域名
		queryCVSet.setServerName(request.getServerName());
		localCVSetManage.queryCVSetPageListFromSearch(pl, queryCVSet,proviceId);
		
		for (CVSet cvSet : (List<CVSet>)pl.getList()) {
            //统计项目被多少人学习过
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cvSet.getId());
            List<LogStudyCVSet> list = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(list != null && list.size() > 0){
            	cvSet.setStudentNum(list.size());
            }else{
            	cvSet.setStudentNum(0);
            }
        }
		
		// 名师		
        PageList plExpert = new PageList();
       
        plExpert.setPageNumber(currentPage);
        plExpert.setObjectsPerPage(10);
        plExpert.setSortCriterion("id");
        plExpert.setSortDirection(SortOrderEnum.DESCENDING);

        localExpertManageManage.getExpertListFromSearch(plExpert, strSearch);
        //////////////////////////////////////////
        
        // 机构
        PageList plOrg = new PageList();
        plOrg.setPageNumber(currentPage);
        plOrg.setObjectsPerPage(4);;
        PeixunOrg item = new PeixunOrg();
        item.setName(strSearch);
        int totalSize = localOrgManage.getOrgListSize(item);
		plOrg.setFullListSize(totalSize);
		List<PeixunOrg> data = localOrgManage.queryOrgList(item,plOrg);
		plOrg.setList(data);
        
        //////////////////////////////////////////
        
        // 专委会
        ExpertGroup group = new ExpertGroup();

        List<ExpertGroupModel> groupModelList = new ArrayList<ExpertGroupModel>();
        // 设置查询条件        
        group.setParent(0L);
        group.setState(1);//正常

        PageList expertGroupPage = new PageList();        
        expertGroupPage.setObjectsPerPage(8);
        expertGroupPage.setPageNumber(currentPage);        
        
        localExpertgroupManage.getExpertGroupListFromSearch(strSearch, expertGroupPage);
        
        for (int i = 0; i< expertGroupPage.getList().size(); i++) {
            ExpertGroup eGroup = (ExpertGroup)expertGroupPage.getList().get(i);
            ExpertGroupModel newGroup = new ExpertGroupModel();            
            newGroup.setGroup(eGroup);
            newGroup.setExpertList(localExpertgroupManage.getGroupExpertInfo(eGroup.getId(), 1));
            groupModelList.add(newGroup);
        }
        expertGroupPage.setList(expertGroupPage.getList());
        
        //////////////////////////////////////////     
		request.setAttribute("Search", strSearch);
		if(pl.getFullListSize()==0){
			pl.setFullListSize(pl.getList().size());
		}
		request.setAttribute("XiangMuPage", pl);
        request.setAttribute("pager", plExpert);
        request.setAttribute("plOrg", plOrg);
        request.setAttribute("ExpertGroupPage", expertGroupPage);
        request.setAttribute("ExpertGroupInfo", groupModelList);
        //request.setAttribute("mode", "mingshi");
        if(mode != null && mode.equals("xiangmu")){
        	request.setAttribute("CurrentTab", XiangMu_TAB);
        }else if(mode != null && mode.equals("mingshi")){
        	request.setAttribute("CurrentTab", MingShi_TAB);
        }else if(mode != null && mode.equals("org")){
        	request.setAttribute("CurrentTab", ORG_TAB);
        }else if(mode != null && mode.equals("expert")){
        	request.setAttribute("CurrentTab", EXPERT_TAB);
        }else{
        	request.setAttribute("CurrentTab", ALL_TAB);
        }
        
		return "success";
	}
	
	/**
	 * @author B.Sky
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return string type
	 * @throws FrameworkException 
	 * @Description get XiangMu List
	 */
	private String getXiangMuListAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FrameworkException {

		String strSearch = request.getParameter("search") !=null ? request.getParameter("search"):null;
		//当前登录人
		Long userProvinceId=null;
			SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
			if(user!=null){
				if(user.getUserConfig()!=null){
					userProvinceId=user.getUserConfig().getUserProvinceId();
					
				}
			}
		// 项目
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
				
		pl.setPageNumber(currentPage);
		pl.setObjectsPerPage(8);
		pl.setSortCriterion("id");
		pl.setSortDirection(SortOrderEnum.DESCENDING);
		
		CVSet queryCVSet = new CVSet();
		queryCVSet.setName(strSearch);
		
		localCVSetManage.queryCVSetPageListFromSearch(pl, queryCVSet,userProvinceId);
		
		request.setAttribute("Search", strSearch);
		request.setAttribute("XiangMuPage", pl);
        request.setAttribute("CurrentTab", XiangMu_TAB);
		return "success";
	}
	
	/**
	 * @author B.Sky
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return string type
	 * @throws FrameworkException 
	 * @Description get MingShi List
	 */
	private String getMingShiListAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FrameworkException {
		
		String strSearch = request.getParameter("search") !=null ? request.getParameter("search"):null;
		// 名师
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		
		PageList plExpert = new PageList();
	       
		plExpert.setPageNumber(currentPage);
		plExpert.setObjectsPerPage(10);
		plExpert.setSortCriterion("id");
		plExpert.setSortDirection(SortOrderEnum.DESCENDING);

        localExpertManageManage.getExpertListFromSearch(plExpert, strSearch);
        //////////////////////////////////////////
		
		request.setAttribute("Search", strSearch);
		request.setAttribute("pager", plExpert);
		request.setAttribute("CurrentTab", MingShi_TAB);
		request.setAttribute("mode", "mingshi");
		return "success";
	}
	
	/**
	 * @author B.Sky
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return string type
	 * @throws FrameworkException 
	 * @Description get Expert List
	 */
	private String getExpertListAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FrameworkException {

		String strSearch = request.getParameter("search") !=null ? request.getParameter("search"):null;
		
		ExpertGroup group = new ExpertGroup();

        List<ExpertGroupModel> groupModelList = new ArrayList<ExpertGroupModel>();
        // 设置查询条件        
        group.setParent(0L);
        group.setState(1);//正常

        int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		
        PageList expertGroupPage = new PageList();        
        expertGroupPage.setObjectsPerPage(8);
        expertGroupPage.setPageNumber(currentPage);        
        
        localExpertgroupManage.getExpertGroupListFromSearch(strSearch, expertGroupPage);
        
        for (int i = 0; i< expertGroupPage.getList().size(); i++) {
            ExpertGroup eGroup = (ExpertGroup)expertGroupPage.getList().get(i);
            ExpertGroupModel newGroup = new ExpertGroupModel();            
            newGroup.setGroup(eGroup);
            newGroup.setExpertList(localExpertgroupManage.getGroupExpertInfo(eGroup.getId(), 1));
            groupModelList.add(newGroup);
        }
        expertGroupPage.setList(expertGroupPage.getList());
        
        request.setAttribute("Search", strSearch);
        request.setAttribute("ExpertGroupPage", expertGroupPage);
        request.setAttribute("ExpertGroupInfo", groupModelList);
        request.setAttribute("CurrentTab", EXPERT_TAB);
        return "success";
	}
}
