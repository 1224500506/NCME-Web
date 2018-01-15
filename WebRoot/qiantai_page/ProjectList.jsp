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
<form name="ProjectList" id = "ProjectList" action="${ctx}/ProjectList.do" method="post">
<div class="container">  
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li><br></li><li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#" id="biao">优质慕课</a></li>
    </ul>
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
                <span id="level6" onclick = "javascript:selLevel(6);" class="<c:if test="${level == 6}">active</c:if>">无学分</span>
                <input type="hidden" id="level_h" name="level_h" value = "${level}"/>

            </li>
        </ul>
        <ul>
         <li>授课<!-- 形式 -->:</li>
          <li class="item_list">
                <span class="teaching_form <c:if test="${forma eq null || forma eq '' || forma eq '全部'}">active</c:if>">全部</span>
                <span class="teaching_form range <c:if test="${forma eq '远程' }">active</c:if>">远程</span>
                <span class="teaching_form face  <c:if test="${forma eq '面授' }">active</c:if>">面授</span>
                <input type="hidden" id="forma" name="forma" value = "${forma}"/>
            </li>
        </ul>
    </div>
    <div class="content top_border">
        <ul class="item_filter">
            <li class="new active">最新</li>
            <li>
                             学分 <i class="fa fa-angle-up fa-angle-down"></i>
                <span class="drop_down">
                       <a href="javascript:ScoreSort(1);">从低到高</a>
                       <a href="javascript:ScoreSort(2);">从高到低</a>
                </span>
                 <input type="hidden" id="score_sort" name="score_sort" value="${scoreSort}" />
            </li>
            <li>
                              价格 <i class="fa fa-angle-up fa-angle-down"></i>
                <span class="drop_down">
                       <a href="javascript:CostSort(1);">从低到高</a>
                       <a href="javascript:CostSort(2);">从高到低</a>
                </span>
            	<input type="hidden" id="cost_sort" name="cost_sort" value="${costSort}" /> 
            </li>
      <%--       <li class="search">
                <input type="text" id="item_name" name="item_name" value="${param.item_name}" placeholder="请输入课程名称">
                <button name="item_search_btn" type="submit"><i class="fa fa-search"> 
                <input type="hidden" id="recent_create" name="recent_create" />
                </i></button>
            </li> --%>
        </ul>
        <ul class="item_list item_img_list">
            <c:forEach items="${CVSetList}" var ="list">
                <li onclick = "javascript:gotoDetail(${list.id});">
                    <span class="item_cover" style="<c:if test="${list.file_path != null && list.file_path != ''}">background:url(${list.file_path})</c:if> no-repeat center;background-size:cover"></span>
                    <div class="item_cont">
                        <h2 class="title" title="${list.name}">${list.name}</h2>
                        <div class="info">
                            <span>项目负责人：${list.managerList[0].name}</span>
                        </div>
                        <p class="desc topvebanner-p">${list.introduction}</p>
                        <p>
                            <c:choose>
                                <c:when test="${list.level eq 1}">国家I类 ${list.score} 分</c:when>
                                <c:when test="${list.level eq 2}">省级I类 ${list.score} 分</c:when>
                                <c:when test="${list.level eq 3}">市级I类 ${list.score} 分</c:when>
                                <c:when test="${list.level eq 4}">省级II类 ${list.score} 分</c:when>
                                <c:when test="${list.level eq 5}">市级II类 ${list.score} 分</c:when>
                                <c:when test="${list.level eq 6}"> ${list.score} 分</c:when>
                            </c:choose>
                        </p>
                        <div class="foot no_border">
                            <c:choose>
                                    <c:when test="${list.cost_type eq 0 || list.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${list.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${list.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
			                            	 ￥${list.cost}
			                            </span>
                                    </c:when>
                                    <c:otherwise>
                            <span class="font_red" style="float:left;">
                            	 ￥${list.cost}
                            </span>
                                    </c:otherwise>
                                </c:choose>
                            <span>
                            <%-- <c:choose>
                                <c:when test="${list.studyTimes eq null}">
                                    
                                </c:when>
                                <c:otherwise>
                                    ${list.studyTimes}
                                </c:otherwise>
                            </c:choose> --%>
                            	 1${list.studentNum} 次学习
                            </span>
                        </div>
                    </div>
                </li>
            </c:forEach>
            <c:if test="${CVSetList== null || fn:length(CVSetList) == 0}">
           		 暂无内容！
            </c:if>
        </ul>
        <%@include file="/commons/page.jsp"%>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
</body>
<script>
	$(function(){
		if('${sign}' != null && '${sign}' != ''){
			$('#biao').text('${sign}');
		}
		var biao = window.location.href.split("?")[1].split("=")[0];
		if(biao != null && biao != ''){
			var biaoVal = window.location.href.split("?")[1].split("=")[1];
			if(biaoVal != null && biaoVal != ''){
				if(biao == 'xueke' && biaoVal == '%E5%85%A8%E7%A7%91%E5%8C%BB%E5%AD%A6'){
					$('#biao').text('基层学院');
				}/* else if(biao == 'sign'){
					$('#biao').text('${sign}');
				} */
			}
		}
	});
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
	$(".teaching_form").click(function() {
		$("#forma").val($(this).html());
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
	//最新 
	$(".new.active").click(function() {
		$("#cost_sort").val("");
		$("#score_sort").val("");
		$(ProjectList).submit();
	});
	function ScoreSort(sort) {
		$("#cost_sort").val("");
		var asc = $("#score_sort").val();
		if (asc == 1)
			asc = "asc";
		else if (asc == 2)
			asc = "desc";
		else
			asc = null;//未设置学分时默认为空
		$("#score_sort").val(asc);
		$(ProjectList).submit();
	}
	function CostSort(sort) {
		$("#score_sort").val("");
		var asc = $("#cost_sort").val();
		if (asc == 1)
			asc = "asc";
		else if (asc == 2)
			asc = "desc";
		else
			asc = null;//未设置学分时 
		$("#cost_sort").val(asc);
		$(ProjectList).submit();
	}

	function RecentCreate() {
		$("#cost_sort").val("");
		$("#score_sort").val("");
		$("#recent_create").val("RC");
		$(ProjectList).submit();
	}
	function gotoDetail(id) {
		location.href = "${ctx}/projectDetail.do?id=" + id;
	}
</script>
</html>