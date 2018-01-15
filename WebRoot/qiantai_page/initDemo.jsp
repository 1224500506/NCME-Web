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
     <%-- <script src="${ctx}/qiantai_page/js/yzm.js"></script> --%>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet"> 
    <%-- <link href="${ctx}/qiantai_page/css/iconfont/iconfont.css" rel="stylesheet">  --%> 
     
     <link href="${ctx}/qiantai_page/css/test/iconfont.css" rel="stylesheet">
     <link href="${ctx}/qiantai_page/css/zhiboicon/iconfont.css" rel="stylesheet">
     <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
     <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>

<style type="text/css">

</style>
      
</head>


<body onload='createCode()'>

<p style="float:left;margin-left:300px;">
   <button type="button" name="study_begin" class="btn btn_blue btn_big">初始化课程和项目学习记录信息</button>
   	
</p>
<div id="resP" style="float:left;margin-left:300px;display:none;">
<span id="res">正在执行。。。。。。</span>
</div>
<script type="text/javascript">
    $(function () {
        $("button[name=study_begin]").click(function(){
        	$("#resP").show();
        	$(this).css("background-color","#e3e3e3").attr("disabled","disabled");
        		 $.ajax({
 					type: 'POST',
 					url: '${ctx}/study/logstudy.do?mode=initializeDemo',
 					//async: false,		
 					dataType: 'JSON',
 					success : function(data){
 						alert("执行成功["+data.count+"]个记录，共计["+data.total+"]个记录");
 						$("#resP").hide();
 					}
 		    });
        	
        });
 });       
     
</script>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
</body>
</html>