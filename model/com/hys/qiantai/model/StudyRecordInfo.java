package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;

public class StudyRecordInfo implements Serializable {

	private static final long serialVersionUID = 3299179869424516827L;

	private Long userId;
	
	private Long cvSetId;
	
	private Integer favorite;
		
	private Double score;
	
	private String year;

	private	String month;
	
	private String day;
	
	private String name;
	
	private String file_path;

	private Integer level;

	private String sign;
	
	private Long status;

	private Date startDate;
	
	private Date endDate;
	
	private String expertName;

	private String code;
	
	private String orgName;
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public Date getEndDate() {
		return startDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
