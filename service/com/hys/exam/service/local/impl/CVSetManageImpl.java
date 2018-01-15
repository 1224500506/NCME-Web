package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CVSetManageDAO;
import com.hys.exam.dao.local.ExpertManageDAO;
import com.hys.exam.model.BaseProjectRefModel;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetScheduleFaceTeach;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.SystemUserFaceteach;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.qiantai.model.CVSetFavorites;
import com.hys.qiantai.model.CVSetScoreLog;
import com.hys.qiantai.model.MyStudyInfo;

public class CVSetManageImpl extends BaseMangerImpl implements CVSetManage {

	private CVSetManageDAO localCVSetManageDAO;
	private ExpertManageDAO localExpertManageDAO;
	
	@Override
	public List<CVSet> getCVSetList(CVSet queryCVSet) {
		return localCVSetManageDAO.getCVSetList(queryCVSet);
	}

	public void setLocalExpertManageDAO(ExpertManageDAO localExpertManageDAO) {
		this.localExpertManageDAO = localExpertManageDAO;
	}

	@Override
	public Long addCVSet(CVSet cvSet) {
		return localCVSetManageDAO.addCVSet(cvSet);
	}

	@Override
	public boolean updateCVSet(CVSet cvSet) {
		return localCVSetManageDAO.updateCVSet(cvSet);
	}

	@Override
	public boolean deleteCVSet(CVSet cvSet) {
		return localCVSetManageDAO.deleteCVSet(cvSet);
	}

	public CVSetManageDAO getLocalCVSetManageDAO() {
		return localCVSetManageDAO;
	}

	public void setLocalCVSetManageDAO(CVSetManageDAO localCVSetManageDAO) {
		this.localCVSetManageDAO = localCVSetManageDAO;
	}
	@Override
	public CVSet getCVSetById(Long id, SystemUser user)
	{
		return this.localCVSetManageDAO.getCVSetById(id, user);
	}
	@Override
	public int updateDistribute(List<Object> params)
	{
		return this.localCVSetManageDAO.updateDistribute(params);
	}
	@Override
	public List<CVSet> getCVSetListFromUser(MyStudyInfo queryCVSet, PageList page)
	{
		return this.localCVSetManageDAO.getCVSetListFromUser(queryCVSet, page);
	}
	
	@Override
	public Integer getStudentNum(CVSet cv)
	{
		return this.localCVSetManageDAO.getStudentNum(cv);
	}

	@Override
	public void delFav(String id, String userId) {
		localCVSetManageDAO.delFav(id, userId);
	}

	@Override
	public int getCVSetListFromUserSize(MyStudyInfo queryCVSet) {
		return localCVSetManageDAO.getCVSetListFromUserSize(queryCVSet);
	}
	
	@Override
	public int getFavoriteCVSetListFromUserSize(CVSetFavorites queryCVSetFav) {
		return localCVSetManageDAO.getFavoriteCVSetListFromUserSize(queryCVSetFav);
	}
	
    @Override
    public void queryCVSetPageList(PageList<CVSet> pl, CVSet queryCVSet,SystemUser user) {
        localCVSetManageDAO.queryCVSetPageList(pl, queryCVSet,user);
    }

    @Override
    public void queryCVSetPageList2(Pager<CVSet> pl, CVSet queryCVSet) {
        localCVSetManageDAO.queryCVSetPageList2(pl, queryCVSet);        
    }
    
    @Override
    public void queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet) {
        localCVSetManageDAO.queryCVSetPageList3(pl, queryCVSet);        
    }

    @Override
    public CVSet getCVSetById(CVSet query) {
        return localCVSetManageDAO.getCVSetById(query);
    }

    @Override
    public List<ExamProp> getExamPropList(Long id) {
        return localCVSetManageDAO.getExamPropList(id);
    }

    @Override
    public List<ExpertInfo> getManagerList(Long id, int type) {
        return localCVSetManageDAO.getManagerList(id, type);
    }
    //查找直播的授课教师
/*    @Override
    public List<ExpertInfo> getTeacherList(Long id, int type) {
        return localCVSetManageDAO.getTeacherList(id, type);
    }*/
    
    @Override
    public List<CV> getCvList(Long id) {

        List<CV> list = localCVSetManageDAO.getCvList(id);

        for (int i = 0; i < list.size(); ++i) {
            CV cv = list.get(i);
            cv.setUnitList(localCVSetManageDAO.getUnitList(cv.getId()));
        }

        return list;
    }

	@Override
	public List<CVSet> getFavoriteCVSetListFromUser(CVSetFavorites queryCVSetFav,
			PageList page) {
		return localCVSetManageDAO.getFavoriteCVSetListFromUser(queryCVSetFav, page);
	}
	
	/**
     * @author Tiger.
     * @param pl
     * @param queryCVSet
     * @param user
     * 
     * Detail : Get the bind cv_set list.
     */
	@Override
	public void getBindList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user)
	{
		localCVSetManageDAO.getBindList(pl, queryCVSet, user);
	}

	@Override
	public List<PeixunOrg> getPeixunOrgList(Long cvSetId) {
		return localCVSetManageDAO.getPeixunOrgList(cvSetId);
	}

	@Override
	public void updateCritiqueScoreLog(Long userId, CVSet query) {
		localCVSetManageDAO.updateCritiqueScoreLog(userId, query);
	}
		/**
     * @param XiangMu
     * @return List
     * @time 2017-01-026
     * @author B.Sky
     */
	@Override
	public void queryCVSetPageListFromSearch(PageList<CVSet> pl, CVSet cvSet,Long proviceId) {
			queryCVSetPageListByNameAndProvice(pl, cvSet,proviceId);	
//			localCVSetManageDAO.queryCVSetPageListFromSearch(pl, cvSet.getName(),null);
	}

	/**
	 * @author Han
	 * @time	2017-02-07
	 * 取得项目评论列表
	 */
	@Override
	public void getCVSetScoreLogList(CVSetScoreLog log, Pager<CVSetScoreLog> pl) {
		localCVSetManageDAO.getCVSetScoreLogList(log, pl);
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-12
	 * 人是否已经评价了项目
	 */
	@Override
	public boolean getCVSetScoreLogIsExist(CVSetScoreLog log){
		return localCVSetManageDAO.getCVSetScoreLogIsExist(log) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-17
	 * 获取我的学科下的项目列表
	 */
	@Override
	public void getMyXuekeProjectList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user) {
		localCVSetManageDAO.getMyXuekeProjectList(pl, queryCVSet, user) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-17
	 * 获取我的学科下的慕课列表，YHQ 2017-02-17，改自queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet)
	 */
	@Override
	public void getMyXuekeMukeList(PageList<CVSet> pl, CVSet queryCVSet) {
		localCVSetManageDAO.getMyXuekeMukeList(pl, queryCVSet) ;
	}


	@Override
	public List<CVSet> getCVSetListByTime(CVSet query) {
		// TODO Auto-generated method stub
		return localCVSetManageDAO.getCVSetByTime(query);
	}
	
    @Override
    public CVSet viewCVSetById(CVSet query) {
        return localCVSetManageDAO.viewCVSetById(query);
    }

	@Override
	public List<CVSet> toCostById(Long id) {
		// TODO Auto-generated method stub
		return localCVSetManageDAO.toCostById(id);
	}
	
    @Override
    public void queryHaiWaiShiYeCVSetPageList(Pager<CVSet> pl, CVSet queryCVSet) {
        localCVSetManageDAO.queryHaiWaiShiYeCVSetPageList(pl, queryCVSet);
    }

	@Override
	public List<BaseProjectRefModel> getRefereeBookByCvId(Long id) {
		// TODO Auto-generated method stub
		return localCVSetManageDAO.getRefereeBookByCvId(id);
	}

	@Override
	public List<BaseProjectRefModel> getKnowledgebaseByCvId(Long id) {
		// TODO Auto-generated method stub
		return localCVSetManageDAO.getKnowledgebaseByCvId(id);
	}

	@Override
	public List<BaseProjectRefModel> getReferencelatelyByCvId(Long id) {
		// TODO Auto-generated method stub
		return localCVSetManageDAO.getReferencelatelyByCvId(id);
	}
	//根据省份查询项目信息
	@Override
	public PageList<CVSet>  queryCVSetPageListByNameAndProvice(PageList<CVSet> pl, CVSet cvSet,Long proviceId){
		PageList<CVSet> cvsetListPl=localCVSetManageDAO.queryCVSetPageListByNameAndProvice(pl, cvSet, proviceId);
		//添加专家信息
		List<CVSet> cvsetList=cvsetListPl.getList();
		for(CVSet cvSetNew:cvsetList){
			Long cvSetId=cvSetNew.getId();
			List<ExpertInfo> expertList=localExpertManageDAO.getExpertListByCvSetId(cvSetId);
			cvSetNew.setManagerList(expertList);
		}
		return cvsetListPl;
	}

	/**
	 * (non-Javadoc)
	 * @see com.hys.exam.service.local.CVSetManage#queryByZN()
	 */
	@Override
	public List<CVSet> queryByZN()
	{
		
		// TODO Auto-generated method stub
		return localCVSetManageDAO.queryCVSetListByZN();
		
	}
   /**
    * 培训期数
    */
	@Override
	public List<CVSetScheduleFaceTeach> getFaceTeach(Long cvSetId) {

		return localCVSetManageDAO.getFaceTeach(cvSetId);
	}
    /**
     * 面授详情
     */
	@Override
	public CVSetScheduleFaceTeach getFace(Integer faceId) {
		
		return localCVSetManageDAO.getFace(faceId);
	}
    /**
     * 保存面授报名
     */
	@Override
	public void saveFaceTeach(SystemUserFaceteach faceteach) {
		localCVSetManageDAO.saveFaceTeach(faceteach);
	}

	@Override
	public List<CVSetScheduleFaceTeach> searchFaceTeach(Long cv_set_id, Long userId) {
		return localCVSetManageDAO.seachFaceTeach(cv_set_id,userId);
	}
    /**
     * 直播课程列表
     * @param cv_set_id
     * @param cv_type
     * @return
     */
	@Override
	public List<CV> getCvSending(Long cv_set_id, Integer cv_type) {
		return localCVSetManageDAO.getCvSending(cv_set_id,cv_type);
	}

	@Override
	public List<CVSetScheduleFaceTeach> getMyFaceTeach(long cv_set_id, Long fid) {

		return localCVSetManageDAO.getMyFaceTeach(cv_set_id,fid);
	}

	@Override
	public CVSet findCVSetById(Long cvSetId) {
		return localCVSetManageDAO.findCVSetById(cvSetId);
	}

	@Override
	public int findCvSetId(String setId) {
	
		return  localCVSetManageDAO.findCvSetId(setId);
	}

	@Override
	public String queryNameCode(Long cvId) {
		
		return localCVSetManageDAO.queryNameCode(cvId);
	}

	@Override
	public String queryCode(Long cvId) {
		
		return localCVSetManageDAO.queryCode(cvId);
	}

	/**
	 * @author 王印涛
	 * 2018年1月2日 下午4:26:48
	 * @Description 获取新评分制度的综合评分
	 */
	@Override
	public String getGrade(Long cvSetId) {

		return localCVSetManageDAO.getGrade(cvSetId);
	}

	
}
