package com.hys.exam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 专家信息类
 * @author Han
 *
 */
public class ExpertInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217567995666788022L;
	
	//Id
	private Long id;
	//名称
	private String name;
	//编码
	private String code;
	//所属委员会Id
	private Long groupId;
	//所属学组Id
	private Long subGroupId;
	//职务Id
	private Integer office;
	//届期Id
	private Long term;
	//职称Id
	private Long job;
	//状态
	private Integer state;
	//离职时间
	private Date breakDate;
	//禁用状态
	private Integer lockState;
	//工作单位
	private String workUnit;
	//固定电话
	private String phone1;
	//手机号
	private String phone2;
	//邮箱
	private String email;
	//国籍
	private Integer isNation;
	//秘书名称
	private String clerkName;
	//秘书电话
	private String clerkPhone;
	//银行
	private String bank;
	//银行卡号
	private String bankCard;
	//身份证号
	private String identityNum;
	//照片
	private String photo;
	//账号
	private String userName;
	//简介
	private String summary;
	
	//学组名称
	private String subGroupName;
	//委员会名称
	private String groupName;
	//届期
	private String termStr;
	//职称
	private String jobName;
	
	//关联学科Id
	private String propIds;
	//关联学科名称
	private String propNames;
	//关联学科
	private List<ExamProp> prop;
	
	private Integer personage;
	//多选择委员会
	private String groupIds;
	
	private String groupNames;
	
	private Long user_id;
	
	private Integer workUnit_office;
	
	private List<ExpertGroup> group;
	
	//Type of expert in group.
	private Integer groupOffice;
	
	public String getPropIds() {
		return propIds;
	}
	public void setPropIds(String propIds) {
		this.propIds = propIds;
	}
	public String getPropNames() {
		return propNames;
	}
	public void setPropNames(String propNames) {
		this.propNames = propNames;
	}
	public List<ExamProp> getProp() {
		return prop;
	}
	public void setProp(List<ExamProp> prop) {

		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();

		for(ExamProp p: prop){
			ids.add(p.getProp_val_id());
			names.add(p.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setPropIds(idStr);
		this.setPropNames(nameStr);

		this.prop = prop;
	}
	
	public List<ExpertGroup> getGroup() {
		return group;
	}
	public void setGroup(List<ExpertGroup> group) {

		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();

		for(ExpertGroup g: group){
			ids.add(g.getId());
			names.add(g.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setGroupIds(idStr);
		this.setGroupNames(nameStr);

		this.group = group;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTermStr() {
		return termStr;
	}
	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getIsNation() {
		return isNation;
	}
	public void setIsNation(Integer isNation) {
		this.isNation = isNation;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(Long subGroupId) {
		this.subGroupId = subGroupId;
	}
	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}
	public Integer getOffice() {
		return office;
	}
	public void setOffice(Integer office) {
		this.office = office;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public Long getJob() {
		return job;
	}
	public void setJob(Long job) {
		this.job = job;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(Date breakDate) {
		this.breakDate = breakDate;
	}
	public Integer getLockState() {
		return lockState;
	}
	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public String getClerkPhone() {
		return clerkPhone;
	}
	public void setClerkPhone(String clerkPhone) {
		this.clerkPhone = clerkPhone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getGroupNames() {
		return groupNames;
	}
	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}
	public Integer getPersonage() {
		return personage;
	}
	public void setPersonage(Integer personage) {
		this.personage = personage;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Integer getWorkUnit_office() {
		return workUnit_office;
	}
	public void setWorkUnit_office(Integer workUnit_office) {
		this.workUnit_office = workUnit_office;
	}
	public Integer getGroupOffice() {
		return groupOffice;
	}
	public void setGroupOffice(Integer groupOffice) {
		this.groupOffice = groupOffice;
	}
	
	
}
