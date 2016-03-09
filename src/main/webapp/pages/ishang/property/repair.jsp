<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "pm");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>物业报修</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/pages.css" />
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/commodity.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/pages.js"></script>

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
<div class="container" style=" min-height:743px;">
  <div class="row">
    <div class="col-xs-12 nav-tree">物业管理 > 物业报修 > 我的</div>
    <div class="col-xs-12" style="text-align:center;">
      <div class="left-goods">
        <div class="left-goodstop" >
          <div class="left width15">图片</div>
          <div class="left width15">昵称</div>
          <div class="left width20">状态</div>
          <div class="left width25">问题描述</div>
          <div class="left width25">时间</div>
        </div>
        <c:if test="${fn:length(bizPropertiesRepairVOs.data)==0}">没有报修记录</c:if>
        <c:forEach items="${bizPropertiesRepairVOs.data}" var="item">
	        <div class="height130">
	          <div class="left width15">
	            <div class="left repairs-pic"><img src="${base}/resources/images/ishangimg/problem.jpg" width="100%" height="100%"></div>
	          </div>
	          <div class="left width15">${item.contacts}</div>
	          <div class="left width20" style="color:#ff5b00;">
	          	<c:if test="${item.repairStatus==1}">未处理</c:if>
	          	<c:if test="${item.repairStatus==2}">已处理</c:if>
	          </div>
	          <div class="left width25">${item.content}</div>
	          <div class="left width25">${item.publishDt}</div>
	        </div>
	        <div class="left repairs-reply"><span>
	        	<c:if test="${empty item.replyContent}">物业尚未回复</c:if>
	        	<c:if test="${not empty item.replyContent}">物业回复：${item.replyContent}</c:if>
	        	</span></div>
	        <div class="clear"></div>
        </c:forEach>
      </div>
    </div>
  </div>
</div>
<div style="width:800px; margin:15px auto;">
<div id="kkpager"></div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<script type="text/javascript">
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

//init
$(function(){
	$.ajax({
		type:'post',
		url:'${base}/appservice/mobileLogin',
		data:{cmd:'5001',page:'1',count:'100',repairStatus:'0'},
		success:function(data){
			alert(data);
		}
	});
	var totalPage = 20;
	var totalRecords = 390;
	var pageNo = getParameter('pno');
	if(!pageNo){
		pageNo = 1;
	}
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		//链接前部
		hrefFormer : 'repairs-list',
		//链接尾部
		hrefLatter : '.html',
		getLink : function(n){
			return this.hrefFormer + this.hrefLatter + "?pno="+n;
		}
		
		//}
	});
});
</script>
</body>
</html>
