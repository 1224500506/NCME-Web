package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;

public interface UserImageManageDAO {
	List<UserImage> getUserImageList(UserImage userImage);
	
	boolean addUserImage(UserImage userImage);
	
	boolean updateUserImage(UserImage userImage);
	
	boolean deleteUserImage(UserImage userImage);
	
	List<PropUnit> getHospitalList();
	
	List<PropUnit> getAreaList();
	
	List<PropUnit> getDutyList();
	
	List<PropUnit> getMajorList();
}
