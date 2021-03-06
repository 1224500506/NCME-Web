package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.system.SystemRoleMenuProp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 manage
 * 
 * 说明:
 */
public interface SystemRoleManage {
	
	public List<SystemRole> getListByItem(SystemRole item);

	public void querySystemRoleList(Page<SystemRole> page,
			SystemRole item);

	public Integer save(SystemRole item);

	public SystemRole getItemById(Long id);

	public int update(SystemRole item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
	
	public List<SystemRoleMenuProp> getRoleMenuList(Integer roleId);
	
	public int setRoleMenuProp(Integer roleId, String menuList);
}