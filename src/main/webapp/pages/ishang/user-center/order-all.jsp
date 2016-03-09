<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心02</title>
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
    <div class="col-xs-10" style="margin:15px 0 0 15px; padding:0;">
      <input type="text" class="left manage-text" style="margin-left:0; font-size:14px;" placeholder="商品名称、商品编号、订单编号">
      <div class="left manage-searchbtn"><a href="#"><span class="btnsearch" style="font-size:14px;">查 询</span></a></div>
      <div class="col-xs-12 topcart-bg"> <span><i id="cart-check" class="cart-ico cart-ico-input"></i>全选</span> <span class="marginl75">商品</span><span class="marginl175">单价（元）</span><span class="marginl75">数量</span> <span class="marginl75">实付款</span><span class="marginl75">交易状态</span><span class="marginl75">操作</span> </div>
      <div class="col-xs-12 store-name1"> <span class="left"><i class="cart-ico cart-ico-input"></i>店铺名称：沃尔玛</span> <span class="left marginl30">订单编号：123456789</span> <span class="left marginl30">2015-07-10</span> <span class="right"><i class="cart-ico cart-ico-dele"></i>删除</span> </div>
      <div class="col-xs-12 border-ccc">
      <ul>
        <li class="borderbt-ccc" style="text-align:center;"><i class="cart-ico margin25-20" style=""></i> <img src="${base}/resources/images/ishangimg/goods.jpg" width="66" height="66" class="left margin15-0" style="">
          <div class="left p02name">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味 </div>
          <div class="left p02price">&yen;168.00</div>
          <div class="left p02number">1</div>
          <div class="left p02redprice"><span class="red-word12">&yen;89.00</span> </div>
          <div class="left p02handle"><span>交易成功</span><br/>
            <span>订单详情</span></div>
          <div class="left p02handle"><span>商品评价</span><br/>
            <span>再次购买</span></div>
          <div class="clear"></div>
        </li>
        <li class="" style="text-align:center;"><i class="cart-ico margin25-20" style=""></i> <img src="${base}/resources/images/ishangimg/goods.jpg" width="66" height="66" class="left margin15-0" style="">
          <div class="left p02name">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味 </div>
          <div class="left p02price">&yen;168.00</div>
          <div class="left p02number">1</div>
          <div class="left p02redprice"><span class="red-word12">&yen;89.00</span> </div>
          <div class="left p02handle"><span>交易成功</span><br/>
            <span>订单详情</span></div>
          <div class="left p02handle"><span>商品评价</span><br/>
            <span>再次购买</span></div>
          <div class="clear"></div>
        </li>
        </ul>
      </div>
      <div class="col-xs-12 store-name1"> <span class="left"><i class="cart-ico cart-ico-input"></i>店铺名称：沃尔玛</span> <span class="left marginl30">订单编号：123456789</span> <span class="left marginl30">2015-07-10</span> <span class="right"><i class="cart-ico cart-ico-dele"></i>删除</span> </div>
      <div class="col-xs-12 border-ccc">
      <ul>
        <li class="" style="text-align:center;"><i class="cart-ico margin25-20" style=""></i> <img src="${base}/resources/images/ishangimg/goods.jpg" width="66" height="66" class="left margin15-0">
          <div class="left p02name">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味 </div>
          <div class="left p02price">&yen;168.00</div>
          <div class="left p02number">1</div>
          <div class="left p02redprice"><span class="red-word12">&yen;89.00</span> </div>
          <div class="left p02handle"><span>待接单</span><br/>
            <span>订单详情</span></div>
          <div class="left p02handle"><span>商品评价</span><br/>
            <span>再次购买</span></div>
          <div class="clear"></div>
        </li>
        </ul>
      </div>
      <div class="col-xs-12 store-name1"> <span class="left"><i class="cart-ico cart-ico-input"></i>店铺名称：沃尔玛</span> <span class="left marginl30">订单编号：123456789</span> <span class="left marginl30">2015-07-10</span> <span class="right"><i class="cart-ico cart-ico-dele"></i>删除</span> </div>
      <div class="col-xs-12 border-ccc">
      <ul>
        <li class="" style="text-align:center;"><i class="cart-ico margin25-20" style=""></i> <img src="${base}/resources/images/ishangimg/goods.jpg" width="66" height="66" class="left margin15-0">
          <div class="left p02name">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味 </div>
          <div class="left p02price">&yen;168.00</div>
          <div class="left p02number">1</div>
          <div class="left p02redprice"><span class="red-word12">&yen;89.00</span> </div>
          <div class="left p02handle"><span>已接单</span><br/>
            <span>订单详情</span></div>
          <div class="left p02handle"><span>商品评价</span><br/>
            <span>再次购买</span></div>
          <div class="clear"></div>
        </li>
        </ul>
      </div>
      <div class="col-xs-12 store-name1"> <span class="left"><i class="cart-ico cart-ico-input"></i>店铺名称：沃尔玛</span> <span class="left marginl30">订单编号：123456789</span> <span class="left marginl30">2015-07-10</span> <span class="right"><i class="cart-ico cart-ico-dele"></i>删除</span> </div>
      <div class="col-xs-12 border-ccc">
      <ul>
        <li class="" style="text-align:center;"><i class="cart-ico margin25-20" style=""></i> <img src="${base}/resources/images/ishangimg/goods.jpg" width="66" height="66" class="left margin15-0">
          <div class="left p02name">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味 </div>
          <div class="left p02price">&yen;168.00</div>
          <div class="left p02number">1</div>
          <div class="left p02redprice"><span class="red-word12">&yen;89.00</span> </div>
          <div class="left p02handle"><span>配送中</span><br/>
            <span>订单详情</span></div>
          <div class="left p02handle"><span>商品评价</span><br/>
            <span>再次购买</span></div>
          <div class="clear"></div>
        </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
