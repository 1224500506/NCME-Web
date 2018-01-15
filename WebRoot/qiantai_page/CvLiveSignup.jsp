<%@page import="java.util.List"%>
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
     <script src="${ctx}/qiantai_page/js/document.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <script src="${ctx}/qiantai_page/js/rating.js"></script>
    <script src="${ctx}/qiantai_page/js/data.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/styles.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/axure_rp_page.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/default.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/maps/default.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/maps/global.css.map" rel="stylesheet">
    	
    <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>
</head>
<body>
<div class="container">
     <%@include file="/qiantai_page/top2.jsp"%>
    <%@include file="/qiantai_page/zhiboPayComm.jsp" %>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="index.html">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="project_list.html">分类名称<a> <i class="fa fa-angle-right"></i></a></a></li>
        <li><a href="project_detail.html" id="name1"><%=request.getParameter("name")%></a></li>
    </ul>
      <ul class="project_info">
   
        <li>
            <h1 class="p_title"><span class="right_icon"><i class="fa fa-share-alt">
            </i><div class="jiathis_style_32x32" style="display:none">
            <a class="jiathis_button_weixin"></a> <a class="jiathis_button_tsina"></a> <a
                    class="jiathis_button_qzone"></a> <a class="jiathis_button_douban"></a></div><script
                    src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script><i
                    class="like fa fa-star fa-star-o"></i> </span><%=request.getParameter("name")%>
            </h1>
            <h3 class="p_sub_title">学科：医学基础</h3><span class="item_cover"
             style="background:url(img/covers/cover_1.jpg) no-repeat center;background-size:cover"></span>

            <div class="item_cont">
                <h2 class="title">
                </h2>
                <div class="info"><span>报名人数：200</span>
                 <span id ="startDate">直播时间：<%=request.getParameter("startDate")%></span>
                 <span>授课教师：<%=request.getParameter("teacher").substring(5)%></span> <span>单位：<%=request.getParameter("suyu")%></span>
                    <span id="name2">所属项目：<%=request.getParameter("name2")%></span>
                    <span id="hi" style="display: none;"><%=request.getParameter("costType")%></span>
                     <span>项目编号：<%=request.getParameter("code")%></span>
                </div>
                </br>
                <span style="display: none" id="cvsetId" name="cvsetId"><%=request.getParameter("id")%></span> 
            </div>
           <div class="bottom">
                <p>
                    <button type="button" name="study_begin" style="display: block;" class="btn btn_orange btn_big whj">报名</button>
                     <button type="button" name="already_study" style = "display: none;"class="btn btn_gray btn_big zxw">已报名</button>
                </p>
            </div>
        </li>
    </ul>
    <div class="content top_border">
        <div class="main_cont">
            <div class="cont"><h2 class="title"><span>课程简介</span></h2>

                <p style="text-indent:2em">
                <%=request.getParameter("introduction")%>
                   </p>
            </div>
            <div class="cont"><h2 class="title"><span>授课教师</span></h2>
                <ul class="teachers clearfix">
                    <li class="user_info"><span class="avatar"><img src="img/avatars/002.jpg"></span>

                        <p class="user_name"><%=request.getParameter("teacher").substring(5)%></p>

                        <p class="user_cont">教授<br>主任医师</p></li>
                   <!--  <li class="user_info"><span class="avatar"><img src="img/avatars/003.jpg"></span>

                        <p class="user_name">钱老师</p>

                        <p class="user_cont">教授<br>主任医师</p></li>
                    <li class="user_info"><span class="avatar"><img src="img/avatars/004.jpg"></span>

                        <p class="user_name">孙老师</p>

                        <p class="user_cont">教授<br>主任医师</p></li>
                    <li class="user_info"><span class="avatar"><img src="img/avatars/005.jpg"></span>

                        <p class="user_name">李老师</p>

                        <p class="user_cont">教授<br>主任医师</p></li> -->
                 
                </ul>
            </div>
        </div>
        <div class="aside cont"><h2 class="title"><span>目录</span></h2>
            <ul class="course_list">
                <li>课程1：人工晶体的计算</li>
                <ul>
                    <li class="has_done"><i class="fa fa-check-circle"></i> <a href="course_detail.html">单元1：生物学测量</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-video-camera"></i></span></li>
                    <li><i class="fa fa-check-circle"></i> <a href="course_detail.html">单元2：考试测验</a> <span><i
                            class="fa fa-pencil-square"></i></span></li>
                    <li><i class="fa fa-circle"></i> <a href="course_detail.html">单元3：适当的IOL计算公式</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-commenting"></i></span></li>
                    <li><i class="fa fa-circle-o"></i> <a href="course_detail.html">单元4：随堂讨论</a> <span><i
                            class="fa fa-file-text"></i></span></li>
                    <li><i class="fa fa-circle-o"></i> <a href="course_detail.html">单元5：拓展阅读</a> <span><i
                            class="fa fa-file-text"></i></span></li>
                </ul>
                <li>课程2：人工晶体的选择</li>
                <ul>
                    <li><i class="fa fa-check-circle"></i> <a href="course_detail.html">单元1：人工晶体的发展概述</a> <span><i
                            class="fa fa-video-camera"></i></span></li>
                    <li><i class="fa fa-check-circle"></i> <a href="course_detail.html">单元2：随堂测验</a> <span><i
                            class="fa fa-pencil-square"></i></span></li>
                    <li class="has_done"><i class="fa fa-circle"></i> <a href="course_detail.html">单元3：人工晶体的选择策略</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-commenting"></i></span></li>
                    <li><i class="fa fa-circle-o"></i> <a href="course_detail.html">单元4：随堂讨论</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-file-text"></i></span></li>
                    <li><i class="fa fa-circle-o"></i> <a href="course_detail.html">单元5：拓展阅读</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-file-text"></i></span></li>
                </ul>
                <li>课程3：标本兼治——关注白内障术后干眼</li>
                <ul>
                    <li><i class="fa fa-check-circle"></i> <a href="course_detail.html">单元1：干眼症的鉴别诊断</a> <span><i
                            class="fa fa-video-camera"></i></span></li>
                    <li><i class="fa fa-check-circle"></i> <a href="course_detail.html">单元2：考试测验</a> <span><i
                            class="fa fa-pencil-square"></i></span></li>
                    <li class="has_done"><i class="fa fa-circle"></i> <a href="course_detail.html">单元3：干眼液的选择策略</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-commenting"></i></span></li>
                    <li><i class="fa fa-circle-o"></i> <a href="course_detail.html">单元4：病例分享</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-file-text"></i></span></li>
                    <li><i class="fa fa-circle-o"></i> <a href="course_detail.html">单元5：拓展阅读</a><i
                            class="fa fa-info-circle"> 任务点</i> <span><i class="fa fa-file-text"></i></span></li>
                </ul>
            </ul>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>

<div class="popup aaa">
    <div class="mask"></div>
    <div class="popup_cont clearfix login_form" ><h2 class="qrbm1"><span class="close"><i class="fa fa-times"></i> </span>面授报确认</h2>

        <div class="join_form" id=" popup_form1">
            <div class="input_div">
                <p class="qrbm">您确认报名【面授项目名称】第1期2017-10-10至2017-10-12的面授项目吗？</br><em>注意：该项目费用1000元，请在线下缴费。</em></p>
            </div>
            <div class="input_div qxqd">
                <button name="no" type="button" class="btn" style="margin-right: 12px;">取&nbsp;消</button>
                <button name="comment_submit" type="button" class="btn btn_orange btn_small">确&nbsp;定</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/qiantai_page/zhiboLoginComm.jsp" %>
<script src="${ctx}/qiantai_page/js/zhibaoSignup.js"></script>

<script>
$(function () {
	var x = document.getElementById("cvsetId").innerHTML;
	console.log("oooooo")
	$.ajax({
		type: 'POST',
		async:false, 
		url:  ctxJS + '/cvLiveSignupManage/find.do?mode=find&cvsetId='+x,
		dataType: 'JSON',
		success : function(data){
			var result = eval(data);
			if(result.message =='errorMsg'){
				console.log("nininini")
				$("button[name=study_begin]").hide();
				$("button[name=already_study]").show();
			}else if(result.message== 'success'){
				console.log("wowowo")
				$("button[name=study_begin]").show();
				
			}else if(result.message=='errorMsg2'){
				alert("请先登录！！");
			}
		}
	});
	
});



$(function() {
    $(".go_comment").click(function () {
        $(".comment_starts,.comment_form").show()
    }),
    $(".course_list ul li:not('.has_done')").on("mouseover", function () {
        $(this).find(".fa-info").show()
    }),
    $(".course_list ul li:not('.has_done')").on("mouseout", function () {
        $(this).find(".fa-info").hide()
    }),
    $(".course_list ul li.has_done").on("mouseover", function () {
        $(this).find(".fa-info-circle").removeClass("fa-info").show()
    }), 
    $(".course_list ul li.has_done").on("mouseout", function () {
        $(this).find(".fa-info-circle").hide().addClass("fa-info")
    }),
    $(".star-rating-1").rating(function (t, n) {
        1 == t && $(".star-rating-1").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-1").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-1").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-1").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-1").parent().find(".star_tip").text("内容很丰富，收获很多")
    }),
    $(".star-rating-2").rating(function (t, n) {
        1 == t && $(".star-rating-2").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-2").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-2").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-2").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-2").parent().find(".star_tip").text("内容很丰富，收获很多")
    }),
    $(".star-rating-3").rating(function (t, n) {
        1 == t && $(".star-rating-3").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-3").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-3").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-3").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-3").parent().find(".star_tip").text("内容很丰富，收获很多")
    }), 
    $(".star-rating-4").rating(function (t, n) {
        1 == t && $(".star-rating-4").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-4").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-4").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-4").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-4").parent().find(".star_tip").text("内容很丰富，收获很多")
    }), 
    $(".user_info").click(function () {
        window.open("teacher_detail.html", "blank")
    }),
    $("button[name=study]").click(function () {
        window.location.href = "course_detail.html"
    }),
    $("button[name=study_begin]").click(function () {
    	console.log("111111");
    	$.ajax({
			type: 'POST',
			url:  ctxJS + '/study/logstudy.do?mode=queryLogin',
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message == 'noLogin'){
					console.log("22222")
					$(".popup").show();
					$(".aaa").hide();
				}else if(result.message=='success'){
						var x100 = document.getElementById("hi").innerHTML;
						var x2 = document.getElementById("cvsetId").innerHTML;
					   	$.ajax({
						    url:ctxJS + "/study/logstudy.do?mode=CheckBind&cvsetId="+x2+"&costType="+x100,
						    type: 'POST',
						    dataType: 'json',
						    success: function(data){   
						    	var result = eval(data);
								if(result.card == '0'){
									$(".aaa").show(); 
								}else{
								   $(".popup").hide();
								   $(".aaa").hide();
								   $(".paybox").show();
									return false;
								}
						    }
						});	 
				}
			}
    	});
    }), 
    $(".close,button[name=submit]").click(function () {
        $(".popup").hide()
    }), 
    $("button[name=submit]").click(function () {
        window.localStorage.setItem("isLogin", "true")
    }), 
    $(".fa-share-alt, .jiathis_style_32x32 ").on("mouseover", function () {
        $(".jiathis_style_32x32").show()
    }),
    $(".fa-share-alt, .jiathis_style_32x32 ").on("mouseout", function () {
        $(".jiathis_style_32x32").hide()
    }),
    $(".comment_form .comment_cont").on("keyup", function () {
        var t = $(".comment_form .comment_cont").val().length;
        if (t > 200) {
            $(".comment_form .num_count").text(" 0 ");
            var n = $(".comment_form .comment_cont").val().substring(0, 200);
            $(".comment_form .comment_cont").val(n)
        } else $(".comment_form .num_count").text(200 - t)
    }); 
    $("button[name=yes],button[name=no],.close").click(function () {
        $(".popup").hide();
    });
});


  $("#paysubmit").click(function(){
        	var cardnumberval = $("#cardnumber").val();
        	var cardpwd = $("#cardpwd").val();
        	var x9 =<%=request.getParameter("id")%>
        	console.log(x9);
        	/* var zbcvsetid = $("#cvsetId").val(); */
        	/* var zbcvid = $("#zbcvid").val(); */
        	if(!cardnumberval){
        		alert("请输入卡号");
        		return false;
        	}
        	if(!cardpwd){
        		alert("请输入密码");
        		return false;
        	}
        	
         	$.ajax({
				type: 'POST',
				url: ctxJS + "/study/logstudy.do?mode=playSubmit&cvsetId="+x9+"&cardNumber="+cardnumberval+"&cardPassword="+cardpwd,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'success'){
						//弹出报名的框
						$(".aaa").show(); 
						$(".paybox").hide();
						
					}else if(result.message == 'noenable'){
						alert("该卡已被使用，无法重复添加"); //卡状态不可用
						return false;
					}else if(result.message == 'time'){
						alert("您的学习卡已经过期");
						return false;
					}else if(result.message == 'notfind'){
						alert("输入的卡号或密码错误");
						return false;
					}else if(result.message == 'typeno'){
						alert("卡类型无效");
						return false;
					}else if(result.message == 'alreadyBind'){//添加改卡已经绑过的情况处理--taoliang
						alert("此卡已被用户使用！");
						return false;
					}
				}
	    	});
        });

$("button[name=comment_submit]").click(function () {
	var x = document.getElementById("cvsetId").innerHTML;
	var x1= document.getElementById("name1").innerHTML;
	var x2= document.getElementById("startDate").innerHTML;
	var x3= document.getElementById("name2").innerHTML;
	
	
	
    	console.log(x2)
    	$.ajax({
			type: 'POST',
			url:  ctxJS + '/cvLiveSignupManage/add.do?mode=add&cvsetId='+x,
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message == 'errorMsg'){
					console.log("555555")
					alert("报名失败");
				}else if(result.message=='success'){
					console.log("66666")
					
					window.location.href = ctxJS + "/qiantai_page/success.jsp?name="+x1+"&date="+x2+"&name2="+x3;
				};
			}
    });
}); 


</script>
</body>
</html>