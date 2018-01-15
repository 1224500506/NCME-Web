package com.hys.exam.model;

public class ExCVSet extends CVSet{

	/**
	 * Lee 2016-11-28
	 */
	
	private static final long serialVersionUID = 2809365696882071729L;
	
	private Long ordernum;

	public Long getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}
	
	public ExCVSet() {}
	
	public ExCVSet(CVSet cvSet) {
		
		this.setId(cvSet.getId());
		this.setName(cvSet.getName());
		this.setCourseList(cvSet.getCourseList());
		this.setCode(cvSet.getCode());
		this.setUserImageList(cvSet.getUserImageList());//.get(0).getDepartmentPropList().get(0).getName());
		//this.set
		this.setManagerList(cvSet.getManagerList());
		//this.set
		this.setCreate_date(cvSet.getCreate_date());
		this.setStatus(cvSet.getStatus());
		this.setCreate_date(cvSet.getCreate_date());
		
	}
	
}
