
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
 
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script>
    	var ctxJS = "${ctx}";
	  	$(function () {
	        <!-- 检测用户是否登录-->
	        $.ajax({
	            //接口地址
	            url:'${ctx}/loginAJAX.do?loginCheck=afasdlkfjadls',
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
	                    window.localStorage.setItem("isLogin","true");
	                }else{
	                    window.localStorage.setItem("isLogin","false");
	                }
	            },
	        });
	    });
    </script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
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
    
</head>
<body>
<form id = "fm1" name = "fm1" method = "post" action = "${ctx}/courseManage/courseList.do?type=1">
<div class="container">
	<#-- 头部 -->
<div class="header header_main">
    <div class="head_cont">
       
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
                <input type="text" name="search_input" id="search_input"  value="" placeholder="请输入搜索关键词" >
                        <button type="button" id="search_button"><i class="icon iconfont">&#xe604;</i></button>
            </div>
	       	<div class="right_nav">
	            
	                <a href="${ctx}/login.do" class="login_btn">登录</a>
	                <a href="${ctx}/registerUser.do">注册</a>
	                <a href="${ctx}/help.jsp">帮助</a>
	           
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
	
	<#-- 内容 -->
	
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/courseManage/courseList.do">学科导航</a></li>
    </ul>
 
    <div class="filter_cont content" style = "min-height:500px">
	    <#if prop_val1?? || prop_val1 == 0>
	    	<#list propList as p>
	            <div class="cont filter_cont">
	            	<h2 class="main_title">
	            		<span id ="${p.id}" name ="firstLevel">${p.name}</span>
		          	</h2>
		          	<#list p.examProp2 as p2>
		          		<h2 style="padding-left:20px;font-size:14px;font-weight:bold">
		          			<span name="secondLevel" id ="${p2.id}"> ${p2.name} :</span>
		          		</h2>
		          		<ul class="no_hidden"> <li class="so_many item_list ">
			          	<#list p2.examProp3 as p3>
					    	<#if p3.prop_val1 == 0>
					    		<span style="color:#c0c0c0">${p3.name}</span>
					    	<#else>
						    	<#if p2.id==510005>
						    		<span onclick = "javascript:viewSubject2('${p.id}','${p2.id}' ,'${p3.name}','${p3.id }');">${p3.name}</span>
						    	<#else>
						    		<span onclick = "javascript:viewSubject('${p.id}' ,'${p3.name}','${p3.id }');">${p3.name}</span>
						    	</#if>
					    	</#if>
			          	</#list>
			   		</li></ul>
		          	</#list>
	       		 </div>
	        </#list>
	     </#if>
	     <#if subProp!'' != ''>
	        <div class="cont filter_cont">
	           	<h3 class="main_title">
	           		<span href = "${ctx}/qiantai_page/first.jsp" id ="${subProp.id}" name ="firstLevel">${subProp.name}</span>
	          	</h3>
	       	</div>
	     </#if>
    </div>
    <#-- 尾部 -->
    	<div class="footer">
                <div class="foot-top" style="margin:10px auto;">
                    <ul class="aside-ul-modify">
                        <li>
                            <a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a>
                        </li>
                        <li>
                            <a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a>
                        </li>
                        <li>
                            <a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a>
                        </li>
                        <li>
                            <a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a>
                        </li>
                    </ul>
                </div>
                <a target="_blank" href="http://www.rkrc.cn/" class="foot_bottom" style="line-height:20px;width:650px;magin:10px auto;display:block;">
                    <img src="${ctx}/qiantai_page/img/first_logo.png" style="margin-top:5px;"/>
                </a>
                <div class="foot_bottom" style="padding:10px 0px 0px 0px;">
                    <div class="share" style="text-align:center">
                        版权所有 &copy; all rights reserved 京ICP备12023720号-1<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261128151'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1261128151%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
                    </div>
                </div>
                <div style="width:202px;margin:0 auto;">
                    <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11040202450051">
                        <img src="${ctx}/qiantai_page/img/beian.png" />
                    </a>
                </div>
            </div>
    
</div>
</form>
</body>
<script>
  $(function () {
        <!-- 检测用户是否登录-->
        $.ajax({
            //接口地址
            url:'${ctx}/loginAJAX.do?loginCheck=afasdlkfjadls',
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
                    window.localStorage.setItem("isLogin","true");
                }else{
                    window.localStorage.setItem("isLogin","false");
                }
            },
        });
    });
    
     function viewSubject(propId,pname,id)
     {
     	id = id.replace(/,/g,'');
    	window.location.href="${ctx}/ProjectList.do?xueke=" + encodeURI(pname) + "&ppid=" + id;
     }
     function viewSubject2(propId,pid,pname,id)
     {
     	id = id.replace(/,/g,'');
     	pid = pid.replace(/,/g,'');
    	window.location.href="${ctx}/ProjectList.do?pid="+pid+"&xueke=" + encodeURI(pname) + "&ppid=" + id;
     }
    
     
     	$(".courseLink01").click(function () {
			document.getElementById("fm1").action = "${ctx}/courseManage/courseList.do?type=1&id="+$(this).prop("id");
			$("#fm1").submit();
        });
         $(".all").click(function () {
			document.getElementById("fm1").action = "${ctx}/courseManage/courseList.do?type=1";
			$("#fm1").submit();
        });
     
     
     
     
     
     
     
     var url = $("#url").val();

	
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
        $('.callnum a').click(function(){
            $(this).parent().css('display','none');
        });
    });

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
   
    
    $("#search_button").click(function(){
    	
    	window.location.href="${ctx}/searchAction.do?search="+$("#search_input").val();
    })
    
    
    
     
     
     
     
     
</script>
</html>