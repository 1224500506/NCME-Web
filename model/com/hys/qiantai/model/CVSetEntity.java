package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CVSetEntity implements Serializable {

	/**
	 * Lee
	 */
	private static final long serialVersionUID = 3299179869424516827L;

	private Long userId;
	
	private Long cvSetId;
	
	private String content;
	
	private Integer favorite;
	
	private Integer status;
	
	private Double score;

	private String date;
	
	private String name;
	
	private String photo_url;

	private Date review_date;
	
	private String year;

	private	String month;
	
	private String day;
	
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

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}


	
	
}