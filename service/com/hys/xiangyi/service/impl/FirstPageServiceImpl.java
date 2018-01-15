package com.hys.xiangyi.service.impl;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.XiangYiProvince;
import com.hys.xiangyi.dao.FirstPageDao;
import com.hys.xiangyi.service.FirstPageService;

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
public class FirstPageServiceImpl implements FirstPageService {
	
	private FirstPageDao firstPageDao;

	public FirstPageDao getFirstPageDao() {
		return firstPageDao;
	}

	public void setFirstPageDao(FirstPageDao firstPageDao) {
		this.firstPageDao = firstPageDao;
	}

	@Override
	public List<XiangYiProvince> getProvinceByCode(Long code) {
		
		return firstPageDao.getProvinceByCode(code);
	}

	@Override
	public List<XiangYiProvince> getProvinceAll() {
		return firstPageDao.getProvinceAll();
	}

	@Override
	public List<Sentence> getFirstPageInformation() {
		return firstPageDao.getFirstPageInformation();
	}

	@Override
	public List<Sentence> getEveryProvinceInformationByCode(Long code) {
		return firstPageDao.getEveryProvinceInformationByCode(code);
	}

	@Override
	public String getEveryProvinceNameByCode(Long code) {
		List<XiangYiProvince> list = firstPageDao.getEveryProvinceNameByCode(code);
		String nameString = list.get(0).getProvinceName();
		return nameString;
	}

	@Override
	public Sentence getDynamicNoticeById(Long id) {
		List<Sentence> list = firstPageDao.getDynamicNoticeById(id);
		return list.get(0);
	}

	@Override
	public List<CVSet> getCVSetAll(CVSet cvSet,Long code) {
		return firstPageDao.getCVSetAll(cvSet,code);
	}

	@Override
	public List<CV> getCVAll(String servserName, Long code) {
		return firstPageDao.getCVAll(servserName,code);
	}

	@Override
	public List<CVSet> getFirstPageCVSetAll() {
		return firstPageDao.getFirstPageCVSetAll();
	}

	@Override
	public List<XiangYiProvince> getProvinceCode(Long code) {
		return firstPageDao.getProvinceCode(code);
	}
	
	
}