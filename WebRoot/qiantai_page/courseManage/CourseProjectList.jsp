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
<form name="ProjectList" id = "ProjectList" action="${ctx}/ProjectList.do" method="post">
<div class="container">  
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
       <li><br></li><li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">慕课</a></li>
    </ul>
    <input type = "hidden" name = "xueke" id = "xueke" value = "${xueke}"/>
    <input type = "hidden" name = "brand" id = "brand" value = "${brand}"/>
    <input type = "hidden" name = "mode" id = "mode" value = "${mode}"/>
    <input type = "hidden" name = "ppid" id = "ppid" value = "${ppid}"/>
    <input type = "hidden" name = "pname" id = "pname" value = "${pname}"/>
    <div class="filter_cont">
        <ul class="no_hidden">
            <li>学科：</li>
            <li>
                <span>${pname}</span>                 
            </li>
        </ul>
        <ul>
            <li>类型：</li>
            <li class="item_list">
                <span id="sign1" onclick = "javascript:selSign(-1);"  class = "<c:if test="${sign == -1 || sign==null || sign == ''}">active</c:if>">全部</span>
                <span id="sign1" onclick = "javascript:selSign(0);"  class = "zhinan <c:if test="${sign == 0 && sign != ''}">active</c:if>">指南解读</span>
                <span id="sign1" onclick = "javascript:selSign(1);"  class = "gongxu <c:if test="${sign == 1 && sign != ''}">active</c:if>">公需科目</span>
                <input type="hidden" id="sign" name="sign" value = "${sign}"/>
            </li>
        </ul>
        <ul>
            <li>级别：</li>
            <li class="item_list">
                <span id="level0" onclick = "javascript:selLevel(-1);" class="<c:if test="${level == -1}">active</c:if>">全部</span>
                <span id="level1" onclick = "javascript:selLevel(1);" class="<c:if test="${level == 1}">active</c:if>">国家I类</span>
                <span id="level2" onclick = "javascript:selLevel(2);" class="<c:if test="${level == 2}">active</c:if>">省级I类</span>
                <span id="level3" onclick = "javascript:selLevel(3);" class="<c:if test="${level == 3}">active</c:if>">市级I类</span>
                <span id="level4" onclick = "javascript:selLevel(4);" class="<c:if test="${level == 4}">active</c:if>">省级II类</span>
                <span id="level5" onclick = "javascript:selLevel(5);" class="<c:if test="${level == 5}">active</c:if>">市级II类</span>
                <input type="hidden" id="level_h" name="level_h" value = "${level}"/>
                <input type="hidden" id="score_sort" name="score_sort" value="${scoreSort}" />
            	<input type="hidden" id="cost_sort" name="cost_sort" value="${costSort}" /> 
            	<input type="hidden" id="recent_create" name="recent_create" />  
            </li>
        </ul>
    </div>
    <div class="content top_border">
        <div class= "main_cont" >
        <display:table id="item" name="CVSet" requestURI="${ctx}/ProjectList.do"
					decorator="com.hys.exam.displaytag.OverOutWrapper" class = "myTable" excludedParams="msg" pagesize="10">					
				<display:caption>
					<ul class="item_filter">
               			<li class="search">
               			    <input type="text" name="item_name" value="${ItemName}" placeholder="请输入课程名称">
              			     <button name="item_search_btn" type="button"><i class="fa fa-search"></i></button>
            		   </li>
            		   <li onclick="javascript:RecentCreate();" class="new active">最新</li>
             		   <li onclick="javascript:ScoreSort();">学分 <i class="<c:if test="${scoreSort eq 'asc'}">fa fa-angle-up fa-angle-down</c:if> <c:if test="${scoreSort eq 'desc'}">fa fa-angle-up fa-angle-up</c:if>"></i></li>
            		   <li onclick="javascript:CostSort();">价格 <i class="<c:if test="${costSort eq 'asc'}">fa fa-angle-up fa-angle-down</c:if> <c:if test="${costSort eq 'desc'}">fa fa-angle-up fa-angle-up</c:if>"></i></li>
           			 </ul>
				</display:caption>
				<display:column>
					<ul class="item_list">
					<li onclick = "javascript:gotoDetail(${item.id});">
						<div class="item_cover">
							<img src="${ctx}${item.file_path}" />
						</div>	                    
	                    <div class="item_cont">
	                        <h2 class="title"><span>${item.cost}<c:if test = "${item.cost eq 0 || item.cost == null}">0.0</c:if></span>${item.name}</h2>
	                        <div class="info">
	                            <span>类型：${item.type}</span>
	                            <span style = "width:330px;">项目编号：${item.code}</span>
	                            <span>项目负责人：<c:forEach items = "${item.managerList}" var = "manager">${manager.name},</c:forEach></span>
	                            
	                            <span>级别：
		                            <c:if test = "${item.level eq -1}">全部</c:if>
		                            <c:if test = "${item.level eq 1}">国家I类</c:if>
		                            <c:if test = "${item.level eq 2}">省级I类</c:if>
		                            <c:if test = "${item.level eq 3}">市级I类</c:if>
		                            <c:if test = "${item.level eq 4}">省级II类</c:if>
		                            <c:if test = "${item.level eq 5}">市级II类</c:if>
	                            </span>
	                            <span>学分：${item.score}<c:if test = "${item.score eq 0 || item.score == null}">0.0</c:if>分</span>
	                            <span>来源：<c:forEach items = "${item.organizationList}" var = "org">${org.name},</c:forEach></span>
	                        </div>
	                        <div class="foot no_border">
	                            <span>起止时间：
		                            <c:if test = "${item.start_date != null}">
		                            <fmt:formatDate value="${item.start_date}" pattern="yyyy-MM-dd"/>至
									<fmt:formatDate value="${item.end_date}" pattern="yyyy-MM-dd"/>
									</c:if>
								</span>
	                            <span>项目审核人：${item.deli_man}</span>
	                        </div>
	                        <p class="desc">${item.introduction}</p>
	                     </div>
	                     </li>
	                     </ul>
				</display:column>
		</display:table>
        </div>
       <div class="aside cont">
            <h2 class="title"><a href="#" class="more">更多</a>热门推荐</h2>
            <ul class="item_list">
            <% Integer k = 1; %>
               <c:forEach items = "${cvEditionList}" var = "edition">
               		<%if(k <4 ) {%>
	               <li onclick = "javascript:gotoDetail(${edition.id });">
	                    <span class="item_cover" style="background:url(${ctx}${edition.file_path}) no-repeat center;background-size:cover">
	                        <span class="list_id"><%=k%></span>
	                    </span>
	                    <div class="item_cont">
	                        <h2 class="title">${edition.name}</h2>
	                        <p class="desc">${edition.reference}</p>
	                    </div>
	                </li>
	                <%}
	                else if(k <= 10){
	                 %>
	                   <li onclick = "javascript:gotoDetail(${edition.id });"><h2 class="title"><%=k%>.${edition.name}</h2></li>
              		 <%}k++;%>
                
                </c:forEach>
            </ul>
        </div>
    </div>    
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>
</body>
<script>
	var xueke = "${xueke}";
    courseUpdate();
	function courseUpdate()
     {
     	var url = "${ctx}/propManage/getPropListAjax.do";
     	var obj = $(".all");
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		$(result.list).each(function(key, value){
			   			var strSecond = '<span onclick = "javascript:changeClass(this,' + value.id + ');" class = "xueke" name="secondLevel" id ="'+value.id+'">' + value.name + '</span>';
			   			$(obj).after(strSecond);
			   		});
			   		$('.xueke').each(function(key,val){
				     	var id = $(this).prop("id");
				     	if(id == xueke)
				     	{
				     		$(this).addClass("active").siblings().removeClass("active");
				     	}
   		  			});
			   };
		    }
		});
		
     }
     
     function changeClass(obj,id)
     {
   	  	$(obj).addClass("active").siblings().removeClass("active");
   	  	$("#xueke").val(id);         	       	
       	$(ProjectList).submit();
     } 
     function selSign(sign)
    {
        $("#sign").val(sign);         	       	
       	$(ProjectList).submit();
   	}
     $(".xueke_quanbu").click(function () {
   	  	$("#xueke").val("");        	       	
       	$(ProjectList).submit();
     });
     function selLevel(level)
    {
        $("#level_h").val(level);         	       	
       	$(ProjectList).submit();
   	}
    function ScoreSort() {
    	$("#cost_sort").val("");
        var asc = $("#score_sort").val();
   		if(asc == "")
   			asc = "asc";
   		else if(asc == "asc")
   			asc = "desc";
   		else
   			asc = "asc";
   		$("#score_sort").val(asc);
   		$(ProjectList).submit(); 
   	}
   	function CostSort() {
   		$("#score_sort").val("");
   		var asc = $("#cost_sort").val();
   		if(asc == "")
   			asc = "asc";
   		else if(asc == "asc")
   			asc = "desc";
   		else
   			asc = "asc";
   		$("#cost_sort").val(asc);
   		$(ProjectList).submit();  		
   	}
   	function RecentCreate(){
	   	$("#cost_sort").val("");
	   	$("#score_sort").val("");
   		$("#recent_create").val("RC");
   		$(ProjectList).submit();
   	}
   	 function gotoDetail(id) {
	     	location.href = "${ctx}/entityManage/entityView.do?id="+id;
	 }
</script>
</html>
