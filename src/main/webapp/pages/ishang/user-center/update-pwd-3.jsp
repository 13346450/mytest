<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心11</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<script>
window.onload = function() {
	topshow();
}
</script>
</head>

<body>
<!--login-->
 <jsp:include page="../common/headerLogin.jsp"/>
    <jsp:include page="../common/headerNav.jsp"/>
	<jsp:include page="common/personalHeader.jsp"/>
<!--login-->
<div class="container" style="margin-top:-40px; min-height:743px;">
  <div class="row">
     <jsp:include page="common/personalLeftNav.jsp"/>
      <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">修改密码</div>
      <div style="margin:30px 0;">
        <div class="progress-pic"></div>
        <div class="progress-pic progress-pic2on"></div>
        <div class="progress-pic03 progress-pic03on"></div>
        <div class="col-xs-10" style="padding-left:0px;">
          <div class="topcart-word"><span style="color:#00a0e9; margin:5px;">验证身份</span> </div>
          <div class="topcart-word"><span style="color:#00a0e9; margin:5px;">修改登录密码</span> </div>
          <div class="topcart-word"><span style="color:#00a0e9; margin:5px;">成功</span> </div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="succeedpic"></div>
      <div class="left margint50"><p style="color:#44b9ef; font-size:18px; font-weight:bold;">恭喜您，修改密码成功！</p>
      <span class="left">最新安全级别：</span><span class="grade-dd" style="width:60px; background-color:#d9d9d9;"><i class="grade-ddone"></i></span><span style="color:#ff6c00;">高级</span></div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
     
     