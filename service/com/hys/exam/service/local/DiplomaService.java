package com.hys.exam.service.local;

import java.util.List;

import com.hys.framework.service.BaseService;
import com.hys.qiantai.model.CvDiplomaEntity;

public interface DiplomaService extends BaseService {

	/**
	 * 
	* @Title: addDiplomaEntity 
	* @Description: TODO(添加证书)
	* @author 程宏业 
	* @date 2017-2-18下午1:37:03 
	* @param @param cvDiplomaEntity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addDiplomaEntity(CvDiplomaEntity cvDiplomaEntity);
	
	/**
	 * 
	* @Title: findDiplomaEntityByItemNumber 
	* @Description: TODO(查询证书通过项目编号)
	* @author 程宏业 
	* @date 2017-2-18下午1:38:34 
	* @param @return    设定文件 
	* @return List<CvDiplomaEntity>    返回类型 
	* @throws
	 */
	
	public List<CvDiplomaEntity> findListByItemNumber(CvDiplomaEntity cvDiplomaEntity);
	
	
	
	/***
	 * 
	 * 通过用户ID和时间查询证书列表
	 * 方法名：findListByUidTime
	 * 创建人：程宏业
	 * 时间：2017-3-1-下午12:44:37 
	 * 手机:15210211487
	 * @param cvDiplomaEntity
	 * @return List<CvDiplomaEntity>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<CvDiplomaEntity> findListByUidTime(CvDiplomaEntity cvDiplomaEntity);
	
	
	

}
