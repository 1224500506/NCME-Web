package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.*;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.BaseService;
import com.hys.qiantai.model.CVSetFavorites;
import com.hys.qiantai.model.CVSetScoreLog;
import com.hys.qiantai.model.MyStudyInfo;

public interface CVSetManage extends BaseService {

    List<CVSet> getCVSetList(CVSet queryCVSet);

    void queryCVSetPageList(PageList<CVSet> pl, CVSet queryCVSet,SystemUser user);

    void queryCVSetPageList2(Pager<CVSet> pl, CVSet queryCVSet);
    
    void queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet);
    
    void queryCVSetPageListFromSearch(PageList<CVSet> pl,CVSet cvSet,Long proviceId);
    
    public void getBindList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user);

    Long addCVSet(CVSet cvSet);

    boolean updateCVSet(CVSet cvSet);

    boolean deleteCVSet(CVSet cvSet);

    public CVSet getCVSetById(Long Id, SystemUser user);
    
    public String queryCode(Long cvId);
    
    public String queryNameCode(Long cvId);

    public int updateDistribute(List<Object> saveParams);

    public List<CVSet> getCVSetListFromUser(MyStudyInfo queryCVSet,
            PageList page);

    public List<CVSet> getFavoriteCVSetListFromUser(CVSetFavorites queryCVSet,
            PageList page);
    
    public Integer getStudentNum(CVSet cv);

    void delFav(String id, String userId);

    int getCVSetListFromUserSize(MyStudyInfo queryCVSet);

    int getFavoriteCVSetListFromUserSize(CVSetFavorites queryCVSetFav);
    
    public CVSet getCVSetById(CVSet query);

    public List<ExamProp> getExamPropList(Long id);

    public List<ExpertInfo> getManagerList(Long id, int type);
    
   /* public List<ExpertInfo> getTeacherList(Long id, int type); //查找直播的授课教师
*/
    public List<CV> getCvList(Long id);

    public List<PeixunOrg> getPeixunOrgList(Long cvSetId);

	void updateCritiqueScoreLog(Long userId, CVSet query);

	void getCVSetScoreLogList(CVSetScoreLog log, Pager<CVSetScoreLog> pl);
	
	boolean getCVSetScoreLogIsExist(CVSetScoreLog log);//人是否已经评价了项目，YHQ 2017-02-12
	
	void getMyXuekeProjectList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user); //获取我的学科下的项目列表，YHQ 2017-02-17
	void getMyXuekeMukeList(PageList<CVSet> pl, CVSet queryCVSet);//获取我的学科下的慕课列表，YHQ 2017-02-17，改自queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet)
	
	
	/** 查询在年度时间段内当前用户所有学过的项目
	 *  程宏业
	 *  2017-2-22
	 * 
	 * ***/
	
	public List<CVSet> getCVSetListByTime(CVSet query);

	/***
	 * 
	 * (项目预览)
	 * 方法名：viewCVSetById
	 * 创建人： 
	 * 时间：2017-3-30-上午11:40:53 
	 * @param query
	 * @return CVSet
	 * @exception 
	 * @since  1.0.0
	 */
	public CVSet viewCVSetById(CVSet query);
	
	
	/***
	 * 
	 *通过项目ID查询项目的价格
	 * 方法名：toCostById
	 * 创建人：程宏业
	 * 时间：2017-4-18-上午10:40:34 
	 * @param id
	 * @return List<CVSet>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<CVSet> toCostById(Long id);
	
	//void queryHaiWaiShiYeCVSetPageList(PageList<CVSet> pl, CVSet queryCVSet);
	void queryHaiWaiShiYeCVSetPageList(Pager<CVSet> pl, CVSet queryCVSet);

	/**
	 * 书籍查询
	 * @param id
	 * @return
	 */
	public List<BaseProjectRefModel> getRefereeBookByCvId(Long id);
	/**
	 * 指南共识查询
	 * @param id
	 * @return
	 */
	public List<BaseProjectRefModel> getKnowledgebaseByCvId(Long id);
	/**
	 * 最新文献
	 * @param id
	 * @return
	 */
	public List<BaseProjectRefModel> getReferencelatelyByCvId(Long id);
	/**
	 *	根据名称和省份id查询项目 
	 */
	public PageList<CVSet> queryCVSetPageListByNameAndProvice(PageList<CVSet> pl, CVSet cvSet,Long proviceId);

	/**
	 * queryByZN:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 *
	 * @param      设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　version 1.0
	*/
	
	public List<CVSet> queryByZN();
    /**
     * sunny
     * 培训期数
     * @param id
     * @param long1 
     * @return
     */
	public List<CVSetScheduleFaceTeach> getFaceTeach(Long cvSetId);
 /**
  * sunny
  * @param faceId
  * @return
  */
	public CVSetScheduleFaceTeach getFace(Integer faceId);
    /**
     *
     * 保存面授报名
     */
    public void saveFaceTeach(SystemUserFaceteach faceteach);
    /**
     * 查找我的面授项目详情
     * @param cv_set_id
     * @param userId
     * @return
     */
	List<CVSetScheduleFaceTeach> searchFaceTeach(Long cv_set_id, Long userId);
    /**
     * 直播课程列表
     * @param cv_set_id
     * @param cv_type
     * @return
     */
	List<CV> getCvSending(Long cv_set_id, Integer cv_type);
    /**
     * 查找我的学习面授期数
     * @param cv_set_id
     * @param fid
     * @return
     */
	List<CVSetScheduleFaceTeach> getMyFaceTeach(long cv_set_id, Long fid);
    /**
     * 查找项目信息
     * sunny
     * @param cvSetId
     * @return
     */
	CVSet findCVSetById(Long cvSetId);

	
	public int findCvSetId(String setId);

	/**
	 * @author 王印涛
	 * 2018年1月2日 下午4:26:01
	 * @Description:查找新评分制度的评分
	 * @param cvSetId
	 * @return CVSet
	 */
	String getGrade(Long cvSetId);
	
}
