package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.*;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.qiantai.model.CVSetFavorites;
import com.hys.qiantai.model.CVSetScoreLog;
import com.hys.qiantai.model.MyStudyInfo;

public interface CVSetManageDAO {

    List<CVSet> getCVSetList(CVSet queryCVSet);

    Long addCVSet(CVSet cvSet);

    boolean updateCVSet(CVSet cvSet);

    boolean deleteCVSet(CVSet cvSet);

    public CVSet getCVSetById(Long id, SystemUser user);
    
    

    public int updateDistribute(List<Object> params);

    public List<CVSet> getCVSetListFromUser(MyStudyInfo queryCVSet,
            PageList page);
    
    public List<CVSet> getFavoriteCVSetListFromUser(CVSetFavorites queryCVSet,
            PageList page);

    public Integer getStudentNum(CVSet cv);

    void delFav(String id, String userId);

    int getCVSetListFromUserSize(MyStudyInfo queryCVSet);
    
    int getFavoriteCVSetListFromUserSize(CVSetFavorites queryCVSetFav);

    void queryCVSetPageList(PageList<CVSet> pl, CVSet queryCVSet,SystemUser user);

    void queryCVSetPageList2(Pager<CVSet> pl, CVSet queryCVSet);
    
    void queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet);
    @Deprecated
    void queryCVSetPageListFromSearch(PageList<CVSet> pl, String search,Long proviceId);
    
    public void getBindList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user);

    public CVSet getCVSetById(CVSet query);
    
    public String queryNameCode(Long cvId);

    public List<ExamProp> getExamPropList(Long id);

    public List<ExpertInfo> getManagerList(Long id, int type);
    
    public List<ExpertInfo> getTeacherList1(Long id, int type); //查找直播的授课教师

    public List<CV> getCvList(Long id);

    public List<CVUnit> getUnitList(Long id);

    public List<PeixunOrg> getPeixunOrgList(Long cvSetId);

	void updateCritiqueScoreLog(Long userId, CVSet query);

	void getCVSetScoreLogList(CVSetScoreLog log, Pager<CVSetScoreLog> pl);
	
	boolean getCVSetScoreLogIsExist(CVSetScoreLog log);//人是否已经评价了项目，YHQ 2017-02-12
	void getMyXuekeProjectList(PageList<CVSet> pl, CVSet queryCVSet, SystemUser user); //获取我的学科下的项目列表，YHQ 2017-02-17
	void getMyXuekeMukeList(PageList<CVSet> pl, CVSet queryCVSet);//获取我的学科下的慕课列表，YHQ 2017-02-17，改自queryCVSetPageList3(PageList<CVSet> pl, CVSet queryCVSet)
	
	/**
	 * 
	* @Title: getCVSetByTime 
	* @Description: TODO(查询用户某年度时间内学习的所有项目)
	* @author 程宏业 
	* @date 2017-2-22上午10:27:36 
	* @param @param query
	* @param @return    设定文件 
	* @return List<CVSet>    返回类型 
	* @throws
	 */
	List<CVSet> getCVSetByTime(CVSet query);
	
    public CVSet viewCVSetById(CVSet query);
	
    /**
     * 
     * 通过项目ID查询项目的价格
     * 方法名：toCostById
     * 创建人：程宏业
     * 时间：2017-4-18-上午10:42:10 
     * @param id
     * @return List<CVSet>
     * @exception 
     * @since  1.0.0
     */
    public List<CVSet> toCostById(Long id );
    
    //void queryHaiWaiShiYeCVSetPageList(PageList<CVSet> pl, CVSet queryCVSet);
    void queryHaiWaiShiYeCVSetPageList(Pager<CVSet> pl, CVSet queryCVSet);

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
	 * 
	 * @param pl
	 * @param search
	 * @param proviceId
	 */
	public PageList<CVSet> queryCVSetPageListByNameAndProvice(PageList<CVSet> pl, CVSet cvSet,Long proviceId);
	
	/**
	 * queryCVSetListByZN:根据指南查询项目
	
	 *
	 * @param  @return    设定文件
	 * @return PageList<CVSet>    DOM对象
	 * @throws 
	 * @since  CodingExample　version 1.0
	*/
	public List<CVSet> queryCVSetListByZN();
    /**
     * 培训期数
     * @param id
     * @param userId 
     * @return
     */
	List<CVSetScheduleFaceTeach> getFaceTeach(Long id);
    /**
     * 面授详情
     * @param faceId
     * @return
     */
	CVSetScheduleFaceTeach getFace(Integer faceId);
    /**
     * 保存面授报名
     * @param faceteach
     */
	void saveFaceTeach(SystemUserFaceteach faceteach);
     /**
      * sunny
      * 查找我的面授项目
      * @param cv_set_id
      * @param userId
      * @return
      */
	List<CVSetScheduleFaceTeach> seachFaceTeach(Long cv_set_id, Long userId);
     /**
      * 直播课程列表
      * @param cv_set_id
      * @param cv_type
      * @return
      */
	List<CV> getCvSending(Long cv_set_id, Integer cv_type);
     /**
      * sunny
      * @param cv_set_id
      * @param fid
      * @return
      */
	List<CVSetScheduleFaceTeach> getMyFaceTeach(long cv_set_id, Long fid);
    /**
     * sunny
     * 查找项目信息
     * @param cvSetId
     * @return
     */
	CVSet findCVSetById(Long cvSetId);
	
	public int findCvSetId(String setId);

	public String queryCode(Long cvId);

	/**
	 * @author 王印涛
	 * 2018年1月2日 下午4:29:09
	 * @Description: 获取新评分制度的综合评分
	 * @param cvSetId
	 * @return String
	 */
	String getGrade(Long cvSetId);
}
