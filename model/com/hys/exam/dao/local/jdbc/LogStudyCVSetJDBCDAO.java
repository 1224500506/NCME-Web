package com.hys.exam.dao.local.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.LogStudyCVSetManageDAO;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.utils.StringUtils;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVSetJDBCDAO.java
 *     
 *     Description       :   学习记录(项目)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVSetJDBCDAO extends BaseDao implements LogStudyCVSetManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	@Override
	public void addLogStudyCVSet(LogStudyCVSet log) {
		String sql = " insert into log_study_cv_set(system_user_id,cv_set_id,state,is_apply_credit,last_update_date,apply_date,site_id) values(?,?,2,1,sysdate(),sysdate(),1) ";
		getJdbcTemplate().update(sql, log.getSystemUserId(), log.getCvSetId());
	}

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 修改学习记录(项目)
	 */
	@Override
	public void updLogStudyCVSet(LogStudyCVSet log) {
		String sql = " update log_study_cv_set set last_update_date=sysdate() where system_user_id=? and cv_set_id=? ";
		getJdbcTemplate().update(sql, log.getSystemUserId(), log.getCvSetId());
	}

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(项目)
	 */
	@Override
	public List<LogStudyCVSet> searchLogStudyCVSet(LogStudyCVSet log) {
		String sql = " select * from log_study_cv_set where system_user_id=? and cv_set_id=? ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVSet.class),log.getSystemUserId(),log.getCvSetId());
	}
	
    /**
     * @author   张建国
     * @return   String
     * @param    LogStudyCVSet
     * @time     2017-01-16
     * 方法说明： 统计指定项目的学习单元经历
     */
	@Override
    public Map<String,Object> tongjiUnitStudy(LogStudyCVSet log){
    	String sql = " select group_concat(cv_unit_id separator '_') as unitIds from log_study_cv_unit cv where cv.log_study_cv_set_id=? and system_user_id=? ";
    	return getJdbcTemplate().queryForMap(sql,log.getCvSetId(),log.getSystemUserId());
    }
	
	/**
     * @author   张建国
     * @return   List
     * @param    Long
     * @time     2017-02-15
     * 方法说明：  根据项目Id查询项目下的单元集合
     */
	public List<CVUnit> queryCVUnitByProjectId(String code,Long projectId){
		String sql ="";
		if(StringUtils.isNotBlank(code)){
			sql = "select * from cv_ref_unit cr,cv_unit cu where cr.cv_id in ( "
                    +" select cv_id from cv_schedule where SCHEDULE_ID in ( "
		                +" select CV_SCHEDULE_ID from cv_set_schedule where CV_SET_ID=?"
	                    +")"
                    +") and cr.unit_id = cu.id and cu.`point` = 1";
		}else{
			
		   sql = "select * from cv_ref_unit where cv_id in ( "
                    +" select cv_id from cv_schedule where SCHEDULE_ID in ( "
		                +" select CV_SCHEDULE_ID from cv_set_schedule where CV_SET_ID=?"
	                    +")"
                    +")";
		}
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class),projectId);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-02-15
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(单元)
	 */
	@Override
	public List<LogStudyCVUnit> searchLogStudyCVUnit(String code,Long projectId,Long userId) {
		String sql ="";
		if(StringUtils.isNotBlank(code) && code.equals("1") ){
		  sql = " select * from log_study_cv_unit,cv_unit where system_user_id=? and log_study_cv_set_id=? and log_study_cv_unit.cv_unit_id = cv_unit.id and cv_unit.`point` =1 and log_study_cv_unit.status=2";
		}else if(StringUtils.isNotBlank(code) && code.equals("2")){
		   sql = " select * from log_study_cv_unit,cv_unit where system_user_id="+userId+" and log_study_cv_unit.cv_unit_id = cv_unit.id  and log_study_cv_unit.status=2 and log_study_cv_unit.cv_unit_id ="+projectId+"";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class));
		}else if(StringUtils.isNotBlank(code)&& code.equals("3")){
			sql = " select * from log_study_cv_unit,cv_unit where system_user_id=? and cv_unit_id=? and log_study_cv_unit.cv_unit_id = cv_unit.id and cv_unit.`point` =1 and log_study_cv_unit.status=1";
		}else if(StringUtils.isNotBlank(code)&& code.equals("4")){
			sql = " select * from log_study_cv_unit where system_user_id=? and log_study_cv_set_id=? and status = 2 ";
			
	    }else if(projectId==null){
	    	// 如果项目id为空根据用户id查询未完成的单元课程
	    	sql = " select cvu.LOG_STUDY_CV_SET_ID from log_study_cv_unit as cvu WHERE cvu.SYSTEM_USER_ID ="+userId+" and cvu.`STATUS` =1  GROUP BY cvu.LOG_STUDY_CV_SET_ID  ";
	    	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class));
	    	
	    }else{
			sql = " select * from log_study_cv_unit where system_user_id=? and log_study_cv_set_id=? ";
		}
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class),userId,projectId);
	}

	@Override
	public void delLogStudyCVSet(LogStudyCVSet log) {
		// TODO Auto-generated method stub
		List<LogStudyCVSet> loglist = searchLogStudyCVSet(log);
	    	for (LogStudyCVSet logStudyCVSet : loglist) {
	    		List<LogStudyCVUnit> ulist = searchLogStudyCVUnit(null,logStudyCVSet.getCvSetId(),logStudyCVSet.getSystemUserId());
	    		for (LogStudyCVUnit logStudyCVUnit : ulist) {
	    			try {
	    				delUnit(logStudyCVUnit.getId());	
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
	    			
				}
	    		delStudylog(logStudyCVSet.getId());
			}
	  
	}

/**
 * 	
 * (删除课程单元表)
 * 方法名：delUnit
 * 创建人：程宏业
 * 时间：2017-2-28-上午11:52:57 
 * 手机:15210211487
 * @param id void
 * @exception 
 * @since  1.0.0
 */

	public void delUnit(Long id){
		String sql = " delete from log_study_cv_unit where id="+id+"";
		getJdbcTemplate().update(sql);
	}
	
	/**
	 * 
	 * (删除学习日志表)
	 * 方法名：delStudylog
	 * 创建人：程宏业
	 * 时间：2017-2-28-上午11:53:14 
	 * 手机:15210211487
	 * @param id void
	 * @exception 
	 * @since  1.0.0
	 */
	
	public void delStudylog(Long id){
		String sql = " delete from log_study_cv_set where id="+id+"";
		getJdbcTemplate().update(sql);
	}
	
	

	
	public static void main(String[] args) {
		Long log = null;
		System.out.println(log.toString());
	}

	@Override
	public List<LogStudyCVSet> LogStudyCVSetList(LogStudyCVSet log) {
		String sql = " select * from log_study_cv_set where cv_set_id=? ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVSet.class),log.getCvSetId());
	}

	@Override
	public List<LogStudyCVSet> getAllLogStudyCVSet() {
		String sql = " select t.CV_SET_ID,t.SYSTEM_USER_ID from log_study_cv_set t";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVSet.class));
	}
	
	
	
	
	
	
}
