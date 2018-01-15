package com.hys.exam.struts.form;


import org.apache.struts.upload.FormFile;

import com.hys.exam.model.Feedback;
import com.hys.framework.web.form.BaseForm;

public class FeedbackForm extends BaseForm{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8196468189393440849L;

	private String method ;
	
	private Feedback model = new Feedback();
	
	private FormFile matFile;

	private String cover;
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public FormFile getMatFile() {
		return matFile;
	}

	public void setMatFile(FormFile matFile) {
		this.matFile = matFile;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Feedback getModel() {
		return model;
	}

	public void setModel(Feedback model) {
		this.model = model;
	}

	

}
