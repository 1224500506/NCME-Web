<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.hys.exam.model.SystemUser" %>
<%@ page import="com.hys.exam.common.util.LogicUtil" %>
<%
	SystemUser user = LogicUtil.getSystemUser(request);
	String username = "";
	if(null != user){
		if(null != user.getRealName()){
			username = user.getRealName();
		}
	}
%>	
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
<form name="OrgteacherList" id = "OrgteacherList" action="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=${type}" method="post">
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
    <div class="header">
        <div class="top">
            <div class="top_cont">
                <div class="left_nav">
                    <a href="${ctx}/first.do">首页</a>
                    <a href="${ctx}/PublicSubject.do?sign=1">公需科目</a>
                    <a href="${ctx}/BasicSubject.do">基层学院</a>
                    <a href="${ctx}/Ability.do">胜任力</a>
                    <a href="${ctx}/teacherManage/teacherManage.do">名师</a>
                    <a href="${ctx}/OrgManage/OrgManage.do">机构</a>
                    <a href="${ctx}/ExpertGroup.do">专委会</a>
                    <a href="${ctx}/courseManage/courseList.do">学科导航</a>
                </div>
                 <div class="right_nav">
                	<c:if test="${SESSION_TRAIN_ADMIN_USER != null}">
                		<script>
                			window.localStorage.setItem("isLogin","true");
                		</script>
                    </c:if>
                    <c:if test="${SESSION_TRAIN_ADMIN_USER == null}">
                    	<script>
                			window.localStorage.setItem("isLogin","false");
                		</script>
	                    <a href="${ctx}/login.do" class="login_btn">登录</a>
	                    <a href="${ctx}/registerUser.do">注册</a>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="head_cont">
            <div class="logo"></div>
            <div class="org_logo"><img src="${ctx}${org.filePath}"></div>
            <span class="slogan"><img src="../img/org_slogan.png"></span>
            <div class="menu_bar org_menu">
                <div class="search_form">
                    <input type="text" name="search_input" value="" placeholder="请输入搜索关键词">
                    <button type="button"><i class="fa fa-search"></i></button>
                </div>
                <ul>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS&id=${id}">主页</a></li>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=1">远程项目</a></li>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=2">面授项目</a></li>
                    <li class="active"><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_teacherList&id=${id}">名师团队</a></li>
                    <li><a href="">机构公告</a></li>
                    <li><a href="">关于我们</a></li>
                </ul>
            </div>
        </div>
    </div>
        <div class="content no_padding top_border">
        <h1 class="main_title_2">${org.name}</h1>
        <p class="clear"><img src="${ctx}${org.photoPath}" class="float_left" width="107">${org.description}</p>
    </div>
    <div class="filter_cont">
        <ul>
            <li>学科：</li>
            <li class="so_many item_list">
                <span class="explore">展开 <i class="fa fa-angle-down"></i></span>
                <span onclick = "javascript:searchProp(0)" <c:if test = "${propId eq '' || propId eq 0}"> class="all active"</c:if>>全部</span>
                <c:forEach items = "${propList}" var = "prop">
                	<span onclick = "javascript:searchProp('${prop.id}')" <c:if test = "${propId == prop.id}">class="active"</c:if>>${prop.name}</span>
                </c:forEach>
            </li>
        </ul>
    </div>
    <div class="content top_border">
        <ul class="teachers_list">
            <c:forEach items = "${Org_Teacher}" var = "item">
	            <li onclick = "javascript:viewTeacher('${item.id}');" style="background:url(../upload/expert/${item.photo}) no-repeat center;background-size:100% auto" >
	                <div class="bg_color"></div>
	                <div class="bg_intro">
	                    <h2>${item.name}</h2>
			            <h3>
				            <c:forEach items="${unitList}" var="unit">
								<c:if test="${unit.id==item.job}">${item.name}</c:if>
							</c:forEach>&nbsp;&nbsp;${item.workUnit}
						</h3>
			            <h3>		
							<c:if test="${item.workUnit_office==1}">院长</c:if>
							<c:if test="${item.workUnit_office==2}">副院长</c:if>
							<c:if test="${item.workUnit_office==3}">主任</c:if>
							<c:if test="${item.workUnit_office==4}">副主任</c:if>
							<c:if test="${item.workUnit_office==5}">所长</c:if>
							<c:if test="${item.workUnit_office==6}">副所长</c:if>
							<c:if test="${item.workUnit_office==7}">护士长</c:if>
						</h3>             
	                </div>
	            </li>
            </c:forEach>
        </ul>
        <display:table id="teacher" name="pageTeacher" requestURI="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=${type}"
					decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" pagesize="10" class = "teacher_table">
		</display:table>
    </div>    
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
function viewTeacher(id)
    {
    	var url = "${ctx}/teacherManage/teacherDetail.do?mode=view&id=" + id;
    	window.open(url,"_blank");
    }
function searchProp(id)
    {
    	document.location.href = "${ctx}/teacherManage/teacherManage.do?propId=" + id;
    }
</script>
</html>