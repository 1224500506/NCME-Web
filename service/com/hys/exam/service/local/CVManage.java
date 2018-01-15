package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.BaseService;

public interface CVManage extends BaseService {

    List<CV> getCVList(CV queryCV);

    List<Long> getCVIdByTeacherId(Long teacherId, Integer cvType);

    List<CVSet> getCVSetFromCVList(List<Long> cvIdList);

    boolean addCV(CV cv);

    boolean addCVUnit(CVUnit cvUnit);

    boolean updateCV(CV cv);

    boolean delete(CV cv);

    List<CVUnit> getCVUnitList(CVUnit cvUnit);

    List<CVUnit> getCloneCVUnitList(CV queryCV);

    int cloneCVUnitList(CV queryCV);

    List<CV> getCloneCVList(CV queryCV);

    List<ExpertInfo> getTeacherList();

    void addUnionUpdate(CV queryCV);

    boolean deleteUnit(Long id);

    void swapCVUnit(CVUnit unit1, CVUnit unit2);

    void updateCVUnit(CVUnit cvUnit);

    void updateUnion(CVUnit cvUnit);

    List<CVUnit> getCVUnitList(CV queryCV);

    void queryCVPageList(PageList<CV> pl, CV queryCv,SystemUser user);
    
    void queryCVPager(Pager<CV> pg, CV queryCv);

    void queryCVPageList2(Pager<CV> pl, CV queryCv);
    
    public List<ExpertInfo> getManagerList(Long id);

	void queryCVPageList2(PageList<CV> pl, CV queryCVSet);
	
	//直播相关列表
	void queryCVLivePageList(PageList<CV> pl, CV queryCVSet);
	void queryCVLivePageList(Pager<CV> pl, CV queryCVSet);
	//对课程进行省份过滤
	public List<CV> cvForProvinceManager(List<CV> list,SystemUser user);
	
	/**
	 * @author taoliang
	 * @param cv
	 * @return
	 * @desc 查询课程通用方法---无其他业务逻辑掺杂
	 */
	public List<CV> queryCVForCommunal(CV cv);
	
	/**
	 * @author taoliang
	 * @param cvSetId
	 * @return
	 * @desc 判断一个项目中是否只存在直播项目
	 */
	public boolean isExistOtherCVForLiveProject(Long cvSetId);
	public boolean isExistOtherCVForLiveProject1(Long cvSetId);
}
