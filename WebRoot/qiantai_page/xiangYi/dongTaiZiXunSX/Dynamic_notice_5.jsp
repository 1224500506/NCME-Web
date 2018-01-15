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
                <h1>“卫生计生人才强基层工程”为山西培养乡村医生</h1>
                <h3>
                    <span>2017-05-18 14：30</span>
                    <span>来源：山西日报</span>
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
                <p>山西日报记者秦洋报道  5月18日，国家卫生计生委能力建设和继续教育中心主办的“卫生计生人才强基层工程——乡村医生师资培训”在我省开班。依照培训计划安排，今年将在我省培训200名乡村医生师资和1000名乡村医生。</p>
				<p>参加本次面授培训的50多名学员来自省内各市县的基层医疗机构。经过面授培训后，他们还将进行乡村医生师资培训项目的远程课程。经过“面授+远程”系统化的培训，考核结业者，将被授予“乡村医生师资培训合格证书”，成为带教师资培训其周边的乡村医生。</p>
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