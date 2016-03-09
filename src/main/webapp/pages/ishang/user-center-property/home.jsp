<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物业版</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
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
	<jsp:include page="propertyTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="propertyLeftNav.jsp"/>
    <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="" style="height:360px;">
      <img src="${base}/resources/images/ishangimg/property-indexpic.jpg" width="100%" height="100%" style="padding:15px;">
      </div>
    </div>
    <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
     <div class="boxitem" style="background-color:#fff3e9;color:#ff8f58;margin-left:15px;">
        <div style="margin-top:70px;">
         <p>90</p>
         <span>人</span>
        </div>
        <div class="clear"></div>
        <p style="margin-left:75px; font-size:14px;">未缴物业费</p>
      </div>
      <div class="boxitem" style="background-color:#e5f5dd;color:#5f9046;">
       <div style="margin-top:70px;">
         <p>190</p>
         <span>条</span>
        </div>
        <div class="clear"></div>
        <p style="margin-left:75px; font-size:14px;">过期通知公告</p>
      </div>
      <div class="boxitem" style="background-color:#e0f7ff;color:#3e8aa5;">
       <div style="margin-top:70px;">
         <p>1190</p>
         <span>条</span>
       </div>
       <div class="clear"></div>
       <p style="margin-left:75px; font-size:14px;">待处理报修</p>
      </div>
      <div class="boxitem" style="background-color:#ffeefe;color:#aa51a5;margin-right:15px;">
      <div style="margin-top:70px;">
         <p>5</p>
         <span>条</span>
       </div>
       <div class="clear"></div>
       <p style="margin-left:75px; font-size:14px;">新增投诉建议</p>
      </div>
     
    </div>
    </div>
  </div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
