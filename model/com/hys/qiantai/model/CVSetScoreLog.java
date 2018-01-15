package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.SystemUser;

/**
 * 
 * @author Han
 * @time	2017-02-07
 * model of cv_set_score_log table
 */

public class CVSetScoreLog extends SystemUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 项目ID
	private Long cvSetId;
	
	// 用户名称
	private String realName;
	
	// 用户ID
	private Long systemUserId;
	
	// Log日期
	private Date scoreDate;
	
	private String logDate;
	
	// 评论内容
	private String scoreContent;
	
	
	private Integer user_image;

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Date getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}

	public String getScoreContent() {
		return scoreContent;
	}

	public void setScoreContent(String scoreContent) {
		this.scoreContent = scoreContent;
	}

	public Integer getUser_image() {
		return user_image;
	}

	public void setUser_image(Integer user_image) {
		this.user_image = user_image;
	}


	
	
	
}