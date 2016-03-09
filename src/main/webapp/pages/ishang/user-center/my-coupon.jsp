<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心07</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>

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
      <div class="pdiscount-btn pdiscount-btnon">已领取（10）</div>
      <div class="pdiscount-btn">已使用（3）</div>
      <div class="pdiscount-btn">已过期（2）</div>
      
      <ul>
      <c:forEach items="${list}" var="item">
      	<p>1</p>
      </c:forEach>
        <li class="border-ccc pdiscount-width">
          <div class="classify-pic classifycou-pic01" style="margin:12px;">
            <div class="left height100" style=""><span class="symbol-price">&yen;</span><span class="store-price">100</span></div>
            <div class="right" style="margin-top:10px;"><span class="font-size24">满100元</span><br/>
              <span class="font-size24">减20元</span></div>
            <div class="clear"></div>
            <div class="margin5" style="font-size:16px;">使用条件：沃尔玛所有商品</div>
            <div class="margin5">特价商品不能使用 有效期截止7月8日-7月28日</div>
          </div>
          <div class="left p07delete"><i class="cart-ico cart-ico-dele"></i>删除</span></div>
          <div class="right p07delet" style="margin-right:10px;color:#00a0e9;">查看可购买商品></span></div>
        </li>
        <li class="border-ccc pdiscount-width">
          <div class="classify-pic classifycou-pic02" style="margin:12px;">
            <div class="left height100" style=""><span class="symbol-price">&yen;</span><span class="store-price">100</span></div>
            <div class="right" style="margin-top:10px;"><span class="font-size24">满100元</span><br/>
              <span class="font-size24">减20元</span></div>
            <div class="clear"></div>
            <div class="margin5" style="font-size:16px;">使用条件：沃尔玛所有商品</div>
            <div class="margin5">特价商品不能使用 有效期截止7月8日-7月28日</div>
          </div>
          <div class="left p07delete"><i class="cart-ico cart-ico-dele"></i>删除</span></div>
          <div class="right p07delet" style="margin-right:10px;color:#00a0e9;">查看可购买商品></span></div>
        </li>
        <li class="border-ccc pdiscount-width">
          <div class="classify-pic classifycou-pic03" style="margin:12px;">
            <div class="left height100" style=""><span class="symbol-price">&yen;</span><span class="store-price">100</span></div>
            <div class="right" style="margin-top:10px;"><span class="font-size24">满100元</span><br/>
              <span class="font-size24">减20元</span></div>
            <div class="clear"></div>
            <div class="margin5" style="font-size:16px;">使用条件：沃尔玛所有商品</div>
            <div class="margin5">特价商品不能使用 有效期截止7月8日-7月28日</div>
          </div>
          <div class="left p07delete"><i class="cart-ico cart-ico-dele"></i>删除</span></div>
          <div class="right p07delet" style="margin-right:10px;color:#00a0e9;">查看可购买商品></span></div>
        </li>
        <li class="border-ccc pdiscount-width">
          <div class="classify-pic classifycou-pic04" style="margin:12px;">
            <div class="left height100" style=""><span class="symbol-price">&yen;</span><span class="store-price">100</span></div>
            <div class="right" style="margin-top:10px;"><span class="font-size24">满100元</span><br/>
              <span class="font-size24">减20元</span></div>
            <div class="clear"></div>
            <div class="margin5" style="font-size:16px;">使用条件：沃尔玛所有商品</div>
            <div class="margin5">特价商品不能使用 有效期截止7月8日-7月28日</div>
          </div>
          <div class="left p07delete"><i class="cart-ico cart-ico-dele"></i>删除</span></div>
          <div class="right p07delet" style="margin-right:10px;color:#00a0e9;">查看可购买商品></span></div>
        </li>
        <li class="border-ccc pdiscount-width">
          <div class="classify-pic classifycou-pic05" style="margin:12px;">
            <div class="left height100" style=""><span class="symbol-price">&yen;</span><span class="store-price">100</span></div>
            <div class="right" style="margin-top:10px;"><span class="font-size24">满100元</span><br/>
              <span class="font-size24">减20元</span></div>
            <div class="clear"></div>
            <div class="margin5" style="font-size:16px;">使用条件：沃尔玛所有商品</div>
            <div class="margin5">特价商品不能使用 有效期截止7月8日-7月28日</div>
          </div>
          <div class="left p07delete"><i class="cart-ico cart-ico-dele"></i>删除</span></div>
          <div class="right p07delet" style="margin-right:10px;color:#00a0e9;">查看可购买商品></span></div>
        </li>
        <li class="border-ccc pdiscount-width">
          <div class="classify-pic classifycou-pic06" style="margin:12px;">
            <div class="left height100" style=""><span class="symbol-price">&yen;</span><span class="store-price">100</span></div>
            <div class="right" style="margin-top:10px;"><span class="font-size24">满100元</span><br/>
              <span class="font-size24">减20元</span></div>
            <div class="clear"></div>
            <div class="margin5" style="font-size:16px;">使用条件：沃尔玛所有商品</div>
            <div class="margin5">特价商品不能使用 有效期截止7月8日-7月28日</div>
          </div>
          <div class="left p07delete"><i class="cart-ico cart-ico-dele"></i>删除</span></div>
          <div class="right p07delet" style="margin-right:10px;color:#00a0e9;">查看可购买商品></span></div>
        </li>
      </ul>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
     