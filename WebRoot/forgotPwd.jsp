<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=0,maximum-scale=1,user-scalable=yes">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="中国继续医学教育网">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="">
    <meta name="description" content="">
    <title>中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js?rev=222fe8abc0fda3427a0f94eaf2939390"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <style>
       .inputTip{
       	   color: #888888;
       	   float: left;
       	   margin-left: 20px;
       	   margin-top: 5px;
       }
    </style>
</head>
<body>
	<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
	    <ul class="bread_crumbs">
	        <li>您的位置：</li>
	        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
	        <li><a href="${ctx}/help.jsp">找回密码</a></li>
	    </ul>
	    <div class="content" style="height:650px;">
	        <h1 class="main_title">找回密码</h1>
	        <div class="join_form inline_form step_1" id="step_1">
	            <div class="input_div">
	                <label>手机号</label>
	                <input type="text" name="phone" size="30" id="phone" onblur="checkPhone();"/>
	            </div>
	            <div class="input_div">
	                <label>短信验证码</label>
	                <input type="text" name="verify_code" id="verify_code" style="min-width:11.7em;"/>
	                <button type="button" name="verify_btn" id="verify_btn" onclick="daojishi();" style="border-radius:7px;">获取验证码</button>
	                <input type="hidden" name="yzm" id="yzm"/>
	            </div>
	            <div class="input_div">
	                <label>&nbsp;</label>
	                <button class="btn btn_blue btn_next" onclick="next();">下一步</button>
	            </div>
	        </div>
	        <div class="join_form inline_form step_2" style="display:none" id="step_2">
	            <div class="input_div">
	                <label>新密码</label>
	                <input type="password" name="password" id="password" onblur="checkpwd(this);"/>
	                <p class="cp" style="color: red; display: none;float: left;margin-top: 5px;" >*</p>
	                <p class="inputTip">8-20位，须包含大写字母、小写字母、数字，不能使用空格</p>
	            </div>
	            <div class="input_div">
	                <label>确认密码</label>
	                <input type="password" name="passwordCon" id="passwordCon"/>
	            </div>
	            <div class="input_div">
	                <label>&nbsp;</label>
	                <button class="btn btn_blue btn_done" onclick="subPassword();">完成</button>
	            </div>
	        </div>
	        <div class="info" id="info" style="display:none">
	            <h1 class="text_center">恭喜，密码重置成功！</h1>
	            <div class="text_center">
	                <button class="btn btn_blue btn_login" onclick="login();">立即登录</button>
	            </div>
	        </div>
	    </div>
	    <%@include file="/qiantai_page/bottom.jsp"%>
	<script type="text/javascript">
	    /**
		 * @author 张建国
		 * @time   2017-01-17
		 * 说     明：检查手机号码是否重复
		 **/
		function checkPhone(){
			var phone = $("#phone").val();
			if(phone==null || phone==""){
				alert("请输入手机号码.");
				return ;
			}else if(phone.length!=11){
				alert("请输入有效的手机号码.");
				return;
			}else {
				$.ajax({
					type:'POST',
					url:'${ctx}/registerUser.do?method=checkMobile&mobile_phone='+phone,
					dataType:'json',
					async: false,
					success:function(data){
						var result = eval(data);
						if(result.message=='success'){
							alert('该手机号未注册.');
							return;
						}
					}
				});
			}
		}
	  
		/*
		 * @author 张建国
		 * @time   2017-01-09
		 * 说       明：验证码倒计时
		 */
		 var lock = true;
		function daojishi(){
			//验证手机号码是否输入
			var phone = $("#phone").val();
			if(phone!=null && phone!=''){
				var obj = $("#verify_btn");
				var timer = null;
				var n=60;
				//加上lock是为了防止一下子快速多次点击获取验证码
				if(lock){
					lock = false;
					if(n!=0){
						//发送验证码
						geyYZM();
					}
					timer =setInterval(function(){
						n--;
						obj.text(n+'s重新获取');
						obj.attr("disabled",true); 
						
						if(n==0){
							clearInterval(timer);
							obj.attr("disabled",false); 
							obj.text('获取验证码');
							lock = true;
						}
					},1000);
				}
			}else{
				alert("请输入有效的手机号码.");
			}
	    }
		
		/*
		 * @author  张建国
		 * @time    2017-01-09
		 * 说         明：获取服务端验证码信息 
		 */
		function geyYZM(){
			//获取用户填写的手机号码
			var phone = $("#phone").val();
			if(phone!=null && phone!=''){
				if(phone.length==11){
					$.ajax({
						type:'POST',
						url:'${ctx}/registerUser.do?method=getPassword&mobile_phone='+phone,
						dataType:'json',
						success:function(data){
							var result = eval(data);
							if(result.message=='success'){
								//alert('验证码已发送，请耐心等待');
								$("#yzm").val(result.yzm);
							}else{
								alert('验证码发送失败.');
							}
						}
					});
				}else{
					alert("请填写有效的手机号码!");
				}
			}else{
				alert("请填写手机号码!");
			}
		}
		
		/*
		 * @author 张建国
		 * @time   2017-01-23
		 * 说     明： 点击进入下一步
		 */
		 function next(){
			//判断验证码是否正确
			var verify_code = $("#verify_code").val();
			var yzm = $("#yzm").val();
			if(verify_code==null || verify_code==""){
				alert("请输入验证码.");
				return;
			}else if(verify_code!=yzm){
				alert("验证码输入错误，请重新输入.");
				return;
			}else if(yzm==null || yzm== ""){
				alert("验证码获取异常，请重新获取.")
			}else if(verify_code==yzm){ 
				$("#step_1").css("display","none");
				$("#step_2").css("display","block");
			}
		}
		
		/*
		 * @author 张建国
		 * @time   2017-01-23
		 * 说     明： 提交密码重置
		 */
		function subPassword(){
		    var phone = $("#phone").val();
			var password = $("#password").val();
			var passwordCon = $("#passwordCon").val();
			if(password==null || password == ""){
				alert("请输入新密码.");
				return;
			}
			var pattern = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))[a-zA-Z0-9]{8,20}$/;
			if(!pattern.test(password)){
				alert("密码格式不正确！");
				return;
			}
			if(passwordCon==null || passwordCon==""){
				alert("请输入确认密码.");
				return;
			}
			if(password!=passwordCon){
				$("#passwordCon").val('').focus();
				alert("两次输入的密码不符，请重新输入.");
				return;
			}
			if(password==passwordCon){
				//通过AJAX进行密码修改
				$.ajax({
					type:'POST',
					url:'${ctx}/registerUser.do?method=updatePassword&mobile_phone='+phone+'&password='+password,
					dataType:'json',
					success:function(data){
						var result = eval(data);
						if(result.message=='success'){
							$("#step_2").css("display","none");
							$("#info").css("display","block");
						}else{
							alert('密码修改失败.');
						}
					}
				});
			}
		}
		
		/*
		 * @author 张建国
		 * @time   2017-01-23
		 * 说      明：点击跳转
		 */
		 function login(){
			location.href="${ctx}/login.do";
		}
		
		//校验密码
		 function checkpwd(obj){
				$thi=$(obj);
				var pattern = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))[a-zA-Z0-9]{8,20}$/;
				if(!pattern.test($thi.val())) {
					$thi.next().text("*密码格式不正确");
					$thi.next().css("color","red");
					$thi.next().show();
					return false;
				}else{
					$thi.next().hide();
				}
				
			}
	</script>
</body>
</html>
