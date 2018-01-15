package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

public class CvLiveCoursewareStudy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5210574786076713648L;
	
	private Integer id;
	private Long userId;//学生ID
	private String coursewareId;//课件ID
	private String username;//用户名称
	private Long joinTime;//加入直播时间
	private Long leaveTime;//离开直播时间
	private String duration;//学员观看时长
	private String ip;
	private Integer device;//终端类型：0-PC;1-Mac;2-Linux;4-Ipad;8-Iphone;16-AndriodPad;32-AndriodPhone;132-IPad(PlayerSDK);136-IPhone(PlayerSDK);144-AndriodPad(PlayerSDK);256-AndriodPhone(PlayerSDK);237-移动设备;
	private Date recordDate;//最后一次记录时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCoursewareId() {
		return coursewareId;
	}
	public void setCoursewareId(String coursewareId) {
		this.coursewareId = coursewareId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

}
