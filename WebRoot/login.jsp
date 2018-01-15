<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <% 
  Object obj = session.getAttribute("random");
  String random =null;
    if(obj!=null && !"".equals(obj)) 
  {
    	random = obj.toString();
    } 		
    
    %>
<head lang="en">
   	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=0,maximum-scale=1.0,user-scalable=yes" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="中国继续医学教育网">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="">
    <meta name="description" content="">
    <title>中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery-1.7.2.min.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
   
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
     <script src="${ctx}/qiantai_page/js/drag.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
    
    
    <style>
    
    .yzmbg{
    
    display: block;
    height: 44px;
    line-height: 44px;
    color: #fff;
    text-align: center;
    margin-top: 20px;
    font-size: 1.5em;
    text-decoration: none;
    clear: both;
    width: 100%;
    }
    
    #yzmCode {
	font-family: Arial, 宋体;
	font-style: italic;
	color: green;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
	}
    
    </style>
  
</head>


<body onload='createCode()'>
<form id="loginForm" name="loginForm" action = "${ctx}/login.do" method="post">
	<div class="container">
		<%@include file="/qiantai_page/top2.jsp"%>
        <div class="join_form clearfix" style="margin:0 auto;min-height:600px;padding-top:30px;">
            <h2 class="main_title_2 text_center"><br /></h2><h2 class="main_title_2 text_center">用户登录</h2>
            <div class="join_form" id="popup_form">
                <div class="input_div">
                    <label>用户名/手机/邮箱</label>
                    <input type="text" id="userdata" name="userData" placeholder="用户名/手机/邮箱" />
                </div>
                <div class="input_div">
                    <label>密码</label>
                    <input type="password" id="password" name="password" placeholder="密   码" />
                </div>
                <!-- <div class="input_div">
                    <label style="display: block;">验证码</label>
                    <input type="hidden" id ="yzm" />
                <div id="drag"></div>
				</div> -->

				<div class="input_div">
					<input type="text" id="yzmInput" style="width:50%" placeholder="验证码"/> <input type="button"
						id="yzmCode" onclick="createCode()" style="width:60px;margin-left:19%;height:40px;width:30%;font-size:30px"
						title='点击更换验证码' /> <input type="hidden" id ="yzm" />
				</div>

                <div class="input_div">
                    <a href="${ctx}/forgotPwd.jsp" class="float_right">忘记密码？</a>
                    <input type="checkbox" name="remember_pwd" id = "remember_pwd"/> 在本机记住密码
                </div>
                <div class="input_div">
                    <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block float_left">确认登录</button>
                    <button type="button" name="reg_btn" id = "reg_btn" class="btn btn_blue btn_block">新用户请注册</button>
                </div>
            </div>
        </div>
        <%@include file="/qiantai_page/bottom.jsp"%>
    </div>
</form>
</body>
<script>

var basepath ='${ctx}';
//$('#drag').drag();
var flag = false;
var errorMsg = "${errorMessage}";
var input = "${inputMode}";
var userData = "${userData}";
var userPass = "${userPass}";
if(errorMsg != "")
{
	alert(errorMsg);
	$('#userdata').val(userData);
	$('#password').val(userPass);
	if(input == "1")
	{
		$('#userdata').focus();
	}
	else if(input == "2")
	{
		$('#password').focus();
	}
	else
	{
		//$('#yzm').focus();
		$('#yzmInput').focus();
	}
}
init();
function init()
{
	if(getCookie("userName") != null && getCookie("userName") != "")
	{
		$("#userdata").val(getCookie("userName"));
		if(getCookie("pass") != null && getCookie("pass") != "")
		{
			$("#password").val(getCookie("pass"));
		}		
	}
}
$(function(){
	
	$("#userdata").focus();
	$("#reg_btn").click(function(){
		document.location.href = "${ctx}/registerUser.do";
	});
	$("#submit_btn").click(function(){
		var yzmv ='${random}';
		flag = true;
		if(flag){
			$("#yzm").val(yzmv);
		}
		if($('#userdata').val() == "")
		{
			alert("请输入用户名/手机/邮箱！");
			return;
		}
		if($('#password').val() == "")
		{
			alert("请输入密码！");
			return;
		}
		if(!validate()){
			return;
		}
		 /* if(!flag)
		{    
			alert("请完成验证码验证！");
			return;
		}  */
		if($("#remember_pwd").prop("checked") == true)
		{
			setCookie("userName",$('#userdata').val());
			setCookie("pass",$('#password').val());
			setCookie("remember_pwd","true");
		}
		$("#loginForm").submit();
	});
});
/**
 * 
 2017-2-26 
 程宏业
 回车登录方法修改
 
 
 */

 
 

// 回车登录
$("body").keydown(function() {
    if (event.keyCode == "13") {//keyCode=13是回车键
    	
    	$("#submit_btn").click();
      
    }
});

function changeValidateCode(obj) {     
       //获取当前的时间作为参数，无具体意义     
    var timenow = new Date().getTime();     
       //每次请求需要一个不同的参数，否则可能会返回同样的验证码     
    //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。     
    obj.src="${ctx}/login/GetRandomPictureAction.do?d="+timenow;     
}
function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
function setCookie(name,value)
{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}


</script>
</html>
