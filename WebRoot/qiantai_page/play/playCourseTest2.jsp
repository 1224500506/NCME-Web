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
    <script type="text/javascript" src="${ctx}/play/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/jquery.mobile.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/iconfont.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/main.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/swfobject.js"></script>
    <link href="${ctx}/play/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/play/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">
</head>
<body class="view">
<div class="container">
    <div class="course_video" style="position:relative;">
        <span class="logo"></span>
        <span class="go_back"><i class="fa fa-angle-left"></i> 返回项目详情</span>
        <div class="course_title" style="display:none;">
            <h2 id="className">课程1--单元1.1 医学基础导论课程</h2>
            <h3>
                <span id="classType">类型：话题讨论</span>
                <span id="classTeacher">授课教师：李老师</span>
                <span id="classDB">达标要求：完整观看完</span>
            </h3>
        </div>
        <div id="preview" style="margin:8px;width:100%;height:90%;background:#000;color:#FFF;margin-top:80px;box-sizing:border-box;">
            <!-- 封面信息 -->
            <c:if test="${fengmian==null || fengmian==''}">
               <img src="${ctx}/img/proLogo.jpg" name="fengmian" id="fengmian" style="width:100%;height:60%;margin-top:11%;">
            </c:if>
            <c:if test="${fengmian!=null && fengmian!=''}">
               <img src="${fengmian}" name="fengmian" id="fengmian" style="width:100%;height:100%;">
            </c:if> 
        </div>
        <div id="swfDiv" style="display:none;margin-left:10px;width:100%;height:100%;position:absolute;z-index:99999;top:100px;left:0;"></div> 
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
                    <c:forEach items = "${list}"  var="list">
                       <li class="${list.id}">${list.name}</li>
                       <ul>
                           <c:forEach items = "${list.unitList}" var="unit">
	                            <li class="has_done">
	                                <i class="fa fa-circle-o"></i>
	                                <a class="unit" title="${unit.id}" href="javascript:void(0);" onclick="zkContrent('${unit.id}',this);">${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i>
	                                <span class="${unit.id}" style="display:none;">${unit.type}</span>
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
 
    //设置资源管理系统全局变量
    var adminURL = "${ctxAdminURL}"; 
    
    //设置培训后台全局变量
    var peixunURL = "${ctxPeixunURL}";

    var gobalObj = '';
    
    var unitId = '${unitId}';
    
    var projectId = '${project.id}';
    
    var userId = '${userId}';

    $(function () {
        $(".go_back").click(function () {
            document.location.href = ctxJS + "/projectDetail.do?id=${project.id}";
        });
        
        //统计学习过哪些单元
        tongjiUnitStudy('${project.id}');
        
        zkContrent2(unitId);
        
        
    });
    
    //根据id查询组课内容
    function zkContrent(obj,dom){
    	//如果播放视频未关闭 手动关闭
    	on_spark_player_stop();
    	//先设置显示区颜色
    	$("#preview").css("background","black");
    	//设置全局单元Id
    	unitId=obj;
    	var cvId = $(dom).parent().parent().prev().attr("class");
    	//通过AJAX保存已经学习的单元
    	$.ajax({
		    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId=${project.id}&cvId="+cvId+"&unitId="+obj,
		    type: 'POST',
		    dataType: 'json',
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message=='success'){
		    		//保存学习记录成功
		    		
		    	}
		    }
		});	
    	//设置置灰
    	$(dom).css("color","#ADADAD");
    	//设置单元图标
    	$(dom).prev().attr("class","fa fa-check-circle");
    	//先清空div内容
    	$("#preview").empty();
    	//隐藏欢迎页面
    	$("#fengmian").css("display","none");
    	unitId = obj;
    	var content = '';
    	$.ajax({
		    url:"${ctx}/entityManage/entityView.do?type=getAJAXContent&unitId="+obj,
		    type: 'POST',
		    dataType: 'json',
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message=='success'){
		    		var unit = eval(result.list);
		    		if(unit!=null && unit.length>0){
		    			content = unit[0].classContent;
		    			if(content!='' && content!=null){
		    				document.getElementById("preview").innerHTML = content;
		    				//判断是否自动播放  如果是考试或者病例不自动播放
		    				var alt = $("#preview img").attr("alt");
		    				if(alt!='bingli' && alt!='paper' && alt!='talk'){
		    					play($("#preview img"));
		    				}else{
		    					preview($("#preview img").attr("_url"),$("#preview img").attr("alt"));
		    				}
		    			}else{
		    				$("#fengmian").css("display","block");
		    			}
		    			
		    		}
		    	}
		    }
		});	
    	
    	//查询课程信息
    	queryCVByCVId($(dom).parent().parent().prev().attr("class"),$(dom).parent().parent().prev().text(),unitId,$(dom).text());
    	
    	//查询笔记信息
    	queryUnitNotes(unitId);
    	
    	//查询讨论记录
    	queryUnitTalks(unitId);
    	
    };
    
    //点击播放
    function play(obj){
       var swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf', 'playerswf', '100%', '88%', '8');
       swfobj.addVariable( "userid" , "078E396B1332FD8E");	//	partnerID,用户id
       swfobj.addVariable( "videoid" , $(obj).attr("_url"));	//	spark_videoid,视频所拥有的 api id
       swfobj.addVariable( "mode" , "api");	//	mode, 注意：必须填写，否则无法播放
       swfobj.addVariable( "autostart" , "true");	//	开始自动播放，true/false
       swfobj.addVariable( "jscontrol" , "true");	//	开启js控制播放器，true/false
       swfobj.addParam('allowFullscreen','true');
       swfobj.addParam('allowScriptAccess','always');
       swfobj.addParam('wmode','transparent');
       swfobj.write('swfDiv');
       $("#swfDiv").css("display","block");
       $(obj).css("display","none");
       gobalObj = obj;
       document.getElementById("playerswf").spark_player_start();
    }
    
    //播放完毕
    function on_spark_player_stop() {
    	 $("#swfDiv").css("display","none");
    	 $("#fengmian").css("display","block");
         //zkContrent(unitId);
	}
    
    //暂停播放
    function on_spark_player_pause() {
    	$("#swfDiv").css("display","none");
    	$("#fengmian").css("display","block");
        //zkContrent(unitId);
	}
    
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
		    	var idArray = new Array();
		    	if(result.message=='success'){
		    		if(result.unitIds!=null && result.unitIds!=""){
		    			//对学习过的单元进行操作
		    			ids = result.unitIds;
		    			idArray = ids.split("_");
		    			$("a[class^='unit']").each(function(){
		    				var unitTempId = $(this).attr("title");
		    				for(var i=0;i<idArray.length;i++){
		    					if(unitTempId == idArray[i]){
		    						//设置单元显示字体
		    						$(this).css("color","#ADADAD");
		    						//设置单元前的图标
		    						$(this).prev().attr("class","fa fa-check-circle");
		    					}
		    				}
		    			});
		    		}
		    	}
		    }
		});	
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
     		    	cvsetId:'${project.id}'
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
     	                                    + "      <span class='name'>"+result.userName+"</span>"
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
      		    	cvsetId:'${project.id}'
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
      	                                    + "      <span class='name'>"+result.userName+"</span>"
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
     
     /**
      * @author 张建国
      * @time   2017-01-17
      * @param  unitId
      * 说     明：根据单元id查询课程信息(课程名称/单元名称/课程类型/授课教师)
      **/
     function queryCVByCVId(cvId,cvName,unitId,unitName){
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
    	 if($('.'+unitId).text()==1){
    		 $("#classType").text('类型：理论讲解');
    	 }else if($('.'+unitId).text()==2){
    		 $("#classType").text('类型：主题讨论');
    	 }else if($('.'+unitId).text()==3){
    		 $("#classType").text('类型：随堂考试');
    	 }else if($('.'+unitId).text()==4){
    		 $("#classType").text('类型：操作演示');
    	 }else if($('.'+unitId).text()==5){
    		 $("#classType").text('类型：扩展阅读');
    	 }else if($('.'+unitId).text()==6){
    		 $("#classType").text('类型：病例分享');
    	 }
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
     
     /*
      * @author 张建国
      * @time   2017-02-10
      * 说     明： 点击病例或试卷事件
      */
      function preview(id,type){
    	 $("#preview").css("background","white");
    	 if(type=='bingli'){ 
    		 var html = "<iframe src=\""+adminURL+"/caseManage/caseEdit2.do?type=1&caseId="+id+"&mode=1\" style=\"width:100%;height:100%;\"></iframe>";
    		 document.getElementById("preview").innerHTML = html; 
    	 }
    	 if(type=='paper'){
    		 var html = "<iframe src=\""+peixunURL+"/paperManage/paperView.do?handle=exam&paperId="+id+"\" style=\"width:100%;height:100%;\"></iframe>";
    		 document.getElementById("preview").innerHTML = html; 
    	 }
    	 if(type=='talk'){
    		 var html = "<iframe src=\""+peixunURL+"/talk/topicDiscussionManage.do?handle=queryOne&talkId="+id+"\" style=\"width:100%;height:100%;\"></iframe>";
    		 document.getElementById("preview").innerHTML = html; 
    	 }
     }
     
      function zkContrent2(obj){
      	//如果播放视频未关闭 手动关闭
      	on_spark_player_stop();
      	//先设置显示区颜色
      	$("#preview").css("background","black");
      	//设置全局单元Id
      	unitId=obj;
      	var cvId = $("#unit").parent().parent().prev().attr("class");
      	//通过AJAX保存已经学习的单元
      	$.ajax({
  		    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId=${project.id}&cvId="+cvId+"&unitId="+obj,
  		    type: 'POST',
  		    dataType: 'json',
  		    success: function(data){   
  		    	var result = eval(data);
  		    	if(result.message=='success'){
  		    		//保存学习记录成功
  		    		
  		    	}
  		    }
  		});	
      	//设置置灰
      	$("#unit").css("color","#ADADAD");
      	//设置单元图标
      	$("#unit").prev().attr("class","fa fa-check-circle");
      	//先清空div内容
      	$("#preview").empty();
      	//隐藏欢迎页面
      	$("#fengmian").css("display","none");
      	unitId = obj;
      	var content = '';
      	$.ajax({
  		    url:"${ctx}/entityManage/entityView.do?type=getAJAXContent&unitId="+obj,
  		    type: 'POST',
  		    dataType: 'json',
  		    success: function(data){   
  		    	var result = eval(data);
  		    	if(result.message=='success'){
  		    		var unit = eval(result.list);
  		    		if(unit!=null && unit.length>0){
  		    			content = unit[0].classContent;
  		    			if(content!='' && content!=null){
  		    				document.getElementById("preview").innerHTML = content;
  		    				//判断是否自动播放  如果是考试或者病例不自动播放
  		    					preview($("#preview img").attr("_url"),'paper');
  		    			}else{
  		    				$("#fengmian").css("display","block");
  		    			}
  		    			
  		    		}
  		    	}
  		    }
  		});	
      	
      	//查询课程信息
      	queryCVByCVId($("#unit").parent().parent().prev().attr("class"),$("#unit").parent().parent().prev().text(),unitId,$("#unit").text());
      	
      	//查询笔记信息
      	queryUnitNotes(unitId);
      	
      	//查询讨论记录
      	queryUnitTalks(unitId);
      	
      };

</script>
</body>
</html>