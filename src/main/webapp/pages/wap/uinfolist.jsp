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
	<title>留言板</title>

	<link rel="stylesheet" href="${base}/resources/wap-resources/css/common.css"/>
	<link href="${base}/resources/wap-resources/css/base.css" rel="stylesheet" media="screen" />
	<script src="${base}/resources/javascripts/mobile-common.js"></script>
	<style type="text/css">
		p.fn-fr { width: auto; margin: 15px; color: #FFF;float: right; }
		.btn { display: block; height: 28px; font-size: 15px; line-height: 
		28px; padding: 0 8px; text-align: center; border: 1px solid #BBB; border-radius: 2px;
		 background: #EEE;
		  background-image: -webkit-gradient(linear, left top, left bottom, from( #FFF ), to( #EAEAEA ));
		   background-image: -webkit-linear-gradient( #FFF, #EAEAEA );
		    background-image: -moz-linear-gradient( #FFF, #EAEAEA ); 
		    background-image: -ms-linear-gradient( #FFF, #EAEAEA ); 
		    background-image: -o-linear-gradient( #FFF, #EAEAEA ); 
		    background-image: linear-gradient( #FFF, #EAEAEA );
		     text-shadow: 0 0 1px #FFF; box-shadow: 0 0 0; }
	</style>
	<script type="text/javascript" src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${base}/resources/wap-resources/js/jquery.timeago.js"></script>
	<script type="text/javascript">
		function save()
		{
			this.location.href="${base}/appservice/editUinfo/"+$("#communityId").val();
		}
		
		$(function(){
			getLogo();
			 $("u.timeago").timeago();
	         $("#editForm").width(($(window).width()/4)*3)
	         $(window).resize(function(){
	            $("#editForm").width(($(window).width()/4)*3)
	         });
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
	<p class="fn-fr fn-btn">
		<a class="btn" href="#" id="myId" onclick="save()">我的建议</a>
	</p>
</div>

<div id="wrap">
	<div class="box">
	    <ul class="ul-list focus-list" style="margin-left: 15px;">
	    <li><input type="hidden" id="communityId" value="${communityId}"/></li>
	    	<c:forEach items="${bizUinfoCollectVO}" var="list">
	    		<li >
	    			<label><font style="font-size:17px;"> ${list.userContent}</font></label>
	    			<u><br/></u>
            		<p class="p-btm">
            			<u>${list.userName}</u>|
            			<u></u>
            			<u class="timeago" title="<fmt:formatDate value="${list.publishDt}" pattern ="yyyy-MM-dd HH:mm:ss"/>"></u>   
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