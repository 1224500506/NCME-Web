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
<form name="OrgList" id = "OrgList" action="${ctx}/OrgManage/OrgManage.do" method="post">
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
                    <a href="${ctx}/qiantai_page/edu/edu_man.jsp">继教管理</a>
                	<a href="${ctx}/qiantai_page/CertificatQeuery/Certif_query.jsp">证书查询</a>
                    <a href="${ctx}/courseManage/courseList.do">学科导航</a>
                    <a href="${ctx}/haiWaiShiYe.do">海外视野</a>
                    <a href="${ctx}/specialAbility.html">专项能力</a>
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
                    <li class="active"><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS&id=${id}">主页</a></li>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=1">远程项目</a></li>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=2">面授项目</a></li>
                    <li><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_teacherList&id=${id}">名师团队</a></li>
                    <li><a href="">机构公告</a></li>
                    <li><a href="">关于我们</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="slider">
        <ul class="bxslider">
            <li><img src="${org.photoPath}"></li>
        </ul>
    </div>
    <div class="content no_padding">

        <div class="cont clearfix">
            <h2 class="title"><span>远程项目<span>已发布的项目${Org_CVSet1.size()}个</span></span><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=1" class="more">更多 <i class="fa fa-angle-right"></i> </a></h2>
            <ul class="item_list item_img_list org_pro">                
                <c:if test = "${Org_CVSet1.size() >= 4 }">
	                <c:forEach items = "${Org_CVSet1.subList(0,4)}" var = "item">                	
		                	<li onclick="javascript:gotoDetail('${item.id}')">
			                    <div class="item_cover">
									<img src="${ctx}${item.file_path}" />
								</div>
			                    <div class="item_cont">
			                        <h2 class="title">${item.name}</h2>
			                        <p class="desc">${item.introduction}</p>
			                        <p>授课教师：${item.teacherList.get(0).name}</p>
			                        <div class="foot no_border">
			                            <span class="font_green">${item.cost}</span>
			                            <span>1${item.studentNum}次学习</span>
			                        </div>
			                    </div>
		                	</li>                	
	                </c:forEach> 
                </c:if>
                <c:if test = "${Org_CVSet1.size() < 4 }">
	                <c:forEach items = "${Org_CVSet1}" var = "item">                	
		                	<li onclick="javascript:gotoDetail('${item.id}')">
			                    <div class="item_cover">
									<img src="${ctx}${item.file_path}" />
								</div>
			                    <div class="item_cont">
			                        <h2 class="title">${item.name}</h2>
			                        <p class="desc">${item.introduction}</p>
			                        <p>授课教师：${item.teacherList.get(0).name}</p>
			                        <div class="foot no_border">
			                            <span class="font_green">${item.cost}</span>
			                            <span>1${item.studentNum}次学习</span>
			                        </div>
			                    </div>
		                	</li>                	
	                </c:forEach> 
                </c:if>                       
            </ul>
        </div>
        <div class="cont clearfix">
            <h2 class="title"><span>面授项目<span>已发布的项目${Org_CVSet2.size()}个</span></span><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_list&id=${id}&type=2" class="more">更多 <i class="fa fa-angle-right"></i> </a></h2>
            <ul class="item_list item_img_list org_face">                
                <c:if test = "${Org_CVSet2.size() >= 4 }">
	                <c:forEach items = "${Org_CVSet2.subList(0,4)}" var = "item">                	
			                <li onclick="javascript:gotoDetail('${item.id }')">
			                    <div class="item_cover">
										<img src="${ctx}${item.file_path}" />
								</div>
			                    <div class="item_cont">
			                        <h2 class="title">${item.name}</h2>
			                        <p class="desc">${item.introduction}</p>
			                        <p>授课教师：${item.teacherList.get(0).name}</p>
			                        <div class="foot no_border">
			                            <span class="font_red">${item.cost}</span>
			                            <span>1${item.studentNum}次学习</span>
			                        </div>
			                    </div>
			                </li>                 	
	                </c:forEach> 
                </c:if>
                <c:if test = "${Org_CVSet2.size() < 4 }">
	                <c:forEach items = "${Org_CVSet2}" var = "item">                	
			                <li onclick="javascript:gotoDetail('${item.id }')">
			                    <div class="item_cover">
										<img src="${ctx}${item.file_path}" />
								</div>
			                    <div class="item_cont">
			                        <h2 class="title">${item.name}</h2>
			                        <p class="desc">${item.introduction}</p>
			                        <p>授课教师：${item.teacherList.get(0).name}</p>
			                        <div class="foot no_border">
			                            <span class="font_red">${item.cost}</span>
			                            <span>1${item.studentNum}次学习</span>
			                        </div>
			                    </div>
			                </li>                 	
	                </c:forEach> 
                </c:if>                                      
            </ul>
        </div>
        <div class="cont clearfix">
            <h2 class="title"><span>名师<span>已推荐名师${Org_Teacher.size()}名</span></span><a href="${ctx}/OrgManage/OrgDetail.do?mode=get_CVS_teacherList&id=${id}" class="more">更多 <i class="fa fa-angle-right"></i> </a></h2>
            <ul class="teacher_list clearfix">
            	<c:if test = "${Org_Teacher.size() >= 6 }">
	                <c:forEach items = "${Org_Teacher.subList(0,6)}" var = "item">
		                <li class="user_info" style="width:30%">
		                    <span class="avatar"><img src="${ctx}${item.photo}"></span>
		                    <p class="user_name">${item.name}</p>
		                    <p class="user_cont">${item.workUnit}</p>
		                </li>
	                </c:forEach>
                </c:if>
                <c:if test = "${Org_Teacher.size() < 6 }">
	                <c:forEach items = "${Org_Teacher }" var = "item">
		                <li class="user_info" style="width:30%">
		                    <span class="avatar"><img src="${ctx}${item.photo}"></span>
		                    <p class="user_name">${item.name}</p>
		                    <p class="user_cont">${item.workUnit}</p>
		                </li>
	                </c:forEach>
                </c:if>
            </ul>
        </div>
        <div class="cont clearfix">
            <h2 class="title"><span>机构公告</span><a class="more">更多 <i class="fa fa-angle-right"></i></a></h2>
            <ul class="news_list float_left" style="width:45%;margin-right:5%;clear:none">
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
            </ul>
            <ul class="news_list float:left;" style="45%;clear:none">
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
                <li><span>2016-11-22</span><a href="" target="_blank">关于在线学习的公告</a></li>
            </ul>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
function gotoDetail(id){
	location.href = "${ctx}/entityManage/entityView.do?id="+id;
}
</script>
</html>