package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPaperDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperQuestion;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-28
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperJDBCDAO extends BaseDao implements
		ExamPaperDAO {

	public Long addExamPaper(ExamPaper examPaper) {

		// 子试卷列表
		List<ExamPaper> childPaperList = examPaper.getChildExamPaperList();
		// 试卷试题列表
		List<ExamPaperQuestion> questionList = examPaper.getExamPaperQuestionList();
		
		//设定试卷试题顺序
		if (examPaper.getPaper_mode() == Constants.PAPER_MODE_RM
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_FT
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_FT2
				|| examPaper.getPaper_mode() == Constants.PAPER_MODE_S) {
			for(int i=0;i<questionList.size();i++){
				questionList.get(i).setSeq(i+1);
			}
		}
		
		if(childPaperList != null && childPaperList.size() > 0 && examPaper.getChild_paper_num()>0){
			//设定试卷试题顺序
			for (int i = 0; i < childPaperList.size(); i++) {
				List<ExamPaperQuestion> childPaperQuestionList = childPaperList.get(i).getExamPaperQuestionList();
				for(int j=0;j<childPaperQuestionList.size();j++){
					childPaperQuestionList.get(j).setSeq(j+1);
				}
				questionList.addAll(childPaperQuestionList);
			}
			childPaperList.add(examPaper);
		}else{
			childPaperList = new ArrayList<ExamPaper>();
			childPaperList.add(examPaper);
		}
		//保存试卷和子试卷
		saveExamPaper(childPaperList);
		
		//精细试卷
		if(examPaper.getPaper_mode()==Constants.PAPER_MODE_RM){
			List<ExamPaperTactic> paperTacticList = examPaper.getPaperTacticList();
			if(paperTacticList!=null && paperTacticList.size()>0){
				//保存策略
				saveExamPaperTactic(paperTacticList);
			}
		}
		
		// 试题试题列表不为空
		if(questionList!= null && questionList.size()>0){
			saveExamPaperQuestion(questionList);
		}
		return examPaper.getId();
	}

	private void saveExamPaper(List<ExamPaper> examPaperList){
		//String sql = "insert into exam_testpaper (id, parent_id, child_paper_num, type_id, name, paper_score, paper_mode, question_num, create_date, grade, state, useful_date, paper_plan_type, examination_time, redo_num, isnot_view_score, isnot_exp_paper) values (:id, :parent_id, :child_paper_num, :type_id, :name, :paper_score, :paper_mode, :question_num, to_date(:create_date,'yyyy-mm-dd hh24:mi:ss'), :grade, :state, to_date(:useful_date,'yyyy-mm-dd hh24:mi:ss'), :paper_plan_type, :examination_time, :redo_num, :isnot_view_score, :isnot_exp_paper)";
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		//库里是短日期丫非要存长日期艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹		
		String sql = "insert into exam_testpaper (id, parent_id, child_paper_num, type_id, name, paper_score, paper_mode, question_num, create_date, grade, state, useful_date, paper_plan_type, examination_time, redo_num, isnot_view_score, isnot_exp_paper) values (:id, :parent_id, :child_paper_num, :type_id, :name, :paper_score, :paper_mode, :question_num, "
		           + FuncMySQL.shortDateForInsert("create_date") + " , :grade, :state, " + FuncMySQL.shortDateForInsert("useful_date") + " , :paper_plan_type, :examination_time, :redo_num, :isnot_view_score, :isnot_exp_paper)";
		saveList(sql, examPaperList);
	}
	
	private void saveExamPaperQuestion(List<ExamPaperQuestion> examPaperQuestionList){
		String sql = "insert into exam_paper_question (id, paper_id, question_id, question_score, seq) values (null, :paper_id, :question_id, :question_score, :seq)";
		saveList(sql, examPaperQuestionList);
	}
	
	@Override
	public void deleteExamPaper(Long[] idArr) {
		List<Object[]> list = new ArrayList<Object[]>();
		if(idArr != null && idArr.length > 0){
			List<ExamPaper> paperlist = getPaperAndChildPaper(idArr);
			if(paperlist != null && paperlist.size() > 0){
				for (int i = 0; i < paperlist.size(); i++) {
					list.add(new Object[] { paperlist.get(i).getId() });
				}
			}
			// 删除试卷试题列表
			deleteExamPaperQuestion(list);
			// 删除试卷策略列表
			deleteExamPaperTactic(list);
			// 删除试卷列表
			deleteExamPaper(list);
		}
	}

	@Override
	public Long getExamPaperId() {
		return getNextId("exam_testpaper");
	}

	@Override
	public ExamPaper getExamPaperById(Long id) {
		String sql = "select t.*, t1.name as paper_type_name from exam_testpaper t, exam_paper_type t1 where t.type_id = t1.id and (t.id=? or t.parent_id=?)";
		List<ExamPaper> list = new ArrayList<ExamPaper>();
		// 试卷列表
		list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class), id ,id);
		
		if(list!=null && list.size()>0){
			if(list.size()==1 && list.get(0).getParent_id()!=0){
				// 子试卷试题列表
				list.get(0).setExamQuestionList(getExamQuestionListByPaperId(list.get(0).getId()));
				// 子试卷试卷试题
				list.get(0).setExamPaperQuestionList(getExamPaperQuestionList(list.get(0).getId()));
				return list.get(0);
			}

			ExamPaper examPaper = null;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getParent_id() == 0){
					// 主试卷信息
					examPaper = list.remove(i);
					// 试卷试题列表
					examPaper.setExamPaperQuestionList(getExamPaperQuestionList(id));
					
					//随机试卷
					if(examPaper.getPaper_mode() == Constants.PAPER_MODE_RM){
						// 试卷策略列表
						examPaper.setPaperTacticList(getExamPaperTacticList(id));
					}
					// 子试卷列表
					examPaper.setChildExamPaperList(list);
					// 试题列表
					examPaper.setExamQuestionList(getExamQuestionListByPaperId(id));
					
				}else{
					// 子试卷试题列表
					list.get(i).setExamQuestionList(getExamQuestionListByPaperId(list.get(i).getId()));
					// 子试卷试卷试题
					list.get(i).setExamPaperQuestionList(getExamPaperQuestionList(list.get(i).getId()));
				}
			}
			return examPaper;			
		}
		return null;
	}

	@Override
	public List<ExamPaper> getExamPaperList(ExamPaperQuery examPaperQuery) {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append("select * from exam_testpaper t where id>0");
		if(examPaperQuery.getType_id() != null)
		{
			sql.append(" and exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))");
			list.add(examPaperQuery.getType_id());
		}
		
		
		if(!StringUtils.checkNull(examPaperQuery.getName())){
			list.add("%"+examPaperQuery.getName()+"%");
			sql.append(" and t.name like ? ");
		}
		if(examPaperQuery.getPaper_mode()!=null && examPaperQuery.getPaper_mode()>0){
			list.add(examPaperQuery.getPaper_mode());
			sql.append(" and t.paper_mode = ?");
		}
		if(examPaperQuery.getState()!=null && examPaperQuery.getState()>0){
			list.add(examPaperQuery.getState());
			sql.append(" and t.state = ?");
		}
		
		if(!StringUtils.checkNull(examPaperQuery.getCreate_date())){
			/*sql.append(" and to_char(t.create_date,'yyyy-mm-dd') = ? ");
			list.add(examPaperQuery.getCreate_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库			
			sql.append(" and t.create_date =  " + FuncMySQL.shortDateForUpdate(examPaperQuery.getCreate_date()) + " ");
		}		
		
		sql.append(" order by t.id desc");
		
		List<ExamPaper> paperList = getList(PageUtil.getPageSql(sql.toString(), examPaperQuery.getPageNo(), examPaperQuery
				.getPageSize()), list, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class)); 
		return paperList;
	}

	
	public int getExamPaperListSize(ExamPaperQuery examPaperQuery){
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append("select count(1) from exam_testpaper t where exists (select * from exam_paper_type t1 where t.type_id = t1.id and t1.code like CONCAT((select t2.code from exam_paper_type t2 where id = ?),'%'))");
		list.add(examPaperQuery.getType_id());
		if(!StringUtils.checkNull(examPaperQuery.getName())){
			list.add("%"+examPaperQuery.getName()+"%");
			sql.append(" and t.name like ? ");
		}
		if(examPaperQuery.getPaper_mode()!=null && examPaperQuery.getPaper_mode()>0){
			list.add(examPaperQuery.getPaper_mode());
			sql.append(" and t.paper_mode = ?");
		}
		if(examPaperQuery.getState()!=null && examPaperQuery.getState()>0){
			list.add(examPaperQuery.getState());
			sql.append(" and t.state = ?");
		}
		
		if(!StringUtils.checkNull(examPaperQuery.getCreate_date())){
			/*sql.append(" and to_char(t.create_date,'yyyy-mm-dd') = ? ");
			list.add(examPaperQuery.getCreate_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库			
			sql.append(" and t.create_date =  " + FuncMySQL.shortDateForUpdate(examPaperQuery.getCreate_date()) + " ");
			
		}	
		return getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
	}
	
	@Override
	public void updateExamPaper(ExamPaper examPaper) {
		// 子试卷列表
		List<ExamPaper> childPaperList = examPaper.getChildExamPaperList();
		
		/*
			// 试卷试题列表
			List<ExamPaperQuestion> questionList = examPaper.getExamPaperQuestionList();
			// 存放试卷ID和主试卷ID
			List<Object[]> paperIdList = new ArrayList<Object[]>();
			
			paperIdList.add(new Object[] {examPaper.getId()});
			
			//随机试卷
			if(examPaper.getPaper_mode().intValue() == Constants.PAPER_MODE_RM){
				List<ExamPaperTactic> paperTacticList = examPaper.getPaperTacticList();
				if(paperTacticList!=null){
					//删除策略
					deleteExamPaperTactic(paperIdList);
					//保存策略
					saveExamPaperTactic(paperTacticList);
				}
				
				if(childPaperList!= null && childPaperList.size()>0){
					for (int i = 0; i < childPaperList.size(); i++) {
						if(childPaperList.get(i).getExamPaperQuestionList()!= null){
							// 将子试卷试卷试题放入试题列表
							questionList.addAll(childPaperList.get(i).getExamPaperQuestionList());
							// 将子试卷ID放入删除操作参数中
							paperIdList.add(new Object[] {childPaperList.get(i).getId()});
						}
					}
				}			
			}
			
			if(questionList != null){
				// 删除试卷试题列表
				deleteExamPaperQuestion(paperIdList);
				// 添加试卷试题列表
				saveExamPaperQuestion(questionList);
			}
			
		*/
		// 修改试卷信息
		if(childPaperList != null && childPaperList.size() >0 ){
			childPaperList.add(examPaper);
		}else{
			childPaperList = new ArrayList<ExamPaper>();
			childPaperList.add(examPaper);
		}
		updateExamPaperInfo(childPaperList);
	}
	
	
	@Override
	public void updateBatchExamPaper(ExamPaper paper, Long[] id) {
	}
	
	private List<ExamPaperTactic> getExamPaperTacticList(Long paperId){
		String sql = "select * from exam_paper_base_tactic t where t.paper_id = ?";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperTactic.class), paperId);
	}
	
	
	/**
	 * 保存策略
	 * @param paperTacticList
	 */
	private void saveExamPaperTactic(List<ExamPaperTactic> paperTacticList){
		String sql ="insert into exam_paper_base_tactic (null, paper_id, question_label_id, grade, amount, question_score, question_type_id, cascade_id, cascade_name, prop_point2_id, prop_point2_name, prop_cognize_id, prop_cognize_name, prop_title_id, prop_title_name, question_type_name) values (:id, :paper_id, :question_label_id, :grade, :amount, :question_score, :question_type_id, :cascade_id, :cascade_name, :prop_point2_id, :prop_point2_name, :prop_cognize_id, :prop_cognize_name, :prop_title_id, :prop_title_name, :question_type_name)";
		saveList(sql, paperTacticList);
	}

	/**
	 * 删除试卷
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaper(List<Object[]> batchArgs){
		String d_sql = "delete from exam_exam_paper where paper_id=?";
		getSimpleJdbcTemplate().batchUpdate(d_sql, batchArgs);
		
		String sql = "delete from exam_testpaper where id = ?";
		getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 删除试卷试题
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaperQuestion(List<Object[]> batchArgs){
		String sql = "delete from exam_paper_question where paper_id = ?";
		getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 删除试卷策略
	 * @param List<Object[]> batchArgs 试卷ID
	 */
	private void deleteExamPaperTactic(List<Object[]> batchArgs){
		String sql = "delete from exam_paper_base_tactic where paper_id = ?";
		getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 通过试卷ID取主试卷和子试卷
	 * @param idArr
	 * @return
	 */
	private List<ExamPaper> getPaperAndChildPaper(Long[] idArr){
		String idStr = "";
		for (int i = 0; i < idArr.length; i++) {
			idStr += idArr[i] + ",";
		}
		if(idStr != null && !idStr.trim().equals("")){
			idStr = idStr.substring(0, idStr.length()-1);
			String sql = "SELECT * FROM exam_testpaper WHERE ID in (" + idStr + ") or PARENT_ID in (" + idStr + ")";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class));
			
		}else{
			return null;
		}
	}	
	
	
	private void updateExamPaperInfo(List<ExamPaper> examPaperList){
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_testpaper set ");
		sql.append("parent_id = :parent_id,");
		sql.append("type_id = :type_id,");
		sql.append("name = :name,");
		sql.append("paper_score = :paper_score,");
		sql.append("question_num = :question_num,");
		sql.append("grade = :grade,");
		sql.append("state = :state,");
		
		//sql.append("useful_date = to_date(:useful_date,'yyyy-MM-dd'),");
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库		
		sql.append("useful_date = " + FuncMySQL.shortDateForInsert("useful_date") + " ,");
		
		sql.append("paper_plan_type = :paper_plan_type,");
		sql.append("examination_time = :examination_time,");
		sql.append("isnot_view_score = :isnot_view_score,");
		sql.append("isnot_exp_paper = :isnot_exp_paper,");
		sql.append("redo_num = :redo_num");
		sql.append(" where id = :id");
		saveList(sql.toString(), examPaperList);
	}
	
	private List<ExamQuestion> getExamQuestionListByPaperId(Long paperId){
		String sql = "select t.*, (select count(1) from exam_question t2 where t2.parent_id = t.id) as childQuestionNum, t1.question_score from exam_question t, exam_paper_question t1 where t.id = t1.question_id and t1.paper_id = ? order by t.question_label_id, t1.seq";
		String sqlKey = "select * from exam_question_key t where t.question_id = ? order by t.seq";
		List<ExamQuestion> questionList = new ArrayList<ExamQuestion>();
		questionList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class),paperId);
		
		if(questionList!=null && questionList.size()>0){
			for(int i=0;i<questionList.size();i++){
				// 如果是父试题
				if(questionList.get(i).getParent_id() == 0){
					// 查询子试题
					String childSql = "select * from exam_question q where parent_id = ?";
					List<ExamQuestion> childQuestionList = getJdbcTemplate().query(childSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class), questionList.get(i).getId());
					if(childQuestionList!=null && childQuestionList.size()>0){
						for (int j = 0; j < childQuestionList.size(); j++) {
							// 子试题选项
							childQuestionList.get(j).setQuestionKeyList(getJdbcTemplate().query(sqlKey, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionKey.class), childQuestionList.get(j).getId()));
						}
					}
					questionList.get(i).setChildQuestionList(childQuestionList);
				}	
				// 试题选项
				questionList.get(i).setQuestionKeyList(getJdbcTemplate().query(sqlKey, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestionKey.class), questionList.get(i).getId()));
			}
		}
		
		return questionList;
	}
	
	/**
	 * 试卷试题列表
	 * @param paperId
	 * @return
	 */
	private List<ExamPaperQuestion> getExamPaperQuestionList(Long paperId){
		String sql = "SELECT * FROM exam_paper_question WHERE PAPER_ID = ? ORDER BY SEQ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperQuestion.class), paperId);
	}

	public List<ExamPaper> getExamPaperListByExamId(Long examId) {
		//String sql = "select t.* from exam_testpaper t,exam_examination t1,exam_exam_paper t2 where t1.id = t2.exam_id and t2.paper_id = t.id and t.state = 1 and (t.useful_date is null or TO_DATE(TO_CHAR(t.useful_date, 'YYYY-MM-DD'), 'YYYY-MM-DD') >= TO_DATE(TO_CHAR(sysdate(), 'YYYY-MM-DD'), 'YYYY-MM-DD')) and t1.id = ? order by t2.seq";
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		String sql = "select t.* from exam_testpaper t,exam_examination t1,exam_exam_paper t2 where t1.id = t2.exam_id and t2.paper_id = t.id and t.state = 1 and (t.useful_date is null or t.useful_date >= sysdate()) and t1.id = ? order by t2.seq";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class),examId);
	}

	public List<ExamPaper> getExamPaperAndChildPaper(Long[] idArr) {
		String idStr = "";
		for (int i = 0; i < idArr.length; i++) {
			idStr += idArr[i] + ",";
		}
		if(idStr != null && !idStr.trim().equals("")){
			idStr = idStr.substring(0, idStr.length()-1);
			String sql = "SELECT * FROM exam_testpaper WHERE ID in (" + idStr + ") or PARENT_ID in (" + idStr + ")";
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class));
			
		}else{
			return null;
		}
	}

	@Override
	public void updateExamePaperTypeByPaperId(Long paperTypeId, Long paperId) {
		String sql = "update exam_testpaper t set t.type_id = ? where t.id = ? or t.parent_id = ?";
		getJdbcTemplate().update(sql,paperTypeId,paperId,paperId);
	}

	@Override
	public void updateExamPaperQuestion(Long paperId, Long oldQuestionID,
			Long newQuestionId, Double score) {
		
		String seq_sql = "select max(seq) from exam_paper_question where paper_id=? and question_id=?";
		String del_sql = "delete from exam_paper_question where paper_id=? and question_id=?";
		String add_sql = "insert into exam_paper_question (id, paper_id, question_id, question_score, seq) values (null, :paper_id, :question_id, :question_score, :seq)";
		int seq = getJdbcTemplate().queryForInt(seq_sql);
		getJdbcTemplate().update(del_sql, paperId,oldQuestionID);
		
		ExamPaperQuestion pq = new ExamPaperQuestion();
		pq.setPaper_id(paperId);
		pq.setQuestion_id(newQuestionId);
		pq.setQuestion_score(score);
		pq.setSeq(seq);
		getSimpleJdbcTemplate().update(add_sql, new BeanPropertySqlParameterSource(pq));
	}

	@Override
	public int getExamCountByPaperIds(Integer labelId, String paperIds) {
		/*String[] ids = paperIds.split(",");
		List<Integer> idArr = new ArrayList<Integer>();
		for(int i = 0 ; i < ids.length ; i ++)
		{
			idArr.add(Integer.valueOf(ids[i].trim()));
		}*/
		
		String GET_SQL = "select count(id) from (select DISTINCT eq.* from EXAM_TESTPAPER tp LEFT JOIN EXAM_PAPER_QUESTION pq on TP.ID = PQ.PAPER_ID LEFT JOIN EXAM_QUESTION eq on pq.question_id=eq.id where eq.question_label_id = ? and TP.id in (" + paperIds + ")) group by question_label_id ORDER BY question_label_id";
		try
		{
			return getJdbcTemplate().queryForInt(GET_SQL, labelId);
		}catch(Exception e)
		{
			return 0;
		}
		
	}	
}
