package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.service.local.ExamExaminationManage;
import com.hys.exam.sessionfacade.local.ExamExaminationFacade;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminationFacadeImpl extends BaseSessionFacadeImpl implements
		ExamExaminationFacade {
	
	
	private ExamExaminationManage localExamExaminationManage;

	public ExamExaminationManage getLocalExamExaminationManage() {
		return localExamExaminationManage;
	}

	public void setLocalExamExaminationManage(
			ExamExaminationManage localExamExaminationManage) {
		this.localExamExaminationManage = localExamExaminationManage;
	}

	public Long addExamination(ExamExamination exam)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminationManage.addExamination(exam);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("新增考试出现异常!",e);
		}
	}

	@Override
	public void deleteExamination(List<Long> id)
			throws FrameworkRuntimeException {
		try{
			localExamExaminationManage.deleteExamination(id);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("删除考试出现异常!",e);
		}
	}

	@Override
	public ExamExamination getExamExaminationById(Long id)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminationManage.getExamExaminationById(id);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("获取考试出现异常!",e);
		}
	}

	@Override
	public List<ExamExamination> getExaminationList(
			ExamExaminationQuery examExaminationQuery)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminationManage.getExaminationList(examExaminationQuery);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!",e);
		}
	}

	@Override
	public int getExaminationListSize(ExamExaminationQuery examExaminationQuery)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminationManage.getExaminationListSize(examExaminationQuery);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!",e);
		}
	}
	
	
	@Override
	public void updateExaminationById(ExamExamination exam)
			throws FrameworkRuntimeException {
		try{
			localExamExaminationManage.updateExaminationById(exam);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("修改考试出现异常!",e);
		}
	}
	
	@Override
	public List<ExamExamination> getExaminationListByExamTypeId(Long examTypeId)
			throws FrameworkRuntimeException {
		return null;
	}

	@Override
	public List<ExamExamination> getExaminationListByIds(
			ExamExaminationQuery query) throws FrameworkRuntimeException {
		try{
			return localExamExaminationManage.getExaminationListByIds(query);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!",e);
		}
	}

	@Override
	public int getExaminationListByIdsSize(ExamExaminationQuery query)
			throws FrameworkRuntimeException {
		try{
			return localExamExaminationManage.getExaminationListByIdsSize(query);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!",e);
		}
	}

	@Override
	public void updateExaminationTypeByExamId(Long typeId, Long id)
			throws FrameworkRuntimeException {
		try{
			localExamExaminationManage.updateExaminationTypeByExamId(typeId, id);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("修改考试分类出现异常!",e);
		}
	}

	@Override
	public void deleteExaminationList(List<Long> id) {
		try{
			localExamExaminationManage.deleteExaminationList(id);
		}catch(Exception e){
			logger.error("",e);
			throw new FrameworkRuntimeException("删除考试出现异常!",e);
		}
	}
	
	//恢复
	@Override
	public void recoverExaminationList(List<Long> id){
		this.localExamExaminationManage.recoverExaminationList(id);
	}
}
