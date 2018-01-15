package com.hys.exam.service.local.impl;


import java.util.List;

import com.hys.exam.dao.local.CvLiveDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveCoursewareStudy;
import com.hys.exam.model.CvLiveSignk;
import com.hys.exam.model.CvLiveStudy;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CvLiveManageImpl extends BaseMangerImpl implements CvLiveManage {

	private CvLiveDAO localCvLiveManageDAO;
	
	public CvLiveDAO getLocalCvLiveManageDAO() {
		return localCvLiveManageDAO;
	}
	public void setLocalCvLiveManageDAO(CvLiveDAO localCvLiveManageDAO) {
		this.localCvLiveManageDAO = localCvLiveManageDAO;
	}

	@Override
	public Long saveCvLiveSignk(CvLiveSignk cvls) {
		return localCvLiveManageDAO.saveCvLiveSignk(cvls);
	}
	@Override
	public List<CvLiveSignk> getCvLiveSignkList(Long userId, String signk) {
		return localCvLiveManageDAO.getCvLiveSignkList(userId, signk);
	}
	@Override
	public boolean delCvLiveSignk(CvLiveSignk cvls) {
		return localCvLiveManageDAO.delCvLiveSignk(cvls);
	}
	@Override
	public List<CvLive> queryCvLiveList(Long cvId) {
		return localCvLiveManageDAO.queryCvLiveList(cvId);
	}
	@Override
	public Long saveCvLiveStudy(CvLiveStudy record) {
		return localCvLiveManageDAO.saveCvLiveStudy(record);
	}
	@Override
	public List<CvLiveStudyRef> queryCvLiveStudyRef(CvLiveStudyRef record) {
		return localCvLiveManageDAO.queryCvLiveStudyRef(record);
	}
	@Override
	public boolean updateCvLiveStudyRef(CvLiveStudyRef record) {
		return localCvLiveManageDAO.updateCvLiveStudyRef(record);
	}
	@Override
	public int getcvZBTypeForInt(Long cvId) {
		return localCvLiveManageDAO.getcvZBTypeForInt(cvId);
	}
	@Override
	public int getZBEndTypeForInt(Long cvId) {
		return localCvLiveManageDAO.getZBEndTypeForInt(cvId);
	}
	@Override
	public List<CvLiveCourseware> getCvLiveCoursewareList(
			CvLiveCourseware record) {
		return localCvLiveManageDAO.getCvLiveCoursewareList(record);
	}
	@Override
	public List<CVUnit> getCvUnitForLive(Long cvId) {
		return localCvLiveManageDAO.getCvUnitForLive(cvId);
	}
	@Override
	public List<CvLiveRefUnit> getCvLiveRefUnitList(CvLiveRefUnit record) {
		return localCvLiveManageDAO.getCvLiveRefUnitList(record);
	}
	public CVUnit getCvUnitByCvId(Long cvId){
		return localCvLiveManageDAO.getCvUnitByCvId(cvId);
	}
	@Override
	public List<CvLiveCourseware> queryCvLiveCoursewareByCoursewareId(String coursewareId) {
		return localCvLiveManageDAO.queryCvLiveCoursewareByCoursewareId(coursewareId);
	}
	@Override
	public List<CvLiveRefUnit> queryCvLiveRefUnitByCoursewareId(String coursewareId) {
		return localCvLiveManageDAO.queryCvLiveRefUnitByCoursewareId(coursewareId);
	}
	@Override
	public List<CvLiveCoursewareStudy> queryCvLiveCoursewareStudy(String coursewareIdUserID) {
		return localCvLiveManageDAO.queryCvLiveCoursewareStudy(coursewareIdUserID);
	}
	@Override
	public List<CvLiveCoursewareStudy> queryCvLiveCoursewareStudy(String coursewareId,
			Long uid) {
		return localCvLiveManageDAO.queryCvLiveCoursewareStudy(coursewareId, uid);
	}
	@Override
	public boolean saveCvLiveCoursewareStudy(CvLiveCoursewareStudy record) {
		return localCvLiveManageDAO.saveCvLiveCoursewareStudy(record);
	}
	@Override
	public boolean updateCvLiveCoursewareStudy(String coursewareIdUserID, CvLiveCoursewareStudy record) {
		return localCvLiveManageDAO.updateCvLiveCoursewareStudy(coursewareIdUserID, record);
	}
	@Override
	public boolean updateCvLiveStudyRefForAudience(CvLiveStudyRef record) {
		return localCvLiveManageDAO.updateCvLiveStudyRefForAudience(record);
	}
	/**
	 * 查询直播
	 */
	@Override
	public CvLive getCvLive(Long cvId) {
		return localCvLiveManageDAO.getCvLive(cvId);
	}
	
	@Override
	public int cvLiveNumber(Long cvId) {
		
		return localCvLiveManageDAO.cvLiveNumber(cvId);
	}
	@Override
	public int cvLiveOverNumber(Long cvId) {
		
		return localCvLiveManageDAO.cvLiveOverNumber(cvId);
	}
	@Override
	public int cvLiveBackNumber(Long cvId) {
		
		return localCvLiveManageDAO.cvLiveBackNumber(cvId);
	}
	/*@Override
	public CVSet queryNameCode(Long cvId) {
		
		return localCvLiveManageDAO.queryNameCode(cvId);
	}*/
}
