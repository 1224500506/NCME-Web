package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPaperFasterTacticDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperFasterTacticJDBCDAO extends BaseDao
		implements ExamPaperFasterTacticDAO {

	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC = "insert into exam_paper_tactic (id, paper_id, tactic_name, question_type_id, question_type_name, exam_paper_type_id) values (:id, :paper_id, :tactic_name, :question_type_id, :question_type_name, :exam_paper_type_id)";

	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC1 = "Insert Into exam_paper_tactic1 (tactic_id, question_label_id, num, score) Values (:tactic_id, :question_label_id, :num, :score)";
	
	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC2 = "Insert Into exam_paper_tactic2 (tactic_id, study1_id, study1_name, study2_id, study2_name, study3_id, study3_name, unit_id, unit_name, point_id, point_name) Values (:tactic_id, :study1_id, :study1_name, :study2_id, :study2_name, :study3_id, :study3_name, :unit_id, :unit_name, :point_id, :point_name)";
	
	private static final String SQL_ADD_EXAM_PAPER_FASTER_TACTIC3 = "Insert Into exam_paper_tactic3 (tactic_id, point2_id, point2_name, cognize_id, cognize_name, title_id, title_name) Values (:tactic_id, :point2_id, :point2_name, :cognize_id, :cognize_name, :title_id, :title_name)";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC = "Delete From exam_paper_tactic Where Id = ?";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC1 = "Delete From exam_paper_tactic1 Where tactic_id = ?";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC2 = "Delete From exam_paper_tactic2 Where tactic_id = ?";
	
	private static final String DELETE_EXAM_PAPER_FASTER_TACTIC3 = "Delete From exam_paper_tactic3 Where tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPER_ID = "select * From exam_paper_tactic Where paper_id = ?";
	
//	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_ID = "select * From exam_paper_tactic Where Id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC1_BY_ID = "select * From exam_paper_tactic1 t1 Where t1.tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC2_BY_ID = "select * From exam_paper_tactic2 t2 Where t2.tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC3_BY_ID = "select * From exam_paper_tactic3 t3 Where t3.tactic_id = ?";
	
	private static final String SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPERTYPEID = "Select * From exam_paper_tactic t Where Exists (Select * From Exam_Paper_Type T1 Where t.exam_paper_type_id = T1.Id And T1.Code Like Concat((Select T2.Code From Exam_Paper_Type T2 Where Id = ?),'%'))";
	
	public void addExamPaperFasterTactic(ExamPaper paper) {
		
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FT){
			ExamPaperFasterTactic tactic = paper.getPaperFasterTactic();
			
			Long id = getNextId("exam_paper_tactic");
			tactic.setId(id);
			tactic.setPaper_id(paper.getId());
			
			getSimpleJdbcTemplate()
			.update(SQL_ADD_EXAM_PAPER_FASTER_TACTIC, new BeanPropertySqlParameterSource(tactic));
			
			List<ExamPaperFasterTactic1> tactic1List =  tactic.getExamPaperFasterTactic1();
			
			List<ExamPaperFasterTactic2> tactic2List =  tactic.getExamPaperFasterTactic2();
			
			List<ExamPaperFasterTactic3> tactic3List =  tactic.getExamPaperFasterTactic3();
			
			
			//保存 策略 题型
			for(ExamPaperFasterTactic1 t1: tactic1List){
				t1.setTactic_id(id);
				t1.setNum(null);
				t1.setScore(null);
			}
			addExamPaperFasterTactic1(tactic1List);
			
			//保存 策略 级联属性
			for(ExamPaperFasterTactic2 t2 : tactic2List){
				t2.setTactic_id(id);
			}
			addExamPaperFasterTactic2(tactic2List);
	
			//保存 策略 非级联属性
			if((tactic3List!=null) && (!tactic3List.isEmpty())){
				for(ExamPaperFasterTactic3 t3 : tactic3List){
					t3.setTactic_id(id);
				}
				addExamPaperFasterTactic3(tactic3List);
			}
		}
		
		if(paper.getPaper_mode() == Constants.PAPER_MODE_FT2){
			
		}
	}

	public void deleteExamPaperFasterTactic(Long paper_id) {
		
		ExamPaperFasterTactic t = getExamPaperFasterTacticByPaperId(paper_id);
		
		getJdbcTemplate().update(DELETE_EXAM_PAPER_FASTER_TACTIC,t.getId());
		getJdbcTemplate().update(DELETE_EXAM_PAPER_FASTER_TACTIC1,t.getId());
		getJdbcTemplate().update(DELETE_EXAM_PAPER_FASTER_TACTIC2,t.getId());
		getJdbcTemplate().update(DELETE_EXAM_PAPER_FASTER_TACTIC3,t.getId());
	}
	
	
	private ExamPaperFasterTactic getExamPaperFasterTacticByPaperId(Long paper_id) {
		ExamPaperFasterTactic tactic = getJdbcTemplate().queryForObject(SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPER_ID,ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperFasterTactic.class),paper_id);
		return tactic;
	}
	
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long paper_id) {
		ExamPaperFasterTactic tactic = getJdbcTemplate().queryForObject(SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPER_ID,ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperFasterTactic.class),paper_id);
		
		tactic.setExamPaperFasterTactic1(getJdbcTemplate().query(SQL_GET_EXAM_PAPER_FASTER_TACTIC1_BY_ID, ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperFasterTactic1.class),tactic.getId()));
		
		tactic.setExamPaperFasterTactic2(getJdbcTemplate().query(SQL_GET_EXAM_PAPER_FASTER_TACTIC2_BY_ID, ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperFasterTactic2.class),tactic.getId()));
		
		tactic.setExamPaperFasterTactic3(getJdbcTemplate().query(SQL_GET_EXAM_PAPER_FASTER_TACTIC3_BY_ID, ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperFasterTactic3.class),tactic.getId()));
		
		return tactic;
	}

	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			ExamPaperFasterTactic tactic) {
		return getJdbcTemplate().query(SQL_GET_EXAM_PAPER_FASTER_TACTIC_BY_PAPERTYPEID, ParameterizedBeanPropertyRowMapper
				.newInstance(ExamPaperFasterTactic.class),tactic.getExam_paper_type_id());
	}
	
	
	/**
	 * 保存　策略　题型
	 * @param tactic1List
	 */
	private void addExamPaperFasterTactic1(List<ExamPaperFasterTactic1> tactic1List){
		saveList(SQL_ADD_EXAM_PAPER_FASTER_TACTIC1,tactic1List);
	}

	/**
	 * 保存　策略　级联属性
	 * @param tactic1List
	 */
	private void addExamPaperFasterTactic2(List<ExamPaperFasterTactic2> tactic2List){
		saveList(SQL_ADD_EXAM_PAPER_FASTER_TACTIC2,tactic2List);
	}
	
	/**
	 * 保存　策略　非级联属性
	 * @param tactic1List
	 */
	private void addExamPaperFasterTactic3(List<ExamPaperFasterTactic3> tactic3List){
		saveList(SQL_ADD_EXAM_PAPER_FASTER_TACTIC3,tactic3List);
	}

}
