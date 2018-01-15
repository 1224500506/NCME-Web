package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class Edition implements Serializable {

	/**
	 * Lee 2016-11-28
	 */
	private static final long serialVersionUID = 2809365696882071729L;
	
	private Long id;	
	
	private String name;
	
	private String title;
	
	private String kind;
	
	private Integer type;
	
	private Integer n_ContentSize;

	private CVSet cvSet;
	
	public CVSet getCvSet() {
		return cvSet;
	}

	public void setCvSet(CVSet cvSet) {
		this.cvSet = cvSet;
	}

	private List<ExCVSet> cvSetList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getN_ContentSize() {
		return n_ContentSize;
	}

	public void setN_ContentSize(Integer n_ContentSize) {
		this.n_ContentSize = n_ContentSize;
	}

	public List<ExCVSet> getCvSetList() {
		return cvSetList;
	}

	public void setCvSetList(List<ExCVSet> cvSetList) {
		this.cvSetList = cvSetList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
