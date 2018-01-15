package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CvLiveDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveCoursewareStudy;
import com.hys.exam.model.CvLiveSignk;
import com.hys.exam.model.CvLiveStudy;
import com.hys.exam.model.CvLiveStudyRef;

public class CvLiveJdbcDAO extends BaseDao implements CvLiveDAO {
	
	@Override
	public Long saveCvLiveSignk(CvLiveSignk cvls) {
		Long Id = cvls.getId();
		if(Id == null) Id = this.getNextId("cv_live_signk");
		
		String sql = "insert into cv_live_signk (id,user_id,real_name,signk_code," +
				"create_time) VALUES(?,?,?,?,SYSDATE())";
		getSimpleJdbcTemplate().update(sql, Id,cvls.getUser_id(),cvls.getReal_name(),cvls.getSignk_code());
		return Id;
	}

	@Override
	public List<CvLiveSignk> getCvLiveSignkList(Long userId, String signk) {
		String sql = "select * from cv_live_signk t WHERE t.user_id = "+userId+" and t.signk_code = '"+signk+"'";
		List<CvLiveSignk> cvLiveSignkList = new ArrayList<CvLiveSignk>();
		cvLiveSignkList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveSignk.class));
		return cvLiveSignkList;
	}

	@Override
	public boolean delCvLiveSignk(CvLiveSignk cvls) {
		String sql = "";
		String signk = cvls.getSignk_code();
		Long userid = cvls.getUser_id();
		if(signk != null && !"".equals(signk)){
			sql = "delete from cv_live_signk where signk_code ='" +signk+"'";
		}else if(userid != null && !"".equals(userid)){
			sql = "delete from cv_live_signk where signk_code =" +userid;
		}
		getJdbcTemplate().update(sql);
		return true;
	}

	@Override
	public List<CvLive> queryCvLiveList(Long cvId) {
		String sql = "SELECT * FROM cv_live t WHERE t.cv_id = "+cvId;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
	}
	
/*	@Override
	public List<CvLive> queryCvAllLiveList() {
		String sql = "SELECT * FROM cv_live";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
	}*/

	@Override
	public Long saveCvLiveStudy(CvLiveStudy record) {
		Long clstudyId = 0L;
		String querySql = "select * from cv_live_study t where t.class_no = '"+record.getClass_no()+"' AND t.nickname LIKE '%"+record.getNickname()+"%' ORDER BY t.leaveTime DESC";
		System.out.println(querySql);
		List<CvLiveStudy> studyList = getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudy.class));
		if(studyList != null && studyList.size() > 0){
			//int liveActualLength = record.getLive_actual_length() + studyList.get(0).getLive_actual_length();//累加同一堂课不同场次的直播时长
			int usefulLength = record.getUseful_length() + studyList.get(0).getUseful_length();//累加同一堂课不同场次的学生参会时长
			String sql = "UPDATE cv_live_study t SET t.joinTime = "+record.getJoinTime()+",t.leaveTime = "+record.getLeaveTime()+",t.useful_length = "+usefulLength+" " +
					"WHERE t.class_no = '"+record.getClass_no()+"' AND t.nickname LIKE '%"+record.getNickname()+"%'";
			getSimpleJdbcTemplate().update(sql);
			clstudyId = studyList.get(0).getId();
		}else{
			Long Id = record.getId();
			if(Id == null) Id = this.getNextId("cv_live_study");
			
			String sql = "insert into cv_live_study (id,user_id,class_no,nickname,mobile," +
					"joinTime,leaveTime,ip,device,useful_length,company,area,name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			getSimpleJdbcTemplate().update(sql, Id,record.getUser_id(),record.getClass_no(),record.getNickname(),record.getMobile(),
					record.getJoinTime(),record.getLeaveTime(),record.getIp(),record.getDevice(),
					record.getUseful_length(),record.getCompany(),record.getArea(),record.getName());
			clstudyId = Id;
		}
		return clstudyId;
	}

	@Override
	public List<CvLiveStudyRef> queryCvLiveStudyRef(CvLiveStudyRef record) {
		String querySql = "select * from cv_live_study_ref t where t.class_no = '"+record.getClass_no()+"' ORDER BY t.end_time DESC";
		return getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudyRef.class));
	}

	@Override
	public boolean updateCvLiveStudyRef(CvLiveStudyRef record) {
		String querySql = "select * from cv_live_study_ref t where t.class_no = '"+record.getClass_no()+"'";
		System.out.println(querySql);
		List<CvLiveStudyRef> studyrefList = getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudyRef.class));
		String sql = "";
		if(studyrefList != null && studyrefList.size() > 0){
			if(record.getStart_time() != null && record.getStart_time() > 0){
				sql = "UPDATE cv_live_study_ref SET start_time ="+record.getStart_time()+" WHERE class_no='"+record.getClass_no()+"'";
				getSimpleJdbcTemplate().update(sql);
			}else if(record.getEnd_time() != null && record.getEnd_time() > 0){
				sql = "UPDATE cv_live_study_ref SET end_time ="+record.getEnd_time()+" WHERE class_no='"+record.getClass_no()+"'";
				System.out.println(sql);
				getSimpleJdbcTemplate().update(sql);
				Long totallivelength = 0L;
				//累加总直播时长
				if(studyrefList.get(0).getTotal_live_length() == null ||
						studyrefList.get(0).getTotal_live_length() == 0){
					totallivelength = record.getEnd_time() - studyrefList.get(0).getStart_time();
				}else{
					List<CvLiveStudyRef> refList = getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudyRef.class));
					totallivelength = (record.getEnd_time() - refList.get(0).getStart_time()) + studyrefList.get(0).getTotal_live_length();
				}
				
				String totalSql = "UPDATE cv_live_study_ref SET total_live_length ="+totallivelength+" WHERE class_no='"+record.getClass_no()+"'";
				System.out.println(totalSql);
				getSimpleJdbcTemplate().update(totalSql);
			}
			
		}else{
			Long Id = record.getId();
			if(Id == null) Id = this.getNextId("cv_live_study_ref");
			sql = "insert into cv_live_study_ref (id,class_no,start_time,end_time,total_live_length) VALUES(?,?,?,?,0)";
			getSimpleJdbcTemplate().update(sql, Id,record.getClass_no(),record.getStart_time(),record.getEnd_time());
		}
		return true;
	}
	
	
	@Override
	public int cvLiveNumber(Long cvId){
		// 获取当前直播在线学习人数 
	
		String sql ="SELECT t.audience_num FROM cv_live_study_ref t LEFT JOIN cv_live t1 ON t1.class_no = t.class_no WHERE t1.cv_id ="+cvId;
		return getSimpleJdbcTemplate().queryForInt(sql);
		
		
	}
	@Override
	public int cvLiveOverNumber(Long cvId){
	// 获取直播结束时学习人数 
	 
	    String  sql = "select count(1) from cv_live_study t LEFT JOIN cv_live t1 ON t1.class_no = t.class_no WHERE t1.cv_id  ="+cvId;
	    return getSimpleJdbcTemplate().queryForInt(sql);
	
	}
	
	
	
	@Override
	public int cvLiveBackNumber(Long cvId){
	
	// 获取直播回放学习人数
	  String sql = "SELECT count(1) FROM cv_live_courseware_study t LEFT JOIN cv_live_courseware t1 ON t1.courseware_no = t.coursewareId LEFT JOIN cv_live t2 ON t2.class_no = t1.class_no WHERE t2.cv_id ="+cvId;
	  return getSimpleJdbcTemplate().queryForInt(sql);
	
	
	}
	@Override
	public int getcvZBTypeForInt(Long cvId) {
		int type = 0;
		Long nowTime = System.currentTimeMillis()/1000;
		Long invalidTime = 0L;//课程失效时间
		String _sql = "SELECT UNIX_TIMESTAMP(t.invalid_date) AS invalidTime FROM cv_live t WHERE t.cv_id = "+ cvId;
		List<CvLive> list =  getJdbcTemplate().query(_sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
		if(list != null && list.size() > 0){
			invalidTime = list.get(0).getInvalidTime();
		}
		
		String sql_live = "SELECT * FROM cv_live t WHERE t.cv_id = "+cvId;
		List<CvLive> lt = getJdbcTemplate().query(sql_live, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
		CvLive cl = new CvLive();
		if(lt != null && lt.size() > 0){
			cl = lt.get(0);
		}
		
		if(invalidTime > nowTime ){//当在有效的时间范围内
			String sql = "SELECT t.*,t1.start_time,t1.end_time FROM cv_live t ,cv_live_study_ref t1 WHERE t.class_no = t1.class_no AND t.cv_id ="+cvId;
			List<CvLive> CvLiveList =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
			if(CvLiveList != null && CvLiveList.size() > 0){
				CvLive live = CvLiveList.get(0);
				Long startTime = live.getStartTime();
				Long endTime = live.getEndTime();
				
				if(endTime != null && endTime > 0 ){
					if(startTime > endTime){//startTime > endTime考虑多场次情况
						type = 1;//正在直播
					}else if(startTime < endTime && endTime < nowTime){
						if(cl.getCourse_make_type() == 0){
							type = 5;//未完成转码
						}else if(cl.getCourse_make_type() == 1){
							type = 4;//观看回放
						}else if(cl.getCourse_make_type() == 2){
							type = 3;//直播结束
						}else{
							type = 3;//直播结束
						}
					}
				}else{
					if(startTime != null && startTime > 0){
						type = 1;//正在直播
					}
				}
			}else{
				type = 2;//即将开课 
			}
		}else{
			if(cl.getCourse_make_type() == 0){
				type = 5;//未完成转码
			}else if(cl.getCourse_make_type() == 1){
				type = 4;//观看回放
			}else if(cl.getCourse_make_type() == 2){
				type = 3;//直播结束
			}else{
				type = 3;//直播结束
			}
		}
		return type;
	}

	@Override
	public int getZBEndTypeForInt(Long cvId) {
		int endType = 0;
		CvLive live = null;
		String _sql = "SELECT * FROM cv_live t WHERE t.cv_id = "+ cvId;
		List<CvLive> list =  getJdbcTemplate().query(_sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
		//直播课程结束状态  1.尚未完成转码  2.已添加回放 3.已完成直播课程学习
		if(list != null && list.size() > 0){
			live = list.get(0);
			/*if(live.getCourseware_view_url() != null && !"".equals(live.getCourseware_view_url()) && 
					live.getCourseware_token() != null && !"".equals(live.getCourseware_token())){
				endType = 2;
			}else{
				endType = 1;
			}*/
		}
		return endType;
	}

	@Override
	public List<CvLiveCourseware> getCvLiveCoursewareList(
			CvLiveCourseware record) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cv_live_courseware t where 1=1");
		if(record.getClass_no() != null && !"".equals(record.getClass_no())){
			sql.append(" and t.class_no = '"+record.getClass_no()+"'");
		}
		if(record.getCourseware_no() != null && !"".equals(record.getCourseware_no())){
			sql.append(" and t.courseware_no = '"+record.getCourseware_no()+"'");
		}
		if(record.getCv_id() != null && record.getCv_id() > 0L){
			sql.append(" and t.cv_id = "+record.getCv_id());
		}
		if(record.getId() != null && record.getId() > 0L){
			sql.append(" and t.id = "+record.getId());
		}
		sql.append(" ORDER BY t.courseware_create_time ASC");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CvLiveCourseware.class));
	}

	@Override
	public List<CVUnit> getCvUnitForLive(Long cvId) {
		String sql = " SELECT MAX(unit_make_type) as unitMakeType FROM CV_UNIT WHERE ID IN ( "
                +" SELECT UNIT_ID FROM CV_REF_UNIT WHERE CV_ID = "+cvId
             +" ) order by sequenceNum asc";
		List<CVUnit> unitlist = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		int unitMakeType = 0;
		List<CVUnit> list = null;
		if(unitlist != null && unitlist.size() > 0){
			unitMakeType = unitlist.get(0).getUnitMakeType();
			sql = " SELECT * FROM CV_UNIT t WHERE ID IN ( "
	                +" SELECT UNIT_ID FROM CV_REF_UNIT WHERE CV_ID = "+cvId
	             +" ) AND t.unit_make_type = "+unitMakeType+" order by sequenceNum asc";
			list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		}
		return list;
	}
	
	
	@Override
	public List<CvLiveRefUnit> getCvLiveRefUnitList(CvLiveRefUnit record) {
		//String sql = "SELECT * FROM cv_live_ref_unit t WHERE t.courseware_no = '"+record.getCourseware_no()+"'";
		StringBuilder sql = new StringBuilder();
		sql.append("select * from cv_live_ref_unit t where 1=1");
		if(record.getClass_no() != null && !"".equals(record.getClass_no())){
			sql.append(" and t.class_no = '"+record.getClass_no()+"'");
		}
		if(record.getCourseware_no() != null && !"".equals(record.getCourseware_no())){
			sql.append(" and t.courseware_no = '"+record.getCourseware_no()+"'");
		}
		if(record.getCv_id() != null && record.getCv_id() > 0L){
			sql.append(" and t.cv_id = "+record.getCv_id());
		}
		if(record.getUnit_id() != null && record.getUnit_id() > 0L){
			sql.append(" and t.unit_id = "+record.getUnit_id());
		}
		if(record.getId() != null && record.getId() > 0L){
			sql.append(" and t.id = "+record.getId());
		}
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CvLiveRefUnit.class));
		//return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveRefUnit.class));
	}

	public CVUnit getCvUnitByCvId(Long cvId){
		String querySql = "SELECT cu.* FROM cv_unit cu LEFT JOIN cv_ref_unit cru ON cu.id = cru.unit_id WHERE cru.cv_id = " + cvId + " AND cu.unit_make_type = 0";
		List<CVUnit> list = getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<CvLiveCourseware> queryCvLiveCoursewareByCoursewareId(String coursewareId) {
		String querySql = "SELECT duration,cv_id FROM cv_live_courseware WHERE courseware_no = '" + coursewareId + "'";
		return getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveCourseware.class));
	}

	@Override
	public List<CvLiveRefUnit> queryCvLiveRefUnitByCoursewareId(String coursewareId) {
		String querySql = "SELECT unit_id FROM cv_live_ref_unit WHERE courseware_no = '" + coursewareId + "'";
		return getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveRefUnit.class));
	}

	@Override
	public List<CvLiveCoursewareStudy> queryCvLiveCoursewareStudy(String coursewareIdUserID) {
		String querySql = "SELECT * FROM cv_live_courseware_study WHERE CONCAT(coursewareId,user_id) = '" + coursewareIdUserID + "'";
		return getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveCoursewareStudy.class));
	}

	@Override
	public List<CvLiveCoursewareStudy> queryCvLiveCoursewareStudy(String coursewareId,
			Long uid) {
		String querySql = "SELECT * FROM cv_live_courseware_study WHERE coursewareId = '" + coursewareId + "' AND user_id = " + uid + "";
		return getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveCoursewareStudy.class));
	}
	
	@Override
	public boolean saveCvLiveCoursewareStudy(CvLiveCoursewareStudy record) {
		String sql = "INSERT INTO cv_live_courseware_study (user_id,coursewareId,username,joinTime,leaveTime,duration,ip,device,recordDate) VALUES(?,?,?,?,?,?,?,?,?)";
		return getSimpleJdbcTemplate().update(sql, record.getUserId(), record.getCoursewareId(), 
				record.getUsername(), record.getJoinTime(), record.getLeaveTime(), record.getDuration(), record.getIp(), record.getDevice(), record.getRecordDate()) > 0;
	}

	@Override
	public boolean updateCvLiveCoursewareStudy(String coursewareIdUserID, CvLiveCoursewareStudy record) {
		String sql = "UPDATE cv_live_courseware_study SET joinTime = ?,leaveTime = ?,duration = ?,ip = ?,device = ?,recordDate = ? WHERE CONCAT(coursewareId,user_id) = ?";
		return getSimpleJdbcTemplate().update(sql, record.getJoinTime(), record.getLeaveTime(), 
				record.getDuration(), record.getIp(), record.getDevice(), record.getRecordDate(), coursewareIdUserID) > 0;
	}

	@Override
	public boolean updateCvLiveStudyRefForAudience(CvLiveStudyRef record) {
		try{
			String querySql = "select * from cv_live_study_ref t where t.class_no = '"+record.getClass_no()+"'";
			System.out.println(querySql);
			List<CvLiveStudyRef> studyrefList = getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudyRef.class));
			String sql = "";
			if(studyrefList != null && studyrefList.size() > 0){
				sql = "UPDATE cv_live_study_ref SET audience_num ="+record.getAudience_num()+" WHERE class_no='"+record.getClass_no()+"'";
				getSimpleJdbcTemplate().update(sql);
			}
		}catch(Exception ex){
			System.out.println("给["+record.getClass_no()+"]维护直播实时观看人数失败");
		}
		return true;
	}
   /**
    * 查找直播
    */
	@Override
	public CvLive getCvLive(Long cvId) {
		String sql = "SELECT * FROM cv_live t WHERE t.cv_id = "+ cvId;
		       
		return getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
	}

}