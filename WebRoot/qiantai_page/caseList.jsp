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
    <script src="${ctx}/qiantai_page/js/drag.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
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
<form name="caseList" id = "caseList" action="${ctx}/caseList.do" method="post">
<input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">典型病例</a></li>
    </ul>
    <div class="filter_cont">
        <ul class="no_hidden">
            <li>学科：</li>
            <%@include file="/qiantai_page/xueke.jsp"%>
        </ul>
    </div>
    <div class="content top_border">
        <ul class="item_list item_img_list">
            <c:forEach items = "${pager.list}" var = "muke">
                <li onclick = "javascript:gotoDetail2(${muke.id});" style="height:265px;">
                    <span class="item_cover" style="<c:if test="${muke.file_path != null && muke.file_path != ''}">background:url(${muke.file_path})</c:if> no-repeat center;background-size:cover;"></span>
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
<!--                                   	<span class="font_green" style="float:left;">收费</span> -->
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
            <c:if test="${pager.list== null || fn:length(pager.list) == 0}">
           		 暂无内容！
            </c:if>
        </ul>
            <%@include file="/commons/page.jsp"%>
    </div>
            <%@include file="/qiantai_page/bottom.jsp"%>
</form>
<div class="popup">
	<form id="loginForm" name="loginForm" action = "${ctx}/loginAjax.do" method="post">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form">
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

</div>
<script src="${ctx}/qiantai_page/js/hide.js"></script>

<script>
//$('#drag').drag();
var flag = false;
$(".xueke_quanbu").click(function() {
    $("#xueke").val($(this).html());
    $(caseList).submit();
});

function gotoDetail2(id) {
	$("#id").val(id);
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
$(".close,button[name=submit]").click(function () {
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
   	 	/* if(!flag)
		{    
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
					//刷新页面
					var id=$('#id').val();
					location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class";
				};
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
</script>
</body>
</html>
