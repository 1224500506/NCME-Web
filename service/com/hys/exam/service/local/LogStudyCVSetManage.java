package com.hys.exam.service.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.CVUnit;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.framework.service.BaseService;
import com.hys.exam.model.LogStudyCVUnit;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVSetManage.java
 *     
 *     Description       :   学习记录(项目)Service接口
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public interface LogStudyCVSetManage extends BaseService {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	public void addLogStudyCVSet(LogStudyCVSet log);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 修改学习记录(项目)
	 */
	public void updLogStudyCVSet(LogStudyCVSet log);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(项目)
	 */
	public List<LogStudyCVSet> searchLogStudyCVSet(LogStudyCVSet log);
	
	/**
     * @author   张建国
     * @return   String
     * @param    LogStudyCVSet
     * @time     2017-01-16
     * 方法说明： 统计指定项目的学习单元经历
     */
	public Map<String,Object> tongjiUnitStudy(LogStudyCVSet log);
	
	/**
     * @author   张建国
     * @return   List
     * @param    Long
     * @time     2017-02-15
     * 方法说明：根据项目Id查询项目下的单元集合
     */
	public List<CVUnit> queryCVUnitByProjectId(String code,Long projectId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-15
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 根据条件查询学习记录(单元)
	 */
	public List<LogStudyCVUnit> searchLogStudyCVUnit(String code,Long projectId,Long userId);
	
	
	
	/**
	 * 
	 * (通过cvsetid 删除对应的学习记录);
	 * 方法名：delLogStudy
	 * 创建人：程宏业
	 * 时间：2017-2-28-上午11:11:42 
	 * 手机:15210211487
	 * @param cv_setId void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delLogStudy(Long cv_setId,Long userid);
	
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
