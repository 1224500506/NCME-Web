package com.hys.auth.model;

import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.SystemMessageManage;

public class NewsModel {
	
	//保存学习记录(项目)Service接口
	private LogStudyCVSetManage localLogStudyCVSetManage;
	
	// 项目信息
	private CVSetManage localCVSetManage;
	
	// 系统消息
    private SystemMessageManage localSystemMesageManage;

    
	public LogStudyCVSetManage getLocalLogStudyCVSetManage() {
		return localLogStudyCVSetManage;
	}

	public void setLocalLogStudyCVSetManage(
			LogStudyCVSetManage localLogStudyCVSetManage) {
		this.localLogStudyCVSetManage = localLogStudyCVSetManage;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public SystemMessageManage getLocalSystemMesageManage() {
		return localSystemMesageManage;
	}

	public void setLocalSystemMesageManage(
			SystemMessageManage localSystemMesageManage) {
		this.localSystemMesageManage = localSystemMesageManage;
	}
	
    
    
	
    
    

}
