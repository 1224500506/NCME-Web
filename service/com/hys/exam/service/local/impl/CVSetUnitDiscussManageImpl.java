package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVSetUnitDiscussManageDAO;
import com.hys.exam.model.CVSetUnitDiscuss;
import com.hys.exam.service.local.CVSetUnitDiscussManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitDiscussManageImpl.java
 *     
 *     Description       :   单元讨论Service实现类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetUnitDiscussManageImpl extends BaseMangerImpl implements CVSetUnitDiscussManage{
	
	//单元讨论DAO
	private CVSetUnitDiscussManageDAO localCVSetUnitDiscussDAO;

	public CVSetUnitDiscussManageDAO getLocalCVSetUnitDiscussDAO() {
		return localCVSetUnitDiscussDAO;
	}

	public void setLocalCVSetUnitDiscussDAO(CVSetUnitDiscussManageDAO localCVSetUnitDiscussDAO) {
		this.localCVSetUnitDiscussDAO = localCVSetUnitDiscussDAO;
	}

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitDiscuss
	 * @return   void
	 * 方法说明： 添加单元讨论
	 */
	@Override	
	public void addCVSetUnitDiscuss(CVSetUnitDiscuss discuss) {
		localCVSetUnitDiscussDAO.addCVSetUnitDiscuss(discuss);
	}
	
	/**
	 * @param    CVSetUnitDiscuss
	 * @return   List<CVSetUnitDiscuss>
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明： 查询单元讨论信息
	 */
	public List<CVSetUnitDiscuss> queryUnitTalk(CVSetUnitDiscuss discuss){
		return localCVSetUnitDiscussDAO.queryUnitTalk(discuss);
	}

	@Override
	public Long getCaseId(CVSetUnitDiscuss discuss) {
		return localCVSetUnitDiscussDAO.getCaseId(discuss);
	}

	
	@Override
	public long CountCVSetUnitDiscuss(CVSetUnitDiscuss discuss) {
		// TODO Auto-generated method stub
		return localCVSetUnitDiscussDAO.CountCVSetUnitDiscuss(discuss);
	}

}
