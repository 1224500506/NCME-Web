package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveCoursewareStudy;
import com.hys.exam.model.CvLiveSignk;
import com.hys.exam.model.CvLiveStudy;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.framework.service.BaseService;

public interface CvLiveManage extends BaseService {
	
	/**
	 * @author taoliang
	 * @param cvId
	 * @return
	 * @desc 根据课程ID拿到相关联的直播信息
	 */
	List<CvLive> queryCvLiveList(Long cvId);
	
	
	/**
	 * @author zxw
	 * @param cvId
	 * @return
	 * @desc 根据课程ID拿到回放人数
	 */
	
	int cvLiveBackNumber(Long cvId);
	
	/**
	 * @author zxw
	 * @param cvId
	 * @return
	 * @desc 根据课程ID拿到项目
	 */
	
/*	CVSet queryNameCode(Long cvId);*/
	
	
	
	/**
	 * @author zxw
	 * @param cvId
	 * @return
	 * @desc 根据课程ID拿到直播结束观看人数
	 */
	int cvLiveOverNumber(Long cvId);
	
	/**
	 * @author taoliang
	 * @param cvls
	 * @return
	 */
	Long saveCvLiveSignk(CvLiveSignk cvls);
	/**
	 * 查询认证K值信息
	 * @param cv
	 * @return
	 */
	 List<CvLiveSignk> getCvLiveSignkList(Long userId, String signk);
	 
	 /**
	  * @author taoliang
	  * @param cvls
	  */
	 boolean delCvLiveSignk(CvLiveSignk cvls);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  * @desc 添加课堂参会记录
	  */
	 Long saveCvLiveStudy(CvLiveStudy record);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @desc 查询直播间中间表信息
	  */
	 List<CvLiveStudyRef> queryCvLiveStudyRef(CvLiveStudyRef record);
	 /**
	  * @author taoliang
	  * @param record
	  * @desc 修改直播间中间表
	  */
	 boolean updateCvLiveStudyRef(CvLiveStudyRef record);
/**
 * 
 * @param cvId
 * @return
 *  @desc 查找直播在线的人数
 */
	 int cvLiveNumber(Long cvId);
	 
	 /**
	  * @author taoliang
	  * @param cvId
	  * @desc 获取当前直播课程实时的直播状态  1 正在直播 2 即将开课 3 已经结束
	  */
	 int getcvZBTypeForInt(Long cvId);
	 
	 /**
	  * @author taoliang
	  * @param cvId
	  * @desc 获取当前直播课程结束状态  1.尚未完成转码  2.已添加回放 3.已完成直播课程学习
	  */
	 int getZBEndTypeForInt(Long cvId);
	 
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  * @desc 获取课件信息
	  */
	 List<CvLiveCourseware> getCvLiveCoursewareList(CvLiveCourseware record);
	 
	 /**
	  * @author taoliang
	  * @param cvId
	  * @return
	  * @desc 筛选直播课程单元
	  */
	 List<CVUnit> getCvUnitForLive(Long cvId);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @return
	  */
	 List<CvLiveRefUnit> getCvLiveRefUnitList(CvLiveRefUnit record);
	 /**
	  * @author yxt
	  * @param cvId
	  * @return
	  * @desc 根据课程ID查询类型为直播的单元信息
	  */
	 CVUnit getCvUnitByCvId(Long cvId);
	 /**
	  * @author yxt
	  * @param coursewareId
	  * @return
	  * @desc 根据课件ID查询课件总时长和课程ID
	  */
	 List<CvLiveCourseware> queryCvLiveCoursewareByCoursewareId(String coursewareId);
	 /**
	  * @author yxt
	  * @param coursewareId
	  * @return
	  * @desc 根据课件ID查询课件单元信息
	  */
	 public List<CvLiveRefUnit> queryCvLiveRefUnitByCoursewareId(String coursewareId);
	 /**
	  * @author yxt
	  * @param coursewareId, userID
	  * @return
	  * @desc 根据课程ID和userID拼接字符为条件查询课件观看信息
	  */
	 List<CvLiveCoursewareStudy> queryCvLiveCoursewareStudy(String coursewareIdUserID);
	 /**
	 * @author yxt
	 * @param coursewareId, uid
	 * @return
	 * @desc 根据课件ID,用户ID查询课件观看信息
	 */
	 List<CvLiveCoursewareStudy> queryCvLiveCoursewareStudy(String coursewareId, Long uid);
	 /**
	  * @author yxt
	  * @param record
	  * @desc 添加课件学习记录
	  */
	 boolean saveCvLiveCoursewareStudy(CvLiveCoursewareStudy record);
	 /**
	  * @author yxt
	  * @param record
	  * @desc 根据课程ID和userID拼接字符为条件修改课件学习记录
	  */
	 boolean updateCvLiveCoursewareStudy(String coursewareIdUserID, CvLiveCoursewareStudy record);
	 
	 /**
	  * @author taoliang
	  * @param record
	  * @desc 修改直播间中间表---维护直播试试观众人数
	  */
	 boolean updateCvLiveStudyRefForAudience(CvLiveStudyRef record);
    /**
     * 查询直播
     * @param id
     * @return
     */
	CvLive getCvLive(Long id);
}
