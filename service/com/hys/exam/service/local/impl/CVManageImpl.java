package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVManageDAO;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CVManageImpl extends BaseMangerImpl implements CVManage {

    private CVManageDAO localCVManageDAO;

    @Override
    public List<CV> getCVList(CV queryCV) {
        return localCVManageDAO.getCVList(queryCV);
    }

    @Override
    public boolean addCV(CV cv) {
        return localCVManageDAO.addCV(cv);
    }

    @Override
    public boolean updateCV(CV cv) {
        return localCVManageDAO.updateCV(cv);
    }

    @Override
    public boolean delete(CV cv) {
        return localCVManageDAO.deleteCV(cv);
    }

    public CVManageDAO getLocalCVManageDAO() {
        return localCVManageDAO;
    }

    public void setLocalCVManageDAO(CVManageDAO localCVManageDAO) {
        this.localCVManageDAO = localCVManageDAO;
    }

    @Override
    public boolean addCVUnit(CVUnit cvUnit) {

        return localCVManageDAO.addCVUnit(cvUnit);
    }

    @Override
    public List<CVUnit> getCVUnitList(CVUnit cvUnit) {

        return localCVManageDAO.getCVUnitList(cvUnit);
    }

    @Override
    public List<CVUnit> getCloneCVUnitList(CV queryCV) {

        return localCVManageDAO.getCloneCVUnitList(queryCV);
    }

    @Override
    public List<CV> getCloneCVList(CV queryCV) {
        return localCVManageDAO.getCloneCVList(queryCV);
    }

    @Override
    public List<ExpertInfo> getTeacherList() {

        return localCVManageDAO.getTeacherList();
    }

    @Override
    public void addUnionUpdate(CV queryCV) {
        localCVManageDAO.addUnionUpdate(queryCV);

    }

    @Override
    public int cloneCVUnitList(CV queryCV) {
        return localCVManageDAO.cloneCVUnitList(queryCV);
    }

    @Override
    public boolean deleteUnit(Long id) {
        return localCVManageDAO.deleteUnit(id);
    }

    @Override
    public void swapCVUnit(CVUnit unit1, CVUnit unit2) {
        localCVManageDAO.swapCVUnit(unit1, unit2);

    }

    @Override
    public void updateCVUnit(CVUnit cvUnit) {
        localCVManageDAO.updateCVUnit(cvUnit);

    }

    @Override
    public void updateUnion(CVUnit cvUnit) {
        localCVManageDAO.updateUnion(cvUnit);

    }

    @Override
    public List<CVUnit> getCVUnitList(CV queryCV) {

        return localCVManageDAO.getCVUnitList(queryCV);
    }

    @Override
    public List<Long> getCVIdByTeacherId(Long teacherId, Integer cvType) {
        return localCVManageDAO.getCVIdByTeacherId(teacherId, cvType);
    }

    @Override
    public List<CVSet> getCVSetFromCVList(List<Long> cvIdList) {
        return localCVManageDAO.getCVSetFromCVList(cvIdList);
    }

    @Override
    public void queryCVPageList(PageList<CV> pl, CV queryCVSet,SystemUser user) {
        localCVManageDAO.queryCVPageList(pl, queryCVSet,user);
    }

    @Override
    public void queryCVPager(Pager<CV> pg, CV queryCVSet) {
        localCVManageDAO.queryCVPager(pg, queryCVSet);
    }
    
    @Override
    public void queryCVPageList2(Pager<CV> pl, CV queryCv) {///////////////
        localCVManageDAO.queryCVPageList2(pl, queryCv);
    }

    @Override
    public List<ExpertInfo> getManagerList(Long id) {
        return localCVManageDAO.getManagerList(id);
    }

	@Override
	public void queryCVPageList2(PageList<CV> pl, CV queryCv) {
		localCVManageDAO.queryCVPageList2(pl, queryCv);
	}

	@Override
	public void queryCVLivePageList(PageList<CV> pl, CV queryCVSet) {
		localCVManageDAO.queryCVLivePageList(pl, queryCVSet);
	}

	@Override
	public void queryCVLivePageList(Pager<CV> pl, CV queryCVSet) {
		localCVManageDAO.queryCVLivePageList(pl, queryCVSet);
	}

	@Override
	public List<CV> cvForProvinceManager(List<CV> list,SystemUser user) {
		return localCVManageDAO.cvForProvinceManager(list,user);
	}

	@Override
	public List<CV> queryCVForCommunal(CV cv) {
		return localCVManageDAO.queryCVForCommunal(cv);
	}

	@Override
	public boolean isExistOtherCVForLiveProject(Long cvSetId) {
		return localCVManageDAO.isExistOtherCVForLiveProject(cvSetId);
	}
	@Override
	public boolean isExistOtherCVForLiveProject1(Long cvSetId) {
		return localCVManageDAO.isExistOtherCVForLiveProject1(cvSetId);
	}
}
