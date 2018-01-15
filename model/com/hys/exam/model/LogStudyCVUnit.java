package com.hys.exam.model;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnit.java
 *     
 *     Description       :   学习记录实体类(单元)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-16                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVUnit {

	//主键Id
	private Long id;
	
	//项目Id
	private Long logStudyCvSetId;
	
	//用户Id
	private Long systemUserId;
	
	//课程Id
	private Long cvId;
	
	//单元Id
	private Long cvUnitId;
	
    //完成状态  1 -未完成 2 -已完成
	private int status;
	
	//最后修改时间
	private String lastUpdateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogStudyCvSetId() {
		return logStudyCvSetId;
	}

	public void setLogStudyCvSetId(Long logStudyCvSetId) {
		this.logStudyCvSetId = logStudyCvSetId;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Long getCvId() {
		return cvId;
	}

	public void setCvId(Long cvId) {
		this.cvId = cvId;
	}

	public Long getCvUnitId() {
		return cvUnitId;
	}

	public void setCvUnitId(Long cvUnitId) {
		this.cvUnitId = cvUnitId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
}///*~
