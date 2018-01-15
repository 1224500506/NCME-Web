<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="继续再教育平台">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="">
    <meta name="description" content="">
    <title>中国继续医学教育网_NCME</title>
    <%@include file="/commons/taglibs.jsp" %>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/rating.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css" rel="stylesheet">
</head>
<style type="text/css">
	.container .content .news_cont .news_cont{text-align:left;}
	.news_cont dl{margin-left:35px;}
	.news_cont dl dt{font-size:15px;}
	.news_cont dl dd{font-size:14px;line-height:30px;-webkit-margin-start: 20px;}
	.table{width:1000px; border:1px solid #E0E0E0;border-radius:10px;-moz-border-radius:10px;-webkit-border-radius:10px;-khtml-border-radius:10px;}
	.table tr{width:100%;}
	.table tr th{text-align:center;height:30px;background:#1498f1;font-size:13px;color:#333;font-weight:500;color:#fff;border:1px solid #dddddd;font-weight:600;}
	.table tr td{text-align:center;padding:10px 0px 10px 0px;font-size:13px;color:#333;font-weight:400;border:1px solid #dddddd;}
</style>
<body>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">项目动态</a></li>
    </ul>
     <div class="content no_padding">
        <div class="news_cont">
            <div class="top_cont">
                <h1>山西省面授学习手册</h1>
                <h3>
                    <span>2017-05-18 14：30</span>
                    <span>来源：国家卫生计生委能力建设和继续教育中心</span>
                    <span class="no_print">【字体：<a name="big">大</a>&nbsp;&nbsp;<a name="middle">中</a>&nbsp;&nbsp;<a name="small">小</a>】</span>
                    <span class="print no_print"><i class="fa fa-print"></i> 打印</span>
                    <span class="no_print">
                        
                        <i class="fa fa-share-alt" style="float:left;margin-right:10px"></i>
                        <div class="jiathis_style" style="display:inline;margin-top:10px">
                            <a class="jiathis_button_weixin"></a>
                            <a class="jiathis_button_tsina"></a>
                            <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
                        </div>
                        <script>
                        var jiathis_config={
                            summary:"",
                            shortUrl:false,
                            hideMore:false
                        }
                        </script>
                        <script src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                        
                    </span>
                </h3>
            </div>
            <div class="news_cont">
				<img style="margin-left:150px;" alt="" src="${ctx}/qiantai_page/img/TownshipMedical/dongTaiZiXunSX/3.jpg">
            </div>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<script>
    $(function(){
        $("a[name=big]").click(function () {
            $(".news_cont p").css({
                "font-size":"18px",
                "line-height":"30px"
            });
			$(".news_cont dl dt").css({
                "font-size":"18px",
                "line-height":"30px"
            });
			$(".news_cont dl dd").css({
                "font-size":"18px",
                "line-height":"30px"
            });
        });
        $("a[name=middle]").click(function () {
            $(".news_cont p").css({
                "font-size":"16px",
                "line-height":"25px"
            });
			$(".news_cont dl dt").css({
                "font-size":"16px",
                "line-height":"25px"
            });
			$(".news_cont dl dd").css({
                "font-size":"16px",
                "line-height":"25px"
            });
        });
        $("a[name=small]").click(function () {
            $(".news_cont p").css({
                "font-size":"14px",
                "line-height":"25px"
            });
			$(".news_cont dl dt").css({
                "font-size":"14px",
                "line-height":"25px"
            });
			$(".news_cont dl dd").css({
                "font-size":"14px",
                "line-height":"25px"
            });
        });
        $(".print").click(function () {
            window.print();
        });
    });
</script>
</body>
</html>                                                                                                                                                                                                            