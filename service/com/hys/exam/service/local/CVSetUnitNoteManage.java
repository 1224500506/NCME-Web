package com.hys.exam.service.local;

import java.util.List;
import com.hys.exam.model.CVSetUnitNote;
import com.hys.framework.service.BaseService;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitNoteManage.java
 *     
 *     Description       :   单元讨论Service接口
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public interface CVSetUnitNoteManage extends BaseService {

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	public void addCVSetUnitNote(CVSetUnitNote note);
	
	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   List<CVSetUnitNote>
	 * 方法说明： 查询单元笔记
	 */
	public List<CVSetUnitNote> queryUnitNote(CVSetUnitNote note);
	
}
