package com.hys.exam.dao.local.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CvLiveSignupDAO;
import com.hys.exam.model.CvLiveSignup;

public class CvLiveSignupJdbcDAO extends BaseDao implements CvLiveSignupDAO {

	@Override
	public Long saveCvLiveSignup(CvLiveSignup cvLiveSignup) {
		
		Long Id = cvLiveSignup.getID();
		if(Id == null) Id = this.getNextId("cv_live_signup");
		String sql = "insert into cv_live_signup(cvsetid,userid,startdate) VALUES(?,?,?)";
		getSimpleJdbcTemplate().update(sql,cvLiveSignup.getCvsetid(),cvLiveSignup.getUserId(),cvLiveSignup.getStartDate());
		return Id;
	}

	@Override
	public Long cvLiveSignupFind(Long cvsetId) {
		String sql ="SELECT s.userid from cv_live_signup s where s.cvsetid=?";
		try{
			
	
			return getSimpleJdbcTemplate().queryForLong(sql, cvsetId);
		}catch(EmptyResultDataAccessException e){
			
			return null;
		}

	}
}
