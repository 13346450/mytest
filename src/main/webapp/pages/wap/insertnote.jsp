<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no" name="format-detection" />
<title>我要发帖</title>

<link rel="stylesheet"
	href="${base}/resources/wap-resources/css/main.css" />
<link rel="stylesheet"
	href="${base}/resources/wap-resources/css/common.css" />
<link href="${base}/resources/wap-resources/css/base.css"
	rel="stylesheet" media="screen" />
<script src="${base}/resources/wap-resources/js/mobile-common.js"></script>
<script type="text/javascript"
	src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
<style type="text/css">
</style>

<script type="text/javascript">
	var checkSubmitFlg = false;
	function checkSubmit() {
		if (checkSubmitFlg == true) {
			return false;
		}
		checkSubmitFlg = true;
		return true;
	}
	document.ondblclick = function docondblclick() {
		window.event.returnValue = false;
	}
	document.onclick = function doconclick() {
		if (checkSubmitFlg) {
			window.event.returnValue = false;
		}
	}
	$(function() {
		getLogo();
		$("#text").width(($(window).width() / 4) * 3);
		$("#title").width(($(window).width() / 4) * 2);
		$("#type").width(($(window).width() / 4) * 2);
		$(window).resize(function() {
			$("#text").width(($(window).width() / 4) * 3);
			$("#title").width(($(window).width() / 4) * 2);
			$("#type").width(($(window).width() / 4) * 2);
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
	function handleChange() {
		var ExportForm = $("#form");
		var value1 = $("#title").val();
		var value2 = $("#text").val();

		ExportForm.method = "POST";
		ExportForm.action = "${base}/appservice/insertNote";

		if (value1 == "" || value1 == null || value2 == "" || value2 == null)
			return;
		else
			ExportForm.submit();
	}
</script>

</head>
<body>
	<div class="headBox">
		<div id="logo"></div>
	</div>
	<div id="wrapper">
		<div class="box">

			<form id="form" action="${base}/appservice/insertNote" method="post"
				id="editForm" onsubmit="return checkSubmit();">
				<ul class="editInfos">

					<li><input type="hidden" name="idKey" /> <label>帖子标题:</label>
						<input style="background-color: rgb(220, 226, 241);" id="title"
						type="text" name="noteTitle" value="" /></li>
					<li><label style="margin-left: -3px;">信息类别:</label> <select
						id="type" name="noteType"
						style="width: 168px; height: 25px; background-color: rgb(220, 226, 241);">
							<c:forEach items="${listCategory}" var="list">
								<option value="${list.idKey}">${list.categoryName}</option>
							</c:forEach>
					</select></li>

					<li><textarea id="text" name="noteContent" cols="35%"
							rows="15%" style="background-color: rgb(220, 226, 241);"></textarea></li>
					<li><input type="button" value="确认提交" class="submitBtn"
						onclick="handleChange()" /> &nbsp; <input type="reset" value="重置"
						class="submitBtn" /></li>
				</ul>
			</form>
		</div>
	</div>
	<!-- footer -->
	<footer>
	<div class="footer">

		<p class="footer_t">
			闽ICP备07501510号-1 <span class="footer_t_ico"><%=(new java.util.Date()).toLocaleString()%></span>
		</p>
	</div>
	</footer>
</body>
</html>