<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<body>
<form name="caseList" id = "caseList" action="${ctx}/liveList.do" method="post">
<input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
<input type = "hidden" name = "type" id = "type" value = "${type}"/>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">直播嵌入</a></li>
    </ul>
    <div class="filter_cont">
        
        
    </div>
    <div class="content top_border">
        <ul class="item_list">
            <!-- <iframe id="video" width="1000" height="600" src="" frameborder=”0”></iframe> -->
            <div >
			<h1 align="center">老师直播自定义页面</h1>
			<!-- <div align="center">
				Power by:wenkai.li
			</div>
			<p><b>Jsonp背景说明：</b>使用jsonp最终目的为使客户方便自行<font color="red">定制客户端启动页面</font>的UI并解决前台实现时跨域问题而设计。(纯前台js脚本实现，可右键查看源代码做参考)</p>
			<form>
				<b>Training客户端URL地址：</b>
				<input id="url" type="text" style="width: 500px;" value="http://demo.gensee.com/training/site/s/93589479" /><br /><br />
				<b>昵称：</b>
				<input id="nickname" type="text" value="学生-文凯"/><br /><br />
				<b>客户端口令：</b>
				<input id="token" type="text" value="333333" /><br /><br /><br />
				<input id="button_post" type="button"  value="jsonp请求发送" style="width: 150px; height: 40px; background-color: #38f; font-size: 16px; color: white;"/>
			</form>
			<br />
			<br />
			<p id="result_print"></p>
			<br />
			<font id="console_log" color="red"></font>
			<br />
			<br /> -->
			<div style="position: relative; top: 40px;">
				<h1>如果您已安装启动器</h1></br>
				<a id="client_code_link" href=""><img src="img/start.png" style="width: 50px; height: 50px;">点击启动客户端</a>
				<a id="gsLauncher_download" target="_blank" href="" style="position: relative; left: 90px;" >
				<img src="img/download.png" style="width: 50px; height: 50px;">点击下载启动器</a>
			</div>		
		</div>
            
        </ul>
        <%@include file="/commons/page.jsp"%>
    </div>
        <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
</html>
<script type="text/javascript">
$("document").ready(function(){
	//var url ="http://rkrc.gensee.com/training/site/r/07231248";
	//var url ="http://rkrc.gensee.com/training/site/r/23439041";
	var url ="http://rkrc.gensee.com/training/site/r/56131705";
	
	var nickname=encodeURIComponent("admin@rkrc.com");
	//var token="150036";
	//var token="002540";
	var token="814882";
	
		if(url !="")
		{
			var url_get=url + '?nickname='+nickname+'&token='+token+'&type=jsonp';
			$.ajax({
				type:"post",
				url:url_get,
				jsonp:"jsonpcallback",
				dataType:"jsonp",
				success:function (data) {
	//								alert(data.protocol);  注册协议
	//								alert(data.code);   客户端启动协议串
	//								alert(data.download); 启动器下载地址
	//								alert(data.success); 调用是否成功
	//								alert(data.msg); 失败消息
					
					if (data.success) 
					{
						//显示返回信息
						//var result_print_string='<b>此次jsonp请求：</b><br/> <b>protocol:</b>' +data.protocol+'<br/><b>code:</b>'+data.code+'<br/><b>download:</b>'+data.download+'<br/><b>success:<b>'+data.success;
						//$("#result_print").html(result_print_string);
						
						//启动客户端链接拼接
						client_link=data.protocol +'://'+ data.code;
						$("#client_code_link").attr("href",client_link);
						//$("#client_code_link").html('<img src="img/start.png" style="width: 50px; height: 50px;">点击启动客户端');
						//window.open(client_link);
						
						//下载启动器
						$("#gsLauncher_download").attr("href",data.download);
						//$("#gsLauncher_download").html('<img src="img/download.png" style="width: 50px; height: 50px;">点击下载启动器');
						
						//在控制台输出
						///console.log(data);
						//$("#console_log").html('以上jsonp返回值信息也可同步打开web控制台查看');
						
						//模拟点击启动
						//window.location.href = $('#client_code_link').attr('href');
					} 
					else
					{
						//console.log(data);
						alert('失败啦：' + data.msg);									
					}								
				},
				error: function(e){
					alert(e);
				}
			});					
		}
		else{
			alert("客户端URL地址不能为空！");					
		}
});
</script>