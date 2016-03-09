<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
	request.setAttribute("headerMenu", "biz");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>商铺</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/pages.css" />
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyFile.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyUpload.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/demo.js"></script>
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
<div><img src="${base}/resources/images/ishangimg/store-toppic.jpg" style="width:100%;"></div>
<div class="container">
<div class="store-topname" style="">
	<div class="left store-toppic">
	<img alt="无logo" src="${base}/" width="100%" height="100%"></div>
	<span class="left store-topword">|</span>
	<span class="left store-topword">${bizShop.name}</span></div>
  <div class="row">
      <c:forEach items="${bizPromotionPage.rows}" var="item" end="3" varStatus="i">
  		<div class="left classify-pic classifycou-pic0${i.index+1}">
	      	<div class="left height100" style="">
<!-- 	      	<span class="symbol-price" style="">&yen;</span> -->
<!-- 	      	<span class="store-price">100</span> -->
			</div>
	      	<div class="right" style="margin-top:10px;">
	      	<span class="font-size24">${item.name}</span><br/>
<!-- 	      	<span class="font-size24">减20元</span> -->
	      	</div>
	      	<div class="clear"></div>
	      	<div class="getdiscount-btn getdiscount-btn0${i.index+1}">立即领取</div>
	      	<div class="margin5">
<!-- 	      		特价商品不能使用  -->
	      有效期截止<fmt:formatDate value="${item.startDt}" pattern="MM月dd日" />-<fmt:formatDate value="${item.endDt}" pattern="MM月dd日" /></div>
      	</div>
  	</c:forEach>
    <div class="col-xs-2" style="width:23%;">
      <div class="left-goods">
        <div class="left-goodstop"><span class="left-goodsword">店长推荐</span></div>
        <c:forEach items="${recommendedGoodPage.rows}" var="item">
        	<div onclick="location.href='${base}/shopping/item?goodId=${item.idKey}'"  class="left-goodspic" style="background:url(${base}/resources/images/ishangimg/adpic-hotico.png) no-repeat left top;">
        		<img src="${base}/${item.limages}" width="223" height="223" class="margin20-10" style="position:relative; z-index:-1;">
	          <p class="left-goodsword">${fn:substring(item.goodsName,0,20)}</p>
	          <p class="red-word margin10-30">&yen;${item.discountPrice}</p>
	        </div>
        </c:forEach>
      </div>
    </div>
    <div class="col-xs-10" style="width:77%;">
    <c:forEach items="${goodPage.rows}" var="item" varStatus="i">
    	<c:if test="${i.index%4==0}"><div class="storerow"></c:if>
    	<div onclick="location.href='${base}/shopping/item?goodId=${item.idKey}'" class="left w215-h260 <c:if test="${i.index%4!=0}">marginl10</c:if>">
	        <div class="w213-h160"><img src="${base}/${item.limages}" width="100%" height="100%"></div>
	        <div class="margint20"><span class="store-grayname color-gray">${bizShop.name}</span></div>
	        <span class="goodsitem-name">${fn:substring(item.goodsName,0,10)}</span>
	        <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;">
	        	<b>&yen;${item.unitPrice}</b></span>
	        	<span class="right through-price marginr20">&yen;${item.discountPrice}</span></div>
	    </div>
	    <c:if test="${i.index%4==3}">
	    	</div>
	    	 <div class="clear"></div>
	    </c:if>
    </c:forEach>
     
     
      
      <div style="width:700px;margin:45px 0 0 180px;">
      <div id="kkpager"></div>
      </div>
      
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司 版权所有</div>
<script type="text/javascript">
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

//init
$(function(){
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : parseInt("${goodPage.pageNo}"),
		//总页码
		total : parseInt("${goodPage.totalPage}"),
		//总数据条数
		totalRecords : parseInt("${goodPage.total}"),
// 		//链接前部
// 		hrefFormer : 'store',
// 		//链接尾部
// 		hrefLatter : '.html',
		getLink : function(n){
			return this.hrefFormer + this.hrefLatter + "?idKey=${param.idKey}&pageIndex="+n;
		}
		/*
		,lang				: {
			firstPageText			: '首页',
			firstPageTipText		: '首页',
			lastPageText			: '尾页',
			lastPageTipText			: '尾页',
			prePageText				: '上一页',
			prePageTipText			: '上一页',
			nextPageText			: '下一页',
			nextPageTipText			: '下一页',
			totalPageBeforeText		: '共',
			totalPageAfterText		: '页',
			currPageBeforeText		: '当前第',
			currPageAfterText		: '页',
			totalInfoSplitStr		: '/',
			totalRecordsBeforeText	: '共',
			totalRecordsAfterText	: '条数据',
			gopageBeforeText		: '&nbsp;转到',
			gopageButtonOkText		: '确定',
			gopageAfterText			: '页',
			buttonTipBeforeText		: '第',
			buttonTipAfterText		: '页'
		}*/
		
		//,
		//mode : 'click',//默认值是link，可选link或者click
		//click : function(n){
		//	this.selectPage(n);
		//  return false;
		//}
	});
});
</script>
</body>
</html>
