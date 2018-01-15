<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
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
    <title>中国继续医学教育网_NCME</title>
    <%@include file="/commons/taglibs.jsp" %>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/rating.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
<script src="${ctx}/qiantai_page/js/drag.js"></script>
<script src="${ctx}/qiantai_page/js/yzm.js"></script>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
<link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css" rel="stylesheet">
</head>
<style type="text/css">
	.DynamicInformation_img{width:575px;height:370px;overflow:hidden;float:left;}
	.DynamicInformation_right{width:555px;float:right;}
	.DynamicInformation_title{width:100%;}
	.DynamicInformation_title h2{margin:10px 0;background:url('${ctx}/qiantai_page/img/TownshipMedical/icon.png') no-repeat 0px 6px;text-indent:12px;float:left;}
	.DynamicInformation_title a{padding-top:16px;display:block;float:right;color:#1498f1;}
	.DynamicInformation_list li{margin-top:30px;font-size:14px;cursor:pointer;}
	.DynamicInformation_list li span{font-size:12px;padding:0px 10px 0px 10px;}
	.container .content .cont ol li, .container .content .cont ul li{line-height:20px;}
	.container .content .item_list.item_img_list li .item_cover{height:160px;}
	.container .content .img_list .item_cont .title, .container .content .item_list li .item_cont .title{height:20px}
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
<body onload='createCode()'>
<div class="bgc"></div>

<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">乡医</a></li>
    </ul>
    <div class="content no_padding">
        <div class="tabs">
            <ul class="tab_list">
                	<c:forEach   items="${xyList}" var="XiangYiProvince" >
                		<c:choose>
                			<c:when test="${code==XiangYiProvince.provinceCode}">
				                <li class="tab active" id="${XiangYiProvince.provinceCode}">${XiangYiProvince.provinceName}</li>
					       </c:when>
					       <c:otherwise>
				                <li class="tab" id="${XiangYiProvince.provinceCode}" onclick = "javascript:gotoDetail2(${XiangYiProvince.provinceCode});">${XiangYiProvince.provinceName}</li>
					       </c:otherwise>
						</c:choose>
                	</c:forEach>
                
            </ul>
        </div>
    </div>
	<div class="content" style="padding:20px 0;">
		<div class="DynamicInformation">
			<div class="DynamicInformation_img">
				<img src="${ctx}/qiantai_page/img/TownshipMedical/details.png" alt="" />
			</div>
			<div class="DynamicInformation_right">
				<div class="DynamicInformation_title">
					<h2>动态资讯</h2>
					<a href="${ctx}/DynamicList.do?code=${code}">更多></a>
				</div>
				<div style="clear:both;"></div>
				<ul class="DynamicInformation_list">
				
						<c:choose>
                			<c:when test="${SentenceList!= null && fn:length(SentenceList) > 6}">
                				<c:forEach items="${SentenceList}" var="Sentence"  begin="0" end="5" step="1">
									<li onclick="window.open('${ctx}/DynamicNotice.do?id=${Sentence.id}','_self')">
										${Sentence.title}
										<span>|</span>
										<c:if test="${Sentence.provinceCode==111111111}">
											全国
										</c:if>
										<c:if test="${Sentence.provinceCode!=111111111}">
											${Sentence.provinceName}
										</c:if>
									</li> 
			                	</c:forEach>
					       </c:when>
                			<c:when test="${SentenceList!= null}">
                				<c:forEach items="${SentenceList}" var="Sentence">
									<li onclick="window.open('${ctx}/DynamicNotice.do?id=${Sentence.id}','_self')">
										${Sentence.title}
										<span>|</span>
										<c:if test="${Sentence.provinceCode==111111111}">
											全国
										</c:if>
										<c:if test="${Sentence.provinceCode!=111111111}">
											${Sentence.provinceName}
										</c:if>
									</li> 
			                	</c:forEach>
					       </c:when>
					       
						</c:choose>
				
				
				</ul>
			</div>
		</div>
	</div>
	<div class="content no_padding">
		<div class="DynamicInformation_title">
			<h2>在线项目</h2>
		</div>
		<div style="clear:both;"></div>
		<div class="content clearfix">
			<ul class="item_list item_img_list">
				
				<c:forEach items="${FirstPageCVSetList}" var ="list">
                <li onclick = "javascript:gotoDetail(${list.id});">
                    <span class="item_cover" style="<c:if test="${list.file_path != null && list.file_path != ''}">background:url(${list.file_path})</c:if> no-repeat center;background-size:cover"></span>
                    <div class="item_cont">
                        <h2 class="title" title="${list.name}">${list.name}</h2>
                        <div class="info">
                            <span>项目负责人：${list.managerList[0].name}</span>
                        </div>
                        <p class="desc topvebanner-p">${list.introduction}</p>
                        <p>
                            <c:choose>
                                <c:when test="${list.level eq 1}">国家I类${list.score}分</c:when>
                                <c:when test="${list.level eq 2}">省级I类${list.score}分</c:when>
                                <c:when test="${list.level eq 3}">市级I类${list.score}分</c:when>
                                <c:when test="${list.level eq 4}">省级II类${list.score}分</c:when>
                                <c:when test="${list.level eq 5}">市级II类${list.score}分</c:when>
                            </c:choose>
                        </p>
                        <div class="foot no_border">
                            <c:choose>
                                    <c:when test="${list.cost_type eq 0 || list.cost == null}">
                                    	<span class="font_green" style="float:left;">免费</span>
                                    </c:when>
                                    <c:when test="${list.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${list.cost_type eq 2}">
<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
	                            	 		￥${list.cost}
	                            		</span>
                                    </c:when>
                                    <c:otherwise>
                            		<span class="font_red" style="float:left;">
                            	 		￥${list.cost}
                            		</span>
                                    </c:otherwise>
                                </c:choose>
                            <span>
                            <%-- <c:choose>
                                <c:when test="${list.studyTimes eq null}">
                                    
                                </c:when>
                                <c:otherwise>
                                    ${list.studyTimes}
                                </c:otherwise>
                            </c:choose> --%>
                            	 1${list.studentNum} 次学习
                            </span>
                        </div>
                    </div>
                </li>
            </c:forEach>
				
			</ul>
		</div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<div class="popup">
	<form id="loginForm" name="loginForm" action = "${ctx}/loginAjax.do" method="post">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form" style="margin: -150px 0 0 -270px">
		        <h2><span class="close" id="closeLogin"><i class="fa fa-times"></i> </span> 用户登录</h2>
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
<input type="hidden" id="h_Pcode">
<script>
    $(function () {
        $(".p_list li:first-child").click(function () {
            window.open("project_detail.html","new");
        });
        $(".p_list li:last-child").click(function () {
            window.open("org_face_detail.html","new");
        });
        $(".org_list .logo,.org_list h2,.org_list p").click(function () {
            window.open("org1/org_detail.html","new");
        });
    });
    function gotoDetail(id) {
		location.href = "${ctx}/projectDetail.do?id=" + id;
	}
	
	//$('#drag').drag();
	var flag = false;
    function gotoDetail2(id) {
		$("#h_Pcode").val(id);
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
						$.ajax({
								type: 'POST',
								url: '${ctx}/UserProvinceCode.do?code='+id,
								dataType: 'JSON',
								success : function(data){
									var result = eval(data);
									if(result.message == 'yes'){
										location.href = "${ctx}/XiangYiIndex.do?code=" + id;
									}
// 									if(result.message == 'no'){
// 										layer.open({
// 												  offset: '200px',
// 												  title: '提示',
// 												  content: '您的所在地不属于本省，无法查看本省信息！',
// 												  btn: ['返回'],
// 													yes: function (index, layero) {
// 														$("#"+id).removeClass("active");
// 														$("#111111111").addClass("active");
// 														layer.close(index);
// 													}
// 												});
// 									};
									if(result.message == 'no'){
										$("#"+id).removeClass("active");
										layer.open({
												  offset: '200px',
												  title: '提示',
												  content: '您的所在地不属于本省，无法查看本省信息！',
												  btn: ['返回'],
													yes: function (index, layero) {
														location.href = "${ctx}/XiangYiIndex.do?code=" + result.pcode;
													}
												});
									};
									if(result.message == 'null'){
										layer.open({
												  offset: '200px',
												  title: '提示',
												  content: '您的所在地不属于本省，无法查看本省信息！',
												  btn: ['返回'],
													yes: function (index, layero) {
														location.href = "${ctx}/XiangYiIndex.do?code=111111111";
													}
												});
									};
								}
					    });
					};
				}
	    });
    }
     $("#closeLogin").click(function () {
     	var id = $("#h_Pcode").val();
     	$("#"+id).removeClass("active");
     	$("#111111111").addClass("active");
        $(".popup").hide();
    });
    $("button[name=submit]").click(function () {
        window.localStorage.setItem("isLogin","true");
    });
    
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
   	 	/* if(!flag) {    
			alert("请完成验证码验证！");
			return;
		}  */
   		//通过AJAX实现登录功能
   		$.ajax({
			type: 'POST',
			url:  '${ctx}/loginAJAX.do',
			data:$("#loginForm").serialize(),
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message="success"){
					var id=$('#h_Pcode').val();
					$(".popup").hide();
					$.ajax({
						type: 'POST',
						url: '${ctx}/UserProvinceCode.do?code='+id,
						dataType: 'JSON',
						success : function(data){
							var result = eval(data);
							if(result.message == 'yes'){
								location.href = "${ctx}/XiangYiIndex.do?code=" + id;
							}
							if(result.message == 'no'){
								$("#"+id).removeClass("active");
								layer.open({
										  offset: '200px',
										  title: '提示',
										  content: '您的所在地不属于本省，无法查看本省信息！',
										  btn: ['返回'],
											yes: function (index, layero) {
												location.href = "${ctx}/XiangYiIndex.do?code=" + result.pcode;
											}
										});
							};
							if(result.message == 'null'){
								layer.open({
										  offset: '200px',
										  title: '提示',
										  content: '您的所在地不属于本省，无法查看本省信息！',
										  btn: ['返回'],
											yes: function (index, layero) {
												location.href = "${ctx}/XiangYiIndex.do?code=111111111";
											}
										});
							};
						}
			   		});
				};
			}
        });
   	}); 
</script>
</body>
</html>                                                                                                                                                                                                            