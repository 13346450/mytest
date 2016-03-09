<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商铺版-首页</title>
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
    <jsp:include page="../headerLogin.jsp"/>
    <jsp:include page="../headerNav.jsp"/>
	<jsp:include page="storeTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="storeLeftNav.jsp"/>
     <div class="col-xs-10" style="margin:15px 0 0 15px; padding:0;">
     <div class="collect-top">商铺管理</div>
        <ul>
        <li class="border-ccc" style="margin-top:10px; background:url(${base}/resources/images/ishangimg/firstadd.png) no-repeat left top;"> <i class="history-listitempic"></i>
          <div class="left" style="margin:30px;"><img src="${base}/resources/images/ishangimg/storename-pic.png" width="128" height="103"></div>
          <p style="margin:30px 0 10px 62px;">商铺名称：<span>沃尔玛</span></p>
          <p>手机号码：<span>12345678912</span></p>
          <p>所在地区：<span>思明区水晶国际五栋1203室</span></p>
          <p>详细地址：<span>福建厦门思明区</span></p>
          <p style="text-align:right; margin:5px 50px 10px 50px;"><a href="#"><span class="marginl30" style="color:#00a0e9;">编辑</span></a></p>
        </li>
        <li class="border-ccc" style="margin-top:10px;"> <i class="history-listitempic"></i>
          <div class="left" style="margin:30px;"><img src="${base}/resources/images/ishangimg/storename-pic.png" width="128" height="103"></div>
          <p style="margin:30px 0 10px 62px;">商铺名称：<span>沃尔玛</span></p>
          <p>手机号码：<span>12345678912</span></p>
          <p>所在地区：<span>思明区水晶国际五栋1203室</span></p>
          <p>详细地址：<span>福建厦门思明区</span></p>
          <p style="text-align:right; margin:5px 50px 10px 50px;"><a href="#"><span style="color:#00a0e9;">设置为默认商铺</span></a><a href="#"><span class="marginl30" style="color:#00a0e9;">编辑</span></a></p>
        </li>
        <li class="border-ccc" style="margin-top:10px;"> <i class="history-listitempic"></i>
          <div class="left" style="margin:30px;"><img src="${base}/resources/images/ishangimg/storename-pic.png" width="128" height="103"></div>
          <p style="margin:30px 0 10px 62px;">商铺名称：<span>沃尔玛</span></p>
          <p>手机号码：<span>12345678912</span></p>
          <p>所在地区：<span>思明区水晶国际五栋1203室</span></p>
          <p>详细地址：<span>福建厦门思明区</span></p>
          <p style="text-align:right; margin:5px 50px 10px 50px;"><a href="#"><span style="color:#00a0e9;">设置为默认商铺</span></a><a href="#"><span class="marginl30" style="color:#00a0e9;">编辑</span></a></p>
        </li>
            
           </ul>
      </div>
   </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
