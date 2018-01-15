<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
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
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
    <script src="${ctx}/qiantai_page/js/rating.js?rev=5cab9c748cf8c51b4937cb8e6cf0d306"></script>
   
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script>
    <%-- <script src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script> --%>
    <script src="${ctx}/qiantai_page/js/main.min.js?rev=222fe8abc0fda3427a0f94eaf2939390"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
     <script src="${ctx}/qiantai_page/js/drag.js"></script>
     <script src="${ctx}/qiantai_page/js/yzm.js"></script>
     
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">   
    <%-- <link href="${ctx}/qiantai_page/css/iconfont/iconfont.css" rel="stylesheet">  --%>  
     <link href="${ctx}/qiantai_page/css/test/iconfont.css" rel="stylesheet">
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
	.my-span{  
	    overflow: hidden;  
	    text-overflow: ellipsis;  
	    white-space: nowrap;  
	    cursor: pointer;  
	}
</style>
</head>
<body  onload='createCode()'>
<div class="container">
  <%--   <%@include file="/qiantai_page/top2.jsp"%> --%>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        
        <li><a href="#">${cvSet.name}</a></li>
    </ul>
    <ul class="project_info">
        <li>
            <h1 class="p_title">                
                ${cvSet.name}
            </h1>
            <h3 class="p_sub_title">学科：
            <c:forEach items="${cvSet.examPropList}" var="list">
                ${list.name} &nbsp;
            </c:forEach>
            </h3>
            <span class="item_cover" style="<c:if test="${cvSet.file_path != null}">background:url(${cvSet.file_path})</c:if> no-repeat center;background-size:cover"></span>
            <div class="item_cont">
                <h2 class="title">
                    <span style=" position: absolute; /* margin-left: 362px; */">综合评分：
                    <c:choose>
                        <c:when test="${cvSet.grade != null}">
                            ${cvSet.grade}
                        </c:when>
                        <c:otherwise>
                            暂无评分
                        </c:otherwise>
                    </c:choose>
                    </span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-login"></use>
                    </svg>
                </h2>
                <div class="info">
                    <%-- <span style="width:360px;">类型：${cvSet.sign}</span> --%>
                    <span style="width:360px;">项目负责人：${cvSet.managerList[0].name}</span>
                    <span class="my-span" style="width:360px;" title="${cvSet.serial_number_str}">项目编号：${cvSet.serial_number_str}</span>
                    <%-- <span style="width:360px;">级别：
                        <c:choose>
                            <c:when test="${cvSet.level eq 1}">国家I类</c:when>
                            <c:when test="${cvSet.level eq 2}">省级I类</c:when>
                            <c:when test="${cvSet.level eq 3}">市级I类</c:when>
                            <c:when test="${cvSet.level eq 4}">省级II类</c:when>
                            <c:when test="${cvSet.level eq 5}">市级II类</c:when>
                            <c:when test="${cvSet.level eq 6}">无学分</c:when>
                        </c:choose>
                    </span> --%>
                    <span class="my-span" style="width:360px;" title="${cvSet.level_score_str}">学分：${cvSet.level_score_str}</span>
                    <span style="width:360px;">来源：${cvSet.organizationList[0].name}</span>
                    <span class="my-span" style="width:360px;" title="${cvSet.start_end_date_str}">起止时间：${cvSet.start_end_date_str}</span>
                    <%-- <span style="width:360px;">起止时间：<fmt:formatDate value="${cvSet.start_date}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${cvSet.end_date}" pattern="yyyy-MM-dd"/></span> --%>
                    <!-- <span style="width:360px;">项目审核人：${cvSet.deli_man}</span> -->
                </div>
            </div>
            <div class="bottom">
                <div class="big_num" style="float:left;margin-top:15px;">
                    <c:choose>
                        <c:when test="${cvSet.cost eq 0 || cvSet.cost == null}"><span class="font_green" style="float:left;">免费</span></c:when>
                        <c:otherwise>
                        <span class="font_red">
                            ￥${cvSet.cost}
                        </span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <p style="float:left;margin-left:300px;">                    
                    <button type="button" name="study_begin" id="study_begin" class="btn btn_blue btn_big">开始学习</button>                    
                </p>
            </div>
        </li>
    </ul>
    <div class="content top_border">
        <div class="main_cont">
            <div class="cont">
                <h2 class="title"><span>学习须知</span></h2>
                <ul>
                    <li>${cvSet.knowledge_needed}</li>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>项目简介</span></h2>
                <p style="text-indent:2em">${cvSet.introduction}</p>
            </div>
            <div class="cont">
                <h2 class="title"><span>项目负责人介绍</span></h2>
                <div class="main_user_info">
                        <span class="user_avatar">
                        <c:choose>
                            <c:when test="${cvSet.managerList[0].photo !=null && cvSet.managerList[0].photo != ''}">
                                <img src="${cvSet.managerList[0].photo}">
                            </c:when>
                            <c:otherwise>
                                <img src="${ctx}/qiantai_page/img/2.png">
                            </c:otherwise>
                        </c:choose>
                        </span>

                        <p class="user_name">${cvSet.managerList[0].name}</p>
                        <p class="user_cont">
                            ${cvSet.managerList[0].summary}
                        </p>
                </div>
            </div>
            <div class="cont">
                <h2 class="title"><span>授课教师</span></h2>
                <ul class="teachers">
                    <c:forEach items="${cvSet.teacherList}" var ="list">
                        <li class="user_info">
                            <span class="avatar">
                            <c:choose>
                                <c:when test="${list.photo !=null && list.photo != ''}">
                                    <img src="${list.photo}">
                                </c:when>
                                <c:otherwise>
                                    <img src="${ctx}/qiantai_page/img/2.png">
                                </c:otherwise>
                            </c:choose>                            
                            </span>
                            <p class="user_name">${list.name}</p>
                            <p class="user_cont">${list.jobName}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>书籍推荐</span></h2>
                <ul>
                    <%--<li>${cvSet.reference}</li>--%>
                    <c:forEach items="${cvSet.refereeBookList}" var ="list">
                        <li>
                                ${list.name}  ${list.url}
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>指南共识</span></h2>
                <ul>
                    <%--<li>${cvSet.knowledge_base}</li>--%>
                    <c:forEach items="${cvSet.knowledgeBaseList}" var ="list">
                        <li>
                                ${list.name}  ${list.url}
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>最新文献</span></h2>
                <ul>
                    <%--<li>${cvSet.reference_lately}</li>--%>
                    <c:forEach items="${cvSet.referencelatelyList}" var ="list">
                        <li>
                                  ${list.name}  ${list.url}
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>项目评价</span></h2>
                <div class="comment_top">
                    <span>综合评分：${cvSet.grade}</span>
                    <!-- 
                    <span class="go_comment" style="cursor:pointer"><i class="fa fa-edit"></i> 去评价 </span>
                    
                    <span style="cursor:pointer"><i class="fa fa-edit"></i> 去评价 </span> -->
                </div>
                <div class="comment_starts" style="display:none">
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
                            <input type="radio" name="example" class="rating" value="3">
                            <input type="radio" name="example" class="rating" value="4">
                            <input type="radio" name="example" class="rating" value="5">
                        </div>
                        <div class="star_tip"></div>
                    </div>
                </div>
                
                <ul class="comments_list">
                	
                	<c:forEach items="${pinglun}" var="pitem">
	                	<li>
	                        <span class="avatar tiny">
                            	<c:if test="${pitem.user_image !=  null}">
				               		<img src="${ctx}/../storage/upload/USER/${pitem.user_image}" onerror="imgonload(this,'${pitem.sex}');" id="userImage" name="userImage" >
				               	</c:if>
				               	<c:if test="${pitem.user_image == null}">
				               		<img src="${ctx}/qiantai_page/img/user_avatar.jpg"  onerror="imgonload(this,'${pitem.sex}');" id="userImage" name="userImage" >
				               	</c:if> 
	                        </span>
	                        <p>
	                            <span class="name">${pitem.realName}</span>
	                            <span class="date"><fmt:formatDate value="${pitem.scoreDate}" pattern="yyyy-MM-dd"/></span>
	                            
	                        </p>
	                        <p class="cont">${pitem.scoreContent}</p>
	                    </li>
                	</c:forEach>
                </ul>
            </div>
	        <%@include file="/commons/page.jsp"%>
            
        </div>
        <div class="aside cont">
            <h2 class="title"><span>目录</span></h2>
            <ul class="course_list">
                <c:forEach items="${cvSet.cvList}" var="list" varStatus="status">
                    <li class="${list.id}">课程${status.index+1}：${list.name}</li>
                    <ul>
                        <c:forEach items="${list.unitList}" var="unitList" varStatus="unitListStatus">
                        <li class="has_done">                              
                            <i class="iconfont pm" id="pm">&#xe674;</i>                            
                            <a title="${unitList.name}" displayLength="35" class="unit" title="${unit.name}" href="javascript:void(0);" onclick="playUnit('${unitList.id}',this,'${unitList.type}','${unitList.content}','${unitList.quota}');">单元${unitListStatus.index+1}：${unitList.name}</a>
                            <c:if test="${unitList.point eq 1}"><i class="fa fa-info-circle">任务点</i></c:if>
                            <span>
                              <!-- 根据不同单元类型显示对应图标 -->
                              <c:if test="${unitList.type eq 1}"><i class="fa fa-video-camera"></i></c:if>
                              <c:if test="${unitList.type eq 2}"><i class="fa fa-commenting"></i></c:if>
                              <c:if test="${unitList.type eq 3}"><i class="fa fa-pencil-square"></i></c:if>
                              <c:if test="${unitList.type eq 4}"><i class="fa fa-pencil-square"></i></c:if>
                              <c:if test="${unitList.type eq 5}"><i class="fa fa-file-text"></i></c:if>
                              <c:if test="${unitList.type eq 6}"><i class="fa fa-file-text"></i></c:if>
                            </span>                           
                        </li>
                        </c:forEach>
                    </ul>
                </c:forEach>
            </ul>
        </div>
    </div>
  <%--   <%@include file="/qiantai_page/bottom.jsp"%> --%>
</div>


<script type="text/javascript">
$("button[name=study_begin]").click(function(){
   	window.open("${ctx}/entityManage/entityView.do?type=play4View&paramType=project&id=${id}");
});

//项目根路径
var basepath ='${ctx}';
// 加载验证码方法
// $('#drag').drag();
var flag = false;
	var cvSetId = '${cvSet.id}';
		
    $(function () {    	    	    	    	    	        
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
                $(".star-rating-1").parent().find(".star_tip").text("内容很丰富，收获很多");
            }
        });
        $(".star-rating-2").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-2").parent().find(".star_tip").text("讲解很啰嗦，完全不明白");
            }
            if (vote == 2){
                $(".star-rating-2").parent().find(".star_tip").text("多余内容较多，需要挑着看");
            }
            if (vote == 3){
                $(".star-rating-2").parent().find(".star_tip").text("语言简洁，顺畅易懂");
            }
            if (vote == 4){
                $(".star-rating-2").parent().find(".star_tip").text("语言讲解精彩，收获很多");
            }
            if (vote == 5){
                $(".star-rating-2").parent().find(".star_tip").text("言简意赅，讲解有惊喜，非常容易理解");
            }
        });
        $(".star-rating-3").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-3").parent().find(".star_tip").text("逻辑混乱，完全听不明白");
            }
            if (vote == 2){
                $(".star-rating-3").parent().find(".star_tip").text("逻辑不清，课程目标不明");
            }
            if (vote == 3){
                $(".star-rating-3").parent().find(".star_tip").text("按部就班，基本达到课程目标");
            }
            if (vote == 4){
                $(".star-rating-3").parent().find(".star_tip").text("逻辑清晰，达到学习目标");
            }
            if (vote == 5){
                $(".star-rating-").parent().find(".star_tip").text("逻辑非常清晰，重点突出，收获超出预期");
            }
        });
        $(".star-rating-4").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-4").parent().find(".star_tip").text("课程设计敷衍，完全不知道在讲什么");
            }
            if (vote == 2){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线不清晰，和课程目标有差距");
            }
            if (vote == 3){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线相对清晰，基本能达到预期课程目标");
            }
            if (vote == 4){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线清晰，知识结构合理，达到学习目标");
            }
            if (vote == 5){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线非常清晰，教学形式设计有惊喜，收获超出预期");
            }
        });
                
        
        $(".close,button[name=submit]").click(function () {
            $(".popup").hide();
        });
        
        
       /*
       	* @author	han
        * @time		2017-01-25
        * Description	还能输入字数计算
        */
        $('.comment_cont').keypress(function(){
        	var txt = 200 - $(this).eq(0).val().length -1;
        	if (txt<0) return false;
        	$('.num_count').text(txt);
        });
        $('.comment_cont').keyup(function(){
        	var txt = 200 - $(this).eq(0).val().length;
        	$('.num_count').text(txt);
        });
        
        var score1 = parseInt("${cvSet.critiqueScore1-1}");
        var score2 = parseInt("${cvSet.critiqueScore2-1}");
        var score3 = parseInt("${cvSet.critiqueScore3-1}");
        var score4 = parseInt("${cvSet.critiqueScore4-1}");

		if (score1>=0)
        	$('.star-rating-1 .star').eq(score1).addClass('fullStar').prevAll().addClass('fullStar');
		if (score2>=0)
        	$('.star-rating-2 .star').eq(score2).addClass('fullStar').prevAll().addClass('fullStar');
		if (score3>=0)
        	$('.star-rating-3 .star').eq(score3).addClass('fullStar').prevAll().addClass('fullStar');
		if (score4>=0)
        	$('.star-rating-4 .star').eq(score4).addClass('fullStar').prevAll().addClass('fullStar');
        //$('.star-rating-1').parent().find(".star_tip").text("内容很丰富，收获很多");
        
        $("button[name=comment_submit]").click(function(){        
        	var param = "score1="+$('.star-rating-1 .fullStar').length;
        	param += "&score2="+$('.star-rating-2 .fullStar').length;
        	param += "&score3="+$('.star-rating-3 .fullStar').length;
        	param += "&score4="+$('.star-rating-4 .fullStar').length;
        	param += "&comment="+$('.comment_cont').val();        	        	
        });
    
    });
       
    
    /**
     * @author 张建国
     * @time   2017-01-16
     * @param  Obj
     * 说      明：通过点击单元进入学习页面
     **/
     function playUnit(obj,dom,type,content,quota){
    	var unitId = obj;
    	var cvId = $(dom).parent().parent().prev().attr("class");
    	var cvsetId = '${cvSet.id}';
    	
    	window.open("${ctx}/entityManage/entityView.do?type=play4View&paramType=project&id=${id}" + "&unitId=" + unitId + "&cvId=" + cvId);    	    	
    	//var unitType = type;    	
    }
     
                                 	
     
</script>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
</body>
</html>