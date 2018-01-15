package com.hys.exam.service.local;

import com.hys.exam.model.SystemUser;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;
import com.hys.qiantai.model.SystemMessageModel;

public interface SystemMessageManage extends BaseService {

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
	 * @param messageId
	 * @param messageType 0 : all, 1 : System message, 2 : Discuss message
	 * @return
	 * 
	 * Detail : Delete message.
	 */
	public Boolean deleteMessage(Long messageId, Integer messageType);

	
	/**
	 * 
	* @Title: SaveMessage 
	* @Description: TODO(保存用户系统信息)
	* @author 程宏业 
	* @date 2017-2-16下午6:48:02 
	* @param @param Message    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void SaveMessage(SystemMessageModel SystemMessage);
	
	
	
	
	/***
	 * 
	 * 查询消息是否存在
	 * 方法名：findMessage
	 * 创建人：chy
	 * 时间：2017-3-30-下午2:14:21 
	 * @param SystemMessage
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
    public boolean findMessage(SystemMessageModel SystemMessage); 
	
	
	
}
