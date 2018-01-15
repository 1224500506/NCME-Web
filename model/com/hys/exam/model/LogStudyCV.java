package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * @author taoliang
 * @desc 课程学习记录表
 */

public class LogStudyCV implements Serializable  {

	private static final long serialVersionUID = -4748054475442599061L;
	
	//主键Id
	private Long id;
	//用户Id
	private Long systemUserId;
	//项目Id
	private Long cvSetId;
	//课程Id
	private Long cvId;
    //完成状态  1 -未完成 2 -已完成
	private int state;
	//最后修改时间
	private String lastUpdateDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSystemUserId() {
		return systemUserId;
	}
	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}
	public Long getCvSetId() {
		return cvSetId;
	}
	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}
	public Long getCvId() {
		return cvId;
	}
	public void setCvId(Long cvId) {
		this.cvId = cvId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
