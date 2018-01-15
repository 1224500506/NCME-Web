<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
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
	    <script type="text/javascript" src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
	    <script type="text/javascript" src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
	    <script type="text/javascript" src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
	    <script type="text/javascript" src="${ctx}/qiantai_page/js/iconfont.js"></script>
	    <script type="text/javascript" src="${ctx}/qiantai_page/js/main.min.js"></script>
	    <script type="text/javascript" src="${ctx}/qiantai_page/js/swfobject.js"></script>
	    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
	    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
	    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
	</head>
<body class="view">
<div class="container">
       <!-- 2017.01.26 Add By IE -->
    <div class ="course_video courseTests" style="position:relative;overflow:auto;">
    	
	    	<span class="logo"></span>
	        <span class="go_back"><i class="fa fa-angle-left"></i> 返回项目详情</span>
	     	<div class="course_title" style="margin-top:40px;">
	            <h2 id="className">${curCV.name}--${curUnit.name}</h2>
	            <h3>
	                <span id="classType">类型：话题讨论</span>
	                <span id="classTeacher">授课教师：
	                	<c:forEach items="${cv.teacherList}" var="teacher" varStatus="teacherStatus">
	                		${teacher.name }
	                		<c:if test = "${(varStatus + 1) != cv.teacherList.size()}">,</c:if>
	                	</c:forEach>
	                </span>
	                <span id="classDB">达标要求：</span>
	            </h3>
	        </div>
      
	    <div class="content no_padding " style="width:50%;margin-top:80px" >
	        <h1 class="main_title_2">
	        	<span class="float_right font_red">
            	</span>
	        	${paper.name}
	        </h1>
	        <c:forEach items = "${quesList}" var = "qt">
		        <div class="cont" style="padding:20px;border:1px solid #ccc" >
			        <p style="margin:0">题型：
				        <c:choose>
							<c:when test='${qt.question_label_id eq 1}'>单选题(A1)</c:when>
							<c:when test='${qt.question_label_id eq 2}'>单选题(A2)</c:when>
							<c:when test='${qt.question_label_id eq 3}'>单选题(A3/A4)</c:when>
							<c:when test='${qt.question_label_id eq 9}'>单选题(B1)</c:when>
							<c:when test='${qt.question_label_id eq 11}'>多选题(X)</c:when>
							<c:when test='${qt.question_label_id eq 12}'>填空题</c:when>
							<c:when test='${qt.question_label_id eq 13}'>判断题</c:when>
							<c:when test='${qt.question_label_id eq 14}'>简答题</c:when>
							<c:when test='${qt.question_label_id eq 18}'>名词解释</c:when>
							<c:when test='${qt.question_label_id eq 20}'>案例分析题</c:when>
						</c:choose>
			        </p>
			        <p style="margin:0">题干：${qt.content}</p>
		        </div>
		        <ul class="comments_list" id='${qt.id}'>
		        	<li>
		        		<div class="test_result" style = "display:none;width:40%;">
							<h2 id = "correct_solution_${qt.id}"></h2>
                   			<h2 id = "question_analysis_${qt.id}"></h2>
                		</div>
                		<script type="text/javascript">var i=65</script>
			        	<c:forEach items = "${qt.questionKeyList }" var = "key">
			        		
			        		<c:if test='${qt.question_label_id eq 1}'><p><input type="radio" name="${qt.id}" value = "${key.id}"><em class="fl"><script type="text/javascript">document.write(String.fromCharCode(i++));</script></em>.&nbsp; ${key.content}</p></c:if>
			        		<c:if test='${qt.question_label_id eq 2}'><p><input type="radio" name="${qt.id}" value = "${key.id}"><em class="fl"><script type="text/javascript">document.write(String.fromCharCode(i++));</script></em>.&nbsp; ${key.content}</p></c:if>
			        		<c:if test='${qt.question_label_id eq 3}'><p><input type="radio" name="${qt.id}" value = "${key.id}"><em class="fl"><script type="text/javascript">document.write(String.fromCharCode(i++));</script></em>.&nbsp; ${key.content}</p></c:if>
			        		<c:if test='${qt.question_label_id eq 9}'><p><input type="radio" name="${qt.id}" value = "${key.id}"><em class="fl"><script type="text/javascript">document.write(String.fromCharCode(i++));</script></em>.&nbsp; ${key.content}</p></c:if>
	                		<c:if test='${qt.question_label_id eq 11}'><p><input type="checkbox" name="${qt.id}" value = "${key.id}"><em class="fl"><script type="text/javascript">document.write(String.fromCharCode(i++));</script></em>.&nbsp; ${key.content}</p></c:if>
	                	</c:forEach>
                	</li>
		        </ul>

	        </c:forEach>
	        <div class="text_center"><button type="button" class="btn btn_blue btn_submit">提交</button> </div>
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
                    <c:forEach items = "${list}"  var="list">
                       <li class="${list.id}" title="${list.name}">${list.name}</li>
                       <ul>
                           <c:forEach items = "${list.unitList}" var="unit">
	                            <li class="has_done">
	                                <i class="fa fa-check-circle"></i>
	                                <c:choose>
	                                	<c:when test ="${unit.type==3}"><a href="javascript:courseTestTime('${unit.id}',this);">${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i></c:when>
	                                	<c:when test="${unit.type == 6}"><a href="javascript:diseaseAnalysis('${unit.id}');">${unit.name}</a><i class="fa fa-pencil-square"></i></c:when>
	                                	<c:when test="${unit.type == 2}"><a href="javascript:subjectDiscuss('${unit.id}', '${cvsetId}');">${unit.name}</a><i class="fa fa-pencil-square"></i></c:when>
	                                	<c:otherwise>
	                                		<a href="javascript:void(0);" id="${unit.id}" onclick="zkContrent('${unit.id}',this);">${unit.name}</a><i class="fa fa-info-circle fa-info"> 任务点</i>
	                                	</c:otherwise>
	                                </c:choose>
	                                <span><i class="fa fa-video-camera"></i> </span>
	                            </li>
                           </c:forEach>
                        </ul>
                     </c:forEach>
                    </ul>
                </div>
                <div class="tab2 tab_cont" style="display:none"></div>
                <div class="tab3 tab_cont" style="display:none"></div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    
    /*
    *@auth IE
    *@time 2017-01-27
    */
    var cvId = '${cvId}';
        
    //单元id
    var unitId = '${unitId}';
        
    //项目Id
    var cvsetId = '${cvsetId}';
    
    $(function () {

        $(".go_back").click(function () {
            document.location.href = ctxJS + "/projectDetail.do?id=${cvsetId}";
        });
       
       $('.btn_submit').click(function(){
       		
       		var testResultStr = "";
       		
       		$('.comments_list').each(function(key,val){
       			var chkStr = "";
       			testResultStr += $(this).prop("id") + ",";
       			$(this).find('li p input').each(function(key, val){
       				if ($(this).prop("checked")){
       					 chkStr += $(this).val() + "_";
       				}
       			});
       			testResultStr += chkStr + "/";
       		});
       		
       		$.ajax({
       			url:'${ctx}/entityManage/entityView.do?type=doMarking',
       			data:'testResult='+testResultStr+'&unitId='+unitId+'&cvId='+cvId+'&cvsetId='+cvsetId,
       			type:'POST',
       			dataType:'json',
       			success:function(result) {
       				if(result != null) {
       					var scoreStr = "分数：" + result.score;
       					$('.float_right').text(scoreStr);
       					$(result.info).each(function(key, val){
       						correctAnswerId = "#correct_solution_" + val.questionId;
       						questionAnalysisId = "#question_analysis_" + val.questionId;
       						$(correctAnswerId).text("正确答案：" + val.correctSolution);
       						$(questionAnalysisId).text("试题解析：" + val.analysis); 
       					});
       					$('.test_result').show();
       				}
       			}
       		});
       });
    });
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
    function zkContrent(_id, obj) {
    	$.ajax({
			    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId="+cvsetId+"&cvId="+cvId+"&unitId="+unitId,
			    type: 'POST',
			    dataType: 'json',
			    success: function(data){   
			    	var result = eval(data);
			    	if(result.message=='success'){
			    		//保存学习记录成功
			    		location.href = "${ctx}/entityManage/entityView.do?type=play3&cvsetId="+cvsetId+"&cvid="+cvId+"&unitId="+unitId;
			    	}else if(result.message == 'noLogin'){
			    		alert("请先登录.");
			    	}
			    }
			});
    }
</script>
</html>