package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;
import com.hys.framework.service.BaseService;

public interface EditionManage extends BaseService {
	
	Edition getEditionById(Long id);
	
	List<Edition> getEditionList(Edition edtion);
	
    List<Edition> getEditionListView(Edition edition, CVSet queryCVSet);
	
	Long addEdition(Edition edition);
	
	public boolean resortOrderNum(String orderstr);
	
	boolean updateEdition(Edition edition);
	
	boolean deleteEditionById(Long id);
}
