package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamExaminationDAO;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.service.local.ExamExaminationManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminationManageImpl extends BaseMangerImpl implements
		ExamExaminationManage {

	
	private ExamExaminationDAO localExamExaminationDAO;
	
	public ExamExaminationDAO getLocalExamExaminationDAO() {
		return localExamExaminationDAO;
	}

	public void setLocalExamExaminationDAO(
			ExamExaminationDAO localExamExaminationDAO) {
		this.localExamExaminationDAO = localExamExaminationDAO;
	}

	@Override
	public Long addExamination(ExamExamination exam) {
		return localExamExaminationDAO.addExamination(exam);
	}

	@Override
	public void deleteExamination(List<Long> id) {
		localExamExaminationDAO.deleteExamination(id);
	}

	@Override
	public ExamExamination getExamExaminationById(Long id) {
		return localExamExaminationDAO.getExamExaminationById(id);
	}

	@Override
	public List<ExamExamination> getExaminationList(
			ExamExaminationQuery examExaminationQuery) {
		return localExamExaminationDAO.getExaminationList(examExaminationQuery);
	}
	
	@Override
	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery) {
		return localExamExaminationDAO.getExaminationListSize(examExaminationQuery);
	}

	@Override
	public void updateExaminationById(ExamExamination exam) {
		localExamExaminationDAO.updateExaminationById(exam);
	}	

	@Override
	public List<ExamExamination> getExaminationListByIds(
			ExamExaminationQuery query) {
		return localExamExaminationDAO.getExaminationListByIds(query);
	}

	@Override
	public int getExaminationListByIdsSize(ExamExaminationQuery query) {
		return localExamExaminationDAO.getExaminationListByIdsSize(query);
	}

	@Override
	public void updateExaminationTypeByExamId(Long typeId, Long id) {
		localExamExaminationDAO.updateExaminationTypeByExamId(typeId, id);
	}

	@Override
	public void deleteExaminationList(List<Long> id) {
		localExamExaminationDAO.deleteExaminationList(id);
	}
	
	//恢复
	@Override
	public void recoverExaminationList(List<Long> id){
		this.localExamExaminationDAO.recoverExaminationList(id);
	}
}
