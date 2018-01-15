package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.Pager;
import com.hys.framework.service.BaseService;

/**
 * 专家管理
 * @author Han
 *
 */
public interface ExpertManageManage extends BaseService {

	/**
	 * 查询专家列表
	 * @param expert
	 * @return
	 */
	List<ExpertInfo> getExpertList(ExpertInfo expert,PageList pl);
	
	/**
	 * @param    ExpertInfo
	 * @return   List
	 * @time     2017-01-04
	 * @author   张建国
	 * 方法说明： 查询专家列表不分页
	 * 备          注： 张建国新增代码
	 */
	List<ExpertInfo> getExpertListNoPage(ExpertInfo expert);
	
	List<ExpertInfo> getExpertList(ExpertInfo expert);
	
	Integer getExpertListSize(ExpertInfo expert); 
	/**
	 * 取得专家
	 * @param expert
	 * @return
	 */
	ExpertInfo getExpertInfo(ExpertInfo expert);
	
	/**
	 * 取得专家
	 * @param id
	 * @return
	 */
	ExpertInfo getExpertInfo(Long id);
	
	/**
	 * 添加专家
	 * @param expert
	 * @return
	 * @throws Exception 
	 */
	boolean addExpertInfo(ExpertInfo expert) throws Exception;
	
	/**
	 * 修改专家
	 * @param expert
	 * @return
	 */
	boolean updateExpertInfo(ExpertInfo expert) throws Exception;
	
	/**
	 * 删除专家
	 * @param expert
	 * @return
	 */
	boolean deleteExpertInfo(ExpertInfo expert);
	
	/**
	 * 删除专家
	 * @param id
	 * @return
	 */
	boolean deleteExpertInfo(Long id);
	
	public List<CVSet> getCVSetFromExpert(ExpertInfo e,Integer type);

	/**
     * @param pl
     * @param queryCVSet
     */
    void getExpertList(Pager<ExpertInfo> pl, ExpertInfo expert);
    
    /**
     * @Description Search Expert Info
     * @author B.Sky
     * @param pl
     * @param search
     */
    void getExpertListFromSearch(PageList<ExpertInfo> pl, String search);
}
