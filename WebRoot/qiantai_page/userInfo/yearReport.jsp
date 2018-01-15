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
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/components-knob-dials.js"></script>
	<script src="${ctx}/qiantai_page/js/jquery.knob.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<script type="text/javascript">

/***
 * 程宏业
 2017-2-23
 判断图片加载是否成功
 */ 

function imgonload(obj,sex){
	if(sex==2){
	$(obj).attr("src","${ctx}/qiantai_page/img/user_avatar.jpg");
	}else{
		$(obj).attr("src","${ctx}/qiantai_page/img/user_avatar1.png");
	}
}


</script>

<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
  <div class="user_cover">
	        <div class="content no_padding">
	            <div class="user_ctrl" style="display:none"><!-- YHQ 2017-02-15 功能以后实现 -->>
	                <a href="javascript:void(0)">
	                    <span>1</span>关注
	                </a>
	                <a href="javascript:void(0)">
	                    <span>3</span>粉丝
	                </a>
	            </div>
	            <div class="user_big_avatar">
               		<c:if test="${userInfo.user_avatar !=  null}">
	               		<img src="${userInfo.user_avatar}" onerror="imgonload(this,'${userInfo.sex}');" id="userImage" name="userImage" />
	               	</c:if>
	               	<c:if test="${userInfo.user_avatar == null}">
		               <c:if test="${userInfo.sex == 1}">
		               <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" />
		               </c:if>
		               <c:if test="${userInfo.sex == 2}">
		               <img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" />
		               </c:if>
	               </c:if>
	            </div>
	            <div class="user_info_list">
	                <h2>${userInfo.realName }</h2>
	                <h3>
	                	${userInfo.profTitle}
	                </h3>
	                <h3>
	                	${userInfo.workUnit}
	           	    </h3>
            	</div>
	        </div>
	    </div>
    <div class="content">
        <div class="left_nav">
            <ul>
                <li><a href="${ctx}/userInfo/userAbility.do">我的胜任力</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do">我的学习</a></li>
                <li id="2tab"><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
                <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li>
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li class="active"><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>                
                
            </ul>
        </div>
        <div class="main_cont right">
            <div class="tabs">
                <ul class="tab_list">
                    <li class="tab" id="tab1">项目学习报告</li>
                    <li class="tab active" id="tab2">年度学习报告</li>
                </ul>
                <div class="tab2 tab_cont">
                <form action="${ctx}/userInfo/studyYearReport.do" name="fms" method="post">
                    <select name="year" id="year">
                        <option value="0">请选择年</option>
                        <option value="2016" <c:if test = "${year==2016}">selected</c:if>>2016</option>
                        <option value="2017" <c:if test = "${year==2017}">selected</c:if>>2017</option>
                        <option value="2018" <c:if test = "${year==2018}">selected</c:if>>2018</option>
                        <option value="2019" <c:if test = "${year==2019}">selected</c:if>>2019</option>
                        <option value="2020" <c:if test = "${year==2020}">selected</c:if>>2020</option>
                    </select>
                    <h1><span class="main_title">年度数据报告</span><span class="main_sub_title">${startDate} 至 ${endDate}</span></h1>
                    <div class="data_report clearfix" style="margin-bottom:30px">
                        <span class="circle1" >
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.points==0}">0</c:if>
                            <c:if test="${stat.points!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.percentage}" /></c:if>" data-fgColor="green"></input>
                            <span class="cont1">学习进度<span>
                            ${stat.percentage}%
                            </span></span>
                        </span>
                         <span class="circle1">
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.points==0}">0</c:if>
                            <c:if test="${stat.points!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.completedLogUnits*100/stat.points}" /></c:if>" data-fgColor="#fe6c6c"></input>
                            <span class="cont1">学习耗时<span>${stat.time_consuming}'</span></span>
                        </span> 
                        <span class="circle1">
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.cvs==0}">0</c:if>
                            <c:if test="${stat.cvs!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.completedCVs*100/stat.cvs}" /></c:if>" data-fgColor="#175fba"></input>
                            <span class="cont1">完成课程<span>${stat.completedCVs}/${stat.cvs}</span></span>
                        </span>
                        <span class="circle1">
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.points==0}">0</c:if>
                            <c:if test="${stat.points!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.completedLogUnits*100/stat.points}" /></c:if>" data-fgColor="#f90"></input>
                            <span class="cont1">完成任务点<span>${stat.completedLogUnits}/${stat.points}</span></span>
                        </span>
                    </div>
                    <h1><span class="main_title">年度岗位胜任力</span><span class="main_sub_title">${startDate} 至 ${endDate}</span></h1>
                    <div class="ability_cont">
                        <div class="bg_circle"></div>
                        <span class="main">岗位<br>胜任力</span>
                        <c:set var="noTwoLevel" value="sub" />
                        <c:if test="${abilityLevelOneList!=null && abilityLevelOneList.size() > 0}">
                            <c:forEach items="${abilityLevelOneList}" var="abilityLevelOne" varStatus="abilityLevelOneStatus">
                                <c:if test="${abilityLevelOne.level > 0}">
			                       <c:set var="noTwoLevel" value="sub active" />
			                    </c:if>
			                    <c:if test="${abilityLevelOne.level <= 0}">
			                       <c:set var="noTwoLevel" value="sub" />
			                    </c:if>
			                    <span class="${noTwoLevel}">
                                    <span class="sub_1"><em class="text"><i class="fa fa-map-marker"></i>${abilityLevelOne.name}</em></span>
                                </span>
                            </c:forEach>
                        </c:if>
                                                
                    </div>
                    <h1><span class="main_title">已完成项目</span><span class="main_sub_title">${startDate} 至 ${endDate}</span></h1>
                    <ul class="item_list item_img_list">
                        <c:forEach items="${pageStudyRecord.list}" var="item">
                        <li>
                            <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:cover"></span>
                            <div class="item_cont">
                                <h2 class="title">${item.name}</h2>
                                <div class="foot no_border">
                                    <a href="javascript:viewReport(${item.log_id},${item.cv_set_id});" class="btn btn_border blue_border">查看项目报告</a>
                                </div>
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                     <display:table id="item" name="pageStudyRecord" requestURI="${ctx}/userInfo/studyYearReport.do"
						decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" pagesize="${objectsPerPage}" class = "user_set_table" >
						<display:column property="id"></display:column>
					</display:table>
				</form>
                </div>
            </div>
    	</div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>    
<script>
    $(function () {
        $(window).scroll(function () {
            if ($(window).scrollTop() > 300){
//                $(".sub:nth-child(1)").animate({display:"block"},500);
                $(".sub:nth-child(3)").animate({left:"50%",right:"auto",margin:"0 0 0 -50px",top:"20px",bottom:"auto"},500);
                $(".sub:nth-child(4)").animate({left:"536px",right:"auto",margin:"0",top:"65px",bottom:"auto"},500);
                $(".sub:nth-child(5)").animate({left:"596px",right:"auto",margin:"0",top:"245px",bottom:"auto"},500);
                $(".sub:nth-child(6)").animate({left:"536px",right:"auto",margin:"0",top:"440px",bottom:"auto"},500);
                $(".sub:nth-child(7)").animate({left:"50%",right:"auto",margin:"0 0 0 -50px",top:"485px",bottom:"auto"},500);
                $(".sub:nth-child(8)").animate({left:"150px",right:"auto",margin:"0",top:"440px",bottom:"auto"},500);
                $(".sub:nth-child(9)").animate({left:"80px",right:"auto",margin:"0",top:"245px",bottom:"auto"},500);
                $(".sub:nth-child(10)").animate({left:"150px",right:"auto",margin:"0",top:"65px",bottom:"auto"},500);
            }
        });

        $(".btn_apply").click(function(){
            $(".popup.credit_tips").show();
        });
        $(".btn_cert,.btn_credit").click(function(){
            $(".popup.cert").show();
        });
        $("button[name=yes],button[name=no],.close").click(function () {
            $(".popup").hide();
        });
        $('#year').change(function(){
        	$(fms).submit();
        });
        
        $('#tab1').click(function(){
        	document.location.href="${ctx}/userInfo/studyRecordManage.do";
        });
        
		ComponentsKnobDials.init();        
    });
    
// goto detail page 2017-01-12 han 

/**
 * 
 	程宏业 2017-02-21
 	点击查看项目报告跳转到相应的页面
 ***/
function viewReport(id,proid){
	
	document.location.href="${ctx}/userInfo/studyReport.do?id="+id+"&proid="+proid;
}
</script>
</html>