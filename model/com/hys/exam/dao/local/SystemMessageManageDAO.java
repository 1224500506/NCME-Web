package com.hys.exam.dao.local;

import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.qiantai.model.SystemMessageModel;

public interface SystemMessageManageDAO {
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param user
	 * @param pageList
	 * 
	 * Detail : Get the user's message list.
	 */
	public void getUserMessageList(SystemUser user, PageList<SystemMessageModel> pList);
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Read all message of messageType.
	 */
	public Boolean readAll(Long userId, Integer messageType);
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete all message of messageType.
	 */
	public Boolean deleteAll(Long userId, Integer messageType);
	
	/**
	 * @author Tiger
	 * @time 2017-1-11
	 * @param userId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete all message of messageType.
	 */
	public Boolean deleteMessage(Long messageId, Integer messageType);
	
	/**
	 * 
	* @Title: SaveMessage 
	* 保存系统消息
	* @author 程宏业 
	* @date 2017-2-16下午6:53:26 
	* @param Message    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void SaveMessage(SystemMessageModel SystemMessage);
	
	
	/***
	 * 
	 * 查询系统消息是否存在
	 * 方法名：findMessage
	 * 创建人：xuchengfei 
	 * 时间：2017-3-30-下午2:22:53 
	 * @param SystemMessage
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean findMessage(SystemMessageModel SystemMessage);
	
	
	
	
}
