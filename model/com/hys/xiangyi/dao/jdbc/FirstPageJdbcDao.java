package com.hys.xiangyi.dao.jdbc;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.alibaba.druid.sql.visitor.functions.If;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.XiangYiProvince;
import com.hys.exam.util.NcmeProperties;
import com.hys.xiangyi.dao.FirstPageDao;


public class FirstPageJdbcDao extends BaseDao implements FirstPageDao {
	
	@Override
	public List<XiangYiProvince> getProvinceCode(Long code) {
		String sqlString = "select x.PROVINCE_CODE,x.PROVINCE_NAME from xiangyi_province x " +
				"left JOIN system_user_config s ON x.PROVINCE_CODE = s.user_province_id where s.user_id="+code;
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
	}

	@Override
	public List<XiangYiProvince> getProvinceByCode(Long code) {
		String sqlString = "SELECT x.PROVINCE_CODE, x.PROVINCE_NAME FROM xiangyi_province x " +
				"LEFT JOIN system_user_config s ON x.PROVINCE_CODE = s.user_province_id WHERE s.user_id = "+code;
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
	}

	@Override
	public List<XiangYiProvince> getProvinceAll() {
		String sqlString = "select x.PROVINCE_CODE,x.PROVINCE_NAME from xiangyi_province x ORDER BY x.ORDER_NUMBER";
		List<XiangYiProvince> list = getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
		return list;
	}

	@Override
	public List<Sentence> getFirstPageInformation() {
		String sqlString = "select c.* from content_sentence_province p LEFT JOIN content_sentence c on c.ID=p.SENTENCE_ID "+
				"where c.TYPE=3 and p.PROVINCE_ID=111111111 ORDER BY c.DELI_DATE DESC";
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class));
	}

	@Override
	public List<Sentence> getEveryProvinceInformationByCode(Long code) {
		String sqlString = "select c.*,p.PROVINCE_ID as provinceCode, x.PROVINCE_NAME AS provinceName " +
				"from content_sentence_province p " +
				"LEFT JOIN content_sentence c ON c.ID=p.SENTENCE_ID " +
				"LEFT JOIN xiangyi_province x on p.PROVINCE_ID = x.province_code " +
				"where c.TYPE=3 and c.STATE = 2";
		
		if(code!=111111111){
			sqlString +=" and (p.PROVINCE_ID="+code+" or p.PROVINCE_ID = 111111111 )";
		}
				
		sqlString += " ORDER BY c.ORDERNUM , c.DELI_DATE DESC";
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class));
	}

	@Override
	public List<XiangYiProvince> getEveryProvinceNameByCode(Long code) {
		String sqlString = "SELECT x.* from xiangyi_province x where x.PROVINCE_CODE="+code;
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
	}

	@Override
	public List<Sentence> getDynamicNoticeById(Long id) {
		String sqlString = "select * from content_sentence where id="+id;
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class));
	}

	@Override
	public List<CVSet> getCVSetAll(CVSet cvSet,Long code) {
		String sqlString = "select t.*, t7.grade, t7.study_times from cv_set t " +
									  "join cv_set_system_site t5 on t.id=t5.cv_set_id " +
									  "join system_site t6 on t5.system_site_id=t6.id " +
									  "left join cv_set_score t7 on t7.cv_set_id=t.id " +
									  "inner JOIN cv_region c on c.CV_SET_ID = t.ID " +
							"where t6.domain_name='"+cvSet.getServerName()+"' AND (t.FORMA = 1 or t.FORMA = 2) and t.status="+cvSet.getStatus()+" and t.cv_set_type=1 and c.REGION_ID=" + code +
							" and exists (select 1 from cv_set_user_image t2 where t2.cv_set_id=t.id) order by t.id  desc";
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
	}
	
	@Override
	public List<CV> getCVAll(String servserName,Long code) {
		String sql = " SELECT A.* FROM( SELECT t2.*, live.start_date, live.invalid_date AS end_date , (CASE " +
        		"WHEN live.start_date <= NOW() AND live.invalid_date >= NOW() THEN 1 " +
        		"WHEN live.start_date > NOW() THEN 2 " +
        		"WHEN live.invalid_date < NOW() THEN 3 END) AS type " +
				"FROM cv t2, cv_live live, cv_schedule t3 " +
				"WHERE t2.id = t3.cv_id AND t2.cv_type = 2 " +
				"AND EXISTS ( SELECT 1 FROM cv_set_schedule t4 " +
				"JOIN cv_set t5 ON t4.cv_set_id = t5.id " +
				"JOIN cv_region c ON c.CV_SET_ID = t5.id " +
				"JOIN cv_set_system_site t6 ON t6.cv_set_id = t5.id " +
				"JOIN system_site t7 ON t6.system_site_id = t7.id " +
				"JOIN cv_set_user_image t8 ON t5.id = t8.cv_set_id " +
				"WHERE t3.schedule_id = t4.cv_schedule_id " +
				"AND c.REGION_ID = " + code +
				" AND t5.cv_set_type = 1 " +
				" AND t7.domain_name = '"+servserName+"' " +
				" AND t5. STATUS = 5 AND t2.id = live.cv_id ) ) A LEFT JOIN cv_live_type_sort t9 ON t9.type = A.type ORDER BY t9.id ASC, A.start_date DESC ";
		List<CV> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		/*if(list != null && list.size() > 0){
        	for(CV cv : list){
        		Date now = new Date();
        		Date startDate = cv.getStartDate();
        		Date endDate = cv.getEndDate();
        		if(startDate.getTime() > now.getTime()){
        			cv.setType(2);
        		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
        			cv.setType(1);
        		}else if(endDate.getTime() < now.getTime()){
        			cv.setType(3);
        		}
        	}
        }*/
		return list;
	}
	
	@Override
	public List<CVSet> getFirstPageCVSetAll(){
		String num = NcmeProperties.getContextPropertyValue("provinceNum");
		String sqlString = "select c.*,s.grade, s.study_times from cv_set as c INNER JOIN "+
						"(select CV_SET_ID,count(*) as num from cv_region GROUP BY CV_SET_ID) as c1 "+
						"on c.ID=c1.CV_SET_ID LEFT JOIN cv_set_score as s ON s.cv_set_id=c.id "+
						"where  c1.num > "+num+" and c.cv_set_type=1 and c.STATUS=5";
		return getJdbcTemplate().query(sqlString, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));
	}
}
