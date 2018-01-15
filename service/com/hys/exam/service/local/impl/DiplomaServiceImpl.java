package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CvDiplomaDao;
import com.hys.exam.model.CVSet;
import com.hys.exam.service.local.DiplomaService;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.qiantai.model.CvDiplomaEntity;

public class DiplomaServiceImpl extends BaseMangerImpl implements DiplomaService{

	private CvDiplomaDao cvDiplomaDao;
	
	
	@Override
	public void addDiplomaEntity(CvDiplomaEntity cvDiplomaEntity) {
		// TODO Auto-generated method stub
		cvDiplomaDao.addCvDiploma(cvDiplomaEntity);
	}

	
	@Override
	public List<CvDiplomaEntity> findListByItemNumber(CvDiplomaEntity cvDiplomaEntity) {
		// TODO Auto-generated method stub
		return cvDiplomaDao.findListByItemNumber(cvDiplomaEntity);
	}
	

	
	// dao getting and setting 
	
	public CvDiplomaDao getCvDiplomaDao() {
		return cvDiplomaDao;
	}

	public void setCvDiplomaDao(CvDiplomaDao cvDiplomaDao) {
		this.cvDiplomaDao = cvDiplomaDao;
	}


	@Override
	public List<CvDiplomaEntity> findListByUidTime(
			CvDiplomaEntity cvDiplomaEntity) {
		// TODO Auto-generated method stub
		return cvDiplomaDao.findListByUidTime(cvDiplomaEntity);
	}


	
	
}
