<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.dnake.domain.business.BizProperInfoVO"%>
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
	<title>帖子详情</title>
	
	<link rel="stylesheet" href="${base}/resources/wap-resources/css/main.css" />	
	<link rel="stylesheet" href="${base}/resources/wap-resources/css/common.css"/>
	<link href="${base}/resources/wap-resources/css/base.css" rel="stylesheet" media="screen" />
	
	<script src="${base}/resources/javascripts/jquery-1.11.1.min.js" ></script>
</head>
<body >
    <div class="headBox">
    	<div id="logo"></div>
    </div>
	<div class="box" id="listPost">
		<div class="bd">
			<div class="title">
				<h1 align="center"><font size="5px">${bizNoteVO.noteTitle}</font></h1>
				<input type="hidden" id="myId" value="${bizNoteVO.idKey}"/>
				<p class="fn-cf"><span class="fn-fl">点击： ${bizNoteVO.clicksCount}   回复：${bizNoteVO.replyCount} </span><span class="fn-fr">1/1页</span></p>
			</div>
		</div>
		<div class="item">
			<div class="c-hd fn-cf">
				<p class="fn-fl author"> <span><font>${bizNoteVO.userName}</font> </span></p>
				<p class="fn-fr"><fmt:formatDate value="${bizNoteVO.publishDt }" pattern ="yyyy-MM-dd" /></p>
			</div>
			<div class="c-bd">
				<span class="floor"><i class="floor-self">楼主</i></span>
				<p><font size="4px">${bizNoteVO.noteContent}</font></p>
			</div>
		</div>
				<br />
		<div id="output1" class="item">
			<% int i = 1; %>
			<c:forEach items="${bizNoteReplyVOList}" var="list">
			<div id="output2" class="c-hd fn-cf">
				<p class="fn-fl author"> <span ><font>${list.replyUserName}</font></span></p>
				<p class="fn-fr"><fmt:formatDate value="${list.replyDt }" pattern ="yyyy-MM-dd" /></p>
			</div>
			<div class="c-bd">
				
				<span class="floor"><%= i++ %>楼
				</span>
					<p><font size="3px"> ${list.replyContent}</font></p>
				
			</div>
			</c:forEach>
			<table id="tableAdd" style="width:100%;">
				<tr>
					<td> <input type="hidden" name="first"/> </td>
				</tr>
			</table>	
		</div>	
	</div>	
	
	<section id="bar-ft" class="fn-cf">
		<!-- <p class="fn-fl fn-btn"><a class="btn" href="#">返回列表</a></p> -->
		<p class="fn-fr fn-btn"><a class="btn" href="${base}/appservice/editNote">我要发帖</a></p>
		<p class="fn-fl fn-btn"><font  color="white" style="font-size:14px; font-weight:bold;">回复本帖</font></p>
	</section>
	<div align="center" class="box">
		<form id="fm"  method="post" onsubmit="return checkSubmit();">
			<ul class="editInfos">
				<li><textarea id="txt"  name="replyContent" cols="40%" rows="5%"></textarea></li>
			</ul>
		
		
			<a href="javascript:void(0)" class="submitBtn"  onclick="save()">确认提交</a>  &nbsp; <input type="reset" value="取消" class="submitBtn"/>
		</form>
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
	<script src="${base}/resources/wap-resources/js/mobile-common.js"></script>
	<script type="text/javascript">
	var postUrl = "";
    var checkSubmitFlg = false; 
    function checkSubmit() { 
      if (checkSubmitFlg == true) { 
         return false; 
      } 
      checkSubmitFlg = true; 
      return true; 
   }
    //屏幕自适应
   $(function(){
	   getLogo();
        $("#txt").width(($(window).width()/4)*3);
        $(window).resize(function(){
           $("#txt").width(($(window).width()/4)*3);
        });
    });
   
   document.ondblclick = function docondblclick() { 
    window.event.returnValue = false; 
   } 
   document.onclick = function doconclick() { 
       if (checkSubmitFlg) { 
         window.event.returnValue = false; 
       } 
   }   

 	/* 保存数据 */
	function save() {
 		handleChange();
 		var value1 = $("#txt").val();
 		
 		if(value1 == "" || value1 == null)	
 			alert("输入为空，请重新输入！");
 		else
		{
			var json = {
					url : '${base}/appservice/insertNoteReply/'+$('#myId').val(),
					formId : "fm",
					successJSFun: function(result){
						if (result.successMsg){
	        	    	setTimeout("$('#txt').val('');addBlock('"+result.replyUserName+"','"+result.replyDt+"','"+result.replyContent+"');");
	                 } else {
	                 	$.messager.alert('错误信息',res.errorMsg,'error');
	                 }
				}
			};
			ajaxSubmitForm(json);
		}
		
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
	function addBlock(replyUserName,replyDt,replyContent)
 	{
		var rows = $('.c-bd').length;
 		var block1="<tr>\n"+"<td>\n" +
 		" <div class='c-hd fn-cf'>"+
 		"<p class='fn-fl author'> <span ><font>"+replyUserName+"</font></span></p>"+
 		"<p class='fn-fr'><span >"+replyDt+"</span></p>"+
 		"</div>"+
		"<div class='c-bd'>"+
		"<span class='floor'>"+ rows +"楼</span>"+
		"<p>"+ replyContent+"</p>"+
		"</div>"+
		"</td>\n"+"</tr>\n"
 		$("#tableAdd tr:last").after(block1);			
 	}
	</script>
</body>
</html>