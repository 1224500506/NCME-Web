package com.hys.xiangyi.service;

import java.util.List;

import com.hys.exam.model.CVSetScheduleFaceTeach;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-07
 * 
 * 描述：面授课程jdbcService
 * 
 * 说明:
 */
public interface CVSetScheduleFaceTeachService {
	
	//根据项目类型和所在省份查询面授课程
	public List<CVSetScheduleFaceTeach> getCVSetScheduleFaceTeachListByCondition(Integer cvSetType,
			Integer provinceCode);
}
