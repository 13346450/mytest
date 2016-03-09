<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-xs-12 first">
	<div class="col-xs-2">
		<img onclick="location.href='${base}/'" src="${base}/resources/images/ishangimg/slogo.png" width="100%"
			height="100%">
	</div>
	<div class="col-xs-10 logo-after">
		<div class="row search">
			<input type="text" class="col-xs-8 search-text">
			<div class="col-xs-1 search-btn">
				<a href="#"><span class="btnsearch">搜 索</span></a>
			</div>
			<div class="col-xs-2 cart">
				<i class="ico-cart"></i>
				<p onclick="location.href='${base}/shopping/cart'">
					购物车（<span style="color: #F00;">25</span>）
				</p>
			</div>
		</div>
	</div>
</div>