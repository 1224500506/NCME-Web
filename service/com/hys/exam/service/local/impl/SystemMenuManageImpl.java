package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CaseDAO;
import com.hys.exam.dao.local.ExamImportQuestAttDAO;
import com.hys.exam.dao.local.SystemMenuManageDAO;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.ExamQuestAtt;
import com.hys.exam.model.ExamQuestRelation;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.service.local.ExamImportQuestManage;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class SystemMenuManageImpl extends BaseMangerImpl implements
		SystemMenuManage {

	private SystemMenuManageDAO localSystemMenuManageDAO;
	public SystemMenuManageDAO getLocalSystemMenuManageDAO() {
		return localSystemMenuManageDAO;
	}
	public void setLocalSystemMenuManageDAO(
			SystemMenuManageDAO localSystemMenuManageDAO) {
		this.localSystemMenuManageDAO = localSystemMenuManageDAO;
	}
	
	public List<SystemMenu> getMenuList(SystemMenu searchMenu)
	{
		return localSystemMenuManageDAO.getMenuList(searchMenu);
	}
	public Boolean addMenu(SystemMenu menu)
	{
		return localSystemMenuManageDAO.addMenu(menu);
	}
	public Boolean updateMenu(SystemMenu menu)
	{
		return localSystemMenuManageDAO.updateMenu(menu);
	}
	public Boolean updateState(Integer id,Integer state)
	{
		return localSystemMenuManageDAO.updateState(id, state);
	}
}
