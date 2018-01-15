package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.utils.PageList;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 manage
 * 
 * 说明:
 */
public interface OrgManage {
	
	public List<PeixunOrg> queryOrgList(PeixunOrg item);
	
	public List<PeixunOrg> queryOrgList(PeixunOrg item,PageList pl);
	
	public Integer getOrgListSize(PeixunOrg item);
	
	public int addPeixunOrg(PeixunOrg item);
	
	public PeixunOrg getItemById(Long id);

	public void updatePeixunOrg(PeixunOrg item);

	public void delItemById(PeixunOrg item);
	public List<SystemAccount> getAccount();

	public List<ExamHospital> getHospital();
	
}