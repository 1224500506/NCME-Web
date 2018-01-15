package com.hys.qiantai.struts.action.liveservice.comm;

import com.hys.exam.util.NcmeProperties;

/**
 * 
 * @author taoliang
 * @desc 直播接口通用配置
 */
public class CvLiveConstant {
	
	//所有接口中 被认证用户具有教师或管理员角色的用户名和密码【为展示互动分配】
	public static final String LOGIN_NAME = NcmeProperties.getContextPropertyValue("GenseeSiteLoginName");
	public static final String PASSWORD = NcmeProperties.getContextPropertyValue("GenseeSitePassword");
	
	//*********************************直播/教育大讲堂和人员状态接口****************************************
	private static String ncmeSite = NcmeProperties.getContextPropertyValue("ncmeGenseeSite");
	
	public static final String TRAINING_EXPORT_HISTORY = ncmeSite + "/integration/site/training/export/history";
	//*********************************获取课堂录制下的所有课件 ****************************************
	public static final String TRAINING_COURSEWARE_LIST = ncmeSite + "/integration/site/training/courseware/list";
	//*********************************获取课件学习记录 ****************************************
	public static final String TRAINING_COURSEWARE_LIST_STUDY_LOG = ncmeSite + "/integration/site/training/export/study/history";
}
