package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CVSet implements Serializable {

    /**
     * boy
     */
    private static final long serialVersionUID = 3299179869424516827L;

    private Long id;

    private String name;

    private Integer forma;//项目类型 1 远程 , 3 面授

    private String code;

    private List<PropUnit> courseList;

    private List<UserImage> userImageList;

    private List<ExpertInfo> managerList;

    private List<ExpertInfo> teacherList;

    private List<ExpertInfo> otherTeacherList;

    private List<PeixunOrg> organizationList;
    
    private List<ExamProp> xueKeList;

	private Integer studentNum;

    private String introduction;

    private String announcement;

    private String file_path;

    private String knowledge_needed;

    private String knowledge_base;

    private String reference;

    private String reference_lately;

    private List<CVSchedule> CVScheduleList;

    private List<SystemSite> siteList;

    private String serial_number;

    private Integer level;

    private Double score;

    private String serial_number_str;//项目预览专用
    private String level_score_str;//项目预览专用
    private String start_end_date_str;//项目预览专用

    private String cost_sort;

    private String score_sort;

    private String recent_create;

    private String serverName;

    private Integer edtionId;

    private Integer propId;

    private String propName;

    private List<CV> cvList;// 课程列表

    private List<ExamProp> examPropList;//所属学科
    
    private List<CVSetScheduleFaceTeach> faceList;// 面授详情
    
    //学习进度
    private double studyProgress;
    
    //最近学习章节
    private String lastUnitName;
    
    //项目评价	add by han. 2017-01-26
    private Double critiqueScore1;
    private Double critiqueScore2;
    private Double critiqueScore3;
    private Double critiqueScore4;
    //书籍推荐
    private List<BaseProjectRefModel> refereeBookList;
    //指南共识
    private List<BaseProjectRefModel> knowledgeBaseList;
    //最新文献
    private List<BaseProjectRefModel> referencelatelyList;
    
    //0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
    private Integer cost_type;//0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
    //项目类型 0继续项目;1乡医培训
    private Integer cv_set_type;
    
    private boolean commentType;  //0 未评价 1,已评价
    
    private Long userProvinceCode;
    
	public boolean isCommentType() {
		return commentType;
	}

	public void setCommentType(boolean commentType) {
		this.commentType = commentType;
	}
  
    
    public Long getUserProvinceCode() {
		return userProvinceCode;
	}

	public void setUserProvinceCode(Long userProvinceCode) {
		this.userProvinceCode = userProvinceCode;
	}

	public String getSerial_number_str() {
		return serial_number_str;
	}

	public void setSerial_number_str(String serial_number_str) {
		this.serial_number_str = serial_number_str;
	}

	public String getLevel_score_str() {
		return level_score_str;
	}

	public void setLevel_score_str(String level_score_str) {
		this.level_score_str = level_score_str;
	}

	public String getStart_end_date_str() {
		return start_end_date_str;
	}

	public void setStart_end_date_str(String start_end_date_str) {
		this.start_end_date_str = start_end_date_str;
	}

	public Double getCritiqueScore1() {
		return critiqueScore1;
	}

	public void setCritiqueScore1(Double critiqueScore1) {
		this.critiqueScore1 = critiqueScore1;
	}

	public Double getCritiqueScore2() {
		return critiqueScore2;
	}

	public void setCritiqueScore2(Double critiqueScore2) {
		this.critiqueScore2 = critiqueScore2;
	}

	public Double getCritiqueScore3() {
		return critiqueScore3;
	}

	public void setCritiqueScore3(Double critiqueScore3) {
		this.critiqueScore3 = critiqueScore3;
	}

	public Double getCritiqueScore4() {
		return critiqueScore4;
	}

	public void setCritiqueScore4(Double critiqueScore4) {
		this.critiqueScore4 = critiqueScore4;
	}

	public String getLastUnitName() {
		return lastUnitName;
	}

	public void setLastUnitName(String lastUnitName) {
		this.lastUnitName = lastUnitName;
	}

	public double getStudyProgress() {
		return studyProgress;
	}

	public void setStudyProgress(double studyProgress) {
		this.studyProgress = studyProgress;
	}

	public List<ExamProp> getExamPropList() {
        return examPropList;
    }
    
    public List<CVSetScheduleFaceTeach> getFaceList() {
		return faceList;
	}

	public void setFaceList(List<CVSetScheduleFaceTeach> faceList) {
		this.faceList = faceList;
	}

	public void setExamPropList(List<ExamProp> examPropList) {
        this.examPropList = examPropList;
    }

    public List<CV> getCvList() {
        return cvList;
    }

    public void setCvList(List<CV> cvList) {
        this.cvList = cvList;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }
    
    public List<ExamProp> getXueKeList() {
		return xueKeList;
	}

	public void setXueKeList(List<ExamProp> xueKeList) {
		this.xueKeList = xueKeList;
	}

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public Integer getEdtionId() {
        return edtionId;
    }

    public void setEdtionId(Integer edtionId) {
        this.edtionId = edtionId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getRecent_create() {
        return recent_create;
    }

    public void setRecent_create(String recent_create) {
        this.recent_create = recent_create;
    }

    private Double cost;

    private Integer type;

    private Integer status;

    private String maker;

    private Date start_date;

    private Date end_date;

    private Date create_date;

    private Date deli_date;

    private Long break_days;

    private String sign; //标签

    private Long provinceId;

    private Long cityId;

    private String deli_man;

    private String opinion;

    private String opinionType;

    private String flag;

    private Integer studyTimes;

    private Double grade;

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Integer getStudyTimes() {
        return studyTimes;
    }

    public void setStudyTimes(Integer studyTimes) {
        this.studyTimes = studyTimes;
    }

    public String getScore_sort() {
        return score_sort;
    }

    public void setScore_sort(String score_sort) {
        this.score_sort = score_sort;
    }

    public String getCost_sort() {
        return cost_sort;
    }

    public void setCost_sort(String cost_sort) {
        this.cost_sort = cost_sort;
    }

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

    public Integer getForma() {
        return forma;
    }

    public void setForma(Integer forma) {
        this.forma = forma;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserImage> getUserImageList() {
        return userImageList;
    }

    public void setUserImageList(List<UserImage> userImageList) {
        this.userImageList = userImageList;
    }

    public List<ExpertInfo> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<ExpertInfo> managerList) {
        this.managerList = managerList;
    }

    public List<ExpertInfo> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<ExpertInfo> teacherList) {
        this.teacherList = teacherList;
    }

    public List<ExpertInfo> getOtherTeacherList() {
        return otherTeacherList;
    }

    public void setOtherTeacherList(List<ExpertInfo> otherTeacherList) {
        this.otherTeacherList = otherTeacherList;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getKnowledge_base() {
        return knowledge_base;
    }

    public void setKnowledge_base(String knowledge_base) {
        this.knowledge_base = knowledge_base;
    }

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference_lately() {
        return reference_lately;
    }

    public void setReference_lately(String reference_lately) {
        this.reference_lately = reference_lately;
    }

    public List<CVSchedule> getCVScheduleList() {
        return CVScheduleList;
    }

    public void setCVScheduleList(List<CVSchedule> cVScheduleList) {
        CVScheduleList = cVScheduleList;
    }

    public List<SystemSite> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<SystemSite> siteList) {
        this.siteList = siteList;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getBreak_days() {
        return break_days;
    }

    public void setBreak_days(Long break_days) {
        this.break_days = break_days;
    }

    public String getKnowledge_needed() {
        return knowledge_needed;
    }

    public void setKnowledge_needed(String knowledge_needed) {
        this.knowledge_needed = knowledge_needed;
    }

    public List<PropUnit> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<PropUnit> courseList) {
        this.courseList = courseList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer cVSetStatus) {
        this.status = cVSetStatus;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getDeli_man() {
        return deli_man;
    }

    public void setDeli_man(String deli_man) {
        this.deli_man = deli_man;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getDeli_date() {
        return deli_date;
    }

    public void setDeli_date(Date deli_date) {
        this.deli_date = deli_date;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getOpinionType() {
        return opinionType;
    }

    public void setOpinionType(String opinionType) {
        this.opinionType = opinionType;
    }

    public List<PeixunOrg> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<PeixunOrg> organizationList) {
        this.organizationList = organizationList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

	public List<BaseProjectRefModel> getRefereeBookList() {
		return refereeBookList;
	}

	public void setRefereeBookList(List<BaseProjectRefModel> refereeBookList) {
		this.refereeBookList = refereeBookList;
	}

	public List<BaseProjectRefModel> getKnowledgeBaseList() {
		return knowledgeBaseList;
	}

	public void setKnowledgeBaseList(List<BaseProjectRefModel> knowledgeBaseList) {
		this.knowledgeBaseList = knowledgeBaseList;
	}

	public List<BaseProjectRefModel> getReferencelatelyList() {
		return referencelatelyList;
	}

	public void setReferencelatelyList(List<BaseProjectRefModel> referencelatelyList) {
		this.referencelatelyList = referencelatelyList;
	}

	public Integer getCost_type() {
		return cost_type;
	}

	public void setCost_type(Integer cost_type) {
		this.cost_type = cost_type;
	}

	public Integer getCv_set_type() {
		return cv_set_type;
	}

	public void setCv_set_type(Integer cv_set_type) {
		this.cv_set_type = cv_set_type;
	}

    
}
