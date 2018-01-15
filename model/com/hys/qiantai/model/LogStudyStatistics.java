package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hys.exam.model.CVUnit;

/**
 * 
 * @author	Han
 * @time	2017-01-12
 * 
 */
public class LogStudyStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8043557121580240548L;

	// 用户ID
	private Long userId;
	// CVSetID
	private Long cvSetId;
	
	// 查询时间
	private Integer year;
	private Integer month;
	
	private Date startDate;
	private Date endDate;
	
	//学习耗时统计
	private long time_consuming ;

	//视频时长总计
	private long time_consuming_all ;
	
	// 百分比
	private  double percentage;
	
	
	
	
	
	
	
	
	/**
	 * 查询结果
	 */
	private Integer units;
	private Integer points;
	private Integer logUnits;
	private Integer completedLogUnits;
	private Integer cvs;
	private Integer completedCVs;
	private Integer notes;
	private Integer discuss;
	
/*	public class statMinute implements Serializable {
		public String d;
		public Long m;
		public String getD() {
			return d;
		}
		public void setD(String d) {
			this.d = d;
		}
		public Long getM() {
			return m;
		}
		public void setM(Long m) {
			this.m = m;
		}
		
	}*/
	
	
	
	
	private List<LogStudyStatMinutes> minutes;
	
	

	public long getTime_consuming() {
		return time_consuming;
	}

	public void setTime_consuming(long time_consuming) {
		this.time_consuming = time_consuming;
	}

	public long getTime_consuming_all() {
		return time_consuming_all;
	}

	public void setTime_consuming_all(long time_consuming_all) {
		this.time_consuming_all = time_consuming_all;
	}

	private List<CVUnit> lastUnits;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getLogUnits() {
		return logUnits;
	}

	public void setLogUnits(Integer logUnits) {
		this.logUnits = logUnits;
	}

	public Integer getCompletedLogUnits() {
		return completedLogUnits;
	}

	public void setCompletedLogUnits(Integer completedLogUnits) {
		this.completedLogUnits = completedLogUnits;
	}

	public Integer getCvs() {
		return cvs;
	}

	public void setCvs(Integer cvs) {
		this.cvs = cvs;
	}

	public Integer getCompletedCVs() {
		return completedCVs;
	}

	public void setCompletedCVs(Integer completedCVs) {
		this.completedCVs = completedCVs;
	}

	public List<LogStudyStatMinutes> getMinutes() {
		return minutes;
	}

	public void setMinutes(List<LogStudyStatMinutes> minutes) {
		this.minutes = minutes;
	}

	public Integer getNotes() {
		return notes;
	}

	public void setNotes(Integer notes) {
		this.notes = notes;
	}

	public Integer getDiscuss() {
		return discuss;
	}

	public void setDiscuss(Integer discuss) {
		this.discuss = discuss;
	}

	public List<CVUnit> getLastUnits() {
		return lastUnits;
	}

	public void setLastUnits(List<CVUnit> lastUnits) {
		this.lastUnits = lastUnits;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}


	
	
}
