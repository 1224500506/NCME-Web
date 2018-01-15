package com.hys.xiangyi.dao;

import java.util.List;

import com.hys.exam.model.CVSetScheduleFaceTeach;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-07
 * 
 * 描述：面授课程dao
 * 
 * 说明:
 */
public interface CVSetScheduleFaceTeachDao {
	
	//根据项目类型和省份查询面授课程
	public List<CVSetScheduleFaceTeach> getCVSetScheduleFaceTeachListByCondition(Integer cvSetType,Integer provinceCode);
}
