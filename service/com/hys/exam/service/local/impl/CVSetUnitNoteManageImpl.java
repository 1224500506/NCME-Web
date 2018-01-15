package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVSetUnitNoteManageDAO;
import com.hys.exam.model.CVSetUnitNote;
import com.hys.exam.service.local.CVSetUnitNoteManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitNoteManageImpl.java
 *     
 *     Description       :   单元笔记Service实现类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetUnitNoteManageImpl extends BaseMangerImpl implements CVSetUnitNoteManage{
	
	//单元笔记DAO
	private CVSetUnitNoteManageDAO localCVSetUnitNoteDAO;

	public CVSetUnitNoteManageDAO getLocalCVSetUnitNoteDAO() {
		return localCVSetUnitNoteDAO;
	}

	public void setLocalCVSetUnitNoteDAO(CVSetUnitNoteManageDAO localCVSetUnitNoteDAO) {
		this.localCVSetUnitNoteDAO = localCVSetUnitNoteDAO;
	}

	/**
	 * @author   张建国
	 * @time     2017-01-16
	 * @param    CVSetUnitNote
	 * @return   void
	 * 方法说明： 添加单元笔记
	 */
	@Override	
	public void addCVSetUnitNote(CVSetUnitNote note) {
		localCVSetUnitNoteDAO.addCVSetUnitNote(note);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   List<CVSetUnitNote>
	 * 方法说明： 查询单元笔记
	 */
	public List<CVSetUnitNote> queryUnitNote(CVSetUnitNote note){
		return localCVSetUnitNoteDAO.queryUnitNote(note);
	}

}
