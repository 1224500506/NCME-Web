package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.qiantai.model.CVSetEntity;

public class SystemCard extends CVSetEntity implements Serializable  {

	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	private Long ID;
	private String CARD_NUMBER;
	private String CARD_PASSWORD;
	private Integer CARD_TYPE;
	private Date IMPORT_DATE;
	private Date USEFUL_DATE;
	private Integer ISNOT_BIND;
	private Integer FACE_VALUE;
	private Integer CREATE_USER_ID;
	private Date CREATE_DATE;
	private Integer STATUS;
	private Integer LAST_UPDATE_USER_ID;
	private Date LAST_UPDATE_DATE;
	private Double BALANCE;
	private Integer ORDER_ID;
	private Integer ISSELLED;
	private Integer SELL_STYLE;
	private Integer COST;
	


	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getCARD_NUMBER() {
		return CARD_NUMBER;
	}

	public void setCARD_NUMBER(String cARD_NUMBER) {
		CARD_NUMBER = cARD_NUMBER;
	}

	public String getCARD_PASSWORD() {
		return CARD_PASSWORD;
	}

	public void setCARD_PASSWORD(String cARD_PASSWORD) {
		CARD_PASSWORD = cARD_PASSWORD;
	}

	public Integer getCARD_TYPE() {
		return CARD_TYPE;
	}

	public void setCARD_TYPE(Integer cARD_TYPE) {
		CARD_TYPE = cARD_TYPE;
	}

	public Date getIMPORT_DATE() {
		return IMPORT_DATE;
	}

	public void setIMPORT_DATE(Date iMPORT_DATE) {
		IMPORT_DATE = iMPORT_DATE;
	}

	public Date getUSEFUL_DATE() {
		return USEFUL_DATE;
	}

	public void setUSEFUL_DATE(Date uSEFUL_DATE) {
		USEFUL_DATE = uSEFUL_DATE;
	}

	public Integer getISNOT_BIND() {
		return ISNOT_BIND;
	}

	public void setISNOT_BIND(Integer iSNOT_BIND) {
		ISNOT_BIND = iSNOT_BIND;
	}

	public Integer getFACE_VALUE() {
		return FACE_VALUE;
	}

	public void setFACE_VALUE(Integer fACE_VALUE) {
		FACE_VALUE = fACE_VALUE;
	}

	public Integer getCREATE_USER_ID() {
		return CREATE_USER_ID;
	}

	public void setCREATE_USER_ID(Integer cREATE_USER_ID) {
		CREATE_USER_ID = cREATE_USER_ID;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public Integer getLAST_UPDATE_USER_ID() {
		return LAST_UPDATE_USER_ID;
	}

	public void setLAST_UPDATE_USER_ID(Integer lAST_UPDATE_USER_ID) {
		LAST_UPDATE_USER_ID = lAST_UPDATE_USER_ID;
	}

	public Date getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}

	public void setLAST_UPDATE_DATE(Date lAST_UPDATE_DATE) {
		LAST_UPDATE_DATE = lAST_UPDATE_DATE;
	}

	public Double getBALANCE() {
		return BALANCE;
	}

	public void setBALANCE(Double bALANCE) {
		BALANCE = bALANCE;
	}

	public Integer getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(Integer oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}

	public Integer getISSELLED() {
		return ISSELLED;
	}

	public void setISSELLED(Integer iSSELLED) {
		ISSELLED = iSSELLED;
	}

	public Integer getSELL_STYLE() {
		return SELL_STYLE;
	}

	public void setSELL_STYLE(Integer sELL_STYLE) {
		SELL_STYLE = sELL_STYLE;
	}

	public Integer getCOST() {
		return COST;
	}

	public void setCOST(Integer cOST) {
		COST = cOST;
	}

}
