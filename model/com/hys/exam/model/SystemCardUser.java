package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

/***
 * 
 * 
 * SystemCardUser
 * 创建人:chy
 * 时间：2017-4-17-下午5:19:55 
 * @version 1.0.0
 *
 */
public class SystemCardUser implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer CARD_ID;
	private Integer USER_ID;
	private Integer SITE_ID;
	private Date BIND_DATE;

	public Integer getCARD_ID() {
		return CARD_ID;
	}

	public void setCARD_ID(Integer cARD_ID) {
		CARD_ID = cARD_ID;
	}

	public Integer getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(Integer uSER_ID) {
		USER_ID = uSER_ID;
	}

	public Integer getSITE_ID() {
		return SITE_ID;
	}

	public void setSITE_ID(Integer sITE_ID) {
		SITE_ID = sITE_ID;
	}

	public Date getBIND_DATE() {
		return BIND_DATE;
	}

	public void setBIND_DATE(Date bIND_DATE) {
		BIND_DATE = bIND_DATE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	

	
	

}
