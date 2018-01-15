<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/qiantai_page/css/iconfont.css" rel="stylesheet">
<style type="text/css">
 #udeskIm,#udeskCall,#erweima2{
                display: block;
            }
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
     .callnum strong{
            cursor:pointer;
            }
    
    .callnum img{
        vertical-align: middle;
    }
     .erweima{
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
            .erweima a{
                color: #ffffff;
                font-size: 26px;
                display: inline-block;
                text-decoration: none;
                float:right;
                cursor:pointer;
            }
            .erweima img{
                vertical-align: middle;
            } 
            .erweima strong{
            cursor:pointer;
            }
    </style>
<!-- 头部 -->
<div class="header header_main">
    <div class="head_cont">
       <!--  <div class="logo"></div> -->
       <h1 class="logo"><a href="${ctx}/first.do" title="中国继续医学教育网_NCME">中国继续医学教育网_NCME</a></h1>
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
                <input type="text" name="search_input"  id="search_input" value="${Search}" placeholder="请输入搜索关键词" >
                <button id="search_button" type="button"><i class="icon iconfont">&#xe604;</i></button>
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
</div>
 <div class="suspension">
        <div class="suspension-wrap">
            <a href="javascript:;" class="service" id="udeskIm" title="在线客服"><span>在线客服</span></a>
            <a href="javascript:;" class="telephone" id="udeskCall" title="电话咨询"><span>电话咨询</span></a>
            <a href="javascript:;" class="Wechat" id="erweima2" title="微信二维码"><span class="code">微信</br>二维码</span></a>
            <a href="javascript:;" class="feedback" id="feedback" title="意见反馈"><span>意见反馈</span></a>

        </div>
        <div class="callnum">
                <img src="${ctx}/qiantai_page/img/telcall.png" alt="Udesk拨打电话图标">
                <span>400-863-1000</span>
                <strong>×</strong>
            </div>
            
            <div class="erweima">
            <img src="${ctx}/qiantai_page/img/cert_search_QR.png">
                <strong>×</strong>
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
	
      (function(a,h,c,b,f,g){a["UdeskApiObject"]=f;a[f]=a[f]||function(){(a[f].d=a[f].d||[]).push(arguments)};g=h.createElement(c);g.async=1;g.src=b;c=h.getElementsByTagName(c)[0];c.parentNode.insertBefore(g,c)})(window,document,"script","http://ncme.udesk.cn/im_client/js/udeskApi.js?1483061109688","ud");
      ud({
        "code": "1a9dg2j6",
        "link": "http://ncme.udesk.cn/im_client?web_plugin_id=23375",
        "targetSelector": "#udeskIm"
      });
    $(function(){
        $('#udeskCall').click(function(){
            $('.callnum').css('display','block');
        });
        $('.callnum strong').click(function(){
            $(this).parent().css('display','none');
        });
        $('#feedback').click(function(){
            window.location.href = "${ctx}/qiantai_page/aFeedback/feedback.jsp";
        })
    });
    $(function(){
        $('#erweima2').click(function(){
            $('.erweima').css('display','block');
        })
        $('.erweima strong').click(function(){
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

    var url = window.location.href;

    if(url.indexOf("%E5%85%AC%E9%9C%80%E7%A7%91%E7%9B%AE") > -1){
        $("#menu2").addClass("active");
    }
    if(url.indexOf("%E5%85%A8%E7%A7%91%E5%8C%BB%E5%AD%A6") > -1){
        $("#menu3").addClass("active");
    }
    if(url.indexOf("Ability") > -1){
    	$("#menu4").addClass("active");
    }
    if(url.indexOf("teacherManage") > -1){
        $("#menu5").addClass("active");
    }
    if(url.indexOf("Org") > -1){
        $("#menu6").addClass("active");
    }
    if(url.indexOf("ExpertGroup") > -1){
        $("#menu7").addClass("active");
    }
    if(url.indexOf("edu_man") > -1){
        $("#menu8").addClass("active");
    }
    if(url.indexOf("CertificatQeuery") > -1){
        $("#menu9").addClass("active");
    }
    if(url.indexOf("courseList") > -1){
        $("#menu10").addClass("active");
    }
    if(url.indexOf("haiWaiShiYe") > -1){
        $("#menu11").addClass("active");
    }
    /* $(".login_btn").click(function(){
    	
    	location.reload();
    	
    	
    }); */
    <%--
    $("#search_input").mouseover(function(){
   	 $(".header,header_main").css("height","61px");
 	   	 $(".head_cont").css("height","40px");
 	   	 $(".menu_bar").css("position","absolute");
 	   	 $(".menu_bar").css("margin-left","85px");
 	   	 $(".right_nav").hide();
      }).mouseout(function(){
   		
   	   $(".right_nav").show(100);
      });
    --%>
    
    $("#search_button").click(function(){
    	
    	window.location.href="${ctx}/searchAction.do?search="+encodeURI(encodeURI($("#search_input").val()));
    })
    
    
    
    
</script>