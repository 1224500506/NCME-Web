package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.SystemUserDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemConfigVO;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.model.system.SystemUserAddress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;
import com.hys.exam.utils.MD5Util;
import com.hys.exam.utils.PageUtil;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 jdbcDao
 * 
 * 说明:
 */
public class SystemUserJDBCDAO extends AbstractJDBCDAO implements SystemUserDAO {
	
	////final static private String getSystemUserList_SQL = "select u.*,u.id user_id,a.account_id,a.account_password,a.account_remark from system_user u,system_account a where a.account_name = u.account_name and u.status = 1  " ;
	//private String getSystemUserList_SQL = " select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark, a.account_status from system_user u left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id where u.id>0  " ;
	private String getSystemUserList_SQL = " select u.*,a.account_name,a.account_password as accountPassword, u.id user_id, a.account_id,  a.account_remark, a.account_status, p.PROP_ID, j.JOB_ID from system_user u left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id left join system_user_prop_val as p on u.ID= p.USER_ID left join system_user_job_val as j on u.ID=j.USER_ID where u.id>0 " ;
	final static private String addSystemUser_SQL = " insert into system_user (id, domain_name, remark, client_id, application_id, status, role_id) values (:id, :domainName, :remark, :clientId, :applicationId, :status, :roleId) ";
	final static private String insertSystemUser_SQL = " insert into system_user (id, real_name, certificate_no, certificate_type, email, work_unit, work_unit_id, user_type, sex, mobil_phone, dept_name, health_certificate, education, hospital_address, other_hospital_name,grassroot) values (:userId, :realName, :certificateNo, :certificateType, :email, :workUnit, :work_unit_id, :userType, :sex, :mobilPhone, :deptName, :health_certificate, :education, :hospitalAddress, :otherHospitalName,:grassroot) ";

	@Override  
	public List<SystemUser> getListByItem(SystemUser item){
		StringBuilder sql = new StringBuilder();
		sql.append(getSystemUserList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getUserId() != null && item.getUserId()>0) {
			sql.append(" and u.Id = ? ");
			list.add(item.getUserId());
		}
		if (item.getRealName() != null && !item.getRealName().trim().equals("")) {
			sql.append(" and u.Real_Name = ? ");
			list.add(item.getRealName().trim());
		} 
		if (item.getCertificateNo() != null && !item.getCertificateNo().trim().equals("")) {
			sql.append(" and u.Certificate_No like ? ");
			list.add("%"+item.getCertificateNo().trim()+"%" );
		} 
		if(item.getUserType() != null)
		{
			if(item.getUserType() != 1)
			{
				if (item.getWorkUnit() != null && !item.getWorkUnit().equals("")) {
					sql.append(" and u.Work_Unit like ? ");
					list.add("%"+item.getWorkUnit().trim()+"%" );
				}	
			}
			else
			{
				if (item.getWorkUnit() != null && !item.getWorkUnit().equals("")) {
					sql.append(" and u.work_unit_id in (?)");
					list.add(item.getWorkUnit().trim());
				}	
			}
		}
		
		if (item.getAccountName() != null && !item.getAccountName().trim().equals("")) {
			sql.append(" and a.ACCOUNT_NAME = ? ");
			list.add(item.getAccountName().trim());
		}
		if (item.getMobilPhone() != null && !item.getMobilPhone().trim().equals("")) {
			sql.append(" and u.MOBIL_PHONE = ? ");
			list.add(item.getMobilPhone().trim());
		}
		if (item.getEmail() != null && !item.getEmail().trim().equals("")) {
			sql.append(" and u.EMAIL = ? ");
			list.add(item.getEmail().trim());
		}
		if (item.getAccountPassword() != null && !item.getAccountPassword().trim().equals("")) {
			sql.append(" and a.ACCOUNT_PASSWORD = ?");
			list.add(item.getAccountPassword().trim());
		}
		sql.append(" order by u.reg_date desc ");
				
		// 查询结果集
		List<SystemUser> resList = getList(sql.toString(), list, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		
		SystemUserConfig systemUserConfig = new SystemUserConfig();
		
		if(resList != null && resList.size() > 0) {
			for(SystemUser user : resList)
			{
				String getUserConfigSql = "select user_id as userId, user_province_id as userProvinceId, user_city_id as userCityId, user_counties_id as userCountiesId from system_user_config where user_id=?";
				
				List<SystemUserConfig> configList = getJdbcTemplate().query(getUserConfigSql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUserConfig.class), user.getUserId());
				
				if(configList != null && configList.size() > 0){
					systemUserConfig = configList.get(0);
				}
				user.setUserConfig(systemUserConfig);
				
				//Get the user's work unit add by Tiger.
				String workUnitSql = "select t.* from exam_hospital t where t.id = ?";
				List<ExamHospital> hospList =  getJdbcTemplate().query(workUnitSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class), user.getWork_unit_id());
				if(hospList != null && hospList.size() > 0)
				{
					user.setWorkUnit(hospList.get(0).getName());
				}
				
				//Get the user's job add by Tiger.
				 String jobSql = "select t.*,v.id as prop_val_id from exam_prop_val t,sub_sys_prop_val v where t.id = v.prop_val_id and t.id = ? order by t.code";
				 List<ExamProp> propList =  getJdbcTemplate().query(jobSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),user.getJob_Id());
				 if(propList != null && propList.size() > 0)
				 {
					user.setProfTitle(propList.get(0).getName());
				 }					
			}
		}
		return resList;
	}

	@Override
	public void querySystemUserList(Page<SystemUser> page, SystemUser item) {

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemUserList_SQL);

		Integer status = item.getStatus();
		String workUnit = item.getWorkUnit();
		String deptName = item.getDeptName();
		Integer sex = item.getSex();
		Integer userType = item.getUserType();


		List<Object> list = new ArrayList<Object>();

		if (item.getUserId() != null && item.getUserId()>0) {
			sql.append(" and u.Id = ? ");
			list.add(item.getUserId() );
		} 
		if (StringUtils.isNotBlank(item.getRealName())) {
			sql.append(" and u.Real_Name like ? ");
			list.add("%"+item.getRealName().trim()+"%" );
		} 
		if (StringUtils.isNotBlank(item.getCertificateNo())) {
			sql.append(" and u.Certificate_No like ? ");
			list.add("%"+item.getCertificateNo().trim()+"%" );
		}
		if (status != null && status > 0) {
			sql.append(" and a.account_status = ? ");
			list.add(status);
		}
		if (item.getUserType() != null)
		if(item.getUserType() != 1)
		{
			if (workUnit != null && !workUnit.equals("")) {
				sql.append(" and u.Work_Unit like ? ");
				list.add("%"+workUnit.trim()+"%" );
			}	
		}
		else
		{
			if (workUnit != null && !workUnit.equals("")) {
				sql.append(" and u.work_unit_id in (?)");
				list.add(workUnit.trim());
			}	
		}
		
		if (deptName != null && !deptName.equals("")) {
			sql.append(" and u.DEPT_NAME like ? ");
			list.add("%"+deptName.trim()+"%" );
		}
		if (sex != null && sex > 0) {
			sql.append(" and u.sex = ? ");
			list.add(sex);
		}
		if (userType != null && userType > 0L) {
			sql.append(" and u.user_Type = ? ");
			list.add(userType);
		}
		
		//add by han.
		
		if (item.getProfTitle()!=null && !item.getProfTitle().equals("")){
			sql.append(" and u.prof_title = ? ");
			list.add(item.getProfTitle());
		}
		if (item.getUserConfig() != null){
			SystemUserConfig userConfig = item.getUserConfig();
			if (userConfig.getUserProvinceId()!=null && userConfig.getUserProvinceId()!=0){
				sql.append(" and c.user_province_id = ? ");
				list.add(userConfig.getUserProvinceId());
			}
			if (userConfig.getUserCityId()!=null && userConfig.getUserCityId()!=0){
				sql.append(" and c.user_province_id = ? ");
				list.add(userConfig.getUserCityId());
			}
			if (userConfig.getUserCountiesId()!=null && userConfig.getUserCountiesId()!=0){
				sql.append(" and c.user_counties_id = ? ");
				list.add(userConfig.getUserCountiesId());
			}
		}
		//end.		
		sql.append(" order by u.reg_date desc ");

		// 查询结果集
		List<SystemUser> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));				
				
		// add by han.
		for(SystemUser user: resList){
			SystemUserConfig userConfig = new SystemUserConfig();
			userConfig = this.getSystemUserConfigByUserId(user.getUserId());
			user.setUserConfig(userConfig);
		}
		//取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}
	
	@Override
	public SystemUserConfig getSystemUserConfigByUserId(Long userId){
		String sql = "select t.id, (select o1.org_name from system_org o1 where o1.id = t.user_province_id) userProvinceName," +
				" (select o2.org_name from system_org o2 where o2.id = t.user_city_id) userCityName," +
				" (select o3.org_name from system_org o3 where o3.id = t.user_counties_id) userCountiesName," +
				" (select o4.org_name from system_org o4 where o4.id = t.user_street_id) userStreetName," +
				" t.user_industry_id " +
				" from system_user_config t  where t.user_id = ?";
		List<Object> p = new ArrayList<Object>();
		p.add(userId);
		List<SystemUserConfig> list = getList(sql.toString(), p,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUserConfig.class));
		if(!Utils.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public SystemIndustry getSystemIndustryByPositionId(Long id){
		String sql = "select (select t.industry_name from system_industry t where t.id = si.parent_id)  || '一' || si.industry_name as industryName" +
				" from system_industry si where si.id = ?";
		return this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemIndustry.class), new Object[] { id });
	}

	public Integer save(SystemUser item) {
		Long id = this.getSequence("SYSTEM_USER_SEQ");
		item.setUserId(id);
		getNamedParameterJdbcTemplate().update(addSystemUser_SQL, new BeanPropertySqlParameterSource(item));
		return  id.intValue();
	}
	
	public Integer insert(SystemUser item) {
		Long id = this.getSequence("SYSTEM_USER");
		item.setUserId(id);
	//	NamedParameterJdbcTemplate templ = getNamedParameterJdbcTemplate();
	//	templ.update(insertSystemUser_SQL, new BeanPropertySqlParameterSource(item));
		getNamedParameterJdbcTemplate().update(insertSystemUser_SQL, new BeanPropertySqlParameterSource(item));
		
		// add by han
		item.setAccountId(null);
		String insertSystemAccount_SQL = "insert into system_account (account_id, account_name, account_password, account_remark, user_id, account_status) value (null, :accountName, :accountPassword, :accountRemark, :userId, :account_status)";
		item.setAccount_status(1);
		item.setAccountRemark("");
		String newPass = MD5Util.string2MD5(item.getAccountPassword());
		item.setAccountPassword(newPass);
		if (item.getAccountName() != null && !item.getAccountName().equals(""))
			getNamedParameterJdbcTemplate().update(insertSystemAccount_SQL, new BeanPropertySqlParameterSource(item));
		// end

		//Add by Lee for insert propVal
		if(!StringUtil.checkNull(item.getProp_Id())) {
			String insertSystemPropVal_SQL = "insert into system_user_prop_val (user_id, prop_id) values(:userId, :prop_Id)";
			getNamedParameterJdbcTemplate().update(insertSystemPropVal_SQL, new BeanPropertySqlParameterSource(item));
		}
		//Add by Lee for insert propVal
		if(item.getUserId() > 0L) {
			SystemUserConfig systemUserConfig = item.getUserConfig();
			systemUserConfig.setUserId(id);
			String insertSystemPropVal_SQL = "insert into system_user_config (user_id, user_province_id, user_city_id, user_counties_id) values(:userId, :userProvinceId, :userCityId, :userCountiesId)";
			getNamedParameterJdbcTemplate().update(insertSystemPropVal_SQL, new BeanPropertySqlParameterSource(systemUserConfig));
		}
		//Add by Lee for insert jobVal
		if(!StringUtil.checkNull(item.getJob_Id())) {
			String insertSystemPropVal_SQL = "insert into system_user_job_val (user_id, job_id) values(:userId, :job_Id)";
			System.out.println(insertSystemPropVal_SQL);
			getNamedParameterJdbcTemplate().update(insertSystemPropVal_SQL, new BeanPropertySqlParameterSource(item));
		}
		
		return  id.intValue();
	}
	
	public Integer setPass(String account,String password) {
		String sql = "update system_account set account_password=? where account_name = ?";
		return this.getJdbcTemplate().update(sql,password,account);
	}
	
	public SystemUser getItemById(Long userId) {
		SystemUser item = new SystemUser();
		item.setUserId(userId);
		List<SystemUser> list = this.getListByItem(item);
		
		return list!=null&&list.size()==1 ? list.get(0) : item;
	}
	
	public SystemUser getItemByAccountName(String accountName,String password) {
		SystemUser item = new SystemUser();
		item.setRealName(accountName);
		item.setAccountName(password);

		StringBuilder sql = new StringBuilder();
		sql.append(getSystemUserList_SQL);

		List<Object> list = new ArrayList<Object>();

		if (item.getRealName() != null && !item.getRealName().trim().equals("")) {
			sql.append(" and u.Real_Name = ? ");
			list.add(item.getRealName().trim());
		}
		
		if (item.getAccountName() != null && !item.getAccountName().trim().equals("")) {
			sql.append(" or a.ACCOUNT_NAME = ? ");
			list.add(item.getAccountName().trim());
		}
		sql.append(" order by u.reg_date desc ");
				
		// 查询结果集
		List<SystemUser> resList = getList(sql.toString(), list, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		
		return resList!=null&&resList.size()>0 ? resList.get(0) : null;
	}
	
	
	@Override
	public int update(SystemUser item) {
		//Update Account Table---add by lee
		String account_sql = "update system_account set account_name=? where user_id=?";
		getJdbcTemplate().update(account_sql, item.getAccountName(), item.getUserId());
		//Add by Lee for Update propVal
		if(!StringUtil.checkNull(item.getProp_Id())) {
			//YHQ 2016-02-16 modify insert--------
			List<Object> params = new ArrayList<Object>();
	        params.add(item.getProp_Id());
	        params.add(item.getUserId());
	        
	        String updateSystemPropVal_SQL = "select count(1) from system_user_prop_val where user_id=?";	        
	        int rsNum = getJdbcTemplate().queryForInt(updateSystemPropVal_SQL, item.getUserId());
	        if (rsNum > 0) {
	        	//updateSystemPropVal_SQL = "update system_user_prop_val set prop_id=? where user_id=?";
	        	//因表设计当初为prop_id 和 user_id为联合主键，所以有一定概率出现主键冲突现象，在此处做出处理----taoliang
	        	String deleteSystemPropVal_SQL = "delete from system_user_prop_val where user_id=?";
	        	getJdbcTemplate().update(deleteSystemPropVal_SQL, item.getUserId());
	        	updateSystemPropVal_SQL = "insert into system_user_prop_val (prop_id,user_id) VALUES (?,?)";
	        	
	        } else {
	        	updateSystemPropVal_SQL = "insert into system_user_prop_val (prop_id,user_id) VALUES (?,?)";
	        }
	        getJdbcTemplate().update(updateSystemPropVal_SQL, params.toArray());
			//getJdbcTemplate().update(updateSystemPropVal_SQL, item.getProp_Id(), item.getUserId());
		}
		//Add by Lee for Update propVal
		SystemUserConfig systemUserConfig = item.getUserConfig();
		String updateUserConfig_SQL = "update system_user_config set user_province_id=?, user_city_id=?, user_counties_id=? where user_id=?";
		getJdbcTemplate().update(updateUserConfig_SQL, systemUserConfig.getUserProvinceId(), systemUserConfig.getUserCityId(), systemUserConfig.getUserCountiesId(),item.getUserId());
	
		//Add by Lee for insert jobVal
		if(!StringUtil.checkNull(item.getJob_Id())) {
			List<Object> params = new ArrayList<Object>();
	        params.add(item.getJob_Id());
	        params.add(item.getUserId());
			String updateJob_SQL = "select count(1) from system_user_job_val where user_id=?";
			int rsNum = getJdbcTemplate().queryForInt(updateJob_SQL, item.getUserId());
			if (rsNum > 0) {
				updateJob_SQL = "update system_user_job_val set job_id=? where user_id=?";
			}else{
				updateJob_SQL = "insert into system_user_job_val (job_id,user_id) VALUES (?,?)";
			}
//			getJdbcTemplate().update(updateJob_SQL, item.getJob_Id(),item.getUserId());
			getJdbcTemplate().update(updateJob_SQL, params.toArray());
		}
		
		//Update SystemUser Table---change by lee
		String sql = "update system_user s set s.REAL_NAME = ?, s.work_unit_id=?, s.WORK_UNIT = ?, s.SEX = ?, s.health_certificate=?, s.education=?, s.user_avatar=?,s.CERTIFICATE_NO=?, s.hospital_address=?, s.other_hospital_name=? where s.ID = ?";//用户头像，原user_image废弃，YHQ 2017-02-20
		return this.getJdbcTemplate().update(sql, item.getRealName(), item.getWork_unit_id(), item.getWorkUnit(), item.getSex(), item.getHealth_certificate(), item.getEducation(), item.getUser_avatar(),item.getCertificateNo(),item.getHospitalAddress(),item.getOtherHospitalName(), item.getUserId());//用户头像，原user_image废弃，YHQ 2017-02-20
	}

	@Override
	public int delete(long id, String idColName) {
		String sql = "delete system_user s where s.USER_ID = ?";
	//	return this.getJdbcTemplate().execute(sql);
		return super.delete(id, idColName);
	}
	
	@Override
	public List<SystemUserPostHistory> getSystemPostHistoryList(Page<SystemUserPostHistory> page, SystemUserPostHistory p){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*, t2.user_name, t2.address, t2.mobile_phone, t3.name from system_user_post_history t");
		sql.append(" left join system_user_address t2 on t.address_id = t2.id ");
		sql.append(" left join system_express t3 on t.express_id = t3.id ");
		sql.append("  where t.status = ?");

		List<Object> list = new ArrayList<Object>();
		list.add(Constants.STATUS_1);
		
		if(null != p.getCertificateName()){
			sql.append(" and t.certificate_name like ? ");
			list.add("%" + p.getCertificateName().trim() + "%");
		}
		
		if(null != p.getMobilePhone()){
			sql.append(" and t2.mobile_phone like ? ");
			list.add("%" + p.getMobilePhone().trim() + "%");
		}
		
		if(null != p.getUserName()){
			sql.append(" and t2.user_name like ? ");
			list.add("%" + p.getUserName().trim() + "%");
		}
		
		sql.append(" order by t.id desc ");

		// 查询结果集
		List<SystemUserPostHistory> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUserPostHistory.class));

		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
		
		return resList;
	}
	
	@Override
	public SystemUserPostHistory getSystemPostHistoryById(Long id){
		String sql = "select t.*, t2.user_name, t2.address, t2.mobile_phone, t3.name, t3.code as expressCode from system_user_post_history t " +
				" left join system_user_address t2 on t.address_id = t2.id " +
				" left join system_express t3 on t.express_id = t3.id where t.id = ?";
		return this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUserPostHistory.class),id);
	}
	
	@Override
	public int updateSystemUserPostHistory(SystemUserPostHistory p){
		String sql = "update system_user_post_history t set t.express_no = ?, t.express_id = ?, t.invoice_status = ?, t.certificate_name=?, t.description=?, t.create_date=sysdate() where t.id = ?";
		this.getJdbcTemplate().update(sql, p.getExpressNo(), p.getExpressId(), p.getInvoiceStatus(), p.getCertificateName(), p.getDescription(), p.getId());
		sql = "update system_user_address set user_name = ?, address = ?, mobile_phone = ?, create_date = sysdate() where id in (select address_id from system_user_post_history where id = ?)";
		return this.getJdbcTemplate().update(sql, p.getUserName(), p.getAddress(), p.getMobilePhone(), p.getId());		
	}
	
	@Override
	public List<SystemExpress> getSystemExpressList(){
		String sql = "select * from system_express t where t.status = 1";
		return getList(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemExpress.class));
	}
	
	@Override
	public SystemUserAddress getSystemUserAddressById(Long id){
		String sql = "select * from system_user_address t where t.id = ?";
		return this.getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUserAddress.class), id);
	}
	
	@Override
	public SystemExpress getSystemExpressById(Long id){
		String sql = "select * from system_express t where t.id = ?";
		return this.getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemExpress.class), id);
	}
	
	@Override
	public void querySystemAccountList(Page<SystemUser> page,SystemUser item,Integer role) {

		StringBuilder sql = new StringBuilder();
		if(role != null)
		{
			sql.append("select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark,a.account_status from system_account a left join system_user u on a.user_Id = u.id left join system_account_role ar on a.account_id = ar.account_id where a.account_id>0 ");	
		}
		else
		{
			sql.append("select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark,a.account_status from system_account a left join system_user u on a.user_Id = u.id where a.account_id>0 ");
		}
		

		Integer status = item.getAccount_status();
		String workUnit = item.getWorkUnit();
		Integer userType = item.getUserType();


		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(item.getRealName())) {
			sql.append(" and u.Real_Name like ? ");
			list.add("%"+item.getRealName().trim()+"%" );
		} 
		if (StringUtils.isNotBlank(item.getAccountName())) {
			sql.append(" and a.account_name like ? ");
			list.add("%"+item.getAccountName().trim()+"%" );
		} 
		if (status != null && status > 0) {
			sql.append(" and a.account_status = ? ");
			list.add(status);
		}
		if (workUnit != null && !workUnit.equals("")) {
			sql.append(" and u.Work_Unit like ? ");
			list.add("%"+workUnit.trim()+"%" );
		}
		if (userType != null && userType > 0L) {
			sql.append(" and u.User_Type = ? ");
			list.add(userType);
		}
		if(role != null && role>0L)
		{
			sql.append(" and ar.role_id = ?");
			list.add(role);
		}
		
		
		sql.append(" order by u.reg_date desc ");

		// 查询结果集
		List<SystemUser> resList = getList(
				PageUtil.getPageSql(sql.toString(), page.getCurrentPage(),page.getPageSize()),  list,
				ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));				
				
		// //取得总记录数
		Integer totalCount = getCount(sql.toString(), list.toArray());
		page.setList(resList);
		page.setCount(totalCount);
	}
	
	public List<SystemUserType> getUserTypeList()
	{
		String sql = "select * from system_user_type";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUserType.class));
	}
	public List<SystemRole> getUserRoleList(Long userId)
	{
		String sql = "select r.* from system_role r LEFT JOIN system_account_role a on r.ROLE_ID = a.ROLE_ID where a.ACCOUNT_ID = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		return getJdbcTemplate().query(sql, list.toArray(),
				ParameterizedBeanPropertyRowMapper.newInstance(SystemRole.class));
	}
	
	public int updateAccountState(Integer id, Integer newState)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_account set ");
		if(null != newState){
			sql.append("ACCOUNT_STATUS=? ");
			values.add(newState);
		}
		else
		{
			return 0;
		}
		sql.append("where account_id = ?");
		values.add(id);
		
		return getJdbcTemplate().update(sql.toString(),values.toArray());
		
	}
	public int updateAccountRole(Integer accountId,String[] roleList)
	{
		StringBuffer deleteSql = new StringBuffer();
		deleteSql.append("delete from system_account_role where account_id = ?");
		getJdbcTemplate().update(deleteSql.toString(), accountId);
		
		int result = 0;
		for(String roleId : roleList)
		{
			String sql = "insert into system_account_role (account_id,role_id) values (?,?)";
			List list = new ArrayList();
			list.add(accountId);
			list.add(roleId.trim());
			result = getJdbcTemplate().update(sql.toString(),list.toArray());
		}
		return result;
	}
	
	/**
	 * @author Alisa
	 * @param SystemUser
	 */
	@Override
	public boolean updatePME(SystemUser user) {				
		
		Boolean flage = Boolean.TRUE;
		
		if(!StringUtil.checkNull(user.getAccountPassword())){
			String updatePass = "update system_account set account_password= ? where user_id=?";
			if(getJdbcTemplate().update(updatePass, user.getAccountPassword(),user.getUserId()) < 1){
				flage = Boolean.FALSE;
			}
		}
		
		List paramlist = new ArrayList();
		StringBuilder updateME_query = new StringBuilder("update system_user set REAL_NAME = ?");
		paramlist.add(user.getRealName());
		
		if(!StringUtil.checkNull(user.getMobilPhone())){
			updateME_query.append(", mobil_phone = ?");
			paramlist.add(user.getMobilPhone());
		}
		if(!StringUtil.checkNull(user.getEmail())){
			updateME_query.append(", email=?");
			paramlist.add(user.getEmail());
		}
		updateME_query.append(" where id=?");
		paramlist.add(user.getUserId());
		if(getJdbcTemplate().update(updateME_query.toString(),paramlist.toArray()) < 1){
			flage = Boolean.FALSE;
		}
		
		return flage;
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
		StringBuffer sql = new StringBuffer();
		sql.append("select * from system_user u left join system_account a on u.id = a.user_id where u.id > 0");
		
		List list = new ArrayList();
		
		if(item.getUserId() != null && !item.getUserId().equals(0L))
		{
			sql.append(" and u.id != ?");
			list.add(item.getUserId());
		}
		if(!StringUtil.checkNull(item.getAccountName()))
		{
			sql.append(" and a.account_name = ?");
			list.add(item.getAccountName());
		}
		if(!StringUtil.checkNull(item.getRealName()))
		{
			sql.append(" and u.real_name = ?");
			list.add(item.getRealName());
		}
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), list.toArray());
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
		int count=0;
		String sql = " select * from system_user where MOBIL_PHONE ='"+phone+"'";
		List<SystemUser> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		if(list!=null && list.size()>0){
			count=1;
		}
		return count;
	}
	
	
	public List<SystemUser> checkUserCode(String code){
		String sql = "select * from system_user where HEALTH_CERTIFICATE ='"+code+"'";
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
	}
	
	/**
	 * @param    String
	 * @return   int
	 * 方法说明： 根据邮箱查询是否已经存在
	 */
	@Override
	public int checkEmail(String email){
		int count=0;
		String sql = " select * from system_user where email ='"+email+"'";
		List<SystemUser> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		if(list!=null && list.size()>0){
			count=1;
		}
		return count;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-0-17
	 * @param    String
	 * @return   int
	 * 方法说明： 根据证件号码查询是否已经存在
	 */
	@Override
	public int checkIdCard(String idCard){
		int count=0;
		String sql = " select * from system_user where CERTIFICATE_NO ='"+idCard+"'";
		List<SystemUser> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		if(list!=null && list.size()>0){
			count=1;
		}
		return count;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-23
	 * @param    String
	 * @return   SystemAccount
	 * 方法说明： 根据手机号码查询账号信息
	 */
	public SystemAccount getSystemAccount(String phone){
		/*String sql = " select * from system_account where user_id in ( "
                      +" select id from system_user where mobil_phone = '"+phone+"'"
                    +")";*/
		String sql = " select sa.* from system_account sa LEFT JOIN system_user su ON su.ID = sa.USER_ID where su.MOBIL_PHONE = '" + phone + "' ";
	    List<SystemAccount> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemAccount.class));
	    if(list!=null && list.size()>0){
	    	return list.get(0);
	    }else{
	    	return new SystemAccount();
	    }
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-23
	 * @param    systemAccount
	 * @return   void
	 * 方法说明： 修改账户密码
	 */
	public void updatePassword(SystemAccount systemAccount){
		String sql = "update system_account set account_password='"+systemAccount.getAccountPassword()+"' where account_id = "+systemAccount.getAccountId();
		getJdbcTemplate().update(sql.toString());
	}

	@Override
	public int checkUserName(String username) {
		// TODO Auto-generated method stub
		int count=0;
		String sql = " select * from system_account where account_name ='"+username +"'";
		List<SystemUser> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		if(list!=null && list.size()>0){
			count=1;
		}
		return count;
	}

	@Override
	public SystemUser getUserByIdCard(String idCard) {
		// TODO Auto-generated method stub
		String sql = " select * from system_user where CERTIFICATE_NO ='"+idCard+"'";
		List<SystemUser> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class));
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null; 
	}

	
	/**
	 * 通过用户id获取用户省份id
	 * @param userid
	 * @return
	 */
	@Override
	public int getUserProviceId(long userid) {
		String sql = "select t.USER_PROVINCE_ID from system_user_config t where t.USER_ID = " + userid;
		int proviceId = 0;
		//此处加上try catch的原因是为了解决当查询不到结果时，程序抛出EmptyResultDataAccessException异常
		try {  
			proviceId = getJdbcTemplate().queryForInt(sql);  
		} catch (EmptyResultDataAccessException e) {  
		    return proviceId;  
		}  
		return proviceId;
	}

	
	/**
	 * 根据用户的省份id和项目id查询项目id，查询关联表，如果关联表有数据，则该用户所归属省份包含在项目开放的省份中
	 * @param id
	 * @return
	 */
	@Override
	public int getcvSetIdFromcvId(Long id) {
		String sql = "select  css.CV_SET_ID FROM cv_set_schedule css where css.CV_SCHEDULE_ID in(select cs.SCHEDULE_ID from cv_schedule cs where cs.CV_ID = ?)";
		return getJdbcTemplate().queryForInt(sql, id);
		
	}

	@Override
	public SystemUser queryUserForLive(SystemUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from system_user u left join system_account a on u.id = a.user_id where u.id > 0");
		List list = new ArrayList();
		if(user.getUserId() != null && user.getUserId() > 0L){
			sql.append(" and u.id = ?");
			list.add(user.getUserId());
		}
		if(!StringUtil.checkNull(user.getAccountName())){
			sql.append(" and a.account_name = ?");
			list.add(user.getAccountName());
		}
		if(!StringUtil.checkNull(user.getRealName())){
			sql.append(" and u.real_name = ?");
			list.add(user.getRealName());
		}
		List<SystemUser> userList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), list.toArray());
		if(userList.size() > 0){
			return userList.get(0);
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.SystemUserDAO#getLoginLimtVo()
	 */
	@Override
	public SystemConfigVO getLoginLimtVo() {
		
		String sql = "select *  from system_login_limit";
		List<SystemConfigVO> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemConfigVO.class));
		return list.get(0);
		
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.SystemUserDAO#updateLoginErrors(com.hys.exam.model.SystemUser)
	 */
	@Override
	public int updateLoginErrors(SystemUser item) {
		
		String sql = "update system_user set login_error_num = ?,login_error_first_time=?,login_error_last_time=? where id =?";
		return  getJdbcTemplate().update(sql, item.getLoginErrorNum(),item.getLoginErrorFirstTime(),item.getLoginErrorLastTime(),item.getId());
		
		
	}
	
}
