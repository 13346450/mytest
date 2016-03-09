<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setAttribute("headerMenu", "biz");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<style type="text/css">
.item-checked{background-position:  0 0;}
</style>
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
      <div class="progress-pic progress-pic2"></div>
      <div class="progress-pic progress-pic3"></div>
      <div class="progress-pic progress-pic4"></div>
      <div class="progress-pic progress-pic5"></div>
    </div>
    <div class="col-xs-2 topcart-font">全部商品</div>
    <div class="col-xs-10" style="padding-left:155px;">
      <div class="topcart-word"><span style="color:#00a0e9; margin:5px;">我的购物车</span><br/>
        <p class="color-gray">2015-06-12 17:15:00</p>
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">填写核对订单</span><br/>
        <!-- <p class="color-gray" >2015-06-12 17:20:00</p>-->
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">订单提交成功</span><br/>
        <!-- <p class="color-gray"">2015-06-12 17:15:00</p>-->
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">确认收货</span><br/>
        <!-- <p class="color-gray">2015-06-12 17:15:00</p>-->
      </div>
      <div class="topcart-word1"><span style="color:#000; margin:5px;">评价</span><br/>
        <!-- <p class="color-gray">2015-06-12 17:15:00</p>-->
      </div> 
    </div>
    <div class="col-xs-12 topcart-bg"> 
    	<span><i data-root class="cart-check-item cart-ico cart-ico-input"></i>全选</span> 
    	<span class="margin100">商品</span>
    	<span class="margin400">单价（元）</span>
    	<span class="margin150">数量</span> 
    	<span class="margin150">小计（元）</span>
    </div>
    <c:forEach items="${bizShoppingcartVOMap}" var="shop">
    	<div class="col-xs-12 store-name1"> 
	    	<span><i data-shop="${shop.key}" class="cart-check-item cart-ico cart-ico-input"></i>${shop.value[0].shopName}</span>
	    </div>
	    <div class="col-xs-12 border-ccc">
	    	<c:forEach items="${shop.value}" var="good">
	    	<div class="col-xs-12 borderbt-ccc">
	      		<i data-id="${good.idKey}" data-goodsId="${good.goodsId}" data-item="${shop.key}" class="cart-check-item cart-ico cart-ico-input margin50"></i> 
	      		<img src="${base}/${good.simageUrl}" width="96" height="96" class="left margin-pic">
		        <div class="left cart-word-width"> 
		        	<span>${good.goodsName}<br/></span>
		        	<span class="red-word12">---------------</span>
		        </div>
		        <div class="left margin20"> 
		        	<span class="through-price">&yen;${good.unitPrice}<br/></span>
		        	<span class="red-word">&yen;${good.discountPrice}</span>
		        	
		        </div>
		        <div class="left reduce good-less"  data-id="${good.idKey}">-</div>
		        <div class="left reduce number" data-price="${good.discountPrice}" id="count_${good.idKey}">${good.goodsCount}</div>
		        <div class="left reduce increat good-plus" data-id="${good.idKey}">+</div>
		        <div class="left margin20-90"><span class="red-word">&yen;<span id="money_${good.idKey}" class="item-total-money">${good.discountPrice * good.goodsCount}</span></span> </div>
		      </div>
	    	</c:forEach>
	    </div>
    </c:forEach>
    <div class="col-xs-12 topcart-bg footcart-bg"> <i data-root class="cart-check-item cart-ico"></i><span class="left">全选</span>
      <div class="left"> 
      		<i class="cart-ico cart-ico-dele"></i>
      		<span id="remove-goods" class="left" >删除</span>
      		<i class="cart-ico cart-ico-col"></i>
      		<span id="add-collection" class="left">移进收藏夹</span>
      		<i class="cart-ico cart-ico-s"></i>
      		<span id="add-share" class="left">分享</span>
      	</div>
      	<div class="right"><span>已选 <i class="red-word12">${size}</i> 件商品，总计（不含配送费）</span></span>
      	<span class="red-word marginr30">&yen;<span class="total-money">89.00</span></span>
        <input class="cart-footbtn" id="js" type="button" value="立即结算">
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<script type="text/javascript">
$().ready(function(){
	$("#add-collection").click(function(){
		alert("功能未开发[add-collection]");
	});
	$("#add-share").click(function(){
		alert("功能未开发[add-share]");
	});
	$("#remove-goods").click(function(){
		var ids = "";
		$(".cart-check-item.item-checked[data-item]").each(function(i,e){
			ids+=$(e).attr("data-id")+",";
		})
		if(ids==""){
			alert("请勾选要移除的商品");return;
		}
		
		$.ajax({
			type:'post',
			url:'${base}/appservice/ehome',
			data:{cmd:3505,deleteIds:ids},
			success:function(data){
				alert("删除成功");
				location.reload();
			},error:function(){
				alert("接口异常");
			}
		});
	});
	$(".good-plus,.good-less").click(function(){
		var plus = $(this).hasClass("good-plus");
		var dataId = $(this).attr("data-id");
		var countEl = $("#count_"+dataId);
		var count = parseInt(countEl.html())+(plus?1:-1);
		if(count<1){
			count = 1;
		}
		var price = parseFloat(countEl.attr("data-price"));
		countEl.html(count);
		$("#money_"+dataId).html(price*count);
		$(".total-money").click();
		$.ajax({
			type:'post',
			url:'${base}/appservice/ehome',
			data:{cmd:3504,goodsCount:count,idKey:dataId},
			success:function(data){
				alert(data);
			},error:function(){
				alert("接口异常");
			}
		});
	});
	$(".cart-check-item").addClass("item-checked");
	$(".cart-check-item").click(function(){
		var shopId = $(this).attr("data-shop");
		var itemShopId = $(this).attr("data-item");
		
		if(!shopId&&!itemShopId){
			//全选
			if($(this).hasClass("item-checked")){
				$(".cart-check-item").removeClass("item-checked");
			}else{
				$(".cart-check-item").addClass("item-checked");
			}
		}else if(shopId){
			if($(this).hasClass("item-checked")){
				$(".cart-check-item[data-item='"+shopId+"']").removeClass("item-checked");
				$(this).removeClass("item-checked");
				$(".cart-check-item[data-root]").removeClass("item-checked");
			}else{
				$(".cart-check-item[data-item='"+shopId+"']").addClass("item-checked");
				$(this).addClass("item-checked");
			}
		}else{
			if($(this).hasClass("item-checked")){
				$(this).removeClass("item-checked");
			}else{
				$(this).addClass("item-checked");
			}
			var f = true;
			$(".cart-check-item[data-item='"+itemShopId+"']").each(function(i,e){
				if(!$(e).hasClass("item-checked"))
					f = false;
			});
			if(f){
				$(".cart-check-item[data-shop='"+itemShopId+"']").addClass("item-checked");
			}else{
				$(".cart-check-item[data-shop='"+itemShopId+"']").removeClass("item-checked");
			}
		}
		var f = true;
		$(".cart-check-item[data-shop]").each(function(i,e){
			if(!$(e).hasClass("item-checked"))
				f = false;
		});
		if(f){
			$(".cart-check-item[data-root]").addClass("item-checked");
		}else{
			$(".cart-check-item[data-root]").removeClass("item-checked");
		}
	});
	//item-total-money
	$(".total-money").click(function(){
		var totalMoney = 0;
		$(".item-total-money").each(function(i,e){
			totalMoney+=parseFloat($(e).html());
		});
		$(".total-money").html(totalMoney);
	});
	$(".total-money").click();
	$("#js").click(function(){
		var paramString = "";
		$(".cart-check-item[data-item]").each(function(i,e){
			var count = $("#count_"+$(e).attr("data-id")).html();
			paramString+="id="+$(e).attr("data-goodsId")+"&count="+count+"&";
		});
		location.href='${base}/shopping/confirm-order?'+paramString;
	});
});
</script>
</body>
</html>
