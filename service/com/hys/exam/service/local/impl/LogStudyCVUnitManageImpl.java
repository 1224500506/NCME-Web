package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.LogStudyCVUnitManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.qiantai.model.LogStudyCVUnitVideo;
import com.hys.qiantai.model.LogStudyCVUnitVideoCensus;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitManageImpl.java
 *     
 *     Description       :   学习记录(单元)Service实现类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-16                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVUnitManageImpl extends BaseMangerImpl implements LogStudyCVUnitManage{
	
	private LogStudyCVUnitManageDAO localLogStudyCVUnitDAO;

	public LogStudyCVUnitManageDAO getLocalLogStudyCVUnitDAO() {
		return localLogStudyCVUnitDAO;
	}

	public void setLocalLogStudyCVUnitDAO(LogStudyCVUnitManageDAO localLogStudyCVUnitDAO) {
		this.localLogStudyCVUnitDAO = localLogStudyCVUnitDAO;
	}

	/**
	 * @author   张建国
	 * @time     2017-01-16
	 * @param    LogStudyCVUnit
	 * @return   void
	 * 方法说明： 添加学习记录(单元)
	 */
	@Override
	public void addLogStudyCVUnit(LogStudyCVUnit log) {
		localLogStudyCVUnitDAO.addLogStudyCVUnit(log);
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记录是否存在
	 */
	public List<LogStudyCVUnit> queryLogStudyCVUnitByUnitId(LogStudyCVUnit cvs){
		return localLogStudyCVUnitDAO.queryLogStudyCVUnitByUnitId(cvs);
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记修改单元学习记录
	 */
    public void updLogStudyCVUnit(Long unitId, Long userId){
    	localLogStudyCVUnitDAO.updLogStudyCVUnit(unitId,userId);
    }
    
    /**
	 * @author   杨红强
	 * @param    LogStudyCVUnitVideo
	 * @return   boolean
	 * 方法说明： 保存学习记录的视频记录，YHQ，2017-03-09
	 */
    public boolean saveLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog) {
        return localLogStudyCVUnitDAO.saveLogStudyCVUnitVideo(videoLog) ;
    }
    
    /**
	 * @author   yxt
	 * @param    LogStudyCVUnitVideoCensus
	 * @return   boolean
	 * 方法说明： 保存视频实际学习时间记录，YXT，2017-07-13
	 */
    public boolean saveLogStudyCVUnitVideoCensus(LogStudyCVUnitVideoCensus videoLog) {
        return localLogStudyCVUnitDAO.saveLogStudyCVUnitVideoCensus(videoLog) ;
    }
    
    /**
	 * @author   杨红强
	 * @param    LogStudyCVUnitVideo
	 * @return   LogStudyCVUnitVideo
	 * 方法说明： 获取个人学习记录的视频记录，YHQ，2017-03-10
	 */
    public LogStudyCVUnitVideo queryLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog) {
        return localLogStudyCVUnitDAO.queryLogStudyCVUnitVideo(videoLog) ;
    }

	@Override
	public void updLogStudyCVUnitById(LogStudyCVUnit cvs) {
		// TODO Auto-generated method stub
		  localLogStudyCVUnitDAO.updLogStudyCVUnitById(cvs);
	}
	
	/**
     * 根据用户ID和单元ID更新单元学习记录的状态
     * @param videoLog
     * @return
     */
    public boolean updLogStudyCVUnitByUserAndCUId(LogStudyCVUnit cvs){
    	return localLogStudyCVUnitDAO.updLogStudyCVUnitByUserAndCUId(cvs);
    }
    
    /**
     * 根据用户ID和单元ID更新视频学习记录的状态
     * @param cvs
     * @return
     */
    public boolean updLogStudyCVUnitVideoByUserAndCUId(LogStudyCVUnitVideo cvs){
    	return localLogStudyCVUnitDAO.updLogStudyCVUnitVideoByUserAndCUId(cvs);
    }

	/**
	 * 根据项目id和用户id查询该课程的学习记录
	 * 
	 */
	@Override
	public List<LogStudyCVUnit> queryLogStudyCVUnitByCVId(LogStudyCVUnit cvStudyLog) {
		
		return localLogStudyCVUnitDAO.queryLogStudyCVUnitByCVId(cvStudyLog);
	}

	@Override
	public boolean addLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog) {
		return localLogStudyCVUnitDAO.addLogStudyCVUnitVideo(videoLog);
	}

	@Override
	public LogStudyCVUnitVideo getVideoLog(String classNo, Long userId, Long cvLiveStudyId) {
		return localLogStudyCVUnitDAO.getVideoLog(classNo, userId, cvLiveStudyId);
	}

	@Override
	public List<LogStudyCVUnit> queryLogStudyCVUnitForLiveFilter(
			LogStudyCVUnit cvStudyLog) {
		return localLogStudyCVUnitDAO.queryLogStudyCVUnitForLiveFilter(cvStudyLog);
	}

	@Override
	public CVSet getCVSetByCVId(Long cvId) {
		return localLogStudyCVUnitDAO.getCVSetByCVId(cvId);
	}

	@Override
	public boolean updateCVForLogStudy(CV cv, SystemUser user) {
		return localLogStudyCVUnitDAO.updateCVForLogStudy(cv, user);
	}

	@Override
	public boolean updateCVSetForLogStudy(CV cv, SystemUser user) {
		return localLogStudyCVUnitDAO.updateCVSetForLogStudy(cv, user);
	}
    /**
     * sunny
     * 保存面授报名
     */
	@Override
	public void saveLogStudy(LogStudyCVSet logStudy) {
		localLogStudyCVUnitDAO.saveLogStudy(logStudy);
	}
}
