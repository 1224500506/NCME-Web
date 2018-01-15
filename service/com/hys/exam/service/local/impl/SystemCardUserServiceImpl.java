package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.SystemCardUserDao;
import com.hys.exam.model.SystemCard;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.system.SystemCardUser;
import com.hys.exam.service.local.SystemCardUserService;
import com.hys.framework.service.impl.BaseMangerImpl;

public class SystemCardUserServiceImpl extends BaseMangerImpl implements SystemCardUserService{

	
	private SystemCardUserDao systemCardUserDao;
	
	
	// Getting and Setting 
	public SystemCardUserDao getSystemCardUserDao() {
		return systemCardUserDao;
	}

	public void setSystemCardUserDao(SystemCardUserDao systemCardUserDao) {
		this.systemCardUserDao = systemCardUserDao;
	}


	@Override
	public List<SystemCard> findListByUserId(Long uid, Long proid) {
		// TODO Auto-generated method stub
		return systemCardUserDao.findByUserid(uid, proid);
	}

	@Override
	public List<SystemCardTypeCvSet> findListByProid(Long proid) {
		// TODO Auto-generated method stub
		return systemCardUserDao.findByporId(proid);
	}

	@Override
	public List<SystemCard> findListByUidAndCardNumber(Long uid,
			String cardNumber) {
		// TODO Auto-generated method stub
		return systemCardUserDao.findLIstByUidandCardNumber(uid, cardNumber);
	}

	@Override
	public List<SystemCardTypeCvSet> findListByCardType(Long proid) {
		// TODO Auto-generated method stub
		return systemCardUserDao.findListByCardType(proid);
	}

	@Override
	public List<SystemCard> findCardListByUid(Long uid) {
		// TODO Auto-generated method stub
		return systemCardUserDao.findCardListByUid(uid);
	}

	@Override
	public void UpdateCard(SystemCard systemCard) {
		// TODO Auto-generated method stub
		systemCardUserDao.UpdateCard(systemCard);
	}

	@Override
	public List<SystemCard> findByProidNumber(Long proid, String cardNumber) {
		// TODO Auto-generated method stub
		return systemCardUserDao.findByUseridNumber(proid, cardNumber);
	}

	@Override
	public void SaveBindUserinfor(SystemCardUser systemCardUser) {
		// TODO Auto-generated method stub
		systemCardUserDao.SaveBindUserinfor(systemCardUser);
	}

	@Override
	public SystemCard findCardByCardNumberAndpassword(String cardNumber,
			String password) {
		// TODO Auto-generated method stub
		
		return	systemCardUserDao.findCardByCardNumberAndpassword(cardNumber, password);
		
	}

	@Override
	public SystemCard findCardByCardNum(String cardNumber) {
		return systemCardUserDao.findCardByCardNum(cardNumber);
	}

	
	
	
	
}
