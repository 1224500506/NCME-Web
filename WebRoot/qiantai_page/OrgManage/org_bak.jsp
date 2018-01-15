<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
   	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
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
<form name="OrgList" id = "OrgList" action="${ctx}/OrgManage/OrgManage.do" method="post">
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/OrgManage/OrgManage.do">机构</a></li>
    </ul>
    <div class="content no_padding">
        <div class="tabs">
            <ul class="tab_list">
                <li onclick="javascript:SelOrg(1);" class="tab <c:if test="${typelist == 1}">active</c:if>" id="tab1">医疗机构</li>
                <li onclick="javascript:SelOrg(2);" class="tab <c:if test="${typelist == 2}">active</c:if>" id="tab2">医学院校</li>
                <li onclick="javascript:SelOrg(3);" class="tab <c:if test="${typelist == 3}">active</c:if>" id="tab3">学/协会</li>
                <li onclick="javascript:SelOrg(4);" class="tab <c:if test="${typelist == 4}">active</c:if>" id="tab4">出版社</li>
                <li onclick="javascript:SelOrg(5);" class="tab <c:if test="${typelist == 5}">active</c:if>" id="tab5">社会单位</li>
                <input type="hidden" id="org_h" name="org_h" />
            </ul>
        </div>
        <div class="tab1 tab_cont">
            
	            <c:forEach items = "${orglist}" var = "item">            
	            <div class="org_list">            	
	                <div class="logo">
	                    <img src="${item.org.filePath}" onclick="javascript:gotoDetail('${item.org.id}');">
	                </div>                
	                <h3 onclick="javascript:gotoDetail('${item.org.id}');">${item.org.name}</h3>
	                <ul class="i_list">
	                    <li>
	                        <span>${item.projectList.size()}</span>
	                        <span>远程项目</span>
	                    </li>
	                    <li>
	                        <span>${item.faceList.size()}</span>
	                        <span>面授项目</span>
	                    </li>
	                    <li>
	                        <span>${item.teacherList.size()}</span>
	                        <span>推出名师</span>
	                    </li>
	                </ul>
	                <p class="clearfix" onclick="javascript:gotoDetail('${item.org.id}');">${item.org.description}</p>
	                <ul class="p_list">					
						<c:if test = "${item.projectList != null && item.projectList.size() != 0}">	                    
			                    <li style="background:url('${ctx}${item.projectList.get(0).file_path}') no-repeat center;background-size:cover" onclick="javascript:cv_gotoDetail('${item.projectList.get(0).id}')">
			                        <h3>${item.projectList.get(0).name}</h3>
			                    </li>	                   
		                </c:if>
		                <c:if test = "${item.projectList != null && item.projectList.size() == 0}">	                    
			                    <li style="background-size:cover">
			                    </li>	                   
		                </c:if>	                   
		                <c:if test = "${item.faceList != null && item.faceList.size() != 0}">	                	   
			                    <li style="background:url('${ctx}${item.faceList.get(0).file_path}') no-repeat center;background-size:cover" onclick="javascript:cv_gotoDetail('${item.faceList.get(0).id}')">
			                        <h3>${item.faceList.get(0).name}</h3>
			                    </li>	                 	
	                    </c:if>
	                    <c:if test = "${item.projectList != null && item.projectList.size() == 0 && item.faceList != null && item.faceList.size() == 0}">
	                    	<li> </li>
	                    </c:if>                    
	                </ul>
	            </div>
	            </c:forEach>    
	    	      
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
 $(function () {       
       /*  $("#org_detail").click(function () {            
           	var url = "${ctx}/OrgManage/OrgDetail.do?mode=get_CVS"; 
   			document.getElementById("OrgList").action = url;
   			document.getElementById("OrgList").submit();
        }); */ 
        
        
    });

function gotoDetail(id) {
	var url = "${ctx}/OrgManage/OrgDetail.do?mode=get_CVS&id=" +id; 
	document.getElementById("OrgList").action = url;
	document.getElementById("OrgList").submit();
}; 
function SelOrg(type){
	$("#org_h").val(type);
	$(OrgList).submit();
}
function cv_gotoDetail(id){
	location.href = "${ctx}/entityManage/entityView.do?id="+id;
}
</script>
</html>