package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.BannerManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.service.local.BannerManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class BannerManageImpl extends BaseMangerImpl implements BannerManage {

	private BannerManageDAO bannerManageDAO;
	
	public BannerManageDAO getBannerManageDAO() {
		return bannerManageDAO;
	}

	public void setBannerManageDAO(BannerManageDAO bannerManageDAO) {
		this.bannerManageDAO = bannerManageDAO;
	}

	@Override
	public List<Advert> bannerList(Advert adv) {
		return bannerManageDAO.bannerList(adv);
	}

	@Override
	public Advert getAdvertById(Long id) {
		// TODO Auto-generated method stub
		return bannerManageDAO.getAdvertById(id);
	}

}
