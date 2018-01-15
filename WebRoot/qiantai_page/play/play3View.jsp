<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
     <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="继续再教育平台">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="">
    
    <meta name="description" content="">
    <%@include file="/commons/taglibs.jsp"%>
   <!-- <object id="cc_A5FEB0EA05F2100B9C33DC5901307461">
 	<param name="progressbar_enable" value="0" />
	</object>
 -->
    <title>中国继续医学教育网</title>
    <script type="text/javascript" src="${ctx}/play/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/jquery.mobile.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/iconfont.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/main.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/swfobject.js"></script>
    <link href="${ctx}/play/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/play/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/play/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">
  <%--   <link href="${ctx}/qiantai_page/css/iconfont/iconfont.css" rel="stylesheet">   --%>
  

   <link href="${ctx}/qiantai_page/css/test/iconfont.css" rel="stylesheet">  

<style type="text/css">
.box{
height: 88px;
position:  fixed;
width: 70%;
z-index: 10;

}

.InnerDiv{

width: 100%;
height: 50%;
background-color: red;
z-index: 1000;


}




</style>





</head>








<body class="view">

<div class="container">
    <div class="course_video" style="position: relative;">
        <span class="logo"></span>
        <span class="go_back"><i class="fa fa-angle-left"></i> 返回项目详情</span>
        <div  class="box">
        <div class="course_title" style="position:relative;">
            <h2 id="className">课程1--单元1.1 医学基础导论课程</h2>
            <h3>
                <span id="classType" >类型：视频播放</span>
                <span id="classTeacher">授课教师：李老师</span>
                <span id="classDB">达标要求：<span id="quota"></span></span>
            </h3>
        </div>
        </div>
   
        
        <div id="preview" style="margin:8px;width:100%;height:90%;background:#000;color:#FFF;margin-top:100px;box-sizing:border-box;">
            <!-- 封面信息 -->
            <c:if test="${fengmian==null || fengmian==''}">
               <img src="${ctx}/img/proLogo.jpg" name="fengmian" id="fengmian" style="width:100%;height:60%;margin-top:11%;">
            </c:if>
            <c:if test="${fengmian!=null && fengmian!=''}">
               <img src="${fengmian}" name="fengmian" id="fengmian" style="width:100%;height:100%;">
            </c:if> 
        </div>
            
 		<div id="swfDiv" style="display:none; margin-left:10px;width:100%;height:100%;position:absolute;z-index:100;top:100px;left:0;">
        </div> 
    </div>
    <div class="course_right">
        <div class="course_info">
            <c:if test="${fengmian!=null && fengmian!=''}">
               <img src="${fengmian}" class="float_right">
            </c:if>
            <c:if test="${fengmian==null || fengmian==''}">
               <img src="${ctx}/play/img/cover_1.jpg" class="float_right">
            </c:if>
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
                           <c:forEach items = "${list.unitList}" var="unit" varStatus="status">
	                            <li class="has_done">
	                              <i class="iconfont pm" id="pm" style="color: rgb(18,188,255);">&#xe674;</i>    
	                               <!--  <i class="iconfont" style="font-size: 16px; color: rgb(18,188,255);float:right;">&#xe603;</i> -->
	                                <%--  <c:choose> 
	                                	<c:when test ="${unit.type==3}"><a href="javascript:courseTestTime('${unit.id}',this);">单元${status.index+1}:${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i></c:when>
	                                	<c:when test="${unit.type == 6}"><a href="javascript:diseaseAnalysis('${unit.id}');">单元${status.index+1}:${unit.name}</a><i class="fa fa-pencil-square"></i></c:when>
	                                	<c:when test="${unit.type == 2}"><a href="javascript:subjectDiscuss('${unit.id}', '${cvsetId}');">单元${status.index+1}:${unit.name}</a><i class="fa fa-pencil-square"></i></c:when>	                                
	                                	<c:otherwise> --%>
	                                		<a href="javascript:void(0);" class="unit" title="${unit.id}" id="${unit.id}" onclick="zkContrent('${unit.id}',this,'${unit.content}','${unit.quota}');">单元${status.index+1}:${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i>
	                               <%--  	</c:otherwise>
	                                </c:choose>  --%>
	                                <span><i class="fa fa-video-camera"></i> </span>
	                            </li>
                           </c:forEach>
                        </ul>
                     </c:forEach>
                    </ul>
                </div>   
                <div class="tab2 tab_cont" style="display:none">
                  <form id="talkForm" method="post">
                    <div class="comment_form">
                        <label>内容：</label>
                        <textarea name="comment_cont" id="talk" onkeyup="jisuanzishu('talk');"></textarea>
                        <div class="foot">
                            <button name="comment_submit" type="button" class="btn btn_orange btn_small" onclick="saveTalk();">评论</button>
                            <span class="text_count">还能输入<span class="num_count" id="talkCount">200</span>字</span>
                        </div>
                    </div>
                    <ul class="comments_list" id="talkList"></ul>
                  </form>
                </div>
                <div class="tab3 tab_cont" style="display:none">
                   <form id="noteForm" method="post">
	                    <div class="comment_form">
	                        <label>内容：</label>
	                        <textarea name="comment_cont" id="notice" onkeyup="jisuanzishu('notice');"></textarea>
	                        <div class="foot">
	                            <button name="comment_submit" type="button" class="btn btn_blue btn_small" onclick="saveNotece();">保存笔记</button>
	                            <span class="text_count">还能输入<span class="num_count" id="noticeCount">200</span>字</span>
	                        </div>
	                     </div>
	                      <ul class="comments_list" id="noteList"></ul>
                    </form>
                </div>            
            </div>
        </div>
    </div>
</div>
<script>

var baseurl = window.location.href;

	//设置资源管理系统全局变量
	
	var adminURL = "${ctxAdminURL}"; 
	
   //var adminURL = "http://localhost:8081/admin";
	
	//设置培训后台全局变量
  var peixunURL = "${ctxPeixunURL}";
	
  // var peixunURL = "http://localhost:8081/test_peixun";

    var gobalObj = '';
    
	var cvId = '${cvId}';
        
    //单元id
    var unitId = '${unitId}';
        
    //项目Id
    var cvsetId = '${cvsetId}';
    
    var projectId = '${project.id}';

    var userId = '${userId}';
    
    var content = '${content}';
    
    var quota = '${quota}';
    
    var swfobj ;//YHQ，2017-03-10
    var vLength   = 0 ; //YHQ，2017-03-10，视频长度（秒）
    var vPosition = 0 ; //YHQ，2017-03-10，视频播放位置（秒）
    var vFlag = false ; //YHQ，2017-03-10，是否视频
    var videoCvsetId = 0;//YHQ，2017-03-10
    var videoCvId = 0 ;//YHQ，2017-03-10
    var videoUnitId = 0;//YHQ，2017-03-10
    var videoLogStudyId = ${videoLogStudyId}; //YHQ，2017-03-10
    var videoStartDate = getNowFormatDate() ;//YHQ，2017-03-09
    $(window).bind('beforeunload',function () {  	     
       var evt = window.event || arguments[0];  
       var userAgent = navigator.userAgent;  
       if (userAgent.indexOf("MSIE") > 0) {  
           var n = window.event.screenX - window.screenLeft;  
           var b = n > document.documentElement.scrollWidth - 20;  
           if (b && window.event.clientY < 0 || window.event.altKey) {  
               winLeave();  
           }else {  
               winLeave();  
           }  
       }else {  
           winLeave();
       } 	    
	}) ; 
	
     
    $(function () {
        $(".go_back").click(function () {
            document.location.href = ctxJS + "/projectView.do?id=${cvsetId}";
        });

        //统计学习过哪些单元
        tongjiUnitStudy('${cvsetId}');

        //初始化自动播放
        zkContrent(unitId,$("#"+unitId+""),content,quota);
        
    });
    
    /**
     * @author 张建国
     * @time   2017-01-6
     * @param  cvsetId
     * 说     明： 统计项目下的学习记录
     **/
     function tongjiUnitStudy(obj){
      	$.ajax({
  		    url:"${ctx}/study/logstudy.do?mode=tongjiUnitStudy&cvsetId="+obj,
  		    type: 'POST',
  		    dataType: 'json',
  		    success: function(data){   
  		    	var result = eval(data);
  		    	var ids = '';
  		    	var cols ='';
  		    	var clas ='';
  		    	var idArray = new Array();
  		    	var colorArray = new Array();
  		    	var claArray = new Array();
  		    	if(result.message=='success'){
  		    		if(result.unitIds!=null && result.unitIds!=""){
  		    			//对学习过的单元进行操作
  		    			ids = result.unitIds;
  		    			clas = result.cla;
  		    			idArray = ids.split("_");
  		    			claArray = clas.split("_");
  		    			$("a[class^='unit']").each(function(){
  		    				var unitTempId = $(this).attr("title");
  		    				for(var i=0;i<idArray.length;i++){
  		    					if(unitTempId == idArray[i]){
  		    						if(claArray[i]=="1"){
  		    							//设置单元显示字体
  			    						$(this).css("color","#ADADAD");
  			    						//设置单元前的图标
  			    						$(this).prev().html("&#xe61c;")
  			    						
  		    						}else if(claArray[i]=="0"){
  		    							
  		    							//$(this).css("color","");
  			    						//设置单元前的图标
  			    						$(this).prev().html("&#xe639;")
  		    							
  		    						}
  		    						
  		    						
  		    					}
  		    				}
  		    			});
  		    		}
  		    	}
  		    }
  		});	
      }
    
    /*
     * @author 张建国
     * @time   2017-01-22
     * 说      明: 计算textarea中的字数 
     */
     function jisuanzishu(type){
   	  if(type=='talk'){
   		  var length = $("#talk").val().length;
       	  if(length>200){
       		  $("#talkCount").text(length);  
       		  alert("字数请在200字以内.");
       	  }else{
       		  $("#talkCount").text(200-length);  
       	  }
   	  }else if(type=='notice'){
   		  var length = $("#notice").val().length;
       	  if(length>200){
       		  $("#talkCount").text(length);  
       		  alert("字数请在200字以内.");
       	  }else{
       		  $("#noticeCount").text(200-length);  
       	  } 
   	  } 
     }
   
      
      
    //根据id查询组课内容
    function zkContrent(obj,dom,type,quota){

        winLeave();//YHQ，2017-03-10
        vLength   = 0 ; //YHQ，2017-03-10，视频长度（秒）
        vPosition = 0 ; //YHQ，2017-03-10，视频播放位置（秒）
        vFlag = false ; //YHQ，2017-03-10，是否视频
        videoCvsetId = 0;//YHQ，2017-03-10
        videoCvId = 0 ;//YHQ，2017-03-10
        videoUnitId = 0;//YHQ，2017-03-10
        videoLogStudyId = 0;//YHQ，2017-03-10
        videoStartDate = getNowFormatDate() ;//YHQ，2017-03-09
        
    	//如果播放视频未关闭 手动关闭
    	on_spark_player_stop();
        
    	var cvId = $(dom).parent().parent().prev().attr("class");
        
     	$.ajax({
		    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId=${project.id}&cvId="+cvId+"&unitId="+obj,
		    type: 'POST',
		    dataType: 'json',
                    async: false,
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message=='success'){
		    		//保存学习记录成功
                            videoLogStudyId = parseInt(result.logStudyId) ;   //YHQ，2017-03-09 
		    	} 
		    	<%--
		    	//项目预览时不保存学习记录
		    	else {
                            alert("保存学习记录失败！") ;
                        }
                --%>
		    }
		});	
     	
    	//设置置灰
    	$(dom).css("color","#ADADAD");
    	//设置单元图标
    	//$(dom).prev().attr("class","fa fa-check-circle");
    	//$(dom).prev().html("&#xe61c;");  

    	//先清空div内容
    	$("#preview").empty();
    	//隐藏欢迎页面
    	$("#fengmian").css("display","none");
    	unitId = obj;
    	var content = '';
    	$.ajax({
		    url:"${ctx}/entityManage/entityView.do?type=getAJAXContent&unitId="+obj,
		    type: 'POST',
		   // async: false,
		    dataType: 'json',
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message=='success'){
		    		var unit = eval(result.list);
		    		if(unit!=null && unit.length>0){
		    			content = unit[0].classContent;
		    			if(content!='' && content!=null){
		    				if(content.indexOf("卫纪委")!=-1){
			    				document.getElementById("preview").innerHTML = content;
			    				}else{
			    	               html = content.substring(content.indexOf("<p><img"),content.lastIndexOf("</p>")+4);
			    					document.getElementById("preview").innerHTML = html;
			    				} 
		    				//判断是否自动播放  如果是考试或者病例不自动播放
		    				var alt = $("#preview img").attr("alt");
		    				if(alt!='bingli' && alt!='paper' && alt!='talk'){
		    				    if (result.videoLogStudyId != undefined) videoLogStudyId = parseInt(result.videoLogStudyId) ;   //YHQ，2017-03-10
		    					play($("#preview img"),${project.id},cvId,unitId,videoLogStudyId,type,$(dom).text(),quota);
		    				}else{
		    					preview($("#preview img").attr("_url"),$("#preview img").attr("alt"),unitId);
		    			
		    					
		    				}
		    			}else{
		    				$("#fengmian").css("display","block");
		    			}
		    		}
		    	}
		    }
		});	
    	
    	//查询课程信息
    	queryCVByCVId($(dom).parent().parent().prev().attr("class"),$(dom).parent().parent().prev().text(),type,$(dom).text(),quota);
    	
    	//查询笔记信息
    	queryUnitNotes(unitId);
    	
    	//查询讨论记录
    	queryUnitTalks(unitId);
    	
    };
    
    /**
     * @author 张建国
     * @time   2017-01-17
     * @param  unitId
     * 说     明：根据单元id查询课程信息(课程名称/单元名称/课程类型/授课教师)
     **/
    function queryCVByCVId(cvId,cvName,type,unitName,quota){

   	 //设置课程信息可见
   	 $(".course_title").css("display","block");
   	 //设置课程名称
   	 $("#className").text(cvName+"--"+unitName);
   	 //设置课程主讲教师
	 $.ajax({
		    url:"${ctx}/entityManage/entityView.do?type=queryTeacherByCVId",
		    type: 'POST',
		    dataType: 'json',
		    data:{
		    	cvId:cvId
		    },
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message=='success'){
		    		$("#classTeacher").text("授课教师："+result.teacherName);
		    	}
		    }
		});
	   	 //设置课程类型
		 if(type==''){
			 $("#classType").text('类型：视频');
		 }else if(type=='talk'){
			 $("#classType").text('类型：主题讨论');
		 }else if(type=='paper'){
			 $("#classType").text('类型：随堂考试');
		 }else if(type==4){
			 $("#classType").text('类型：操作演示');
		 }else if(type==5){
			 $("#classType").text('类型：扩展阅读');
		 }else if(type=='bingli'){
			 $("#classType").text('类型：病例分享');
		 }
		//达标要求
		 if(type==''){
			 $("#classDB").text('达标要求：完成'+quota+'%视频观看');
		 }else if(type=='talk'){
			 $("#classDB").text('达标要求：参与'+parseInt(quota)+'次讨论');
		 }else if(type=='paper'){
			 if(!quota){
				
			$("#classDB").text('达标要求：取得0分以上成绩');
			 }else{
				 $("#classDB").text('达标要求：取得'+parseInt(quota)+'分以上成绩');
			 }
			
		 }else if(type==4){
			 $("#classDB").text('达标要求：完整观看完');
		 }else if(type==5){
			 $("#classDB").text('达标要求：完整观看完');
		 }else if(type=='bingli'){
			 alert
			 $("#classDB").text('达标要求：完成'+parseInt(quota)+'条发言讨论');
		 }
    }
    
    /**
     * @author  张建国
     * @time    2017-01-17
     * 说        明：保存单元笔记
     **/
    function saveNotece(){
    	var notice = $("#notice").val().length;
    	//首先判断手否选中了单元
    	if(unitId==null || unitId==''){
    		alert("请先选择课程单元.");
    	}else if(notice==0 || notice>200){
    		alert("笔记内容请在200字以内.");
    	}else{
    		$.ajax({
     		    url:"${ctx}/entityManage/entityView.do?type=addUnitNote",
     		    type: 'POST',
     		    dataType: 'json',
     		    data:{
     		    	cvsetId:'${project.id}',
     		    	unitId:unitId,
     		    	content:$("#notice").val()
     		    },
     		    success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){
     		    		//alert("单元笔记保存成功.");
     		    		queryUnitNotes(unitId);
     		    		//清空笔记文本框
     		    		$("#notice").val('');
     		    	}else if(result.message=='noLogin'){
     		    		alert("请先登录.");
     		    	}else{
     		    		alert("单元笔记保存失败.");
     		    	}
     		    }
     		});
    	} 	
    }
    
    /**
     * @author  张建国
     * @time    2017-01-17
     * 说        明：保存单元讨论
     **/
    function saveTalk(){
    	var talk = $("#talk").val().length;
    	//首先判断手否选中了单元
    	if(unitId==null || unitId==''){
    		alert("请先选择课程单元.");
    	}else if(talk==0 || talk>200){
    		alert("讨论内容请在200字以内.");
    	}else{
    		$.ajax({
     		    url:"${ctx}/entityManage/entityView.do?type=addUnitTalk",
     		    type: 'POST',
     		    dataType: 'json',
     		    data:{
     		    	cvsetId:'${project.id}',
     		    	unitId:unitId,
     		    	content:$("#talk").val()
     		    },
     		    success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){
     		    		//alert("单元讨论保存成功.");
     		    		queryUnitTalks(unitId);
     		    		//清空讨论文本框
     		    		$("#talk").val('');
     		    	}else if(result.message=='noLogin'){
     		    		alert("请先登录.");
     		    	}else{
     		    		alert("单元讨论保存失败.");
     		    	}
     		    }
     		});
    	} 	
    }
    
    /**
     * @author 张建国
     * @time   2017-01-17
     * @param  unitId
     * 说     明：根据单元Id查询发表的笔记记录
     **/
     function queryUnitNotes(unitId){
    	//先清空笔记li
    	$("#noteList").find("li").remove(); 
    	if(unitId!=null && unitId!=''){
    		//通过AJAX查询笔记记录
    		$.ajax({
     		    url:"${ctx}/entityManage/entityView.do?type=queryUnitNote",
     		    type: 'POST',
     		    dataType: 'json',
     		    data:{
     		    	unitId:unitId,
     		    	cvsetId:'${cvsetId}'
     		    },
     		    success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){
     		    		//对返回的集合进行操作
     		    		if(result.list!=null && result.list.length>0){
     		    			for(var i=0;i<result.list.length;i++){
     		    				var content = "<li>"
     	                                    + "  <span class='avatar tiny'><img style='width:40px;height:50px;' src='${ctx}/qiantai_page/img/userlogo.jpg'></span>"
     	                                    + "    <p>"
     	                                    + "      <span class='name'>"+result.list[i].real_name+"</span>"
     	                                    + "      <span class='date'>"+result.list[i].noteDate+"</span>"
     	                                    + "    </p>"
     	                                    + "    <p class='cont'>"+result.list[i].noteContent+"</p>"
     	                                    + "</li>";
     	                        //动态追加
     	                        $("#noteList").append(content);
     		    			}
     		    		}
     		    	}
     		    }
     		});
    	}
    }
    
     /**
      * @author 张建国
      * @time   2017-01-17
      * @param  unitId
      * 说     明：根据单元Id查询发表的讨论记录
      **/
    function queryUnitTalks(unitId){
     	//先清空笔记li
     	$("#talkList").find("li").remove(); 
     	if(unitId!=null && unitId!=''){
     		//通过AJAX查询笔记记录
     		$.ajax({
      		    url:"${ctx}/entityManage/entityView.do?type=queryUnitTalk",
      		    type: 'POST',
      		    dataType: 'json',
      		    data:{
      		    	unitId:unitId,
      		    	cvsetId:'${cvsetId}'
      		    },
      		    success: function(data){   
      		    	var result = eval(data);
      		    	if(result.message=='success'){
      		    		//对返回的集合进行操作
      		    		if(result.list!=null && result.list.length>0){
      		    			for(var i=0;i<result.list.length;i++){
      		    				var content = "<li>"
      	                                    + "  <span class='avatar tiny'><img style='width:40px;height:50px;' src='${ctx}/qiantai_page/img/userlogo.jpg'></span>"
      	                                    + "    <p>"
      	                                    + "      <span class='name'>"+result.list[i].real_name+"</span>"
      	                                    + "      <span class='date'>"+result.list[i].discussDate+"</span>"
      	                                    + "    </p>"
      	                                    + "    <p class='cont'>"+result.list[i].discussContent+"</p>"
      	                                    + "</li>";
      	                        //动态追加
      	                        $("#talkList").append(content);
      		    			}
      		    		}
      		    	}
      		    }
      		});
     	}
     }
    
    //点击播放
    function play(obj,cvsetIdVal,cvIdVal,unitIdVal,logStudyIdVal,type,unitName,quota){
       videoCvsetId = cvsetIdVal ;//YHQ，2017-03-09
       videoCvId    = cvIdVal ;//YHQ，2017-03-09
       videoUnitId  = unitIdVal ;//YHQ，2017-03-09
       videoLogStudyId = logStudyIdVal ;
       vFlag = true ;
       
       $.ajax({
                url:"${ctx}/study/logstudy.do?mode=queryVideoStudyLog",
                type: 'POST',
                dataType: 'json', 
                async: false,               
                data:{     		    	
                    cvId:videoCvId,                        
                    cvUnitId:videoUnitId     		    	
                },
                success: function(data){   
                	// 控制滚动条不可拖拽
              
                	
                	
                	
                	
                    var result = eval(data);
                    if(result.message == 'success' && result.videoLog != undefined){ 
                        try {
                            vPosition = result.videoLog.videoPlayLength ;   
                            queryCVByCVId(cvId,cvName,type,unitName,quota);
                        }catch(err){}  
                    }
               }
        });
       
       
       swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf', 'playerswf', '100%', '88%', '8');
       swfobj.addVariable( "userid" , "078E396B1332FD8E");	//	partnerID,用户id
       swfobj.addVariable( "videoid" , $(obj).attr("_url"));	//	spark_videoid,视频所拥有的 api id
       swfobj.addVariable( "mode" , "api");	//	mode, 注意：必须填写，否则无法播放
       swfobj.addVariable( "autostart" , "true");	//	开始自动播放，true/false
       swfobj.addVariable( "jscontrol" , "true");	//	开启js控制播放器，true/false
       swfobj.addVariable( "control_enable" , "0");
       swfobj.addParam('allowFullscreen','true');
       swfobj.addParam('allowScriptAccess','always');
       swfobj.addParam('wmode','transparent');
       swfobj.write('swfDiv');
       $("#swfDiv").css("display","block");
       $(obj).css("display","none");
       gobalObj = obj;
     
    }
    
    //播放完毕
    function on_spark_player_stop() {
         var currPosition = 0 ;
         try {
            currPosition = document.getElementById("playerswf").spark_player_position() ;
         }catch(err){}        
         if (currPosition > vPosition) vPosition = currPosition ;
	        
	     winLeave() ;
        
    	 $("#swfDiv").css("display","none");
    	 $("#fengmian").css("display","block");
	}
    
   
    
  
    
    //暂停播放
    function on_spark_player_pause() {
        var currPosition = 0 ;
        try {
            currPosition = document.getElementById("playerswf").spark_player_position() ;
        }catch(err){}        
        if (currPosition > vPosition) vPosition = currPosition ;
        
    	$("#fengmian").css("display","block");
      
	}
	
	//开始播放时回调，YHQ，2017-03-08，获取视频长度（秒）
    function on_spark_player_start() { 
        videoStartDate = getNowFormatDate() ;
        try {
            vLength = document.getElementById("playerswf").spark_player_duration() ;
        }catch(err){}
                
        if (vPosition > 0) {
           try {
        	   document.getElementById("playerswf").spark_player_seek(vPosition);
           }catch(err){} 
        }                       
    }        
       
    function winLeave() {
        var currPosition = 0 ;
        try {
            currPosition = document.getElementById("playerswf").spark_player_position() ;
        }catch(err){}        
        if (currPosition > vPosition) vPosition = currPosition ;
        vPosition = Math.round(vPosition) ;        
        if (vFlag) {
            $.ajax({
     		    url:"${ctx}/study/logstudy.do?mode=saveVideoStudyLog",
     		    type: 'POST',
     		    dataType: 'json',
     		   	async: false,  
     		    data:{
     		    	logStudyCvUnitId:videoLogStudyId,
     		    	cvId:videoCvId,                        
                        cvUnitId:videoUnitId,
     		    	videoLength:vLength,
                        videoPlayLength:vPosition,
                        startDate:videoStartDate
     		    },
     		    success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){ 
     		    		tongjiUnitStudy('${project.id}');
     		    	}else if(result.message=='noLogin'){
     		    		//alert("请先登录.");
     		    	}else{
     		    		//alert("单元笔记保存失败.");
     		    	}
     		   }
     	    });
        }        
    }
    
    //拖动播放时调用的函数
 
    
    
    
    
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
	//病例分析 page display added below by ma
	function diseaseAnalysis(unitId){	
		
		var url = '${ctx}/entityManage/entityView.do?type=CaseAnalysis&cvId=' + "${cvid}" + '&unitId='
				  +unitId+'&cvsetId='+'${cvsetId}';		
		window.location.href = url;
		
	}
	//go to 话题讨论page
	function subjectDiscuss(unitId, cvsetId){
	
		var url = '${ctx}/entityManage/entityView.do?type=subjectDiscuss&unitId=' + unitId 
				+ '&cvsetId=' + cvsetId;
		window.location.href = url;
	}
	
	$(".go_comment").click(function(){
            $(".comment_starts,.comment_form").toggle();
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
    
     /*
      * @author 张建国
      * @time   2017-02-10
      * 说     明： 点击病例或试卷事件
      */
      function preview(id,type,unitId){
     	 $("#preview").css("background","white");
     	 if(type=='bingli'){ 
     		 var html = "<iframe src=\""+adminURL+"/caseManage/caseEdit2.do?type=1&caseId="+id+"&mode=1\" style=\"width:100%;height:100%;\"></iframe>";
     		 document.getElementById("preview").innerHTML = html; 
     	 }
     	 if(type=='paper'){
     		 var html = "<iframe src=\""+peixunURL+"/paperManage/paperView.do?handle=exam&paperId="+id+"&unitId="+unitId+"&userId=${userId}\" style=\"width:100%;height:100%;\"></iframe>"
     		 document.getElementById("preview").innerHTML = html; 
     	 }
     	 if(type=='talk'){
     		 var html = "<iframe src=\""+peixunURL+"/talk/topicDiscussionManage.do?handle=queryOne&talkId="+id+"\" style=\"width:100%;height:100%;\"></iframe>"
     		 document.getElementById("preview").innerHTML = html; 
     	 }
      }
     
     //日期格式化，YHQ 2017-03-10
     function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }
     
   function load(){
	   tongjiUnitStudy('${project.id}');
   } 
    
   

   
   
   // 查询达标指标
/*    function  QueryQuota(unitid){
  	 
  	 $.ajax({
  		    url:"${ctx}/play/CvUnit.do?mode=Query&unitid="+unitid,
  		    type: 'POST',
  		    dataType: 'json',
  		    data:{
  		    },
  		    success: function(data){   
  		    	var result = eval(data);
  		    	//视频
  		    	if(result.code=="1"){
  		    		alert(result.quota);
  		    		$("#quota").text("视频观看"+result.quota+"分钟");
  		          // 病例。讨论
  		    	}else if(result.code=="2"){
  		    		alert(result.quota);
  		    		$("#quota").text("参与"+result.quota+"讨论");
  		    		// 测试
  		    	}else if(result.code=="3"){
  		    		alert(result.quota);
  		    		$("#quota").text("测试达到"+result.quota+"分");
  		    	}
  		    	
  		    }
  		});
  	 
   }
    */
   
    
    
           /*****程宏业
              2017-03-24
                                       供子页面调用更改框架父页面的样式
           
           ****/ 	
    	   function disp(){
    		  	 $(".box").css("background-color","white"); 
    		   }
    		   function disn(){
    		  	 $(".box").css("background-color",""); 
    		   }
    		   
    		   /***
    		    程宏业  2017-03-24  
    		  设置内置播放器属性
    		  详情见 http://doc.bokecc.com/vod/dev/PlayerAPI/player01/
    		  官方文档
    		  该方法单独存在不可被其他方法调用!     
    		   **/
    		   /**** 方法开始***/
    		   function on_cc_player_init( vid, objectID ){var config = {};
    		    //config.fullscreen_enable = 1; //启用自定义全屏
    		   // config.fullscreen_function = "customFullScreen"; //设置自定义全屏函数的名称
    		   // config.on_player_pause = "onPlayPaused"; //设置当暂停播放时的回调函数的名称
    		   
    		   	config.on_player_start="on_spark_player_start";
    		    config.control_enable= 1;
    		//    config.progressbar_enable = 0;
    		    config.autoStart=true;
    		    var player = getSWF( objectID );
    		    player.setConfig( config );
    		  }
    		function getSWF( swfID ) {
    		      if (window.document[ swfID ]) {
    			return window.document[ swfID ];
    		    } else if (navigator.appName.indexOf("Microsoft") == -1) {
    				if (document.embeds && document.embeds[ swfID ]) {
    			return document.embeds[ swfID ];
    		      }
    		    } else {
    			return document.getElementById( swfID );
    		    }
    		  }
    		/*****方法结束****/
    		
    		
    		</script>
    				  
    	<script type="text/javascript" src="${ctx}/qiantai_page/js/util.js"></script>			  
    				  
    				   

</body>
</html>