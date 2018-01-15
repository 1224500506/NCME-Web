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
<form id = "fm1" name = "fm1" method = "post" action = "${ctx}/courseManage/courseList.do?type=1">
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/courseManage/courseList.do">学科导航</a></li>
    </ul>
    <%-- <div class="filter_cont">
        <ul class="no_hidden">
            <li>学科：</li>
            <li class="so_many item_list" >
                <span class="all <c:if test = "${prop_val1 eq 0 || prop_val1 eq null}">active</c:if>">全部</span>
                <c:forEach items = "${propList}" var = "p">
                	<span class="<c:if test = "${prop_val1 == p.id}">active</c:if> courseLink01" id = "${p.id}">${p.name}</span>
                </c:forEach>
            </li>
        </ul>
    </div> --%>
    <div class="filter_cont content" style = "min-height:500px">
    <c:if test = "${prop_val1 eq 0 || prop_val1 eq null}">
    	<c:forEach items = "${propList}" var = "p">
            <div class="cont filter_cont">
            	<h2 class="main_title">
            		<span id ="${p.id}" name ="firstLevel">${p.name}</span>
	          	</h2>
       		 </div>
        </c:forEach>
     </c:if>
     <c:if test = "${subProp != null}">
        <div class="cont filter_cont">
           	<h3 class="main_title">
           		<span href = "${ctx}/qiantai_page/first.jsp" id ="${subProp.id}" name ="firstLevel">${subProp.name}</span>
          	</h3>
       	</div>
     </c:if>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
    $(function () {
    
        $("[name=firstLevel]").each(function(){
        	var url = "${ctx}/propManage/getPropListAjax.do?id="+$(this).prop("id");
        	var obj = $(this).parent();
        	var ppid = $(this).prop("id");
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
					    var arr = result.list.reverse(); 
				   		$(arr).each(function(key, value){
				   			var strSecond = '<h2 style="padding-left:20px;font-size:14px;font-weight:bold"><span name="secondLevel" id ="'+value.id+'">' + value.name + ' : </span></h2>';
				   			$(obj).after(strSecond);
				   			updateList(obj,value.id, ppid);
				   		});
				   };
			    }
			});
        });
    });
    
     function viewSubject(propId,pname,id)
     {
    	//window.open("${ctx}/courseManage/courseDetail.do?mode=xkxm&xueke=" + id);
    	window.location.href="${ctx}/ProjectList.do?xueke=" + encodeURI(pname) + "&ppid=" + id;
    	//alert("123");
     }
     function viewSubject2(propId,pid,pname,id)
     {
    	window.location.href="${ctx}/ProjectList.do?pid="+pid+"&xueke=" + encodeURI(pname) + "&ppid=" + id;
     }
     function updateList(obj,id,ppid)
     {
     	var third = $(obj).next();
       	var url = "${ctx}/courseManage/courseDetail.do?id="+ id;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   	    var strThird = '<ul class="no_hidden"> <li class="so_many item_list ">';
			   		$(result.list).each(function(key, value){
			   			if (value.prop_val1 == 0){
			   				strThird += '<span style="color:#c0c0c0">' + value.name + '</span>';
			   			} else{
			   				if(id=='510005'){//判断是否是儿科学，如果是就将儿科学id作为参数传递（二级学科id）
			   					strThird += '<span onclick = "javascript:viewSubject2('+ ppid +','+ id +',\''+ value.name + '\','+ value.id + ');">' + value.name + '</span>';
			   				}else{
			   					strThird += '<span onclick = "javascript:viewSubject('+ ppid + ',\''+ value.name + '\','+ value.id + ');">' + value.name + '</span>';
			   				}
			   			}
			   		});
			   		strThird += "</li></ul>";
			   		$(third).after(strThird);
			   };
		    }
		});
     }
     
     	$(".courseLink01").click(function () {
			document.getElementById("fm1").action = "${ctx}/courseManage/courseList.do?type=1&id="+$(this).prop("id");
			$("#fm1").submit();
        });
         $(".all").click(function () {
			document.getElementById("fm1").action = "${ctx}/courseManage/courseList.do?type=1";
			$("#fm1").submit();
        });
     
     function changeClass(obj)
     {
   	  $(obj).addClass("active").siblings().removeClass("active");
     }
</script>
</html>