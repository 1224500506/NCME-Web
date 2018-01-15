package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CvLiveSignk implements Serializable {

	/**
	 * boy
	 */
	private static final long serialVersionUID = 4599448644515104811L;
	
	private Long id;
	private Long user_id;//用户ID
	private String real_name;//真实姓名
	private String signk_code;//认证K值
	private Date create_time;//创建时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getSignk_code() {
		return signk_code;
	}
	public void setSignk_code(String signk_code) {
		this.signk_code = signk_code;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
