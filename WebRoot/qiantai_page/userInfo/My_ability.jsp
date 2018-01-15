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
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    
    
</head>


<form name="Myfav" id = "Myfav" action="${ctx}/userInfo/userFav.do" method="post">
<div class="container">  
<%@include file="/qiantai_page/top2.jsp"%>
    <div class="user_cover">
        <div class="content no_padding">
            <div class="user_ctrl" style="display:none"><!-- YHQ 2017-02-15 功能以后实现 -->
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
                <li  class="active"><a href="${ctx}/userInfo/userAbility.do">我的胜任力</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do">我的学习</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
                <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>
            </ul>
        </div>
        
        <div class="main_cont right">
           <div class="cont">
               <h1 class="main_title">我的胜任力 <span class="float_right"><span id="myYiyuan"></span>&nbsp;&nbsp;<span id="myXueKe">${myXueKe}</span>&nbsp;&nbsp;<span id="myZhicheng">${myZhicheng}</span></span></h1>
        
		        <div class="ability_main" style="overflow:hidden;min-height:1000px;">
		            <div class="big_circle animated fadeIn ani_1s" style="text-align:left;margin-top:-310px;">岗位<br>胜任力</div>
		            <div class="big_circle_line animated flipInY ani_20s" style="margin-top:-410px;"></div>            
		            <c:set var="aniNum" value="5" />
		            <c:set var="noTwoLevel" value="no_cont" />
		            <c:if test="${abilityLevelOneList!=null && abilityLevelOneList.size() > 0}">                
			            <c:forEach items="${abilityLevelOneList}" var="abilityLevelOne" varStatus="abilityLevelOneStatus">
			               <c:if test="${abilityLevelOneStatus.count > 1}">
			                  <c:set var="aniNum" value="${abilityLevelOneStatus.count * 2 + 3}" />
			               </c:if> 
			               <c:if test="${abilityLevelOne.subjectPropList!=null && abilityLevelOne.subjectPropList.size() > 0}">
			                  <c:set var="noTwoLevel" value="" />
			               </c:if>
			               <c:if test="${abilityLevelOne.subjectPropList == null || abilityLevelOne.subjectPropList.size() <= 0}">
			                  <c:set var="noTwoLevel" value="no_cont" />
			               </c:if>
			               <c:if test="${abilityLevelOne.level <= 0}">
			                  <c:set var="noTwoLevel" value="no_cont" />
			               </c:if>
			               <div class="circle_item circle_${abilityLevelOneStatus.count} ${noTwoLevel} animated fadeInLeft ani_${aniNum}s"><h3>${abilityLevelOne.name}</h3>
			                  <c:if test="${abilityLevelOne.subjectPropList != null && abilityLevelOne.subjectPropList.size() > 0 }">
				                  <ul>
				                    <li><span></span><span></span></li>
				                    <li>
				                        <table>
				                            <thead>
				                            	<c:forEach items="${abilityLevelOne.subjectPropList}" var="abilityLevelTwo"> 
					                                <tr>
					                                    <td><span></span></td>
					                                </tr>
				                                </c:forEach>
				                            </thead>
				                            <tbody>
				                                <c:forEach items="${abilityLevelOne.subjectPropList}" var="abilityLevelTwo"> 
				                                    <tr>
				                                       <td class="yanghongqiang20170221Ajax" style="background:#fff;" id="${abilityLevelTwo.id}">${abilityLevelTwo.name}</td>
				                                    </tr>
					                            </c:forEach>		                                		                                
				                            </tbody>
				                        </table>
				                    </li>
				                </ul>		                  		                  		                  
			                  </c:if>
			               </div>
						</c:forEach> 
					</c:if>
					                                                                                  
			        </div>
			              
           </div>
        </div>
        
               
    </div>    
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>

<script>
$(function () {
        setTimeout(function(){
            $(".circle_2").find("ul").show("ease");
        },5900);
        $(".circle_item").on("mouseover",function () {
            $(this).find("ul").show("ease").parent().siblings().find("ul").hide("slow");
        });
        
        $("#myYiyuan").text("${myYiyuan}");
}); 


</script>

</body>
</html>