package com.hys.exam.dao.local.jdbc;

import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.CVSetUnitNoteManageDAO;
import com.hys.exam.model.CVSetUnitNote;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetUnitNoteJDBCDAO.java
 *     
 *     Description       :   单元笔记
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-17                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetUnitNoteJDBCDAO extends BaseDao implements CVSetUnitNoteManageDAO {

	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   void
	 * 方法说明： 添加单元笔记
	 */
	@Override
	public void addCVSetUnitNote(CVSetUnitNote note) {
		String sql = " insert into cv_set_unit_note(cv_set_id,cv_unit_id,system_user_id,note_date,note_content,status,note_type) values(?,?,?,sysdate(),?,2,?) ";
		getJdbcTemplate().update(sql, note.getCvSetId(),note.getCvUnitId(),note.getSystemUserId(),note.getNoteContent(),note.getNote_type());
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-17
	 * @param    CVSetUnitNote
	 * @return   List<CVSetUnitNote>
	 * 方法说明： 查询单元笔记
	 */
	public List<CVSetUnitNote> queryUnitNote(CVSetUnitNote note){
		String sql = "select n.*, u.real_name, u.user_avatar as user_image, u.sex from cv_set_unit_note n, system_user u where n.system_user_id = u.id and n.cv_unit_id = ? and n.system_user_id = ? and n.cv_set_id = ? and n.note_type = ? order by n.NOTE_DATE desc ";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(CVSetUnitNote.class),note.getCvUnitId(),note.getSystemUserId(),note.getCvSetId(),note.getNote_type());
	}

}
