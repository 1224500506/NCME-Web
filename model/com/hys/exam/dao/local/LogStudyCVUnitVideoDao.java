package com.hys.exam.dao.local;

import java.util.List;
import com.hys.qiantai.model.LogStudyCVUnitVideo;

/**
 * 
 * 
 * LogStudyCVUnitVide
 * 创建人:程宏业 时间：2017-3-10-上午11:31:36 
 * @version 1.0.0
 *
 */
public interface LogStudyCVUnitVideoDao {
	
	/**
	 * 
	 * 通过用户ID和单元ID查询单元学习耗时
	 * 方法名：findListByUserId
	 * 创建人：程宏业
	 * 时间：2017-3-10-上午11:36:13 
	 * 手机:15210211487
	 * @param video
	 * @return List<LogStudyCVUnitVideo>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<LogStudyCVUnitVideo> findListByUserId(LogStudyCVUnitVideo video);

}
