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
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>中国继续医学教育网</title>
	
    <%@include file="/commons/taglibs.jsp"%>
    <link href="${ctx}/qiantai_page/css/iconfont.css" rel="stylesheet"> 

	<script src='http://keleyi.com/keleyi/pmedia/jquery-2.0.2.min.js'></script>
	<script src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/qiantai_page/js/jquery-1.8.3.min.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <script src="${ctx}/qiantai_page/aFeedback/file/js/jquery.js"></script>
	<script src="${ctx}/qiantai_page/aFeedback/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate.js"></script>
	<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="${ctx}/qiantai_page/js/fileUpload.js"></script>
    
    <script src="${ctx}/js/migrate.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
    <script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script>
    <script src="${ctx}/qiantai_page/js/util.js"></script>
    <script src="${ctx}/qiantai_page/aFeedback/jquery-form.js"></script>
    
    
    <!-- 根据需求新引进的css,js -->
    <script src="${ctx}/qiantai_page/aFeedback/feedback/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
    <script src="${ctx}/qiantai_page/aFeedback/feedback/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
    <script src="${ctx}/qiantai_page/aFeedback/feedback/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script>
    <script src="${ctx}/qiantai_page/aFeedback/feedback/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script>
    <script src="${ctx}/qiantai_page/aFeedback/feedback/js/main.min.js?rev=21c2f98ef43f0a8b456ab54440b323bc"></script>
    <link href="${ctx}/qiantai_page/aFeedback/feedback/css/imgUp.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/aFeedback/feedback/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/aFeedback/feedback/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet">
    <link href="${ctx}/qiantai_page/aFeedback/feedback/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
	<link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
   
   <script type="text/javascript" src="${ctx}/qiantai_page/aFeedback/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/qiantai_page/aFeedback/js/swfobject.js"></script>
	<script type="text/javascript" src="${ctx}/qiantai_page/aFeedback/js/jquery.uploadify.v2.1.0.min.js"></script>
   
   
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
        
        
    
    .yzmbg{
    
    display: block;
    height: 44px;
    line-height: 44px;
    color: #fff;
    text-align: center;
    margin-top: 20px;
    font-size: 1.5em;
    text-decoration: none;
    clear: both;
    width: 100%;
    }
    
    #yzmCode {
	font-family: Arial, 宋体;
	font-style: italic;
	color: green;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
	}
  

    </style>
</head>


<body>

<div class="container">
   <div class="header header_main">
   <%@include file="/qiantai_page/top2.jsp"%>
        <%-- <div class="tank">
            <a href="###"><img src="${ctx}/qiantai_page/img/NCME.png"></a>
            <a href="javascript:;" id="udeskIm"><img src="${ctx}/qiantai_page/img/im.png"></a>
            <a href="javascript:;" id="udeskCall"><img src="${ctx}/qiantai_page/img/call_02.png"></a>
            <div class="callnum">
                <img src="${ctx}/qiantai_page/img/telcall.png" alt="Udesk拨打电话图标">
                <span>400-863-1000</span>
                <a>×</a>
            </div>
            <a href="javascript:;" id="udeskFeedback"><img src="${ctx}/qiantai_page/img/feedback.png"></a>
        </div>  --%>
        <div class="head_cont">
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
        <li><a href="#">意见反馈</a></li>
    </ul>
 
 	    <div class="content">
 	    <div class="comment_form" style="position: relative" >
            <h1 class="main_title">意见反馈</h1>
            <form class="join_form inline_form" action="" method="post" enctype="multipart/form-data" name="feedbackFrom" id="uploadForm">
            	
            	
                   <div class="fk_tit">
                       <label style=" font-size: 16px; color: #12bce1; font-weight: 600; display: block; width: 100%; text-align: left">意见反馈：</label>
                       <textarea name="content" id="content" class="comment_cont liuyan"></textarea>
                   </div>
					<br>
					<embed src="/images/zhansi.swf" id="flashOn" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="100" height="115" style="display:none;">  
					<input type="file" name="matFile" id="uploadify" />
					<a href="javascript:$('#uploadify').uploadifyUpload()">开始上传</a>
					<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传</a>
					<span id="result" style="font-size: 13px;color: red"></span>
					<div id="fileQueue" style="width:200px;height: 200px; border: 2px solid white;">
					<div id="box"></div>
					</div>
               		
               	<div class="input_div">
                        <p class="input_div_tit">请您留下联系方式，我们会为您严格保密</p>
                        <label><em class="font_red">*</em> 手机号</label>
                        <input type="text" name="telphone" id="tel" style="min-width:25.4em;">
                        <p class="cp" style="color: red; display: none;">*</p>
                    </div>
                
                <div class="input_div">
               		<label><em class="font_red">*</em> 验证码</label>
					<input type="text" id="yzmInput" style="width:8%;" placeholder="验证码"/> <input type="button"
						id="yzmCode" onclick="createCode()" style="width:100px;margin-left:8%;height:40px;font-size:30px"
						title='点击更换验证码' /> <input type="hidden" id ="yzm" />
				</div>
                
                <div class="input_div"><label><em class="font_red">*</em> 其他联系方式</label>
                 <input type="text" name="email" id="qq" style="min-width:25.4em;" placeholder="QQ/邮箱">
                </div>
                <input type="hidden" name="creater" id="creater">
                <input type="hidden" name="image" id="image">
                <div class="input_div">
                    <label>&nbsp;</label>
                   <a   class="btn btn_orange btn_small" onclick="javascript:testUpload();" style="width:140px;margin:10px 20px 0 0;">确认提交</a>
                </div>
                
                <div class="foot" style="overflow: hidden">
<!--                         <button name="comment_submit" type="button" class="btn btn_orange btn_small" style="margin-right: 869px; margin-top: 26px; padding:8px 20px">提　交</button> -->
<!--                         <span class="text_count" style="position: absolute; top: 0px; width: 300px;left: 566px;">还能输入<span class="num_count">200</span>字</span> -->
                    </div>
                </form>

			</div>
			<div style="margin-top:300px;">
		        <%@include file="/qiantai_page/bottom.jsp"%>
		    </div>  
		</div>
            
	</div>
	
	
<script>

    var url = $("#url").val();

    function viewSubject(propId) {
        window.location.href = "http://www.ncme.org.cn/ProjectList.do?xueke=" + propId;
    }
    function searchProject(mode) {
        if (mode == 1) {
            window.location.href = "http://www.ncme.org.cn/ProjectList.do?sign="
                + encodeURI("指南解读");
        }
        if (mode == 2) {
            window.location.href = "http://www.ncme.org.cn/caseList.do";
        }
        if (mode == 3) {
            window.location.href = "http://www.ncme.org.cn/liveList.do";
        }
        if (mode == 4) {
            window.location.href = "http://www.ncme.org.cn/vrList.do";
        }
    }
    function postView(mode, id) {
        var e = "http://www.ncme.org.cn/postView.do?mode=" + mode + "&id=" + id;
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
        var e = "http://www.ncme.org.cn/postList.do?mode=" + mode;
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
        location.href = "http://www.ncme.org.cn/entityManage/entityView.do?id=" + id;
    }

    (function(a,h,c,b,f,g){a["UdeskApiObject"]=f;a[f]=a[f]||function(){(a[f].d=a[f].d||[]).push(arguments)};g=h.createElement(c);g.async=1;g.src=b;c=h.getElementsByTagName(c)[0];c.parentNode.insertBefore(g,c)})(window,document,"script","http://ncme.udesk.cn/im_client/js/udeskApi.js?1483061109688","ud");
    ud({
        "code": "1a9dg2j6",
        "link": "http://ncme.udesk.cn/im_client?web_plugin_id=23375",
        "targetSelector": "#udeskIm"
    });
    $(function(){
    	createCode();
        $('#udeskCall').click(function(){
            $('.callnum').css('display','block');
        });
        $('.callnum a').click(function(){
            $(this).parent().css('display','none');
        });
        $('#udeskFeedback').click(function(){
            window.location.href = "${ctx}/qiantai_page/aFeedback/feedback.jsp";
        });
        
        $(".item_list li").click(function () {
            window.location.href = "project_detail.html"
        }), $(".drop_down a").click(function () {
            $(this).parent().parent().addClass("active").find("i").removeClass("fa-angle-down"), $(this).parent().parent().siblings().removeClass("active")
        })
        $("input[type=file]").change(function(){$(this).parents(".uploader").find(".filename").val($(this).val());});
		$("input[type=file]").each(function(){
			if($(this).val()==""){$(this).parents(".uploader").find(".filename").val("No file selected...");}
		});
        
        	 // 检测用户是否登录
            $.ajax({
                //接口地址
                url:'${ctx}/loginAJAXlogin.do?loginCheck=afasdlkfjadls&time=' + new Date().getMilliseconds(),
                //请求方式
                type:'post',
                //返回数据类型
                dataType:'json',
                async:false,
                //请求失败时处理方式
                error:function(){
                	window.localStorage.setItem("isLogin","false");
                },
                //请求成功时处理方式
                success:function(result){
                    //console.log(JSON.stringify(result))
                    if(result.message == 'loginHasLogin'){
                    	$('#creater').val(result.username);
                    	$('#tel').val(result.telphone);
                       // window.localStorage.setItem("isLogin","true");
                       // window.localStorage.setItem("userimg",result.userimge); //用户登录后保存其头像
     		    		//window.localStorage.setItem("usersex",result.usersex);  //用户性别
                    }else{
                       // window.localStorage.setItem("isLogin","false");
                       // window.localStorage.setItem("usersex",result.usersex); 
                    }
                },
            });
            //首先清除下历史
            /* var storage = window.localStorage.getItem("isLogin");
            var remember_pwd=getCookie("remember_pwd");
            if(storage=="false"&&!remember_pwd=="true"){
                clearCookie();
            } */
          
//             window.ue = UE.getEditor('editor');
        
        
    });
	
    
    
    
    function projectList_sign(str) {
        window.location.href = "http://www.ncme.org.cn/ProjectList.do?sign=" + encodeURI(str);
    }
    function projectList_xueke(str) {
        window.location.href = "http://www.ncme.org.cn/ProjectList.do?xueke=" + encodeURI(str);
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

  /*   $(".login_btn").click(function(){

        location.reload();


    }); */

    function barHeight(){
        $(".header,header_main").css("height","61px");
        $(".head_cont").css("height","40px");
        $(".menu_bar").css("position","absolute");
        $(".menu_bar").css("margin-left","85px");
    };
    function testUpload(){
	 
	 var content =$('#content').val();
		if ($.trim(content) ==""){
			alert ("描述意见不能为空");
			$("#content").focus();
			return false;
		}
		if($('#content').val().length>400||$('#content').val().length<20){   //长度可自定义
	        alert("请完善描述反馈意见，支持输入10~200字符");
	        $("#content").focus();
	    	 return false;
	    }
  		var tel = $('#tel').val();
		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/; 
	    if($.trim(tel) == ""){ 
	    	alert("请填写手机号");
	    	$("#tel").focus();
	    	return false;
	    }else {
	    	if(!myreg.test(tel)){
	    		alert("请填写正确手机号");
		        return false;
	    	}
		
	   	var yzmv ='${random}';
		flag = true;
		if(flag){
			$("#yzm").val(yzmv);
		}
		
		if($('#yzmInput').val() == "")
		{
			alert("请输入验证码！");
			$("#yzmInput").focus();
			return false;
		}
		 if(!validate()){
			return false;
		} 
	    	
	    
			var url = "${ctx}/feedback/feedbackManage.do?method=fileUp";
			$.ajax({
				url:url,
				data:$("#uploadForm").serialize(),
				type	:'post',
				dataType:'text',
				success: function(result){
					if (result == 'success') {
						alert("提交成功！");
						location.replace(location.href);
					}else{
						alert("网络超时，稍后再试！");
						location.replace(location.href);
					}
				}
			});
			return;
			}
		
   }

   
</script>

<script type="text/javascript">
$(document).ready(function() {
	var str="";
	$("#uploadify").uploadify({
		'uploader'       : 'js/uploadify.swf',
		'script'         : '${ctx}/file/fileUpload.do', //调用上传的方法
		'cancelImg'      : 'js/cancel.png',
		'buttonImg'		 : 'js/xzButton.gif',
		'folder'         : '/photos',
		'queueID'        : 'fileQueue',
		'auto'           : false,
		'multi'          : true,
		'wmode'			 : 'transparent',
		'simUploadLimit' : 2,
		'queueSizeLimit' : 5,
		'fileSizeLimit'  :'5MB',
		'buttonText': '<div>上传截图</div>',
		'progressData'	 : 'percentage',
		'fileExt'		 : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
		'fileDesc'		 : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
		'fileObjName'    :'matFile',
		 'fileDataName'  :'matFile',
		'onSelectOnce'   : function(event,data,data)
        {
          filesSelected:true;
        },
        'onComplete' : function(event,queueId,fileObj,response,data)
        {
        	var inputDom=$("<input type='hidden' name='hiddenDom' value='"+response+"' />");
        	var v = JSON.parse(response).message;
            var $image=$("<img src='"+v+"'  width='50px' height='50px'/>");
        	inputDom.appendTo("#box");
        	$image.appendTo("#box");
        	
        	$("#image").val($("#image").val()+","+v);
        	
        	
        },
		'onAllComplete' : function(event,data) 
		{
		   alert('一共上传了'+data.filesUploaded+'张图片');
		},

	});
	if(!flashOn()){
		$("#flashOn").show();
	}
});
function flashOn(){  
    var flag = false;  
    if(window.ActiveXObject){  
      try{  
        var swf = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");  
        if(swf){  
          flag = true;  
        }  
      }catch(e){  
      }  
    }else{  
      try{  
        var swf = navigator.plugins['Shockwave Flash'];  
        if(swf){  
          flag = true;  
        }  
      }catch(e){  
      }  
    }  
    return flag;
  }  
</script>
</body>
</html>
