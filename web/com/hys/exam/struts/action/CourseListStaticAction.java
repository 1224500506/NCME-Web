package com.hys.exam.struts.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.exam.common.util.FreeMarkerUtils;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 学科管理
 * @author iU
 *
 */
public class CourseListStaticAction extends BaseAction {
    private ExamPropValFacade localExamPropValFacade;
    
    private CVManage localCVManage;
    
    private CVSetManage localCVSetManage;
    
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

    public void setLocalExamPropValFacade(
            ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }

    protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // PageList pl = new PageList();

        // 取得查询条件
        String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
        String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
        String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
        
        String executeHtml=request.getParameter("eh");
        String logIndex=request.getParameter("logIndex");
        String basePath = request.getSession().getServletContext().getRealPath("/");

        String indexPath = basePath + "courseList.html";
        File file = new File(indexPath);// 文件是否存在
        //静态文件index.html不存在或者executeHtml等于626 触发重新生成条件
        boolean flag=false;
        boolean reCreateFlag=StringUtils.isNotBlank(executeHtml)&&executeHtml.equals("626");
        if(reCreateFlag){
            file.delete();
            flag=true;
        }
        if(!file.exists()){
            flag=true;
        }
        if (flag) {
            synchronized (this){

		        ExamProp prop = new ExamProp();
		        // 取得一级学科
		        prop.setType(1);
		        prop.setName(sname);
		        List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
		        
		        for(ExamProp ep2 : list){
		        	List<ExamProp> list2 = new ArrayList<ExamProp>();
		        	ExamPropQuery query2 = new ExamPropQuery();
		            query2.setSysPropId(ep2.getId());
		            //SCP 2017/6/2 只查询为科室的学科
		            query2.setExt_type(Integer.valueOf(1));
		            ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query2);
		            list2 = rprop.getReturnList();
		            for(ExamProp ep3 : list2){
		            	List<ExamProp> list3 = new ArrayList<ExamProp>();
		            	ExamPropQuery query3 = new ExamPropQuery();
		                query3.setSysPropId(ep3.getId());
		                ExamReturnProp rprop3 = localExamPropValFacade.getNextLevelPropExam(query3);
		                list3 = rprop3.getReturnList();
		                if (list3 != null && list3.size() > 0) {
			                for (ExamProp examProp : list3) {
			                	if (examProp.getType() != 3){
			                		break;
			                	}
			            		// 优质慕课
			                    List<CVSet> mukeList = getCVSetList(request, 3, examProp.getName());
			                    
			            		//Get the 
			            		List<CVSet> zhinanList = getCVSetList(request, 7, examProp.getName());
			            		
			            		//Get the bingli list.Brand = 1 : bingli.
//			            		List<CV> bingliList = getCVList(request, "1", examProp.getName());
			            		
			            		long xiangmus = mukeList.size()+zhinanList.size();//+bingliList.size();
			            		
			            		examProp.setProp_val1(xiangmus);
			            	}
		                }
		                ep3.setExamProp3(list3);
		            }
		            ep2.setExamProp2(list2);
		        }
		
		        List<ExamPropType> tlist = localExamPropValFacade.getExamPropTypeList();
		        if (id != null && !id.equals("0")) {
		            ExamProp subProp = localExamPropValFacade.getSysPropVal(Long.valueOf(id));
		            request.setAttribute("subProp", subProp);
		        }
		
		        // request.setAttribute("parentid", "0");
		        request.setAttribute("type", type);
		        request.setAttribute("prop_val1", id);
		        request.setAttribute("propList", list);
		        request.setAttribute("ctype", tlist);
		        request.setAttribute("sname", sname);
		        request.setAttribute("url", request.getRequestURL().toString());
        
		      //获取上下文
                String ctx=request.getContextPath();
                // 封装数据
                Map<String, Object> targetData = new HashMap<String, Object>();
                targetData.put("ctx", ctx);

                targetData.put("type", type);
                targetData.put("prop_val1", id);
                targetData.put("propList", list);
                targetData.put("ctype", tlist);
                targetData.put("sname", sname);
                targetData.put("url", request.getRequestURL().toString());

                String templateName = "courseList.ftl";
                FreeMarkerUtils.generateHtml(file, targetData, basePath, templateName);
            }
        }
        //2：跳转至静态页面
        if(logIndex == null || logIndex.equals("")){
        	request.getRequestDispatcher("../courseList.html").forward(request, response);
        }
        return "";
//        return "SUCCESS";
    }
    
 // 查询项目列表
    public List<CVSet> getCVSetList(HttpServletRequest request, Integer edtionId, String propName)
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
        
       	query.setPropName(propName);

       	localCVSetManage.queryCVSetPageList3(pl, query);

        return pl.getList();
    }
    
    public List<CV> getCVList(HttpServletRequest request, String brand, String propName) throws Exception {
        PageList<CV> pl = new PageList<CV>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CV queryCv = new CV();
        queryCv.setBrand(brand);
        queryCv.setServerName(request.getServerName());
        
       	queryCv.setPropName(propName);
        //已发布
        queryCv.setStatus(5);

        localCVManage.queryCVPageList2(pl, queryCv);

        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
        }
        //localCVManage.updateCVUnit(cvUnit);
        return pl.getList();
    }
    
}
