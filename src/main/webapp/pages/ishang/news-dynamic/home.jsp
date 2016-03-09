<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "news");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新闻动态</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/pages.css" />
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<script>
window.onload = function() {
	
	topshow();
	getHeight();
	
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
    <div class="col-xs-2" id="about-lt">
      <div onclick="location.href='${base}/news/home?newsType=0'" class="leftbtn <c:if test="${sysNewsDynamicVOPage.params.newsType==0}">leftbtn-act</c:if>">最新动态</div>
      <div onclick="location.href='${base}/news/home?newsType=1'" class="leftbtn <c:if test="${sysNewsDynamicVOPage.params.newsType==1}">leftbtn-act</c:if>">媒体报道</div>
    </div>
    <div class="col-xs-10" id="about-rt">
      <div class="news-list">
        <div class="word-top word-border">
          <h2>
          	<c:if test="${param.newsType==1}">
          	媒体报道
          	</c:if>
          	<c:if test="${param.newsType==0}">
          	新闻动态
          	</c:if>
           <small style="color:#CCC;">News</small></h2>
        </div>
        <c:forEach  items="${sysNewsDynamicVOPage.rows}" var="item">
	        <div class="word-border row">
	          <div class="col-xs-3"><span style="margin-left:20px;">${item.publishDate}</span>
	            <div style=" height:200px; border:1px solid #999; margin:10px;"><img src="${base}/${item.imageUrl}" width="100%" height="100%" style="padding:5px;"></div>
	          </div>
	          <div class="col-xs-9">
	            <h5 class="word-top">${item.newsTitle}</h5>
	            <div class="preview">${item.newsContent}</div>
	            <div class="nextview"></div>
	            <div data-id="${item.idKey}" class="read-more" style="text-align:right; color:#e89900; bottom:0; padding-top:20px;">>点击查看全文</div>
	          </div>
	        </div>
        </c:forEach>
      </div>
      <c:forEach  items="${sysNewsDynamicVOPage.rows}" var="item">
	       <div class="news-content" id="${item.idKey}">
	        <div class="word-top word-border">
	          <h4 style="text-align:center;"><strong>${item.newsTitle}</strong></h4>
	          <h5 class="" style="text-align:right; color:#999;">${item.publishDate}</h5>
	        </div>
	        <div class="aboutus">
	          <p>${item.newsContent}</p>
	        </div>
	      </div>
      </c:forEach>
     
    </div>
  </div>
</div>
<div style="width:1150px; margin:25px auto; padding-left:400px;">
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
	$(".read-more").click(function(){
		var id = $(this).attr("data-id");
		$('#'+id).show();
		$(".news-list").hide();
	});	
	var totalPage = parseInt('${sysNewsDynamicVOPage.totalPage}');
	var totalRecords = parseInt('${sysNewsDynamicVOPage.total}');;
	var pageNo = parseInt('${sysNewsDynamicVOPage.pageNo}');
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		//链接前部
		//hrefFormer : 'homemaking-list',
		//链接尾部
		//hrefLatter : '.html',
		getLink : function(n){
			return this.hrefFormer + this.hrefLatter + "?newsType=${sysNewsDynamicVOPage.params.newsType}&page="+n;
		}
		
		//}
	});
});
</script>
</body>
</html>
