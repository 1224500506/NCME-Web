package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.OrgDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.utils.PageList;
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
public  class OrgJDBCDAO extends BaseDao implements OrgDAO {
	
   
	@Override  
	public List<PeixunOrg> queryOrgList(PeixunOrg item,PageList pl){
		
		List <Object> list = new ArrayList<Object>();
		
		if(item.getId() != null){
			StringBuilder query = new StringBuilder("select * from peixun_org where ID="+item.getId());
			return  getJdbcTemplate().query(query.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class),list.toArray());
		}
		StringBuilder sb = new StringBuilder ("select * from peixun_org where id>0");
		
		if (item.getName() != null && !item.getName().equals("")){
			sb.append(" and name like ?");
			list.add("%"+item.getName()+"%");
		}
		
		if (item.getLevel() != null && item.getLevel() > 0){
			sb.append(" and level=?");
			list.add(item.getLevel());
		}
		
		if(item.getType() != null)
		{
			sb.append(" and type=?");
			list.add(item.getType());
		}
		sb.append(" order by id desc");
		
		return getList(PageUtil.getPageSql(sb.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), list, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
		
	}
	
	@Override  
	public List<PeixunOrg> queryOrgList(PeixunOrg item){
		
		List <Object> list = new ArrayList<Object>();
		
		if(item.getId() != null){
			StringBuilder query = new StringBuilder("select * from peixun_org where ID="+item.getId());
			return  getJdbcTemplate().query(query.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class),list.toArray());
		}
		StringBuilder sb = new StringBuilder ("select * from peixun_org where id>0");
		
		if (item.getName() != null && !item.getName().equals("")){
			sb.append(" and name like ?");
			list.add("%"+item.getName()+"%");
		}
		
		if (item.getLevel() != null && item.getLevel() > 0){
			sb.append(" and level=?");
			list.add(item.getLevel());
		}
		
		if(item.getType() != null)
		{
			sb.append(" and type=?");
			list.add(item.getType());
		}
		sb.append(" order by id desc");
		
		return getJdbcTemplate().query(sb.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class),list.toArray());
		
	}
	
	public int addPeixunOrg(PeixunOrg item)
	{
		StringBuilder query = new StringBuilder("insert into peixun_org(NAME, REGION1_ID, REGION2_ID, LEVEL, CONTACT, PHONE_NUMBER, DESCRIPTION) ");
					query.append("value (:name, :region1_Id, :region2_Id, :level, :contact, :phone_Number, :description)");		
		getSimpleJdbcTemplate().update(query.toString(), new BeanPropertySqlParameterSource(item));
		return 1;
	}
	
	public PeixunOrg getItemById(Long id) {
		
		PeixunOrg item = new PeixunOrg();
		item.setId(id);
		List<PeixunOrg> list = this.queryOrgList(item);
		return list!=null&&list.size()==1 ? list.get(0) : item;
	}
	
	public void updatePeixunOrg(PeixunOrg item) {
		
		if(item.getName() != ""){
			StringBuilder query = new StringBuilder("update peixun_org set NAME=:name, REGION1_ID=:region1_Id, REGION2_ID=:region2_Id, LEVEL=:level, CONTACT=:contact,");
				query.append(" PHONE_NUMBER=:phone_Number, DESCRIPTION=:description where ID=:id" );	
			getSimpleJdbcTemplate().update(query.toString(), new BeanPropertySqlParameterSource(item));
		}
	}

	@Override
	public void setState(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExamHospital> getHospital() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemAccount> getAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePeixunOrg(PeixunOrg item) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer getOrgListSize(PeixunOrg item)
	{
	List <Object> list = new ArrayList<Object>();
		
		if(item.getId() != null){
			StringBuilder query = new StringBuilder("select count(distinct id) from peixun_org where ID="+item.getId());
			return  getJdbcTemplate().queryForInt(query.toString());
		}
		StringBuilder sb = new StringBuilder ("select count(distinct id) from peixun_org where id>0");
		
		if (item.getName() != null && !item.getName().equals("")){
			sb.append(" and name like ?");
			list.add("%"+item.getName()+"%");
		}
		
		if (item.getLevel() != null && item.getLevel() > 0){
			sb.append(" and level=?");
			list.add(item.getLevel());
		}
		
		if(item.getType() != null)
		{
			sb.append(" and type=?");
			list.add(item.getType());
		}
		sb.append(" order by id desc");
		return getJdbcTemplate().queryForInt(sb.toString(),list.toArray());
	}
}