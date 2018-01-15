package com.hys.exam.struts.form;

import java.util.List;

//import org.apache.struts.upload.FormFile;

import com.hys.exam.model.ExpertInfo;
//import com.hys.exam.model.PropUnit;
import com.hys.framework.web.form.BaseForm;

public class CVForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8528979877396678303L;

	private Long id;
	
	private String name;
	
	private String serial_number;
	
	//private List<PropUnit> courseList;
	
	private String brand;
	
	//private List<ExpertInfo> teacherList;
	
	//private List<ExpertInfo> otherTeacherList;
	
	private String introduction;
	
	private String announcement;
	
	private String file_path;
	
	private String creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	/*public List<ExpertInfo> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<ExpertInfo> teacherList) {
		this.teacherList = teacherList;
	}

	public List<ExpertInfo> getOtherTeacherList() {
		return otherTeacherList;
	}

	public void setOtherTeacherList(List<ExpertInfo> otherTeacherList) {
		this.otherTeacherList = otherTeacherList;
	}*/

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
