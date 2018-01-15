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
<form name="OrgProjectList" id = "OrgProjectList" action="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=${type}" method="post">
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
                    <li class="<c:if test="${type == 1}">active</c:if>"><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=1">远程项目</a></li>
                    <li class="<c:if test="${type == 2}">active</c:if>"><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=2">面授项目</a></li>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_teacherList&id=${id}">名师团队</a></li>
                    <li><a href="">机构公告</a></li>
                    <li><a href="">关于我们</a></li>
                </ul>
            </div>
        </div>
    </div>
    <input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
    <div class="filter_cont" style="margin-top:20px;border-top:1px solid #999;padding-top:30px">
        <ul class="no_hidden">
            <li>学科：</li>
            <li class="so_many item_list">
                <span class="all xueke_quanbu <c:if test = "${xueke eq 0 || xueke eq null}">active</c:if>">全部</span>
            </li>
        </ul>
        <ul>
            <li>类型：</li>
            <li class="item_list">
                <span id="sign1" onclick = "javascript:selSign(-1);"  class = "<c:if test="${sign == -1 || sign==null}">active</c:if>">全部</span>
                <span id="sign1" onclick = "javascript:selSign(0);"  class = "zhinan <c:if test="${sign == 0}">active</c:if>">指南解读</span>
                <span id="sign1" onclick = "javascript:selSign(1);"  class = "gongxu <c:if test="${sign == 1}">active</c:if>">公需科目</span>
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
                <input type="hidden" id="level_h" name="level_h" value = "${level}"/>
                <input type="hidden" id="score_sort" name="score_sort" value="${scoreSort}" />
            	<input type="hidden" id="cost_sort" name="cost_sort" value="${costSort}" /> 
            	<input type="hidden" id="recent_create" name="recent_create" />  
            </li>
        </ul>
    </div>
    <div class="content top_border">
        <ul class="item_filter">
           <li class="search">
               <input type="text" name="item_name" id="item_name" value="${ItemName}" placeholder="请输入课程名称">
               <button name="item_search_btn" type="button" onclick="javascript:name_search();"><i class="fa fa-search"></i></button>
           </li>
           <li onclick="javascript:RecentCreate();" class="new active">最新</li>
   		   <li onclick="javascript:ScoreSort();">学分 <i class="<c:if test="${scoreSort eq 'asc'}">fa fa-angle-up fa-angle-down</c:if> <c:if test="${scoreSort eq 'desc'}">fa fa-angle-up fa-angle-up</c:if>"></i></li>
  		   <li onclick="javascript:CostSort();">价格 <i class="<c:if test="${costSort eq 'asc'}">fa fa-angle-up fa-angle-down</c:if> <c:if test="${costSort eq 'desc'}">fa fa-angle-up fa-angle-up</c:if>"></i></li>
        </ul>
        <ul class="item_list item_img_list">
            <c:forEach items = "${Org_CVSet}" var = "item">
            <li onclick="javascript:gotoDetail('${item.id }')">
                <div class="item_cover">
					<img src="${ctx}${item.file_path}" />
				</div>
                <div class="item_cont">
                    <h2 class="title">${item.name}</h2>
                    <div class="info">
                        <span>项目负责人：<c:forEach items = "${item.managerList}" var = "manager">${manager.name},</c:forEach></span>
                    </div>
                    <p class="desc">${item.introduction}</p>
                   	<p>	<c:if test = "${item.level eq 1}">国家I类</c:if>
	                    <c:if test = "${item.level eq 2}">省级I类</c:if>
	                    <c:if test = "${item.level eq 3}">市级I类</c:if>
	                    <c:if test = "${item.level eq 4}">省级II类</c:if>
	                    <c:if test = "${item.level eq 5}">市级II类</c:if>		             
                     	${item.score}分
                    </p>
                    <div class="foot no_border">
                        <span class="font_red">${item.cost}</span>
                        <span>1${item.studentNum}次学习</span>
                    </div>
                </div>
            </li>
            </c:forEach>
        </ul>
          <display:table id="OrgCVSet" name="pageOrgCVSet" requestURI="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=${type}"
					decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" pagesize="1" class = "teacher_table">
		</display:table>
    </div>  
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
var xueke = "${xueke}";
$("#item_name").focus();
$(function () {        
        $("#item_name").keydown(function(event){
        	if(event.which == 13){        		
        		$(OrgProjectList).submit();        		
        	}
        }); 
        
        courseUpdate();        
    });
function name_search(){
	$(OrgProjectList).submit();	
}
function gotoDetail(id){
	location.href = "${ctx}/entityManage/entityView.do?id="+id;
}
    
function courseUpdate() {
    	var url = "${ctx}/propManage/getPropListAjax.do";
    	var obj = $(".all");
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		$(result.list).each(function(key, value){
		   			var strSecond = '<span onclick = "javascript:changeClass(this,' + value.id + ');" class = "xueke" name="secondLevel" id ="'+value.id+'">' + value.name + '</span>';
		   			$(obj).after(strSecond);
		   		});
		   		$('.xueke').each(function(key,val){
			     	var id = $(this).prop("id");
			     	if(id == xueke)
			     	{
			     		$(this).addClass("active").siblings().removeClass("active");
			     	}
  		  			});
		   };
	    }
	});	
    }    
function changeClass(obj,id)
{
 	$(obj).addClass("active").siblings().removeClass("active");
 	$("#xueke").val(id);         	       	
  	$(OrgProjectList).submit();
} 
function selSign(sign)
{
    $("#sign").val(sign);         	       	
   	$(OrgProjectList).submit();
}
$(".xueke_quanbu").click(function () {
 	$("#xueke").val("");        	       	
  	$(OrgProjectList).submit();
});
function selLevel(level)
{
    $("#level_h").val(level);         	       	
   	$(OrgProjectList).submit();
}
function ScoreSort() {
	$("#cost_sort").val("");
    var asc = $("#score_sort").val();
	if(asc == "")
		asc = "asc";
	else if(asc == "asc")
		asc = "desc";
	else
		asc = "asc";
	$("#score_sort").val(asc);
	$(OrgProjectList).submit(); 
}
function CostSort() {
	$("#score_sort").val("");
	var asc = $("#cost_sort").val();
	if(asc == "")
		asc = "asc";
	else if(asc == "asc")
		asc = "desc";
	else
		asc = "asc";
	$("#cost_sort").val(asc);
	$(OrgProjectList).submit();  		
}
function RecentCreate(){
	$("#cost_sort").val("");
	$("#score_sort").val("");
	$("#recent_create").val("RC");
	$(OrgProjectList).submit();
}
</script>
</html>