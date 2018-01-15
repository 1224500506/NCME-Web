package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.UserImageManageDAO;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;

import com.hys.exam.service.local.UserImageManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class UserImageManageImpl extends BaseMangerImpl implements UserImageManage {
	
	private UserImageManageDAO localUserImageManageDAO;

	public UserImageManageDAO getLocalUserImageManageDAO() {
		return localUserImageManageDAO;
	}

	public void setLocalUserImageManageDAO(UserImageManageDAO localUserImageManageDAO) {
		this.localUserImageManageDAO = localUserImageManageDAO;
	}

	@Override
	public List<UserImage> getUserImageList(UserImage userImage) {
		return localUserImageManageDAO.getUserImageList(userImage);
	}

	@Override
	public boolean addUserImage(UserImage userImage) {
		return localUserImageManageDAO.addUserImage(userImage);
	}

	@Override
	public boolean deleteUserImage(UserImage userImage) {
		return localUserImageManageDAO.deleteUserImage(userImage);
	}

	@Override
	public boolean updateUserImage(UserImage userImage) {
		return localUserImageManageDAO.updateUserImage(userImage);
	}

	@Override
	public List<PropUnit> getHospitalList() {
		
		return localUserImageManageDAO.getHospitalList();
	}

	@Override
	public List<PropUnit> getAreaList() {
		
		return localUserImageManageDAO.getAreaList();
	}

	@Override
	public List<PropUnit> getDutyList() {
		
		return localUserImageManageDAO.getDutyList();
	}

	@Override
	public List<PropUnit> getMajorList() {
		
		return localUserImageManageDAO.getMajorList();
	}

}
