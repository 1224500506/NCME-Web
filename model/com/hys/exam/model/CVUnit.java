package com.hys.exam.model;

import java.io.Serializable;

public class CVUnit implements Serializable {

    /**
     * boy
     * 
     */
    private static final long serialVersionUID = -5172346305325905061L;

    private Long id;

    private String name;

    private Integer type;//类型 1.理论讲解 2.主题讨论 3.随堂考试 4. 操作演示 5 扩展阅读 6.病例分享

    private Integer point;

    private Integer state;

    private String content;

    private Integer isBound;
    
    // 指标
    private Double  quota;
    
    // 是否完成指标0未完成1完成
    private Integer isquota; 
    

    private Integer logStatus; //学习记录状态：null、1、2
    private String  mediaType; //媒体类型（null为无类型,paper为试卷,talk为讨论点,bingli为病例,video为视频）
    private String  mediaId  ; //媒体的id就是富文本里的_url值
    private Integer videoPlayLength ;//视频观看长度    
    private Integer sequenceNum ;//单元顺序
    private String  logStudylastUpdateDate;//学习记录上次更新时间
 

    private Integer unitMakeType;
    
    // 课程ID	2017-07-24(图表统计自用字段--yxt)
    private Long cvId; 
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsBound() {
        return isBound;
    }

    public void setIsBound(Integer isBound) {
        this.isBound = isBound;
    }

	public Double getQuota() {
		return quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

	public Integer getIsquota() {
		return isquota;
	}

	public void setIsquota(Integer isquota) {
		this.isquota = isquota;
	}

	public CVUnit() {
		super();
	}

	public CVUnit(Long id) {
		super();
		this.id = id;
	}


	public Integer getUnitMakeType() {
		return unitMakeType;
	}

	public void setUnitMakeType(Integer unitMakeType) {
		this.unitMakeType = unitMakeType;
	}

	public Integer getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public Integer getVideoPlayLength() {
		return videoPlayLength;
	}
	public void setVideoPlayLength(Integer videoPlayLength) {
		this.videoPlayLength = videoPlayLength;
	}
	public Integer getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public String getLogStudylastUpdateDate() {
		return logStudylastUpdateDate;
	}
	public void setLogStudylastUpdateDate(String logStudylastUpdateDate) {
		this.logStudylastUpdateDate = logStudylastUpdateDate;
	}
	
	public Long getCvId() {
		return cvId;
	}

	public void setCvId(Long cvId) {
		this.cvId = cvId;
	}


}
