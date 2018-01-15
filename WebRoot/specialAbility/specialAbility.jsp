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
<style type="text/css">
.tank {
	position: fixed;
	bottom: 10px;
	right: 10px;
	z-index: 999999;
}

#udeskIm, #udeskCall {
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

.callnum a {
	color: #ffffff;
	font-size: 26px;
	display: inline-block;
	text-decoration: none;
	float: right;
	cursor: pointer;
}

.callnum img {
	vertical-align: middle;
}
</style>
<script>
	function viewSubject(propId) {
		window.location.href = "./ProjectList.do?xueke=" + propId;
	}
	function searchProject(mode) {
		if (mode == 1) {
			window.location.href = "./ProjectList.do?sign="
					+ encodeURI("指南解读");
		}
		if (mode == 2) {
			window.location.href = "./caseList.do";
		}
		if (mode == 3) {
			window.location.href = "./liveList.do";
		}
		if (mode == 4) {
			window.location.href = "./vrList.do";
		}
	}
	function postView(mode, id) {
		var e = "./postView.do?mode=" + mode + "&id=" + id;
		var c = 800;
		var d = 1100;
		window.open(e,"","top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);
	}
	function viewList(mode) {
		var e = "./postList.do?mode=" + mode;
		var c = 900;
		var d = 1300;
		window.open(e,"","top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);
	}
	function gotoDetail(id) {
		location.href = "./entityManage/entityView.do?id=" + id;
	}

	(function(a, h, c, b, f, g) {
		a["UdeskApiObject"] = f;
		a[f] = a[f] || function() {
			(a[f].d = a[f].d || []).push(arguments)
		};
		g = h.createElement(c);
		g.async = 1;
		g.src = b;
		c = h.getElementsByTagName(c)[0];
		c.parentNode.insertBefore(g, c)
	})
	(window,document,"script","http://ncme.udesk.cn/im_client/js/udeskApi.js?1483061109688","ud");
	ud({
		"code" : "1a9dg2j6",
		"link" : "http://ncme.udesk.cn/im_client?web_plugin_id=23375",
		"targetSelector" : "#udeskIm"
	});
	$(function() {
		$('#udeskCall').click(function() {
			$('.callnum').css('display', 'block');
		});
		$('.callnum a').click(function() {
			$(this).parent().css('display', 'none');
		});
		 $('#udeskFeedback').click(function(){
             window.location.href = "${ctx}/qiantai_page/aFeedback/feedback.jsp";
         });
	});
	/****************************************************************************/
	function projectList_sign(str) {
		window.location.href = "./ProjectList.do?sign=" + encodeURI(str);
	}
	function projectList_xueke(str) {
		window.location.href = "./ProjectList.do?xueke=" + encodeURI(str);
	}
	var url = window.location.href;

	if (url.indexOf("%E5%85%AC%E9%9C%80%E7%A7%91%E7%9B%AE") > -1) {
		$("#menu2").addClass("active");
	}
	if (url.indexOf("%E5%85%A8%E7%A7%91%E5%8C%BB%E5%AD%A6") > -1) {
		$("#menu3").addClass("active");
	}
	if (url.indexOf("Ability") > -1) {
		$("#menu4").addClass("active");
	}
	if (url.indexOf("teacherManage") > -1) {
		$("#menu5").addClass("active");
	}
	if (url.indexOf("Org") > -1) {
		$("#menu6").addClass("active");
	}
	if (url.indexOf("ExpertGroup") > -1) {
		$("#menu7").addClass("active");
	}
	if (url.indexOf("edu_man") > -1) {
		$("#menu8").addClass("active");
	}
	if (url.indexOf("CertificatQeuery") > -1) {
		$("#menu9").addClass("active");
	}
	if (url.indexOf("courseList") > -1) {
		$("#menu10").addClass("active");
	}
	if (url.indexOf("haiWaiShiYe") > -1) {
		$("#menu11").addClass("active");
	}
	$("#search_button").click(function() {
		window.location.href = "./searchAction.do?search="
				+ encodeURI(encodeURI($("#search_input").val()));
	})
	function gotoSzd() {
		window.open("http://szd.ncme.org.cn");
    }
	function gotoZlxr() {
		window.open("http://zlxr.ncme.org.cn");
    }
</script>
</head>
<body>
	<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
	
		<!-- 头部 -->
		<ul class="bread_crumbs">
			<li><br></li>
			<li>您的位置：</li>
			<li><a href="./first.do">首页</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#">专项能力</a></li>
		</ul>
		<div style="height:11px;"></div>
		<div class="content top_border">
			<h1 class="main_title">专项能力</h1>
			<ul class="item_list item_img_list">
				<li onclick="javascript:gotoSzd();"><span class="item_cover"
					style="background: url('${ctx}/img/zxnl1.png') no-repeat center; background-size: cover"></span>
					<!-- <div class="item_cover">
						<div style="background-color:rgba(241,236,241,0.66);margin:142px 0px 0px 0px;padding:3px 0px 5px 10px;">
							<font>远程+面授+临床实践</font>
						</div>
					</div> -->
					<div class="item_cont">
						<div style="background-color:rgba(241,236,241,0.66);margin:-10px -10px 18px -10px;padding:10px 0px 15px 10px;">
							<font>远程+面授+临床实践</font>
						</div>
						<h2 class="title" style="margin-bottom:20px;" title="生殖道感染诊疗技术专项能力培训">生殖道感染诊疗技术专项能力培训</h2>
						<div class="info">
							<span></span>
						</div>
						<p class="desc topvebanner-p" style="height:160px;line-height:20px;">生殖道感染是我国女性常见疾病，为规范、推广相关诊疗技术，设立“生殖道感染诊疗技术专项能力培训项目”，包括生殖道感染干预技术和阴道镜规范操作技术两项专项能力培训。通过“远程+面授+基地”的新型继续教育模式，致力于提升基层医疗机构对生殖道感染的诊疗技术能力。</p>
					</div></li>
				<li onclick="javascript:gotoZlxr();"><span class="item_cover"
					style="background: url('${ctx}/img/zxnl2.png') no-repeat center; background-size: cover"></span>
					<!-- <div class="item_cover">
						<div style="background-color:rgba(241,236,241,0.66);margin:142px 0px 0px 0px;padding:3px 0px 5px 10px;">
							<font>远程+面授+临床实践</font>
						</div>
					</div> -->
					<div class="item_cont">
						<div style="background-color:rgba(241,236,241,0.66);margin:-10px -10px 18px -10px;padding:10px 0px 15px 10px;">
							<font>远程+面授+临床实践</font>
						</div>
						<h2 class="title" style="margin-bottom:20px;" title="肿瘤消融治疗技术专项能力培训">肿瘤消融治疗技术专项能力培训</h2>
						<div class="info">
							<span></span>
						</div>
						<p class="desc topvebanner-p" style="height:154px;">肿瘤消融治疗技术是采用物理方法直接损毁肿瘤的局部治疗技术，属新兴交叉学科。通过设立“肿瘤消融治疗技术专项能力培训项目”，利用“远程+面授+基地”的新型继续教育模式，将有效加强对肿瘤消融技术应用人员的培训，提升其专项能力。</p>
					</div>
					</li>
			</ul>
			<!-- 分页   删除 -->
			<ul class="paginate">
				<li class="first_page"><a href="#">首页</a></li>
				<li class="prev"><a href="#">上一页</a></li>
				<li class="active"><font>1</font></li>
				<li class="prev"><a href="#">下一页</a></li>
				<li class="last_page"><a href="#">尾页</a></li>
				<li style="width: 8em; border: 0 none; padding: 0px 4px 0px 4px;">共<b>&nbsp;2&nbsp;</b>条数据
				</li>
			</ul>
			<!-- 分页 -->
<%-- 	    <%@include file="/commons/page.jsp"%> --%>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</body>
</html>