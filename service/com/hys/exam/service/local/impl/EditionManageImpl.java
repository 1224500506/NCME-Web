package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.EditionManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.exam.service.local.EditionManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class EditionManageImpl extends BaseMangerImpl implements EditionManage {

	private EditionManageDAO localEditionManageDAO;

	public EditionManageDAO getLocalEditionManageDAO() {
		return localEditionManageDAO;
	}

	public void setLocalEditionManageDAO(EditionManageDAO localEditionManageDAO) {
		this.localEditionManageDAO = localEditionManageDAO;
	}

	@Override
	public Edition getEditionById(Long id) {
		return localEditionManageDAO.getEditionById(id);
	}

	@Override
	public boolean resortOrderNum(String orderstr) {
		boolean flag = false;
		
		try{
			flag = localEditionManageDAO.resortOrderNum(orderstr);
		}
		catch(Exception e){
			flag = false;
		}
		return flag;
	}

    @Override
    public List<Edition> getEditionList(Edition edtion) {
        try {
            return localEditionManageDAO.getEditionList(edtion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public List<Edition> getEditionListView(Edition edtion, CVSet queryCVSet) {
		return localEditionManageDAO.getEditionListView(edtion, queryCVSet);
	}

	@Override
	public Long addEdition(Edition edition) {
		return localEditionManageDAO.addEdition(edition);
	}

	@Override
	public boolean updateEdition(Edition edition) {
		return localEditionManageDAO.updateEdition(edition);
	}

	@Override
	public boolean deleteEditionById(Long id) {
		return localEditionManageDAO.deleteEditionById(id);
	}
	
}
