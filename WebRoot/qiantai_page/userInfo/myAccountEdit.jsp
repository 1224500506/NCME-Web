<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myAccountEdit.jsp' starting page</title>
    <%@include file="/commons/taglibs.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
               <table>
                        <thead>
                        <tr>
                            <th>卡类型</th>
                            <th>卡号</th>
                            <th>适用的项目</th>
                            <th>面值</th>
                            <th>有效期</th>
                        </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${cardlist}" var="cards" >
                        <tr>
                            <td>${cards.cardTypeName}</td>
                            <td>${cards.CARD_NUMBER}</td>
                             <td>${cards.ITEM_NAME}</td>
                            <td>￥${cards.price}</td>
                            <td>${cards.USEFUL_DATE}</td>
                             
                            
                        </tr>
                         </c:forEach>
                        </tbody>
                    </table>
  </body>
</html>
