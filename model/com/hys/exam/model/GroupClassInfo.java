package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 标题：组课实体类
 * 
 * 作者：ZJG 2016-12-19
 * 
 * 描述：暂无
 * 
 * 说明: 用于组课功能
 */
public class GroupClassInfo implements Serializable{

	private static final long serialVersionUID = 470502570090795912L;

	//主键id
	private long id;
	
	//课程id
	private long classId;
	
	//课程名称
	private String className;
	
	//课程父级id
	private long classParentId;
	
	//课程父级id
	private String classParentName;
	
	//课程内容
	private String classContent;
	
	//课程状态 1表示正常 0表示弃用
	private int state;
		
	//课程创建日期
	private Date createDate;
	
	//模板类型
	private String templateType;
	
	//备注
	private String remark;
	
	//视频完成指标
	private int compIndex;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getClassParentId() {
		return classParentId;
	}

	public void setClassParentId(long classParentId) {
		this.classParentId = classParentId;
	}

	public String getClassParentName() {
		return classParentName;
	}

	public void setClassParentName(String classParentName) {
		this.classParentName = classParentName;
	}

	public String getClassContent() {
		return classContent;
	}

	public void setClassContent(String classContent) {
		this.classContent = classContent;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getCompIndex() {
		return compIndex;
	}

	public void setCompIndex(int compIndex) {
		this.compIndex = compIndex;
	}

}
