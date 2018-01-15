package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVSetFacoritesManageDAO;
import com.hys.exam.model.CVSetFavorutes;
import com.hys.exam.service.local.CVSetFavoritesManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetFavoritesManageImpl.java
 *     
 *     Description       :   项目收藏Service实现类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetFavoritesManageImpl extends BaseMangerImpl implements CVSetFavoritesManage{
	
	private CVSetFacoritesManageDAO localCVSetFavoritesDAO;
	
	public CVSetFacoritesManageDAO getLocalCVSetFavoritesDAO() {
		return localCVSetFavoritesDAO;
	}

	public void setLocalCVSetFavoritesDAO(CVSetFacoritesManageDAO localCVSetFavoritesDAO) {
		this.localCVSetFavoritesDAO = localCVSetFavoritesDAO;
	}

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户添加项目收藏
	 */
	@Override
	public void addCVSetFavorites(CVSetFavorutes cvs) {
		 localCVSetFavoritesDAO.addCVSetFavorites(cvs);	
	}

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   void
	 * 方法说明： 用户取消项目收藏
	 */
	@Override
	public void deleteCVSetFavorites(CVSetFavorutes cvs) {
		localCVSetFavoritesDAO.deleteCVSetFavorites(cvs);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    CVSetFavorutes
	 * @return   List<CVSetFavorutes>
	 * 方法说明： 查询用户收藏项目
	 */
	@Override
	public List<CVSetFavorutes> searchCVSetFavorites(CVSetFavorutes cvs) {
		return localCVSetFavoritesDAO.searchCVSetFavorites(cvs);
	}

}
