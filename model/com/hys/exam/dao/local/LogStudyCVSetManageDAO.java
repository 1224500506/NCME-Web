package com.hys.exam.dao.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.CVUnit;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVSetManageDAO.java
 *     
 *     Description       :   学习记录(项目)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public interface LogStudyCVSetManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	void addLogStudyCVSet(LogStudyCVSet log);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 修改学习记录(项目)
	 */
	void updLogStudyCVSet(LogStudyCVSet log);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(项目)
	 */
	List<LogStudyCVSet> searchLogStudyCVSet(LogStudyCVSet log);
	
	 /**
     * @author   张建国
     * @return   String
     * @param    LogStudyCVSet
     * @time     2017-01-16
     * 方法说明： 统计指定项目的学习单元经历
     */
	Map<String,Object> tongjiUnitStudy(LogStudyCVSet log);
	
	/**
     * @author   张建国
     * @return   List
     * @param    Long
     * @time     2017-02-15
     * 方法说明：根据项目Id查询项目下的单元集合
     */
	List<CVUnit> queryCVUnitByProjectId(String code,Long projectId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-15
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(单元)
	 */
	List<LogStudyCVUnit> searchLogStudyCVUnit(String code,Long projectId,Long userId);
	
	/**
	 * 
	 * (删除学习记录)
	 * 方法名：delLogStudyCVSet
	 * 创建人：程宏业
	 * 时间：2017-2-28-上午11:16:41 
	 * 手机:15210211487
	 * @param log void
	 * @exception 
	 * @since  1.0.0
	 */
	void  delLogStudyCVSet(LogStudyCVSet log);
	
	/**
	 * @author   taoliang
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(项目)
	 */
	public List<LogStudyCVSet> LogStudyCVSetList(LogStudyCVSet log);
	
	/**
	 * @author taoliang
	 * @return
	 * @desc 获取LogStudyCVSet所有记录
	 */
	public List<LogStudyCVSet> getAllLogStudyCVSet();
	
}
