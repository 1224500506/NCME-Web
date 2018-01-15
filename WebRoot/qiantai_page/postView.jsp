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
<body>
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs no_print">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <c:if test="${mode==1 }">
        	<li><a href="#">资讯公告</a></li>
        </c:if>
        <c:if test="${mode==2 }">
        	<li><a href="#">政策法规</a></li>
        </c:if>
    </ul>
    <div class="content no_padding" style="min-height:600px;">
        <div class="news_cont">
            <div class="top_cont">
                <h1>${title}</h1>
                <h3>
                    <span>${deli_date}</span>
                    <span>来源：${source}</span>
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
            	<!-- 
                <img src="img/news_img.jpg">
                 -->
                <p>${content}</p>
            </div>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<script>
    $(function(){
        $("a[name=big]").click(function () {
            $(".news_cont p span").css({
                "font-size":"18px",
                "line-height":"30px"
            });
        });
        $("a[name=middle]").click(function () {
            $(".news_cont p span").css({
                "font-size":"16px",
                "line-height":"25px"
            });
        });
        $("a[name=small]").click(function () {
            $(".news_cont p span").css({
                "font-size":"14px",
                "line-height":"25px"
            });
        });
        $(".print").click(function () {
            window.print();
        });
    })
</script>
</body>
</html>