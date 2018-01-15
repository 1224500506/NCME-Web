package com.hys.exam.dao.local.jdbc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SystemMessageManageDAO;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.qiantai.model.SystemMessageModel;


public class SystemMessageJDBCDAO extends BaseDao
		implements SystemMessageManageDAO {
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param user
	 * @param pageList
	 * 
	 * Detail : Get the user's message list.
	 */
	public void getUserMessageList(SystemUser user, PageList<SystemMessageModel> pList)
	{
		StringBuffer sql = new StringBuffer();
		StringBuffer countSql = new StringBuffer();
		
		sql.append("select * from system_message where id> 0");
		countSql.append("select count(*) from system_message where id> 0");
		
		List list = new ArrayList();
		if(user.getUserId() != null && user.getUserId() != 0L)
		{
			sql.append(" and system_user_id = ?");
			countSql.append(" and system_user_id = ?");
			list.add(user.getUserId());
		}
		
		// paging code
		if (pList.getSortCriterion()!=null && !pList.getSortCriterion().equals("")){
			sql.append(" order by ").append(pList.getSortCriterion());
			
			if (pList.getSortDirection() == SortOrderEnum.DESCENDING)
				sql.append(" desc");
		}
		
		Integer fullListSize = getJdbcTemplate().queryForInt(countSql.toString(),list.toArray());
		pList.setFullListSize(fullListSize);
		
		// 查询结果集
		List<SystemMessageModel> resList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), pList.getPageNumber(), pList.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(SystemMessageModel.class),list.toArray());
		pList.setList(resList);
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Read all message of messageType.
	 */
	public Boolean readAll(Long userId, Integer messageType) {
		StringBuffer sql = new StringBuffer();
		
		if(messageType == 1)
		{
			//Read all system message.
			sql.append("update system_message set is_read = 2 where system_user_id = ?");
			int result = getJdbcTemplate().update(sql.toString(), userId);
			if(result < 0 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(messageType == 2)
		{
			//Read all discuss message.
			return true;
		}
		else
		{
			//Read all system message and discuss message;
			sql.append("update system_message set is_read = 2 where system_user_id = ?");
			int result = getJdbcTemplate().update(sql.toString(), userId);
			
			if(result < 0 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete all message of messageType.
	 */
	public Boolean deleteAll(Long userId, Integer messageType) {
		StringBuffer sql = new StringBuffer();
		
		if(messageType == 1)
		{
			//Delete all system message.
			sql.append("delete from system_message where system_user_id = ?");
			int result = getJdbcTemplate().update(sql.toString(), userId);
			if(result < 0 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(messageType == 2)
		{
			//Delete all discuss message.
			return true;
		}
		else
		{
			//Delete all system message and discuss message;
			sql.append("delete from system_message where system_user_id = ?");
			int result = getJdbcTemplate().update(sql.toString(),userId);
			
			if(result < 0 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param messageId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete message.
	 */
	public Boolean deleteMessage(Long messageId, Integer messageType) {
		StringBuffer sql = new StringBuffer();
		
		if(messageType == 1)
		{
			//Delete system message.
			sql.append("delete from system_message where id = ?");
			int result = getJdbcTemplate().update(sql.toString(), messageId);
			if(result < 0 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(messageType == 2)
		{
			//Delete discuss message.
			return true;
		}
		else
		{
			//Delete system message and discuss message;
			sql.append("delete from system_message where id = ?");
			int result = getJdbcTemplate().update(sql.toString(), messageId);
			
			if(result < 0 )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}

	@Override
	public void SaveMessage(SystemMessageModel Message) {
		// TODO Auto-generated method stub
		
		SystemMessageModel newMessageM = new SystemMessageModel(
				getNextId("SYSTEM_MESSAGE"),
				Message.getSystem_user_id(), Message.getMessage_content(),
				new Date(), 1,Message.getCv_set_id());
		String sql = "insert into SYSTEM_MESSAGE (ID,SYSTEM_USER_ID,MESSAGE_CONTENT,MESSAGE_DATE,IS_READ) values (:id, :system_user_id, :message_content,:message_date,:is_read)";
		getSimpleJdbcTemplate().update(sql,
				new BeanPropertySqlParameterSource(newMessageM));
	}

	@Override
	public boolean findMessage(SystemMessageModel SystemMessage) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		
			//Delete system message.
			sql.append("select * from system_message where SYSTEM_USER_ID ="+SystemMessage.getSystem_user_id()+" ");
			List<SystemMessageModel> list = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(SystemMessageModel.class));
			if(list.size() == 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	
	
	
	
	
}
