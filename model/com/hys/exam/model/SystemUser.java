package com.hys.exam.model;

import java.util.Date;

import com.hys.exam.model.system.SystemUserConfig;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 model
 * 
 * 说明:
 */
public class SystemUser extends SystemAccount {

	private static final long serialVersionUID = 3120104883143599691L;

	/**
	 * 主键ID
	 */
	private Long userId;
	
	
	/**
	 * 用户ID
	 */
	
	private Long id;
	
	
	
	
	
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 出生日期
	 */
	private java.util.Date birthday;
	
	/**
	 * 性别 1 -男 2 -女
	 */
	private Integer sex;
	
	/**
	 * 手机
	 */
	private String mobilPhone;
	
	/**
	 * 固定电话
	 */
	private String telphone;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	/**
	 * 证件类型 1 -身份证 2 -军官证
	 */
	private Integer certificateType;
	
	/**
	 * 证件号码
	 */
	private String certificateNo;
	
	/**
	 * 单位信息
	 */
	private String workUnit;
	
	private Integer work_unit_id;
	
	/*是否来自基层*/
	private Integer grassroot;
	
	/**
	 * 联系地址
	 */
	private String contactAddress;
	
	/**
	 * 邮政编码
	 */
	private String postCode;
	
	/**
	 * 所属行业
	 */
	private String profession;
	
	/**
	 * 所属科室
	 */
	private String deptName;
	/**
	 * 职称
	 */
	private String profTitle;
	
	/**
	 * 密码问题
	 */
	private String passwordQuestion;
	
	/**
	 * 答案
	 */
	private String passwordQuestionAnswer;
	
	/**
	 * 用户状态 1 -正常 -1 -删除 -2 -禁用
	 */
	private Integer status;
	
	/**
	 * 最后登录时间
	 */
	private java.util.Date lastLoginDate;
	
	private java.util.Date regDate;
	
	/**
	 * 最后登录IP
	 */
	private String lastLoginIp;
	
	/**
	 * 用户状态 1 -正常 -1 -删除 -2 -禁用
	 */
	private Integer age;
	

	/**
	 * 用户类别根据不同的站点区分
	 * 1 -学员 
	 * 101 -带教老师 102 -科室管理员 103 -医院管理员 104 -市管理员 105 -省管理员
	 */
	private Integer userType;
	
	/**
	 * 绑定时间
	 */
	private Date bindDate;
	
	private Integer education;
	
	private SystemUserConfig userConfig = new SystemUserConfig();

	private String health_certificate;
	
	private String prop_Id;
	
	private String job_Id;
	
	private Integer user_image;
	
	private String user_avatar ;//用户头像，原user_image废弃，YHQ 2017-02-20
	
	//所属医院地址
	private String hospitalAddress;
	private String otherHospitalName;
	
	private Integer loginErrorNum;//登录错误次数
	
	private Long loginErrorFirstTime;//登录错误第一次时间
	private Long loginErrorLastTime;//登录错误最后一次时间
	
	
	
	
	
	public Integer getGrassroot() {
		return grassroot;
	}

	public void setGrassroot(Integer grassroot) {
		this.grassroot = grassroot;
	}

	/**
	 * loginErrorNum
	 *
	 * @return  the loginErrorNum
	 * @since    Ver 1.0
	*/
	
	public Integer getLoginErrorNum() {
		return loginErrorNum;
	}

	/**
	 * loginErrorNum
	 *
	 * loginErrorNum    the loginErrorNum to set
	 * @since    Ver 1.0
	 */
	
	public void setLoginErrorNum(Integer loginErrorNum) {
		this.loginErrorNum = loginErrorNum;
	}

 


	/**
	 * loginErrorFirstTime
	 *
	 * @return  the loginErrorFirstTime
	 * @since    Ver 1.0
	*/
	
	public Long getLoginErrorFirstTime() {
		return loginErrorFirstTime;
	}

	/**
	 * loginErrorFirstTime
	 *
	 * loginErrorFirstTime    the loginErrorFirstTime to set
	 * @since    Ver 1.0
	 */
	
	public void setLoginErrorFirstTime(Long loginErrorFirstTime) {
		this.loginErrorFirstTime = loginErrorFirstTime;
	}


	/**
	 * loginErrorLastTime
	 *
	 * @return  the loginErrorLastTime
	 * @since    Ver 1.0
	*/
	
	public Long getLoginErrorLastTime() {
		return loginErrorLastTime;
	}

	/**
	 * loginErrorLastTime
	 *
	 * loginErrorLastTime    the loginErrorLastTime to set
	 * @since    Ver 1.0
	 */
	
	public void setLoginErrorLastTime(Long loginErrorLastTime) {
		this.loginErrorLastTime = loginErrorLastTime;
	}

	public Integer getUser_image() {
		return user_image;
	}

	public void setUser_image(Integer user_image) {
		this.user_image = user_image;
	}

	public String getJob_Id() {
		return job_Id;
	}

	public void setJob_Id(String job_Id) {
		this.job_Id = job_Id;
	}

	public String getProp_Id() {
		return prop_Id;
	}

	public void setProp_Id(String prop_Id) {
		this.prop_Id = prop_Id;
	}

	public String getHealth_certificate() {
		return health_certificate;
	}

	public void setHealth_certificate(String health_certificate) {
		this.health_certificate = health_certificate;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobilPhone() {
		return mobilPhone;
	}

	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getProfTitle() {
		return profTitle;
	}

	public void setProfTitle(String profTitle) {
		this.profTitle = profTitle;
	}

	public String getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordQuestionAnswer() {
		return passwordQuestionAnswer;
	}

	public void setPasswordQuestionAnswer(String passwordQuestionAnswer) {
		this.passwordQuestionAnswer = passwordQuestionAnswer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.util.Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(java.util.Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public java.util.Date getRegDate() {
		return regDate;
	}

	public void setRegDate(java.util.Date regDate) {
		this.regDate = regDate;
	}

	public SystemUserConfig getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(SystemUserConfig userConfig) {
		this.userConfig = userConfig;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getWork_unit_id() {
		return work_unit_id;
	}

	public void setWork_unit_id(Integer work_unit_id) {
		this.work_unit_id = work_unit_id;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	//用户头像，原user_image废弃，YHQ 2017-02-20
	public String getUser_avatar() {
		return user_avatar;
	}
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOtherHospitalName() {
		return otherHospitalName;
	}

	public void setOtherHospitalName(String otherHospitalName) {
		this.otherHospitalName = otherHospitalName;
	}
	
}
