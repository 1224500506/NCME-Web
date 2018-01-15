<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/qiantai_page/css/iconfont.css" rel="stylesheet">
<style type="text/css">
    .tank{
        position: fixed;
        bottom: 10px;
        right: 10px;
        z-index:999999;
       
    }
    #udeskIm,#udeskCall{
        display: block;
    }
    .callnum{
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
    </style>
<!-- 头部 -->
<div class="header index_top">
    <div class="top">
        <div class="top_cont">
            <div class="left_nav">
                <a href="${ctx}/first.do">首页</a>
                <a href="javascript:void(0)" onclick="projectList_sign('公需科目')">公需科目</a>
                <a href="javascript:void(0)" onclick="projectList_xueke('全科医学')">基层学院</a>
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
                    <a href="${ctx}/help.jsp">帮助</a>
                </c:if>
            </div>
        </div>
    </div>
    <div class="head_cont">
        <h1 class="logo"><a href="${ctx}/first.do" title="中国继续医学教育网_NCME">中国继续医学教育网_NCME</a></h1>
        <div class="menu_bar subject_menu">
            <ul>
                <li><a href="javascript:void(0)" onclick="projectList_xueke('全科医学')">全科医学</a></li>
                <li><a href="javascript:void(0)" onclick="projectList_xueke('妇产科学')">妇产科学</a></li>
                <li><a href="javascript:void(0)" onclick="projectList_xueke('儿科学')">儿科学</a></li>
                <li><a href="javascript:void(0)" onclick="projectList_xueke('精神卫生学')">精神卫生学</a></li>
                <li><a href="javascript:void(0)" onclick="projectList_xueke('康复医学')">康复医学</a></li>
                <li><a href="${ctx}/ProjectList.do">更多</a> <i class="fa fa-ellipsis-h"></i></li>
            </ul>
            <div class="search_form" >
                <input type="text" name="search_input" id="search_input"  value="" placeholder="请输入搜索关键词" >
                <button type="button" id="search_button"><i class="icon iconfont">&#xe604;</i></button>
            </div>
        </div>
    </div>
</div>
<div class="tank">
	<a href="###"><img src="${ctx}/qiantai_page/img/NCME.png"></a>
    <a href="javascript:;" id="udeskIm"><img src="${ctx}/qiantai_page/img/im.png"></a>
    <a href="javascript:;" id="udeskCall"><img src="${ctx}/qiantai_page/img/call_02.png"></a>
    <div class="callnum">
        <img src="${ctx}/qiantai_page/img/telcall.png" alt="Udesk拨打电话图标">
        <span>400-863-1000</span>
        <a>×</a>
    </div>
</div>
<script>
	var url = $("#url").val();

	function viewSubject(propId) {
		window.location.href = "${ctx}/ProjectList.do?xueke=" + propId;
	}
	function searchProject(mode) {
		if (mode == 1) {
			window.location.href = "${ctx}/ProjectList.do?sign="
					+ encodeURI("指南解读");
		}
		if (mode == 2) {
			window.location.href = "${ctx}/caseList.do";
		}
		if (mode == 3) {
			window.location.href = "${ctx}/liveList.do";
		}
		if (mode == 4) {
			window.location.href = "${ctx}/vrList.do";
		}
	}
	function postView(mode, id) {
		var e = "${ctx}/postView.do?mode=" + mode + "&id=" + id;
		var c = 800;
		var d = 1100;
		window
				.open(
						e,
						"",
						"top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="
								+ d + ",height=" + c);
	}
	function viewList(mode) {
		var e = "${ctx}/postList.do?mode=" + mode;
		var c = 900;
		var d = 1300;
		window
				.open(
						e,
						"",
						"top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="
								+ d + ",height=" + c);
	}
	function gotoDetail(id) {
		location.href = "${ctx}/entityManage/entityView.do?id=" + id;
	}
	//$(function() {
		//$('.bxslider').bxSlider({
			//auto : true
		//});
	//});

    (function (a, h, c, b, f, g) {
        a["UdeskApiObject"] = f;
        a[f] = a[f] || function () {
                (a[f].d = a[f].d || []).push(arguments)
            };
        g = h.createElement(c);
        g.async = 1;
        g.src = b;
        c = h.getElementsByTagName(c)[0];
        c.parentNode.insertBefore(g, c)
    })(window, document, "script", "http://ncme.udesk.cn/im_client/js/udeskApi.js?1483061109688", "ud");
    ud({
        "code": "1a9dg2j6",
        "link": "http://ncme.udesk.cn/im_client?web_plugin_id=23375",
        "targetSelector": "#udeskIm"
    });

    $(function(){
        $('#udeskCall').click(function(){
            $('.callnum').css('display','block');
        })
        $('.callnum a').click(function(){
            $(this).parent().css('display','none');
        })
    })
</script>
<script>
	function projectList_sign(str) {
		window.location.href = "${ctx}/ProjectList.do?sign=" + encodeURI(str);
	}
    function projectList_xueke(str) {
        window.location.href = "${ctx}/ProjectList.do?xueke=" + encodeURI(str);
    }
    
/*     function barHeight(){
   	 $(".header,header_main").css("height","61px");
   	 $(".head_cont").css("height","40px");
   	 $(".menu_bar").css("position","absolute");
   	 $(".menu_bar").css("margin-left","85px");
   	 $(".right_nav").hide();
       }; */
    
       
/*        $("#search_input").mouseover(function(){
    	 $(".header,header_main").css("height","61px");
  	   	 $(".head_cont").css("height","40px");
  	   	 $(".menu_bar").css("position","absolute");
  	   	 $(".menu_bar").css("margin-left","85px");
  	  
       });
        */
       
       
    
        $("#search_button").click(function(){
        	
        	window.location.href="${ctx}/searchAction.do?search="+encodeURI(encodeURI($("#search_input").val()));
        })
        
        
    
</script>