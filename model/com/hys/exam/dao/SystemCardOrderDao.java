package com.hys.exam.dao;

import java.util.List;

import com.hys.exam.model.SystemCardOrder;

/**
 * 
 * 业务订单表
 * SystemCardOrderDao
 * 创建人:CHY
 * 时间：2017-4-18-下午7:42:06 
 * @version 1.0.0
 *
 */
public interface SystemCardOrderDao {

	/**
	 * 
	 * 根据项目的id和用户的id查询用户是否支付对应的项目
	 * 方法名：find
	 * 创建人：程宏业
	 * 时间：2017-4-18-下午7:43:45 
	 * @param userid
	 * @param proid
	 * @return List<SystemCardOrder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCardOrder> find(Long userid,Long proid,String cardNumber);
	
	/**
	 * 替代	public List<SystemCardOrder> find(Long userid,Long proid,String cardNumber);
	 * @param userid
	 * @param proid
	 * @return
	 */
	public Boolean find2(Long userid, Long proid);
	
	/***
	 * 
	 * 添加订单信息
	 * 方法名：AddCardOrder
	 * 创建人：程宏业
	 * 时间：2017-4-18-下午8:24:43 
	 * @param systemCardOrder void
	 * @exception 
	 * @since  1.0.0
	 */
	
	public void AddCardOrder(SystemCardOrder systemCardOrder);
	
	
	 /***
	  * 
	  * 通过id查询用户已绑定项目的所有订单
	  * 方法名：findListByUid
	  * 创建人：程宏业
	  * 时间：2017-4-20-上午11:55:14 
	  * @param uid
	  * @return List<SystemCardOrder>
	  * @exception 
	  * @since  1.0.0
	  */
	public List<SystemCardOrder> findListByUid(Long uid);
	
	
	/***
	 * 
	 * 各种查询
	 * 方法名：findAllList
	 * 创建人：程宏业
	 * 时间：2017-4-20-下午12:58:59 
	 * @param T
	 * @return List<SystemCardOrder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCardOrder> findAllList(SystemCardOrder T);
	
	
	/***
	 * 
	 * 通过卡号查询绑卡列表
	 * 方法名：findListByCardNumber
	 * 创建人：程宏业
	 * 时间：2017-5-3-上午9:56:13 
	 * @param T
	 * @return List<SystemCardOrder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCardOrder> findListByCardNumber(SystemCardOrder T);
	
	/**
	 * @author taoliang
	 * @param queryParam
	 * @return
	 * @desc 获取用户使用了卡的项目信息
	 */
	public List<SystemCardOrder> getListForUseCvSet(SystemCardOrder queryParam); 
	
}
