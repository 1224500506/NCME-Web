package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.Edition;

public interface EditionManageDAO {
	
	Edition getEditionById(Long id);
	
	List<Edition> getEditionList(Edition edtion);
	
	List<Edition> getEditionListView(Edition edition, CVSet queryCVset);
	
	Long addEdition(Edition edition);
	
	boolean updateEdition(Edition edition);
	
	boolean deleteEditionById(Long id);

	boolean resortOrderNum(String orderstr);

}
