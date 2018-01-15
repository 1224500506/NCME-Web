package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;


import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SystemCardUserDao;

import com.hys.exam.model.SystemCard;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.system.SystemCardUser;

public class SystemCardUserJDBCDAO extends BaseDao implements SystemCardUserDao{

	@Override
	public List<SystemCard> findByUserid(Long uid,Long proid) {
		// TODO Auto-generated method stub
		
		String sql = "select * from system_card_user as card_user,system_card as cards," +
				"system_card_type_cv_set as type_cv WHERE card_user.CARD_ID = cards.ID and" +
				" card_user.USER_ID = "+uid+" and type_cv.CARDTYPE_ID = cards.CARD_TYPE and  type_cv.CV_SET_ID ="+proid+" and cards.`STATUS`= 1 ";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class));
		 
	    
	}
	
	
	
	@Override
	public List<SystemCard> findByUseridNumber(Long proid,String cardNumber) {
		// TODO Auto-generated method stub
		
		String sql = "select * from system_card as cards," +
				"system_card_type_cv_set as type_cv WHERE " +
				" cards.CARD_NUMBER = '"+cardNumber+"' and type_cv.CARDTYPE_ID = cards.CARD_TYPE and  type_cv.CV_SET_ID ="+proid;//+" and cards.`STATUS`= 1 ";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class));
		 
	    
	}
	
	
	

	@Override
	public List<SystemCardTypeCvSet> findByporId(Long proid) {
		// TODO Auto-generated method stub
		
		String sql = "select * from system_card_type_cv_set as type_cv WHERE type_cv.CV_SET_ID ="+proid+" ";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardTypeCvSet.class));
	}

	@Override
	public List<SystemCard> findLIstByUidandCardNumber(Long uid,
			String cardNumber) {
		// TODO Auto-generated method stub
		//String sql ="select * from system_card_user as card_user,system_card as card WHERE card_user.CARD_ID =  card.ID and card.CARD_NUMBER = '"+cardNumber+"' and card_user.USER_ID ="+uid+" and card.`STATUS`= 1 ";
		
		
		String sql ="select * from  system_card as card WHERE  card.CARD_NUMBER = '"+cardNumber+"'  and card.`STATUS`= 1 ";
		
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class));
		
	}

	@Override
	public List<SystemCardTypeCvSet> findListByCardType(Long cardtype) {
		// TODO Auto-generated method stub
		
		String sql ="select * from system_card_type_cv_set as type_cv WHERE type_cv.CARDTYPE_ID ="+cardtype+"";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardTypeCvSet.class));
	}

	@Override
	public List<SystemCard> findCardListByUid(Long uid) {
		// TODO Auto-generated method stub
		
		String sql ="select cards.CARD_NUMBER,cards.FACE_VALUE,cards.USEFUL_DATE,cve.`NAME` " +
				"from system_card_user as card_user,system_card as cards,system_card_type_cv_set " +
				"as type_cv,cv_set as cve  WHERE card_user.CARD_ID = cards.ID and cve.ID = type_cv.CV_SET_ID and " +
				"card_user.USER_ID = "+uid+" and type_cv.CARDTYPE_ID = cards.CARD_TYPE ";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class));
	}

	@Override
	public void UpdateCard(SystemCard systemCard) {
		// TODO Auto-generated method stub
		
		String sql ="UPDATE system_card set `STATUS` = 2 WHERE CARD_NUMBER = '"+systemCard.getCARD_NUMBER()+"'";
		
		getJdbcTemplate().update(sql);
		
	}



	@Override
	public void SaveBindUserinfor(SystemCardUser systemCardUser) {
		// TODO Auto-generated method stub
		
	String sql ="insert into system_card_user(CARD_ID,USER_ID,SITE_ID,BIND_DATE) VALUES("+systemCardUser.getCardId()+","+systemCardUser.getUserId()+","+systemCardUser.getSiteId()+",now())"; //"+systemCardUser.getBindDate2()+"
		
		getJdbcTemplate().update(sql);
		
	}



	@Override
	public SystemCard findCardByCardNumberAndpassword(String cardNumber,
			String password) {
		// TODO Auto-generated method stub
		
		String sql ="select * from  system_card as card WHERE  card.CARD_NUMBER = '"+cardNumber+"' and card.CARD_PASSWORD ='"+password+"'";
		
		List<SystemCard> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class)); 
		if(list.size()>0){
			return list.get(0);
		}else{
			
			return null;
			
		}
		
		
	}

	
	@Override
	public SystemCard findCardByCardNum(String cardNumber) {
		
		String sql ="select * from  system_card as card WHERE  card.CARD_NUMBER = '"+cardNumber+"'";
		List<SystemCard> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCard.class)); 
		if(list.size()>0){
			return list.get(0);
		}else{
			
			return null;
			
		}
	}
	
	
	
}
