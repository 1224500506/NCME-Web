package com.hys.auth.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hys.exam.constants.Constants;
import com.hys.exam.util.NcmeProperties;
import com.hys.qiantai.struts.action.liveservice.CvLivePlaybackTimer;
import com.hys.qiantai.struts.action.liveservice.CvSetOfflineTimer;

public class WebAppContextListener implements ServletContextListener {
	private ServletContext context = null;
	private CvLivePlaybackTimer cvLivePlaybackTimer = null;
	private CvSetOfflineTimer cvSetOfflineTimer = null;
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		context = null;
		if(cvLivePlaybackTimer!=null){
			cvLivePlaybackTimer.timerStop();
		}
		if(cvSetOfflineTimer!=null){
			cvSetOfflineTimer.timerStop();
		}
		System.out.println("关闭信息！");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Constants.validateLicense = true;
		
		event.getServletContext().setAttribute("ctxPropertiesMap", NcmeProperties.getCtxPropertiesMap()); //YHQ，2017-05-12
		
		/******	com.hys.qiantai.struts.action.liveservice.CvLivePlaybackTimer同步直播回放记录	YXT,2017-07-21	******/
		cvLivePlaybackTimer = new CvLivePlaybackTimer();
		cvLivePlaybackTimer.timerStart();
		/******	com.hys.qiantai.struts.action.liveservice.CvLivePlaybackTimer同步直播回放记录	YXT,2017-07-21	******/
		
		/******	com.hys.qiantai.struts.action.liveservice.CvSetOfflineTimer过期项目下线	YXT,2017-12-04	******/
		cvSetOfflineTimer = new CvSetOfflineTimer();
		cvSetOfflineTimer.timerStart();
		/******	com.hys.qiantai.struts.action.liveservice.CvSetOfflineTimer过期项目下线	YXT,2017-12-04	******/
		
//		
//		//验证license
//		if (!LicenseManage.validateLicense()){
//			System.out.println("许可证无效,请重新导入许可证!");
//		}else{
//			Constants.validateLicense = true;
//			System.out.println("许可证通过,感谢使用本产品!");
//		}
		
	}
	
}