package com.hys.xiangyi.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.XiangYiProvince;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.framework.web.action.BaseAction;
import com.hys.xiangyi.service.CVSetScheduleFaceTeachService;
import com.hys.xiangyi.service.FirstPageService;

public class FirstPageAction extends BaseAction {

    
    private FirstPageService firstPageService;
    
    private CVSetManage localCVSetManage;
    
    private CVManage localCVManage;
    
    private CVSetScheduleFaceTeachService cvSetScheduleFaceTeachService;
    
	private CVSetEntityManage localCVSetEntityDAO;
	
	private LogStudyCVSetManage localLogStudyCVSetManage;
    

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

	
	public CVSetScheduleFaceTeachService getCvSetScheduleFaceTeachService() {
		return cvSetScheduleFaceTeachService;
	}

	public void setCvSetScheduleFaceTeachService(CVSetScheduleFaceTeachService cvSetScheduleFaceTeachService) {
		this.cvSetScheduleFaceTeachService = cvSetScheduleFaceTeachService;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}

	public CVManage getLocalCVManage() {
        return localCVManage;
    }

    public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public FirstPageService getFirstPageService() {
		return firstPageService;
	}

	public void setFirstPageService(FirstPageService firstPageService) {
		this.firstPageService = firstPageService;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
			Long codeLong = Long.valueOf(request.getParameter("code"));//地区编码
			String returnStr = null;
		
    		SystemUser user = LogicUtil.getSystemUser(request);
			List<XiangYiProvince> listxy = getProvinceAll();
			request.setAttribute("xyList", listxy);
			List<String> codelist = new ArrayList<String>();
			for(XiangYiProvince xiangYiProvince :listxy){
				codelist.add(xiangYiProvince.getProvinceCode());
			}
			if(user!=null && codelist.contains(codeLong.toString())){
				returnStr = codeLong.toString();
			}else{
				returnStr = "111111111";
			}
			

    		request.setAttribute("code", codeLong);

    		request.setAttribute("FirstPageCVSetList", getFirstPageCVSetAll());
    		request.setAttribute("CVSetList", getCVSetAll(request,codeLong));
    		request.setAttribute("CVList", getCVAll(request,codeLong));
    		
    		List<Sentence> list = getEveryProvinceInformationByCode(codeLong);
    		request.setAttribute("SentenceList", list);
    		request.setAttribute("name", getEveryProvinceNameByCode(codeLong));
    		
    		Integer provinceCode = Integer.valueOf(request.getParameter("code"));
    		
    		if(provinceCode!=111111111){
    			//localCVSetManage.getCVSetById(query)
    			List<CVSetScheduleFaceTeach> cvSetScheduleFaceTeachList = getCVSetScheduleFaceTeachListByCondition(1,provinceCode);
    			request.setAttribute("cvSetScheduleFaceTeachList", cvSetScheduleFaceTeachList);
    			List<CVSet> cvsetList1 = new ArrayList<CVSet>();
    			for (CVSetScheduleFaceTeach cvSetScheduleFaceTeach1 : cvSetScheduleFaceTeachList) {
    				CVSet cvSet1 = localCVSetManage.getCVSetById((long)cvSetScheduleFaceTeach1.getCv_set_id(),user);
    				cvsetList1.add(cvSet1);
				}
    			
    			request.setAttribute("cvsetList1", cvsetList1);
    		}
    		
    		
    		return returnStr;
    		
    }
	
	public List<CVSet> getFirstPageCVSetAll(){
		List<CVSet> list = firstPageService.getFirstPageCVSetAll();
		for (CVSet cvSet : list) {
			 //项目负责人
			cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getId(), 1));
			//统计项目被多少人学习过---taoliang
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cvSet.getId());
            List<LogStudyCVSet> LogStudyCVSetList = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(LogStudyCVSetList != null && LogStudyCVSetList.size() > 0){
            	cvSet.setStudentNum(localLogStudyCVSetManage.LogStudyCVSetList(log).size());
            }else{
            	cvSet.setStudentNum(0);
            }
        }
		return list;
	}
	
	public List<CV> getCVAll(HttpServletRequest request,Long code){
		List<CV> list = firstPageService.getCVAll(request.getServerName(),code);
		for (CV cv : list) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
            //获取项目费用类型情况
            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
        }
		return list;
	}
	
	public List<CVSet> getCVSetAll(HttpServletRequest request,Long code){
		CVSet cvSet = new CVSet();
		cvSet.setServerName(request.getServerName());
		cvSet.setStatus(5);//已发布
		List<CVSet> list = firstPageService.getCVSetAll(cvSet,code);
		for (CVSet cvSet2 : list) {
            //项目负责人
			cvSet2.setManagerList(localCVSetManage.getManagerList(cvSet2.getId(), 1));
			//统计项目被多少人学习过---taoliang
            LogStudyCVSet log = new LogStudyCVSet();
            log.setCvSetId(cvSet2.getId());
            List<LogStudyCVSet> LogStudyCVSetList = localLogStudyCVSetManage.LogStudyCVSetList(log);
            if(LogStudyCVSetList != null && LogStudyCVSetList.size() > 0){
            	cvSet2.setStudentNum(localLogStudyCVSetManage.LogStudyCVSetList(log).size());
            }else{
            	cvSet2.setStudentNum(0);
            }
        }
		return list;
	}

	public String getEveryProvinceNameByCode(Long code){
		return firstPageService.getEveryProvinceNameByCode(code);
	}
	
	public List<Sentence> getEveryProvinceInformationByCode(Long code){
		return firstPageService.getEveryProvinceInformationByCode(code);
	}
	
	public List<Sentence> getFirstPageInformation(){
		return firstPageService.getFirstPageInformation();
	}
    
	public List<XiangYiProvince> getProvinceAll() {
		
		return firstPageService.getProvinceAll();
	}
	
	//根据项目类型和省份获取面授课程信息
	protected List<CVSetScheduleFaceTeach> getCVSetScheduleFaceTeachListByCondition(Integer cvSetType,Integer provinceCode) {
		
		//获取面授课程信息
		List<CVSetScheduleFaceTeach> cvSetScheduleFaceTeachList = cvSetScheduleFaceTeachService.getCVSetScheduleFaceTeachListByCondition(1, provinceCode);
		for(CVSetScheduleFaceTeach cssf : cvSetScheduleFaceTeachList){//根据时间设置面授的类型---taoliang
			Date now = new Date();
    		Date startDate = cssf.getTrain_starttime();
    		Date endDate = cssf.getTrain_endtime();
    		if(startDate.getTime() > now.getTime()){
    			cssf.setFaceType(2);
    		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
    			cssf.setFaceType(1);
    		}else if(endDate.getTime() < now.getTime()){
    			cssf.setFaceType(3);
    		}
		}
		return cvSetScheduleFaceTeachList;
	}
	
}
