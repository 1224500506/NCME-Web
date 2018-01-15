package com.hys.exam.dao.local.jdbc;


import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.FeedbackManageDAO;
import com.hys.exam.model.Feedback;

public class FeedbackManageJDBCDAO  extends BaseDao  implements FeedbackManageDAO {

	/**
	 * dao添加数据
	 */
	@Override
	public boolean add(Feedback feedback) {
		feedback.setId(this.getSequence("FEEDBACK"));
		String sql="INSERT INTO feedback (CREATER,TELPHONE,CONTENT,SYSTEM,SYSTEMVERSION,IMAGE,SITE,STATE) VALUES(:creater,:telphone,:content,:system,:systemversion,:image,:site,:state);";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(feedback));

		return true;
	}
	/**
	 * 图片添加的方法
	 */
	@Override
	public boolean addImage(String result) {
		String sql="insert into feedback_image (image) VALUES ("+result+");";
		getJdbcTemplate().update(sql);
	    return true;
	}
	
	@Override
	public int addFeedback(Feedback feedback) {
		
		return 0;
	}

	

}
