package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.SystemCardOrder;
import com.hys.framework.service.BaseService;
/**
 * 
 * 业务订单管理
 * SystemCardOrderManage
 * 创建人:chy
 * 时间：2017-4-18-下午8:19:57 
 * @version 1.0.0
 *
 */
public interface SystemCardOrderManage extends BaseService {

	
	/**
	 * 
	 * 通过用户id和项目id查询订单是否存在
	 * 方法名：findByUidProid
	 * 创建人：程宏业
	 * 时间：2017-4-18-下午8:19:15 
	 * @param uid
	 * @param proid
	 * @return List<SystemCardOrder>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<SystemCardOrder> findByUidProid(Long uid,Long proid,String CardNumeber);
	
	/**
	 * 替代	public List<SystemCardOrder> findByUidProid(Long userid,Long proid,String cardNumber);
	 * @param userid
	 * @param proid
	 * @return
	 */
	public Boolean findByUidProid2(Long uid, Long proid);
	
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
	
	
	/**
	 * 
	 * 各种查询根据传参的不同
	 * 方法名：findAllList
	 * 创建人：程宏业
	 * 时间：2017-4-20-下午1:02:33 
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
