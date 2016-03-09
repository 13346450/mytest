<%-- <%  response.sendRedirect("loginPage"); %> --%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.dnake.utils.SessionBean"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>
<link rel="stylesheet"
	href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet"
	href="${base}/resources/css/ishangcss/indexbase.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css">
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script
	src="${base}/resources/javascripts/ishangjs/jquery.fullPage.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/jquery.bxslider.js"></script>
<script src="${base}/resources/javascripts/ishangjs/main.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>

<script>
window.onload = function() {
	topshow();
	if(getCookie("bindTel")!=null){
		$('#bindTel').val(getCookie("bindTel"));
	}
}
</script>
</head>

<body>
	<!--login-->
	<jsp:include page="common/headerLogin.jsp"/>
	
	<jsp:include page="common/headerNav.jsp"/>
	<jsp:include page="common/headerSearchAndMenu.jsp"/>
	<!--boy-->
    <div class="boybg">
      <div style="margin-top:105px;">
      <a href="#">
         <i class="closeboy"></i>
         <span style="color:#ef3d4a; padding-left:15px; margin-bottom:3px; display:block;">待处理</span>
         <img src="resources/images/ishangimg/problem.jpg" width="80" height="60">
         <span style="padding:3px 15px; display:block;">电梯门口的灯坏了，请……</span>
       </a>
      </div>
    </div>
   <!--end boy-->
	<div id="pictures">
		 <div id="welcome" class="fullpage-wrapper">
    <div class="section fp-section active" id="section0">
    <div class="flashyun"></div>
    <img src="resources/images/ishangimg/01.jpg" width="100%" height="100%"> </div>
    <div class="section fp-section" id="section1">
    
    <img class="gold01" style="left:22%;" src="resources/images/ishangimg/gold.png" width="46" height="46">
    <img class="gold03" style="left:25%;" src="resources/images/ishangimg/gold.png" width="76" height="76"> 
    <img class="gold02" style="left:30%;" src="resources/images/ishangimg/gold.png" width="86" height="86">
    <img class="gold01" style="left:35%;" src="resources/images/ishangimg/gold.png" width="46" height="46">
    <img class="gold03" style="left:37%;" src="resources/images/ishangimg/gold.png" width="76" height="76"> 
    <img class="gold02" style="left:40%;" src="resources/images/ishangimg/gold.png" width="66" height="66">
    <img class="gold01" style="left:46%;" src="resources/images/ishangimg/gold.png" width="76" height="65">
    <img class="gold03" style="left:50%;" src="resources/images/ishangimg/gold.png" width="56" height="56">
    
    <img src="resources/images/ishangimg/02.jpg" width="100%" height="100%"> 
    </div>
    
    <div class="section fp-section" id="section2">
    <div style="background-image:url(resources/images/ishangimg/bubble01.png); width:86px; height:86px; left:63%;" class="fly01"></div>
    <div style="background-image:url(resources/images/ishangimg/bubble02.png); width:123px; height:123px; left:68%;" class="fly02"></div>
    <div style="background-image:url(resources/images/ishangimg/bubble03.png); width:90px; height:90px; left:75%;" class="fly03"></div>
    <img src="resources/images/ishangimg/03.jpg" width="100%" height="100%"> 
    </div>
    <div class="section fp-section" id="section3">
    
     <img src="resources/images/ishangimg/yun02.png" class="yun02" width="508" height="122">
     
    <img src="resources/images/ishangimg/04.jpg" width="100%" height="100%"> 
    </div>
  </div>
<!-- 		<div id="welcome" class="fullpage-wrapper"> -->
<%-- 			<c:forEach items="${sysLogoSettings}" var="item" varStatus="i"> --%>
<!-- 				<div -->
<%-- 					class="section fp-section <c:if test="${i.index == 0}">active</c:if>" --%>
<%-- 					id="section${i.index}" style="height: 100%;"> --%>
<%-- 					<img src="${base}/${item.imageUrl}" width="100%" height="100%"> --%>
<!-- 				</div> -->
<%-- 			</c:forEach> --%>
<!-- 		</div> -->
	</div>
	<div id="fp-nav" class="right">
		<ul>
		</ul>
	</div>
</body>
</html>
