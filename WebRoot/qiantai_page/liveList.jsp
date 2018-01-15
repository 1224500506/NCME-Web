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
    	
    <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>
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
<div class="bgc"></div>
<form name="caseList" id = "caseList" action="${ctx}/liveList.do" method="post">
<input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
<input type = "hidden" name = "type" id = "type" value = "${type}"/>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <%@include file="/qiantai_page/zhiboPayComm.jsp" %>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">精彩直播</a></li>
    </ul>
    <div class="filter_cont">
        <ul class="no_hidden">
            <li>学科：</li>
            <%@include file="/qiantai_page/xueke.jsp"%>
        </ul>
        <ul>
            <li>类型：</li>
            <li class="item_list">
                <span class="all leixing_quanbu <c:if test = "${type eq null || type eq '' || type eq '全部'}">active</c:if>">全部</span>
                <span class="all leixing_quanbu <c:if test = "${type eq '正在直播'}">active</c:if>" >正在直播</span>
                <span class="all leixing_quanbu <c:if test = "${type eq '未开始'}">active</c:if>">未开始</span>
                <span class="all leixing_quanbu <c:if test = "${type eq '直播回放'}">active</c:if>">直播回放</span>
                <span class="all leixing_quanbu <c:if test = "${type eq '已结束'}">active</c:if>">已结束</span>
            </li>
        </ul>
    </div>
    <div class="content top_border">
     	<ul class="item_list item_img_list" id="timerFreshUL">
                <c:forEach items = "${pager.list}" var = "muke" varStatus="SerialNumber">
                    <li onclick = "javascript:gotoLiveView('${muke.id}','${muke.type}','${muke.cost_type}','${muke.startDate}','${muke.endDate}','${SerialNumber.index}');">
		            <span id="costType${SerialNumber.index}" style="display: none;">${muke.cost_type}</span>
		            <span class="item_cover"
                          style="background:url('${muke.file_path}') no-repeat center;background-size:cover">
                        <div class="item_title">
                        	<input type="hidden" id="cvId${SerialNumber.index}" value="${muke.id}" />
                    		<%-- <span class="ribbon soon" id="soon${SerialNumber.index}">...</span> --%>
                    		
                    		<input type="hidden" id="timerCvType${muke.id}" value="${muke.type}" />
                        	<span id="startDate${SerialNumber.index}" ><fmt:formatDate value="${muke.startDate}"  pattern="MM月dd日"/>
	                        <fmt:formatDate value="${muke.startDate}"  pattern="HH:mm"/></span><span style="color:#fff;">-</span>
	                       <span id = "endDate${SerialNumber.index}" type = "hidden" ><fmt:formatDate  value="${muke.endDate}"  pattern="MM月dd日"/> 
	                        <fmt:formatDate value="${muke.endDate}"  pattern="HH:mm"/></span>
                        </div>
                        <i class="fa fa-play-circle-o"></i>
		            </span>
                        <div class="item_cont">
                            <h2 class="title" style="display: none;" id="name${SerialNumber.index}" title="${muke.name}">${muke.name}</h2> 
                          <p>${muke.name}</p>
                            <div class="foot no_border">
                            	<%-- <c:choose>
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
                                </c:choose> --%>
                                <p style="display: none;" id="introduction${SerialNumber.index}" class="desc topvebanner-p">${muke.introduction}</p>
                                 <span style="display: none;" id="teacher${SerialNumber.index}">授课教师：${muke.teacherList[0].name}</span> 
                               <c:if test = "${muke.type eq 1}">
                    			<span class="ribbon soon" id="soon${SerialNumber.index}" style="float:right;">正在直播</span>
                    			<span style="float:left;">在线人数：${muke.number}</span>
                    		</c:if>
                    		<c:if test = "${muke.type eq 2}">
                    			<span class="ribbon soon" id="soon${SerialNumber.index}" style="float:right;">即将开课</span>
                    			<span style="float:left;">已学人数：200</span>
                    			<%-- <span style="display: none;"id="name2${SerialNumber.index}">${muke.name2}</span>
                    		<span style="display: none;"id="code${SerialNumber.index}">${muke.code}</span> --%>
                    			
                    		</c:if>
                    		<c:if test = "${muke.type eq 3}">
                    			<span class="ribbon soon" id="soon${SerialNumber.index}" style="float:right;">已结束</span>
                    		<span style="float:left;" id = "number3${SerialNumber.index}">已学人数：${muke.number3}</span>
                    		</c:if>
                    		<c:if test = "${muke.type eq 4}">
                    			<span class="ribbon soon" id="soon${SerialNumber.index}" style="float:right;">直播回放</span>
                    			<span style="float:left;" id = "number4${SerialNumber.index}">已看人数：${muke.number4}</span>
                    		</c:if>
                                
                            </div>
                            <div class="foot" style="padding:8px 0 0 0;">
                                
                                <span style="display:block;width:100%;text-align:center;" id="suyu${SerialNumber.index}">${muke.teacherList[0].workUnit}</span>
                                <span style="display: none;"id="name2${SerialNumber.index}">${muke.name2}</span>
                    		<span style="display: none;"id="code${SerialNumber.index}">${muke.code}</span>
                    		 <span style="display: none;" id="mukeid${SerialNumber.index}" >
                            	 ${muke.id}
                            </span>
                    		
                            </div>
                        </div>
                    </li>
                </c:forEach>
             	<c:if test="${pager.list== null || fn:length(pager.list) == 0}">
           		 	暂无内容！
            	</c:if>
            </ul>       
    </div>
        <%@include file="/qiantai_page/bottom.jsp"%>
</div>

<%@include file="/qiantai_page/zhiboLoginComm.jsp" %>
<script src="${ctx}/qiantai_page/js/zhiboComm.js"></script>

<script src="${ctx}/qiantai_page/js/hide.js"></script>
<script>
$(document).ready(function(){
	$(".xueke_quanbu").click(function() {
	    $("#xueke").val($(this).html());
	    $(caseList).submit();
	});
	
	$(".leixing_quanbu").click(function() {
	    $("#type").val($(this).html());
	    $(caseList).submit();
	});
	$(".numberHH").click(function() {
	    $("#cVId").val($(this).html());
	    $(caseList).submit();
	});
	 setInterval("timerFun()",1000);
})
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
						//}
					//})
				}
	    	});	
		 
    }) 	
}

</script>
</form>
</body>
</html>