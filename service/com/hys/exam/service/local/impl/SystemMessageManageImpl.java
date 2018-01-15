package com.hys.exam.service.local.impl;

import com.hys.exam.dao.local.SystemMessageManageDAO;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.SystemMessageManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.qiantai.model.SystemMessageModel;

public class SystemMessageManageImpl extends BaseMangerImpl implements
		SystemMessageManage {

	private SystemMessageManageDAO localSystemMessageManageDAO;

	public SystemMessageManageDAO getLocalSystemMessageManageDAO() {
		return localSystemMessageManageDAO;
	}

	public void setLocalSystemMessageManageDAO(
			SystemMessageManageDAO localSystemMessageManageDAO) {
		this.localSystemMessageManageDAO = localSystemMessageManageDAO;
	}

	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param user
	 * @param pageList
	 * 
	 * Detail : Get the user's message list.
	 */
	@Override
	public void getUserMessageList(SystemUser user, PageList<SystemMessageModel> pList) {
		localSystemMessageManageDAO.getUserMessageList(user, pList);
	}

	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Read all message of messageType.
	 */
	@Override
	public Boolean readAll(Long userId, Integer messageType) {
		return localSystemMessageManageDAO.readAll(userId, messageType);
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete all message of messageType.
	 */
	@Override
	public Boolean deleteAll(Long userId, Integer messageType) {
		return localSystemMessageManageDAO.deleteAll(userId, messageType);
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param messageId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete message.
	 */
	@Override
	public Boolean deleteMessage(Long messageId, Integer messageType) {
		return localSystemMessageManageDAO.deleteMessage(messageId, messageType);
	}

	
	/**
	 * 保存用户系统信息
	 * @author hongye 
	 */
	@Override
	public void SaveMessage(SystemMessageModel SystemMessage) {
		// TODO Auto-generated method stub
		localSystemMessageManageDAO.SaveMessage(SystemMessage);
		
		
	}

	/***
	 * 
	 */
	@Override
	public boolean findMessage(SystemMessageModel SystemMessage) {
		// TODO Auto-generated method stub
		return localSystemMessageManageDAO.findMessage(SystemMessage);
	}
	
	
	  
}
