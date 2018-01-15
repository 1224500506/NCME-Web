package com.hys.exam.dao.local.jdbc;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hys.exam.model.*;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.web.context.request.RequestContextHolder;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Log;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.dao.local.CVSetManageDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.dao.local.UserImageManageDAO;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.exam.utils.StringUtils;
import com.hys.qiantai.model.CVSetFavorites;
import com.hys.qiantai.model.CVSetScoreLog;
import com.hys.qiantai.model.CvDiplomaEntity;
import com.hys.qiantai.model.MyStudyInfo;

public class CVSetManageJdbcDAO extends BaseDao implements CVSetManageDAO {

	private CVManageDAO localCVManageDAO;
	
	private UserImageManageDAO localUserImageManageDAO;
	private ExpertManageDAO localExpertManageDAO;
	
	@Override
	public List<CVSet> getCVSetList(CVSet queryCVSet) {
		
		List<CVSet> list = new ArrayList<CVSet>();
		StringBuffer sql = new StringBuffer();
		
		StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
		
		sql.append("SELECT DISTINCT " + cvSetColumnSQL + " FROM cv_set as t join cv_set_authorization csa JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on t.id=csa.cv_set_id and t.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID");
		//SELECT t.* FROM cv_set t, cv_set_user_image tu, qm_user_image u, qm_user_image_prop p WHERE u.id=p.USERIMAGE_ID and t.ID=tu.CV_SET_ID and tu.USERIMAGE_ID=p.USERIMAGE_ID and p.PROP_ID IN (147)
		if(queryCVSet.getId()>0){
			sql.append(" and t.id=").append(queryCVSet.getId());
		}else {
			if(!StringUtils.checkNull(queryCVSet.getName())){
				sql.append(" and t.name like '%"+queryCVSet.getName()+"%'");
			} else if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
				sql.append(" and t.status ="+queryCVSet.getStatus());
			}
		}
		
		if(!StringUtils.checkNull(queryCVSet.getMaker())){
			if (queryCVSet.getId() != null && queryCVSet.getId() < 0) 
				sql.append(" and t.maker = '"+queryCVSet.getMaker()+"'");
			else if(queryCVSet.getFlag() !=null && queryCVSet.getFlag().equals("qualify")){
				sql.append(" and t.maker != '"+queryCVSet.getMaker()+"'");
			}else{
				sql.append(" and t.maker like '%"+queryCVSet.getMaker()+"%'");
			}
		}
		
		if(!StringUtils.checkNull(queryCVSet.getDeli_man())){
			sql.append(" and t.deli_man like '%"+queryCVSet.getDeli_man()+"%'");
		}
		
		if(!StringUtils.checkNull(queryCVSet.getName())){
			sql.append(" and t.name like '%"+queryCVSet.getName()+"%'");
		}
		if(queryCVSet.getStatus() != null && queryCVSet.getStatus() > 0){
			sql.append(" and t.status ="+queryCVSet.getStatus());
		}
		if(!StringUtils.checkNull(queryCVSet.getSign())){
			sql.append(" and csa.CV_SET_SIGN like '%"+queryCVSet.getSign()+"%'");
		}
		if(queryCVSet.getLevel() != null){
			sql.append(" and csa.CV_SET_LEVEL ="+queryCVSet.getLevel());
		}
		if(queryCVSet.getScore() != null){
			sql.append(" and csa.CV_SET_SCORE ="+queryCVSet.getScore());
		}
	//search by xueke
		if(queryCVSet.getCourseList() != null && queryCVSet.getCourseList().size() != 0){			
			sql.append(" and t.ID=c.CV_SET_ID and u.PROP_ID IN (") ;			
					for(int i=0; i<queryCVSet.getCourseList().size(); i++){
						if(i == (queryCVSet.getCourseList().size()-1)){
							sql.append(queryCVSet.getCourseList().get(i).getId());
						}else{
							sql.append(queryCVSet.getCourseList().get(i).getId()).append(",");
						}
					}
					sql.append(")");
			
			//list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		}
		if(queryCVSet.getProvinceId() != null && queryCVSet.getProvinceId() > 0 ) {
			sql.append(" and t.provinceid =" + queryCVSet.getProvinceId());
		}
		if(queryCVSet.getCityId() != null && queryCVSet.getCityId() > 0) {
			sql.append(" and t.cityid =" + queryCVSet.getCityId());
		}
		if(queryCVSet.getStart_date() != null){
			//sql.append(" and t.start_date <= '" + DateUtil.format(queryCVSet.getStart_date(), "yyyy-MM-dd") + "'");
			//sql.append(" and t.end_date >= '" + DateUtil.format(queryCVSet.getStart_date(), "yyyy-MM-dd") + "'");
			sql.append(" and csa.CV_SET_START_DATE <= '" + DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG) + "'");//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
			sql.append(" and csa.CV_SET_END_DATE >= '"   + DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG) + "'");//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
		}
		
		if(queryCVSet.getUserProvinceCode()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID "
        			+ "WHERE csar.PROP_VAL_ID = " + queryCVSet.getUserProvinceCode() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID "
        			+ "HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }
		
		sql.append(" group by t.id ");
		
		if(queryCVSet.getCost_sort() != null && !queryCVSet.getCost_sort().equals("")){
			sql.append(" order by csa.CV_SET_COST " +queryCVSet.getCost_sort());
		}
		if(queryCVSet.getScore_sort() != null && !queryCVSet.getScore_sort().equals("")){
			sql.append(" order by csa.CV_SET_SCORE " +queryCVSet.getScore_sort());
		}
		if(queryCVSet.getRecent_create() != null && !queryCVSet.getRecent_create().equals("")){
			sql.append(" order by t.create_date desc");
		}
		
		list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		List<CVSet> result_list = new ArrayList<CVSet>();
		
		/*String groupIds = "";
		if (queryCVSet.getCourseList() != null && !queryCVSet.getCourseList().isEmpty()) groupIds = queryCVSet.getCourseList().get(0).getName();
		if(groupIds != null && !groupIds.equals("") && groupIds.charAt(groupIds.length()-1) == ',')
		{
			groupIds = groupIds.substring(0, groupIds.length()-1);
		}*/
		
		for(CVSet cvSet:list){
			
			/*String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV_set t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+cvSet.getId();
			
			if(!groupIds.equals("")) {
				prop_sql += " and t1.prop_id in (" + groupIds +")";
			}
			
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));			
			if (!groupIds.equals("") && courseList.size() == 0) continue;			
			cvSet.setCourseList(courseList);*/	
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			if (queryCVSet.getSiteList() != null && !queryCVSet.getSiteList().isEmpty()) {
				String site_sql = "select s.* from cv_set_authorization csa left join cv_set_authorization_system_site sp on sp.AUTHORIZATION_ID = csa.id left join system_site s on sp.SYSTEM_SITE_ID = s.ID where csa.CV_SET_ID = " + cvSet.getId() + " and s.DOMAIN_NAME like  '%" + queryCVSet.getSiteList().get(0).getDomainName() + "%'";
				
				List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
				if(queryCVSet.getSiteList().get(0).getDomainName() != null && !queryCVSet.getSiteList().get(0).getDomainName().equals(""))
				{
					if (systemSite.size() == 0) continue;
					cvSet.setSiteList(systemSite);
				}
			}
			
			String sql_exist = "select count(1) from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
			Long cnt =  getJdbcTemplate().queryForLong(sql_exist);					
			
			if (cnt > 0) {
				String sql_get = "select USERIMAGE_ID as id from CV_SET_USER_IMAGE where CV_SET_ID=" + cvSet.getId();
				List<UserImage> userImageList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				List<UserImage> userImageList_ = new ArrayList<UserImage>();
				for(UserImage userImage: userImageList){
					userImage = localUserImageManageDAO.getUserImageList(userImage).get(0);
					userImageList_.add(userImage);
				}
				cvSet.setUserImageList(userImageList_);
				/*UserImage image = new UserImage();
				Long image_id = getJdbcTemplate().queryForLong(sql_get);
				if (image_id != null) {
					image.setId(image_id);
					List<UserImage> userImageList = localUserImageManageDAO.getUserImageList(image);
					cvSet.setUserImageList(userImageList);
				}*/
			}
			
			sql_exist = "select count(1) from CV_SET_EXPERT where CV_SET_ID=" + cvSet.getId();
			cnt = getJdbcTemplate().queryForLong(sql_exist);
			
			if (cnt > 0) {
				String sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 1 and CV_SET_ID=" + cvSet.getId() + ")";				
				List<ExpertInfo> managerList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:managerList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 2 and CV_SET_ID=" + cvSet.getId() + ")";
				List<ExpertInfo> teacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:teacherList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 3 and CV_SET_ID=" + cvSet.getId() + ")";
				List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
				for (ExpertInfo info:otherTeacherList) {
					info = localExpertManageDAO.getExpertInfo(info.getId());
				}
				
				cvSet.setManagerList(managerList);
				cvSet.setTeacherList(teacherList);
				cvSet.setOtherTeacherList(otherTeacherList);
				
			}
			
			String sql_scheID = "select CV_SCHEDULE_ID as schedule_id from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId();
			List<CVSchedule> scheduleListID = getJdbcTemplate().query(sql_scheID, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
			
			String sql_schedule = "";
			if(queryCVSet.getType() != null && !queryCVSet.getType().equals(0))
			{
				sql_schedule = "select distinct cs.CV_ID as ID, cs.START_DATE, cs.END_DATE from CV_SCHEDULE as cs left join cv as c on cs.cv_id = c.id where cs.SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId() + ")" + " and c.brand in (" + queryCVSet.getType()+")";
			}
			else
			{
				sql_schedule = "select distinct CV_ID as ID, START_DATE, END_DATE from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId() + ")";	
			}
			List<CVSchedule> _scheduleList = getJdbcTemplate().query(sql_schedule, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
			if(_scheduleList == null || _scheduleList.size() == 0)
			{
				continue;
			}
			List<CVSchedule> scheduleList = new ArrayList<CVSchedule>();
			
			for(CVSchedule schedule:_scheduleList){				
				
				List<CV> cvList = new ArrayList<CV>();
				CV  queryCV = new CV();
				queryCV.setId(schedule.getId());
				cvList = localCVManageDAO.getCVList(queryCV);

				CVSchedule sch = new CVSchedule(cvList.get(0));
				
				sch.setId(schedule.getId());
				sch.setStart_date(schedule.getStart_date());
				sch.setEnd_date(schedule.getEnd_date());
				scheduleList.add(sch);
			}
	
			cvSet.setCVScheduleList(scheduleList);
			
			result_list.add(cvSet);
			
		}
		
		return result_list;
	}
	
	@Override
	public Long addCVSet(CVSet cvSet) {
		
		if (cvSet.getId() > 0 ) {
			this.deleteCVSet(cvSet);
		} else {					
			cvSet.setId(this.getNextId("CV_SET"));	
			cvSet.setStatus(Integer.parseInt("3"));
		}
		String sql_add = "INSERT INTO CV_SET (id, name, forma, code, file_path, introduction, announcement, knowledge_needed, knowledge_base, reference, reference_lately, status, create_date, maker, opinion, opinion_type, report) VALUES (:id, :name, :forma, :code, :file_path, :introduction, :announcement, :knowledge_needed, :knowledge_base, :reference, :reference_lately, :status, sysdate(), :maker, :opinion, :opinionType, :report)";
		getSimpleJdbcTemplate().update(sql_add, new BeanPropertySqlParameterSource(cvSet));
		
		List<UserImage> userImageList = cvSet.getUserImageList();
		if (userImageList != null) {
			for (UserImage image:userImageList) {
				sql_add = "insert into CV_SET_USER_IMAGE (CV_SET_ID, USERIMAGE_ID) values (?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), image.getId());
			}
		}
		
		List<ExpertInfo> managerList = cvSet.getManagerList();
		if (managerList != null) {
			for (ExpertInfo expert:managerList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 1);
			}
			
		}
		List<ExpertInfo> teacherList = cvSet.getTeacherList();
		if (teacherList != null) {
			for (ExpertInfo expert:teacherList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 2);
			}
			
		}
		List<ExpertInfo> otherTeacherList = cvSet.getOtherTeacherList();
		if (otherTeacherList != null) {
			for (ExpertInfo expert:otherTeacherList) {
				sql_add = "insert into CV_SET_EXPERT (CV_SET_ID, EXPERT_ID, TYPE) values (?, ?, ?)";
				getJdbcTemplate().update(sql_add, cvSet.getId(), expert.getId(), 3);
			}
			
		}
		
		List<CVSchedule> scheduleList = cvSet.getCVScheduleList();
		if (scheduleList != null) {			
			
			/*if(cvSet.getId()>0){
				for (CVSchedule schedule:scheduleList) {					
					sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, schedule.getSchedule_id(), schedule.getId());
					
					sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, cvSet.getId(), schedule.getSchedule_id());
				}
			}else{*/
				Long schedule_ID;
				for (CVSchedule schedule:scheduleList) {				
					schedule_ID = this.getNextId("CV_SCHEDULE");
					sql_add = "insert into CV_SCHEDULE (SCHEDULE_ID, CV_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, schedule_ID, schedule.getId());
					sql_add = "insert into CV_SET_SCHEDULE (CV_SET_ID, CV_SCHEDULE_ID) values (?, ?)";
					getJdbcTemplate().update(sql_add, cvSet.getId(), schedule_ID);
				//}	
			}			
		}
		
		return cvSet.getId();
	}

	@Override
	public boolean updateCVSet(CVSet cvSet) {
		this.addCVSet(cvSet);		
		return true;
	}

	@Override
	public boolean deleteCVSet(CVSet cvSet) {
		
		Long del_id = cvSet.getId();
		
		String sql_del = "delete from CV_SET_USER_IMAGE where CV_SET_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SET_ORG where CV_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SET_EXPERT where CV_SET_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID form CV_SET_SCHEDULE where CV_SET_ID =" + del_id + ")";
		
		sql_del = "delete from CV_SET_SCHEDULE where CV_SET_ID=" + del_id;
		getJdbcTemplate().update(sql_del);
		
		sql_del = "delete from CV_SET where id="+del_id;
		getJdbcTemplate().update(sql_del);
		
		return true;
	}

	public CVSet getCVSetById(Long id, SystemUser user)
	{
		
		CVSet result;
		StringBuffer sql = new StringBuffer();
		
		StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t1.ID,t2.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,t2.CV_SET_LEVEL AS LEVEL,t2.CV_SET_SCORE AS SCORE,t2.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("t2.CV_SET_COST_TYPE AS cost_type,t2.CV_SET_COST AS COST,t2.CV_SET_START_DATE AS START_DATE,t2.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("t2.CV_SET_BREAK_DAYS AS BREAK_DAYS,t1.NAME,t1.FORMA,t1.CODE,t1.FILE_PATH,t1.INTRODUCTION,t1.ANNOUNCEMENT,t1.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t1.KNOWLEDGE_BASE,t1.REFERENCE,t1.REFERENCE_LATELY,t1.OPINION,t1.REPORT,t1.OPINION_TYPE,t1.STATUS,t1.TYPE,t1.MAKER,t1.DELI_MAN,");
		cvSetColumnSQL.append("t1.CREATE_DATE,t1.DELI_DATE,t1.PROVINCEID,t1.CITYID,t1.RELATION_QUIZ,t1.cv_set_type");
		
		sql.append("select " + cvSetColumnSQL + " ");
		
		sql.append(" from cv_set t1 left join cv_set_authorization t2 on t2.cv_set_id = t1.id where t1.id = ").append(id);
		
		if(user!=null&&user.getUserConfig()!=null){
        	sql.append(" and t1.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID "
        			+ "WHERE csar.PROP_VAL_ID = " + user.getUserConfig().getUserProvinceId() + ") ");
        }else{
        	sql.append(" and t1.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID "
        			+ "HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }
		
		sql.append("group by t1.id ");
		try{
			result = getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		sql.delete(0, sql.length());
		sql.append("select DISTINCT s.* from cv_set_authorization csa LEFT JOIN cv_set_authorization_system_site csass ON csass.AUTHORIZATION_ID = csa.ID left join system_site s on s.ID = csass.SYSTEM_SITE_ID where csa.CV_SET_ID = ").append(result.getId());
		List<SystemSite> systemSite = getJdbcTemplate().query(sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
		result.setSiteList(systemSite);
		
		sql.delete(0, sql.length());
		sql.append("select cs.SCHEDULE_ID as id,cv.Name,cv.serial_number,cv.brand,cv.introduction,cv.announcement,cv.file_path,cv.create_date,cv.creator from cv_set_schedule css LEFT JOIN cv_schedule cs on css.CV_SCHEDULE_ID = cs.SCHEDULE_ID LEFT JOIN cv as cv on cs.CV_ID = cv.ID where css.CV_SET_ID = ").append(result.getId()).append(" order by cs.start_date desc");
		List<CVSchedule> scheduleList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));	
		result.setCVScheduleList(scheduleList);
		
		String sql_get = "select * from EXPERT_INFO where ID in (select EXPERT_ID from CV_SET_EXPERT where TYPE = 1 and CV_SET_ID=" + result.getId() + ")";				
		List<ExpertInfo> managerList = getJdbcTemplate().query(sql_get, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));				
		result.setManagerList(managerList);
		return result;
	}

	public int updateDistribute(List<Object> saveParams)
	{
		CVSet saveCV = (CVSet)saveParams.get(0);
		String siteIds = (String)saveParams.get(1);
		
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update cv_set set ");
		if(null != saveCV.getProvinceId()){
			sql.append("PROVINCEID=?,");
			values.add(saveCV.getProvinceId());
		}
		if(null != saveCV.getCityId()){
			sql.append("CITYID=?,");
			values.add(saveCV.getCityId());
		}
		if(null != saveCV.getLevel()){
			sql.append("LEVEL=?,");
			values.add(saveCV.getLevel());
		}
		if(null != saveCV.getScore()){
			sql.append("SCORE=?,");
			values.add(saveCV.getScore());
		}
		if(!StringUtils.checkNull(saveCV.getSerial_number())){
			sql.append("SERIAL_NUMBER=?,");
			values.add(saveCV.getSerial_number());
		}
		if(!StringUtils.checkNull(saveCV.getSign())){
			sql.append("SIGN=?,");
			values.add(saveCV.getSign());
		}
		if(null != saveCV.getCost()){
			sql.append("cost=?,");
			values.add(saveCV.getCost());
		}
		
		if(null != saveCV.getBreak_days()){
			sql.append("BREAK_DAYS=?,");
			values.add(saveCV.getBreak_days());
		}
		if(saveCV.getStart_date() != null){
			/*sql.append("start_date = to_date(?,'yyyy-mm-dd'),");
			values.add(saveCV.getStart_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库			
			sql.append("start_date = " + FuncMySQL.longDateForUpdateNoArg(saveCV.getStart_date()) + " ,");//库里是长日期，丫存短日期艹艹艹艹
		}
		if(null != saveCV.getEnd_date()){
			/*sql.append("end_date=to_date(?,'yyyy-mm-dd'),");
			values.add(saveCV.getEnd_date());*/
			
			//YHQ,2017-06-22,函数替换，迁移到分布式数据库
			sql.append("end_date = " + FuncMySQL.longDateForUpdateNoArg(saveCV.getEnd_date()) + " ,");
		}
		sql.append(" id = ? where id = ?");
		values.add(saveCV.getId());
		values.add(saveCV.getId());
		
		
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result > 0)
		{	
			for(CVSchedule schedule : saveCV.getCVScheduleList())
			{
				sql.delete(0, sql.length());
				/*sql.append("update cv_schedule set START_DATE = to_date(?,'yyyy-mm-dd'), END_DATE = to_date(?,'yyyy-mm-dd') where schedule_id = ?");
				List sval = new ArrayList();
				sval.add(schedule.getStart_date());
				sval.add(schedule.getEnd_date());
				sval.add(schedule.getId());*/
				
				
				//YHQ,2017-06-22,函数替换，迁移到分布式数据库
				//库里存的是长日期，丫存短日期艹艹艹艹艹艹艹艹艹艹艹艹
				sql.append("update cv_schedule set START_DATE = " + FuncMySQL.longDateForUpdateNoArg(schedule.getStart_date()) + " , END_DATE = " + FuncMySQL.longDateForUpdateNoArg(schedule.getEnd_date()) + " where schedule_id = ?") ;
				List sval = new ArrayList();				
				sval.add(schedule.getId());				
				result = getJdbcTemplate().update(sql.toString(),sval.toArray());
			}
		}
		if(siteIds.charAt(siteIds.length() - 1) == ',')
		{
			siteIds = siteIds.substring(0, siteIds.length()-1);
		}
		String[] siteArr = siteIds.split(",");
		sql.delete(0, sql.length());
		sql.append("delete from cv_set_system_site where cv_set_id = ").append(saveCV.getId());
		result = getJdbcTemplate().update(sql.toString());
		for(String siteId : siteArr)
		{
			sql.delete(0, sql.length());
			sql.append("insert into cv_set_system_site set cv_set_id =").append(saveCV.getId());
			sql.append(",system_site_id=").append(siteId);
			getJdbcTemplate().update(sql.toString());
		}
		return result;
	}

	public CVManageDAO getLocalCVManageDAO() {
		return localCVManageDAO;
	}

	public void setLocalCVManageDAO(CVManageDAO localCVManageDAO) {
		this.localCVManageDAO = localCVManageDAO;
	}

	public UserImageManageDAO getLocalUserImageManageDAO() {
		return localUserImageManageDAO;
	}

	public void setLocalUserImageManageDAO(UserImageManageDAO localUserImageManageDAO) {
		this.localUserImageManageDAO = localUserImageManageDAO;
	}

	public ExpertManageDAO getLocalExpertManageDAO() {
		return localExpertManageDAO;
	}

	public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
		this.localExpertManageDAO = localExpertManageDAO;
	}
	
	public List<CVSet> getCVSetListFromUser(MyStudyInfo queryCVSet, PageList page)
	{
		StringBuffer sql = new StringBuffer();
		List<CVSet> result;
		sql.append("select c.* from cv_set_entity_info e left join cv_set c on e.cv_set_id = c.id where e.id > 0");
		if(!StringUtils.checkNull(queryCVSet.getSign())){
			sql.append(" and c.sign like '%"+queryCVSet.getSign()+"%'");
		}
		if(queryCVSet.getFavorite() != null)
		{
			sql.append(" and e.favorite=").append(queryCVSet.getFavorite());
		}
		if(queryCVSet.getUserId() != null && !queryCVSet.getUserId().equals(0L))
		{
			sql.append(" and e.user_id=").append(queryCVSet.getUserId());
		}
		result = getList(PageUtil.getPageSql(sql.toString(), page.getPageNumber(), page.getObjectsPerPage()), new ArrayList<Object>(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		for(CVSet cvSet:result){			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
		}
		return result;
	}
	@Override
	public Integer getStudentNum(CVSet cv)
	{
		Integer result = 0;
		if(cv.getId() != null)
		{
			StringBuffer sql = new StringBuffer();
			sql.append("select count(distinct id) from cv_set_entity_info where status = 1 and cv_set_id = ").append(cv.getId());
			result = getJdbcTemplate().queryForObject(sql.toString(),Integer.class);
		}
		return result;
	}

	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param cv_set_id,user_id
	 * @return
	 * 
	 * Detail : Delete user's favorite 'cv_set'.
	 * 
	 */
	@Override
	public void delFav(String id, String userId) {
		String delFav = "delete from cv_set_favorites where CV_SET_ID="+id+" and SYSTEM_USER_ID=?";
		getJdbcTemplate().update(delFav,userId);
	}

	@Override
	public int getCVSetListFromUserSize(MyStudyInfo queryCVSet) {
		StringBuffer sql = new StringBuffer();
		List<CVSet> result;
		sql.append("select count(c.id) from cv_set_entity_info e left join cv_set c on e.cv_set_id = c.id where e.id > 0");
		if(!StringUtils.checkNull(queryCVSet.getSign())){
			sql.append(" and c.sign like '%"+queryCVSet.getSign()+"%'");
		}
		if(queryCVSet.getFavorite() != null)
		{
			sql.append(" and e.favorite=").append(queryCVSet.getFavorite());
		}
		if(queryCVSet.getUserId() != null && !queryCVSet.getUserId().equals(0L))
		{
			sql.append(" and e.user_id=").append(queryCVSet.getUserId());
		}
		
		return getJdbcTemplate().queryForInt(sql.toString());
	}
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param system_user_id
	 * @return
	 * 
	 * Detail : Delete user's favorite 'cv_set'.
	 * 
	 */
	@Override
	public int getFavoriteCVSetListFromUserSize(CVSetFavorites queryCVSetFav) {
		StringBuffer sql = new StringBuffer();
		List list = new ArrayList();
		List<CVSet> result;
		sql.append("select count(DISTINCT c.id) from cv_set_favorites f left join cv_set c on f.cv_set_id = c.id left join cv_set_authorization t2 on t2.cv_set_id = c.id where c.id > 0");
		if(!StringUtils.checkNull(queryCVSetFav.getSign())){
			sql.append(" and t2.CV_SET_SIGN like ?");
			list.add("%" + queryCVSetFav.getSign() + "%");
			
		}
		if(queryCVSetFav.getSystem_user_id() != null && !queryCVSetFav.getSystem_user_id().equals(0L))
		{
			sql.append(" and f.system_user_id=?");
			list.add(queryCVSetFav.getSystem_user_id());
		}
		
		//添加项目省份筛选条件
        if(queryCVSetFav.getUserProvinceCode()!=null){
        	sql.append(" AND c.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID  WHERE csar.PROP_VAL_ID = " + queryCVSetFav.getUserProvinceCode() + ") ");	
        }else{
        	sql.append(" AND c.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }
		
		return getJdbcTemplate().queryForInt(sql.toString(),list.toArray());
	}
	public List<CVSet> getFavoriteCVSetListFromUser(CVSetFavorites queryCVSetFav, PageList page)
	{
		StringBuffer sql = new StringBuffer();
		List<CVSet> result;
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("c.ID,t2.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,t2.CV_SET_LEVEL AS LEVEL,t2.CV_SET_SCORE AS SCORE,t2.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("t2.CV_SET_COST_TYPE AS cost_type,t2.CV_SET_COST AS COST,t2.CV_SET_START_DATE AS START_DATE,t2.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("t2.CV_SET_BREAK_DAYS AS BREAK_DAYS,c.NAME,c.FORMA,c.CODE,c.FILE_PATH,c.INTRODUCTION,c.ANNOUNCEMENT,c.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("c.KNOWLEDGE_BASE,c.REFERENCE,c.REFERENCE_LATELY,c.OPINION,c.REPORT,c.OPINION_TYPE,c.STATUS,c.TYPE,c.MAKER,c.DELI_MAN,");
		cvSetColumnSQL.append("c.CREATE_DATE,c.DELI_DATE,c.PROVINCEID,c.CITYID,c.RELATION_QUIZ,c.cv_set_type");
		sql.append("select " + cvSetColumnSQL + " from cv_set_favorites f left join cv_set c on f.cv_set_id = c.id left join cv_set_authorization t2 on t2.cv_set_id = c.id where c.id > 0");
		if(!StringUtils.checkNull(queryCVSetFav.getSign())){
			sql.append(" and t2.CV_SET_SIGN like '%"+queryCVSetFav.getSign()+"%'");
		}
		if(queryCVSetFav.getSystem_user_id() != null && !queryCVSetFav.getSystem_user_id().equals(0L))
		{
			sql.append(" and f.system_user_id=").append(queryCVSetFav.getSystem_user_id());
		}
		
		//添加项目省份筛选条件
        if(queryCVSetFav.getUserProvinceCode()!=null){
        	sql.append(" AND c.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID  WHERE csar.PROP_VAL_ID = " + queryCVSetFav.getUserProvinceCode() + ") ");	
        }else{
        	sql.append(" AND c.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }
        
        sql.append(" group by c.id ");
		
		result = getList(PageUtil.getPageSql(sql.toString(), page.getPageNumber(), page.getObjectsPerPage()), new ArrayList<Object>(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		for(CVSet cvSet:result){			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
		}
		return result;
	}

    @Override
    public void queryCVSetPageList(PageList<CVSet> pl, CVSet queryCVSet,SystemUser user) {
    	List<Object> params = new ArrayList<Object>();//参数列表
        StringBuilder sql = new StringBuilder();
        //sql.append("from content_edition_ref t ,cv_set t2,cv_set_system_site t3,system_site t4 where t.ref_id=t2.id ");
        //sql.append("and t2.id=t3.cv_set_id and t3.system_site_id=t4.id and t4.domain_name=? and t.edition_id=? and t2.status=? and t.check_state = 1 and t2.START_DATE <= NOW() and t2.END_DATE >= NOW() "); //YHQ，2017-03-11，添加时间范围
        //sql.append("FROM cv_set t1 LEFT JOIN cv_set_authorization t2 ON t2.CV_SET_ID = t1.ID LEFT JOIN cv_set_authorization_system_site t3 ON t3.AUTHORIZATION_ID = t2.ID LEFT JOIN cv_set_authorization_region t4 ON t4.AUTHORIZATION_ID = t2.ID LEFT JOIN system_site t5 ON t5.ID = t3.SYSTEM_SITE_ID LEFT JOIN content_edition_ref t6 ON t6.REF_ID = t1.ID ");
        sql.append("FROM cv_set t1 LEFT JOIN cv_set_authorization t2 ON t2.CV_SET_ID = t1.ID LEFT JOIN cv_set_authorization_system_site t3 ON t3.AUTHORIZATION_ID = t2.ID LEFT JOIN system_site t5 ON t5.ID = t3.SYSTEM_SITE_ID LEFT JOIN content_edition_ref t6 ON t6.REF_ID = t1.ID ");
        sql.append("WHERE t5.DOMAIN_NAME = ? AND t6.EDITION_ID = ? AND t1.`STATUS` = ? AND t6.CHECK_STATE = 1 AND t2.CV_SET_START_DATE <= CURDATE() AND t2.CV_SET_END_DATE >= CURDATE()");
        
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getEdtionId());
        
        // 依后台管理->内容管理-> 页面管理中绑定内容显示
        /*
        if(queryCVSet.getEdtionId() == 7){
        	sql.append(" and t2.sign like '%指南解读%'");
        }*/
        params.add(queryCVSet.getStatus());
        
        //添加项目省份筛选条件
        if(user!=null&&user.getUserConfig()!=null&&user.getUserConfig().getUserProvinceId()!=null){
        	//sql.append(" and t2.id in (select cv_set_id from CV_REGION cr where cr.region_id = ?) ");	
        	sql.append(" AND t1.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID  WHERE csar.PROP_VAL_ID = ?) ");	
        	params.add(user.getUserConfig().getUserProvinceId());
        }else{
        	//没有传省份时，查询授权全国省份的项目
        	//查询省份总数
        	//select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20
        	//授权全国的项目id
        	//select cv_set_id from CV_REGION t group by cv_set_id having count(region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)
        	//sql.append(" and t2.id in (select cv_set_id from CV_REGION cr group by cr.cv_set_id having count(cr.region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)) ");
        	sql.append(" AND t1.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }


        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t1.ID,t2.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,t2.CV_SET_LEVEL AS LEVEL,t2.CV_SET_SCORE AS SCORE,t2.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("t2.CV_SET_COST_TYPE AS cost_type,t2.CV_SET_COST AS COST,t2.CV_SET_START_DATE AS START_DATE,t2.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("t2.CV_SET_BREAK_DAYS AS BREAK_DAYS,t1.NAME,t1.FORMA,t1.CODE,t1.FILE_PATH,t1.INTRODUCTION,t1.ANNOUNCEMENT,t1.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t1.KNOWLEDGE_BASE,t1.REFERENCE,t1.REFERENCE_LATELY,t1.OPINION,t1.REPORT,t1.OPINION_TYPE,t1.STATUS,t1.TYPE,t1.MAKER,t1.DELI_MAN,");
		cvSetColumnSQL.append("t1.CREATE_DATE,t1.DELI_DATE,t1.PROVINCEID,t1.CITYID,t1.RELATION_QUIZ,t1.cv_set_type");
		
		//String get = "select t2.* ";
		String get = "select " + cvSetColumnSQL + " ";
        String get_count = "select count(DISTINCT t1.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        //sql.append(" order by t.ordernum ");
        sql.append(" GROUP BY t1.ID order by t6.sort ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CVSet> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        pl.setList(list);
    }

    public void queryCVSetPageList2(Pager<CVSet> pl, CVSet queryCVSet) {

        StringBuilder sql = new StringBuilder();
        sql.append("from cv_set t left join cv_set_authorization csa on csa.cv_set_id = t.id "
        		+ " left join cv_set_authorization_system_site t5 on csa.id=t5.AUTHORIZATION_ID ");
        sql.append(" left join system_site t6 on t5.system_site_id=t6.id ");
        sql.append(" left join cv_set_score t7 on t7.cv_set_id=t.id ");

        sql.append("where t6.domain_name=? and t.status=? and exists (");
        sql.append("select 1 from cv_set_user_image t2 where t2.cv_set_id=t.id) ");
        List<Object> params = new ArrayList<Object>();
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());

        if (queryCVSet.getName() != null && !"".equals(queryCVSet.getName())){
            sql.append(" and t.name like ? ");
            params.add("%" + queryCVSet.getName() + "%");
        }

        if (queryCVSet.getLevel() != null && queryCVSet.getLevel() > 0) {
            sql.append(" and t.level = ? ");
            params.add(queryCVSet.getLevel());
        }

        if(queryCVSet.getSign() != null && !"".equals(queryCVSet.getSign())){
            sql.append(" and t.sign like ? ");
            params.add("%" + queryCVSet.getSign() + "%");
        }

        if(queryCVSet.getStart_date() != null){
            sql.append(" and t.start_date <= ? "); 
            sql.append(" and t.end_date >= ? "); 
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 数据库由date类型改为datetime，造成当天发布的项目查询不出来，2017-03-03
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 数据库由date类型改为datetime，造成当天发布的项目查询不出来，2017-03-03
        }

        if(queryCVSet.getUserProvinceCode()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + queryCVSet.getUserProvinceCode() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        if(queryCVSet.getPropName() != null){
            sql.append("and exists (select 1 from cv_set_user_image t2,qm_user_image t3,qm_user_image_prop t4,exam_prop_val t5,prop_val_ref t6 where t2.cv_set_id=t.ID and t2.userimage_id = t3.id and t3.id = t4.userimage_id and t5.id = t6.prop_val1 and t5.name=? and (t4.prop_id = t6.prop_val2 or t4.prop_id = t6.prop_val1))");
            params.add(queryCVSet.getPropName());
        }
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");

        String get = "select " + cvSetColumnSQL + ",t7.grade,t7.study_times ";
        String get_count = "select count(t.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setCount(fullListSize);

        sql.append(" group by t.id order by t.id ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CVSet> list = getJdbcTemplate().query(
        		PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        pl.setList(list);
    }

    public CVSet getCVSetById(CVSet query) {

        StringBuilder sql = new StringBuilder();
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
		
		sql.append(" select " + cvSetColumnSQL + ", ");
        
        sql.append(" t7.* from cv_set t left join cv_set_score t7 on t.id=t7.cv_set_id "
        		+ "left join cv_set_authorization csa on csa.cv_set_id = t.id "
        		+ "left join cv_set_authorization_system_site csass on csass.AUTHORIZATION_ID = csa.id "
        		+ "left join system_site t6 on t6.id = csass.SYSTEM_SITE_ID ");
        sql.append(" where t6.domain_name=? ");
        sql.append(" and t.status=? and t.start_date <= CURDATE() and t.end_date >= CURDATE() and t.id= ? ");
        
        List<Object> params = new ArrayList<Object>();
        params.add(query.getServerName());
        params.add(query.getStatus());
        //params.add(DateUtil.format(query.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
        //params.add(DateUtil.format(query.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
        params.add(query.getId());
        
        if(query.getUserProvinceCode()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = ?) ");
        	params.add(query.getUserProvinceCode());
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        sql.append(" group by t.id ");

        List<CVSet> list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class),params.toArray());
        if(!list.isEmpty()){
            return list.get(0);
        }

        return null;
    }

    //根据项目id查询所属学科
    public List<ExamProp> getExamPropList(Long id){

        StringBuilder sql = new StringBuilder();
        sql.append("select * from exam_prop_val t ");
        sql.append("where exists ( ");
        sql.append("select 1 from cv_set_user_image t2 ");
        sql.append("join qm_user_image t3 on t2.userimage_id=t3.id ");
        sql.append("join qm_user_image_prop t4 on t3.id=t4.userimage_id ");
        sql.append("where t2.cv_set_id=? and t.id=t4.prop_id)");

        return getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),
                id);
    }

    @Override
    public List<ExpertInfo> getManagerList(Long id, int type) {
        String sql = "select t2.*,t3.name as jobName from cv_set_expert t join expert_info t2 on t.expert_id=t2.id left join exam_prop_val t3 on t2.job=t3.id  where t.cv_set_id=? and t.type=?"; 
    
        return getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class),
                id,type);
    }
    //查找直播的授课教师
  
    public List<ExpertInfo> getTeacherList1(Long id, int type) {
        String sql = "select t2.*,t3.name as jobName from cv_set_expert t join expert_info t2 on t.expert_id=t2.id left join exam_prop_val t3 on t2.job=t3.id  where t.cv_set_id=? and t.type=?"; 
   
        return getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class),
                id,type);
    }

    @Override
    public List<CV> getCvList(Long id) {
    	//YHQ，2015-05-30，添加按课程顺序排序字段
        String sql = "select t.* from cv t join cv_schedule t2 on t.id=t2.cv_id join cv_set_schedule t3 on t2.schedule_id=t3.cv_schedule_id where t3.cv_set_id = ?  order by sequenceNum asc "; 

        return getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(CV.class),
                id);
    }

    @Override
    public List<CVUnit> getUnitList(Long id) {
        String sql = "select t3.* from cv t join cv_ref_unit t2 on t.id=t2.cv_id join cv_unit t3 on t2.unit_id=t3.id where t.id=?";

        return getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class),
                id);
    }
    
    /**
     * @author Tiger.
     * @time 2017-1-13
     * @param pl
     * @param queryCVSet
     * 
     * Detail : Get the CVSet list form xueke id and edition.
     */
    @Override
    public void queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet) {

        StringBuilder sql = new StringBuilder();
        sql.append("from content_edition_ref ref, cv_set t, cv_set_authorization csa, cv_set_authorization_system_site t5,system_site t6 ");
        sql.append("where ref.ref_id=t.id AND t.id = csa.CV_SET_ID AND csa.ID = t5.AUTHORIZATION_ID AND t5.system_site_id = t6.id and t6.domain_name=? and t.status=? and ref.edition_id=? and ref.check_state = 1 and exists (");
        sql.append("select 1 from cv_set_user_image t2 where t2.cv_set_id=t.id) ");
        List<Object> params = new ArrayList<Object>();
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());
        params.add(queryCVSet.getEdtionId());

        if (queryCVSet.getName() != null && !"".equals(queryCVSet.getName())){
            sql.append(" and t.name like ? ");
            params.add("%" + queryCVSet.getName() + "%");
        }

        if (queryCVSet.getLevel() != null && queryCVSet.getLevel() > 0) {
            sql.append(" and csa.CV_SET_LEVEL = ? ");
            params.add(queryCVSet.getLevel());
        }

        if(queryCVSet.getSign() != null && !"".equals(queryCVSet.getSign())){
            sql.append(" and csa.CV_SET_SIGN like ? ");
            params.add("%" + queryCVSet.getSign() + "%");
        }

        if(queryCVSet.getStart_date() != null){
            sql.append(" and csa.CV_SET_START_DATE <= ? ");
            sql.append(" and csa.CV_SET_END_DATE >= ? ");
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
        }

        if(queryCVSet.getUserProvinceCode()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + queryCVSet.getUserProvinceCode() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        if(queryCVSet.getPropName() != null){
            sql.append("and exists (select 1 from cv_set_user_image t2,qm_user_image t3,qm_user_image_prop t4,exam_prop_val t5,prop_val_ref t6 where t2.cv_set_id=t.ID and t2.userimage_id = t3.id and t3.id = t4.userimage_id and t5.id = t6.prop_val1 and t5.name=? and (t4.prop_id = t6.prop_val2 or t4.prop_id = t6.prop_val1))");
            params.add(queryCVSet.getPropName());
        }

        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
        
        String get = "select DISTINCT " + cvSetColumnSQL + " ";
        String get_count = "select count(DISTINCT t.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" group by t.id order by t.id ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CVSet> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        
        for (CVSet cvSet : list) {
            //项目负责人
            cvSet.setManagerList(getManagerList(cvSet.getId(), 1));
            //授课教师
//            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
        }
        pl.setList(list);
    }
    
    /**
     * @author 杨红强
     * @time 2017-2-17
     * @param pl
     * @param queryCVSet
     * 
     * Detail : 获取我的学科下的慕课列表，YHQ 2017-02-17，改自queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet)
     */
    @Override
    public void getMyXuekeMukeList(PageList<CVSet> pl, CVSet queryCVSet) {
    	if (queryCVSet.getPropId()== null) {
    		pl.setFullListSize(0);
    		return ;
    	}
        StringBuilder sql = new StringBuilder();
        sql.append(" from content_edition_ref ref, cv_set t, cv_set_authorization csa,cv_set_authorization_system_site t5, system_site t6 ,cv_set_user_image x2,qm_user_image x3,qm_user_image_prop x4 "
        		+ "where ref.ref_id=t.id AND t.id = csa.CV_SET_ID AND csa.ID = t5.AUTHORIZATION_ID AND t5.system_site_id = t6.id and t6.domain_name=? and t.status=? and ref.edition_id=? and exists (select 1 from cv_set_user_image t2 where t2.cv_set_id=t.id) and t5.cv_set_id = x2.cv_set_id and x2.userimage_id=x3.id and x3.id=x4.userimage_id and x4.prop_id =?");
        
        List<Object> params = new ArrayList<Object>();
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());
        params.add(queryCVSet.getEdtionId());
        params.add(queryCVSet.getPropId());

        if (queryCVSet.getName() != null && !"".equals(queryCVSet.getName())){
            sql.append(" and t.name like ? ");
            params.add("%" + queryCVSet.getName() + "%");
        }

        if (queryCVSet.getLevel() != null && queryCVSet.getLevel() > 0) {
            sql.append(" and csa.CV_SET_LEVEL = ? ");
            params.add(queryCVSet.getLevel());
        }

        if(queryCVSet.getSign() != null && !"".equals(queryCVSet.getSign())){
            sql.append(" and csa.CV_SET_SIGN like ? ");
            params.add("%" + queryCVSet.getSign() + "%");
        }

        if(queryCVSet.getStart_date() != null){
            sql.append(" and csa.CV_SET_START_DATE <= ? ");
            sql.append(" and csa.CV_SET_END_DATE >= ? ");
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
        }

        if(queryCVSet.getUserProvinceCode()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + queryCVSet.getUserProvinceCode() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        /*
        if(queryCVSet.getPropName() != null){
            sql.append("and exists (select 1 from cv_set_user_image t2,qm_user_image t3,qm_user_image_prop t4,exam_prop_val t5,prop_val_ref t6 where t2.cv_set_id=t.ID and t2.userimage_id = t3.id and t3.id = t4.userimage_id and t5.id = t6.prop_val1 and t5.name=? and (t4.prop_id = t6.prop_val2 or t4.prop_id = t6.prop_val1))");
            params.add(queryCVSet.getPropName());
        }*/

        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
        
        String get = "select distinct " + cvSetColumnSQL + " ";
        String get_count = "select count(distinct(t.id)) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" group by t.id order by t.id ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CVSet> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        
        for (CVSet cvSet : list) {
            //项目负责人
            cvSet.setManagerList(getManagerList(cvSet.getId(), 1));
            //授课教师
//            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
        }
        pl.setList(list);
    }

    /**
     * @author Tiger.
     * @time 2017-1-13
     * @param pl
     * @param user
     * 
     * Detail : Get the bind list from user.
     */
	@Override
	public void getBindList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append("from cv_set t left join cv_set_authorization csa on csa.CV_SET_ID = t.id "
				+ "left join cv_set_authorization_system_site t5 on csa.id = t5.AUTHORIZATION_ID left join system_site t6 on t5.system_site_id = t6.id "
				+ "where t.id > 0 and t6.domain_name = ? and t.status = ?");
		sql.append(" and t.id not in (select cv_set_id from log_study_cv_set where system_user_id = ?)");

		List<Object> params = new ArrayList<Object>();
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());
        params.add(user.getUserId());
        
        if(user.getUserConfig()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + user.getUserConfig().getUserProvinceId() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        if(queryCVSet.getStart_date() != null){
            sql.append(" and csa.CV_SET_START_DATE <= ? ");
            sql.append(" and csa.CV_SET_END_DATE >= ? ");
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
        }
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
        
		String get = "select " + cvSetColumnSQL + " ";
        String get_count = "select count(t.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" group by t.id order by t.id ");
        get += sql.toString();
        
        List<CVSet> list = getJdbcTemplate().query(
        PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class),params.toArray());
        
        for (CVSet cvSet : list) {
            //项目负责人
            cvSet.setManagerList(getManagerList(cvSet.getId(), 1));
            //授课教师
//            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
        }
        pl.setList(list);
	}
	
	/**
     * @author 杨红强.
     * @time 2017-2-17
     * @param pl
     * @param user
     * 
     * Detail : 获取我的学科下的项目列表，YHQ 2017-02-17；改造自getBindList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user)
     */
	@Override
	public void getMyXuekeProjectList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user) {
		if (StringUtils.checkNull(user.getProp_Id())) {
			pl.setFullListSize(0);
			return;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" from cv_set t left join cv_set_authorization csa on csa.CV_SET_ID = t.id "
				+ "left join cv_set_authorization_system_site t5 on csa.id = t5.AUTHORIZATION_ID left join system_site t6 on t5.system_site_id = t6.id "
				+ "inner join cv_set_user_image x2 on t.id = x2.cv_set_id left join qm_user_image x3 on x2.userimage_id=x3.id left join qm_user_image_prop x4 on x3.id=x4.userimage_id "
				+ "left join log_study_cv_set x5 on t.id=x5.cv_set_id and x5.system_user_id =? "
				+ "where t.id > 0 and t6.domain_name =? and t.status =?  and x4.prop_id =? and x5.cv_set_id is null ");		

		List<Object> params = new ArrayList<Object>();
		params.add(user.getUserId());
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());
        params.add(user.getProp_Id());
        
        if(user.getUserConfig()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + user.getUserConfig().getUserProvinceId() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        if(queryCVSet.getStart_date() != null){
            sql.append(" and csa.CV_SET_START_DATE <= ? ");
            sql.append(" and csa.CV_SET_END_DATE >= ? ");
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
            params.add(DateUtil.format(queryCVSet.getStart_date(), DateUtil.FORMAT_LONG));//FORMAT_SHORT——YHQ 2017-03-03，数据库类型由date改为datetime
        }
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
        
		String get = "select distinct(t.id)," + cvSetColumnSQL + " ";
        String get_count = "select count(distinct(t.id)) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" group by t.id order by t.id ");
        get += sql.toString();
        
        List<CVSet> list = getJdbcTemplate().query(
        PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class),params.toArray());
        
        for (CVSet cvSet : list) {
            //项目负责人
            cvSet.setManagerList(getManagerList(cvSet.getId(), 1));
            //授课教师
//            cvSet.setTeacherList(localCVSetManage.getManagerList(cvSet.getId(), 2));
        }
        pl.setList(list);
	}

	@Override
	public List<PeixunOrg> getPeixunOrgList(Long cvSetId) {
		String sql = "select t.* from peixun_org t, cv_set_org t2 where t.id=t2.org_id and t2.cv_id=?";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class), cvSetId);
	}

	/**
	 * @author Han
	 * @time	2017-01-26
	 * Description 项目评价心思保存，更新
	 */
	@Override
	public void updateCritiqueScoreLog(Long userId, CVSet query) {
		if (query==null||query.getId()==null)
			return;

		int scoreRatio = 20;//一星代表2分
		System.out.println(query.getCritiqueScore1() * scoreRatio);
		// delete old log.
		String sql = "delete from cv_set_score_log where CV_SET_ID=? and SYSTEM_USER_ID=?";
		getJdbcTemplate().update(sql, query.getId(), userId);
		
		// insert new log.
		sql = "INSERT INTO cv_set_score_log (CV_SET_ID, SYSTEM_USER_ID, SCORE1, SCORE2, SCORE3, SCORE4, SCORE_DATE, SCORE_CONTENT) VALUES (?, ?, ?, ?, ?, ?, sysdate(), ?)";
		getSimpleJdbcTemplate().update(sql, query.getId(), userId, query.getCritiqueScore1() * scoreRatio, query.getCritiqueScore2() * scoreRatio, query.getCritiqueScore3() * scoreRatio, query.getCritiqueScore4() * scoreRatio, query.getOpinion());
        
		//YHQ 2017-02-14 1618 kill----begin
		/*		
		// delete old cv_set score.
		sql = "delete from cv_set_score where cv_set_id=?";
		getJdbcTemplate().update(sql, query.getId());
		
		// get new cv_set score.
		sql = "select DISTINCT t.CV_SET_ID as id, s.GRADE, s.STUDY_TIMES, AVG(t.SCORE1) critiqueScore1, AVG(t.SCORE2) critiqueScore2, AVG(t.SCORE3) critiqueScore3, AVG(t.SCORE4) critiqueScore4 "
						+ "from cv_set_score_log t left join cv_set_score s on t.CV_SET_ID=s.CV_SET_ID where t.CV_SET_ID=?";
		List<CVSet>resList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), query.getId());
		
		// insert cv_set score.
		if (resList!=null && resList.size()>0){
			sql = "insert into cv_set_score (cv_set_id, grade, study_times, critiqueScore1, critiqueScore2, critiqueScore3, critiqueScore4)" +
					" VALUES (:id, :grade, :studyTimes, :critiqueScore1, :critiqueScore2, :critiqueScore3, :critiqueScore4)";
			
			// return result.
			query = resList.get(0);
			if (query.getGrade()==null)
				query.setGrade(0d);
			if (query.getStudyTimes()==null)
				query.setStudyTimes(0);
			
			getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(query));
		} 
		*/
		//YHQ 2017-02-14 1618 kill----over
		
		//YHQ 2017-02-14 1618  -----begin
		double grade = 0.0 , critiqueScore1 = 0.0 , critiqueScore2 = 0.0 , critiqueScore3 = 0.0 , critiqueScore4 = 0.0 ;		
		sql = "select * from cv_set_score where cv_set_id=?" ;
		List<CVSet> cssList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), query.getId());		
		
        NumberFormat nfObj = NumberFormat.getNumberInstance();                
        nfObj.setMaximumFractionDigits(2);// 保留两位小数        
        nfObj.setRoundingMode(RoundingMode.UP);// 如果不需要四舍五入，可以使用RoundingMode.DOWN        
		
		if (cssList != null && cssList.size() > 0){
			sql = "select count(1) from cv_set_score_log where CV_SET_ID =?" ;
			int csslNum = getJdbcTemplate().queryForInt(sql, query.getId()) ;
			boolean dataErrFlag = false ; //脏数据标识
			if (csslNum > 0) {
				sql = "select avg(SCORE1) as critiqueScore1,avg(SCORE2) as critiqueScore2,avg(SCORE3) as critiqueScore3,avg(SCORE4) as critiqueScore4 ,avg((SCORE1+SCORE2+SCORE3+SCORE4)/4) as score from cv_set_score_log where CV_SET_ID =?" ;
				List<CVSet> csslList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), query.getId());
				if (csslList != null && csslList.size() > 0){
					CVSet csslObj = csslList.get(0) ;
					critiqueScore1 = csslObj.getCritiqueScore1() ;
					critiqueScore2 = csslObj.getCritiqueScore2() ;
					critiqueScore3 = csslObj.getCritiqueScore3() ;
					critiqueScore4 = csslObj.getCritiqueScore4() ;					
					grade          = csslObj.getScore()  ;
					
					grade          = new Double(nfObj.format(grade))	;									
					critiqueScore1 = new Double(nfObj.format(critiqueScore1))	;					
					critiqueScore2 = new Double(nfObj.format(critiqueScore2))	;				
					critiqueScore3 = new Double(nfObj.format(critiqueScore3))	;					
					critiqueScore4 = new Double(nfObj.format(critiqueScore4))	;
				} else
					dataErrFlag = true ;
			} else 
				dataErrFlag = true ;
			
			if (dataErrFlag) {
				critiqueScore1 = query.getCritiqueScore1() * scoreRatio;
				critiqueScore2 = query.getCritiqueScore2() * scoreRatio;
				critiqueScore3 = query.getCritiqueScore3() * scoreRatio;
				critiqueScore4 = query.getCritiqueScore4() * scoreRatio;
				grade          = (query.getCritiqueScore1() + query.getCritiqueScore2() + query.getCritiqueScore3() + query.getCritiqueScore4()) /4 ;			
				grade          = new Double(nfObj.format(grade))	;
			}
												
			sql = "update cv_set_score set grade=?, critiqueScore1=?, critiqueScore2=?, critiqueScore3=?, critiqueScore4=? where cv_set_id=?" ;			
			getJdbcTemplate().update(sql, grade, critiqueScore1, critiqueScore2, critiqueScore3, critiqueScore4, query.getId());
			System.out.println();
		} else {
			critiqueScore1 = query.getCritiqueScore1() * scoreRatio;
			critiqueScore2 = query.getCritiqueScore2() * scoreRatio;
			critiqueScore3 = query.getCritiqueScore3() * scoreRatio;
			critiqueScore4 = query.getCritiqueScore4() * scoreRatio;
			grade          = critiqueScore1 + critiqueScore2 + critiqueScore3 + critiqueScore4 ;
			grade          = grade / 4 ;
			grade          = new Double(nfObj.format(grade))	;
			
			sql = "insert into cv_set_score (cv_set_id, grade, study_times, critiqueScore1, critiqueScore2, critiqueScore3, critiqueScore4) " +
			      " VALUES (?,?,?,?,?,?,?)" ;							
			getJdbcTemplate().update(sql, query.getId(), grade, 0 ,critiqueScore1, critiqueScore2, critiqueScore3, critiqueScore4);
		}						
		//YHQ 2017-02-14 1618  -----over
		
	}
		/**
	 * @author B.Sky
	 * @param PageList, String.
	 * @description get CVSet from search
	 */
	@Override
	public void queryCVSetPageListFromSearch(PageList<CVSet> pl, String search,Long proviceId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        //sql.append("from content_edition_ref t ,cv_set t2,cv_set_system_site t3,system_site t4, cv_set_expert t5 where t.ref_id=t2.id ");
        //sql.append("and t2.id=t3.cv_set_id and t3.system_site_id=t4.id and t.check_state = 1 and t2.id=t5.cv_set_id and t2.name like ?");
        //YHQ modify 2017-2-13 1109 top is source
        sql.append("from content_edition_ref t ,cv_set t2,cv_set_system_site t3,system_site t4 where t.ref_id=t2.id ");
        sql.append("and t2.id=t3.cv_set_id and t3.system_site_id=t4.id and t.check_state = 1 and t2.name like ? ");
        if(proviceId !=null){
        	sql.append("and t2.id in (select cv_set_id from CV_REGION cr where cr.region_id = "+proviceId+") ");	
        }
        List<Object> params = new ArrayList<Object>();
        params.add("%" + search + "%");

        //String get = "select t2.*, t5.* ";
        //String get_count = "select count(t2.id) ";        
        //YHQ modify 2017-2-13 1109 top is source
        String get = "select distinct(t2.id),t2.*,t.ordernum ";
        String get_count = "select count(distinct(t2.id)) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" order by t.ordernum ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        
        List<CVSet> list;
        
        list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        
        if(list.size()==0){
       // 如果列表为空则重新按条件查询 	
        String sql2 ="select * from cv_set WHERE cv_set.`NAME` LIKE '%"+ search +"%' and status= 5";	
         list = getJdbcTemplate().query(
                 PageUtil.getPageSql(sql2, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
        }
        
        
        if (list != null) {
        	for (CVSet cvSet:list) {
        		String getManagerSQL = "select DISTINCT t2.* from cv_set_expert t1, expert_info t2 where t1.expert_id=t2.id and t1.cv_set_id="+cvSet.getId();
        		List<ExpertInfo> expertInfoList = getJdbcTemplate().query(getManagerSQL, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
        		if(expertInfoList.size()>0){
        			cvSet.setManagerList(expertInfoList);
        		}
        		
        	}
        }
        pl.setList(list);
	}

	/**
	 * @author Han
	 * @time	2017-02-07
	 * 取得项目评论列表
	 */
	@Override
	public void getCVSetScoreLogList(CVSetScoreLog log,
			Pager<CVSetScoreLog> pl) {
		
		if (log == null || log.getCvSetId() == null) {
			return;
		}
		
		String sql ="from cv_set_score_log l left join system_user u on l.SYSTEM_USER_ID=u.ID where l.CV_SET_ID=? ORDER BY l.SCORE_DATE desc";
		String get_count = "select count(1) " + sql;
		String get_list = "select l.*, u.REAL_NAME, u.user_avatar, u.sex " + sql;
		
		int fullListSize = getJdbcTemplate().queryForInt(get_count, log.getCvSetId());
		pl.setCount(fullListSize);

		List<CVSetScoreLog> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get_list, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(CVSetScoreLog.class), log.getCvSetId());
		
		pl.setList(list);
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-12
	 * 人是否已经评价了项目
	 */
	@Override
	public boolean getCVSetScoreLogIsExist(CVSetScoreLog log){
		boolean resVal = true ;
		String sql = "select count(1) from cv_set_score_log where CV_SET_ID=? and SYSTEM_USER_ID=?" ;
		int fullListSize = getJdbcTemplate().queryForInt(sql, log.getCvSetId(), log.getSystemUserId());
		if (fullListSize <= 0) resVal = false ;
		return resVal ;
	}
	
	/***
	 *  程宏业
	 *  2017-2-22
	 *  根据时间查询出用户在某年度时间内学过的所有项目
	 */
	
    public List<CVSet> getCVSetByTime(CVSet query) {

        StringBuilder sql = new StringBuilder();
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
        
        sql.append(" select " + cvSetColumnSQL + ", t7.*,COUNT(DISTINCT t.id) from cv_set t LEFT JOIN cv_set_authorization csa ON csa.CV_SET_ID = t.ID "
        		+ "left join cv_set_score t7 on t.id=t7.cv_set_id,cv_set_authorization_system_site t5,system_site t6,log_study_cv_set t8 ");
        sql.append(" where csa.ID = t5.AUTHORIZATION_ID and t5.system_site_id=t6.id and t8.cv_set_id= t.id and t8.system_user_id="+query.getId()+" and t6.domain_name=? ");
        
        if(query.getUserProvinceCode()!=null){
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + query.getUserProvinceCode() + ") ");
        }else{
        	sql.append(" and t.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        if(StringUtils.isEmpty(query.getName())){
        	  sql.append(" and t.status=? and t.start_date <= ? GROUP BY t.id");
        }else{
        	 sql.append(" and t.status=? and t.start_date >= ? and t.end_date <= ?  GROUP BY t.id ");
        }
       
        List<Object> params = new ArrayList<Object>();
        params.add(query.getServerName());
        params.add(query.getStatus());
        params.add(DateUtil.format(query.getStart_date(), DateUtil.FORMAT_SHORT));
        if(StringUtils.isNotEmpty(query.getName())){
        params.add(DateUtil.format(query.getEnd_date(), DateUtil.FORMAT_SHORT));
        }
        List<CVSet> list = getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class),
                params.toArray());


        return list;
    }
	/**
	 * 项目预览
	 * */
    public CVSet viewCVSetById(CVSet query) {

        StringBuilder sql = new StringBuilder();
        //sql.append(" select t.*, t7.* from cv_set t left join cv_set_score t7 on t.id=t7.cv_set_id left join cv_set_system_site t5 on t.id=t5.cv_set_id ");
        //sql.append(" left join system_site t6 on t5.system_site_id=t6.id  where t.id=?");
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,GROUP_CONCAT(CASE WHEN t2.CV_SET_SERIAL_NUMBER IS NULL THEN t.CODE ELSE t2.CV_SET_SERIAL_NUMBER END) AS SERIAL_NUMBER_STR,");
		cvSetColumnSQL.append("GROUP_CONCAT(CONCAT(CASE WHEN t2.CV_SET_LEVEL = 1 THEN '国家I类' WHEN t2.CV_SET_LEVEL = 2 THEN '省级I类' ");
		cvSetColumnSQL.append("WHEN t2.CV_SET_LEVEL = 3 THEN '市级I类' WHEN t2.CV_SET_LEVEL = 4 THEN '省级II类' WHEN t2.CV_SET_LEVEL = 5 THEN '市级II类' ELSE '无学分' END,");
		cvSetColumnSQL.append("CASE WHEN t2.CV_SET_SCORE IS NULL THEN 0 ELSE cast(t2.CV_SET_SCORE as decimal(9,2)) END,'分')) AS LEVEL_SCORE_STR,");
		cvSetColumnSQL.append("GROUP_CONCAT(CONCAT(t2.CV_SET_START_DATE,'至',t2.CV_SET_END_DATE)) AS START_END_DATE_STR,");
		cvSetColumnSQL.append("t2.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,t2.CV_SET_LEVEL AS LEVEL,t2.CV_SET_SCORE AS SCORE,t2.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("t2.CV_SET_COST_TYPE AS cost_type,t2.CV_SET_COST AS COST,t2.CV_SET_START_DATE AS START_DATE,t2.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("t2.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
		
        
        sql.append("select ").append(cvSetColumnSQL).append(",t1.* from ");
        sql.append("cv_set t LEFT JOIN cv_set_score t1 ON t.id = t1.cv_set_id LEFT JOIN cv_set_authorization t2 ON t2.CV_SET_ID = t.ID WHERE t.id = ? GROUP BY t.ID;");
        
        List<Object> params = new ArrayList<Object>();
        params.add(query.getId());

        List<CVSet> list = getJdbcTemplate().query(sql.toString(),
                ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class),
                params.toArray());

        if(!list.isEmpty()){
			CVSet cvSet= list.get(0);
			if (cvSet.getId() != null && cvSet.getId() > 0) {
				String bvoString = "select cv_set_id as id, book_name as name, book_url as url  from cv_set_refereebook where cv_set_id = " + cvSet.getId() ;
				List<BaseProjectRefModel> refereeBookList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseProjectRefModel.class));
				cvSet.setRefereeBookList(refereeBookList);

				bvoString = "select cv_set_id as id, knowledgebase_name as name, knowledgebase_url as url  from cv_set_knowledgebase where cv_set_id = " + cvSet.getId() ;
				List<BaseProjectRefModel> knowledgeBaseList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseProjectRefModel.class));
				cvSet.setKnowledgeBaseList(knowledgeBaseList);

				bvoString = "select cv_set_id as id, reference_name as name, reference_url as url  from cv_set_referencelately where cv_set_id = " + cvSet.getId() ;
				List<BaseProjectRefModel> referenceLatelyList = getJdbcTemplate().query(bvoString, ParameterizedBeanPropertyRowMapper.newInstance(BaseProjectRefModel.class));
				cvSet.setReferencelatelyList(referenceLatelyList);
			}
            return cvSet;
        }

        return null;
    }
	
	
    
    
    /***
     * 
     * 通过项目id查询项目的价格
     * 方法名：toCostByid
     * 创建人：程宏业
     * 时间：2017-4-18-上午10:37:16 
     * @param id
     * @return List<CVSet>
     * @exception 
     * @since  1.0.0
     */
    
  

	@Override
	public List<CVSet> toCostById(Long id) {
		// TODO Auto-generated method stub
		String sql = "select * from cv_set where id = "+id+"";
    	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
	}
    
    @Override
    public void queryHaiWaiShiYeCVSetPageList(Pager<CVSet> pl, CVSet queryCVSet) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
       
        /**带有站点过滤的sql**/
//        sql.append(" from cv_set t2,cv_set_system_site t3,system_site t4 where");
////        sql.append(" t2.cv_set_type = 0 and");
//        sql.append(" t2.id=t3.cv_set_id and t3.system_site_id=t4.id");
//
//        sql.append(" and t4.domain_name=?");
//        params.add(queryCVSet.getServerName());        
        /**带有站点过滤的sql**/

        sql.append(" from cv_set t2 left join cv_set_authorization csa on csa.cv_set_id = t2.id where t2.forma =1");
        
        
        
        sql.append(" and t2.status=?");
        params.add(queryCVSet.getStatus());
        
        
        //根据学科查询符合学科的项目
        if(queryCVSet.getPropId()!=null && !queryCVSet.getPropId().equals("") && (queryCVSet.getPropId()== 510005 || queryCVSet.getPropId().equals(510005))){
        	sql.append(" and t2.id in (select distinct(cvim.cv_set_id) from CV_SET_USER_IMAGE cvim where USERIMAGE_ID in (select ip.userimage_id from qm_user_image_prop ip where prop_id in (select id from exam_prop_val where ");
        	List<ExamProp> list = queryCVSet.getXueKeList();
        	if(list.size()==1){
        		sql.append("name='"+ list.get(0).getName()+"'");
        	}else if(list.size()>1){
        		sql.append("name='"+ list.get(0).getName()+"'");
        		for (int i = 1; i < list.size(); i++) {
        			sql.append(" or name='"+ list.get(i).getName()+"'");
				}
        	}
        	sql.append(")))");
        }else{
        	if(StringUtils.isNotEmpty(queryCVSet.getPropName())){
            	sql.append(" and t2.id in (select distinct(cvim.cv_set_id) from CV_SET_USER_IMAGE cvim where USERIMAGE_ID in (select ip.userimage_id from qm_user_image_prop ip where prop_id in (select id from exam_prop_val where name =?)))");
            	params.add(queryCVSet.getPropName());
            }
        }
        
      //类型指南解读|公需科目
        if(queryCVSet.getSign() != null && !"".equals(queryCVSet.getSign())){
            sql.append(" and csa.CV_SET_SIGN like ? ");
            params.add("%" + queryCVSet.getSign() + "%");
        }else{
        	sql.append(" and csa.CV_SET_SIGN like '%海外视野%'");
        }
        
      //级别
        if (queryCVSet.getLevel() != null && queryCVSet.getLevel() > 0) {
            sql.append(" and csa.CV_SET_LEVEL = ?");
            params.add(queryCVSet.getLevel());
        }
        
        sql.append(" and csa.CV_SET_START_DATE <= CURDATE() and csa.CV_SET_END_DATE >= CURDATE() "); //YHQ，2017-03-11，添加时间范围
        
        if(queryCVSet.getUserProvinceCode()!=null){
        	sql.append(" and t2.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID WHERE csar1.PROP_VAL_ID = " + queryCVSet.getUserProvinceCode() + ") ");
        }else{
        	sql.append(" and t2.id in (SELECT DISTINCT csa1.CV_SET_ID FROM cv_set_authorization_region csar1 LEFT JOIN cv_set_authorization csa1 ON csa1.ID = csar1.AUTHORIZATION_ID GROUP BY csar1.AUTHORIZATION_ID "
        			+ "HAVING count(csar1.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv1 WHERE epv1.type = 20)) ");
        }
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t2.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t2.NAME,t2.FORMA,t2.CODE,t2.FILE_PATH,t2.INTRODUCTION,t2.ANNOUNCEMENT,t2.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t2.KNOWLEDGE_BASE,t2.REFERENCE,t2.REFERENCE_LATELY,t2.OPINION,t2.REPORT,t2.OPINION_TYPE,t2.STATUS,t2.TYPE,t2.MAKER,t2.DELI_MAN,");
		cvSetColumnSQL.append("t2.CREATE_DATE,t2.DELI_DATE,t2.PROVINCEID,t2.CITYID,t2.RELATION_QUIZ,t2.cv_set_type");
        
        String get = "select " + cvSetColumnSQL + " ";
		//String get = "select t2.* ";
        String get_count = "select count(DISTINCT t2.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        //pl.setFullListSize(fullListSize);
        pl.setCount(fullListSize);;

        sql.append(" GROUP BY t2.id order by t2.START_DATE ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CVSet> list = getJdbcTemplate().query(PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        pl.setList(list);
    }

	@Override
	public List<BaseProjectRefModel> getRefereeBookByCvId(Long id) {
		// TODO Auto-generated method stub
		String sql = "select cv_set_id as id,book_name as name,book_url as url from cv_set_refereebook where cv_set_id = "+id+"";
    	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BaseProjectRefModel.class));
	}
    
	@Override
	public List<BaseProjectRefModel> getKnowledgebaseByCvId(Long id) {
		// TODO Auto-generated method stub
		String sql = "select cv_set_id as id,knowledgebase_name as name,knowledgebase_url as url from cv_set_knowledgebase where cv_set_id = "+id+"";
    	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BaseProjectRefModel.class));
	}
	
	@Override
	public List<BaseProjectRefModel> getReferencelatelyByCvId(Long id) {
		// TODO Auto-generated method stub
		String sql = "select cv_set_id as id,reference_name as name,reference_url as url from cv_set_referencelately where cv_set_id = "+id+"";
    	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BaseProjectRefModel.class));
	}
    
	/**
	 *根据名称和省份id查询项目 
	 */
	@Override
	public PageList<CVSet> queryCVSetPageListByNameAndProvice(PageList<CVSet> pl, CVSet cvSet,Long proviceId) {
		//参数数组   
		List<Object> params = new ArrayList<Object>();
		//拼装sql语句
		StringBuilder sql = new StringBuilder();
		sql.append(" from cv_set t left join cv_set_authorization csa on csa.CV_SET_ID = t.id left join cv_set_authorization_region csar on csar.AUTHORIZATION_ID = csa.id "
				+ "where 1=1 AND csa.CV_SET_START_DATE <= CURDATE() AND csa.CV_SET_END_DATE >= CURDATE() ");
		if(cvSet.getName()!=null){
	        sql.append(" and t.name like ? ");
	        params.add("%" + cvSet.getName() + "%");
		}
        //状态
        if(cvSet.getStatus() !=null){
        	sql.append("and t.status =? ");	
        	params.add(cvSet.getStatus());
        }
        //授课形式
        if(cvSet.getForma() !=null){
        	sql.append("and t.forma =? ");	
        	params.add(cvSet.getForma());
        }
        
        //授课类型
        if(cvSet.getCv_set_type() !=null){
        	sql.append("and t.cv_set_type =? ");	
        	params.add(cvSet.getCv_set_type());
        }
        //类型指南解读|公需科目
        if(cvSet.getSign() != null && !"".equals(cvSet.getSign())){
            sql.append(" and csa.CV_SET_SIGN like ? ");
            params.add("%" + cvSet.getSign() + "%");
        }
        //级别
        if (cvSet.getLevel() != null && cvSet.getLevel() > 0) {
            sql.append(" and csa.CV_SET_LEVEL = ? ");
            params.add(cvSet.getLevel());
        }
        //省份
        if(proviceId !=null){
        	sql.append(" and t.id in (select DISTINCT(ccc.CV_SET_ID) from cv_set_authorization_region csar left join cv_set_authorization ccc on ccc.id = csar.AUTHORIZATION_ID "
        			+ "where csar.PROP_VAL_ID = ?) ");	
        	params.add(proviceId);
        }else{
        	//没有传省份时，查询授权全国省份的项目
        	//查询省份总数
        	//select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20
        	//授权全国的项目id
        	//select cv_set_id from CV_REGION t group by cv_set_id having count(region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)
        	sql.append(" and t.id in (select ccc.CV_SET_ID from cv_set_authorization_region csar left join cv_set_authorization ccc on ccc.id = csar.AUTHORIZATION_ID group by ccc.cv_set_id,ccc.id "
        			+ "having count(1)=(select count(1) from exam_prop_val epv where epv.type=20)) ");
        }
        //授权域名
        if(!StringUtils.isEmpty(cvSet.getServerName())){
        	sql.append(" and t.id in (select DISTINCT(CV_SET_ID) from cv_set_authorization csa left join cv_set_authorization_system_site csass on csass.AUTHORIZATION_ID = csa.ID "
        			+ "left join system_site ss on csass.SYSTEM_SITE_ID=ss.id where ss.DOMAIN_NAME=?) ");
        	params.add(cvSet.getServerName());
        }
        //根据学科查询符合学科的项目
        if(cvSet.getPropId()!=null && !cvSet.getPropId().equals("") && (cvSet.getPropId()== 510005 || cvSet.getPropId().equals(510005))){
        	sql.append(" and t.id in (select distinct(cvim.cv_set_id) from CV_SET_USER_IMAGE cvim where USERIMAGE_ID in (select ip.userimage_id from qm_user_image_prop ip where prop_id in (select id from exam_prop_val where ");
        	List<ExamProp> list = cvSet.getXueKeList();
        	if(list.size()==1){
        		sql.append("name='"+ list.get(0).getName()+"'");
        	}else if(list.size()>1){
        		sql.append("name='"+ list.get(0).getName()+"'");
        		for (int i = 1; i < list.size(); i++) {
        			sql.append(" or name='"+ list.get(i).getName()+"'");
				}
        	}
        	sql.append(")))");
        }else{
        	if(StringUtils.isNotEmpty(cvSet.getPropName())){
            	sql.append(" and t.id in (select distinct(cvim.cv_set_id) from CV_SET_USER_IMAGE cvim where USERIMAGE_ID in (select ip.userimage_id from qm_user_image_prop ip where prop_id in (select id from exam_prop_val where name =?)))");
            	params.add(cvSet.getPropName());
            }
        }
        
        StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
		
		String get = "select " + cvSetColumnSQL + " ";
        
        //String get = "select t.* ";
        String get_count = "select count(DISTINCT t.id) ";
        try{
        //获取总量
        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);
        if (cvSet.getCost_sort() !=null) {//按价格查询
        	 sql.append(" group by t.id order by t.cost " +cvSet.getCost_sort());
		}else if (cvSet.getScore_sort() != null) {//按学分查询
			sql.append(" group by t.id order by t.score " +cvSet.getScore_sort());
		}else {
			 sql.append(" group by t.id order by t.create_date desc");
		}
       
        get += sql.toString();
        }catch(Exception e){
        	
        	e.printStackTrace();
        	Log.info(e);
        }
        List<CVSet> list;
        
        list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), params.toArray());
        
        pl.setList(list);
        return pl;
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.dao.local.CVSetManageDAO#queryCVSetListByZN()
	 */
	@Override
	public List<CVSet> queryCVSetListByZN(){
	
		StringBuffer cvSetColumnSQL = new StringBuffer();
		cvSetColumnSQL.append("t.ID,csa.CV_SET_SERIAL_NUMBER AS SERIAL_NUMBER,csa.CV_SET_LEVEL AS LEVEL,csa.CV_SET_SCORE AS SCORE,csa.CV_SET_SIGN AS SIGN,");
		cvSetColumnSQL.append("csa.CV_SET_COST_TYPE AS cost_type,csa.CV_SET_COST AS COST,csa.CV_SET_START_DATE AS START_DATE,csa.CV_SET_END_DATE AS END_DATE,");
		cvSetColumnSQL.append("csa.CV_SET_BREAK_DAYS AS BREAK_DAYS,t.NAME,t.FORMA,t.CODE,t.FILE_PATH,t.INTRODUCTION,t.ANNOUNCEMENT,t.KNOWLEDGE_NEEDED,");
		cvSetColumnSQL.append("t.KNOWLEDGE_BASE,t.REFERENCE,t.REFERENCE_LATELY,t.OPINION,t.REPORT,t.OPINION_TYPE,t.STATUS,t.TYPE,t.MAKER,t.DELI_MAN,");
		cvSetColumnSQL.append("t.CREATE_DATE,t.DELI_DATE,t.PROVINCEID,t.CITYID,t.RELATION_QUIZ,t.cv_set_type");
		
		String sql ="select " + cvSetColumnSQL + " from cv_set t left join cv_set_authorization csa on csa.cv_set_id = t.id "
				+ "where 1=1 AND csa.CV_SET_START_DATE <= CURDATE() AND csa.CV_SET_END_DATE >= CURDATE() and csa.CV_SET_SIGN like ? group by t.ID ";
		List<Object> params = new ArrayList<Object>();
		params.add("%指南解读%");
		
		 List<CVSet> list = getJdbcTemplate().query(sql, params.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		 
		 return list;
		
	}
/**
 * 培训期数
 */
	@Override
	public List<CVSetScheduleFaceTeach> getFaceTeach(Long cvSetId) {
		String sql ="select *  from  cv_set_schedule_faceteach  where cv_set_id ="+cvSetId+"";

     return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetScheduleFaceTeach.class));
	}
    /**
     * 面授详情
     */
	@Override
	public CVSetScheduleFaceTeach getFace(Integer faceId) {
		String sql = "select * from cv_set_schedule_faceteach where id = "+faceId+"";
		return getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetScheduleFaceTeach.class));
	}
   /**
    * 保存面授报名
    */
	@Override
	public void saveFaceTeach(SystemUserFaceteach faceteach) {
		String sql = "INSERT INTO system_user_face_entry (user_id,fid,entry_time)VALUES(?,?,NOW())";
		getJdbcTemplate().update(sql,faceteach.getUser_id(),faceteach.getFid());
	}
    /**
     * 查找 我的面授项目
     */
	@Override
	public List<CVSetScheduleFaceTeach> seachFaceTeach(Long cv_set_id, Long userId) {
		String sql = "SELECT *FROM system_user_face_entry s LEFT JOIN cv_set_schedule_faceteach c ON s.fid = c.id WHERE s.user_id ="+ userId+" AND c.cv_set_id ="+cv_set_id;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetScheduleFaceTeach.class));
}
   /**
    * sunny
    * 查找直播列表
    */
	@Override
	public List<CV> getCvSending(Long cv_set_id, Integer cv_type) {
	     String sql = "select t.* from cv t join cv_schedule t2 on t.id=t2.cv_id join cv_live cl on cl.cv_id = t.id join cv_set_schedule t3 on t2.schedule_id=t3.cv_schedule_id where t3.cv_set_id = ? and t.cv_type = 2  order by sequenceNum asc "; 

	        return getJdbcTemplate().query(sql.toString(),
	                ParameterizedBeanPropertyRowMapper.newInstance(CV.class),
	                cv_set_id);
	}

@Override
public List<CVSetScheduleFaceTeach> getMyFaceTeach(long cv_set_id, Long fid) {
	String sql = "SELECT *FROM system_user_face_entry s LEFT JOIN cv_set_schedule_faceteach c ON s.fid = c.id WHERE s.fid ="+ fid+" AND c.cv_set_id ="+cv_set_id;
	return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetScheduleFaceTeach.class));
}

@Override
public CVSet findCVSetById(Long cvSetId) {
	String sql = "select * from cv_set where id = "+cvSetId;
	return getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
}

	@Override
	public int findCvSetId(String setId) {
		String sql = "";
		sql="SELECT id from cv_set where code = ?";
		return getJdbcTemplate().queryForInt(sql,setId);
		
	}

	/*@Override
	public String  queryNameCode(Long cvId){
		String sql = "SELECT t.name FROM cv_set t LEFT JOIN cv_set_schedule t1 ON t1.CV_SET_ID = t.ID LEFT JOIN cv_schedule t2 ON t2.SCHEDULE_ID = t1.CV_SCHEDULE_ID WHERE t2.CV_ID ="+cvId;
		return  getJdbcTemplate().query(sql);
	}*/
	@Override
	public String queryNameCode(Long cvId) {
		String sql = "SELECT t.name FROM cv_set t LEFT JOIN cv_set_schedule t1 ON t1.CV_SET_ID = t.ID LEFT JOIN cv_schedule t2 ON t2.SCHEDULE_ID = t1.CV_SCHEDULE_ID WHERE t2.CV_ID =?";
		
		return (String)getJdbcTemplate().queryForObject(sql, new Object[]{cvId}, java.lang.String.class);
	}
	
	@Override
	public String queryCode(Long cvId) {
		String sql = "SELECT t.code FROM cv_set t LEFT JOIN cv_set_schedule t1 ON t1.CV_SET_ID = t.ID LEFT JOIN cv_schedule t2 ON t2.SCHEDULE_ID = t1.CV_SCHEDULE_ID WHERE t2.CV_ID =?";
		
		return (String)getJdbcTemplate().queryForObject(sql, new Object[]{cvId}, java.lang.String.class);
	}

	/**
	 * @author 王印涛
	 * 2018年1月2日 下午4:29:47
	 * @Description 获取新评分制度的综合评分
	 */
	@Override
	public String getGrade(Long cvSetId) {
        String sql = "SELECT (CRITIQUESCORE3 + CRITIQUESCORE4)/2 FROM cv_set_score c LEFT JOIN cv_set  s on c.CV_SET_ID = s.ID where s.ID = ?";
		return (String)getJdbcTemplate().queryForObject(sql, new Object[]{cvSetId}, java.lang.String.class);
	}
	
}
