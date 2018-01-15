package com.hys.exam.service.local.impl;



import com.hys.exam.dao.local.CvLiveSignupDAO;
import com.hys.exam.model.CvLiveSignup;
import com.hys.exam.service.local.CvLiveSignupManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CvLiveSignupManageImpl  extends BaseMangerImpl implements CvLiveSignupManage {
private CvLiveSignupDAO localCvLiveSignupManageDAO;


	


	public CvLiveSignupDAO getLocalCvLiveSignupManageDAO() {
	return localCvLiveSignupManageDAO;
}





public void setLocalCvLiveSignupManageDAO(CvLiveSignupDAO localCvLiveSignupManageDAO) {
	this.localCvLiveSignupManageDAO = localCvLiveSignupManageDAO;
}





	@Override
	public Long saveCvLiveSignup(CvLiveSignup cvLiveSignup) {
		
		return localCvLiveSignupManageDAO.saveCvLiveSignup(cvLiveSignup);
	}





	@Override
	public Long cvLiveSignupFind(Long cvsetId) {
		return localCvLiveSignupManageDAO.cvLiveSignupFind(cvsetId);
	}

}
