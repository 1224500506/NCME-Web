package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSetUnitNote;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitNoteManageDAO.java
 *     
 *     Description       :   单元笔记
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public interface CVSetUnitNoteManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   void
	 * 方法说明： 添加单元笔记
	 */
	void addCVSetUnitNote(CVSetUnitNote note);
	
	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   List<CVSetUnitNote>
	 * 方法说明： 查询单元笔记
	 */
	List<CVSetUnitNote> queryUnitNote(CVSetUnitNote note);

}
