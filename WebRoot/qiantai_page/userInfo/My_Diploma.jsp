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
    <title id="tit">中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/qrcode.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/dayinzhengshu.css" rel="stylesheet">
  

</head>
    <style>
    
   #qrcode{
   
     margin-left: 71px;
    position: relative;
    top: 6px;
    left: -99px;
    z-index: 99;
   
   }
    
    </style>
    
    
<body>

<c:forEach items="${diploma}" var="dip"> 
	<!--startprint-->
	
	
	
	
	<div style="width:800px;height:600px;margin:0 auto;">
			<div class="center">
					<img src="${ctx}/qiantai_page/css/1.png"/>
     				<div class="center1">
     				   <h1 class="h1">学分证书</h1>
     					 <p class="p">
     					   姓名<span style="padding:0px 20px 0px 20px;">${dip.system_user_name}</span>身份证号<span style="padding:0px 20px 0px 20px;">${dip.id_number}</span>于
     					   <span style="padding:0px 20px 0px 20px;" id="stime">
     					   </span>
     					 至<span style="padding:0px 20px 0px 20px;" id="etime"></span>参加继续医学教育项目（面授&nbsp;<input type="checkbox" id="ms" />，
     					 远程教育&nbsp;<input type="checkbox" id="yc" />，混合&nbsp; <input type="checkbox" id="hh"  />)[项目编号：<span style="padding:0px 20px 0px 20px;">${dip.item_number}</span>][项目名称：<span style="padding:0px 20px 0px 20px;">${dip.item_name}</span>]。经考核合格，特授予&nbsp;${dip.item_grade}
     					 <span style="padding:0px 20px 0px 20px;"></span> 继续医学教育学分<span style="padding:0px 20px 0px 20px" >${dip.item_score}</span>分。
     					</p>
     					<div class="zhengshu">
     						<p style="float:left;">证书编号：<span>${dip.diploma_number}</span></p>
     						<p style="float:right;">国家卫生计生委能力建设和继续教育中心</p>
     					</div>
     					<div style="clear:both;"></div>
     					<div class="zhengshu">
     						<p style="float:left;width:70px;height:70px;margin-left:50px;"></p>
     						<p style="float:right;padding-top:20px;"><span style="padding:0px 20px 0px 20px;" id="apply"></span></p>
     					</div>
     					<div id="qrcode"></div>
   						<img src="${ctx}/qiantai_page/css/dz.png" style="margin-top:-518px;margin-left:-76px;"/>
     					<!-- <span class="bj"></span> -->
     				</div> 
     			</div>
     	</div>	
<!--endprint-->
<script>

window.onload=function(){
	
	var urlpa = location.search;
	if(urlpa.indexOf("pri")!=-1){
	   bdhtml=window.document.body.innerHTML;
	   $("#tit").text("打印完成后关闭窗口即可");
	   sprnstr="<!--startprint-->";    
 	   eprnstr="<!--endprint-->";    
 	   prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
 	   prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
 	   window.document.body.innerHTML=prnhtml;    
 	   window.print(); 
	}
	
}




/*  window.onbeforeunload = onbeforeunload_handler;   
window.onunload = onunload_handler;   
function onbeforeunload_handler(){   
    var warning="确认退出?";           
    return warning;   
}   
   
function onunload_handler(){   
    var warning="谢谢光临";   
    alert(warning);   
}    
 */





$(function(){
	
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width : 90,//设置宽高
            height : 90
        });
        qrcode.makeCode(basepath+"/myStudy/DiplomaAction.do?method=findT&diploma_number="+'${dip.diploma_number}'+"&lastUpdateDate="+'${lastUpdateDate}');
      //扫描证书学习时间和查看证书的学习时间保持一致
	$("#stime").html(toDate('${dip.begin_time}'));
	$("#etime").html(toDate('${dip.end_time}'));
	
	//$("#stime").html(toDate('${lastUpdateDate}'));
	//$("#etime").html(toDate('${dip.apply_time}'));
	$("#apply").html(toDate('${dip.apply_time}'));
	
	if('${dip.item_type}'=="远程"){
		$("#yc").attr("checked","checked");
		
	}else if('${dip.item_type}'=="面授"){
		$("#ms").attr("checked","checked");
	}else {
		$("#hh").attr("checked","checked");
	}
		
	
	
	
	
	function toDate(str){
		var arr = new Array();
		if(str.length>0){
			arr = str.split("-");
			return arr[0]+"&nbsp;年"+arr[1]+"&nbsp;月"+arr[2].substring(0,2)+"&nbsp;日";
		}else{
			return "&nbsp;年&nbsp;月&nbsp;日";
		}
		
	}
	
      
       
	
});

</script>

</c:forEach>






</body>



</html>