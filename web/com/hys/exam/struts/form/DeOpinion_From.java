package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;

import com.hys.framework.web.form.BaseForm;

public class DeOpinion_From extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1785852375250450009L;
	private Integer deId;
	private int qq;
	private String email;	
	private String mobil_phone;
	private  String opinion;
	private  String image_path;

	public Integer getDeId() {
		return deId;
	}
	public void setDeId(Integer deId) {
		this.deId = deId;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobil_phone() {
		return mobil_phone;
	}
	public void setMobil_phone(String mobil_phone) {
		this.mobil_phone = mobil_phone;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	
}
