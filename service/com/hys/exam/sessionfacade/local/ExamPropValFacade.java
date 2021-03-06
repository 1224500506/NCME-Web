package com.hys.exam.sessionfacade.local;

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
import com.hys.framework.sessionfacade.BaseSessionFacade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-22
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPropValFacade extends BaseSessionFacade {
	

	/**
	 * 无关联属性值
	 * @return
	 */
	public Map<String,List<ExamPropVal>> getSysPropValBySysId() throws FrameworkRuntimeException;
	
	/**
	 * 系统基本属性
	 * @param type
	 * @return
	 */
	public List<ExamPropValTemp> getBasePropVal(Integer type)throws FrameworkRuntimeException;
	
	
	/**
	 * 关联属性导出
	 * @param type 0:基本ID,1:系统ID
	 * @return
	 */
	public List<ExamPropVal> getBaseRelPorp(int type) throws FrameworkRuntimeException;
	
	
	
	/**
	 * 一级学科属性
	 * @return
	 */
	public List<ExamProp> getPropListByType(ExamProp prop) throws FrameworkRuntimeException;
	public List<ExamProp> getNewPropListByType(ExamProp prop) throws FrameworkRuntimeException;
	/**
	 * 下级属性
	 * @param propId
	 * @return
	 */
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery) throws FrameworkRuntimeException;
	
	/**
	 * 添加属性
	 * @param propVal
	 * @return
	 */
	public boolean addPropVal(ExamProp prop) throws FrameworkRuntimeException;
	
	
	/**
	 * 删除属性
	 * @param prop
	 */
	public boolean deletePropVal(ExamProp prop) throws FrameworkRuntimeException;
	
	/**
	 * 修改属性
	 * @param prop
	 */
	public boolean updatePropVal(ExamProp prop) throws FrameworkRuntimeException;
	
	
	/**
	 * 取属性值类别
	 * @return
	 */
	public List<ExamPropType> getExamPropTypeList() throws FrameworkRuntimeException;
	
	/**
	 * 取出所有关系属性列表及对应关系 
	 * 以知识点ID为key
	 * @return Map<String,ExamRelationProp>
	 */
	public Map<String,ExamRelationProp> getExamRelationPropAll() throws FrameworkRuntimeException;
	
	
	/**
	 * 取出所有关系属性列表及对应关系 
	 * @return List<ExamRelationProp>
	 */
	public List<ExamRelationProp> getExamRelationPropList() throws FrameworkRuntimeException;
	
	
	/**
	 * 通过类型取属性值ID
	 * @return
	 */
	public List<Long> getExamPropTypeList(int type) throws FrameworkRuntimeException;
	
	/**
	 * 取系统属性值
	 * @param id
	 * @return
	 */
	public ExamProp getSysPropVal(Long id) throws FrameworkRuntimeException;
	
	/**
	 * 移动 属性
	 * @param 目标 target_id
	 * @param 要移动 current_id
	 * @param 属性id
	 * @param 属性类别 type
	 */
	public boolean updateMoveSysPropVal(Long target_id,Long current_id,Long propId,Integer type) throws FrameworkRuntimeException;

	
	/**
	 * 移动试题属性
	 * @param 目标 target_id
	 * @param 属性id
	 * @param 属性类别
	 */
	public boolean updatReplaceQuestionPropVal(Long target_id,Long propId,Integer type) throws FrameworkRuntimeException;
	
	/**
	 * 取得Icd目录
	 * @param prop
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamICD> getIcdListByType(ExamICD prop) throws FrameworkRuntimeException;
	
	/**
	 * 添加Icd
	 * @param propVal
	 * @return
	 */
	public boolean addIcdVal(ExamICD prop) throws FrameworkRuntimeException;
	
	
	/**
	 * 删除Icd
	 * @param prop
	 */
	public boolean deleteIcdVal(ExamICD prop) throws FrameworkRuntimeException;
	
	/**
	 * 修改Icd
	 * @param prop
	 */
	public boolean updateIcdVal(ExamICD prop) throws FrameworkRuntimeException;

	/**
	 * 取得来源类型目录
	 * @param prop
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop) throws FrameworkRuntimeException;
	
	/**
	 * 添加来源类型
	 * @param propVal
	 * @return
	 */
	public boolean addSourceType(ExamSourceType prop) throws FrameworkRuntimeException;
	
	
	/**
	 * 删除来源类型
	 * @param prop
	 */
	public boolean deleteSourceType(ExamSourceType prop) throws FrameworkRuntimeException;
	
	/**
	 * 修改来源类型
	 * @param prop
	 */
	public boolean updateSourceType(ExamSourceType prop) throws FrameworkRuntimeException;

	/**
	 * 取得来源目录
	 * @param prop
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamSource> getSourceValList(ExamSource prop) throws FrameworkRuntimeException;
	
	/**
	 * 添加来源
	 * @param prop
	 * @return
	 */
	public boolean addSourceVal(ExamSource prop) throws FrameworkRuntimeException;
	
	
	/**
	 * 删除来源
	 * @param prop
	 */
	public boolean deleteSourceVal(ExamSource prop) throws FrameworkRuntimeException;
	
	/**
	 * 修改来源
	 * @param prop
	 */
	public boolean updateSourceVal(ExamSource prop) throws FrameworkRuntimeException;

	/**
	 * 取得医院列表
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalList(ExamHospital host) throws FrameworkRuntimeException;
	
	/**
	 * 取得医院列表scp
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalListAll(ExamHospital host) throws FrameworkRuntimeException;
	
	/**
	 * 取得每个区医院列表scp
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalDistrict(ExamHospital host) throws FrameworkRuntimeException;
	
	/**
	 * 根据id取得医院列表scp
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospital(Long id) throws FrameworkRuntimeException;
	
	/**
	 * 添加医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public Long addHospital(ExamHospital host) throws FrameworkRuntimeException;
	
	/**
	 * 删除医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean deleteHospital(ExamHospital host) throws FrameworkRuntimeException;
	
	/**
	 * 修改医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean updateHospital(ExamHospital host) throws FrameworkRuntimeException;
	
	/**
	 * 取得专业排名列表
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major) throws FrameworkRuntimeException;
	
	/**
	 * 添加专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean addMajorOrder(ExamMajorOrder major) throws FrameworkRuntimeException;
	
	/**
	 * 删除专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean deleteMajorOrder(ExamMajorOrder major) throws FrameworkRuntimeException;
	
	/**
	 * 修改专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean updateMajorOrder(ExamMajorOrder major) throws FrameworkRuntimeException;

	public Long getParentPropId(Long id);

	public ExamReturnProp getNextLevelPropExam(ExamPropQuery propQuery) throws FrameworkRuntimeException;
}
