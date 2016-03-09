<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "aboutus");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>关于我们</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<script>
window.onload = function() {
	getHeight();
	topshow();
}
</script>
</head>

<body>
<!--导入：登入注册忘记密码 弹窗模块-->
<jsp:include page="../common/headerLogin.jsp"/>
<!--导入：头部nav-->
<jsp:include page="../common/headerNav.jsp"/>
<!--导入：搜索和菜單-->
<jsp:include page="../common/headerSearchAndMenu.jsp"/>
<div class="container">
  <div class="row">
    <div class="col-xs-2" id="about-lt">
      <div onclick="location.href='${base}/about-us/home'" class="leftbtn leftbtn-act">关于我们</div>
      <div onclick="location.href='${base}/about-us/culture'" class="leftbtn">企业文化</div>
      <div onclick="location.href='${base}/about-us/honor'" class="leftbtn">企业荣誉</div>
      <div onclick="location.href='${base}/about-us/event'" class="leftbtn">大事记</div>
    </div>
    <div class="col-xs-10" id="about-rt">
      <div class="word-top word-border">
      	
        <h2>
        	<c:if test="${empty sysAboutUS}">数据为空</c:if>
			<c:if test="${not empty sysAboutUS}">${sysAboutUS.aboutUSTitle}</c:if>        
        	<small style="color:#CCC;">
	        	<c:if test="${empty sysAboutUS}">数据为空</c:if>
				<c:if test="${not empty sysAboutUS}">${sysAboutUS.aboutUSSubtitle}</c:if>
        	</small>
        </h2>
      </div>
      <div class="word-pictb"><img src="${base}/resources/images/ishangimg/banner_2.jpg" width="100%" height="100%" style="padding:10px;"></div>
      <div class="aboutus">
        <p>
        	<c:if test="${empty sysAboutUS}">数据为空</c:if>
			<c:if test="${not empty sysAboutUS}">${sysAboutUS.aboutUSContent}</c:if>
        </p>
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
