package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExamSource;

public interface CVUnitManageDAO {
	
	/**
	 * 
	 * 查询课程单元
	 * 方法名：findCvunit
	 * 创建人：程宏业
	 * 时间：2017-3-6-下午3:38:20 
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
	 * 2017年12月29日 上午11:07:44
	 * @Description: 根据单元ID查找数据
	 * @param unitId
	 * @return ExamSource
	 */
	public List<ExamSource> getSourceVal(Long unitId);

	/**
	 * @author 王印涛
	 * 2017年12月29日 下午5:11:40
	 * @Description: 添加扩展阅读读后感
	 * @param content
	 * @param cvUnitId void
	 */
	public void addContent(String content, Long cvUnitId);	
}
