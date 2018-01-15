package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ContentIssueManageDAO;
import com.hys.exam.model.ContentIssue;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;



public class ContentIssueManageJDBCDAO extends BaseDao implements ContentIssueManageDAO {

    @Override
    public void queryIssuePageList(PageList<ContentIssue> pl, ContentIssue issue) {

        StringBuilder sql = new StringBuilder();
        sql.append("from content_issue_site t,content_issue t2,system_site t3 where t.issue_id=t2.id and t.site_id=t3.id and t3.domain_name=? ");

        List<Object> params = new ArrayList<Object>();
        params.add(issue.getServerName());

        String get = "select t2.* ";
        String get_count = "select count(t2.id) ";
//		StringBuilder sql = new StringBuilder(" from content_issue i left join content_issue_site c on i.id=c.ISSUE_ID where i.id>0 ");
//		
//		List<Object> params = new ArrayList<Object>();
//		
//		if (issue.getType()!=null && issue.getType()!=0){
//			sql.append(" and i.type=? ");
//			params.add(issue.getType());
//		}
//		if (issue.getTitle()!=null && !issue.getTitle().equals("")){
//			sql.append(" and i.title like ? ");
//			params.add("%"+issue.getTitle()+"%");
//		}
//		if (issue.getStartDate()!=null && !issue.getStartDate().equals("")){
//			sql.append(" and i.startdate>=? ");
//			params.add(DateUtil.parse(issue.getStartDate(),"yyyy-MM-dd"));
//		}
//		if (issue.getEndDate()!=null && !issue.getEndDate().equals("")){
//			sql.append(" and i.enddate<=? ");
//			params.add(DateUtil.parse(issue.getEndDate(),"yyyy-MM-dd"));
//		}
//		if (issue.getSiteId()!=null && issue.getSiteId()!=0){
//			sql.append(" and c.site_id=? ");
//			params.add(issue.getSiteId());
//		}

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" order by t2.id ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<ContentIssue> list = getJdbcTemplate().query(
                PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(ContentIssue.class), params.toArray());
        pl.setList(list);
    }

	@Override
	public ContentIssue getIssueById(Long id) {
		String GET_SQL = "select * from content_issue where id=?";
		String GET_REF_SQL = "select site_id from content_issue_site where issue_id=?";
		try{
			ContentIssue issue = getJdbcTemplate().queryForObject(GET_SQL, ParameterizedBeanPropertyRowMapper.newInstance(ContentIssue.class), id);
			
			List<Long> siteList = getJdbcTemplate().queryForList(GET_REF_SQL, Long.class, id);
			Long res[] = new Long[]{};
			res = siteList.toArray(res);
			issue.setSiteIds(res);
			return issue;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean insertContentIssue(ContentIssue issue) {
		issue.setId(getSequence("content_issue"));
		String ADD_SQL = "insert into content_issue (id, type, title, startdate, enddate, ordernum, content) value (:id, :type, :title, :startDateObj, :endDateObj, :orderNum, :content)";
		String ADD_REF_SQL = "insert into content_issue_site (issue_id, site_id) value (?,?)";
		
		try{
			getSimpleJdbcTemplate().update(ADD_SQL, new BeanPropertySqlParameterSource(issue));
	
			if (issue.getSiteIds()!=null&& issue.getSiteIds().length>0){
				Long siteIds[] = issue.getSiteIds();
				for (int i = 0; i < siteIds.length; i++)
				{
					getJdbcTemplate().update(ADD_REF_SQL, issue.getId(), siteIds[i]);
				}
			}
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	@Override
	public boolean updateContentIssue(ContentIssue issue) {
		
		String UPDATE_SQL = "update content_issue set id=id ";
		String DEL_REF_SQL = "delete from content_issue_site where issue_id=?";
		String ADD_REF_SQL = "insert into content_issue_site (issue_id, site_id) value (?,?)";
		
		List<Object> params = new ArrayList<Object>();
		
		if (issue.getId()==null || issue.getId() == 0) return false;
		
		if (issue.getType()!=null){
			UPDATE_SQL += ", type=? ";
			params.add(issue.getType());
		}
		if (issue.getTitle()!=null){
			UPDATE_SQL += ", title=? ";
			params.add(issue.getTitle());
		}
		if (issue.getStartDate()!=null){
			UPDATE_SQL += ", startdate=? ";
			params.add(issue.getStartDate());
		}
		if (issue.getEndDate()!=null){
			UPDATE_SQL += ", enddate=? ";
			params.add(issue.getEndDate());
		}
		if (issue.getOrderNum()!=null){
			UPDATE_SQL += ", ordernum=? ";
			params.add(issue.getOrderNum());
		}
		if (issue.getContent()!=null){
			UPDATE_SQL += ", content=? ";
			params.add(issue.getContent());
		}
		
		UPDATE_SQL += " where id=?";
		params.add(issue.getId());
		
		try{
			getJdbcTemplate().update(UPDATE_SQL, params.toArray());
	
			getJdbcTemplate().update(DEL_REF_SQL, issue.getId());
			if (issue.getSiteIds()!=null&& issue.getSiteIds().length>0){
				Long siteIds[] = issue.getSiteIds();
				for (int i = 0; i < siteIds.length; i++)
				{
					getJdbcTemplate().update(ADD_REF_SQL, issue.getId(), siteIds[i]);
				}
			}
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	@Override
	public boolean deleteContentIssue(Long id) {
		String DEL_SQL = "delete from content_issue where id=?";
		String DEL_REF_SQL = "delete from content_issue_site where issue_id=?";
		try{
			getJdbcTemplate().update(DEL_REF_SQL, id);
			getJdbcTemplate().update(DEL_SQL, id);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean resortOrderNum(String orderstr) {
		String item[] = orderstr.split(";");
		
		String SORT_SQL = "update content_issue set ordernum=? where id=?";
		
		try{
			for (int i = 0; i < item.length; i++){
				String val[] = item[i].split("_");
				Long id = Long.valueOf(val[0]);
				Long orderNum = Long.valueOf(val[1]);
				getJdbcTemplate().update(SORT_SQL, orderNum, id);
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
