package com.hys.xiangyi.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.xiangyi.dao.CVSetScheduleFaceTeachDao;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-07
 * 
 * 描述：面授课程jdbcDao
 * 
 * 说明:
 */
public class CVSetScheduleFaceTeachJdbcDao extends BaseDao implements CVSetScheduleFaceTeachDao {
	
	/**
	 * 根据项目类型和所在省份查询面授课程
	 */
	@Override
	public List<CVSetScheduleFaceTeach> getCVSetScheduleFaceTeachListByCondition(Integer cvSetType,
			Integer provinceCode) {
		String sqlString = "SELECT * FROM cv_set cs, cv_region cr , cv_set_schedule_faceteach cssf  "
				+ "WHERE cs.id =cr.cv_set_id AND cs.id = cssf.cv_set_id AND cs.cv_set_type = "+cvSetType
				+ " AND cr.REGION_ID="+provinceCode +" AND cs.FORMA in (2,3)" +" AND cs.STATUS= 5"+" order by cssf.sequenceNum";
		List<CVSetScheduleFaceTeach> cvSetScheduleFaceTeachList = getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(CVSetScheduleFaceTeach.class));
		return cvSetScheduleFaceTeachList;
	}

}
