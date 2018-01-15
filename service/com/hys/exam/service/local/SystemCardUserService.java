package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.SystemCard;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.system.SystemCardUser;
import com.hys.framework.service.BaseService;

public interface SystemCardUserService extends BaseService {

	/**
	 * 
	 * 通过 用户id查询绑卡列表
	 * 方法名：findListByUserId
	 * 创建人：程宏业
	 * 时间：2017-4-17-下午6:00:20 
	 * @param long1
	 * @return List<SystemCardUser>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCard> findListByUserId(Long uid,Long proid);
	
	
	/**
	 * 
	 * 通过项目id查询学习该项目需不需要绑卡
	 * 方法名：findListByProid
	 * 创建人：程宏业
	 * 时间：2017-4-18-下午1:59:31 
	 * @param proid
	 * @return List<SystemCardTypeCvSet>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCardTypeCvSet> findListByProid(Long proid);
	
	/***
	 * 
	 * 通过用户id和卡号查询学习卡
	 * 方法名：findListByUidAndCardNumber
	 * 创建人：程宏业
	 * 时间：2017-4-19-下午6:14:36 
	 * @param uid
	 * @param cardNumber
	 * @return List<SystemCard>
	 * @exception 
	 * @since  1.0.0
	 */
    public List<SystemCard> findListByUidAndCardNumber(Long uid,String cardNumber);
	
	/***
	 * 
	 * 根据卡类型查询相关联的项目
	 * 方法名：findListByCardType
	 * 创建人：程宏业
	 * 时间：2017-4-19-下午6:31:24 
	 * @param proid
	 * @return List<SystemCardTypeCvSet>
	 * @exception 
	 * @since  1.0.0
	 */
    public List<SystemCardTypeCvSet> findListByCardType(Long cardtype);
    
    
    /***
     * 
     * 通过id查询个人拥有的卡的数量
     * 方法名：findCardListByUid
     * 创建人：程宏业
     * 时间：2017-4-20-上午9:30:44 
     * @param uid
     * @return List<SystemCard>
     * @exception 
     * @since  1.0.0
     */
    public List<SystemCard> findCardListByUid(Long uid);
	
    
    
    /**
     * 
     * 更新学习卡状态
     * 方法名：UpdateCard
     * 创建人：程宏业
     * 时间：2017-4-24-下午3:40:42 
     * @param systemCard void
     * @exception 
     * @since  1.0.0
     */
    public void UpdateCard(SystemCard systemCard);
    
    
  
    /**
     * 
     * 通过项目id和学习卡号查找学习卡
     * 方法名：findByUseridNumber
     * 创建人：程宏业
     * 时间：2017-4-29-下午6:54:52 
     * @param proid
     * @param cardNumber
     * @return List<SystemCard>
     * @exception 
     * @since  1.0.0
     */
    List<SystemCard> findByProidNumber(Long proid,String cardNumber);
    
    
    /***
     * 
     * 绑定卡号之后保存绑卡用户的信息
     * 方法名：SaveBindUserinfor
     * 创建人：程宏业
     * 时间：2017-5-2-下午3:41:14 
     * @param systemCardUser void
     * @exception 
     * @since  1.0.0
     */
    public void SaveBindUserinfor(SystemCardUser systemCardUser);
    
	
    
    
    
    
    /**
     * 
     * 通过卡号和密码查询学习卡
     * 方法名：findCardByCardNumberAndpassword
     * 创建人：程宏业
     * 时间：2017-5-2-下午6:21:24 
     * @param cardNumber
     * @param password
     * @return SystemCard
     * @exception 
     * @since  1.0.0
     */
    
    public  SystemCard findCardByCardNumberAndpassword(String cardNumber,String password);
    
    
    /**
	 * @author taoliang
	 * @param queryParm
	 * @return
	 * @desc 根据学习卡号，拿到卡信息
	 */
	public SystemCard findCardByCardNum(String cardNumber);
	
}
