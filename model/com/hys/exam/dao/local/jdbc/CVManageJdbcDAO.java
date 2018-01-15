package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.util.NcmeProperties;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;

public class CVManageJdbcDAO extends BaseDao implements CVManageDAO {

	@Override
	public List<CV> getCVList(CV queryCV) {
		List<CV> cvList = new ArrayList<CV>();
		
		if ( queryCV.getId() == null){
			StringBuilder sql1 = new StringBuilder();
			if ( queryCV.getCourseList() != null && queryCV.getCourseList().size() >0 ){
				
				sql1.append("select DISTINCT t.* from CV t,CV_REF_PROP_COURSE t1,cv_ref_teacher t2 where t.id>0");
				sql1.append(" and t1.cv_id=t.id and t1.prop_id in (");
				for(int i=0;i < queryCV.getCourseList().size() ; i++ ){
					if( i > 0 ) sql1.append(",");
						sql1.append(queryCV.getCourseList().get(i).getId());
				}
				sql1.append(")");
				cvList=getJdbcTemplate().query(sql1.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CV.class));	
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					
					String date_sql = "select create_date from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));
					
					
				}			
			} 
			if ( !StringUtil.checkNull(queryCV.getCreator())){
				String sql_creator= "select * from cv where creator like '%"+queryCV.getCreator()+"%'";
				cvList = getJdbcTemplate().query(sql_creator, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					
					String date_sql = "select create_date from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));
					
					
				}			
			} 
			if( ! StringUtil.checkNull(queryCV.getName())){
				List<PropUnit> cv_set_schedule_propList = new ArrayList<PropUnit>();
				List<PropUnit> cv_schedule_propList = new ArrayList<PropUnit>();
				String sql_select_cvSet = "select id from cv_set where cv_set.name like '%"+queryCV.getName()+"%'";
				List<PropUnit> propList = getJdbcTemplate().query(sql_select_cvSet, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				if(propList != null && propList.size() >0 ){
					String sql_select_cvSet_schedule = "select cv_schedule_id as id from cv_set_schedule where cv_set_id in (";
					for( int i=0;i<propList.size();i++){
						if(i>0) sql_select_cvSet_schedule += ",";
						sql_select_cvSet_schedule += propList.get(i).getId();
					}
					sql_select_cvSet_schedule += ")";
					cv_set_schedule_propList = getJdbcTemplate().query(sql_select_cvSet_schedule, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				}
				if(cv_set_schedule_propList != null && cv_set_schedule_propList.size()>0){
					String sql_select_cvSchedule = "select cv_id as id from cv_schedule where schedule_id in (";
					for(int i=0;i<cv_set_schedule_propList.size();i++){
						if(i>0) sql_select_cvSchedule +=",";
						sql_select_cvSchedule += cv_set_schedule_propList.get(i).getId();
					}
					sql_select_cvSchedule += ")";
					cv_schedule_propList = getJdbcTemplate().query(sql_select_cvSchedule,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				}
				if(cv_schedule_propList != null && cv_schedule_propList.size() >0 ){
					String sql_select_cv = "select * from cv where cv.id in (";
					for(int i=0;i<cv_schedule_propList.size();i++){
						if(i>0) sql_select_cv += ",";
						sql_select_cv += cv_schedule_propList.get(i).getId();
					}
					sql_select_cv +=")";
					cvList = getJdbcTemplate().query(sql_select_cv, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
				}
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					
					String date_sql = "select create_date from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));
					
					
				}			
				
			} else {
				String sql="select * from cv where id>0";
				cvList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
				for ( CV simpleCV:cvList ) {
					String prop_sql = "select t1.prop_id as id, t3.name from cv_ref_prop_course t1, EXAM_PROP_VAL t3, CV t2 where t1.prop_id=t3.id and t2.id=t1.cv_id and t2.id="+simpleCV.getId();
					List<PropUnit> courseList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setCourseList(courseList);
					String teacher_sql = "select t1.expert_id as id,t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+com.hys.exam.constants.Constants.TeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> teacherList = getJdbcTemplate().query(teacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setTeacherList(teacherList);
					
					String otherTeacher_sql = "select t1.expert_id as id, t2.name from cv_ref_teacher t1,expert_info t2, cv t3 where t1.expert_id=t2.id and t1.cv_id=t3.id and t1.type="+Constants.OtherTeacherType+" and t3.id ="+simpleCV.getId();
					List<ExpertInfo> otherTeacherList = getJdbcTemplate().query(otherTeacher_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
					simpleCV.setOtherTeacherList(otherTeacherList);
					
					String sql_select_usingItems = "select distinct t1.id,t1.name from cv_set t1,cv_set_schedule t2,cv_schedule t3,cv t4 where t3.cv_id="+simpleCV.getId()+" and t2.cv_schedule_id = t3.schedule_id and t2.cv_set_id= t1.id";
					List<PropUnit> usingItems = getJdbcTemplate().query(sql_select_usingItems, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					simpleCV.setUsingItem(usingItems);
					
					String date_sql = "select create_date from CV where id="+simpleCV.getId();
					List<Date> date = getJdbcTemplate().query(date_sql, ParameterizedBeanPropertyRowMapper.newInstance(Date.class));
					
					
					simpleCV.setCreate_date(date.get(0));
					
					
				}			
			} 
		
		} else if (queryCV.getId() != null) {
			
			Long ID = queryCV.getId();
			String sql="select * from cv where id=" + queryCV.getId();
			if(queryCV.getBrand() != null && !queryCV.getBrand().equals(0))
			{
				sql += " and FIND_IN_SET("+ queryCV.getBrand()+",brand)" ;
			}
			cvList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
			for(CV simpleCV:cvList){

				List<ExpertInfo> teacherList = new ArrayList<ExpertInfo>();
				String sql_teacher = "select t1.expert_id as id, t2.name from cv t, cv_ref_teacher t1, expert_info t2 where t.id=t1.cv_id and t.id="+ID +" and t1.expert_id=t2.id and t1.type="+Constants.TeacherType;
				teacherList = getJdbcTemplate().query(sql_teacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
				simpleCV.setTeacherList(teacherList);
				
				List<ExpertInfo> other_TeacherList = new ArrayList<ExpertInfo>();
				String sql_otherTeacherList = "select t1.expert_id as id, t2.name from cv t, cv_ref_teacher t1, expert_info t2 where t.id=t1.cv_id and t.id="+ID +" and t1.expert_id=t2.id and t1.type="+Constants.OtherTeacherType;
				other_TeacherList = getJdbcTemplate().query(sql_otherTeacherList, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
				simpleCV.setOtherTeacherList(other_TeacherList);
				
				List<PropUnit> propList = new ArrayList<PropUnit>();
				String sql_propList ="select t1.prop_id as id,t2.name from CV t, cv_ref_prop_course t1,exam_prop_val t2 where t.id=t1.cv_id and t1.prop_id=t2.id and t.id="+ ID; 
				propList = getJdbcTemplate().query(sql_propList, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				simpleCV.setCourseList(propList);
				
				List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
				String sql_cvUnitList = "select t2.* from cv t,cv_ref_unit t1,cv_unit t2 where t.id=t1.cv_id and t1.unit_id=t2.id and t.id="+ID;
				cvUnitList = getJdbcTemplate().query(sql_cvUnitList, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
				simpleCV.setUnitList(cvUnitList);
			}
			
		} 
			
		return cvList;
	}

	@Override
	public boolean addCV(CV cv) {
		
		Long Id = cv.getId();
		if(Id == null) Id = this.getNextId("CV");
		cv.setId(Id);
		
		String courseName = cv.getName();
		String serial = cv.getSerial_number();
		List<PropUnit> prop_course = cv.getCourseList();
		String brand = cv.getBrand();
		List<ExpertInfo> teacher = cv.getTeacherList();
		List<ExpertInfo> other_Teacher = cv.getOtherTeacherList();
		String introduction = cv.getIntroduction();
		String filePath=cv.getFile_path();
		String announcement = cv.getAnnouncement();
		String creator = cv.getCreator();
		List<CVUnit> cvUnitList = cv.getUnitList();
		
		
		String sql = "INSERT INTO CV (ID,NAME,SERIAL_NUMBER,BRAND,INTRODUCTION,ANNOUNCEMENT,FILE_PATH,CREATE_DATE) VALUES(?,?,?,?,?,?,?,SYSDATE())";
		getSimpleJdbcTemplate().update(sql, Id,courseName,serial,brand,introduction,announcement,filePath);
		String sql_creator = "UPDATE CV SET CREATOR='"+creator.toString() +"' where id="+Id;
		getSimpleJdbcTemplate().update(sql_creator);
//Insert Teacher
		
		if(teacher != null && teacher.size()>0){
			
			for(int i=0;i<teacher.size();i++){
				String sql_teacher = "INSERT INTO CV_REF_TEACHER (CV_ID,EXPERT_ID,TYPE) VALUES(?,?,?)";
				getSimpleJdbcTemplate().update(sql_teacher, Id,teacher.get(i).getId(),Constants.TeacherType);
			}
		}
		
		if(other_Teacher != null && other_Teacher.size()>0){
			for(int i=0;i<other_Teacher.size();i++){
				String sql_other_teacher = "INSERT INTO CV_REF_TEACHER (CV_ID,EXPERT_ID,TYPE) VALUES (?,?,?)";
				getSimpleJdbcTemplate().update(sql_other_teacher, Id,other_Teacher.get(i).getId(),Constants.OtherTeacherType);
			}
		}
		
//Insert Course	
		if(prop_course != null && prop_course.size()>0){
			for(int i=0; i<prop_course.size();i++){
				String sql_prop_course = "INSERT INTO CV_REF_PROP_COURSE (CV_ID,PROP_ID) VALUES (?,?)";
				getSimpleJdbcTemplate().update(sql_prop_course, Id,prop_course.get(i).getId());
			}
		}
		
//Insert CVUnit
		
		if(cvUnitList != null && cvUnitList.size()>0){
			for(int i=0;i<cvUnitList.size();i++){
				String sql_CVRefUnit = "INSERT INTO CV_REF_UNIT (CV_ID,UNIT_ID) VALUES (?,?)";
				getSimpleJdbcTemplate().update(sql_CVRefUnit, Id,cvUnitList.get(i).getId());
				String sql_cvUnit = "UPDATE CV_UNIT SET ISBOUND=1 WHERE ISBOUND=0";
				getSimpleJdbcTemplate().update(sql_cvUnit);
				if(cvUnitList.get(i).getContent()!=null){
					String sql_cvUnit_Content = "update cv_unit set content='"+cvUnitList.get(i).getContent() +"' where id="+cvUnitList.get(i).getId();
					getSimpleJdbcTemplate().update(sql_cvUnit_Content);
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateCV(CV cv) {
		Long id=cv.getId();
		String courseName = cv.getName();
		String serial = cv.getSerial_number();
		List<PropUnit> prop_course = cv.getCourseList();
		String brand = cv.getBrand();
		List<ExpertInfo> teacher = cv.getTeacherList();
		List<ExpertInfo> other_Teacher = cv.getOtherTeacherList();
		String introduction = cv.getIntroduction();
		String filePath=cv.getFile_path();
		String announcement = cv.getAnnouncement();
		String creator = cv.getCreator();
		List<CVUnit> cvUnitList = cv.getUnitList();
		
		
		String sql = "UPDATE CV SET NAME='"+courseName+"', SERIAL_NUMBER="+serial+", BRAND="+brand+", INTRODUCTION='"+introduction+"', FILE_PATH='"+filePath+"',ANNOUNCEMENT='"+announcement+"', CREATE_DATE=SYSDATE(), CREATOR='"+creator.toString()+"' WHERE ID="+cv.getId();
		getSimpleJdbcTemplate().update(sql);
		
//Insert Teacher
		
		if(teacher != null && teacher.size()>0){
			
			for(int i=0;i<teacher.size();i++){
				String sql_teacher="UPDATE CV_REF_TEACHER SET EXPERT_ID="+teacher.get(i).getId()+" WHERE TYPE="+Constants.TeacherType+" AND CV_ID="+cv.getId();
				
				getSimpleJdbcTemplate().update(sql_teacher);
			}
		}
		
		if(other_Teacher != null && other_Teacher.size()>0){
			for(int i=0;i<other_Teacher.size();i++){
				String sql_other_teacher = "UPDATE CV_REF_TEACHER SET EXPERT_ID="+teacher.get(i).getId()+" WHERE TYPE="+Constants.OtherTeacherType+" AND CV_ID="+cv.getId();;
				getSimpleJdbcTemplate().update(sql_other_teacher);
			}
		}
		
//Insert Course	
		if(prop_course != null && prop_course.size()>0){
			for(int i=0; i<prop_course.size();i++){
				String sql_prop_course = "UPDATE CV_REF_PROP_COURSE SET PROP_ID="+prop_course.get(i).getId()+" WHERE CV_ID="+cv.getId();
				getSimpleJdbcTemplate().update(sql_prop_course);
			}
		}
		
//Insert CVUnit
		
		if(cvUnitList != null && cvUnitList.size()>0){
			for(int i=0;i<cvUnitList.size();i++){
				/*String sql_CVRefUnit = "UPDATE CV_REF_UNIT SET UNIT_ID="+cvUnitList.get(i).getId()+" WHERE CV_ID="+cv.getId();
				getSimpleJdbcTemplate().update(sql_CVRefUnit);*/
				String sql_cvUnit = "UPDATE CV_UNIT t,CV t1,CV_REF_UNIT t2 SET t.ISBOUND=1 WHERE t.ISBOUND=0 AND t2.CV_ID="+cv.getId()+" and t2.unit_id="+cvUnitList.get(i).getId();
				getSimpleJdbcTemplate().update(sql_cvUnit);
				
				/*if(cvUnitList.get(i).getPoint() == 1 ){
					
					getSimpleJdbcTemplate().update("update cv_unit set point = 1 where cv_unit.id="+cvUnitList.get(i).getId());
				}
				if(cvUnitList.get(i).getState() == 1 )
					getSimpleJdbcTemplate().update("update cv_unit set state = 1 where cv_unit.id="+cvUnitList.get(i).getId());
				
				if(cvUnitList.get(i).getContent()!=null){
					String sql_cvUnit_Content = "update cv_unit set content="+cvUnitList.get(i).getContent() +" where id="+cvUnitList.get(i).getId();
					getSimpleJdbcTemplate().update(sql_cvUnit_Content);
				}*/
			}
		}
		return true;
	}

	@Override
	public boolean deleteCV(CV cv) {
		String sql_none = "select prop_id as id from cv_ref_prop_course where cv_ref_prop_course.cv_id="+cv.getId();
		List<PropUnit> propCourse = getJdbcTemplate().query(sql_none, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		
		if(propCourse !=null && propCourse.size()>0){
			String sql_prop_course_delete = "DELETE FROM CV_REF_PROP_COURSE WHERE CV_REF_PROP_COURSE.CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(sql_prop_course_delete);
		}
		String sql_teacher_none = "select expert_id as id from cv_ref_teacher where cv_ref_teacher.cv_id="+cv.getId();
		List<ExpertInfo> teacherInfo = getJdbcTemplate().query(sql_teacher_none, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		
		if(teacherInfo != null && teacherInfo.size()>0){
			String sql_teacher_delete = "DELETE FROM CV_REF_TEACHER WHERE CV_REF_TEACHER.CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(sql_teacher_delete);
		}	
		
		String sql_cvUnit_ref_none = "SELECT UNIT_ID AS ID FROM CV_REF_UNIT WHERE CV_REF_UNIT.CV_ID="+cv.getId();
		List<PropUnit> cvUnit_ref = getJdbcTemplate().query(sql_cvUnit_ref_none, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		List<PropUnit> cvUnit_List = new ArrayList<PropUnit>();
		if(cvUnit_ref != null && cvUnit_ref.size() > 0){
		
			for(int i=0;i<cvUnit_ref.size();i++){
				
				String sql_cvUnit_none = "SELECT T1.ID FROM CV_UNIT T1,CV_REF_UNIT T2, CV T3 WHERE T1.ID="+cvUnit_ref.get(i).getId()+" AND T2.CV_ID="+cv.getId();
				cvUnit_List = getJdbcTemplate().query(sql_cvUnit_none, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			}
			String sql_delete_cvUnit_ref = "DELETE FROM CV_REF_UNIT WHERE CV_REF_UNIT.CV_ID="+cv.getId();
			getSimpleJdbcTemplate().update(sql_delete_cvUnit_ref);
			
		}
		
		if(cvUnit_List !=null && cvUnit_List.size()>0){
			for(int i=0;i<cvUnit_List.size();i++){
				String sql_delete_cvUnit = "DELETE FROM CV_UNIT WHERE CV_UNIT.ID="+cvUnit_List.get(i).getId()/*+" AND CV_REF_UNIT.CV_ID="+cv.getId()*/;
				getSimpleJdbcTemplate().update(sql_delete_cvUnit);
			}
			
		}
		
		String sql_delete = "DELETE FROM CV WHERE ID="+cv.getId();
		getSimpleJdbcTemplate().update(sql_delete);
		
		return true;
	}

	@Override
	public boolean addCVUnit(CVUnit cvUnit) {
			
		Long cvUnit_Id = cvUnit.getId();
		if (cvUnit_Id == null || cvUnit_Id < 1) {
			cvUnit_Id = this.getNextId("CV_UNIT");
			cvUnit.setId(cvUnit_Id);
		}
		String sql_addUnit = "INSERT INTO CV_UNIT (ID,NAME,TYPE,`POINT`,STATE,CONTENT,ISBOUND) VALUES (?,?,?,?,?,?,?)";
		getSimpleJdbcTemplate().update(sql_addUnit, cvUnit_Id,cvUnit.getName(),cvUnit.getType(),cvUnit.getPoint(),cvUnit.getState(),cvUnit.getContent(),cvUnit.getIsBound());
		
		return true;
	}

	@Override
	public List<CVUnit> getCVUnitList(CVUnit cvUnit) {
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		String sql = "SELECT * FROM CV_UNIT WHERE ID>0";
		
		if (cvUnit.getIsBound() == null) {
			
		} else if (cvUnit.getIsBound() == 0) {
			sql += " and ISBOUND=0";
		} else {
			sql += " and ISBOUND=1";
		}
	
		if(cvUnit.getId()!=null)
			sql += " and id="+cvUnit.getId();
		
		cvUnitList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
	
		
		return cvUnitList;
	}

	@Override
	public List<CVUnit> getCloneCVUnitList(CV queryCV) {
		Long Id = queryCV.getId();
		String sql_cloneCopyUnit = "select t1.* from cv_unit t1,cv_ref_unit t2 where t2.cv_id="+Id+" and t2.unit_id=t1.id";
		List<CVUnit> cvCloneUnitList = new ArrayList<CVUnit>();
		cvCloneUnitList = getJdbcTemplate().query(sql_cloneCopyUnit, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		
		
		
		return cvCloneUnitList;
	}

	@Override
	public List<CV> getCloneCVList(CV queryCV) {
		Long Id = queryCV.getId();
		String sql_cloneCopyCV = "select * from cv where cv.id="+Id;
		List<CV> cvCloneList = getJdbcTemplate().query(sql_cloneCopyCV, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		return cvCloneList;
	}

	@Override
	public List<ExpertInfo> getTeacherList() {
		String getTeacher = "select id,name from expert_info where id>0 and lockstate=1";
		List<ExpertInfo> teacherList = getJdbcTemplate().query(getTeacher, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		return teacherList;
	}

	@Override
	public void addUnionUpdate(CV queryCV) {
		Long parentId = queryCV.getId();
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		cvUnitList = queryCV.getUnitList();
		for(CVUnit simpleUnit:cvUnitList){
			Long cvUnit_Id = simpleUnit.getId();
			if(cvUnit_Id == null) cvUnit_Id = this.getNextId("CV_UNIT");
			String sql_addUnit = "INSERT INTO CV_UNIT (ID,NAME,TYPE,`POINT`,STATE,CONTENT,ISBOUND) VALUES (?,?,?,?,?,?,?)";
			getSimpleJdbcTemplate().update(sql_addUnit, cvUnit_Id,simpleUnit.getName(),simpleUnit.getType(),simpleUnit.getPoint(),simpleUnit.getState(),simpleUnit.getContent(),simpleUnit.getIsBound());
			String sql_ref_Unit = "INSERT INTO CV_REF_UNIT (CV_ID,UNIT_ID) VALUES(?,?)";
			getJdbcTemplate().update(sql_ref_Unit,parentId,cvUnit_Id);
			
		}
		
	}

	@Override
	public int cloneCVUnitList(CV queryCV) {
		
		List<CVUnit> list = this.getCloneCVUnitList(queryCV);
		
		for (CVUnit cvUnit:list) {
			cvUnit.setId(-1L);
			cvUnit.setIsBound(0);
			this.addCVUnit(cvUnit);
		}
		
		return list.size();
	}

	@Override
	public boolean deleteUnit(Long id) {
		String sql_select_unit_ref = "DELETE FROM CV_UNIT_REF WHERE UNIT_ID="+id;
		getSimpleJdbcTemplate().update(sql_select_unit_ref);
		String sql_delete_Unit = "DELETE FROM CV_UNIT WHERE ID="+id;
		getSimpleJdbcTemplate().update(sql_delete_Unit);
		return true;
	}

	@Override
	public void swapCVUnit(CVUnit unit1, CVUnit unit2) {
		Long unit1_id = unit1.getId();
		Long unit2_id = unit2.getId();
		
		String sql_unit1_to_unit2 = "update cv_unit set cv_unit.name='"+unit1.getName()+"', cv_unit.`point`="+unit1.getPoint()+", cv_unit.state="+unit1.getState()+", cv_unit.content='"+unit1.getContent()+"', cv_unit.isbound="+unit1.getIsBound()+" where cv_unit.id="+unit2_id;
		getSimpleJdbcTemplate().update(sql_unit1_to_unit2);
		String sql_unit2_to_unit1 = "update cv_unit set cv_unit.name='"+unit2.getName()+"', cv_unit.`point`="+unit2.getPoint()+", cv_unit.state="+unit2.getState()+", cv_unit.content='"+unit2.getContent()+"', cv_unit.isbound="+unit2.getIsBound()+" where cv_unit.id="+unit1_id;
		getSimpleJdbcTemplate().update(sql_unit2_to_unit1);
	}

	@Override
	public void updateCVUnit(CVUnit cvUnit) {
		if(cvUnit.getPoint() != 0){
			String sql_update_point = "update cv_unit set cv_unit.`point`="+cvUnit.getPoint()+" where cv_unit.id="+cvUnit.getId();
			getSimpleJdbcTemplate().update(sql_update_point);
		}
		
		if(cvUnit.getState() != 0){
			String sql_update_state = "update cv_unit set cv_unit.state="+cvUnit.getState()+" where cv_unit.id="+cvUnit.getId();
			getSimpleJdbcTemplate().update(sql_update_state);
		}
		
	}

	@Override
	public void updateUnion(CVUnit cvUnit) {
		
		String sql_Update_CVUnit_Content = "UPDATE CV_UNIT SET CONTENT='"+cvUnit.getContent()+"' WHERE ID="+cvUnit.getId();
		getSimpleJdbcTemplate().update(sql_Update_CVUnit_Content);
	}

	@Override
	public List<CVUnit> getCVUnitList(CV queryCV) {
		List<CVUnit> cvUnitList = new ArrayList<CVUnit>();
		
		String sql_select_cvUnit_ID = "SELECT UNIT_ID AS ID FROM CV_REF_UNIT WHERE CV_REF_UNIT.CV_ID="+queryCV.getId();
		List<PropUnit> prop_for_cvUnit = getJdbcTemplate().query(sql_select_cvUnit_ID, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		
		if(prop_for_cvUnit != null && prop_for_cvUnit.size() >0 ){
			String sql_select_cvUnit = "SELECT * FROM CV_UNIT WHERE CV_UNIT.ID IN(";
			for(int i=0; i<prop_for_cvUnit.size();i++){
				if(i>0) sql_select_cvUnit += ",";
				sql_select_cvUnit += prop_for_cvUnit.get(i).getId();
			}
			sql_select_cvUnit += ")";
			cvUnitList = getJdbcTemplate().query(sql_select_cvUnit, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		}
		
		return cvUnitList;
	}
	@Override
	public List<Long> getCVIdByTeacherId(Long teacherId,Integer cvType)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select c.id from cv_ref_teacher t left join cv as c on t.cv_id = c.id where t.expert_id = ").append(teacherId).append(" and t.type = ").append(1).append(" and c.brand = ").append(cvType);
		return getJdbcTemplate().queryForList(sql.toString(), Long.class);
	}
	@Override
	public List<CVSet> getCVSetFromCVList(List<Long> cvIdList)
	{
		List<CVSet> resultList = new ArrayList<CVSet>();
		StringBuffer sql_select_schedule = new StringBuffer();		
		sql_select_schedule.append("select schedule_id from cv_schedule where cv_id in (");
		if(cvIdList != null && cvIdList.size() != 0)
		{
			for(int i = 0 ; i < cvIdList.size() ; i++)
			{
				if(i != cvIdList.size() - 1)
				{
					sql_select_schedule.append(cvIdList.get(i) + ",");	
				}
				else
				{
					sql_select_schedule.append(cvIdList.get(i));
				}
			}
			sql_select_schedule.append(")");
			List<Long>scheduleList = getJdbcTemplate().queryForList(sql_select_schedule.toString(), Long.class);
			if(scheduleList != null && scheduleList.size() != 0)
			{
				StringBuffer sql_cv_set = new StringBuffer();
				sql_cv_set.append("select DISTINCT cvs.* from cv_set_schedule css left join cv_set cvs on css.cv_set_id = cvs.id where css.cv_schedule_id in (");
				for(int i = 0 ; i < scheduleList.size() ; i++)
				{
					if(i != scheduleList.size() - 1)
					{
						sql_cv_set.append(scheduleList.get(i) + ",");	
					}
					else
					{
						sql_cv_set.append(scheduleList.get(i));
					}
				}
				sql_cv_set.append(")");
				resultList = getJdbcTemplate().query(sql_cv_set.toString(),ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
			}
		}
		return resultList;
		
	}
	//查询课程信息
    @Override
    public void queryCVPageList(PageList<CV> pl, CV queryCV,SystemUser user) {

        StringBuilder sql = new StringBuilder();
        //sql.append("from content_edition_ref t,cv t2,cv_schedule t3 ");
        //sql.append("where t.cv_id=t2.id and t.edition_id=? and t2.id=t3.cv_id and exists ( ");
        //sql.append("select 1 from cv_set_schedule t4,cv_set t5,cv_set_system_site t6, system_site t7 ");
        //sql.append("where t3.schedule_id=t4.cv_schedule_id and t4.cv_set_id=t5.id and t6.cv_set_id=t5.id ");
        //sql.append("and t6.system_site_id=t7.id and t7.domain_name=? and t5.status=? and t.check_state = 1 and t5.START_DATE <= NOW() and t5.END_DATE >= NOW()  "); //YHQ，添加时间范围，2017-03-11
        sql.append("FROM cv t1 LEFT JOIN cv_schedule t2 ON t2.CV_ID = t1.ID LEFT JOIN content_edition_ref t3 ON t3.CV_ID = t1.ID LEFT JOIN cv_set_schedule t4 ON t4.CV_SCHEDULE_ID = t2.SCHEDULE_ID LEFT JOIN cv_set t5 ON t5.ID = t4.CV_SET_ID LEFT JOIN cv_set_authorization t6 ON t6.CV_SET_ID = t5.ID LEFT JOIN cv_set_authorization_system_site t7 ON t7.AUTHORIZATION_ID = t6.ID LEFT JOIN system_site t8 ON t8.ID = t7.SYSTEM_SITE_ID LEFT JOIN cv_set_authorization_cv_schedule t9 ON t9.AUTHORIZATION_ID = t6.ID ");
        sql.append("WHERE t3.EDITION_ID = ? AND t8.domain_name = ? AND t5.`STATUS` = ? AND t3.CHECK_STATE = 1 AND t6.CV_SET_START_DATE <= CURDATE() AND t6.CV_SET_END_DATE >= CURDATE() ");
        
        List<Object> params = new ArrayList<Object>();
        params.add(queryCV.getEdtionId());
        params.add(queryCV.getServerName());
        params.add(queryCV.getStatus());
        //添加项目省份筛选条件
        if(user!=null&&user.getUserConfig()!=null&&user.getUserConfig().getUserProvinceId()!=null){
        	//sql.append(" and t5.id in (select cv_set_id from CV_REGION cr where cr.region_id = ?) ");	
        	sql.append(" AND t5.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID  WHERE csar.PROP_VAL_ID = ?) ");
        	params.add(user.getUserConfig().getUserProvinceId());
        }else{
        	//没有传省份时，查询授权全国省份的项目
        	//查询省份总数
        	//select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20
        	//授权全国的项目id
        	//select cv_set_id from CV_REGION t group by cv_set_id having count(region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)
        	//sql.append(" and t5.id in (select cv_set_id from CV_REGION cr group by cr.cv_set_id having count(cr.region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)) ");
        	sql.append(" AND t5.ID IN (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }


        String get = "select t1.*,t9.start_date,t9.end_date ";
        String get_count = "select count(DISTINCT t1.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        //sql.append(" order by t3.start_date,t.ordernum ");
        //sql.append(" order by t3.start_date,t.sort ");
        //sql.append(" order by t.sort ");
        sql.append(" group by t1.ID order by t3.sort ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }
        get += sql.toString();
        List<CV> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class), params.toArray());
        pl.setList(list);
    }

    // created by ABoy
    @Override
    public void queryCVPager(Pager<CV> pg, CV queryCV) {
    	
    	StringBuilder sql = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
        
        //sql.append("from cv t2,cv_schedule t3 ");
        //sql.append("where t2.id=t3.cv_id and find_in_set('4',t2.brand) and exists ( ");
        
        //sql.append("select 1 from cv_set_schedule t4 ");
        //sql.append("join cv_set t5 on t4.cv_set_id=t5.id ");
        //sql.append("join cv_set_system_site t6 on t6.cv_set_id=t5.id ");
        //sql.append("join system_site t7 on t6.system_site_id=t7.id ");
        //sql.append("join cv_set_user_image t8 on t5.id=t8.cv_set_id ");
        
        sql.append("FROM cv t2 LEFT JOIN cv_schedule t3 ON t3.CV_ID = t2.ID ");
		sql.append("LEFT JOIN cv_set_schedule t4 ON t4.cv_schedule_id = t3.schedule_id ");
		sql.append("LEFT JOIN cv_set t5 ON t4.cv_set_id = t5.id ");
		sql.append("LEFT JOIN cv_set_authorization t6 ON t6.cv_set_id = t5.id ");
		sql.append("LEFT JOIN cv_set_authorization_system_site t7 ON t7.AUTHORIZATION_ID = t6.ID ");
		sql.append("LEFT JOIN system_site t8 ON t7.SYSTEM_SITE_ID = t8.id ");
		sql.append("LEFT JOIN cv_set_user_image t9 ON t5.id = t9.cv_set_id ");
        
        if (queryCV.getPropName() != null && !"".equals(queryCV.getPropName())) {
            //sql.append("join qm_user_image t9 on t8.userimage_id=t9.id ");
            //sql.append("join qm_user_image_prop t10 on t9.id=t10.userimage_id ");
            //sql.append("join prop_val_ref t12 on (t10.prop_id = t12.prop_val2 or t10.prop_id = t12.prop_val1) ");
            //sql.append("join exam_prop_val t11 on t12.prop_val1=t11.id and t11.name = ? ");
        	//params.add(queryCV.getPropName());
            sql.append("LEFT JOIN qm_user_image t10 ON t9.userimage_id = t10.id ");
            sql.append("LEFT JOIN qm_user_image_prop t11 ON t10.id = t11.userimage_id ");
    		sql.append("LEFT JOIN prop_val_ref t12 ON (t11.prop_id = t12.prop_val2 OR t11.prop_id = t12.prop_val1) ");
			sql.append("LEFT JOIN exam_prop_val t13 ON t13.id =  t12.prop_val1 ");
        }
        
        //sql.append("where t3.schedule_id=t4.cv_schedule_id and t7.domain_name=? and t5.status=? ");
        sql.append("WHERE find_in_set('4', t2.brand) AND t8.domain_name = ? AND t5. STATUS = ? ");
        params.add(queryCV.getServerName());
        params.add(queryCV.getStatus());
        if (queryCV.getPropName() != null && !"".equals(queryCV.getPropName())) {
        	sql.append("AND t13.`NAME` = ? ");
        	params.add(queryCV.getPropName());
        }
        
        if(queryCV.getUserProvinceCode()!=null && !queryCV.getUserProvinceCode().equals("")){
        	//sql.append(" and t5.id in (select cv_set_id from CV_REGION cr where cr.region_id = ?) ");
        	sql.append(" and t5.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID WHERE csar.PROP_VAL_ID = ?) ");
        	params.add(queryCV.getUserProvinceCode());
        }else{
        	//sql.append(" and t5.id in (select cv_set_id from CV_REGION cr group by cr.cv_set_id having count(cr.region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)) ");
        	sql.append(" and t5.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID "
        			+ "HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }
        //sql.append(" ) ");

        String get = "select t2.*,t3.start_date,t3.end_date ";
        String get_count = "select count(DISTINCT t2.id) ";

        get_count += sql.toString();
        
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pg.setCount(fullListSize);

        sql.append(" group by t2.ID order by t3.start_date");
        if (pg.getSortDirection() != null
                && pg.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }
        
        get += sql.toString();
        List<CV> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pg.getPageOffset(), pg.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class), params.toArray());
        pg.setList(list);
    }
    
    @Override
    public void queryCVLivePageList(Pager<CV> pl, CV queryCV) {

        StringBuilder sql = new StringBuilder();
        //sql.append("from cv t2,cv_schedule t3 ");
        sql.append(" from cv t2,cv_live live,cv_schedule t3 ");
        sql.append("where exists ( ");
        sql.append("select 1 from cv_set_schedule t4 ");
        sql.append(" join cv_set t5 on t4.cv_set_id=t5.id ");
        sql.append(" join cv_set_user_image t8 on t5.id=t8.cv_set_id ");
        sql.append(" join cv_set_authorization csa on csa.cv_set_id=t5.id ");
        sql.append(" join cv_set_authorization_system_site csass on csass.AUTHORIZATION_ID=csa.ID ");
        sql.append(" join system_site t7 on csass.SYSTEM_SITE_ID=t7.id ");

        List<Object> params = new ArrayList<Object>();

        if (queryCV.getPropName() != null && !"".equals(queryCV.getPropName())) {
            sql.append(" join qm_user_image t9 on t8.userimage_id=t9.id ");
            sql.append(" join qm_user_image_prop t10 on t9.id=t10.userimage_id ");
            sql.append(" join prop_val_ref t12 on (t10.prop_id = t12.prop_val2 or t10.prop_id = t12.prop_val1) ");
            sql.append(" join exam_prop_val t11 on t12.prop_val1=t11.id and t11.name = ? ");
            params.add(queryCV.getPropName());
        }

        //sql.append("where t3.schedule_id=t4.cv_schedule_id and t7.domain_name=? and t5.status=? ");
        sql.append("where t3.schedule_id=t4.cv_schedule_id and t7.domain_name=? and t5.status=? AND t2.id = live.cv_id ");
        params.add(queryCV.getServerName());
        params.add(queryCV.getStatus());

        if (queryCV.getType() != null) {
            //直播状态 1 正在直播 2 即将开课 3 已经结束
            Date now = new Date();
            if (queryCV.getType() == 1) {
                sql.append(" and live.start_date <= ? and live.invalid_date >= ? ");
                sql.append(" AND live.course_make_type <> 1 ");
                params.add(now);
                params.add(now);
            } else if (queryCV.getType() == 2) {
                sql.append(" and live.start_date > ? ");  
                params.add(now);
            } else if (queryCV.getType() == 3) {
                sql.append(" and live.invalid_date < ? ");
                sql.append(" AND live.course_make_type <> 1 ");
                params.add(now);
            } else if (queryCV.getType() == 4) {//检索回放状态的直播课程
                sql.append(" AND  live.course_make_type = 1 ");
            }
        }

        sql.append(")");

        sql.append(" and t2.id=t3.cv_id ");

        //添加了课程类型后，现在得根据课程类型查询--taoliang
        if(queryCV.getCv_type() != null && queryCV.getCv_type() != 0){
            sql.append(" and t2.cv_type = ? ");
            params.add(queryCV.getCv_type());
        }

        String get = "SELECT A.* FROM ( SELECT t2.*, live.start_date, live.invalid_date AS end_date, live.course_make_type, " +
        		"( CASE WHEN live.start_date <= NOW() AND live.invalid_date >= NOW() THEN 1 WHEN live.start_date > NOW() THEN 2 WHEN " +
        		"live.invalid_date < NOW() AND live.course_make_type <> 1 THEN 3 WHEN live.course_make_type = 1 THEN 4 END ) AS type ";

        
        String get_count = "select count(DISTINCT t2.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setCount(fullListSize);
        sql.append(" ) A LEFT JOIN cv_live_type_sort t9 ON t9.type = A.type group by A.id ORDER BY t9.id ASC, A.start_date DESC ");

        get += sql.toString();
        List<CV> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class), params.toArray());
        pl.setList(list);
    }

    @Override
    public void queryCVPageList2(Pager<CV> pl, CV queryCV) {
    	List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        //sql.append("from cv t2,cv_schedule t3 ");
        //sql.append("where t2.id = t3.cv_id");
        sql.append("FROM cv t2 LEFT JOIN cv_schedule t3 ON t3.CV_ID = t2.ID ");
		sql.append("LEFT JOIN cv_set_schedule t4 ON t4.cv_schedule_id = t3.schedule_id ");
		sql.append("LEFT JOIN cv_set t5 ON t4.cv_set_id = t5.id ");
		sql.append("LEFT JOIN cv_set_authorization t6 ON t6.cv_set_id = t5.id ");
		sql.append("LEFT JOIN cv_set_authorization_system_site t7 ON t7.AUTHORIZATION_ID = t6.ID ");
		sql.append("LEFT JOIN system_site t8 ON t7.SYSTEM_SITE_ID = t8.id ");
		sql.append("LEFT JOIN cv_set_user_image t9 ON t5.id = t9.cv_set_id ");
		
		if (queryCV.getPropName() != null && !"".equals(queryCV.getPropName())) {
            sql.append("LEFT JOIN qm_user_image t10 ON t9.userimage_id = t10.id ");
            sql.append("LEFT JOIN qm_user_image_prop t11 ON t10.id = t11.userimage_id ");
    		sql.append("LEFT JOIN prop_val_ref t12 ON (t11.prop_id = t12.prop_val2 OR t11.prop_id = t12.prop_val1) ");
			sql.append("LEFT JOIN exam_prop_val t13 ON t13.id =  t12.prop_val1 ");
        }
        
        sql.append("WHERE t8.domain_name = ? AND t5. STATUS = ? ");
        if(queryCV.getBrand() != null && queryCV.getBrand() != ""){
        	if(queryCV.getBrand().equals("3")){
        		sql.append(" AND (FIND_IN_SET('3',t2.brand) or FIND_IN_SET('5',t2.brand)) ");
        	}else{
        		sql.append(" AND find_in_set('1',t2.brand) ");
        	}
	    }
        params.add(queryCV.getServerName());
        params.add(queryCV.getStatus());
        if (queryCV.getPropName() != null && !"".equals(queryCV.getPropName())) {
        	sql.append("AND t13.`NAME` = ? ");
        	params.add(queryCV.getPropName());
        }
        
        if(queryCV.getUserProvinceCode()!=null && !queryCV.getUserProvinceCode().equals("")){
        	sql.append(" and t5.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID WHERE csar.PROP_VAL_ID = ?) ");
        	params.add(queryCV.getUserProvinceCode());
        }else{
        	sql.append(" and t5.id in (SELECT DISTINCT csa.CV_SET_ID FROM cv_set_authorization_region csar LEFT JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID GROUP BY csar.AUTHORIZATION_ID "
        			+ "HAVING count(csar.PROP_VAL_ID) = (SELECT count(1) FROM exam_prop_val epv WHERE epv.type = 20)) ");
        }
        
        
        //sql.append(" AND exists  ( ");
        //sql.append("select 1 from cv_set_schedule t4 ");
        //sql.append(" join cv_set t5 on t4.cv_set_id=t5.id ");
        //sql.append(" join cv_set_user_image t8 on t5.id=t8.cv_set_id ");
        //sql.append(" join cv_set_system_site t6 on t6.cv_set_id=t5.id ");
        //sql.append(" join system_site t7 on t6.system_site_id=t7.id ");

        /*if (queryCV.getPropName() != null && !"".equals(queryCV.getPropName())) {
            sql.append(" join qm_user_image t9 on t8.userimage_id=t9.id ");
            sql.append(" join qm_user_image_prop t10 on t9.id=t10.userimage_id ");
            sql.append(" join prop_val_ref t12 on (t10.prop_id = t12.prop_val2 or t10.prop_id = t12.prop_val1) ");
            sql.append(" join exam_prop_val t11 on t12.prop_val1=t11.id and t11.name = ? ");
            params.add(queryCV.getPropName());
        }*/

        //sql.append("where t3.schedule_id=t4.cv_schedule_id and t7.domain_name=? and t5.status=? ");

        //params.add(queryCV.getServerName());
        //params.add(queryCV.getStatus());
        
        /*if(queryCV.getUserProvinceCode()!=null && !queryCV.getUserProvinceCode().equals("")){
        	sql.append(" and t5.id in (select cv_set_id from CV_REGION cr where cr.region_id = ?) ");
        	params.add(queryCV.getUserProvinceCode());
        }else{
        	sql.append(" and t5.id in (select cv_set_id from CV_REGION cr group by cr.cv_set_id having count(cr.region_id)=(select count(1) from sub_sys_prop_val ss left join exam_prop_val epv on  epv.id=ss.prop_val_id where epv.type=20)) ");
        }*/

        if (queryCV.getType() != null) {
            //直播状态 1 正在直播 2 即将开课 3 已经结束
            Date now = new Date();
            if (queryCV.getType() == 1) {
                sql.append(" and t3.start_date <= ? and t3.end_date >= ? ");
                params.add(now);
                params.add(now);
            } else if (queryCV.getType() == 2) {
                sql.append(" and t3.start_date > ? ");
                params.add(now);
            } else if (queryCV.getType() == 3) {
                sql.append(" and t3.end_date < ? ");
                params.add(now);
            }
        }

        //sql.append(")");

//        sql.append(" and t2.id=t3.cv_id ");

//        if(queryCV.getBrand() != null && queryCV.getBrand() != ""){
//            sql.append(" and FIND_IN_SET(?,t2.brand) ");
//            params.add(queryCV.getBrand());
//        }

        String get = "select t2.*,t3.start_date,t3.end_date ";
        String get_count = "select count(DISTINCT t2.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setCount(fullListSize);
        sql.append(" GROUP BY t2.ID order by t3.start_date ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CV> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class), params.toArray());
        pl.setList(list);
    }
    @Override
    public void queryCVPageList2(PageList<CV> pl, CV queryCVSet) {

        StringBuilder sql = new StringBuilder();
        sql.append("from cv t2 ");
        sql.append("where exists ( ");
        sql.append("select 1 from cv_schedule t3 join cv_set_schedule t4 on t3.schedule_id=t4.cv_schedule_id ");
        sql.append(" join cv_set t5 on t4.cv_set_id=t5.id ");
        sql.append(" join cv_set_user_image t8 on t5.id=t8.cv_set_id ");
        sql.append(" join cv_set_system_site t6 on t6.cv_set_id=t5.id ");
        sql.append(" join system_site t7 on t6.system_site_id=t7.id ");

        List<Object> params = new ArrayList<Object>();

        if (queryCVSet.getPropName() != null && !"".equals(queryCVSet.getPropName())) {
            sql.append(" join qm_user_image t9 on t8.userimage_id=t9.id ");
            sql.append(" join qm_user_image_prop t10 on t9.id=t10.userimage_id ");
            sql.append(" join exam_prop_val t11 on t10.prop_id=t11.id and t11.name = ? ");
            params.add(queryCVSet.getPropName());
        }

        sql.append("where t2.id=t3.cv_id and t7.domain_name=? and t5.status=?)");

        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());

        if(queryCVSet.getBrand() != null && queryCVSet.getBrand() != ""){
            sql.append(" and FIND_IN_SET(?,t2.brand) ");
            params.add(queryCVSet.getBrand());
        }

        String get = "select t2.* ";
        String get_count = "select count(t2.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" order by t2.id ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<CV> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(CV.class), params.toArray());
        pl.setList(list);
    }
    
  //首页直播课程列表暂时独立出来
    @Override
    public void queryCVLivePageList(PageList<CV> pl, CV queryCVSet) {

        /*StringBuilder sql = new StringBuilder();
        sql.append("from  cv_live A,cv t2 ");
        sql.append("where exists ( ");
        sql.append("select 1 from cv_schedule t3 join cv_set_schedule t4 on t3.schedule_id=t4.cv_schedule_id ");
        sql.append(" join cv_set t5 on t4.cv_set_id=t5.id ");
        sql.append(" join cv_set_user_image t8 on t5.id=t8.cv_set_id ");
        sql.append(" join cv_set_system_site t6 on t6.cv_set_id=t5.id ");
        sql.append(" join system_site t7 on t6.system_site_id=t7.id ");

        List<Object> params = new ArrayList<Object>();

        if (queryCVSet.getPropName() != null && !"".equals(queryCVSet.getPropName())) {
            sql.append(" join qm_user_image t9 on t8.userimage_id=t9.id ");
            sql.append(" join qm_user_image_prop t10 on t9.id=t10.userimage_id ");
            sql.append(" join exam_prop_val t11 on t10.prop_id=t11.id and t11.name = ? ");
            params.add(queryCVSet.getPropName());
        }

        sql.append("where t2.id=t3.cv_id and t7.domain_name=? and t5.status=? AND t2.id = A.cv_id)");
        sql.append(" AND t2.cv_type = ?");
        params.add(queryCVSet.getServerName());
        params.add(queryCVSet.getStatus());
        params.add(queryCVSet.getCv_type());
        
        String get = "select t2.*, A.start_date,A.invalid_date AS end_date, (CASE " +
        		"WHEN A.start_date <= NOW() AND A.invalid_date >= NOW() THEN 1 " +
        		"WHEN A.start_date > NOW() THEN 2 " +
        		"WHEN A.invalid_date < NOW() THEN 3 END) AS type ";
        String get_count = "select count(t2.id) ";
*/
    	//重新构建首页直播课程查询
    	List<Object> params = new ArrayList<Object>();
    	StringBuilder sql = new StringBuilder();
    	/*sql.append("FROM (( SELECT cvl.*, ( CASE WHEN cvl.start_date <= NOW() AND cvl.invalid_date >= NOW() THEN 1 WHEN cvl.start_date > NOW() THEN 2 WHEN cvl.invalid_date < NOW() THEN 3 END ) AS type FROM cv_live cvl ) A ");
    	sql.append(" JOIN ( SELECT t2.* FROM content_edition_ref t, cv t2, cv_schedule t3 WHERE t.cv_id = t2.id AND t.edition_id = 6 AND t2.id = t3.cv_id AND t2.cv_type = 2 AND EXISTS ( SELECT 1 FROM cv_set_schedule t4, cv_set t5, cv_set_system_site t6, system_site t7 ");
    	sql.append("WHERE t3.schedule_id = t4.cv_schedule_id AND t4.cv_set_id = t5.id AND t6.cv_set_id = t5.id AND t6.system_site_id = t7.id AND t7.domain_name = ? AND t5. STATUS = 5 AND t.check_state = 1 AND t5.START_DATE <= NOW() AND t5.END_DATE >= NOW()) ");
    	sql.append("ORDER BY t3.start_date, t.ordernum ASC ) B ON B.id = A.cv_id )");*/
    	sql.append(" FROM( SELECT cvl.*, ( CASE WHEN cvl.start_date <= NOW() AND cvl.invalid_date  ");
    	sql.append(" >= NOW() THEN 1 WHEN cvl.start_date > NOW() THEN 2 WHEN cvl.invalid_date <    ");
    	sql.append(" NOW() AND cvl.course_make_type <> 1 THEN 3 WHEN cvl.course_make_type = 1      ");
    	sql.append(" THEN 4 END ) AS type FROM cv_live cvl ) A JOIN ( SELECT t2.* FROM             ");
    	sql.append(" content_edition_ref t, cv t2, cv_schedule t3 WHERE t.cv_id = t2.id AND        ");
    	sql.append(" t.edition_id = 6 AND t2.id = t3.cv_id AND t2.cv_type = 2 AND EXISTS ( SELECT  ");
    	sql.append(" 1 FROM cv_set_schedule t4, cv_set t5, cv_set_authorization csa, cv_set_authorization_system_site csass, system_site t7   ");
    	sql.append(" WHERE t3.schedule_id = t4.cv_schedule_id AND t4.cv_set_id = t5.id AND         ");
    	sql.append(" csa.CV_SET_ID = t5.id AND csass.AUTHORIZATION_ID = csa.ID AND csass.SYSTEM_SITE_ID = t7.id AND t7.domain_name =  ?    ");
    	sql.append(" AND t5. STATUS = 5 AND t.check_state = 1 AND t5.START_DATE <=     ");
    	sql.append(" CURDATE() AND t5.END_DATE >= CURDATE() ) ) B ON B.id = A.cv_id                        ");
    	sql.append(" LEFT JOIN cv_live_type_sort t9 ON t9.type = A.type ORDER BY t9.id ASC, A.start_date DESC ");
    	params.add(queryCVSet.getServerName());
    	
    	String get_count = "select COUNT(B.id) ";
    	get_count += sql.toString();
    	
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);
        String get = "SELECT B.*, A.start_date, A.invalid_date AS end_date, A.type ";
        get += sql.toString();
        List<CV> list = getJdbcTemplate().query(
        PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), 
        	ParameterizedBeanPropertyRowMapper.newInstance(CV.class), params.toArray());
        /*if(list != null && list.size() > 0){
        	for(CV cv : list){
        		Date now = new Date();
        		Date startDate = cv.getStartDate();
        		Date endDate = cv.getEndDate();
        		if(startDate.getTime() > now.getTime()){
        			cv.setType(2);
        		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
        			cv.setType(1);
        		}else if(endDate.getTime() < now.getTime()){
        			cv.setType(3);
        		}
        	}
        }*/
        
        pl.setList(list);
    }

    public List<ExpertInfo> getManagerList(Long id) {
        String sql = "select t2.*,t3.name as jobName from cv_ref_teacher t join expert_info t2 on t.expert_id=t2.id left join exam_prop_val t3 on t2.job=t3.id  where t.CV_ID=?";

        return getJdbcTemplate().query(sql,
                ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), id);
    }

	@Override
	public List<CV> cvForProvinceManager(List<CV> list,SystemUser user) {
		
		List<CV> allArry = new ArrayList<CV>();
		allArry.addAll(list);
		if(list != null && list.size() > 0){
			try{
	        	for(CV cv : list){
	        		String sqlStr = " select * from cv_set where id in ( "
	    	                +" select cv_set_id from cv_set_schedule where cv_schedule_id in ( " 
	    		            +" select SCHEDULE_id from cv_schedule where cv_id=? "
	    	                +" ) "
	                        +")";
		    		List<CVSet> cvsetList = getJdbcTemplate().query(sqlStr, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), cv.getId());
		    		if(cvsetList!=null && cvsetList.size()>0){
		    			//String sql_count = "select COUNT(*) from CV_REGION t where  t.CV_SET_ID="+cvsetList.get(0).getId();
		    			String sql_count = "select csar.AUTHORIZATION_ID from cv_set_authorization csa LEFT JOIN cv_set_authorization_region csar ON csar.AUTHORIZATION_ID = csa.ID "
		    					+ "where csa.CV_SET_ID = " + cvsetList.get(0).getId() + " GROUP BY csar.AUTHORIZATION_ID HAVING COUNT(csar.ID) = "
		    					+ "(SELECT COUNT(epv.ID) FROM exam_prop_val epv WHERE epv.type = 20)";
		    			List<Long> propCount = getJdbcTemplate().queryForList(sql_count,Long.class);
		    			//int num = Integer.valueOf(NcmeProperties.getContextPropertyValue("provinceNum"));
		    			//if(propCount < num){//如果该项目授权不是全国则进行处理
	    				if(propCount == null || propCount.size() == 0){//如果该项目授权不是全国则进行处理
		    				if(user != null){
		    					String sql_propCount = "select count(*) from system_user_config s JOIN cv_set_authorization_region csar ON csar.PROP_VAL_ID = s.USER_PROVINCE_ID JOIN cv_set_authorization csa ON csa.ID = csar.AUTHORIZATION_ID WHERE s.USER_ID = "+user.getUserId()+" AND csa.CV_SET_ID = "+cvsetList.get(0).getId();
		    					int user_propCount = getJdbcTemplate().queryForInt(sql_propCount);
		    					if(user_propCount < 1){//如果该项目不是该会员所在省时则移除
		    						allArry.remove(cv);
		    					}
		    				}else{
		    					allArry.remove(cv);
		    				}
		    			}
		    		}
	        	}
			}catch(Exception ex){
				ex.printStackTrace();
			}
        }
		return allArry;
	}

	@Override
	public List<CV> queryCVForCommunal(CV cv) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cv t where 1=1");
		List list = new ArrayList();
		if(cv.getId() != null && cv.getId() > 0L){
			sql.append(" and t.id = ?");
			list.add(cv.getId());
		}
		if(cv.getCv_type() != null && cv.getCv_type() > 0){
			sql.append(" and t.cv_type =  ?");
			list.add(cv.getCv_type());
		}
		if(!StringUtil.checkNull(cv.getName())){
			sql.append(" and t.`NAME`=  ?");
			list.add(cv.getName());
		}
		List<CV> cvList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CV.class), list.toArray());
		
		if(cvList.size() > 0){
			return cvList;
		}
		return null;
	}

	@Override
	public boolean isExistOtherCVForLiveProject(Long cvSetId) {
		boolean trueOrfalse = false;
		String sql = "SELECT count(*) FROM ( SELECT t2.cv_type, t3.course_make_type FROM cv_schedule t JOIN cv_set_schedule t1 ON t.SCHEDULE_ID = t1.CV_SCHEDULE_ID AND t1.CV_SET_ID = "+cvSetId+" LEFT JOIN cv t2 ON t2.ID = t.CV_ID LEFT JOIN cv_live t3 ON t3.cv_id = t.CV_ID ) A WHERE A.cv_type <> 2";
		int count = getJdbcTemplate().queryForInt(sql);
		if(count == 0){//如果只存在直播课程
			String liveSql = "SELECT t3.course_make_type FROM cv_schedule t JOIN cv_set_schedule t1 ON t.SCHEDULE_ID = t1.CV_SCHEDULE_ID AND t1.CV_SET_ID = "+cvSetId+" LEFT JOIN cv t2 ON t2.ID = t.CV_ID LEFT JOIN cv_live t3 ON t3.cv_id = t.CV_ID";
			List<CvLive> list = getJdbcTemplate().query(liveSql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
			if(list.size() > 0){
				if(list.get(0).getCourse_make_type() != 2){
					trueOrfalse = true;
				}
			}
		}
		return trueOrfalse;
	}
	@Override
	public boolean isExistOtherCVForLiveProject1(Long cvSetId) {
		boolean trueOrfalse = false;
		String sql = "SELECT count(*) FROM ( SELECT t2.cv_type, t3.course_make_type FROM cv_schedule t JOIN cv_set_schedule t1 ON t.SCHEDULE_ID = t1.CV_SCHEDULE_ID AND t1.CV_SET_ID = "+cvSetId+" LEFT JOIN cv t2 ON t2.ID = t.CV_ID LEFT JOIN cv_live t3 ON t3.cv_id = t.CV_ID ) A WHERE A.cv_type <> 2";
		int count = getJdbcTemplate().queryForInt(sql);
		if(count == 0){//如果只存在直播课程
			trueOrfalse = true;
		}
		return trueOrfalse;
	}
}
