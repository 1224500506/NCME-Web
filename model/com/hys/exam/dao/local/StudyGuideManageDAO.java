package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.StudyGuide;

public interface StudyGuideManageDAO {
	
	List<StudyGuide> getStudyGuideList(StudyGuide guide);
	
	boolean addStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr);
	
	boolean deleteStudyGuide(StudyGuide guide);
}
