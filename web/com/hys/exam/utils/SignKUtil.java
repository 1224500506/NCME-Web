package com.hys.exam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

import com.hys.exam.model.SystemUser;

public class SignKUtil {

	public SignKUtil(){}
	
	/**
	 * @desc K值认证生成码 【目前根据部分用户信息及时间戳】
	 * @author taoliang
	 * @return
	 */
	public static String getSignK(SystemUser sysUser){
		//获取当前时间
		String newMonth="";
		String newDay="";
		String newHour="";
		String newMinute="";
		String newSecond="";
		Calendar cal=Calendar.getInstance();//使用日历类  
		String year=cal.get(Calendar.YEAR)+"";//得到年  
		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1  
		if(month<10){
			newMonth = "0"+month;
		}
		else{
			newMonth = String.valueOf(month);
		}
		//天
		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		if(day<10){
			newDay = "0"+day;
		}
		else{
			newDay = String.valueOf(day);
		}	
		//小时
		int hour= cal.get(Calendar.HOUR_OF_DAY);
		if(hour<10){
			newHour = "0"+hour;
		}
		else{
			newHour = String.valueOf(hour);
		}	
		//分钟
		int minute=cal.get(Calendar.MINUTE);
		if(minute<10){
			newMinute = "0"+minute;
		}
		else{
			newMinute = String.valueOf(minute);
		}
		//秒
		int second=cal.get(Calendar.SECOND);
		if(second<10){
			newSecond = "0"+second;
		}
		else{
			newSecond = String.valueOf(second);
		}
		Long userId = sysUser.getId();
		String timestamp = year+"-"+newMonth+"-"+newDay+" "+newHour+":"+newMinute+":"+newSecond;
		String signK = MD5Util.string2MD5(userId + timestamp);
		
		return signK;
	}

	public static void main(String[] args) {
		
		SignKUtil s = new SignKUtil();
		String str = s.getSignK(new SystemUser());
		System.out.print("加密signK："+str);
		//String jie = MD5Util.convertMD5(str);
		//System.out.print("解密signK："+jie);
	}
	
	

}
