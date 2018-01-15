<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
   	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
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
    <script src="${ctx}/qiantai_page/js/drag.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    
<style>
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

<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">胜任力</a></li>
    </ul>
    <div class="filter_cont" style = "min-height:500px;">
        <div class="cont fileter_cont">
            <h2 class = "main_title">
            	<span>我的胜任力</span>
            </h2>
            <ul>
            	<li style="background-image: url(${ctx}/qiantai_page/img/ability.jpg); background-size: 100%; float: left; list-style: none; position: relative; width: 1200px; height:1000px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;"></li>
            </ul>
        </div>
        <div class="cont">
            <h2 class="main_title_2">胜任力</h2>
            	<p style="text-indent: 2em;">国际医学教育专家委员会21世纪医学教育展望报告中提出以胜任力为导向的第3代继续医学教育改革，岗位胜任力的概念是：“在日常医疗服务中熟练精准地运用交流沟通能力、学术知识、技术手段、临床思维、情感表达、价值取向和个人体会，以求所服务的个人和群体受益。”</p>
            	<p style="text-indent: 2em;">国家卫生计生委能力建设和继续教育中心接受科教司委托，建立《中国继续医学教育指南》课题研究组，并设立《卫生计生专业技术人员岗位胜任力模型》子课题，致力构建符合中国国情的卫生计生专业技术人员的岗位胜任力通用模型，并形成各自专业的培训指标体系和评价指标体系，从而不断提高继续医学教育效果，提高和改善卫生计生专业技术人员的职业素质和服务能力。经过前期大量的文献研究及对美国ACGME、加拿大RCPSC和中国医科大学孙宝志教授牵头研究构建的“中国临床医生岗位胜任力通用模型标准”的充分借鉴，课题组现已初步建立面向医师、护师、药师、公卫、技师、中医等六类人员的《卫生计生专业技术人员岗位胜任力模型》。在此基础上， 将通过各个学科专家委员会研讨会、进一步深入的调查研究和系统实证，逐步完善和更新岗位胜任力模型各级指标，为中国继续医学教育提供理论指导。</p>
        </div>
    </div>
    
    <div class="popup">
	<form id="loginForm" name="loginForm" action = "${ctx}/loginAjax.do" method="post">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form" style="margin: -200px 0px 0px -270px;">
		        <h2><span class="close" id="closeLogin"><i class="fa fa-times"></i> </span> 用户登录</h2>
		        <div class="join_form" id="popup_form">
		            <div class="input_div">
		                <label>用户名/手机/邮箱</label>
		                <input type="text" name="userData" id="userName"/>
		            </div>
		            <div class="input_div">
		                <label>密码</label>
		                <input type="password" name="password" id="password"/>
		            </div>
		          <div class="input_div">
                    <!-- <label>验证码</label>
                    <input type="hidden" id ="yzm" />
                <div id="drag"></div> -->
                	<input type="text" id="yzmInput" style="width:50%" placeholder="验证码"/> <input type="button"
						id="yzmCode" onclick="createCode()" style="width:60px;margin-left:18%;height:40px;width:30%;font-size:30px"
						title='点击更换验证码' /> <input type="hidden" id ="yzm" />
		        </div>
	            <div class="input_div">
	               <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block float_left">确认登录</button>
	            </div>
		    </div>
		</div>
	</form>
    </div>
    
	<%-- <%@include file="/qiantai_page/bottom.jsp"%> --%>
</div>



<script type="text/javascript">
var basepath ='${ctx}';
//$('#drag').drag();
//var falg =false;
    $(".popup").show();
    
    $("#closeLogin").click(function () {
        $(".popup").hide();
    });
    //点击登录
    	/*
    	 * @author 张建国
    	 * @time   2017-02-17
    	 * 说      明：弹出层AJAX登录方法
    	 */
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
   	 	/* if(!flag)
		{    
			alert("请完成验证码验证！");
			return;
		}  */
   		//通过AJAX实现登录功能
   		$.ajax({
			type: 'POST',
			url:  '${ctx}/loginAJAX.do',
			data:$("#loginForm").serialize(),
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				//console.log(result);
				if(result.message="success"){
					//刷新页面
					location.reload();
				}
			}
        });
   	});
   	   	
        
</script>

</body>
</html>