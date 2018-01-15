<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/commons/taglibs.jsp"%>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="index.css" />

  </head>
  
  <body>
<div class="center">
	<div class="center1">
		<h1 class="h1">学分证书</h1>
		<p class="p">
			姓名<span style="padding:0px 40px 0px 40px;"></span>身份证号<span style="padding:0px 80px 0px 80px;"></span>于<span style="padding:0px 40px 0px 40px;"></span>年<span style="padding:0px 40px 0px 40px;"></span>月
			<span style="padding:0px 40px 0px 40px;"></span>日至<span style="padding:0px 40px 0px 40px;"></span>年<span style="padding:0px 40px 0px 40px;"></span>月<span style="padding:0px 40px 0px 40px;"></span>日参加继续医学教育项目（面授&nbsp;<i></i>，远程教育&nbsp;<i></i>，混合&nbsp;<i></i>）[项目编号：<span style="padding:0px 80px 0px 80px;"></span>] [项目名称：<span style="padding:0px 80px 0px 80px;"></span>]。经考核合格，特授予 <span style="padding:0px 40px 0px 40px;"></span> 继续医学教育学分<span style="padding:0px 40px 0px 40px;"></span>分。
		</p>
		<div class="zhengshu">
			<p style="float:left;">证书编号：<span></span></p>
			<p style="float:right;">国家卫生计生委能力建设和继续教育中心</p>
		</div>
		<div style="clear:both;"></div>
		<div class="zhengshu">
			<p style="float:left;width:70px;height:70px;margin-left:50px;"></p>
			<p style="float:right;padding-top:20px;">年<span style="padding:0px 20px 0px 20px;"></span>月<span style="padding:0px 20px 0px 20px;"></span>日</p>
		</div>
	</div>
</div>
</body>		
</html>
