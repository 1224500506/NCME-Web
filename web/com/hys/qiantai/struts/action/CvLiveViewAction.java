package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.loader.custom.RootReturn;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveSignk;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.utils.SignKUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.struts.action.liveservice.comm.UserKeyHandleUtils;
/**
 * 
 * @author taoliang
 * @desc 直播情况处理
 * @time 2017-06-02
 */
public class CvLiveViewAction extends BaseAction {
	
	private CvLiveManage localCvLiveManage;
	private CVSetEntityManage localCVSetEntityDAO;
	private CVManage localCVManage;
	private LogStudyCVUnitManage localLogStudyCVUnitManage;
	private CVSetEntityManage cVSetEntityManage;
    @Override
    protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String mode = ParamUtils.getParameter(request, "mode", "");
		if (mode.equals("getSignk")) {
			return getSignk(mapping,form,request,response);
		}
		if (mode.equals("getZBType")) {
			return getZBType(mapping,form,request,response);
		}
		if (mode.equals("getZBEndType")) {//用于直播结束后状态判断
			return getZBEndType(mapping,form,request,response);
		}
		if (mode.equals("viewLivePlayback")) {//用于直播结束后状态判断
			return viewLivePlayback(mapping,form,request,response);
		}
    	return null;
    }
    
    
    private String getSignk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	SystemUser sysUser = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
        if(sysUser==null || sysUser.getUserId()==0L){//验证用户是否登录
        	response.sendRedirect("login.do");
			return null;
		}
        System.out.println("===========学员观看直播K值校验start===========");
        String signK = "";
    	List<CV> list = new ArrayList<CV>();
    	//拿到课程相关的直播信息
    	Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
    	//进行省权限拦截
    	CV queryCV = new CV();
    	queryCV.setId(cvId);
    	list = localCVManage.getCVList(queryCV);
    	if(list != null && list.size() > 0){
    		List<CV> cvList = localCVManage.cvForProvinceManager(list,sysUser);
    		if(cvList != null && cvList.size() > 0){
    			signK = SignKUtil.getSignK(sysUser);//获取身份认证K值
    			System.out.println("我方生成的动态K值为=============="+signK);
    		}
    	}
    	
    	List<CvLive> liveList = localCvLiveManage.queryCvLiveList(cvId);
    	if(liveList == null){
    		return null;
    	}
    	CvLive live = liveList.get(0);
    	
    	//通过课程id查询项目信息
    	CVSet cvSet = localCVSetEntityDAO.queryCVSetListByCvId(cvId);
    	
    	{//此块用于初始化直播课程单元进入log_study_cv_unit
    		//实例化学习记录(单元)
    		Long cvUnitId = localCVManage.getCloneCVUnitList(queryCV).size()>0?
    				localCVManage.getCloneCVUnitList(queryCV).get(0).getId():0;
    		LogStudyCVUnit cvs = new LogStudyCVUnit();
    		cvs.setLogStudyCvSetId(cvSet.getId());
    		cvs.setCvId(cvId);
    		cvs.setCvUnitId(cvUnitId);
    		cvs.setSystemUserId(sysUser.getUserId());
    		//根据单元id判断是否存在记录
    		List<LogStudyCVUnit> logstudylist = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvs);
    		if(logstudylist!=null && logstudylist.size()>0){
    			//执行修改记录
    			localLogStudyCVUnitManage.updLogStudyCVUnit(cvUnitId,sysUser.getUserId());//YHQ,2017-07-24,更新个人的时间而不是更新所有人的记录
    		}else{
    			//执行保存记录
    			localLogStudyCVUnitManage.addLogStudyCVUnit(cvs);
    		}
    	}
        
        //将K值持久化操作
        CvLiveSignk cvls = new CvLiveSignk();
        cvls.setUser_id(sysUser.getId());
        cvls.setReal_name(sysUser.getRealName());
        cvls.setSignk_code(signK);
        localCvLiveManage.saveCvLiveSignk(cvls);
        
        request.setAttribute("ZBUrl", live.getStudent_join_url());
        request.setAttribute("nickname", sysUser.getAccountName());
        request.setAttribute("token", live.getStudent_token());
        request.setAttribute("signK", signK);
        request.setAttribute("uid", UserKeyHandleUtils.inputUserKey(sysUser.getId()));
        request.setAttribute("className", live.getClass_name());
        request.setAttribute("cvsetName", cvSet.getName());
        return "viewListIn";
    }
    
    private String getZBType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		//int typeInt = localCvLiveManage.getcvZBTypeForInt(cvId);
		JSONObject result = new JSONObject();
		//result.put("typeInt", typeInt);
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}  
    
    private String getZBEndType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		
		int endTypeInt = localCvLiveManage.getZBEndTypeForInt(cvId);
		JSONObject result = new JSONObject();
		result.put("endTypeInt", endTypeInt);
		StrutsUtil.renderText(response, result.toString());		
		return null;
	}
    
    private String viewLivePlayback(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	SystemUser sysUser = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
        if(sysUser==null || sysUser.getUserId()==0L){//验证用户是否登录
        	response.sendRedirect("login.do");
			return null;
		}
        Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
        Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
        
        CvLiveRefUnit CRefU = new CvLiveRefUnit();
        CRefU.setCv_id(cvId);
        CRefU.setUnit_id(unitId);
        
        List<CvLiveRefUnit> list = localCvLiveManage.getCvLiveRefUnitList(CRefU);
    	if(list.size() < 1){
    		return null;
    	}
    	
    	CvLiveRefUnit ref = list.get(0);
    	CvLiveCourseware record = new CvLiveCourseware();
    	//record.setCourseware_no(ref.getCourseware_no());
    	record.setClass_no(ref.getClass_no());
    	List<CvLiveCourseware> wareList = localCvLiveManage.getCvLiveCoursewareList(record);
    	
    	if(wareList != null && wareList.size() > 0){
    		for(CvLiveCourseware c:wareList){
    			List<CvLiveRefUnit> listCu = localCvLiveManage.queryCvLiveRefUnitByCoursewareId(c.getCourseware_no());
				if(listCu.size()>0){
					//CvLiveCourseware实体中的cv_id在回放页面用作unit_id
					c.setCv_id(listCu.get(0).getUnit_id());
				}else{
					c.setCv_id(0l);
				}
    		}
    		/*request.setAttribute("backUrl", wareList.get(0).getCourseware_url());
            request.setAttribute("token", wareList.get(0).getCourseware_token());*/
    		if(unitId!=null&&unitId>0L){
    			request.setAttribute("coursewareNo", ref.getCourseware_no());
    		}
    		request.setAttribute("uid", UserKeyHandleUtils.inputUserKey(sysUser.getId()));
    		request.setAttribute("nickname", sysUser.getAccountName());
    		CVSet cvSet = cVSetEntityManage.queryCVSetListByCvId(cvId);
    		request.setAttribute("cvsetId", cvSet.getId());
    		request.setAttribute("cvId", cvId);
    		request.setAttribute("cvLiveCoursewareList", JSONArray.fromObject(wareList));
    		
    		if(cvSet != null){
    			request.setAttribute("cvsetName", cvSet.getName());
    		}
    		//通过课程Id查询教师信息
    		ExpertInfo expert = localCVSetEntityDAO.queryTeacherByCVId(cvId);
    		if(expert != null){
    			request.setAttribute("teacherName", expert.getName());
    		}
    		//通过课程Id查询课程信息
    		CV cv = new CV();
    		cv.setId(cvId);
    		List<CV> cvList = localCVManage.queryCVForCommunal(cv);
    		if(cvList != null && cvList.size() > 0){
    			request.setAttribute("cvName", cvList.get(0).getName());
    		}
    	}
    	return "viewBack";
    }
    
    public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}


	public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}


	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}


	public CVManage getLocalCVManage() {
		return localCVManage;
	}


	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}


	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}


	public void setLocalLogStudyCVUnitManage(
			LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}


	public CVSetEntityManage getcVSetEntityManage() {
		return cVSetEntityManage;
	}


	public void setcVSetEntityManage(CVSetEntityManage cVSetEntityManage) {
		this.cVSetEntityManage = cVSetEntityManage;
	}
	
}
