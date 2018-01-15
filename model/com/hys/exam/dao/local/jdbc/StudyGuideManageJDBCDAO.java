package com.hys.exam.dao.local.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.StudyGuideManageDAO;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.MaterialProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.StringUtils;

public class StudyGuideManageJDBCDAO extends BaseDao implements
		StudyGuideManageDAO {

	private UserImageManageJDBCDAO userImageManageJDBCDAO;

	public UserImageManageJDBCDAO getUserImageManageJDBCDAO() {
		return userImageManageJDBCDAO;
	}

	public void setUserImageManageJDBCDAO(
			UserImageManageJDBCDAO userImageManageJDBCDAO) {
		this.userImageManageJDBCDAO = userImageManageJDBCDAO;
	}
	
	@Override
	public List<StudyGuide> getStudyGuideList(StudyGuide queryGuide) {
		
		String sql = "";
		List<StudyGuide> guideList = new ArrayList<StudyGuide>();
		
		if (queryGuide.getLevel() == 1L) {
			
			sql = "select *  from QM_GUIDE where LEVEL=1 ";

			List<StudyGuide> tmp_list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:tmp_list) {
			
				sql = "select USERIMAGE_ID from QM_GUIDE where ID=" + guide.getId();				
				Long image_id = getJdbcTemplate().queryForLong(sql);
				
				UserImage queryImage = new UserImage();
				queryImage.setId(image_id);
				if (guide.getUserImage() != null && !StringUtils.checkNull(guide.getUserImage().getName())) 
					queryImage.setName(guide.getUserImage().getName());
				
				List<UserImage> userImageList = userImageManageJDBCDAO.getUserImageList(queryImage);
				
				guide.setUserImage(userImageList.get(0));
				
				guideList.add(guide);
			}
			
			
		} else if (queryGuide.getLevel() == 4L) {
			
			sql = "select * from QM_GUIDE where PARENT_ID=";
			sql += queryGuide.getParent_id();
			sql += " and QUALITY_ID=";
			sql += queryGuide.getQuality().getId();
			if (queryGuide.getName() != null) sql += " and name like '%" + queryGuide.getName() + "%'";
			
			guideList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:guideList) {

				sql = "select ID, NAME from EXAM_ICD_PROP where ID in (select PROP_ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getId() + ")";
				List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				guide.setIcdPropList(icdPropList);
			}
			
			
		} else if (queryGuide.getLevel() == 5L) {
			
			if (queryGuide.getParent_id() == null)
				sql = "select * from QM_GUIDE where ID=" + queryGuide.getId();
			else 
				sql = "select * from QM_GUIDE where PARENT_ID=" + queryGuide.getParent_id();
			
			if (!StringUtils.checkNull(queryGuide.getName()))
				sql += " and NAME like '%" + queryGuide.getName() + "%'";
			
			guideList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			for (StudyGuide guide:guideList) {

				sql = "select ID, NAME from EXAM_ICD_PROP where ID in (select PROP_ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getId() + ")";
				List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				guide.setIcdPropList(icdPropList);
			}
		} else {}
		
		return guideList;
	}

	@Override
	public boolean addStudyGuide(StudyGuide guide) {		
		
		Long id = this.getNextId("QM_GUIDE");
		guide.setId(id);
		String sql = "insert into QM_GUIDE (ID, NAME, USERIMAGE_ID, QUALITY_ID, LEVEL, PARENT_ID) values (?, ?, ?, ?, ?, ?)";
		
		if (guide.getUserImage() != null && guide.getQuality() != null)
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), guide.getUserImage().getId(), guide.getQuality().getId(), guide.getLevel(), guide.getParent_id());
		else if (guide.getUserImage() != null) 
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), guide.getUserImage().getId(), null, guide.getLevel(), guide.getParent_id());
		else if (guide.getQuality() != null)
			getJdbcTemplate().update(sql, guide.getId(), guide.getName(), null, guide.getQuality().getId(), guide.getLevel(), guide.getParent_id());
		
		if(guide.getParent_id() > 0){
			sql = "select USERIMAGE_ID from QM_GUIDE where ID=" + guide.getParent_id();
			Long image_id = getJdbcTemplate().queryForLong(sql); 
			
			if (image_id != null && image_id > 0)
				sql = "select t0.ID, t0.NAME from EXAM_ICD_PROP t0, EXAM_ICD_PROP_VAL t1, QM_USER_IMAGE_PROP t2, SUB_SYS_PROP_VAL t3 where t2.PROP_ID=t3.PROP_VAL_ID and t3.ID=t1.PROPID and t1.ICDID=t0.ID and t2.USERIMAGE_ID=" + image_id;
			else
				sql = "select PROP_ID as ID from QM_GUIDE_PROP where GUIDE_ID=" + guide.getParent_id();
			
			List<PropUnit> icdPropList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			guide.setIcdPropList(icdPropList);
			addICDProp(guide);
		}
		return true;
	}

	@Override
	public boolean updateStudyGuide(StudyGuide guide) {		
		List<StudyGuide> qq = new ArrayList();
		if (!StringUtils.checkNull(guide.getName())) {
			String sql = "update QM_GUIDE set NAME = :name where Id = :id";
			getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(guide));
			return true;
		}
		else if(guide.getId()!=-1 && guide.getId()>0){
			String sql = "SELECT T1.NAME FROM QM_USER_IMAGE T1,QM_GUIDE T3 WHERE T3.USERIMAGE_ID = T1.ID AND T3.ID="+guide.getId();
			qq = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StudyGuide.class));
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteStudyGuide(StudyGuide guide) {
		
		String sql = "select count(1) from QM_GUIDE where PARENT_ID=" + guide.getId();
		Long cnt = getJdbcTemplate().queryForLong(sql);		
		if (cnt > 0L) return false;
		
		sql = "delete from QM_GUIDE where ID=" + guide.getId();
		getJdbcTemplate().update(sql);
		return true;
	}

	public void addICDProp(StudyGuide guide) {
		String sql = "insert into QM_GUIDE_PROP (GUIDE_ID, PROP_ID) values (?, ?)";
		for (PropUnit prop:guide.getIcdPropList()) {
			getJdbcTemplate().update(sql, guide.getId(), prop.getId());
		}
	}

	@Override
	public boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr) {
		
		String sql = "select count(1) from QM_GUIDE_PROP where GUIDE_ID=" + guideId + " and PROP_ID=" + icdPropId;
		Long p = getJdbcTemplate().queryForLong(sql);
		
		if (p != null && p > 0) {
			sql = "delete from QM_GUIDE_PROP where GUIDE_ID=" + guideId + " and PROP_ID=" + icdPropId;
		} else {
			sql = "insert into QM_GUIDE_PROP (GUIDE_ID, PROP_ID) values (" + guideId + ", " + icdPropId + ")";
		}
		
		getJdbcTemplate().update(sql);
		
		return true;
	}
}
