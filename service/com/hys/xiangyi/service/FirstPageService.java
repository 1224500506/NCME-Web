package com.hys.xiangyi.service;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.XiangYiProvince;
import com.hys.exam.model.system.SystemExpress;
import com.hys.exam.model.system.SystemUserConfig;
import com.hys.exam.model.system.SystemUserPostHistory;
import com.hys.exam.model.system.SystemUserType;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：刘金明 2017-06-2
 * 
 * 描述：乡医地区
 * 
 * 说明:
 */
public interface FirstPageService {
	
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
	public String getEveryProvinceNameByCode(Long code);
	
	/**
	 * 根据id获得动态资讯内容
	 * @param id
	 * @return
	 */
	public Sentence getDynamicNoticeById(Long id);
	
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