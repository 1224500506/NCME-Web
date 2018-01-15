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
    <script src="${ctx}/qiantai_page/js/rating.js?rev=5cab9c748cf8c51b4937cb8e6cf0d306"></script> 
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/qrcode.js"></script>
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/chakanzhengshu.css" rel="stylesheet" id="css">
    <link href="${ctx}/qiantai_page/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">  
    
   <style type="text/css">
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
    width:67%;
    margin:20px auto;
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
}
</style>
    
</head>
<body>
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
      <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
         <li><a href="#">慕课></a></li>
        <li><a href="#">${cvSet.name}</a></li>
    </ul>
		<div style="padding:20px 0;overflow:hidden;">
			<span class="item_cover" style="background:url(${cvSet.file_path}) no-repeat center;background-size:100% auto;float:left;width:150px;height:150px;margin-right:20px;"></span>
			<div style="float:left;margin-top:25px;">
				<div style="font-size:35px;" >面授项目报名成功</div><br />
				   <div>
					您已报名【${cvSet.name }】${faceteach.class_name}&nbsp;<fmt:formatDate value="${faceteach.train_starttime}" 
					pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${faceteach.train_endtime}" pattern="yyyy-MM-dd"/>
					的面授项目，请在我的学习中查看报名表~
				   </div>
			</div>	
				   
		</div>	   
		
		<div>
		    <div class="cont">
			
                 <h2 class="title" style="text-align: center;font-size:18px;"><span>面授项目报名表</span></h2>
                 <table class="gridtable" >
                 <input type="hidden" id = "fid" value = '${faceteach.id}'/>
                 <h3 style="width:67%;margin:0 auto;color:blue;text-align:right;" id="OK">打印报名表</h3>
					<tr>
					    <th>报名编号</th>
					    <th colspan="3">${cvSet.name }${faceteach.class_name}${faceteach.id}</th>
					    
					</tr>
					<tr>
					    <th>姓名</th>
					    <th>${user.realName }</th>
					    <th>性别</th>
					    <th> 
						    <c:if test = "${user.sex eq 1}">男</c:if>
						    <c:if test = "${user.sex eq 2}">女</c:if>
					    </th>
					</tr>
					<tr>
					    <th>
						    <c:if test = "${user.certificateType eq 1}">身份证号</c:if>
						    <c:if test = "${user.certificateType eq 2}">军官证号</c:if>
					    </th>
					    <th>${user.certificateNo }</th>
					    <th>手机号</th>
					    <th>${user.mobilPhone }</th>
					</tr>
					<tr>
					    <th>单位名称</th>
					    <th>${user.workUnit }</th>
					    <th>单位地址</th>
					    <th>${user.contactAddress }</th>
					</tr>
					<tr>
					    <th>学科</th>
					    <th>${exam3.name }> ${exam2.name } >${exam3.name }</th>
					    <th>项目名称</th>
					    <th>${cvSet.name }</th>
					</tr>
					<tr>
					    <th>培训期数</th>
					    <th>${faceteach.class_name}</th>
					    <th>培训时间</th>
					   <th> <fmt:formatDate value="${faceteach.train_starttime}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${faceteach.train_endtime}" pattern="yyyy-MM-dd"/></th>
					</tr>
					<tr>
					    <th>培训地址</th>
					    <th>${faceteach.train_place}</th>
					    <th>培训费用</th>
					    <th>${cvSet.cost }元（请在线下缴费)</th>
					</tr>
				</table>
			</div>
		</div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</body>
<script>

var fid = $("#fid").val();
$("#OK").click(function () {
	   // 打印证书
	   window.open(basepath+'/entityManage/faceEntry.do?method=printFaceEntry&fid='+fid);// 打印面授报名);
 	
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
        
</script>
</html>
'&score1='+score1+'&score2='+score2+'&score3='+score3+'&score4'+score4