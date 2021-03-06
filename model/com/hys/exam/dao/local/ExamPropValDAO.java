package com.hys.exam.dao.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamMajorOrder;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamRelationProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.framework.exception.FrameworkRuntimeException;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPropValDAO {
	
	/**
	 * 无关联属性值
	 * @return
	 */
	public Map<String,List<ExamPropVal>> getSysPropValBySysId();
	
	/**
	 * 系统基本属性
	 * @param type
	 * @return
	 */
	public List<ExamPropValTemp> getBasePropVal(Integer type);
	
	/**
	 * 关联属性导出
	 * @param type 0:基本ID,1:系统ID
	 * @return
	 */
	public List<ExamPropVal> getBaseRelPorp(int type);
	
	
	/**
	 * 一级学科属性
	 * @return
	 */
	public List<ExamProp> getPropListByType(ExamProp prop);
	public List<ExamProp> getNewPropListByType(ExamProp prop);
	/**
	 * 通过属性关系值取下级属性
	 * @param propId
	 * @return
	 */
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery);
	
	
	/**
	 * 通过属性值取下级属性
	 * @param propValId
	 * @return
	 */
	public List<ExamProp> getNextLevelProp(Long  propValId);

	
	/**
	 * 添加属性
	 * @param propVal
	 * @return
	 */
	public ExamProp addPropVal(ExamProp prop) throws Exception;
	
	/**
	 * 删除属性
	 * @param prop
	 */
	public boolean deletePropVal(ExamProp prop);
	
	/**
	 * 修改属性
	 * @param prop
	 * @throws Exception 
	 */
	public void updatePropVal(ExamProp prop) throws Exception;
	
	/**
	 * 添加系统属性
	 * @param propVal
	 */
	public ExamProp addSysPropVal(ExamProp prop);
	
	/**
	 * 增加属性关系
	 */
	public void addRel(ExamProp prop);
	
	/**
	 * 取属性值类别
	 * @return
	 */
	public List<ExamPropType> getExamPropTypeList();
	
	/**
	 * 取出所有关系属性列表及对应关系
	 * @return
	 */
	public List<ExamRelationProp> getExamRelationPropAll();
	
	/**
	 * 取系统属性值
	 * @param id
	 * @return
	 */
	public ExamProp getSysPropVal(Long id);
	
	/**
	 * 移动 属性
	 * @param 目标 target_id
	 * @param 要移动 current_id
	 */
	public void updateMoveSysPropVal(Long target_id,Long current_id);

	/**
	 * 查询Icd属性
	 * @param prop
	 * @return
	 */
	public List<ExamICD> getIcdListByType(ExamICD prop);

	/**
	 * 添加属性
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamICD addIcdVal(ExamICD prop) throws Exception;
	
	/**
	 * 删除属性
	 * @param prop
	 */
	public boolean deleteIcdVal(ExamICD prop);
	
	/**
	 * 修改属性
	 * @param prop
	 * @throws Exception 
	 */
	public void updateIcdVal(ExamICD prop) throws Exception;
	
	/**
	 * 取得来源类型目录
	 * @param prop
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop);
	
	/**
	 * 添加来源类型
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamSourceType addSourceType(ExamSourceType prop) throws Exception;
	
	
	/**
	 * 删除来源类型
	 * @param prop
	 */
	public boolean deleteSourceType(ExamSourceType prop);
	
	/**
	 * 修改来源类型
	 * @param prop
	 * @throws Exception 
	 */
	public void updateSourceType(ExamSourceType prop) throws Exception ;

	/**
	 * 取得来源目录
	 * @param prop
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamSource> getSourceValList(ExamSource prop);
	
	/**
	 * 添加来源
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamSource addSourceVal(ExamSource prop) throws Exception;
	
	
	/**
	 * 删除来源
	 * @param prop
	 */
	public boolean deleteSourceVal(ExamSource prop);
	
	/**
	 * 修改来源
	 * @param prop
	 */
	public boolean updateSourceVal(ExamSource prop);

	/**
	 * 取得医院列表
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalList(ExamHospital host);
	
	/**
	 * 取得医院列表scp
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalListAll(ExamHospital host);
	
	/**
	 * 取得每个区医院列表scp
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalDistrict(ExamHospital host);
	
	/**
	 * 根据id取得医院scp
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospital(Long id);
	
	/**
	 * 添加医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Long addHospital(ExamHospital host);
	
	/**
	 * 删除医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean deleteHospital(ExamHospital host);
	
	/**
	 * 修改医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean updateHospital(ExamHospital host);

	/**
	 * 取得专业排名列表
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major);
	
	/**
	 * 添加专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean addMajorOrder(ExamMajorOrder major);
	
	/**
	 * 删除专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean deleteMajorOrder(ExamMajorOrder major);
	
	/**
	 * 修改专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean updateMajorOrder(ExamMajorOrder major);

	public Long getParentPropId(Long id);

	public ExamReturnProp getNextLevelPropExam(ExamPropQuery propQuery);

}
