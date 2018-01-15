package com.hys.qiantai.struts.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetScoreLog;

public class ProjectViewAction extends BaseAction {
    private CVSetManage localCVSetManage;
	private CVSetEntityManage localCVSetEntityDAO;

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

    @Override
    protected String actionExecute(ActionMapping arg0, ActionForm arg1,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String mode = ParamUtils.getParameter(request, "mode", "");
    	Long id = Long.parseLong(request.getParameter("id"));
    	SystemUser user = LogicUtil.getSystemUser(request);
    	
    	if (mode != null && mode.equals("isLogin")){//判断是否登录过
    		Map<String,Object> map = new HashMap<String,Object>();    		
    		if (user==null || user.getUserId() == 0){
    			map.put("message", "noLogin");    			
    		} else {
    			map.put("message", "isLogined");
    		}
    		StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
    	if (mode != null && mode.equals("scoreLogIsExist")){//判断是否评价过 YHQ 2017-02-17    		
    		Map<String,Object> map = new HashMap<String,Object>();
    		CVSetScoreLog cssLog = new CVSetScoreLog() ; 
    		cssLog.setCvSetId(id);
    		cssLog.setSystemUserId(user.getUserId());
    		boolean userIsExit = localCVSetManage.getCVSetScoreLogIsExist(cssLog);
    		map.put("message", userIsExit);
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
    	// 评价保存处理
    	if (mode.equals("critique")){    		    		
    		//返回信息
    		Map<String,Object> map = new HashMap<String,Object>();
    		
    		if (user==null || user.getUserId() == 0){
    			map.put("message", "noLogin");
    			StrutsUtil.renderText(response, JsonUtil.map2json(map));
    			return null;
    		}
    		
    		//已评价 YHQ 2017-02-14 1827---begin
    		CVSetScoreLog cssLog = new CVSetScoreLog() ; 
    		cssLog.setCvSetId(id);
    		cssLog.setSystemUserId(user.getUserId());
    		boolean userIsExit = localCVSetManage.getCVSetScoreLogIsExist(cssLog);
    		if (userIsExit) {
    			map.put("message", "isCritiqued");
    			StrutsUtil.renderText(response, JsonUtil.map2json(map));
    			return null;
    		}
    		//已评价 YHQ 2017-02-14 1827---over
    		
    		CVSet query = new CVSet();
    		query.setId(id);
    		query.setCritiqueScore1(Double.valueOf(request.getParameter("score1")));
    		query.setCritiqueScore2(Double.valueOf(request.getParameter("score2")));
    		query.setCritiqueScore3(Double.valueOf(request.getParameter("score3")));
    		query.setCritiqueScore4(Double.valueOf(request.getParameter("score4")));
    		query.setOpinion(request.getParameter("comment"));
    		localCVSetManage.updateCritiqueScoreLog(user.getUserId(), query);

    		map.put("message", "success");
    		map.put("scores", query);
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
    	}
    	
        CVSet query = new CVSet();
        query.setId(NumberUtil.parseLong(request.getParameter("id")));

        CVSet cvSet = localCVSetManage.viewCVSetById(query);

        if (cvSet != null) {
            //所属学科
            cvSet.setExamPropList(localCVSetManage.getExamPropList(cvSet.getId()));
            //项目负责人
            cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getId(), 1));
            //所属机构
            cvSet.setOrganizationList(localCVSetManage.getPeixunOrgList(cvSet.getId()));
            //授课教师
            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
            //课程列表
            List<CV> cvList = localCVSetManage.getCvList(cvSet.getId());
          //合并查询课程与单元信息
    		if(cvList!=null && cvList.size()>0){
    			try {
					int size=cvList.size();
					for(int i=0;i<size;i++){
						CV cv =  cvList.get(i);
						//根据课程信息查询单元信息
						List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
						//查询素材信息SCP
						List<GroupClassInfo> groupClassInfo = localCVSetEntityDAO.queryGroupClassInfo(cv.getId());
						//截取地址中的类型循环存入
						for(int j=0;j<groupClassInfo.size();j++){
							String html = groupClassInfo.get(j).getClassContent();
							Document doc = Jsoup.parse(html);
							Elements allImg = doc.getElementsByTag("img") ;
							for (Element imgElt : allImg) {
								Attributes allProp = imgElt.attributes() ;
								String altAttr = allProp.get("alt") ;
								unitList.get(j).setContent(altAttr);
							}
						}
						cv.setUnitList(unitList);
 		//		cvList.add(cv);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			
    		}
            cvSet.setCvList(cvList);
        }

        /**
         * @author Han
         * @time	2017-02-07
         * 取得项目评论log列表
         */
        Pager<CVSetScoreLog> pl = new Pager<CVSetScoreLog>();
        int currentPage = PageUtil.getPageIndex2(request);        
        pl.setPageOffset(currentPage);
        pl.setUrl("projectDetail.do");
        pl.setQueryString(request);
        
        /*PageList<CVSetScoreLog> pl = new PageList<CVSetScoreLog>();
        int currentPage = PageUtil.getPageIndex(request);
        pl.setPageNumber(currentPage);
        //pl.setObjectsPerPage(20);
*/        CVSetScoreLog log = new CVSetScoreLog();
        log.setCvSetId(id);
        
        localCVSetManage.getCVSetScoreLogList(log, pl);
        request.setAttribute("pinglun", pl.getList());
        request.setAttribute("pager", pl);
        
        request.setAttribute("cvSet", cvSet);

        request.setAttribute("id", id);

        return "detail";
    }
}
