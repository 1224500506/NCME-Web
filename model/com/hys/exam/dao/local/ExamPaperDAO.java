package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.queryObj.ExamPaperQuery;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-18
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPaperDAO {
	
	/**
	 * 添加试卷返回试卷ID
	 * @param paper
	 * @return
	 */
	public Long addExamPaper(ExamPaper paper);
	
	/**
	 * 取ID
	 * @return
	 */
	public Long getExamPaperId();
	
	/**
	 * 取试卷
	 * @return
	 */
	public ExamPaper getExamPaperById(Long id);
	
	/**
	 * 删除试卷
	 * @param id
	 */
	public void deleteExamPaper(Long[] id);
	
	/**
	 * 修改试卷
	 * @param paper
	 */
	public void updateExamPaper(ExamPaper paper);
	
	/**
	 * 试卷查询分页
	 * @param ExamPaper
	 * @return
	 */
	public List<ExamPaper> getExamPaperList(ExamPaperQuery examPaperQuery);
	public int getExamPaperListSize(ExamPaperQuery examPaperQuery);
	
	/**
	 * 批量修改试卷
	 * @param paper
	 * @param id
	 */
	public void updateBatchExamPaper(ExamPaper paper,Long[] id);
	
	
	/**
	 * 通过考场ID 取 试卷列表
	 * @param examId
	 * @return
	 */
	public List<ExamPaper> getExamPaperListByExamId(Long examId);
	
	/**
	 * 查询主试卷和子试卷的List
	 * @param idArr
	 * @return
	 */
	public List<ExamPaper> getExamPaperAndChildPaper(Long[] idArr);
	
	/**
	 * 通过试卷ID修改试卷分类
	 * @param paper_type_id 试卷分类ID
	 * @param paperId	试卷ID
	 */
	public void updateExamePaperTypeByPaperId(Long paper_type_id,Long paperId);
	
	/**
	 * 更换试卷试题
	 * @param paperId
	 * @param oldQuestionID
	 * @param newQuestionId
	 * @param score
	 */
	public void updateExamPaperQuestion(Long paperId,Long oldQuestionID,Long newQuestionId,Double score);

	/**
	 * 
	 * @param paperIds
	 */
	public int getExamCountByPaperIds(Integer labelId, String paperIds);
}
