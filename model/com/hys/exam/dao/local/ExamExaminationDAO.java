package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-11
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamExaminationDAO {

	
	/**
	 * 增加考场
	 * @param ExamExamination exam
	 * @return 考场ID
	 */
	public Long  addExamination(ExamExamination exam);
	
	/**
	 * 批量删除考场
	 * @param Long id
	 * @return
	 */
	public void deleteExamination(List<Long> id);

	/**
	 * 批量删除考场
	 * 
	 * @param id
	 */
	public void deleteExaminationList(List<Long> id);
	
	//恢复
	public void recoverExaminationList(List<Long> id);

	/**
	 * 修改考场含考场试卷
	 * @param ExamExamination exam
	 */
	public void updateExaminationById(ExamExamination exam);
	
	/**
	 * 获取考场信息和考场试卷
	 * @param id
	 * @return
	 */
	public ExamExamination getExamExaminationById(Long id);
	
	/**
	 * 
	 * FunName:getExaminationList
	 * Description: 查询考试列表
	 * @param examExaminationQuery 考试查询对象
	 * @return: List<ExamExamination>
	 * @Create Time: 2010-1-20 下午02:33:50
	 */
	public List<ExamExamination> getExaminationList(ExamExaminationQuery examExaminationQuery);

	/**
	 * 
	 * FunName:getExaminationListSize
	 * Description: 查询考试列表总条数
	 * @param examExaminationQuery 考试查询对象
	 * @return: int
	 * @Create Time: 2010-1-20 下午02:33:53
	 */
	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery);
	
	
	//*********************************************************************************
	
	/**
	 * 通过考试分类取考试列表
	 * @param examTypeId 考试分类ID
	 * @return 考试列表 List<ExamExamination>
	 */
	public List<ExamExamination> getExaminationListByExamTypeId(Long examTypeId);
	
	/**
	 * 通过考试ID 数组取考试列表
	 * @param query
	 * @return
	 */
	public List<ExamExamination> getExaminationListByIds(ExamExaminationQuery query);
	
	/**
	 * 通过考试ID 数组取size
	 * @param query
	 * @return
	 */
	public int getExaminationListByIdsSize(ExamExaminationQuery query);
		
	/**
	 * 通过考试ID 修改考试分类
	 * @param typeId 分类ID
	 * @param id 考试ID
	 */
	public void updateExaminationTypeByExamId(Long typeId,Long id);
	
}
