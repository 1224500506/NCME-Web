package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;

import com.hys.framework.web.form.BaseForm;

public class CVS_Form extends BaseForm {

	/**
	 * Ma
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Integer forma;
	
	private String userImage;
	
	private String code;
	
	private String manager;
	
	private String lessonTeacher;
	
	private String generalTeacher;
	
	private String introduction;
	
	private FormFile cover; 
	
	private String announcement;
	
	private String knowledge_needed;
	
	private String knowledge_base;
	
	private String reference;
	
	private String reference_lately;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getForma() {
		return forma;
	}

	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLessonTeacher() {
		return lessonTeacher;
	}

	public void setLessonTeacher(String lessonTeacher) {
		this.lessonTeacher = lessonTeacher;
	}

	public String getGeneralTeacher() {
		return generalTeacher;
	}

	public void setGeneralTeacher(String generalTeacher) {
		this.generalTeacher = generalTeacher;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getKnowledge_needed() {
		return knowledge_needed;
	}

	public void setKnowledge_needed(String knowledge_needed) {
		this.knowledge_needed = knowledge_needed;
	}

	public String getKnowledge_base() {
		return knowledge_base;
	}

	public void setKnowledge_base(String knowledge_base) {
		this.knowledge_base = knowledge_base;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference_lately() {
		return reference_lately;
	}

	public void setReference_lately(String reference_lately) {
		this.reference_lately = reference_lately;
	}

	public FormFile getCover() {
		return cover;
	}

	public void setCover(FormFile cover) {
		this.cover = cover;
	}

}
