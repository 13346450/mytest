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
<script src="${base}/resources/javascripts/ishangjs/jquery.datetimepicker.js" charset="gbk"></script>
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
	<jsp:include page="storeTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="storeLeftNav.jsp"/>
     <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">添加红包</div>
      <div id="information" class="marginl20 margint10">
        <p><i class="redstart">*</i>红包名称:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>红包个数:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>使用条件:</p>
        <p class="firstword" style="">满</p>
        <input type="text" class="border-ccc input60"/>
        <div class="secondword" style="border-left:0;">元</div>
        <p class="firstword">减</p>
        <input type="text" class="border-ccc input60"/>
        <div class="secondword" style="border-left:0;">元</div>
        <p class="firstword">使用，每笔订单限用一个</p>
        <div class="clear"></div>
        <p class="margint10"><i class="redstart">*</i>红包使用有效期限:</p>
        <input type="text" onclick="SelectDate(this,'yyyy-MM-dd')" class="begintime" placeholder="红包开始时间"/>
          <p class="firstword">至</p>
        <input type="text" onclick="SelectDate(this,'yyyy-MM-dd')" class="endtime" placeholder="红包结束时间"/>  
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
