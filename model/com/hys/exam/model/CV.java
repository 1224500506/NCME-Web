package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class CV implements Serializable {

    /**
     * boy
     */
    private static final long serialVersionUID = 4599448644515104811L;

    private Long id;

    private String name;

    private String serial_number;

    private List<PropUnit> courseList;

    private String brand;
    
    private String name2;//项目名
    
    private String code;//项目编号

    private List<ExpertInfo> teacherList;

    private List<ExpertInfo> otherTeacherList;
    
    private int number;//正在看直播人数
    
    private int number3;//已学人数
    
    private int number4;//报名人数

    private String introduction;

    private String announcement;

    private String file_path;

    private String creator;

    private List<CVUnit> unitList;

    private List<PropUnit> usingItem;

    private Date create_date;

    private Integer edtionId;

    private String serverName;

    private Integer status;

    private String propName;

    private Integer type;// 直播状态  1.正在直播  2.即将开课  3.已经结束  4.观看回放 5.未完成转码

    private Date startDate;

    private Date endDate;
    
    private Integer cv_type ; //0点播，1面授，2直播（默认的老课程都是点播）
    
    //0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
    private Integer cost_type;//0 免费，1 学习卡 ，2 收费(默认都为免费)--tl 
    private Double cost;//项目价格--tl
    
    private Long userProvinceCode;
    
 
    
    
    



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public int getNumber4() {
		return number4;
	}

	public void setNumber4(int number4) {
		this.number4 = number4;
	}

	public int getNumber3() {
		return number3;
	}

	public void setNumber3(int number3) {
		this.number3 = number3;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getUserProvinceCode() {
		return userProvinceCode;
	}

	public void setUserProvinceCode(Long userProvinceCode) {
		this.userProvinceCode = userProvinceCode;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getEdtionId() {
        return edtionId;
    }

    public void setEdtionId(Integer edtionId) {
        this.edtionId = edtionId;
    }

    public CV() {
    }

    public CV(CV cv) {
        setId(cv.getId());
        setName(cv.getName());
        setSerial_number(cv.getSerial_number());
        setCourseList(cv.getCourseList());
        setBrand(cv.getBrand());
        setTeacherList(cv.getTeacherList());
        setOtherTeacherList(cv.getOtherTeacherList());
        setIntroduction(cv.getIntroduction());
        setAnnouncement(cv.getAnnouncement());
        setFile_path(cv.getFile_path());
        setCreator(cv.getCreator());
        setUnitList(cv.getUnitList());
        setCreate_date(cv.getCreate_date());
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

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public List<PropUnit> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<PropUnit> courseList) {
        this.courseList = courseList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public List<CVUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<CVUnit> unitList) {
        this.unitList = unitList;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<PropUnit> getUsingItem() {
        return usingItem;
    }

    public void setUsingItem(List<PropUnit> usingItem) {
        this.usingItem = usingItem;
    }

	public Integer getCv_type() {
		return cv_type;
	}

	public void setCv_type(Integer cv_type) {
		this.cv_type = cv_type;
	}

	public Integer getCost_type() {
		return cost_type;
	}

	public void setCost_type(Integer cost_type) {
		this.cost_type = cost_type;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
