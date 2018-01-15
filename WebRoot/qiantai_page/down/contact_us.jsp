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
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a></li>
    </ul>
    <div class="content no_padding">
        <div class="left_nav about_nav">
            <ul>
                <li><a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a></li>
                <li class="active"><a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a></li>
                <li><a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a></li>
                <li><a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a></li>
            </ul>
        </div>
        <div class="main_cont right about_cont">
            <div class="cont">
                <h2 class="has_border">中国继续医学教育网<span>National Continuing Medical Education</span></h2>
                <ul>
                    <li>地址：北京市海淀区知春路6号锦秋国际大厦A座1808</li>
                    <li>邮编：100873</li>
                    <li>传真：010-68945082</li>
                    <li>全国客服热线：400-863-1000</li>
                    <li>邮箱：service@ncme.org.cn</li>
                </ul>
            </div>
            <img src="${ctx}/qiantai_page/img/address_map.png" style="margin-left:30px">
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>

</body>
</html>