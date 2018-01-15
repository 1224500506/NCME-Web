package com.hys.exam.service.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;

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
public interface SystemUserManage {
	
	public List<SystemUser> getListByItem(SystemUser item);

	public void querySystemUserList(Page<SystemUser> page,
			SystemUser item);

	public Integer save(SystemUser item);
	
	public Integer insert(SystemUser item);
	
	public Integer setPass(String accountName, String accountPass);

	public SystemUser getItemById(Long id);
	
	public SystemUser getItemByAccountName(String accountName , String password);
		
	public SystemUserConfig getSystemUserConfigByUserId(Long userId);

	public int update(SystemUser item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
	
	//user post history
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p);
	
	public SystemUserPostHistory getSystemPostHistoryById(Long id);
	
	public int updateSystemUserPostHistory(SystemUserPostHistory history);
	
	public List<SystemExpress> getSystemExpressList();

	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role);
	
	public List<SystemUserType> getUserTypeList();
	
	public List<SystemRole> getUserRoleList(Long userId);
	
	public int updateAccountState(Integer accountId,Integer newState);
	
	public int updateAccountRole(Integer accountId,String[] roleList);

	//update user's Password,Mobile,Email
	public boolean updatePME(SystemUser user);
	
	/**
	 * @author Tiger.
	 * @time 2017-1-11
	 * @param item
	 * @return
	 * 
	 * Detail : Check to duplicate user name and user account name.
	 */
	public List<SystemUser> isDuplicate(SystemUser item);
	
	/**
	 * @author   张建国
	 * @time     2017-0-15
	 * @param    String
	 * @return   int
	 * 方法说明： 根据手机号码查询是否已经存在
	 */
	public int checkMobile(String phone);
	
	
	public List<SystemUser> checkUserCode(String code);
	
	/**
	 * 根据邮箱查询是否已经存在
	 * @param email
	 * @return
	 */
	public int checkEmail(String email);
	
/**
 * 
 * 检测用户名是否可用
 * 方法名：checkUserName
 * 创建人：程宏业
 * 时间：2017-2-28-上午9:51:37 
 * 手机:15210211487
 * @param username
 * @return int
 * @exception 
 * @since  1.0.0
 */
	public int checkUserName(String username);
	
	/**
	 * @author   张建国
	 * @time     2017-0-15
	 * @param    String
	 * @return   int
	 * 方法说明： 根据证件号码查询是否已经存在
	 */
	public int checkIdCard(String idCard);
	
	/**
	 * @author   张建国
	 * @time     2017-01-23
	 * @param    String
	 * @return   SystemAccount
	 * 方法说明： 根据手机号码查询账号信息
	 */
	public SystemAccount getSystemAccount(String phone);
	
	/**
	 * @author   张建国
	 * @time     2017-01-23
	 * @param    systemAccount
	 * @return   void
	 * 方法说明： 修改账户密码
	 */
	public void updatePassword(SystemAccount systemAccount);
	
	
	
	/**
	 * 
	 * (通过身份证号获取用户信息)
	 * 方法名：getUserByIdCard
	 * 创建人：程宏业
	 * 时间：2017-3-1-上午11:43:57 
	 * 手机:15210211487
	 * @return SystemUser
	 * @exception 
	 * @since  1.0.0
	 */
	public SystemUser getUserByIdCard(String idCard);
	
	
	/**
	 * 通过用户id获取用户省份id
	 * @param userid
	 * @return
	 */
	public int getUserProviceId(long userid);

	/**
	 * 根据用户的省份id和项目id查询项目id，查询关联表，如果关联表有数据，则该用户所归属省份包含在项目开放的省份中
	 * @param id
	 * @return
	 */
	public int getcvSetIdFromcvId(Long id);
	
	/**
	 * @author taoliang
	 * @param user
	 * @return
	 * @desc 查询用户信息通用方法
	 */
	public SystemUser queryUserForLive(SystemUser user);

	/**
	 * updateLoginErrors:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param item    
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	public int updateLoginErrors(SystemUser item);

	/**
	 * getLoginLimtVo:得到登录限制相关数据
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param      
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	public SystemConfigVO getLoginLimtVo();
	
	
	
	
	
	
}