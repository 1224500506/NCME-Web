package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVUnitManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSetUnitNote;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.PropUnit;


public class CVUnitManageJDBCDAO extends BaseDao implements CVUnitManageDAO{
    @Override
    public CVUnit findCvunit(CVUnit cvu) {
            // TODO Auto-generated method stub

            String sql = "select * from cv_unit WHERE id ="+cvu.getId()+"";

            List<CVUnit> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));

            if(list.size()>0){
                    return list.get(0);
            }

            return null;
    }

	@Override
	public CVUnit getUnitForLive(Long cvSetId) {
		CVUnit unit = null;
		String queryCVSql = "SELECT t2.cv_type, t2.ID FROM cv_schedule t JOIN cv_set_schedule t1 ON t.SCHEDULE_ID = t1.CV_SCHEDULE_ID AND t1.CV_SET_ID = "+cvSetId+" LEFT JOIN cv t2 ON t2.ID = t.CV_ID";
		List<CV> cvList = getJdbcTemplate().query(queryCVSql, ParameterizedBeanPropertyRowMapper.newInstance(CV.class));
		if(cvList.size() > 0){
			CV cv = cvList.get(0);
			if(cv.getId() != null){
				String sql = " SELECT MAX(unit_make_type) as unitMakeType FROM CV_UNIT WHERE ID IN ( "
		                +" SELECT UNIT_ID FROM CV_REF_UNIT WHERE CV_ID = "+cv.getId()
		             +" ) order by sequenceNum asc";
				List<CVUnit> unitlist = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
				int unitMakeType = 0;
				List<CVUnit> list = null;
				if(unitlist != null && unitlist.size() > 0){
					unitMakeType = unitlist.get(0).getUnitMakeType();
					sql = " SELECT * FROM CV_UNIT t WHERE ID IN ( "
			                +" SELECT UNIT_ID FROM CV_REF_UNIT WHERE CV_ID = "+cv.getId()
			             +" ) AND t.unit_make_type = "+unitMakeType+" order by sequenceNum asc";
					list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVUnit.class));
					
					if(list != null && list.size() > 0){
						unit = list.get(0);//当直播课程下有多个单元时，默认取第一个
						unit.setCvId(cv.getId());
					}
				}
			}
		}
		
		return unit;
	}

	/**
	 * @author 王印涛
	 * 2017年12月29日 上午11:09:23
	 * @Description 根据单元ID查找书籍
	 */
	@Override
	public List<ExamSource> getSourceVal(Long unitId) {
		String sql = "select s.* from cv_unit_ref_source as c left join exam_source_val as s ON c.source_id = s.id where c.unit_id =?" ;
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSource.class),unitId);
	}

	/**
	 * @author 王印涛
	 * 2017年12月29日 下午5:12:15
	 * @Description 添加扩展阅读读后感
	 */
	@Override
	public void addContent(String content, Long cvUnitId) {
		String sql = "UPDATE cv_unit SET content =? WHERE id = ?";
		getJdbcTemplate().update(sql, content,cvUnitId);
	}
}
