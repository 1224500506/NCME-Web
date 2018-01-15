package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExpertGroupDAO;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 专家委员会信息管理
 * 
 * @author Han
 * 
 */
public class ExpertGroupManageImpl extends BaseMangerImpl implements
        ExpertGroupManage {

    private ExpertGroupDAO localExpertGroupDAO;

    public ExpertGroupDAO getLocalExpertGroupDAO() {
        return localExpertGroupDAO;
    }

    public void setLocalExpertGroupDAO(ExpertGroupDAO localExpertGroupDAO) {
        this.localExpertGroupDAO = localExpertGroupDAO;
    }

    @Override
    public List<ExpertGroup> getExpertGroupList(ExpertGroup group) {
        return localExpertGroupDAO.getExpertGroupList(group);
    }

    @Override
    public List<ExpertGroup> getExpertGroupList(ExpertGroup group, PageList pl) {
        return localExpertGroupDAO.getExpertGroupList(group, pl);
    }

    public Integer getExpertGroupListSize(ExpertGroup group) {
        return localExpertGroupDAO.getExpertGroupListSize(group);
    }

    @Override
    public ExpertGroup getExpertGroup(ExpertGroup group) {
        return localExpertGroupDAO.getExpertGroup(group);
    }

    @Override
    public ExpertGroup getExpertGroup(Long id) {
        return localExpertGroupDAO.getExpertGroup(id);
    }

    @Override
    public List<ExpertGroup> getExpertGroupfromName(ExpertGroup group) {
        return localExpertGroupDAO.getExpertGroupfromName(group);
    }

    @Override
    public boolean addExpertGroup(ExpertGroup group) {
        return localExpertGroupDAO.addExpertGroup(group);
    }

    @Override
    public boolean updateExpertGroup(ExpertGroup group) {
        return localExpertGroupDAO.updateExpertGroup(group);
    }

    @Override
    public boolean deleteExpertGroup(ExpertGroup group) {
        return localExpertGroupDAO.deleteExpertGroup(group);
    }

    @Override
    public boolean deleteExpertGroup(Long id) {
        return localExpertGroupDAO.deleteExpertGroup(id);
    }

    @Override
    public List<ExpertGroupTerm> getTermList(ExpertGroup group) {
        return localExpertGroupDAO.getTermList(group);
    }

    @Override
    public ExpertGroupTerm getExpertGroupTerm(ExpertGroupTerm term) {
        return localExpertGroupDAO.getExpertGroupTerm(term);
    }

    @Override
    public boolean addExpertGroupTerm(ExpertGroupTerm term) {
        return localExpertGroupDAO.addExpertGroupTerm(term);
    }

    @Override
    public boolean updateExpertGroupTerm(ExpertGroupTerm term) {
        return localExpertGroupDAO.updateExpertGroupTerm(term);
    }

    @Override
    public boolean deleteExpertGroupTerm(ExpertGroupTerm term) {
        return localExpertGroupDAO.deleteExpertGroupTerm(term);
    }

    @Override
    public List<ExpertInfo> getGroupExpertInfo(Long groupId, int... args) {
        return localExpertGroupDAO.getGroupExpertInfo(groupId, args);
    }

    @Override
    public void getExpertGroupList(ExpertGroup group, Pager<ExpertGroup> pl) {
        localExpertGroupDAO.getExpertGroupList(group, pl);
    }
    /**
     * @param String, PageList
     * @return List
     * @time 2017-01-026
     * @author B.Sky
     */
	@Override
	public void getExpertGroupListFromSearch(String search,
			PageList<ExpertGroup> pl) {
		// TODO Auto-generated method stub
		localExpertGroupDAO.getExpertGroupListFromSearch(search, pl);
	}
	
    @Override
    public List<ExpertInfo> getGroupExpertInfo(Long groupId) {
        return localExpertGroupDAO.getGroupExpertInfo(groupId);
    }
}
