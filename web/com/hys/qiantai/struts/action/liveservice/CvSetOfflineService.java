package com.hys.qiantai.struts.action.liveservice;

import java.util.Set;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hys.exam.service.local.CVSetEntityManage;
/**
 * 过期项目下线业务类
 * @author yxt
 */
public class CvSetOfflineService {

	private CVSetEntityManage cVSetEntityManage;
	
	public CvSetOfflineService(){
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		if(cVSetEntityManage == null){
			cVSetEntityManage = (CVSetEntityManage) applicationContext.getBean("localCVSetEntity");
		}
	}
	
	public void toDo(){
		Set<Long> set = cVSetEntityManage.getCvSetByOverdue();
		if(set.size()>0){
			Integer c = cVSetEntityManage.updateCvSetByIds(set);
			System.out.println("共" + set.size() + "个项目过期," + c + "个项目下线成功!");
		}
	}
	
}