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
       <li><br></li><li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">海外视野</a></li>
    </ul>
<form name="ProjectList" id = "ProjectList" action="${ctx}/haiWaiShiYe.do" method="post">
    <input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
    <input type = "hidden" name = "brand" id = "brand" value = "${brand}"/>
    <input type = "hidden" name = "mode" id = "mode" value = "${mode}"/>
    <input type = "hidden" name = "pid" id = "pid" value = "${xueke}"/>
    <div class="filter_cont">
        <ul class="no_hidden">
            <li>学科：</li>
            <%@include file="/qiantai_page/xueke.jsp"%>
        </ul>
        <ul>
            <li>类型：</li>
            <li class="item_list">
                <span class="leixing_quanbu <c:if test="${sign eq null || sign eq '' || sign == '全部'}">active</c:if>">全部</span>
                <span class="leixing_quanbu zhinan <c:if test="${sign eq '指南解读' || param.sign eq '指南解读' }">active</c:if>">指南解读</span>
                <span class="leixing_quanbu gongxu <c:if test="${sign eq '公需科目' || param.sign eq '公需科目'}">active</c:if>">公需科目</span>
                <input type="hidden" id="sign" name="sign" value = "${sign}"/>
            </li>
        </ul>
        <ul>
            <li>级别：</li>
            <li class="item_list">
                <span id="level0" onclick = "javascript:selLevel(-1);" class="<c:if test="${level == -1}">active</c:if>">全部</span>
                <span id="level1" onclick = "javascript:selLevel(1);" class="<c:if test="${level == 1}">active</c:if>">国家I类</span>
                <span id="level2" onclick = "javascript:selLevel(2);" class="<c:if test="${level == 2}">active</c:if>">省级I类</span>
                <span id="level3" onclick = "javascript:selLevel(3);" class="<c:if test="${level == 3}">active</c:if>">市级I类</span>
                <span id="level4" onclick = "javascript:selLevel(4);" class="<c:if test="${level == 4}">active</c:if>">省级II类</span>
                <span id="level5" onclick = "javascript:selLevel(5);" class="<c:if test="${level == 5}">active</c:if>">市级II类</span>
                <input type="hidden" id="level_h" name="level_h" value = "${level}"/>
                <input type="hidden" id="score_sort" name="score_sort" value="${scoreSort}" />
            	<input type="hidden" id="cost_sort" name="cost_sort" value="${costSort}" /> 
            	<input type="hidden" id="recent_create" name="recent_create" />  
            </li>
        </ul>
    </div>
</form>
    <div class="content top_border">
            <h1 class="main_title">海外视野</h1>
            <ul class="item_list item_img_list">
                <c:forEach items="${mukeList}" var="muke">
                    <li onclick="javascript:gotoDetail(${muke.id});">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <div class="info">
                                <span>项目负责人：${muke.managerList[0].name}</span>
                            </div>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <p class="aa">
                                <c:if test="${muke.level==1}">国家I类${muke.score}分</c:if>
                                <c:if test="${muke.level==2}">省级I类${muke.score}分</c:if>
                                <c:if test="${muke.level==3}">市级I类${muke.score}分</c:if>
                                <c:if test="${muke.level==4}">省级II类${muke.score}分</c:if>
                                <c:if test="${muke.level==5}">市级II类${muke.score}分</c:if>
                            </p>
                            <!--<div class="foot no_border">
	                    <span class="font_red">￥290</span>
	                    <span>${muke.studentNum}次学习</span>
	                </div>-->
                            <h3 class="foot no_border">
                                    <c:choose>
	                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
	                                    	<span class="font_green" style="float:left;">免费</span>
	                                    </c:when>
	                                    <c:when test="${muke.cost_type eq 1}">
	                                    	<span class="font_green" style="float:left;">学习卡项目</span>
	                                    </c:when>
	                                    <c:when test="${muke.cost_type eq 2}">
	                                    	<span class="font_red" style="float:left;">
	                            	 			￥${muke.cost}
	                            			</span>
	                                    </c:when>
	                                    <c:otherwise>
		                            		<span class="font_red" style="float:left;">
		                            	 		￥${muke.cost}
		                            		</span>
	                                    </c:otherwise>
                                </c:choose>
                                1${muke.studentNum}次学习
                            </h3>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <%@include file="/commons/page.jsp"%>
    </div>
    <div style="margin-top:120px;">
    	<%@include file="/qiantai_page/bottom.jsp"%>
    </div>
</div>
</body>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
<script>
/******************按条件查询****************************************************************/

	var xueke = "${xueke}";

	function changeClass(obj, id) {
		$(obj).addClass("active").siblings().removeClass("active");
		$("#xueke").val(id);
		$(ProjectList).submit();
	}
	$(".leixing_quanbu").click(function() {
		$("#sign").val($(this).html());

		$(ProjectList).submit();
	});
	$(".xueke_quanbu").click(function() {
		$("#xueke").val($(this).html());
		$(ProjectList).submit();
	});
	function selLevel(level) {
		$("#level_h").val(level);
		$(ProjectList).submit();
	}
	function ScoreSort() {
		$("#cost_sort").val("");
		var asc = $("#score_sort").val();
		if (asc == "")
			asc = "asc";
		else if (asc == "asc")
			asc = "desc";
		else
			asc = "asc";
		$("#score_sort").val(asc);
		$(ProjectList).submit();
	}
	function CostSort() {
		$("#score_sort").val("");
		var asc = $("#cost_sort").val();
		if (asc == "")
			asc = "asc";
		else if (asc == "asc")
			asc = "desc";
		else
			asc = "asc";
		$("#cost_sort").val(asc);
		$(ProjectList).submit();
	}
	function RecentCreate() {
		$("#cost_sort").val("");
		$("#score_sort").val("");
		$("#recent_create").val("RC");
		$(ProjectList).submit();
	}

/******************按条件查询****************************************************************/

    function gotoDetail(id) {
        location.href = "${ctx}/projectDetail.do?id=" + id;
    }
</script>
</html>