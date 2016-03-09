<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>智能控制</title>
<link rel="stylesheet"  href="resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="resources/css/ishangcss/normal.css" >
<script src="resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="resources/javascripts/ishangjs/header.js"></script>
<script>
window.onload = function() {
	getHeight();
	topshow();
}
</script>
</head>

<body>
<!--login-->

    <jsp:include page="../headerLogin.jsp"/>
    <jsp:include page="../headerNav.jsp"/>
<div class="container">
  <div class="row">
    <div class="col-xs-2" id="about-lt">
      <div  class="leftbtn leftbtn-act">安防对讲</div>
      <div class="leftbtn">智能家居</div>
      <div class="leftbtn">智能停车</div>
    </div>
    <div class="col-xs-10" id="about-rt">
      <div class="word-top word-border">
  <h2>安防对讲<small style="color:#CCC;">Security Intercom</small></h2>
</div>

<div class="word-pictb" style="border:0;"><img src="resources/images/ishangimg/security.jpg" width="100%" height="100%" style="padding:10px;"></div>
<div class="aboutus">
  <p>始于客户需求，终于客户满意&rdquo;服务理念为市场提供优质产品与最优服务为市场提供优质产品与最优服务为市场提供优质产品与最优服务为市场提供优质产品与最优服务，为市场提供优质产品与最优服务！</p>
</div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
