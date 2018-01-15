package com.hys.exam.service.local.impl;

import java.util.List;
import java.util.Map;

import com.hys.exam.dao.local.LogStudyCVSetManageDAO;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.exam.model.LogStudyCVUnit;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVSetManageImpl.java
 *     
 *     Description       :   学习记录(项目)Service实现类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVSetManageImpl extends BaseMangerImpl implements LogStudyCVSetManage{
	
	private LogStudyCVSetManageDAO localLogStudyCVSetDAO;

	public LogStudyCVSetManageDAO getLocalLogStudyCVSetDAO() {
		return localLogStudyCVSetDAO;
	}

	public void setLocalLogStudyCVSetDAO(LogStudyCVSetManageDAO localLogStudyCVSetDAO) {
		this.localLogStudyCVSetDAO = localLogStudyCVSetDAO;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	@Override
	public void addLogStudyCVSet(LogStudyCVSet log) {
		localLogStudyCVSetDAO.addLogStudyCVSet(log);
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
		localLogStudyCVSetDAO.updLogStudyCVSet(log);
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
		return localLogStudyCVSetDAO.searchLogStudyCVSet(log);
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
		return localLogStudyCVSetDAO.tongjiUnitStudy(log);
	}
	
	/**
     * @author   张建国
     * @return   List
     * @param    Long
     * @time     2017-02-15
     * 方法说明：根据项目Id查询项目下的单元集合
     */
	@Override
	public List<CVUnit> queryCVUnitByProjectId(String code,Long projectId){
		return localLogStudyCVSetDAO.queryCVUnitByProjectId(code,projectId);
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
		return localLogStudyCVSetDAO.searchLogStudyCVUnit(code,projectId,userId);
	}

	@Override
	public void delLogStudy(Long cv_setId,Long userid) {
		// TODO Auto-generated method stub
		LogStudyCVSet log = new LogStudyCVSet();
		log.setCvSetId(cv_setId);
		log.setSystemUserId(userid);
		localLogStudyCVSetDAO.delLogStudyCVSet(log);
	}

	@Override
	public List<LogStudyCVSet> LogStudyCVSetList(LogStudyCVSet log) {
		return localLogStudyCVSetDAO.LogStudyCVSetList(log);
	}

	@Override
	public List<LogStudyCVSet> getAllLogStudyCVSet() {
		return localLogStudyCVSetDAO.getAllLogStudyCVSet();
	}

}
