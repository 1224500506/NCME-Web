package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVSetManageDAO;
import com.hys.exam.dao.local.EditionManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.exam.model.ExCVSet;

/**
 * Lee 2016-11-28
 */

public class EditionManageJDBCDAO extends BaseDao implements EditionManageDAO {

	private EditionManageDAO localEditionManageDAO;
	
	private CVSetManageDAO localCVSetManageDAO;
	
	public EditionManageDAO getLocalEditionManageDAO() {
		return localEditionManageDAO;
	}

	public void setLocalEditionManageDAO(EditionManageDAO localEditionManageDAO) {
		this.localEditionManageDAO = localEditionManageDAO;
	}

	public CVSetManageDAO getLocalCVSetManageDAO() {
		return localCVSetManageDAO;
	}

	public void setLocalCVSetManageDAO(CVSetManageDAO localCVSetManageDAO) {
		this.localCVSetManageDAO = localCVSetManageDAO;
	}

	@Override
	public Edition getEditionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean resortOrderNum(String orderstr) {
		String item[] = orderstr.split(";");
		
		String SORT_SQL = "update content_edition_ref set ordernum=? where ref_id=?";
		
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
	
    @Override
    public List<Edition> getEditionList(Edition edtion) {
        int nType = edtion.getType();

        List<Edition> lst_Edition = new ArrayList<Edition>();

        if (nType == 1) {

            List<Edition> result_list = new ArrayList<Edition>();
            String get_cvset_sql = "select ref_id as id, ordernum from content_edition_ref where edition_id=? order by ordernum asc";

            List<ExCVSet> queryIdList = getJdbcTemplate().query(get_cvset_sql, ParameterizedBeanPropertyRowMapper.newInstance(ExCVSet.class), edtion.getId());

            if (queryIdList.size() > 0) {
                CVSet queryCVSet = new CVSet();
                for (int i = 0; i < queryIdList.size(); i++) {
                    Edition edition = new Edition();
                    queryCVSet.setId(queryIdList.get(i).getId());

                    List<CVSet> cvSetList = localCVSetManageDAO.getCVSetList(queryCVSet);

                    List<ExCVSet> ex_cvSetList = new ArrayList<ExCVSet>();
                    for (CVSet cvs : cvSetList) {
                        ExCVSet ex_cvs = new ExCVSet(cvs);
                        ex_cvs.setOrdernum(queryIdList.get(i).getOrdernum());
                        ex_cvSetList.add(ex_cvs);
                    }

                    edition.setId(queryIdList.get(i).getOrdernum());
                    edition.setCvSetList(ex_cvSetList);

                    result_list.add(edition);
                }
            }

            return result_list;
        } else {
            String bName = edtion.getName();
            String tName = edtion.getTitle();
            String kName = edtion.getKind();

            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM content_edition Where id>0 ");

            if (!StringUtil.checkNull(bName)) {
                if (bName.equals("1"))
                    sql.append(" AND name like '%首页%'");
                else if (bName.equals("2"))
                    sql.append(" AND name like '%募课%'");
            }
            if (!StringUtil.checkNull(tName)) {
                if (tName.equals("1"))
                    sql.append(" AND title like '%推荐项目%'");
                else if (tName.equals("2"))
                    sql.append(" AND title like '%推荐课程%'");
                else if (tName.equals("3"))
                    sql.append(" AND title like '%远程%'");
                else if (tName.equals("4"))
                    sql.append(" AND title like '%远程+面授%'");
                else if (tName.equals("5"))
                    sql.append(" AND title like '%VR%'");
                else if (tName.equals("5"))
                    sql.append(" AND title like '%VR%'");
                else if (tName.equals("6"))
                    sql.append(" AND title like '%募课%'");

            }
            if (!StringUtil.checkNull(kName)) {
                if (!kName.equals("0"))
                    sql.append(" AND type=" + kName);
            }

            lst_Edition = getJdbcTemplate().query(
                    sql.toString(),
                    ParameterizedBeanPropertyRowMapper
                            .newInstance(Edition.class));

            for (Edition edition : lst_Edition) {
                if (lst_Edition != null && lst_Edition.size() > 0) {
                    String sql_schedule = "SELECT count(ID) as n_ContentSize FROM content_edition as tbl_edition "
                            + "LEFT JOIN content_edition_ref as tbl_ref ON tbl_edition.ID=tbl_ref.EDITION_ID "
                            + "where tbl_ref.EDITION_ID=" + edition.getId();
                    int count = getJdbcTemplate().queryForInt(sql_schedule);
                    edition.setN_ContentSize(count);

                }
            }
            return lst_Edition;
        }

    }

	@Override
	public List<Edition> getEditionListView(Edition edition, CVSet queryCVSet)
	{
		return null;
	}
	
	@Override
	public Long addEdition(Edition edition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEdition(Edition edition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEditionById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
