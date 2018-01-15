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
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<body>
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
                <li><a href="${ctx}/userInfo/userAbility.do">我的胜任力</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do">我的学习</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
                <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li  class="active"><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>                
                
            </ul>
        </div>
        <div class="main_cont right">
            <div class="tabs">
                <ul class="tab_list">
                    <li class="tab" id="tab1">全部</li>
                    <li class="tab active" id="tab2">系统通知</li>
                   	<!--  讨论通知不显示--> 
                   <!--  <li class="tab" id="tab3">讨论通知</li> -->
                </ul>
                <div class="tab1 tab_cont">
                    <button class="btn btn_gray btn_s btn_read" onclick = "javascript:readAll(0);" id = "readAll">全部设为已读</button>
                    <button class="btn btn_gray btn_s" onclick = "javascript:deleteAll(this,0);">清空所有消息</button>
                    <ul class="info_list">
	                    <c:forEach items = "${messageData}" var = "message">
	                        <li>
	                            <span class="ctrl" ><i class="fa fa-times" onclick = "javascript:deleteMessage(${message.id},1);"></i></span>
	                            <span class="avatar small">
	                            	<c:if test="${admin.user_avatar !=  null}">
					               		<img src="${admin.user_avatar}" onerror="imgonload(this,'${admin.sex}');" id="userImage" name="userImage" />
					               	</c:if>
					               	<c:if test="${admin.user_avatar == null}">
						               <c:if test="${admin.sex == 1}">
						               <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" />
						               </c:if>
						               <c:if test="${admin.sex == 2}">
						               <img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" />
						               </c:if>
					               </c:if>
	                            </span>
	                           
	                            <span>系统管理员</span>
	                            <c:choose>
	                            	<c:when test="${message.is_read == 1}">
	                            		<p> <b>${message.message_content}</b> </p>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<p> ${message.message_content} </p>
	                            	</c:otherwise>
	                            </c:choose>
	                        </li>
	                     </c:forEach>
                    </ul>
                    <c:if test = "${messageData.size() != 0 }">
	                     <display:table id="itemSystemMessage" name="messagePageList" requestURI="${ctx}/userInfo/myMessageManage.do"
							decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" class = "user_set_table" style = "margin:0px 0px;">
			            </display:table>
		            </c:if>
		            <c:if test = "${messageData.size() == 0 }">
		            	暂时还没收到消息~
		            </c:if>
                </div>
                <div class="tab2 tab_cont" style="display:none">
                    <button class="btn btn_gray btn_s btn_read" onclick = "javascript:readAll(1);" id = "readAllSystemMessage">全部设为已读</button>
                    <button class="btn btn_gray btn_s" onclick = "javascript:deleteAll(this,1);">清空所有消息</button>
                    <ul class="info_list">
                        <c:forEach items = "${messageData}" var = "message">
	                        <li>
	                            <span class="ctrl" ><i class="fa fa-times" onclick = "javascript:deleteMessage(${message.id},1);"></i></span>
	                            <span class="avatar small">
	                            <c:if test="${admin.user_avatar !=  null}">
					               		<img src="${admin.user_avatar}" onerror="imgonload(this,'${admin.sex}');" id="userImage" name="userImage" />
					               	</c:if>
					               	<c:if test="${admin.user_avatar == null}">
						               <c:if test="${admin.sex == 1}">
						               <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" />
						               </c:if>
						               <c:if test="${admin.sex == 2}">
						               <img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" />
						               </c:if>
					               </c:if>
					               </span>
	                            <span>系统管理员</span>
	                           <c:choose>
	                            	<c:when test="${message.is_read == 1}">
	                            		<p> <b>${message.message_content}</b> </p>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<p> ${message.message_content} </p>
	                            	</c:otherwise>
	                            </c:choose>
	                        </li>
	                     </c:forEach>
                    </ul>
                    <c:if test = "${messageData.size() != 0 }">
	                     <display:table id="itemSystemMessage" name="messagePageList" requestURI="${ctx}/userInfo/myMessageManage.do"
							decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" class = "user_set_table" style = "margin:0px 0px;">
			            </display:table>
		            </c:if>
		            <c:if test = "${messageData.size() == 0 }">
		            	暂时还没收到消息~
		            </c:if>
                </div>
                <div class="tab3 tab_cont" style="display:none">
                    <button class="btn btn_gray btn_s btn_read" onclick = "javascript:readAll(2);" id = "readAllDiscuss">全部设为已读</button>
                    <button class="btn btn_gray btn_s" onclick = "javascript:deleteAll(this,2);">清空所有消息</button>
                    <ul class="info_list">
                        <li class="unread">
                            <span class="ctrl" ><i class="fa fa-times"></i></span>
                            <span class="avatar small"><img src="${ctx}/qiantai_page/img/user_avatar.jpg"></span>
                            <a href="#">王杰</a>
                            <p>回复了你：同样收获很多！</p>
                            <p><a href="#">小张</a>：我的留言内容</p>
                        </li>
                        <li>
                            <span class="ctrl"><i class="fa fa-times"></i></span>
                            <span class="avatar small"><img src="${ctx}/qiantai_page/img/user_avatar.jpg"></span>
                            <a href="#">王杰</a>
                            <p>回复了你：同样收获很多！</p>
                            <p><a href="#">小张</a>：我的留言内容</p>
                        </li>
                        <li>
                            <span class="ctrl"><i class="fa fa-times"></i></span>
                            <span class="avatar small"><img src="${ctx}/qiantai_page/img/user_avatar.jpg"></span>
                            <a href="#">王杰</a>
                            <p>回复了你：同样收获很多！</p>
                            <p><a href="#">小张</a>：我的留言内容</p>
                        </li>
                    </ul>
                    <ul class="paginate">
                        <li class="first_page">首页</li>
                        <li class="prev">上一页</li>
                        <li class="active">1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>……</li>
                        <li class="next">下一页</li>
                        <li class="last_page">尾页</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
    </div>
</body>
<script>
	var userId = "${userInfo.userId}";
	
	//Read all message.
	function readAll(messageType)
	{
		var messageSize = "${messageData.size()}";
		if(messageSize==0){
			alert("您目前没有任何消息！");
			return false;
		}
		var url="${ctx}/userInfo/myMessageManage.do?method=readAll";
		$.ajax({
			url:url,
			data:"userId="+userId + "&messageType=" + messageType,
			datatype:'text',
			success:function(result){
				if(result == "success")
				{
					alert("操作成功！");
					document.location.reload(true);
				}
				else
				{
					alert("操作失败!");
				}
			}
		});
	}
	//Delete all message.
	function deleteAll(obj, messageType)
	{
		var messageSize = "${messageData.size()}";
		if(messageSize==0){
			alert("您目前没有任何消息！");
			return false;
		}
		if(!confirm("您确定要清空信息列表吗？"))
		{
			return false;
		}
		if($(obj).parent().find('ul').children().length != 0)
		{
			var url="${ctx}/userInfo/myMessageManage.do?method=deleteAll";
			$.ajax({
				url:url,
				data:"userId="+userId + "&messageType=" + messageType,
				datatype:'text',
				success:function(result){
					if(result == "success")
					{
						alert("操作成功！");
						document.location.reload(true);
					}
					else
					{
						alert("操作失败!");
					}
				}
			});
		}		
	}
	
	//Delete messgae.
	function deleteMessage(messageId, messageType)
	{
		if(!confirm("您确定要删除此信息吗？"))
		{
			return false;
		}
		var url="${ctx}/userInfo/myMessageManage.do?method=deleteMessage";
		$.ajax({
			url:url,
			data:"messageId=" + messageId + "&messageType=" + messageType,
			datatype:'text',
			success:function(result){
				if(result == "success")
				{
					alert("操作成功！");
					var active_tab = $("#tab1");
					$('.tab').each(function(key,val){
						if($(this).attr("class") == "tab active")
						{
							active_tab = $(this);
						}
					});
					document.location.reload(true);
					$(active_tab).removeClass();
					$(active_tab).addClass("tab active");
				}
				else
				{
					alert("操作失败!");
				}
			}
		});
	}
</script>
</html>