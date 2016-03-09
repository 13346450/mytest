<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />  
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />  
	<meta content="telephone=no" name="format-detection" />
	<title>住户信息采集</title>

	<link rel="stylesheet" href="${base}/resources/wap-resources/css/main.css" />	
	<link rel="stylesheet" href="${base}/resources/wap-resources/css/common.css"/>
	<link href="${base}/resources/wap-resources/css/base.css" rel="stylesheet" media="screen" />
	
	<script type="text/javascript" src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
	<script src="${base}/resources/wap-resources/js/mobile-common.js"></script>
	
	<script type="text/javascript">
	 $(function(){
		 getLogo();
         $("#editForm").width($(window).width())
         //$("#a").height(($(window).height()/4)*3)
         $(window).resize(function(){
            $("#editForm").width($(window).width())
            //$("#a").height(($(window).height()/4)*3)
         });
      });
	 function handleChange()
	 {
		var ExportForm = $("#form"); 
		var value1 = $("#userContent").val();
		ExportForm.method = "POST"; 
		ExportForm.action = "${base}/appservice/insertUinfo"; 
		
		if(value1 == "" || value1 == null)	
			alert("输入为空，请重新输入！");
		else
			ExportForm.submit();
	 }
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
	</div>
<div id="wrapper">
	<div class="box">
	    
		<form id="form" action="${base}/appservice/insertUinfo"  method="post" id="editForm">
				<ul class="editInfos">
					<%-- <li><input type="hidden" id="communityId" name="communityId" value="${communityId}"/></li> --%>
					<li><label><font color="#ff0000">* </font>消息建议：</label></li>
					<li><textarea id="userContent" name="userContent" cols="45%" rows="15%"></textarea></li>
					<li><input type="button" value="确认提交" class="submitBtn" onclick="handleChange()"/>   &nbsp; <input type="reset" value="取消" class="submitBtn"/></li>
				</ul>
		</form>		
	</div>	
</div>
<!-- footer -->
    <div class="footer" >

        <p class="footer_t">
            	闽ICP备07501510号-1
            <span class="footer_t_ico"><%=(new java.util.Date()).toLocaleString()%></span>
        </p>
    </div>
</body>
</html>