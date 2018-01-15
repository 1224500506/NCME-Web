<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/commons/taglibs.jsp"%>
	<script type="text/javascript" src="${ctx}/qiantai_page/js/lib/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${ctx}/qiantai_page/js/lib/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${ctx}/qiantai_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <style type = "text/css">
    .table tbody tr td {
		  padding-top: 13px;
		  padding-bottom: 13px;
		  text-align:center;
		}
		.table-bordered thead tr {
		  background-color: #e6e6e6;
		  background-image: -moz-linear-gradient(top, #eeeeee, #dadada);
		  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#eeeeee), to(#dadada));
		  background-image: -webkit-linear-gradient(top, #eeeeee, #dadada);
		  background-image: -o-linear-gradient(top, #eeeeee, #dadada);
		  background-image: linear-gradient(to bottom, #eeeeee, #dadada);
		  background-repeat: repeat-x;
		  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffeeeeee', endColorstr='#ffdadada', GradientType=0);
		}
		.table-bordered thead tr th {
		  padding-top: 10px;
		  padding-bottom: 10px;
		  font-size: 13px;
		  font-weight: 600;
		  color: #444;
		  border-left: 1px solid #F1F1F1;
		  border-right: 1px solid #CCC;
		  -webkit-box-shadow: inset 0 1px 0 #ffffff;
		  -moz-box-shadow: inset 0 1px 0 #ffffff;
		  box-shadow: inset 0 1px 0 #ffffff;
		  text-align:center;
		}
		.table-bordered thead tr th:first-child {
		  border-left-color: #CCC;
		}
		.table-bordered thead tr th:last-child {
		  border-right: none;
		}
		.table-bordered tbody tr:first-child td {
		  border-top-color: #CCC;
		}
		.table-bordered tbody tr td {
		  border-left: 1px solid #FFF;
		  border-right: 1px solid #DDD;
		}
		.table-bordered tbody tr td:first-child {
		  border-left-color: #DDD;
		}
		.table-bordered tbody tr td:last-child {
		  border-right: none;
		}
		.table-bordered thead tr {
		  background: #EEE;
		  background: -moz-linear-gradient(top, #eeeeee 0%, #dadada 100%);
		  /* FF3.6+ */
		
		  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #dadada));
		  /* Chrome,Safari4+ */
		
		  background: -webkit-linear-gradient(top, #eeeeee 0%, #dadada 100%);
		  /* Chrome10+,Safari5.1+ */
		
		  background: -o-linear-gradient(top, #eeeeee 0%, #dadada 100%);
		  /* Opera11.10+ */
		
		  background: -ms-linear-gradient(top, #eeeeee 0%, #dadada 100%);
		  /* IE10+ */
		
		  background: linear-gradient(top, #eeeeee 0%, #dadada 100%);
		  /* W3C */
		
		  -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#DADADA')";
		  filter: progid:dximagetransform.microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#DADADA');
		}
		.table-bordered thead th {
		  padding-top: 10px;
		  padding-bottom: 10px;
		  font-size: 13px;
		  font-weight: 400;
		  color: #444;
		  border-left: 1px solid #F1F1F1;
		  border-right: 1px solid #CCC;
		  -moz-box-shadow: inset 0 1px 0 #ffffff;
		  -webkit-box-shadow: inset 0 1px 0 #ffffff;
		  box-shadow: inset 0 1px 0 #ffffff;
		}
		.table-bordered thead th:first-child {
		  border-left-color: #CCC;
		}
		.table-bordered thead th:last-child {
		  border-right: none;
		}
		.table-bordered tbody td {
		  border-left: 1px solid #FFF;
		  border-right: 1px solid #DDD;
		}
		.table-bordered tbody tr td:first-child {
		  border-left-color: #DDD;
		}
		.table-bordered tbody tr td:last-child {
		  border-right: none;
		}
		.table-highlight.table-bordered thead tr {
		  background-color: #292929;
		  background-image: -moz-linear-gradient(top, #333333, #1a1a1a);
		  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#333333), to(#1a1a1a));
		  background-image: -webkit-linear-gradient(top, #333333, #1a1a1a);
		  background-image: -o-linear-gradient(top, #333333, #1a1a1a);
		  background-image: linear-gradient(to bottom, #333333, #1a1a1a);
		  background-repeat: repeat-x;
		  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff333333', endColorstr='#ff1a1a1a', GradientType=0);
		}
		.table-highlight.table-bordered thead th {
		  color: #FFF;
		  border-right: 1px solid #0d0d0d;
		  border-left: 1px solid #4d4d4d;
		  -webkit-box-shadow: inset 0 1px 0 #666666;
		  -moz-box-shadow: inset 0 1px 0 #666666;
		  box-shadow: inset 0 1px 0 #666666;
		  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
		}
		.table-highlight.table-bordered tbody tr:nth-child(odd) td,
		.table-highlight.table-bordered tbody tr:nth-child(odd) th {
		  background-color: #F0F3F7;
		}
		.table-highlight.table-bordered {
		  border-top: 1px solid #1C2B37;
		}
		.buttonTick{
			border: 1px solid #b5b5b5;
			padding: 4px 20px;
			border-radius: 5px;
		}
	.buttonTick:hover{
		border: 1px solid #12bce1;
		padding: 4px 20px;
		border-radius: 5px;
		background: #12bce1;
		color: #fff;
	}
    </style>
</head>
<body>
<div class="container">
	<div class="content" style="padding:0px;">
	<c:if test = "${mode == 1 }">
		<display:table id = "item" name = "list" class = "table table-bordered table-striped table-highlight" requestURI="${ctx}/postList.do?mode=${mode}" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" >
			<display:column title="序号" style="width:5%;">${item_rowNum}
			</display:column>			
			<display:column property="title" title="标题" style="width:25% "></display:column>			
			<display:column title="分类" style="width:5% ">
				<c:if test="${item.type==1}">通知</c:if>
				<c:if test="${item.type==2}">公告</c:if>
			</display:column>
			<display:column title="发布时间" style="width:20%"><fmt:formatDate value="${item.deli_date}" pattern="yyyy-MM-dd HH:mm:ss"/></display:column>
			<display:column  style="width:15%;" title="操作" >
			<a href = "javascript:postView(${mode},${item.id});" style="color:#3f37f0">查看</a>
			</display:column>
		</display:table>
	</c:if>
	<c:if test = "${mode == 2}">
		<display:table id = "item" name = "list" class = "table table-bordered table-striped table-highlight" requestURI="${ctx}/postList.do?mode=${mode}" pagesize="10">
			<display:column title="序号" style="width:5%;">${item_rowNum}
			</display:column>			
			<display:column property="title" title="标题" style="width:25% "></display:column>			
			<display:column title="分类" style="width:5% ">
				政策法规				
			</display:column>
			<display:column title="发布时间" style="width:20%"><fmt:formatDate value="${item.deli_date}" pattern="yyyy-MM-dd HH:mm:ss"/></display:column>
			<display:column  style="width:15%;" title="操作" >
			<a class="buttonTick" href = "javascript:postView(${mode},${item.id});" style="color:#3f37f0">查看</a>
			</display:column>
		</display:table>
	</c:if>
	<input type = "hidden" name = "mode" value = "${mode}"/>
	</div>
</div>
</body>
<script>
     function postView(mode,id)
     {
    	var e = "${ctx}/postView.do?mode=" + mode + "&id=" + id;
        var c = 800;
        var d = 1100;
        window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c,"_blank");
     }
</script>
</html>