package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.StudyGuide;
import com.hys.framework.service.BaseService;

public interface StudyGuideManage extends BaseService {
	
	List<StudyGuide> getStudyGuideList(StudyGuide guide);
	
	boolean addStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr);
	
	boolean deleteStudyGuide(StudyGuide guide);

}
