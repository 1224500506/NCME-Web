package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.qiantai.model.CvDiplomaEntity;

public interface CvDiplomaDao {
	/**
	 * 
	 * 保存數據
	* @Title: addCvDiploma 
	* @Description: TODO(保存證書表)
	* @author 程宏业 
	* @date 2017-2-18下午1:03:47 
	* @param @param cvDiplomaEntity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addCvDiploma(CvDiplomaEntity cvDiplomaEntity);
	
	
	/**
	 * 
	* @Title: findListByItemNumber 
	* @Description: TODO(通過項目編號查找list)
	* @author 程宏业 
	* @date 2017-2-18下午1:14:18 
	* @param @return    设定文件 
	* @return List<CvDiplomaEntity>    返回类型 
	* @throws
	 */
	public List<CvDiplomaEntity> findListByItemNumber(CvDiplomaEntity cvDiplomaEntity);
	
	
	
	/**
	 * 
	 * 查询证书列表通过用户ID,时间区间
	 * 方法名：findListByUidTime
	 * 创建人：程宏业
	 * 时间：2017-3-1-上午11:56:50 
	 * 手机:15210211487
	 * @param cvDiplomaEntity
	 * @return List<CvDiplomaEntity>
	 * @exception 
	 * @since  1.0.0
	 */
	
	public List<CvDiplomaEntity> findListByUidTime(CvDiplomaEntity cvDiplomaEntity);
	
	
	
    
	
	
	
	

}

