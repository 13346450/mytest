<%@page import="com.dnake.utils.SessionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	SessionBean bean = (SessionBean)request.getSession().getAttribute("sessionBean");
	if(bean!=null&&bean.getBindTel()==null){
		request.getSession().invalidate();
		response.sendRedirect("/");
	}
%>
<div class="main-header" id="h1" align="center">
  <div class="container contentw">
    <div class="row">
      <div class="col-xs-2" id="top-left1"> 
      <span class="top-font">
      	<span id="comminuty-name">金山国际</span>
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="ci-right"></i>
      	</span>
        <div id="areashow">
          <div id="borderbm" ><span >选择小区</span></div>
          <ul>
            <li style="margin:20px;">城市：
              <select id="provinceId" style="margin-left:20px;"></select>
              <select id="cityId" style="margin-left:20px;"></select>
              <select id="countryId" style="margin-left:20px;"></select>
            </li>
            <li id="area" style="margin:20px;"></li>
          </ul>
        </div>
      </div>
      <div class="col-xs-10">
        <div id="top-right1" class="right"><i class="icon-tel"></i><span class="top-font">客服热线：888888</span></div>
        <c:if test="${sessionBean==null}">
        	<div id="top-center1" class="right">
	        	<span class="top-font">&nbsp;&nbsp;
	        		<span class="loginbtn2">注册</span>
	        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	        	</span>
	        </div>
	        <div id="top-center2" class="right">
	        	<span class="top-fontb">您好，请
	        		<span class="loginbtn1">登录</span>
	        		！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
	        	</span>
	        	<i class="icon-man"></i>
	        	<span class="top-fontb"></span>
	        </div>
        </c:if>
        <c:if test="${sessionBean!=null}">
	        <div id="top-center2" class="right">
	        	<span class="top-fontb">您好，
	        		<span>${sessionBean.bindTel}</span>
	        	</span>
	        	<i onclick="location.href='${base}/user-center/home'" class="icon-man"></i>
	        	<span class="top-fontb"></span>
	        </div>
        </c:if>
        <div id="top-left2" class="right"> 
       
        	<i class="icon-phone"></i>
        	<span onclick="location.href='${base}/appdown'" class="top-font">
        		手机APP&nbsp;&nbsp;</span>
        		<i class="ci-right" style="float:left; margin:5px 10px 0 0;"></i>
        	<span class="top-font">|</span>
          <div id="codeshow"></div>
        </div>
      </div>
    </div>
  </div>
  
</div>
	<div id="provinceTemp" style="display: none;">
		<option value="-1">请选择</option>
		  {{for data}}
		    <option value="{{:idKey}}">{{:cdNm}}</option>
		  {{/for}}
	</div>
	<div id="areaTemp" style="display: none;">
		  {{for data}}
		    <span data-id="{{:idKey}}" value="{{:idKey}}">{{:communityName}}</span>
		  {{/for}}
	</div>
<script type="text/javascript" src="${base}/resources/javascripts/jquery.cookie.js"></script>
<script>
$().ready(function(e){
	$("#top-left1").click(
		function () {
			$.ajax({
				type:'post',
				url : "${base}/appservice/mobileLogin",
				data: "cmd=4601",
				dataType : "json",
				success : function(data) {
					$("#provinceId").html($("#provinceTemp").render(data));
				}
			});
		}
	);
	$("#provinceId").change(function(){
		var val = $(this).val();
		if(val!='-1'){
			$.ajax({
				type:'post',
				url : "${base}/appservice/mobileLogin",
				data: "cmd=4602&id="+val,
				dataType : "json",
				success : function(data) {
					$("#cityId").html($("#provinceTemp").render(data));
				}
			});
		}
	});
	$("#cityId").change(function(){
		var val = $(this).val();
		if(val!='-1'){
			$.ajax({
				type:'post',
				url : "${base}/appservice/mobileLogin",
				data: "cmd=4603&id="+val,
				dataType : "json",
				success : function(data) {
					$("#countryId").html($("#provinceTemp").render(data));
				}
			});
		}
	});
	$("#countryId").change(function(){
		var val = $(this).val();
		if(val!='-1'){
			$.ajax({
				type:'post',
				url : "${base}/appservice/mobileLogin",
				data: "cmd=4604&id="+val,
				dataType : "json",
				success : function(data) {
					$("#area").html($("#areaTemp").render(data));
				}
			});
		}
	});
	var value = $.cookie('ishang-cdata');
	if(value!=null){
		$("#comminuty-name").html(value.split(":")[1]);
	}
	$(document).on("click","#area span",function(){
		var id = $(this).attr("data-id");
		var name = $(this).html();
		$("#comminuty-name").html(name);
		$.cookie('ishang-cdata', id+":"+name, { expires: 30 });
	});
});
</script>