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
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/qrcode.js"></script>
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/chakanzhengshu.css" rel="stylesheet" id="css">
 
    
    <style>
    
  #qrcode{
   
     margin-left: 71px;
    position: relative;
    top: 6px;
    left: -99px;
   
   }
    
    
    .del{
        font-size: 12px;
    	position: absolute;
    	right: 0px;
    	
    	
    
    }
    
    
    .info1 span {
    width: 40%;
    font-size: 12px;
    float: left;
    line-height: 25px;
}
    body{font-family:'Lantinghei SC','hiragino sans GB', 'Microsoft YaHei', 'Helvetica Neue', Helvetica, Arial, sans-serif;}
    </style>
    
</head>
<body>
<script>

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
                <li id="1tab" ><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab1">我的学习</a></li>
                <li id="2tab" class="active"><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
                 <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>
           </ul>
        </div>
        <form name = "myForm" id = "myForm" action = "${ctx}/userInfo/MyStudyManage.do" method = "post">
	        <div class="main_cont right">
	            <div class="tabs">
	                <ul class="tab_list">
	                    <li class="tab active" id="tab2">我的学分/证书</li>
	                </ul>
	                <div class="tab2 tab_cont" >
	                	<c:if test = "${dataPerfect.size() == 0}">
	                    	<div class = "clear"></div>
	                    	<center>没有已完成的内容！</center>
	                    </c:if>
	                    <c:if test = "${dataPerfect.size() != 0}">
		                    <ul class="item_list time">
		                    	<c:forEach items="${dataPerfect}" var="item">
			                       <li>
			                            <div class="time_line">
											<span class="date">
												<c:if test="${lastUpdateDateYear2 != item.lastUpdateDateYear}">
													${item.lastUpdateDateYear}
												</c:if>
											</span>
											<c:set var="lastUpdateDateYear2" value="${item.lastUpdateDateYear}" />
			                                <span class="date"><fmt:formatDate value="${item.apply_date}" pattern="MM"/> 月<fmt:formatDate value="${item.apply_date}" pattern="dd"/> 日</span>
			                                <span class="dot"><input type="hidden" id="lastUpdateDate" value="${item.last_update_date}" /></span>
			                            </div>
			                            <span class="item_cover" style="background:url(${item.file_path}) no-repeat center;background-size:100% auto"></span>
			                            <div class="item_cont">
			                              	<h2 class="title" onclick = "javascript:gotoDetail(${item.cv_set_id});"> ${item.name} </h2>
			                                <div class="info1">
			                                    <span>级别学分：
			                                   
						                            <c:if test = "${item.level eq -1}">全部</c:if>
						                            <c:if test = "${item.level eq 1}">国家I类</c:if>
						                            <c:if test = "${item.level eq 2}">省级I类</c:if>
						                            <c:if test = "${item.level eq 3}">市级I类</c:if>
						                            <c:if test = "${item.level eq 4}">省级II类</c:if>
						                            <c:if test = "${item.level eq 5}">市级II类</c:if>
						                          	<c:if test="${item.score != 0}"> ${item.score} </c:if>
			                                    	<c:if test="${item.score == 0.0}"> 0 </c:if>分 
						                        </span>
			                                    <span>项目编号：${item.code}<input type="hidden" id="itemcode" value="${item.code}"/></span>
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
					                              
							                            <fmt:formatDate value="${item.start_date}"  pattern="yyyy-MM-dd"/>
							                       
							                            至
							                           
							                            <fmt:formatDate value="${item.end_date}"  pattern="yyyy-MM-dd"/>
							                          
													</c:if>
													
													</span>
										  		
						
			                                </div>
			                                <div class="foot no_border">
			                                  <!--   <span class="font_big font_blue" id="sco">
			                                    	
			                                    </span> -->
			                                    <span id="applytime" style="display: none">
										  		 <fmt:formatDate value="${item.apply_date}"  pattern="yyyy-MM-dd"/>
										  		</span>
			                                </div>
			                                
			                                <div style="position:absolute;right:0;top:0;padding-top:50px">
			                               	<c:if test="${item.forma eq 1}"> 
				                                <span class="text_right" style="position:absolute;right:1px;top:4px;">
				                                 <a href="javascript:void(0);" class="" style ="color:#000000;border:1px solid #000;border-radius:10px;padding:0 10px;"> 远程</a>
				                                </span>
				                                <div class="text_right">
				                                  <a href="javascript:checkComment('${item.cv_set_id}');" class="btn btn_border blue_border btn_study"  style ="color:#000000;margin-right:0;margin-top:15px;">查看评价</a>
				                                </div>
			                                </c:if>
			                                <c:if test="${item.forma eq 3}"> 
				                                <span class="text_right" style="position:absolute;right:1px;top:4px;">
				                                <a href="javascript:void(0);" class="" style ="color:#000000;border:1px solid #000;border-radius:10px;padding:0 10px;"> 面授</a>
				                                </span>
				                                <div class="text_right" ><a  onclick="seeFaceTeach('${faceList.id}');" style ="color:blue;">查看报名表</a></div>
			                                </c:if>
			                                <span class="text_right" onclick="findscore('${userInfo.userId}','${item.code}','${item.score}','1','${item.cv_set_id}')">
			                                <a href="javascript:void(0);" class="btn btn_border orange_border btn_credit" style ="color:#000000;margin-right:0;margin-top:25px;">学分证书</a>
			                                </span>
			                                </div>
			                            </div>
			                            <div class="bottom_line"></div>
			                        </li>
			                    </c:forEach>
		                    </ul>
		                </c:if>
	                </div>
	            </div>
	        </div>
        </form>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
<div class="popup credit_tips">
	<div class="mask"></div>
	<div class="popup_cont normal">
		<h2><span class="close"><i class="fa fa-times"></i> </span> 提示</h2>
		<div class="cont">
			<input type="hidden" id="tipsCVId"/>
			<input type="hidden" id="cvSetId" />
			您当前申请的项目“<span id="tipsCVName"></span>”为<br/><span id="tipsCVLevel"></span><span id="tipsCVScore"></span>分项目，确定申请？
		
		</div>
		<div class="foot">
			<button name="yes" type="button" class="btn btn_blue" >确定</button>
			<button name="no" type="button" class="btn">取消</button>
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
		<input type="hidden" id="cvSetId_hidden"/>
        <div class="foot">
            <button name="no" type="button" class="btn btn_blue">返回</button>
            <button name="OK" type="button" class="btn btn_orange" >打印</button>
        </div>
    </div>
</div>

</body>
<script>

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

var formas;

var cvSetId;

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
        $("button[name=no],button[name=yes].close").click(function () {
            $(".popup").hide();
            location.reload();
        });
        
       $("button[name=OK]").click(function () {
    	   var lastUpdateDate = $("#lastUpdateDate").val();
		   var userId = ${userInfo.userId};
    	   var cvSetId = $("#cvSetId_hidden").val();
    	   // 打印证书
    	   window.open(basepath+"/myStudy/DiplomaAction.do?method=findT&system_user_id="+userId+"&diploma_number="+printURL+"&lastUpdateDate="+lastUpdateDate+"&pri"+"&cvSetId="+cvSetId,"_blank");
        	
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
        $(".popup.credit_tips button[name=yes]").click(function () {
        	//提交申请证书的数据
            var url = "${ctx}/userInfo/MyStudyManage.do?mode=applycredit&id=" + $(".popup.credit_tips #tipsCVId").val() + "&cvSetId=" + $(".popup.credit_tips #cvSetId").val();
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'text',
                success: function (result) {
                    if (result == "success") {
                        alert("申请成功！");
                        SubmitForm();
                        SubmitZS();
                        findscore(userId,bianhao,applyscore,1,cvSetId);
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
function findscore(userid,code,score,title,cvSetId){
	if(title==1){
		
		$("#sctitle").html("欢迎查看学分");
	}
	$("#cvSetId_hidden").val(cvSetId);
	 $("#createImg").empty();
	var url = "${ctx}/myStudy/DiplomaAction.do?method=find&system_user_id="+userid+"+&item_number="+code+"&cvSetId="+cvSetId;
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
        	 var lastUpdateDate = $("#lastUpdateDate").val();
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
     		
            qrcode.makeCode(basepath+"/myStudy/DiplomaAction.do?method=findT&system_user_id="+userid+"&diploma_number="+obj.diploma_number+"&lastUpdateDate="+lastUpdateDate+"&cvSetId="+cvSetId);
            
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
	function gotoDetail(id) {
		location.href = "${ctx}/projectDetail.do?id=" + id;
	}
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
	
</script>
</html>