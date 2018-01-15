package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.QualityModelManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.utils.StringUtils;

public class QualityModelManageJDBCDAO extends BaseDao implements
		QualityModelManageDAO {

	@Override
	public List<QualityModel> getQualityModelList(QualityModel qualityModel) {
		
		StringBuffer sql = new StringBuffer();		
		List<QualityModel> qualityModelList = new ArrayList<QualityModel>();
		/*Long id = qualityModel.getId()
		sql.append("select *  from QM_TYPE where id>0 ");
		//List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
		if (qualityModel.getType() != null && !StringUtil.checkNull(qualityModel.getType().getName())) {
			
			sql.append(" and name like '%").append(qualityModel.getType().getName()).append("%'");}
		
			List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
		
			for (ModelType modelType:modelTypeList) {
				QualityModel model = new QualityModel();
				model.setType(modelType);
				qualityModelList.add(model);
			}
		
		else{
			sql.append("select *  from QM_QUALITY_PROP where QUALITY_ID=").append(qualityModel.getId());
			List<PropUnit> propTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			QualityModel model = new QualityModel();
			model.setSubjectPropList(propTypeList);
			qualityModelList.add(model);
		}	*/
		
		if (qualityModel.getId() == null || qualityModel.getId() == 0) {
			
			sql.append("select id, name  from EXAM_PROP_VAL where type=24 ");			
			if (qualityModel.getType() != null && !StringUtil.checkNull(qualityModel.getType().getName())) 
				sql.append(" and name like '%").append(qualityModel.getType().getName()).append("%'");
			List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
			for (ModelType modelType:modelTypeList) {
				QualityModel model = new QualityModel();
				model.setType(modelType);
				qualityModelList.add(model);
			}
			
		} else {
			sql.append("select * from QM_QUALITY where ID=").append(qualityModel.getId());
			qualityModelList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			
			for (QualityModel quality:qualityModelList) {
				String prop_sql = "select QUALITY_ID, PROP_ID as ID from QM_QUALITY_PROP where QUALITY_ID=" + quality.getId();
				List<PropUnit> zuti_propList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				quality.setSubjectPropList(zuti_propList);
			}
		}
		
		return qualityModelList;
	}
	
	@Override
	public List<QualityModel> getNextQualityModelList(QualityModel qualityModel) {
		//ModelType modelType = new ModelType();
		List<QualityModel> modelList = new ArrayList<QualityModel>();
		StringBuffer sql = new StringBuffer();
		
		if (qualityModel.getType() != null && qualityModel.getType().getId() > 0) {			
			Long typeID=qualityModel.getType().getId();
			String searchname = qualityModel.getName();
			//sql.append("select *  from QM_TYPE where id").append(typeID);
			if(!StringUtil.checkNull(searchname)){
				sql.append("select * from QM_QUALITY where NAME like ").append("'%").append(searchname).append("%'").append(" and TYPEID=").append(typeID);
			}else{
				sql.append("select * from QM_QUALITY where TYPEID=").append(typeID);
			}
			modelList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
		} else if (qualityModel.getId() != null) {
			String searchname = qualityModel.getName();
			Long parentID=qualityModel.getId();
			if(!StringUtil.checkNull(searchname)){
				sql.append("select * from QM_QUALITY where NAME like").append("'%").append(searchname).append("%'").append(" and PARENTID=").append(parentID);;
			}else{
				sql.append("select * from QM_QUALITY where PARENTID=").append(parentID);
			}
			
			modelList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class));
			
			for (QualityModel model:modelList) {
				
				String prop_sql = "select t1.prop_id as id, t3.name from QM_QUALITY_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.QUALITY_ID=" + model.getId();
				List<PropUnit> subjectList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				model.setSubjectPropList(subjectList);
			}
		//	modelTypeList=+getJdbcTemplate().query(prop_sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		} else {}
		
		
		
		return modelList;
	}

	@Override
	public boolean addQualityModel(QualityModel qualityModel) {
		
		Long id = getNextId("QM_QUALITY");		
		Long add_type = qualityModel.getId();
		String add_name = qualityModel.getName();
		Long parent_id = qualityModel.getParentId();
		
		String sql_add = "";
		if (parent_id != null && parent_id > 0) 
			sql_add = "INSERT INTO QM_QUALITY (ID,NAME, PARENTID) VALUES (" + id + ", '" + add_name + "'," + parent_id + ")";
		else
			sql_add = "INSERT INTO QM_QUALITY (ID,NAME,TYPEID) VALUES ('"+id+"','"+add_name+"','"+add_type+"')";
		getSimpleJdbcTemplate().update(sql_add.toString());
		
		List<PropUnit> subjectPropList = qualityModel.getSubjectPropList();
		if (subjectPropList != null && subjectPropList.size() > 0) {
			sql_add = "INSERT INTO QM_QUALITY_PROP (QUALITY_ID, PROP_ID) VALUES (?, ?)";
			for (PropUnit prop:subjectPropList) {
				getSimpleJdbcTemplate().update(sql_add, id, prop.getId());
			}
		}
		
		return true;
	
	}
		
	@Override
	public boolean updateQualityModel(QualityModel qualityModel) {
		Long update_id = qualityModel.getId();
		String update_name = qualityModel.getName();
		
		String sql="UPDATE QM_QUALITY SET name='"+update_name+"'WHERE id='"+update_id+"'";
		getSimpleJdbcTemplate().update(sql);
	if(qualityModel.getSubjectPropList() != null){
		sql = "DELETE from QM_QUALITY_PROP where QUALITY_ID="+update_id;
		getSimpleJdbcTemplate().update(sql);
		
		sql = "INSERT INTO QM_QUALITY_PROP (QUALITY_ID, PROP_ID) values (?, ?)";
		List<PropUnit> subjectPropList = qualityModel.getSubjectPropList();
		for(PropUnit prop:subjectPropList){
			getSimpleJdbcTemplate().update(sql, update_id, prop.getId());
		}
	}
		return true;		
	}

	@Override
	public boolean deleteQualityModel(QualityModel qualityModel) {
		
		String sql = "delete from QM_QUALITY where id="+qualityModel.getId();
		getJdbcTemplate().update(sql);
		String sql_prop = "delete from QM_QUALITY_PROP where QUALITY_ID="+qualityModel.getId();
		getJdbcTemplate().update(sql_prop);

		return true;
	}
	@Override
	public List<PropUnit> getZutiListByType() {
		String sql_zuti = "SELECT id, name FROM EXAM_PROP_VAL WHERE type=7";
		List<PropUnit> zutilist = getJdbcTemplate().query(sql_zuti, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return zutilist;
	} 
	
	/**
	 * @author 杨红强
	 * @time	2017-02-18
	 * 胜任力大圆圈里的内容，YHQ 2017-02-18
	 */
	@Override
	public List<QualityModel> getAbilityLevelOneList(QualityModel qualityModel) {
		String sql = "select * from qm_quality qq where qq.TYPEID in (select epv1.id from exam_prop_val epv1 where epv1.type = 24 and epv1.code in (select epv2.ext_type from exam_prop_val epv2 where epv2.id = ?)) order by id " ;
		List<QualityModel> abilityLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qualityModel.getId()) ;
		for (QualityModel qmObj : abilityLevelOneList) {
			sql = "select count(1) from cv_set cs "
				+ "inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
				+ "inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
				+ "inner join cv t on t.id = s1.CV_ID "
				+ "inner join cv_ref_unit t1 on t.id=t1.cv_id "
				+ "inner join cv_unit t2 on t1.unit_id=t2.id "
				+ "inner join cv_unit_ref_quality cq on t2.id = cq.unit_id  and cq.state = 1 " //and cq.level = 2
				+ "inner join log_study_cv_set lscs on lscs.CV_SET_ID = cs.ID "
				+ "inner join qm_quality qq on qq.id = cq.prop_id "
				+ "where qq.parentid = ? and lscs.SYSTEM_USER_ID = ? " ;
			long learnNum = getJdbcTemplate().queryForLong(sql, qmObj.getId(),qualityModel.getParentId());			
			qmObj.setLevel(learnNum) ;
		}
		return abilityLevelOneList ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-18
	 * 胜任力大圆圈下级的内容，YHQ 2017-02-18
	 */
	@Override
	public List<PropUnit> getAbilityLevelTwoList(QualityModel qualityModel) {
		String sql = "select id,name from qm_quality qq where qq.parentid = ? " ;
		List<PropUnit> abilityLevelTwoList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class), qualityModel.getId()) ;
		return abilityLevelTwoList ;
	}

	/**
	 * @author 杨红强
	 * @time	2017-02-21
	 * 胜任力大圆圈下1级关联的项目，YHQ 2017-02-21————推荐没有学过的项目
	 */
	@Override
	public List<CVSet> getAbilityLevelTwoProjectList(QualityModel qualityModel) {
		if (qualityModel != null && qualityModel.getId() != null && qualityModel.getParentId() != null) {
			String sql = "select distinct cs.* from cv_set cs "
					   + "INNER JOIN cv_set_user_image cui ON cs.ID = cui.CV_SET_ID "
					   + "INNER JOIN qm_user_image_prop qui ON cui.USERIMAGE_ID = qui.USERIMAGE_ID "
					   + "INNER JOIN exam_prop_val epv ON qui.PROP_ID = epv.ID ";
			if(qualityModel.getName()!=null){
				sql += " AND epv.id IN " + qualityModel.getName();
			}
			sql += "inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
				+ "inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
				+ "inner join cv t on t.id = s1.CV_ID "
				+ "inner join cv_ref_unit t1 on t.id=t1.cv_id "
				+ "inner join cv_unit t2 on t1.unit_id=t2.id "
				+ "inner join cv_unit_ref_quality cq on t2.id = cq.unit_id  and cq.state = 1 and cq.prop_id = ? " // and cq.level = 2
				+ "left join log_study_cv_set lscs ON lscs.CV_SET_ID = cs.id and lscs.SYSTEM_USER_ID = ? "
				+ "where cs.status=5 and lscs.CV_SET_ID is null";
				//+ " where cs.status=5 and cq.prop_id = ? and cs.id not in (select lscs.CV_SET_ID from log_study_cv_set lscs where lscs.SYSTEM_USER_ID = ?)" ;
			List<CVSet> projectList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class), qualityModel.getId(), qualityModel.getParentId()) ;
			return projectList ;
		} else return null ;		
	}
	
	@Override
	public String getXunKeSql(Long propId) {
		if(propId!=null&&propId>0){
			String sql = "select type from exam_prop_val where id = " + propId;
			Long type = getJdbcTemplate().queryForLong(sql);
			if(type==3){
				return " (" + propId + ") ";
			}else if(type==4){
				String sqll = "select prop_val1 from prop_val_ref where prop_val2 = " + propId;
				Long prop_val1 = getJdbcTemplate().queryForLong(sqll);
				if(prop_val1!=null&&prop_val1>0){
					return " (" + propId + "," + prop_val1 + ") ";
				}
			}else if(type==5){
				String sqll = "select prop_val1 from prop_val_ref where prop_val2 = " + propId;
				Long prop_val1 = getJdbcTemplate().queryForLong(sqll);
				if(prop_val1!=null&&prop_val1>0){
					String sqlll = "select prop_val1 from prop_val_ref where prop_val2 = " + prop_val1;
					Long prop_val11 = getJdbcTemplate().queryForLong(sqlll);
					if(prop_val11!=null&&prop_val11>0){
						return " (" + propId + "," + prop_val1 + "," + prop_val11 + ") ";
					}
				}
			}
			return null;
		}else{
			return null;
		}
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-22
	 * 年度学习报告中胜任力大圆圈里的内容，YHQ 2017-02-22
	 */
	@Override
	public List<QualityModel> getStudyYearReportAbilityList(QualityModel qualityModel) {
		String sql = "select * from qm_quality qq where qq.TYPEID in (select epv1.id from exam_prop_val epv1 where epv1.type = 24 and epv1.code in (select epv2.ext_type from exam_prop_val epv2 where epv2.id = ?)) order by id " ;
		List<QualityModel> abilityLevelOneList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(QualityModel.class), qualityModel.getId()) ;
		for (QualityModel qmObj : abilityLevelOneList) {
			sql = "select count(1) from cv_set cs "
				+ "inner join CV_SET_SCHEDULE s2 on cs.id = s2.CV_SET_ID "
				+ "inner join CV_SCHEDULE s1 on s1.SCHEDULE_ID = s2.CV_SCHEDULE_ID "
				+ "inner join cv t on t.id = s1.CV_ID "
				+ "inner join cv_ref_unit t1 on t.id=t1.cv_id "
				+ "inner join cv_unit t2 on t1.unit_id=t2.id "
				+ "inner join cv_unit_ref_quality cq on t2.id = cq.unit_id  and cq.state = 1 " //and cq.level = 2 
				+ "inner join log_study_cv_set lscs on lscs.CV_SET_ID = cs.ID "
				+ "inner join qm_quality qq on qq.id = cq.prop_id "
				+ "where qq.parentid = ? and lscs.SYSTEM_USER_ID = ? " ;
			List<Object> argsList = new ArrayList<Object>();
			argsList.add(qmObj.getId());
			argsList.add(qualityModel.getParentId());
			
			if (qualityModel.getLevel() != null) {
				sql += " and year(lscs.LAST_UPDATE_DATE) = ? " ;
				argsList.add(qualityModel.getLevel());
			}
			
			long learnNum = getJdbcTemplate().queryForLong(sql, argsList.toArray());			
			qmObj.setLevel(learnNum) ;
		}
		return abilityLevelOneList ;
	}

}
