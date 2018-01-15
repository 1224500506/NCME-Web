<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
    <title>中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script  src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
    <script  src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
    <script  src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script>
    <script  src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script>
    <script  src="${ctx}/qiantai_page/js/main.min.js?rev=21c2f98ef43f0a8b456ab54440b323bc"></script>
    <script  src="${ctx}/qiantai_page/js/swfobject.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
</head>
<body class="view">
<div class="container">
    <div class="course_video" style="position:relative;overflow:auto;">
        <span class="logo"></span>
        <span class="go_back"><i class="fa fa-angle-left"></i> 返回项目详情</span>
        <div class="course_title">
            <h2 id="className">${curCV.name}--${curUnit.name}</h2>
            <h3>
                <span id="classType">类型：话题讨论</span>
                <span id="classTeacher">授课教师：
                	<c:forEach items="${cv.teacherList}" var="teacher" varStatus="teacherStatus">
                		${teacher.name }
                		<c:if test = "${(teacherStatus + 1) != cv.teacherList.size()}">,</c:if>
                	</c:forEach>
                </span>
                <span id="classDB">达标要求：完整观看完</span>
            </h3>
        </div>
        <div class="analysis_container" style="margin-top:105px">
		    <div class="content no_padding" style="width:90%">		          
	        	<div class="comment_top">
	                <h1 class="main_title_2">
	                    <span class="go_comment" style="cursor:pointer"><i class="fa fa-edit"></i> 去讨论 </span>
	               	 讨论点
	                </h1>
	            </div>
	            <div class="comment_form" style="display:none">
	                <textarea id="comment_cont_left" class="comment_cont"></textarea>
	                <div class="foot">
	                    <button id="comment_submit_left" type="button" class="btn btn_orange btn_small">评论</button>
	                    <span class="text_count">还能输入<span class="num_count">200</span>字</span>
	                </div>
	            </div>
	            <div class="news_cont clearfix" style="width:90%">
	                <div class="top_cont">
	                    <h1>临床实验中，您认为有哪些措施能够更好的预防或处理白内障手术并发症？</h1>
	                    <h3>
	                        <span>2016-12-20 14：30</span>
	                        <span>齐虹教授</span>
	                    </h3>
	                </div>                
	             <ul class="comments_list" id="comments_list_main">
	                <!-- place of 病例分析 -->	                
	            </ul>
	            </div>
		    </div>
		</div>        
    </div>
    
    <div class="course_right" style="overflow:auto;height:100%">
        <div class="course_info">
            <img src="${ctx}/play/img/cover_1.jpg" class="float_right">
            <h2>${project.name}</h2>
            <h3>项目负责人：${expert.name}</h3>
            <h3>单位：${org.name}</h3>
            <div class="tabs aside_tabs">
                <ul class="tab_list">
                    <li id="tab1" class="tab active">目录</li>
                    <li id="tab2" class="tab">讨论</li>
                    <li id="tab3" class="tab">笔记</li>
                </ul>
                <div class="tab1 tab_cont">
                  <ul class="course_list">
                    <c:forEach items = "${list}"  var="list"  varStatus="status">
                       <li class="${list.id}" title="${list.name}">课程${status.index+1}：${list.name}</li>
                       <ul>
                           <c:forEach items = "${list.unitList}" var="unit">
	                            <li class="has_done">
	                                <i class="fa fa-check-circle"></i>
	                                <!-- m changed below 2017.1.25-->
	                                <c:choose>
	                                	<c:when test ="${unit.type==3}"><a href="javascript:courseTestTime('${unit.id}',this);">${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i></c:when>
	                                	<c:when test="${unit.type == 6}"><a href="javascript:diseaseAnalysis('${unit.id}');">${unit.name}</a><i class="fa fa-pencil-square"></i></c:when>
	                                	<c:when test="${unit.type == 2}"><a href="javascript:subjectDiscuss('${unit.id}', '${cvsetId}');">${unit.name}</a><i class="fa fa-pencil-square"></i></c:when>
		                                <c:otherwise>
		                               		<a href="javascript:void(0);" id="${unit.id}" onclick="play3('${unit.id}', '${cvsetId}');">${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i>
		                               	</c:otherwise>		                               	
	                                </c:choose>
	                                <span><i class="fa fa-video-camera"></i> </span>
	                            </li>
                           </c:forEach>
                        </ul>
                     </c:forEach>
                    </ul>
                </div>
                <div class="tab2 tab_cont" style="display: none;">
                    <div class="comment_form">
                        <label>内容：</label>
                        <textarea id="comment_cont_right" class="comment_cont"></textarea>
                        <div class="foot">
                            <button id="comment_submit_right" type="button" class="btn btn_orange btn_small">评论</button>
                            <span class="text_count">还能输入<span class="num_count">200</span>字</span>
                        </div>
                    </div>
                    <ul class="comments_list" id="comments_list_right">
                        <!-- place of 病例分析 -->                        
                    </ul>
                </div>
                <div class="tab3 tab_cont" style="display:none">
                    <div class="comment_form">
                        <label>内容：</label>
                        <textarea id="comment_cont_note" class="comment_cont"></textarea>
                        <div class="foot">
                            <button id="comment_submit_note" type="button" class="btn btn_blue btn_small">保存笔记</button>
                            <span class="text_count">还能输入<span class="num_count">200</span>字</span>
                        </div>
                    </div>
                    <ul class="comments_list" id="comments_list_note">
                        <!-- place of note -->                        
                    </ul>
                </div>
                
            </div>
        </div>
    </div>
</div>
<script>

    var gobalObj = '';
    
    var unitId = '';

    $(function () {
        $(".go_back").click(function () {
            document.location.href = ctxJS + "/projectDetail.do?id=${cvsetId}";
        });
        
        //课程Id
        var cvId = '${cvId}';
        
        //单元id
        var unitId = '${unitId}';
        
        //项目Id
        var cvsetId = '${cvsetId}'; 
        
        //display 讨论
        display_analysis();
        //display 笔记
        displayNote();
        
    });
       
	//病例分析 page display added below by ma
	function diseaseAnalysis(unitId){	
		
		var url = '${ctx}/entityManage/entityView.do?type=CaseAnalysis&cvId=${cvId}&unitId='
				  +unitId+'&cvsetId='+'${cvsetId}';		
		window.location.href = url;
		
	}
	//go to 话题讨论page
	function subjectDiscuss(unitId, cvsetId){
	
		var url = '${ctx}/entityManage/entityView.do?type=subjectDiscuss&cvId=${cvId}&unitId=' + unitId 
				+ '&cvsetId=' + cvsetId;
		window.location.href = url;
	}	
	//go to play3
	function play3(unitId, cvsetId){
	
		var url = '${ctx}/entityManage/entityView.do?type=play3&cvId=${cvId}&unitId=' + unitId 
				+ '&cvsetId=' + cvsetId;
		window.location.href = url;
	}
	//2017.01.26 Add By IE to display courseTest Time
   function courseTestTime(_id,obj) {
   		$.ajax({
			    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId="+cvsetId+"&cvId="+cvId+"&unitId="+unitId,
			    type: 'POST',
			    dataType: 'json',
			    success: function(data){   
			    	var result = eval(data);
			    	if(result.message=='success'){
			    		//保存学习记录成功
			    		location.href = "${ctx}/entityManage/entityView.do?type=courseTest&cvsetId="+cvsetId+"&cvId="+cvId+"&unitId="+unitId +"&id="+_id;
			    	}else if(result.message == 'noLogin'){
			    		alert("请先登录.");
			    	}
			    }
			});
   }
	
	$(".go_comment").click(function(){
            $(".comment_form").show();
    });
    $('.comment_cont').on("keyup",function(){
        var txtLeng = $(this).val().length;      //把输入字符的长度赋给txtLeng
        //拿输入的值做判断
        if( txtLeng>200 ){
            //输入长度大于200时span显示0
            $(this).next().children().eq(1).children().text(' 0 ');
            //截取输入内容的前300个字符，赋给fontsize
            var fontsize = $(this).val().substring(0,200);
            //显示到textarea上
            $(this).val( fontsize );
        }else{
            //输入长度小于300时span显示300减去长度
            $(this).next().children().eq(1).children().text(200-txtLeng);
        }
    });
    //add 病例分析讨论 ;M
    $('#comment_submit_left').click(function(){
    	content = $('#comment_cont_left').val();
    	addAnalysis(content);
    });    
    $('#comment_submit_right').click(function(){
    	content = $('#comment_cont_right').val();
    	addAnalysis(content);
    });
    function addAnalysis(content){
    	$.ajax({
    		type:'post',
    		url: '${ctx}/entityManage/entityView.do',
    		data:'type=addUnitTalk&discussType=2&content=' + content + '&cvsetId=' + "${cvsetId}"
    			+ '&unitId=' + "${unitId}",
    		dataType:'json',
    		success:function(result){
    			if(result.message == 'success'){
    				alert("评论添加成功！");
    				display_analysis();
    			}    			
    		}
    	});
    }
    //display 病例分析讨论   every 60s;M
    window.setInterval('display_analysis();', 60000);
    function display_analysis(){
    	$.ajax({
    		type:'post',
    		url: '${ctx}/entityManage/entityView.do',
    		data:'type=queryUnitTalk&discussType=2&cvsetId=' + "${cvsetId}" + '&unitId=' + "${unitId}",
    		dataType:'json',
    		success:function(result){
    			if (result.message == 'success') {
	    		 	var str_li = '';
	    			for(var i=0; i<result.list.length; i++) {
	    				str_li += '<li><span class="avatar tiny"><img src="${ctx}/../storage/upload/USER/${userInfo.user_image}"></span>'
	    							+'<p class="cont">' + result.list[i].discussContent + '</p>' 
	    							+'<p><span class="float_right"><i class="fa fa-thumbs-o-up"></i><i class="fa fa-reply"></i></span>'
			                        + '<span class="name">' + result.list[i].real_name + '</span>'
			                        + '<span class="datetime">  ' + result.list[i].discussDate + '</span></p></li>';
			                
	    			}    				
    				$('#comments_list_main').html(str_li);
    				$('#comments_list_right').html(str_li);    				
    			}
    		}
    	});
    }
    //add 笔记  to DB
     $('#comment_submit_note').click(function(){
	     $.ajax({
	     	type:'post',
	   		url: '${ctx}/entityManage/entityView.do',
	   		data:'type=addUnitNote&note_type=2&content=' + $('#comment_cont_note').val() + '&cvsetId=' + "${cvsetId}"
	   				+ '&unitId=' + "${unitId}",
	   		dataType:'json',
	   		success:function(result){
	   			if(result.message == 'success'){
	   				alert("笔记添加成功！");  
	   				displayNote();			
	   			}    			
	   		}
	   	});
     });
     function displayNote() {
     	$.ajax({
	     	type:'post',
	   		url: '${ctx}/entityManage/entityView.do',
	   		data:'type=queryUnitNote&note_type=2' + '&cvsetId=' + "${cvsetId}" + '&unitId=' + "${unitId}",	   				
	   		dataType:'json',
	   		success:function(result){
	   			if(result.message == 'success'){
	   				var str_li = '';
	    			for(var i=0; i<result.list.length; i++) {
	    				str_li += '<li><span class="avatar tiny"><img src="${ctx}/../storage/upload/USER/${userInfo.user_image}"></span>'
	    							+'<p class="cont">' + result.list[i].noteContent + '</p>' 
	    							+'<p><span class="float_right"><i class="fa fa-thumbs-o-up"></i><i class="fa fa-reply"></i></span>'
			                        + '<span class="name">' + result.list[i].real_name + '</span>'
			                        + '<span class="datetime">  ' + result.list[i].noteDate + '</span></p></li>';
			                
	    			}
    				$('#comments_list_note').html(str_li);   				
	   			}    			
	   		}
	   	});
     }
    
   
</script>
</body>
</html>