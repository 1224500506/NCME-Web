package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;

/**
 * 专家委员会管理
 * 
 * @author Han
 * 
 */
public interface ExpertGroupDAO {

    List<ExpertGroup> getExpertGroupList(ExpertGroup group);

    List<ExpertGroup> getExpertGroupList(ExpertGroup group, PageList pl);

    Integer getExpertGroupListSize(ExpertGroup group);

    ExpertGroup getExpertGroup(ExpertGroup group);

    ExpertGroup getExpertGroup(Long id);

    List<ExpertGroup> getExpertGroupfromName(ExpertGroup group);

    boolean addExpertGroup(ExpertGroup group);

    boolean updateExpertGroup(ExpertGroup group);

    boolean deleteExpertGroup(ExpertGroup group);

    boolean deleteExpertGroup(Long id);

    List<ExpertGroupTerm> getTermList(ExpertGroup group);

    ExpertGroupTerm getExpertGroupTerm(ExpertGroupTerm term);

    boolean addExpertGroupTerm(ExpertGroupTerm term);

    boolean updateExpertGroupTerm(ExpertGroupTerm term);

    boolean deleteExpertGroupTerm(ExpertGroupTerm term);

    public List<ExpertInfo> getGroupExpertInfo(Long groupId, int... args);

    public void getExpertGroupList(ExpertGroup group, Pager<ExpertGroup> pl);
    
    public void getExpertGroupListFromSearch(String search, PageList pl);
    
    public List<ExpertInfo> getGroupExpertInfo(Long groupId);
}
