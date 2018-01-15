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
    <script src="${ctx}/qiantai_page/js/highchart/highcharts.js"></script>
    <script src="${ctx}/qiantai_page/js/highchart/modules/exporting.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/components-knob-dials.js"></script>
	<script src="${ctx}/qiantai_page/js/jquery.knob.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<div class="container">

<script >
/***
 * 程宏业
 2017-2-23
 判断图片加载是否成功
 */ 

function imgonload(obj,sex){
	if(sex==2){
	$(obj).attr("src","${ctx}/qiantai_page/img/user_avatar.jpg");
	}else{
		$(obj).attr("src","${ctx}/qiantai_page/img/user_avatar1.png");
	}
}



</script>


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
                    <h2 class="main_title_2 font_big text_center">
                        <span class="float_right font_m">
                        	<c:if test = "${cvSet.start_date != null}">
	                            <fmt:formatDate value="${cvSet.start_date}" pattern="yyyy-MM-dd"/>至
	                            <fmt:formatDate value="${cvSet.end_date}" pattern="yyyy-MM-dd"/>
							</c:if>
						</span>
                        <i class="fa fa-reply float_left font_big font_blue"></i>
                        ${cvSet.name}
                    </h2>
                    <div class="data_report">
                        <span class="title">数据报告</span>
                        <span class="circle1" >
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.points==0}">0</c:if>
                            <c:if test="${stat.points!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.percentage}" /></c:if>" data-fgColor="green"></input>
                            <span class="cont1">学习进度<span>
                            ${stat.percentage}%
                            </span></span>
                        </span>
                        <span class="circle1">
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.points==0}">0</c:if>
                            <c:if test="${stat.points!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.completedLogUnits*100/stat.points}" /></c:if>" data-fgColor="#fe6c6c"></input>
                            <span class="cont1">学习耗时<span>${stat.time_consuming}'</span></span>
                        </span> 
                        <span class="circle1">
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.cvs==0}">0</c:if>
                            <c:if test="${stat.cvs!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.completedCVs*100/stat.cvs}" /></c:if>" data-fgColor="#175fba"></input>
                            <span class="cont1">完成课程<span>${stat.completedCVs}/${stat.cvs}</span></span>
                        </span>
                        <span class="circle1">
                            <input class="knob" data-width="100" data-displayinput=false value="<c:if test="${stat.points==0}">0</c:if>
                            <c:if test="${stat.points!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.completedLogUnits*100/stat.points}" /></c:if>" data-fgColor="#f90"></input>
                            <span class="cont1">完成任务点<span>${stat.completedLogUnits}/${stat.points}</span></span>
                        </span>
                        <%-- <span class="circle">
                            <span class="border green"></span>
                            <span class="cont">学习进度<span>
                            <c:if test="${stat.units==0}">0%</c:if>
                            <c:if test="${stat.units!=0}"><fmt:formatNumber maxFractionDigits="1" value="${stat.logUnits*100/stat.units}" />%</c:if>
							</span></span>
                        </span>
                        <span class="circle">
                            <span class="border red"></span>
                            <span class="cont">学习耗时<span>${stat.units}'</span></span>
                        </span>
                        <span class="circle">
                            <span class="border blue"></span>
                            <span class="cont">完成课程<span>${stat.completedCVs}/${stat.cvs}</span></span>
                        </span>
                        <span class="circle">
                            <span class="border orange"></span>
                            <span class="cont">完成任务点<span>${stat.completedLogUnits}/${stat.points}</span></span>
                        </span> --%>
                    </div>
                    <div class="cont" style="float:left;width:100%;">
                        <div id="container" style="height:400px;margin:30px auto;float:left;width:90%;"></div>
                        <div style="height:400px;margin:30px auto;float:right;width:10%;">
                        	<select class="nianContainer" style="float:right;margin:10px auto;"></select>
                        	<select class="yueContainer" style="float:right;margin:10px auto;"></select>
                        </div>
                    </div>
                    <div class="half_cont">
                        <h2 class="main_title">项目信息</h2>
                        <div class="icon_cont">
                            <i class="fa fa-edit"></i>
                            <h4>${stat.notes}条</h4>
                            <h3>我的笔记</h3>
                        </div>
                        <div class="icon_cont">
                            <i class="fa fa-comments-o"></i>
                            <h4>${stat.discuss}条</h4>
                            <h3>我的讨论</h3>
                        </div>
                    </div>
                    <div class="half_cont">
                        <h2 class="main_title">最近学习章节</h2>
                        <ul class="union_list">
                        	<c:forEach items="${stat.lastUnits}" var="item" varStatus="status">
                        	<c:if test="${status.index ==0}">
                        		<li><span><a  href="javascript:void(0)" onclick="continueStudys('${id}','${proid}','${item.id}');">继续学习</a></span>${item.name}</li>
                        	</c:if>
                        	</c:forEach>
                        </ul>
                    </div>
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

        <%-- <%=request.getAttribute("yAsix")%>;
        <%=request.getAttribute("xAsix")%>; --%>
        //获取原始绘图数据
        var xAsixMap = <%=request.getAttribute("xAsixMap")%>;
        var yAsixMap = <%=request.getAttribute("yAsixMap")%>;
        if(xAsixMap&&yAsixMap){
	        //初始化绘图数据
	        toData(xAsixMap);
	        //第一次绘图
	        chartHighcharts(xAsixMap[$(".nianContainer").val()+'-'+$(".yueContainer").val()],yAsixMap[$(".nianContainer").val()+'-'+$(".yueContainer").val()],
	        		$(".nianContainer").val(),$(".yueContainer").val());
	        
	        //下拉框值改变触发（月份）
	        $(".yueContainer").change(function(){
	        	if(xAsixMap&&yAsixMap){
		            chartHighcharts(xAsixMap[$(".nianContainer").val()+'-'+$(".yueContainer").val()],yAsixMap[$(".nianContainer").val()+'-'+$(".yueContainer").val()],
		            		$(".nianContainer").val(),$(".yueContainer").val());
	        	}
	        });
        }
        
        $(".fa-reply").click(function () {
            document.location.href= "${ctx}/userInfo/studyRecordManage.do";
        });
        
		$("#tab2").click(function(){
        	document.location.href="${ctx}/userInfo/studyYearReport.do";
        });

		ComponentsKnobDials.init();    
		
		
    });
    
    /*
    <select class="nianContainer" style="float:right;">
                        		<option>年</option>
    */
    
    // 处理图标数据
    function toData(xAsixMap){
       	for (var key in xAsixMap){
       		var $optionNian = $("<option></option>");
       		var $optionYue = $("<option></option>");
       		var nian = key.split("-")[0];
       		var yue = key.split("-")[1];
       		$(".yueContainer").prepend($optionYue.attr({'value':yue}).text(yue+'月'));
       		var nianOld = $(".nianContainer option");
       		if(nianOld.length>0){
	       		$.each(nianOld,function(i,n){
	       			if($(n).val()==nian){
		       			return false
	       			}
		       		$(".nianContainer").prepend($optionNian.attr({'value':nian}).text(nian+'年'));
	       		});
       		}else{
       			$(".nianContainer").prepend($optionNian.attr({'value':nian}).text(nian+'年'));
       		}
		}
    }
    
    //绘制图表
    function chartHighcharts(xData,yData,year,month){
    	if(!year){
    		year='xxxx';
    	}
    	if(!month){
    		month='xx';
    	}
    	Highcharts.chart('container', {
            title: {
                text: year + ' 年',
                x: 10 //center
            },
            subtitle: {
                text: month + ' 月',
                x: 10
            },
            xAxis: {
            	title: {
                    text: '日期/天'
                },
                categories: xData
            },
            yAxis: {
                title: {
                    text: '时间/分'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true          // 开启数据标签
                    },
                    enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                }
            },
            series: [{
                name: '学习耗时',
                data: yData
            }],
            exporting: {
                enabled: false//禁用导出模块
            },
            credits: {
            	enabled: false
            }
        });
    }
        
	// 继续学习
	function continueStudys(sid,id,itemid){
		location.href = "${ctx}/entityManage/entityView.do?type=play3&cvsetId="+'${cvSet.id}'+"&unitId="+itemid+"&id="+'${cvSet.id}'+"&paramType=project&ToPlay3=true";
	};
    
</script>
</html>