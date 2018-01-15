package com.hys.qiantai.struts.action.liveservice;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 过期项目下线
 * @author yxt
 *
 */
public class CvSetOfflineTimer {
	
	private Timer timer;
	private long period = 24 * 60 * 60 * 1000;
	
	public void timerStart(){
		timer = new Timer();
		timer.schedule(new MyTask(), getFirstTime(), period);//每隔24小时执行一次
	}
	
	public void timerStop(){
		if(timer!=null) {
			timer.cancel();
		}
	}

	private static Date getFirstTime(){//定时器启动时间点为凌晨一点
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        if(cal.getTimeInMillis() < System.currentTimeMillis()){
        	cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
        }
		return cal.getTime();
	}
	
	class MyTask extends TimerTask {
		@Override
		public void run() {
			System.out.println(CvSetOfflineTimer.class + "--任务执行:时间--" + new Date() + ";间隔--" + period + "毫秒");
			new CvSetOfflineService().toDo();
		}
	}
	
}
