package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.exam.model.CVSet;

/**
 * 
 * @author Tiger
 * @time 2017-1-10
 * Detail : Model of CV_SET_FAVORITES table.
 */

public class CVSetFavorites extends CVSet implements Serializable {

	private static final long serialVersionUID = 3299179869424516827L;

	//评分人ID
	private Long system_user_id;
	
	//项目ID
	private Long cv_set_id;
	
	//收藏时间
	private Date favorite_date;

	public Long getSystem_user_id() {
		return system_user_id;
	}

	public void setSystem_user_id(Long system_user_id) {
		this.system_user_id = system_user_id;
	}

	public Long getCv_set_id() {
		return cv_set_id;
	}

	public void setCv_set_id(Long cv_set_id) {
		this.cv_set_id = cv_set_id;
	}

	public Date getFavorite_date() {
		return favorite_date;
	}

	public void setFavorite_date(Date favorite_date) {
		this.favorite_date = favorite_date;
	}
}