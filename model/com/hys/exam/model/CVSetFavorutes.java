package com.hys.exam.model;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetFavorutes.java
 *     
 *     Description       :   项目收藏实体类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetFavorutes {

	//项目id
	private Long cvSetId;
	
	//用户id
	private Long systemUserId;
	
	//收藏时间
	private String favoriteDate;

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getFavoriteDate() {
		return favoriteDate;
	}

	public void setFavoriteDate(String favoriteDate) {
		this.favoriteDate = favoriteDate;
	}

}///*~
