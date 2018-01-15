var flag = false;
var  flags = false; 
$(document).ready(function(){ 
//直播相关提取js
$('#drag').drag();
$(".close,button[name=submit]").click(function () {
    $(".popup").hide();
});
$("button[name=submit]").click(function () {
    window.localStorage.setItem("isLogin","true");
});
$("#submit_btn").click(function(){
   		if($('#userName').val() == ""){
   			alert("请输入用户名/手机/邮箱！");
   			return;
   		}
   		if($('#password').val() == ""){
   			alert("请输入密码！");
   			return;
   		}
   		if(!validate()){
			return;
		}
   	 	/*if(!flag)
		{    
			alert("请完成验证码验证！");
			return;
		} */
		$("#submit_btn").attr("disabled","disabled");
   		//通过AJAX实现登录功能
   		$.ajax({
			type: 'POST',
			url: ctxJS + '/loginAJAX.do',
			data:$("#loginForm").serialize(),
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message == "success"){
				$("#submit_btn").removeAttr("disabled");
					//刷新页面
					var id=$('#id').val();
					var isLive = $("#isLive").val();
					var costType = $("#costType").val();
					if(isLive == "true"){//此处用于判断用户进入直播的情况
						var zbcvid = $("#zbcvid").val();
						var zhiboType = $("#timerCvType"+zbcvid).val();
						if(zhiboType == 4){
							checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
							if(flags ==true){
								window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+zbcvid+"&unitId="+0;
								return;
							}
						}
						$.ajax({//根据课程信息拿到项目信息
							type: 'POST',
							url: ctxJS + '/BasicSubject.do?mode=queryCVsetByCVId&cvId='+zbcvid,
							dataType: 'JSON',
							success : function(data){
								var result = eval(data);
								if(result.message=='success'){
									$("#zbcvsetid").val(result.cvSetId);
									checkbind(result.cvSetId,costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
									if(flags ==true){
										$("#isLive").val("false");
 	 									window.location.href = ctxJS + "/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
 	 								}
								}
							},
							error: function(e){
								$(".popup").hide();
							}
	    				}); 
					}else{
						location.href = ctxJS + "/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class";
					}
				}else{
					if(result.message=="checkNumberNull"){
						alert("验证码为空！");
					}
					else if(result.message=="checkNumberError"){
						alert("验证码错误！");
					}
					else if(result.message=="userNull"){
						alert("此用户不存在！");
					}
					else if(result.message=="passwordError"){
						alert("密码错误！");
					}
					else if(result.message=="userNameNull"){
						alert("用户名为空！");
					}
				}
			},
			error: function(e){
				//alert("登录超时......");
			}
        });
   	});

//***************************************  	
   	// 取消
    $("#closebox").click(function(){
    	$(".paybox").hide();
    	$(".bgc").hide();
    })
    
    // 取消按钮
    $("#closebox2").click(function(){
    	$(".paybox").hide();
    	$(".bgc").hide();
    })

    	
});
	    	
    
    
   
   
   
   