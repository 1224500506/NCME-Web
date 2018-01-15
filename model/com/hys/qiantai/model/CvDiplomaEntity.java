package com.hys.qiantai.model;

import java.util.Date;

/**
 * 
 * 證書類
 * @author 程紅葉
 * 2017-02-18
 *
 */
public class CvDiplomaEntity {
	
	

	// id 
	private Integer id;
	
	private Integer cvSetId;
	//用户ID
	private Long system_user_id;
	// 身份证
	private String id_number;
	//用户姓名
	private String system_user_name;
	// 项目编号
	private String item_number;
	// 项目名称
	private String item_name;
	// 申请时间
	private String apply_time;
	// 证书编号
	private String diploma_number;
	// 二维码地址
	private String path_url;
	//是否删除
	private String is_delete;
	//项目登记
	private String item_grade;
	//项目类型
	private String item_type;
	//开始时间  
    private String begin_time;
    //结束时间
    private String end_time;
    
    // 分数
    private String item_score;
    
    
    
    
    
	public Integer getCvSetId() {
		return cvSetId;
	}
	public void setCvSetId(Integer cvSetId) {
		this.cvSetId = cvSetId;
	}
	public String getItem_score() {
		return item_score;
	}
	public void setItem_score(String item_score) {
		this.item_score = item_score;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public String getSystem_user_name() {
		return system_user_name;
	}
	public void setSystem_user_name(String system_user_name) {
		this.system_user_name = system_user_name;
	}
	public String getItem_number() {
		return item_number;
	}
	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getDiploma_number() {
		return diploma_number;
	}
	public void setDiploma_number(String diploma_number) {
		this.diploma_number = diploma_number;
	}
	public String getPath_url() {
		return path_url;
	}
	public void setPath_url(String path_url) {
		this.path_url = path_url;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getItem_grade() {
		return item_grade;
	}
	public void setItem_grade(String item_grade) {
		this.item_grade = item_grade;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}


	
	
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Long getSystem_user_id() {
		return system_user_id;
	}
	public void setSystem_user_id(Long system_user_id) {
		this.system_user_id = system_user_id;
	}


	
	
	
    
	
	
	
	
	
    
}
