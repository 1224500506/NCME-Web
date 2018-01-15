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
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li class="active"><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>                
                
            </ul>
        </div>
        <div class="main_cont right">
            <div class="tabs">
                <ul class="tab_list">
                    <li class="tab active" id="tab1">项目学习报告</li>
                    <li class="tab" id="tab2">年度学习报告</li>
                </ul>
                <div class="tab1 tab_cont">
                <form action="${ctx}/userInfo/studyRecordManage.do" name="fms" method="post">
                    <select name="year" id="year" style="margin-bottom:20px">
                        <option value="0">请选择年</option>
                        <option value="2016" <c:if test = "${year==2016}">selected</c:if>>2016</option>
                        <option value="2017" <c:if test = "${year==2017}">selected</c:if>>2017</option>
                        <option value="2018" <c:if test = "${year==2018}">selected</c:if>>2018</option>
                        <option value="2019" <c:if test = "${year==2019}">selected</c:if>>2019</option>
                        <option value="2020" <c:if test = "${year==2020}">selected</c:if>>2020</option>
                    </select>
                    <select name="month" id="month" style="margin-bottom:20px">
                        <option value="0" >请选择月</option>
                        <option value="1" <c:if test = "${month==1}">selected</c:if>>1</option>
                        <option value="2" <c:if test = "${month==2}">selected</c:if>>2</option>
                        <option value="3" <c:if test = "${month==3}">selected</c:if>>3</option>
                        <option value="4" <c:if test = "${month==4}">selected</c:if>>4</option>
                        <option value="5" <c:if test = "${month==5}">selected</c:if>>5</option>
                        <option value="6" <c:if test = "${month==6}">selected</c:if>>6</option>
                        <option value="7" <c:if test = "${month==7}">selected</c:if>>7</option>
                        <option value="8" <c:if test = "${month==8}">selected</c:if>>8</option>
                        <option value="9" <c:if test = "${month==9}">selected</c:if>>9</option>
                        <option value="10" <c:if test = "${month==10}">selected</c:if>>10</option>
                        <option value="11" <c:if test = "${month==11}">selected</c:if>>11</option>
                        <option value="12" <c:if test = "${month==12}">selected</c:if>>12</option>
                    </select>
                 </form>
                    <ul class="item_list time">
                        <c:forEach items="${pageStudyRecord.list}" var="item">
	              			<li>
	                            <div class="time_line">
	                                <span class="date"> <fmt:formatDate value="${item.last_update_date}" pattern="yyyy"/></span>
	                                <span class="date"><fmt:formatDate value="${item.last_update_date}" pattern="M"/>月<fmt:formatDate value="${item.last_update_date}" pattern="d"/>日</span>
	                                <span class="dot"></span>
	                            </div>
	                            <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:100% auto"></span>
	                            <div class="item_cont">
	                                <h2 class="title"> ${item.name} </h2>
	                                <div class="info1">
	                                	<span>级别：
			                                	
						                            <c:if test = "${item.level eq -1}">全部</c:if>
						                            <c:if test = "${item.level eq 1}">国家I类</c:if>
						                            <c:if test = "${item.level eq 2}">省级I类</c:if>
						                            <c:if test = "${item.level eq 3}">市级I类</c:if>
						                            <c:if test = "${item.level eq 4}">省级II类</c:if>
						                            <c:if test = "${item.level eq 5}">市级II类</c:if>
						                       
						                 </span>
	                                    <span>项目编号:${item.code} </span>
	                                    <span>项目负责人：
		                               		<c:forEach items = "${item.managerList}" var = "manager" varStatus="k">
		                               			${manager.name}
		                               			<c:if test = "${k.index+1 != item.managerList.size()}">,</c:if>
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
	                                    <span class="font_big font_blue">
	                                    	<c:if test="${item.score != 0}"> ${item.score} </c:if>
	                                    	<c:if test="${item.score == 0.0}"> 0 </c:if>分
										</span>
	                                    <span class="text_right"><a href="javascript:viewReport(${item.log_id},${item.cv_set_id});" class="btn btn_border blue_border btn_apply">查看报告</a></span>
	                                </div>
	                            </div>
	                            <div class="bottom_line"></div>
	                        </li>
	              		</c:forEach>
                    </ul>
                     <display:table id="item" name="pageStudyRecord" requestURI="${ctx}/userInfo/studyRecordManage.do"
						decorator="com.hys.exam.displaytag.OverOutWrapper" excludedParams="msg" pagesize="${objectsPerPage}" class = "user_set_table" >
						<display:column property="id"></display:column>
					</display:table>
                </div>
            </div>
    	</div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>    
<script>
    $(function () {
        $(".btn_apply").click(function(){
            $(".popup.credit_tips").show();
        });
        $(".btn_cert,.btn_credit").click(function(){
            $(".popup.cert").show();
        });
        $("button[name=yes],button[name=no],.close").click(function () {
            $(".popup").hide();
        });
         $('#month').change(function(){
        	if($('#year').val() == "0"){
        		alert("请选择年!");
        		$('#month').val("0");
        		return false;
        	}
        	else $(fms).submit();
        });
        $('#year').change(function(){
        	$(fms).submit();
        });
        
        $('#tab2').click(function(){
        	document.location.href="${ctx}/userInfo/studyYearReport.do";
        });
    });

// goto detail page 2017-01-12 han    
/***
 * 程宏业
  2017-02-21 
  点击跳转到项目报告
 */
function viewReport(id,proid){
	
	document.location.href="${ctx}/userInfo/studyReport.do?id="+id+"&proid="+proid;
}

</script>
</html>