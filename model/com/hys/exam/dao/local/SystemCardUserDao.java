package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.SystemCard;
import com.hys.exam.model.SystemCardTypeCvSet;
import com.hys.exam.model.system.SystemCardUser;

public interface SystemCardUserDao  {

	/**
	 * 
	 * 更具用户id和项目id查询学习卡是否存在
	 * 方法名：findByUserid
	 * 创建人：程宏业
	 * 时间：2017-4-17-下午5:25:10 
	 * @param uid
	 * @return List<SystemCardUser>
	 * @exception 
	 * @since  1.0.0
	 */
	List<SystemCard> findByUserid(Long uid,Long proid);
	
	
	/***
	 * 
	 * 查询学习项目需不需要绑卡通过id查询
	 * 方法名：findByporId
	 * 创建人：程宏业
	 * 时间：2017-4-18-下午1:54:11 
	 * @param proid
	 * @return List<SystemCardTypeCvSet>
	 * @exception 
	 * @since  1.0.0
	 */
	List<SystemCardTypeCvSet>  findByporId(Long proid);
	
	
	/***
	 * 
	 *通过用户id和卡号查询学习卡是否存在
	 * 方法名：findLIstByUidandCardNumber
	 * 创建人：程宏业
	 * 时间：2017-4-19-下午6:09:16 
	 * @param uid
	 * @param cardNumber
	 * @return List<SystemCard>
	 * @exception 
	 * @since  1.0.0
	 */
	List<SystemCard> findLIstByUidandCardNumber(Long uid,String cardNumber); 
	
	/**
	 * 
	 * 根据卡类型查询关联的项目
	 * 方法名：findListByCardType
	 * 创建人：程宏业
	 * 时间：2017-4-19-下午6:35:12 
	 * @param proid
	 * @return List<SystemCardTypeCvSet>
	 * @exception 
	 * @since  1.0.0
	 */
	List<SystemCardTypeCvSet> findListByCardType(Long cardtype);
	
	
	
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
	 * 时间：2017-4-24-下午3:41:54 
	 * @param systemCard void
	 * @exception 
	 * @since  1.0.0
	 */
    
    public void UpdateCard(SystemCard systemCard);
	
   
    /**
     * 
     * 通过项目id，学习卡卡号查找学习卡
     * 方法名：findByUseridNumber
     * 创建人：程宏业
     * 时间：2017-4-29-下午6:52:36 
     * @param proid
     * @param cardNumber
     * @return List<SystemCard>
     * @exception 
     * @since  1.0.0
     */
    List<SystemCard> findByUseridNumber(Long proid,String cardNumber);
    
    
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
