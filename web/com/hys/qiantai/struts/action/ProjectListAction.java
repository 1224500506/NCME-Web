package com.hys.qiantai.struts.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.UserImage;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.EditionManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

public class ProjectListAction extends BaseAction {

    private ExamPropValFacade localExamPropValFacade;

    private CVSetManage localCVSetManage;

    private EditionManage localEditionManage;

    private CVManage localCVManage;
    
    private LogStudyCVSetManage localLogStudyCVSetManage;
    

    public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public CVManage getLocalCVManage() {
        return localCVManage;
    }

    public void setLocalCVManage(CVManage localCVManage) {
        this.localCVManage = localCVManage;
    }

    public EditionManage getLocalEditionManage() {
		return localEditionManage;
	}

	public void setLocalEditionManage(EditionManage localEditionManage) {
		this.localEditionManage = localEditionManage;
	}
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String mode = RequestUtil.getParameter(request, "mode");
        //
        Long userProvinceId=null;
        SystemUser user = LogicUtil.getSystemUser(request);
		if(user!=null){
			if(user.getUserConfig()!=null){
				userProvinceId=user.getUserConfig().getUserProvinceId();
				
			}
		}
        if (mode.equals("get_CVS")) {
            return get_CVS(mapping, form, request, response);
        } else if (mode.equals("get_bingli")) {
            return get_bingli(mapping, form, request, response);
        }
        String pid = request.getParameter("pid");
        String xueke = request.getParameter("xueke");
        if(xueke != null && !"".equals(xueke)){
        	xueke = URLDecoder.decode(request.getParameter("xueke"),"UTF-8");
        }
        String sign = request.getParameter("sign");
        if(sign != null && !"".equals(sign)){
        	sign = URLDecoder.decode(request.getParameter("sign"),"UTF-8");
        }
        
        String CVSetName = request.getParameter("item_name");
        String CVSetLevel = ParamUtils.getParameter(request, "level_h", "-1");
        String CVSetCostSort = request.getParameter("cost_sort");//价格
        String CVSetScoreSort = request.getParameter("score_sort");//学分
        String CVSetDateSort = request.getParameter("date_sort");
//        String CVSetRecentCreate = request.getParameter("recent_create");
        String forma = ParamUtils.getParameter(request, "forma");//授课形式
        CVSet queryCVSet = new CVSet();
        queryCVSet.setServerName(request.getServerName());
        queryCVSet.setStart_date(new Date());
        queryCVSet.setStatus(5);//已发布
        queryCVSet.setLevel(NumberUtil.parseInteger(CVSetLevel));
        queryCVSet.setName(CVSetName);
        queryCVSet.setCost_sort(CVSetCostSort);
        queryCVSet.setScore_sort(CVSetScoreSort);
        if ("远程".equals(forma)) {
        	queryCVSet.setForma(1);//授课类型 1远程
        }else if ("面授".equals(forma)) {
			queryCVSet.setForma(3);
		}
        request.setAttribute("forma", forma);
        if (!"全部".equals(sign)) {
            queryCVSet.setSign(sign);
        }

        if(xueke != null && !"".equals(xueke) && !"全部".equals(xueke)){
        	if(xueke.equals("儿科学")){
        		queryCVSet.setPropId(510005);
            	ExamPropQuery query = new ExamPropQuery();
                query.setSysPropId(Long.valueOf(510005));
                //根据二级学科id查询三级学科，只查询为科室的学科
                query.setExt_type(Integer.valueOf(1));
                ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
                queryCVSet.setXueKeList(rprop.getReturnList());
        	}else{
        		queryCVSet.setPropName(xueke);
        	}
            
        }
        if(pid!=null && !pid.equals("") &&pid.equals("510005")){//判断是否和儿科学
        	queryCVSet.setPropId(Integer.valueOf(pid));
        	ExamPropQuery query = new ExamPropQuery();
            query.setSysPropId(Long.valueOf(pid));
            //根据二级学科id查询三级学科，只查询为科室的学科
            query.setExt_type(Integer.valueOf(1));
            ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
            queryCVSet.setXueKeList(rprop.getReturnList());
        }

        
        //以前方法，20170615李飞注销
//        localCVSetManage.queryCVSetPageList2(pl, queryCVSet);
        //分页兼容之前代码，由PageList先查询，查完后放入pager中
        int currentPage = PageUtil.getPageIndex2(request);
        
        PageList<CVSet> pList=new PageList<CVSet>();
        pList.setPageNumber(currentPage);
        localCVSetManage.queryCVSetPageListByNameAndProvice(pList, queryCVSet, userProvinceId);
        
        Pager<CVSet> pl = new Pager<CVSet>();
        pl.setPageOffset(currentPage);
//        pl.setSortDirection(SortOrderEnum.DESCENDING);
        pl.setUrl("ProjectList.do");
        pl.setQueryString(request);
        pl.setList(pList.getList());
        pl.setCount(pList.getFullListSize());
        
        for (CVSet cvSet : pl.getList()) {
            //项目负责人
            cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getId(), 1));
            //统计项目被多少人学习过---taoliang
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cvSet.getId());
            List<LogStudyCVSet> list = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(list != null && list.size() > 0){
            	cvSet.setStudentNum(localLogStudyCVSetManage.LogStudyCVSetList(log).size());
            }else{
            	cvSet.setStudentNum(0);
            }
            //所属机构
			//cvSet.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet.getId()));
        }

        request.setAttribute("costSort", CVSetCostSort);
        request.setAttribute("scoreSort", CVSetScoreSort);
        request.setAttribute("ItemName", queryCVSet.getName());
        request.setAttribute("creater", queryCVSet.getName());
        // request.setAttribute("status", queryCVSet.getStatus());

        request.setAttribute("xueke", xueke);
        request.setAttribute("sign", sign);
        // request.setAttribute("pname", pname);
        // request.setAttribute("ppid", String.valueOf(ppid));
        request.setAttribute("CVSetList", pl.getList());
        request.setAttribute("level", CVSetLevel);
        // request.setAttribute("cvEditionList", eCVSetList);
        request.setAttribute("pager", pl);
        return "list";
    }

	private String get_CVS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		List<CVSet> View = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();	
		queryCVSet.setId(id);
		
		
		View = localCVSetManage.getCVSetList(queryCVSet);
		for (CVSet cvSet:View) {
			cvSet.setFile_path("\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + cvSet.getId());
		}
		request.setAttribute("View", View);
		
		List<UserImage> imageList = View.get(0).getUserImageList();
		String userImageNames = "";
		String userImageIds = "";
		if(imageList !=null && imageList.size() > 0 ){ 
			for (UserImage image:imageList) {
				userImageNames += image.getName() + ",";
				userImageIds += image.getId() + ",";
			}
		}else{}
		List<ExpertInfo> managerlist = View.get(0).getManagerList();
		String manager = "";
		String manager_id = "";
		for(ExpertInfo expertInfo: managerlist){
			manager += expertInfo.getName() + ",";
			manager_id += expertInfo.getId() + ",";
		}
		
		List<ExpertInfo> teacherlist = View.get(0).getTeacherList();
		String teacher = "";
		String teacher_id = "";
		for(ExpertInfo expertInfo: teacherlist){
			teacher += expertInfo.getName() + ",";
			teacher_id += expertInfo.getId() + ",";
		}
		
		List<ExpertInfo> otherTeacherlist = View.get(0).getOtherTeacherList();
		String otherTeacher = "";
		String otherTeacher_id = "";
		for(ExpertInfo expertInfo: otherTeacherlist){
			otherTeacher += expertInfo.getName() + ",";
			otherTeacher_id += expertInfo.getId() + ",";
		}
		
		List<CVSchedule> cvScheduleList = View.get(0).getCVScheduleList();
		String cvschedule = "";
		String cvschedule_id = "";
		String cvschedule_scheduleId = "";
		for(CVSchedule cvshe: cvScheduleList){
			cvschedule += cvshe.getName() + ",";
			cvschedule_id += cvshe.getId() + ",";
			cvschedule_scheduleId += cvshe.getSchedule_id() + ",";
		}
		
		List<PeixunOrg> orgList = new ArrayList<PeixunOrg>();
		for(CVSet cvset:View){		
			orgList = cvset.getOrganizationList();
			//orgList.add(cvset.getOrganizationList().get(0));
		}
		
		request.setAttribute("orgList", orgList);
		request.setAttribute("userImageNames", userImageNames);
		request.setAttribute("userImageIds", userImageIds);
		request.setAttribute("manager", manager);
		request.setAttribute("manager_id", manager_id);
		request.setAttribute("teacher", teacher);
		request.setAttribute("teacher_id", teacher_id);
		request.setAttribute("otherTeacher", otherTeacher);
		request.setAttribute("otherTeacher_id", otherTeacher_id);
		request.setAttribute("cvschedule", cvschedule);
		request.setAttribute("cvschedule_id", cvschedule_id);
		request.setAttribute("cvschedule_scheduleId", cvschedule_scheduleId);
		
		request.setAttribute("imgFile", "\\" + Constants.UPLOAD_FILE_PATH_CVS + "\\" + id);
		return "edit_page";
	}

    private String get_bingli(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Pager<CV> pl = new Pager<CV>();
//
        int currentPage = PageUtil.getPageIndex(request);
//
        String xueke = request.getParameter("xueke");
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CV queryCv = new CV();
        queryCv.setServerName(request.getServerName());
//        queryCv.setBrand("1");//1 病例 2 直播 3 vr
        //已发布
        queryCv.setStatus(5);

        //设置学科
        if(xueke != null && !"".equals(xueke) && !"全部".equals(xueke)){
            queryCv.setPropName(xueke);
        }

        localCVManage.queryCVPageList2(pl, queryCv);
        request.setAttribute("xueke", xueke);
        request.setAttribute("courseList", pl.getList());

        return "courselist";
    }

}
