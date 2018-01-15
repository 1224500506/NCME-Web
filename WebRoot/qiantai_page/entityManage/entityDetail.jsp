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
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <script src="${ctx}/qiantai_page/js/rating.js"></script>
    <script src="${ctx}/qiantai_page/js/drag.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
<style>
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
<body onload='createCode()'>
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
<!-- 
<ul class="bread_crumbs">
    <li>您的位置：</li>
    <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
    <li><a href="${ctx}/BasicSubject.do">分类名称<a> <i class="fa fa-angle-right"></i></a></a></li>
    <li><a href="${ctx}/entityManage/entityView.do?id=${id}">${CVSet.name}</a></li>
</ul>
 -->
<ul class="project_info">
    <li>
        <h1 class="p_title">
            <span class="right_icon">
                <i class="fa fa-share-alt"></i>
                <c:if test="${CVSetEntity.favorite != 1}">
                	<i class="like fa fa-heart fa-heart-o"></i>
                </c:if>
                <c:if test="${CVSetEntity.favorite == 1}">
                	<i class="like fa fa-heart"></i>
                </c:if>
            </span>
            ${CVSet.name}
        </h1>
        <h3 class="p_sub_title">学科：<c:forEach items = "${CVSet.courseList}" var = "prop">${prop.name},</c:forEach></h3>
        <span class="item_cover" style="background:url('${CVSet.file_path}') no-repeat center;background-size:cover"></span>
        <div class="item_cont">
            <h2 class="title">
                <span>综合评分：<c:if test="${CVSetEntity.score ==0.0 || CVSetEntity.score == null}">0.0</c:if><c:if test = "${CVSetEntity.score > 0.0}">${CVSetEntity.score }</c:if>分</span>
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-login"></use>
                </svg>
                1${CVSet.studentNum}</h2>
            <div class="info">
                <span>类型：${CVSet.sign}</span>
                <span>项目编号：${CVSet.code}</span>
                <span>项目负责人：<c:forEach items = "${CVSet.managerList}" var = "manager">${manager.name},</c:forEach></span>
                <span>级别：
                	<c:if test = "${CVSet.level eq -1}">全部</c:if>
		            <c:if test = "${CVSet.level eq 1}">国家I类</c:if>
                    <c:if test = "${CVSet.level eq 2}">省级I类</c:if>
                    <c:if test = "${CVSet.level eq 3}">市级I类</c:if>
                    <c:if test = "${CVSet.level eq 4}">省级II类</c:if>
                    <c:if test = "${CVSet.level eq 5}">市级II类</c:if>
           		</span>
                <span>学分：${CVSet.score}<c:if test = "${CVSet.score eq 0 || CVSet.score == null}">0.0</c:if>分</span>
                <span>来源：<c:forEach items = "${CVSet.organizationList}" var = "org">${org.name},</c:forEach></span>
            </div>
            <div class="foot">
                <span>起止时间：
	                <c:if test = "${CVSet.start_date != null}">
		            <fmt:formatDate value="${CVSet.start_date}" pattern="yyyy-MM-dd"/>至
					<fmt:formatDate value="${CVSet.end_date}" pattern="yyyy-MM-dd"/>
					</c:if>
				</span>
                <span>项目审核人：${CVSet.deli_man}</span>
            </div>
        </div>
        <div class="bottom">
            <div class="big_num">￥${CVSet.cost}</div>
            <p>
                <button type="button" name="study_begin" class="btn btn_blue btn_big">开始学习</button>
                <button type="button" name="study" class="btn btn_orange">继续学习</button>
                <button type="button" name="study_done" class="btn">已学完</button>
            </p>
        </div>
    </li>
</ul>

<div class="content top_border">
    <div class="main_cont">
        <div class="cont">
            <h2 class="title"><span>学习须知</span></h2>
            <ol>
                <li>${CVSet.knowledge_needed}；</li>
                <!-- <li>请按照课程顺序学习，不可跳过、快进。</li> -->
            </ol>
        </div>
        <div class="cont">
            <h2 class="title"><span>项目负责人介绍</span></h2>
            
            <c:forEach items="${CVSet.managerList}" var="manager">
            <div class="main_user_info">
                <a href="${ctx}/teacherManage/teacherDetail.do?mode=view&id=${manager.id}" target="_blank">
                    <span class="user_avatar">
                        <img src="${manager.photo}">
                    </span>
                    <p class="user_name">${manager.name}</p>
                    <p class="user_cont">
                        <span>${manager.workUnit}</span><br>
                        ${manager.summary}
                    </p>
                </a>
                </div>
            </c:forEach>
            
        </div>
        <div class="cont">
            <h2 class="title"><span>授课教师</span></h2>
            <ul class="teachers clearfix">   
            	<c:forEach items="${CVSet.teacherList}" var="teacher">         
	                <li class="user_info">
	                    <span class="avatar"><img src="${teacher.photo}"></span>
	                    <p class="user_name">${teacher.name}</p>
	                    <p>${teacher.workUnit}</p>
	                    <input type="hidden" value="${teacher.id}" id="teacherId">
	                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="cont">
            <h2 class="title"><span>书籍推荐</span></h2>
            <ol>
                <li>${CVSet.reference}</li>
                <!-- <li>《尖峰白内障手术技术》作者：刘保松 ISBN  ISBN 978-7-117-22082-8/R·22083</li> -->
            </ol>
        </div>
        <div class="cont">
            <h2 class="title"><span>指南共识</span></h2>
            <ol>
                <li>${CVSet.knowledge_base}</li>
                <!-- <li>《2010RCO 英国皇家眼科医学院白内障手术指南》 英国皇家眼科医学院</li> -->
            </ol>
        </div>
        <div class="cont">
            <h2 class="title"><span>最新文献</span></h2>
            <ol>
                <li>${CVSet.reference_lately}</li>
                <!-- <li>Vitrectomy for bilateral macular schisis without apparent optic disc anomalies.</li>
                <li>Risk factors for suture requirement and early hypotony in 23-gauge vitrectomy for complex vitreoretinal diseases.</li> -->
            </ol>
        </div>
        <div class="cont">
            <h2 class="title"><span>项目评价</span></h2>
            <div class="comment_top">
                <span>综合评分：</span><span id="scoreVal" style="color:#f90;"></span>
                <span id = "fa-edit"><i class="fa fa-edit"></i> 去评价 </span>
            </div>
            <div class="comment_starts">
                <div class="tips"><i class="fa fa-info-circle"></i> 温馨提醒：用鼠标点击星星就能打分了，打分后不可修改。</div>
                <div class="stars">
                    <div>内容实用</div>
                    <div class="star-rating-1">
                        <input type="radio" name="example" class="rating" value="1">
                        <input type="radio" name="example" class="rating" value="2">
                        <input type="radio" name="example" class="rating" value="3">
                        <input type="radio" name="example" class="rating" value="4">
                        <input type="radio" name="example" class="rating" value="5">
                    </div>
                    <div class="star_tip"></div>
                </div>
                <div class="stars">
                    <div>简洁易懂</div>
                    <div class="star-rating-2">
                        <input type="radio" name="example" class="rating" value="1">
                        <input type="radio" name="example" class="rating" value="2">
                        <input type="radio" name="example" class="rating" value="3">
                        <input type="radio" name="example" class="rating" value="4">
                        <input type="radio" name="example" class="rating" value="5">
                    </div>
                    <div class="star_tip"></div>
                </div>
                <div class="stars">
                    <div>逻辑清晰</div>
                    <div class="star-rating-3">
                        <input type="radio" name="example" class="rating" value="1">
                        <input type="radio" name="example" class="rating" value="2">
                        <input type="radio" name="example" class="rating" value="3">
                        <input type="radio" name="example" class="rating" value="4">
                        <input type="radio" name="example" class="rating" value="5">
                    </div>
                    <div class="star_tip"></div>
                </div>
                <div class="stars">
                    <div>课程设计</div>
                    <div class="star-rating-4">
                        <input type="radio" name="example" class="rating" value="1">
                        <input type="radio" name="example" class="rating" value="2">
                        <input type="radio" id="example" class="rating" value="3">
                        <input type="radio" id="example" class="rating" value="4">
                        <input type="radio" id="example" class="rating" value="5">
                    </div>
                    <div class="star_tip"></div>
                </div>
            </div>
            <div class="comment_form">
                <label>内容：</label>
                <textarea name="comment_cont" id="comment_cont" maxlength="200"></textarea>
                <div class="foot">
                    <button name="comment_submit" id="comment_submit" type="button" class="btn btn_orange btn_small">评论</button>
                    <span class="text_count">还能输入<span class="num_count" id="num_cont">200</span>字</span>
                </div>
            </div>
            <ul class="comments_list">
            <c:forEach items = "${entityList}" var = "entity">
            	<li>
            		<span class='avatar tiny'><img src='${entity.photo_url}'></span>
				 	<p><span class='name'>${entity.name}</span>
				 	<span class='date'><fmt:formatDate value="${entity.review_date}" pattern="yyyy-MM-dd"/></span></p><p class='cont'>${entity.content}</p>
				 </li>
			</c:forEach>
            </ul>
            <display:table name = "entityList" id = "entity" requestURI="${ctx}/entityManage/entityView.do?id=${id}"
					decorator="com.hys.exam.displaytag.OverOutWrapper" class = "teacher_table" excludedParams="msg" pagesize="10">
			</display:table>
        </div>
    </div>
    <div class="aside cont">
        <h2 class="title"><span>目录</span></h2>
        <ul class="course_list">
        	<script>var k = 1;</script>
        	<c:forEach items = "${CVSet.CVScheduleList}" var = "schedule">
            <li>课程<script> document.write(k);k++;</script>：${schedule.name}</li>
            <ul>
            	<script>var j = 1;</script>
            	<c:forEach items = "${schedule.unitList}" var = "unit">
	                <li>
	                    <i class="fa fa-check-circle"></i>
	                    <a href="">单元<script> document.write(j);j++;</script>：${unit.name}</a>
	                    <span><i class="fa fa-pencil-square"></i> </span>
	                </li>
                </c:forEach>
            </ul>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="popup">
	<form id="loginForm" action="${ctx}/login.do" name="loginForm" method="post">
    <div class="mask"></div>
    <div class="popup_cont clearfix login_form">
        <h2><span class="close" id="closeLogin"><i class="fa fa-times"></i> </span> 用户登录</h2>
        <div class="join_form" id="popup_form">
            <div class="input_div">
                    <label>用户名/手机/邮箱</label>
                    <input type="text" id="userdata" name="userData" placeholder="用户名/手机/邮箱" />
            </div>
            <div class="input_div">
                <label>密码</label>
                <input type="password" id="password" name="password" placeholder="密   码" />
            </div>
                   <!-- <div class="input_div">
                    <label>验证码</label>
                    <input type="hidden" id ="yzm" />
                <div id="drag"></div>
                </div> -->
                <div class="input_div">
					<input type="text" id="yzmInput" style="width:50%" placeholder="验证码"/> <input type="button"
						id="yzmCode" onclick="createCode()" style="width:60px;margin-left:18%;height:40px;width:30%;font-size:30px"
						title='点击更换验证码' /> <input type="hidden" id ="yzm" />
				</div>
            <div class="input_div">
                <a href="forget_pwd.html" class="float_right">忘记密码？</a>
                <input type="checkbox" name="remember_pwd"> 在本机记住密码
            </div>
            <div class="input_div">
                <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block">确认登录</button>
            </div>
        </div>
        <%@include file="/qiantai_page/bottom.jsp"%>
    </div>
	</form>
	</body>
</div>
</div>
<input type="hidden" value="${SESSION_TRAIN_ADMIN_USER}" id="session">
<script type="text/javascript">
var flag = false;
$(function () {
   	var totalVal = 0;
   	var usrId = "${user.userId}";
   	if(usrId == "") {
   		var str = "<i class='fa fa-share-alt'></i><i class='like fa fa-heart fa-heart-o'></i>";
   		$('.right_icon').html(str);
   	}
   	   	
 //  	initCommentsList();
   	   	 
       $(".course_list ul li:not('.has_done')").on("mouseover",function(){
           $(this).find(".fa-info").show();
       });
       $(".course_list ul li:not('.has_done')").on("mouseout",function(){
           $(this).find(".fa-info").hide();
       });
       $(".course_list ul li.has_done").on("mouseover",function(){
           $(this).find(".fa-info-circle").removeClass("fa-info").show();
       });
       $(".course_list ul li.has_done").on("mouseout",function(){
           $(this).find(".fa-info-circle").hide().addClass("fa-info");
       });
       $(".star-rating-1").rating(function(vote, event){     
       	var userId = "${user.userId}";
       	if(userId == "") {
       		if(confirm("您尚未登录！请您先登录！")) {
       			$(".popup").show();
       			return false;
       		} else {
       			$('.stars a').each(function(key, val){ 
		       		$(this).prop("class", "star");
		       	});
		       	return false;
       		}
       	}
       	   
       	$('.stars a').each(function(key, val){
       		var classProp = $(this).prop("class");
       		if(classProp == "star tmp_fs fullStar" || classProp=="star fullStar")
       		{
       			totalVal += 1;     			
       		}
       	});
    
    	totalVal = totalVal / 2;
    	$("#scoreVal").text("");
    	$("#scoreVal").text(totalVal);
    	totalVal = 0;
    	
       	if (vote == 1){
               $(".star-rating-1").parent().find(".star_tip").text("内容很少，几乎用不上");
           }
           if (vote == 2){
               $(".star-rating-1").parent().find(".star_tip").text("内容较少，收获很小");                
           }
           if (vote == 3){
               $(".star-rating-1").parent().find(".star_tip").text("内容尚可，达到基本期望");
           }
           if (vote == 4){
               $(".star-rating-1").parent().find(".star_tip").text("内容较充实，收获较多");
           }
           if (vote == 5){
           	$(".example").prop("checked", true);
               $(".star-rating-1").parent().find(".star_tip").text("内容很丰富，收获很多");
           }
           
       });
       $(".star-rating-2").rating(function(vote, event){
       	var userId = "${user.userId}";
       	if(userId == "") {
       		if(confirm("您尚未登录！请您先登录！")) {
        		$(".popup").show();
        		return false;
       		} else {
       			$('.stars a').each(function(key, val){
		       		$(this).prop("class", "star");
		       	});
		       	return false;
       		}
       	}
       	
       	$('.stars a').each(function(key, val){
       		var classProp = $(this).prop("class");
       		if(classProp == "star tmp_fs fullStar" || classProp=="star fullStar")
       		{
       			totalVal += 1;     			
       		}
       	});
    
    	totalVal = totalVal / 2;
    	$("#scoreVal").text("");
    	$("#scoreVal").text(totalVal);
    	totalVal = 0;
    	
           if (vote == 1){
               $(".star-rating-2").parent().find(".star_tip").text("内容很少，几乎用不上");
           }
           if (vote == 2){
               $(".star-rating-2").parent().find(".star_tip").text("内容较少，收获很小");
           }
           if (vote == 3){
               $(".star-rating-2").parent().find(".star_tip").text("内容尚可，达到基本期望");
           }
           if (vote == 4){
               $(".star-rating-2").parent().find(".star_tip").text("内容较充实，收获较多");
           }
           if (vote == 5){
               $(".star-rating-2").parent().find(".star_tip").text("内容很丰富，收获很多");
           }
       });
       $(".star-rating-3").rating(function(vote, event){
       	var userId = "${user.userId}";
       	if(userId == "") {
       		if(confirm("您尚未登录！请您先登录！")) {
        		$(".popup").show();
        		return false;
       		} else {
       			$('.stars a').each(function(key, val){ 
		       		$(this).prop("class", "star");
		       	});
		       	return false;
       		}
       	}
       	
       	$('.stars a').each(function(key, val){
       		var classProp = $(this).prop("class");
       		if(classProp == "star tmp_fs fullStar" || classProp=="star fullStar")
       		{
       			totalVal += 1;     			
       		}
       	});
    
    	totalVal = totalVal / 2;
    	$("#scoreVal").text("");
    	$("#scoreVal").text(totalVal);
    	totalVal = 0;
    	
           if (vote == 1){
               $(".star-rating-3").parent().find(".star_tip").text("内容很少，几乎用不上");
           }
           if (vote == 2){
               $(".star-rating-3").parent().find(".star_tip").text("内容较少，收获很小");
           }
           if (vote == 3){
               $(".star-rating-3").parent().find(".star_tip").text("内容尚可，达到基本期望");
           }
           if (vote == 4){
               $(".star-rating-3").parent().find(".star_tip").text("内容较充实，收获较多");
           }
           if (vote == 5){
               $(".star-rating-").parent().find(".star_tip").text("内容很丰富，收获很多");
           }
       });
       $(".star-rating-4").rating(function(vote, event){
       	var userId = "${user.userId}";
       	if(userId == "") {
       		if(confirm("您尚未登录！请您先登录！")) {
        		$(".popup").show();
        		return false;
       		} else {
       			$('.stars a').each(function(key, val){ 
		       		$(this).prop("class", "star");
		       	});
		       	return false;
       		}
       	}
       	
       	$('.stars a').each(function(key, val){
       		var classProp = $(this).prop("class");
       		if(classProp == "star tmp_fs fullStar" || classProp=="star fullStar")
       		{
       			totalVal += 1;     			
       		}
       	});
    
    	totalVal = totalVal / 2;
    	$("#scoreVal").text("");
    	$("#scoreVal").text(totalVal);
    	totalVal = 0;
    	
           if (vote == 1){
               $(".star-rating-4").parent().find(".star_tip").text("内容很少，几乎用不上");
           }
           if (vote == 2){
               $(".star-rating-4").parent().find(".star_tip").text("内容较少，收获很小");
           }
           if (vote == 3){
               $(".star-rating-4").parent().find(".star_tip").text("内容尚可，达到基本期望");
           }
           if (vote == 4){
               $(".star-rating-4").parent().find(".star_tip").text("内容较充实，收获较多");
           }
           if (vote == 5){
               $(".star-rating-4").parent().find(".star_tip").text("内容很丰富，收获很多");
           }
       });

       $(".user_info").click(function(){
           window.open("${ctx}/teacherManage/teacherDetail.do?mode=view&id="+$('#teacherId').val(),"blank");
       });
       $("button[name=study_begin]").click(function(){
       	if($('#session').val() != ""){
       		
       		//暂时播放
       		location.href="${ctx}//entityManage/entityView.do?type=play";	
       		
       		//暂时屏蔽
       		
       		/*var url = "${ctx}/Ajaxlogin.do?mode=update&id=${id}&userId=${user.userId}";
       		$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'text',
			    success: function(result){   
	//			   initCommentsList();
			    }
			});	*/
       		
       		
       	}
        else $(".popup").show();
        $("#userdata").focus();
       });
       $(".close,button[name=submit]").click(function () {
           $(".popup").hide();
       });
       $("button[name=submit]").click(function () {
           window.localStorage.setItem("isLogin","true");
       });
       
       $('.like').click(function(){
       	var userId = "${user.userId}";
       	if(userId == "") {
       		if(confirm("您尚未登录！请您先登录！"))
       		$(".popup").show();
       		$("#userdata").focus();
       		return false;
       	}
       	var isFavorite = 0;
       	if($(this).prop("class") == "like fa fa-heart")
       	{
       		isFavorite = 1;
       	} else {
       		isFavorite = 0; //no heart
       	}
       	
       	//Ajax process
       	var url = "${ctx}/Ajaxlogin.do?mode=favor&id=${id}&userId=${user.userId}&favor="+isFavorite;
	
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
	//		  initCommentsList();
		    }
		});	
       });
       $("#fa-edit").click(function(){
       		if($("#scoreVal").text() == "" || $("#scoreVal").text() == "0.0")
       		{
       			alert("请选择综合评分!");
       			return false;
       		}
       		var url = "${ctx}/Ajaxlogin.do?mode=score&id=${id}&userId=${user.userId}&score="+$("#scoreVal").text();
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'text',
			    success: function(result){
			    	if(result == 'success')
			    	{
			    		alert("操作成功!");
			    		document.location.href = document.location.href.split("#")[0];
			    	}		    	
			//	 	initCommentsList();
			    }
			});
       });
       
       $("#comment_cont").keyup(function(evt){
       	var str = $("#comment_cont").val();
       	//if(str.length > 200) {
       		$("#num_cont").text(200-str.length);
       	//}
       });
       
       $("#comment_submit").click(function(){
	       	var userId = "${user.userId}";
	       	if(userId == "") {
	       		if(confirm("您尚未登录！请您先登录！"))
	       		$(".popup").show();
	       		$("#userdata").focus();
	       		return false;
	       	}
	       	if($('#comment_cont').val() == ""){
		       		alert("请输入评论内容！");
					return;
		       	}       	
       	//Ajax Process
       	var url = "${ctx}/Ajaxlogin.do?mode=cont&id=${id}&userId=${user.userId}&content="+$("#comment_cont").val();
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'text',
		    success: function(result){
		    	if(result == 'success')
		    	{
		    		alert("操作成功!");
		    		document.location.href = document.location.href.split("#")[0];
		    	}		    	
		//	 	initCommentsList();
		    }
		});	
       });
             
       function initCommentsList() {
       	var url = "${ctx}/entityManage/getEntityInfo.do?mode=display&id=${id}";
	
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			 	var str = "";
			 	$(result.list).each(function(key, value){
			 		if(value.content != "" && value.content != null){
				 		str += "<li><span class='avatar tiny'><img src="+value.photo_url+"'></span>";
				 		str += "<p><span class='name'>"+value.name+"</span>";
				 		str += "<span class='date'>"+value.date+"</span></p><p class='cont'>"+value.content+"</p></li>";
			 		}
			 	});			 	
			 	$(".comments_list").html(str);
		    }
		});	
       }
       
     // Login 
   
   $("#submit_btn").click(function(){	
		if($('#userdata').val() == "")
		{
			alert("请输入用户名/手机/邮箱！");
			return;
		}
		if($('#password').val() == "")
		{
			alert("请输入密码！");
			return;
		}
		if(!validate()){
			return;
		}
		 /* if(!flag)
			{    
				alert("请完成验证码验证！");
				return;
			}  */
		var param = "userData="+$('#userdata').val()+"&password="+$('#password').val()+"&yzm="+$('#yzm').val();
		var url = "${ctx}/Ajaxlogin.do";
		$.ajax({
		    url:url,
		    data:param,
		    type: 'POST',
		    dataType: 'JSON',
		    success: function(result){
		    	if(result.result != 'success')
		    	{
			    	var errorMsg = result.errorMessage;
					var input = result.inputMode;
					var userData = result.userData;
					var userPass = result.userPass;
					if(errorMsg != "")
					{
						alert(errorMsg);
						$('#userdata').val(userData);
						$('#password').val(userPass);
						if(input == "1")
						{
							$('#userdata').focus();
						}
						else if(input == "2")
						{
							$('#password').focus();
						}
						else
						{
							//$('#yzm').focus();
							$('#yzmInput').focus();
						}
					}
		    	}
		    	else window.location.reload(true);	    
		    }
		});
	});
	$("#yzm, #password, #userdata").keydown(function(event){
		if(event.which == 13){
							
				if($('#userdata').val() == "")
				{
					alert("请输入用户名/手机/邮箱！");
					return;
				}
				if($('#password').val() == "")
				{
					alert("请输入密码！");
					return;
				}
				if(!validate()){
					return;
				}
				/* if($('#yzm').val()=="")
				{
					alert("请输入验证码！");
					return;
				} */
			var param = "userData="+$('#userdata').val()+"&password="+$('#password').val()+"&yzm="+$('#yzm').val();
			var url = "${ctx}/Ajaxlogin.do";
			$.ajax({
			    url:url,
			    data:param,
			    type: 'POST',
			    dataType: 'JSON',
			    success: function(result){
			    	if(result.result != 'success')
			    	{
				    	var errorMsg = result.errorMessage;
						var input = result.inputMode;
						var userData = result.userData;
						var userPass = result.userPass;
						if(errorMsg != "")
						{
							alert(errorMsg);
							$('#userdata').val(userData);
							$('#password').val(userPass);
							if(input == "1")
							{
								$('#userdata').focus();
							}
							else if(input == "2")
							{
								$('#password').focus();
							}
							else
							{
								//$('#yzm').focus();
								$('#yzmInput').focus();
							}
						}
			    	}
			    	else window.location.reload(true);	    
			    }
			});
		}
	});
	function changeValidateCode(obj) {     
           //获取当前的时间作为参数，无具体意义     
        var timenow = new Date().getTime();     
           //每次请求需要一个不同的参数，否则可能会返回同样的验证码     
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。     
        obj.src="${ctx}/login/GetRandomPictureAction.do?d="+timenow;     
    }        
});
</script>
</html>
