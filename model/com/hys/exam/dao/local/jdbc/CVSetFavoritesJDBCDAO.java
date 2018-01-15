package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVSetFacoritesManageDAO;
import com.hys.exam.model.CVSetFavorutes;
import com.hys.exam.model.ExamPaperFasterTactic;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetFavoritesJDBCDAO.java
 *     
 *     Description       :   项目收藏JDBCDAO
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetFavoritesJDBCDAO extends BaseDao implements CVSetFacoritesManageDAO {
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户添加项目收藏
	 */
	@Override
	public void addCVSetFavorites(CVSetFavorutes cvs) {
		String sql = " insert into cv_set_favorites (cv_set_id,system_user_id,favorite_date) values(?,?,sysdate()) ";
		getJdbcTemplate().update(sql, cvs.getCvSetId(), cvs.getSystemUserId());
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户取消项目收藏
	 */
	@Override
	public void deleteCVSetFavorites(CVSetFavorutes cvs) {
		String sql = " delete from cv_set_favorites where cv_set_id = ? and system_user_id= ? ";
		getJdbcTemplate().update(sql, cvs.getCvSetId(), cvs.getSystemUserId());
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   List<CVSetFavorutes>
	 * 方法说明： 查询用户收藏项目
	 */
	@Override
	public List<CVSetFavorutes> searchCVSetFavorites(CVSetFavorutes cvs) {
		String sql = " select * from cv_set_favorites where cv_set_id = ? and system_user_id = ? ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetFavorutes.class),cvs.getCvSetId(),cvs.getSystemUserId());
	}

}
