package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPropValDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamMajorOrder;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropExport;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamRelationProp;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropValJDBCDAO extends BaseDao
		implements ExamPropValDAO {
	
	private static final String GETBASEPROPVAL_SQL = "select t2.id, t2.type, t2.name, t2.code, t2.c_type, t1.id as refId from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id and t2.type =? and t2.c_type = ? ";
	private static final String GETBASEPROPVAL_SQL2 = "select c.*,d.id as refId from sub_sys_prop_val b, exam_prop_val c,sub_sys_prop_val d where d.prop_val_id = c.id and b.prop_val_id = c.id and b.id in(select a.prop_val2 from PROP_VAL_REF a where a.prop_val1 =?)　and c.c_type = ?";

	public Map<String, List<ExamPropVal>> getSysPropValBySysId() {
		
		//String sql = "select t.type, t2.id, t2.name,t2.code from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id and t.relation = 0 and t.up_level is null";
		String sql = "select t.type, t2.id, t2.name,t2.code,t1.id as refId from sub_sys_prop t, sub_sys_prop_val t1, exam_prop_val t2 where t.id = t1.sys_prop_id and t1.prop_val_id = t2.id";

		Map<String, List<ExamPropVal>> m = new HashMap<String, List<ExamPropVal>>();

		List<ExamPropVal> propValList = getJdbcTemplate().query(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamPropVal.class));
		
		for (ExamPropVal propval : propValList) {
			if (m.containsKey(propval.getType().toString())) {
				List<ExamPropVal> list = m.get(propval.getType().toString());
				list.add(propval);
			} else {
				List<ExamPropVal> lists = new ArrayList<ExamPropVal>();
				lists.add(propval);
				m.put(propval.getType().toString(), lists);
			}
		}
		
		return m;
	}

	@SuppressWarnings("unchecked")
	public List<ExamPropValTemp> getBasePropVal(Integer type) {
		
		List <ExamPropValTemp> rlist = new ArrayList<ExamPropValTemp>();
		List<ExamPropVal> list = null;
		

		list =  getJdbcTemplate().query(GETBASEPROPVAL_SQL, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class), 1, type);

		ExamPropValTemp pvt = null;
		
		if(null != list && list.size()>0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ExamPropVal name = (ExamPropVal) iterator.next();
				pvt = new ExamPropValTemp();
				pvt.setId(name.getId().toString());
				pvt.setName(name.getName());
				List<ExamPropValTemp>subItem = this.getExamPropVal(type,name.getRefId());
				pvt.setSubItems(subItem);
				
				rlist.add(pvt);
			}
		}
		return rlist;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<ExamPropValTemp> getExamPropVal(Integer type, Long refId) {
		
		List<ExamPropValTemp>subItem = new ArrayList<ExamPropValTemp>();
		List<ExamPropVal> list2 = getJdbcTemplate().query(GETBASEPROPVAL_SQL2, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class), refId, type);
		ExamPropValTemp pvt2 = null;
		if(null != list2 && list2.size()>0) {
			for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
				ExamPropVal name2 = (ExamPropVal) iterator2.next();
				pvt2 = new ExamPropValTemp();
				pvt2.setId(name2.getId().toString());
				pvt2.setName(name2.getName());
				
				if(name2.getType()<6) {
					pvt2.setSubItems(this.getExamPropVal(type,name2.getRefId()));
				}
				
				subItem.add(pvt2);
			}
		}
		
		return subItem;
		
	}

	@Override
	public List<ExamPropVal> getBaseRelPorp(int type) {
		
		String sql_ = "select v.* from sub_sys_prop_val t,exam_prop_val v where t.sys_prop_id=1 and v.id =t.prop_val_id order by v.id";
		
		List<ExamPropVal> list = getJdbcTemplate().query(sql_, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class));
		
		for(int j=0;j<list.size();j++){

			
			ExamPropVal v = list.get(j);

			List<ExamPropExport> eplist =  new ArrayList<ExamPropExport>();
			if(type==0){
				eplist = getRelPorp(v.getId());
			}else{
				eplist = getSubSysRelPorp(v.getId());
			}

			v.setRef(eplist);
		}
		return list;
	}
	
	private List<ExamPropExport> getRelPorp(Long propId){
		StringBuffer sql = new StringBuffer();
		sql.append("select v.name||'('||v.id||')' as p1, v1.name||'('||v1.id||')' as p2, v2.name||'('||v2.id||')' as p3, v3.name||'('||v3.id||')' as p4, v4.name||'('||v4.id||')' as p5");
		sql.append(" from sub_sys_prop_val l ");
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");
		sql.append("join prop_val_ref f1 on l1.id = f1.prop_val1 ");
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");
		sql.append("where v.id = ?");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamPropExport.class),propId);
	}
	
	private List<ExamPropExport> getSubSysRelPorp(Long propId){
		StringBuffer sql = new StringBuffer();
		sql.append("select v.name||'#'||v.id as p1, v1.name||'#'||v1.id as p2, v2.name||'#'||v2.id as p3, v3.name||'#'||v3.id as p4, v4.name||'#'||v4.id as p5");
		sql.append(" from sub_sys_prop_val l ");
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");
		sql.append("join prop_val_ref f1 on l1.id = f1.prop_val1 ");
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");
		sql.append("where v.id = ? order by v.id, v1.id, v2.id, v3.id, v4.id");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamPropExport.class),propId);
	}	

	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) {
		String sql = "select t.*, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = ? ";
		
		if(prop.getExt_type() != null && prop.getExt_type() > 0) {
			sql += "and t.ext_type=" + prop.getExt_type();
		}
		
		//2017.6.23 xh
		if(prop.getImg_type() != null && prop.getImg_type() > 0) {
			sql += " and t.img_type=" + prop.getImg_type();
		}
		
		if(prop.getType()!=null && prop.getType()==20){
			sql += " and t.name like ? order by cast(t.code AS signed)";//省排序字段为字符类型，需用函数转成整数排序，针对省份加载进行处理(类型20)
		}else{
			sql += " and t.name like ? order by t.code";
		}
		
		String searchName = prop.getName();
		if (StringUtil.checkNull(searchName)) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamProp> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),prop.getType(), searchName);
		
		for(ExamProp p :list){
			if (p.getId() != null && p.getType() != null) //YHQ 2017-02-15 add if
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
		}
		
		return list;
	}

	@Override
	public List<ExamProp> getNewPropListByType(ExamProp prop) {
		String sql="";
		if(prop.getType()==24){
			sql = "select t.*, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt "
					+ "where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = ? "
					+ "and t.code in (select l.img_type from exam_prop_val l where l.type=1)";
		}else{
			sql = "select t.*, v.id as prop_val_id, p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, sub_sys_prop p, exam_prop_type tt "
					+ "where t.id = v.prop_val_id and p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.type = ? ";
		}
		
		if(prop.getExt_type() != null && prop.getExt_type() > 0) {
			sql += "and t.ext_type=" + prop.getExt_type();
		}
		
		if(prop.getType()!=null && prop.getType()==20){
			sql += " and t.name like ? order by cast(t.code AS signed)";//省排序字段为字符类型，需用函数转成整数排序，针对省份加载进行处理(类型20)
		}else{
			sql += " and t.name like ? order by t.code";
		}
		
		String searchName = prop.getName();
		if (StringUtil.checkNull(searchName)) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamProp> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),prop.getType(), searchName);
		
		for(ExamProp p :list){
			if (p.getId() != null && p.getType() != null) //YHQ 2017-02-15 add if
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
		}
		
		return list;
	}
	@Override
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery) {
		
		ExamReturnProp r_prop = new ExamReturnProp();
		String sql = "select t.*, v.id as prop_val_id,p.name as typeName, tt.prop_type_name as c_type_name from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ? ";
		String sql_size = "select count(1) from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ?";
		
		if(propQuery.getExt_type() != null && propQuery.getExt_type() > 0) {
			sql += "and t.ext_type=" + propQuery.getExt_type();
			sql_size += "and t.ext_type=" + propQuery.getExt_type();
		}
		
		sql += " order by t.code";
		
		String searchName = propQuery.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamProp> returnList = getJdbcTemplate().query(PageUtil.getPageSql(sql, propQuery.getPageNo(), propQuery
				.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), propQuery.getSysPropId(), searchName);
		for(ExamProp p :returnList){
			if (p.getId() != null) { //YHQ 2017-02-15 add if
				p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));				
				if (p.getProp_val_id() != null) { //YHQ 2017-02-15 add if
					String propSql = "select i.* from EXAM_ICD_PROP i LEFT JOIN EXAM_ICD_PROP_VAL p on i.ID=p.ICDID where p.PROPID=?";
					p.setIcdList(getJdbcTemplate().query(propSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class), p.getProp_val_id()));
				}				
			}			
		}
		
		r_prop.setReturnList(returnList);
		r_prop.setTotal_count(getJdbcTemplate().queryForInt(sql_size,propQuery.getSysPropId(), searchName));
		return r_prop;
	}

	public boolean existPropCode(ExamProp prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_prop_val where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"' and type='"+prop.getType()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	@Override
	public ExamProp addPropVal(ExamProp prop) throws Exception {
		if (existPropCode(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_prop_val");
		prop.setId(prop_id);
		String ADD_PROP_VAL = "insert into exam_prop_val (id, type, name, code, c_type, ext_type) values (:id, :type, :name, :code, :c_type, :ext_type)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));
		return prop;
	}

	@Override
	public void addRel(ExamProp prop) {
		String sql = "insert into prop_val_ref (prop_val1, prop_val2) values (:prop_val1, :prop_val2)";
		getSimpleJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(prop));
	}

	public ExamProp addSysPropVal(ExamProp prop) {
		prop.setProp_val_id(prop.getId());
		Long id = getNextId("sub_sys_prop_val");
		prop.setId(id);
		prop.setProp_val2(id);
		String sql = "insert into sub_sys_prop_val (id, sys_prop_id, prop_val_id) values (:id, :type, :prop_val_id)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(prop));
		return prop;
	}

	public boolean deletePropVal(ExamProp prop) {
		String SQL_DEL_EXAM_PROP_VAL = "delete from exam_prop_val where id = ?";
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from sub_sys_prop_val where prop_val_id = ?";
		String SQL_DEL_PROP_VAL_REF = "delete from prop_val_ref where prop_val2 = ?";
		String SQL_PROP_VAL_REF = "select count(1) from prop_val_ref x where x.prop_val1 = ?";
		
		int size = getJdbcTemplate().queryForInt(SQL_PROP_VAL_REF, prop.getProp_val_id());
		
		//检查是否有下级关联
		if(size==0){
			//删除关联上下级关联
			getJdbcTemplate().update(SQL_DEL_PROP_VAL_REF,prop.getProp_val_id());
			//删除系统属性
			getJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL,prop.getId());
			//删除属性
			getJdbcTemplate().update(SQL_DEL_EXAM_PROP_VAL,prop.getId());
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public void updatePropVal(ExamProp prop) throws Exception {
		if (existPropCode(prop, prop.getId())) throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_prop_val set ");
		List list = new ArrayList();
		if(!StringUtils.checkNull(prop.getName())){
			sql.append("name = ?,");
			list.add(prop.getName());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		if(prop.getC_type()!=null){
			sql.append("c_type = ?,");
			list.add(prop.getC_type());
		}
		if(prop.getExt_type()!=null){
			sql.append("ext_type = ?,");
			list.add(prop.getExt_type());
		}
		sql.append(" type = type where id = ?");
		list.add(prop.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

	}

	@Override
	public List<ExamPropType> getExamPropTypeList() {
		String sql = "select * from exam_prop_type t order by t.prop_type";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamPropType.class));
	}

	@Override
	public List<ExamRelationProp> getExamRelationPropAll() {
		StringBuffer sql = new StringBuffer();

		sql.append("select v.id as study1_prop_id, v1.id as study2_prop_id, v2.id as study3_prop_id, v3.id as unit_prop_id, v4.id as point_prop_id,");
		sql.append("v.name || '-' || v1.name || '-' || v2.name || '-' || v3.name || '-' || v4.name as relationPropName,");
		sql.append("v.id || '-' || v1.id || '-' || v2.id || '-' || v3.id || '-' || v4.id as relationPropId");
		       
		sql.append(" from sub_sys_prop_val l ");
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");
		sql.append("join prop_val_ref f1 on l1.id = f1.prop_val1 ");
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");		       
		sql.append("where exists (select 1 from exam_prop_val x where x.id = v.id and x.type = 1)");
		
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamRelationProp.class));
	}

	/**
	 * 计算试题属性总数
	 * @param prop_val_id 属性id
	 * @param type	属性类型
	 * @return
	 */
	private  int getQuestionNumByPropId(Long prop_val_id,int type){
		String sql_p1 = "select count(distinct t.id) from exam_question t, exam_question_study1 p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p2 = "select count(distinct t.id) from exam_question t, exam_question_study2 p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p3 = "select count(distinct t.id) from exam_question t, exam_question_study3 p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p4 = "select count(distinct t.id) from exam_question t, exam_question_unit p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		String sql_p5 = "select count(distinct t.id) from exam_question t, exam_question_point p1 where t.id = p1.question_id and p1.prop_val_id = ? and t.parent_id = 0";
		int num = 0;
		
		switch (type) {
			case 1:
				num = getJdbcTemplate().queryForInt(sql_p1, prop_val_id);
				break;
			case 2:
				num = getJdbcTemplate().queryForInt(sql_p2, prop_val_id);
				break;
			case 3:
				num = getJdbcTemplate().queryForInt(sql_p3, prop_val_id);
				break;
			case 4:
				num = getJdbcTemplate().queryForInt(sql_p4, prop_val_id);
				break;
			case 5:
				num = getJdbcTemplate().queryForInt(sql_p5, prop_val_id);
				break;
		}
		
		return num;
	}

	public List<ExamProp> getNextLevelProp(Long propValId) {

		String sql = "select v.* from sub_sys_prop_val t, prop_val_ref r, sub_sys_prop_val tt,exam_prop_val v where t.prop_val_id = ? and r.prop_val1 = t.id and tt.id = r.prop_val2 and v.id = tt.prop_val_id";
		
		return getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),
				propValId);
	}

    public ExamProp getSysPropVal(Long id) {
        String sql = "select t.*,v.id as prop_val_id from exam_prop_val t,sub_sys_prop_val v where t.id = v.prop_val_id and t.id = ? order by t.code";
        try{
        return getJdbcTemplate().queryForObject(sql,
                ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class),
                id);
        }
        catch(Exception e){return null;}
    }

	@Override
	public void updateMoveSysPropVal(Long targetId, Long currentId) {
		String del_sql = "delete from prop_val_ref where prop_val2 = ?";
		//删除要移动属性与上一级的关系
		getJdbcTemplate().update(del_sql,currentId);
		
		//添加要移动属性与新上一级的关系
		ExamProp prop = new ExamProp();
		prop.setProp_val1(targetId);
		prop.setProp_val2(currentId);
		addRel(prop);
	}

	/**
	 * 查询ICD属性
	 */
	@SuppressWarnings("unchecked")
	public List<ExamICD> getIcdListByType(ExamICD prop) {
		String sql = "select * from exam_icd_prop where type=? and name like ?";
		String searchName = prop.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List array = new ArrayList();
		array.add(prop.getType());
		array.add(searchName);
		
		if (prop.getId()!=null && prop.getId() !=0){
			sql+= " and id=?";
			array.add(prop.getId());
		}
		sql += " order by code";
		
		//取得关联学科列表
		List<ExamICD> list = getJdbcTemplate().query(sql, array.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class));
		for (ExamICD s: list){
			String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXAM_ICD_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.ICDID=?";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setProp(propList);

		}
		return list;
	}

	public boolean existIcdVal(ExamICD prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_icd_prop where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"' and type='"+prop.getType()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	/**
	 * 添加ICD属性
	 * @throws Exception 
	 */
	@Override
	public ExamICD addIcdVal(ExamICD prop) throws Exception {
		if (existIcdVal(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_icd_prop");
		prop.setId(prop_id);
		String ADD_PROP_VAL = "insert into exam_icd_prop (id, type, name, code, name_en, hint) values (:id, :type, :name, :code, :nameEn, :hint)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));

		//添加关联学科
		try{
		String[] propIds = prop.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_icd_prop_val");
			String ADD_PROP = "insert into exam_icd_prop_val (id, icdid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}
		return prop;
	}

	/**
	 * 删除ICD属性
	 */
	@Override
	public boolean deleteIcdVal(ExamICD prop) {
		String SQL_DEL_EXAM_PROP_VAL = "delete from exam_icd_prop where id = ?";
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from exam_icd_prop_val where icdid = ?";
		String SQL_PROP_VAL_REF = "select count(1) from exam_icd_prop_val x where x.icdid = ?";
		
		int size = getJdbcTemplate().queryForInt(SQL_PROP_VAL_REF, prop.getId());
		size = 0;
		//检查是否有下级关联
		if(size==0){
			//删除系统属性
			getJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL,prop.getId());
			//删除属性
			getJdbcTemplate().update(SQL_DEL_EXAM_PROP_VAL,prop.getId());
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 修改ICD属性
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void updateIcdVal(ExamICD prop) throws Exception {
		if (existIcdVal(prop, prop.getId())) throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_icd_prop set ");
		List list = new ArrayList();
		if(!StringUtils.checkNull(prop.getName())){
			sql.append("name = ?,");
			list.add(prop.getName());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		if(null!=prop.getNameEn()){
			sql.append("name_en = ?,");
			list.add(prop.getNameEn());
		}
		if(null!=prop.getHint()){
			sql.append("hint = ?,");
			list.add(prop.getHint());
		}
		sql.append(" type = type where id = ?");
		list.add(prop.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		
		//删除旧的学科
		String DEL_PROP = "delete from exam_icd_prop_val where icdid=?";
		getSimpleJdbcTemplate().update(DEL_PROP, prop.getId());

		//添加新的学科
		try{
		String[] propIds = prop.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_icd_prop_val");
			String ADD_PROP = "insert into exam_icd_prop_val (id, icdid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}
	}

	/**
	 * 查询来源类型
	 */
	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop) {
		String sql = "select * from exam_source_type where id!=0 and type_name like ? order by code";
		
		String searchName = prop.getType_name();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamSourceType> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSourceType.class), searchName);
		return list;
	}

	public boolean existSourceType(ExamSourceType prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getType_name() == null) return false;
		String getcode = "select count(1) from exam_source_type where ((code='" + prop.getCode() + "') or (type_name='"+prop.getType_name()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	/**
	 * 添加来源类型
	 */
	@Override
	public ExamSourceType addSourceType(ExamSourceType prop) throws Exception {
		if (existSourceType(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_source_type");
		prop.setId(prop_id);
		String ADD_PROP_VAL = "insert into exam_source_type (id, type_name, code) values (:id, :type_name, :code)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));
		return prop;
	}

	/**
	 * 删除来源类型
	 */
	@Override
	public boolean deleteSourceType(ExamSourceType prop) {
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from exam_source_type where id = ?";
		int res = getSimpleJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL, prop.getId());
		return res>0?true:false;
	}

	/**
	 * 修改来源类型
	 */
	@SuppressWarnings("unchecked")
	public void updateSourceType(ExamSourceType prop) throws Exception {
		if (existSourceType(prop, prop.getId())) throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_source_type set ");
		List list = new ArrayList();
		if(!StringUtils.checkNull(prop.getType_name())){
			sql.append("type_name = ?,");
			list.add(prop.getType_name());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		sql.append(" id = id where id = ?");
		list.add(prop.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		
	}

	/**
	 * 查询来源
	 */
	@Override
	public List<ExamSource> getSourceValList(ExamSource prop) {
		String sql = "select t.*, s.type_name as typename from exam_source_val t left join exam_source_type s on t.type=s.id where t.name like ? "; // and s.type_name like ? 
		
		if (prop.getId()!=null && prop.getId() > 0)
			sql += " and t.id=" + prop.getId();
		
		if (prop.getType()!=null && prop.getType() > 0)
			sql += " and t.type=" + prop.getType();
		
		StringBuffer propsql = new StringBuffer();
		if(!StringUtils.checkNull(prop.getPropIds())){
			String ids = prop.getPropIds().replace('"', ' ');
			propsql.append(" and t.id in (select sourceid from EXAM_SOURCE_PROP_VAL where PROPID in (").append(ids).append(")) ");
			sql += propsql.toString();
		}
		
		sql += " order by t.code";

		String searchName = prop.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
/*		String searchTypeName = prop.getTypeName();
		if (searchTypeName == null) searchTypeName = "%";
		else searchTypeName = "%" + searchTypeName + "%";
		
*/		//取得所有的所属学科和主题
		List<ExamSource> list = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamSource.class), searchName);
		for (ExamSource s: list){
			String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXAM_SOURCE_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.SOURCEID=? and p.type<=5";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setProp(propList);

			String GET_ZHUTI = "SELECT p.*, s.id as prop_val_id FROM EXAM_SOURCE_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where p.id>0 and e.SOURCEID=? and p.type=7";
			propList = getJdbcTemplate().query(GET_ZHUTI, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), s.getId());
			s.setZhuti(propList);

		}
		return list;

	}

	public boolean existSourceVal(ExamSource prop, Long id)
	{
		if (prop == null || prop.getCode() == null || prop.getName() == null) return false;
		String getcode = "select count(1) from exam_source_val where ((code='" + prop.getCode() + "') or (name='"+prop.getName()+"')) and id<>"+id.toString();
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	/**
	 * 添加来源
	 * @throws Exception 
	 */
	@Override
	public ExamSource addSourceVal(ExamSource prop) throws Exception {
		if (existSourceVal(prop, 0L)) throw new Exception();
		Long prop_id = getNextId("exam_source_val");
		prop.setId(prop_id);
		String ADD_PROP_VAL = "insert into exam_source_val (id, type, name, code, source, old) values (:id, :type, :name, :code, :source, :old)";
		getSimpleJdbcTemplate().update(ADD_PROP_VAL, new BeanPropertySqlParameterSource(prop));
	
		//添加关联学科
		try{
		String[] propIds = prop.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_source_prop_val");
			String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}

		//添加主题
		try{
		String[] propIds = prop.getZhutiIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("exam_source_prop_val");
			String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
		}
		}catch(Exception e){;}
		return prop;
	}

	//删除来源
	@Override
	public boolean deleteSourceVal(ExamSource prop) {
		String SQL_DEL_EXAM_VAL = "delete from exam_question_source where prop_val_id = ?";
		String SQL_DEL_PROP_VAL = "delete from exam_source_prop_val where sourceid = ?";
		String SQL_DEL_MAT_VAL = "delete from material_source_val where source_id = ?";
		String SQL_DEL_SUB_SYS_PROP_VAL = "delete from exam_source_val where id = ?";
		
		int res = getSimpleJdbcTemplate().update(SQL_DEL_EXAM_VAL, prop.getId());
		res = getSimpleJdbcTemplate().update(SQL_DEL_PROP_VAL, prop.getId());
		res = getSimpleJdbcTemplate().update(SQL_DEL_MAT_VAL, prop.getId());
		res = getSimpleJdbcTemplate().update(SQL_DEL_SUB_SYS_PROP_VAL, prop.getId());
		
		return res>0?true:false;
	}

	//修改来源
	@SuppressWarnings("unchecked")
	public boolean updateSourceVal(ExamSource prop) {
		if (existSourceVal(prop, prop.getId())) return false;
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_source_val set ");
		List list = new ArrayList();
		if(prop.getType()!=null){
			sql.append("type = ?,");
			list.add(prop.getType());
		}
		if(!StringUtils.checkNull(prop.getName())){
			sql.append("name = ?,");
			list.add(prop.getName());
		}
		if(!StringUtils.checkNull(prop.getCode())){
			sql.append("code = ?,");
			list.add(prop.getCode());
		}
		if(prop.getSource()!=null){
			sql.append("source = ?,");
			list.add(prop.getSource());
		}
		if(prop.getState()!=null){
			sql.append("state = ?,");
			list.add(prop.getState());
		}
		if(prop.getOld()!=null){
			sql.append("old = ?,");
			list.add(prop.getOld());
		}
		sql.append(" id = id where id = ?");
		list.add(prop.getId());
		
		int res =getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		if (prop.getPropIds() != null){
			//删除旧的学科
			String DEL_PROP = "delete from EXAM_SOURCE_PROP_VAL where id in (select EXAM_SOURCE_PROP_VAL.id from SUB_SYS_PROP_VAL s LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.id where EXAM_SOURCE_PROP_VAL.PROPID=s.id and EXAM_SOURCE_PROP_VAL.SOURCEID=? and p.type<=5)";
			//"delete from exam_source_prop_val where sourceid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, prop.getId());
	
			//添加新的学科
			try{
			String[] propIds = prop.getPropIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("exam_source_prop_val");
				String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
			}
			}catch(Exception e){;}
		}
		
		if (prop.getZhutiIds() != null){
			//删除旧的主题
			String DEL_ZHUTI = "delete from EXAM_SOURCE_PROP_VAL where id in (select EXAM_SOURCE_PROP_VAL.id from SUB_SYS_PROP_VAL s LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.id where EXAM_SOURCE_PROP_VAL.PROPID=s.id and EXAM_SOURCE_PROP_VAL.SOURCEID=? and p.type=7)";
			//"delete from exam_source_prop_val where sourceid=?";
			getSimpleJdbcTemplate().update(DEL_ZHUTI, prop.getId());
	
			//添加新的主题
			try{
			String[] propIds = prop.getZhutiIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("exam_source_prop_val");
				String ADD_PROP = "insert into exam_source_prop_val (id, sourceid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, prop.getId(), pid);
			}
			}catch(Exception e){;}
		}
		return res>0?true:false;
	}

	@Override
	public List<ExamHospital> getHospitalList(ExamHospital host) {
		String sql = "select t.* from exam_hospital t where ";
		
		List plist = new ArrayList();
		if (host.getPropId() != null){
			if(host.getPropId() == 23L) {
				sql+= "t.propid = ? and ";
				plist.add(host.getPropId());
			} else {
				sql += "t.regionid = ? and ";
				plist.add(host.getPropId());
			}
		}

		String searchName = host.getName();
		if (StringUtil.checkNull(searchName)) searchName = "'%%'";
		else searchName = "'%" + searchName + "%'";

		sql += "t.name like ? order by t.id";
		plist.add(searchName);
		
		List<ExamHospital> list =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),plist.toArray());

		try{
		for(ExamHospital e: list){
			String GET_REGION = "select t.*, r.* from EXAM_PROP_VAL t left join SUB_SYS_PROP_VAL s on t.id=s.PROP_VAL_ID LEFT JOIN PROP_VAL_REF r on r.prop_val2=s.id where s.id=?";
			ExamProp prop;
			prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), e.getRegionId());
			
			if (prop.getType() == 22){
				e.setRegion3(prop.getName());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 21){
				e.setRegion2(prop.getName());
				prop = getJdbcTemplate().queryForObject(GET_REGION, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), prop.getProp_val1());
			}
			if (prop.getType() == 20){
				e.setRegion1(prop.getName());
			}
		}
		}catch(Exception e){;}

		return list;
	}
	
	@Override
	public List<ExamHospital> getHospitalListAll(ExamHospital host) {
		String sql = "select t.* from exam_hospital t where ";
		
		List plist = new ArrayList();
		if (host.getPropId() != null){
			if(host.getPropId() == 23L) {
				sql+= "t.propid = ? and ";
				plist.add(host.getPropId());
			} else {
				sql += "t.regionid = ? and ";
				plist.add(host.getPropId());
			}
		}
		else
		{
			if(host.getId() != null && host.getId() != 0L)
			{
				sql += "t.id = ? and ";
				plist.add(host.getId());
			}
		}

		String searchName = host.getName();
		if (StringUtil.checkNull(searchName)) searchName = "%";
		else searchName = "%" + searchName + "%";

		sql += "t.name like ? order by t.id";
		plist.add(searchName);
		
		List<ExamHospital> list =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),plist.toArray());

		return list;
	}
	
	@Override
	public List<ExamHospital> getHospitalDistrict(ExamHospital host) {
		String sql = "select t.* from exam_hospital t ";
		
		List plist = new ArrayList();
		if (host.getRegionId() != null){
			sql += "where t.regionid = ? ";
			plist.add(host.getRegionId());
		}

		sql += " order by t.id";
		
		List<ExamHospital> list =  getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),plist.toArray());

		return list;
	}
	
	@Override
	public List<ExamHospital> getHospital(Long id) {
		String sql = "select t.* from exam_hospital t where ";
		if(id != null){
			sql += "t.id = ? ";
		}
		List<ExamHospital> list=getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ExamHospital.class),id);
		return list;
	}

	@Override
	public Long addHospital(ExamHospital host) {
		Long id = getNextId("exam_hospital");
		host.setId(id);
		String sql = "insert into exam_hospital (id, name, examiner, regionid, propid, hospital_address) values (:id, :name, :examiner, :regionId, :propId, :hospital_address)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(host));
		return id;
	}

	@Override
	public boolean deleteHospital(ExamHospital host) {
		String SQL_DEL = "delete from exam_hospital where id = ?";
		int res = getSimpleJdbcTemplate().update(SQL_DEL, host.getId());
		return res>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public boolean updateHospital(ExamHospital host) {
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_hospital set ");
		List list = new ArrayList();
		if(host.getRegionId()!=null && host.getRegionId()>0){
			sql.append("regionid = ?,");
			list.add(host.getRegionId());
		}
		/*if(host.getPropId()!=null){
			sql.append("propid = ?,");
			list.add(host.getPropId());
		}*/
		if(!StringUtils.checkNull(host.getName())){
			sql.append("name = ?,");
			list.add(host.getName());
		}
		if(!StringUtils.checkNull(host.getExaminer())){
			sql.append("examiner = ?,");
			list.add(host.getExaminer());
		}
		if(!StringUtils.checkNull(host.getHospital_address())) {
			sql.append("hospital_address=?,");
			list.add(host.getHospital_address());
		}
		sql.append(" id = id where id = ?");
		list.add(host.getPropId());
		
		int res =getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		return res>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from exam_major_order t where t.id<>0");

		List list = new ArrayList();
		if(!StringUtils.checkNull(major.getMajor())){
			sql.append(" and t.major like '%").append(major.getMajor()).append("%'");
		}
		if(!StringUtils.checkNull(major.getHospital())){
			sql.append(" and t.hospital like '%").append(major.getHospital()).append("%'");
		}
		if(major.getClassId() != null && major.getClassId() != 0 ){
			sql.append(" and t.classid=?");
			list.add(major.getClassId());
		}
		
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExamMajorOrder.class));
	}

	@Override
	public boolean addMajorOrder(ExamMajorOrder major) {
		Long id = getNextId("exam_major_order");
		major.setId(id);
		String sql = "insert into exam_major_order (id, major, hospital, ordername, classid) values (:id, :major, :hospital, :orderName, :classId)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(major));
		return true;
	}

	@Override
	public boolean deleteMajorOrder(ExamMajorOrder major) {
		String SQL_DEL = "delete from exam_major_order where id = ?";
		int res = getSimpleJdbcTemplate().update(SQL_DEL, major.getId());
		return res>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public boolean updateMajorOrder(ExamMajorOrder major) {
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_major_order set ");
		List list = new ArrayList();
		if(major.getClassId()!=null){
			sql.append("classid = ?,");
			list.add(major.getClassId());
		}
		if(!StringUtils.checkNull(major.getOrderName())){
			sql.append("ordername = ?,");
			list.add(major.getOrderName());
		}
		if(!StringUtils.checkNull(major.getHospital())){
			sql.append("hospital = ?,");
			list.add(major.getHospital());
		}
		if(!StringUtils.checkNull(major.getMajor())){
			sql.append("major = ?,");
			list.add(major.getMajor());
		}
		sql.append(" id = id where id = ?");
		list.add(major.getId());
		
		int res =getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		return res>0?true:false;
	}

	@Override
	public Long getParentPropId(Long id) {
		String sql = "select p.prop_val1 from prop_val_ref p left join sub_sys_prop_val s on p.prop_val2 = s.id where s.prop_val_id = ?";
		Long parentId = 0L;
		try{
			parentId = getJdbcTemplate().queryForLong(sql, id);
		}catch(Exception e){;}
		return parentId;
	}
	
	@Override
	public ExamReturnProp getNextLevelPropExam(ExamPropQuery propQuery) {
		
		ExamReturnProp r_prop = new ExamReturnProp();
		//String sql = "select e.*, s.id as prop_val_id from sub_sys_prop p,exam_prop_type tt,exam_prop_val t left join sub_sys_prop_val v on t.id=v.prop_val_id left join prop_val_ref r on v.id=r.prop_val1 left join sub_sys_prop_val s on r.prop_val2=s.id left join exam_prop_val e on s.prop_val_id= e.id where p.id = v.sys_prop_id and tt.prop_type = t.c_type and t.id = ? and t.name like ? order by e.code";
		String sql = "SELECT e.*, s.id AS prop_val_id FROM exam_prop_val e LEFT JOIN prop_val_ref r ON e.id = r.prop_val2 LEFT JOIN sub_sys_prop_val s ON r.prop_val2 = s.id LEFT JOIN exam_prop_val ee ON r.prop_val1 = ee.id WHERE r.prop_val1 = ? AND ee. NAME LIKE ? ORDER BY e. CODE";
		String sql_size = "select count(1) from exam_prop_val t, sub_sys_prop_val v, prop_val_ref r,sub_sys_prop p,exam_prop_type tt where t.id = v.prop_val_id and v.id = r.prop_val2 and p.id = v.sys_prop_id and tt.prop_type = t.c_type and r.prop_val1 = ? and t.name like ?";
		
		String searchName = propQuery.getName();
		if (searchName == null) searchName = "%";
		else searchName = "%" + searchName + "%";
		
		List<ExamProp> returnList = getJdbcTemplate().query(PageUtil.getPageSql(sql, propQuery.getPageNo(), propQuery
				.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), propQuery.getSysPropId(), searchName);
		for(ExamProp p :returnList){
			p.setQuestionNum(getQuestionNumByPropId(p.getId(),p.getType()));
			
			String propSql = "select i.* from EXAM_ICD_PROP i LEFT JOIN EXAM_ICD_PROP_VAL p on i.ID=p.ICDID where p.PROPID=?";
			p.setIcdList(getJdbcTemplate().query(propSql, ParameterizedBeanPropertyRowMapper.newInstance(ExamICD.class), p.getProp_val_id()));
		}
		
		r_prop.setReturnList(returnList);
		r_prop.setTotal_count(getJdbcTemplate().queryForInt(sql_size,propQuery.getSysPropId(), searchName));
		return r_prop;
	}

}
