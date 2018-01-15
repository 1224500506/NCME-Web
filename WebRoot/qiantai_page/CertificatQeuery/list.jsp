<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=0,maximum-scale=1,user-scalable=yes">
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
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script>
    <script src="${ctx}/qiantai_page/js/jquery.qrcode.min.js?rev=ddb79e5abac8e281bbdd3cc48d8462cb"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js?rev=21c2f98ef43f0a8b456ab54440b323bc"></script>
    <script src="${ctx}/qiantai_page/js/validate.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/iconfont.css" rel="stylesheet">

    <style type="text/css">
        .tank{
            position: fixed;
            bottom: 10px;
            right: 10px;
            z-index:999999;

        }
        .callnum a{
            color: #ffffff;
            font-size: 26px;
            display: inline-block;
            text-decoration: none;
            float:right;
            cursor:pointer;
        }
        .callnum img{
            vertical-align: middle;
        }
        #udeskIm,#udeskCall{
            display: block;
        }

        .callnum {
            display: none;
            position: absolute;
            right: 95px;
            top: 118px;
            width: 185px;
            padding: 5px 8px;
            text-align: center;
            border-radius: 5px;
            color: rgb(255, 255, 255);
            background-color: rgb(91, 177, 255);
        }
        .zhi{

            padding: 5;
            position: absolute;
            margin-left: 4px;
            padding-top:8px;
        }


        #box span{


            text-align:right;
            font-weight: normal;
            font-size: 14px;

        }

    </style>
    <% String idCard = request.getParameter("CERTIFICATE_NO"); 
       System.out.println("idCard==="+idCard);
    %>
</head>


<body>
<div class="container">
    <div class="header header_main">
        <div class="tank">
            <a href="###"><img src="/qiantai_page/img/NCME.png"></a>
            <a href="javascript:;" id="udeskIm"><img src="/qiantai_page/img/im.png"></a>
            <a href="javascript:;" id="udeskCall"><img src="/qiantai_page/img/call_02.png"></a>
            <div class="callnum">
                <img src="/qiantai_page/img/telcall.png" alt="Udesk拨打电话图标">
                <span>400-863-1000</span>
                <a>×</a>
            </div>
        </div>
        <div class="head_cont">
            <!--  <div class="logo"></div> -->
            <h1 class="logo"><a href="http://www.ncme.org.cn" title="中国继续医学教育网_NCME">中国继续医学教育网_NCME</a></h1>
            <div class="menu_bar">
                <ul>
                    <li><a href="${ctx}/first.do">首页</a></li>
                    <li id="menu2"><a href="javascript:void(0)" onclick="projectList_sign('公需科目')">公需科目</a></li>
                    <li id="menu3"><a href="javascript:void(0)" onclick="projectList_xueke('全科医学')">基层学院</a></li>
                    <li id="menu4"><a href="${ctx}/Ability.do">胜任力</a></li>
                    <li id="menu5"><a href="${ctx}/teacherManage/teacherManage.do">名师</a></li>
                    <li id="menu6"><a href="${ctx}/OrgManage/OrgManage.do">机构</a></li>
                    <li id="menu7"><a href="${ctx}/ExpertGroup.do">专委会</a></li>
                    <li id="menu8"><a href="${ctx}/qiantai_page/edu/edu_man.jsp">继教管理</a></li>
                    <li id="menu9"><a href="${ctx}/qiantai_page/CertificatQeuery/Certif_query.jsp">证书查询</a></li>
                    <li id="menu10"><a href="${ctx}/courseManage/courseList.do">学科导航</a></li>
                    <li id="menu11"><a href="${ctx}/haiWaiShiYe.do">海外视野</a></li>
                    <li id="menu12"><a href="${ctx}/specialAbility.html">专项能力</a></li>
                </ul>
                <div class="search_form">
                    <input type="text" name="search_input"  id="search_input" value="${Search}" placeholder="请输入搜索关键词" onmouseover="barHeight();">
                    <button type="button" id ="search_button"><i class="icon iconfont">&#xe604;</i></button>
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
                        <a href="${ctx}/login.do">登录</a>
                        <a href="${ctx}/registerUser.do">注册</a>
                        <a href="${ctx}/help.jsp">帮助</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="#">证书查询</a></li>
    </ul>
    
    
    <form name="ProjectList" id = "ProjectList" action="${ctx}/ProjectList.do" method="post">
    <div class="tips_cont"></div>
     <table  class="cert_result">
     <caption id="box" style="text-align: left;
   										 margin-left: 12px;">

               		 </caption>
                <thead>
                <tr id="tr">
                    <td>序号</td>
                    <td>年度</td>
                    <td>真实姓名</td>
                    <td>项目名称</td>
                    <td>学分类型</td>
                    <td>授予学分</td>
                    <td>申请时间</td>
                    <td>学分证书</td>
                </tr>
                </thead>
                <c:forEach items="${list}" var="c" varStatus="index">
				<tr>
				<td>${c.class_name}</td>
				</tr>
				</c:forEach>
				</table>
    
 <%@include file="/qiantai_page/bottom.jsp"%>
 <script>
    </script>
    
</div>
</body>
</html>