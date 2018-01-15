package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.framework.service.BaseService;

public interface QualityModelManage extends BaseService {
	
	List<QualityModel> getQualityModelList(QualityModel qualityModel);
	
	List<QualityModel> getNextQualityModelList(QualityModel qualityModel);
	
	boolean addQualityModel(QualityModel qualityModel);
	
	boolean deleteQualityModel(QualityModel qualityModel);
	
	boolean updateQualityModel(QualityModel qualityModel);

	List<PropUnit> getZutiListByType();
	String getXunKeSql(Long propId);
	List<QualityModel> getAbilityLevelOneList(QualityModel qualityModel) ; //胜任力大圆圈里的内容，YHQ 2017-02-18
	List<PropUnit> getAbilityLevelTwoList(QualityModel qualityModel) ; //胜任力大圆圈下级的内容，YHQ 2017-02-18
	List<CVSet> getAbilityLevelTwoProjectList(QualityModel qualityModel) ; //胜任力大圆圈下1级关联的项目，YHQ 2017-02-21
	List<QualityModel> getStudyYearReportAbilityList(QualityModel qualityModel) ; //年度学习报告中胜任力大圆圈里的内容，YHQ 2017-02-22
}
