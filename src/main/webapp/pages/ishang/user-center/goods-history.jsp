<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心03</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>

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
    <div class="col-xs-10 history-boder">
      <div class="history-btn history-btntop">浏览历史</div>
      <div class="history-btn history-btnlist01">6.17<span style="font-size:12px;">（共5件商品）</span></div>
      <ul>
      <li class="history-listitem left">
        <div class="history-listitempic"></div>
        <img src="${base}/resources/images/ishangimg/search-pic18.png" width="158" height="135" style="margin:0px 5px;">
        <p class="marginall5">五芳斋 五香粽子礼盒2只装</p>
        <span class="red-word12 marginall5">&yen;89<i class="persaonal-ico personal09"></i></span> 
        </li>
      <li class="history-listitem left">
        <div class="history-listitempic"></div>
        <img src="${base}/resources/images/ishangimg/search-pic18.png" width="158" height="135" style="margin:0px 5px;">
        <p class="marginall5">五芳斋 五香粽子礼盒2只装</p>
        <span class="red-word12 marginall5">&yen;89<i class="persaonal-ico personal09"></i></span> 
        </li>
      <li class="history-listitem left">
        <div class="history-listitempic"></div>
        <img src="${base}/resources/images/ishangimg/search-pic18.png" width="158" height="135" style="margin:0px 5px;">
        <p class="marginall5">五芳斋 五香粽子礼盒2只装</p>
        <span class="red-word12 marginall5">&yen;89<i class="persaonal-ico personal09"></i></span> 
        </li>
      <li class="history-listitem left">
        <div class="history-listitempic"></div>
        <img src="${base}/resources/images/ishangimg/search-pic18.png" width="158" height="135" style="margin:0px 5px;">
        <p class="marginall5">五芳斋 五香粽子礼盒2只装</p>
        <span class="red-word12 marginall5">&yen;89<i class="persaonal-ico personal09"></i></span> 
        </li>
      <li class="history-listitem left">
        <div class="history-listitempic"></div>
        <img src="${base}/resources/images/ishangimg/search-pic18.png" width="158" height="135" style="margin:0px 5px;">
        <p class="marginall5">五芳斋 五香粽子礼盒2只装</p>
        <span class="red-word12 marginall5">&yen;89<i class="persaonal-ico personal09"></i></span> 
        </li>
        </ul>
      <div class="clear"></div>
      <div class="history-btn history-btnlist01">6.16<span style="font-size:12px;">（共3件商品）</span></div>
      <ul>
      <li class="history-listitem left">
        <div class="history-listitempic"></div>
        <img src="${base}/resources/images/ishangimg/search-pic18.png" width="158" height="135" style="margin:0px 5px;">
        <p class="marginall5">五芳斋 五香粽子礼盒2只装</p>
        <span class="red-word12 marginall5">&yen;89<i class="persaonal-ico personal09"></i></span> 
       </li>
       </ul>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
