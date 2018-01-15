package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;


public interface CVSetOrgManage extends BaseService {

	public List<CVSet> getCVSetOrgByOrgId(CVSet queryCVSet, PageList projectPg);
	public List<CVSet> getCVSetOrgByOrgId(CVSet querySet);
	public PeixunOrg getCurrentOrgById(Long id);
	public int getCVSetOrgByOrgIdSize(CVSet queryCVSet);

}
