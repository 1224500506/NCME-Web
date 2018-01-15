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
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<body>
<form id = "fm1" name = "fm1" method = "post" action = "${ctx}/Ability.do">
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">胜任力</a></li>
    </ul>
    
    <div class="content">
        <h1 class="main_title">我的胜任力 <span class="float_right">${myYiyuan}&nbsp;&nbsp;${myXueKe}&nbsp;&nbsp;${myZhicheng}</span></h1>
        
        <div class="ability_main" style="overflow:hidden;min-height:1000px;">
            <div class="big_circle animated fadeIn ani_1s" style="text-align:left;margin-top:-310px;">岗位<br>胜任力</div>
            <div class="big_circle_line animated flipInY ani_20s" style="margin-top:-410px;"></div>            
            <c:set var="aniNum" value="5" />
            <c:set var="noTwoLevel" value="no_cont" />
            <c:set var="noCommentItem" value="" />
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
	               <c:if test="${abilityLevelOne.level > 0}">
	                  <c:set var="noTwoLevel" value=" " />
	                  <c:set var="noCommentItem" value=" class='yanghongqiang20170221Ajax' " />
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
		                                       <td ${noCommentItem} id="${abilityLevelTwo.id}" style="background:#fff;">${abilityLevelTwo.name}</td>
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
			<div class="comment_item">
                <span class="comment_title">推荐<br>项目</span>
                <div id="level2ProjectDiv"></div>
            </div>                                                                                   
        </div>
        <div class="cont">
            <h2 class="main_title_2">胜任力</h2>
            <p style="text-indent:2em">国际医学教育专家委员会21世纪医学教育展望报告中提出以胜任力为导向的第3代继续医学教育改革，岗位胜任力的概念是：“在日常医疗服务中熟练精准地运用交流沟通能力、学术知识、技术手段、临床思维、情感表达、价值取向和个人体会，以求所服务的个人和群体受益。”</p>
            <p style="text-indent:2em">国家卫生计生委能力建设和继续教育中心接受科教司委托，建立《中国继续医学教育指南》课题研究组，并设立《卫生计生专业技术人员岗位胜任力模型》子课题，致力构建符合中国国情的卫生计生专业技术人员的岗位胜任力通用模型，并形成各自专业的培训指标体系和评价指标体系，从而不断提高继续医学教育效果，提高和改善卫生计生专业技术人员的职业素质和服务能力。经过前期大量的文献研究及对美国ACGME、加拿大RCPSC和中国医科大学孙宝志教授牵头研究构建的“中国临床医生岗位胜任力通用模型标准”的充分借鉴，课题组现已初步建立面向医师、护师、药师、公卫、技师、中医等六类人员的《卫生计生专业技术人员岗位胜任力模型》。在此基础上， 将通过各个学科专家委员会研讨会、进一步深入的调查研究和系统实证，逐步完善和更新岗位胜任力模型各级指标，为中国继续医学教育提供理论指导。</p>
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
        $(".yanghongqiang20170221Ajax").on("mouseover",function () {            
            // 此处可通过ajax附给.comment_item此项学习内容的推荐项目，在show()后面加html内容;            
            $.ajax({
			    url:"${ctx}/Ability.do?mode=level2project&id="+this.id,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
			       if (result.size > 0) {
			          $("#level2ProjectDiv").empty();			                               
                      var l2pDivHtml = "" ;
                      $(result.list).each(function(key, value){
			   			 l2pDivHtml += "<div class='item_list' onclick='javascript:gotoDetail("+value.id+");'>"+value.name+"</div>" ;			   			
			   		  });
			   		  $("#level2ProjectDiv").append(l2pDivHtml);
			       } else {
			          $("#level2ProjectDiv").empty();
			       }
			    }
		    });            
            
            $(".comment_item").show();
        });
    });
    function gotoDetail(id) {
        location.href = "${ctx}/projectDetail.do?id=" + id;
    }
</script>

</body>

</html>