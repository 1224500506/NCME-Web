package com.hys.exam.model;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitDiscuss.java
 *     
 *     Description       :   单元讨论
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetUnitDiscuss{

	//主键Id
	private Long id;
	
	//项目Id
	private Long cvsetId;
	
	//单元Id
	private Long cvUnitId;
	
	//发言人Id
	private Long systemUserId;
	
	//回复人Id
	private Long discussId;
	
	//发言时间
	private String discussDate;
	
	//发言内容
	private String discussContent;
	
	//1.普通讨论 2.话题讨论 3.病例讨论
	private Long discuss_type;
	
	//病例ID
	private Long case_id;
	//user real_name
	private String real_name;
	//user Photo
	private String user_image;
	//性别
	private Integer sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCvsetId() {
		return cvsetId;
	}

	public void setCvsetId(Long cvsetId) {
		this.cvsetId = cvsetId;
	}

	public Long getCvUnitId() {
		return cvUnitId;
	}

	public void setCvUnitId(Long cvUnitId) {
		this.cvUnitId = cvUnitId;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Long getDiscussId() {
		return discussId;
	}

	public void setDiscussId(Long discussId) {
		this.discussId = discussId;
	}

	public String getDiscussDate() {
		return discussDate;
	}

	public void setDiscussDate(String discussDate) {
		this.discussDate = discussDate;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public Long getDiscuss_type() {
		return discuss_type;
	}

	public void setDiscuss_type(Long discuss_type) {
		this.discuss_type = discuss_type;
	}

	public Long getCase_id() {
		return case_id;
	}

	public void setCase_id(Long case_id) {
		this.case_id = case_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	
	
	
	
}///*~
