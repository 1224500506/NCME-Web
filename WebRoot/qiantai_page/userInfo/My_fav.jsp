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
                <li><a href="${ctx}/userInfo/userAbility.do">我的胜任力</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do">我的学习</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li  class="active"><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
               <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>
            </ul>
        </div>
        <div class="main_cont right">
            <div class="cont">
                <h2 class="title"><span>我的收藏</span></h2>
                <div class="filter_cont">
                    <ul>
                        <li>项目标签：</li>
                        <li class="item_list">
                            <span id="sign1" onclick = "javascript:selSign(-1);"  class = "<c:if test="${sign == -1 || sign==null}">active</c:if>">全部</span>
			                <span id="sign1" onclick = "javascript:selSign(0);"  class = "zhinan <c:if test="${sign == 0}">active</c:if>">指南解读</span>
			                <span id="sign1" onclick = "javascript:selSign(1);"  class = "gongxu <c:if test="${sign == 1}">active</c:if>">公需科目</span>
			                <input type="hidden" id="sign" name="sign" value = "${sign}"/>
                        </li>
                    </ul>
                </div>
                <c:if test = "${CVSet.size() == 0}">
                   	<div class = "clear"></div>
                   	<center style="margin-top:20px;">没有收藏的内容！</center>
	            </c:if>
	            <c:if test = "${CVSet.size() != 0}">
	                <ul class="item_list list">
	                <c:forEach items="${CVSet}" var="item">
		                   <li>
		                    <div class="item_cover">
								<img src="${item.file_path}" />
							</div>
		                       <div class="item_cont">
		                           <h2 class="title" ><span class="float_right btn_delete" onclick="javascript:delFav(${item.id})"><i class="fa fa-trash-o"></i> 删除</span>${item.name}</h2>
		                           <div class="info1">
		                               <span>学分：${item.score}</span>
		                               <span>级别：
			                            <c:if test = "${item.level eq -1}">全部</c:if>
			                            <c:if test = "${item.level eq 1}">国家I类</c:if>
			                            <c:if test = "${item.level eq 2}">省级I类</c:if>
			                            <c:if test = "${item.level eq 3}">市级I类</c:if>
			                            <c:if test = "${item.level eq 4}">省级II类</c:if>
			                            <c:if test = "${item.level eq 5}">市级II类</c:if>
		                           	</span>
		                               <span>项目编号：${item.code}</span>
		                              <span>项目负责人：
		                               		<c:forEach items = "${item.managerList}" var = "manager" varStatus = "managerStatus">
		                               			${manager.name}	
		                               			<c:if test = "${(managerStatus.index+1) != item.managerList.size()}">,</c:if>
		                               		</c:forEach>
	                              	  </span>
	                              	  <span>项目标签：${item.sign}</span>
	                              	  <span>来源：
		                               		<c:choose> 
					                    		<c:when test="${item.organizationList.size() > 0}">
				                               		<c:forEach items = "${item.organizationList}" var = "org" varStatus = "orgStatus">
				                               			${org.name}
				                               			<c:if test = "${(orgStatus.index + 1) != item.organizationList.size()}">,</c:if>
				                               		</c:forEach>
					                    		</c:when>
					                    		<c:otherwise> 
					                    			中国继续医学教育网
					                    		</c:otherwise>
				                    		</c:choose>
	                               	   </span>
		                               <span>发布时间：
			                                <c:if test = "${item.start_date != null}">
					                            <fmt:formatDate value="${item.start_date}" pattern="yyyy-MM-dd"/>至
					                            <fmt:formatDate value="${item.end_date}" pattern="yyyy-MM-dd"/>
											</c:if>
									   </span>
		                           </div>
		                           <div class="foot no_border">
		                               <span class="font_big font_blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><!-- YHQ 2017-02-15 功能以后实现：已学${item.studyProgress}%，${item.lastUnitName} -->
		                               <span class="text_right"><a href="javascript:void(0);" onclick="continueStudy('${item.id}');" class="btn btn_border blue_border">继续学习</a></span>
		                           </div>
		                       </div>
		                   </li>
	                   </c:forEach>
	                 </ul>
		            <display:table id="item" name="pageCVSet" requestURI="${ctx}/userInfo/userFav.do"
						decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" class = "user_set_table" style = "margin:0px 0px;">
		            </display:table>
		        </c:if>
            </div>	
        </div>
    </div>    
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
<script>
	var userId = "${userInfo.userId}";
	function delFav(id){
		var url="${ctx}/userInfo/userFav.do?method=delFav";
		if(confirm("确定要删除吗?")){
			$.ajax({
			url:url,
			data:"id="+id+"&userId="+userId,
			datatype:'text',
			success:function(result){
			 	document.location.reload(true);
			 	if(result == "success") {
					alert("删除成功!");
					window.location.reload(true);
				} else {
					alert("删除失败!");
				}
			},
		});
		}
	}
	function selSign(sign){
	    $("#sign").val(sign);         	       	
	   	$(Myfav).submit();
	}

    /**
     * @author 张建国
     * @time   2017-01-16
     * @param  cvsetId
     * 说       明：继续学习
     **/
    function continueStudy(cvsetId){
    	location.href = "${ctx}/entityManage/entityView.do?type=play2&id="+cvsetId+"&paramType=project";
    }
    
    /**
     * @author 张建国
     * @time   2017-01-16
     * @param  cvsetId
     * 说      明：删除收藏的项目
     **/
    function delFavoritePro(cvsetId){
    	$.ajax({
			type: 'POST',
			url: '${ctx}/cvset/cvsetFavorites.do?mode=delete&cvsetId='+cvsetId,
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message == 'success'){
					//页面刷新
					location.reload(true);
				};
			}
        });
    };
     
</script>
</html>