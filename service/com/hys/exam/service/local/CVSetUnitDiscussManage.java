package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSetUnitDiscuss;
import com.hys.framework.service.BaseService;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitDiscussManage.java
 *     
 *     Description       :   单元讨论Service接口
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public interface CVSetUnitDiscussManage extends BaseService {

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitDiscuss
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	public void addCVSetUnitDiscuss(CVSetUnitDiscuss discuss);
	
	/**
	 * @param    CVSetUnitDiscuss
	 * @return   List<CVSetUnitDiscuss>
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明： 查询单元讨论信息
	 */
	public List<CVSetUnitDiscuss> queryUnitTalk(CVSetUnitDiscuss discuss);
	/**
	 * @author M
	 * @param cvsetId
	 * @param unitId
	 * @return caseId
	 * @description get caseId with 项目id and 单元id
	 */
	public Long getCaseId(CVSetUnitDiscuss discuss);
	
	
	/**
	 * 
	 * 根据用户ID和讨论的类型统计讨论的次数
	 * 方法名：CountCVSetUnitDiscuss
	 * 创建人：程宏业
	 * 时间：2017-3-6-上午10:48:20 
	 * 手机:15210211487
	 * @param discuss
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	
	public long CountCVSetUnitDiscuss(CVSetUnitDiscuss discuss);
	
	
	
	
	
	
}
