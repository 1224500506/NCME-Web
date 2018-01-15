package com.hys.exam.service.local.impl;

import java.util.List;
import java.util.Map;

import com.hys.exam.common.util.Page;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 manageImpl
 * 
 * 说明:
 */
public class SystemUserManageImpl extends BaseMangerImpl implements SystemUserManage {

	private SystemUserDAO systemUserDAO ;

	public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
		this.systemUserDAO = systemUserDAO;
	}
	
	@Override //取得所有站点信息
	public List<SystemUser> getListByItem(SystemUser item){
		return systemUserDAO.getListByItem(item) ;
	}
	
	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item) {
		systemUserDAO.querySystemUserList(page, item);
	}
	
	@Override
	public SystemUserConfig getSystemUserConfigByUserId(Long userId){
		SystemUserConfig config = this.systemUserDAO.getSystemUserConfigByUserId(userId);
		if(null != config){
			SystemIndustry industry = this.systemUserDAO.getSystemIndustryByPositionId(config.getUserIndustryId());
			if(null != industry)
				config.setUserIndustryName(industry.getIndustryName());
		}
		return config;
	}
	
	
	public int update(SystemUser item) {
		return systemUserDAO.update(item);
	}

	
	public int deleteList(long[] ids, String idColName) {
		return systemUserDAO.deleteList(ids,idColName);
	}

	
	public SystemUser getItemById(Long id) {
		return systemUserDAO.getItemById(id);
	}
	public SystemUser getItemByAccountName(String accountName , String password)
	{
		return systemUserDAO.getItemByAccountName(accountName, password);
	}

	
	public Integer save(SystemUser item) {
		return systemUserDAO.save(item);
	}
	
	public Integer insert(SystemUser item) {
		return systemUserDAO.insert(item);
	}

	@Override
	public int delete(long id, String idColName) {
		return systemUserDAO.delete(id,idColName);
	}
	
	//user post history
	@Override
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p){
		return this.systemUserDAO.getSystemPostHistoryList(page, p);
	}
	
	@Override
	public SystemUserPostHistory getSystemPostHistoryById(Long id){
		return this.systemUserDAO.getSystemPostHistoryById(id);
	}
	
	@Override
	public int updateSystemUserPostHistory(SystemUserPostHistory history){
		return this.systemUserDAO.updateSystemUserPostHistory(history);
	}
	
	@Override
	public List<SystemExpress> getSystemExpressList(){
		return this.systemUserDAO.getSystemExpressList();
	}

	@Override
	public Integer setPass(String accountName, String accountPass) {
		return this.systemUserDAO.setPass(accountName, accountPass);
	}
	@Override
	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role)
	{
		this.systemUserDAO.querySystemAccountList(page,item,role);
	}
	@Override
	public List<SystemUserType> getUserTypeList()
	{
		return this.systemUserDAO.getUserTypeList();
	}
	@Override
	public List<SystemRole> getUserRoleList(Long userId)
	{
		return this.systemUserDAO.getUserRoleList(userId);
	}
	@Override
	public int updateAccountState(Integer accountId,Integer newState)
	{
		return this.systemUserDAO.updateAccountState(accountId,newState);
	}
	@Override
	public int updateAccountRole(Integer accountId,String[] roleList)
	{
		return this.systemUserDAO.updateAccountRole(accountId,roleList);
	}

	@Override
	public boolean updatePME(SystemUser user) 
	{
		return this.systemUserDAO.updatePME(user);
	}

	/**
	 * @author Tiger.
	 * @time 2017-1-11
	 * @param item
	 * @return
	 * 
	 * Detail : Check to duplicate user name and user account name.
	 */
	@Override
	public List<SystemUser> isDuplicate(SystemUser item) {
		// TODO Auto-generated method stub
		return this.systemUserDAO.isDuplicate(item);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-0-15
	 * @param    String
	 * @return   int
	 * 方法说明： 根据手机号码查询是否已经存在
	 */
	@Override
	public int checkMobile(String phone){
		return systemUserDAO.checkMobile(phone);
	}
	
	@Override
	public List<SystemUser> checkUserCode(String code){
		return systemUserDAO.checkUserCode(code);
	}
	
	/**
	 * @param    String
	 * @return   int
	 * 方法说明： 根据邮箱查询是否已经存在
	 */
	@Override
	public int checkEmail(String email){
		return systemUserDAO.checkEmail(email);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-0-15
	 * @param    String
	 * @return   int
	 * 方法说明： 根据证件号码查询是否已经存在
	 */
	@Override
	public int checkIdCard(String idCard){
		return systemUserDAO.checkIdCard(idCard);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-23
	 * @param    String
	 * @return   SystemAccount
	 * 方法说明： 根据手机号码查询账号信息
	 */
	@Override
	public SystemAccount getSystemAccount(String phone){
		return systemUserDAO.getSystemAccount(phone);
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-23
	 * @param    systemAccount
	 * @return   void
	 * 方法说明： 修改账户密码
	 */
	public void updatePassword(SystemAccount systemAccount){
		systemUserDAO.updatePassword(systemAccount);
	}

	@Override
	public int checkUserName(String username) {
		// TODO Auto-generated method stub
		return systemUserDAO.checkUserName(username);
	}

	
	@Override
	public SystemUser getUserByIdCard(String idCard) {
		// TODO Auto-generated method stub
		return systemUserDAO.getUserByIdCard(idCard);
	}

	
	/**
	 * 通过用户id获取用户省份id
	 * @param userid
	 * @return
	 */
	@Override
	public int getUserProviceId(long userid) {
		return systemUserDAO.getUserProviceId(userid);
	}

	
	/**
	 * 根据用户的省份id和项目id查询项目id，查询关联表，如果关联表有数据，则该用户所归属省份包含在项目开放的省份中
	 * @param id
	 * @return
	 */
	@Override
	public int getcvSetIdFromcvId(Long id) {
		return systemUserDAO.getcvSetIdFromcvId(id);
	}

	@Override
	public SystemUser queryUserForLive(SystemUser user) {
		// TODO Auto-generated method stub
		return systemUserDAO.queryUserForLive(user);
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.SystemUserManage#updateLoginErrors(com.hys.exam.model.SystemUser)
	 */
	@Override
	public int updateLoginErrors(SystemUser item) {
		
		return systemUserDAO.updateLoginErrors(item);
		
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.SystemUserManage#getLoginLimtVo()
	 */
	@Override
	public SystemConfigVO getLoginLimtVo() {
		
		
		
		return systemUserDAO.getLoginLimtVo();
		
	}
	
	
}