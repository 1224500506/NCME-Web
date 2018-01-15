package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.SentenceManageDAO;
import com.hys.exam.model.Sentence;
import com.hys.exam.model.SystemSite;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

public class SentenceManageJDBCDAO extends BaseDao implements SentenceManageDAO {

	@Override
	public Sentence getSentenceById(Long id) {
		// TODO Auto-generated method stub
		if(id != null && !id.equals(0L))
		{
			String sql = "select * from content_sentence where id = " + id.toString();
			return getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class));
		}
		
		return null;
	}

	@Override
	public List<Sentence> getSentenceList(Sentence sentence) {
		
		StringBuilder sql = new StringBuilder();
		sql.append( "select DISTINCT cs.* FROM content_sentence cs left join content_sentence_site ss on cs.id=ss.SENTENCE_ID where cs.id>0"); 
		List values = new ArrayList();
		
		String idVal = String.valueOf(sentence.getId());
		if(!StringUtil.checkNull(idVal) && !idVal.equals("null")) {
			sql.append(" AND cs.id =?");
			values.add(idVal);
		}
		if(sentence.getType()!=null &&sentence.getType()!=0 ){
			sql.append(" AND  cs.type = ?");  
			values.add(sentence.getType());
		}
		
		 	if(sentence.getTitle()!=null && !sentence.getTitle().equals("")){
			sql.append(" AND cs.title like ?");
			values.add("%"+sentence.getTitle()+"%");
		}
		 if(sentence.getState()!=null&&sentence.getState()!=0 ){
			sql.append(" AND cs.state=?");
			values.add(sentence.getState());
		}
		if(sentence.getSite_id()!=null && sentence.getSite_id() > 0 ){
			sql.append(" AND ss.site_id=?");
			values.add(sentence.getSite_id());
		}
		if(sentence.getDeli_date()!=null && !sentence.getDeli_date().equals("") ){
			sql.append(" AND cs.deli_date=?");
			
			values.add(DateUtil.parse(sentence.getDeli_date(), "yyyy-MM-dd"));
		}
		sql.append(" order by cs.deli_date desc");
		List<Sentence> list = getJdbcTemplate().query(sql.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class),values.toArray());
		
		for (Sentence s:list) {
			String sql_site = "select t.* from SYSTEM_SITE t, CONTENT_SENTENCE_SITE t2 where t.ID=t2.SITE_ID and t2.SENTENCE_ID=" + s.getId();
			List<SystemSite> siteList = getJdbcTemplate().query(sql_site,ParameterizedBeanPropertyRowMapper.newInstance(SystemSite.class));
			if(siteList.size() > 0)
				s.setSiteList(siteList);
		}
		
		return list;
 }

	@Override
	public  boolean addSentence(Sentence sentence) {
		sentence.setId(this.getSequence("CONTENT_SENTENCE"));
		String sql="insert into CONTENT_SENTENCE (ID,TITLE,TYPE,STATE,ORDERNUM,CONTENT) values(:id,:title,:type,:state,:ordernum,:content)";
		getSimpleJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sentence));
		String site_sql;
		for(int i=0;i<sentence.getSiteList().size();i++){
			 site_sql="insert into content_sentence_site (sentence_id,site_id) values(?,?)";
			 getJdbcTemplate().update(site_sql, sentence.getId(),sentence.getSiteList().get(i).getId());
		}
		//getJdbcTemplate().query(sql,  list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class));
		return true;
	}

	@Override
	public boolean updateSentence(Sentence sentence) {
		/*String sql = "update CONTENT_SENTENCE set STATE=" + state + "  where ID=" + id;
		getJdbcTemplate().execute(sql);*/
		return true;
	}

	@Override
	public boolean deleteSentenceById(Long id) {

		String sql = "delete from CONTENT_SENTENCE_SITE where SENTENCE_ID=" + id;
		getJdbcTemplate().execute(sql);
		
		sql = "delete from CONTENT_SENTENCE where ID=" + id;
		getJdbcTemplate().execute(sql);
		
		return true;
	}

	Date today=new Date();
	@Override
	
	public boolean updateState(Long id, int state) {
	   if(state==1){
		String sql = "update CONTENT_SENTENCE set STATE=" + state + ", DELI_DATE=null where ID=" + id;
		getJdbcTemplate().execute(sql);
		return true;
		   }
		   else{
				String sql = "update CONTENT_SENTENCE set STATE=" + state + ", DELI_DATE='"+DateUtil.format(today,"yyyy-MM-dd")+"' where ID=" + id;
				getJdbcTemplate().execute(sql);
				   return true;
		   }
		
		
	}
	public Boolean updateMenu(Sentence sentence)
	{
		StringBuffer sql = new StringBuffer();
		List values = new ArrayList();
		sql.append("update system_menu set ");
	
		if(sentence.getType()!=null &&sentence.getType()!=0 ){
			sql.append("  cs.type = ?");  
			values.add(sentence.getType());
		}
		
		 	if(sentence.getTitle()!=null && !sentence.getTitle().equals("")){
			sql.append(" cs.title=?");
			values.add("%"+sentence.getTitle()+"%");
		}
		 if(sentence.getState()!=null&&sentence.getState()!=0 ){
			sql.append(" cs.state=?");
			values.add(sentence.getState());
		}
		if(sentence.getSite_id()!=null && sentence.getSite_id() > 0 ){
			sql.append("  ss.site_id=?");
			values.add(sentence.getSite_id());
		}
		if(sentence.getDeli_date()!=null && !sentence.getDeli_date().equals("") ){
			sql.append("  cs.deli_date=?");
			
			values.add(DateUtil.parse(sentence.getDeli_date(), "yyyy-MM-dd"));
		}
		String idVal = String.valueOf(sentence.getId());
		if(!StringUtil.checkNull(idVal) && !idVal.equals("null")) {
			sql.append("where id = ?");
			values.add(idVal);
		}
		  values.add(sentence.getId());
		int result = getJdbcTemplate().update(sql.toString(),values.toArray());
		if(result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

    @Override
    public void querySentencePageList(PageList<Sentence> pl, Sentence sentence) {

        StringBuilder sql = new StringBuilder();
        sql.append("FROM content_sentence_site t,content_sentence t2,system_site t3 WHERE t.SENTENCE_ID=t2.id AND t.site_id=t3.id AND t3.domain_name=? and t2.state=? AND t2.type=? ");

        List<Object> params = new ArrayList<Object>();
        params.add(sentence.getServerName());
        params.add(sentence.getState());
        params.add(sentence.getType());

        String get = "select t2.* ";
        String get_count = "select count(t2.id) ";

        get_count += sql.toString();
        int fullListSize = getJdbcTemplate().queryForInt(get_count, params.toArray());
        pl.setFullListSize(fullListSize);

        sql.append(" order by t2.ordernum asc,t2.deli_date ");
        if (pl.getSortDirection() != null
                && pl.getSortDirection() == SortOrderEnum.ASCENDING) {
            sql.append(" asc");
        } else{
            sql.append(" desc");
        }

        get += sql.toString();
        List<Sentence> list = getJdbcTemplate().query(
        		PageUtil.getPageSql(get, pl.getPageNumber(), pl.getObjectsPerPage()), ParameterizedBeanPropertyRowMapper.newInstance(Sentence.class), params.toArray());
        pl.setList(list);
    }
}
