<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=yes">
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
    <style>    	
		.tabs_left ul.tab_list_left { display: table; width: 100%; margin-bottom: 20px; background-color: #fff; padding: 3px 0 0; border-bottom: 1px solid #ccc; }		
		.tabs_left ul.tab_list_left li.tab { float: left; margin: 0 25px 0 0; padding: 5px 0; font-size: 16px; line-height: 30px; cursor: pointer; }		
		.tabs_left ul.tab_list_left li.tab.active { font-size: 16px; font-weight: 600; border-bottom: 2px solid #12bce1; line-height: 28px; }
		.tabs_left.aside_tabs ul.tab_list_left { background: transparent; padding: 0; border-color: #a499a5; border-collapse: collapse; table-layout: fixed; }
		.tabs_left.aside_tabs ul.tab_list_left li.tab { display: table-cell; text-align: center; }	
    </style>
</head>
<body class="view">
<div class="container">
    <div class="course_video" style="position:relative;overflow:auto;">
        <span class="logo"></span>
        <span class="go_back"><i class="fa fa-angle-left"></i> 返回项目详情</span>
        <div class="course_title">
            <h2 id="className">${curCV.name}--${curUnit.name}</h2>
            <h3>
                <span id="classType">类型：病例分析</span>
                <span id="classTeacher">授课教师：
                	<c:forEach items="${cv.teacherList}" var="teacher" varStatus="teacherStatus">
	               		${teacher.name }
	               		<c:if test = "${(varStatus + 1) != cv.teacherList.size()}">,</c:if>
	               	</c:forEach>
                </span>
                <span id="classDB">达标要求：完整观看完</span>
            </h3>
        </div>
        <div class="analysis_container" style="margin-top:105px">
		    <div class="content no_padding" style="width:90%">
		        <div class="tabs_left">
		            <ul class="tab_list_left">
		                <li id="tab1" class="tab active">基本信息</li>
		                <li id="tab2" class="tab">病例信息</li>
		            </ul>
		            <div class="tab1 tab_cont">
		                <div class="half_cont" style="float:left;width:50%;">
		                    <ul>
		                        <li>学科：${courseStr}</li>
		                        <li>主题：${subjectStr}</li>
		                        <li>患者姓名：${caseData.patientObject.PName}</li>
		                        <li>患者出生日期：${caseData.patientObject.birthday}</li>
		                        <li>患者身份证号：${caseData.patientObject.certificate}</li>
		                        <li>编号类型1：
		                        	<c:if test="${caseData.patientObject.number1Type == 0}">门诊号</c:if>
		                        	<c:if test="${caseData.patientObject.number1Type == 1}">住院号</c:if>
		                        	<c:if test="${caseData.patientObject.number1Type == 2}">床位号</c:if>
		                        	<c:if test="${caseData.patientObject.number1Type == 3}">其他编号</c:if>
		                        	${caseData.patientObject.number1}</li>
		                        <li>手机号：${caseData.patientObject.phoneNumber1}</li>
		                    </ul>
		                </div>
		                <div class="half_cont" style="float:left;width:50%;">
		                    <ul>
		                        <li>ICD10：${ICD}</li>
		                        <li>就诊日期：${caseData.patientObject.diagDate}</li>
		                        <li>性别：
		                        	<c:if test="${caseData.patientObject.sex == 0}">男</c:if>
		                        	<c:if test="${caseData.patientObject.sex == 1}">女</c:if>
		                        </li>
		                        <li>年龄：${caseData.patientObject.pAge}</li>
		                        <li>国家库：
		                        	<c:if test="${caseData.patientObject.nationalState == 1}">是</c:if>
		                        	<c:if test="${caseData.patientObject.nationalState != 1}">否</c:if>
		                        </li>
		                        <li>编号类型2：
		                        	<c:if test="${caseData.patientObject.number2Type == 0}">门诊号</c:if>
		                        	<c:if test="${caseData.patientObject.number2Type == 1}">住院号</c:if>
		                        	<c:if test="${caseData.patientObject.number2Type == 2}">床位号</c:if>
		                        	<c:if test="${caseData.patientObject.number2Type == 3}">其他编号</c:if>
		                        	${caseData.patientObject.number2}
		                        </li>
		                        <li>固定电话：${caseData.patientObject.phoneNumber2}</li>
		                    </ul>
		                </div>
		                <p class="desc">诊断：
		                	<script>var diagIndex=1;</script>
							<c:forEach items = "${caseData.patientDiagnosis}" var = "diagnosis" varStatus="status">
								<div class="mt10 paDiag1">
									<div class="fl shitin_xinzeng01">
										<p class="fl" style="width:680px; overflow:auto; height:auto;"><span class="fl diagnose" style="text-align:left">诊断${status.index+1}：${diagnosis.diagnosis}</span></p>										
									</div>
									<div class="clear"></div>
								</div>
							</c:forEach>
		                </p>
		            </div>
		            <div class="tab2 tab_cont" style="display:none">
		                <table class="main_table">
		                    <tbody style="border-top:1px solid #ccc">
		                        <tr>
		                            <td>主诉</td>
		                            <td>${caseData.caseDiseaseObject.complaint}</td>
		                        </tr>
		                        <tr>
		                            <td>现病史</td>
		                            <td>${caseData.caseDiseaseObject.currentDisease}</td>
		                        </tr>
		                        <tr>
		                            <td>个人史</td>
		                            <td>
		                                <ul>
		                                    <li>既往史：${caseData.caseDiseaseObject.history1}</li>
		                                    <li>个人史：${caseData.caseDiseaseObject.history2}</li>
		                                    <li>过敏史：${caseData.caseDiseaseObject.history3}</li>
		                                    <li>家族史：${caseData.caseDiseaseObject.history4}</li>
		                                </ul>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>体征</td>
		                            <td>
		                                <ul>
		                                    <li>神经系统：
			                                    <c:if test="${caseData.caseDiseaseObject.bodyState1Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState1Type == 1}">异样</c:if>
											</li>
		                                    <li>头部：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>颈部：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>胸部：
		                                   		<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 1}">异样</c:if>	
		                                    </li>
		                                    <li>腹部：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>四肢：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>皮肤：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 1}">异样</c:if>
		                                    </li>
		                                    <li>淋巴：
		                                    	<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 0}">正常</c:if>
												<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 1}">异样</c:if>
		                                    </li>
		                                </ul>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>辅助检查</td>
		                            <td>
		                                <ul>
		                                    <li>化验：${caseData.caseDiseaseObject.assay}</li>
		                                    <li>影像学：${caseData.caseDiseaseObject.image}</li>
		                                    <li>其他：${caseData.caseDiseaseObject.rest}</li>
		                                </ul>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>诊断</td>
		                            <td>${caseData.diseaseDiagnosisObject}</td>
		                        </tr>
		                        <tr>
		                            <td>治疗方案</td>
		                            <td>${caseData.caseDiseaseObject.plan}</td>
		                        </tr>
		                        <tr>
		                            <td>预后</td>
		                            <td>${caseData.caseDiseaseObject.futureState}</td>
		                        </tr>
		                    </tbody>
		                </table>
		            </div>
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
    </div>
    
    <div class="course_right" style="overflow:auto;">
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

        //初始化自动播放
        zkContrent(unitId,$("#"+unitId+""));
        //display 病例分析讨论
        display_analysis();
        //display 笔记;M
        displayNote();
        //case tab control;M
        if ($(".tabs_left").size() > 0){
	        tabs(".tabs_left");
	    }
        function tabs(tabarea){
		    $(tabarea + " .tab_list_left .tab").each(function(){
		        $(this).click(function(){
		            $(this).addClass("active").siblings().removeClass("active");
		            var c_name = $(this).attr("id");
		            $(tabarea + " .tab_cont." + c_name).show().siblings(".tab_cont").hide();
		        });
		    });
		}
        
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
    //根据id查询组课内容
    function zkContrent(obj, dom){    	
    	//设置置灰
    	$(dom).css("color","#ADADAD");
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
		    				play($("#preview img"));
		    			}else{
		    				$("#fengmian").css("display","block");
		    			}
		    		}
		    	}
		    }
		});	
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
    //add 病例分析讨论
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
    		data:'type=addUnitTalk&discussType=3&content=' + content + '&cvsetId=' + "${cvsetId}"
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
    //display 病例分析讨论   every 60s
    window.setInterval('display_analysis();', 60000);
    function display_analysis(){
    	$.ajax({
    		type:'post',
    		url: '${ctx}/entityManage/entityView.do',
    		data:'type=queryUnitTalk&discussType=3&cvsetId=' + "${cvsetId}" + '&unitId=' + "${unitId}",
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
    //add 笔记  to DB; M
     $('#comment_submit_note').click(function(){
	     $.ajax({
	     	type:'post',
	   		url: '${ctx}/entityManage/entityView.do',
	   		data:'type=addUnitNote&note_type=3&content=' + $('#comment_cont_note').val() + '&cvsetId=' + "${cvsetId}"
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
	   		data:'type=queryUnitNote&note_type=3' + '&cvsetId=' + "${cvsetId}" + '&unitId=' + "${unitId}",	   				
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
     //end;M
   
</script>
</body>
</html>