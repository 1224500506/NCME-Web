package com.hys.exam.dao.local;

import java.util.List;

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
 *     File Name         :   CVSetUnitDiscussManageDAO.java
 *     
 *     Description       :   单元讨论
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public interface CVSetUnitDiscussManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitDiscuss
	 * @return   void
	 * 方法说明： 添加单元讨论
	 */
	void addCVSetUnitDiscuss(CVSetUnitDiscuss discuss);

	/**
	 * @param    CVSetUnitDiscuss
	 * @return   List<CVSetUnitDiscuss>
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明： 查询单元讨论信息
	 */
	List<CVSetUnitDiscuss> queryUnitTalk(CVSetUnitDiscuss discuss);
	

	Long getCaseId(CVSetUnitDiscuss discuss);
	
	/**
	 * 统计单元讨论的发言次数
	 * 方法名：CountCVSetUnitDiscuss
	 * 创建人：程宏业
	 * 时间：2017-3-6-上午10:52:21 
	 * 手机:15210211487
	 * @param discuss
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public long CountCVSetUnitDiscuss(CVSetUnitDiscuss discuss);
	
	
	/**
	 * 
	 * (查询单元)
	 * 方法名：getCvUnit
	 * 创建人：程宏业
	 * 时间：2017-3-6-下午2:51:37 
	 * 手机:15210211487
	 * @param id
	 * @return CVUnit
	 * @exception 
	 * @since  1.0.0
	 */
	public CVUnit getCvUnit(Long id);
	
	
	
	
	
	
	
	
}
