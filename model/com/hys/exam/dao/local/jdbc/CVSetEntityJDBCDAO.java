package com.hys.exam.dao.local.jdbc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hys.exam.utils.Pager;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.auth.displaytag.SimpleDateYYYYMMDDWrapper;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVSetEntityDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.LogStudy;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.qiantai.model.CVSetAuthorization;
import com.hys.qiantai.model.CVSetEntity;
import com.hys.qiantai.model.CvDiplomaEntity;
import com.hys.qiantai.model.LogStudyCVUnit;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCvSet;
import com.hys.qiantai.model.LogStudyStatMinutes;
import com.hys.qiantai.model.LogStudyStatistics;
import com.hys.qiantai.model.MyStudyInfo;


public class CVSetEntityJDBCDAO extends BaseDao implements CVSetEntityDAO {

	@Override
	public boolean addCVSetEntity(CVSetEntity cvSetEntity) {
		// TODO Auto-generated method stub
		String chk_sql = "select count(1) from cv_set_entity_info where user_id=? and cv_set_id=?";// + cvSetEntity.getUserId() + " and cv_set_id=" + cvSetEntity.getCvSetId();
		
		Long resultCount = getJdbcTemplate().queryForLong(chk_sql, cvSetEntity.getUserId(), cvSetEntity.getCvSetId());
		
		Date date = new Date();
		String strDate = DateUtils.formatDate(date, "yyyy-MM-dd");
		
		if(resultCount != null && resultCount > 0) {
			cvSetEntity.setDate(strDate);
			String sql = "update cv_set_entity_info set ";
			List list = new ArrayList();
			
			if(!StringUtil.checkNull(cvSetEntity.getContent())) {
				sql += "content=?, ";
				list.add(cvSetEntity.getContent());
			}
			if(cvSetEntity.getFavorite() != null && cvSetEntity.getFavorite() > 0) {
				sql += "favorite=?, ";
				list.add(cvSetEntity.getFavorite());
			}
			if(cvSetEntity.getScore() != null && cvSetEntity.getScore() > 0) {
				sql += "score=?, ";
				list.add(cvSetEntity.getScore());
			}
			if(cvSetEntity.getStatus() != null && cvSetEntity.getStatus() > 0) {
				sql += "status=?, ";
				list.add(cvSetEntity.getStatus());
			}
			if(!StringUtil.checkNull(cvSetEntity.getDate())) {
				sql += "date=?, ";
				list.add(cvSetEntity.getDate());
			}
			sql += "user_id=" + cvSetEntity.getUserId()+" where user_id=" +cvSetEntity.getUserId()+" and cv_set_id=" + cvSetEntity.getCvSetId();
			getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		} else {
			
			cvSetEntity.setDate(strDate);
			String sql = "insert into cv_set_entity_info(user_id, cv_set_id, content, favorite, status, score, date) " +
					 "values(:userId, :cvSetId, :content, :favorite, :status, :score, :date)";
			int result = getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(cvSetEntity));
		}
				
		return false;
	}

	@Override
	public List<CVSetEntity> getCVSetEntity(CVSetEntity cvSetEntity) {
		// TODO Auto-generated method stub
		String chk_sql = "select * from cv_set_entity_info where id>0 ";
		if(cvSetEntity.getUserId() != null && cvSetEntity.getUserId()>0)
				chk_sql +="and user_id=" + cvSetEntity.getUserId();
		chk_sql += " and cv_set_id="+cvSetEntity.getCvSetId();
		chk_sql += " order by review_date desc";
		
		List<CVSetEntity> cvSetEntityList = getJdbcTemplate().query(chk_sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetEntity.class));
		return cvSetEntityList;
	}
	@Override
	public List<CVSetEntity> getCVSetEntity(CVSetEntity cvSetEntity,PageList pl) {
		// TODO Auto-generated method stub
		String chk_sql = "select * from cv_set_entity_info where id>0 ";
		if(cvSetEntity.getUserId() != null && cvSetEntity.getUserId()>0)
				chk_sql +="and user_id=" + cvSetEntity.getUserId();
		chk_sql += " and cv_set_id="+cvSetEntity.getCvSetId();
		chk_sql += " order by review_date desc";
		
		List<CVSetEntity> cvSetEntityList = getList(PageUtil.getPageSql(chk_sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), new ArrayList<Object>(), ParameterizedBeanPropertyRowMapper.newInstance(CVSetEntity.class));
		return cvSetEntityList;
	}
	
	@Override
	public Integer getCVSetEntitySize(CVSetEntity cvSetEntity) {
		// TODO Auto-generated method stub
		String chk_sql = "select count(distinct id) from cv_set_entity_info where id>0 ";
		if(cvSetEntity.getUserId() != null && cvSetEntity.getUserId()>0)
				chk_sql +="and user_id=" + cvSetEntity.getUserId();
		chk_sql += " and cv_set_id="+cvSetEntity.getCvSetId();
		chk_sql += " order by review_date desc";
		return getJdbcTemplate().queryForInt(chk_sql);
	}
	@Override
	public void updateStatus(Long id, Long userId) {
		String getObj = "select * from cv_set_entity_info where cv_set_id = " + id.toString() + " and user_id=" + userId.toString();
		String query = "";
		if(!id.equals(0L) && !userId.equals(0L))
		{
			List<CVSetEntity> cvObj = getJdbcTemplate().query(getObj, ParameterizedBeanPropertyRowMapper.newInstance(CVSetEntity.class));
			if(cvObj != null && cvObj.size() != 0)
			{
				query +="update cv_set_entity_info set status = 1 where cv_set_id=" + cvObj.get(0).getCvSetId().toString() + " and user_id = " + cvObj.get(0).getUserId().toString();
			}
			else
			{
				query +="insert into cv_set_entity_info (USER_ID, CV_SET_ID, FAVORITE, STATUS, START_DATE) values " +
						"("+userId+","+id+", 0, 1,"+" sysdate());";
			}
			getJdbcTemplate().update(query);	
		}
		
	}

	@Override
	public void updatefavor(Long cvsId, Long userId,Integer favor) {
		String getobj = "select * from cv_set_entity_info where cv_set_id = " + cvsId.toString() + " and user_id=" + userId.toString();
		String query = "";
		if(!cvsId.equals(0L) && !userId.equals(0L))
		{
			List<CVSetEntity> cvObj = getJdbcTemplate().query(getobj, ParameterizedBeanPropertyRowMapper.newInstance(CVSetEntity.class));
			if(cvObj != null && cvObj.size() != 0)
			{
				query +="update cv_set_entity_info set favorite = " + favor +" where cv_set_id=" + cvObj.get(0).getCvSetId().toString() + " and user_id = " + cvObj.get(0).getUserId().toString();
			}
			else
			{
				query +="insert into cv_set_entity_info (USER_ID, CV_SET_ID, FAVORITE, STATUS, START_DATE) values " +
						"("+userId+","+cvsId+"," + favor +","+"0"+","+" sysdate());";
			}
			getJdbcTemplate().update(query);	
		}
	}

	@Override
	public void updateContent(Long cvsId, Long userId, String content) {
		String getobj = "select * from cv_set_entity_info where cv_set_id = " + cvsId.toString() + " and user_id=" + userId.toString();
		String query = "";
		if(!cvsId.equals(0L) && !userId.equals(0L))
		{
			List<CVSetEntity> cvObj = getJdbcTemplate().query(getobj, ParameterizedBeanPropertyRowMapper.newInstance(CVSetEntity.class));
			if(cvObj != null && cvObj.size() != 0)
			{
				query +="update cv_set_entity_info set content = '" + content +"',review_date = sysdate() where cv_set_id=" + cvObj.get(0).getCvSetId().toString() + " and user_id = " + cvObj.get(0).getUserId().toString();
			}
			else
			{
				query +="insert into cv_set_entity_info (USER_ID, CV_SET_ID, CONTENT, SCORE,REVIEW_DATE) values " +
						"("+userId+","+cvsId+",'" + content + "',0,sysdate())";
			}
			getJdbcTemplate().update(query);
		}
	}
	@Override
	public void updateScore(Long cvsId, Long userId, Double score) {
		String getobj = "select * from cv_set_entity_info where cv_set_id = " + cvsId.toString() + " and user_id=" + userId.toString();
		String query = "";
		if(!cvsId.equals(0L) && !userId.equals(0L))
		{
			List<CVSetEntity> cvObj = getJdbcTemplate().query(getobj, ParameterizedBeanPropertyRowMapper.newInstance(CVSetEntity.class));
			if(cvObj != null && cvObj.size() != 0)
			{
				query +="update cv_set_entity_info set score = '" + score +"',review_date = sysdate() where cv_set_id=" + cvObj.get(0).getCvSetId().toString() + " and user_id = " + cvObj.get(0).getUserId().toString();
			}
			else
			{
				query +="insert into cv_set_entity_info (USER_ID, CV_SET_ID, SCORE,REVIEW_DATE) values " +
						"("+userId+","+cvsId+",'" + score + "',sysdate())";
			}
			getJdbcTemplate().update(query);
		}
	}
	@Override
	public List<MyStudyInfo> getCVAllInfo(MyStudyInfo info, PageList page) {
			
		StringBuilder getCVSetEntity_query = new StringBuilder("select ce.USER_ID as userId, ce.cv_set_id as cvSetId, ce.favorite as favorite," +
				" cv.score as score, ce.status as status, cv.START_DATE as startDate, cv.code as code,  cv.name as name, cv.level as level, cv.sign as sign" +
				" from cv_set_entity_info ce left join cv_set cv on ce.cv_set_id=cv.id where ce.id > 0 and cv.id > 0 and ce.status=2 and ce.user_id="+info.getUserId());
		if(info.getEndDate() != null){
			String [] edate = info.getEndDate().split("-");	
			if(!StringUtil.checkNull(edate[0]))
				getCVSetEntity_query.append(" and DATE_FORMAT(cv.START_DATE, '%Y') >='"+edate[0]+"'");
			if(!StringUtil.checkNull(edate[1]))
				getCVSetEntity_query.append(" and DATE_FORMAT(cv.START_DATE, '%d') >='"+edate[1]+"'");
		}
		getCVSetEntity_query.append(" order by cv.START_DATE DESC");
		
		List<MyStudyInfo> AllList = new ArrayList<MyStudyInfo>();
		AllList = getList(PageUtil.getPageSql(getCVSetEntity_query.toString(), page.getPageNumber(), page.getObjectsPerPage()), new ArrayList<Object>(), ParameterizedBeanPropertyRowMapper.newInstance(MyStudyInfo.class));
		
		for(MyStudyInfo item : AllList)
		{
			//AllList.remove(item);
			item.setFile_path("/" + Constants.UPLOAD_FILE_PATH_CVS + "/" + item.getCvSetId());
			String str = DateUtil.format(item.getStartDate(), "yyyy-MM-dd");
			String[] date = str.split("-");
			item.setYear(date[0]);
			item.setMonth(date[1]);
			item.setDay(date[2]);
			String query = "select NAME as expertName from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where  CV_SET_ID="+item.getCvSetId() +")";
			List<MyStudyInfo> expertNameList = getJdbcTemplate().query(query, ParameterizedBeanPropertyRowMapper.newInstance(MyStudyInfo.class));
			if(expertNameList != null && expertNameList.size()>0)
			{
				item.setExpertName(expertNameList.get(0).getExpertName());
			}
			
			String getOrgNamequery = "SELECT t1.name as orgName FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID ="+item.getCvSetId();
			List<MyStudyInfo> getOrgNames = getJdbcTemplate().query(getOrgNamequery, ParameterizedBeanPropertyRowMapper.newInstance(MyStudyInfo.class));
			if(getOrgNames != null && getOrgNames.size()>0)
			{
				item.setOrgName(getOrgNames.get(0).getOrgName());
			}
			if(item.getScore() == null) item.setScore(0.0);
		}
			
		return AllList;
	}

	@Override
	public int getCVAllInfoSize(MyStudyInfo info) {
		StringBuilder getCVSetEntity_query = new StringBuilder("select count(ce.USER_ID) from cv_set_entity_info ce left join cv_set cv on ce.cv_set_id=cv.id where ce.id > 0 and cv.id > 0 and ce.status=2 and ce.user_id="+info.getUserId());
		if(info.getEndDate() != null){
			String [] edate = info.getEndDate().split("-");	
			if(!StringUtil.checkNull(edate[0]))
				getCVSetEntity_query.append(" and DATE_FORMAT(cv.START_DATE, '%Y') >='"+edate[0]+"'");
			if(!StringUtil.checkNull(edate[1]))
				getCVSetEntity_query.append(" and DATE_FORMAT(cv.START_DATE, '%d') >='"+edate[1]+"'");
		}
		getCVSetEntity_query.append(" order by cv.START_DATE DESC;");
		int count = getJdbcTemplate().queryForInt(getCVSetEntity_query.toString());
		return count;
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id查询课程信息
	 */
	@Override
	public List<CV> getCvListByProId(Long proId) {
		String queryCvByProId = " SELECT * FROM CV WHERE ID IN ( "
	                             +" SELECT CV_ID FROM CV_SCHEDULE WHERE SCHEDULE_ID IN ( "
		                           +" SELECT CV_SCHEDULE_ID FROM CV_SET_SCHEDULE WHERE CV_SET_ID = " + proId
	                              + " ) "
                                +")";
		//YHQ，2017-05-31，修改按课程顺序排序
		queryCvByProId = "SELECT c.* FROM CV c "
				       +" inner join CV_SCHEDULE cs on c.id = cs.CV_ID "
				       +" inner join CV_SET_SCHEDULE css on cs.SCHEDULE_ID = css.CV_SCHEDULE_ID " 
				       +" where css.CV_SET_ID =  " + proId
				       +" order by cs.sequenceNum asc" ;
		
		List<CV> resultList =  getJdbcTemplate().query(queryCvByProId, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		//Get teacher list of cv unit.
		for (CV item : resultList){
			List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
			String sql_teacher = "select t1.expert_id as id, t2.name from cv t, cv_ref_teacher t1, expert_info t2 where t.id=t1.cv_id and t.id=" + item.getId() + " and t1.expert_id=t2.id and t1.type="+Constants.TeacherType;
			teacherList = getJdbcTemplate().query(sql_teacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
			item.setTeacherList(teacherList);			
		}
		return resultList;
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询课程信息
	 */
	@Override
	public List<CV> getCvListByClassId(Long classId) {
		String queryCvByClassId = "SELECT * FROM CV WHERE ID = "+classId;
		return getJdbcTemplate().query(queryCvByClassId, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询单元集合
	 */
	@Override
	public List<CVUnit> queryUnitListByClassId(Long classId){
		String queryUnitListByClassId = " SELECT * FROM CV_UNIT WHERE ID IN ( "
                                          +" SELECT UNIT_ID FROM CV_REF_UNIT WHERE CV_ID = "+classId
                                       +" ) order by sequenceNum asc";//YHQ，2015-05-30，添加按单元顺序排序字段
		return getJdbcTemplate().query(queryUnitListByClassId, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id查询项目信息
	 */
	@Override
	public List<CVSet> queryCVUnitById(Long proId){
		String queryCVUnitById = " SELECT * FROM CV_SET WHERE ID = "+proId;
		return getJdbcTemplate().query(queryCVUnitById, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id查询项目负责人信息
	 */
	@Override
	public List<ExpertInfo> queryProjectFZR(Long proId){
		String queryProjectFZR = " SELECT * FROM EXPERT_INFO WHERE ID in ( "
                                  +" SELECT EXPERT_ID FROM CV_SET_EXPERT WHERE CV_SET_ID = "+proId+" AND TYPE=1 "
                                +" ) ";
		return getJdbcTemplate().query(queryProjectFZR, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据项目Id项目查询项目单位信息
	 */
	@Override
	public List<PeixunOrg> queryProjectOrg(Long proId){
		String queryProjectOrg = " SELECT * FROM PEIXUN_ORG WHERE ID in ( "
                                  +" SELECT ORG_ID FROM CV_SET_ORG WHERE CV_ID = "+proId
                                +" ) ";
		return getJdbcTemplate().query(queryProjectOrg, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据单元id查询单元组课信息
	 */
	@Override
	public List<GroupClassInfo> queryGroupByUnitId(Long unitId){
		String sql = "SELECT * FROM GROUP_CLASS_INFO WHERE CLASS_ID="+unitId;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(GroupClassInfo.class));
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据课程id查询课程内容
	 */
	@Override
	public List<CV> queryCVById(Long classId){
		String sql = "SELECT * FROM CV WHERE ID="+classId;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
	}
	/**
	 * @author Tiger
	 * @time 2017-01-11
	 * @return List<LogStudyCvSet>
	 * @param LogStudyCvSet
	 * 
	 * Get the cv_set list of log cv_set from user. 
	 */
	@Override
	public void getLogCVSetListFromUser(LogStudyCvSet logInfo,
			PageList<LogStudyCvSet> page,List<Long> list) {
		List<LogStudyCvSet> resList = new ArrayList<LogStudyCvSet>();
		/**
		 * 2017-2-22
		 * 加入项目编号去重
		 * 程宏业
		 * 
		 */
		
		// 重写分页sql
		StringBuffer pagesql = new StringBuffer();		
		StringBuffer sql = new StringBuffer();
		sql.append("select log.id as log_id, log.site_id, log.system_user_id, log.cv_set_id, log.state, log.is_apply_credit, log.last_update_date, log.apply_date,cv.`CODE`, cv.*, COUNT(DISTINCT cv.`CODE`) from log_study_cv_set log left join cv_set cv on log.cv_set_id = cv.id where log.id > 0 ");
		pagesql.append("select  COUNT(DISTINCT cv.`CODE`) from log_study_cv_set log left join cv_set cv on log.cv_set_id = cv.id where log.id > 0 ");
		
		sql.append("and (log.IS_APPLY_CREDIT=2");
		pagesql.append("and (log.IS_APPLY_CREDIT=2");
		
		if(list!=null && list.size()>0){
			sql.append(" or log.cv_set_id in (");
			pagesql.append(" or log.cv_set_id in (");
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				str.append(list.get(i));
				if(i!=list.size()-1){
					str.append(",");
				}
			}
			sql.append(str);
			pagesql.append(str);
			sql.append(")");
			pagesql.append(")");
		}
		
		sql.append(") ");
		pagesql.append(") ");
	
		
		List list2 = new ArrayList();
		if(logInfo.getSystem_user_id() != null && logInfo.getSystem_user_id() != 0L)
		{
			sql.append(" and log.system_user_id = ?");
			pagesql.append(" and log.system_user_id = ?");
			list2.add(logInfo.getSystem_user_id());
		}
		if(logInfo.getState() != null && logInfo.getState() != 0)
		{
			sql.append(" and log.state = ?");
			pagesql.append(" and log.state = ?");
			list2.add(logInfo.getState());
		}
		if(logInfo.getIs_apply_credit() != null && logInfo.getIs_apply_credit() != 0)
		{
			sql.append(" and log.is_apply_credit = ?");
			pagesql.append(" and log.is_apply_credit = ?");
			list2.add(logInfo.getIs_apply_credit());
		}
		//查询修改时间 2017-01-12 han
		if(logInfo.getsYear() != null && logInfo.getsYear()!=0){
			sql.append(" and year(log.last_update_date)=?");
			pagesql.append(" and year(log.last_update_date)=?");
			list2.add(logInfo.getsYear().toString());
		}
		if(logInfo.getsMonth() != null && logInfo.getsMonth()!=0){
			sql.append(" and month(log.last_update_date)=?");
			pagesql.append(" and month(log.last_update_date)=?");
			list2.add(logInfo.getsMonth().toString());
		}
	
		sql.append(" GROUP BY log_id, log.site_id, log.system_user_id, log.cv_set_id, log.state, log.is_apply_credit, log.last_update_date, log.apply_date,cv.`CODE` order by log.last_update_date desc,cv.`CODE` ");
			
		Integer fullListSize = getJdbcTemplate().queryForInt(pagesql.toString(),list2.toArray());
		page.setFullListSize(fullListSize);
		
		resList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), page.getPageNumber(), page.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class), list2.toArray());
		
		for(LogStudyCvSet cvSet:resList){
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =?";
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class), cvSet.getId());
			cvSet.setOrganizationList(peixunOrgList);
						
			String sql_exist = "select count(1) from CV_SET_EXPERT where CV_SET_ID=?";
			Long cnt = getJdbcTemplate().queryForLong(sql_exist, cvSet.getId());
			
			if (cnt > 0) {
				String sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 1 and CV_SET_ID=?)";
				List<ExpertInfo> managerList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvSet.getId());				
	
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 2 and CV_SET_ID=?)";
				List<ExpertInfo> teacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvSet.getId());
				
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 3 and CV_SET_ID= ?)";
				List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvSet.getId());
				
				cvSet.setManagerList(managerList);
				cvSet.setTeacherList(teacherList);
				cvSet.setOtherTeacherList(otherTeacherList);
			}
		}
		
		page.setList(resList);
		
	}
	@Override
	public void getLogCVSetListFromUser(LogStudyCvSet logInfo,
			PageList<LogStudyCvSet> page) {
		List<LogStudyCvSet> resList = new ArrayList<LogStudyCvSet>();
		/**
		 * 2017-2-22
		 * 加入项目编号去重
		 * 程宏业
		 * 
		 */
		
		// 重写分页sql
		StringBuffer pagesql = new StringBuffer();		
		StringBuffer sql = new StringBuffer();
		sql.append("select log.id as log_id, log.site_id, log.system_user_id, log.cv_set_id, log.state, log.is_apply_credit, log.last_update_date, log.apply_date,cv.`CODE`, cv.*, COUNT(DISTINCT cv.`CODE`) from log_study_cv_set log left join cv_set cv on log.cv_set_id = cv.id where log.id > 0 ");
		pagesql.append("select  COUNT(DISTINCT cv.`CODE`) from log_study_cv_set log left join cv_set cv on log.cv_set_id = cv.id where log.id > 0 ");
		
		List list2 = new ArrayList();
		if(logInfo.getSystem_user_id() != null && logInfo.getSystem_user_id() != 0L)
		{
			sql.append(" and log.system_user_id = ?");
			pagesql.append(" and log.system_user_id = ?");
			list2.add(logInfo.getSystem_user_id());
		}
		if(logInfo.getState() != null && logInfo.getState() != 0)
		{
			sql.append(" and log.state = ?");
			pagesql.append(" and log.state = ?");
			list2.add(logInfo.getState());
		}
		if(logInfo.getIs_apply_credit() != null && logInfo.getIs_apply_credit() != 0)
		{
			sql.append(" and log.is_apply_credit = ?");
			pagesql.append(" and log.is_apply_credit = ?");
			list2.add(logInfo.getIs_apply_credit());
		}
		//查询修改时间 2017-01-12 han
		if(logInfo.getsYear() != null && logInfo.getsYear()!=0){
			sql.append(" and year(log.last_update_date)=?");
			pagesql.append(" and year(log.last_update_date)=?");
			list2.add(logInfo.getsYear().toString());
		}
		if(logInfo.getsMonth() != null && logInfo.getsMonth()!=0){
			sql.append(" and month(log.last_update_date)=?");
			pagesql.append(" and month(log.last_update_date)=?");
			list2.add(logInfo.getsMonth().toString());
		}
	
		sql.append(" GROUP BY log_id, log.site_id, log.system_user_id, log.cv_set_id, log.state, log.is_apply_credit, log.last_update_date, log.apply_date,cv.`CODE` order by log.last_update_date desc,cv.`CODE` ");
			
		Integer fullListSize = getJdbcTemplate().queryForInt(pagesql.toString(),list2.toArray());
		page.setFullListSize(fullListSize);
		
		resList = getJdbcTemplate().query(PageUtil.getPageSql(sql.toString(), page.getPageNumber(), page.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class), list2.toArray());
		
		for(LogStudyCvSet cvSet:resList){
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =?";
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class), cvSet.getId());
			cvSet.setOrganizationList(peixunOrgList);
						
			String sql_exist = "select count(1) from CV_SET_EXPERT where CV_SET_ID=?";
			Long cnt = getJdbcTemplate().queryForLong(sql_exist, cvSet.getId());
			
			if (cnt > 0) {
				String sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 1 and CV_SET_ID=?)";
				List<ExpertInfo> managerList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvSet.getId());				
	
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 2 and CV_SET_ID=?)";
				List<ExpertInfo> teacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvSet.getId());
				
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 3 and CV_SET_ID= ?)";
				List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvSet.getId());
				
				cvSet.setManagerList(managerList);
				cvSet.setTeacherList(teacherList);
				cvSet.setOtherTeacherList(otherTeacherList);
			}
		}
		
		page.setList(resList);
		
	}

	/**
	 * @author 	Han
	 * @time	2017-01-12
	 * @param 	Long
	 * @return	LogStudyCvSet
	 * 方法说明  根据LogStudy_id查询项目内容
	 */
	@Override
	public LogStudyCvSet getCVSetEntityInfoById(Long id) {
		String sql = "select cv.*, lg.id log_id from cv_set cv, log_study_cv_set lg where cv.id=lg.CV_SET_ID and lg.id=?";
		List<LogStudyCvSet> res = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class), id);
		if (res.size()>0)
			return res.get(0);
		else
			return null;
	}

	/**
	 * @author	Han
	 * @time	2017-01-12
	 * @param	LogStudyCVUnit
	 * @return	List<LogStudyCVUnit>
	 * 方法说明  查询LogStudyCVUnit
	 */
	@Override
	public List<LogStudyCVUnit> getLogStudyCVUnitList(LogStudyCVUnit logUnit) {
		String sql = "SELECT * from log_study_cv_unit where id<>0";
		
		List<Object> param = new ArrayList();
		
		if (logUnit.getLogStudyCVSetId()!=null && logUnit.getLogStudyCVSetId()!=0){
			sql += " and log_study_cv_set_id=?";
			param.add(logUnit.getLogStudyCVSetId());
		}
		if (logUnit.getCvId()!=null && logUnit.getCvId()!=0){
			sql += " and cv_id=?";
			param.add(logUnit.getCvId());
		}
		if (logUnit.getCvUnitId()!=null && logUnit.getCvUnitId()!=0){
			sql += " and cv_unit_id=?";
			param.add(logUnit.getCvUnitId());
		}
		if (logUnit.getStatus()!=null && logUnit.getStatus()!=0){
			sql += " and status=?";
			param.add(logUnit.getStatus());
		}
		
		
		return null;
	}

	/**
	 * @author	Han
	 * @time	2017-01-12
	 * @param	LogStudyStatistics
	 * @return	void
	 * 方法说明  
	 */
	@Override
	public void getLogStudyStatistics(LogStudyStatistics sts) {

		// get cvsetids
		String cvSetIds;
		if (sts.getCvSetId()!=null){
			cvSetIds = sts.getCvSetId().toString();
		}
		else if(sts.getYear()!=null && sts.getUserId()!=null){
			List<Long> cvsetids;
			if (sts.getYear() == 0){
				cvsetids = getJdbcTemplate().queryForList("select CV_SET_ID from log_study_cv_set where SYSTEM_USER_ID=?", Long.class, sts.getUserId());
			}else{
				cvsetids = getJdbcTemplate().queryForList("select CV_SET_ID from log_study_cv_set where year(LAST_UPDATE_DATE)=? and SYSTEM_USER_ID=?", Long.class, sts.getYear(), sts.getUserId());
			}
			cvSetIds = cvsetids.toString();
			cvSetIds = cvSetIds.substring(1, cvSetIds.length()-1);
		}
		else if(sts.getStartDate()!=null && sts.getUserId()!=null){
			String sql = "select CV_SET_ID from log_study_cv_set where LAST_UPDATE_DATE>=? and LAST_UPDATE_DATE<=? and SYSTEM_USER_ID=?";
			Date endDate = sts.getEndDate()==null?sts.getStartDate():sts.getEndDate();
			List<Long> cvsetids = getJdbcTemplate().queryForList(sql, Long.class, sts.getStartDate(), endDate, sts.getUserId());
			cvSetIds = cvsetids.toString();
			cvSetIds = cvSetIds.substring(1, cvSetIds.length()-1);
		}
		else if(sts.getUserId()!=null){
			List<Long> cvsetids = getJdbcTemplate().queryForList("select CV_SET_ID from log_study_cv_set where SYSTEM_USER_ID=?", Long.class, sts.getUserId());
			cvSetIds = cvsetids.toString();
			cvSetIds = cvSetIds.substring(1, cvSetIds.length()-1);
		}
		else{
			return;
		}
		
		//get total units
		String sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where r.CV_ID in (select cv.id from cv LEFT JOIN cv_schedule sch on cv.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where ssch.CV_SET_ID in (?))";
		Integer units = getJdbcTemplate().queryForInt(sql, cvSetIds);
		sts.setUnits(units);
		
		//get log units
		sql = "select COUNT(DISTINCT CV_UNIT_ID) from log_study_cv_unit where LOG_STUDY_CV_SET_ID in (?)";
		Integer logUnits = getJdbcTemplate().queryForInt(sql, cvSetIds);
		sts.setLogUnits(logUnits);
		
		//get total points
		sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.`point`=1 and r.CV_ID in (select cv.id from cv LEFT JOIN cv_schedule sch on cv.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where ssch.CV_SET_ID in (?))";
		Integer points = getJdbcTemplate().queryForInt(sql, cvSetIds);
		sts.setPoints(points);
		
		//get completed points
		sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where u.`point`=1 and l.STATUS=2 and l.LOG_STUDY_CV_SET_ID in (?)";
		Integer completedLogUnits = getJdbcTemplate().queryForInt(sql, cvSetIds);
		sts.setCompletedLogUnits(completedLogUnits);
		
		//get cvs
		sql = "select cv.id from cv LEFT JOIN cv_schedule sch on cv.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where ssch.CV_SET_ID in (?)";
		List<Long> cvids = getJdbcTemplate().queryForList(sql, Long.class, cvSetIds);
		sts.setCvs(cvids.size());
		int compls = 0;
		for(Long cv: cvids){
			sql="select count(DISTINCT l.cv_unit_id) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID LEFT JOIN cv_ref_unit r on r.UNIT_ID=u.ID LEFT JOIN cv on cv.ID=r.CV_ID where l.STATUS!=2 and l.LOG_STUDY_CV_SET_ID in (?) and cv.ID=?";
			Integer uncompleteds = getJdbcTemplate().queryForInt(sql, cvSetIds, cv);
			if (uncompleteds==0){
				compls++;
			}
		}
		sts.setCompletedCVs(compls);
				
		//get play times
		//sql="SELECT DATE_FORMAT(l.LAST_UPDATE_DATE,'%Y-%m-01') d, sum(round((TIME_TO_SEC(v.END_DATE)-TIME_TO_SEC(v.START_DATE))/60)) m from log_study_cv_unit_video v LEFT JOIN log_study_cv_unit u on v.LOG_STUDY_CV_UNIT_ID=u.ID LEFT JOIN log_study_cv_set l on u.LOG_STUDY_CV_SET_ID=l.CV_SET_ID where l.CV_SET_ID in (?) group by year(l.LAST_UPDATE_DATE), month(l.LAST_UPDATE_DATE) order by l.LAST_UPDATE_DATE";
		sql="SELECT DATE_FORMAT(l.LAST_UPDATE_DATE,'%Y-%m-01') d, sum(round((TIME_TO_SEC(v.END_DATE)-TIME_TO_SEC(v.START_DATE))/60)) m,year(l.LAST_UPDATE_DATE) a,month(l.LAST_UPDATE_DATE) b from log_study_cv_unit_video v LEFT JOIN log_study_cv_unit u on v.LOG_STUDY_CV_UNIT_ID=u.ID LEFT JOIN log_study_cv_set l on u.LOG_STUDY_CV_SET_ID=l.CV_SET_ID where l.CV_SET_ID in (?) group by a,b order by l.LAST_UPDATE_DATE";
		List<LogStudyStatMinutes> minutes = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyStatMinutes.class), cvSetIds);
		sts.setMinutes(minutes);
		//YHQ 暂时注释调上述代码———— log_study_cv_unit_video无数据，播放视频的岂止时间无法确定；group by的语句在有些mysql出现问题
		//List<LogStudyStatMinutes> minutes = new ArrayList<LogStudyStatMinutes>() ; 
		sts.setMinutes(minutes);
		
		//get notes
		sql = "select count(1) from cv_set_unit_note where CV_SET_ID in (?) and SYSTEM_USER_ID=?";
		Integer notes = getJdbcTemplate().queryForInt(sql, cvSetIds, sts.getUserId());
		sts.setNotes(notes);
		
		//get discuss
		sql = "select count(1) from cv_set_unit_discuss where CV_SET_ID in (?) and SYSTEM_USER_ID=?";
		Integer discuss = getJdbcTemplate().queryForInt(sql, cvSetIds, sts.getUserId());
		sts.setDiscuss(discuss);
		
		//get last log
		//sql = "select cv_unit.* from log_study_cv_unit lu LEFT JOIN log_study_cv_set l on l.CV_SET_ID=lu.LOG_STUDY_CV_SET_ID, cv_unit WHERE cv_unit.ID=lu.CV_UNIT_ID AND l.CV_SET_ID in(?) ORDER BY l.LAST_UPDATE_DATE desc limit 0,3";
		sql = "select cv.* from log_study_cv_unit lu JOIN cv_unit cv ON cv.ID = lu.CV_UNIT_ID LEFT JOIN log_study_cv_set l on l.CV_SET_ID=lu.LOG_STUDY_CV_SET_ID WHERE l.CV_SET_ID in(?) AND lu.LOG_STUDY_CV_SET_ID in(?) ORDER BY l.LAST_UPDATE_DATE desc limit 0,3";
		List<CVUnit> cvUnitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), cvSetIds, cvSetIds);
		sts.setLastUnits(cvUnitList);
		
	}
	
	/**
	 * @author	yxt
	 * @time	2017-07-10
	 * @param	LogStudyStatistics
	 * @return	void
	 * 功能调整copy-branch方法"getLogStudyStatistics(LogStudyStatistics sts);"
	 */
	@Override
	public void getLogStudyStatisticsHcharts(LogStudyStatistics sts) {

		// get cvsetids
		String sql = "";
		String cvSetIds = "";
		if (sts.getCvSetId()!=null){
			cvSetIds = sts.getCvSetId().toString();
			//get play times
			sql="SELECT DATE_FORMAT(CREATE_DATE, '%Y-%m-%d') d, sum(round((videoEndLength - videoStartLength + videoEchoLength)/60)) m FROM log_study_cv_unit_video_census WHERE SYSTEM_USER_ID=? AND CV_SET_ID IN ("+cvSetIds+") GROUP BY YEAR (CREATE_DATE),MONTH (CREATE_DATE),DAY (CREATE_DATE) ORDER BY CREATE_DATE ASC";
			List<LogStudyStatMinutes> minutes = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyStatMinutes.class), sts.getUserId());
			sts.setMinutes(minutes);
			//get notes
			sql = "select count(1) from cv_set_unit_note where CV_SET_ID in ("+cvSetIds+") and SYSTEM_USER_ID=?";
			Integer notes = getJdbcTemplate().queryForInt(sql, sts.getUserId());
			sts.setNotes(notes);
			
			//get discuss
			sql = "select count(1) from cv_set_unit_discuss where CV_SET_ID in ("+cvSetIds+") and SYSTEM_USER_ID=?";
			Integer discuss = getJdbcTemplate().queryForInt(sql, sts.getUserId());
			sts.setDiscuss(discuss);
			
			//get last log
			//sql = "select cv_unit.* from log_study_cv_unit lu LEFT JOIN log_study_cv_set l on l.CV_SET_ID=lu.LOG_STUDY_CV_SET_ID, cv_unit WHERE cv_unit.ID=lu.CV_UNIT_ID AND l.CV_SET_ID in("+cvSetIds+") ORDER BY l.LAST_UPDATE_DATE desc limit 0,3";
			sql = "select cv.* from log_study_cv_unit lu JOIN cv_unit cv ON cv.ID = lu.CV_UNIT_ID LEFT JOIN log_study_cv_set l on l.CV_SET_ID=lu.LOG_STUDY_CV_SET_ID WHERE l.CV_SET_ID in(?) AND lu.LOG_STUDY_CV_SET_ID in(?) ORDER BY l.LAST_UPDATE_DATE desc,lu.LAST_UPDATE_DATE desc limit 0,3";
			List<CVUnit> cvUnitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), cvSetIds, cvSetIds);
			sts.setLastUnits(cvUnitList);
		}else if(sts.getYear()!=null){
			List<Long> cvsetids;
			if (sts.getYear() == 0){
				cvsetids = getJdbcTemplate().queryForList("select CV_SET_ID from log_study_cv_set where SYSTEM_USER_ID=?", Long.class, sts.getUserId());
			}else{
				cvsetids = getJdbcTemplate().queryForList("select CV_SET_ID from log_study_cv_set where year(LAST_UPDATE_DATE)=? and SYSTEM_USER_ID=?", Long.class, sts.getYear(), sts.getUserId());
			}
			if(cvsetids.size()>0){
				cvSetIds = cvsetids.toString();
				cvSetIds = cvSetIds.substring(1, cvSetIds.length()-1);
			}else{
				cvSetIds = "0";
			}
		}else{
			return;
		}
		
		//get total points
		sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.`point`=1 and r.CV_ID in (select cv.id from cv LEFT JOIN cv_schedule sch on cv.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where ssch.CV_SET_ID in ("+cvSetIds+"))";
		Integer points = getJdbcTemplate().queryForInt(sql);
		sts.setPoints(points);
		
		//get completed points
		sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where ";
		if (sts.getYear()!=null&&sts.getYear()!=0){
			sql += "year(l.LAST_UPDATE_DATE)="+sts.getYear()+" and ";
		}
		sql += "u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.LOG_STUDY_CV_SET_ID in ("+cvSetIds+")";
		Integer completedLogUnits = getJdbcTemplate().queryForInt(sql, sts.getUserId());
		sts.setCompletedLogUnits(completedLogUnits);
		
		// 直播课程拦截
		sql = "select c.ID from cv c LEFT JOIN cv_schedule sch on c.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where c.cv_type=2 and ssch.CV_SET_ID in ("+cvSetIds+")";
		List<Long> cvZBs = getJdbcTemplate().queryForList(sql, Long.class);
		for(Long id:cvZBs){
			sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where ";
			if (sts.getYear()!=null&&sts.getYear()!=0){
				sql += "year(l.LAST_UPDATE_DATE)="+sts.getYear()+" and ";
			}
			sql += "u.unit_make_type in (0,1,2) and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
			Integer zbUnitLog = getJdbcTemplate().queryForInt(sql, sts.getUserId(), id);
			if(zbUnitLog>0){
				sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type in (0,1,2) and u.`point`=1 and r.CV_ID=?";
				Integer zbUnits = getJdbcTemplate().queryForInt(sql, id);
				if(zbUnitLog<zbUnits){
					sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where ";
					if (sts.getYear()!=null&&sts.getYear()!=0){
						sql += "year(l.LAST_UPDATE_DATE)="+sts.getYear()+" and ";
					}
					sql += "u.unit_make_type=0 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
					Integer zbUnitLog0 = getJdbcTemplate().queryForInt(sql, sts.getUserId(), id);
					sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=0 and u.`point`=1 and r.CV_ID=?";
					Integer zbUnits0 = getJdbcTemplate().queryForInt(sql, id);
					sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where ";
					if (sts.getYear()!=null&&sts.getYear()!=0){
						sql += "year(l.LAST_UPDATE_DATE)="+sts.getYear()+" and ";
					}
					sql += "u.unit_make_type=1 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
					Integer zbUnitLog1 = getJdbcTemplate().queryForInt(sql, sts.getUserId(), id);
					sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=1 and u.`point`=1 and r.CV_ID=?";
					Integer zbUnits1 = getJdbcTemplate().queryForInt(sql, id);
					sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where ";
					if (sts.getYear()!=null&&sts.getYear()!=0){
						sql += "year(l.LAST_UPDATE_DATE)="+sts.getYear()+" and ";
					}
					sql += "u.unit_make_type=2 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
					Integer zbUnitLog2 = getJdbcTemplate().queryForInt(sql, sts.getUserId(), id);
					sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=2 and u.`point`=1 and r.CV_ID=?";
					Integer zbUnits2 = getJdbcTemplate().queryForInt(sql, id);
					if(((zbUnits0!=0&&zbUnitLog0==zbUnits0)||(zbUnits1!=0&&zbUnitLog1==zbUnits1)||(zbUnits2!=0&&zbUnitLog2==zbUnits2))&&!(zbUnitLog0==zbUnits0&&zbUnitLog1==zbUnits1&&zbUnitLog2==zbUnits2)){
						if(zbUnitLog0<zbUnits0){
							sts.setPoints(sts.getPoints()-zbUnits0);
							sts.setCompletedLogUnits(sts.getCompletedLogUnits()-zbUnitLog0);
						}
						if(zbUnitLog1<zbUnits1){
							sts.setPoints(sts.getPoints()-zbUnits1);
							sts.setCompletedLogUnits(sts.getCompletedLogUnits()-zbUnitLog1);
						}
						if(zbUnitLog2<zbUnits2){
							sts.setPoints(sts.getPoints()-zbUnits2);
							sts.setCompletedLogUnits(sts.getCompletedLogUnits()-zbUnitLog2);
						}
					}
				}
			}
		}
		
		// 学习耗时
		sql = "select uvideo.videoLength as videoLength,uvideo.videoPlayLength as videoPlayLength from log_study_cv_unit_video as uvideo left join log_study_cv_unit as vunit on uvideo.LOG_STUDY_CV_UNIT_ID=vunit.ID WHERE ";
		if (sts.getYear()!=null&&sts.getYear()!=0){
			sql += "year(uvideo.END_DATE)="+sts.getYear()+" and ";
		}
		sql += "uvideo.SYSTEM_USER_ID=? and vunit.LOG_STUDY_CV_SET_ID in ("+cvSetIds+")";
		List<LogStudyCVUnitVideo> lscuvList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnitVideo.class), sts.getUserId());
		int Sum_second = 0;
		int Sum_second_all = 0;
		for(LogStudyCVUnitVideo lscuv:lscuvList){
			Sum_second += lscuv.getVideoPlayLength(); 		 
			Sum_second_all += lscuv.getVideoLength();
		}
		sts.setTime_consuming(Sum_second/60);
		sts.setTime_consuming_all(Sum_second_all/60);
		
		//get cvs
		sql = "select cv.id from cv LEFT JOIN cv_schedule sch on cv.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where ssch.CV_SET_ID in ("+cvSetIds+")";
		List<Long> cvids = getJdbcTemplate().queryForList(sql, Long.class);
		sts.setCvs(cvids.size());
		
		//get completed cvs
		sql = "select count(l.ID) from log_study_cv l where ";
		if (sts.getYear()!=null&&sts.getYear()!=0){
			sql += "year(l.LAST_UPDATE_DATE)="+sts.getYear()+" and ";
		}
		sql += "l.SYSTEM_USER_ID=? and l.STATE=2 and l.CV_SET_ID in ("+cvSetIds+")";
		Integer compls = getJdbcTemplate().queryForInt(sql, sts.getUserId());
		sts.setCompletedCVs(compls);
				
	}
	
	/**
	 * @author	yxt
	 * @time	2017-08-08
	 * @param	cvSetID,	userID
	 * 直播课程拦截
	 */
	@Override
	public Map<String, Integer> doZBData(Long cvSetID, Long userID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer zbUnitsR = 0;
		Integer zbUnitLogR = 0;
		// 直播课程拦截
		String sql = "select c.ID from cv c LEFT JOIN cv_schedule sch on c.id=sch.CV_ID LEFT JOIN cv_set_schedule ssch on ssch.CV_SCHEDULE_ID=sch.SCHEDULE_ID where c.cv_type=2 and ssch.CV_SET_ID = "+cvSetID;
		List<Long> cvZBs = getJdbcTemplate().queryForList(sql, Long.class);
		for(Long id:cvZBs){
			sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
					+ "u.unit_make_type in (0,1,2) and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
			Integer zbUnitLog = getJdbcTemplate().queryForInt(sql, userID, id);
			if(zbUnitLog>0){
				sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type in (0,1,2) and u.`point`=1 and r.CV_ID=?";
				Integer zbUnits = getJdbcTemplate().queryForInt(sql, id);
				if(zbUnitLog<zbUnits){
					sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
							+ "u.unit_make_type=0 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
					Integer zbUnitLog0 = getJdbcTemplate().queryForInt(sql, userID, id);
					sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=0 and u.`point`=1 and r.CV_ID=?";
					Integer zbUnits0 = getJdbcTemplate().queryForInt(sql, id);
					sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
							+ "u.unit_make_type=1 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
					Integer zbUnitLog1 = getJdbcTemplate().queryForInt(sql, userID, id);
					sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=1 and u.`point`=1 and r.CV_ID=?";
					Integer zbUnits1 = getJdbcTemplate().queryForInt(sql, id);
					sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
							+ "u.unit_make_type=2 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
					Integer zbUnitLog2 = getJdbcTemplate().queryForInt(sql, userID, id);
					sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=2 and u.`point`=1 and r.CV_ID=?";
					Integer zbUnits2 = getJdbcTemplate().queryForInt(sql, id);
					if(((zbUnits0!=0&&zbUnitLog0==zbUnits0)||(zbUnits1!=0&&zbUnitLog1==zbUnits1)||(zbUnits2!=0&&zbUnitLog2==zbUnits2))&&!(zbUnitLog0==zbUnits0&&zbUnitLog1==zbUnits1&&zbUnitLog2==zbUnits2)){
						if(zbUnitLog0<zbUnits0){
							zbUnitsR += zbUnits0;
							zbUnitLogR += zbUnitLog0;
						}
						if(zbUnitLog1<zbUnits1){
							zbUnitsR += zbUnits1;
							zbUnitLogR += zbUnitLog1;
						}
						if(zbUnitLog2<zbUnits2){
							zbUnitsR += zbUnits2;
							zbUnitLogR += zbUnitLog2;
						}
					}
				}
			}
		}
		if(zbUnitsR>0){
			map.put("zbUnitsR", zbUnitsR);
			map.put("zbUnitLogR", zbUnitLogR);
			return map;
		}else{
			return null;
		}
	}
	
	/**
	 * @author	yxt
	 * @time	2017-12-04
	 * 获得已上线授权过期的项目列表
	 */
	@Override
	public Set<Long> getCvSetByOverdue(){
		Calendar c = Calendar.getInstance();      
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
		StringBuffer sb = new StringBuffer();
		sb.append("select csa.* from cv_set_authorization csa left join cv_set cs on cs.id = csa.cv_set_id where cs.status = ?");
		List<CVSetAuthorization> list = getJdbcTemplate().query(sb.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSetAuthorization.class), 5);
		Set<Long> ids = new HashSet<Long>();
		for (CVSetAuthorization csa : list) {
			ids.add(csa.getCvSetId());
		}
		for (CVSetAuthorization csa : list) {
			if(csa.getCvSetEndDate()!=null&&csa.getCvSetEndDate().getTime() >= c.getTimeInMillis()){
				ids.remove(csa.getCvSetId());
			}
		}
		return ids;
	}
	
	/**
	 * @author	yxt
	 * @time	2017-12-04
	 * 下线授权过期的项目
	 */
	@Override
	public Integer updateCvSetByIds(Set<Long> ids){
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("update cv_set set status = ? where id in (");
		params.add(6);//项目下线
		for (Long id : ids) {
			sb.append("?,");
			params.add(id);
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		return getJdbcTemplate().update(sb.toString(), params.toArray());
	}
	
	/**
	 * @param LogStudyCvSet
	 * @return boolean 方法说明	更新LogStudyCvSet
	 * @author Han
	 * @time 2017-01-13
	 */
	@Override
	public boolean updateLogCVSet(LogStudyCvSet log) {

		if (log == null || log.getLog_id() == null) {
			return false;
		}
		String sql = "update log_study_cv_set set id=id";
		List<Object> params = new ArrayList<Object>();

		if (log.getState() != null) {
			sql += ", state=?";
			params.add(log.getState());
		}
		if (log.getIs_apply_credit() != null) {
			sql += ", is_apply_credit=?";
			params.add(log.getIs_apply_credit());
		}
		if (log.getApply_date()!=null){
			sql += ", apply_date=sysdate()";
			//sql += ", apply_date=?";
			//params.add(log.getApply_date());
		}
		
		
		
		sql += " where id=?";
		params.add(log.getLog_id());

		int res = getJdbcTemplate().update(sql, params.toArray());
		return (res > 0) ? true : false;
	}

	@Override
	public List<LogStudyCvSet> getLogStudyCvSet(Long userID, String ServerName,Integer year){
		String sql = "SELECT * FROM log_study_cv_set t JOIN cv_set t1 ON t.cv_set_id = t1.id JOIN cv_set_system_site t2 ON t1.id = t2.cv_set_id " +
				"JOIN system_site t3 ON t2.system_site_id = t3.id WHERE t.system_user_id ="+userID+" AND t3.domain_name ='"+ServerName+"' AND t.is_apply_credit =1 ";
		if(year != null && year!=0){
			sql += "and year(t.LAST_UPDATE_DATE)="+year;
		}
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class));
	}

	@Override
	public void getLogCVSetListFromUser2(LogStudyCvSet logInfo, Pager<LogStudyCvSet> pl, SystemUser user) {
		StringBuilder sql = new StringBuilder();

		sql.append("from log_study_cv_set t join cv_set t1 on t.cv_set_id=t1.id ");
		sql.append("join cv_set_authorization t2 ON t1.id = t2.CV_SET_ID ");
		sql.append("join cv_set_authorization_system_site t4 ON t4.AUTHORIZATION_ID = t2.ID ");
		sql.append("join cv_set_authorization_region t5 ON t5.AUTHORIZATION_ID = t2.ID ");
		sql.append("join system_site t3 ON t3.id = t4.SYSTEM_SITE_ID ");
		sql.append("where t.system_user_id=? and t3.domain_name=? ");

		List<Object> params = new ArrayList<Object>();
		params.add(logInfo.getSystem_user_id());
		params.add(logInfo.getServerName());

		if(logInfo.getState() != null){
			sql.append(" and t.state=? ");
			params.add(logInfo.getState());
		}
		//设置添加授课形式
		if(logInfo.getForma() != null){
			sql.append(" and t1.forma=? ");
			params.add(logInfo.getForma());
		}

		if(logInfo.getIs_apply_credit() != null){
			sql.append(" and t.is_apply_credit=? ");
			params.add(logInfo.getIs_apply_credit());
		}

		if(user.getUserConfig()!=null){
			sql.append(" and t5.PROP_VAL_ID=? ");
			params.add(user.getUserConfig().getUserProvinceId());
		}
		
		StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t1.ID,t2.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,t2.CV_SET_LEVEL AS LEVEL,t2.CV_SET_SCORE AS SCORE,t2.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("t2.CV_SET_COST_TYPE AS cost_type,t2.CV_SET_COST AS COST,t2.CV_SET_START_DATE AS START_DATE,t2.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("t2.CV_SET_BREAK_DAYS AS BREAK_DAYS,t1.NAME,t1.FORMA,t1.CODE,t1.FILE_PATH,t1.INTRODUCTION,t1.ANNOUNCEMENT,t1.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t1.KNOWLEDGE_BASE,t1.REFERENCE,t1.REFERENCE_LATELY,t1.OPINION,t1.REPORT,t1.OPINION_TYPE,t1.STATUS,t1.TYPE,t1.MAKER,t1.DELI_MAN,");
		cvSetColumnSQL.append("t1.CREATE_DATE,t1.DELI_DATE,t1.PROVINCEID,t1.CITYID,t1.RELATION_QUIZ,t1.cv_set_type");
		
		String get = "select t.*," + cvSetColumnSQL + ",t3.* ";

		String get_count = "select count(DISTINCT t1.id) ";
		
		get_count += sql.toString();
		int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
		pl.setCount(fullListSize);

		sql.append("group by t1.id order by t.last_update_date ");

		if (pl.getSortDirection() != null
				&& pl.getSortDirection() == SortOrderEnum.ASCENDING) {
			sql.append(" asc");
		} else{
			sql.append(" desc");
		}

		get += sql.toString();
		List<LogStudyCvSet> list = getJdbcTemplate().query(
				PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class), params.toArray());
		pl.setList(list);
	}

	@Override
	public LogStudyCvSet getLogStudyCvSet(Long userId, Long cvSetId) {
		
		String sql = "select t.*,t.id as log_id from log_study_cv_set t where t.system_user_id=? and t.cv_set_id=?";

		List<LogStudyCvSet> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class), userId, cvSetId);

		if(!list.isEmpty()){
			return list.get(0);
		}

		return null;
	}

	@Override
	public List<LogStudyCVUnit> getLogStudyCVUnitList(Long cvSetId, Long logStudyCvSetId) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t6.*,t7.*,t6.id as cvUnitId ");
		sql.append("from cv_set t ");
		sql.append("join cv_set_schedule t2 on t.id=t2.cv_set_id ");
		sql.append("join cv_schedule t3 on t2.cv_schedule_id=t3.schedule_id ");
		sql.append("join cv t4 on t3.cv_id=t4.id ");
		sql.append("join cv_ref_unit t5 on t5.cv_id=t4.id ");
		sql.append("join cv_unit t6 on t5.unit_id=t6.id ");
		sql.append("left join log_study_cv_unit t7 on t7.cv_unit_id=t6.id and t7.log_study_cv_set_id=? ");
		sql.append("where t.id=? ");

		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class), logStudyCvSetId, cvSetId);
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-27
	 * @param unitId
	 * @return
	 * @Description : Get CVUnit by unit id.
	 */
	@Override
	public CVUnit getCVUnitByUnitId(Long unitId) {
		String sql = "select * from cv_unit where id = ?";
		List<CVUnit> unitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), unitId);
		
		if (unitList != null && unitList.size() > 0){
			return unitList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * @author IE
	 */
	@Override
	public Long getPaperIdByUnitId(Long unitId) {
		String sql = "select testPaper_id from cv_unit_ref_exam where unit_id="+unitId;
		
		return getJdbcTemplate().queryForLong(sql);
	}
	
	/**
	 * @author IE
	 */
	
	@Override
	public Long getCurrentSystemUserIDByName(String assignName) {
		String sql = "select id from system_user where real_name=?";
		return getJdbcTemplate().queryForLong(sql, assignName);
	}

	/**
	 * @author IE
	 */
	
	@Override
	public void setLogStudyCVUnitExam(LogStudy logStudy) {
		
		
		Long Id = this.getNextId("LOG_STUDY_CV_UNIT_EXAM");
		logStudy.setId(Id);
		
		String sql = "insert into log_study_cv_unit_exam (id,log_study_cv_unit_id,system_user_id,cv_id,cv_unit_id,testpaper_id,question_id," +
				"question_result,isnot_right,questionlabel_id,seq,parent_id,select_result,right_count,score) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql,logStudy.getId(),logStudy.getLog_study_cv_unit_id(),logStudy.getSystem_user_id(),logStudy.getCv_id(),
				logStudy.getCv_unit_id(),logStudy.getTestPaper_id(),logStudy.getQuestion_id(),logStudy.getQuestion_Result(),logStudy.getIsNot_Right(),
				logStudy.getQuestionLabel_id(),logStudy.getSeq(),logStudy.getParent_id(),logStudy.getSelect_result(),logStudy.getRight_count(),
				logStudy.getScore());
		
	}
	
	/**
	 * @param   Long
	 * @return  ExpertInfo
	 * @author  张建国
	 * @time    2017-02-15
	 * 方法说明：根据课程id查询课程主讲教师
	 */
	public ExpertInfo queryTeacherByCVId(Long cvId){
		String sql = "select name from expert_info where id in ( "
                    +" select expert_id from cv_ref_teacher where cv_id=? and type=1 "
                    +")";
		List<ExpertInfo> expertList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), cvId);
		if(expertList!=null && expertList.size()>0){
			return expertList.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @author 张建国
	 * @time   2017-02-17
	 * @return CVSet 
	 * @param  Long
	 * 方法说明  根据课程Id查询项目信息
	 */
	public CVSet queryCVSetListByCvId(Long cvId){
		String sql = " select * from cv_set where id in ( "
	                +" select cv_set_id from cv_set_schedule where cv_schedule_id in ( " 
		            +" select SCHEDULE_id from cv_schedule where cv_id=? "
	                +" ) "
                    +")";
		List<CVSet> cvsetList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), cvId);
		if(cvsetList!=null && cvsetList.size()>0){
			return cvsetList.get(0);
		}else{
			return null;
		}
	}
	
	
	@Override
	public int findCvSetId(String setId) {
		String sql = "";
		sql="select distinct cv_set.ID from cv_set,cv_diploma where cv_set.CODE=cv_diploma.ITEM_NUMBER and cv_diploma.ITEM_NUMBER="+setId+"";
		return getJdbcTemplate().queryForInt(sql,ParameterizedBeanPropertyRowMapper.newInstance(CvDiplomaEntity.class));
	}
	
	
	
	/**
	 * @auth   scp
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询单元集合
	 */
	@Override
	public List<GroupClassInfo> queryGroupClassInfo(Long classId){
		String queryUnitListByClassId = " SELECT g.* FROM CV_UNIT c inner join group_class_info g on c.id=g.class_id WHERE c.ID IN ( "
                                          +" SELECT UNIT_ID FROM CV_REF_UNIT WHERE CV_ID = "+classId
                                       +" ) order by c.sequenceNum asc"; //YHQ，2017-05-30，增加按单元顺序培训
		return getJdbcTemplate().query(queryUnitListByClassId, ParameterizedBeanPropertyRowMapper.newInstance(GroupClassInfo.class));
	}

	
	/**
	 * 根据用户的省份id和项目id，查询项目和省份关联表中数据，如果能查到记录，则该项目开放的省份和该用户所在省份是一致的
	 * @param cvId
	 * @param userProviceId
	 * @return
	 */
	@Override
	public List getCVId(long cvId, long userProviceId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select csa.CV_SET_ID ");
		sql.append("from cv_set_authorization csa LEFT JOIN cv_set_authorization_region csar ON csar.AUTHORIZATION_ID = csa.ID ");
		sql.append("where csa.CV_SET_ID = ");
		sql.append(cvId).append(" ");
		//当userProviceId为0时，表示用户地域信息不完整
		if(userProviceId != 0){
			sql.append("AND ");
			sql.append("csar.PROP_VAL_ID = ");
			sql.append(userProviceId).append(" ");
		}else{
			sql.append("GROUP BY csar.AUTHORIZATION_ID HAVING COUNT(csar.ID) = ");
			sql.append("(SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20) ");
		}
		return getJdbcTemplate().queryForList(sql.toString());
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-22
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id和userId查询单元信息
	 */
	@Override
	public List<CVUnit> queryUnitListByClassIdAndUserId(Long classId, Long userId) {
		String sql = "" ;
		List<CVUnit> cvUnitList = null ;
		if (userId != null) {
			sql = "SELECT cu.id,cu.name,cu.type,cu.`point`,cu.state,cu.isBound,cu.quota, "
					   + " lscu.STATUS as logStatus,gci.class_content as content, "
					   + " gci.media_type as mediaType,gci.media_id as mediaId,lscuv.videoPlayLength,cu.sequenceNum "
					   + " FROM CV_UNIT cu "
					   + " left join log_study_cv_unit lscu on lscu.CV_UNIT_ID = cu.id and lscu.CV_ID = ? and lscu.SYSTEM_USER_ID = ? " 
					   + " left join group_class_info gci on gci.class_id = cu.id "
					   + " left join log_study_cv_unit_video lscuv on lscuv.SYSTEM_USER_ID = ? and lscuv.CV_ID = ? and lscuv.CV_UNIT_ID = cu.id "
					   + " left join CV_REF_UNIT on cu.ID = CV_REF_UNIT.UNIT_ID where CV_REF_UNIT.CV_ID = ? order by cu.sequenceNum asc " ;
			cvUnitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), classId, userId, userId, classId, classId);
		} else {			
			cvUnitList = queryUnitListByClassId(classId) ;
		}
				
		return cvUnitList ;
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id和userId查询单元的学习记录信息
	 */
	public List<CVUnit> queryUnitLogStudyByCvSetIdAndUserId(Long cvSetId, Long userId) {
		String sql = "SELECT cu.id,lscu.STATUS as logStatus,lscu.LAST_UPDATE_DATE as logStudylastUpdateDate, cu.name ,cs.sequenceNum as cvSequenceNum,cu.sequenceNum as sequenceNum "
				   + " FROM CV_UNIT cu "
				   + " inner join cv_ref_unit cru on cru.UNIT_ID = cu.id "
				   + " inner join CV_SCHEDULE cs on cru.CV_ID = cs.CV_ID "
				   + " inner join CV_SET_SCHEDULE css on cs.SCHEDULE_ID = css.CV_SCHEDULE_ID " 
				   + " left join log_study_cv_unit lscu on lscu.CV_UNIT_ID = cu.id and lscu.CV_ID = cru.CV_ID and lscu.SYSTEM_USER_ID =  ? " 
				   + " WHERE css.CV_SET_ID = ? "
				   + " order by lscu.LAST_UPDATE_DATE desc,cs.sequenceNum asc , cu.sequenceNum asc " ;				   
		List<CVUnit>  cvunitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), userId, cvSetId);
		return cvunitList ;
	}
	
	//区分开直播课程中有直播单元和回放单元的情况
	public List<CVUnit> queryUnitLogStudyByCvSetIdAndUserIdForLive(Long cvSetId, Long userId) {
		String sql = "SELECT cu.id,lscu.STATUS as logStatus,lscu.LAST_UPDATE_DATE as logStudylastUpdateDate, cu.name ,cs.sequenceNum as cvSequenceNum,cu.sequenceNum as sequenceNum "
				   + " FROM CV_UNIT cu "
				   + " inner join cv_ref_unit cru on cru.UNIT_ID = cu.id "
				   + " inner join CV_SCHEDULE cs on cru.CV_ID = cs.CV_ID "
				   + " inner join CV_SET_SCHEDULE css on cs.SCHEDULE_ID = css.CV_SCHEDULE_ID " 
				   + " left join log_study_cv_unit lscu on lscu.CV_UNIT_ID = cu.id and lscu.CV_ID = cru.CV_ID and lscu.SYSTEM_USER_ID =  ? " 
				   + " WHERE css.CV_SET_ID = ? "
				   + " AND cu.unit_make_type = 2 "
				   + " order by lscu.LAST_UPDATE_DATE desc,cs.sequenceNum asc , cu.sequenceNum asc " ;				   
		List<CVUnit>  cvunitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), userId, cvSetId);
		return cvunitList ;
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * @return List 	  
	 * 方法说明  根据单元Id和userId查询单元的学习记录信息、单元信息
	 */
	public CVUnit queryCVUnitLogStudyByUnitIdAndUserId(Long unitId, Long userId) {
		String sql = "" ;
		CVUnit unitObj = null ;
		if (userId != null) {
			sql = "SELECT cu.id,cu.name,cu.type,cu.`point`,cu.state,cu.isBound,cu.quota,"
					   + " lscu.STATUS as logStatus,gci.class_content as content,"
					   + " gci.media_type as mediaType,gci.media_id as mediaId ,lscuv.videoPlayLength "
					   + " FROM CV_UNIT cu "
					   + " left join log_study_cv_unit lscu on lscu.CV_UNIT_ID = cu.id  and lscu.SYSTEM_USER_ID = ? " 
					   + " left join group_class_info gci on gci.class_id = cu.id "
					   + " left join log_study_cv_unit_video lscuv on lscuv.SYSTEM_USER_ID = ?  and lscuv.CV_UNIT_ID = cu.id "
					   + " WHERE cu.id = ? " ;
			List<CVUnit> unitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), userId, userId, unitId);		
			if (unitList != null && unitList.size() > 0) unitObj = unitList.get(0) ;
		} else { //预览而已
			sql = "SELECT cu.id,cu.name,cu.type,cu.`point`,cu.state,cu.isBound,cu.quota,gci.class_content as content,"
					   + " gci.media_type as mediaType,gci.media_id as mediaId "
					   + " FROM CV_UNIT cu "					    
					   + " left join group_class_info gci on gci.class_id = cu.id "					   
					   + " WHERE cu.id = ? " ;
			List<CVUnit> unitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), unitId);		
			if (unitList != null && unitList.size() > 0) unitObj = unitList.get(0) ;
		}
		
		return unitObj ;
	}
	
	
	/**
	 * @author 杨红强
	 * @time   2017-07-29
	 * @return List 
	 * 方法说明  根据课程Id和userId查询单元的学习记录信息
	 */
	public List<CVUnit> queryUnitLogStudyByCvIdAndUserId(Long cvId, Long userId) {
		String sql = "SELECT cu.id,lscu.STATUS as logStatus,lscu.LAST_UPDATE_DATE as logStudylastUpdateDate, cu.name ,cu.sequenceNum as sequenceNum "
				   + " FROM CV_UNIT cu "
				   + " inner join cv_ref_unit cru on cru.UNIT_ID = cu.id "				   
				   + " left join log_study_cv_unit lscu on lscu.CV_UNIT_ID = cu.id and lscu.SYSTEM_USER_ID =  ? " 
				   + " WHERE cru.CV_ID = ? "
				   + " order by lscu.LAST_UPDATE_DATE desc , cu.sequenceNum asc " ;
				   //+ " order by cs.sequenceNum asc , cu.sequenceNum asc ,lscu.LAST_UPDATE_DATE desc " ;
		List<CVUnit>  cvunitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class), userId, cvId);
		return cvunitList ;
	}
}



