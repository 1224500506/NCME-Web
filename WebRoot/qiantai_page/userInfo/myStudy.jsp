<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.hys.qiantai.model.LogStudyCvSet" %>
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
    <title >中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/rating.js?rev=5cab9c748cf8c51b4937cb8e6cf0d306"></script> 
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/qrcode.js"></script>
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/chakanzhengshu.css" rel="stylesheet" id="css">
    <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">  
    
    <style>
    
  #qrcode{
   
     margin-left: 71px;
    position: relative;
    top: 6px;
    left: -99px;
   
   }
    table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
}
table.gridtable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #dedede;
}
table.gridtable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
    .del{
        font-size: 12px;
    	position: absolute;
    	right: 0px;
    	
    }
    body{font-family:'Lantinghei SC','hiragino sans GB', 'Microsoft YaHei', 'Helvetica Neue', Helvetica, Arial, sans-serif;}
    
    </style>
    
</head>
<body>
<script>

window.onload=function(){
	
		$("#tab1").show();
}

</script>
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
    <div class="user_cover">
        <div class="content no_padding">
            <div class="user_ctrl">
                <a href="javascript:void(0)">
                    <!--<span>1</span>关注-->
                </a>
                <a href="javascript:void(0)">
                    <!--<span>3</span>粉丝-->
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
                <h3 style="margin:20px 0">
                	${userInfo.profTitle}
                </h3>
                <h3 style="margin:20px 0">
                	${userInfo.workUnit}
           	   </h3>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="left_nav">
    		<ul>
                <li><a href="${ctx}/userInfo/userAbility.do">我的胜任力</a></li>
                <li id="1tab" class="active"><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab1">我的学习</a></li>
                <li id="2tab"><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
                <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>
           </ul>
        </div>
        <form name = "myForm" id = "myForm" action = "${ctx}/userInfo/MyStudyManage.do?mode =?" method = "post">
	        <div class="main_cont right">
	            <div class="tabs">
	                <ul class="tab_list">
	                    <li class="tab active" id="tab1"  value = "1">远程项目</li>
	                    <li class="tab " id="tab3" value = "3">面授项目</li>
	                    <li class="tab " id="sendingCourse" >直播课程</li>
	                </ul>
	                <div class="tab1 tab_cont" id="tab11">
	                	 <%-- <select name="state" id = "state" style="margin-bottom:30px">
	                        <option value="0" <c:if test = "${state == 0}">selected</c:if>>全部</option>
	                        <option value="2" <c:if test = "${state == 2}">selected</c:if>>未申请学分</option>
	                        <option value="1" <c:if test = "${state == 1}">selected</c:if>>已申请学分</option>
	                    </select>  --%>
	                    <c:if test = "${data.size() == 0}">
	                    	<div class = "clear"></div>
	                    	<center>没有学习的内容！</center>
	                    </c:if>
	                    <c:if test = "${data.size() != 0}">
		                	<ul class="item_list time">
			              		<c:forEach items="${data}" var="item">
			              			  <li >
			                            <div class="time_line">
											<span class="date">
												<c:if test="${lastUpdateDateYear != item.lastUpdateDateYear}">
													${item.lastUpdateDateYear}
												</c:if>
											</span>
											<c:set var="lastUpdateDateYear" value="${item.lastUpdateDateYear}" />
			                                <span class="date"><fmt:formatDate value="${item.last_update_date}" pattern="MM"/> 月<fmt:formatDate value="${item.last_update_date}" pattern="dd"/> 日</span>
			                                <span class="dot"></span>
			                            </div>
			                            <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:100% auto"></span>
			                            <input type="hidden" id="filePath" value ="${item.file_path}"/>
			                            <div class="item_cont" style="padding-right:100px;position:relative;">
			                                <h2 class="title" onclick = "javascript:gotoDetail(${item.cv_set_id});"> ${item.name}  <a class="del" style="display:none" href="javascript:void(0)" onclick="confirmDel('${item.cv_set_id}');">删除</a> 
			                               
			                                 </h2>
			                         
			                                <div class="info1">
			                              
			                                	<span>级别学分：
			                                	
						                            <c:if test = "${item.level eq -1}">全部</c:if>
						                            <c:if test = "${item.level eq 1}">国家I类</c:if>
						                            <c:if test = "${item.level eq 2}">省级I类</c:if>
						                            <c:if test = "${item.level eq 3}">市级I类</c:if>
						                            <c:if test = "${item.level eq 4}">省级II类</c:if>
						                            <c:if test = "${item.level eq 5}">市级II类</c:if>
						                            <c:if test="${item.score != 0}">${item.score} </c:if>
			                                    	<c:if test="${item.score == 0.0}"> 0 </c:if>分
						                        </span>
			                                    <span>项目编号：
			                                   ${item.code}
			                                     </span>
			                                    <span>项目负责人：
				                               		<c:forEach items = "${item.managerList}" var = "manager" varStatus = "managerStatus">
				                               			${manager.name}	
				                               			<c:if test = "${(managerStatus.index+1) != item.managerList.size()}">,</c:if>
				                               		</c:forEach>
			                              		 </span>
			                              	<%-- 	<span class="font_big font_blue" id="scos">
			                                    <c:if test="${item.score != 0}">学分: ${item.score} </c:if>
			                                    	<c:if test="${item.score == 0.0}"> 0 
			         								</c:if>分 </span> --%>
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
					                                 
							                            <fmt:formatDate value="${item.start_date}" pattern="yyyy-MM-dd"/>
							                           
							                            至
							                            
							                            <fmt:formatDate value="${item.end_date}" pattern="yyyy-MM-dd"/>
							                            
													</c:if>
										  		</span>
										  		
			                                </div>
			                                <div class="foot no_border" >
			                                <span>学习进度 ${item.percentage}%</span>
			                                </div>
			                                <div style="position:absolute;right:0;top:0;padding-top:50px">
			                                	<span class="text_right" style="position:absolute;right:0;top:0;"><a href="javascript:void(0);" class="" style ="color:#000000;border:1px solid #000;border-radius:10px;padding:0 10px;"> 远程</a></span>
			                                     <c:if test="${item.statusStudy > 0 }">
		                                    	   <div class="text_right" style="line-height:30px;"><a>还剩${item.statusStudy }天到期</a></div>
		                                	    </c:if> 
		                                	    <c:if test="${item.statusStudy < 0 || item.statusStudy == 0 }">
		                                    	   <div class="text_right" ><a>状态 : 已过期 </a></div>
		                                	    </c:if> 
			                                    <c:if test="${item.fstate == 2 || item.percentage <100.0 }">
			                                    	<div class="text_right"><a href="javascript:void(0);" class="btn btn_border blue_border btn_study" 
			                                    	onclick="continueStudy('${item.cv_set_id}');" style ="color:#000000;margin-right:0;margin-top:30px;">继续学习</a></div>
			                                	</c:if>
			                                	 <c:if test="${(item.fstate == 3 || item.percentage ==100.0) && item.score >0 }">
			                                	    <c:if test="${item.commentType =='false' }">
			                                			<div class="text_right"><a href="javascript:goToComment('${item.cv_set_id}','${item.name}','${item.level}','${item.score}','${item.start_date }','${item.end_date }');" class="btn btn_border blue_border btn_study"  style ="color:#000000;margin-right:0;margin-top:15px;">项目评价</a></div>
			                                		    <div class="text_right"><a href="javascript:beforeApply('${item.cv_set_id}','${item.name}','${item.level}','${item.score}','${item.start_date }','${item.end_date }');" class="btn btn_border orange_border "  style ="color:#000000;margin-right:0;margin-top:15px;">申请学分</a></div>
			                                		</c:if>
			                                		<c:if test="${item.commentType =='true' }">
				                                		<div class="text_right"><a href="javascript:checkComment('${item.cv_set_id}');" class="btn btn_border blue_border btn_study"  style ="color:#000000;margin-right:0;margin-top:15px;">查看评价</a></div>
				                                		<div class="text_right"><a href="javascript:applyCredit('${item.log_id}','${item.name}','${item.level}','${item.score}','${item.cv_set_id}','${item.code}','${item.start_date}','${item.end_date}','${userInfo.userId}','${userInfo.realName}',
				                                		'${item.apply_date}','${item.commentType}','${userInfo.certificateNo}','${item.sign}','${item.forma}');"  class="btn btn_border orange_border btn_apply" style ="color:#000000;margin-right:0;margin-top:15px;" id="applyscore">申请学分</a></div>
			                                		</c:if>
			                                		
			                                	</c:if>
			                                	 <div id="applytime" style="display: none">
										  		 <fmt:formatDate value="${item.apply_date}"  pattern="yyyy-MM-dd"/>
										  		</div>
										  	</div>
			                            </div>
			                            <div class="bottom_line"></div>
			                        </li>
			              		</c:forEach>
			              	</ul>
						</c:if>
				</div>
			<!-- 直播部分 -->	
			   <div class="tab2 tab_cont" id = "sendingCourseShow" style="display:none"    >	
						<c:if test = "${sending.size() == 0}">
	                    	<div class = "clear"></div>
	                    	<center>没有学习的内容！</center>
	                    </c:if>
	                    <c:if test = "${sending.size() != 0}">
		                	<ul class="item_list time">
			              		<c:forEach items="${sending}" var="send" varStatus ="m">
			              		  <c:forEach items ="${send.cvList}" var="cvList" varStatus="cvListStatus">
			              			<c:if test="${cvList.type != 2 }">
			              			 <li>
			                            <div class="time_line">
											<span class="date">
												<c:if test="${lastUpdateDateYear != send.lastUpdateDateYear}">
													${send.lastUpdateDateYear}
												</c:if>
											</span>
											<c:set var="lastUpdateDateYear" value="${send.lastUpdateDateYear}" />
			                                <span class="date"><fmt:formatDate value="${send.last_update_date}" pattern="MM"/> 月<fmt:formatDate value="${send.last_update_date}" pattern="dd"/> 日</span>
			                                <span class="dot"></span>
			                            </div>
			                            <span class="item_cover" style="background:url(${send.file_path}) no-repeat center;background-size:100% auto"></span>
			                            <div class="item_cont" >
			                              <h2 class="title" onclick="continueStudy('${send.cv_set_id}');"> ${send.name}  <a class="del" style="display:none" href="javascript:void(0)" onclick="confirmDel('${send.cv_set_id}');">删除</a>  </h2>
			                                <div class="info1">
			                                	<span>授课教师： ${teacher.name  }</span>
						                        <span onclick = "javascript:gotoDetail(${send.cv_set_id});">所属项目:  ${send.name}  </span>
			                             
			                                    <span style="width:60%">直播时间：
					                                <c:if test = "${cvList.startDate != null}">
					                                 
							                            <fmt:formatDate value="${cvList.startDate}" pattern="yyyy-MM-dd HH:mm"/>
							                            -
							                            <fmt:formatDate value="${cvList.endDate}" pattern="HH:mm"/>
							                            
													</c:if>
										  		</span>
										  		
			                                </div>
			                                <div class="foot no_border">
			                                   <span style = "color: red;">提醒&nbsp;:&nbsp;直播结束后请在所在的项目中查看学习进度!</span>
			                                </div>
			                                  <div style="position:absolute;right:0;top:0;padding-top:30px">
			                                	<span class="text_right" style="position:absolute;right:0;top:5px;"><a href="javascript:void(0);" class="" style ="color:#000000;border:1px solid #000;border-radius:10px;white-space: nowrap;padding:0 10px;"> 直播</a></span>
		                                        <c:if test="${cvList.type == 0 }">
		                                    	   <div class="text_right"  style="line-height:60px;"><a>状态 : 未开始 </a></div>
		                                    	    <div class="text_right"><a href="javascript:void(0);" class="btn btn_border blue_border btn_study" onclick = "javascript:gotoDetail(${send.cv_set_id});"
			                                    	style ="color:#000000;margin-right:0;margin-top:6px;">即将开课</a></div>
		                                	    </c:if> 
		                                	    <c:if test="${cvList.type == 1 }">
		                                    	   <div class="text_right"  style="line-height:60px;"><a>状态 : 直播中 </a></div>
		                                    	   <div class="text_right"><a href="javascript:void(0);" class="btn btn_border blue_border btn_study" 
			                                    	onclick="continueStudy('${send.cv_set_id}');" style ="color:#000000;margin-right:0;margin-top:6px;">继续学习</a></div>
		                                	    </c:if> 
			                                </div>
			                            </div>
			                            <div class="bottom_line"></div>
			                        </li>
			                        </c:if> 
			                        </c:forEach>
			              		</c:forEach>
			              	</ul>
						</c:if>
	              </div>
	                <!-- 直播部分结束 -->
	               <!--  面授内容部分 -->
	                <div class="tab3 tab_cont" id = "tab33" style="display:none"    >
	             
	                    <c:if test = "${data1.size() == 0}">
	                    	<div class = "clear"></div>
	                    	<center>没有学习的内容！</center>
	                    </c:if>
	                    <c:if test = "${data1.size() != 0}">
		                	<ul class="item_list time">
			              		<c:forEach items="${data1}" var="item1" varStatus="i">
			              		 <c:forEach items ="${item1.faceList}" var="faceList" varStatus="face">
			              			  <li>
			                            <div class="time_line">
											<span class="date">
												<c:if test="${lastUpdateDateYear != item1.lastUpdateDateYear}">
													${item1.lastUpdateDateYear}
												</c:if>
											</span>
											<c:set var="lastUpdateDateYear" value="${item1.lastUpdateDateYear}" />
			                                <span class="date"><fmt:formatDate value="${item1.last_update_date}" pattern="MM"/> 月<fmt:formatDate value="${item1.last_update_date}" pattern="dd"/> 日</span>
			                                <span class="dot"></span>
			                            </div>
			                            <span class="item_cover" style="background:url(${item1.file_path}) no-repeat center;background-size:100% auto"></span>
			                            <div class="item_cont" >
			                              <h2 class="title" onclick = "javascript:gotoFaceDetail(${item1.cv_set_id},${faceList.id });"> ${item1.name}  <a class="del" style="display:none" href="javascript:void(0)" onclick="confirmDel('${item1.cv_set_id}');">删除</a>  </h2>
			                    
			                                <div class="info1">
			                                	<span>级别学分：
			                                	
						                            <c:if test = "${item1.level eq -1}">全部</c:if>
						                            <c:if test = "${item1.level eq 1}">国家I类</c:if>
						                            <c:if test = "${item1.level eq 2}">省级I类</c:if>
						                            <c:if test = "${item1.level eq 3}">市级I类</c:if>
						                            <c:if test = "${item1.level eq 4}">省级II类</c:if>
						                            <c:if test = "${item1.level eq 5}">市级II类</c:if>
							                        <c:if test="${item1.score != 0}"> ${item1.score} </c:if>
				                                    <c:if test="${item1.score == 0.0}"> 0 </c:if>分
						                        </span>
			                                <%--     <span>项目编号：
			                                   ${item1.code}
			                                     </span> --%>
			                                    <span>项目负责人：
				                               		<c:forEach items = "${item1.managerList}" var = "manager" varStatus = "managerStatus">
				                               			${manager.name}	
				                               			<c:if test = "${(managerStatus.index+1) != item.managerList.size()}">,</c:if>
				                               		</c:forEach>
			                              		 </span>
			                              		 
			                              		 <span>项目标签：${item1.sign}</span>
			                              		 <span>来源：
			                               			<c:choose> 
							                    		<c:when test="${item1.organizationList.size() > 0}">
						                               		<c:forEach items = "${item1.organizationList}" var = "org" varStatus = "orgStatus">
						                               			${org.name}
						                               			<c:if test = "${(orgStatus.index + 1) != item1.organizationList.size()}">,</c:if>
						                               		</c:forEach>
							                    		</c:when>
							                    		<c:otherwise> 
							                    			中国继续医学教育网
							                    		</c:otherwise>
						                    		</c:choose>
			                               		</span>
			                                    <span>培训时间：
					                                <c:if test = "${faceList.train_starttime != null}">
					                                 
							                            <fmt:formatDate value="${faceList.train_starttime}" pattern="yyyy-MM-dd"/>
							                           
							                            至
							                            
							                            <fmt:formatDate value="${faceList.train_endtime}" pattern="yyyy-MM-dd"/>
							                            
													</c:if>
										  		</span>
										  		<span>培训地点&nbsp;:&nbsp;${faceList.train_place }</span>
										  		
			                                </div>
			                                <div class="foot no_border">
				                                 <c:if test="${faceList.faceType == 0 || faceList.faceType ==1 }">
				                                   <span style="color:#175fba;">未完成</span>
				                                  </c:if>
				                                   <c:if test="${faceList.faceType == 3 }">
				                                   <span style="color:#175fba;">已完成</span>
				                                  </c:if>
			                                </div>
			                                  <div style="position:absolute;right:0;top:0;padding-top:30px">
			                                	<span class="text_right" style="position:absolute;right:0;top:5px;"><a href="javascript:void(0);" class="" style ="color:#000000;border:1px solid #000;border-radius:10px;padding:0 10px;"> 面授</a></span>
		                                        <c:if test="${faceList.faceType == 0 }">
		                                    	   <div class="text_right"  style="line-height:25px;"><a>状态 : 未开始 </a></div>
		                                	    </c:if> 
		                                	    <c:if test="${faceList.faceType == 1 }">
		                                    	   <div class="text_right"  style="line-height:25px;"><a>状态 : 面授中 </a></div>
		                                	    </c:if> 
		                                	     <c:if test="${faceList.faceType == 2 }">
		                                    	   <div class="text_right"  style="line-height:25px;"><a>状态 : 已结束 </a></div>
		                                	    </c:if> 
			                                     <c:if test="${item1.fstate == 2 || faceList.faceType == 1 }">
			                                    	<div class="text_right"><a href="javascript:void(0);" class="btn btn_border blue_border btn_study" 
			                                    	onclick="continueStudy('${item1.cv_set_id}');" style ="color:#000000;margin-right:0;margin-top:12px;">继续学习</a></div>
			                                	</c:if>
			                                	 <c:if test="${(item1.fstate == 3 || faceList.faceType == 2) && item1.score >0 }">
			                                	 <br />
			                                		<div class="text_right" ><a  onclick="seeFaceTeach('${faceList.id}');" style ="color:blue;margin-top:12px;">查看报名表</a></div>
			                                		<div class="text_right"><a href="javascript:applyCredit('${item1.log_id}','${item1.name}','${item1.level}','${item1.score}','${item1.cv_set_id}','${item1.code}','${item1.start_date}','${item1.end_date}','${userInfo1.userId}','${userInfo1.realName}',
			                                		'${item1.apply_date}','${item.commentType }','${userInfo1.certificateNo}','${item1.sign}','${item1.forma}');"  class="btn btn_border orange_border btn_apply" style ="color:#000000;margin-right:0;margin-top:12px;" id="applyscore">申请学分</a></div>
			                                	</c:if>
			                                	 <span id="applytime" style="display: none">
										  		 <fmt:formatDate value="${item1.apply_date}"  pattern="yyyy-MM-dd"/>
										  		</span>
			                                </div>
			                            </div>
			                            <div class="bottom_line"></div>
			                        </li>
			                        </c:forEach>
			              		</c:forEach>
			              	</ul>

						</c:if>
	                </div>
	               <!--  面授部分结束 -->
    
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<div class="layui-form-item">
	<div class="popup credit_tips">
		<div class="mask"></div>
		<div class="popup_cont normal" style="top:65vh;">
			<h2 style="text-align:center;"><span class="close"><i class="fa fa-times"></i> </span> 申请学分</h2>
			<div class="cont">
				<input type="hidden" id="tipsCVId"/>
				<input type="hidden" id="cvSetId" />
				您当前申请的项目“<span id="tipsCVName"></span>”为<br/><span id="tipsCVLevel"></span><span id="tipsCVScore"></span>分项目，确定申请？
			</div>
			<div class="foot">
				<button name="yes" id="yes" type="button" class="btn btn_blue" >确定</button>
				<button name="no" id="no" type="button" class="btn">取消</button>
			</div>
		</div>
	</div>
</div>
<div class="popup cert">
    <div class="mask"></div>
    <div class="popup_cont cert" style="width: 800px; position: relative; top: 450px;">
        <h2 class="text_center" id="sctitle">学分/证书申请成功！</h2>
        <div class="cont text_center">
        <!--startprint-->
           <div id="createImg"></div>
       <!--endprint-->    
       
       
        </div>
        <div class="foot">
            <button name="no" type="button" class="btn btn_blue">返回</button>
            <button name="OK" type="button" class="btn btn_orange" >打印</button>
        </div>
    </div>
</div>
</body>
<script>
//远程项目和面授项目,直播课程的转换
   $("#tab3").click(function () {
          $("#tab11").hide();
          $("#tab33").show();
          $("#sendingCourseShow").hide();
      });
   $("#tab1").click(function () {
          $("#tab33").hide();
          $("#tab11").show();
          $("#sendingCourseShow").hide();
      });
   $("#sendingCourse").click(function () {
	   $("#sendingCourseShow").show();
       $("#tab33").hide();
       $("#tab11").hide();
   });

var printURL;

//申请的全局变量
// 申请的学分
var applyscore;
// 用户名
var usename;
//编号
var bianhao;
//级别
var jibie;
//开始时间
var ktime;
//结束时间
var jtime;
//身份证ID
var sfid ;
//项目名称
var itemname;
// 申请时间
var applytim;
// 用户ID
var userId ;
// 项目类型
var itemtype;
//授课形式
var formas;

    $(function () {
    	
    	jQuery.fn.center = function () { 
    		this.css("position","absolute"); 
    		this.css("top", ( $(window).height() - this.height() ) / 2+$(window).scrollTop() + "px"); 
    		this.css("left", ( $(window).width() - this.width() ) / 2+$(window).scrollLeft() + "px"); 
    		return this; 
    		}; 
    	
         $(".btn_apply").click(function () {
            $(".popup.credit_tips").show();
        }); 
        $(".btn_cert,.btn_credit").click(function () {
        	$(".popup.cert").center();
            $(".popup.cert").show();
        });
        $("#no").click(function () {
            $(".popup").hide();
            location.reload();
        });
        
       $("button[name=OK]").click(function () {
    	   // 打印证书
    	   window.open(basepath+"/myStudy/DiplomaAction.do?method=findT&diploma_number="+printURL+"&pri","_blank");
        	
        }); 
        
       //打印
       function printdiv() {
           var newstr = document.getElementById("createImg").innerHTML;   //获得需要打印的内容
           // alert(newstr);
           var oldstr = document.body.innerHTML;   //保存原先网页的代码
           document.body.innerHTML = newstr; //将网页内容更改成需要打印
           window.print();
           document.body.innerHTML = oldstr;   //将网页还原
           window.reload();
           return false;
       }

       
        $('#state').change(function () {
            $("#myForm").submit();
        });

        // 换肤
        
        function changeStyle4() {
   		var obj = document.getElementById("css");
   		obj.setAttribute("href","${ctx}/qiantai_page/css/dayinzhengshu.css");
 }
        // 生成证书
        function SubmitZS(){
        	var url =  "${ctx}/myStudy/DiplomaAction.do?method=save&apply_time="+applytim+"&begin_time="+ktime+"&end_time="+jtime+"&id_number="+sfid+"&is_delete=0&item_grade="+jibie+"&item_type="+itemtype+"&path_url=1&system_user_name="+usename+"&system_user_id="+userId+"+&item_number="+bianhao+"&item_name="+itemname+"&item_type="+itemtype+"&item_score="+applyscore;
        	 $.ajax({
                 url: url,
                 type: 'POST',
                 async: false,
                 dataType: 'text',
                 success: function (data) {
                	 
                 },
             });
        	
        }
  
        // save apply info to db. 2017-01-13 han
        $("#yes").click(function () {
        	//提交申请证书的数据
            var url = "${ctx}/userInfo/MyStudyManage.do?mode=applycredit&id=" + $(".popup.credit_tips #tipsCVId").val() + "&cvSetId=" + $(".popup.credit_tips #cvSetId").val();
            $(".popup").hide();
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'text',
                success: function (result) {
                    if (result == "success") {
                        alert("申请成功！");
                        location.reload();
                        SubmitForm();
                        SubmitZS();
                        findscore(userId,bianhao,applyscore,1);
                        $(".popup.cert").show();
                    } else {
                        alert("申请失败，请检查项目是否完成学习！");
                    }
                },
            });
        });
    });

    //display cvset info at messagebox. 2017-01-13 han
    function applyCredit(id, name, level, score, cvSetId,code,starttime,endtime,uid,uname,applytime,uIdcard,itemty,forma) {
    	// 申请的学分
		applyscore = score;
		// 用户名
		usename = uname ;
		//编号
		 bianhao = code ;
		//开始时间
		 ktime = starttime ;
		//结束时间
		 jtime = endtime ;
		//身份证ID
		 sfid = uIdcard  ;
		//项目名称
		 itemname = name;
		// 申请时间
		applytim = applytime;
		// 用户ID
		 userId = uid;
		// 项目类型
		 itemtype = itemty;
	   	if(forma==1){
	   		itemtype="远程";
	   	}else if(forma==2){
	   		itemtype="混合";
	   	}else{
	   		itemtype="面授";
	   	}
   
        $(".popup.credit_tips #tipsCVId").val(id);
        $(".popup.credit_tips #cvSetId").val(cvSetId);
        $(".popup.credit_tips #tipsCVName").text(name);
        var lvlstr = "";
        switch (level) {
        
        case '-1':
            jibie = "全部";
            break;
            case '1':
                lvlstr = "国家I类";
                jibie = "国家I类";
                break;
            case '2':
                lvlstr = "省级I类";
                jibie = "省级I类";
                break;
            case '3':
                lvlstr = "市级I类";
                jibie = "市级I类";
                break;
            case '4':
                lvlstr = "省级II类";
                jibie = "省级II类";
                break;
            case '5':
                lvlstr = "市级II类";
                jibie = "市级II类";
                break;
        }
        $(".popup.credit_tips #tipsCVLevel").text(lvlstr);
        $(".popup.credit_tips #tipsCVScore").text(score);
    }

	
//格式化日期
function toDate(str){
	var arr = new Array();
	if(str.length>0){
		arr = str.split("-");
		return arr[0]+"&nbsp;年"+arr[1]+"&nbsp;月"+arr[2].substring(0,2)+"&nbsp;日";
	}else{
		return "&nbsp;年&nbsp;月&nbsp;日";
	}
	
}
	
//查看学分及证书
function findscore(userid,code,score,title){
	if(title==1){
		
		$("#sctitle").html("欢迎查看学分");
	}
	 $("#createImg").empty();
	var url = "${ctx}/myStudy/DiplomaAction.do?method=find&system_user_id="+userid+"+&item_number="+code;
	 $.ajax({
         url: url,
         type: 'POST',
         dataType: 'text',
         async: false,
         success: function (data) {
        	 var obj = eval('(' + data + ')'); 
        	 if(obj.item_grade =="undefined"){
        		 obj.item_grade ="全部";
        	 }
     		 $("#createImg").append(
     				"<div class='center'>"+
     				"<div class='center1'>"+
     					" <h1 class='h1'>学分证书</h1>"+
     					" <p class='p'>"+
     						" 姓名<span style='padding:0px 10px 0px 10px;'>"+obj.system_user_name+"</span>身份证号<span style='padding:0px 10px 0px 10px;'>"+obj.id_number+"</span>于<span style='padding:0px 10px 0px 10px;'>"+toDate(obj.begin_time)+"</span>"+
     						"至<span style='padding:0px 10px 0px 10px;'>"+toDate(obj.end_time)+"</span>参加继续医学教育项目（面授&nbsp;<input type='checkbox' id='ms' />，"+
     						"远程教育&nbsp;<input type='checkbox' id='yc'  />，混合&nbsp; <input type='checkbox' id='hh'  />）[项目编号：<span style='padding:0px 10px 0px 10px;'>"+obj.item_number+"</span>][项目名称：<span style='padding:0px 10px 0px 10px;'>"+obj.item_name+"</span>]。经考核合格，特授予&nbsp;"+obj.item_grade+""+
     						"<span style='padding:0px 10px 0px 10px;'></span> 继续医学教育学分<span style='padding:0px 10px 0px 10px'>"+score+"</span>分。"+
     					"</p>"+
     					"<div class='zhengshu'>"+
     						"<p style='float:left;'>证书编号：<span>"+obj.diploma_number+"</span></p>"+
     						"<p style='float:right;'>国家卫生计生委能力建设和继续教育中心</p>"+
     					"</div>"+
     					"<div style='clear:both;'></div>"+
     					"<div class='zhengshu'>"+
     					"	<p style='float:left;width:70px;height:70px;margin-left:50px;'></p>"+
     					"	<p style='float:right;padding-top:20px;' ><span style='padding:0px 20px 0px 20px;'>"+toDate(obj.apply_time)+"</span></p>"+
     					"<div id ='qrcode'></div>"+
     					"<span class='bj'></span>"+
     					"</div>"+
     				"</div>"+
     			"</div>"
     		);
     		 
     		printURL =  obj.diploma_number;
     		var qrcode = new QRCode(document.getElementById("qrcode"), {
     		 
                width : 90,//设置宽高
                height : 90
            });
     		
            qrcode.makeCode(basepath+"/myStudy/DiplomaAction.do?method=findT&diploma_number="+obj.diploma_number);
            
     		if(obj.item_type="远程"){
        		$("#yc").attr("checked","checked");
        		
        	}else if(obj.item_type="面授"){
        		$("#ms").attr("checked","checked");
        	}else {
        		$("#hh").attr("checked","checked");
        	}
     		
     		
         },
	 });
	
	
};
/**
 * 程宏业
 2017-02-21
 提交表单
 *
 *
 ***/
function SubmitForm(){
	$.ajax({  
        type: "POST",  
        url:"${ctx}/userInfo/MyStudyManage.do",  
        data:$('#myForm').serialize(),// 序列化表单值  
        async: false,  
        success: function(data) {  
        }  
    });  
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

    /****
    删除学习记录
2017-04-01
程宏叶
    **/
 
    function confirmDel(id) {
     if(confirm('确定要删除吗!')) {
         $.ajax({
             url: "${ctx}/userInfo/MyStudyManage.do?mode=del&cv_set_id="+id,
             type: 'POST',
             dataType: 'text',
             success: function () {
            	 location.href="${ctx}/userInfo/MyStudyManage.do";
             },
         });
        
    } else {
    	return false;
    }
     
    
  }
    //项目详情
    function gotoDetail(id) {
		location.href = "${ctx}/projectDetail.do?id=" + id;
	}
   //面授项目详情
	function gotoFaceDetail(cvSetId,fid) {
		location.href = "${ctx}/projectDetail.do?id=" + cvSetId+"&fid="+fid;
	}
   /*  查看面授报名表 */

 function seeFaceTeach(id){ 
	 $.ajax({
			type: 'POST',
			url:  "${ctx}/userInfo/MyStudyManage.do?mode=face&id="+id,
			dataType: 'JSON',
			async: false,
			success : function(data){
				 var result = eval(data);
				   var sex ;
				   if (result.user.sex == 1) {
				      sex = "男"; 
				   } else {
					   sex = "女";
				   }
				   var certificateType;
				   if (result.user.certificateType ==1 ) {
					   certificateType="身份证号";
				   }else{
					   certificateType="军官证号";
				   }
					
			    	var faceTeach = "<div style='padding: 22px; line-height: 22px;' ><div><h2 class='title' style='text-align: center;font-size:22px;padding-bottom: 25px;'><span>面授项目报名表</span></h2>"
				    +"<table class='gridtable' >"
				    +"<tr><th>报名编号</th><th colspan='3'>"+
						result.cvSet.name + result.faceteach.class_name + result.faceteach.id
						+"</th></tr><tr><th>姓名</th><th>"+
						result.user.realName 
						+"</th><th>性别</th><th>"+sex
						+"</th></tr><tr><th>"+certificateType
							    +"</th><th>"+
						    result.user.certificateNo
							    +"</th><th>手机号</th><th>"+
						    result.user.mobilPhone 
							    +"</th></tr><tr> <th>单位名称</th><th>"+
							    result.user.workUnit 
							    +"</th><th>单位地址</th><th>"+
							    result.user.contactAddress 
							    +"</th></tr><tr><th>学科</th><th>"+
							    result.exam3.name +">"+ result.exam2.name  +">"+result.exam3.name 
							    +"</th><th>项目名称</th><th>"+
							    result.cvSet.name
							    +"</th></tr><tr><th>培训期数</th><th>"+
							    result.faceteach.class_name
							    +"</th><th>培训时间</th><th>&nbsp;&nbsp;&nbsp;&nbsp;"+
							    toDate(result.trainStartDate)+"<br>至 "+toDate(result.trainEndDate)
							    +"</th></tr><tr><th>培训地址</th><th>"+
						result.faceteach.train_place
							    +"</th><th>培训费用</th><th>"+
						result.cvSet.cost 
							    +"元（请在线下缴费)</th></tr></table></div>"; 
							    
					layer.open({
						    type: 1
						    ,title: "面授项目报名表" //不显示标题栏
						    ,closeBtn: false
						    ,area: ["800px;","550px"]
						    ,shade: 1
						    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
						    ,resize: false
						    ,btn: ['打印', '取消']
						    ,btnAlign: 'c'
						    ,moveType: 1 //拖拽模式，0或者1
						    ,content: faceTeach 
						    ,success: function(layero){
						      var btn = layero.find('.layui-layer-btn');
						      btn.find('.layui-layer-btn0').attr({
						        href:'${ctx}/entityManage/faceEntry.do?method=printFaceEntry&fid='+id,// 打印面授报名
						   /*      ,target: '_blank' */
						      });
						    }
						  });				
			}
 });
};
   var filePath = $("#filePath").val();
<%--弹出评价的内容--%>
function goToComment(cvSetId,name,level,score,startDate,endDate){ 

	var lvlstr = "";
	switch (level) {
	case '-1':
	    jibie = "全部";
	    break;
	    case '1':
	        lvlstr = "国家I类";
	        jibie = "国家I类";
	        break;
	    case '2':
	        lvlstr = "省级I类";
	        jibie = "省级I类";
	        break;
	    case '3':
	        lvlstr = "市级I类";
	        jibie = "市级I类";
	        break;
	    case '4':
	        lvlstr = "省级II类";
	        jibie = "省级II类";
	        break;
	    case '5':
	        lvlstr = "市级II类";
	        jibie = "市级II类";
	        break;
	}
	 $.ajax({
			type: 'POST',
			url:  "${ctx}/userInfo/MyStudyManage.do?mode=comment&cvSetId="+cvSetId,
			dataType: 'JSON',
			async: false,
			success : function(data){
				 var result = eval(data);
			     comment= "<div class='comment_starts' style='margin-left:85px;'>"+
			                 "<div>"+  
								 "<div  style='margin-top:30px;text-align: center;font-size: 20px;'>项目评价 </div>"+
								 "<div style ='margin-top:25px'>"+
								            "<div class='item_cover' style='display:inline-block;background:url("+result.cvSet.file_path+") no-repeat center;background-size:100% auto;width:132px;height:88px;float: left;'></div>"+
									        "<div style='float:left;margin-top:15px;margin-left: 25px;'>"+
											      "<div class='item_cont' style='padding-right:100px;position:relative;'>"+
											          "<h2 class='title'> "+result.cvSet.name+ "</h2>"+
											          "<div>"+toDate(result.startDate)+"-"+toDate(result.endDate)+"</div>"+
												      "<div>"+lvlstr+"&nbsp;&nbsp;"+result.cvSet.score+"学分 </div>"+
										           "</div>"+
								             "</div>"+
							      "</div>"+
							   "</div>"+  
						   "<div style ='margin-top:135px'>"+
							  "<div class='stars'>"+
							      "<div  style='float:left;'>本项目内容是否符合您的实际需求</div>"+
							      "<div class='star-rating-1' style='float:left;margin-left:62px;'>"+
							         " <input type='radio' name='example' class='rating' value='1'>"+
							          "<input type='radio' name='example' class='rating' value='2'>"+
							          "<input type='radio' name='example' class='rating' value='3'>"+
							          "<input type='radio' name='example' class='rating' value='4'>"+
							          "<input type='radio' name='example' class='rating' value='5'>"+
							      "</div>"+
							      "<input type='hidden' class='val' val=''>"+
							     " <div class='star_tip'></div>"+
							  "</div>"+
							  "<div class='stars'>"+
							      "<div  style='float:left;'>本项目内容在您临床工作中的使用程度</div>"+
							      "<div class='star-rating-2' style='float:left;margin-left:29px;'>"+
							          "<input type='radio' name='example' class='rating' value='1'>"+
							         " <input type='radio' name='example' class='rating' value='2'>"+
							          "<input type='radio' name='example' class='rating' value='3'>"+
							          "<input type='radio' name='example' class='rating' value='4'>"+
							          "<input type='radio' name='example' class='rating' value='5'>"+
							      "</div>"+
							      "<input type='hidden' class='val' val=''>"+
							      "<div class='star_tip'></div>"+
							  "</div>"+
							  "<div class='stars'>"+
							      "<div  style='float:left;'>您对本项目的教学形式是否满意</div>"+
							      "<div class='star-rating-3' style='float:left;margin-left:77px;'>"+
							         " <input type='radio' name='example' class='rating' value='1'>"+
							          "<input type='radio' name='example' class='rating' value='2'>"+
							          "<input type='radio' name='example' class='rating' value='3'>"+
							          "<input type='radio' name='example' class='rating' value='4'>"+
							          "<input type='radio' name='example' class='rating' value='5'>"+
							      "</div>"+
							      "<input type='hidden' class='val' val=''>"+
							      "<div class='star_tip'></div>"+
							 " </div>"+
							  "<div class='stars'>"+
							      "<div  style='float:left;'>您对本项目的教学形式是否满意</div>"+
							      "<div class='star-rating-4' style='float:left;margin-left:77px;'>"+
							          "<input type='radio' name='example' class='rating' value='1'>"+
							          "<input type='radio' name='example' class='rating' value='2'>"+
							          "<input type='radio' name='example' class='rating' value='3'>"+
							          "<input type='radio' name='example' class='rating' value='4'>"+
							          "<input type='radio' name='example' class='rating' value='5'>"+
							      "</div>"+
							      "<input type='hidden' class='val' val=''>"+
							     " <div class='star_tip' ></div>"+
							 " </div>"+
							
									"<div style ='margin-top: 15px;' >"+
									  "<textarea rows='7' cols='90' name='comment_cont' id ='comment_cont' class='comment_cont'></textarea>"+
									 "</div>"+
								"</div>"+  
						    "</div>";	
					  layer.open({
						     type: 1
						    ,title: false //不显示标题栏
						    ,closeBtn: false
						    ,area: ["800px;","480px"]
						    ,shade: 0.8
						    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
						    ,resize: false
						    ,btn: ['提交', '取消']
						    ,btn1:function(){
						    	score1 = $('.star-rating-1 .fullStar').length;
								score2 = $('.star-rating-2 .fullStar').length;
								score3 = $('.star-rating-3 .fullStar').length;
								score4 = $('.star-rating-4 .fullStar').length;
								flag = true;
								if($('.comment_cont').val().length>400){   //长度可自定义
							        alert("请输入不超过200字评价内容！");
							    	 return false;
							    }
								var commentCont = $('.comment_cont').val();
								 $.ajax({
										type: 'POST',
										url:  "${ctx}/userInfo/MyStudyManage.do?mode=saveComment&cvSetId="+cvSetId+"&score1="+score1+
						        		"&score2="+score2+"&score3="+score3+"&score4="+score4+"&commentCont="+commentCont,
										dataType: 'JSON',
										async: false,
										success : function(data){
										 var result = eval(data);
										
										 if(data.success=="success"){
												alert("保存成功！")
											}else{
											    alert("网络超时，稍后再试！")
											}
										 window.parent.location.reload();  //刷新父页面
										 parent.layer.close(index);   //关闭 弹框
										}
								 });
						    }
						    ,btnAlign: 'c'
						    ,moveType: 1 //拖拽模式，0或者1
						    ,content: comment
						    ,success: function(layero){
									var score1 = 0;
									var score2 = 0;
									var score3 = 0;
									var score4 = 0;
						    	 $(".star-rating-1").rating(function(vote, event){
						    	         score1 = vote;
									    if (vote == 1){
									        $(".star-rating-1").parent().find(".star_tip").text("脱离实际需求");
									    }
									    if (vote == 2){
									        $(".star-rating-1").parent().find(".star_tip").text("不太符合实际需求");
									    }
									    if (vote == 3){
									        $(".star-rating-1").parent().find(".star_tip").text("基本符合实际需求");
									    }
									    if (vote == 4){
									        $(".star-rating-1").parent().find(".star_tip").text("较为符合实际需求");
									    }
									    if (vote == 5){
									        $(".star-rating-1").parent().find(".star_tip").text("完全符合实际需求");
									    }
									});
									$(".star-rating-2").rating(function(vote, event){
										score2 = vote;
										if (vote == 1){
									        $(".star-rating-2").parent().find(".star_tip").text("非专业相关的知识/技能");
									    }
									    if (vote == 2){
									        $(".star-rating-2").parent().find(".star_tip").text("应该了解的知识/技能");
									    }
									    if (vote == 3){
									        $(".star-rating-2").parent().find(".star_tip").text("应该熟悉的知识/技能");
									    }
									    if (vote == 4){
									        $(".star-rating-2").parent().find(".star_tip").text("应该掌握的知识/技能");
									    }
									    if (vote == 5){
									        $(".star-rating-2").parent().find(".star_tip").text("熟练掌握的知识/技能");
									    }
									});
									$(".star-rating-3").rating(function(vote, event){
										if (vote == 1){
									        $(".star-rating-3").parent().find(".star_tip").text("不满意");score3 = 1;
									    }
									    if (vote == 2){
									        $(".star-rating-3").parent().find(".star_tip").text("一般");score3 = 2;
									    }
									    if (vote == 3){
									        $(".star-rating-3").parent().find(".star_tip").text("基本满意");score3 = 3;
									    }
									    if (vote == 4){
									        $(".star-rating-3").parent().find(".star_tip").text("满意");score3 = 4;
									    }
									    if (vote == 5){
									        $(".star-rating-3").parent().find(".star_tip").text("非常满意");score3 = 5;
									    }
									});
									$(".star-rating-4").rating(function(vote, event){
										if (vote == 1){
									        $(".star-rating-4").parent().find(".star_tip").text("不满意");score4 = 1;
									    }
									    if (vote == 2){
									        $(".star-rating-4").parent().find(".star_tip").text("一般");score4 = 2;
									    }
									    if (vote == 3){
									        $(".star-rating-4").parent().find(".star_tip").text("基本满意");score4 = 3;
									    }
									    if (vote == 4){
									        $(".star-rating-4").parent().find(".star_tip").text("满意");score4 = 4;
									    }
									    if (vote == 5){
									        $(".star-rating-4").parent().find(".star_tip").text("非常满意");score4 = 5;
									    }
									});
					
						      var btn = layero.find('.layui-layer-btn');
						    }
						  
						  });
				}
			});
						 
	}; 
	<%--查看评价--%>
	function checkComment(cvSetId){
		 var comment = "${ctx}/userInfo/MyStudyManage.do?mode=checkComment&cvSetId="+cvSetId;
		 layer.open({
			  type: 2,
			  title: "",
			  skin: 'layui-layer-demo', //样式类名
			  closeBtn: 2, //不显示关闭按钮
			  area: ['800px', '480px'],
       	      fixed: true, //不固定
			  anim: 2,
			  shadeClose: true, //开启遮罩关闭
			  content: comment
			});
	}
	<%--申请学分前未评价--%>
	function beforeApply(cvSetId,name,level,score,startDate,endDate){
		 var  apply = "<div style ='margin-top: 85px; margin-left: 77px;'>请先完成评价后，再进行学分申请！</div>";
         layer.open({
        	  type: 1
        	  ,title: false //不显示标题栏
        	  ,closeBtn: false
        	  ,area: ['420px', '220px']
        	  ,shade: 0.9
        	  ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        	  ,resize: false
        	  ,btn: ['确定', '取消']
        	  ,btnAlign: 'c'
        	  ,moveType: 1 //拖拽模式，0或者1
        	  ,content: apply
        	  ,yes: function(){
        		  layer.closeAll();
        		  goToComment(cvSetId,name,level,score,startDate,endDate)
        	  }
        	});
	
	}

</script>

</html>