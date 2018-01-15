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
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
    <script src="${ctx}/qiantai_page/js/rating.js?rev=5cab9c748cf8c51b4937cb8e6cf0d306"></script> 
   
     <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
     <script src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script> 
     <script src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script> 
    <script src="${ctx}/qiantai_page/js/main.min.js?rev=222fe8abc0fda3427a0f94eaf2939390"></script> 
    <link href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet"> 
    
     <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">  
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
     <script src="${ctx}/qiantai_page/js/drag.js"></script>
     <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet"> 
    <%-- <link href="${ctx}/qiantai_page/css/iconfont/iconfont.css" rel="stylesheet">  --%> 
     
     <link href="${ctx}/qiantai_page/css/test/iconfont.css" rel="stylesheet">
     <link href="${ctx}/qiantai_page/css/zhiboicon/iconfont.css" rel="stylesheet">
     <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
     <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>
<script type="text/css">

</script>
      
</head>
<body onload='createCode()' style="height:480px;">

 <div style="float:left;margin-top: 20px;margin-left: 30px;">
      <span style="width:360px;color: #12bce1;font-weight:bold;">项目评价：(${pager.count})</span>
      <span style="margin-left: 500px;">综合评分：${grade}</span>
      <ul class="comments_list">
           <c:forEach items="${comment}" var="pitem">
          	  <li style="overflow:hidden;margin-top: 18px;">
                  <div>
	                   <div  class="avatar tiny" style="float:left;margin-right:10px;">
	             	     <c:if test="${pitem.user_avatar !=  null}">
	             		   <img src="${pitem.user_avatar}" onerror="imgonload(this,'${pitem.sex}');" id="userImage" name="userImage" >
	             	     </c:if>
			             <c:if test="${pitem.user_avatar == null}">
			                 <c:if test="${pitem.sex == 1}">
			              	    <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" >
			                 </c:if>
				              <c:if test="${pitem.sex == 2}">
				              	<img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" >
				              </c:if>
			             </c:if>
	                   </div>
	                   <div style="float:left">
	                       <span>${pitem.realName}</span>
	                       <span style="margin-left: 50px"><fmt:formatDate value="${pitem.scoreDate}" pattern="yyyy-MM-dd"/></span>
	                       <p class="cont" style="margin-top: 6px;">${pitem.scoreContent}</p>
	                   </div>
                  </div>
              </li>
         </c:forEach>
     </ul>
     <%-- <ul> <%@include file="/commons/page.jsp"%></ul> --%>
</div>
 <div style ="position: absolute;bottom: 0;left: 50%;transform: translateX(-50%);-ms-transform: translateX(-50%);-moz-transform: translateX(-50%);-webkit-transform: translateX(-50%);"><%@include file="/commons/page.jsp"%></div>
</body>
</html>