package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 专家信息管理
 * 
 * @author Han
 * 
 */
public class ExpertManageManageImpl extends BaseMangerImpl implements
        ExpertManageManage {

    private ExpertManageDAO localExpertManageDAO;

    public ExpertManageDAO getLocalExpertManageDAO() {
        return localExpertManageDAO;
    }

    public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
        this.localExpertManageDAO = localExpertManageDAO;
    }

    @Override
    public List<ExpertInfo> getExpertList(ExpertInfo expert, PageList pl) {
        return localExpertManageDAO.getExpertList(expert, pl);
    }

    /**
     * @param ExpertInfo
     * @return List
     * @time 2017-01-04
     * @author 张建国 方法说明： 查询专家列表不分页 备 注： 张建国新增代码
     */
    @Override
    public List<ExpertInfo> getExpertListNoPage(ExpertInfo expert) {
        return localExpertManageDAO.getExpertListNoPage(expert);
    }

    @Override
    public List<ExpertInfo> getExpertList(ExpertInfo expert) {
        return localExpertManageDAO.getExpertList(expert);
    }

    @Override
    public ExpertInfo getExpertInfo(ExpertInfo expert) {
        return localExpertManageDAO.getExpertInfo(expert.getId());
    }

    @Override
    public ExpertInfo getExpertInfo(Long id) {
        return localExpertManageDAO.getExpertInfo(id);
    }

    @Override
    public boolean addExpertInfo(ExpertInfo expert) throws Exception {
        return localExpertManageDAO.addExpertInfo(expert);
    }

    @Override
    public boolean updateExpertInfo(ExpertInfo expert) throws Exception {
        return localExpertManageDAO.updateExpertInfo(expert);
    }

    @Override
    public boolean deleteExpertInfo(ExpertInfo expert) {
        return localExpertManageDAO.deleteExpertInfo(expert.getId());
    }

    @Override
    public boolean deleteExpertInfo(Long id) {
        return localExpertManageDAO.deleteExpertInfo(id);
    }

    @Override
    public Integer getExpertListSize(ExpertInfo expert) {
        return localExpertManageDAO.getExpertListSize(expert);
    }

    @Override
    public List<CVSet> getCVSetFromExpert(ExpertInfo e, Integer type) {
        return localExpertManageDAO.getCVSetFromExpert(e, type);
    }

    @Override
    public void getExpertList(Pager<ExpertInfo> pl, ExpertInfo expert) {
        localExpertManageDAO.getExpertList(pl, expert);
    }

    /**
     * @param ExpertInfo
     * @return List
     * @time 2017-01-026
     * @author B.Sky
     */
	@Override
	public void getExpertListFromSearch(PageList<ExpertInfo> pl, String search) {
		// TODO Auto-generated method stub
		localExpertManageDAO.getExpertListFromSearch(pl, search);
	}

}
