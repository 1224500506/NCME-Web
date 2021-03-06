/**
 *
 * <p>学习卡生成记录</p>
 * @author chenlaibin
 * @version 1.0 2014-3-14
 */

package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;

@SuppressWarnings("serial")
public class SystemCardCreateRecord extends BaseObject{

	private Long id;
	
	private String cardStartNumber;		//开始卡号
	
	private String cardEndNumber;		//结束卡号
	
	private Integer cardSum;				//卡数量
	
	private Integer cardUserdSum;		//已使用数量
	
	private Date createDate;			//生成日期
	
	private String description;			//描述

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardStartNumber() {
		return cardStartNumber;
	}

	public void setCardStartNumber(String cardStartNumber) {
		this.cardStartNumber = cardStartNumber;
	}

	public String getCardEndNumber() {
		return cardEndNumber;
	}

	public void setCardEndNumber(String cardEndNumber) {
		this.cardEndNumber = cardEndNumber;
	}

	public Integer getCardSum() {
		return cardSum;
	}

	public void setCardSum(Integer cardSum) {
		this.cardSum = cardSum;
	}

	public Integer getCardUserdSum() {
		return cardUserdSum;
	}

	public void setCardUserdSum(Integer cardUserdSum) {
		this.cardUserdSum = cardUserdSum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}


