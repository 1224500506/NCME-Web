package com.hys.exam.service.local.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hys.exam.utils.Pager;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.local.CVSetEntityDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.LogStudy;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.qiantai.model.CVSetEntity;
import com.hys.qiantai.model.LogStudyCVUnit;
import com.hys.qiantai.model.LogStudyCvSet;
import com.hys.qiantai.model.LogStudyStatistics;
import com.hys.qiantai.model.MyStudyInfo;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Lee 2016-12-08
 * 
 * 描述：
 * 
 * 说明:
 */
public class CVSetEntityManageImpl extends BaseMangerImpl implements CVSetEntityManage {

	private CVSetEntityDAO localCVSetEntityDAO;
	
	public CVSetEntityDAO getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}
	public void setLocalCVSetEntityDAO(CVSetEntityDAO localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}
	
	@Override
	public boolean addEntityInfos(CVSetEntity cvSetEntity) {
		// TODO Auto-generated method stub
		return localCVSetEntityDAO.addCVSetEntity(cvSetEntity);
	}
	@Override
	public List<CVSetEntity> getCVSetEntityInfo(CVSetEntity cvSetEntity) {
		// TODO Auto-generated method stub
		return localCVSetEntityDAO.getCVSetEntity(cvSetEntity);
	}
	@Override
	public void updateStatus(Long id, Long userId) {
		localCVSetEntityDAO.updateStatus(id, userId);
	}
	@Override
	public void updatefavor(Long cvsId, Long userId,Integer favor) {
		localCVSetEntityDAO.updatefavor(cvsId, userId,favor);
		
	}
	@Override
	public void updateContent(Long cvsId, Long userId, String content) {
		localCVSetEntityDAO.updateContent(cvsId, userId, content);
	}
	@Override
	public void updateScore(Long cvsId, Long userId, Double score) {
		localCVSetEntityDAO.updateScore(cvsId, userId, score);
	}
	@Override
	public List<CVSetEntity> getCVSetEntityInfo(CVSetEntity cvSetEntity,PageList pl)
	{
		return localCVSetEntityDAO.getCVSetEntity(cvSetEntity,pl);		
	}
	public Integer getCVSetEntitySize(CVSetEntity cvSetEntity)
	{
		return localCVSetEntityDAO.getCVSetEntitySize(cvSetEntity);
	}
	public List<MyStudyInfo> getCVAllInfo(MyStudyInfo info , PageList page) {
		
		return localCVSetEntityDAO.getCVAllInfo(info, page);
	}
	@Override
	public int getCVAllInfoSize(MyStudyInfo info) {
		
		return localCVSetEntityDAO.getCVAllInfoSize(info);
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据项目Id查询课程信息
	 */
	@Override
	public List<CV> getCvListByProId(Long proId){
		return localCVSetEntityDAO.getCvListByProId(proId);
	}

	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询课程信息
	 */
	@Override
	public List<CV> getCvListByClassId(Long classId){
		return localCVSetEntityDAO.getCvListByClassId(classId);
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询单元信息
	 */
	@Override
	public List<CVUnit> queryUnitListByClassId(Long classId){
		return localCVSetEntityDAO.queryUnitListByClassId(classId);
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
		return localCVSetEntityDAO.queryCVUnitById(proId);
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return ExpertInfo 
	 * @param  Long
	 * 方法说明  根据项目Id查询项目负责人信息
	 */
	@Override
	public List<ExpertInfo> queryProjectFZR(Long proId){
		return localCVSetEntityDAO.queryProjectFZR(proId);
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
		return localCVSetEntityDAO.queryProjectOrg(proId);
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
		return localCVSetEntityDAO.queryGroupByUnitId(unitId);
	}
	
	/**
	 * @auth   张建国
	 * @time   2016-12-29
	 * @return PeixunOrg 
	 * @param  Long
	 * 方法说明  根据课程id查询课程信息
	 */
	@Override
	public List<CV> queryCVById(Long classId){
		return localCVSetEntityDAO.queryCVById(classId);
	}
	
	/**
	 * @author Tiger
	 * @time 2017-01-11
	 * @return 
	 * @param LogStudyCvSet
	 * 
	 * Get the cv_set list of log cv_set from user. 
	 */
	@Override
	public void getLogCVSetListFromUser(LogStudyCvSet logInfo,
			PageList<LogStudyCvSet> page) {
		// TODO Auto-generated method stub
		localCVSetEntityDAO.getLogCVSetListFromUser(logInfo,page);
	}
	@Override
	public void getLogCVSetListFromUser(LogStudyCvSet logInfo,
			PageList<LogStudyCvSet> page,List<Long> list) {
		// TODO Auto-generated method stub
		localCVSetEntityDAO.getLogCVSetListFromUser(logInfo,page,list);
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
		return localCVSetEntityDAO.getCVSetEntityInfoById(id);
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
		return localCVSetEntityDAO.getLogStudyCVUnitList(logUnit);
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
		localCVSetEntityDAO.getLogStudyStatistics(sts);
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
		localCVSetEntityDAO.getLogStudyStatisticsHcharts(sts);
	}

	/**
	 * @author	yxt
	 * @time	2017-08-08
	 * @param	cvSetID,	userID
	 * 直播课程拦截
	 */
	@Override
	public Map<String, Integer> doZBData(Long cvSetID, Long userID){
		return localCVSetEntityDAO.doZBData(cvSetID, userID);
	}
	
	/**
	 * @author	yxt
	 * @time	2017-12-04
	 * 获得已上线授权过期的项目列表
	 */
	@Override
	public Set<Long> getCvSetByOverdue(){
		return localCVSetEntityDAO.getCvSetByOverdue();
	}
	
	/**
	 * @author	yxt
	 * @time	2017-12-04
	 * 下线授权过期的项目
	 */
	@Override
	public Integer updateCvSetByIds(Set<Long> ids){
		return localCVSetEntityDAO.updateCvSetByIds(ids);
	}
	
	/**
	 * @author	Han
	 * @time	2017-01-13
	 * @param 	LogStudyCvSet
	 * @return	boolean
	 * 方法说明	更新LogStudyCvSet
	 */
	@Override
	public boolean updateLogCVSet(LogStudyCvSet log) {
		return localCVSetEntityDAO.updateLogCVSet(log);
	}







































































	@Override
	public List<LogStudyCvSet> getLogStudyCvSet(Long userID, String ServerName,Integer year){
		return localCVSetEntityDAO.getLogStudyCvSet(userID, ServerName,year);
	}


	@Override
	public void getLogCVSetListFromUser2(LogStudyCvSet logInfo, Pager<LogStudyCvSet> page, SystemUser user) {
		localCVSetEntityDAO.getLogCVSetListFromUser2(logInfo, page, user);
	}

	/**
	 * 根据用户id和项目id查询学习记录
	 *
	 * @param userId
	 * @param cvSetId
	 * @return
	 */
	public LogStudyCvSet getLogStudyCvSet(Long userId, Long cvSetId) {
		return localCVSetEntityDAO.getLogStudyCvSet(userId, cvSetId);
	}

	@Override
	public boolean getIsPass(Long userId, Long cvSetId) {

		LogStudyCvSet log = localCVSetEntityDAO.getLogStudyCvSet(userId, cvSetId);

		if(log == null){
			return false;
		}

		if (log.getState() == 2 && log.getIs_apply_credit() == 1) {
			//已经申请通过
			return true;
		}

		//查询项目的课程学习单元
		List<LogStudyCVUnit> list = localCVSetEntityDAO.getLogStudyCVUnitList(cvSetId,log.getId());

		for (LogStudyCVUnit unit : list) {
			//是否任务点
			if(unit.getPoint() != null && unit.getPoint() == 1){
				//单元状态为已完成
				if (unit.getStatus() == null || unit.getStatus() != 2) {
					return false;
				}
			}
		}

		return true;
	}
	
	
	
	
	
	
	/**
	 * @author Tiger
	 * @time 2017-1-27
	 * @param unitId
	 * @return
	 * @Description : Get CVUnit by unit id.
	 */
	@Override
	public CVUnit getCVUnitByUnitId(Long unitId)
	{
		return localCVSetEntityDAO.getCVUnitByUnitId(unitId);
	}
	@Override
	public Long getPaperIdByUnitId(Long unitId) {
		
		
		return localCVSetEntityDAO.getPaperIdByUnitId(unitId);
	}
	@Override
	public Long getCurrentSystemUserIDByName(String assignName) {
		
		return localCVSetEntityDAO.getCurrentSystemUserIDByName(assignName);
	}
	@Override
	public void setLogStudyCVUnitExam(LogStudy logStudy) {
		localCVSetEntityDAO.setLogStudyCVUnitExam(logStudy);
	}
	
	/**
	 * @param   Long
	 * @return  ExpertInfo
	 * @author  张建国
	 * @time    2017-02-15
	 * 方法说明：根据课程id查询课程主讲教师
	 */
	public ExpertInfo queryTeacherByCVId(Long cvId){
		return localCVSetEntityDAO.queryTeacherByCVId(cvId);
	}
	
	/**
	 * @author 张建国
	 * @time   2017-02-17
	 * @return CVSet 
	 * @param  Long
	 * 方法说明  根据课程Id查询项目信息
	 */
	public CVSet queryCVSetListByCvId(Long cvId){
		return localCVSetEntityDAO.queryCVSetListByCvId(cvId);
	}
	
	/**
	 * @auth   scp
	 * @time   2016-12-29
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id查询单元信息
	 */
	@Override
	public List<GroupClassInfo> queryGroupClassInfo(Long classId){
		return localCVSetEntityDAO.queryGroupClassInfo(classId);
	}
	
	/**
	 * 根据用户的省份id和项目id，查询项目和省份关联表中数据，如果能查到记录，则该项目开放的省份和该用户所在省份是一致的
	 * @param cvId
	 * @param userProviceId
	 * @return
	 */
	@Override
	public List getCVId(long cvId, long userProviceId) {
		return localCVSetEntityDAO.getCVId(cvId,userProviceId);
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-22
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id和userId查询单元信息
	 */
	public List<CVUnit> queryUnitListByClassIdAndUserId(Long classId, Long userId) {
		return localCVSetEntityDAO.queryUnitListByClassIdAndUserId(classId, userId) ;
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * @return List 
	 * @param  Long
	 * 方法说明  根据课程Id和userId查询单元的学习记录信息
	 */
	public List<CVUnit> queryUnitLogStudyByCvSetIdAndUserId(Long cvSetId, Long userId) {
		return localCVSetEntityDAO.queryUnitLogStudyByCvSetIdAndUserId(cvSetId, userId) ;
	}
	//区分开直播课程中有直播单元和回放单元的情况
	public List<CVUnit> queryUnitLogStudyByCvSetIdAndUserIdForLive(Long cvSetId, Long userId) {
		return localCVSetEntityDAO.queryUnitLogStudyByCvSetIdAndUserIdForLive(cvSetId, userId) ;
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * @return List 	  
	 * 方法说明  根据单元Id和userId查询单元的学习记录信息、单元信息
	 */
	public CVUnit queryCVUnitLogStudyByUnitIdAndUserId(Long unitId, Long userId) {
		return localCVSetEntityDAO.queryCVUnitLogStudyByUnitIdAndUserId(unitId, userId) ;
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-29
	 * @return List 
	 * 方法说明  根据课程Id和userId查询单元的学习记录信息
	 */
	public List<CVUnit> queryUnitLogStudyByCvIdAndUserId(Long cvId, Long userId) {
		return localCVSetEntityDAO.queryUnitLogStudyByCvIdAndUserId(cvId, userId) ;
	}
	@Override
	public int findCvSetId(String setId) {
		
		return localCVSetEntityDAO.findCvSetId(setId);
	}
	
}
