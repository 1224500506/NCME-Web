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
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
<form name="starCourseList" id="starCourseList" action="${ctx}/starCourseList.do" method="post">
<input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">名师课程</a></li>
    </ul>
    <div class="filter_cont">
        <ul class="no_hidden">
            <li>学科：</li>
            <%@include file="/qiantai_page/xueke.jsp"%>
        </ul>
    </div>
    <div class="content top_border">
        <ul class="item_list item_img_list">
            <c:forEach items = "${pager.list}" var = "muke">
                <li onclick = "javascript:gotoDetail2(${muke.id});" style="height:265px;">
                    <span class="item_cover" style="<c:if test="${muke.file_path != null && muke.file_path != ''}">background:url(${muke.file_path})</c:if> no-repeat center;background-size:cover;"></span>
                    <div class="item_cont">
                        <h2 class="title" title="${muke.name}">${muke.name}</h2>
                        <p class="desc topvebanner-p">${muke.introduction}</p>
                        <p>授课教师：${muke.teacherList[0].name}</p>
                        <h3 class="foot no_border">
                      		<c:choose>
                                  <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
                                  	<span class="font_green" style="float:left;">免费</span>
                                  </c:when>
                                  <c:when test="${muke.cost_type eq 1}">
                                  	<span class="font_green" style="float:left;">学习卡项目</span>
                                  </c:when>
                                  <c:when test="${muke.cost_type eq 2}">
<!--                                   	<span class="font_green" style="float:left;">收费</span> -->
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
                        </h3>
                    </div>
                </li>
            </c:forEach>
            <c:if test="${pager.list== null || fn:length(pager.list) == 0}">
          		 	暂无内容！
           	</c:if>
        </ul>
            <%@include file="/commons/page.jsp"%>
    </div>
            <%@include file="/qiantai_page/bottom.jsp"%>
</form>
</div>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
<script>

	var xueke = "${xueke}";

	$(".xueke_quanbu").click(function() {
		$("#xueke").val($(this).html());
		$(starCourseList).submit();
	});
	
	function gotoDetail2(id) {
        location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class&isCv=isCv";
    }
	
</script>
</body>
</html>
