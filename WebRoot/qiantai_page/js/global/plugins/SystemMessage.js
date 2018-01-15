	
//保存系统消息
function saveMessage(userid,msg){
		var url ="/zyy_qiantai/userInfo/myMessageManage.do?method=saveMessage&system_user_id="+userid+"&message_content="+msg;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'text',
		    success: function(data){
		    }
		});	
		
	}	
	
	