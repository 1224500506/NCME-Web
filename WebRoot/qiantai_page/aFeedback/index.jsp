<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html>
<head>
<title>uploadify-实例</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta content="http://schemas.microsoft.com/intellisense/ie5" name="vs_targetSchema">

	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.v2.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadify").uploadify({
		'uploader'       : 'uploadify/scripts/uploadify.swf',
		'script'         : '${ctx}/feedback/feedbackManage.do?method=upload',
		'cancelImg'      : '${ctx}/qiantai_page/aFeedback/js/cancel.png',
		'buttonImg'		 : '${ctx}/qiantai_page/aFeedback/js/xzButton.gif',
		'folder'         : '/photos',
		'queueID'        : 'fileQueue',
		'auto'           : false,
		'multi'          : true,
		'wmode'			 : 'transparent',
		'simUploadLimit' : 999,
		'fileExt'		 : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
		'fileDesc'		 : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
		'onSelectOnce' : function(event,data,data)
        {
          filesSelected:true;
        },
        'onComplete' : function(event,queueId,fileObj,response,data)
        {
        	var inputDom=$("<input type='hidden' name='hiddenDom' value='"+response+"' />");
        	var p='<%=basePath%>'+response;
            var $image=$("<img src='"+p+"'  width='536px'/>");
        	inputDom.appendTo("#box");
        	$image.appendTo("#box");
        	
        },
		'onAllComplete' : function(event,data) 
		{
		   alert('一共上传了'+data.filesUploaded+'张图片');
		}  
	});
});
		</script>
<style type="text/css">
.inputcss
{
	color:#333333;
	font-family: "Tahoma"; 
	font-size: 12px; 
	border:solid 1px #CCCCCC;
}
.buttoncss
{
	color:#333333;
	font-family: "Tahoma"; 
	font-size: 12px; 
	background-color:#FFFFFF;
	border:solid 1px #CCCCCC;
}
</style>
</head>
<body>
	<table style="width: 90%;">
		<tr>
			<td style="width: 100px;">
				<input type="file" name="uploadify" id="uploadify" />
			</td>
			<td align="left">
				<a href="javascript:$('#uploadify').uploadifyUpload()">开始上传</a>|
			<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传</a>
			<span id="result" style="font-size: 13px;color: red"></span>
			</td>
		</tr>
	</table>
		<div id="fileQueue" style="width:500px;height: 300px; border: 2px solid green;"></div>
		<form action="test.jsp" method="post">
		   <div id="box"></div>
		   <input type="submit" value="提交">
		</form>
</body>
</html>