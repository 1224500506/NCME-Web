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
       <li><a href="${ctx}/ExpertGroup.do">专委会</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/ExpertGroup.do">专委会列表</a> <i class="fa fa-angle-right"></i></li>
    </ul>
    <div class="content no_padding">
        <div class="cont">
        <h1 class="main_title_2">${groupInfo.group.name}</h1>
        <p style="text-indent:2em">${groupInfo.group.summary}</p>
        </div>
        <div class="content top_border">
        	<c:forEach items = "${groupInfo.expertList}" var = "expert">
        	<c:if test="${expert.office == 1}">
	            <div class="cont">
	                <h2 class="main_title"><span>主任委员</span></h2>
	                <div class="main_user_info">
	                    <a>
	                        <span class="user_avatar">
	                            <img src="${expert.photo}"></img>
	                        </span>
	                        <p class="user_name">${expert.name}</p>
	                        <p class="user_cont">
	                            <span>${expert.workUnit}</span><span>
		                            <c:if test="${expert.workUnit_office==1}">院长</c:if>
									<c:if test="${expert.workUnit_office==2}">副院长</c:if>
									<c:if test="${expert.workUnit_office==3}">主任</c:if>
									<c:if test="${expert.workUnit_office==4}">副主任</c:if>
									<c:if test="${expert.workUnit_office==5}">所长</c:if>
									<c:if test="${expert.workUnit_office==6}">副所长</c:if>
									<c:if test="${expert.workUnit_office==7}">护士长</c:if>
								</span>
								<span>${expert.jobName}</span><br>
	                           	${expert.summary}
	                        </p>
	                    </a>
	                </div>
             </c:if>
             </c:forEach>
            </div>
            <div class="cont">
                <h2 class="main_title">副主任委员</h2>
                <ul class="name_list dot_name">
                    <c:forEach items = "${groupInfo.expertList}" var = "expert">
        				<c:if test="${expert.office == 2}">
                    		<li><i class="fa fa-user-md"></i>${expert.name}<span>${expert.workUnit}</span></li>
                    	</c:if>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="main_title">秘书长</h2>
                <ul class="name_list dot_name">
                 <c:forEach items = "${groupInfo.expertList}" var = "expert">
        			<c:if test="${expert.office == 3}">
                    	<li><i class="fa fa-user-md"></i> ${expert.name} <span>${expert.workUnit}</span></li>
                    </c:if>
                 </c:forEach>
                </ul>
            </div>
            <div class="cont">
            <c:forEach items = "${subGroupInfo}" var = "sub">
                <h2 class="main_title_3"><span>${sub.group.name}</span></h2>
                <ul class="name_list dot_name">
                  <c:forEach items = "${sub.expertList}" var = "expert">
        			<c:if test="${expert.office == 4}">
                    	<li><strong>组长：</strong> ${expert.name} <span>${expert.workUnit}</span></li>
                    </c:if>
                    </c:forEach>
                </ul>
                <h3 style="padding-left:20px;font-size:14px;font-weight:bold">委员：</h3>
                <ul class="name_list dot_name">
                    <c:forEach items = "${sub.expertList}" var = "expert">
        				<c:if test="${expert.office != 4}">
	                    	<li><i class="fa fa-user-md"></i>${expert.name} <span>${expert.workUnit}</span></li>
	                    </c:if>
	                </c:forEach>
                </ul>
            </c:forEach>    
            </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</body>
<script>
    function viewTeacher(id)
    {
    	var url = "${ctx}/teacherManage/teacherDetail.do?mode=view&id=" + id;
    	window.open(url,"_blank");
    }
</script>
</html>
