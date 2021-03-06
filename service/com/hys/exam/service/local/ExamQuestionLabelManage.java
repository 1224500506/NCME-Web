package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamQuestionLabel;
import com.hys.framework.service.BaseService;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-19
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionLabelManage extends BaseService {
	
	/**
	 * 取试题题型 
	 * @param type 0: 取所有; 1： 不是子试题题型
 	 * @return
	 */
	public List<ExamQuestionLabel> getQuestionLabel(int type);

}
