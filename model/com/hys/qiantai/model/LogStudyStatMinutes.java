package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hys.exam.model.CVUnit;

/**
 * 
 * @author	Han
 * @time	2017-01-12
 * 
 */
public class LogStudyStatMinutes implements Serializable {

	public String d;
	public Long m;
	private int a;
	private int b;
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public Long getM() {
		return m;
	}
	public void setM(Long m) {
		this.m = m;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
		
}
