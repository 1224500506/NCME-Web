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
    <script src="${ctx}/qiantai_page/js/data.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/styles.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/axure_rp_page.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/default.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/maps/default.css" rel="stylesheet">
    	
    <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>
	 <% String number3 = request.getParameter("number3"); 
	    String number = number3.substring(5);
	    String teacher = request.getParameter("teacher");
	    String teather2 = teacher.substring(5);
	    String introduction = request.getParameter("introduction");
	    String startTime = request.getParameter("startDate");
	    String endDate = request.getParameter("code");
	    System.out.println(endDate);
    %>
</head>
<body>
<div class="bgc"></div>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <%@include file="/qiantai_page/zhiboPayComm.jsp" %>
     <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="index.html">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="project_list.html">分类名称<a> <i class="fa fa-angle-right"></i></a></a></li>
        <li><a href="project_detail.html"><%=request.getParameter("name")%></a></li>
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
                <div class="info"><span>报名人数：<%=request.getParameter("number3").substring(5)%></span>
                 <span>直播时间：<%=request.getParameter("startDate")%></span>
                 <%-- <span>授课教师：${muke.teacherList[0].name}</span>  --%>
                 <span>授课教师：<%=request.getParameter("teacher").substring(5)%></span> <span>单位：<%=request.getParameter("suyu")%></span>
                    <span>所属项目：<%=request.getParameter("name2")%></span> <span>项目编号：<%=request.getParameter("code")%></span>
                </div>
                </br>
              
            </div>
            <div class="bottom">
                <p>
                    <button type="button" name="study_begin" class="btn btn_gray btn_big  whj">已结束</button>
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
    <div class="footer">
        <div class="foot-top">
            <ul class="aside-ul-modify">
                <li><a href="about_us.html">关于我们</a></li>
                <li><a href="contact_us.html">联系我们</a></li>
                <li><a href="terms.html">服务条款</a></li>
                <li><a href="privacy.html">隐私声明</a></li>
            </ul>
        </div>
        <div class="foot_bottom">
            <div class="logo"></div>
            <div class="share">版权所有 &copy; all rights reserved 京ICP备15027172号-1号</div>
        </div>
    </div>
</div>
<!--<div class="popup">-->
    <!--<div class="mask"></div>-->
    <!--<div class="popup_cont clearfix login_form"><h2><span class="close"><i class="fa fa-times"></i> </span>用户登录</h2>-->

        <!--<div class="join_form" id="popup_form">-->
            <!--<div class="input_div"><label>用户名/手机/邮箱</label><input type="text" name="user_name"></div>-->
            <!--<div class="input_div"><label>密码</label><input type="password" name="password"></div>-->
            <!--<div class="input_div"><label>密码</label><input type="text" name="verify_code">-->
                <!--<button type="button" name="verify_btn">1234</button>-->
            <!--</div>-->
            <!--<div class="input_div"><a href="forget_pwd.html" class="float_right">忘记密码？</a> <input type="checkbox"-->
                                                                                                  <!--name="remember_pwd">-->
                <!--在本机记住密码-->
            <!--</div>-->
            <!--<div class="input_div">-->
                <!--<button type="button" name="submit" class="btn btn_orange btn_block">确认登录</button>-->
            <!--</div>-->
        <!--</div>-->
    <!--</div>-->
<!--</div>-->
<!-- <div class="popup">
    <div class="mask"></div>
    <div class="popup_cont clearfix login_form" ><h2 class="qrbm1"><span class="close"><i class="fa fa-times"></i> </span>面授报确认</h2>

        <div class="join_form" id=" popup_form1">
            <div class="input_div">
                <p class="qrbm">您确认报名【面授项目名称】第1期2017-10-10至2017-10-12的面授项目吗？</br><em>注意：该项目费用1000元，请在线下缴费。</em></p>
            </div>
            <div class="input_div qxqd">
                <button name="no" type="button" class="btn" style="margin-right: 12px;">取&nbsp;消</button>
                <button name="comment_submit" type="button" class="btn btn_orange btn_small"><a href="报名成功.html" class="colf">确&nbsp;定</a></button>
            </div>
        </div>
    </div>
</div> -->
<script>$(function () {
    $(".go_comment").click(function () {
        $(".comment_starts,.comment_form").show()
    }), $(".course_list ul li:not('.has_done')").on("mouseover", function () {
        $(this).find(".fa-info").show()
    }), $(".course_list ul li:not('.has_done')").on("mouseout", function () {
        $(this).find(".fa-info").hide()
    }), $(".course_list ul li.has_done").on("mouseover", function () {
        $(this).find(".fa-info-circle").removeClass("fa-info").show()
    }), $(".course_list ul li.has_done").on("mouseout", function () {
        $(this).find(".fa-info-circle").hide().addClass("fa-info")
    }), $(".star-rating-1").rating(function (t, n) {
        1 == t && $(".star-rating-1").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-1").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-1").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-1").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-1").parent().find(".star_tip").text("内容很丰富，收获很多")
    }), $(".star-rating-2").rating(function (t, n) {
        1 == t && $(".star-rating-2").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-2").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-2").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-2").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-2").parent().find(".star_tip").text("内容很丰富，收获很多")
    }), $(".star-rating-3").rating(function (t, n) {
        1 == t && $(".star-rating-3").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-3").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-3").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-3").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-3").parent().find(".star_tip").text("内容很丰富，收获很多")
    }), $(".star-rating-4").rating(function (t, n) {
        1 == t && $(".star-rating-4").parent().find(".star_tip").text("内容很少，几乎用不上"), 2 == t && $(".star-rating-4").parent().find(".star_tip").text("内容较少，收获很小"), 3 == t && $(".star-rating-4").parent().find(".star_tip").text("内容尚可，达到基本期望"), 4 == t && $(".star-rating-4").parent().find(".star_tip").text("内容较充实，收获较多"), 5 == t && $(".star-rating-4").parent().find(".star_tip").text("内容很丰富，收获很多")
    }), $(".user_info").click(function () {
        window.open("teacher_detail.html", "blank")
    }), $("button[name=study]").click(function () {
        window.location.href = "course_detail.html"
    }), $("button[name=study_begin]").click(function () {
        $(".popup").show()
    }), $(".close,button[name=submit]").click(function () {
        $(".popup").hide()
    }), $("button[name=submit]").click(function () {
        window.localStorage.setItem("isLogin", "true")
    }), $(".fa-share-alt, .jiathis_style_32x32 ").on("mouseover", function () {
        $(".jiathis_style_32x32").show()
    }), $(".fa-share-alt, .jiathis_style_32x32 ").on("mouseout", function () {
        $(".jiathis_style_32x32").hide()
    }), $(".comment_form .comment_cont").on("keyup", function () {
        var t = $(".comment_form .comment_cont").val().length;
        if (t > 200) {
            $(".comment_form .num_count").text(" 0 ");
            var n = $(".comment_form .comment_cont").val().substring(0, 200);
            $(".comment_form .comment_cont").val(n)
        } else $(".comment_form .num_count").text(200 - t)
    }); $("button[name=yes],button[name=no],.close").click(function () {
        $(".popup").hide()
    })
})</script>
  </body>
</html>