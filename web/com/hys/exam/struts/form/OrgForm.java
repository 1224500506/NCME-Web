package com.hys.exam.struts.form;


import com.hys.exam.model.PeixunOrg;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 form
 * 
 * 说明:
 */
public class OrgForm extends BaseForm{

	
	private static final long serialVersionUID = 189625609171665011L;
	
	private String method ;
	
	private PeixunOrg model = new PeixunOrg();

	public PeixunOrg getModel() {
		return model;
	}
	public void setModel(PeixunOrg model) {
		this.model = model;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
