package com.hys.exam.service.local;


import java.util.List;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;
import com.hys.framework.service.BaseService;

public interface UserImageManage extends BaseService {
	List<UserImage> getUserImageList(UserImage userImage);
	
	boolean addUserImage(UserImage userImage);
	
	boolean deleteUserImage(UserImage userImage);
	
	boolean updateUserImage(UserImage userImage);
	
	List<PropUnit> getHospitalList();
	
	List<PropUnit> getAreaList();
	
	List<PropUnit> getDutyList();

	List<PropUnit> getMajorList();
}
