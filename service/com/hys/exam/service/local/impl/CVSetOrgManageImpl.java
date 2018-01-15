package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVSetOrgDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetOrgManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;


public class CVSetOrgManageImpl extends BaseMangerImpl implements
	CVSetOrgManage {
	
	CVSetOrgDAO localCVSetOrgDAO;
	
	public CVSetOrgDAO getLocalCVSetOrgDAO() {
		return localCVSetOrgDAO;
	}

	public void setLocalCVSetOrgDAO(CVSetOrgDAO localCVSetOrgDAO) {
		this.localCVSetOrgDAO = localCVSetOrgDAO;
	}

	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet)
	{
		return localCVSetOrgDAO.getCVSetOrgByOrgId(querySet);
	}
	public PeixunOrg getCurrentOrgById(Long id)
	{
		return localCVSetOrgDAO.getCurrentOrgById(id);
	}

	@Override
	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet, PageList projectPg) {
		return localCVSetOrgDAO.getCVSetOrgByOrgId(querySet, projectPg);
	}

	@Override
	public int getCVSetOrgByOrgIdSize(CVSet queryCVSet) {
		
		return localCVSetOrgDAO.getCVSetOrgIdSize(queryCVSet);
	}
}
