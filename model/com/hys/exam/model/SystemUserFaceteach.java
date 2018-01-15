package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author sunny
 *
 */
public class SystemUserFaceteach implements Serializable{
	
    private static final long serialVersionUID = 3299179869424516827L;
    
    private Long user_id; //用户id
    private Long fid;//面试id
    private Date entry_time;//报名时间
    
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
    
    
}
