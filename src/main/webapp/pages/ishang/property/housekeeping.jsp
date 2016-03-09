<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setAttribute("headerMenu", "pm");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>家政服务</title>
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
    <div class="col-xs-12 nav-tree">物业管理 > 家政服务</div>
    <div class="col-xs-12" style="text-align:center;">
      <div class="left-goods">
        <div class="left-goodstop" style="padding:0 38px;">
          <c:if test="${bizHousekeepingVOsPage.params.all}">
          	 <div class="goodstop-item"> <span class="left" style="margin:0 5px;">全部</span><i class="right"></i> </div>
          </c:if>
         <c:if test="${!bizHousekeepingVOsPage.params.all}">
          	 <c:forEach items="${bizHousekeepingVOsPage.params.names}" var="item">
          	 	<div class="cuu-category goodstop-item"> <span class="left" style="margin:0 5px;">${item}</span><i class="right delete-pic"></i> </div>
          	 </c:forEach>
          </c:if>
          <div class="right">共${bizHousekeepingVOsPage.total}家相关公司</div>
        </div>
        <div class="" style="height:90px; font-size:14px;">
          <div class="left" style="width:20%; height:40px; line-height:40px;">
            <div class="left" style="width:50%;">分类：</div>
            <div class="left" id="categoryAll" style="width:50%; text-align:left;color:#0080bb;">全部</div>
          </div>
          <div class="left" style="width:80%; height:40px; line-height:40px; padding-right:150px;color:#0080bb;">
			<c:forEach items="${housekeepingCategorys}" var="item">
				<c:if test="${!fn:contains(param.names,item)}">
					<div class="category-item left width20">${item}</div>
				</c:if>
			</c:forEach>           
          </div>
        </div>
      </div>
    </div>
    <div class="col-xs-12" style="padding:0;">
      <c:forEach items="${bizHousekeepingVOsPage.rows}" varStatus="i" var="item">
      	
      	<div class="left allhome-news<c:if test="${i.index%4==3}">-last</c:if>">
        <div class="home-newspic"> <span class="home-company">${item.name}</span> </div>
        <p style="margin:5px 0 0 15px;">介绍:${item.detail}</p>
        <i class="manage-ico manage-icotel" ></i><span class="manage-tel">${item.phoneNumber}</span>
        <div class="clear"></div>
        <i class="manage-ico manage-icoadd"></i><span class="manage-address">${item.address}</span> </div>
      </c:forEach>
    </div>
  </div>
</div>
<div style="width:800px; margin:15px auto;">
  <div id="kkpager"></div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<script type="text/javascript">
//init
$(function(){
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
	    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
	        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	    } else {  
	        return this.replace(reallyDo, replaceWith);  
	    }  
	}  
	$("#categoryAll").click(function(e){
		location.href='${base}/property/housekeeping';
	});
	$(".category-item").click(function(e){
		var category = $(this).text();
		location.href='${base}/property/housekeeping?names=${param.names}'+category+";";
	});
	$(".cuu-category").click(function(e){
		var a = '${param.names}';
		var b = $.trim($(this).text())+";";
		var category = a.replaceAll(b,"");
		location.href='${base}/property/housekeeping?names='+category;
	});
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : parseInt('${bizHousekeepingVOsPage.pageNo}'),
		//总页码
		total : parseInt('${bizHousekeepingVOsPage.totalPage}'),
		//总数据条数
		totalRecords : parseInt('${bizHousekeepingVOsPage.total}'),
		//链接前部
		getLink : function(n){
			return this.hrefFormer + this.hrefLatter + "?names=${param.names}&page="+n;
		}
		//}
	});
});
</script>
</body>
</html>
