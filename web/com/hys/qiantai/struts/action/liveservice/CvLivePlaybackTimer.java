package com.hys.qiantai.struts.action.liveservice;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 同步直播回放记录
 * @author yxt
 *
 */
public class CvLivePlaybackTimer {
	
	private Timer timer;
	private long period = 1 * 30 * 60 * 1000;
	
	public void timerStart(){
		timer = new Timer();
		timer.schedule(new MyTask(), getFirstTime(), period);//延迟30秒后执行，然后每隔30分钟执行一次
	}
	
	public void timerStop(){
		if(timer!=null) {
			timer.cancel();
		}
	}

	private static Date getFirstTime(){//定时器启动时间点为29分钟或者59分钟(具体原因是避免展视互动的数据每天的数据同步出现遗漏)
		Date firstTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 29);
        if(cal.getTimeInMillis()>System.currentTimeMillis()){
        	firstTime = cal.getTime();
        }else{
        	cal.set(Calendar.MINUTE, 59);
        	if(cal.getTimeInMillis()>System.currentTimeMillis()){
        		firstTime = cal.getTime();
        	}else{
        		cal.set(Calendar.MINUTE, 29);
        		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
        		firstTime = cal.getTime();
        	}
        }
		return firstTime;
	}
	
	public static void main(String[] args) {
		getFirstTime();
	}
	
	class MyTask extends TimerTask {
		@Override
		public void run() {
			System.out.println(CvLivePlaybackTimer.class + "--任务执行:时间--" + new Date() + ";间隔--" + period + "毫秒");
			new CvLivePlaybackService().cvLivePlaybackService(1);
		}
	}
	
}
