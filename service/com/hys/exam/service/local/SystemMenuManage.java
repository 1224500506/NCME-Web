package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.system.SystemMenu;
import com.hys.framework.service.BaseService;

public interface SystemMenuManage extends BaseService {
	public List<SystemMenu> getMenuList(SystemMenu searchMenu);
	public Boolean addMenu(SystemMenu menu);
	public Boolean updateMenu(SystemMenu menu);
	public Boolean updateState(Integer id,Integer state);
}
