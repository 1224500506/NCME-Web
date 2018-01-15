package com.hys.exam.model;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitNote.java
 *     
 *     Description       :   学习笔记
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetUnitNote {

	//主键Id
	private Long id;
	
	//项目Id
	private Long cvSetId;
	
	//单元Id
	private Long cvUnitId;
	
	//笔记人Id
	private Long systemUserId;
	
	//记录时间
	private String noteDate;
	
	//笔记内容
	private String noteContent;
	
	//1.普通讨论 2.话题讨论 3.病例讨论
	private Long note_type;
	
	//user real_name
	private String real_name;
	
	//user Photo
	private String user_image;
	
	//笔记状态 1 -私密 2 -公开
	private int status;
	
	//性别
	private Integer sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
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

	public String getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getNote_type() {
		return note_type;
	}

	public void setNote_type(Long note_type) {
		this.note_type = note_type;
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
