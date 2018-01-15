package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class CVSetOrg  implements Serializable {
	
	PeixunOrg org;
	List<ExpertInfo> teacherList;
	List<CVSet> projectList;
	List<CVSet> faceList;
	
	
	public List<ExpertInfo> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<ExpertInfo> teacherList) {
		this.teacherList = teacherList;
	}
	public PeixunOrg getOrg() {
		return org;
	}
	public void setOrg(PeixunOrg org) {
		this.org = org;
	}
	public List<CVSet> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<CVSet> projectList) {
		this.projectList = projectList;
	}
	public List<CVSet> getFaceList() {
		return faceList;
	}
	public void setFaceList(List<CVSet> faceList) {
		this.faceList = faceList;
	}
	
}
