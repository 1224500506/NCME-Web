<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="继续再教育平台">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="">
    <meta name="description" content="">
    <title>中国继续医学教育网_NCME</title>
    <%@include file="/commons/taglibs.jsp" %>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/rating.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css" rel="stylesheet">
</head>
<style type="text/css">
	.DynamicInformation_img{width:575px;height:370px;overflow:hidden;float:left;}
	.DynamicInformation_right{width:555px;float:right;}
	.DynamicInformation_title{width:100%;}
	.DynamicInformation_title h2{margin:10px 0;background:url('${ctx}/qiantai_page/img/TownshipMedical/icon.png') no-repeat 0px 6px;text-indent:12px;float:left;}
	.DynamicInformation_title a{padding-top:16px;display:block;float:right;color:#1498f1;}
	.DynamicInformation_list li{margin-top:30px;font-size:14px;}
	.DynamicInformation_list li span{font-size:12px;padding:0px 10px 0px 10px;}
	.container .content .item_list.item_img_list li{height:300px !important;}
	.container .content{padding:20px 0;}
	.face-to-face{width:100%;padding:20px 0px 20px 0px;;border:1px solid #dddddd;margin-bottom:20px;background:#fdfdfd;}
	.face-to-face-title h3{font-weight:600;float:left;margin:0px 0px 0px 20px;}
	.face-to-face-title a{margin-right:20px;background:#ee551c;float:right;font-size:16px;color:#fff;padding:10px 15px 10px 15px;}
	.face-to-face ul{margin-left:30px;}
	.face-to-face ul li{margin-top:15px;line-height:30px;font-size:14px;color:#333;}
	.face-to-face ul li span{display:block;float:left;font-weight:600;width:80px;}
	.face-to-face ul li p{width:1150px;}
	.face-to-face ul li p.add{margin-left:80px;}
	.table{width:100%; border:1px solid #E0E0E0;border-radius:10px;-moz-border-radius:10px;-webkit-border-radius:10px;-khtml-border-radius:10px;}
	.table tr{width:100%;}
	.table tr th{text-align:center;height:30px;background:#1498f1;font-size:13px;color:#333;font-weight:500;color:#fff;border:1px solid #dddddd;font-weight:600;}
	.table tr td{text-align:center;padding:10px 0px 10px 0px;font-size:13px;color:#333;font-weight:400;border:1px solid #dddddd;}
	.table tr td a{color:#1498f1;}
</style>
<body>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">项目动态</a></li>
    </ul>
       <div class="filter_cont">
        <ul>
            <li>省份：</li>
            <li class="so_many item_list">
                <span class="all">
                	<c:if test="${code=='111111111'}">
						全国
					</c:if>
					<c:if test="${code!='111111111'}">
						全国&nbsp;>&nbsp;${name}
					</c:if>
				</span>
            </li>
        </ul>
    </div>
	<div class="content no_padding">
		<table class="table">
			<tr>
				<th>标题</th>
				<th>省份</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${data}" var="Sentence">
			<tr>
				<td><a href="${ctx}/DynamicNotice.do?id=${Sentence.id}">${Sentence.title }</a></td>
				<td>
					<c:if test="${Sentence.provinceCode=='111111111'}">
						全国
					</c:if>
					<c:if test="${Sentence.provinceCode!='111111111'}">
						${Sentence.provinceName}
					</c:if>
				</td>
				<td><fmt:formatDate value="${Sentence.deli_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><a href="${ctx}/DynamicNotice.do?id=${Sentence.id}">查看</a></td>
			</tr>
             </c:forEach>
		</table>
		<ul class="paginate">
			<li class="first_page">首页</li>
			<li class="prev">上一页</li>
			<li class="active">1</li>
			<li class="next">下一页</li>
			<li class="last_page">尾页</li>
		</ul>
	</div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<script>
    $(function () {
        $(".p_list li:first-child").click(function () {
            window.open("project_detail.html","new");
        });
        $(".p_list li:last-child").click(function () {
            window.open("org_face_detail.html","new");
        });
        $(".org_list .logo,.org_list h2,.org_list p").click(function () {
            window.open("org1/org_detail.html","new");
        });
    });
</script>
</body>
</html>                                                                                                                                                                                                            