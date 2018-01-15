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
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
    <script src="${ctx}/qiantai_page/js/rating.js?rev=5cab9c748cf8c51b4937cb8e6cf0d306"></script> 
   
     <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
     <script src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script> 
     <script src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script> 
    <script src="${ctx}/qiantai_page/js/main.min.js?rev=222fe8abc0fda3427a0f94eaf2939390"></script> 
    <link href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561" rel="stylesheet"> 
    
     <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">  
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
     <script src="${ctx}/qiantai_page/js/drag.js"></script>
     <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet"> 
    <%-- <link href="${ctx}/qiantai_page/css/iconfont/iconfont.css" rel="stylesheet">  --%> 
     
     <link href="${ctx}/qiantai_page/css/test/iconfont.css" rel="stylesheet">
     <link href="${ctx}/qiantai_page/css/zhiboicon/iconfont.css" rel="stylesheet">
     <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
     <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>

<style type="text/css">

.prompt{
	 display: none; 
    text-align: center;
    background:rgba(204, 204, 204, 0.53);
    width: 300px;
    height: 150px;
    position: absolute;
    top: 70px;
    left: 45%;
    z-index: 99;
    
}

.prompt .spn{
display:block;
margin: 60 auto;

}

.paybox{
    padding: 20px;
    border-radius: 10px;
    background-color: #fff;
    position: fixed;
    top: 50vh;
    left: 50%;
    z-index: 999999;
    width: 500px;
    margin: -200px 0 0 -270px;
    display: none; 


}

/* .paybox span{
padding: 0px 4px 0px 4px;
background: rgb(221,221,221);
float: right;
color:white;
border-radius: 6px;
border: solid 1px white;
cursor: pointer;

} */

.paybox .joinform #paysubmit{
    border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;
    background-color: #f90;
    color: #fff;

}


.paybox .joinform #closebox2{
     border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;
    border:1px solid #ccc;
    background-color: #fff;
    color: #615858;

}




.paybox .joinform{
    margin: 20px auto;
    width: 80%;
}

.paybox h2{
	font-size: 18px;
    border-bottom: 1px solid #12bce1;
    margin-top: 0;
}



.joinform .input_div input[type="text"],input[type='password']{
    padding: 5px;
    background: #fff;
    color: #333;
    box-sizing: border-box;
    width: 100%;
    border: 1px solid #666;
    font-size: 14px;
    border-radius: 7px;
}


#labcard{
font-weight: bold;
}

#labpwd{
font-weight: bold;
}

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
</style>
      
</head>


<body onload='createCode()'>

<div class="bgc"></div>
<div class="container">
    <%@include file="/qiantai_page/top2.jsp"%>
    
<div class="prompt">
<span class="spn">收藏成功</span>

</div>
<input type="hidden" id="zbcvid" value="" />
<input type="hidden" id="zbcvsetid" value="" /> 
<input type="hidden" id="costType" value="" />
<div class="paybox" >
 <h2><span class="close" id="closebox" style="float: right;" ><i class="fa fa-times" style="color: rgb(204,204,204);"></i></span> 添加学习卡 </h2>
 <div class="joinform">
<div class="input_div">
 <label id="labcard">输入学习卡卡号<input type="text" id="cardnumber" maxlength="20"></label>
 <label id="labpwd">输入学习卡密码<input type="password" id="cardpwd" maxlength="20"></label>
<br/>
<input type="button" value="提交" id="paysubmit"/>
<input type="button" value="取消" id="closebox2"/>
</div> 
 </div>
</div>

    
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <!-- <li><a href="project_list.html">分类名称<a> <i class="fa fa-angle-right"></i></a></a></li> -->
        <li><a href="#" id="cvSetName">${cvSet.name}</a></li>
    </ul>
    <ul class="project_info">
        <li>
            <h1 class="p_title">
                <span class="right_icon">
                    <!-- 屏蔽分享 -->
                    <!--<i class="fa fa-share-alt"></i>-->
                    <i class="like fa fa-heart fa-heart-o" id="favorites" onclick="favorite(this);"></i>
                   
                    <b></b>
                </span>
                ${cvSet.name}
                <input type= "hidden" name = "cvSetId" value ="${cvSet.id}"/>
            </h1>
            <h3 class="p_sub_title">学科：
            <c:forEach items="${cvSet.examPropList}" var="list">
                ${list.name} &nbsp;
            </c:forEach>
            </h3>
            <span class="item_cover" style="<c:if test="${cvSet.file_path != null}">background:url(${cvSet.file_path})</c:if> no-repeat center;background-size:cover"></span>
            <div class="item_cont">
            
            <!--
                <h2 class="title">
                    <span style=" position: absolute; margin-left: 362px">综合评分：
                    <c:choose>
                        <c:when test="${cvSet.grade != null}">
                            ${cvSet.grade}
                        </c:when>
                        <c:otherwise>
                            暂无评分
                        </c:otherwise>
                    </c:choose>
                    </span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-login"></use>
                    </svg>
                </h2>
             -->
                <div class="info">
                	<div style="float:left;overflow:hidden;width:360px;">
                		<span style="width:360px;color: #12bce1;font-weight:bold;">综合评分：
		                    <c:choose>
		                        <c:when test="${cvSet.grade != null}">
		                            ${cvSet.grade}
		                        </c:when>
		                        <c:otherwise>
		                           	 暂无评分
		                        </c:otherwise>
		                    </c:choose>
	                    </span>
	                    <span style="width:360px;display:none;">类型：${cvSet.sign}</span>
	                    <span style="width:360px;">项目负责人：${cvSet.managerList[0].name}</span>
	                    <span style="width:360px;">学分：${cvSet.score}分</span>
	                    <span style="width:360px;">起止时间：<fmt:formatDate value="${cvSet.start_date}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${cvSet.end_date}" pattern="yyyy-MM-dd"/></span>
	                   <c:if test="${cvSet.forma == 3 }">
		                   <span style="width:360px;">标签：${cvSet.sign}</span>
		                   <input type = "hidden" id ="cvSetCost" value = "${cvSet.cost}" />
		                   <span style="width:360px;">期数：
		                   <c:forEach items="${faceTeach}" var="list">
							<c:if test="${list.faceType ==3 || list.faceType ==2}">
								<input disabled="disabled" type="button" data-listId ="${list.id }" value ="${list.class_name}"/>
							</c:if>
		                    <c:if test="${list.faceType ==1}">
		                    	<input type="button" data-listId ="${list.id }" class="btyn" value ="${list.class_name}" />
		                    </c:if>
		                   </c:forEach>
		                   </span>
		               </c:if>
                 	</div>
                 	<div style="float:left;overflow:hidden;width:360px;">
	                    <span style="width:360px;">&nbsp;&nbsp;&nbsp;</span>
	                    <span style="width:360px;">项目编号：${cvSet.code}</span>
	                    <span style="width:360px;">级别：
	                        <c:choose>
	                            <c:when test="${cvSet.level eq 1}">国家I类</c:when>
	                            <c:when test="${cvSet.level eq 2}">省级I类</c:when>
	                            <c:when test="${cvSet.level eq 3}">市级I类</c:when>
	                            <c:when test="${cvSet.level eq 4}">省级II类</c:when>
	                            <c:when test="${cvSet.level eq 5}">市级II类</c:when>
	                        	<c:when test="${cvSet.level eq 6}">无学分</c:when>
	                        </c:choose>
	                    </span>
	                    <span style="width:360px;">来源：
	                    	<c:choose> 
	                    		<c:when test="${cvSet.organizationList[0].name.length() > 0}">
	                    			${cvSet.organizationList[0].name}
	                    		</c:when>
	                    		<c:otherwise> 
	                    			中国继续医学教育网
	                    		</c:otherwise>
                    		</c:choose>
                    	</span>
                    	<c:if test="${cvSet.forma == 3 }">
		                   <span style="width:360px;">项目审核人：${cvSet.deli_man}</span>
		               </c:if>
                 	</div>
            	</div>
            </div>
            <div class="bottom">
              <c:if test = "${cvSet.forma == 1}">
                <div class="big_num" style="float:left;margin-top:15px;">
                    <c:choose>
                        <c:when test="${cvSet.cost_type eq 0 || cvSet.cost == null}">
                        	<span class="font_green" style="float:left;">免费</span>
                        </c:when>
                        <c:when test="${cvSet.cost_type eq 1}">
                        	<span class="font_green" style="float:left;">学习卡项目</span>
                        </c:when>
                        <c:when test="${cvSet.cost_type eq 2}">
<!--                         	<span class="font_green" style="float:left;">收费</span> -->
	                        <span class="font_red"> ￥${cvSet.cost}</span>
                        </c:when>
                        <c:otherwise>
                        <span class="font_red"> ￥${cvSet.cost}</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <p style="float:left;margin-left:300px;">
                   
	                	<input type="hidden" id="logCVSetStatus" value="${logCVSetStatus}"/>
	                    <!-- 判断单元学习情况 -->
	                    <c:if test = "${logCVSetStatus == 0}">
	                    	<button type="button" name="study_begin" class="btn btn_blue btn_big">开始学习</button>
	                    </c:if>
	                    <c:if test = "${logCVSetStatus == 1}">
	                    	<button type="button" name="study_begin" class="btn btn_blue btn_big">继续学习</button>
	                    </c:if>
	                    <c:if test = "${logCVSetStatus == 2}"> 
	                    	<button type="button" name="study_begin" class="btn btn_blue btn_big">已学完</button>
	                    </c:if>
                   
                </p>
               </c:if>
              
               <c:if test = "${cvSet.forma == 3}">
              <%--  <span class="font_red" style="float:left;"  value="${cvSet.cost }" id ="cvSetCost"> ${cvSet.cost } </span> --%>
	                    <!-- 判断单元学习情况 -->
	                       <c:if test = "${fid == 0}" >
	                         <button type="button" name="entry" class="btn btn_blue btn_big">报名学习</button>
	                       </c:if>
	                       <c:if test = "${fid != 0}" >
		                        <c:if test = "${logCVSetStatus == 0}">
		                    	    <button type="button"  class="btn btn_blue btn_big">即将开课</button>
		                    	</c:if>
	                    	    <c:if test = "${logCVSetStatus == 1}">
	                    	        <button type="button" name="study_begin" class="btn btn_blue btn_big">继续学习</button>
			                    </c:if>
			                    <c:if test = "${logCVSetStatus == 2}"> 
			                    	<button type="button"  class="btn btn_blue btn_big">已学完</button>
			                    </c:if>
	                       </c:if>
	                    </c:if>
	                   
            </div>
        </li>
    </ul>
    <div class="content top_border">
        <div class="main_cont">
            <div class="cont">
                <h2 class="title"><span>学习须知</span></h2>
                <ul>
                    <li>${cvSet.knowledge_needed}</li>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>项目简介</span></h2>
                <p style="text-indent:2em">${cvSet.introduction}</p>
            </div>
          <c:if test = "${cvSet.forma == 3}">
            <div class="cont">
                 <h2 class="title"><span>培训期数</span></h2>
                 <table class="gridtable">
					<tr>
					    <th width="10%">培训期数</th>
					    <th width="25%">培训时间</th>
					    <th width="10%">上课时间</th>
					    <th width="20%">培训地点</th>
					    <th width="20%">乘车路线</th>
					    <th width="15%">联系方式</th>
					</tr>
					
				<c:forEach items="${faceTeach}" var="list">
			 		<tr>
                        <td id="faceName">${list.class_name}</td>
                        <td id="${list.id}">
                        	<fmt:formatDate value="${list.train_starttime}" pattern="yyyy-MM-dd"/> 
                                                                  至 <fmt:formatDate value="${list.train_endtime}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>${list.lession_starttime}<br />${list.lession_endtime}</td> 
                        <td>${list.train_place}</td>
                        <td>${list.route_way}</td>
                        <td>${list.contact_way}</td>
					</tr> 
				</c:forEach>
				</table>
			</div>
          </c:if>
            <div class="cont">
                <h2 class="title"><span>项目负责人介绍</span></h2>
                <div class="main_user_info">
                        <span class="user_avatar">
                        <c:choose>
                            <c:when test="${cvSet.managerList[0].photo !=null && cvSet.managerList[0].photo != ''}">
                                <img src="${cvSet.managerList[0].photo}">
                            </c:when>
                            <c:otherwise>
                                <img src="${ctx}/qiantai_page/img/2.png">
                            </c:otherwise>
                        </c:choose>
                        </span>

                        <p class="user_name">${cvSet.managerList[0].name}</p>
                        <p class="user_cont">
                            ${cvSet.managerList[0].summary}
                        </p>
                </div>
            </div>
            <div class="cont">
                <h2 class="title"><span>授课教师</span></h2>
                <ul class="teachers">
                    <c:forEach items="${cvSet.teacherList}" var ="list">
                        <li class="user_info">
                            <span class="avatar">
                            <c:choose>
                                <c:when test="${list.photo !=null && list.photo != ''}">
                                    <img src="${list.photo}">
                                </c:when>
                                <c:otherwise>
                                    <img src="${ctx}/qiantai_page/img/2.png">
                                </c:otherwise>
                            </c:choose>                            
                            </span>
                            <p class="user_name">${list.name}</p>
                            <p class="user_cont">${list.jobName}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>书籍推荐</span></h2>
                <ul>
                <c:forEach items="${cvSet.refereeBookList}" var="book">
                	<li><a href="${book.url}">${book.name}</a></li>
                </c:forEach>
                    
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>指南共识</span></h2>
                <ul>
	                <c:forEach items="${cvSet.knowledgeBaseList}" var="knowledge">
	                	<li><a href="${knowledge.url}">${knowledge.name}</a></li>
	                </c:forEach>
                    
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>最新文献</span></h2>
                <ul>
	                <c:forEach items="${cvSet.referencelatelyList}" var="referencelately">
	                	<li><a href="${referencelately.url}">${referencelately.name}</a></li>
	                </c:forEach>                    
                </ul>
            </div>
            <div class="cont">
                <h2 class="title"><span>项目评价</span></h2>
                <div class="comment_top">
                    <span>综合评分：${cvSet.grade}</span>
                    <span class="go_comment" style="cursor:pointer"><i class="fa fa-edit"></i> 去评价 </span>
                </div>
                <div class="comment_starts" style="display:none">
                    <div class="tips"><i class="fa fa-info-circle"></i> 温馨提醒：用鼠标点击星星就能打分了，打分后不可修改。</div>
                    <div class="stars">
                        <div>内容实用</div>
                        <div class="star-rating-1">
                            <input type="radio" name="example" class="rating" value="1">
                            <input type="radio" name="example" class="rating" value="2">
                            <input type="radio" name="example" class="rating" value="3">
                            <input type="radio" name="example" class="rating" value="4">
                            <input type="radio" name="example" class="rating" value="5">
                        </div>
                        <div class="star_tip"></div>
                    </div>
                    <div class="stars">
                        <div>简洁易懂</div>
                        <div class="star-rating-2">
                            <input type="radio" name="example" class="rating" value="1">
                            <input type="radio" name="example" class="rating" value="2">
                            <input type="radio" name="example" class="rating" value="3">
                            <input type="radio" name="example" class="rating" value="4">
                            <input type="radio" name="example" class="rating" value="5">
                        </div>
                        <div class="star_tip"></div>
                    </div>
                    <div class="stars">
                        <div>逻辑清晰</div>
                        <div class="star-rating-3">
                            <input type="radio" name="example" class="rating" value="1">
                            <input type="radio" name="example" class="rating" value="2">
                            <input type="radio" name="example" class="rating" value="3">
                            <input type="radio" name="example" class="rating" value="4">
                            <input type="radio" name="example" class="rating" value="5">
                        </div>
                        <div class="star_tip"></div>
                    </div>
                    <div class="stars">
                        <div>课程设计</div>
                        <div class="star-rating-4">
                            <input type="radio" name="example" class="rating" value="1">
                            <input type="radio" name="example" class="rating" value="2">
                            <input type="radio" name="example" class="rating" value="3">
                            <input type="radio" name="example" class="rating" value="4">
                            <input type="radio" name="example" class="rating" value="5">
                        </div>
                        <div class="star_tip"></div>
                    </div>
                </div>
                <div class="comment_form" style="display:none">
                    <label>内容：</label>
                    <textarea name="comment_cont" class="comment_cont"></textarea>
                    <div class="foot">
                        
                        <button name="comment_submit" type="button" class="btn btn_orange btn_small">发布</button>
                        <button id="comment_close" type="button" class="btn">取消</button>
                        <span class="text_count">还能输入<span class="num_count">200</span>字</span>
                    </div>
                </div>
                <ul class="comments_list">
                	
                	<c:forEach items="${pinglun}" var="pitem">
	                	<li>
	                        <span class="avatar tiny">
				               	<c:if test="${pitem.user_avatar !=  null}">
				               		<img src="${pitem.user_avatar}" onerror="imgonload(this,'${pitem.sex}');" id="userImage" name="userImage" >
				               	</c:if>
				               	<c:if test="${pitem.user_avatar == null}">
					               <c:if test="${pitem.sex == 1}">
					               	<img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" >
					               </c:if>
					               <c:if test="${pitem.sex == 2}">
					               	<img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" >
					               </c:if>
				               </c:if>
	                        </span>
	                        <p>
	                            <span class="name">${pitem.realName}</span>
	                            <span class="date"><fmt:formatDate value="${pitem.scoreDate}" pattern="yyyy-MM-dd"/></span>
	                            
	                        </p>
	                        <p class="cont">${pitem.scoreContent}</p>
	                    </li>
                	</c:forEach>
                </ul>
            </div>
	        <%@include file="/commons/page.jsp"%>
            <ul class="paginate">
                <!-- <li class="first_page">首页</li>
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
                <li class="last_page">尾页</li> -->
            </ul>
        </div>
        <div class="aside cont">
            <h2 class="title"><span>目录</span></h2>
            <ul class="course_list">
                <c:forEach items="${cvSet.cvList}" var="list" varStatus="status">
                    <li class="${list.id}" >课程${status.index+1}：${list.name}</li>
                    <ul <c:if test="${list.cv_type eq 2}">name="2"</c:if><c:if test="${list.cv_type != 2}">name="1"</c:if>>
                    <input type="hidden" name="cvId" value="${list.id}"/>
                    <input type="hidden" id="timerCvType${list.id}" value="${list.type}" />
                        <c:forEach items="${list.unitList}" var="unit" varStatus="status">
                        <li class="has_done"  name="makeType${list.id}">
	                            <c:if test = "${list.cv_type eq 2}">	                               	                              
	                               <c:if test="${unit.unitMakeType != 2}">
	                               		<c:set var="unitLogStatusStyle" value=""/>	                            	
		                               	<c:if test = "${unit.logStatus == null}">
		                               		<i class="iconfont pm" id="pm${unit.id}">&#xe674;</i>	                               
		                            	</c:if>
		                            	<c:if test = "${unit.logStatus == 1}">
		                               		<i class="iconfont pm" id="pm${unit.id}">&#xe639;</i>	                               
		                            	</c:if>
		                            	<c:if test = "${unit.logStatus == 2}">
		                               		<i class="iconfont pm" id="pm${unit.id}">&#xe61c;</i>
		                               		<c:set var="unitLogStatusStyle" value="style='color:#ADADAD'"/>	                               
		                            	</c:if>
	                               		<a displayLength="35" class="unit" title="${unit.name}" ${unitLogStatusStyle}  id="unit${unit.id}" href="javascript:void(0);" onclick="javascript:gotoLiveView('${list.id}','${list.type}','${list.cost_type}','${unit.id}','${list.startDate}','${list.endDate}');">单元${status.index+1}：${unit.name}</a>
	                               </c:if>
	                               <c:if test="${unit.unitMakeType eq 2}">
	                            		<c:set var="unitLogStatusStyle" value=""/>	                            	
		                               	<c:if test = "${unit.logStatus == null}">
		                               		<i class="iconfont pm" id="pm${unit.id}">&#xe674;</i>	                               
		                            	</c:if>
		                            	<c:if test = "${unit.logStatus == 1}">
		                               		<i class="iconfont pm" id="pm${unit.id}">&#xe639;</i>	                               
		                            	</c:if>
		                            	<c:if test = "${unit.logStatus == 2}">
		                               		<i class="iconfont pm" id="pm${unit.id}">&#xe61c;</i>
		                               		<c:set var="unitLogStatusStyle" value="style='color:#ADADAD'"/>	                               
		                            	</c:if>
	                               		<a displayLength="35" class="unit" title="${unit.name}"  ${unitLogStatusStyle}  id="unit${unit.id}" href="javascript:void(0);" onclick="playUnit('${unit.id}',this,'${unit.type}','','${unit.quota}');">单元${status.index+1}：${unit.name}</a>
	                               </c:if>	                               
	                            </c:if>
	                            
	                            <c:if test = "${list.cv_type != 2}">
	                            	<c:set var="unitLogStatusStyle" value=""/>	                            	
	                               	<c:if test = "${unit.logStatus == null}">
	                               		<i class="iconfont pm" id="pm${unit.id}">&#xe674;</i>	                               
	                            	</c:if>
	                            	<c:if test = "${unit.logStatus == 1}">
	                               		<i class="iconfont pm" id="pm${unit.id}">&#xe639;</i>	                               
	                            	</c:if>
	                            	<c:if test = "${unit.logStatus == 2}">
	                               		<i class="iconfont pm" id="pm${unit.id}">&#xe61c;</i>
	                               		<c:set var="unitLogStatusStyle" value="style='color:#ADADAD'"/>	                               
	                            	</c:if>
	                            	<a displayLength="35" class="unit" title="${unit.name}"  ${unitLogStatusStyle}  id="unit${unit.id}" href="javascript:void(0);" onclick="playUnit('${unit.id}',this,'${unit.type}','','${unit.quota}');">单元${status.index+1}：${unit.name}</a>
	                            </c:if>
	                                  <c:if test="${unit.point eq 1}">
		                                  <img src="${ctx}/qiantai_page/img/point.png" width="12px" height="12px" />
		                                  <a id="porint" style="font-size: 12px; display: inline; font-weight: normal; color: rgb(28,192,226); display: none;">任务点</a>
	                                  </c:if> 
		                            <span>
		                              <!-- 根据不同单元类型显示对应图标 -->
		                              <c:if test="${unit.type eq 1}"><i class="fa fa-video-camera"></i></c:if>
		                              <c:if test="${unit.type eq 2}"><i class="fa fa-commenting"></i></c:if>
		                              <c:if test="${unit.type eq 3}"><i class="fa fa-pencil-square"></i></c:if>
		                              <c:if test="${unit.type eq 4}"><i class="fa fa-pencil-square"></i></c:if>
		                              <c:if test="${unit.type eq 5}"><i class="fa fa-file-text"></i></c:if>
		                              <c:if test="${unit.type eq 6}"><i class="fa fa-file-text"></i></c:if>
		                              <c:if test="${unit.type eq 7}"><i class="iconfont icon-zhibo"></i></c:if>
		                            </span>
	                            </li>
                        </c:forEach>
                    </ul>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<div class="popup">
	<form id="loginForm" name="loginForm" action = "${ctx}/loginAjax.do" method="post">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form" style="margin:-200px 0px 0px -270px;">
		    <input type="hidden" id ="isLive" value="false"/>
		        <h2><span class="close"><i class="fa fa-times"></i> </span> 用户登录</h2>
		        <div class="join_form" id="popup_form">
		            <div class="input_div">
		                <label>用户名/手机/邮箱</label>
		                <input type="text" name="userData" id="userName">
		            </div>
		            <div class="input_div">
		                <label>密码</label>
		                <input type="password" name="password" id="password">
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
<!-- 项目分享 -->
<div class="jiathis_style_24x24">
		<a class="jiathis_button_qzone"></a>
		<a class="jiathis_button_tsina"></a>
		<a class="jiathis_button_tqq"></a>
		<a class="jiathis_button_weixin"></a>
		<a class="jiathis_button_renren"></a>
		<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank">ceshi </a>
		<a class="jiathis_counter_style"></a>
</div>


<script type="text/javascript">
    var jiathis_config = {data_track_clickback:'true'};
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0&amp;uid=2125661" charset="utf-8"></script>
<script type="text/javascript">
//项目根路径
var basepath ='${ctx}';
// 加载验证码方法
//$('#drag').drag();
var flag = false;
var  flags = false; 
	var cvSetId = '${cvSet.id}';
	
    $(function () {
    	
    	//根据项目初始化学习记录
        projectCensus();
    	
    	//设置JiaThis底部分享按钮
    	$(".link_01").text("NCME");
    	$(".link_01").attr("href","javascript:void(0);");
    	$(".link_01").removeAttr("target");
    	
    	//查询用户是否收藏过该项目
    	$.ajax({
				type: 'POST',
				url: '${ctx}/cvset/cvsetFavorites.do?mode=search&cvsetId=${id}',
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					//如果收藏过，则显示实心
					if(result.message == 'yes'){
						$("#favorites").attr('class','like fa fa-heart');
					}
				}
	    });

        $(".go_comment").click(function(){            
        	<%--判断是否已登录，杨红强2017-02-17--%>
        	var isloginFlag = true ;
        	$.ajax({
					type: 'POST',
					url: '${ctx}/projectDetail.do?mode=isLogin&id=${id}',
					dataType: 'JSON',
					async: false,
					success : function(data){
						var result = eval(data);
						if (result.message == 'noLogin'){
						    isloginFlag = false ;
						}
					}
		    });
		    		    
		    if (!isloginFlag) {
		       $(".popup").show();
		       return false ;
		    }  		    
		            	
        	/* 判断是否已评价，杨红强2017-02-17 */
        	var isPingFlag = false ;
        	$.ajax({
					type: 'POST',
					url: '${ctx}/projectDetail.do?mode=scoreLogIsExist&id=${id}',
					dataType: 'JSON',
					async: false,
					success : function(data){
						var result = eval(data);
						if (result.message == 'true'){							        					
        					isPingFlag = true ;
						}						
					}
		    });
		    if (isPingFlag) {
		       alert("已评价，不能再次评价！");
		       return false;
		    } 
		    
		    <%--判断是否已学完，学完才能评价，杨红强2017-02-17--%>
		    var isFinishFlag = true ;
		    var logCVSetStatus = $("#logCVSetStatus").val();
		    if(logCVSetStatus != 2){
		    	isFinishFlag = false ;
		    }
  		     if (!isFinishFlag) {
  		       alert("学完才能评价！");
        	   return false;
  		    } 
        	
           $(".comment_starts,.comment_form").show();
        });
        $(".course_list ul li:not('.has_done')").on("mouseover",function(){
            $(this).find(".fa-info").show();
        });
        $(".course_list ul li:not('.has_done')").on("mouseout",function(){
            $(this).find(".fa-info").hide();
        });
        $(".course_list ul li.has_done").on("mouseover",function(){
            $(this).find(".fa-info-circle").removeClass("fa-info").show();
        });
        $(".course_list ul li.has_done").on("mouseout",function(){
            $(this).find(".fa-info-circle").hide().addClass("fa-info");
        });
        $(".star-rating-1").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-1").parent().find(".star_tip").text("内容很少，几乎用不上");
            }
            if (vote == 2){
                $(".star-rating-1").parent().find(".star_tip").text("内容较少，收获很小");
            }
            if (vote == 3){
                $(".star-rating-1").parent().find(".star_tip").text("内容尚可，达到基本期望");
            }
            if (vote == 4){
                $(".star-rating-1").parent().find(".star_tip").text("内容较充实，收获较多");
            }
            if (vote == 5){
                $(".star-rating-1").parent().find(".star_tip").text("内容很丰富，收获很多");
            }
        });
        $(".star-rating-2").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-2").parent().find(".star_tip").text("讲解很啰嗦，完全不明白");
            }
            if (vote == 2){
                $(".star-rating-2").parent().find(".star_tip").text("多余内容较多，需要挑着看");
            }
            if (vote == 3){
                $(".star-rating-2").parent().find(".star_tip").text("语言简洁，顺畅易懂");
            }
            if (vote == 4){
                $(".star-rating-2").parent().find(".star_tip").text("语言讲解精彩，收获很多");
            }
            if (vote == 5){
                $(".star-rating-2").parent().find(".star_tip").text("言简意赅，讲解有惊喜，非常容易理解");
            }
        });
        $(".star-rating-3").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-3").parent().find(".star_tip").text("逻辑混乱，完全听不明白");
            }
            if (vote == 2){
                $(".star-rating-3").parent().find(".star_tip").text("逻辑不清，课程目标不明");
            }
            if (vote == 3){
                $(".star-rating-3").parent().find(".star_tip").text("按部就班，基本达到课程目标");
            }
            if (vote == 4){
                $(".star-rating-3").parent().find(".star_tip").text("逻辑清晰，达到学习目标");
            }
            if (vote == 5){
                $(".star-rating-").parent().find(".star_tip").text("逻辑非常清晰，重点突出，收获超出预期");
            }
        });
        $(".star-rating-4").rating(function(vote, event){
            if (vote == 1){
                $(".star-rating-4").parent().find(".star_tip").text("课程设计敷衍，完全不知道在讲什么");
            }
            if (vote == 2){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线不清晰，和课程目标有差距");
            }
            if (vote == 3){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线相对清晰，基本能达到预期课程目标");
            }
            if (vote == 4){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线清晰，知识结构合理，达到学习目标");
            }
            if (vote == 5){
                $(".star-rating-4").parent().find(".star_tip").text("学习路线非常清晰，教学形式设计有惊喜，收获超出预期");
            }
        });
/*
        $(".user_info").click(function(){
            window.open("teacher_detail.html","blank");
        });
        
        $("button[name=study]").click(function(){
            window.location.href= "course_detail.html";
        });
        */
        
        /*
         * @author 张建国
         * @time   2017-01-10
         * 说      明：保存学习记录(项目)
         */
        
        $("button[name=study_begin]").click(function(){
        	//项目id
        	var $thi = $(this);
        	var id = '${id}';
        	$.ajax({
 					type: 'POST',
 					url: '${ctx}/study/logstudy.do?mode=isExistOtherCVForLiveProject&cvSetId='+id,
 					async: false,		
 					dataType: 'JSON',
 					success : function(data){
 						if(data.res == "true"){
 							var cvId = data.cvId;
 							var unitId = data.unitId;
 							var startDate =data.startDate ;
 							var endDate =data.endDate ;
 							gotoLiveView(cvId,'','',unitId,startDate,endDate);
 						}else if(data.res == "false"){
							$.ajax({
				 					type: 'POST',
				 					url: '${ctx}/study/logstudy.do?mode=queryLogin&cvsetId='+id,
				 					async: false,		
				 					dataType: 'JSON',
				 					success : function(data){
				 						var result = eval(data);
				 						if(result.message == 'noLogin'){
				 							//弹出登录窗口
				 							$(".popup").show();
				 						}else if(result.message == 'success'){
											if($thi.text()=="开始学习"){
												checkbind(result.costType);
				 								if(flags ==true){
				 									location.href = "${ctx}/entityManage/entityView.do?type=play2&id="+id+"&paramType=project";
				 								}
											}else{
												location.href = "${ctx}/entityManage/entityView.do?type=play2&id="+id+"&paramType=project";
											}
				 						}
				 					}
				 		    });
 						}else{
 							alert(data.message);
 						}
 					},
				error: function(e){
					//alert("登录超时......");
				}
 		    });
        });
        var faceId;
        var faceText;
        $(".btyn").click(function(){
				faceId = $(this).attr('data-listid'); 
				faceText = $(this).val()
		});
        
        var cvSetId =$("#cvSetId").html();
        var cvSetCostStr =$("#cvSetCost").val();
        console.log(cvSetCostStr)
        var projectNameStr=$("#cvSetName").html();
		$("button[name=entry]").click(function() {
							//项目id
							var $thi = $(this);
							var id = '${id}';
							$.ajax({
										type : 'POST',
										url : '${ctx}/study/logstudy.do?mode=isExistOtherCVForLiveProject&cvSetId='+ id,
										async : false,
										dataType : 'JSON',
										success : function(data) {
											if (data.res == "true") {
												var cvId = data.cvId;
												var unitId = data.unitId;
												var startDate = data.startDate;
												var endDate = data.endDate;
												gotoLiveView(cvId, '', '',unitId, startDate,endDate);
											} else if (data.res == "false") {
												$.ajax({
															type : 'POST',
															url : '${ctx}/study/logstudy.do?mode=queryLogin&cvsetId='+ id,
															async : false,
															dataType : 'JSON',
															success : function(data) {
																var result = eval(data);
																if (result.message == 'noLogin') {
																	//弹出登录窗口
																	$(".popup").show();
																} else {//已经登录的 返回 报名成功
																	if (faceId == null || faceId == '') {
																		layer.alert('请选择您要报名的期数！');
																	} else {
																		var ok = "<div style='margin-top: 30px;margin-left: 35px;margin-right: 30px;margin-bottom: 32px;'>"
																		      +"<div style ='text-align:center;'>"
																				+ "面授报名确认<br>--------------------------</div>"
																				+"<div>&nbsp;&nbsp;&nbsp;&nbsp;您确认报名【"+ projectNameStr+"】"+faceText+$("#"+faceId).html()+"的面授项目吗？"
																				+ "<div>&nbsp;&nbsp;&nbsp;&nbsp;注意：该项目费用￥"
																				+ cvSetCostStr
																		        +"元，请在线下缴费</div></div>";
																		layer.open({
																					type : 1,
																					title : false //不显示标题栏
																					,closeBtn : false,
																					area : ["400px;","270px" ],
																					shade : 0.9,
																					id : 'LAY_layuipro',  //设定一个id，防止重复弹出
																					resize : false,
																					btn : ['确认','取消' ],
																					btnAlign: 'c',
																					moveType : 1, //拖拽模式，0或者1
																					content : ok,
																					success : function(layero) {
																						var btn = layero.find('.layui-layer-btn');
																						btn.find('.layui-layer-btn0').attr(
																								{href : "${ctx}/entityManage/faceEntry.do?method=faceEntry&id="+faceId
																								,target : '_blank'
																						});
																					}
																				});

																	}
																}
															}
														});
											} else {
												alert(data.message);
											}
										},
										error : function(e) {
											//alert("登录超时......");
										}
									});
						});

		$(".close,button[name=submit]").click(function() {
			$(".popup").hide();
		});
		$("button[name=submit]").click(function() {
			window.localStorage.setItem("isLogin", "true");
		});

		/*
		 * @author	han
		 * @time		2017-01-25
		 * Description	还能输入字数计算
		 */
		$('.comment_cont').keypress(function() {
			var txt = 200 - $(this).eq(0).val().length - 1;
			if (txt < 0)
				return false;
			$('.num_count').text(txt);
		});
		$('.comment_cont').keyup(function() {
			var txt = 200 - $(this).eq(0).val().length;
			$('.num_count').text(txt);
		});

		var score1 = parseInt("${cvSet.critiqueScore1-1}");
		var score2 = parseInt("${cvSet.critiqueScore2-1}");
		var score3 = parseInt("${cvSet.critiqueScore3-1}");
		var score4 = parseInt("${cvSet.critiqueScore4-1}");

		if (score1 >= 0)
			$('.star-rating-1 .star').eq(score1).addClass('fullStar').prevAll()
					.addClass('fullStar');
		if (score2 >= 0)
			$('.star-rating-2 .star').eq(score2).addClass('fullStar').prevAll()
					.addClass('fullStar');
		if (score3 >= 0)
			$('.star-rating-3 .star').eq(score3).addClass('fullStar').prevAll()
					.addClass('fullStar');
		if (score4 >= 0)
			$('.star-rating-4 .star').eq(score4).addClass('fullStar').prevAll()
					.addClass('fullStar');
		//$('.star-rating-1').parent().find(".star_tip").text("内容很丰富，收获很多");

		$("button[name=comment_submit]").click(function() {

			var param = "score1=" + $('.star-rating-1 .fullStar').length;
			param += "&score2=" + $('.star-rating-2 .fullStar').length;
			param += "&score3=" + $('.star-rating-3 .fullStar').length;
			param += "&score4=" + $('.star-rating-4 .fullStar').length;
			param += "&comment=" + $('.comment_cont').val();

			$.ajax({
				type : 'POST',
				url : '${ctx}/projectDetail.do?mode=critique&id=' + cvSetId,
				data : param,
				dataType : 'JSON',
				success : function(data) {
					var result = eval(data);
					if (result.message == 'success') {
						//暂时播放
						alert("操作成功。");
						window.location.reload(true);
					}
					if (result.message == 'noLogin') {
						//弹出登录窗口
						alert("请先登录.");
					}
					if (result.message == 'isCritiqued') {
						//已评价 YHQ 2017-02-14 1827
						alert("已评价，不能再次评价！");
					}
				}
			});
		});

		//###########################taol##############################
		$("#submit_btn")
				.click(
						function() {
							if ($('#userName').val() == "") {
								alert("请输入用户名/手机/邮箱！");
								return;
							}
							if ($('#password').val() == "") {
								alert("请输入密码！");
								return;
							}
							if (!validate()) {
								return;
							}
							/* if(!flag)
							{    
							alert("请完成验证码验证！");
							return;
							}  */
							//$("#submit_btn").attr("disabled","disabled");
							//通过AJAX实现登录功能
							$.ajax({
										type : 'POST',
										url : ctxJS + '/loginAJAX.do',
										data : $("#loginForm").serialize(),
										dataType : 'JSON',
										success : function(data) {
											var result = eval(data);
											if (result.message == "success") {
												//$("#submit_btn").removeAttr("disabled");
												var isLive = $("#isLive").val();
												if (isLive == "true") {//此处用于判断用户进入直播的情况
													var zbcvid = $("#zbcvid")
															.val();//获取当前课程信息
													var costType = $(
															"#costType").val();
													checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
													if (flags == true) {
														$("#isLive").val(
																"false");
														window.location.href = ctxJS
																+ "/viewLiveListInterface.do?mode=getSignk&cvId="
																+ zbcvid;
													}
												} else {
													location.reload();
												}

												/* else{
													if( $("button[name=study_begin]").text()=="开始学习"){
														checkbind(costType);
														if(flags ==true){
															location.href = ctxJS + "/entityManage/entityView.do?type=play2&id=" + '${id}' + "&paramType=project";
														}
													}else{
														location.href = ctxJS + "/entityManage/entityView.do?type=play2&id=" + '${id}' + "&paramType=project";
													}
												} */
											} else {
												if (result.message == "checkNumberNull") {
													alert("验证码为空！");
												} else if (result.message == "checkNumberError") {
													alert("验证码错误！");
												} else if (result.message == "userNull") {
													alert("此用户不存在！");
												} else if (result.message == "passwordError") {
													alert("密码错误！");
												} else if (result.message == "userNameNull") {
													alert("用户名为空！");
												}
											}
										},
										error : function(e) {
											//alert("登录超时......");
										}
									});
						});

		// 取消
		$("#closebox").click(function() {
			$(".paybox").hide();
			$(".bgc").hide();
		})

		// 取消按钮
		$("#closebox2").click(function() {
			$(".paybox").hide();
			$(".bgc").hide();
		})

		// 提交支付信息
		$("#paysubmit").click(
						function() {
							var cardnumberval = $("#cardnumber").val();
							var cardpwd = $("#cardpwd").val();
							var zbcvid = $("#zbcvid").val();
							var isLive = $("#isLive").val();
							if (!cardnumberval) {
								alert("请输入卡号");
								return false;
							}
							if (!cardpwd) {
								alert("请输入密码");
								return false;
							}
							$.ajax({
										type : 'POST',
										url : ctxJS+ "/study/logstudy.do?mode=playSubmit&cvsetId="+ '${id}' + "&cardNumber="+ cardnumberval+ "&cardPassword=" + cardpwd,
										dataType : 'JSON',
										success : function(data) {
											var result = eval(data);
											if (result.message == 'success') {
												//进入点播或者直播
												if (isLive == "true") {
													window.location.href = ctxJS
															+ "/viewLiveListInterface.do?mode=getSignk&cvId="
															+ zbcvid;
												} else {
													$(".bgc").hide();
													$(".paybox").hide();
													//location.href = "${ctx}/entityManage/entityView.do?type=play2&id="+'${id}'+"&paramType=project";
												}
											} else if (result.message == 'noenable') {
												alert("该卡已被使用，无法重复添加"); //卡状态不可用
												return false;
											} else if (result.message == 'time') {
												alert("您的学习卡已经过期");
												return false;
											} else if (result.message == 'notfind') {
												alert("输入的卡号或密码错误");
												return false;
											} else if (result.message == 'typeno') {
												alert("该卡未绑定项目，无法添加成功"); //卡类型无效
												return false;
											} else if (result.message == 'typedisable') {
												alert("该学习卡处于禁用状态，不可绑定用户"); //卡类型无效
												return false;
											}
											/* else if(result.message == 'notbind'){
												//已评价 YHQ 2017-02-14 1827
												alert("卡状态不可用或类型错误");
												return false;
											}else if(result.message == 'time'){
												alert("您的学习卡已经过期");
												return false;
											}else if(result.message == 'notfind'){
												alert("输入的卡号或密码错误");
												return false;
											}else if(result.message == 'typeno'){
												alert("卡类型无效");
												return false;
											}else if(result.message == 'alreadyBind'){//添加改卡已经绑过的情况处理--taoliang
												alert("此卡已被用户使用！");
												return false;
											} */
										}
									});
						});
		//#########################################################
		$(".container .course_list ul li").mouseover(function() {
			$(this).find("img").show();
			$(this).find("#porint").show();
		}).mouseout(function() {
			$(this).find("img").hide();
			$(this).find("#porint").hide();

		});

	});
	//###################################################
	//直播入口操作
	function gotoLiveView(id, zhiboType, costType, unitId, startDate, endDate) {

		zhiboType = $("#timerCvType" + id).val();
		if (zhiboType == 2) {
			alert("直播时间为" + startDate.split('.')[0] + " -- "
					+ endDate.split('.')[0] + "，尚未开始！");
			return;
		} else if (zhiboType == 3) {
			alert("直播已结束！");
			return;
		} else if (zhiboType == 4) {//回放入口
			$.ajax({
						type : 'POST',
						url : ctxJS + '/study/logstudy.do?mode=queryLogin&id='+ id,
						dataType : 'JSON',
						success : function(data) {
							var result = eval(data);
							if (result.message == 'noLogin') {
								//弹出登录窗口
								$(".popup").show();
							} else if (result.message == 'success') {
								$("#zbcvid").val(id);//初始化课程ID
								checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
								if (flags == true) {
									InitializationCVAndCVSetLog(id);//回放入口添加学习记录维护
									window.location.href = ctxJS
											+ "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="
											+ id + "&unitId=" + unitId;
								}
							};
						}
					});
			return;
		} else if (zhiboType == 5) {
			alert("直播结束，视频转码中，敬请期待！");
			return;
		} else {//直播观看入口 
			$.ajax({
						type : 'POST',
						url : ctxJS + '/study/logstudy.do?mode=queryLogin&id='+ id,
						dataType : 'JSON',
						success : function(data) {
							var result = eval(data);
							if (result.message == 'noLogin') {
								$("#zbcvid").val(result.id);//初始化课程ID
								$("#costType").val(costType);//初始化卡类型
								//弹出登录窗口
								$("#isLive").val("true");
								$(".popup").show();
							} else if (result.message == 'success') {

								$("#zbcvid").val(id);//初始化课程ID
								checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
								if (flags == true) {
									InitializationCVAndCVSetLog(id);//直播入口添加学习记录维护
									window.location.href = ctxJS
											+ "/viewLiveListInterface.do?mode=getSignk&cvId="
											+ id;
								}
							}
							;
						}
					});
		}
	}
	//###################################################

	/*
	 * @author 张建国
	 * @time   2017-01-10
	 * 说      明：项目收藏
	 */
	function favorite(obj) {
		var cla = $(obj).attr("class");
		if (cla == 'like fa fa-heart fa-heart-o') {
			//进行收藏
			$.ajax({
				type : 'POST',
				url : '${ctx}/cvset/cvsetFavorites.do?mode=add&cvsetId=${id}',
				dataType : 'JSON',
				success : function(data) {
					var result = eval(data);
					if (result.message == 'success') {
						$(".prompt").find("span").text("收藏成功！");
						$(".prompt").show(hidebox);
						$(obj).attr('class', 'like fa fa-heart');
					}
					if (result.message == 'noLogin') {
						//alert("请先登录.");
						$(obj).attr("class", "like fa fa-heart fa-heart-o");
						$(".popup").show();
					}
				}
			});
		}
		if (cla == 'like fa fa-heart') {
			$.ajax({
						type : 'POST',
						url : '${ctx}/cvset/cvsetFavorites.do?mode=delete&cvsetId=${id}',
						dataType : 'JSON',
						success : function(data) {
							var result = eval(data);
							if (result.message == 'success') {
								$(".prompt").find("span").text("取消收藏成功！");
								$(".prompt").show(hidebox);
								$(obj).attr('class',
										'like fa fa-heart fa-heart-o');
							}
							if (result.message == 'noLogin') {
								alert("请先登录.");
								$(obj).attr("class",
										"like fa fa-heart fa-heart-o")
							}
						}
					});
		}
	}

	/**
	 * @author 张建国
	 * @time   2017-01-16
	 * @param  Obj
	 * 说      明：通过点击单元进入学习页面
	 **/
	function playUnit(obj, dom, type, content, quota) {
		var unitId = obj;
		var cvId = $(dom).parent().parent().prev().attr("class");
		var cvsetId = '${cvSet.id}';
		//2017.01.26 Add By IE
		var unitType = type;//类型 1.理论讲解 2.主题讨论 3.随堂考试 4. 操作演示 5 扩展阅读 6.病例分享
		$.ajax({
			type : 'POST',
			url : ctxJS + '/study/logstudy.do?mode=queryLogin&id=' + cvId,
			dataType : 'JSON',
			async : false,
			success : function(data) {
				var result = eval(data);
				if (result.message == 'noLogin') {
					//弹出登录窗口
					$(".popup").show();
				} else if (result.message == 'success') {
					checkbind('');
				}
				;
			}
		});
       if(unitType ==5){//跳转拓展阅读
    	   $.ajax({
				url : "${ctx}/study/logstudy.do?mode=addUnit&cvsetId="+ cvsetId + "&cvId=" + cvId + "&unitId="+ unitId,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					var result = eval(data);
					if (result.message == 'success') {
						InitializationCVAndCVSetLog(cvId);//添加学习记录维护
						location.href = "${ctx}/entityManage/entityView.do?type=reading&cvsetId="
							+ cvsetId+ "&cvId="+ cvId+ "&unitId="+ unitId
							+ "&id=${id}"+ "&paramType=project&ToPlay3=true";
				} else if (result.message == 'noLogin') {
					$(".popup").show();
				}
				}
			});
       }else  if (flags == true) {
			//先保存单元学习记录
			$.ajax({
						url : "${ctx}/study/logstudy.do?mode=addUnit&cvsetId="+ cvsetId + "&cvId=" + cvId + "&unitId="+ unitId,
						type : 'POST',
						dataType : 'json',
						success : function(data) {
							var result = eval(data);
							if (result.message == 'success') {
								InitializationCVAndCVSetLog(cvId);//添加学习记录维护
								location.href = "${ctx}/entityManage/entityView.do?type=play3&cvsetId="
										+ cvsetId+ "&cvId="+ cvId+ "&unitId="+ unitId
										+ "&id=${id}"+ "&paramType=project&ToPlay3=true";
							} else if (result.message == 'noLogin') {
								//alert("请先登录.");
								$(".popup").show();
							}
						}
					});
		}

	}

	/**
	CHY 2017-04-18 
	      验证项目是否需要付费是否绑卡
	 */
	function checkbind(costType) {
		/* if(costType == 1){
			$(".popup").hide();
		$(".bgc").show();
		$(".paybox").show();
		return false;
		}    */
		$.ajax({
			url : "${ctx}/study/logstudy.do?mode=CheckBind&cvsetId=" + '${id}'+ "&costType=" + costType,
			type : 'POST',
			async : false,
			dataType : 'json',
			success : function(data) {
				var result = eval(data);
				if (result.card == '0') {
					flags = true;
				} else {
					// 弹出支付窗口
					$(".bgc").show();
					$(".paybox").show();
					$(".popup").hide();
					return false;
				}
			}
		});

	}

	/**
	 * @author 张建国
	 * @time   2017-01-6
	 * @param  cvsetId
	 * 说     明： 统计项目下的学习记录
	 **/
	function tongjiUnitStudy(obj) {
		$.ajax({
					url : "${ctx}/study/logstudy.do?mode=tongjiUnitStudy&cvsetId="+ obj,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						var result = eval(data);
						var ids = '';
						var cols = '';
						var clas = '';
						var idArray = new Array();
						var colorArray = new Array();
						var claArray = new Array();
						if (result.message == 'success') {
							if (result.unitIds != null && result.unitIds != "") {
								//对学习过的单元进行操作
								ids = result.unitIds;
								clas = result.cla;
								idArray = ids.split("_");
								claArray = clas.split("_");
								$("a[class^='unit']").each(function() {
													var unitTempId = $(this)
															.attr("_title");
													for (var i = 0; i < idArray.length; i++) {
														if (unitTempId == idArray[i]) {
															if (claArray[i] == "1") {
																//设置单元显示字体

																$(this).css("color","rgb(204,204,204)");
																$(this).next().css("color","");
																//设置单元前的图标
																//$(this).prev().html("<img src='${ctx}/qiantai_page/css/iconfont/wancheng.png' style='width: 12px;height: 12px;'/>");
																//$(this).prev().css("color","rgb(18,188,255)");
																//$(this).prev().css("font-size","12px");
																$(this).prev().html("&#xe61c;")
															} else if (claArray[i] == "0") {

																//$(this).css("color","");
																//设置单元前的图标
																//$(this).prev().html("<img src='${ctx}/qiantai_page/css/iconfont/xuexizhong.png' style='width: 12px;height: 12px;'/>");
																$(this).prev().html("&#xe639;")

															}

														}
													}
												});
							}
						}
					}
				});
	}

	/**
	 * @author 张建国
	 * @time   2017-02-14
	 * @param  projectId
	 * 说       明：统计项目学习进度
	 **/
	/* function tongjiProjectStudyLog(obj){
	 $.ajax({
		    url:"${ctx}/study/logstudy.do?mode=tongjiProjectStudyLog&cvsetId="+obj,
		    type: 'POST',
		    dataType: 'json',
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message==1){
		    		$("#study_begin").text("开始学习");
		    	}
		    	if(result.message==2){
		    		$("#study_begin").text("继续学习");
		    	}
		    	if(result.message==3){
		    		$("#study_begin").text("已学完");
		    	}
		    }
		});
	} */

	//保存学习中的项目
	function saveItemStudy() {

		var id = '${id}';
		$.ajax({
			type : 'POST',
			url : '${ctx}/study/logstudy.do?mode=add&cvsetId=' + id,
			dataType : 'JSON',
			success : function(data) {
			},
		});
	};

	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义     
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码     
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。     
		obj.src = "${ctx}/login/GetRandomPictureAction.do?d=" + timenow;
	}

	// 取消
	$("#comment_close").click(function() {

		$(".comment_starts,.comment_form").hide();
	});

	// 显示一秒后关闭
	function hidebox() {
		setTimeout(function() {
			$(".prompt").hide();
		}, 1000);

	}

	$(function() {
		setInterval("timerFun()", 300);
	});
	function timerFun() {
		$("ul[name='2']")
				.each(
						function(index) {//2标识该课程为直播课程
							var _this = $("ul[name='2']");
							var cvId = $(this).find("input[name='cvId']").val();
							//alert(cvId);
							$.ajax({
										type : 'POST',
										url : ctxJS+ "/viewLiveListInterface.do?mode=getZBType&cvId="+ cvId,
										dataType : 'JSON',
										success : function(result) {
											//alert(result.typeInt);
											if (result.typeInt == '1') {
												$("#timerCvType" + cvId).val("1");
												$("li[name='makeType"+ cvId + "']").find("a").attr("title","直播中，点击进入直播页面");
											}
											if (result.typeInt == '2') {
												$("#timerCvType" + cvId).val("2");
												$("li[name='makeType"+ cvId + "']").find("a").attr("title","直播尚未开始");
											}
											if (result.typeInt == '3') {
												$("#timerCvType" + cvId).val("3");
												//_this.find("li a").attr("title","直播已结束");
											}
											if (result.typeInt == '4') {
												$("#timerCvType" + cvId).val("4");
												$("li[name='makeType"+ cvId + "']").find("a").attr("title","观看回放。点击，进入回放页面。");
											}
											if (result.typeInt == '5') {
												$("#timerCvType" + cvId).val("5");
												$("li[name='makeType"	+ cvId + "']").find("a").attr("title","直播结束，视频转码中，敬请期待！");
											}
										}
									})
						})
		/* $("#timerFreshUL li").each(function(index){
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
							if(result.typeInt == '4'){
								$("#timerCvType"+_cvId).val("4");
								_this.find("#soon"+index).text("观看回放");
							}
							if(result.typeInt == '5'){
								$("#timerCvType"+_cvId).val("5");
								_this.find("#soon"+index).text("已结束");
							}
						//}
					//})
				}
		 	})	
		}); */
   }  	
	
	//用于维护课程和项目学习记录（根据课程）---taoliang
	function InitializationCVAndCVSetLog(cvId){
		$.ajax({
			type : 'POST',
			url : ctxJS+ "/study/logstudy.do?mode=initializeCVAndCVSetStudyLog&cvId="+ cvId,
			dataType : 'JSON',
			success : function(result) {
				if (result.message != 'success') {
					//alert(result.message);
				}
			}
    	})
	} 
	
	//用于维护课程和项目学习记录（根据项目）---taoliang
    function projectCensus(){
    	var id = '${id}';
		$.ajax({
			type: 'POST',
			url: ctxJS + "/study/logstudy.do?mode=projectCensus&cvSetId="+id,
			dataType: 'JSON',
			success : function(result){
			}
    	})
	} 
     
</script>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
</body>
</html>