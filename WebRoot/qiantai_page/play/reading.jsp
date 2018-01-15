<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
    <title>中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script type="text/javascript" src="${ctx}/play/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/jquery.mobile.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/iconfont.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/main.min.js"></script>
    <script type="text/javascript" src="${ctx}/play/js/swfobject.js"></script>
    
    <link href="${ctx}/play/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/play/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">
   	<link href="${ctx}/qiantai_page/css/test/iconfont.css" rel="stylesheet">  
  	<link href="${ctx}/qiantai_page/css/zhiboicon/iconfont.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
  <style type="text/css">
  
  .box{
height: 88px;
position:  fixed;
width: 70%;
z-index: 10;


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

  </style>
  
</head>
<body class="view">
<div class="bgc"></div>
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
<div class="container">
    <div class="course_video" style="position:relative;">
        <span class="logo"></span>
        <span class="go_back"><i class="fa fa-angle-left"></i> 返回项目详情</span>       
       <div class="box">
        <div class="course_title" style="display:none;">
            <h2 id="className">课程1--单元1.1 医学基础导论课程</h2>
            <h3>
                <span id="classType">类型：话题讨论</span><span id="quota"></span>
                <span id="classTeacher">授课教师：李老师</span>
                <span id="classDB">达标要求：</span>
            </h3>
        </div>
       </div> 
            <!-- 拓展阅读 -->
        <div style="width:100%;height:calc(100% - 100px);margin-left:25px;margin-top:100px;box-sizing:border-box;">
         <div>阅读说明：阅读目的、阅读范围说明等，不超过500字</div><br>
         <div style="overflow:hidden">
         	<div style="float:left;line-height:20px;">阅读材料：</div>
             <div style="float:left">
		         <c:forEach items="${sourceVal}" var="list">
		         <p style="margin:0;line-height:20px;">书籍名称${list.name }&nbsp; 出版社${list.source}</p>
		         </c:forEach>
	         </div>
         </div>
         <div>
         <textarea cols="120" rows="20"name="reading" id ="reading">请在此输入读后感,不少于300字</textarea>
         </div>
	         <button type="button" name="publish" class="btn btn_blue btn_big">发布</button>
        </div>
   
            
        <div id="swfDiv" style="display:none;margin-left:10px;width:100%;height:calc(100% - 100px);position:absolute;z-index:99999;top:100px;left:0;margin-bottom:30px;"></div> 
    </div>
    <div class="course_right" style="overflow-x:hidden;overflow-y:auto;height:calc(100vh - 40px);position:relative;">
        <div class="course_info">
            <c:if test="${fengmian!=null && fengmian!=''}">
               <img src="${fengmian}" class="float_right">
            </c:if>
            <c:if test="${fengmian==null || fengmian==''}">
               <img src="${ctx}/play/img/cover_1.jpg" class="float_right">
            </c:if>
            <h2>${project.name}</h2>
            <h3>项目负责人：${expert.name}</h3>
            <h3>来源：<c:choose><c:when test="${not empty org.name}">${org.name}</c:when><c:otherwise>中国继续医学教育网</c:otherwise></c:choose></h3>
            <div class="tabs aside_tabs">
                <ul class="tab_list">
                    <li id="tab1" class="tab active">目录</li>
                    <li id="tab2" class="tab">讨论</li>
                    <li id="tab3" class="tab">笔记</li>
                </ul>
                <div class="tab1 tab_cont">
                  <ul class="course_list">
                    <c:forEach items = "${list}"  var="list">
                       <%-- <li class="${list.id}" <c:if test = "${list.cv_type eq 2}">onclick = "javascript:gotoLiveView('${list.id}','${list.type}');" style="cursor:pointer;"</c:if>>${list.name}</li> --%>
                       <li class="${list.id}" id="cv${list.id}">${list.name}</li>
                       <ul <c:if test="${list.cv_type eq 2}">name="2"</c:if><c:if test="${list.cv_type != 2}">name="1"</c:if>>
	                       <input type="hidden" name="cvId" value="${list.id}"/>
	                       <input type="hidden" id="timerCvType${list.id}" value="${list.type}" />
                           <c:forEach items = "${list.unitList}" var="unit" varStatus="status">
	                            <li class="has_done"  name="makeType${list.id}">
	                            <c:if test = "${list.cv_type eq 2}">	                               	                              
	                               <c:if test="${unit.unitMakeType != 2}">
	                               		<%-- <i class="iconfont pm" id="pm${unit.id}">&#xe674;</i> --%>
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
	                            		<a class="unit" title="${unit.name}" ${unitLogStatusStyle}  href="javascript:void(0);" onclick="javascript:gotoLiveView('${list.id}','${list.type}','${list.cost_type}','${unit.id}','${list.startDate}','${list.endDate}');">单元${status.index+1}:${unit.name}</a>
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
		                            	<a class="unit" title="${unit.name}"  ${unitLogStatusStyle}  id="unit${unit.id}" href="javascript:void(0);" onclick="zkContrentNew('${list.id}','${unit.id}');">单元${status.index+1}:${unit.name}</a>
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
	                            	<a class="unit" title="${unit.name}"  ${unitLogStatusStyle}  id="unit${unit.id}" href="javascript:void(0);" onclick="zkContrentNew('${list.id}','${unit.id}');">单元${status.index+1}:${unit.name}</a>
	                            </c:if>
	                            	                            	                             
	                                 <!-- <i class="iconfont" style="font-size: 16px; color: rgb(18,188,255); float: right;">&#xe603;</i> -->
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
                <div class="tab2 tab_cont" style="display:none">
                  <form id="talkForm" method="post">
                    <div class="comment_form">
                        <label>内容：</label>
                        <textarea name="comment_cont" id="talk" onkeyup="jisuanzishu('talk');" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"></textarea>
                        <div class="foot">
                            <button name="comment_submit" type="button" class="btn btn_orange btn_small" onclick="saveTalk();">发布</button>
                            <span class="text_count">还能输入<span class="num_count" id="talkCount">200</span>字</span>
                        </div>
                    </div>
                    <ul class="comments_list" id="talkList"></ul>
                  </form>
                </div>
                <div class="tab3 tab_cont" style="display:none">
                   <form id="noteForm" method="post">
	                    <div class="comment_form">
	                        <label>内容：</label>
	                        <textarea name="comment_cont" id="notice" onkeyup="jisuanzishu('notice');" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"></textarea>
	                        <div class="foot">
	                            <button name="comment_submit" type="button" class="btn btn_blue btn_small" onclick="saveNotece();">保存笔记</button>
	                            <span class="text_count">还能输入<span class="num_count" id="noticeCount">200</span>字</span>
	                        </div>
	                     </div>
	                      <ul class="comments_list" id="noteList"></ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    //设置资源管理系统全局变量
   var adminURL = "${ctxAdminURL}";//"http://101.200.85.213:8090/admin"; 
   basePath="${ctx}";
 // var adminURL = "http://localhost:8081/admin";
    
    //设置培训后台全局变量
  var peixunURL = "${ctxPeixunURL}";//"http://101.200.85.213:8090/peixun";
    
 // var peixunURL = "http://localhost:8081/test_peixun";
   
    var unitId = '';
    
    var projectId = '${project.id}';
    
    var userId = '${userId}';
    
    var swfobj ;
    var vLength   = 0 ; //YHQ，2017-03-08，视频长度（秒）
    var vPosition = 0 ; //YHQ，2017-03-08，视频播放位置（秒）
    var vStartPosition = -1 ; //YXT，2017-07-13，视频第一次开始播放时间点（秒）
    var vEchoLength = 0 ; //YXT，2017-07-13，视频回放时间（秒）
    var vMaxPlayLength = 0 ; //YXT，2017-07-13，视频本次播放最大时间点（秒）-- 视频回放记录
    var vLastEchoLength = -1 ; //YXT，2017-07-13，记录视频上次回放时间点（秒）-- 视频回放记录
    var vFlag = false ; //YHQ，2017-03-08，是否视频
    var videoCvsetId = 0;//YHQ，2017-03-09
    var videoCvId = 0 ;//YHQ，2017-03-09
    var videoUnitId = 0;//YHQ，2017-03-09
    var videoLogStudyId = 0;//YHQ，2017-03-09
    var videoStartDate = getNowFormatDate() ;//YHQ，2017-03-09
    var vSaveFlag = true ; //YHQ，2017-05-11，是否需要保存视频学习记录    
    var vPlayFlag = false ; //YXT，2017-07-13，是否开始观看视频（过滤广告时间）    
    var vPlayBegin = false ; //YXT，2017-08-11，开始观看视频（用做屏蔽定位到上次观看时间的拖拽事件）    
    var saveDiscuss = true ; //YXT，2017-08-14，保存讨论（防止保存操作重复触发）
    var saveNote = true ; //YXT，2017-08-14，保存笔记（防止保存操作重复触发）
    var currVideoPlayLengthMax = 0 ; //YXT，2017-08-21，视频快进的最大时间点
    
    var  flags = false; //是否绑定学习卡
    
    var currCvSetIdVal = '${project.id}';//当前项目
    var currCvIdVal;//当前课程
	var currUnitIdVal;//当前单元id
	var currUnitTypeVal;//当前单元类型
	var currUnitPointVal;//当前单元任务点
	var currUnitQuotaVal;//当前单元任务点指标
	var currUnitMediaTypeVal;//当前单元媒体类型
	var currUnitMediaIdVal;//当前单元媒体id
	var currUnitVideoPlayLengthVal;//当前单元视频播放长度
	var currUnitLogStatusVal;//当前单元学习记录状态
	var currUnitVideoLogStudyId;//当前单元视频学习记录id
	
    $(window).bind('beforeunload',function () {  	     
       var evt = window.event || arguments[0];  
       var userAgent = navigator.userAgent;  
       if (userAgent.indexOf("MSIE") > 0) {  
           var n = window.event.screenX - window.screenLeft;  
           var b = n > document.documentElement.scrollWidth - 20;  
           if (b && window.event.clientY < 0 || window.event.altKey) {  
              //同步更新学习记录中视频观看长度 
		  	  saveVideo(false,currUnitMediaTypeVal,currUnitLogStatusVal,currUnitVideoPlayLengthVal,currUnitVideoLogStudyId,currCvIdVal,currUnitIdVal) ;
           }else {  
               //同步更新学习记录中视频观看长度 
		  	  saveVideo(false,currUnitMediaTypeVal,currUnitLogStatusVal,currUnitVideoPlayLengthVal,currUnitVideoLogStudyId,currCvIdVal,currUnitIdVal) ;
           }  
       }else {  
           //同步更新学习记录中视频观看长度 
		   saveVideo(false,currUnitMediaTypeVal,currUnitLogStatusVal,currUnitVideoPlayLengthVal,currUnitVideoLogStudyId,currCvIdVal,currUnitIdVal) ;
       } 	
       //同步统计记录视频实际学习时间
       playCensus(false,currCvSetIdVal,currCvIdVal,currUnitIdVal);
	}) ; 

    $(function () {
        $(".go_back").click(function () {
            document.location.href = ctxJS + "/projectDetail.do?id=${project.id}";
        });
        
        //判断是否绑定学习卡，防止用户直接打开地址，其实就是违法访问时也能观看视频
        checkbind('');           
      
        //接着上次学习的单元继续学习
        
        <c:if test="${currStudyUnitId != null}">
	      if (flags) {
	         $("#unit${currStudyUnitId}").click() ;
	      }       		    		
        </c:if>
        
        InitializationCVAndCVSetLog(currCvIdVal);
    });    
    
    
	//点击单元切换
    function zkContrentNew(cvIdVal,unitIdVal) {
       if (flags) {
       }else {
       		checkbind('');
       		return ;          
       }
       InitializationCVAndCVSetLog(cvIdVal);       
       //1.切换单元前————保存当前课程的学习记录，代码调用用同步调用，即保存完再切换到下一单元！！！！
       if (currUnitIdVal != undefined && currUnitMediaTypeVal != '') {
       		InitializationCVAndCVSetLog(currCvIdVal);
       		//保存学习记录
          	$.ajax({
			    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId=" + currCvSetIdVal + "&cvId="+currCvIdVal+"&unitId="+currUnitIdVal,
			    type: 'POST',
			    dataType: 'json',
	            async: false,
			    success: function(data){   
			    	var result = eval(data);
			    	if(result.message=='success'){
			    		//保存学习记录成功	                    
			    	} else if(result.message=='noLogin'){
			    		alert('请登录后再学习，否则无法保存学习记录！') ;
			    	}else {
			    		alert("保存单元学习记录失败！") ;
	                }                
			    },
			    error: function(data){
			    	alert('保存单元学习记录出错：' + data) ;
			    	return ;		    
			    } 
		  	});
		  	//同步更新学习记录中视频观看长度 
		  	saveVideo(false,currUnitMediaTypeVal,currUnitLogStatusVal,currUnitVideoPlayLengthVal,currUnitVideoLogStudyId,currCvIdVal,currUnitIdVal) ;
		  	//统计记录视频实际学习时间
       		playCensus(false,currCvSetIdVal,currCvIdVal,currUnitIdVal);
		  	/**		重置实际学习时间变量	**/
		  	vStartPosition = -1 ; //YXT，2017-07-13，视频第一次开始播放时间点（秒）
		    vEchoLength = 0 ; //YXT，2017-07-13，视频回放时间（秒）
		    vMaxPlayLength = 0 ; //YXT，2017-07-13，视频本次播放最大时间点（秒）-- 视频回放记录
		    vLastEchoLength = -1 ; //YXT，2017-07-13，记录视频上次回放时间点（秒）-- 视频回放记录
		    vPlayFlag = false ; //YXT，2017-07-13，是否开始观看视频（过滤广告时间）    
		    var currVideoPlayLengthMax = 0 ; //YXT，2017-08-21，视频快进的最大时间点
		  	/**		重置实际学习时间变量	**/
		  	
       }
                     
       //2.切换到下一单元，1.加载单元信息
       $.ajax({
		    url:"${ctx}/entityManage/entityView.do?type=queryUnitInfo&unitId=" + unitIdVal,
		    type: 'POST',
		    dataType: 'json',
            async: false,
		    success: function(data){   
		    	var result = eval(data);
		    	if(result.message=='noLogin'){
		    		alert('没有登录，请登录！') ;
		    		return ;		    	    		    		
		    	} else if(result.message=='success'){
		    		if (result.unitObj != null && result.unitObj != '') {
		    		   currCvIdVal = cvIdVal;
		    		   currUnitIdVal = unitIdVal ;
		    		   currUnitTypeVal = result.unitObj.type;
					   currUnitPointVal = result.unitObj.point;//当前单元任务点
					   currUnitQuotaVal = result.unitObj.quota;//当前单元任务点指标
					   currUnitMediaTypeVal = result.unitObj.mediaType;//当前单元媒体类型
					   currUnitMediaIdVal = result.unitObj.mediaId;//当前单元媒体id
					   currUnitVideoPlayLengthVal = result.unitObj.videoPlayLength;//当前单元视频播放长度
					   currUnitLogStatusVal = result.unitObj.logStatus;//当前单元学习记录状态
		    		} else {
		    			alert('切换到下一单元unitObj无单元信息！') ;
		    			return ;
		    		}
		    	} else {
		    		alert('切换到下一单元else') ;
		    		return ;
		    	}		    	
		    },
		    error: function(data){
		    	alert('切换到下一单元出错：' + data) ;
		    	return ;		    
		    } 
		});
		
		//2.切换到下一单元，2.设置课程信息可见
    	$(".course_title").css("display","block");
    	//设置课程名称
    	$("#className").text($("#cv" + currCvIdVal).html() + "--" + $("#unit" + currUnitIdVal).html());
    	//设置课程主讲教师
    	 $.ajax({
   		    url:"${ctx}/entityManage/entityView.do?type=queryTeacherByCVId",
   		    type: 'POST',
   		    dataType: 'json',
   		    data:{
   		    	cvId:currCvIdVal
   		    },
   		    success: function(data){   
   		    	var result = eval(data);
   		    	if(result.message=='success'){
   		    		$("#classTeacher").text("授课教师："+result.teacherName);
   		    	}
   		    },
		    error: function(data){
		    	alert('加载授课教师出错：' + data) ;
		    	return ;		    
		    }
   		});
   		
   		//2.切换到下一单元，3.
   		if(currUnitTypeVal == '1'){
    		 $("#classType").text('类型：理论讲解 ');
    	}else if(currUnitTypeVal == '2'){
    		 $("#classType").text('类型：主题讨论');
    	}else if(currUnitTypeVal == '3'){
    		 $("#classType").text('类型：随堂考试');
    	}else if(currUnitTypeVal == '4'){
    		 $("#classType").text('类型：操作演示');
    	}else if(currUnitTypeVal == '5'){
    		 $("#classType").text('类型：扩展阅读');
    	}else if(currUnitTypeVal == '6'){
    		 $("#classType").text('类型：病例分享');
    	}
		
		//2.切换到下一单元，4.
		if (currUnitPointVal == '1') {
			if (currUnitQuotaVal != '') {
				if (currUnitMediaTypeVal == 'video')   $("#classDB").text('达标要求：完成' + currUnitQuotaVal + '%的视频观看');
				if (currUnitMediaTypeVal == 'talk')    $("#classDB").text('达标要求：至少发表' + parseInt(currUnitQuotaVal) + '条讨论发言');
				if (currUnitMediaTypeVal == 'bingli')  $("#classDB").text('达标要求：至少发表' + parseInt(currUnitQuotaVal) + '条病例分析');
				if (currUnitMediaTypeVal == 'paper')   $("#classDB").text('达标要求：取得' + currUnitQuotaVal + '分及以上成绩');
			}else if (currUnitTypeVal == '5') {
				$("#classDB").text('达标要求：输入读后感300字');
			} else {
				$("#classDB").text('达标要求：是任务点但未设置达标要求');
			}			 
		} else {
			$("#classDB").text('达标要求：非任务点无达标要求');
		}
		
		//2.切换到下一单元，保存新单元的学习记录
		if (currUnitMediaTypeVal != '') {
			//延时1秒
			var now = new Date();
			var exitTime = now.getTime() + 1000 ;
			var exitFlag = true ;
			while (exitFlag) {
		       now = new Date();
		       if (now.getTime() > exitTime) exitFlag = false ;
		    }
			
        	$.ajax({
			    url:"${ctx}/study/logstudy.do?mode=addUnit&cvsetId=" + currCvSetIdVal + "&cvId="+currCvIdVal+"&unitId="+currUnitIdVal,
			    type: 'POST',
			    dataType: 'json',
	            async: false,
			    success: function(data){   
			    	var result = eval(data);
			    	if(result.message=='success'){
			    		//保存学习记录成功
	                    currUnitVideoLogStudyId = parseInt(result.logStudyId) ;   //YHQ，2017-03-09 
			    	} else if(result.message=='noLogin'){
			    		alert('请登录后再学习，否则无法保存学习记录！') ;
			    	}else {
			    		alert("保存单元学习记录失败！") ;
	                }                
			    },
			    error: function(data){
			    	alert('保存单元学习记录出错：' + data) ;
			    	return ;		    
			    } 
			});
		}
		
		//2.切换到下一单元，5.判断单元里的素材类型			
        if (currUnitMediaTypeVal != '') {//null为无类型,paper为试卷,talk为讨论点,bingli为病例,video为视频
          //2.切换到下一单元，6.设置置灰
    	  $("#unit" + currUnitIdVal).css("color","#ADADAD");
    	  $("#pm" + currUnitIdVal).html("&#xe639;");
    	  //先清空div内容
    	  $("#preview").empty();
    	  //隐藏欢迎页面
    	  $("#fengmian").css("display","none");
    	  if (currUnitLogStatusVal == '1'){
    	  		$("#pm" + currUnitIdVal).html("&#xe639;");
    	  }
    	  if (currUnitLogStatusVal == '2'){
    	  		$("#pm" + currUnitIdVal).html("&#xe61c;");
    	  }
    	  
    	  try {        	   
        	   //getSWF("playerswf").pause();
        	   $("#playerswf").remove();
        	   $("#swfDiv").css("display","none");
          }catch(e){}
          
          //先设置显示区颜色
          $("#preview").css("background","black");
          //先清空div内容
    	  $("#preview").empty();
    	  //隐藏欢迎页面
    	  $("#fengmian").css("display","none");
    	          	
          //2.切换到下一单元，7.加载单元信息
          if (currUnitMediaTypeVal == 'video') {	          	
	          	swfobj = new SWFObject('http://union.bokecc.com/flash/player.swf', 'playerswf', '100%', '100%', '8');
		        swfobj.addVariable( "userid" , "078E396B1332FD8E");	//	partnerID,用户id
		        swfobj.addVariable( "videoid" , currUnitMediaIdVal);	//	spark_videoid,视频所拥有的 api id
		        swfobj.addVariable( "mode" , "api");	//	mode, 注意：必须填写，否则无法播放
		        swfobj.addVariable( "autostart" , "true");	//	开始自动播放，true/false
		        swfobj.addVariable( "jscontrol" , "true");	//	开启js控制播放器，true/false
		        swfobj.addVariable( "control_enable" , "1");       
		        swfobj.addParam('allowFullscreen','true');
		        swfobj.addParam('allowScriptAccess','always');
		        swfobj.addParam('wmode','transparent');       
		        swfobj.write('swfDiv');    
		        $("#swfDiv").css("display","block");
		        $("#preview img").css("display","none");
		        videoStartDate = getNowFormatDate() ;	        	            	
          }                              
          if (currUnitMediaTypeVal == 'paper') {
          		preview(currUnitMediaIdVal,'paper',currUnitIdVal);
          }
          if (currUnitMediaTypeVal == 'talk') {
          		preview(currUnitMediaIdVal,'talk',currUnitIdVal);
          }
          if (currUnitMediaTypeVal == 'bingli') {
          		preview(currUnitMediaIdVal,'bingli',currUnitIdVal);
          }
          //2.切换到下一单元，8.查询笔记信息
    	  queryUnitNotes(currUnitIdVal);    	
    	  //2.切换到下一单元，9.查询讨论记录
    	  queryUnitTalks(currUnitIdVal);          
       } else {//未上上传素材的单元
       		//设置置灰
    		$("#unit" + unitIdVal).css("color","#ADADAD");
    		$("#fengmian").css("display","block");
    		//先清空div内容
    	  	$("#preview").empty();
    	  	$("#preview").css("background","white");
       		alert('即将开课，敬请期待！') ;
       }
    }        
    
    //播放完毕
    function on_spark_player_stop() {    	
        var currPosition = 0 ;
        try {
            currPosition = document.getElementById("playerswf").getPosition() ;
        }catch(err){}        
        if (currPosition > vPosition) vPosition = currPosition ;
                        
        //同步更新学习记录中视频观看长度 
		saveVideo(false,currUnitMediaTypeVal,currUnitLogStatusVal,currUnitVideoPlayLengthVal,currUnitVideoLogStudyId,currCvIdVal,currUnitIdVal) ;		
        //统计记录视频实际学习时间     
        playCensus(false,currCvSetIdVal,currCvIdVal,currUnitIdVal)  ; 
        
    	 $("#swfDiv").css("display","none");
    	 $("#fengmian").css("display","block");
    	 
    	 var currRow  ;
    	 $(".unit").each(function(index,value){
			if ($(this)[0].id == "unit" + currUnitIdVal ) {
				currRow = index ;				
		    }
		    if (currRow != undefined) {
		    	if (index == (currRow + 1)) {
			       $(this)[0].onclick() ;
			       return true ;
				}
		    }		    
		 });
    }
    
    //暂停播放
    function on_spark_player_pause() {
        //同步更新学习记录中视频观看长度 
		saveVideo(false,currUnitMediaTypeVal,currUnitLogStatusVal,currUnitVideoPlayLengthVal,currUnitVideoLogStudyId,currCvIdVal,currUnitIdVal) ;
    	$("#fengmian").css("display","block");
    }
    
    //开始播放时回调，YHQ，2017-03-08，获取视频长度（秒）
    function on_spark_player_start() {    	
        videoStartDate = getNowFormatDate() ;
        try {
            vLength = document.getElementById("playerswf").getDuration() ;
        }catch(err){}
        
        if (currUnitLogStatusVal != '2') {//未学完
           	var currVideoPlayLengthDB = 0 ;
	        if (currUnitVideoPlayLengthVal != '') {
	        	try {
	        		currVideoPlayLengthDB = parseInt(currUnitVideoPlayLengthVal) ;
	        	}catch(err){}
	        }
	        if (currVideoPlayLengthDB > 0) {
	           try {
	        	   currVideoPlayLengthMax = currVideoPlayLengthDB;
	        	   vPlayBegin = true;
	        	   document.getElementById("playerswf").spark_player_seek(currVideoPlayLengthDB);
	           }catch(err){
	        	   vPlayBegin = false;
	           } 
	        }
        }        
                               
        if(vStartPosition==-1){
        	vStartPosition = document.getElementById("playerswf").getPosition();
        	vMaxPlayLength = vStartPosition;
        }
        vPlayFlag = true; //是否开始观看视频（过滤广告时间）
    }        
    
    //更新学习记录中视频观看长度   
    function saveVideo(asyncType,unitMediaTypeVal,unitLogStatusVal,unitVideoPlayLengthVal,unitVideoLogStudyId,cvIdVal,unitIdVal) {        
        if (unitMediaTypeVal == 'video') {
       		if (unitLogStatusVal != '2') {//未学完
       			var currVideoPosition = 0 ;
       			var currVideoTotalLength = 0 ;
       			var currVideoPlayLengthDB = 0 ;
		        try {
		            currVideoTotalLength = document.getElementById("playerswf").getDuration() ;
		            currVideoTotalLength = parseInt(currVideoTotalLength) ;
		        }catch(err){}
		        try {		            
		            currVideoPosition    = document.getElementById("playerswf").getPosition() ;
		            currVideoPosition 	 = parseInt(currVideoPosition) ;
		        }catch(err){}
		        
		        if (unitVideoPlayLengthVal != '') {
		        	try {
		        		currVideoPlayLengthDB = parseInt(unitVideoPlayLengthVal) ;
		        	}catch(err){}
		        }
		        var saveVideoFlag = false ;
		        if (currVideoPosition != 0 && currVideoTotalLength != 0  && currVideoPosition > currVideoPlayLengthDB) saveVideoFlag = true ;
		        if (saveVideoFlag) {
		        	$.ajax({
		     		    url:"${ctx}/study/logstudy.do?mode=saveVideoStudyLog",
		     		    type: 'POST',
		     		    dataType: 'json', 
		     		   	async: asyncType,  
		     		    data:{
		     		    	logStudyCvUnitId:unitVideoLogStudyId,
		     		    	cvId:cvIdVal,                        
		                    cvUnitId:unitIdVal,
		     		    	videoLength:currVideoTotalLength,
		                    videoPlayLength:currVideoPosition,
		                    startDate:videoStartDate
		     		    },
		     		    success: function(data){   
		     		    	var result = eval(data);
		     		    	if(result.message=='success'){
		     		    		if(result.isPass=='1'){
		     		    			$("#pm" + unitIdVal).html("&#xe61c;");
		     		    		}
		     		    	}else if(result.message=='noLogin'){
		     		    		alert('请登录后再学习，否则无法保存学习记录！') ;
		     		    	}else{
		     		    		alert("保存单元学习记录失败！") ;
		     		    	}
		     		   },
					   error: function(data){
					    	alert('保存单元学习记录出错：' + data) ;						    	
					   }
		     	   });
		        }			        			       
            }          		
        }      
    }
    
    /**
     * @author yxt
     * @time   2017-07-13
     * 说     明： 统计记录视频实际学习时间
     **/
    function playCensus(asyncType,cvSetIdVal,cvIdVal,cvUnitIdVal) {
        if (vPlayFlag) {
	        var currPosition = 0 ;
	        try {
	            currPosition = document.getElementById("playerswf").getPosition() ;
	        }catch(err){
	        	currPosition = vMaxPlayLength;
	        }
	        if(vLastEchoLength>-1){
	        	if(currPosition > vMaxPlayLength){
	        		vEchoLength += vMaxPlayLength - vLastEchoLength;
	        		vMaxPlayLength = currPosition;
	        	}else{
	        		if(currPosition>vLastEchoLength){
		        		vEchoLength += currPosition - vLastEchoLength;
	        		}else{
	        			vEchoLength += vLastEchoLength - currPosition;
	        		}
	        	}
	        }else{
	        	if(currPosition > vMaxPlayLength){
	            	vMaxPlayLength = currPosition;
	            }
	        }
        	var vec = Math.round(vEchoLength);
        	var vst = Math.round(vStartPosition);
        	var vma = Math.round(vMaxPlayLength);
        	var vle = Math.round(vLength);
            $.ajax({
     		    url:"${ctx}/study/logstudy.do?mode=saveCensusVideoStudyLog",
     		    type: 'POST',
     		    dataType: 'json', 
     		   	async: asyncType,
     		    data:{
					cvSetId:cvSetIdVal,//项目ID
     		    	cvId:cvIdVal,//课程ID                        
                    cvUnitId:cvUnitIdVal,//单元ID
                    videoEchoLength:vec,//视频回放时间
                    videoStartLength:vst,//视频播放开始时间点
                    videoEndLength:vma,//视频本次播放最大时间点
     		    	videoLength:vle//视频播放总时间
     		    },
     		    success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){
     		    		
     		    	}else if(result.message=='noLogin'){
     		    		alert("请先登录.");
     		    	}else{
     		    		alert("保存实际视频播放记录失败.");
     		    	}
     		   },
			   error: function(data){
			    	alert('保存实际视频播放记录出错：' + data) ;						    	
			   }
     	    });
        }        
    }
    
    /**
     * @author yxt
     * @time   2017-07-13
     * @param  from	to
     * 说     明： 向后拖动视频进度条触发
     **/
	function recodLastTimeCallbackCustom(from,to){
		if(from>vMaxPlayLength){
			vMaxPlayLength = from;
		}
		if(vLastEchoLength>-1){
			vEchoLength += from - vLastEchoLength;			
		}
		vLastEchoLength = to;
	}
    
    
   /**
     * @author  张建国
     * @time    2017-01-17
     * 说        明：保存单元笔记
     **/
    function saveNotece(){
    	var saveUnitId = currUnitIdVal ;
    	var notice = $("#notice").val().length;
    	//首先判断手否选中了单元
    	if(saveUnitId == undefined || saveUnitId == ''){
    		alert("请先选择课程单元.");
    	}else if(notice==0 || notice>200){
    		alert("笔记内容请在200字以内.");
    	}else{
    		if(saveNote){
    			saveNote = false;
    			$.ajax({
         		    url:"${ctx}/entityManage/entityView.do?type=addUnitNote",
         		    type: 'POST',
         		    dataType: 'json',
         		    data:{
         		    	cvsetId:projectId,
         		    	unitId:saveUnitId,
         		    	content:$("#notice").val()
         		    },
         		    success: function(data){
         		    	saveNote = true;
         		    	var result = eval(data);
         		    	if(result.message=='success'){     		    		
         		    		queryUnitNotes(saveUnitId);     		    		
         		    		$("#notice").val('');
         		    	}else if(result.message=='noLogin'){
         		    		alert("请先登录.");
         		    	}else{
         		    		alert("保存单元笔记失败.");
         		    	}
         		    },
    			    error: function(data){
    			    	saveNote = true;
    			    	alert('保存单元笔记出错：' + data) ;			    	
    			    }
         		});
            }else{
            	alert("不要重复保存笔记");
            }
    	} 	
    }
    
    /**
     * @author  张建国
     * @time    2017-01-17
     * 说        明：保存单元讨论
     **/
    function saveTalk(){
    	var saveUnitId = currUnitIdVal ;
    	var talk = $("#talk").val().length;
    	//首先判断手否选中了单元
    	if(saveUnitId == undefined || saveUnitId == ''){
    		alert("请先选择课程单元.");
    		return ;
    	}else if(talk==0 || talk>200){
    		alert("讨论内容请在200字以内.");
    		return ;
    	}else{
    		if(saveDiscuss){
    			saveDiscuss = false;
    			$.ajax({
         		    url:"${ctx}/entityManage/entityView.do?type=addUnitTalk",
         		    type: 'POST',
         		    dataType: 'json',
         		    data:{
         		    	cvsetId:projectId,
         		    	unitId:saveUnitId,
         		    	content:$("#talk").val()
         		    },
         		    success: function(data){   
         		    	saveDiscuss = true;
         		    	var result = eval(data);
         		    	if(result.message=='success'){     		    		
         		    		queryUnitTalks(saveUnitId);     		    		
         		    		$("#talk").val('');
         		    	}else if(result.message=='noLogin'){
         		    		alert("请先登录.");
         		    	}else{
         		    		alert("保存单元讨论失败.");
         		    	}
         		    },
    			    error: function(data){
    			    	saveDiscuss = true;
    			    	alert('保存单元讨论出错：' + data) ;			    	
    			    }
         		});
    		}else{
    			alert("不要重复发布讨论");
    		}
    	} 	
    }
    
    /**
     * @author 张建国
     * @time   2017-01-17
     * @param  unitId
     * 说     明：根据单元Id查询发表的笔记记录
     **/
     function queryUnitNotes(unitId){
    	//先清空笔记li
    	$("#noteList").find("li").remove(); 
    	if(unitId != undefined && unitId !=''){
    		//通过AJAX查询笔记记录
    		$.ajax({
     		    url:"${ctx}/entityManage/entityView.do?type=queryUnitNote",
     		    type: 'POST',
     		    dataType: 'json',
     		    data:{
     		    	unitId:unitId,
     		    	cvsetId:projectId
     		    },
     		    success: function(data){   
     		    	var result = eval(data);
     		    	if(result.message=='success'){
     		    		//对返回的集合进行操作
     		    		if(result.list!=null && result.list.length>0){
     		    			for(var i=0;i<result.list.length;i++){
     		    				var userImage = result.list[i].user_image;
     		    				if(userImage == null || userImage == ""){
     		    					var sex = result.list[i].sex;
     		    					if(sex == 2){
     		    						userImage = "${ctx}/qiantai_page/img/user_avatar1.png";
     		    					}else{
     		    						userImage = "${ctx}/qiantai_page/img/user_avatar.jpg";
     		    					}
     		    				}
     		    				var content = "<li>"
     	                                    + "  <span class='avatar tiny'><img style='width:100%;' src='"+userImage+"'></span>"
     	                                    + "    <p>"
     	                                    + "      <span class='name'>"+result.list[i].real_name+"</span>"
     	                                    + "      <span class='date'>"+result.list[i].noteDate.slice(0,result.list[i].noteDate.indexOf("."))+"</span>"
     	                                    + "    </p>"
     	                                    + "    <p class='cont' style='word-wrap:break-word;'>"+result.list[i].noteContent+"</p>"
     	                                    + "</li>";
     	                        //动态追加
     	                        $("#noteList").append(content);
     		    			}
     		    		}
     		    	}
     		    },
			    error: function(data){
			    	alert('加载笔记出错：' + data) ;			    	   
			    } 
     		});
    	}
    }
    
     /**
      * @author 张建国
      * @time   2017-01-17
      * @param  unitId
      * 说     明：根据单元Id查询发表的讨论记录
      **/
    function queryUnitTalks(unitId){
     	//先清空笔记li
     	$("#talkList").find("li").remove(); 
     	if(unitId!=null && unitId!=''){
     		//通过AJAX查询笔记记录
     		$.ajax({
      		    url:"${ctx}/entityManage/entityView.do?type=queryUnitTalk",
      		    type: 'POST',
      		    dataType: 'json',
      		    data:{
      		    	unitId:unitId,
      		    	cvsetId:projectId
      		    },
      		    success: function(data){   
      		    	var result = eval(data);
      		    	if(result.message=='success'){
      		    		//对返回的集合进行操作
      		    		if(result.list!=null && result.list.length>0){
      		    			for(var i=0;i<result.list.length;i++){
      		    				var userImage = result.list[i].user_image;
     		    				if(userImage == null || userImage == ""){
     		    					var sex = result.list[i].sex;
     		    					if(sex == 2){
     		    						userImage = "${ctx}/qiantai_page/img/user_avatar1.png";
     		    					}else{
     		    						userImage = "${ctx}/qiantai_page/img/user_avatar.jpg";
     		    					}
     		    				}
      		    				var content = "<li>"
      	                                    + "  <span class='avatar tiny'><img style='width:100%;' src='"+userImage+"'></span>"
      	                                    + "    <p>"
      	                                    + "      <span class='name'>"+result.list[i].real_name+"</span>"
      	                                    + "      <span class='date'>"+result.list[i].discussDate.slice(0,result.list[i].discussDate.indexOf("."))+"</span>"
      	                                    + "    </p>"
      	                                    + "    <p class='cont' style='word-wrap:break-word;'>"+result.list[i].discussContent+"</p>"
      	                                    + "</li>";
      	                        //动态追加
      	                        $("#talkList").append(content);
      		    			}
      		    		}
      		    	}
      		    },
			    error: function(data){
			    	alert('加载讨论出错：' + data) ;			    	   
			    }
      		});
     	}
     } 
    
    /*
      * @author 张建国
      * @time   2017-01-22
      * 说      明: 计算textarea中的字数 
      */
      function jisuanzishu(type){
    	  if(type=='talk'){
    		  var length = $("#talk").val().length;
        	  if(length>200){
        		  $("#talkCount").text(length);  
        		  alert("字数请在200字以内.");
        	  }else{
        		  $("#talkCount").text(200-length);  
        	  }
    	  }else if(type=='notice'){
    		  var length = $("#notice").val().length;
        	  if(length>200){
        		  $("#talkCount").text(length);  
        		  alert("字数请在200字以内.");
        	  }else{
        		  $("#noticeCount").text(200-length);  
        	  } 
    	  } 
      }
     
     /*
      * @author 张建国
      * @time   2017-02-10
      * 说     明： 点击病例或试卷事件
      */
      function preview(id,type,unitId){
    	 $("#preview").css("background","white");
    	 if(type=='bingli'){ 
    		 var html = "<iframe src=\""+adminURL+"/caseManage/caseEdit2.do?type=1&caseId="+id+"&unitId="+unitId+"&mode=1\" style=\"width:100%;height:100%;left:0px;\"></iframe>";
    		 document.getElementById("preview").innerHTML = html; 
    	 }
    	 if(type=='paper'){
    		 var html = "<iframe src=\""+peixunURL+"/paperManage/paperView.do?handle=exam&paperId="+id+"&unitId="+unitId+"&userId=${userId}\" style=\"width:100%;height:100%;left:0px;\"></iframe>"
    		 document.getElementById("preview").innerHTML = html; 
    	 }
    	 if(type=='talk'){
    		 var html = "<iframe src=\""+peixunURL+"/talk/topicDiscussionManage.do?handle=queryOne&talkId="+id+"&unitId="+unitId+"\" style=\"width:100%;height:100%;left:0px;\"></iframe>"
    		 document.getElementById("preview").innerHTML = html; 
    	 }
     }
     
     //日期格式化，YHQ 2017-03-09
     function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }
    
    //###################################################
    //直播入口操作
    function gotoLiveView(id,zhiboType,costType,unitId,startDate,endDate) {
    	InitializationCVAndCVSetLog(id);
    	zhiboType = $("#timerCvType"+id).val();
    	if(zhiboType == 2){
    		alert("直播时间为"+startDate.split('.')[0]+" -- "+endDate.split('.')[0]+"，尚未开始！");
    		return;
    	}else if(zhiboType == 3){
			alert("直播已结束！");
    		return;
		}else if(zhiboType == 4){
			//alert("准备进入直播回放！");
			$.ajax({
				type: 'POST',
				url:  ctxJS + '/study/logstudy.do?mode=queryLogin&id='+id,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'noLogin'){
						//弹出登录窗口
						$(".popup").show();
					}else if(result.message=='success'){
						//$("#zbcvid").val(id);//初始化课程ID
						//checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
						//if(flags ==true){
							window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+id+"&unitId="+unitId;
						//}
					};
				}
		    });
		    return;
		}else if(zhiboType == 5){
			alert("直播结束，视频转码中，敬请期待！");
    		return;
		}
    	else{
	    	window.location.href = ctxJS + "/viewLiveListInterface.do?mode=getSignk&cvId="+id;
    	}
    }
    //###################################################
    
     //判断是否绑定学习卡
    function checkbind(costType){
	   	$.ajax({
		    url:"${ctx}/study/logstudy.do?mode=CheckBind&cvsetId="+projectId+"&costType="+costType,
		    type: 'POST',
		    async: false,
		    dataType: 'json',
		    success: function(data){   
		    	var result = eval(data);
				if(result.card == '0'){
					flags = true;
					if (currUnitIdVal == undefined) { //无点击事件，初始加载
						<c:if test="${currStudyUnitId != null}">
							$("#${currStudyUnitId}").click() ;					         
				        </c:if>
					} else {
							$("#" + currUnitIdVal).click() ;
					}
				}else{
					// 弹出支付窗口
				   $(".bgc").show();
				   $(".paybox").show();
					return false;
				}
		    }
		});	 
   }
    
    // 取消按钮
    $("#closebox2").click(function(){
    	$(".paybox").hide();
    	$(".bgc").hide();
    })

	// 提交支付信息
    $("#paysubmit").click(function(){
    	var cardnumberval = $("#cardnumber").val();
    	var cardpwd = $("#cardpwd").val();
    	var zbcvid = $("#zbcvid").val();
    	var isLive = $("#isLive").val();
    	if(!cardnumberval){
    		alert("请输入卡号");
    		return false;
    	}
    	if(!cardpwd){
    		alert("请输入密码");
    		return false;
    	}
    	
     	$.ajax({
			type: 'POST',
			url: ctxJS + "/study/logstudy.do?mode=playSubmit&cvsetId="+projectId+"&cardNumber="+cardnumberval+"&cardPassword="+cardpwd,
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message == 'success'){
					//进入点播或者直播
					if(isLive == "true"){
		        		window.location.href = ctxJS + "/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
		        	}else{
		        		$(".bgc").hide();
		   				$(".paybox").hide();
			   			
			   			flags = true;
			   			if (currUnitIdVal == undefined) { //无点击事件，初始加载
							<c:if test="${currStudyUnitId != null}">
								$("#${currStudyUnitId}").click() ;					         
					        </c:if>
						} else {
								$("#" + currUnitIdVal).click() ;
						}			   	      	
		        	}
				}else if(result.message == 'notbind'){
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
				}
			}
    	});
    });
    
    
    $(function () {
  		setInterval("timerFun()",1000);
    });
    function timerFun(){
    	$("ul[name='2']").each(function(index){//2标识该课程为直播课程
    		var _this = $("ul[name='2']");
		    var cvId = $(this).find("input[name='cvId']").val();
		    $.ajax({
				type: 'POST',
				url: ctxJS + "/viewLiveListInterface.do?mode=getZBType&cvId="+cvId,
				dataType: 'JSON',
				success : function(result){
						if(result.typeInt == '1'){
							$("#timerCvType"+cvId).val("1");
							$("li[name='makeType"+cvId+"']").find("a").attr("title","直播中，点击进入直播页面");
						}
						if(result.typeInt == '2'){
							$("#timerCvType"+cvId).val("2");
							$("li[name='makeType"+cvId+"']").find("a").attr("title","直播尚未开始");
						}
						if(result.typeInt == '3'){	
							$("#timerCvType"+cvId).val("3");
						}
						if(result.typeInt == '4'){
							$("#timerCvType"+cvId).val("4");
							$("li[name='makeType"+cvId+"']").find("a").attr("title","观看回放。点击，进入回放页面。");
						}
						if(result.typeInt == '5'){
							$("#timerCvType"+cvId).val("5");
							$("li[name='makeType"+cvId+"']").find("a").attr("title","直播结束，视频转码中，敬请期待！");
						}
				}
	    	})
		})
   }
    
   
   //?????????????????????????????-------未知
   /*****程宏业
     2017-03-24
                              供子页面调用更改框架父页面的样式
 	 ****/ 	
    function disp(){
   	 $(".box").css("background-color","white"); 
    }
    function disn(){
   	 $(".box").css("background-color",""); 
    }
         
	/**
	     程宏业
	 2017-04-01
	 显示隐藏任务点	
	*/
	$(".container .course_right .course_list ul li").mouseover(function(){
		$(this).find("img").show();
		$(this).find("#porint").show();
	}).mouseout(function(){		
		$(this).find("img").hide();
		$(this).find("#porint").hide();
		
	});
   
    //用于维护课程和项目学习记录---taoliang
    function InitializationCVAndCVSetLog(cvId){
		$.ajax({
			type: 'POST',
			url: ctxJS + "/study/logstudy.do?mode=initializeCVAndCVSetStudyLog&cvId="+cvId,
			dataType: 'JSON',
			success : function(result){
				if(result.message != 'success'){
					//alert(result.message);
				}
			}
    	})
	} 
    //用于维护扩展阅读单元学习记录
    $("button[name=publish]").click(function() {
    	//计算读后感的字数
    	var currStudyUnitId  = ${currStudyUnitId};
    	var currStudyCvId    = ${cvId};
    	var currStudyCvSetId = ${cvsetId};
        var content          = $('#reading').val();
    		if($('#reading').val().length<600){   //长度暂时定300字
		        alert("很遗憾，您的读后感内容未达到要求！");
		    	return false;
		    }
    	//没有判定关键字
    		$.ajax({
    			type: 'POST',
    		    url: ctxJS + "/study/logstudy.do?mode=addUnit&unitId="+currStudyUnitId+"&cvId="+currStudyCvId+"&cvsetId="+currStudyCvSetId +"&content="+content,
    			dataType: 'JSON',
    			success : function(result){
    				if(result.message != 'success'){
    					//alert(result.message);
    				}
    			}
        	})
    });
 </script>
			  
     <script type="text/javascript" src="${ctx}/qiantai_page/js/util.js"></script>
     
</body>
</html>