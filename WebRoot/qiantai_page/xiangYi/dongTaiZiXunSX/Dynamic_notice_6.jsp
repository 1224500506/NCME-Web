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
                <h1>“最美医生”贺星龙有了导师</h1>
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
                <p>北京知名专家将其培养成优秀全科医生</p>
                <img style="margin-left:200px;" alt="" src="${ctx}/qiantai_page/img/TownshipMedical/dongTaiZiXunSX/6.jpg">
				<p>山西日报记者秦洋报道 5月18日，荣获中央电视台2016年度“最美医生”的大宁县徐家垛乡乐堂村村医贺星龙有了带教导师。北京月坛社区卫生服务中心副主任董建琴与贺星龙签约，双方自此建立指导关系，意味着这名来自国家级贫困县的基层医生，今后将接受最好的全科医生系统化教育，更好地服务周边广大居民。</p>
				<p>贺星龙自2000年行医以来，骑坏6辆摩托车，背坏12个药包，脚步跨越山西、陕西两省的28个行政村，累计行程40多万公里。乡亲有病，他24小时随叫随到；付不起药费，费用他能免则免。他还当选为“2016感动山西”十大人物。</p>
				<p>由于贺星龙是卫校毕业，还需要进行系统化提升教育和培训。经过国家卫生计生委科教司的牵线搭桥，北京月坛社区卫生服务中心与贺星龙建立指导帮扶关系，北京月坛社区卫生服务中心主任杜雪平、副主任董建琴作为全国知名专家，将成为贺星龙的名誉导师和导师，通过面授、远程教学、进修实践等方式，将贺星龙培养成为一名优秀的全科医生。</p>
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