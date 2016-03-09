<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "biz");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cart02</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<script src="${base}/resources/javascripts/JSON2.js"></script>
<script>
window.onload = function() {
	topshow();
}
</script>
</head>

<body>
<!--导入：登入注册忘记密码 弹窗模块-->
<jsp:include page="../common/headerLogin.jsp"/>
<!--导入：头部nav-->
<jsp:include page="../common/headerNav.jsp"/>
<!--导入：搜索-->
<jsp:include page="../common/headerSearchNoMenu.jsp"/>
<div class="container" style="margin-top:-30px; min-height:743px;">
  <div class="row">
    <div class="col-xs-2 second-navword">社区商圈 > 购物车</div>
    <div class="col-xs-10" style="padding-left:155px;">
      <div class="progress-pic"></div>
      <div class="progress-pic progress-pic2on"></div>
      <div class="progress-pic progress-pic3"></div>
      <div class="progress-pic progress-pic4"></div>
      <div class="progress-pic progress-pic5"></div>
    </div>
    <div class="col-xs-2 topcart-font">填写核对订单</div>
    <div class="col-xs-10" style="padding-left:155px;">
      <div class="topcart-word"><span style="color:#000; margin:5px;">我的购物车</span><br/>
        <p class="color-gray" >2015-06-12 17:15:00</p>
      </div>
      <div class="topcart-word"><span style="color:#00a0e9; margin:5px;">填写核对订单</span><br/>
        <p class="color-gray" >2015-06-12 17:20:00</p>
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">订单提交成功</span><br/>
       <!-- <p class="color-gray" >2015-06-12 17:15:00</p>-->
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">确认收货</span><br/>
        <!-- <p class="color-gray" >2015-06-12 17:15:00</p>-->
      </div>
      <div class="topcart-word1"><span style="color:#000; margin:5px;">评价</span><br/>
       <!--  <p class="color-gray" >2015-06-12 17:15:00</p>-->
      </div>
    </div>
    <form id="order-confirm-form" onsubmit="return false;">
    <input name="cmd" value="3801" type="hidden">
    <input name="data" value="" type="hidden">
    <div class="col-xs-12" style="border:1px solid #ccc; font-size:16px;margin-top: 15px;">
    
    <p style="margin:15px 10px; float:left;">收货信息</p><p onclick="location.href='${base}//user-center/update-address'" style="margin:10px; float:right; color:#00a0e9;">新增收货地址</p> 
    <div class="clear"></div>
    	<c:forEach items="${addressList}" var="item" end="0">
	    	
	    	<div style="height:50px; line-height:50px;">
	    		<input name="receiver" type="hidden" value="${item.userName}">
	    		<input name="orderAddr" type="hidden" value="${item.provinceNm} ${item.cityNm} ${item.districtNm} ${item.addressDetail}">
	    		<i class="address-select cart-ico cart-ico-input" style=" background-position:0;"></i>
	    		<span class="left">${item.userName}</span>
	    		<span class="left marginl30">${item.phoneNum}</span>
	    		<span class="left marginl30">${item.provinceNm} ${item.cityNm} ${item.districtNm} ${item.addressDetail}</span>
	    	</div>
    	</c:forEach>
    
    <div id="more-addshow" style="display:none;">
   	 	<c:forEach items="${addressList}" var="item" begin="1">
	    	<div style="height:50px; line-height:50px;">
	    	<input disabled name="receiver" type="hidden" value="${item.userName}">
	    	<input disabled name="orderAddr" type="hidden" value="${item.provinceNm} ${item.cityNm} ${item.districtNm} ${item.addressDetail}">
	    	<i class="address-select cart-ico cart-ico-input" style=""></i>
	    	<span class="left">${item.userName}</span>
	    	<span class="left marginl30">${item.phoneNum}</span>
	    	<span class="left marginl30"class="left marginl30">${item.provinceNm} ${item.cityNm} ${item.districtNm} ${item.addressDetail}</span></div>
    	</c:forEach>
    </div>
    
    <div style="margin:20px 10px; font-size:12px;"><span id="more-add" class="downlist-ico">更多地址</span></div>
    </div>
      <div class="col-xs-12 topcart-bg" style="border:1px solid #ccc;"> <span style="margin-left:50px;">店铺名称</span> <span class="margin400">单价（元）</span><span class="margin150">数量</span> <span class="margin150">小计（元）</span><span class="margin100">配送</span> </div>
    
    
    <c:forEach items="${goodsMap}" var="item">
    <div class="col-xs-12" style="height:30px; line-height:30px; margin-left:50px; font-weight:bold;">${item.value[0].shopName}</div>
    <div class="col-xs-12 border-ccc">
      <c:forEach items="${item.value}" var="goods">
	      <div class="col-xs-12 borderbt-ccc"><img src="${base}/${goods.limages}" width="96" height="96" class="left margin-pic">
	        <div class="left cart-word-width"> <span>${goods.goodsName}<br/>
	          </span><span class="red-word12">${goods.goodsNote}</span> </div>
	        <div class="left margin20-150 "> <span class="" style="font-size:16px;">&yen;${goods.discountPrice}</span> </div>
	        <div class="left" style="margin:20px 0 0 170px;">1</div>
	        <div class="left margin20-90" style="margin-left:165px;"><span class="red-word">&yen;${goods.discountPrice*countMap[goods.idKey]}</span> </div>
	        <div class="left margin20-107"> <span>自提</span> </div>
	      </div>
      </c:forEach>
    </div>
    <div class="col-xs-12 cart00-bt" style="border-top:0;"> 
      <div class="left marginl30">订单备注
      <input name="remarks" type="text" class="word60" placeholder="限60个字符"></div>
    </div>
    
    </c:forEach>
    
    
    
    
     <div class="col-xs-12 cart00-bt" style="margin-top:15px; border:0;"> 
     <div class="right"><span>商品合计（不含配送费）</span></span><span class="red-word marginr30">&yen;${totalMoney}</span>
        <input class="cart-footbtn" style="margin-right:-1px;" type="submit" value="立即结算">
      </div>
    </div>
    </form>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<script type="text/javascript">
$().ready(function(e){
	var data = [];
	//<c:forEach items="${goodsMap}" var="item">
	var shop = {goods:[],shopId:parseFloat('${item.value[0].shopId}'),remarks:'',giftId:[]};
	//<c:forEach items="${item.value}" var="goods">
	var goods = {goodsId:parseFloat('${goods.idKey}'),goodsQuantity:parseFloat('${countMap[goods.idKey]}')};
	shop.goods.push(goods);
	//</c:forEach>
	data.push(shop);
	//</c:forEach>
	$("#order-confirm-form").submit(function(){
 		var form = $(this);
		form.find("[name='remarks']").each(function(i,e){
			data[i].remarks=$(e).val();
		});
		$(this).find("[name='data']").val(JSON.stringify(data));
		$(this).ajaxSubmit({
			type:'post',
			url:'${base}/appservice/ehome',
			dataType:'json',
			success:function(data){
				alert("下单成功");
				location.href="${base}/shopping/select-pay-type";
			}
		});
	});
	$(".address-select").click(function(){
		$(".address-select").prevAll("input").attr("disabled","disabled");
		$(".address-select").css("background-position","-30px 0");
		$(this).css("background-position","0");
		$(this).prevAll("input").removeAttr("disabled");
	});
});

</script>
</body>
</html>
