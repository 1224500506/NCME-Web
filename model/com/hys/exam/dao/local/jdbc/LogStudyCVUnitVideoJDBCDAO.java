package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;

import com.hys.exam.dao.local.LogStudyCVUnitVideoDao;
import com.hys.qiantai.model.LogStudyCVUnitVideo;

public class LogStudyCVUnitVideoJDBCDAO extends BaseDao implements LogStudyCVUnitVideoDao {


	@Override
	public List<LogStudyCVUnitVideo> findListByUserId(LogStudyCVUnitVideo video) {
		// TODO Auto-generated method stub
		
		String sql = " select uvideo.videoLength,uvideo.videoPlayLength  from log_study_cv_unit_video as uvideo,log_study_cv_unit as vunit " +
					" WHERE uvideo.LOG_STUDY_CV_UNIT_ID = vunit.ID and uvideo.LOG_STUDY_CV_UNIT_ID ="+video.getLogStudyCvUnitId()+" and uvideo.SYSTEM_USER_ID ="+video.getSystemUserId()+"";
		if(!"".equals(video.getEndDate())&& video.getEndDate()!=null){
			sql +="and START_DATE >="+video.getStartDate()+"";
		}		
		if(!"".equals(video.getEndDate()) && video.getEndDate()!=null){
			sql +="and END_DATE <= "+video.getEndDate()+"";
			
		}
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnitVideo.class));
		
	}

	
	
}
