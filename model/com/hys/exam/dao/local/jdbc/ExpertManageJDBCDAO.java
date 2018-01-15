package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.exam.utils.StringUtils;
/**
 * 专家委员会管理
 * @author Han
 *
 */
public class ExpertManageJDBCDAO extends BaseDao implements
	ExpertManageDAO {
	 
	/**
	 * 查询专家列表
	 */
	@SuppressWarnings("unchecked")
	public List<ExpertInfo> getExpertList(ExpertInfo expert,PageList pl) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select e.* from expert_info e ");
		
		//添加学科条件
		if(!StringUtils.checkNull(expert.getPropIds())){
			String propIds = expert.getPropIds().substring(0, expert.getPropIds().length()-1);
			sql.append("where e.id in (select expertid from EXPERT_PROP_VAL where PROPID in (").append(propIds).append(")) ");
		}
		else
			sql.append("where e.id>0 ");
		
		//添加加入委员会条件
		if(!StringUtils.checkNull(expert.getGroupIds())){
			String ids = expert.getGroupIds().replace('"', ' ');
			sql.append(" and e.id in (select expertid from EXPERT_GROUP_VAL where GROUPID in (").append(ids).append(")) ");
		}
		
		//添加其他条件
		List list = new ArrayList();
		if(!StringUtils.checkNull(expert.getName())){
			sql.append(" and e.name like '%").append(expert.getName()).append("%' ");
		}
		if(null != expert.getGroupId() && -1 != expert.getGroupId()){
			sql.append(" and e.groupid = ? ");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId() && 0 != expert.getSubGroupId()){
			sql.append(" and e.subgroupid = ? ");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice() && 0 != expert.getOffice()){
			sql.append(" and e.office = ? ");
			list.add(expert.getOffice());
		}
		if(null != expert.getJob() && 0 != expert.getJob()){
			sql.append(" and e.job = ? ");
			list.add(expert.getJob());
		}
		if(null != expert.getTerm() && 0 != expert.getTerm()){
			sql.append(" and e.term = ? ");
			list.add(expert.getTerm());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append(" and e.workunit like '%").append(expert.getWorkUnit()).append("%' ");
		}
		if(null != expert.getState() && 0 != expert.getState()){
			sql.append(" and e.state = ? ");
			list.add(expert.getState());
		}
		if(null != expert.getLockState() && 0 != expert.getLockState()){
			sql.append(" and e.lockState = ? ");
			list.add(expert.getLockState());
		}
		if(null != expert.getPersonage() && expert.getPersonage().equals(1))
		{
			sql.append(" and e.personage = ? ");
			list.add(expert.getPersonage());
		}
		
		//演示信息
		//sql.append(" and e.name in ('李兰娟','刘志红','祝墡珠','孙颖浩','励建安','李凌江','唐佩福','魏丽惠','郎景和','黄敏') ");
		sql.append("order by e.code");
		return getList(PageUtil.getPageSql(sql.toString(), pl.getPageNumber(), pl.getObjectsPerPage()), list, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
	}
	
	/**
	 * @param    ExpertInfo
	 * @return   List
	 * @time     2017-01-04
	 * @author   张建国
	 * 方法说明： 查询专家列表不分页
	 * 备          注： 张建国新增代码
	 */
	@Override
	public List<ExpertInfo> getExpertListNoPage(ExpertInfo expert){
		//添加其他条件
        StringBuffer sql = new StringBuffer();
		sql.append("select e.* from expert_info e where e.personage=1 ");
		sql.append(" order by convert(e.name using gbk) ");
		return getJdbcTemplate().query(sql.toString() ,ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));		
	}

	public Integer getExpertListSize(ExpertInfo expert) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(distinct e.id) from expert_info e ");
		
		//添加学科条件
		if(!StringUtils.checkNull(expert.getPropIds())){
			String propIds = expert.getPropIds().substring(0, expert.getPropIds().length()-1);
			sql.append("where e.id in (select expertid from EXPERT_PROP_VAL where PROPID in (").append(propIds).append(")) ");
		}
		else
			sql.append("where e.id>0 ");
		
		//添加加入委员会条件
		if(!StringUtils.checkNull(expert.getGroupIds())){
			String ids = expert.getGroupIds().replace('"', ' ');
			sql.append(" and e.id in (select expertid from EXPERT_GROUP_VAL where GROUPID in (").append(ids).append(")) ");
		}
		if(!StringUtils.checkNull(expert.getName())){
			sql.append(" and e.name like '%").append(expert.getName()).append("%' ");
		}
		if(null != expert.getGroupId() && -1 != expert.getGroupId()){
			sql.append(" and e.groupid = ").append(expert.getGroupId());
		}
		if(null != expert.getSubGroupId() && 0 != expert.getSubGroupId()){
			sql.append(" and e.subgroupid = ").append(expert.getSubGroupId());
		}
		if(null != expert.getOffice() && 0 != expert.getOffice()){
			sql.append(" and e.office = ").append(expert.getOffice());
		}
		if(null != expert.getJob() && 0 != expert.getJob()){
			sql.append(" and e.job = ").append(expert.getJob());
		}
		if(null != expert.getTerm() && 0 != expert.getTerm()){
			sql.append(" and e.term = ").append(expert.getTerm());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append(" and e.workunit like '%").append(expert.getWorkUnit()).append("%' ");
		}
		if(null != expert.getState() && 0 != expert.getState()){
			sql.append(" and e.state = ").append(expert.getState());
		}
		if(null != expert.getLockState() && 0 != expert.getLockState()){
			sql.append(" and e.lockState = ").append(expert.getLockState());
		}
		if(null != expert.getPersonage() && expert.getPersonage().equals(1))
		{
			sql.append(" and e.personage = ").append(expert.getPersonage());
		}
		sql.append(" order by e.code");
		return getJdbcTemplate().queryForInt(sql.toString());
	}
	public List<ExpertInfo> getExpertList(ExpertInfo expert) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select e.* from expert_info e ");
		
		List list = new ArrayList();
		
		//添加学科条件
		if(!StringUtils.checkNull(expert.getPropIds())){
			String propIds = expert.getPropIds().substring(0, expert.getPropIds().length()-1);
			sql.append("where e.id in (select expertid from EXPERT_PROP_VAL where PROPID in (?)) ");
			list.add(propIds);
		}
		else
			sql.append("where e.id>0 ");
		
		//添加加入委员会条件
		if(!StringUtils.checkNull(expert.getGroupIds())){
			String ids = expert.getGroupIds().replace('"', ' ');
			sql.append(" and e.id in (select expertid from EXPERT_GROUP_VAL where GROUPID in (?)) ");
			list.add(ids);
		}
		

		if(!StringUtils.checkNull(expert.getName())){
			sql.append(" and e.name like '%").append(expert.getName()).append("%' ");
		}
		if(null != expert.getGroupId() && -1 != expert.getGroupId()){
			sql.append(" and e.groupid = ? ");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId() && 0 != expert.getSubGroupId()){
			sql.append(" and e.subgroupid = ? ");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice() && 0 != expert.getOffice()){
			sql.append(" and e.office = ? ");
			list.add(expert.getOffice());
		}
		if(null != expert.getJob() && 0 != expert.getJob()){
			sql.append(" and e.job = ? ");
			list.add(expert.getJob());
		}
		if(null != expert.getTerm() && 0 != expert.getTerm()){
			sql.append(" and e.term = ? ");
			list.add(expert.getTerm());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append(" and e.workunit like '%").append(expert.getWorkUnit()).append("%' ");
		}
		if(null != expert.getState() && 0 != expert.getState()){
			sql.append(" and e.state = ? ");
			list.add(expert.getState());
		}
		if(null != expert.getLockState() && 0 != expert.getLockState()){
			sql.append(" and e.lockState = ? ");
			list.add(expert.getLockState());
		}
		if(null != expert.getPersonage() && expert.getPersonage().equals(1))
		{
			sql.append(" and e.personage = ? ");
			list.add(expert.getPersonage());
		}
		sql.append("order by convert(e.name using gbk)");
		return getJdbcTemplate().query(sql.toString() ,list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
	}
	/**
	 * 取得被选id的专家信息
	 */
	@Override
	public ExpertInfo getExpertInfo(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from expert_info where id=? ");
		ExpertInfo expert =  getJdbcTemplate().queryForObject(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), id);
		
		//取得关联学科
		String GET_PROP = "SELECT p.*, s.id as prop_val_id FROM EXPERT_PROP_VAL e LEFT JOIN SUB_SYS_PROP_VAL s ON e.propid=s.id LEFT JOIN EXAM_PROP_VAL p on s.prop_val_id=p.ID where e.EXPERTID=?";
		List<ExamProp> propList = getJdbcTemplate().query(GET_PROP, ParameterizedBeanPropertyRowMapper.newInstance(ExamProp.class), id);
		expert.setProp(propList);
	
		//取得关联学科
		String GET_GROUP = "SELECT g.* FROM EXPERT_GROUP g LEFT JOIN EXPERT_GROUP_VAL v ON g.id=v.groupid where v.EXPERTID=?";
		List<ExpertGroup> groupList = getJdbcTemplate().query(GET_GROUP, ParameterizedBeanPropertyRowMapper.newInstance(ExpertGroup.class), id);
		expert.setGroup(groupList);
		
		return expert;
	}

	/**
	 * 添加专家
	 */
	
	public boolean existExpertCode(ExpertInfo prop)
	{
		if (prop == null || prop.getCode() == null ) return false;
		String getcode = "";
		if(prop.getId() != null)
		{
			getcode = "select count(1) from expert_info where code='" + prop.getCode() + "' and id<>"+prop.getId();
		}
		else
		{
			getcode = "select count(1) from expert_info where code='" + prop.getCode() +"'";
		}
		int dupkey = getJdbcTemplate().queryForInt(getcode);
		return (dupkey == 0)?false:true;
	}
	
	@Override
	public boolean addExpertInfo(ExpertInfo expert) throws Exception {
		if (existExpertCode(expert))  throw new Exception();
		Long next_id = getNextId("expert_info");
		expert.setId(next_id);
		expert.setLockState(1);
		
		
		String ADD_SQL = "insert into expert_info (id, name, code, groupid, subgroupid, office, term, job, state, breakdate, lockstate, workunit, phone1, phone2, photo, email, clerkname, clerkphone, bank, bankcard, identitynum, summary, username, isnation) values (:id, :name, :code, :groupId, :subGroupId, :office, :term, :job, :state, :breakDate, :lockState, :workUnit, :phone1, :phone2, :photo, :email, :clerkName, :clerkPhone, :bank, :bankCard, :identityNum, :summary, :userName, :isNation)";
		getSimpleJdbcTemplate().update(ADD_SQL, new BeanPropertySqlParameterSource(expert));
		
		//添加专家关联学科
		try{
		String[] propIds = expert.getPropIds().split(",");
		for(String ps: propIds){
			Long pid = Long.valueOf(ps);
			Long vid = getNextId("expert_prop_val");
			String ADD_PROP = "insert into expert_prop_val (id, expertid, propid) values (?,?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, vid, expert.getId(), pid);
		}
		}catch(Exception e){;}

		//添加关联委员会
		try{
		String[] groupIds = expert.getGroupIds().split(",");
		for(String ps: groupIds){
			Long gid = Long.valueOf(ps);
			String ADD_PROP = "insert into expert_group_val (expertid, groupid) values (?,?)";
			getSimpleJdbcTemplate().update(ADD_PROP, expert.getId(), gid);
		}
		}catch(Exception e){;}
		return true;
	}

	/**
	 * 修改专家信息
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public boolean updateExpertInfo(ExpertInfo expert) throws Exception {
		if (existExpertCode(expert))  throw new Exception();
		StringBuffer sql = new StringBuffer();
		sql.append("update expert_info set ");
		List list = new ArrayList();
		
		//设置查询条件信息
		
		if(!StringUtils.checkNull(expert.getName())){
			sql.append("name = ?,");
			list.add(expert.getName());
		}
		if(!StringUtils.checkNull(expert.getCode())){
			sql.append("code = ?,");
			list.add(expert.getCode());
		}
		if(null != expert.getGroupId()){
			sql.append("groupid = ?,");
			list.add(expert.getGroupId());
		}
		if(null != expert.getSubGroupId()){
			sql.append("subgroupid = ?,");
			list.add(expert.getSubGroupId());
		}
		if(null != expert.getOffice()){
			sql.append("office = ?,");
			list.add(expert.getOffice());
		}
		if(null != expert.getTerm()){
			sql.append("term = ?,");
			list.add(expert.getTerm());
		}
		if(null != expert.getJob()){
			sql.append("job = ?,");
			list.add(expert.getJob());
		}
		if(null != expert.getState()){
			sql.append("state = ?,");
			list.add(expert.getState());
		}
		if(null != expert.getBreakDate()){
			sql.append("breakdate = ?,");
			list.add(expert.getBreakDate());
		}
		if(null != expert.getLockState()){
			sql.append("lockstate = ?,");
			list.add(expert.getLockState());
		}
		if(!StringUtils.checkNull(expert.getWorkUnit())){
			sql.append("workunit = ?,");
			list.add(expert.getWorkUnit());
		}
		if(!StringUtils.checkNull(expert.getPhone1())){
			sql.append("phone1 = ?,");
			list.add(expert.getPhone1());
		}
		if(!StringUtils.checkNull(expert.getPhone2())){
			sql.append("phone2 = ?,");
			list.add(expert.getPhone2());
		}
		if(!StringUtils.checkNull(expert.getPhoto())){
			sql.append("photo = ?,");
			list.add(expert.getPhoto());
		}
		if(!StringUtils.checkNull(expert.getEmail())){
			sql.append("email = ?,");
			list.add(expert.getEmail());
		}
		if(!StringUtils.checkNull(expert.getClerkName())){
			sql.append("clerkname = ?,");
			list.add(expert.getClerkName());
		}
		if(!StringUtils.checkNull(expert.getClerkPhone())){
			sql.append("clerkphone = ?,");
			list.add(expert.getClerkPhone());
		}
		if(!StringUtils.checkNull(expert.getBank())){
			sql.append("bank = ?,");
			list.add(expert.getBank());
		}
		if(!StringUtils.checkNull(expert.getBankCard())){
			sql.append("bankcard = ?,");
			list.add(expert.getBankCard());
		}
		if(!StringUtils.checkNull(expert.getIdentityNum())){
			sql.append("identitynum = ?,");
			list.add(expert.getIdentityNum());
		}
		if(!StringUtils.checkNull(expert.getSummary())){
			sql.append("summary = ?,");
			list.add(expert.getSummary());
		}
		if(!StringUtils.checkNull(expert.getUserName())){
			sql.append("username = ?,");
			list.add(expert.getUserName());
		}
		if(null != expert.getIsNation()){
			sql.append("isnation = ?,");
			list.add(expert.getIsNation());
		}

		sql.append(" id = id where id = ?");
		list.add(expert.getId());
		
		getSimpleJdbcTemplate().update(sql.toString(), list.toArray());
		
		if (expert.getPropIds() != null){
			//删除旧的学科信息
			String DEL_PROP = "delete from expert_prop_val where expertid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, expert.getId());
			
			//添加新的学科信息
			try{
			String[] propIds = expert.getPropIds().split(",");
			for(String ps: propIds){
				Long pid = Long.valueOf(ps);
				Long vid = getNextId("expert_prop_val");
				String ADD_PROP = "insert into expert_prop_val (id, expertid, propid) values (?,?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, vid, expert.getId(), pid);
			}
			}catch(Exception e){;}
		}
		
		if (expert.getGroupIds() != null){
			//删除旧的委员会信息
			String DEL_PROP = "delete from expert_group_val where expertid=?";
			getSimpleJdbcTemplate().update(DEL_PROP, expert.getId());
			
			//添加关联委员会
			try{
			String[] groupIds = expert.getGroupIds().split(",");
			for(String ps: groupIds){
				Long gid = Long.valueOf(ps);
				String ADD_PROP = "insert into expert_group_val (expertid, groupid) values (?,?)";
				getSimpleJdbcTemplate().update(ADD_PROP, expert.getId(), gid);
			}
			}catch(Exception e){;}
		}
		return true;
	}

	/**
	 * 删除专家
	 */
	@Override
	public boolean deleteExpertInfo(Long id) {

		String SQL_DEL = "delete from expert_info where id = ?";
		getJdbcTemplate().update(SQL_DEL,id);
		return true;
	}
	
	@Override
	public List<CVSet> getCVSetFromExpert(ExpertInfo e,Integer type)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select cv.* from cv_set_expert as e left join cv_set as cv on e.cv_set_id = cv.id where e.expert_id = ").append(e.getId());
		sql.append(" and e.type = ").append(type);
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(CVSet.class));		
	}

    @Override
    public void getExpertList(Pager<ExpertInfo> pl, ExpertInfo expert) {

        //添加其他条件
        StringBuilder sql = new StringBuilder();
        sql.append("from expert_info e where e.personage=1 and e.lockstate=1");

        String get = "select e.* ";
        String get_count = "select count(e.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count);
        pl.setCount(fullListSize);

        sql.append(" order by convert(e.name using gbk) ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<ExpertInfo> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageOffset(), pl.getPageSize()), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));

        pl.setList(list);
    }

    /**
     * @author B.Sky
     * @param PageList, String
     * @description 查询
     */
	@Override
	public void getExpertListFromSearch(PageList<ExpertInfo> pl, String search) {
		// TODO Auto-generated method stub
		//添加其他条件
        StringBuilder sql = new StringBuilder();
        sql.append("from expert_info e where e.personage=1 and e.lockstate=1 and e.name like ?");

        String get = "select e.* ";
        String get_count = "select count(e.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, "%"+search+"%");
        pl.setFullListSize(fullListSize);

        sql.append(" order by convert(e.name using gbk) ");

        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<ExpertInfo> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class), "%"+search+"%");

        pl.setList(list);
	}

	//根据项目id查询专家
	@Override
	public List<ExpertInfo> getExpertListByCvSetId(Long CvSetId) {
		String getManagerSQL = "select t2.* from cv_set_expert t1, expert_info t2 where t1.expert_id=t2.id and t1.cv_set_id="+CvSetId;
		List<ExpertInfo> expertInfoList = getJdbcTemplate().query(getManagerSQL, ParameterizedBeanPropertyRowMapper.newInstance(ExpertInfo.class));
		return expertInfoList;
	}
	
}
