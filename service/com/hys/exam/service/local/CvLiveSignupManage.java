package com.hys.exam.service.local;

import com.hys.exam.model.CvLiveSignup;
import com.hys.framework.service.BaseService;

public interface CvLiveSignupManage  extends BaseService{
	 Long saveCvLiveSignup(CvLiveSignup cvLiveSignup);
	 Long cvLiveSignupFind(Long cvsetId);
}
