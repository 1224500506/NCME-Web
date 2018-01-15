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
<form action="">
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">名师</a></li>
    </ul>
    
   <div class="filter_cont">
   		<p style="font-size:16px;line-hEight:10px;color:#000;text-indent:0EM;">按姓名拼音顺序排序，排名不分先后</p>
        <%-- <ul <c:if test = "${downState == 1}">class = "no_hidden"</c:if>>
            <li>学科：</li>
            <li class="so_many item_list">
                <span class="explore">展开 <i class="fa fa-angle-down"></i></span>
                <span onclick = "javascript:searchProp(0)" <c:if test = "${propId eq '' || propId eq 0}"> class="all active"</c:if>>全部</span>
                <c:forEach items = "${propList}" var = "prop">
                	<span onclick = "javascript:searchProp(${prop.id})" <c:if test = "${propId == prop.id}">class="active"</c:if>>${prop.name}</span>
                </c:forEach> 
            </li>
        </ul> --%>
        
    </div>
    <div class="content" style="padding:0px 0px 50px 0px;">
		<ul class="teachers_list">
			<c:forEach items = "${pager.list}" var ="teacher">
	            <li style="background:url(${teacher.photo}) no-repeat center;background-size:100% auto">
	                <div class="bg_color"></div>
	                <div class="bg_intro">
	                    <h2>${teacher.name}</h2>
			            <h3>
				            <c:forEach items="${unitList}" var="bg_intro">
								<c:if test="${unit.id==teacher.job}">${unit.name}</c:if>
							</c:forEach>${teacher.workUnit}
						</h3>
			            <h3>
							<c:if test="${teacher.workUnit_office==1}">院长</c:if>
							<c:if test="${teacher.workUnit_office==2}">副院长</c:if>
							<c:if test="${teacher.workUnit_office==3}">主任</c:if>
							<c:if test="${teacher.workUnit_office==4}">副主任</c:if>
							<c:if test="${teacher.workUnit_office==5}">所长</c:if>
							<c:if test="${teacher.workUnit_office==6}">副所长</c:if>
							<c:if test="${teacher.workUnit_office==7}">护士长</c:if>
						</h3>
			        </div>
	            </li>
            </c:forEach>
         </ul>
         <%@include file="/commons/page.jsp"%>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
  
    function viewTeacher(id)
    {
    	var url = "${ctx}/teacherManage/teacherDetail.do?mode=view&id=" + id;
    	document.location.href = url;
    }
    function searchProp(id)
    {
    	var downState = 0;
    	if($('.filter_cont').find("ul").prop("class") == "no_hidden")
    	{
    		downState = 1;
    	}
    	else
    	{
    		downState = 0;
    	}
    	document.location.href = "${ctx}/teacherManage/teacherManage.do?propId=" + id +"&downState=" + downState;
    }
    
</script>
</html>