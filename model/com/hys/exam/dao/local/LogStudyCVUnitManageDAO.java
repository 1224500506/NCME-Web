package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.LogStudyCVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
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
 *     File Name         :   LogStudyCVUnitManageDAO.java
 *     
 *     Description       :   学习记录(单元)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-16                                   张建国	               Created
 ********************************************************************************
 */

public interface LogStudyCVUnitManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(单元)
	 */
	void addLogStudyCVUnit(LogStudyCVUnit log);
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记录是否存在
	 */
    List<LogStudyCVUnit> queryLogStudyCVUnitByUnitId(LogStudyCVUnit cvs);
    
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记修改单元学习记录
	 */
    public void updLogStudyCVUnit(Long unitId, Long userId);
    
    public boolean saveLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog);//保存学习记录的视频记录，YHQ，2017-03-09
    
    public boolean saveLogStudyCVUnitVideoCensus(LogStudyCVUnitVideoCensus videoLog);//保存视频实际学习时间记录，YXT，2017-07-13
    
    public LogStudyCVUnitVideo queryLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog);//获取个人学习记录的视频记录，YHQ，2017-03-10
    
    /**
     * 
     * 
     * 方法名：updLogStudyCVUnitById
     * 创建人：程宏业
     * 时间：2017-3-11-下午2:31:16 
     * 手机:15210211487
     * @param cvs void
     * @exception 
     * @since  1.0.0
     */
    public void updLogStudyCVUnitById(LogStudyCVUnit cvs);
    
    /**
     * 根据用户ID和单元ID更新单元学习记录的状态
     * @param cvs
     * @return
     */
    public boolean updLogStudyCVUnitByUserAndCUId(LogStudyCVUnit cvs);
    
    /**
     * 根据用户ID和单元ID更新视频学习记录的状态
     * @param cvs
     * @return
     */
    public boolean updLogStudyCVUnitVideoByUserAndCUId(LogStudyCVUnitVideo cvs);
    
    /**
     * 根据项目id和用户id查询该课程的学习记录
     * @param videoLog
     * @return
     */
    public List<LogStudyCVUnit> queryLogStudyCVUnitByCVId(LogStudyCVUnit cvStudyLog);
    
    /**
     * @author taoliang
     * @param videoLog
     * @desc add LogStudyCVUnitVideo 
     */
    public boolean addLogStudyCVUnitVideo(LogStudyCVUnitVideo videoLog);
    
    /**
     * @author taoliang
     * @param classNo
     * @param userId
     * @desc getVideoLog
     */
    public LogStudyCVUnitVideo getVideoLog(String classNo, Long userId, Long cvLiveStudyId);
    
    /**
     * @author taoliang
     * @param cvStudyLog
     * @return
     * @desc 筛选出直播生成单元中未学习单元
     */
    public List<LogStudyCVUnit> queryLogStudyCVUnitForLiveFilter(LogStudyCVUnit cvStudyLog);

    /**
     * @author taoliang
     * @param cvId
     * @return
     * @desc 根据课程拿项目信息
     */
    public CVSet getCVSetByCVId(Long cvId);
    
    /**
     * @author taoliang
     * @param cv
     * @return
     * @desc 处理直播和点播中课程学习记录
     */
    public boolean updateCVForLogStudy(CV cv, SystemUser user);
    
    /**
     * @author taoliang
     * @param cv
     * @return
     * @desc 处理直播和点播中项目学习记录
     */
    public boolean updateCVSetForLogStudy(CV cv, SystemUser user);
    /**
     *sunny
     *保存面授报名 
     * @param logStudy
     */
	void saveLogStudy(LogStudyCVSet logStudy);
}
