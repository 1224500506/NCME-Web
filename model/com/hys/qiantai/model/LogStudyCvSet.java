package com.hys.qiantai.model;

import com.hys.exam.model.CVSet;

import java.io.Serializable;
import java.util.Date;
import com.hys.exam.common.util.DateUtil;

/**
 * 
 * @author Tiger
 * @time 2017-1-10
 * Detail : Model of LOG_STUDY_CV_SET.
 */

public class LogStudyCvSet extends CVSet implements Serializable {

	private static final long serialVersionUID = 3299179869424516827L;

	
	Long log_id;
	
	//用户ID
	private Long system_user_id;
	
	//站点ID
	private Long site_id;
	
	//项目ID
	private Long cv_set_id;
	/**
	 * 所有单元完成 
	 * 1 ：学习中
	 * 2： 已完成
	 */
	private Integer state;
	
	/**
	 * 是否申请学分
	 * 1:未申请 
	 * 2:已申请
	 */
	private Integer is_apply_credit;
	
	//最后更新时间
	private Date last_update_date;

	private String lastUpdateDateYear;
	
	//申请学分时间
	private Date apply_date;

	//查询用时间 2017-01-12 han
	private Integer sYear;

	private Integer sMonth;
	
// 完成状态
	// 1,完成中 2,已完成
	private Integer fstate;
	
	// 百分比
	private  double percentage;
	
	//项目状态。远程项目 剩余XX天；面授项目：未开始 面授中 已结束 ；直播课程  ：未开始”、“正在直播”
	private String statusStudy;

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Integer getFstate() {
		return fstate;
	}

	public void setFstate(Integer fstate) {
		this.fstate = fstate;
	}

	public String getLastUpdateDateYear() {
		return lastUpdateDateYear;
	}

	public void setLastUpdateDateYear(String lastUpdateDateYear) {
		this.lastUpdateDateYear = lastUpdateDateYear;
	}

	public Integer getsYear() {
		return sYear;
	}

	public void setsYear(Integer sYear) {
		this.sYear = sYear;
	}

	public Integer getsMonth() {
		return sMonth;
	}

	public void setsMonth(Integer sMonth) {
		this.sMonth = sMonth;
	}

	public Long getSystem_user_id() {
		return system_user_id;
	}

	public void setSystem_user_id(Long system_user_id) {
		this.system_user_id = system_user_id;
	}

	public Long getSite_id() {
		return site_id;
	}

	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}

	public Long getCv_set_id() {
		return cv_set_id;
	}

	public void setCv_set_id(Long cv_set_id) {
		this.cv_set_id = cv_set_id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIs_apply_credit() {
		return is_apply_credit;
	}

	public void setIs_apply_credit(Integer is_apply_credit) {
		this.is_apply_credit = is_apply_credit;
	}

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
		this.lastUpdateDateYear = DateUtil.format(last_update_date, DateUtil.FORMAT_YEAR);
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public Long getLog_id() {
		return log_id;
	}

	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}

	public String getStatusStudy() {
		return statusStudy;
	}

	public void setStatusStudy(String statusStudy) {
		this.statusStudy = statusStudy;
	}	

	
}