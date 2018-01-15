package com.hys.exam.dao.local;

import com.hys.exam.model.CvLiveSignup;



public interface CvLiveSignupDAO {

	/**
	 * @author zxw
	 * 直播报名添加
	 *
	 */
	 Long saveCvLiveSignup(CvLiveSignup cvLiveSignup);
	 
	 
	Long cvLiveSignupFind(Long cvId);
}
