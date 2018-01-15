package com.hys.exam.dao.local.jdbc;

import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVSetUnitDiscussManageDAO;
import com.hys.exam.model.CVSetUnitDiscuss;
import com.hys.exam.model.CVUnit;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitDiscussJDBCDAO.java
 *     
 *     Description       :   单元讨论
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetUnitDiscussJDBCDAO extends BaseDao implements CVSetUnitDiscussManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitDiscuss
	 * @return   void
	 * 方法说明： 添加单元讨论————DISCUSS_TYPE:病例的讨论存1，素材中插入的讨论点存2，课程单元学习时发的讨论存0
	 */
	@Override
	public void addCVSetUnitDiscuss(CVSetUnitDiscuss discuss) {
		String sql = " insert into cv_set_unit_discuss(cv_set_id,cv_unit_id,discuss_id,system_user_id,discuss_date,discuss_content,discuss_type) values(?,?,?,?,sysdate(),?,0) ";
		getJdbcTemplate().update(sql, discuss.getCvsetId(),discuss.getCvUnitId(),discuss.getDiscussId(),discuss.getSystemUserId(),discuss.getDiscussContent());
	}
	
	/**
	 * @param    CVSetUnitDiscuss
	 * @return   List<CVSetUnitDiscuss>
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明： 查询单元讨论信息————DISCUSS_TYPE:病例的讨论存1，素材中插入的讨论点存2，课程单元学习时发的讨论存0
	 */
	public List<CVSetUnitDiscuss> queryUnitTalk(CVSetUnitDiscuss discuss){
		//YHQ，重写,2017-08-01，加载课程单元的所有讨论
		String sql = " select d.*, u.real_name, u.user_avatar as user_image, u.sex from cv_set_unit_discuss d left join system_user u on d.system_user_id = u.id where d.cv_unit_id = ? and d.discuss_type = 0 order by d.DISCUSS_DATE desc";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetUnitDiscuss.class),discuss.getCvUnitId());
	}
	

	@Override
	public Long getCaseId(CVSetUnitDiscuss discuss) {
		String sql = " select case_id from cv_unit_ref_case where unit_id = ?";
		return getJdbcTemplate().queryForLong(sql, discuss.getCvUnitId());
		
	}

	@Override
	public long CountCVSetUnitDiscuss(CVSetUnitDiscuss discuss) {
		//YHQ,2017-08-01,废弃！！！无调用————————DISCUSS_TYPE:病例的讨论存1，素材中插入的讨论点存2，课程单元学习时发的讨论存0
		/*
		String sql = "select count(*) from cv_set_unit_discuss as unit_dis "
				+ "WHERE unit_dis.CV_SET_ID =" + discuss.getCvsetId() + " and "
				+ "unit_dis.SYSTEM_USER_ID =" + discuss.getSystemUserId()
				+ " and unit_dis.CV_UNIT_ID =" + discuss.getCvUnitId()
				//+ " and unit_dis.DISCUSS_TYPE = " + discuss.getDiscuss_type()
				+ " ";
		 */
		String sql = "select count(*) from cv_set_unit_discuss WHERE SYSTEM_USER_ID = ?  and CV_UNIT_ID = ? " ;
		
		Long discussNum = getJdbcTemplate().queryForLong(sql,discuss.getSystemUserId(),discuss.getCvUnitId()) ; 
		return discussNum ; 
	}

	@Override
	public CVUnit getCvUnit(Long id) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
