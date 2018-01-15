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
    <style type="text/css">
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
    width:85%;
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
    
<body>
	<!--startprint-->
	<div>
		    <div class="cont">
			
                 <h2 class="title" style="text-align: center;font-size:18px;"><span>面授项目报名表</span></h2>
                 <table class="gridtable" >
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
					   <th> &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${faceteach.train_starttime}" pattern="yyyy-MM-dd"/> 
					         </br>至 <fmt:formatDate value="${faceteach.train_endtime}" pattern="yyyy-MM-dd"/></th>
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


</script>


</body>



</html>