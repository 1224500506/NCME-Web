package com.hys.xiangyi.dao;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.XiangYiProvince;


public interface FirstPageDao {
	/**
	 * 根据用户地区编号获得乡医地区
	 * @param code
	 * @return
	 */
	public List<XiangYiProvince> getProvinceByCode(Long code);
	
	/**
	 * 根据用户地区编号获得乡医地区编号
	 * @param code
	 * @return
	 */
	public List<XiangYiProvince> getProvinceCode(Long code);

	/**
	 * 获取全部的乡医地区
	 * @return
	 */
	public List<XiangYiProvince> getProvinceAll();
	
	
	/**
	 * 获取乡医首页动态资讯
	 * @return
	 */
	public List<Sentence> getFirstPageInformation();
	
	
	/**
	 * 根据用户地区编号获得各省动态资讯
	 * @param code
	 * @return
	 */
	public List<Sentence> getEveryProvinceInformationByCode(Long code);
	
	/**
	 * 根据用户地区编号获得各省名称
	 * @param code
	 * @return
	 */
	public List<XiangYiProvince> getEveryProvinceNameByCode(Long code);
	
	
	/**
	 * 根据id获得动态资讯内容
	 * @param id
	 * @return
	 */
	public List<Sentence> getDynamicNoticeById(Long id);
	
	
	/**
	 * 获取乡医首页全部项目
	 * @return
	 */
	public List<CVSet> getFirstPageCVSetAll();
	/**
	 * 获取乡医各省全部项目
	 * @return
	 */
	public List<CVSet> getCVSetAll(CVSet cvSet,Long code);
	
	
	/**
	 * 获取全部课程
	 * @return
	 */
	public List<CV> getCVAll(String servserName,Long code);
	
}
