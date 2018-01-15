package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamExaminationDAO;
import com.hys.exam.model.ExamExaminOrg;
import com.hys.exam.model.ExamExaminPaper;
import com.hys.exam.model.ExamExaminUser;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

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
public class ExamExaminationJDBCDAO extends BaseDao implements
		ExamExaminationDAO {

	@Override
	public Long addExamination(ExamExamination exam) {
		Long id = getNextId("exam_examination");
		exam.setId(id);
		//String sql = "insert into exam_examination (id, exam_type_id, name, exam_times, exam_time, exam_nature, isnot_see_result, pass_condition, pass_value, state, isnot_see_test_report, start_time, end_time, isnot_decide, isnot_online, exam_type, exam_num, is_cut_screen, cut_screen_num, paper_display_mode, question_display_mode, single_scoring, paper_scoring, create_time, remark, zyy_exam_type, isopen_nextlevel, site_id) values (:id, :exam_type_id, :name, :exam_times, :exam_time, :exam_nature, :isnot_see_result, :pass_condition, :pass_value, :state, :isnot_see_test_report, to_date(:start_time,'yyyy-mm-dd hh24:mi:ss'), to_date(:end_time,'yyyy-mm-dd hh24:mi:ss'), :isnot_decide, :isnot_online, :exam_type, :exam_num, :is_cut_screen, :cut_screen_num, :paper_display_mode, :question_display_mode, :single_scoring, :paper_scoring, sysdate(), :remark, :zyy_exam_type, :isopen_nextlevel, :siteId)";
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		//库里是短日期丫非要存长日期艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹
		String sql = "insert into exam_examination (id, exam_type_id, name, exam_times, exam_time, exam_nature, isnot_see_result, pass_condition, pass_value, state, isnot_see_test_report, start_time, end_time, isnot_decide, isnot_online, exam_type, exam_num, is_cut_screen, cut_screen_num, paper_display_mode, question_display_mode, single_scoring, paper_scoring, create_time, remark, zyy_exam_type, isopen_nextlevel, site_id) values (:id, :exam_type_id, :name, :exam_times, :exam_time, :exam_nature, :isnot_see_result, :pass_condition, :pass_value, :state, :isnot_see_test_report, "
		           + FuncMySQL.shortDateForUpdate(exam.getStart_time()) + " , " + FuncMySQL.shortDateForUpdate(exam.getEnd_time()) + " , :isnot_decide, :isnot_online, :exam_type, :exam_num, :is_cut_screen, :cut_screen_num, :paper_display_mode, :question_display_mode, :single_scoring, :paper_scoring, sysdate(), :remark, :zyy_exam_type, :isopen_nextlevel, :siteId)";
		getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(exam));
		
		List<ExamExaminPaper> examPaperList = exam.getExaminPaperList();
		if(examPaperList != null)
		{
			for(ExamExaminPaper ep : examPaperList){
				ep.setExam_id(id);
			}
			saveExamTestPaper(examPaperList);	
		}
		
		List<ExamExaminUser> examUser = exam.getExaminUserList();
		if(examUser != null)
		{
			for(ExamExaminUser user : examUser){
				user.setExam_id(id);
			}
			saveExamUser(examUser);	
		}
		List<ExamExaminUser> examConsider = exam.getExaminConsierList();
		if(examConsider != null)
		{
			for(ExamExaminUser con : examConsider){
				con.setExam_id(id);
			}
			saveExamUser(examConsider);	
		}
		List<ExamExaminOrg> examOrg = exam.getExaminOrgList();
		if(examOrg != null)
		{
			for(ExamExaminOrg org : examOrg){
				org.setExam_id(id);
			}		
			saveExamOrg(examOrg);	
		}				
		return id;
	}

	@Override
	public void deleteExamination(List<Long> id) {
		String sql = "delete from exam_examination where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		deleteExamTestPaperByExamId(id);
		updateBatch(sql, batch);
	}

	@Override
	public void deleteExaminationList(List<Long> id) {
		String sql = "update exam_examination set state=? where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { Constants.STATUS_2, id.get(i) };
			batch.add(values);
		}
		deleteExamTestPaperByExamId(id);
		deleteExamOrgByExamId(id);
		deleteExamUserByExamId(id);
		updateBatch(sql, batch);
	}
	
	//恢复
	@Override
	public void recoverExaminationList(List<Long> id){
		String sql = "update exam_examination set state=? where id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { Constants.STATUS_1, id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}

	@Override
	public ExamExamination getExamExaminationById(Long id) {
		ExamExamination exam = new ExamExamination();
		try{
			//String sql = "select t.id,t.exam_type_id,t1.name as exam_type_name,t.name,t.exam_times,t.exam_time,t.exam_nature,t.isnot_see_result,t.pass_condition,t.pass_value,t.state,t.isnot_see_test_report,to_char(t.start_time, 'yyyy-mm-dd hh:mi:ss') as start_time,to_char(t.end_time, 'yyyy-mm-dd hh:mi:ss') as end_time,t.isnot_decide,t.isnot_online,t.exam_type,t.exam_num,t.is_cut_screen,t.cut_screen_num,t.paper_display_mode,t.question_display_mode,t.single_scoring,t.paper_scoring, to_char(t.create_time, 'yyyy-mm-dd hh:mi:ss') as create_time, t.remark from exam_examination t,exam_exam_type t1 where t.exam_type_id = t1.id and t.id = ?";
			String sql_ = "select t.*,t1.name as exam_type_name, t2.alias_name, t2.domain_name " +
					" from exam_examination t left join exam_exam_type t1 on t.exam_type_id = t1.id" +
					" left join system_site t2 on t2.id = t.site_id " +
					" where t.id = ?";
			exam = getJdbcTemplate().queryForObject(sql_, ParameterizedBeanPropertyRowMapper.newInstance(ExamExamination.class),id);
			exam.setExaminPaperList(getExamTestPaperByExamId(id));
			exam.setExaminUserList(getExamUserByExamId(id));
			exam.setExaminConsierList(getExamConsiderByExamId(id));
			exam.setExaminOrgList(getExamOrgByExamId(id));
		}catch(Exception e){
			System.out.println(e);
		}
		return exam;
	}

	@Override
	public List<ExamExamination> getExaminationList(
			ExamExaminationQuery examExaminationQuery) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		String[] commonTypeCode = null;
		if(examExaminationQuery.getExam_type_id() != null)
		{
			commonTypeCode = examExaminationQuery.getExam_type_id().split(",");
		}
		sql.append("SELECT t.*,t2.NAME as exam_type_name FROM EXAM_EXAMINATION t join EXAM_EXAM_TYPE t2 on t.exam_type_id=t2.id WHERE 1=1");		
		if (commonTypeCode != null && commonTypeCode.length > 0) {
            for (int i = 0; i < commonTypeCode.length; i++) {
                if (i == 0) {
                    sql.append(" and ( t2.id = " + commonTypeCode[i]);
                } else {
                    sql.append(" or t2.id = " + commonTypeCode[i]);
                }
            }
            sql.append(" ) ");
        }
		// 拼查询条件
		setParams(sql, examExaminationQuery, list);
		
		sql.append(" order by t.create_time desc");

		return getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(),
						examExaminationQuery.getPageNo(),
						examExaminationQuery.getPageSize()),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExamination.class), list.toArray());		
	}
	

	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		String[] commonTypeCode = null;
		if(examExaminationQuery.getExam_type_id() != null)
		{
			commonTypeCode = examExaminationQuery.getExam_type_id().split(",");
		}
		sql.append("SELECT count(1) as exam_type_name FROM EXAM_EXAMINATION t join EXAM_EXAM_TYPE t2 on t.exam_type_id=t2.id WHERE 1=1");		
		if (commonTypeCode != null && commonTypeCode.length > 0) {
            for (int i = 0; i < commonTypeCode.length; i++) {
                if (i == 0) {
                    sql.append(" and ( t2.id = " + commonTypeCode[i]);
                } else {
                    sql.append(" or t2.id = " + commonTypeCode[i]);
                }
            }
            sql.append(" ) ");
        }
		// 拼查询条件
		setParams(sql, examExaminationQuery, list);
		int num = getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
		return num;
	}
	
	private void setParams(StringBuffer sql,ExamExaminationQuery query,List<Object> list){
		
		if(!StringUtils.checkNull(query.getName())){
			sql.append(" and t.name like ? ");
			list.add("%"+query.getName()+"%");
		}
		
		/*if(query.getState()>0){
			sql.append(" and t.state = ? ");
			list.add(query.getState());
		}*/
		
		if(!StringUtils.checkNull(query.getCreate_time())){
			/*sql.append(" and to_char(t.create_time,'yyyy-mm-dd') = ? ");
			list.add(query.getCreate_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库			
			sql.append(" and t.create_time =  " + FuncMySQL.shortDateForUpdate(query.getCreate_time()) + " ");
		}
		
		if(query.getExam_type() != null && query.getExam_type() > 0){
			sql.append(" and t.exam_type = ? ");
			list.add(query.getExam_type());
		}
		if(query.getState() != null && query.getState() >= 0){
			sql.append(" and t.state = ? ");
			list.add(query.getState());
		}
		else
		{
			sql.append(" and t.state != ? ");
			list.add(-1);
		}
		
	}

	@SuppressWarnings("unchecked")
	public void updateExaminationById(ExamExamination exam) {
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update exam_examination set ");
		if(!StringUtils.checkNull(exam.getName())){
			sql.append("name = ?,");
			values.add(exam.getName());
		}
		
		if(null != exam.getExam_times()){
			sql.append("exam_times = ?,");
			values.add(exam.getExam_times());
		}
		if(null != exam.getExam_time()){
			sql.append("exam_time=?,");
			values.add(exam.getExam_time());
		}
		if(null != exam.getExam_nature()){
			sql.append("exam_nature = ?,");
			values.add(exam.getExam_nature());
		}
		if(null != exam.getIs_cut_screen()){
			sql.append("is_cut_screen = ?,");
			values.add(exam.getIs_cut_screen());
		}
		if(null != exam.getIsnot_see_test_report()){
			sql.append("isnot_see_test_report=?,");
			values.add(exam.getIsnot_see_test_report());
		}
		if(null != exam.getPass_condition()){
			sql.append("pass_condition = ?,");
			values.add(exam.getPass_condition());
		}
		if(null != exam.getPass_value()){
			sql.append("pass_value=?,");
			values.add(exam.getPass_value());
		}
		if(null != exam.getState()){
			sql.append("state=?,");
			values.add(exam.getState());
		}
		if(!StringUtils.checkNull(exam.getStart_time())){
			/*sql.append("start_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			values.add(exam.getStart_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			//库里是短日期丫非要存长日期艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹
			sql.append("start_time = " + FuncMySQL.shortDateForUpdate(exam.getStart_time()) + " ,");
		}
		if(!StringUtils.checkNull(exam.getEnd_time())){
			/*sql.append("end_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			values.add(exam.getEnd_time());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			//库里是短日期丫非要存长日期艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹艹
			sql.append("end_time = " + FuncMySQL.shortDateForUpdate(exam.getEnd_time()) + " ,");
		}
		if(null != exam.getIsnot_decide()){
			sql.append("isnot_decide=?,");
			values.add(exam.getIsnot_decide());
		}
		if(null != exam.getIsnot_online()){
			sql.append("isnot_online=?,");
			values.add(exam.getIsnot_online());
		}
		if(null != exam.getExam_type()){
			sql.append("exam_type=?,");
			values.add(exam.getExam_type());
		}
		if(null != exam.getExam_num()){
			sql.append("exam_num=?,");
			values.add(exam.getExam_num());
		}
		if(null != exam.getCut_screen_num()){
			sql.append("cut_screen_num=?,");
			values.add(exam.getCut_screen_num());
		}
		if(null != exam.getPaper_display_mode()){
			sql.append("paper_display_mode=?,");
			values.add(exam.getPaper_display_mode());
		}
		if(null != exam.getQuestion_display_mode()){
			sql.append("question_display_mode=?,");
			values.add(exam.getQuestion_display_mode());
		}
		if(null != exam.getSingle_scoring()){
			sql.append("single_scoring=?,");
			values.add(exam.getSingle_scoring());
		}
		if(null != exam.getPaper_scoring()){
			sql.append("paper_scoring=?,");
			values.add(exam.getPaper_scoring());
		}
		
		if(!StringUtils.checkNull(exam.getRemark())){
			sql.append("remark = ?,");
			values.add(exam.getRemark());
		}
		
		if(null != exam.getIsopen_nextlevel()){
			sql.append("isopen_nextlevel = ?,");
			values.add(exam.getIsopen_nextlevel());
		}
		
		if(null != exam.getSiteId() && exam.getSiteId()>0){
			sql.append("site_id = ?,");
			values.add(exam.getSiteId());
		}
		
		sql.append("create_time = sysdate() where id = ?");
		values.add(exam.getId());
		
		
		List<Long> examId = new ArrayList<Long>();
		examId.add(exam.getId());
		deleteExamTestPaperByExamId(examId);
		deleteExamOrgByExamId(examId);
		deleteExamUserByExamId(examId);
		
		List<ExamExaminPaper> examPaperList = exam.getExaminPaperList();
		if(examPaperList != null)
		{
			for(ExamExaminPaper ep : examPaperList){
				ep.setExam_id(exam.getId());
			}
			saveExamTestPaper(examPaperList);	
		}
		
		List<ExamExaminUser> examUser = exam.getExaminUserList();
		if(examUser != null)
		{
			for(ExamExaminUser user : examUser){
				user.setExam_id(exam.getId());
			}
			saveExamUser(examUser);	
		}
		List<ExamExaminUser> examConsider = exam.getExaminConsierList();
		if(examConsider != null)
		{
			for(ExamExaminUser con : examConsider){
				con.setExam_id(exam.getId());
			}
			saveExamUser(examConsider);	
		}
		List<ExamExaminOrg> examOrg = exam.getExaminOrgList();
		if(examOrg != null)
		{
			for(ExamExaminOrg org : examOrg){
				org.setExam_id(exam.getId());
			}		
			saveExamOrg(examOrg);	
		}				
				
		getJdbcTemplate().update(sql.toString(),values.toArray());
	}

	
	@Override
	public List<ExamExamination> getExaminationListByExamTypeId(Long examTypeId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ExamExamination> getExaminationListByIds(
			ExamExaminationQuery query) {
		
		StringBuffer sql = new StringBuffer();
		Long[] idArr = query.getIdArr();
		List list = new ArrayList();
		
		if(idArr == null || idArr.length <= 0){
			return null;
		}
		
		sql.append("select * from exam_examination");
		sql.append(" where id in (");
		for (int i = 0; i < idArr.length; i++) {
			if((idArr.length-1)==i)
				sql.append("?");
			else
				sql.append("?,");
			
			list.add(idArr[i]);
		}
		sql.append(")");
		
		if((!StringUtils.checkNull(query.getName())) && (!query.getName().equals(""))){
			sql.append(" and name like ?");
			list.add("%"+query.getName()+"%");
		}	
		
		if(null != query.getExam_type()){
			sql.append(" and exam_type = ? ");
			list.add(query.getExam_type());
		}
		
		if(null != query.getState()){
			sql.append(" and state = ? ");
			list.add(query.getState());
		}
		if(query.getQueryType()!= null && query.getQueryType().intValue() !=0){
			if(query.getQueryType().intValue() ==1){
				sql.append(" and end_time > sysdate() ");
			}else{
				sql.append(" and end_time <= sysdate() ");
			}
		}
		
		sql.append("order by id");


		return getJdbcTemplate().query(
				PageUtil.getPageSql(sql.toString(),
						query.getPageNo(),
						query.getPageSize()
						),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamExamination.class), list.toArray());
		
		
	}

	@SuppressWarnings("unchecked")
	public int getExaminationListByIdsSize(ExamExaminationQuery query) {
		
		StringBuffer sql = new StringBuffer();
		Long[] idArr = query.getIdArr();
		List list = new ArrayList();
		
		if(idArr == null || idArr.length <= 0){
			return 0;
		}		
		sql.append("select count(1) from exam_examination");
		sql.append(" where id in (");
		
		for (int i = 0; i < idArr.length; i++) {
			if((idArr.length-1)==i)
				sql.append("?");
			else
				sql.append("?,");
			
			list.add(idArr[i]);
		}
		sql.append(")");		
		
		if((!StringUtils.checkNull(query.getName())) && (!query.getName().equals(""))){
			sql.append(" and name like ?");
			list.add("%"+query.getName()+"%");
		}	
		
		if(null != query.getExam_type()){
			sql.append(" and exam_type = ?");
			list.add(query.getExam_type());
		}
		
		if(null != query.getState()){
			sql.append(" and state = ? ");
			list.add(query.getState());
		}
		if(query.getQueryType()!= null && query.getQueryType().intValue() !=0){
			if(query.getQueryType().intValue() ==1){
				sql.append(" and end_time > sysdate() ");
			}else{
				sql.append(" and end_time <= sysdate() ");
			}
		}
		
		return getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
	}	
	
	private void deleteExamTestPaperByExamId(List<Long> id){
		String sql = "delete from exam_exam_paper where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}
	private void deleteExamOrgByExamId(List<Long> id){
		String sql = "delete from exam_exam_org where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}
	private void deleteExamUserByExamId(List<Long> id){
		String sql = "delete from exam_exam_user where exam_id = ?";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); ++i) {
			Object[] values = new Object[] { id.get(i) };
			batch.add(values);
		}
		updateBatch(sql, batch);
	}
	private void saveExamTestPaper(List<ExamExaminPaper> examinPaperList){
		String sql = "insert into exam_exam_paper (exam_id, paper_id, seq) values (?, ?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinPaperList.size(); i++) {
			Object[] params = null;
			ExamExaminPaper examPaper = examinPaperList.get(i);
			if(examPaper.getChild_paper_num()!=null && examPaper.getChild_paper_num()>0){
				List<ExamPaper> childPaperList = getExamTestPaperChildIdByParentId(examPaper.getPaper_id());
				for(int j=0;j<childPaperList.size();j++){
					ExamPaper childpaper = childPaperList.get(j);
					params = new Object[] { examPaper.getExam_id(), childpaper.getId(), i+1 };	
					batch.add(params);
				}
			}else{
				params = new Object[] { examPaper.getExam_id(),examPaper.getPaper_id(), i+1 };
				batch.add(params);
			}
		}
		updateBatch(sql, batch);
	}
	private void saveExamUser(List<ExamExaminUser> examinUserList){
		String sql = "insert into exam_exam_user (exam_id, system_user_id, system_user_type) values (?, ?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinUserList.size(); i++) {
			Object[] params = null;
			ExamExaminUser examUser = examinUserList.get(i);
			params = new Object[]{examUser.getExam_id(),examUser.getSystem_user_id(),examUser.getSystem_user_type()};
			batch.add(params);
		}
		updateBatch(sql, batch);
	}
	private void saveExamOrg(List<ExamExaminOrg> examinOrgList){
		String sql = "insert into exam_exam_org (exam_id, org_id) values (?, ?)";
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < examinOrgList.size(); i++) {
			Object[] params = null;
			ExamExaminOrg examOrg  = examinOrgList.get(i);
			params = new Object[]{examOrg.getExam_id(),examOrg.getOrg_id()};
			batch.add(params);
		}
		updateBatch(sql, batch);
	}
	private List<ExamPaper> getExamTestPaperChildIdByParentId(Long id){
		String sql = "select t.id from exam_testpaper t where t.id = ? or t.parent_id = ?";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPaper.class), id ,id);
	}
	
	private List<ExamExaminPaper> getExamTestPaperByExamId(Long id) {
		String sql = "select t.exam_id,t.paper_id,t.seq,t1.name as paper_name,t1.paper_mode as paper_mode,t1.paper_score as paper_score,t1.question_num as question_num,t1.create_date as create_date,t1.child_paper_num from exam_exam_paper t,exam_testpaper t1 where t.paper_id = t1.id and t.exam_id = ? and t1.parent_id = 0";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminPaper.class), id);
	}
	private List<ExamExaminUser> getExamUserByExamId(Long id){
		String sql = "select t.* from exam_exam_user as t where t.exam_id=? and t.system_user_type = 1";
		List<ExamExaminUser> result =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminUser.class), id);
		for(ExamExaminUser eu : result)
		{
			String u_sql = "select u.*,a.* from system_user as u left join system_account as a on u.id = a.user_id where id = ?";
			SystemUser user = getJdbcTemplate().queryForObject(u_sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), eu.getSystem_user_id());
			eu.setUser(user);
		}
		return result;
	}
	private List<ExamExaminUser> getExamConsiderByExamId(Long id){
		String sql = "select t.* from exam_exam_user as t where t.exam_id=? and t.system_user_type = 2";
		List<ExamExaminUser> result =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminUser.class), id);
		for(ExamExaminUser eu : result)
		{
			String u_sql = "select u.*,a.* from system_user as u left join system_account as a on u.id = a.user_id where id = ?";
			SystemUser user = getJdbcTemplate().queryForObject(u_sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), eu.getSystem_user_id());
			eu.setUser(user);
		}
		return result;
	}
	private List<ExamExaminOrg> getExamOrgByExamId(Long id){
		String sql = "select t.*,o.* from exam_exam_org as t left join exam_hospital as o on t.org_id = o.id where t.exam_id=?";
		List<ExamExaminOrg> result =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamExaminOrg.class), id);
		for(ExamExaminOrg eo : result)
		{
			String u_sql = "select * from exam_hospital where id = ?";
			ExamHospital org = getJdbcTemplate().queryForObject(u_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class), eo.getOrg_id());
			eo.setOrg(org);
		}
		return result;
	}
	@Override
	public void updateExaminationTypeByExamId(Long typeId, Long id) {
		String sql = "update exam_examination set exam_type_id = ? where id = ?";
		getJdbcTemplate().update(sql,typeId,id);
	}
}
