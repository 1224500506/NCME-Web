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
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">我的学科</a></li>
    </ul>
    
    <div class="content no_padding">
        <h1 class="main_title_2">${prop.name}</h1>
	        <h3>${groupInfo.group.name}</h3>
	        <p style="text-indent:2em">${groupInfo.group.summary}</p>
	        <div class="cont">
	            <h4>&dot; <span class="font_width font_bold">主 任 委 员</span>
		            <c:forEach items = "${groupInfo.expertList}" var = "expertList" >  
		            	<c:if test="${expertList.office == 1}">${expertList.name}&nbsp;&nbsp;</c:if>
		            </c:forEach>
	            </h4>
	            <h4>&dot; <span class="font_width font_bold">副主任委员</span>
		            <c:forEach items = "${groupInfo.expertList}" var = "expertList">  
		            	<c:if test="${expertList.office == 2}">${expertList.name}&nbsp;&nbsp;</c:if>
		            </c:forEach>
	            </h4>
	            <h4>&dot; <span class="font_width font_bold">秘&nbsp;&nbsp;&nbsp;书&nbsp;&nbsp;&nbsp;长</span>
		            <c:forEach items = "${groupInfo.expertList}" var = "expertList">  
		            	<c:if test="${expertList.office == 3}">${expertList.name}&nbsp;&nbsp;</c:if>
		            </c:forEach>
	            </h4>
	            <h4>&dot; <span class="font_width font_bold" style="text-align: justify">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;组</span>
	            	<c:forEach items = "${subGroupInfo}" var = "groupList" varStatus = "groupStatus">
		              	${groupList.group.name}               		
						<c:if test = "${(groupStatus.index + 1) != subGroupInfo.size()}">,</c:if>
		            </c:forEach>
	            </h4>
	        </div>
    </div>
    <div class="content top_border">
        <h1 class="main_title">推荐项目</h1>
            <ul class="item_list item_img_list clearfix">
            
             <c:if test="${fn:length(bindList)==0}">
                 	暂无内容
              </c:if>
             <c:if test="${fn:length(bindList)>0}">
                <c:forEach items = "${bindList}" var = "item">
               
               
                        <li class="bind" value="${item.id}">
	                    <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:cover"></span>
	                    <div class="item_cont">
	                        <h2 class="title">
	                        	<c:if test="${fn:length(item.name)>18}">
						             ${fn:substring(item.name,0,18)}...
						        </c:if>
						        <c:if test="${fn:length(item.name)<=18}">
						        	 ${item.name}
						        </c:if>
	                        </h2>
	                        <div class="info">
	                            <span>项目负责人：
	                            	<c:forEach items = "${item.managerList}" var = "manager" varStatus = "managerStatus">
			                        	${manager.name}	
			                	        <c:if test = "${(managerStatus.index+1) != item.managerList.size()}" >,</c:if>
			                   		</c:forEach>
	                            </span>
	                        </div>
	                        <p class="desc">${item.introduction}</p>
	                        <p>
		                        <c:choose>
	                                <c:when test="${item.level eq 1}">国家I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 2}">省级I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 3}">市级I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 4}">省级II类${item.score}分</c:when>
	                                <c:when test="${item.level eq 5}">市级II类${item.score}分</c:when>
	                            </c:choose>
	                        </p>
	                        <div class="foot no_border">
		                        <c:choose>
	                                <c:when test="${item.cost_type eq 0 || item.cost == null}">
	                                	<span class="font_green">免费</span>
	                                </c:when>
	                                <c:when test="${item.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${item.cost_type eq 2}">
									<!-- <span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
			                            	 ￥${item.cost}
			                            </span>
                                    </c:when>
	                                <c:otherwise>
	                                <span class="font_red">
	                                                                  ￥${item.cost}
	                                </span>
	                                </c:otherwise>
	                            </c:choose>
	                            <span>1${item.studentNum}</span>
	                        </div>
	                    </div>
	                </li>
                
               
                
                
                
	 
	             </c:forEach>
	              </c:if>
            </ul>
        <h1 class="main_title">慕课</h1>
        <ul class="item_list item_img_list clearfix">
         <c:if test="${fn:length(mukeList.list)==0}">
               	暂无内容
               </c:if>
                <c:if test="${fn:length(mukeList.list)>0}">
               <c:forEach items = "${mukeList.list}" var = "item">
              
               
                
                        <li class="bind" value="${item.id}">
	                    <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:cover"></span>
	                    <div class="item_cont">
	                        <h2 class="title">
	                        	<c:if test="${fn:length(item.name)>18}">
						             ${fn:substring($item.name,0,18)}...
						        </c:if>
						        <c:if test="${fn:length(item.name)<=18}">
						        	 ${item.name}
						        </c:if>
	                        </h2>
	                        <div class="info">
	                            <span>项目负责人：
	                            	<c:forEach items = "${item.managerList}" var = "manager" varStatus = "managerStatus">
			                        	${manager.name}	
			                	        <c:if test = "${(managerStatus.index+1) != item.managerList.size()}" >,</c:if>
			                   		</c:forEach>
	                            </span>
	                        </div>
	                        <p class="desc">${item.introduction}</p>
	                        <p>
		                        <c:choose>
	                                <c:when test="${item.level eq 1}">国家I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 2}">省级I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 3}">市级I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 4}">省级II类${item.score}分</c:when>
	                                <c:when test="${item.level eq 5}">市级II类${item.score}分</c:when>
	                            </c:choose>
	                        </p>
	                        <div class="foot no_border">
		                        <c:choose>
	                                <c:when test="${item.cost_type eq 0 || item.cost == null}"><span class="font_green">免费</span></c:when>
	                                <c:when test="${item.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${item.cost_type eq 2}">
									<!-- <span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
			                            	 ￥${item.cost}
			                            </span>
                                    </c:when>
	                                <c:otherwise>
	                                <span class="font_red">
	                                                                  ￥${item.cost}
	                                </span>
	                                </c:otherwise>
	                            </c:choose>
	                            <span>1${item.studentNum}</span>
	                        </div>
	                    </div>
	                </li>
               
	             </c:forEach>
	             </c:if>
            </ul>
        <h1 class="main_title">病例</h1>
            <ul class="item_list item_img_list clearfix">
             <c:if test="${fn:length(bingliList)==0}">
               	暂无内容
               </c:if>
               <c:if test="${fn:length(bingliList)>0}">
            	<c:forEach items = "${bingliList}" var = "item">
            	
            	
              	
                         <li class="bingli" value="${item.id}">
	                    <span class="item_cover" style="background:url('${item.file_path}') no-repeat center;background-size:cover"></span>
	                    <div class="item_cont">
	                       <h2 class="title">
	                        	<c:if test="${fn:length(item.name)>18}">
						             ${fn:substring(item.name,0,18)}...
						        </c:if>
						        <c:if test="${fn:length(item.name)<=18}">
						        	 ${item.name}
						        </c:if>
	                        </h2>
	                        <div class="info">
		                         <div class="info">
		                            <span>项目负责人：
		                            	<c:forEach items = "${item.teacherList}" var = "manager" varStatus = "managerStatus">
				                        	${manager.name}	
				                	        <c:if test = "${(managerStatus.index+1) != item.teacherList.size()}" >,</c:if>
				                   		</c:forEach>
		                            </span>
		                        </div>
	                        </div>
	                        <p class="desc">${item.introduction}</p>
	                    </div>
	                 
	                </li>
              
                </c:forEach>
                 </c:if>
           </ul>
        <h1 class="main_title">指南</h1>
            <ul class="item_list item_img_list clearfix">
             <c:if test="${fn:length(zhinanList.list)==0}">
               	暂无内容
               </c:if>
               <c:if test="${fn:length(zhinanList.list)>0}">
            	<c:forEach items = "${zhinanList.list}" var = "item">
            	
            	
            	
               		   <li class="bind" value="${item.id}">
	                    <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:cover"></span>
	                    <div class="item_cont">
	                        <h2 class="title">
	                        	<c:if test="${fn:length(item.name)>18}">
						             ${fn:substring(item.name,0,18)}...
						        </c:if>
						        <c:if test="${fn:length(item.name)<=18}">
						        	 ${item.name}
						        </c:if>
	                        </h2>
	                        <div class="info">
	                            <span>项目负责人：
	                            	<c:forEach items = "${item.managerList}" var = "manager" varStatus = "managerStatus">
			                        	${manager.name}	
			                	        <c:if test = "${(managerStatus.index+1) != item.managerList.size()}" >,</c:if>
			                   		</c:forEach>
	                            </span>
	                        </div>
	                        <p class="desc">${item.introduction}</p>
	                        <p>
		                        <c:choose>
	                                <c:when test="${item.level eq 1}">国家I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 2}">省级I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 3}">市级I类${item.score}分</c:when>
	                                <c:when test="${item.level eq 4}">省级II类${item.score}分</c:when>
	                                <c:when test="${item.level eq 5}">市级II类${item.score}分</c:when>
	                            </c:choose>
	                        </p>
	                        <div class="foot no_border">
		                        <c:choose>
	                                <c:when test="${item.cost_type eq 0 || item.cost == null}"><span class="font_green">免费</span></c:when>
	                                <c:when test="${item.cost_type eq 1}">
                                    	<span class="font_green" style="float:left;">学习卡项目</span>
                                    </c:when>
                                    <c:when test="${item.cost_type eq 2}">
									<!-- <span class="font_green" style="float:left;">收费</span> -->
                                    	<span class="font_red" style="float:left;">
			                            	 ￥${item.cost}
			                            </span>
                                    </c:when>
	                                <c:otherwise>
	                                <span class="font_red">
	                                                                  ￥${item.cost}
	                                </span>
	                                </c:otherwise>
	                            </c:choose>
	                            <span>1${item.studentNum}次学习</span>
	                        </div>
	                    </div>
	                </li>
               
	             </c:forEach>
	             </c:if>
            </ul>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</body>
<script>
    $(function () {
        $(".item_list li").click(function () {
            window.location.href = '#';
        });
        $(".drop_down a").click(function () {
            $(this).parent().parent().addClass("active").find("i").removeClass("fa-angle-down");
            $(this).parent().parent().siblings().removeClass("active");
        });
        
        /*** 2017-03-13
                                 程鸿业
                                  点击 慕课 ,学科,指南进行跳转
        **/
        // 慕课 ,学科,指南
        $(".bind").click(function(){
        	
        	window.location.href= basepath+"/projectDetail.do?id="+$(this).attr("value");
        	
        })
 
        // 病例
      $(".bingli").click(function(){
    	  
        	window.location.href= basepath+"/entityManage/entityView.do?type=play2&id="+$(this).attr("value")+"&paramType=class&isCv=isCv";
        	
        })
        
        
       
        
    })
</script>
</html>
