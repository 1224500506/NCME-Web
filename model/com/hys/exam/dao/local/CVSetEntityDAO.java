package com.hys.exam.dao.local;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.LogStudy;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.qiantai.model.CVSetEntity;
import com.hys.qiantai.model.LogStudyCVUnit;
import com.hys.qiantai.model.LogStudyCvSet;
import com.hys.qiantai.model.LogStudyStatistics;
import com.hys.qiantai.model.MyStudyInfo;


public interface CVSetEntityDAO {
	
	public boolean addCVSetEntity(CVSetEntity cvSetEntity);
	
	public List<CVSetEntity> getCVSetEntity(CVSetEntity cvSetEntity);
	
	public List<CVSetEntity> getCVSetEntity(CVSetEntity cvSetEntity,PageList pl);
	
	public Integer getCVSetEntitySize(CVSetEntity cvSetEntity);

	public void updateStatus(Long id, Long userId);

	public void updatefavor(Long cvsId, Long userId,Integer favor);

	public void updateContent(Long cvsId, Long userId, String content);
	
	public void updateScore(Long cvsId, Long userId, Double score);

	public List<MyStudyInfo> getCVAllInfo(MyStudyInfo info, PageList page);

	public int getCVAllInfoSize(MyStudyInfo info);
	
	public List<LogStudyCvSet> getLogStudyCvSet(Long userID, String ServerName,Integer yea);
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id查询课程信息
	 */
	public List<CV> getCvListByProId(Long proId);
	
	/**
	 * @author   张建国
	 * @time     2017-02-17
	 * @return   CVSet 
	 * @param    Long
	 * 方法说明      根据课程Id查询项目信息
	 */
	public CVSet queryCVSetListByCvId(Long cvId);

	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询课程信息
	 */
	public List<CV> getCvListByClassId(Long classId);
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询单元集合
	 */
	public List<CVUnit> queryUnitListByClassId(Long classId);

	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id查询项目信息
	 */
	public List<CVSet> queryCVUnitById(Long proId);
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return ExpertInfo 
	 * @param  Long
	 * 方法说明  根据项目Id查询项目负责人信息
	 */
	public List<ExpertInfo> queryProjectFZR(Long proId);
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据项目Id项目查询项目单位信息
	 */
	public List<PeixunOrg> queryProjectOrg(Long proId);
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据单元id查询单元组课信息
	 */
	public List<GroupClassInfo> queryGroupByUnitId(Long unitId);
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据课程id查询课程内容
	 */
	public List<CV> queryCVById(Long classId);
		
	/**
	 * @author Tiger
	 * @time 2017-01-11
	 * @return List<LogStudyCvSet>
	 * @param 
	 * 
	 * Get the cv_set list of log cv_set from user. 
	 */
	public void getLogCVSetListFromUser(LogStudyCvSet logInfo,PageList<LogStudyCvSet> page);
	public void getLogCVSetListFromUser(LogStudyCvSet logInfo,PageList<LogStudyCvSet> page,List<Long> list);

	/**
	 * @author 	Han
	 * @time	2017-01-12
	 * @param 	Long
	 * @return	LogStudyCvSet
	 * 方法说明  根据LogStudy_id查询项目内容
	 */
	public LogStudyCvSet getCVSetEntityInfoById(Long id);

	/**
	 * @author	Han
	 * @time	2017-01-12
	 * @param	LogStudyCVUnit
	 * @return	List<LogStudyCVUnit>
	 * 方法说明  查询LogStudyCVUnit
	 */
	public List<LogStudyCVUnit> getLogStudyCVUnitList(LogStudyCVUnit logUnit);

	/**
	 * @author	Han
	 * @time	2017-01-12
	 * @param	LogStudyStatistics
	 * @return	void
	 * 方法说明  
	 */
	public void getLogStudyStatistics(LogStudyStatistics sts);
	
	/**
	 * @author	yxt
	 * @time	2017-07-10
	 * @param	LogStudyStatistics
	 * @return	void
	 * 功能调整copy-branch方法"getLogStudyStatistics(LogStudyStatistics sts);"
	 */
	public void getLogStudyStatisticsHcharts(LogStudyStatistics sts);

	/**
	 * @author	yxt
	 * @time	2017-08-08
	 * @param	cvSetID,	userID
	 * 直播课程拦截
	 */
	public Map<String, Integer> doZBData(Long cvSetID, Long userID);
	
	public Set<Long> getCvSetByOverdue();
	
	public Integer updateCvSetByIds(Set<Long> ids);
	
	/**
	 * @author	Han
	 * @time	2017-01-13
	 * @param 	LogStudyCvSet
	 * @return	boolean
	 * 方法说明	更新LogStudyCvSet
	 */
	public boolean updateLogCVSet(LogStudyCvSet log);

	public void getLogCVSetListFromUser2(LogStudyCvSet logInfo, Pager<LogStudyCvSet> page, SystemUser user);

	/**
	 * 根据用户id和项目id查询学习记录
	 *
	 * @param userId
	 * @param cvSetId
	 * @return
	 */
	public LogStudyCvSet getLogStudyCvSet(Long userId, Long cvSetId);

	/**
	 * 查询用户学习记录单元
	 *
	 * @return
	 */
	public List<LogStudyCVUnit> getLogStudyCVUnitList(Long cvSetId, Long logStudyCvSetId);
	
	/**
	 * @author Tiger
	 * @time 2017-1-27
	 * @param unitId
	 * @return
	 * @Description : Get CVUnit by unit id.
	 */
	public CVUnit getCVUnitByUnitId(Long unitId);
	
	/**
	 * @author IE
	 * @param unitId
	 * @return
	 */
	public Long getPaperIdByUnitId(Long unitId);
	
	
	/**
	 * @author IE
	 * @param assignName
	 * @return
	 */
	public Long getCurrentSystemUserIDByName(String assignName);
	
	/**
	 * @author IE
	 * @param logStudy
	 */
	public void setLogStudyCVUnitExam(LogStudy logStudy);
	
	/**
	 * @param   Long
	 * @return  ExpertInfo
	 * @author  张建国
	 * @time    2017-02-15
	 * 方法说明：根据课程id查询课程主讲教师
	 */
	public ExpertInfo queryTeacherByCVId(Long cvId);
	
	/**
	 * @auth   scp
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询单元集合
	 */
	public List<GroupClassInfo> queryGroupClassInfo(Long classId);

	/**
	 * 根据用户的省份id和项目id，查询项目和省份关联表中数据，如果能查到记录，则该项目开放的省份和该用户所在省份是一致的
	 * @param cvId
	 * @param userProviceId
	 * @return
	 */
	public List getCVId(long cvId, long userProviceId);
	
	/**
	 * @author 杨红强
	 * @time   2017-07-22
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id和userId查询单元信息
	 */
	public List<CVUnit> queryUnitListByClassIdAndUserId(Long classId, Long userId);
	
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id和userId查询单元的学习记录信息
	 */
	public List<CVUnit> queryUnitLogStudyByCvSetIdAndUserId(Long cvSetId, Long userId);
	//区分开直播课程中有直播单元和回放单元的情况
	public List<CVUnit> queryUnitLogStudyByCvSetIdAndUserIdForLive(Long cvSetId, Long userId);
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * @return List 	  
	 * 方法说明  根据单元Id和userId查询单元的学习记录信息、单元信息
	 */
	public CVUnit queryCVUnitLogStudyByUnitIdAndUserId(Long unitId, Long userId);
	
	/**
	 * @author 杨红强
	 * @time   2017-07-29
	 * @return List 
	 * 方法说明  根据课程Id和userId查询单元的学习记录信息
	 */
	public List<CVUnit> queryUnitLogStudyByCvIdAndUserId(Long cvId, Long userId);
	
	public int findCvSetId(String setId);
}
