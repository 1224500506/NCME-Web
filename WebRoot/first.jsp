<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
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
    <meta name="keyword" content="中国继续医学教育网，NCME，CME，继续医学教育，公需科目，基层学院，胜任力，名师，专委会，指南，病例，慕课，直播，VR，三维动画">
    <meta name="description" content="中国继续医学教育网是国家卫生计生委重点督办项目，包含了公需科目，基层学院，胜任力，名师，专委会，指南，病例，慕课，直播，VR，三维动画等栏目">
    <title>中国继续医学教育网_NCME</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    
    <link rel="shortcut icon" href="/favicon.ico"/>
     <script src="${ctx}/qiantai_page/js/drag.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    
<style type="text/css">

.paybox{
    padding: 20px;
    border-radius: 10px;
    background-color: #fff;
    position: fixed;
    top: 50vh;
    left: 50%;
    z-index: 999999;
    width: 500px;
    margin: -200px 0 0 -270px;
    display: none; 


}


.paybox .joinform #paysubmit{
    border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;
    background-color: #f90;
    color: #fff;

}


.paybox .joinform #closebox2{
     border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;
    border:1px solid #ccc;
    background-color: #fff;
    color: #615858;

}




.paybox .joinform{
    margin: 20px auto;
    width: 80%;
}

.paybox h2{
	font-size: 18px;
    border-bottom: 1px solid #12bce1;
    margin-top: 0;
}



.joinform .input_div input[type="text"],input[type='password']{
    padding: 5px;
    background: #fff;
    color: #333;
    box-sizing: border-box;
    width: 100%;
    border: 1px solid #666;
    font-size: 14px;
    border-radius: 7px;
}


#labcard{
font-weight: bold;
}

#labpwd{
font-weight: bold;
}

.bgc{
    width: 100%;
    height: 100%;
    background-color: rgba(51, 51, 51, 0.5);
    position:absolute;
    top: 0;
    left: 0;
    z-index: 100;
    display: none;
    
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
<body onload='createCode()'>

<div class="bgc"></div>
<form id="fm1" name="fm1" method="post" action="${ctx}/first.do">
    <div class="container">
        <%@include file="/qiantai_page/top.jsp" %>
        <div class="slider">
            <ul class="bxslider">
        	   <%--   <c:forEach	items="${CVS.userImageList}" varStatus="status" var="image">
					<c:forEach	items="${image.departmentPropList}" varStatus="status" var="prop">
				      <li style="background:url(${}) no-repeat center;background-size:100% auto" onclick="javascript:ifLogin(0);"></li>
					</c:forEach>
				</c:forEach>  --%>
            	<c:forEach items="${advers}" var="image">
            		<c:if test="${image.href !=  null}">
	            		<li style="background:url(${image.href}) no-repeat center;background-size:100% auto">
	            			<a href="${image.href}"><img src="${image.image}"/></a>
	            		</li>
            		</c:if>
            		<c:if test="${image.href ==  null && image.href =='' ">
            			<li style="background:url(${ctx}/contentManage/bannerManage.do?method=list&id"+${image.id}) no-repeat center;background-size:100% auto">
	            			<a href="${ctx}/contentManage/bannerManage.do?method=list&id"+${image.id}><img src="${image.image}"/></a>
	            		</li>
            		</c:if>
            	</c:forEach>
            	
<%--             	<img   src="${model.url}" onerror="imgonload(this,'${url}');" id="Image"  name="model.url" > --%>
            	
<%--             	<li style="background:url(${}) no-repeat center;background-size:100% auto" onclick="javascript:ifLogin(0);"></li> --%>
            	<%-- <li style="background:url(${ctx}/qiantai_page/img/slides/slider_4.jpg) no-repeat center;background-size:100% auto" onclick="javascript:ifLogin(0);"></li>
                <!-- <li style="background:url(${ctx}/qiantai_page/img/slides/slider_3.jpg) no-repeat center;background-size:100% auto"></li>  -->
                <li style="background:url(${ctx}/qiantai_page/img/slides/slider_1.jpg) no-repeat center;background-size:100% auto"></li>
                <li style="background:url(${ctx}/qiantai_page/img/slides/slider_2.jpg) no-repeat center;background-size:100% auto"></li> --%>
            </ul>
        </div>
        <input type="hidden" id="dcvid" value="" />
        <input type="hidden" id="zbcvid" value="" />
        <input type="hidden" id="zbcvsetid" value="" />
        <input type="hidden" id="costType" value="" />
        <!-- 直播加入时卡项目入口页 -->
        <div class="paybox" >
			 <h2><span class="close" id="closebox" style="float: right;" ><i class="fa fa-times" style="color: rgb(204,204,204);"></i></span> 添加学习卡 </h2>
			 <div class="joinform">
			<div class="input_div">
			 <label id="labcard">输入学习卡卡号<input type="text" id="cardnumber" maxlength="20"></label>
			 <label id="labpwd">输入学习卡密码<input type="password" id="cardpwd" maxlength="20"></label>
			<br/>
			<input type="button" value="提交" id="paysubmit"/>
			<input type="button" value="取消" id="closebox2"/>
			</div> 
			 </div>
		</div>
        <div class="content">
            <ul class="icons">
                <li onclick="javascript:searchProject(1);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-guide-2"></use>
                    </svg>
                    
                </span>
                    <h3>指南</h3>
                </li>
                <li onclick="javascript:searchProject(2);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-bingli"></use>
                    </svg>
                </span>
                    <h3>病例</h3>
                </li>
                <li onclick="javascript:viewSubject('');">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-mooc"></use>
                    </svg>
                </span>
                    <h3>慕课</h3>
                </li>
                <li onclick="javascript:searchProject(3);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-video"></use>
                    </svg>
                </span>
                    <h3>直播</h3>
                </li>
                <li onclick="javascript:searchProject(4);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-vr-1"></use>
                    </svg>
                </span>
                    <h3>VR/三维动画</h3>
                </li>
            </ul>
        </div>
        <div class="content content_all has_bg_1 clearfix">
            <h1 class="main_title"><a href="javascript:viewSubject('');" class="more">更多 <i
                    class="fa fa-angle-right"></i></a>优质慕课</h1>
            <ul class="item_list item_img_list">
                <c:forEach items="${mukeList}" var="muke">
                    <li onclick="javascript:gotoDetail(${muke.id});">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <div class="info">
                                <span>项目负责人：${muke.managerList[0].name}</span>
                            </div>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <p class="aa">
                                <c:if test="${muke.level==1}">国家I类${muke.score}分</c:if>
                                <c:if test="${muke.level==2}">省级I类${muke.score}分</c:if>
                                <c:if test="${muke.level==3}">市级I类${muke.score}分</c:if>
                                <c:if test="${muke.level==4}">省级II类${muke.score}分</c:if>
                                <c:if test="${muke.level==5}">市级II类${muke.score}分</c:if>
                                <c:if test="${muke.level==6}">${muke.score}分</c:if>
                            </p>
                            <%-- <div class="foot no_border">
	                    		<!-- <span class="font_red">￥290</span> -->
	                    		<span>${muke.studentNum}次学习</span>
	                		</div> --%>
                            <h3 class="foot no_border">
                                    <%-- <span class="float_right">${muke.studentNum}人在学</span> --%>
                                <c:choose>
                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
                            	 			￥${muke.cost}
                            			</span>
                                    </c:when>
                                    <c:otherwise>
                            		<span class="font_red" style="float:left;">
                            	 		￥${muke.cost}
                            		</span>
                                    </c:otherwise>
                                </c:choose>
                                1${muke.studentNum}次学习
                            </h3>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="content clearfix">
            <h1 class="main_title"><a href="${ctx}/starCourseList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>名师课程</h1>
            <ul class="item_list item_img_list">
                <c:forEach items="${mingshiList}" var="muke">
                    <li onclick="javascript:gotoDetail2(${muke.id});" style="height:270px;">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat top center;background-size:100% auto"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <p class="desc topvebanner-p"> ${muke.introduction}</p>
                            <p>授课教师：${muke.teacherList[0].name}</p>
                            <h3 class="foot no_border">
								<c:choose>
                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
                            	 			￥${muke.cost}
                            			</span>
                                    </c:when>
                                    <c:otherwise>
                            <span class="font_red" style="float:left;">
                            	 ￥${muke.cost}
                            </span>
                                    </c:otherwise>
                                </c:choose>
                            </h3>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="content content_all has_bg_1 clearfix">
            <h1 class="main_title"><a href="${ctx}/caseList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>典型病例</h1>
            <ul class="item_list item_img_list">
                <c:forEach items="${bingliList}" var="muke">
                    <li onclick="javascript:gotoDetail2(${muke.id});" style="height:265px;">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <p>授课教师：${muke.teacherList[0].name}</p>
                            <h3 class="foot no_border">
								<c:choose>
                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
                            	 			￥${muke.cost}
                            			</span>
                                    </c:when>
                                    <c:otherwise>
                            <span class="font_red" style="float:left;">
                            	 ￥${muke.cost}
                            </span>
                                    </c:otherwise>
                                </c:choose>
                            </h3>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="content clearfix" style="display:none">
            <h1 class="main_title"><a href="${ctx}/liveList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>精彩直播
            </h1>
            <ul class="item_list item_img_list" id="timerFreshUL">
                <c:forEach items="${zhiboList}" var="muke" varStatus="SerialNumber">
                    <li onclick = "javascript:gotoLiveView('${muke.id}','${muke.type}','${muke.cost_type}','${muke.startDate}','${muke.endDate}');">
		            <span class="item_cover"
                          style="background:url('${muke.file_path}') no-repeat center;background-size:cover">
                        <div class="item_title">
                        	<input type="hidden" id="cvId${SerialNumber.index}" value="${muke.id}" />
                    		<span class="ribbon soon" id="soon${SerialNumber.index}">...</span>
                    		<input type="hidden" id="timerCvType${muke.id}" value="${muke.type}" />
                        	<span><fmt:formatDate value="${muke.startDate}"  pattern="MM月dd日"/>
	                        <fmt:formatDate value="${muke.startDate}"  pattern="HH:mm"/></span><span style="color:#fff;">-</span>
	                        <span><fmt:formatDate value="${muke.endDate}"  pattern="MM月dd日"/>
	                        <fmt:formatDate value="${muke.endDate}"  pattern="HH:mm"/></span>
                        </div>
                        <i class="fa fa-play-circle-o"></i>
		            </span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <div class="foot no_border">
                            	<c:choose>
                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
                            	 			￥${muke.cost}
                            			</span>
                                    </c:when>
                                    <c:otherwise>
                            <span class="font_red" style="float:left;">
                            	 ￥${muke.cost}
                            </span>
                                    </c:otherwise>
                                </c:choose>
                                <span style="line-height:35px;">授课教师：${muke.teacherList[0].name}</span>
                                <!-- <span style="line-height:35px;">人数：0/1000</span> -->
                            </div>
                            <div class="foot" style="padding:8px 0 0 0;">
                                <span style="display:block;width:100%;text-align:center;">${muke.teacherList[0].workUnit}</span>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="content clearfix">
            <h1 class="main_title"><a href="javascript:searchProject(1);" class="more">更多 <i
                    class="fa fa-angle-right"></i></a>指南解读</h1>
            <ul class="item_list item_img_list">
                <c:forEach items="${zhinanList}" var="zhinan">
                    <li onclick="javascript:gotoDetail(${zhinan.id});">
                        <span class="item_cover"
                              style="background:url('${zhinan.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${zhinan.name}">${zhinan.name}</h2>
                            <div class="info">
                                <span>项目负责人：${zhinan.managerList[0].name}</span>
                            </div>
                            <p class="desc topvebanner-p">${zhinan.introduction}</p>
                            <p class="aa">
                                <c:if test="${zhinan.level==1}">国家I类${zhinan.score}分</c:if>
                                <c:if test="${zhinan.level==2}">省级I类${zhinan.score}分</c:if>
                                <c:if test="${zhinan.level==3}">市级I类${zhinan.score}分</c:if>
                                <c:if test="${zhinan.level==4}">省级II类${zhinan.score}分</c:if>
                                <c:if test="${zhinan.level==5}">市级II类${zhinan.score}分</c:if>
                            </p>

                            <h3 class="foot no_border">
                                   <c:choose>
                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${muke.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
                            	 			￥${muke.cost}
                            			</span>
                                    </c:when>
                                    <c:otherwise>
                            <span class="font_red" style="float:left;">
                            	 ￥${muke.cost}
                            </span>
                                    </c:otherwise>
                                </c:choose>
                            </h3>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="content">
            <div class="left_cont" style="min-height:300px;">
                <!-- <h1 class="main_title"><i class="fa fa-bullhorn"></i> 资讯公告</h1> -->

                <h1 class="main_title"><a href="javascript:viewList(1);" class="more">更多 <i
                        class="fa fa-angle-right"></i></a><i class="fa fa-bullhorn"></i> 资讯公告</h1>

                <ul class="news_list" style="border-top: 1px solid #ccc;">
                    <c:forEach items="${contentList}" var="sentence">
                        <li><span><fmt:formatDate value="${sentence.deli_date}" pattern="yyyy-MM-dd"/></span><i
                                class="fa fa-angle-right"></i> <a href="javascript:postView(1,${sentence.id});"
                                                                  title="${sentence.title}">
                            <c:choose>
                                <c:when test="${fn:length(sentence.title) > 28}">
                                    ${fn:substring(sentence.title, 0, 28)}...
                                </c:when>
                                <c:otherwise>
                                    ${sentence.title}
                                </c:otherwise>
                            </c:choose>
                        </a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="right_cont" style="min-height:300px;">
                <!-- <h1 class="main_title"> <i class="fa fa-book"></i> 政策法规</h1> -->

                <h1 class="main_title"><a href="javascript:viewList(2);" class="more">更多 <i
                        class="fa fa-angle-right"></i> </a><i class="fa fa-book"></i> 政策法规</h1>

                <ul class="news_list" style="border-top: 1px solid #ccc;">
                    <c:forEach items="${sentenceList}" var="sentence">
                        <li><span><fmt:formatDate value="${sentence.deli_date}" pattern="yyyy-MM-dd"/></span><i
                                class="fa fa-angle-right"></i> <a href="javascript:postView(2,${sentence.id});"
                                                                  title="${sentence.title}">
                            <c:choose>
                                <c:when test="${fn:length(sentence.title) > 28}">
                                    ${fn:substring(sentence.title, 0, 28)}...
                                </c:when>
                                <c:otherwise>
                                    ${sentence.title}
                                </c:otherwise>
                            </c:choose>
                        </a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="content content_all has_bg_1">
            <h1 class="center_title">学习流程</h1>
            <ul class="icons icons_bg flow">
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-login"></use>
                    </svg>
                </span>
                    <h3>登录</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-signup"></use>
                    </svg>
                </span>
                    <h3>选课</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-learn"></use>
                    </svg>
                </span>
                    <h3>学习</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-application"></use>
                    </svg>
                </span>
                    <h3>申请</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-graduation"></use>
                    </svg>
                </span>
                    <h3>完成</h3>
                </li>
            </ul>
        </div>
        <%@include file="/qiantai_page/bottom.jsp" %>
    </div>
</form>
<div class="popup">
	<form id="loginForm" name="loginForm" action = "${ctx}/loginAjax.do" method="post">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form">
		    	<input type="hidden" id ="isLive" value="false"/>
		        <h2><span class="close"><i class="fa fa-times"></i> </span> 用户登录</h2>
		        <div class="join_form" id="popup_form">
		            <div class="input_div">
		                <label>用户名/手机/邮箱</label>
		                <input type="text" name="userData" id="userName"/>
		            </div>
		            <div class="input_div">
		                <label>密码</label>
		                <input type="password" name="password" id="password"/>
		            </div>
		          <div class="input_div">
                    <!-- <label>验证码</label>
                    <input type="hidden" id ="yzm" />
                <div id="drag"></div> -->
                <input type="text" id="yzmInput" style="width:50%" placeholder="验证码"/> <input type="button"
						id="yzmCode" onclick="createCode()" style="width:60px;margin-left:18%;height:40px;width:30%;font-size:30px"
						title='点击更换验证码' /> <input type="hidden" id ="yzm" />
		        </div>
	            <div class="input_div">
	               <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block float_left">确认登录</button>
	            </div>
		    </div>
		</div>
	</form>
</div>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
</body>
</html>

<script>
var basepath ='${ctx}';
//$('#drag').drag();
var flag = false;
var  flags = false; 
    var url = $("#url").val();

    function viewSubject(propId) {
        window.location.href = "${ctx}/ProjectList.do?xueke=" + propId;
    }

    function searchProject(mode) {
        if (mode == 1) {
            window.location.href = "${ctx}/ProjectList.do?sign="
                + encodeURI(encodeURI("指南解读"));
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
        var c = document.body.clientHeight;
        var d = document.body.clientWidth;
        window.location.href = e;
        //window.open(e,"","top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="+ d + ",height=" + c);
    }

    function viewList(mode) {
        var e = "${ctx}/postList.do?mode=" + mode;
        var c = document.body.clientHeight;
        var d = document.body.clientWidth;
        window.location.href = e;
        //window.open(e,"","top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="+ d + ",height=" + c);
    }

    function gotoDetail(id) {
        location.href = "${ctx}/projectDetail.do?id=" + id;
    }

    function gotoDetail2(id) {
		$("#dcvid").val(id);
    	//保存学习记录--项目
    	$.ajax({
				type: 'POST',
				url: '${ctx}/study/logstudy.do?mode=queryLogin&id='+id,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'noLogin'){
						//弹出登录窗口
						$(".popup").show();
					}else{
						location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class&isCv=isCv";
					};
				}
	    });
    }
    function ifLogin(id) {
    	//保存学习记录--项目
    	$.ajax({
				type: 'POST',
				url: '${ctx}/study/logstudy.do?mode=queryLogin&id='+id,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					location.href = "${ctx}/XiangYiIndex.do?code="+result.code;
				}
	    });
    }
    $(".close,button[name=submit]").click(function () {
        $(".popup").hide();
    });
    $("button[name=submit]").click(function () {
        window.localStorage.setItem("isLogin","true");
    });
    
    //$("#submit_btn").click(function(){
		/* if($('#userName').val() == ""){
			alert("请输入用户名/手机/邮箱！");
			return;
		}
		if($('#password').val() == ""){
			alert("请输入密码！");
			return;
		}
		 if(!flag)
			{    
				alert("请完成验证码验证！");
				return;
			} 
		//通过AJAX实现登录功能
		$.ajax({
			type: 'POST',
			url:  '${ctx}/loginAJAX.do',
			data:{
				userData:$("#userName").val(),
				password:$("#password").val(),
				yzm:$("#yzm").val(),
			},
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message="success"){
					//刷新页面
					var id=$('#id').val();
					location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class";
				}
			}
        }); */
        
        
        
        
        
	//});
    
    
    
   	$("#submit_btn").click(function(){
   		if($('#userName').val() == ""){
   			alert("请输入用户名/手机/邮箱！");
   			return;
   		}
   		if($('#password').val() == ""){
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
		//$("#submit_btn").attr("disabled","disabled");
   		//通过AJAX实现登录功能
   		$.ajax({
			type: 'POST',
			url:  '${ctx}/loginAJAX.do',
			data:$("#loginForm").serialize(),
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message == "success"){
					$("#submit_btn").removeAttr("disabled");
					//刷新页面
					var id=$("#dcvid").val();
					var isLive = $("#isLive").val();
					var costType = $("#costType").val()
					if(isLive == "true"){//此处用于判断用户进入直播的情况
						var zbcvid = $("#zbcvid").val();
						var zhiboType = $("#timerCvType"+zbcvid).val();
						if(zhiboType == 4){
							checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
							if(flags ==true){
								window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+zbcvid+"&unitId="+0;
								return;
							}
						}
						$.ajax({//根据课程信息拿到项目信息
							type: 'POST',
							url: '${ctx}/BasicSubject.do?mode=queryCVsetByCVId&cvId='+zbcvid,
							dataType: 'JSON',
							success : function(data){
								var result = eval(data);
								if(result.message=='success'){
									$("#zbcvsetid").val(result.cvSetId);
									checkbind(result.cvSetId,costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
									if(flags ==true){
										$("#isLive").val("false");
 	 									window.location.href = "${ctx}/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
 	 								}
								}
							},
							error: function(e){
								$(".popup").hide();
							}
	    				}); 
					}else{
						location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class";
					}
				}else{
					if(result.message=="checkNumberNull"){
							alert("验证码为空！");
						}
						else if(result.message=="checkNumberError"){
							alert("验证码错误！");
						}
						else if(result.message=="userNull"){
							alert("此用户不存在！");
						}
						else if(result.message=="passwordError"){
							alert("密码错误！");
						}
						else if(result.message=="userNameNull"){
							alert("用户名为空！");
						}
				}
			},
			error: function(e){
				//alert("登录超时......");
			}
        });
   	});
   	   	
    
    
    
	function changeValidateCode(obj) {     
	    //获取当前的时间作为参数，无具体意义     
	 var timenow = new Date().getTime();     
	    //每次请求需要一个不同的参数，否则可能会返回同样的验证码     
	 //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。     
	 obj.src="${ctx}/login/GetRandomPictureAction.do?d="+timenow;     
	}

    $(function () {
        $('.bxslider').bxSlider({
            auto: true
        });
    });
    

 //***************************************  	
   	// 取消
    $("#closebox").click(function(){
    	$(".paybox").hide();
    	$(".bgc").hide();
    })
    
    // 取消按钮
    $("#closebox2").click(function(){
    	$(".paybox").hide();
    	$(".bgc").hide();
    })
    
    	// 提交支付信息
        $("#paysubmit").click(function(){
        	var cardnumberval = $("#cardnumber").val();
        	var cardpwd = $("#cardpwd").val();
        	var zbcvsetid = $("#zbcvsetid").val();
        	var zbcvid = $("#zbcvid").val();
        	if(!cardnumberval){
        		alert("请输入卡号");
        		return false;
        	}
        	if(!cardpwd){
        		alert("请输入密码");
        		return false;
        	}
        	
         	$.ajax({
				type: 'POST',
				url: "${ctx}/study/logstudy.do?mode=playSubmit&cvsetId="+zbcvsetid+"&cardNumber="+cardnumberval+"&cardPassword="+cardpwd,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'success'){
						//进入直播
						var zbcvid = $("#zbcvid").val();
			        	window.location.href = "${ctx}/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
					}else if(result.message == 'notbind'){
						//已评价 YHQ 2017-02-14 1827
						alert("卡状态不可用或类型错误");
						return false;
					}else if(result.message == 'time'){
						alert("您的学习卡已经过期");
						return false;
					}else if(result.message == 'notfind'){
						alert("输入的卡号或密码错误");
						return false;
					}else if(result.message == 'typeno'){
						alert("卡类型无效");
						return false;
					}else if(result.message == 'alreadyBind'){//添加改卡已经绑过的情况处理--taoliang
						alert("此卡已被用户使用！");
						return false;
					}
				}
	    	});
        })
   	
    //直播入口操作
    function gotoLiveView(id,zhiboType,costType,startDate,endDate) {
    	/* if(zhiboType == 3){
    		alert("课程未完成转码，敬请期待！");
    		return;
    	} */
    	zhiboType = $("#timerCvType"+id).val();
    	if(zhiboType == 2){
    		alert("直播时间为"+startDate.split('.')[0]+" -- "+endDate.split('.')[0]+"，尚未开始！");
    		return;
    	}else if(zhiboType == 3){
			alert("已结束！");
    		return;
		}else if(zhiboType == 4){
			$.ajax({
				type: 'POST',
				url:  ctxJS + '/study/logstudy.do?mode=queryLogin&id='+id,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'noLogin'){
						$("#zbcvid").val(result.id);
						$("#costType").val(costType);//初始化卡类型
						$("#isLive").val("true");
						$(".popup").show();
					}else if(result.message=='success'){
						$("#zbcvid").val(id);//初始化课程ID
						checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
						if(flags ==true){
							window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+id+"&unitId="+0;
						}
					};
				}
		    });
			return;
		}else if(zhiboType == 5){
			alert("直播结束，视频转码中，敬请期待！");
    		return;
		}
    	else{
	    	$.ajax({
					type: 'POST',
					url: '${ctx}/study/logstudy.do?mode=queryLogin&id='+id,
					dataType: 'JSON',
					success : function(data){
						var result = eval(data);
						if(result.message == 'noLogin'){
							$("#zbcvid").val(result.id);
							$("#costType").val(costType);//初始化卡类型
							//弹出登录窗口
							$("#isLive").val("true");
							$(".popup").show();
						}else if(result.message=='success'){
							$.ajax({//根据课程信息拿到项目信息
								type: 'POST',
								url: '${ctx}/BasicSubject.do?mode=queryCVsetByCVId&cvId='+id,
								dataType: 'JSON',
								success : function(data){
									var result = eval(data);
									if(result.message=='success'){
										$("#zbcvid").val(id);
										$("#zbcvsetid").val(result.cvSetId);
										checkbind(result.cvSetId,costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
										if(flags ==true){
	 	 									window.location.href = "${ctx}/viewLiveListInterface.do?mode=getSignk&cvId="+id;
	 	 								}
									}
								}
		    				});
						};
					}
		    });
	    }
    }
    
    //验证项目是否需要付费是否绑卡
   function checkbind(cvSetId,costType){
	   /* if(costType == 1){
	   		$(".popup").hide();
	    	$(".bgc").show();
	    	$(".paybox").show();
			return false;
	  	} */
   		$.ajax({
	    url:"${ctx}/study/logstudy.do?mode=CheckBind&cvsetId="+cvSetId+"&costType="+costType,
	    type: 'POST',
	    async: false,
	    dataType: 'json',
	    success: function(data){   
	    	var result = eval(data);
			if(result.card == '0'){
				flags = true;
			}else{
			   $(".popup").hide();
			   $(".bgc").show();
			   $(".paybox").show();
			   return false;
			}
	    }
	});	 
	   
   }
   
   $(function () {
   		setInterval("timerFun()",1000);
    });
    function timerFun(){
	   $("#timerFreshUL li").each(function(index){
   		var _this = $("#timerFreshUL li");
   		var _cvId = $("#cvId"+index).val();
   		//alert(_cvId);starttime=1499989933,endtime=1499990049
   		$.ajax({
				type: 'POST',
				url: ctxJS + "/viewLiveListInterface.do?mode=getZBType&cvId="+_cvId,
				dataType: 'JSON',
				success : function(result){
					//_this.find("#soon"+index).each(function(index){
						//var soonVal = $(this).text();
						//if(soonVal != null && soonVal != ""){
							if(result.typeInt == '1'){
								$("#timerCvType"+_cvId).val("1");
								_this.find("#soon"+index).text("正在播放");
							}
							if(result.typeInt == '2'){
								$("#timerCvType"+_cvId).val("2");
								_this.find("#soon"+index).text("即将开课");
							}
							if(result.typeInt == '3'){
								$("#timerCvType"+_cvId).val("3");
								_this.find("#soon"+index).text("已结束");
							}
							if(result.typeInt == '4'){
								$("#timerCvType"+_cvId).val("4");
								_this.find("#soon"+index).text("观看回放");
							}
							if(result.typeInt == '5'){
								$("#timerCvType"+_cvId).val("5");
								_this.find("#soon"+index).text("已结束");
							}
						//}
					//})
				}
	    	})	
		});
   }  	
	/* function timerFun(){
    	$("#timerFreshUL li").each(function(index){
			var _timerTimeInputStart = $("#timerTimeInputStart"+index).val();
			var _timerTimeInputEnd = $("#timerTimeInputEnd"+index).val();
			var startDate = new Date((_timerTimeInputStart).replace(/-/g,"/"));
			var endDate = new Date((_timerTimeInputEnd).replace(/-/g,"/"));
			var date = new Date();
			$(this).find(".soon").each(function(index){
				var soonVal = $(this).text();
				if(soonVal != "" && soonVal != "已结束"){
					if(endDate < date){
						$(this).text("已结束");
					}
				}
			})
		})
    }  	 */
</script>