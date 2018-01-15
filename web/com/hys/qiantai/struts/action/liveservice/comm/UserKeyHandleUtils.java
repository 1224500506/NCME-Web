package com.hys.qiantai.struts.action.liveservice.comm;
/**
 * @author taoliang
 * @desc UserKeyHandleUtils
 */


public class UserKeyHandleUtils {
	
	public UserKeyHandleUtils(){}
	
	public static long inputUserKey(Long uid){
		long newUserKey = 0L;
		if(uid != null){
			if(java.util.regex.Pattern.compile("^[0-9]*$").matcher(uid.toString()).matches()){
				newUserKey = uid + 1000000000;
			}
		}
		return newUserKey;
	}
	
	public static long outputUserKey(Long uid){
		long newUserKey = 0L;
		if(uid != null){
			if(java.util.regex.Pattern.compile("^[0-9]*$").matcher(uid.toString()).matches()){
				newUserKey = uid - 1000000000;
			}
		}
		return newUserKey;
	}
	
	 public static void main(String [] agrs){
		 long num = UserKeyHandleUtils.inputUserKey(123L);
		 long num1 = UserKeyHandleUtils.outputUserKey(1000000123L);
		 System.out.println(num);
		 System.out.println(num1);
	 }
}
