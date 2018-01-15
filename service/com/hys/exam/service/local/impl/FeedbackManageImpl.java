package com.hys.exam.service.local.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.local.FeedbackManageDAO;
import com.hys.exam.model.Feedback;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.service.local.FeedbackManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class FeedbackManageImpl extends BaseMangerImpl implements FeedbackManage {

	private FeedbackManageDAO feedbackManageJDBCDAO;
	
	
	public FeedbackManageDAO getFeedbackManageJDBCDAO() {
		return feedbackManageJDBCDAO;
	}

	public void setFeedbackManageJDBCDAO(FeedbackManageDAO feedbackManageJDBCDAO) {
		this.feedbackManageJDBCDAO = feedbackManageJDBCDAO;
	}

	/**
	 * 首页--意见反馈，添加
	 */
	@Override
	public boolean add(Feedback feedback) {
		boolean b = feedbackManageJDBCDAO.add(feedback);
		if (b) {
			return true;
		}
		return false;
	}
	/**
	 * 首页--意见反馈，图片添加
	 */
	@Override
	public boolean addImage(String result) {
		boolean b = feedbackManageJDBCDAO.addImage(result);
		if (b) {
			return true;
		}
		return false;
	}

	
	
	/**
	 * 
	 */
	@Override
	public int addFeedback(Feedback feedback) {
		return feedbackManageJDBCDAO.addFeedback(feedback);
	}
	@Override
	public BeanFactory getBeanFacory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getFeedbackPageList(PageList pl, Feedback feedback) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SystemSite> getSiteListByFeebackId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback getfeedbackyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateState(Long id, int state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFeedbackById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteFeedbackType(Long[] typeIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

	

	

}
