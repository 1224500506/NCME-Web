package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSetFavorutes;
import com.hys.framework.service.BaseService;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetFavoritesManage.java
 *     
 *     Description       :   项目收藏Service接口
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public interface CVSetFavoritesManage extends BaseService {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户添加项目收藏
	 */
	public void addCVSetFavorites(CVSetFavorutes cvs);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户取消项目收藏
	 */
	public void deleteCVSetFavorites(CVSetFavorutes cvs);
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   List<CVSetFavorutes>
	 * 方法说明： 查询用户收藏项目
	 */
	public List<CVSetFavorutes> searchCVSetFavorites(CVSetFavorutes cvs);
}
