package com.hys.xiangyi.service.impl;

import java.util.List;

import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.xiangyi.dao.CVSetScheduleFaceTeachDao;
import com.hys.xiangyi.service.CVSetScheduleFaceTeachService;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-07
 * 
 * 描述：面授课程ServiceImpl
 * 
 * 说明:
 */
public class CVSetScheduleFaceTeachServiceImpl implements CVSetScheduleFaceTeachService {
	
	private CVSetScheduleFaceTeachDao cvSetScheduleFaceTeachDao;

	public CVSetScheduleFaceTeachDao getCvSetScheduleFaceTeachDao() {
		return cvSetScheduleFaceTeachDao;
	}

	public void setCvSetScheduleFaceTeachDao(CVSetScheduleFaceTeachDao cvSetScheduleFaceTeachDao) {
		this.cvSetScheduleFaceTeachDao = cvSetScheduleFaceTeachDao;
	}


	/**
	 * 根据项目类型和所在省份查询面授课程
	 */
	@Override
	public List<CVSetScheduleFaceTeach> getCVSetScheduleFaceTeachListByCondition(Integer cvSetType,
			Integer provinceCode) {
		
		return cvSetScheduleFaceTeachDao.getCVSetScheduleFaceTeachListByCondition(cvSetType, provinceCode);
	}

}
