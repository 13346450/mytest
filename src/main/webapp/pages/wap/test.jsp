<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/wap-resources/js/mobile-common.js"></script>
<script src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
<style>
body {
	margin-left: 5px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript" src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/resources/wap-resources/js/jquery.timeago.js"></script>

<title>时间格式化</title>
<style type="text/css">
.a {
	width: 200px;
	height: 100px;
	background-color: #FC9
}
</style>
<script type="text/javascript">
		$(function() {
			//alert(new Date());
			//var mydate = getDateTimeStamp(new Date());
			//$(".timeago1").title();
			$("span.timeago").timeago();
			//$("abbr.timeago1").timeago(mydate);
			//jQuery("div.timeago").timeago();  
		});
</script>
</head>
<body>
	<div class="a timeago"></div>
	<span class="timeago" title="<fmt:formatDate value="<%=new Date() %>" pattern ="yyyy-MM-dd HH:mm:ss" />"></span>
	
</body>
</html>