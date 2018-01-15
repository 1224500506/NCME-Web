package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.BannerManageDAO;
import com.hys.exam.model.Advert;
import com.hys.exam.model.ExpertInfo;

public class BannerManageJDBCDAO extends BaseDao implements BannerManageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Advert> bannerList(Advert adv) {
		StringBuffer sql = new StringBuffer();

		sql.append("select * from advert where 1=1");
		List list = new ArrayList();
		if(null != adv.getState() && 0 != adv.getState()){
			sql.append(" and state = ? ");
			list.add(adv.getState());
		}
		sql.append(" and type = 1 ");
		sql.append(" and state = 2 ");
		sql.append("order by CREATEDATE desc limit 0,5 ");
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(Advert.class));
	}

	@Override
	public Advert getAdvertById(Long id) {
		Advert advert = new Advert();
		String sql = "select * from advert where ID=" + id;
		advert = getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Advert.class));
		return advert;
	}

}
