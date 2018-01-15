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
    <script type="text/javascript">
		var ctxJS="<%=request.getContextPath()%>";
	</script>
</head>
<style type="text/css">
	.DynamicInformation_img{width:575px;height:370px;overflow:hidden;float:left;}
	.DynamicInformation_right{width:555px;float:right;}
	.DynamicInformation_title{width:100%;}
	.DynamicInformation_title h2{margin:10px 0;background:url('${ctx}/qiantai_page/img/TownshipMedical/icon.png') no-repeat 0px 6px;text-indent:12px;float:left;}
	.DynamicInformation_title a{padding-top:16px;display:block;float:right;color:#1498f1;}
	.DynamicInformation_list li{margin-top:30px;font-size:14px;cursor:pointer;}
	.DynamicInformation_list li span{font-size:12px;padding:0px 10px 0px 10px;}
	.container .content .item_list.item_img_list li{height:300px !important;}
	.container .content{padding:20px 0;}
	.face-to-face{width:100%;padding:20px 0px 20px 0px;;border:1px solid #dddddd;margin-bottom:20px;background:#fdfdfd;}
	.face-to-face-title h3{font-weight:600;float:left;margin:0px 0px 0px 20px;}
	.face-to-face-title a{margin-right:20px;background:#ee551c;float:right;font-size:16px;color:#fff;padding:10px 15px 10px 15px;}
	.face-to-face ul{margin-left:30px;}
	.face-to-face ul li{margin-top:15px;line-height:30px;font-size:14px;color:#333;}
	.face-to-face ul li span{display:block;float:left;font-weight:600;width:80px;}
	.face-to-face ul li p{width:1150px;}
	.face-to-face ul li p.add{margin-left:80px;}
	.table{width:100%; border:1px solid #E0E0E0;border-radius:10px;-moz-border-radius:10px;-webkit-border-radius:10px;-khtml-border-radius:10px;}
	.table tr{width:100%;}
	.table tr th{text-align:center;height:30px;background:#1498f1;font-size:13px;color:#333;font-weight:500;color:#fff;border:1px solid #dddddd;font-weight:600;}
	.table tr td{text-align:center;padding:10px 0px 10px 0px;font-size:13px;color:#333;font-weight:400;border:1px solid #dddddd;}
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
    <%@include file="/qiantai_page/zhiboPayComm.jsp" %>
    <ul class="bread_crumbs">
       <li>您的位置：</li>
       <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="${ctx}/XiangYiIndex.do?code=111111111">乡医</a> <i class="fa fa-angle-right"></i></li>
       <li><a href="#">浙江</a></li>
    </ul>
    <div class="content no_padding">
        <div class="tabs">
            <ul class="tab_list">
<!-- 				<c:if test="${xyList!= null || fn:length(xyList) > 0}"> -->
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
<!--                 </c:if> -->
						
               <%--
                <li class="tab active" id="tab4">广东</li>
                <li class="tab" id="tab3">西藏</li>
                <li class="tab" id="tab4">山西</li>
                <li class="tab" id="tab5">新疆</li>
				<li class="tab" id="tab6">青海</li>
                <li class="tab" id="tab7">湖南</li>
                <li class="tab" id="tab8">辽宁</li>
                <li class="tab" id="tab9">黑龙江</li>
                --%>
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
                					<c:if test="${Sentence.provinceCode=='111111111'}">
										<li onclick="window.open('${ctx}/DynamicNotice.do?id=${Sentence.id}','_self')">${Sentence.title}<span>|</span>全国</li> 
									</c:if>
									<c:if test="${Sentence.provinceCode!='111111111'}">
										<li onclick="window.open('${ctx}/DynamicNotice.do?id=${Sentence.id}','_self')">${Sentence.title}<span>|</span>${Sentence.provinceName}</li> 
									</c:if>
			                	</c:forEach>
					       </c:when>
                			<c:when test="${SentenceList!= null}">
                				<c:forEach items="${SentenceList}" var="Sentence">
									<c:if test="${Sentence.provinceCode=='111111111'}">
										<li onclick="window.open('${ctx}/DynamicNotice.do?id=${Sentence.id}','_self')">${Sentence.title}<span>|</span>全国</li> 
									</c:if>
									<c:if test="${Sentence.provinceCode!='111111111'}">
										<li onclick="window.open('${ctx}/DynamicNotice.do?id=${Sentence.id}','_self')">${Sentence.title}<span>|</span>${Sentence.provinceName}</li> 
									</c:if>
			                	</c:forEach>
					       </c:when>
					       
						</c:choose>
<!-- 					<li onclick="window.open('${ctx}/qiantai_page/xiangYi/dongTaiZiXunAll/Dynamic_notice_3.jsp','_self')">2017年全国乡村医生师资培训课程表<span>|</span>全国</li> -->
<!-- 					<li onclick="window.open('${ctx}/qiantai_page/xiangYi/dongTaiZiXunSX/Dynamic_notice_2.jsp','_self')">山西省卫生计生委通知文件<span>|</span>山西</li> -->
<!-- 					<li onclick="window.open('${ctx}/qiantai_page/xiangYi/dongTaiZiXunSX/Dynamic_notice_4.jsp','_self')">卫生计生人才强基层工程-乡村医生师资培训在山西开班<span>|</span>山西</li> -->
<!-- 					<li onclick="window.open('${ctx}/qiantai_page/xiangYi/dongTaiZiXunSX/Dynamic_notice_5.jsp','_self')">“卫生计生人才强基层工程”为山西培养乡村医生<span>|</span>山西</li> -->
<!-- 					<li onclick="window.open('${ctx}/qiantai_page/xiangYi/dongTaiZiXunSX/Dynamic_notice_6.jsp','_self')">“最美医生”贺星龙有了导师<span>|</span>山西</li> -->
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
				
				<c:forEach items="${CVSetList}" var ="list">
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
		
		<c:if test="${CVList!=null && CVList.size()>0}">
			<div class="DynamicInformation_title">
				<h2>直播课程</h2>
			</div>
			<div style="clear:both;"></div>
			<div class="content clearfix">
				<ul class="item_list item_img_list" id="timerFreshUL">
				
				<c:forEach items="${CVList}" var="muke" varStatus="SerialNumber">
	                    <li onclick = "javascript:gotoLiveView('${muke.id}','${muke.type}','${muke.cost_type}','${muke.startDate}','${muke.endDate}');" style="height:265px;">
			            <span class="item_cover"
	                          style="background:url('${muke.file_path}') no-repeat center;background-size:cover">
	                        <div class="item_title">
								<input type="hidden" id="cvId${SerialNumber.index}" value="${muke.id}" />
	                    		<span class="ribbon soon" id="soon${SerialNumber.index}">...</span>
	                    		<input type="hidden" id="timerCvType${muke.id}" value="${muke.type}" />
	                    		<span><fmt:formatDate value="${muke.startDate}"  pattern="MM月dd日"/>
		                        <fmt:formatDate value="${muke.startDate}"  pattern="HH:mm"/></span><span style="color:#fff;">-</span>
		                        <span><fmt:formatDate value="${muke.endDate}"  pattern="MM月dd日"/>
		                        <fmt:formatDate value="${muke.endDate}"  pattern="HH:mm"/></span>
	                        </div>
	                        <i class="fa fa-play-circle-o"></i>
			            </span>
	                        <div class="item_cont">
	                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
	                            <p class="desc topvebanner-p">${muke.introduction}</p>
	                            <div class="foot no_border">
	                            	<c:choose>
	                                    <c:when test="${muke.cost_type eq 0 || muke.cost == null}">
	                                    	<span class="font_green" style="float:left;">免费</span>
	                                    </c:when>
	                                    <c:when test="${muke.cost_type eq 1}">
	                                    	<span class="font_green" style="float:left;">学习卡项目</span>
	                                    </c:when>
	                                    <c:when test="${muke.cost_type eq 2}">
	<!--                                     	<span class="font_green" style="float:left;">收费</span> -->
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
	                                <span style="line-height:35px;">授课教师：${muke.teacherList[0].name}</span>
	                                <!-- <span style="line-height:35px;">人数：0/1000</span> -->
	                            </div>
	                            <div class="foot" style="padding:8px 0 0 0;">
	                                <span style="display:block;width:100%;text-align:center;">${muke.teacherList[0].workUnit}</span>
	                            </div>
	                        </div>
	                    </li>
	                </c:forEach>
				
				</ul>
			</div>
		</c:if>
		
		<div class="content no_padding">
			<div class="DynamicInformation_title">
				<h2>面授课程</h2>
				<%--
				<a href="#">更多></a>
				 --%>
			</div>
			<div style="clear:both;"></div>
			
			<div class="face-to-face">
				<%--
					<div class="face-to-face-title">
						<p style="font-size:18px;color:#666;padding:0 10px;">面授课程尚未开始，敬请期待！</p>
						<h3>面授测试项目-001</h3>
						<a href="#">面授中</a>
						 
					</div>
				--%>
					<div style="clear:both;"></div>
					<ul>
						<li><span>培训时间：</span><p>2017年7月25日—2017年7月27日</p></li>
						<li><span>培训地点：</span><p>杭州市滨江区龙禧大酒店</p></li>
						<li><span>培训内容：</span><p>见课程表</p></li>
						<li><span>面授名额：</span><p>51人</p></li>
						<li><span>联系方式：</span><p>余小波 13754328002  赵士洁 13811968903</p></li>
					</ul>
				</div>
			
<!-- 	开始		实现好的免受课程，数据不完整暂时屏蔽 -->
<!-- 			<c:forEach items="${cvSetScheduleFaceTeachList}" var="cvSetScheduleFaceTeach"> -->
<!-- 				<div class="face-to-face"> -->
<!-- 					<div class="face-to-face-title"> -->
<!-- 						<h3> -->
<!-- 						<c:forEach items="${cvsetList1}" var="cvset1"> -->
<!-- 							<c:if test="${cvSetScheduleFaceTeach.cv_set_id == cvset1.id }"> -->
<!-- 								${cvset1.name} -->
<!-- 							</c:if> -->
<!-- 						</c:forEach> -->
<!-- 						</h3> -->
						<!-- <a href="#">面授中</a> -->
<!-- 						<c:if test="${cvSetScheduleFaceTeach.faceType eq 1}"><a href="#">面授中</a></c:if> -->
<!-- 						<c:if test="${cvSetScheduleFaceTeach.faceType eq 2}"><a href="#">未开始</a></c:if> -->
<!-- 						<c:if test="${cvSetScheduleFaceTeach.faceType eq 3}"><a href="#">已结束</a></c:if> -->
<!-- 					</div> -->
<!-- 					<div style="clear:both;"></div> -->
<!-- 					<ul> -->
<!-- 						<li><span>培训时间：</span><p> -->
<!-- 						<fmt:formatDate value="${cvSetScheduleFaceTeach.train_starttime }" pattern="yyyy-MM-dd"/> -->
<!-- 						~<fmt:formatDate value="${cvSetScheduleFaceTeach.train_endtime }" pattern="yyyy-MM-dd"/> -->
<!-- 						</p></li> -->
<!-- 						<li><span>培训地点：</span><p>${cvSetScheduleFaceTeach.train_place }</p></li> -->
<!-- 						<li><span>培训内容：</span><p>${cvSetScheduleFaceTeach.class_name }</p></li> -->
<!-- 						<li><span>面授名额：</span><p>${cvSetScheduleFaceTeach.people_number }人</p></li> -->
<!-- 						<li><span>联系方式：</span><p> -->
<!-- 							<c:set value="${ fn:split(cvSetScheduleFaceTeach.contact_way, '，') }" var="contactWays" /> -->
<!-- 							<c:forEach items="${contactWays}" var="contactWay" varStatus="status"> -->
<!-- 								<c:if test="${ status.index>0}"> -->
<!-- 									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 								</c:if> -->
<!-- 								${contactWay} <br/> -->
<!-- 							</c:forEach> -->
<!-- 						</p></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			</c:forEach> -->
<!-- 	结束		实现好的免受课程，数据不完整暂时屏蔽 -->	
		</div>
		<div class="content no_padding">
			<div class="DynamicInformation_title">
				<h2>课程表</h2>
			</div>
			<div style="clear:both;"></div>
			<%--
			<div class="face-to-face">
				<div class="face-to-face-title">
					<p style="font-size:18px;color:#666;padding:0 10px;">课程尚未开始，敬请期待！</p>
				</div>
				<div style="clear:both;"></div>
			</div>
			--%>
			<table class="table">
				<tr>
					<th>日期</th>
					<th>时间</th>
					<th>内容</th>
					<th>主讲人/主持人</th>
				</tr>
				<tr>
					<td rowspan="3">7月25日</td>
					<td>10:10-12:00</td>
					<td>新版基本公共卫生服务规范</td>
					<td>杭州师范大学医学院 章志量书记</td>
				</tr>
				<tr>
					<td>13:30-15:10</td>
					<td>乡村医生服务模式与签约服务</td>
					<td>杭州市凯旋街道卫生服务中心扈峻峰主任</td>
				</tr>
				<tr>
					<td>15:30-17:20</td>
					<td>健康浙江与基层卫生</td>
					<td>浙江省卫计委基卫处 吴燕苹副处长</td>
				</tr>
				<tr>
					<td rowspan="5">7月26日</td>
					<td>08:20-10:00</td>
					<td>糖尿病病人的基本治疗与健康管理</td>
					<td>浙二医院内分泌科副主任 任跃忠教授</td>
				</tr>
				<tr>
					<td>10:20-12:00</td>
					<td>高血压病人规范治疗与健康管理</td>
					<td>浙江预防医学与疾控高血压学会副主委 俞蔚教授</td>
				</tr>
				<tr>
					<td>13:00-14:30</td>
					<td>刮痧技术和拨罐技术的临床应用</td>
					<td>衢州市中医院针灸科 金瑛主任</td>
				</tr>
				<tr>
					<td>14:40-16:00</td>
					<td>肿瘤疾病的规范化管理</td>
					<td>浙江大学附属邵逸夫医院全科医学科 方力争主任</td>
				</tr>
				<tr>
					<td>16:20-18:00</td>
					<td>以病人为中心的病史采集</td>
					<td>浙江大学附属邵逸夫医院全科医学科 方力争主任</td>
				</tr>
				<tr>
					<td rowspan="2">7月27日</td>
					<td>08:20-10:00</td>
					<td>家庭现场急救技术的指导</td>
					<td>浙江省红十字会师资库一级培训师 凌杨青教授</td>
				</tr>
				<tr>
					<td>10:20-12:00</td>
					<td>应对新发传染病隔离防护技术</td>
					<td>浙江省疾控中心消毒与医院感染控制科 陆烨副教授</td>
				</tr>
			</table>
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
<%@include file="/qiantai_page/zhiboLoginComm.jsp" %>
<script src="${ctx}/qiantai_page/js/zhiboComm.js"></script>
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
    })
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
// 												     	$("#"+id).removeClass("active");
// 												     	$("#111000011").addClass("active");
//      													layer.close(index);
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
     	$("#111000011").addClass("active");
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