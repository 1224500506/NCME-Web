package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CvDiplomaDao;
import com.hys.exam.model.CVSet;
import com.hys.exam.utils.StringUtils;
import com.hys.qiantai.model.CvDiplomaEntity;

public class CvDiplomaEntityJdbcDao extends BaseDao implements CvDiplomaDao{

	@Override
	public void addCvDiploma(CvDiplomaEntity t) {
		// TODO Auto-generated method stub
		Long Id = this.getNextId("CV_DIPLOMA");
		t.setId(Id.intValue());

		String sql = "insert into cv_diploma (ID,SYSTEM_USER_ID,ID_NUMBER,SYSTEM_USER_NAME,"
				+ "ITEM_NUMBER,ITEM_NAME,APPLY_TIME,DIPLOMA_NUMBER,PATH_URL,IS_DELETE,ITEM_GRADE"
				+ ",ITEM_TYPE,BEGIN_TIME,END_TIME,ITEM_SCORE)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, t.getId(), t.getSystem_user_id(),
				t.getId_number(), t.getSystem_user_name(), t.getItem_number(),
				t.getItem_name(), t.getApply_time(), t.getDiploma_number(),
				t.getPath_url(), t.getIs_delete(), t.getItem_grade(),
				t.getItem_type(),t.getBegin_time(),t.getEnd_time(),t.getItem_score());

	}

	@Override
	public List<CvDiplomaEntity> findListByItemNumber(
			CvDiplomaEntity t) {
		// TODO Auto-generated method stub
		
		String sql ="";
		if(StringUtils.isNotEmpty(t.getDiploma_number())){
			 sql = "select * from cv_diploma WHERE diploma_number = '"+t.getDiploma_number()+"'";
		}else{
			 sql = "select * from cv_diploma WHERE ITEM_NUMBER = '"+t.getItem_number()+"' and SYSTEM_USER_ID = "+t.getSystem_user_id()+"";
			
		}
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CvDiplomaEntity.class));

	}

	@Override
	public List<CvDiplomaEntity> findListByUidTime(
			CvDiplomaEntity t) {
		// TODO Auto-generated method stub
		 String sql="";
		 sql = "select * from cv_diploma WHERE APPLY_TIME >= '"+t.getBegin_time()+"' and APPLY_TIME<='"+t.getEnd_time()+"' and SYSTEM_USER_ID = "+t.getSystem_user_id()+"";
		
		 return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CvDiplomaEntity.class));
	}

	

	

}
