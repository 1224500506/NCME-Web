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
     <script src="${ctx}/qiantai_page/js/rating.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css" rel="stylesheet">
    	
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
    </ul>
    <div class="content no_padding">
        <ul class="project_info teacher_info">
            <h2 class="pt13 pt14"><%=request.getParameter("name")%>直播课程报名成功！</h2>
            <li class="ml320">
                  <div class="juzhong">
                      <p class="imgfl imgfl_1"> <img src="${ctx}/qiantai_page/img/erweima.png"></p>
                      <p class="des">
                         ◆  <%=request.getParameter("name")%></br> 开课时间：<%=request.getParameter("date").substring(5)%></br></p>
                       <p class="desc">
                         ◆ <%=request.getParameter("name")%><a> <%=request.getParameter("name2").substring(5)%></a>项目，先去学习项目中其他课程。</p>
                       <p class="desc">◆ 扫描左侧二维码，关注微信<a>NCME助手</a>，体验移动学习。</p>
                      </br>
                      </br>
                      </br>
                  </div>
            </li>
        </ul>
    </div>
     <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<div class="popup">
    <div class="mask"></div>
    <div class="popup_cont clearfix login_form"><h2><span class="close"><i class="fa fa-times"></i> </span>用户登录</h2>

        <div class="join_form" id="popup_form">
            <div class="input_div"><label>用户名/手机/邮箱</label><input type="text" name="user_name"></div>
            <div class="input_div"><label>密码</label><input type="password" name="password"></div>
            <div class="input_div"><label>密码</label><input type="text" name="verify_code">
                <button type="button" name="verify_btn">1234</button>
            </div>
            <div class="input_div"><a href="forget_pwd.html" class="float_right">忘记密码？</a> <input type="checkbox"
                                                                                                  name="remember_pwd">
                在本机记住密码
            </div>
            <div class="input_div">
                <button type="button" name="submit" class="btn btn_orange btn_block">确认登录</button>
            </div>
        </div>
    </div>
</div>
  </div>
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
    })
})</script>
<script>
    $(function () {
        $('#open').click(function () {
            if ($('#stop').is(':hidden')) {
                $('#stop').slideDown('slow');
                $(this).text('收起');
            } else {
                $('#stop').slideUp('slow');
                $(this).text('展开更多');
            }
        });
    });
</script>
<script>
    $(function () {
        $('#open1').click(function () {
            if ($('#stop1').is(':hidden')) {
                $('#stop1').slideDown('slow');
                $(this).text('收起');
            } else {
                $('#stop1').slideUp('slow');
                $(this).text('展开更多');
            }
        });
    });
</script>
    

      

</body>
</html>