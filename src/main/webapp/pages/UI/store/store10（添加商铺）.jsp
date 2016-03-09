<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商铺版</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/zyFile.js"></script>
<script src="${base}/resources/javascripts/ishangjs/zyUpload.js"></script>
<script src="${base}/resources/javascripts/ishangjs/demo.js"></script>
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
      <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">添加商铺</div>
      <div id="information" class="marginl20 margint10">
        <p><i class="redstart">*</i>商铺名称:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>手机号码/电话号码:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>所属小区:</p>
        <input type="text" maxlength="100" class="border-ccc p08input" style="width:500px;"/>
        <p><i class="redstart">*</i>详细地址:</p>
        <input type="text" maxlength="100" class="border-ccc p08input" style="width:500px;"/>
        <div class="clear"></div>
        <div style="margin:20px 0 20px 15px;">
          <input type="button" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="提交"/>
          <div class="clear"></div>
        </div>
      </div>
    </div>
  </div>
 </div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>

</body>
</html>