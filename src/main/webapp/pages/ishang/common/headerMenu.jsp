<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-xs-12 second">
	<div class="navbar-collapse collapse nav">
		<ul id="nav" style="padding-left:0;">
			<li><a href="${base}/" 
				<c:if test="${empty headerMenu}">class="active"</c:if>
			>首页</a></li>
			<li class="navpadding"><a href="${base}/property/home"
				<c:if test="${headerMenu=='pm'}">class="active"</c:if>
			>物业管理</a></li>
			<li class="navpadding"><a href="${base}/shopping/home/"
				<c:if test="${headerMenu=='biz'}">class="active"</c:if>
			>社区商圈</a></li>
			<li class="navpadding"><a href="${base}/">智能控制</a></li>
			<li class="navpadding"><a href="${base}/">产品与解决方案</a></li>
			
			<li class="navpadding"><a href="${base}/news/home" 
				<c:if test="${headerMenu=='news'}">class="active"</c:if>
			 >新闻动态</a></li>
			<li class="navpadding"><a href="${base}/about-us/home" 
				<c:if test="${headerMenu=='aboutus'}">class="active"</c:if>
			>关于我们</a></li>
		</ul>
	</div>
</div>