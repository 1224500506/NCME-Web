package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

public class CvLiveSignup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216861359069191127L;
	private Long ID;
	private Long cvsetid;
	private Date StartDate;
	private Long  UserId;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	public Long getCvsetid() {
		return cvsetid;
	}
	public void setCvsetid(Long cvsetid) {
		this.cvsetid = cvsetid;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	
	

}
