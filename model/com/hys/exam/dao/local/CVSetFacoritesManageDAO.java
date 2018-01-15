package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSetFavorutes;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetFacoritesManageDAO.java
 *     
 *     Description       :   项目收藏DAO
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public interface CVSetFacoritesManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户添加项目收藏
	 */
	void addCVSetFavorites(CVSetFavorutes cvs);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户取消项目收藏
	 */
	void deleteCVSetFavorites(CVSetFavorutes cvs);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   List<CVSetFavorutes>
	 * 方法说明： 查询用户收藏项目
	 */
	List<CVSetFavorutes> searchCVSetFavorites(CVSetFavorutes cvs);
}
