package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.Advert;
import com.hys.framework.service.BaseService;

public interface BannerManage extends BaseService{
	
	List<Advert> bannerList(Advert adv);

	Advert getAdvertById(Long id);

}
