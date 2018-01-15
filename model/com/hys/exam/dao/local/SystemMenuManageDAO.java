package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.system.SystemMenu;


public interface SystemMenuManageDAO {
	public List<SystemMenu> getMenuList(SystemMenu searchCase);
	public Boolean addMenu(SystemMenu menu);
	public Boolean updateMenu(SystemMenu menu);
	public Boolean updateState(Integer id, Integer state);
}
