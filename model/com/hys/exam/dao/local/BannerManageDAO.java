package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Advert;

public interface BannerManageDAO {
	List<Advert> bannerList(Advert adv);

	Advert getAdvertById(Long id);

}
