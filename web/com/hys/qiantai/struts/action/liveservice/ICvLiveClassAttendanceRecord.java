package com.hys.qiantai.struts.action.liveservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.transaction.annotation.Transactional;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.CommDoPost;
import com.hys.exam.common.util.JsonToMap;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLiveSignk;
import com.hys.exam.model.CvLiveStudy;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.struts.form.CVForm;
import com.hys.exam.struts.form.CVUnitForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.FilesUtils;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

import net.sf.json.JSONObject;
/**
 * 
 * @author taoliang
 * @desc 直播/教育大讲堂和人员状态接口 
 */
@SuppressWarnings("unused")
public class ICvLiveClassAttendanceRecord extends BaseAction {

	private CvLiveManage localCvLiveManage;
	private LogStudyCVUnitManage localLogStudyCVUnitManage;
	private SystemUserManage localSystemUserManage;
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*http://211.157.226.150:8888/gensee_api/callback.php
			页面收到的是http://211.157.226.150:8888/gensee_api/callback.php?ClassNo=SsPRrxjvRG&Operator=0&Action=105&Affected=0&totalusernum=0
		 	*/		
		String classNo = request.getParameter("ClassNo");//实时课堂ID
		String action = request.getParameter("Action");//触发的动作类型
		String totalusernum = request.getParameter("totalusernum");//参会总人数

		{//用于维护实时观看人数
			CvLiveStudyRef record = new CvLiveStudyRef();
			record.setClass_no(classNo);
			record.setAudience_num(totalusernum == "" ? 0 : Integer.parseInt(totalusernum));
			localCvLiveManage.updateCvLiveStudyRefForAudience(record);
		}
		System.out.println("ClassNo:"+classNo);
		System.out.println("Action:"+action);
		if("103".equals(action)){
			System.out.println("开始直播了");
			//初始化直播间中间表
			CvLiveStudyRef record = new CvLiveStudyRef();
			record.setClass_no(classNo);
			record.setStart_time(System.currentTimeMillis()/1000);
			record.setEnd_time(0L);
			localCvLiveManage.updateCvLiveStudyRef(record);
		}
		if("105".equals(action)){
			System.out.println("结束直播了");
			CvLiveStudyRef record = new CvLiveStudyRef();
			record.setClass_no(classNo);
			record.setEnd_time(System.currentTimeMillis()/1000);
			localCvLiveManage.updateCvLiveStudyRef(record);
		    /*使用异步的方式进行课堂参数记录数据处理*/
			CvLiveRecordThread initCvLiveRecordThread = new CvLiveRecordThread(classNo,localCvLiveManage,localLogStudyCVUnitManage,localSystemUserManage);
			  new Thread(new FutureTask<String>(initCvLiveRecordThread)).start();
		}
		return null;
	}
	
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
        Object obj = beanClass.newInstance();  
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
        return obj;  
    }    
	
	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}
	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}

	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}

	public void setLocalLogStudyCVUnitManage(
			LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}

	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}
	
}