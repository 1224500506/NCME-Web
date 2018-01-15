package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.QualityModelManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class QualityModelManageImpl extends BaseMangerImpl implements
		QualityModelManage {
	
	private QualityModelManageDAO localQualityModelManageDAO;
	
	public QualityModelManageDAO getLocalQualityModelManageDAO() {
		return localQualityModelManageDAO;
	}

	public void setLocalQualityModelManageDAO(QualityModelManageDAO localQualityModelManageDAO) {
		this.localQualityModelManageDAO = localQualityModelManageDAO;
	}

	@Override
	public List<QualityModel> getQualityModelList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getQualityModelList(qualityModel);
	}
	
	@Override
	public List<QualityModel> getNextQualityModelList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getNextQualityModelList(qualityModel);
	}

	@Override
	public boolean addQualityModel(QualityModel qualityModel) {
		return localQualityModelManageDAO.addQualityModel(qualityModel);
	}

	@Override
	public boolean deleteQualityModel(QualityModel qualityModel) {
		return localQualityModelManageDAO.deleteQualityModel(qualityModel);
	}

	@Override
	public boolean updateQualityModel(QualityModel qualityModel) {
		return localQualityModelManageDAO.updateQualityModel(qualityModel);
	}


	@Override
	public List<PropUnit> getZutiListByType() {
		return  localQualityModelManageDAO.getZutiListByType();
	
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-18
	 * 胜任力大圆圈里的内容，YHQ 2017-02-18
	 */
	@Override
	public List<QualityModel> getAbilityLevelOneList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getAbilityLevelOneList(qualityModel) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-18
	 * 胜任力大圆圈下级的内容，YHQ 2017-02-18
	 */
	@Override
	public List<PropUnit> getAbilityLevelTwoList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getAbilityLevelTwoList(qualityModel) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-21
	 * 胜任力大圆圈下1级关联的项目，YHQ 2017-02-21
	 */
	@Override
	public 	List<CVSet> getAbilityLevelTwoProjectList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getAbilityLevelTwoProjectList(qualityModel) ;
	}
	
	@Override
	public String getXunKeSql(Long propId){
		return localQualityModelManageDAO.getXunKeSql(propId);
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-22
	 * 年度学习报告中胜任力大圆圈里的内容，YHQ 2017-02-22
	 */
	@Override
	public 	List<QualityModel> getStudyYearReportAbilityList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getStudyYearReportAbilityList(qualityModel) ;
	}

}
