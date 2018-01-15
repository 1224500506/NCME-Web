package com.hys.exam.dao.local.jdbc;


import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;


import com.hys.exam.common.util.DateUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.dao.local.CVSetOrgDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.dao.local.UserImageManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSchedule;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;


public class CVSetOrgJDBCDAO extends BaseDao
		implements CVSetOrgDAO {
	private CVManageDAO localCVManageDAO;
	private UserImageManageDAO localUserImageManageDAO;
	private ExpertManageDAO localExpertManageDAO;
	
	@Override
	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct cv.* from cv_set_org as org left join cv_set as cv on org.cv_id = cv.id JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on cv.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID where cv.id>0 and cv.status = 5");
		if(querySet.getOrganizationList() != null && querySet.getOrganizationList().get(0) != null)
		{
			sql.append(" and org.org_id=").append(querySet.getOrganizationList().get(0).getId());
		}
		if(querySet.getForma() != null && querySet.getForma() > 0){
			sql.append(" and cv.forma ="+querySet.getForma());
		}
		if(querySet.getStatus() != null && querySet.getStatus() > 0){
			sql.append(" and cv.status ="+querySet.getStatus());
		}
		if(!StringUtils.checkNull(querySet.getName())){
			sql.append(" and cv.name like '%"+querySet.getName()+"%'");
		}
		if(!StringUtils.checkNull(querySet.getSign())){
			sql.append(" and cv.sign like '%"+querySet.getSign()+"%'");
		}
		if(querySet.getLevel() != null){
			sql.append(" and cv.level ="+querySet.getLevel());
		}
		if(querySet.getCourseList() != null && querySet.getCourseList().size() != 0){			
			sql.append(" and cv.ID=c.CV_SET_ID and u.PROP_ID IN (") ;			
					for(int i=0; i<querySet.getCourseList().size(); i++){
						if(i == (querySet.getCourseList().size()-1)){
							sql.append(querySet.getCourseList().get(i).getId());
						}else{
							sql.append(querySet.getCourseList().get(i).getId()).append(",");
						}
					}
					sql.append(")");			
		}
		if(querySet.getStart_date() != null){
			sql.append(" and cv.start_date <= '" + DateUtil.format(querySet.getStart_date(), "yyyy-MM-dd") + "'");
			sql.append(" and cv.end_date >= '" + DateUtil.format(querySet.getStart_date(), "yyyy-MM-dd") + "'");
		}
		if(querySet.getProvinceId() != null && querySet.getProvinceId() > 0 ) {
			sql.append(" and t.provinceid =" + querySet.getProvinceId());
		}
		if(querySet.getCityId() != null && querySet.getCityId() > 0) {
			sql.append(" and t.cityid =" + querySet.getCityId());
		}
		if (querySet.getSiteList() != null && !querySet.getSiteList().isEmpty())
		{
			sql.append(" and exists (select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = cv.id and s.DOMAIN_NAME like  '%" + querySet.getSiteList().get(0).getDomainName() + "%')");
		}
		if(querySet.getCost_sort() != null && !querySet.getCost_sort().equals("")){
			sql.append(" order by cv.cost " +querySet.getCost_sort());
		}
		if(querySet.getScore_sort() != null && !querySet.getScore_sort().equals("")){
			sql.append(" order by cv.score " +querySet.getScore_sort());
		}
		if(querySet.getRecent_create() != null && !querySet.getRecent_create().equals("")){
			sql.append(" order by cv.create_date desc");
		}
		//sql.append(" order by create_date desc");
		
		List<CVSet> result_list = new ArrayList<CVSet>();
		List<CVSet> list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		for(CVSet cvSet:list){
			
			String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV_set t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+cvSet.getId();
			
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));					
			cvSet.setCourseList(courseList);	
			
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			if (querySet.getSiteList() != null && !querySet.getSiteList().isEmpty()) {
				String site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId() + " and s.DOMAIN_NAME like  '%" + querySet.getSiteList().get(0).getDomainName() + "%'";
				
				List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
				if(querySet.getSiteList().get(0).getDomainName() != null && !querySet.getSiteList().get(0).getDomainName().equals(""))
				{
					if (systemSite.size() == 0) continue;				
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
			
			
			
			String sql_schedule = "select CV_ID as ID, START_DATE, END_DATE from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId() + ")";			
			List<CVSchedule> _scheduleList = getJdbcTemplate().query(sql_schedule, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
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
			/*for(CVSchedule schedule:scheduleListID){
				schedule.getSchedule_id();
				scheduleList.get(0).setSchedule_id(schedule.getSchedule_id());
			}*/
			
			for(int i=0;i<scheduleListID.size();i++){
				scheduleList.get(i).setSchedule_id(scheduleListID.get(i).getSchedule_id());
			}
	
			cvSet.setCVScheduleList(scheduleList);
			
			result_list.add(cvSet);
		}
		return result_list;
	}
	
	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet, PageList pl) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct cv.* from cv_set_org as org left join cv_set as cv on org.cv_id = cv.id JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on cv.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID where cv.id>0 and status = 5");
		if(querySet.getOrganizationList() != null && querySet.getOrganizationList().get(0) != null)
		{
			sql.append(" and org.org_id=").append(querySet.getOrganizationList().get(0).getId());
		}
		if(querySet.getForma() != null && querySet.getForma() > 0){
			sql.append(" and cv.forma ="+querySet.getForma());
		}
		if(querySet.getStatus() != null && querySet.getStatus() > 0){
			sql.append(" and cv.status ="+querySet.getStatus());
		}
		if(!StringUtils.checkNull(querySet.getName())){
			sql.append(" and cv.name like '%"+querySet.getName()+"%'");
		}
		if(!StringUtils.checkNull(querySet.getSign())){
			sql.append(" and cv.sign like '%"+querySet.getSign()+"%'");
		}
		if(querySet.getLevel() != null){
			sql.append(" and cv.level ="+querySet.getLevel());
		}
		if(querySet.getProvinceId() != null && querySet.getProvinceId() > 0 ) {
			sql.append(" and cv.provinceid =" + querySet.getProvinceId());
		}
		if(querySet.getCityId() != null && querySet.getCityId() > 0) {
			sql.append(" and cv.cityid =" + querySet.getCityId());
		}
		if (querySet.getSiteList() != null && !querySet.getSiteList().isEmpty())
		{
			sql.append(" and exists (select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = cv.id and s.DOMAIN_NAME like  '%" + querySet.getSiteList().get(0).getDomainName() + "%')");
		}
		if(querySet.getCourseList() != null && querySet.getCourseList().size() != 0){			
			sql.append(" and cv.ID=c.CV_SET_ID and u.PROP_ID IN (") ;
				for(int i=0; i<querySet.getCourseList().size(); i++){
					if(i == (querySet.getCourseList().size()-1)){
						sql.append(querySet.getCourseList().get(i).getId());
					}else{
						sql.append(querySet.getCourseList().get(i).getId()).append(",");
					}
				}
			sql.append(")");			
		}
		if(querySet.getStart_date() != null){
			sql.append(" and cv.start_date <= '" + DateUtil.format(querySet.getStart_date(), "yyyy-MM-dd") + "'");
			sql.append(" and cv.end_date >= '" + DateUtil.format(querySet.getStart_date(), "yyyy-MM-dd") + "'");
		}
		if(querySet.getCost_sort() != null && !querySet.getCost_sort().equals("")){
			sql.append(" order by cv.cost " +querySet.getCost_sort());
		}
		if(querySet.getScore_sort() != null && !querySet.getScore_sort().equals("")){
			sql.append(" order by cv.score " +querySet.getScore_sort());
		}
		if(querySet.getRecent_create() != null && !querySet.getRecent_create().equals("")){
			sql.append(" order by cv.create_date desc");
		}
		
		List<CVSet> result_list = new ArrayList<CVSet>();
		List<CVSet> list = getList(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), new ArrayList<Object>(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
		for(CVSet cvSet:list){
			
			String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV_set t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+cvSet.getId();
			
			List<PropUnit> courseList = getJdbcTemplate().query(prop_sql,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));					
			cvSet.setCourseList(courseList);	
			
			
			String org_sql = "SELECT t1.* FROM peixun_org t1, cv_set_org t2 WHERE t1.id = t2.ORG_ID AND t2.CV_ID =" + cvSet.getId();
			List<PeixunOrg> peixunOrgList = getJdbcTemplate().query(org_sql, ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
			cvSet.setOrganizationList(peixunOrgList);
			
			if (querySet.getSiteList() != null && !querySet.getSiteList().isEmpty()) {
				String site_sql = "select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = " + cvSet.getId() + " and s.DOMAIN_NAME like  '%" + querySet.getSiteList().get(0).getDomainName() + "%'";
				
				List<SystemSite> systemSite = getJdbcTemplate().query(site_sql.toString(),  ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
				if(querySet.getSiteList().get(0).getDomainName() != null && !querySet.getSiteList().get(0).getDomainName().equals(""))
				{
					if (systemSite.size() == 0) continue;				
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
			
			
			
			String sql_schedule = "select CV_ID as ID, START_DATE, END_DATE from CV_SCHEDULE where SCHEDULE_ID in (select CV_SCHEDULE_ID from CV_SET_SCHEDULE where CV_SET_ID=" + cvSet.getId() + ")";			
			List<CVSchedule> _scheduleList = getJdbcTemplate().query(sql_schedule, ParameterizedBeanPropertyRowMapper.newInstance(CVSchedule.class));
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
			/*for(CVSchedule schedule:scheduleListID){
				schedule.getSchedule_id();
				scheduleList.get(0).setSchedule_id(schedule.getSchedule_id());
			}*/
			
			for(int i=0;i<scheduleListID.size();i++){
				scheduleList.get(i).setSchedule_id(scheduleListID.get(i).getSchedule_id());
			}
	
			cvSet.setCVScheduleList(scheduleList);
			
			result_list.add(cvSet);
		}
		return result_list;
	}
	
	
	@Override
	public int getCVSetOrgIdSize(CVSet querySet) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct cv.id) from cv_set_org as org left join cv_set as cv on org.cv_id = cv.id JOIN cv_set_user_image as c JOIN qm_user_image_prop as u on cv.ID=c.CV_SET_ID and c.USERIMAGE_ID=u.USERIMAGE_ID where cv.id>0 and status = 5");
		if(querySet.getOrganizationList() != null && querySet.getOrganizationList().get(0) != null)
		{
			sql.append(" and org.org_id=").append(querySet.getOrganizationList().get(0).getId());
		}
		if(querySet.getForma() != null && querySet.getForma() > 0){
			sql.append(" and cv.forma ="+querySet.getForma());
		}
		if(querySet.getStatus() != null && querySet.getStatus() > 0){
			sql.append(" and cv.status ="+querySet.getStatus());
		}
		if(!StringUtils.checkNull(querySet.getName())){
			sql.append(" and cv.name like '%"+querySet.getName()+"%'");
		}
		if(!StringUtils.checkNull(querySet.getSign())){
			sql.append(" and cv.sign like '%"+querySet.getSign()+"%'");
		}
		if(querySet.getLevel() != null){
			sql.append(" and cv.level ="+querySet.getLevel());
		}
		if(querySet.getProvinceId() != null && querySet.getProvinceId() > 0 ) {
			sql.append(" and cv.provinceid =" + querySet.getProvinceId());
		}
		if(querySet.getCityId() != null && querySet.getCityId() > 0) {
			sql.append(" and cv.cityid =" + querySet.getCityId());
		}
		if (querySet.getSiteList() != null && !querySet.getSiteList().isEmpty())
		{
			sql.append(" and exists (select s.* from cv_set_system_site sp left join system_site s on sp.SYSTEM_SITE_ID = s.ID where sp.CV_SET_ID = cv.id and s.DOMAIN_NAME like  '%" + querySet.getSiteList().get(0).getDomainName() + "%')");
		}
		if(querySet.getCourseList() != null && querySet.getCourseList().size() != 0){			
			sql.append(" and cv.ID=c.CV_SET_ID and u.PROP_ID IN (") ;
				for(int i=0; i<querySet.getCourseList().size(); i++){
					if(i == (querySet.getCourseList().size()-1)){
						sql.append(querySet.getCourseList().get(i).getId());
					}else{
						sql.append(querySet.getCourseList().get(i).getId()).append(",");
					}
				}
			sql.append(")");			
		}
		if(querySet.getStart_date() != null){
			sql.append(" and cv.start_date <= '" + DateUtil.format(querySet.getStart_date(), "yyyy-MM-dd") + "'");
			sql.append(" and cv.end_date >= '" + DateUtil.format(querySet.getStart_date(), "yyyy-MM-dd") + "'");
		}
		if(querySet.getProvinceId() != null && querySet.getProvinceId() > 0 ) {
			sql.append(" and cv.provinceid =" + querySet.getProvinceId());
		}
		if(querySet.getCityId() != null && querySet.getCityId() > 0) {
			sql.append(" and cv.cityid =" + querySet.getCityId());
		}
		if(querySet.getCost_sort() != null && !querySet.getCost_sort().equals("")){
			sql.append(" order by cv.cost " +querySet.getCost_sort());
		}
		if(querySet.getScore_sort() != null && !querySet.getScore_sort().equals("")){
			sql.append(" order by cv.score " +querySet.getScore_sort());
		}
		if(querySet.getRecent_create() != null && !querySet.getRecent_create().equals("")){
			sql.append(" order by cv.create_date desc");
		}		
		return getJdbcTemplate().queryForInt(sql.toString());
	}
	
	
	
	public PeixunOrg getCurrentOrgById(Long id)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from peixun_org where id = ").append(id);
		return getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PeixunOrg.class));
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

}
