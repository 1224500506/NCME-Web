package com.hys.exam.sessionfacade.local.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hys.exam.service.local.ExamPropValManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

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
public class ExamPropValFacadeImpl extends BaseSessionFacadeImpl implements
		ExamPropValFacade {

	private ExamPropValManage localExamPropValManage;
	
	public ExamPropValManage getLocalExamPropValManage() {
		return localExamPropValManage;
	}
	public void setLocalExamPropValManage(ExamPropValManage localExamPropValManage) {
		this.localExamPropValManage = localExamPropValManage;
	}
	public Map<String, List<ExamPropVal>> getSysPropValBySysId()
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSysPropValBySysId();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamPropValTemp> getBasePropVal(Integer type)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getBasePropVal(type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamPropVal> getBaseRelPorp(int type) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getBaseRelPorp(type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getNextLevelProp(propQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public ExamReturnProp getNextLevelPropExam(ExamPropQuery propQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getNextLevelPropExam(propQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getPropListByType(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamProp> getNewPropListByType(ExamProp prop) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getNewPropListByType(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addPropVal(ExamProp prop) {
		boolean flag = false;
		try {
			localExamPropValManage.addPropVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
		
	}
	@SuppressWarnings("finally")
	public boolean deletePropVal(ExamProp prop) {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deletePropVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updatePropVal(ExamProp prop) {
		boolean flag = false;
		try {
			localExamPropValManage.updatePropVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@Override
	public List<ExamPropType> getExamPropTypeList()
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getExamPropTypeList();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public Map<String,ExamRelationProp> getExamRelationPropAll()
			throws FrameworkRuntimeException {
		try {
			Map<String,ExamRelationProp> m = new HashMap<String,ExamRelationProp>();
			List<ExamRelationProp> list = localExamPropValManage.getExamRelationPropAll();
			for(ExamRelationProp p : list){
				m.put(p.getPoint_prop_id().toString(), p);
			}
			return m;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public List<Long> getExamPropTypeList(int type)
			throws FrameworkRuntimeException {
		try {
			ExamProp prop = new ExamProp();
			prop.setType(type);
			List<Long> list = new ArrayList<Long>();
			List<ExamProp> propList = localExamPropValManage.getPropListByType(prop);
			
			for(ExamProp p : propList){
				list.add(p.getId());
			}
			
			
			return list;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public ExamProp getSysPropVal(Long id) throws FrameworkRuntimeException {
		ExamProp prop = null;
		try {
			prop = localExamPropValManage.getSysPropVal(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
		} finally {
			return prop;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateMoveSysPropVal(Long target_id,Long current_id,Long propId,Integer type)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updateMoveSysPropVal(target_id, current_id,propId,type);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updatReplaceQuestionPropVal(Long targetId, Long propId,
			Integer type) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updatReplaceQuestionPropVal(targetId, propId, type);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}		
	}
	
	@Override
	public List<ExamRelationProp> getExamRelationPropList()
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getExamRelationPropAll();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public List<ExamICD> getIcdListByType(ExamICD prop) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getIcdListByType(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@SuppressWarnings("finally")
	public boolean addIcdVal(ExamICD prop) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.addIcdVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean deleteIcdVal(ExamICD prop) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteIcdVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateIcdVal(ExamICD prop) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updateIcdVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	
	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSourceTypeList(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addSourceType(ExamSourceType prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.addSourceType(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteSourceType(ExamSourceType prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteSourceType(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateSourceType(ExamSourceType prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updateSourceType(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@Override
	public List<ExamSource> getSourceValList(ExamSource prop)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSourceValList(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addSourceVal(ExamSource prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.addSourceVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteSourceVal(ExamSource prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteSourceVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateSourceVal(ExamSource prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updateSourceVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public List<ExamHospital> getHospitalList(ExamHospital host)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getHospitalList(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public List<ExamHospital> getHospitalListAll(ExamHospital host)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getHospitalListAll(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@SuppressWarnings("finally")
	public List<ExamHospital> getHospitalDistrict(ExamHospital host)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getHospitalDistrict(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@SuppressWarnings("finally")
	public List<ExamHospital> getHospital(Long id)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getHospital(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@SuppressWarnings("finally")
	public Long addHospital(ExamHospital host)
			throws FrameworkRuntimeException {
		Long flag = 0L;
		try {
			flag = localExamPropValManage.addHospital(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = 0L;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteHospital(ExamHospital host)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteHospital(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateHospital(ExamHospital host)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updateHospital(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getMajorOrderList(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addMajorOrder(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.addMajorOrder(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteMajorOrder(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteMajorOrder(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateMajorOrder(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updateMajorOrder(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	public Long getParentPropId(Long id){
		return localExamPropValManage.getParentPropId(id);
	}

}
