package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class SpecialUserImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -209303380883463528L;
	
	private List<PropUnit> majorPropList;
	
	public List<PropUnit> getMajorPropList() {
		return majorPropList;
	}

	public void setMajorPropList(List<PropUnit> majorPropList) {
		this.majorPropList = majorPropList;
	}

	public List<PropUnit> getDutyPropList() {
		return dutyPropList;
	}

	public void setDutyPropList(List<PropUnit> dutyPropList) {
		this.dutyPropList = dutyPropList;
	}

	private List<PropUnit> dutyPropList;
	
}
