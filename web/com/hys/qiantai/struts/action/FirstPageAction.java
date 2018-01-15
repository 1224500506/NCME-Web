package com.hys.qiantai.struts.action;

import com.hys.exam.common.util.FreeMarkerUtils;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.*;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.service.local.*;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.exam.util.NcmeProperties;
import com.hys.exam.utils.HttpRequest;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstPageAction extends BaseAction {

    private ExamPropValFacade localExamPropValFacade;

    private ContentIssueManage localContentIssueManage;

    private SystemSiteFacade systemSiteFacade;

    private SentenceManage localSentenceManage;

    private CVSetManage localCVSetManage;

    private CVManage localCVManage;
    
    private CVSetEntityManage localCVSetEntityDAO;
    private SystemSiteManage systemSiteManage;

    private LogStudyCVSetManage localLogStudyCVSetManage;

    public SystemSiteManage getSystemSiteManage() {
        return systemSiteManage;
    }

    public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
        this.systemSiteManage = systemSiteManage;
    }

    //调用banner图
    private BannerManage bannerManage;

    public BannerManage getBannerManage() {
		return bannerManage;
	}

	public void setBannerManage(BannerManage bannerManage) {
		this.bannerManage = bannerManage;
	}

	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
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

    protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String executeHtml=request.getParameter("eh");
        String basePath = request.getSession().getServletContext().getRealPath("/");

        String indexPath = basePath + "index.html";
        File file = new File(indexPath);// 文件是否存在
        //静态文件index.html不存在或者executeHtml等于626 触发重新生成条件
        boolean flag=false;
        //发出指令，重新生成
        boolean reCreateFlag=StringUtils.isNotBlank(executeHtml)&&executeHtml.equals("626");
        if(reCreateFlag){
            file.delete();
            flag=true;
        }
        if(!file.exists()){
            flag=true;
        }
        //flag 为true 重新生成，false 不重新生成
        if (flag) {
            //获取系统中配置的站点信息
            List<SystemSiteVO> siteList= systemSiteManage.getSystemSiteList();
            String site="";
            if(siteList.size()>0){
                site=siteList.get(0).getDomainName();
            }else{
                site=request.getServerName();
            }
            synchronized (this){
                // 通知公告
                List<Sentence> contentList = getSentenceList(request,site);
                // 政策法规
                List<Sentence> sentenceList = getSentenceList2(request,site);
                // 优质慕课
                List<CVSet> mukeList = getCVSetList(request, 3,site);
                // 名师课程
                List<CV> list1 = getCVList(request, 4,site);
                // 典型病例
                List<CV> list2 = getCVList(request, 5,site);
                // 精彩直播
                //List<CV> list3 = getCVList(request, 6);
                List<CV> list3 = getCVLiveList(request, 2,site);
                // 指南解读
                List<CVSet> zhinanList = getCVSetList(request, 7,site);
                
                //banner
                List<Advert> advertList =bAdverts(request);
                //banner存在request
                request.setAttribute("adverList", advertList);
                
                request.setAttribute("contentList", contentList);
                request.setAttribute("sentenceList", sentenceList);
                request.setAttribute("mukeList", mukeList);
                request.setAttribute("zhinanList", zhinanList);
                request.setAttribute("mingshiList", list1);
                request.setAttribute("bingliList", list2);
                request.setAttribute("zhiboList", list3);
                //获取上下文
                String ctx=request.getContextPath();
                // 封装数据
                Map<String, Object> targetData = new HashMap<String, Object>();
                targetData.put("ctx", ctx);
                //banner存在map
                targetData.put("advertList", advertList);
                
                targetData.put("contentList", contentList);
                targetData.put("sentenceList", sentenceList);
                targetData.put("mukeList", mukeList);
                targetData.put("zhinanList", zhinanList);
                targetData.put("mingshiList", list1);
                targetData.put("bingliList", list2);
                targetData.put("zhiboList", list3);
                String templateName = "index.ftl";
                FreeMarkerUtils.generateHtml(file, targetData, basePath, templateName);

                //删除主从节点的index.html文件
                String siteNode= NcmeProperties.getContextPropertyValue("distributed_site_node");
                String[] nodeArray = siteNode.split(",");
                for(String node : nodeArray){
                    HttpRequest.sendPost("http://"+node +"first.do", "eh=626");
                }
            }
        }
        //2：跳转至静态页面
        request.getRequestDispatcher("index.html").forward(request, response);
        return "";
//        return Constants.SUCCESS;
        
//        return "SUCCESS";
    }


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


    public List<Advert> bAdverts(HttpServletRequest request)
            throws Exception {
    	//查询已发布的  状态是state=2
    	Advert adv = new Advert();
    	Integer state =2;
    	adv.setState(state);
    	List<Advert> list = bannerManage.bannerList(adv);
        return list;
    }
    
    
    public List<Sentence> getSentenceList(HttpServletRequest request,String site)
            throws Exception {
        Sentence sentence = new Sentence();
        //设置分类
        sentence.setType(1);
        //设置状态 已发布
        sentence.setState(2);
        sentence.setServerName(site);

        PageList<Sentence> pl = new PageList<Sentence>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(7);
        pl.setSortDirection(SortOrderEnum.DESCENDING);

        localSentenceManage.querySentencePageList(pl, sentence);

        return pl.getList();
    }

    public List<Sentence> getSentenceList2(HttpServletRequest request,String site)
            throws Exception {
        Sentence sentence = new Sentence();
        //设置分类
        sentence.setType(2);
        //设置状态 已发布
        sentence.setState(2);
        sentence.setServerName(site);

        PageList<Sentence> pl = new PageList<Sentence>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(7);
        pl.setSortDirection(SortOrderEnum.DESCENDING);

        localSentenceManage.querySentencePageList(pl, sentence);

        return pl.getList();
    }
    
    //查询课程列表
    public List<CV> getCVList(HttpServletRequest request, Integer edtionId,String site) throws Exception {
        PageList<CV> pl = new PageList<CV>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CV queryCv = new CV();
        queryCv.setEdtionId(edtionId);
        queryCv.setServerName(site);
        //已发布
        queryCv.setStatus(5);
        //获取用户信息
        SystemUser user = LogicUtil.getSystemUser(request);
        localCVManage.queryCVPageList(pl, queryCv,user);

        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
            //获取项目费用类型情况
            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
        }
        //localCVManage.updateCVUnit(cvUnit);
        return pl.getList();
    }

    // 查询项目列表
    public List<CVSet> getCVSetList(HttpServletRequest request, Integer edtionId,String site)
            throws Exception {
        PageList<CVSet> pl = new PageList<CVSet>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CVSet query = new CVSet();
        query.setEdtionId(edtionId);
        query.setServerName(site);
        //已发布
        query.setStatus(5);
        //获取用户信息
        SystemUser user = LogicUtil.getSystemUser(request);
        localCVSetManage.queryCVSetPageList(pl, query,user);

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
            
            //授课教师
//            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
        }
        return pl.getList();
    }
    
  //查询直播课程列表
    public List<CV> getCVLiveList(HttpServletRequest request, Integer cvType,String site) throws Exception {
    	PageList<CV> pl = new PageList<CV>();
        int currentPage = PageUtil.getPageIndex(request);
        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);
        
        CV queryCv = new CV();
        queryCv.setCv_type(2);
        queryCv.setServerName(site);
        // 已发布
        queryCv.setStatus(5);
        localCVManage.queryCVLivePageList(pl, queryCv);
        SystemUser user = LogicUtil.getSystemUser(request);
        if(user!=null && user.getUserId()!=0L){
        	pl.setList(localCVManage.cvForProvinceManager(pl.getList(),user));
        }else{
        	pl.setList(localCVManage.cvForProvinceManager(pl.getList(),null));
        }
        
        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
            //获取项目费用类型情况
            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
        }
        request.setAttribute("pager", pl);

        return pl.getList();
    }
}
