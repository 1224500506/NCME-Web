package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.auth.util.DateUtils;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExpertGroupDAO;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.exam.utils.StringUtils;

/**
 * 专家委员会或学组管理
 * @author Han
 *
 */
public class ExpertGroupJDBCDAO extends BaseDao implements
	ExpertGroupDAO {

	/**
	 * 查询委员会列表
	 * @param group
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpertGroup> getExpertGroupList(ExpertGroup group) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select DISTINCT e.* from expert_group e left join expert_group_term t on e.id=t.groupid ");
		
		//添加学科条件
		if(!StringUtils.checkNull(group.getPropIds())){
			String ids = group.getPropIds().replace('"', ' ');
			sql.append("where e.id in (select groupid from EXPERT_GROUP_PROP_VAL where PROPID in (").append(ids).append(")) ");
		}
		else
			sql.append("where e.id>0 ");

		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(group.getName())){
			sql.append(" and e.name like '%").append(group.getName()).append("%' ");
		}
		if(!StringUtils.checkNull(group.getContact())){
			sql.append(" and e.contact like '%").append(group.getContact()).append("%' ");
		}
		if(null != group.getLockState() && 0 != group.getLockState()){
			sql.append(" and e.lockstate = ? ");
			list.add(group.getLockState());
		}
		if(null != group.getParent()){
			sql.append(" and e.parent = ? ");
			list.add(group.getParent());
		}
		if(!StringUtils.checkNull(group.getStartDate())){
			sql.append(" and t.startdate>=? ");
			Date startDate = DateUtils.parse(group.getStartDate(), "yyyy-MM-dd");
			list.add(startDate);
		}
		if(!StringUtils.checkNull(group.getEndDate())){
			sql.append(" and t.enddate<=? ");
			Long endTime = DateUtils.parse(group.getEndDate(), "yyyy-MM-dd").getTime();
			list.add(new Date(endTime));
		}
        if(null != group.getState()){
            sql.append(" and e.state=? ");
            list.add(group.getState());
        }
		sql.append(" order by e.id desc");
		
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class));

	}

	@SuppressWarnings("unchecked")
	public List<ExpertGroup> getExpertGroupList(ExpertGroup group,PageList pl) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select DISTINCT e.* from expert_group e left join expert_group_term t on e.id=t.groupid ");
		
		//添加学科条件
		if(!StringUtils.checkNull(group.getPropIds())){
			String ids = group.getPropIds().replace('"', ' ');
			sql.append("where e.id in (select groupid from EXPERT_GROUP_PROP_VAL where PROPID in (").append(ids).append(")) ");
		}
		else
			sql.append("where e.id>0 ");

		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(group.getName())){
			sql.append(" and e.name like '%").append(group.getName()).append("%' ");
		}
		if(!StringUtils.checkNull(group.getContact())){
			sql.append(" and e.contact like '%").append(group.getContact()).append("%' ");
		}
		if(null != group.getLockState() && 0 != group.getLockState()){
			sql.append(" and e.lockstate = ? ");
			list.add(group.getLockState());
		}
		if(null != group.getParent()){
			sql.append(" and e.parent = ? ");
			list.add(group.getParent());
		}
		if(!StringUtils.checkNull(group.getStartDate())){
			sql.append(" and t.startdate>=? ");
			Date startDate = DateUtils.parse(group.getStartDate(), "yyyy-MM-dd");
			list.add(startDate);
		}
		if(!StringUtils.checkNull(group.getEndDate())){
			sql.append(" and t.enddate<=? ");
			Long endTime = DateUtils.parse(group.getEndDate(), "yyyy-MM-dd").getTime();
			list.add(new Date(endTime));
		}
        if(null != group.getState()){
            sql.append(" and e.state=? ");
            list.add(group.getState());
        }
		sql.append(" order by e.id desc");
		
		return getList(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), list, ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class));

	}
	/**
	 * 取得委员会信息
	 * @param group
	 * @return
	 */
	@Override
	public ExpertGroup getExpertGroup(ExpertGroup group) {
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from expert_group where id=? ");
		return getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class), group.getId());
	}
	@Override
	public List<ExpertGroup> getExpertGroupfromName(ExpertGroup group) {
		StringBuffer sql = new StringBuffer();
		if(group.getId() != null)
		{
			sql.append("select *  from expert_group where id != ? and name=?");
			return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class),group.getId() , group.getName());
		}
		else
		{
			sql.append("select *  from expert_group where name=?");
			return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class), group.getName());
		}
			
	}
	/**
	 * 取得委员会和所属学科信息
	 * @param id
	 * @return
	 */
	@Override
	public ExpertGroup getExpertGroup(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from expert_group where id=? ");
		if (id != 0){
			ExpertGroup group =  getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class), id);
			
			//取得所属学科
			String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXPERT_GROUP_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where e.GROUPID=?";
			List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), id);
			group.setProp(propList);
			return group;
		}
		else
			return null;
	}

	/**
	 * 添加委员会信息
	 * @param group
	 * @return
	 */
	@Override
	public boolean addExpertGroup(ExpertGroup group) {
		Long next_id = getNextId("expert_group");
		group.setId(next_id);
		group.setLockState(1);
		String ADD_SQL = "insert into expert_group (id, name, contact, parent, phone1, phone2, email, address, summary, note, organizedate, breakdate, state, master, lockstate) values (:id, :name, :contact, :parent, :phone1, :phone2, :email, :address, :summary, :note, :organizeDate, :breakDate, :state, :master, :lockState)";
		getSimpleJdbcTemplate().update(ADD_SQL, new BeanPropertySqlParameterSource(group));

		//添加所属学科信息
		try{
		String[] propIds = group.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("expert_group_prop_val");
			String ADD_PROP = "insert into expert_group_prop_val (id, groupid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, group.getId(), pid);
		}
		}catch(Exception e){;}
		return true;
	}

	/**
	 * 修改委员会信息
	 * @param group
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateExpertGroup(ExpertGroup group) {
		StringBuffer sql = new StringBuffer();
		sql.append("update expert_group set ");
		List list = new ArrayList();
		if(!StringUtils.checkNull(group.getName())){
			sql.append("name = ?,");
			list.add(group.getName());
		}
		if(!StringUtils.checkNull(group.getContact())){
			sql.append("contact = ?,");
			list.add(group.getContact());
		}
		if(null != group.getParent()){
			sql.append("parent = ?,");
			list.add(group.getParent());
		}
		if(!StringUtils.checkNull(group.getPhone1())){
			sql.append("phone1 = ?,");
			list.add(group.getPhone1());
		}
		if(!StringUtils.checkNull(group.getPhone2())){
			sql.append("phone2 = ?,");
			list.add(group.getPhone2());
		}
		if(!StringUtils.checkNull(group.getEmail())){
			sql.append("email = ?,");
			list.add(group.getEmail());
		}
		if(!StringUtils.checkNull(group.getAddress())){
			sql.append("address = ?,");
			list.add(group.getAddress());
		}
		if(!StringUtils.checkNull(group.getSummary())){
			sql.append("summary = ?,");
			list.add(group.getSummary());
		}
		if(!StringUtils.checkNull(group.getNote())){
			sql.append("note = ?,");
			list.add(group.getNote());
		}
		if(null != group.getOrganizeDate()){
			sql.append("organizedate = ?,");
			list.add(group.getOrganizeDate());
		}
		if(null != group.getBreakDate()){
			sql.append("breakdate = ?,");
			list.add(group.getBreakDate());
		}
		if(null != group.getState()){
			sql.append("state = ?,");
			list.add(group.getState());
		}
		if(null != group.getLockState()){
			sql.append("lockstate = ?,");
			list.add(group.getLockState());
		}
		if(!StringUtils.checkNull(group.getMaster())){
			sql.append("master = ?,");
			list.add(group.getMaster());
		}

		sql.append(" id = id where id = ?");
		list.add(group.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());

		if(group.getPropIds() != null){
			
			//删除旧的学科信息
			String DEL_PROP = "delete from expert_group_prop_val where groupid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, group.getId());
			//添加新的学科信息
			try{
			String[] propIds = group.getPropIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("expert_group_prop_val");
				String ADD_PROP = "insert into expert_group_prop_val (id, groupid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, group.getId(), pid);
			}
			}catch(Exception e){;}
		}
		//如果禁用状态，学组成员状态离职
		if(null != group.getLockState() && group.getLockState()!=1){
			Date now = new Date();
			String SET_BREAK = "update EXPERT_INFO e set state=2, breakdate=? where e.subgroupid=? or e.groupid=?";
			String SET_GROUP_LOCK = "update EXPERT_GROUP e set lockstate=2 where e.parent=?";
			getSimpleJdbcTemplate().update(SET_BREAK, now, group.getId(), group.getId());
			getSimpleJdbcTemplate().update(SET_GROUP_LOCK, group.getId());
		}
		return true;
	}

	/**
	 * 删除委员会
	 * @param group
	 * @return
	 */
	@Override
	public boolean deleteExpertGroup(ExpertGroup group) {
		String SQL_DEL = "delete from expert_group where id = ?";
		getJdbcTemplate().update(SQL_DEL,group.getId());
		return true;
	}

	/**
	 * 删除委员会
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteExpertGroup(Long id) {
		String SQL_DEL = "delete from expert_group where id = ?";
		String SQL_SUB = "select count(1) from expert_group where parent = ?";
		Long count = getJdbcTemplate().queryForLong(SQL_SUB, id);
		if (count > 0)
			return false;
		else
			getJdbcTemplate().update(SQL_DEL,id);
		return true;
	}

	/**
	 * 查询所属届期列表
	 * @param group
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpertGroupTerm> getTermList(ExpertGroup group) {

		StringBuffer sql = new StringBuffer();
		sql.append("select t.*, g.name as groupname  from expert_group_term t left join expert_group g on t.groupid=g.id where t.id>0 ");
		List list = new ArrayList();
		if(!StringUtils.checkNull(group.getName())){
			sql.append(" and g.name like '%").append(group.getName()).append("%' ");
		}

		if(null != group.getId() && 0 != group.getId()){
			sql.append(" and t.groupid = ? ");
			list.add(group.getId());
		}
		
		sql.append("order by t.startdate");
		
		return getJdbcTemplate().query(sql.toString(), list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroupTerm.class));

	}

	/**
	 * 取得届期
	 * @param term
	 * @return
	 */
	@Override
	public ExpertGroupTerm getExpertGroupTerm(ExpertGroupTerm term) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from expert_group_term where id=? ");
		try{
			return getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroupTerm.class), term.getId());
		}catch(Exception e){ 
			return term;
		}
	}

	/**
	 * 添加届期
	 * @param term
	 * @return
	 */
	@Override
	public boolean addExpertGroupTerm(ExpertGroupTerm term) {
		Long next_id = getNextId("expert_group_term");
		term.setId(next_id);
		String ADD_SQL = "insert into expert_group_term (id, groupid, startdate, enddate) values (:id, :groupId, :startDate, :endDate)";
		getSimpleJdbcTemplate().update(ADD_SQL, new BeanPropertySqlParameterSource(term));
		return true;
	}

	/**
	 * 修改届期
	 * @param term
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateExpertGroupTerm(ExpertGroupTerm term) {
		StringBuffer sql = new StringBuffer();
		sql.append("update expert_group_term set ");
		List list = new ArrayList();
		if(null != term.getGroupId()){
			sql.append("groupid = ?,");
			list.add(term.getGroupId());
		}
		if(null != term.getStartDate()){
			sql.append("startdate = ?,");
			list.add(term.getStartDate());
		}
		if(null != term.getEndDate()){
			sql.append("enddate = ?,");
			list.add(term.getEndDate());
		}

		sql.append(" id = id where id = ?");
		list.add(term.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		return true;
	}

	/**
	 * 删除届期
	 * @param term
	 * @return
	 */
	@Override
	public boolean deleteExpertGroupTerm(ExpertGroupTerm term) {
		String SQL_DEL = "delete from expert_group_term where id = ?";
		getJdbcTemplate().update(SQL_DEL,term.getId());
		return true;
	}
	
	public Integer getExpertGroupListSize(ExpertGroup group) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select COUNT(DISTINCT e.id) from expert_group e left join expert_group_term t on e.id=t.groupid ");
		
		//添加学科条件
		if(!StringUtils.checkNull(group.getPropIds())){
			String ids = group.getPropIds().replace('"', ' ');
			sql.append("where e.id in (select groupid from EXPERT_GROUP_PROP_VAL where PROPID in (").append(ids).append(")) ");
		}
		else
			sql.append("where e.id>0 ");

		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(group.getName())){
			sql.append(" and e.name like '%").append(group.getName()).append("%' ");
		}
		if(!StringUtils.checkNull(group.getContact())){
			sql.append(" and e.contact like '%").append(group.getContact()).append("%' ");
		}
		if(null != group.getLockState() && 0 != group.getLockState()){
			sql.append(" and e.lockstate = ? ");
			list.add(group.getLockState());
		}
		if(null != group.getParent()){
			sql.append(" and e.parent = ? ");
			list.add(group.getParent());
		}
		if(!StringUtils.checkNull(group.getStartDate())){
			sql.append(" and t.startdate>=? ");
			Date startDate = DateUtils.parse(group.getStartDate(), "yyyy-MM-dd");
			list.add(startDate);
		}
		if(!StringUtils.checkNull(group.getEndDate())){
			sql.append(" and t.enddate<=? ");
			Long endTime = DateUtils.parse(group.getEndDate(), "yyyy-MM-dd").getTime();
			list.add(new Date(endTime));
		}
        if(null != group.getState()){
            sql.append(" and e.state=? ");
            list.add(group.getState());
        }

		sql.append(" order by e.id desc");
		return getJdbcTemplate().queryForInt(sql.toString(), list.toArray());
		
	}

    public List<ExpertInfo> getGroupExpertInfo(Long groupId, int... args) {

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        params.add(groupId);
//注掉的是研发中心的sql
//        sql.append("select t.* , g.office as groupOffice from expert_info t left join expert_group_val g on t.id = g.expertid where g.groupid=? and g.office in (");
        sql.append("select t.* from expert_info t left join expert_office_val eo on t.id = eo.expertid where t.groupid=? and eo.officeid in (");

        for (int i = 0; i < args.length; ++i) {
            if (i == (args.length - 1)) {
                sql.append("?) ");
            } else {
                sql.append("?,");
            }
            params.add(args[i]);
        }
//        sql.append(" and t.lockState = 1 order by convert(t.name using gbk)");
        sql.append(" and t.state=1 and t.lockstate=1 order by convert(t.name using gbk)");

        return getJdbcTemplate().query(
                sql.toString(),
                params.toArray(),
                ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
    }

    @Override
    public void getExpertGroupList(ExpertGroup group, Pager<ExpertGroup> pl) {
        StringBuilder sql = new StringBuilder();

        /***
         * 程宏业
         * 2017-04-04
         * 专委会按学科编号排序
         * **/
        
        sql.append("from expert_group e ");
//        sql.append("left join expert_group_term t on e.id=t.groupid ");
        sql.append("left join expert_group_prop_val t1 on e.id=t1.groupid ");
//        sql.append("left join exam_prop_val t2 on t1.propid=t2.id ");
        sql.append("where e.parent=0 and e.lockstate=1 ");
        sql.append(" group by e.name ");

        String get = "select e.* ";
        String get_count = "select e.* ";

        get_count += sql.toString();
        List<ExpertGroup> fullListSize = getJdbcTemplate().query(get_count,ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class));
        pl.setCount(fullListSize.size());

        sql.append(" order by t1.PROPID ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<ExpertGroup> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class));

        pl.setList(list);
//        pl.setCount(list.size());
    }

    /**
     * @author B.Sky
     * @Description 查询
     */
	@Override
	public void getExpertGroupListFromSearch(String search, PageList pl) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();		
		sql.append("from expert_group e left join expert_group_term t ");
		sql.append("on e.id=t.groupid where e.PARENT= 0 and e.state=1 and e.name like ?");		
		sql.append(" order by e.id desc");
		
		String getCountSQL = "select count(1) " + sql.toString();
		String getListSQL = "select DISTINCT e.* " + sql.toString();
		
		int fullListSize = getJdbcTemplate().queryForInt(getCountSQL, "%"+search+"%");
		pl.setFullListSize(fullListSize);
		
		List<ExpertGroup> list = getJdbcTemplate().query(
                PageUtil.getPageSql(getListSQL, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class), "%"+search+"%");
			
		pl.setList(list);
	}
	
    public List<ExpertInfo> getGroupExpertInfo(Long groupId) {

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        params.add(groupId);
        sql.append("select * from expert_info t where t.subgroupid=? and t.state=1 and t.lockstate=1 order by convert(t.name using gbk)");
        return getJdbcTemplate().query(
                sql.toString(),
                params.toArray(),
                ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
    }
    
}
