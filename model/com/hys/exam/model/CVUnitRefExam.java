package com.hys.exam.model;

import java.io.Serializable;

public class CVUnitRefExam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long unitId;
	private Long testpaperId;
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public Long getTestpaperId() {
		return testpaperId;
	}
	public void setTestpaperId(Long testpaperId) {
		this.testpaperId = testpaperId;
	}
}
