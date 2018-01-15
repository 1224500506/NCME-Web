package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamPaperDAO;
import com.hys.exam.dao.local.ExamPaperFasterTacticDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.service.local.ExamPaperManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperManageImpl extends BaseMangerImpl implements
		ExamPaperManage {

	


	private ExamPaperDAO localExamPaperDAO;
	
	private ExamPaperFasterTacticDAO localExamPaperFasterTacticDAO;
	
	public ExamPaperDAO getLocalExamPaperDAO() {
		return localExamPaperDAO;
	}

	public void setLocalExamPaperDAO(ExamPaperDAO localExamPaperDAO) {
		this.localExamPaperDAO = localExamPaperDAO;
	}

	public ExamPaperFasterTacticDAO getLocalExamPaperFasterTacticDAO() {
		return localExamPaperFasterTacticDAO;
	}

	public void setLocalExamPaperFasterTacticDAO(
			ExamPaperFasterTacticDAO localExamPaperFasterTacticDAO) {
		this.localExamPaperFasterTacticDAO = localExamPaperFasterTacticDAO;
	}

	@Override
	public Long addExamPaper(ExamPaper paper) {
		//保存策略
		if((null!=paper.getIsnot_save_tactic()) && (paper.getIsnot_save_tactic()==1)){
			localExamPaperFasterTacticDAO.addExamPaperFasterTactic(paper);
		}
		return localExamPaperDAO.addExamPaper(paper);
	}

	@Override
	public void deleteExamPaper(Long[] id) {
		localExamPaperDAO.deleteExamPaper(id);
	}

	@Override
	public ExamPaper getExamPaperById(Long id) {
		return localExamPaperDAO.getExamPaperById(id);
	}

	@Override
	public Long getExamPaperId() {
		return localExamPaperDAO.getExamPaperId();
	}

	@Override
	public List<ExamPaper> getExamPaperList(ExamPaperQuery examPaperQuery) {
		return localExamPaperDAO.getExamPaperList(examPaperQuery);
	}

	@Override
	public void updateBatchExamPaper(ExamPaper paper, Long[] id) {
	}

	@Override
	public void updateExamPaper(ExamPaper paper) {
		localExamPaperDAO.updateExamPaper(paper);
	}

	@Override
	public int getExamPaperListSize(ExamPaperQuery examPaperQuery) {
		return localExamPaperDAO.getExamPaperListSize(examPaperQuery);
	}

	@Override
	public List<ExamPaper> getExamPaperListByExamId(Long examId) {
		return localExamPaperDAO.getExamPaperListByExamId(examId);
	}

	@Override
	public List<ExamPaper> getExamPaperAndChildPaper(Long[] idArr) {
		return localExamPaperDAO.getExamPaperAndChildPaper(idArr);
	}

	@Override
	public void deleteExamPaperFasterTactic(Long id) {
		localExamPaperFasterTacticDAO.deleteExamPaperFasterTactic(id);		
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long id) {
		return localExamPaperFasterTacticDAO.getExamPaperFasterTacticById(id);
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			ExamPaperFasterTactic tactic) {
		return localExamPaperFasterTacticDAO.getExamPaperFasterTacticByPaperTypeId(tactic);
	}

	@Override
	public void updateExamePaperTypeByPaperId(Long paperTypeId, Long paperId) {
		localExamPaperDAO.updateExamePaperTypeByPaperId(paperTypeId, paperId);
	}

	@Override
	public void updateExamPaperQuestion(Long paperId, Long oldQuestionID,
			Long newQuestionId, Double score) {
		localExamPaperDAO.updateExamPaperQuestion(paperId, oldQuestionID, newQuestionId, score);
	}

	@Override
	public int getExamCountByPaperIds(Integer labelId, String paperIds) {
		return localExamPaperDAO.getExamCountByPaperIds(labelId, paperIds);
	}

}
