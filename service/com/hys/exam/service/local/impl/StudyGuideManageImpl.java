package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.StudyGuideManageDAO;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.service.local.StudyGuideManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class StudyGuideManageImpl extends BaseMangerImpl implements
		StudyGuideManage {

	private StudyGuideManageDAO localStudyGuideManageDAO;

	public StudyGuideManageDAO getLocalStudyGuideManageDAO() {
		return localStudyGuideManageDAO;
	}

	public void setLocalStudyGuideManageDAO(
			StudyGuideManageDAO localStudyGuideManageDAO) {
		this.localStudyGuideManageDAO = localStudyGuideManageDAO;
	}
	
	@Override
	public List<StudyGuide> getStudyGuideList(StudyGuide guide) {
		return localStudyGuideManageDAO.getStudyGuideList(guide);
	}

	@Override
	public boolean addStudyGuide(StudyGuide guide) {
		return localStudyGuideManageDAO.addStudyGuide(guide);
	}

	@Override
	public boolean updateStudyGuide(StudyGuide guide) {
		return localStudyGuideManageDAO.updateStudyGuide(guide);
	}

	@Override
	public boolean deleteStudyGuide(StudyGuide guide) {
		return localStudyGuideManageDAO.deleteStudyGuide(guide);
	}

	@Override
	public boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr) {
		return localStudyGuideManageDAO.updateStudyGuideICDs(guideId, icdPropId, ctr);
	}

}
