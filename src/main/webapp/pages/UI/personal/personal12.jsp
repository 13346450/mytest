<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心12</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>
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
	<jsp:include page="../personalHeader.jsp"/>
<!--login-->
<div class="container" style="margin-top:-40px; min-height:743px;">
  <div class="row">
     <jsp:include page="personalLeftNav.jsp"/>
      <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">添加收货地址</div>
      <div id="information" class="marginl20 margint10">
        <p><i class="redstart">*</i>收货人:</p>
          <input type="text" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>联系电话:</p>
          <input type="text" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>所在地区:</p>
        <select class="areadownlist">
          <option value ="1">福建省</option>
          <option value ="2">北京市</option>
          <option value="3">江西省</option>
          <option value="4">浙江省</option>
        </select>
        <select class="areadownlist">
          <option value ="1">厦门市</option>
          <option value ="2">龙岩市</option>
          <option value="3">三明市</option>
          <option value="4">漳州市</option>
        </select>
        <select class="areadownlist">
          <option value ="1">湖里区</option>
          <option value ="2">海沧区</option>
          <option value="3">集美区</option>
          <option value="4">思明区</option>
        </select>
        <p class="margint10"><i class="redstart">*</i>详细地址:</p>
        <input type="text" maxlength="100" class="border-ccc p08input" style="width:500px;"/>
        <div style="margin:20px 0 20px 15px;">
          <input type="button" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="保存"/>   <span  style="width:120px; height:40px; line-height:40px;"><i id="cart-check" class="cart-ico cart-ico-input" style="margin:5px 5px 0 80px;"></i>设置为默认收货地址</span>
        <div class="clear"></div>
        </div>
      </div>
    </div>
     <div class="col-xs-10 addlist" style="padding:0;">
      <ul>
        <li class="border-ccc" style=" background:url(resources/images/ishangimg/firstadd.png) no-repeat left top;"> <i class="history-listitempic"></i>
          <p style="margin:30px 0 10px 62px;">收货人：<span>李小姐</span></p>
          <p>手机号码：<span>福建厦门思明区</span></p>
          <p>所在地区：<span>思明区水晶国际五栋1203室</span></p>
          <p>详细地址：<span>12345678912</span></p>
          <p style="text-align:right; margin:5px 50px 10px 50px; color:#00a0e9;"><span class="marginl30">编辑</span></p>
        </li>
        <li class="border-ccc"> <i class="history-listitempic"></i>
          <p style="margin:30px 0 10px 62px;">收货人：<span>李小姐</span></p>
          <p>手机号码：<span>福建厦门思明区</span></p>
          <p>所在地区：<span>思明区水晶国际五栋1203室</span></p>
          <p>详细地址：<span>12345678912</span></p>
          <p style="text-align:right; margin:5px 50px 10px 50px; color:#00a0e9;"><span>设置为默认收货地址</span><span class="marginl30">编辑</span></p>
        </li>
        <li class="border-ccc"> <i class="history-listitempic"></i>
          <p style="margin:30px 0 10px 62px;">收货人：<span>李小姐</span></p>
          <p>手机号码：<span>福建厦门思明区</span></p>
          <p>所在地区：<span>思明区水晶国际五栋1203室</span></p>
          <p>详细地址：<span>12345678912</span></p>
          <p style="text-align:right; margin:5px 50px 10px 50px; color:#00a0e9;"><span>设置为默认收货地址</span><span class="marginl30">编辑</span></p>
        </li>
      </ul>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
     