package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.utils.PageList;


public interface CVSetOrgDAO {
	
	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet);
	
	public PeixunOrg getCurrentOrgById(Long id);

	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet, PageList projectPg);

	public int getCVSetOrgIdSize(CVSet queryCVSet);
}
