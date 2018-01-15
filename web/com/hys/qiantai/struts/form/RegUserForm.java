package com.hys.qiantai.struts.form;

import java.util.List;

import org.apache.struts.upload.FormFile;

import com.hys.exam.model.ExpertInfo;
import com.hys.framework.web.form.BaseForm;

public class RegUserForm extends BaseForm {
	/**
	 *  Lee 2016-12-05
	 */
	private static final long serialVersionUID = 8528979877396678303L;

	private Long id;
	private Integer grassroot;
	private Integer certificate_type;
	private String certificate_no;
	private String real_name;
	private Integer sex;
	private String account_name;
	private String account_password;
	private String mobile_phone;
	private String email;
	private Long region1;
	private Long region2;
	private Long rspropid;
	private Integer hospital_address;
	private String other_hospital_name;
	private Integer hospital_level;
	private String hospital_region;
	private Integer work_type;
	private Integer work_unit;
	private String work_id;
	private Integer xueke1;
	private Integer xueke2;
	private Integer xueke3;
	private Integer grade;
	private FormFile selImage;
	
	public FormFile getSelImage() {
		return selImage;
	}
	public void setSelImage(FormFile selImage) {
		this.selImage = selImage;
	}
	
	
	
	public Integer getGrassroot() {
		return grassroot;
	}
	public void setGrassroot(Integer grassroot) {
		this.grassroot = grassroot;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCertificate_type() {
		return certificate_type;
	}
	public void setCertificate_type(Integer certificate_type) {
		this.certificate_type = certificate_type;
	}
	public String getCertificate_no() {
		return certificate_no;
	}
	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getRegion1() {
		return region1;
	}
	public void setRegion1(Long region1) {
		this.region1 = region1;
	}
	public Long getRegion2() {
		return region2;
	}
	public void setRegion2(Long region2) {
		this.region2 = region2;
	}
	public Long getRspropid() {
		return rspropid;
	}
	public void setRspropid(Long rspropid) {
		this.rspropid = rspropid;
	}
	
	public Integer getHospital_address() {
		return hospital_address;
	}
	public void setHospital_address(Integer hospital_address) {
		this.hospital_address = hospital_address;
	}
	public String getOther_hospital_name() {
		return other_hospital_name;
	}
	public void setOther_hospital_name(String other_hospital_name) {
		this.other_hospital_name = other_hospital_name;
	}
	
	public Integer getHospital_level() {
		return hospital_level;
	}
	public void setHospital_level(Integer hospital_level) {
		this.hospital_level = hospital_level;
	}
	public String getHospital_region() {
		return hospital_region;
	}
	public void setHospital_region(String hospital_region) {
		this.hospital_region = hospital_region;
	}
	public Integer getWork_type() {
		return work_type;
	}
	public void setWork_type(Integer work_type) {
		this.work_type = work_type;
	}
	public Integer getWork_unit() {
		return work_unit;
	}
	public void setWork_unit(Integer work_unit) {
		this.work_unit = work_unit;
	}
	public String getWork_id() {
		return work_id;
	}
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	public Integer getXueke1() {
		return xueke1;
	}
	public void setXueke1(Integer xueke1) {
		this.xueke1 = xueke1;
	}
	public Integer getXueke2() {
		return xueke2;
	}
	public void setXueke2(Integer xueke2) {
		this.xueke2 = xueke2;
	}
	public Integer getXueke3() {
		return xueke3;
	}
	public void setXueke3(Integer xueke3) {
		this.xueke3 = xueke3;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
}
