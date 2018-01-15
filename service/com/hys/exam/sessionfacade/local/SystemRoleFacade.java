package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 facade
 * 
 * 说明:
 */
public interface SystemRoleFacade {

	public List<SystemRole> getListByItem(SystemRole item);

	public void querySystemRoleList(Page<SystemRole> page,
			SystemRole item);

	public Integer save(SystemRole item);

	public SystemRole getItemById(Long id);

	public int update(SystemRole item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);
}