package com.hys.auth.util;

public enum MessageUtil {

	RegMessage("欢迎你来到中国继续医学教育网，从今天起我将通过视频、讨论、测试等方式与你一起完成各项目的学习，我会认真考核你，提醒你，不要嫌我烦哦！快来一起进步吧~"),
	
	StudyMessage("Hi,亲爱的 XXX（用户名），您正在学习的XXXXX（项目名称）马上就要过期啦，为避免您的损失，请在XXXX（过期日）前完成该项目的学习！");
	
	
	private String message;

	
	private MessageUtil(String message){
		
		this.message = message;
	}
	
	
	
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	
	
}
