package com.hys.qiantai.model;

import java.io.Serializable;
import java.util.Date;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitVideoCensus.java
 *     
 *     Description       :   视频实际学习时间记录实体类(单元)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-07-13              YXT                   Created
 ********************************************************************************
 */

public class LogStudyCVUnitVideoCensus implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6037093691357104174L;
	
	private Long id ;
    private Long systemUserId ;
    private Long cvSetId ;
    private Long cvId ;
    private Long cvUnitId ;
    private Date createDate ;
    private Long videoEchoLength ;
    private Long videoStartLength ;
    private Long videoEndLength ;
    private Long videoLength ;

    public LogStudyCVUnitVideoCensus() {
		super();
	}
    
	public LogStudyCVUnitVideoCensus(Long systemUserId, Long cvSetId, Long cvId, Long cvUnitId) {
		super();
		this.systemUserId = systemUserId;
		this.cvSetId = cvSetId;
		this.cvId = cvId;
		this.cvUnitId = cvUnitId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public Long getCvId() {
		return cvId;
	}

	public void setCvId(Long cvId) {
		this.cvId = cvId;
	}

	public Long getCvUnitId() {
		return cvUnitId;
	}

	public void setCvUnitId(Long cvUnitId) {
		this.cvUnitId = cvUnitId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getVideoEchoLength() {
		return videoEchoLength;
	}

	public void setVideoEchoLength(Long videoEchoLength) {
		this.videoEchoLength = videoEchoLength;
	}

	public Long getVideoStartLength() {
		return videoStartLength;
	}

	public void setVideoStartLength(Long videoStartLength) {
		this.videoStartLength = videoStartLength;
	}

	public Long getVideoEndLength() {
		return videoEndLength;
	}

	public void setVideoEndLength(Long videoEndLength) {
		this.videoEndLength = videoEndLength;
	}

	public Long getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(Long videoLength) {
		this.videoLength = videoLength;
	}

}
