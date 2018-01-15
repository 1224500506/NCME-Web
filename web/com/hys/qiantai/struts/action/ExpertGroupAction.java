package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.LongUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.ExpertGroupModel;

/**
 * 
 * @author Tiger.
 * @time 2017-1-13
 * 专家委员会管理.
 */
public class ExpertGroupAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	
	private ExpertManageManage localExpertManageManage;
	
    private CVSetManage localCVSetManage;

    private CVManage localCVManage;

	private ExamPropValFacade localExamPropValFacade;
	
	private LogStudyCVSetManage localLogStudyCVSetManage;

    public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}
	
    public CVSetManage getLocalCVSetManage() {
        return localCVSetManage;
    }

    public void setLocalCVSetManage(CVSetManage localCVSetManage) {
        this.localCVSetManage = localCVSetManage;
    }
    
    public CVManage getLocalCVManage() {
        return localCVManage;
    }

    public void setLocalCVManage(CVManage localCVManage) {
        this.localCVManage = localCVManage;
    }
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}
	
	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
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
		else if (method.equals("lock")){
			return lock(mapping, form, request, response);
		}
		else if (method.equals("personal")){
			return personal(mapping, form, request, response);
		}
		else if (method.equals("detail")){
			return detail(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}
	

	/**
	 * 显示添加专委会页面
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
		ExpertGroup group = new ExpertGroup();
		group.setState(1);
		request.setAttribute("info", group);
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
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group = localExpertgroupManage.getExpertGroup(eform.getId());
		request.setAttribute("info", group);
		return "edit";
	}
	/**
	 * 保存委员会信息
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
		ExpertGroupForm eform = (ExpertGroupForm)form;
		
		ExpertGroup oldGroup = new ExpertGroup();
		
		String name = request.getParameter("rsName");
		String propIds = request.getParameter("propIds");
		String summary = request.getParameter("summary");
		String email = request.getParameter("email");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String note = request.getParameter("note");
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		String organizeDate = request.getParameter("organizeDate");
		//设置查询条件
		if(id != null && !id.equals(""))
		{
			oldGroup.setId(Long.valueOf(id));
		}
		oldGroup.setName(name);
		List<ExpertGroup> nameList = localExpertgroupManage.getExpertGroupfromName(oldGroup);
		if(nameList.size() != 0)
		{
			StrutsUtil.renderText(response, "repeatname");
			return null;
		}
		ExpertGroup group = new ExpertGroup();
		
		//如果是修改设置ID
		if (id != null && !id.equals(""))
			group.setId(LongUtil.parseLong(id));
		
		//设置信息
		group.setAddress(address);
//		group.setBreakDate(DateUtil.parse(eform.getBreakDate(), "yyyy-MM-dd"));
		group.setContact(contact);
		group.setEmail(email);
//		group.setMaster(eform.getMaster());
		group.setName(name);
		group.setNote(note);
		group.setOrganizeDate(DateUtil.parse(organizeDate, "yyyy-MM-dd"));
		group.setParent(0L);
		group.setPhone1(phone1);
		group.setPhone2(phone2);
		if(state != null && state !="")
			group.setState(Integer.valueOf(state));
		group.setSummary(summary);
		group.setPropIds(propIds);
		
		if (group.getId() == null)	//添加
			localExpertgroupManage.addExpertGroup(group);
		else	//修改
			localExpertgroupManage.updateExpertGroup(group);
		
		StrutsUtil.renderText(response, "success");
		return null;
	}
	
	/**
	 * 删除委员会
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
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group.setId(eform.getId());
		
		boolean flag = localExpertgroupManage.deleteExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 禁用委员会
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置禁用状态为2
		group.setId(eform.getId());
		group = localExpertgroupManage.getExpertGroup(group);
		if(group.getLockState() != null && group.getLockState() == 1)
			group.setLockState(2);
		else
			group.setLockState(1);
		
		boolean flag = localExpertgroupManage.updateExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 取得符合查询条件的委员会数据
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

        ExpertGroupForm eform = (ExpertGroupForm) form;
        ExpertGroup group = new ExpertGroup();

        List<ExpertGroupModel> groupModelList = new ArrayList<ExpertGroupModel>();
        // 设置查询条件
        group.setName(eform.getName());
        group.setLockState(eform.getState());
        group.setPropIds(eform.getPropIds());
        group.setPropNames(eform.getPropNames());
        group.setParent(0L);
        group.setState(1);//正常
        group.setStartDate(eform.getStartDate());
        group.setEndDate(eform.getEndDate());
        
       /* Pager<CVSet> pl = new Pager<CVSet>();

        int currentPage = PageUtil.getPageIndex2(request);
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.DESCENDING);
        pl.setUrl("ProjectList.do");
        pl.setQueryString(request);
//        pl.setPageSize(1);

        localCVSetManage.queryCVSetPageList2(pl, queryCVSet);*/
        
        
        Pager<ExpertGroup> page = new Pager<ExpertGroup>();
        int curPage = PageUtil.getPageIndex2(request);
        page.setPageOffset(curPage);
        page.setUrl("ExpertGroup.do");
        page.setQueryString(request);
        localExpertgroupManage.getExpertGroupList(group, page);
        
        
/*        PageList pl = new PageList();
        int currentPage = PageUtil.getPageIndex(request);
        pl.setObjectsPerPage(10);
        pl.setPageNumber(currentPage);
        int totalSize = localExpertgroupManage.getExpertGroupListSize(group);
        pl.setFullListSize(totalSize);

        List<ExpertGroup> list = localExpertgroupManage.getExpertGroupList(group, pl);*/
        
        for (ExpertGroup e : page.getList()) {
            ExpertGroupModel newGroup = new ExpertGroupModel();
            newGroup.setGroup(e);
            newGroup.setExpertList(localExpertgroupManage.getGroupExpertInfo(e.getId(), 1));
            groupModelList.add(newGroup);
        }
        request.setAttribute("pager", page);
        request.setAttribute("info", groupModelList);

        return "list";
    }
				
    /**
	 * @author Alisa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 * detail: get all GropInformation corresponding user of course
	 */
    
	private String personal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SystemUser user = LogicUtil.getSystemUser(request);		
		if(user == null)
		{
			return "fail";
		}
		String xueke = user.getProp_Id();		
		if (!StringUtils.checkNull(xueke)) //if(xueke != null && !xueke.equals(""))  YHQ 2017-02-16
		{
			ExamProp prop = localExamPropValFacade.getSysPropVal(Long.valueOf(xueke));
			request.setAttribute("prop", prop);
			
			ExpertGroup group = new ExpertGroup();
			group.setPropIds(xueke);
			
			List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
			for (ExpertGroup e: list){

				Long id = e.getId();
		
				ExpertGroupModel groupModel = new ExpertGroupModel();
				List<ExpertGroupModel> subGroupModel = new ArrayList<ExpertGroupModel>();
				
				// get group include user
				ExpertGroup curGroup =  localExpertgroupManage.getExpertGroup(Long.valueOf(id));
				if(curGroup==null){
					break;
				}
				groupModel.setGroup(curGroup);
	            // 主任委员 副主任委员 秘书长 信息
	            groupModel.setExpertList(localExpertgroupManage.getGroupExpertInfo(
	                    curGroup.getId(), 1, 2, 3));			
				
				ExpertGroup temp = new ExpertGroup();
				
				temp.setParent(curGroup.getId());
				temp.setLockState(1);
				
				// get subgroup which group include
				List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
				for(ExpertGroup s : sublist){
					ExpertGroupModel sub = new ExpertGroupModel();
					sub.setGroup(s);
					subGroupModel.add(sub);
				}
				request.setAttribute("groupInfo", groupModel);
				request.setAttribute("subGroupInfo", subGroupModel);
				break;
			}
		}
		
		// 优质慕课
        PageList<CVSet> mukeList = getCVSetListNew(request, 3);
        
		//Get the bind list.
		List<CVSet> bindList = getBindList(request, user);
		
		//Get the 
		PageList<CVSet> zhinanList = getCVSetListNew(request,7);
		
		//Get the bingli list.Brand = 1 : bingli.
		List<CV> bingliList = getCVList(request,"1");
		
		for (CVSet cs : mukeList.getList()) {
			//统计项目被多少人学习过---taoliang
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cs.getId());
            List<LogStudyCVSet> list = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(list != null && list.size() > 0){
            	cs.setStudentNum(localLogStudyCVSetManage.LogStudyCVSetList(log).size());
            }else{
            	cs.setStudentNum(0);
            }
		}
		
		for (CVSet cs : bindList) {
			//统计项目被多少人学习过---taoliang
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cs.getId());
            List<LogStudyCVSet> list = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(list != null && list.size() > 0){
            	cs.setStudentNum(localLogStudyCVSetManage.LogStudyCVSetList(log).size());
            }else{
            	cs.setStudentNum(0);
            }
		}
		
		for (CVSet cs : zhinanList.getList()) {
			//统计项目被多少人学习过---taoliang
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cs.getId());
            List<LogStudyCVSet> list = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(list != null && list.size() > 0){
            	cs.setStudentNum(localLogStudyCVSetManage.LogStudyCVSetList(log).size());
            }else{
            	cs.setStudentNum(0);
            }
		}
		
		request.setAttribute("mukeList", mukeList);
		request.setAttribute("bindList", bindList);
		request.setAttribute("zhinanList", zhinanList);
		request.setAttribute("bingliList", bingliList);
		return "personal";
	}
	/**
	 * 显示添加专委会页面
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
        String id = request.getParameter("id");
        ExpertGroupModel groupModel = new ExpertGroupModel();
        List<ExpertGroupModel> subGroupModel = new ArrayList<ExpertGroupModel>();

        if (id != null && !id.equals("")) {
            ExpertGroup curGroup = localExpertgroupManage.getExpertGroup(Long.valueOf(id));
            groupModel.setGroup(curGroup);
            groupModel.setExpertList(new ArrayList<ExpertInfo>());
            // 主任委员 副主任委员 秘书长 信息
            groupModel.setExpertList(localExpertgroupManage.getGroupExpertInfo(
                    curGroup.getId(), 1, 2, 3));
            for(ExpertInfo ex : groupModel.getExpertList())
            {
            	try{
            		if (ex.getJob() != 0){
            			ExamProp prop = localExamPropValFacade.getSysPropVal(ex.getJob());
            			ex.setJobName(prop.getName());
            		}
            		}catch(Exception e){}
            }
            ExpertGroup temp = new ExpertGroup();
            temp.setParent(curGroup.getId());
            temp.setLockState(1);

            List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
            for (ExpertGroup s : sublist) {
                ExpertGroupModel sub = new ExpertGroupModel();
                sub.setGroup(s);
                
                ExpertInfo expertInfo = new ExpertInfo();
                expertInfo.setGroupIds(s.getId().toString());
                // 取得专家
//                sub.setExpertList(localExpertgroupManage.getGroupExpertInfo(
//                    s.getId(), 4, 5));
                sub.setExpertList(localExpertgroupManage.getGroupExpertInfo(s.getId()));
                subGroupModel.add(sub);
            }
            request.setAttribute("groupInfo", groupModel);
            request.setAttribute("subGroupInfo", subGroupModel);
            
            
        }
        return "detail";
    }
    
    //查询课程列表
    public List<CV> getCVList(HttpServletRequest request, String brand) throws Exception {
        PageList<CV> pl = new PageList<CV>();

        SystemUser user = LogicUtil.getSystemUser(request);
        
        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CV queryCv = new CV();
        queryCv.setBrand(brand);
        queryCv.setServerName(request.getServerName());
        
        if(!StringUtils.checkNull(user.getProp_Id()))
		{
			ExamProp userXueke = localExamPropValFacade.getSysPropVal(Long.valueOf(user.getProp_Id()));
			
	        if(userXueke!=null && userXueke.getName() != null && !"".equals(userXueke.getName())){
	        	queryCv.setPropName(userXueke.getName());
	        }
		}
        //已发布
        queryCv.setStatus(5);

        localCVManage.queryCVPageList2(pl, queryCv);

        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
        }
        //localCVManage.updateCVUnit(cvUnit);
        return pl.getList();
    }
    
    /**
     * @author Tiger.
     * @time 2017-1-13
     * @param request
     * @param edtionId
     * @return
     * @throws Exception
     * 
     * Get the 'zhinan' CV_Set list from user's information.
     */
    // 查询项目列表
    public List<CVSet> getCVSetList(HttpServletRequest request, Integer edtionId)
            throws Exception {    	
    	SystemUser user = LogicUtil.getSystemUser(request);		
    	
        PageList<CVSet> pl = new PageList<CVSet>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CVSet query = new CVSet();
        query.setEdtionId(edtionId);
        query.setServerName(request.getServerName());
        //已发布
        query.setStatus(5);
        
    	if(!StringUtils.checkNull(user.getProp_Id()))
		{
			ExamProp userXueke = localExamPropValFacade.getSysPropVal(Long.valueOf(user.getProp_Id()));
			
	        if(userXueke!=null && userXueke.getName() != null && !"".equals(userXueke.getName())){
	        	query.setPropName(userXueke.getName());
	        }
		}
    	
    	if(user!=null&&user.getUserConfig()!=null){
    		query.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
    	}
    	
    	if (edtionId == 3) { //优质慕课，获取本人学科下的慕课，YHQ 2017-02-17
    		if(!StringUtils.checkNull(user.getProp_Id())) query.setPropId(Integer.parseInt(user.getProp_Id()));
    		localCVSetManage.getMyXuekeMukeList(pl, query) ;
    	} else if (edtionId == 7){ //指南
    		localCVSetManage.queryCVSetPageList3(pl, query);
    	}       

        return pl.getList();
    }
    
    // 查询项目列表
    public PageList<CVSet> getCVSetListNew(HttpServletRequest request, Integer edtionId)
            throws Exception {    	
        SystemUser user = LogicUtil.getSystemUser(request);		
    	Long userProvinceId=null;
    	if(user!=null){
			if(user.getUserConfig()!=null){
				userProvinceId=user.getUserConfig().getUserProvinceId();
			}
		}
    	
        PageList<CVSet> pl = new PageList<CVSet>();

        int currentPage = PageUtil.getPageIndex2(request);

        pl.setPageNumber(currentPage);
        //pl.setObjectsPerPage(8);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CVSet query = new CVSet();
        query.setServerName(request.getServerName());
        query.setStart_date(new Date());
        //已发布
        query.setStatus(5);
        query.setForma(1);//授课类型 1远程
        
        if (edtionId == 7) {
            query.setSign("指南解读");
        }
        
    	if(!StringUtils.checkNull(user.getProp_Id()))
		{
			ExamProp userXueke = localExamPropValFacade.getSysPropVal(Long.valueOf(user.getProp_Id()));
			
	        if(userXueke!=null && userXueke.getName() != null && !"".equals(userXueke.getName())){
	        	query.setPropName(userXueke.getName());
	        }
		}
    	if(!StringUtils.checkNull(user.getProp_Id())) {
    		query.setPropId(Integer.parseInt(user.getProp_Id()));
    	}
    	
        localCVSetManage.queryCVSetPageListByNameAndProvice(pl, query, userProvinceId);

        return pl;
    }
    /**
     * @author Tiger.
     * @time 2017-1-11
     * @param request
     * @param user
     * @return
     * @throws Exception
     * 
     * Detail : Get the bind cv_set list.
     */
    public List<CVSet> getBindList(HttpServletRequest request, SystemUser user) throws Exception
    {			
        PageList<CVSet> pl = new PageList<CVSet>();

        CVSet query = new CVSet();
        query.setServerName(request.getServerName());
        //已发布
        query.setStatus(5);
        query.setStart_date(new Date());
        
        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);
        
        //localCVSetManage.getBindList(pl, query, user);
        localCVSetManage.getMyXuekeProjectList(pl, query, user) ; //获取我的学科下的项目列表，YHQ 2017-02-17
    	return pl.getList();
    }
}
