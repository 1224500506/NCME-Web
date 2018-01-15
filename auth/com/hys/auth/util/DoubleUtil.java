package com.hys.auth.util;

import java.math.BigDecimal;

public class DoubleUtil {

	
	public static double Format(double str){
		
		
		   BigDecimal bg = new BigDecimal(str);  
           double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
          
		return f1;
	}
	
	
	
	
	
	
	
	
}
