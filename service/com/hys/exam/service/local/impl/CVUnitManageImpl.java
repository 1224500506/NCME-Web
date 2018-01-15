package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVUnitManageDAO;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExamSource;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CVUnitManageImpl extends BaseMangerImpl implements CVUnitManage  {

	private CVUnitManageDAO cvUnitManageDAO;

    public CVUnitManageDAO getCvUnitManageDAO() {
        return cvUnitManageDAO;
    }

    public void setCvUnitManageDAO(CVUnitManageDAO cvUnitManageDAO) {
        this.cvUnitManageDAO = cvUnitManageDAO;
    }
	
        
	
	@Override
	public CVUnit findCvunit(CVUnit cvu) {
		// TODO Auto-generated method stub
		
		return cvUnitManageDAO.findCvunit(cvu);
	}

	@Override
	public CVUnit getUnitForLive(Long cvSetId) {
		return cvUnitManageDAO.getUnitForLive(cvSetId);
	}

	/**
	 * @author 王印涛
	 * 2017年12月29日 上午11:06:33
	 * @Description 根据单元ID查找书籍
	 */
	@Override
	public List<ExamSource> getSourceVal(Long unitId) {
	
		return cvUnitManageDAO.getSourceVal(unitId);
	}

	/**
	 * @author 王印涛
	 * 2017年12月29日 下午5:10:28
	 * @Description 添加扩展阅读读后感
	 */
	@Override
	public void addContent(String content, Long cvUnitId) {
		cvUnitManageDAO.addContent(content,cvUnitId);
		
	}


}
