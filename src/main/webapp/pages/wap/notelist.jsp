<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />  
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />  
	<meta content="telephone=no" name="format-detection" />
	<title>我的帖子</title>
	<link rel="stylesheet" href="${base}/resources/wap-resources/css/main.css" />
	<link rel="stylesheet" href="${base}/resources/wap-resources/css/common.css"/>
	<link href="${base}/resources/wap-resources/css/base.css" rel="stylesheet" media="screen" />
	
	<script src="${base}/resources/javascripts/jquery-1.11.1.min.js" ></script>
	
	<script type="text/javascript">
		$(function(){
		   	getLogo();
		});
		//获取自定义logo
		function getLogo(){
			$.ajax({
				type : "POST",
				url : "${base}/system/SysLogoSettings/queryAll",
				datatype : "json",
				success : function(data) {
					var myobj = eval(data);
					for (var i = 0; i < myobj.length; i++) {
						if (myobj[i].type == 'wapLogo') {
							var imageUrl=myobj[i].imageUrl;
							if(imageUrl != null){
								$('#logo').css('background-image','url(${base}/'+imageUrl+')');
							}
							
						}
					}
				}
			});
		}
	</script>
</head>
<body >
  <div class="headBox">
    	<div id="logo"></div>
    	<p class="fn-fr fn-btn" style="margin: 10px;"><a class="btn" href="${base}/appservice/editNote">我要发帖</a></p>
  </div>
<div id="wrap">
	<div  class="box">
	    <ul class="ul-list focus-list">
	    	<c:forEach items="${bizNoteList}" var="list">
	    		<li>
	    			<a href="${base}/appservice/viewNoteInfo/${list.idKey}" >
	    				<font color="black" style="font-size: 16px;">
	    					&nbsp;&nbsp;&nbsp; 	${list.noteTitle}
	    				</font>
	    			</a>
	    			<br />
            		<p class="p-btm"><u><fmt:formatDate value="${list.publishDt}" pattern ="yyyy-MM-dd" /></u>|
            		<u>点击(${list.clicksCount})</u>|<i>
            		<u>评论(${list.replyCount})</u></i>|
            		<i><a href="${base}/appservice/deleteNote?idKey=${list.idKey}">删除</a></i>
            		</p>
	    		</li>
	    	</c:forEach>
	    </ul>
	</div>	
</div>
<!-- footer -->
<footer>
    <div class="footer" >

        <p class="footer_t">
            	闽ICP备07501510号-1
            <span class="footer_t_ico"><%=(new java.util.Date()).toLocaleString()%></span>
        </p>
    </div>
</footer>
</body>
</html>