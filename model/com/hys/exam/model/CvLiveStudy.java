package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CvLiveStudy implements Serializable {

	private static final long serialVersionUID = -4329091807921776017L;
	
	private Long id;
	private Long user_id;//学生ID 如果进入直播没有传值，则展示互动生成
	private String class_no;//直播间ID
	private String nickname;//账户名称
	private String mobile;
	private Long joinTime;//加入直播时间
	private Long leaveTime;//离开直播时间
	private String ip;
	private Integer device;//设备类型 设备类型 0  PC 客户端  1  PC Web 端  2  PC Web 端
	//private Integer live_actual_length;//直播有效时长	
	private Integer useful_length;//学员学习时长
	private String company;
	private String area;
	private String name;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Long joinTime) {
		this.joinTime = joinTime;
	}
	public Long getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	/*public Integer getLive_actual_length() {
		return live_actual_length;
	}
	public void setLive_actual_length(Integer live_actual_length) {
		this.live_actual_length = live_actual_length;
	}*/
	public Integer getUseful_length() {
		return useful_length;
	}
	public void setUseful_length(Integer useful_length) {
		this.useful_length = useful_length;
	}

}
