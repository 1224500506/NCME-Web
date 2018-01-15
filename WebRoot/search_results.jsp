<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
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
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
</head>
<body>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="#">搜索结果</a> </li>
    </ul>
    <div class="content no_padding">
        <div class="search_keywords">
            <span class="key_w">关键词<span>${Search}</span></span>
            <span>共找到
            	<span>
            		<c:if test = "${(XiangMuPage.fullListSize + pager.fullListSize + ExpertGroupPage.fullListSize) == 0}">
            		0
            		</c:if>
            		<c:if test = "${(XiangMuPage.fullListSize + pager.fullListSize + ExpertGroupPage.fullListSize) != 0}">
            		${XiangMuPage.fullListSize + pager.fullListSize + ExpertGroupPage.fullListSize}
            		</c:if>
            	</span>个相关的内容
            </span>
        </div>
        <div class="tabs">
            <ul class="tab_list">
                <li id="tab1" class="tab active"><a href="javascript:location.href='${ctx}/searchAction.do?search='+encodeURI(encodeURI('${Search}'));">全部</a>
                	<span class="font_red">(
	                	<c:choose>
	                    	<c:when  test = "${(XiangMuPage.fullListSize + pager.fullListSize + ExpertGroupPage.fullListSize) > 0}">
	            			${XiangMuPage.fullListSize + pager.fullListSize + ExpertGroupPage.fullListSize}
	            			</c:when>
	            			<c:otherwise>
	            			0
	            			</c:otherwise>
	            		</c:choose>)
            		</span>
            	</li>
                <li id="tab2" class="tab"><a href="javascript:location.href='${ctx}/searchAction.do?mode=xiangmu&search='+encodeURI(encodeURI('${Search}'));">项目</a>
                	<span class="font_red">(
	                	<c:choose>
	                    	<c:when test = "${XiangMuPage.fullListSize > 0}">
	            			${XiangMuPage.fullListSize}
	            			</c:when>
	            			<c:otherwise>
	            			0
	            			</c:otherwise>
	            		</c:choose>)
            		</span>
            	</li>
                <li id="tab3" class="tab"><a href="javascript:location.href='${ctx}/searchAction.do?mode=mingshi&search='+encodeURI(encodeURI('${Search}'));">名师</a>
                 	<span class="font_red">(
	                	<c:choose>
	                    	<c:when test = "${pager.fullListSize > 0}">
	            			${pager.fullListSize}
	            			</c:when>
	            			<c:otherwise>
	            			0
	            			</c:otherwise>
	            		</c:choose>)
            		</span>
            	</li>
                <!-- <li id="tab4" class="tab"><a href="${ctx}/searchAction.do?search=${Search}&mode=org">机构</a>
                 	<span class="font_red">(
	                	<c:choose>
	                    	<c:when test = "${plOrg.fullListSize > 0}">
	            			${plOrg.fullListSize}
	            			</c:when>
	            			<c:otherwise>
	            			0
	            			</c:otherwise>
	            		</c:choose>)
            		</span>
            	</li> -->
                <li id="tab5" class="tab"><a href="javascript:location.href='${ctx}/searchAction.do?mode=expert&search='+encodeURI(encodeURI('${Search}'));">专委会</a>
                	<span class="font_red">(
                		<c:choose>
	                    	<c:when test = "${ExpertGroupPage.fullListSize > 0}">
	            			${ExpertGroupPage.fullListSize}
	            			</c:when>
	            			<c:otherwise>
	            			0
	            			</c:otherwise>	            		
	            		</c:choose>)
            		</span>
				</li>
            </ul>
            <div class="tab1 tab_cont">
                <h2 class="main_title_3">项目</h2>
                <c:choose>   
                 <c:when test="${XiangMuPage.fullListSize > 0}">   
                <%-- 	<c:when test="${XiangMuPage.list.size()> 0}">          --%>     
		                <ul class="item_list item_img_list">
		                <c:forEach items="${XiangMuPage.list}" var="list">
		                    <li onclick = "javascript:gotoDetail(${list.id});">
		                        <span class="item_cover" style="<c:if test="${list.file_path != null && list.file_path != ''}">background:url(${list.file_path})</c:if> no-repeat center;background-size:cover"></span>
		                        <div class="item_cont">
		                            <h2 class="title">${list.name}</h2>
		                            <p class="desc">${list.introduction}</p>
		                            <p>授课教师：${list.managerList[0].name}</p>
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
		                                    0
		                                	</c:when>
		                                	<c:otherwise>
		                                    	${list.studyTimes}
		                                	</c:otherwise>
		                            	</c:choose>--%>
		                                1${list.studentNum}  次学习 </span>
		                            </div>
		                        </div>
		                    </li>                  
		                    </c:forEach>
		                </ul>
		                <div class="clear"></div>
		                <c:if test="${XiangMuPage.fullListSize > 8}">
		                	<div class="foot search_foot_1"><a href="javascript:void(0);">更多项目搜索结果</a></div>
		                </c:if>
                	</c:when>
                	<c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
        		</c:choose>
                
                <div class="clear"></div>
                
                <h2 class="main_title_3">名师</h2>
                      <c:choose>
                	<c:when test="${pager.fullListSize > 0}">
		                <ul class="teachers_list">
		                	<c:forEach items = "${pager.list}" var ="teacher">
		                    <%-- <li style="background:url(${teacher.photo}) no-repeat center;background-size:100% auto"
		                    	onclick="javascript:viewTeacher(${teacher.id})"> --%>
		                    <li style="background:url(${teacher.photo}) no-repeat center;background-size:100% auto">
		                        <div class="bg_color"></div>
		                        <div class="bg_intro">
		                            <h2><span>${teacher.name}</span></h2>
		                            <h3>
										<c:forEach items="${unitList}" var="bg_intro">
											<c:if test="${unit.id==teacher.job}">${unit.name}</c:if>
										</c:forEach>${teacher.workUnit}
									</h3>
		                            <h3>
										<c:if test="${teacher.workUnit_office==1}">院长</c:if>
										<c:if test="${teacher.workUnit_office==2}">副院长</c:if>
										<c:if test="${teacher.workUnit_office==3}">主任</c:if>
										<c:if test="${teacher.workUnit_office==4}">副主任</c:if>
										<c:if test="${teacher.workUnit_office==5}">所长</c:if>
										<c:if test="${teacher.workUnit_office==6}">副所长</c:if>
										<c:if test="${teacher.workUnit_office==7}">护士长</c:if>
									</h3>
		                        </div>
		                    </li>
		                    </c:forEach>       
		                </ul>
		                <div class="clear"></div>
		                <c:if test="${pager.fullListSize > 10}">
		                	<div class="foot search_foot_2"><a href="javascript:void(0);">更多名师搜索结果</a></div>
		                </c:if>
	            	</c:when>
	            	<c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
        		</c:choose>
                 	
	          	<div class="clear"></div>
	          	
                <!-- <h2 class="main_title_3">机构</h2>
                 <c:choose>
                	<c:when test="${plOrg.fullListSize > 0}">
	                	<div class="org_list">
	                    <div class="logo">
	                        <img src="img/logos/001.jpg">
	                    </div>
	                    <h2>北京协和医院</h2>
	                    <ul class="i_list">
	                        <li>
	                            <span>65</span>
	                            <span>远程项目</span>
	                        </li>
	                        <li>
	                            <span>65</span>
	                            <span>面授项目</span>
	                        </li>
	                        <li>
	                            <span>15</span>
	                            <span>推出名师</span>
	                        </li>
	                    </ul>
	                    <p class="clearfix">机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介机构简介</p>
	                    <ul class="p_list">
	                        <li style="background:url(img/covers/cover_1.jpg) no-repeat center;background-size:cover">
	                            <h3>高血压及合理用药</h3>
	                        </li>
	                        <li style="background:url(img/covers/cover_2.jpg) no-repeat center;background-size:cover">
	                            <h3>高血压及合理用药</h3>
	                        </li>
	                    </ul>
	                </div> 
	                    <div class="clear"></div>
		                <c:if test="${pager.fullListSize > 4}">
		                	<div class="foot search_foot_3"><a href="javascript:void(0);">更多机构搜索结果</a></div>
		                </c:if>
                	</c:when>
                	<c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
                </c:choose> -->
                 
                <!-- <div class="foot search_foot_3"><a href="javascript:void(0);">更多机构搜索结果</a></div> -->
                
                <div class="clear"></div>
                <h2 class="main_title_3">专委会</h2>
                <c:choose>
	                <c:when test="${ExpertGroupPage.fullListSize > 0}">              
		                <ul class="card_list">
		                	<c:forEach items = "${ExpertGroupInfo}" var="p">
			                    <li>
			                        <a href="${ctx}/ExpertGroup.do?mode=detail&id=${p.group.id}"  id="groupList">
			                            <div class="top_cont">
			                                <h2 id = "${p.group.id}">${p.group.name}</h2>
			                            </div>
			                            <div class="user_cont">
			                                <span class="title">主任委员</span>
			                                <c:if test = "${p.expertList != null && p.expertList.size()!=0 }">
			                                <span class="avatar"><img src="${p.expertList.get(0).photo}"></span>
			                                <h2>${p.expertList.get(0).name}</h2>
			                                <h3 class="topvebanner-p" title='${p.expertList.get(0).workUnit}'>
												<c:if test="${fn:length(p.expertList.get(0).workUnit)>15}">
						                          ${fn:substring(p.expertList.get(0).workUnit,0,15)}...
						                        </c:if>
						                        <c:if test="${fn:length(p.expertList.get(0).workUnit)<=15}">
						                          ${p.expertList.get(0).workUnit}
						                        </c:if>
											</h3>
		                                </c:if>
			                            </div>
			                        </a>
			                    </li>
			            	</c:forEach>                    
		                </ul>
		                <div class="clear"></div>
		                <c:if test="${ExpertGroupPage.fullListSize > 8}">
		                	<div class="foot search_foot_4"><a href="javascript:void(0);">更多专委会搜索结果</a></div>
		                </c:if>
		        	</c:when>
		        	<c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
        		</c:choose>
            </div>
            <div class="tab2 tab_cont" style="display:none">
            	<c:choose>
	                <c:when test="${XiangMuPage.fullListSize > 0}">              
	                <ul class="item_list item_img_list">
		                <c:forEach items="${XiangMuPage.list}" var="list">
		                    <li onclick = "javascript:gotoDetail(${list.id});">
		                        <span class="item_cover" style="<c:if test="${list.file_path != null && list.file_path != ''}">background:url(${list.file_path})</c:if> no-repeat center;background-size:cover"></span>
		                        <div class="item_cont">
		                            <h2 class="title">${list.name}</h2>
		                            <p class="desc">${list.introduction}</p>
		                            <p>授课教师：${list.managerList[0].name}</p>
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
		                                    0
		                                	</c:when>
		                                	<c:otherwise>
		                                    	${list.studyTimes}
		                                	</c:otherwise>
		                            	</c:choose> --%>
		                                1${list.studentNum} 次学习 </span>
		                            </div>
		                        </div>
		                    </li>                  
		                </c:forEach>
	                </ul>
		            <div class="clear"></div>
	        		
	        		<display:table name="XiangMuPage" requestURI="${ctx}/searchAction.do?mode=xiangmu" id="p" class="mt10 table"  excludedParams="mode" size="${ExpertGroupPage.fullListSize}" pagesize="${ExpertGroupPage.objectsPerPage}" style="display:none;">
					</display:table>
		            </c:when>
		            
		            <c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
        		</c:choose>
            </div>
            <div class="tab3 tab_cont" style="display:none">
            	<c:choose>
	                <c:when test="${pager.fullListSize > 0}">
		                <ul class="teachers_list">
		                	<c:forEach items = "${pager.list}" var ="teacher">
		                    <li style="background:url(${teacher.photo}) no-repeat center;background-size:100% auto"
		                    	onclick="javascript:viewTeacher(${teacher.id})">
		                        <div class="bg_color"></div>
		                        <div class="bg_intro">
		                            <h2><span>${teacher.name}</span></h2>
		                            <h3>
										<c:forEach items="${unitList}" var="bg_intro">
											<c:if test="${unit.id==teacher.job}">${unit.name}</c:if>
										</c:forEach>${teacher.workUnit}
									</h3>
		                            <h3>
										<c:if test="${teacher.workUnit_office==1}">院长</c:if>
										<c:if test="${teacher.workUnit_office==2}">副院长</c:if>
										<c:if test="${teacher.workUnit_office==3}">主任</c:if>
										<c:if test="${teacher.workUnit_office==4}">副主任</c:if>
										<c:if test="${teacher.workUnit_office==5}">所长</c:if>
										<c:if test="${teacher.workUnit_office==6}">副所长</c:if>
										<c:if test="${teacher.workUnit_office==7}">护士长</c:if>
									</h3>
		                        </div>
		                    </li>
		                    </c:forEach>                 
		                </ul>
		                <div class="clear"></div>
		                <display:table name="pager" requestURI="${ctx}/searchAction.do?mode=mingshi" id="p" class="mt10 table"  excludedParams="mode" size="${pager.fullListSize}" pagesize="${pager.objectsPerPage}" style="display:none;">
						</display:table>              
		            </c:when>
		            <c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
        		</c:choose>
            </div>
             <div class="tab4 tab_cont" style="display:none">
                <c:choose>
                	<c:when test="${plOrg.fullListSize > 0}">
                		<c:forEach items="${plOrg.list}" var="org">
                			<div class="org_list">
		                    <div class="logo">
		                        <img src="${org.photoPath }">
		                    </div>
		                    <h2>${org.name}</h2>
		                    <ul class="i_list">
		                        <li>
		                            <span>65</span>
		                            <span>远程项目</span>
		                        </li>
		                        <li>
		                            <span>65</span>
		                            <span>面授项目</span>
		                        </li>
		                        <li>
		                            <span>15</span>
		                            <span>推出名师</span>
		                        </li>
		                    </ul>
		                    <p class="clearfix">${org.description}</p>
		                    <ul class="p_list">
		                        <li style="background:url(img/covers/cover_1.jpg) no-repeat center;background-size:cover">
		                            <h3>高血压及合理用药</h3>
		                        </li>
		                        <li style="background:url(img/covers/cover_2.jpg) no-repeat center;background-size:cover">
		                            <h3>高血压及合理用药</h3>
		                        </li>
		                    </ul>
		                </div> 
                		</c:forEach>
	                	
	                    <div class="clear"></div>
		                <c:if test="${pager.fullListSize > 4}">
		                	<div class="foot search_foot_3"><a href="javascript:void(0);">更多机构搜索结果</a></div>
		                </c:if>
                	</c:when>
                	<c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
                </c:choose>
            </div>                
            <div class="tab5 tab_cont" style="display:none">
            	<c:choose>               
                	<c:when test="${ExpertGroupPage.fullListSize > 0}">              
	                <ul class="card_list">
	                	<c:forEach items = "${ExpertGroupInfo}" var="p">
		                    <li>
		                        <a href="${ctx}/ExpertGroup.do?mode=detail&id=${p.group.id}" id="groupList">
		                            <div class="top_cont">
		                                <h2 id = "${p.group.id}">${p.group.name}</h2>
		                            </div>
		                            <div class="user_cont">
		                                <span class="title">主任委员</span>
		                                <c:if test = "${p.expertList != null && p.expertList.size()!=0 }">
		                                <span class="avatar"><img src="${p.expertList.get(0).photo}"></span>
		                                <h2>${p.expertList.get(0).name}</h2>
		                                <h3 class="topvebanner-p" title='${p.expertList.get(0).workUnit}'>
											<c:if test="${fn:length(p.expertList.get(0).workUnit)>15}">
					                          ${fn:substring(p.expertList.get(0).workUnit,0,15)}...
					                        </c:if>
					                        <c:if test="${fn:length(p.expertList.get(0).workUnit)<=15}">
					                          ${p.expertList.get(0).workUnit}
					                        </c:if>
										</h3>
	                                </c:if>
		                            </div>
		                        </a>
		                    </li>
		            	</c:forEach>                    
	                </ul>
	                <div class="clear"></div>
	        		
	        		<display:table name="ExpertGroupPage" requestURI="${ctx}/searchAction.do?mode=expert" id="p" class="mt10 table"  excludedParams="mode" size="${ExpertGroupPage.fullListSize}" pagesize="${ExpertGroupPage.objectsPerPage}" style="display:none;">
					</display:table>
        			</c:when>
        			<c:otherwise>
        				<div class="foot">根据条件查询没有结果</div>
        			</c:otherwise>
        		</c:choose>        		
            </div>
        </div>
    </div>    
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<script>
	var current_tab = "${CurrentTab}";
	if (current_tab != "") {
		
		switch (current_tab) {
			case "2": //项目
				$(".tab2").show();
	            $(".tab1,.tab3,.tab4,.tab5").hide();
	            $("#tab2.tab").addClass("active").siblings().removeClass("active");
	            break;
	        case "3": // 名师
	        	$(".tab3").show();
	            $(".tab1,.tab2,.tab4,.tab5").hide();
	            $("#tab3.tab").addClass("active").siblings().removeClass("active");
	        	break;
	        case "4": // 机构
	        	$(".tab4").show();
	            $(".tab1,.tab3,.tab2,.tab5").hide();
	            $("#tab4.tab").addClass("active").siblings().removeClass("active");
	        	break;
 	        case "5": // 专委会
	        	$(".tab5").show();
            	$(".tab1,.tab3,.tab4,.tab2").hide();
           		$("#tab5.tab").addClass("active").siblings().removeClass("active");
	        	break;
	        default:
	        	$(".tab1").show();
            	$(".tab2,.tab3,.tab4,.tab5").hide();
           		$("#tab1.tab").addClass("active").siblings().removeClass("active");
	        	break;	        	        
		}
	}
    $(function(){
        $(".search_foot_1").click(function(){
            $(".tab2").show();
            $(".tab1,.tab3,.tab4,.tab5").hide();
            $("#tab2.tab").addClass("active").siblings().removeClass("active");
        });
        $(".search_foot_2").click(function(){
            $(".tab3").show();
            $(".tab1,.tab2,.tab4,.tab5").hide();
            $("#tab3.tab").addClass("active").siblings().removeClass("active");
        });
        $(".search_foot_3").click(function(){
            $(".tab4").show();
            $(".tab1,.tab3,.tab2,.tab5").hide();
            $("#tab4.tab").addClass("active").siblings().removeClass("active");
        });
        $(".search_foot_4").click(function(){
            $(".tab5").show();
            $(".tab1,.tab3,.tab4,.tab2").hide();
            $("#tab5.tab").addClass("active").siblings().removeClass("active");
        });
        $(".foot").click(function(){
            $('body,html').animate({scrollTop:0},1000);
        });        
        $(".org_list .logo,.org_list h2,.org_list p").click(function () {
            window.open("org_detail.html","new");
        });
    });
    
    // goto Detail Page
    function gotoDetail(id) {
		//location.href = "${ctx}/projectDetail.do?id=" + id;
		window.open("${ctx}/projectDetail.do?id=" + id);
	}
	// goto Detail page of Teacher
	function viewTeacher(id)
    {
    	var url = "${ctx}/teacherManage/teacherDetail.do?mode=view&id=" + id;
    	//document.location.href = url;
    	window.open(url);
    }
</script>
</body>
</html>