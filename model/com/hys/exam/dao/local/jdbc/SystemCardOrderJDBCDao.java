package com.hys.exam.dao.local.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.StringUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.SystemCardOrderDao;
import com.hys.exam.model.SystemCardOrder;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;

public class SystemCardOrderJDBCDao extends BaseDao implements SystemCardOrderDao {

	
	
	@Override
	public List<SystemCardOrder> find(Long userid, Long proid,String cardNumber) {
		// TODO Auto-generated method stub
	
		if(StringUtils.isNotEmpty(cardNumber)){
			String sql ="select * from system_card_set_order as card_order WHERE  card_order.USER_ID ="+userid+" and card_order.CV_SET_ID ="+proid+" and card_order.CARD_NUMBER ='"+cardNumber+"'";
			
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrder.class));
		}else{
			String sql ="select * from system_card_set_order as card_order WHERE  card_order.USER_ID ="+userid+" and card_order.CV_SET_ID ="+proid+"";
			
			return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrder.class));
			
		}
		
	
	}
	
	@Override
	public Boolean find2(Long userid, Long proid) {
		
		String sql ="SELECT count(*) FROM system_card_user scu LEFT JOIN system_card sc ON scu.CARD_ID = sc.ID "
				+ "LEFT JOIN system_card_type_cv_set sctcs ON sc.CARD_TYPE = sctcs.CARDTYPE_ID "
				+ "WHERE scu.USER_ID = " + userid + " AND sctcs.CV_SET_ID = " + proid;
		
		return getJdbcTemplate().queryForInt(sql) > 0;
		
	}

	@Override
	public void AddCardOrder(SystemCardOrder T) {
		// TODO Auto-generated method stub
	String sql = "insert into system_card_set_order (CARD_TYPE_ID,USER_ID," +
			"USEFUL_DATE,PRICE,AMOUNT,PAY_DATE,PAYMODE_CODE,ORDER_NUMBER," +
			"STATUS,ORDER_TYPE,CV_SET_ID,CARD_NUMBER,ITEM_NAME)VALUES("+T.getCARD_TYPE_ID()+"," +
					""+T.getUSER_ID()+",'"+(new SimpleDateFormat("yyyy-MM-dd").format(T.getUSEFUL_DATE())+" 23:59:59")+"'," +
							""+T.getPRICE()+","+T.getAMOUNT()+",'"+T.getPAY_DATE()+"'," +
									"'"+T.getPAYMODE_CODE()+"',"+T.getORDER_NUMBER()+","+T.getSTATUS()+","+T.getORDER_TYPE()+","+T.getCV_SET_ID()+","+T.getCARD_NUMBER()+",'"+T.getITEM_NAME()+"')";
	

		getJdbcTemplate().update(sql);

	}

	@Override
	public List<SystemCardOrder> findListByUid(Long uid) {
		// TODO Auto-generated method stub
		String sql ="select * from system_card_set_order as card_order WHERE  card_order.USER_ID ="+uid+"";
		
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrder.class));
	}

	
	@Override
	public List<SystemCardOrder> findAllList(SystemCardOrder T) {
		// TODO Auto-generated method stub
		String sql ="";
		if(T.getSTATUS()==0){
				 sql ="select * from system_card_set_order as card_order,system_card_type as card_type WHERE  card_order.USER_ID ="+T.getUSER_ID()+" and card_type.ID = card_order.CARD_TYPE_ID AND UNIX_TIMESTAMP(card_order.USEFUL_DATE) < UNIX_TIMESTAMP(NOW()) group by  card_order.PAY_DATE DESC ";
		}else{
				sql ="select * from system_card_set_order as card_order,system_card_type as card_type WHERE  card_order.USER_ID ="+T.getUSER_ID()+" and  card_type.ID = card_order.CARD_TYPE_ID AND UNIX_TIMESTAMP(card_order.USEFUL_DATE) > UNIX_TIMESTAMP(NOW()) group by  card_order.PAY_DATE DESC  ";
		}
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrder.class));
	}

	@Override
	public List<SystemCardOrder> findListByCardNumber(SystemCardOrder T) {
		// TODO Auto-generated method stub
		
	 String	sql ="select * from system_card_set_order as card_order,system_card_type as card_type WHERE  card_type.ID = card_order.CARD_TYPE_ID and card_order.CARD_NUMBER = '"+T.getCARD_NUMBER()+"'";
		
		
	 return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrder.class));
	}

	@Override
	public List<SystemCardOrder> getListForUseCvSet(SystemCardOrder queryParam) {
		String	sql ="select * from system_card_set_order t where t.CARD_TYPE_ID = "+queryParam.getCARD_TYPE_ID()+" and t.CARD_NUMBER= '"+queryParam.getCARD_NUMBER()+"' and t.USER_ID = "+queryParam.getUSER_ID()+" and t.CV_SET_ID="+queryParam.getCV_SET_ID()+"";
		return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SystemCardOrder.class));
	}	
	
}
