package com.hys.exam.model.systemQuery;

import com.hys.exam.model.system.SystemCardType;

/**
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-17
*/
@SuppressWarnings("serial")
public class SystemCardTypeQuery extends SystemCardType {

	//总数
	private Integer allNum;
	
	//已使用
	private Integer userdNum;
	
	//剩余数量
	private Integer remainNum;
	
	private Long cvSetId;//项目ID--taoliang
	private Integer use_status;//标识改卡对应的项目是否已经使用过了该卡 【0未使用 1已使用】---taoliang
	private Integer level;//项目等级--taoliang
	private Double score;//项目学分--taoliang
	private String name;//项目名称--taoliang
	private String payDate;//支付时间--taoliang
	

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getUse_status() {
		return use_status;
	}

	public void setUse_status(Integer use_status) {
		this.use_status = use_status;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getUserdNum() {
		return userdNum;
	}

	public void setUserdNum(Integer userdNum) {
		this.userdNum = userdNum;
	}

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

}


