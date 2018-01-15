<%@ page language="java" pageEncoding="UTF-8" import="com.hys.exam.util.NcmeProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.springside.org.cn/jodd_form" prefix="jodd" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.newworld.com/tags/examTag" prefix="exam" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:set var="ctxAdminURL" value="${ctxPropertiesMap.adminURL}" />
<c:set var="ctxPeixunURL" value="${ctxPropertiesMap.peixunURL}" />

<script type="text/javascript" src="${ctx}/qiantai_page/js/lib/layer/layer.js"></script>
<script type="text/javascript">var basepath = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}';</script>
<script type="text/javascript">
	var ctxJS = "${pageContext.request.contextPath}";
	String.prototype.getBytes = function() {
	    var cArr = this.match(/[^\x00-\xff]/ig);
	    return this.length + (cArr == null ? 0 : cArr.length);
	};
</script> 