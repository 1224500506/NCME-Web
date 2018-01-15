package com.hys.qiantai.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ContentIssue;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.ContentIssueManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.SentenceManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

public class HaiWaiShiYeAction extends BaseAction {

    private ExamPropValFacade localExamPropValFacade;

    private ContentIssueManage localContentIssueManage;

    private SystemSiteFacade systemSiteFacade;

    private SentenceManage localSentenceManage;

    private CVSetManage localCVSetManage;

    private CVManage localCVManage;
    
    private LogStudyCVSetManage localLogStudyCVSetManage;            

    protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // List<ExamProp> propList = getPropList(request);
        // 通知公告
        //List<Sentence> contentList = getSentenceList(request);
        // 政策法规
        //List<Sentence> sentenceList = getSentenceList2(request);
        // 优质慕课
        //List<CVSet> mukeList = getCVSetList(request, 3);
        // 名师课程
        //List<CV> list1 = getCVList(request, 4);
        // 典型病例
        //List<CV> list2 = getCVList(request, 5);
        // 精彩直播
        //List<CV> list3 = getCVList(request, 6);
        // 指南解读
        //List<CVSet> zhinanList = getCVSetList(request, 7);

        // if (mukeList.size() > 4) {
        // mukeList = mukeList.subList(0, 4);
        // }
        // if (zhinanList.size() > 4) {
        // zhinanList = zhinanList.subList(0, 4);
        // }
        // request.setAttribute("propList", propList);
        /*
        request.setAttribute("contentList", contentList);
        request.setAttribute("sentenceList", sentenceList);        
        request.setAttribute("zhinanList", zhinanList);
        request.setAttribute("mingshiList", list1);
        request.setAttribute("bingliList", list2);
        request.setAttribute("zhiboList", list3);
        */
    	
    	String pid = request.getParameter("pid");
        String xueke = request.getParameter("xueke");
        String sign = request.getParameter("sign");
        String CVSetLevel = ParamUtils.getParameter(request, "level_h", "-1");
        String CVSetCostSort = request.getParameter("cost_sort");
        String CVSetScoreSort = request.getParameter("score_sort");
    	
        
    	int currentPage = PageUtil.getPageIndex2(request);
        
		 Pager<CVSet> pl = new Pager<CVSet>();
	     pl.setPageOffset(currentPage);
	     pl.setUrl("haiWaiShiYe.do");
	     pl.setQueryString(request);
    	        
        CVSet queryCVSet = new CVSet();        

        if(pid!=null && !pid.equals("") &&pid.equals("510005")){//判断是否和儿科学
        	queryCVSet.setPropId(Integer.valueOf(pid));
        	ExamPropQuery query = new ExamPropQuery();
            query.setSysPropId(Long.valueOf(pid));
            //根据二级学科id查询三级学科，只查询为科室的学科
            query.setExt_type(Integer.valueOf(1));
            ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
            queryCVSet.setXueKeList(rprop.getReturnList());
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
        
        if (!"全部".equals(sign)) {
            queryCVSet.setSign(sign);
        }
        
        queryCVSet.setLevel(NumberUtil.parseInteger(CVSetLevel));
        queryCVSet.setServerName(request.getServerName());        
        queryCVSet.setStatus(5);//已发布
        SystemUser user = LogicUtil.getSystemUser(request);
        if(user!=null&&user.getUserConfig()!=null){
        	queryCVSet.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
        }
        
        localCVSetManage.queryHaiWaiShiYeCVSetPageList(pl, queryCVSet);
                       
        pl.setList(pl.getList());        
                
        for (CVSet cvSet : pl.getList()) {
        	//YHQ,2017-06-27,来自FirstPageAction
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
        }
        
        request.setAttribute("costSort", CVSetCostSort);
        request.setAttribute("scoreSort", CVSetScoreSort);
        request.setAttribute("xueke", xueke);
        request.setAttribute("sign", sign);
        request.setAttribute("level", CVSetLevel);
        
        request.setAttribute("mukeList", pl.getList());
        request.setAttribute("pager", pl);

        return Constants.SUCCESS;
    }
//
//    public List<ExamProp> getPropList(HttpServletRequest request) {
//        String id = request.getParameter("id") == null ? "0" : request
//                .getParameter("id");
//        String type = request.getParameter("type") == null ? "1" : request
//                .getParameter("type");
//        ExamProp prop = new ExamProp();
//        prop.setType(1);
//        prop.setName("全科");
//        List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
//        ExamProp quanProp = null;
//        if (list != null && list.size() != 0) {
//            quanProp = list.get(0);
//        }
//        prop.setName("");
//        list = localExamPropValFacade.getPropListByType(prop);
//        list.add(0, quanProp);
//        List<ExamProp> resultList;
//        if (list.size() >= 6) {
//            resultList = list.subList(0, 6);
//        } else {
//            resultList = list;
//        }
//        return resultList;
//    }

    public List<ContentIssue> getIssueList(HttpServletRequest request)
            throws Exception {
        ContentIssue issue = new ContentIssue();
        issue.setServerName(request.getServerName());

        PageList<ContentIssue> pl = new PageList<ContentIssue>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(7);
        pl.setSortDirection(SortOrderEnum.DESCENDING);
//        pl.setFullListSize(6);

        localContentIssueManage.queryIssuePageList(pl, issue);

        return pl.getList();
    }

    public List<Sentence> getSentenceList(HttpServletRequest request)
            throws Exception {
        Sentence sentence = new Sentence();
        //设置分类
        sentence.setType(1);
        //设置状态 已发布
        sentence.setState(2);
        sentence.setServerName(request.getServerName());

        PageList<Sentence> pl = new PageList<Sentence>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(7);
        pl.setSortDirection(SortOrderEnum.DESCENDING);

        localSentenceManage.querySentencePageList(pl, sentence);

        return pl.getList();
    }

    public List<Sentence> getSentenceList2(HttpServletRequest request)
            throws Exception {
        Sentence sentence = new Sentence();
        //设置分类
        sentence.setType(2);
        //设置状态 已发布
        sentence.setState(2);
        sentence.setServerName(request.getServerName());

        PageList<Sentence> pl = new PageList<Sentence>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(7);
        pl.setSortDirection(SortOrderEnum.DESCENDING);

        localSentenceManage.querySentencePageList(pl, sentence);

        return pl.getList();
    }
    
    //查询课程列表
    public List<CV> getCVList(HttpServletRequest request, Integer edtionId) throws Exception {
        PageList<CV> pl = new PageList<CV>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CV queryCv = new CV();
        queryCv.setEdtionId(edtionId);
        queryCv.setServerName(request.getServerName());
        //已发布
        queryCv.setStatus(5);
        SystemUser user = LogicUtil.getSystemUser(request);
        localCVManage.queryCVPageList(pl, queryCv,user);

        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
        }
        //localCVManage.updateCVUnit(cvUnit);
        return pl.getList();
    }

    // 查询项目列表
    public List<CVSet> getCVSetList(HttpServletRequest request, Integer edtionId)
            throws Exception {
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

        //localCVSetManage.queryHaiWaiShiYeCVSetPageList(pl, query);

        for (CVSet cvSet : pl.getList()) {
        	//YHQ,2017-06-27,来自FirstPageAction
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
            
            //授课教师
//            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
        }
        return pl.getList();
    }
    
    public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public CVSetManage getLocalCVSetManage() {
        return localCVSetManage;
    }

    public void setLocalCVSetManage(CVSetManage localCVSetManage) {
        this.localCVSetManage = localCVSetManage;
    }

    public SentenceManage getLocalSentenceManage() {
        return localSentenceManage;
    }

    public void setLocalSentenceManage(SentenceManage localSentenceManage) {
        this.localSentenceManage = localSentenceManage;
    }

    public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }

    public void setLocalExamPropValFacade(
            ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }

    public SystemSiteFacade getSystemSiteFacade() {
        return systemSiteFacade;
    }

    public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
        this.systemSiteFacade = systemSiteFacade;
    }

    public ContentIssueManage getLocalContentIssueManage() {
        return localContentIssueManage;
    }

    public void setLocalContentIssueManage(
            ContentIssueManage localContentIssueManage) {
        this.localContentIssueManage = localContentIssueManage;
    }

    public CVManage getLocalCVManage() {
        return localCVManage;
    }

    public void setLocalCVManage(CVManage localCVManage) {
        this.localCVManage = localCVManage;
    }
}
