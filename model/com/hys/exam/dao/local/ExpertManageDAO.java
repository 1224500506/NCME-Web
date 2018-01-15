package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;

/**
 * 专家信息管理
 * 
 * @author Han
 * 
 */
public interface ExpertManageDAO {

    List<ExpertInfo> getExpertList(ExpertInfo expert, PageList pl);

    List<ExpertInfo> getExpertList(ExpertInfo expert);

    Integer getExpertListSize(ExpertInfo expert);

    ExpertInfo getExpertInfo(Long id);

    public List<CVSet> getCVSetFromExpert(ExpertInfo e, Integer type);

    boolean addExpertInfo(ExpertInfo expert) throws Exception;

    boolean updateExpertInfo(ExpertInfo expert) throws Exception;

    boolean deleteExpertInfo(Long id);

    /**
     * @param ExpertInfo
     * @return List
     * @time 2017-01-04
     * @author 张建国 方法说明： 查询专家列表不分页 备 注： 张建国新增代码
     */
    List<ExpertInfo> getExpertListNoPage(ExpertInfo expert);

    /**
     * @param pl
     * @param queryCVSet
     */
    void getExpertList(Pager<ExpertInfo> pl, ExpertInfo expert);
    
    /**
     * @author B.Sky
     * @param pl
     * @param search
     */
    void getExpertListFromSearch(PageList<ExpertInfo> pl, String search);
    /**
     * 根据项目id查询专家
     */
    public List<ExpertInfo> getExpertListByCvSetId(Long CvSetId);
}
