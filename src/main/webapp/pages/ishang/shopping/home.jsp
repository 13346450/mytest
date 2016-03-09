<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "biz");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>社区商圈</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyFile.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyUpload.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/demo.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
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
<!--导入：搜索和菜單-->
<jsp:include page="../common/headerSearchAndMenu.jsp"/>
<div class="container">
  <div class="row">
    <div class="left top-classify">
      <div class="classify-bt">
        <p>全部商品分类</p>
      </div>
      <div class="classify-top classify-bt">
        <p class="left">社区商城</p>
        <p class="right" id="more-goods" style="color:#fff;">></p>
        <div class="clear"></div>
        <div class="goods-items01">
        	<c:forEach items="${BizCategory1}" end="5" var="item">
        		<a href="#" class="classify-item">${item.categoryName}</a>
        	</c:forEach> 
        </div>
        <div class="goods-items02">
        	<c:forEach items="${BizCategory1}" end="5" var="item">
        		<a href="#" class="left">${item.categoryName}</a>
        	</c:forEach> 
        </div>
      </div>
      <div class="classify-top classify-bt">
        <p class="left">餐饮美食</p>
        <p class="right" style="color:#fff;">></p>
        <div class="clear"></div>
        <div class="goods-items01">
        	<c:forEach items="${BizCategory2}" end="5" var="item">
        		<a class="classify-item">${item.categoryName}</a>
        	</c:forEach> 
         </div>
         <div class="goods-items02" style="margin-top:90px;">
        	<c:forEach items="${BizCategory1}" end="5" var="item">
        		<a href="#" class="left">${item.categoryName}</a>
        	</c:forEach> 
        </div>
      </div>
      <div class="classify-top" style="padding-bottom:5px;">
        <p class="left">休闲健身</p>
        <p class="right" style="color:#fff;">></p>
        <div class="clear"></div>
        <div class="goods-items01"> 
        	<c:forEach items="${BizCategory3}" end="5" var="item">
        		<a class="classify-item">${item.categoryName}</a>
        	</c:forEach> 
        </div>
        <div class="goods-items02" style="margin-top:180px;">
        	<c:forEach items="${BizCategory1}" end="5" var="item">
        		<a href="#" class="left">${item.categoryName}</a>
        	</c:forEach> 
        </div>
      </div>
    </div>
      <div class="left bannerCon" style="width:918px;height:317px;margin:0px auto;position:relative; overflow:hidden;"> 
        <div class="scroll" style="width:2000%;height:317px;position:relative;">
          <img src="${base}/resources/images/ishangimg/business-top01.jpg"><img src="${base}/resources/images/ishangimg/business-top02.jpg"><img src="${base}/resources/images/ishangimg/business-top01.jpg"><img src="${base}/resources/images/ishangimg/business-top02.jpg">
        </div>
         <div class="bannerBut" style="width:100px;height:20px;position:absolute;right:0;bottom:0px;">
     <ul>
     <li class="hover"></li><li></li><li></li><li></li>
     </ul>
    </div>
      </div>
      
   
    <div class=""><img src="${base}/resources/images/ishangimg/district-ad01.png" class="classify-pic"><img src="${base}/resources/images/ishangimg/district-ad02.png" class="classify-pic"><img src="${base}/resources/images/ishangimg/district-ad03.png" class="classify-pic"><img src="${base}/resources/images/ishangimg/district-ad04.png" style="margin-top:15px;"></div>
    <div class="left manage-lt business01">
      <h2>1F 社区商城</h2>
    </div>
    <div class="left" id="manage-rt" style="border-top:2px solid #ccc;">
    	<c:forEach items="${bizGoodsVOs1}" varStatus="i" var="item">
    		<div onclick="location.href='${base}/shopping/item?goodId=${item.idKey}'" class="left borderbt-ccc boder-rightccc w229-h50">
		        <div style="width:218px; height:130px;margin-bottom:5px;cursor:pointer;">
		        <img src="${base}/upload_files/${item.showLimages}">
		        </div>
		        <span class="goodsitem-name">${item.goodsName}</span>
		        <div class="store-evaluate">
		        	<span class="left red-word14 marginl20">&yen;${item.discountPrice}</span>
		        	<span class="right through-price marginr20">&yen;${item.unitPrice}</span>
		        </div>
		     </div>
		     <c:if test="${i.index%4==3}">
		     	<div class="clear"></div>
		     </c:if>
        </c:forEach> 
    </div>
    <div class="left manage-lt business02">
      <h2>2F 餐饮美食</h2>
    </div>
    <div class="left Div1" id="manage-rt" style="border-top:2px solid #ccc;">
      	<c:forEach items="${bizGoodsVOs1}" varStatus="i" var="item">
		     <div onclick="location.href='${base}/shopping/item?goodId=${item.idKey}'" class="left borderbt-ccc boder-rightccc w229-h50">
		        <div style="width:218px; height:130px;margin-bottom:5px;cursor:pointer;">
		        <img src="${base}/upload_files/${item.showLimages}" width="100%" height="100%">
		        </div>
		        <span class="goodsitem-name" style="cursor:pointer;">${item.goodsName}</span>
		        
		        <div class="store-evaluate"><span class="left red-word14 marginl20">&yen;${item.discountPrice}</span>
		        	<span class="right through-price marginr20">&yen;${item.unitPrice}</span></div>
		      </div>
		     <c:if test="${i.index%4==3}">
		     	<div class="clear"></div>
		     </c:if>
        </c:forEach> 
    </div>
    <div class="clear"></div>
    <div class="left manage-lt business03">
      <h2>3F 休闲健身</h2>
    </div>
    <div class="left Div1" id="manage-rt" style="border-top:2px solid #ccc;">
       <c:forEach items="${bizGoodsVOs3}" end="1" varStatus="i" var="item">
		   <div onclick="location.href='${base}/shopping/item?goodId=${item.idKey}'" class="left borderbt-ccc boder-rightccc w229-h50" style="width:458px;">
	        <div class="left"><img src="${base}/upload_files/${item.showLimages}"></div>
	        <div class="left classify-3fone"><span class="goodsitem-name02">${item.goodsName}</span><p>${item.shopName}</p>
	        <div class=""><p class="red-word14">&yen;${item.discountPrice}</p><p class="through-price">&yen;${item.unitPrice}</p></div></div>
	      </div>
        </c:forEach> 
      <div class="clear"></div>
      <c:forEach items="${bizGoodsVOs3}" begin="2" varStatus="i" var="item">
      		<div  onclick="location.href='${base}/shopping/item?goodId=${item.idKey}'" class="left boder-rightccc w229-h50">
	        <div style="width:218px; height:130px;margin-bottom:5px;cursor:pointer;">
	        <img src="${base}/upload_files/${item.showLimages}">
	        </div>
	        <span class="goodsitem-name">${item.goodsName}</span>
	        <div class="store-evaluate"><span class="left red-word14 marginl20">&yen;${item.discountPrice}</span><span class="right through-price marginr20">&yen;${item.unitPrice}</span></div>
	      </div>
      </c:forEach>
    </div>
    <div class="clear"></div>
  </div>
</div>
<script type="text/javascript">
$(function(){	
	$(".classify-top").mouseover(function(){
			$(this).css("background-color","#fff");
			$(this).css("color","#000");
			$(this).children().children('.classify-item').css("color","#000");
			$(this).children('.goods-items02').show();
			});	
	$(".classify-top").mouseout(function(){
			$(this).css("background-color","#00a7f3");
			$(this).css("color","#fff");
			$(this).children().children('.classify-item').css("color","#fff");
			$(this).children('.goods-items02').hide();
			});	
	$(".goods-items02").mouseover(function(){
		$(this).css("background-color","#fff");
			$(this).css("color","#000");
			$(this).children('.goods-items01').children('.classify-item').css("color","#000");
			$(this).parent('.classify-bt').css("background-color","#fff");
			$(this).parent('.classify-bt').css("color","#000");
			$(this).siblings('.goods-items01').children('.classify-item').css("color","#000");
			$(this).show();
			});	
	$(".goods-items02").mouseout(function(){
			$(this).parent('.classify-bt').css("background-color","#00a7f3");
			$(this).parent('.classify-bt').css("color","#fff");
			$(this).siblings('.goods-items01').children('.classify-item').css("color","#fff");
			$(this).hide();
			});	
	});
</script>
<div class="footer">厦门狄耐克电子科技有限公司 版权所有</div>
</body>
</html>
