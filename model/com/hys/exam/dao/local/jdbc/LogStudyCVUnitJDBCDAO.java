package com.hys.exam.dao.local.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.LogStudyCVUnitManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveStudy;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.LogStudyCV;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.StringUtils;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCVUnitVideoCensus;
import com.hys.qiantai.model.LogStudyCvSet;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitJDBCDAO.java
 *     
 *     Description       :   学习记录(单元)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVUnitJDBCDAO extends BaseDao implements LogStudyCVUnitManageDAO {

	private String getSystemUserList_SQL = " select u.*,a.account_name, u.id user_id, a.account_id,  a.account_remark, a.account_status from system_user u left join system_user_config c on u.id=c.user_id left join system_account a on u.id = a.user_Id where u.id>0  " ;
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	@Override
	public void addLogStudyCVUnit(LogStudyCVUnit log) {
		String sql = " insert into log_study_cv_unit(log_study_cv_set_id,system_user_id,cv_id,cv_unit_id,status,last_update_date) values(?,?,?,?,1,sysdate()) ";
		getJdbcTemplate().update(sql, log.getLogStudyCvSetId(), log.getSystemUserId(),log.getCvId(),log.getCvUnitId());
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记录是否存在
	 */
	public List<LogStudyCVUnit> queryLogStudyCVUnitByUnitId(LogStudyCVUnit cvs){
		String sql = " select * from log_study_cv_unit where cv_unit_id =? and system_user_id = ? ";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class),cvs.getCvUnitId(),cvs.getSystemUserId());
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记修改单元学习记录————YHQ，2017-07-24，更新个人的记录不是更新所有的
	 */
    public void updLogStudyCVUnit(Long unitId, Long userId){
		String sql = " update log_study_cv_unit set last_update_date = sysdate()  where cv_unit_id = ? and system_user_id = ? ";
		getJdbcTemplate().update(sql,unitId,userId);
	}
    
    
    
    
    
    
    /**
	 * @author   杨红强
	 * @param    LogStudyCVUnitVideo
	 * @return   boolean
	 * 方法说明： 保存学习记录的视频记录，YHQ，2017-03-09————保存视频学习的记录(非日志)、并判断学习单元是否达标
    */
    @Override
    public boolean saveLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog){
        boolean ret = false ;
        int logNum = 0 ;
        if (videoLog != null) {
        	Long userId = videoLog.getSystemUserId() ;
        	Long unitId = videoLog.getCvUnitId() ;
        	Long cvId = videoLog.getCvId() ;
        	Long logStudyCvUnitId = videoLog.getLogStudyCvUnitId() ;
        	Long videoLength = videoLog.getVideoLength() ;
        	Long videoPlayLength = videoLog.getVideoPlayLength() ;
        	String startDate = videoLog.getStartDate() ;
        	
        	if (userId != null && unitId != null) {
        		String sql = "select count(*) from log_study_cv_unit_video where SYSTEM_USER_ID = ? and CV_UNIT_ID = ? " ;
        		logNum = getJdbcTemplate().queryForInt(sql, userId,unitId) ;
        		if (cvId == null) cvId = 0L ;
    			if (videoLength == null) videoLength = 0L ;
    			if (videoPlayLength == null) videoPlayLength = 0L ;
    			if (logStudyCvUnitId == null) logStudyCvUnitId = 0L ;
    			
    			
        		if (logNum > 0) {
        			sql = "update log_study_cv_unit_video set videoLength = ?, videoPlayLength = ?, END_DATE = NOW()  where CV_UNIT_ID = ? and SYSTEM_USER_ID = ? " ;
                    logNum = getJdbcTemplate().update(sql,videoLength,videoPlayLength,unitId,userId) ;
        		} else { 
        			if (startDate == null) {        				
        				sql = "insert into log_study_cv_unit_video (LOG_STUDY_CV_UNIT_ID,SYSTEM_USER_ID,CV_ID,CV_UNIT_ID,videoLength,videoPlayLength,START_DATE,END_DATE) values(?,?,?,?,?,?,NOW(),NOW())" ;
        				logNum = getJdbcTemplate().update(sql,logStudyCvUnitId,userId,cvId,unitId,videoLength,videoPlayLength) ;
        			}else { 
        				sql = "insert into log_study_cv_unit_video (LOG_STUDY_CV_UNIT_ID,SYSTEM_USER_ID,CV_ID,CV_UNIT_ID,videoLength,videoPlayLength,START_DATE,END_DATE) values(?,?,?,?,?,?,?,NOW())" ;
        				logNum = getJdbcTemplate().update(sql,logStudyCvUnitId,userId,cvId,unitId,videoLength,videoPlayLength,startDate) ;
        			}
        		}
        		if (logNum > 0 && videoLength != 0) {
        			Double playStep =  (100d * videoPlayLength) / videoLength ; 
        			
        			sql = "select * from cv_unit where id = ? " ;
                    List<CVUnit> cvQuotaList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class),unitId);
                    if (cvQuotaList != null && cvQuotaList.size() > 0) {
                    	CVUnit cvunitObj = cvQuotaList.get(0) ;
                    	boolean unitIsPass = false ;
                    	if (cvunitObj != null) {
                    		Integer unitPoint = cvunitObj.getPoint() ;
                    		if (unitPoint != null && unitPoint ==1) {
                    			Double unitQuota = cvunitObj.getQuota() ;
                    			if (unitQuota != null) {
                    				if (playStep >= unitQuota) unitIsPass = true ;
                    			} else {
                    				unitIsPass = true ;
                    			}
                    		} else {
                    			unitIsPass = true ;
                    		}
                    	}
                    	if (unitIsPass) {
                    		sql = "update log_study_cv_unit set STATUS = 2, LAST_UPDATE_DATE = NOW() where SYSTEM_USER_ID = ? and CV_UNIT_ID = ? " ;
                    		logNum = getJdbcTemplate().update(sql,userId,unitId) ;
                    	}
                    }
        		}
        	}        	
        }
        
        if (logNum > 0) ret = true ; 
        
        {//此代码块用户维护用户的课程和项目学习记录----taoliang(如果有异常，则吃掉，不影响其它功能)
        	try{
	        	List<CV> cvList = null;
	        	List<SystemUser> resList = null;
		        String queryCVSql = "select * from cv t where t.id = "+videoLog.getCvId();
		        cvList = getJdbcTemplate().query(queryCVSql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		        
		        StringBuilder sql = new StringBuilder();
				sql.append(getSystemUserList_SQL).append(" and u.Id = ? ");
				resList = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(SystemUser.class), videoLog.getSystemUserId());
		        
				if( (cvList != null && cvList.size() > 0) && (resList != null && resList.size() > 0) ){
					updateCVForLogStudy(cvList.get(0), resList.get(0));
					updateCVSetForLogStudy(cvList.get(0), resList.get(0));
		        }
	        
	        }catch(Exception ex){}
        }
        
        return ret ;
    }
    
    /**
	 * @author   yxt
	 * @param    LogStudyCVUnitVideoCensus
	 * @return   boolean
	 * 方法说明： 保存视频实际学习时间记录，YXT 2017-07-13
    */
    @Override
    public boolean saveLogStudyCVUnitVideoCensus(LogStudyCVUnitVideoCensus videoLog){
        boolean ret = false ;
        if (videoLog != null && videoLog.getCvSetId() > 0L && videoLog.getCvId() > 0L && videoLog.getCvUnitId() > 0L && videoLog.getSystemUserId() > 0L) {
            String sql = "" ;
            int logNum = 0;
            sql = "insert into log_study_cv_unit_video_census (SYSTEM_USER_ID,CV_SET_ID,CV_ID,CV_UNIT_ID,videoEchoLength,videoStartLength,videoEndLength,videoLength) values(" 
            		+ videoLog.getSystemUserId() + "," + videoLog.getCvSetId() + "," + videoLog.getCvId() + "," + videoLog.getCvUnitId() + "," + videoLog.getVideoEchoLength() + "," 
            		+ videoLog.getVideoStartLength() + ",'" + videoLog.getVideoEndLength() + "'," + videoLog.getVideoLength() + ")" ; 
            if (!StringUtils.checkNull(sql)) {
            	logNum = getJdbcTemplate().update(sql) ;
				if (logNum > 0) {                   
					ret = true ;
				}
            }
        } else {
        	if(videoLog!=null){
        		System.out.println("CvSetId:"+videoLog.getCvSetId()+";CvId:"+videoLog.getCvId()+";CvUnitId:"+videoLog.getCvUnitId()+";SystemUserId:"+videoLog.getSystemUserId()+";");
        	}else{
        		System.out.println("实体类为NULL");
        	}
        }
        return ret ;
    }
    
    /**
	 * @author   杨红强
	 * @param    LogStudyCVUnitVideo
	 * @return   LogStudyCVUnitVideo
	 * 方法说明： 获取个人学习记录的视频记录，YHQ，2017-03-10
    */
    @Override
    public LogStudyCVUnitVideo queryLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog) {
        LogStudyCVUnitVideo videoLogObjRes = null ;
        if (videoLog != null) {
            Long cvId = videoLog.getCvId() ;
            Long cvUnitId = videoLog.getCvUnitId();
            Long userId = videoLog.getSystemUserId();
            if (cvId != null && cvId > 0 && cvUnitId != null && cvUnitId > 0 && userId != null && userId > 0) {                
                String sql = "select * from log_study_cv_unit_video where CV_ID = " + videoLog.getCvId() + " and CV_UNIT_ID = " 
                                           + videoLog.getCvUnitId() + " and SYSTEM_USER_ID = " + videoLog.getSystemUserId() ;
                List<LogStudyCVUnitVideo> lscuvList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnitVideo.class));
                if (lscuvList != null && lscuvList.size() > 0) {
                    videoLogObjRes = lscuvList.get(0) ;
                }
            }
        }
        return videoLogObjRes ;
    }

	@Override
	public void updLogStudyCVUnitById(LogStudyCVUnit cvs) {		
		String sql = " update log_study_cv_unit set status = ? , LAST_UPDATE_DATE = NOW()  where cv_unit_id = ? and SYSTEM_USER_ID = ? " ;
		getJdbcTemplate().update(sql,cvs.getStatus(),cvs.getCvUnitId(),cvs.getSystemUserId());
		
	}

	/**
     * 根据用户ID和单元ID更新单元学习记录的状态
     * @param cvs
     * @return
     */
    public boolean updLogStudyCVUnitByUserAndCUId(LogStudyCVUnit cvs){
    	String sql = "UPDATE log_study_cv_unit SET status = "+cvs.getStatus()+" WHERE system_user_id="+cvs.getSystemUserId()+" AND cv_unit_id="+cvs.getCvUnitId();
		return getJdbcTemplate().update(sql) > 0;
    }
	
    /**
     * 根据用户ID和单元ID更新视频学习记录的状态
     * @param cvs
     * @return
     */
    public boolean updLogStudyCVUnitVideoByUserAndCUId(LogStudyCVUnitVideo cvs){
    	String sql = "UPDATE log_study_cv_unit_video SET videoLength = "+cvs.getVideoLength()+",videoPlayLength = "+cvs.getVideoPlayLength()+" WHERE system_user_id="+cvs.getSystemUserId()+" AND cv_unit_id="+cvs.getCvUnitId();
		return getJdbcTemplate().update(sql) > 0;
    }
    
	/**
     * 根据项目id和用户id查询该课程的学习记录
     * @param videoLog
     * @return
     */
	@Override
	public List<LogStudyCVUnit> queryLogStudyCVUnitByCVId(LogStudyCVUnit cvUnitLog) {
		String sql = " select * from log_study_cv_unit where log_study_cv_set_id =? and system_user_id = ? order by last_update_date desc";
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class),cvUnitLog.getLogStudyCvSetId(),cvUnitLog.getSystemUserId());
	}

	@Override
	public boolean addLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog) {//YHQ,2017-07-30,应该废弃掉，指向saveLogStudyCVUnitVideo：保存视频学习的记录(非日志)、并判断学习单元是否达标
		return saveLogStudyCVUnitVideo(videoLog) ;		
	}

	@Override
	public LogStudyCVUnitVideo getVideoLog(String classNo, Long userId, Long cvLiveStudyId) {
		LogStudyCVUnitVideo study  = new LogStudyCVUnitVideo();
		String sql = "select * from cv_live t WHERE t.class_no = '"+classNo+"' ORDER BY t.create_date desc";
		List<CvLive> cvList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CvLive.class));
		if(cvList != null && cvList.size() > 0){
			study.setCvId(cvList.get(0).getCv_id());
			String sql1 = "select * from log_study_cv_unit t where t.CV_ID = "+cvList.get(0).getCv_id()+" AND t.SYSTEM_USER_ID = "+userId;
			List<LogStudyCVUnit> logstudyunitList = getJdbcTemplate().query(sql1,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class));
			if(logstudyunitList != null && logstudyunitList.size() > 0){
				study.setLogStudyCvUnitId(logstudyunitList.get(0).getId());
				study.setCvUnitId(logstudyunitList.get(0).getCvUnitId());
			}
			
		}
		String querySql = "select * from cv_live_study_ref t where t.class_no = '"+classNo+"'";
		List<CvLiveStudyRef> studyrefList = getJdbcTemplate().query(querySql, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudyRef.class));
		if(studyrefList != null && studyrefList.size() > 0){
			study.setVideoLength(Long.valueOf(studyrefList.get(0).getTotal_live_length()));
		}
		String sql2 = "select * from cv_live_study t where t.id = "+cvLiveStudyId;
		List<CvLiveStudy> studyList = getJdbcTemplate().query(sql2, ParameterizedBeanPropertyRowMapper.newInstance(CvLiveStudy.class));
		if(studyList != null && studyList.size() > 0){
			study.setVideoPlayLength(Long.valueOf(studyList.get(0).getUseful_length()));
		}
		
		return study;
	}

	@Override
	public List<LogStudyCVUnit> queryLogStudyCVUnitForLiveFilter(
			LogStudyCVUnit cvStudyLog) {
		String sql = "SELECT t.* FROM log_study_cv_unit t, cv_unit t1, log_study_cv_unit_video t2 WHERE t.cv_unit_id = t1.id AND t.cv_unit_id = ? AND t.system_user_id = ? AND t1.unit_make_type <> 2 AND t1.id = t2.CV_UNIT_ID AND t2.videoPlayLength = 0 AND EXISTS ( SELECT 1 FROM cv_live t3 WHERE t2.cv_id = t3.cv_id )"; 
		return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCVUnit.class),cvStudyLog.getCvUnitId(),cvStudyLog.getSystemUserId());
	}

	@Override
	public CVSet getCVSetByCVId(Long cvId) {
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
	public boolean updateCVForLogStudy(CV cv, SystemUser user) {
		
		boolean trueOrfalse = false;
		try{
		//log_study_cv中是否存在该课程学习记录
		String queryLogCVsql = "SELECT * FROM log_study_cv t where t.CV_ID = ? AND t.SYSTEM_USER_ID = ?";
		List<LogStudyCV> logCVList = getJdbcTemplate().query(queryLogCVsql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCV.class), cv.getId(), user.getUserId());
		
		//拿到该课程的项目信息
		List<CVSet> cvSetList = null;
		CVSet cvSet = new CVSet();
		String queryCVSetSql = "SELECT * FROM cv_set WHERE id IN ( SELECT cv_set_id FROM cv_set_schedule WHERE cv_schedule_id IN ( SELECT SCHEDULE_id FROM cv_schedule WHERE cv_id = ? ))";
		cvSetList = getJdbcTemplate().query(queryCVSetSql,ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), cv.getId());
		if(cvSetList != null && cvSetList.size() > 0){
			cvSet = cvSetList.get(0);
		}
		
		if(cv.getCv_type() == 2){//当该课程为直播课程时
			int num = 0;
			String sql = "";
			//如果为直播课程则直播、回放、点播中有一个达标其它都达标，即该课程已学完
			{//直播--直播单元
				sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
						+ "u.unit_make_type=0 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
				Integer make0 = getJdbcTemplate().queryForInt(sql, user.getUserId(), cv.getId());
				sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=0 AND u.`POINT` = 1 and r.CV_ID=?";
				Integer unit0 = getJdbcTemplate().queryForInt(sql, cv.getId());
				if(unit0 > 0){
					if(make0 == unit0){
						num = 1;
					}
				}
			}
			{//直播--回放单元
				sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
						+ "u.unit_make_type=1 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
				Integer make1 = getJdbcTemplate().queryForInt(sql, user.getUserId(), cv.getId());
				sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=1 AND u.`POINT` = 1 and  r.CV_ID=?";
				Integer unit1 = getJdbcTemplate().queryForInt(sql, cv.getId());
				if(unit1 > 0){
					if(make1 == unit1){
						num = 1;
					}
				}
			}
			{//直播--点播单元
				sql = "select COUNT(DISTINCT l.CV_UNIT_ID) from log_study_cv_unit l LEFT JOIN cv_unit u on l.CV_UNIT_ID=u.ID where "
						+ "u.unit_make_type=2 and u.`point`=1 and l.STATUS=2 and l.SYSTEM_USER_ID=? and l.CV_ID=?";
				Integer make2 = getJdbcTemplate().queryForInt(sql, user.getUserId(), cv.getId());
				sql = "select COUNT(DISTINCT u.id) from cv_unit u LEFT JOIN cv_ref_unit r on u.ID=r.UNIT_ID where u.unit_make_type=2 AND u.`POINT` = 1 and  r.CV_ID=?";
				Integer unit2 = getJdbcTemplate().queryForInt(sql, cv.getId());
				if(unit2 > 0){
					if(make2 == unit2){
						num = 1;
					}
				}
			}
			if(logCVList.size() > 0){
				String upLogCVSql = "";
				if(num > 0){
					upLogCVSql = "UPDATE log_study_cv SET STATE = 2 , LAST_UPDATE_DATE = NOW()  where CV_ID = ? and SYSTEM_USER_ID = ? ";
				}else{
					upLogCVSql = "UPDATE log_study_cv SET STATE = 1 , LAST_UPDATE_DATE = NOW()  where CV_ID = ? and SYSTEM_USER_ID = ? ";
				}
				int count = getJdbcTemplate().update(upLogCVSql,cv.getId(),user.getUserId());
				if(count > 0){
					trueOrfalse = true;
				}
			}else{
				String upLogCVSql = "";
				if(num > 0){
					upLogCVSql = "insert into log_study_cv (SYSTEM_USER_ID,CV_SET_ID,CV_ID,STATE,LAST_UPDATE_DATE) values(?,?,?,2,NOW())" ;
				}else{
					upLogCVSql = "insert into log_study_cv (SYSTEM_USER_ID,CV_SET_ID,CV_ID,STATE,LAST_UPDATE_DATE) values(?,?,?,1,NOW())";
				}
				int count = getJdbcTemplate().update(upLogCVSql,user.getUserId(), cvSet.getId() ,cv.getId());
				if(count > 0){
					trueOrfalse = true;
				}
			}
		}else{//当课程为普通点播课程时
			int totalCount = 0;//课程下总的任务点单元数量
			int studyCount = 0;//会员已学过的任务点单元数量
			String totalSql = "SELECT count(1) FROM cv_unit t, cv_ref_unit t1 WHERE t.ID = t1.UNIT_ID AND t.`POINT` = 1 AND t1.CV_ID = "+cv.getId();
			totalCount = getJdbcTemplate().queryForInt(totalSql);
			
			String studySql = "SELECT count(1) FROM log_study_cv_unit t, cv_unit t1 WHERE t.CV_UNIT_ID = t1.ID AND t.CV_ID = "+cv.getId()+" AND t.SYSTEM_USER_ID = "+user.getUserId()+" AND t.`STATUS` = 2 AND t1.`POINT` = 1";
			studyCount = getJdbcTemplate().queryForInt(studySql);
			
			if(logCVList.size() > 0){
				String upLogCVSql = "";
				if(studyCount == totalCount){
					upLogCVSql = "UPDATE log_study_cv SET STATE = 2 , LAST_UPDATE_DATE = NOW()  where CV_ID = ? and SYSTEM_USER_ID = ? ";
				}else{
					upLogCVSql = "UPDATE log_study_cv SET STATE = 1 , LAST_UPDATE_DATE = NOW()  where CV_ID = ? and SYSTEM_USER_ID = ? ";
				}
				int count = getJdbcTemplate().update(upLogCVSql,cv.getId(),user.getUserId());
				if(count > 0){
					trueOrfalse = true;
				}
			}else{
				String upLogCVSql = "";
				if(studyCount == totalCount){
					upLogCVSql = "insert into log_study_cv (SYSTEM_USER_ID,CV_SET_ID,CV_ID,STATE,LAST_UPDATE_DATE) values(?,?,?,2,NOW())" ;
				}else{
					upLogCVSql = "insert into log_study_cv (SYSTEM_USER_ID,CV_SET_ID,CV_ID,STATE,LAST_UPDATE_DATE) values(?,?,?,1,NOW())";
				}
				int count = getJdbcTemplate().update(upLogCVSql,user.getUserId(), cvSet.getId() ,cv.getId());
				if(count > 0){
					trueOrfalse = true;
				}
			}
		}
		}catch(Exception ex){}
		
		return trueOrfalse;
	}

	@Override
	public boolean updateCVSetForLogStudy(CV cv, SystemUser user) {
		boolean trueOrfalse = false;
		try{
		//拿到该课程的项目信息
		List<CVSet> cvSetList = null;
		CVSet cvSet = null;
		String queryCVSetSql = "SELECT * FROM cv_set WHERE id IN ( SELECT cv_set_id FROM cv_set_schedule WHERE cv_schedule_id IN ( SELECT SCHEDULE_id FROM cv_schedule WHERE cv_id = ? ))";
		cvSetList = getJdbcTemplate().query(queryCVSetSql,ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), cv.getId());
		
		if(cvSetList != null && cvSetList.size() > 0){
			cvSet = cvSetList.get(0);
		}
		if(cvSet == null){
			return trueOrfalse;
		}
		
		//log_study_cv_set中是否存在该项目学习记录
		String queryLogCVsql = "SELECT * FROM log_study_cv_set t where t.CV_SET_ID = ? AND t.SYSTEM_USER_ID = ? ";
		List<LogStudyCvSet> logCVList = getJdbcTemplate().query(queryLogCVsql,ParameterizedBeanPropertyRowMapper.newInstance(LogStudyCvSet.class), cvSet.getId(), user.getUserId());
		
		int totalCount = 0;//项目下的课程数量
		int studyCount = 0;//项目下已完成的课程数量
		String totalSql = "SELECT count(1) FROM cv_schedule WHERE SCHEDULE_ID IN ( SELECT CV_SCHEDULE_ID FROM cv_set_schedule WHERE CV_SET_ID = "+cvSet.getId()+" )";
		totalCount = getJdbcTemplate().queryForInt(totalSql);
		
		String studySql = "SELECT count(1) FROM log_study_cv t WHERE t.STATE = 2 AND t.CV_SET_ID = "+cvSet.getId()+" AND t.SYSTEM_USER_ID = "+user.getUserId()+"";
		studyCount = getJdbcTemplate().queryForInt(studySql);
		
		if(logCVList.size() > 0){
			String upLogCVSql = "";
			if(studyCount == totalCount){
				//upLogCVSql = "UPDATE log_study_cv_set SET STATE = 2 , LAST_UPDATE_DATE = NOW()  where CV_SET_ID = ? and SYSTEM_USER_ID = ?";
				upLogCVSql = "UPDATE log_study_cv_set SET STATE = 2  where CV_SET_ID = ? and SYSTEM_USER_ID = ?";
			}else{
				//upLogCVSql = "UPDATE log_study_cv_set SET STATE = 1 , LAST_UPDATE_DATE = NOW()  where CV_SET_ID = ? and SYSTEM_USER_ID = ?";
				upLogCVSql = "UPDATE log_study_cv_set SET STATE = 1  where CV_SET_ID = ? and SYSTEM_USER_ID = ?";
			}
			int count = getJdbcTemplate().update(upLogCVSql,cvSet.getId(),user.getUserId());
			if(count > 0){
				trueOrfalse = true;
			}
		}else{
			String upLogCVSql = "";
			if(studyCount == totalCount){
				upLogCVSql = "insert into log_study_cv_set (SYSTEM_USER_ID,CV_SET_ID,STATE,IS_APPLY_CREDIT,LAST_UPDATE_DATE,APPLY_DATE,SITE_ID) values(?,?,2,1,NOW(),NOW(),1)" ;
			}else{
				upLogCVSql = "insert into log_study_cv_set (SYSTEM_USER_ID,CV_SET_ID,STATE,IS_APPLY_CREDIT,LAST_UPDATE_DATE,APPLY_DATE,SITE_ID) values(?,?,1,1,NOW(),NOW(),1)";
			}
			int count = getJdbcTemplate().update(upLogCVSql,user.getUserId(), cvSet.getId());
			if(count > 0){
				trueOrfalse = true;
			}
		}
		}catch(Exception ex){}
		return trueOrfalse;
	}
    /**
     * sunny
     * 保存面授报名
     */
	@Override
	public void saveLogStudy(LogStudyCVSet logStudy) {
		
		String sql = "insert into log_study_cv_set (SYSTEM_USER_ID,CV_SET_ID,STATE,IS_APPLY_CREDIT,LAST_UPDATE_DATE,APPLY_DATE,SITE_ID) values(?,?,1,1,NOW(),NOW(),1)";
	    getJdbcTemplate().update(sql,logStudy.getSystemUserId(), logStudy.getCvSetId());
		
	}
    
}
