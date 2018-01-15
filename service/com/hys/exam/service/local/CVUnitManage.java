package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExamSource;
import com.hys.framework.service.BaseService;

/**
 * 
 * 
 * CVUnitManage
 * 创建人:程宏业 时间：2017-3-6-下午3:46:16 
 * @version 1.0.0
 *
 */
public interface CVUnitManage extends BaseService {
	
/**
 * 	
 * 查询单元信息
 * 方法名：findCvunit
 * 创建人：程宏业
 * 时间：2017-3-6-下午3:47:15 
 * 手机:15210211487
 * @param cvu
 * @return CVUnit
 * @exception 
 * @since  1.0.0
 */
public CVUnit findCvunit(CVUnit cvu);
	
	
/**
 * @author taoliang	
 * @param cvSetId
 * @return
 * @desc 为直播项目获取其单元
 */
public CVUnit getUnitForLive(Long cvSetId);


/**
 * @author 王印涛
 * 2017年12月29日 上午10:56:34
 * @Description:根据单元查找书籍
 * @param unitId
 * @return
 */
public List<ExamSource> getSourceVal(Long unitId);


/**
 * @author 王印涛
 * 2017年12月29日 下午5:09:53
 * @Description:添加扩展阅读读后感
 * @param content
 * @param cvUnitId void
 */
public void addContent(String content, Long cvUnitId);


}
