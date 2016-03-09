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
	<title>物业信息详情</title>
	
	<script src="${base}/resources/javascripts/jquery-1.11.1.min.js" ></script>
	<link rel="stylesheet" href="${base}/resources/wap-resources/css/common.css"/>
	<link href="${base}/resources/wap-resources/css/base.css" rel="stylesheet" media="screen" />
	<script type="text/javascript">
	 $(function(){
		 getLogo();
         $("#a").width(($(window).width()/12)*11)
         //$("#a").height(($(window).height()/4)*3)
         $(window).resize(function(){
            $("#a").width(($(window).width()/12)*11)
            //$("#a").height(($(window).height()/4)*3)
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
<body>
  <div class="headBox">
    	<div id="logo"></div>
  </div>
<div id="wrap">
	<div class="neirong">
		<h1 align="center">${bizProperInfoVO.infoTitle }</h1>
			<div class="other" align="center" style="font-size:12pt;  ">${bizProperInfoVO.typeName }</div>
			<table id="a" align="center" cellpadding="0" cellspacing="0" class="neirong-box" >
			<tbody>
                <tr>
			        <td >
			        	${bizProperInfoVO.infoContent }
			        	<br />
			        	<div align="right" style="margin-right:20px; "> ${bizProperInfoVO.properName} </div>
			        	<div align="right" style="margin-right:20px; "> <fmt:formatDate value="${bizProperInfoVO.publishDt}" pattern ="yyyy-MM-dd" /></div>
			        	<br />
					</td>
				</tr>
			</tbody>
			</table>
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