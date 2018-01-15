package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Sentence implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6244319531794294629L;

    private Long id;
    private String title;
    private Integer type;
    private Date deli_date;
    private Integer state;
    private Integer ordernum;
    private String content;
    private String source;
    private List<SystemSite> siteList;
    private Long site_id;
    private String serverName;
	private String provinceCode;
	private String provinceName;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDeli_date() {
        return deli_date;
    }

    public void setDeli_date(Date deli_date) {
        this.deli_date = deli_date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SystemSite> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<SystemSite> siteList) {
        this.siteList = siteList;
    }

    public Long getSite_id() {
        return site_id;
    }

    public void setSite_id(Long site_id) {
        this.site_id = site_id;
    }

}
