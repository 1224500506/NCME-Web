package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.LogStudyCVUnitVideoDao;
import com.hys.exam.service.local.CVUnitVideoManage;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.qiantai.model.LogStudyCVUnitVideo;

public class CVUnitVideoManageImpl extends BaseMangerImpl implements CVUnitVideoManage {

	private LogStudyCVUnitVideoDao localLogStudyCVUnitVideoDao;
	
	@Override
	public List<LogStudyCVUnitVideo> findListByUserId(LogStudyCVUnitVideo video) {
		
		// TODO Auto-generated method stub
		
    	return 	localLogStudyCVUnitVideoDao.findListByUserId(video);
		
	}

	
	
	
	
	// geting and setting 
	
	public LogStudyCVUnitVideoDao getLocalLogStudyCVUnitVideoDao() {
		return localLogStudyCVUnitVideoDao;
	}

	public void setLocalLogStudyCVUnitVideoDao(
			LogStudyCVUnitVideoDao localLogStudyCVUnitVideoDao) {
		this.localLogStudyCVUnitVideoDao = localLogStudyCVUnitVideoDao;
	}

	
}
