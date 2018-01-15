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
    
    	// 提交支付信息
        $("#paysubmit").click(function(){
        	var cardnumberval = $("#cardnumber").val();
        	var cardpwd = $("#cardpwd").val();
        	var zbcvsetid = $("#zbcvsetid").val();
        	var zbcvid = $("#zbcvid").val();
        	if(!cardnumberval){
        		alert("请输入卡号");
        		return false;
        	}
        	if(!cardpwd){
        		alert("请输入密码");
        		return false;
        	}
        	
         	$.ajax({
				type: 'POST',
				url: ctxJS + "/study/logstudy.do?mode=playSubmit&cvsetId="+zbcvsetid+"&cardNumber="+cardnumberval+"&cardPassword="+cardpwd,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'success'){
						//进入直播
			        	window.location.href = ctxJS + "/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
					}else if(result.message == 'notbind'){
						//已评价 YHQ 2017-02-14 1827
						alert("卡状态不可用或类型错误");
						return false;
					}else if(result.message == 'time'){
						alert("您的学习卡已经过期");
						return false;
					}else if(result.message == 'notfind'){
						alert("输入的卡号或密码错误");
						return false;
					}else if(result.message == 'typeno'){
						alert("卡类型无效");
						return false;
					}else if(result.message == 'alreadyBind'){//添加改卡已经绑过的情况处理--taoliang
						alert("此卡已被用户使用！");
						return false;
					}
				}
	    	});
        })
        
        setInterval("timerFun()",1000);//每隔一秒监测直播视频的直播状态
});
    //直播入口操作
    function gotoLiveView(id,zhiboType,costType,startDate,endDate,index) {
    	//alert("old:"+zhiboType);
    	zhiboType = $("#timerCvType"+id).val();
    	//alert("new:"+zhiboType);
    	if(zhiboType == 2){
    		var x1 = document.getElementById("mukeid"+index).innerHTML;
    		var x2 = document.getElementById("teacher"+index).innerHTML;
    		var x3 = document.getElementById("startDate"+index).innerHTML;
    		var x4 = document.getElementById("endDate"+index).innerHTML;
    		var x5 = document.getElementById("introduction"+index).innerHTML;
    		var x6 = document.getElementById("suyu"+index).innerHTML;
    		var x7 = document.getElementById("name"+index).innerHTML;
    		var x8 = document.getElementById("name2"+index).innerHTML;
    		var x9 = document.getElementById("code"+index).innerHTML;
    		var x10 = document.getElementById("costType"+index).innerHTML;
    		window.location.href = "qiantai_page/CvLiveSignup.jsp?teacher="+x2+"&startDate="+x3+"&endDate="+x4+"&introduction="+x5+"&suyu="+x6+"&name="+x7+"&name2="+x8+"&code="+x9+"&id="+x1+"&costType="+x10;
    		return;
    	}else if(zhiboType == 3){
    		var x1 = document.getElementById("number3"+index).innerHTML;
    		var x2 = document.getElementById("teacher"+index).innerHTML;
    		var x3 = document.getElementById("startDate"+index).innerHTML;
    		var x4 = document.getElementById("endDate"+index).innerHTML;
    		var x5 = document.getElementById("introduction"+index).innerHTML;
    		var x6 = document.getElementById("suyu"+index).innerHTML;
    		var x7 = document.getElementById("name"+index).innerHTML;
    		var x8 = document.getElementById("name2"+index).innerHTML;
    		var x9 = document.getElementById("code"+index).innerHTML;
    		
    		window.location.href = "qiantai_page/CvLiveOver.jsp?number3="+x1+"&teacher="+x2+"&startDate="+x3+"&endDate="+x4+"&introduction="+x5+"&suyu="+x6+"&name="+x7+"&name2="+x8+"&code="+x9;
    		return;
		}else if(zhiboType == 4){
			$.ajax({
				type: 'POST',
				url:  ctxJS + '/study/logstudy.do?mode=queryLogin&id='+id,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'noLogin'){
						$("#zbcvid").val(result.id);
						$("#costType").val(costType);//初始化卡类型
						$("#isLive").val("true");
						$(".popup").show();
					}else if(result.message=='success'){
						$("#zbcvid").val(id);//初始化课程ID
						checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
						if(flags ==true){
							var x1 = document.getElementById("number4"+index).innerHTML;
				    		var x2 = document.getElementById("teacher"+index).innerHTML;
				    		var x3 = document.getElementById("startDate"+index).innerHTML;
				    		var x4 = document.getElementById("endDate"+index).innerHTML;
				    		var x5 = document.getElementById("introduction"+index).innerHTML;
				    		var x6 = document.getElementById("suyu"+index).innerHTML;
				    		var x7 = document.getElementById("name"+index).innerHTML;
				    		var x8 = document.getElementById("name2"+index).innerHTML;
				    		var x9 = document.getElementById("code"+index).innerHTML;
							window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+id+"&unitId="+0+"number4="+x1+"&teacher="+x2+"&startDate="+x3+"&endDate="+x4+"&introduction="+x5+"&suyu="+x6+"&name="+x7+"&name2="+x8+"&code="+x9;
						}
					};
				}
		    });
			return;
		}else if(zhiboType == 5){
			alert("直播结束，视频转码中，敬请期待！");
    		return;
		}else{
	    	$.ajax({
					type: 'POST',
					url:  ctxJS + '/study/logstudy.do?mode=queryLogin&id='+id,
					dataType: 'JSON',
					success : function(data){
						var result = eval(data);
						if(result.message == 'noLogin'){
							$("#zbcvid").val(result.id);
							$("#costType").val(costType);//初始化卡类型
							$("#isLive").val("true");
							$(".popup").show();
						}else if(result.message=='success'){
							$.ajax({//根据课程信息拿到项目信息
								type: 'POST',
								url: ctxJS + '/BasicSubject.do?mode=queryCVsetByCVId&cvId='+id,
								dataType: 'JSON',
								success : function(data){
									var result = eval(data);
									if(result.message=='success'){
										$("#zbcvid").val(id);
										$("#zbcvsetid").val(result.cvSetId);
										checkbind(result.cvSetId,costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
										if(flags ==true){
	 	 									window.location.href = ctxJS + "/viewLiveListInterface.do?mode=getSignk&cvId="+id;
	 	 								}
									}
								}
		    				});
						};
					}
		    });
    	}
    }
    
    //验证项目是否需要付费是否绑卡
   function checkbind(cvSetId,costType){
	   	$.ajax({
		    url:ctxJS + "/study/logstudy.do?mode=CheckBind&cvsetId="+cvSetId+"&costType="+costType,
		    type: 'POST',
		    async: false,
		    dataType: 'json',
		    success: function(data){   
		    	var result = eval(data);
				if(result.card == '0'){
					flags = true;
				}else{
				   $(".popup").hide();
				   $(".bgc").show();
				   $(".paybox").show();
					return false;
				}
		    }
		});	 
	   
   }
   
   
   function timerFun(){
	   $("#timerFreshUL li").each(function(index){
   		var _this = $("#timerFreshUL li");
   		var _cvId = $("#cvId"+index).val();
   		//alert(_cvId);starttime=1499989933,endtime=1499990049
   		$.ajax({
				type: 'POST',
				url: ctxJS + "/viewLiveListInterface.do?mode=getZBType&cvId="+_cvId,
				dataType: 'JSON',
				success : function(result){
					//_this.find("#soon"+index).each(function(index){
						//var soonVal = $(this).text();
						//if(soonVal != null && soonVal != ""){
							if(result.typeInt == '1'){
								$("#timerCvType"+_cvId).val("1");
								_this.find("#soon"+index).text("正在播放");
							}
							if(result.typeInt == '2'){
								$("#timerCvType"+_cvId).val("2");
								_this.find("#soon"+index).text("即将开课");
							}
							if(result.typeInt == '3'){
								$("#timerCvType"+_cvId).val("3");
								_this.find("#soon"+index).text("已结束");
							}
							if(result.typeInt == '4'){
								$("#timerCvType"+_cvId).val("4");
								_this.find("#soon"+index).text("观看回放");
							}
							if(result.typeInt == '5'){
								$("#timerCvType"+_cvId).val("5");
								_this.find("#soon"+index).text("已结束");
							}
						//}
					//})
				}
	    	})	
		});
   		/*$("#timerFreshUL li").each(function(index){
			var _timerTimeInputStart = $("#timerTimeInputStart"+index).val();
			var _timerTimeInputEnd = $("#timerTimeInputEnd"+index).val();
			var startDate = new Date((_timerTimeInputStart).replace(/-/g,"/"));
			var endDate = new Date((_timerTimeInputEnd).replace(/-/g,"/"));
			var date = new Date();
			$(this).find(".soon").each(function(index){
				var soonVal = $(this).text();
				if(soonVal != "" && soonVal != "已结束"){
					if(endDate < date){
						$(this).text("已结束");
					}
				}
			})
		})*/
	  /* $(".study_begin").click(function () {
	    	$.ajax({
				type: 'POST',
				url:  ctxJS + '/study/logstudy.do?mode=queryLogin&id='+id,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'noLogin'){
						$(".popup").show();
					}else if(result.message=='success'){
						$(".popup1").show()
					};
				}
	    });
	   });*/
   }  	