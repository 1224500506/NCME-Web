package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.common.util.DateUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CaseDAO;
import com.hys.exam.dao.local.SystemMenuManageDAO;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;


public class SystemMenuManageJdbcDAO extends BaseDao
		implements SystemMenuManageDAO {
	
	
	public List<SystemMenu> getMenuList(SystemMenu searchMenu){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from system_menu where id > 0");
		
		if (searchMenu.getName() != null && !searchMenu.getName().equals("")) {
			sql.append(" AND name like '%").append(searchMenu.getName().trim()).append("%'");
		}
		if (searchMenu.getSystem_type() != null && !searchMenu.getSystem_type().equals("")) {
			sql.append(" AND system_type like '%").append(searchMenu.getSystem_type().trim()).append("%'");
		}
		if (searchMenu.getMenu_type() != null && !searchMenu.getMenu_type().equals("")) {
			sql.append(" AND menu_type like '%").append(searchMenu.getMenu_type().trim()).append("%'");
		}
		if(searchMenu.getState() != null)
		{
			sql.append(" AND state =").append(searchMenu.getState());
		}
		sql.append(" order by system_type,menu_type,id");
		return getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemMenu.class));
	}
	public Boolean addMenu(SystemMenu menu)
	{
		String add_sql = "insert into system_menu (NAME,SYSTEM_TYPE,MENU_TYPE,URL,STATE) values (:name,:system_type,:menu_type,:url,:state)";
		int result = getSimpleJdbcTemplate().update(add_sql,
				new BeanPropertySqlParameterSource(menu));
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public Boolean updateMenu(SystemMenu menu)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_menu set ");
		if(!StringUtils.checkNull(menu.getName())){
			sql.append("NAME=?,");
			values.add(menu.getName());
		}
		if(!StringUtils.checkNull(menu.getSystem_type())){
			sql.append("SYSTEM_TYPE=?,");
			values.add(menu.getSystem_type());
		}
		if(null != menu.getState()){
			sql.append("STATE=?,");
			values.add(menu.getState());
		}
		if(!StringUtils.checkNull(menu.getMenu_type())){
			sql.append("MENU_TYPE=?,");
			values.add(menu.getMenu_type());
		}
		if(!StringUtils.checkNull(menu.getUrl())){
			sql.append("URL = ?");
			values.add(menu.getUrl());
		}
		sql.append("where id = ?");
		values.add(menu.getId());
		
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public Boolean updateState(Integer id,Integer state)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_menu set ");
		if(null != state){
			sql.append("STATE=? ");
			values.add(state);
		}
		else
		{
			return false;
		}
		sql.append("where id = ?");
		values.add(id);
		
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
