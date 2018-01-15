package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.BaseService;

/**
 * 专家委员会管理
 * 
 * @author Han
 * 
 */
public interface ExpertGroupManage extends BaseService {

    /**
     * 查询委员会或学组
     * 
     * @param group
     * @return
     */
    List<ExpertGroup> getExpertGroupList(ExpertGroup group);

    List<ExpertGroup> getExpertGroupList(ExpertGroup group, PageList pl);

    void getExpertGroupList(ExpertGroup group, Pager<ExpertGroup> pl);

    public void getExpertGroupListFromSearch(String search, PageList<ExpertGroup> pl);
    
    Integer getExpertGroupListSize(ExpertGroup group);

    /**
     * 取得委员会或学组
     * 
     * @param group
     * @return
     */
    ExpertGroup getExpertGroup(ExpertGroup group);

    /**
     * 取得委员会或学组
     * 
     * @param id
     * @return
     */
    ExpertGroup getExpertGroup(Long id);

    /**
     * 添加委员会或学组
     * 
     * @param group
     * @return
     */
    boolean addExpertGroup(ExpertGroup group);

    /**
     * 修改委员会或学组
     * 
     * @param group
     * @return
     */
    boolean updateExpertGroup(ExpertGroup group);

    /**
     * 删除委员会或学组
     * 
     * @param group
     * @return
     */
    boolean deleteExpertGroup(ExpertGroup group);

    /**
     * 删除委员会或学组
     * 
     * @param id
     * @return
     */
    boolean deleteExpertGroup(Long id);

    /**
     * 查询委员会的届期
     * 
     * @param group
     * @return
     */
    List<ExpertGroupTerm> getTermList(ExpertGroup group);

    /**
     * 查询届期
     * 
     * @param term
     * @return
     */
    ExpertGroupTerm getExpertGroupTerm(ExpertGroupTerm term);

    /**
     * 添加届期
     * 
     * @param term
     * @return
     */
    boolean addExpertGroupTerm(ExpertGroupTerm term);

    /**
     * 修改届期
     * 
     * @param term
     * @return
     */
    boolean updateExpertGroupTerm(ExpertGroupTerm term);

    /**
     * 删除届期
     * 
     * @param term
     * @return
     */
    boolean deleteExpertGroupTerm(ExpertGroupTerm term);

    List<ExpertGroup> getExpertGroupfromName(ExpertGroup group);

    /**
     * 查询学组下专家 主委 副主委 秘书长
     * 
     * @param groupId
     * @return
     */
    List<ExpertInfo> getGroupExpertInfo(Long groupId, int... args);
    
    List<ExpertInfo> getGroupExpertInfo(Long groupId);
}
