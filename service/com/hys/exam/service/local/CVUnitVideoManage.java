package com.hys.exam.service.local;

import java.util.List;

import com.hys.framework.service.BaseService;
import com.hys.qiantai.model.LogStudyCVUnitVideo;

/**
 * 
 * 
 * CVUnitVideoManage
 * 创建人:程宏业 时间：2017-3-10-下午1:13:44 
 * @version 1.0.0
 *
 */
public interface CVUnitVideoManage extends BaseService {

/**
 * 	
 * 通过用户ID和单元ID查询单元学习耗时
 * 方法名：findListByUserId
 * 创建人：程宏业
 * 时间：2017-3-10-下午1:15:04 
 * 手机:15210211487
 * @param video
 * @return List<LogStudyCVUnitVideo>
 * @exception 
 * @since  1.0.0
 */
 public List<LogStudyCVUnitVideo> findListByUserId(LogStudyCVUnitVideo video);
	
	
}
