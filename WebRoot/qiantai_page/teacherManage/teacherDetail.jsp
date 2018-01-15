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
<!-- 
  <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/teacherManage/teacherManage.do">名师</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/teacherManage/teacherDetail.do?mode=view&id=${expert.id}">${expert.name}</a></li>
    </ul>
 -->
    <div class="content">
        <ul class="project_info teacher_info">
            <li>
                <h1 class="main_title_2">${expert.name}</h1>
                 <c:choose>
                    <c:when test="${expert.photo != null && expert.photo != ''}">
                        <span class="item_cover" style="background:url(${expert.photo}) no-repeat top;background-size:cover"></span>
                    </c:when>
                    <c:otherwise>
                        <span class="item_cover" style="background:url('${ctx}/qiantai_page/img/2.png') no-repeat top;background-size:cover"></span>
                    </c:otherwise>
                </c:choose>
                <div class="item_cont">
                    <h2 class="p_title">
                    	<c:forEach items="${proplist}" var="prop">
							<c:if test = "${prop.id eq expert.job}">${prop.name}</c:if>
						</c:forEach>
                    </h2>
                    <h2 class="p_title">${expert.workUnit}</h2>
                    <p class="">${expert.summary}</p>
                </div>
            </li>
        </ul>
        <div class="content top_border">
            <div class="cont">
                <h2 class="title">
                <span>负责的项目<span>负责的项目：${manageCVList.size()}个</span></span>
                <a href="${ctx}/ProjectList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>
                </h2>
                <ul class="item_list teacher_course">
                	<c:forEach items = "${manageCVList}" var = "managerCV"> 
	                    <li onclick = "javascript:gotoDetail(${managerCV.id});">    
			                   	<span class="item_cover" style="background:url('${managerCV.file_path}') no-repeat center;background-size:cover">
			                        <div class="item_title">${managerCV.name}</div>
			                    </span>
		                        <div class="item_cont">
		                            <div class="foot">
		                                <span><i class="fa fa-eye"></i> 100</span>
		                            </div>
		                        </div>
		                 </li>
	                  </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>教授的课程<span>已教授的课程：${teacherCVList.size()}个</span></span>
	               	 <a href="#" class="more">更多 <i class="fa fa-angle-right"></i></a>
                </h2>
                <ul class="item_list teacher_course course_link">
                	<c:forEach items = "${teacherCVList}" var = "teacherCV">
	                    <li onclick = "javascript:gotoDetail(${teacherCV.id});">   
	                       <span class="item_cover" style="background:url('${ctx}${teacherCV.file_path}') no-repeat center;background-size:cover">
	                            <div class="item_title">${teacherCV.name}</div>
	                        </span>
	                        <div class="item_cont">
	                            <div class="foot">
	                                <span><i class="fa fa-eye"></i> 100</span>
	                            </div>
	                        </div>
	                    </li>
	                 </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h1 class="title">
                	<span>主持的直播<span>已主持的直播：${brand2List.size()}个</span></span>
                	<a href="#" class="more">更多 <i class="fa fa-angle-right"></i></a>
                </h1>
                
                <ul class="item_list teacher_course">
                	<c:forEach items = "${brand2List}" var = "brand2">
                    <li onclick = "javascript:gotoDetail(${brand2.id});">
                  		 <span class="item_cover" style="background:url('${ctx}${brand2.file_path}') no-repeat center;background-size:cover">
                   		     <div class="item_title">${brand2.name }</div>
                   		 </span>
                        <div class="item_cont">
                            <div class="foot">
                                <span><i class="fa fa-eye"></i> 100</span>
                            </div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h1 class="title">
                	<span>主持的病例讨论<span>已主持的病例讨论：${brand1List.size()}个</span></span>
                	<a href="#" class="more">更多 <i class="fa fa-angle-right"></i></a>
                </h1>
                <ul class="item_list teacher_course">
                <c:forEach items = "${brand1List}" var = "brand1">
                   <li onclick = "javascript:gotoDetail(${brand1.id});">
                  		 <span class="item_cover" style="background:url('${ctx}${brand1.file_path}') no-repeat center;background-size:cover">
                   		     <div class="item_title">${brand1.name}</div>
                   		 </span>
                        <div class="item_cont">
                            <div class="foot">
                                <span><i class="fa fa-eye"></i> 100</span>
                            </div>
                        </div>
                    </li>          
                  </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</body>

<script>
    function gotoDetail(id) {
	     	location.href = "${ctx}/entityManage/entityView.do?id="+id;
	}
</script>
</html>