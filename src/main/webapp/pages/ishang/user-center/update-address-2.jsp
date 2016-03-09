<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>收货地址管理</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<script>
window.onload = function() {
	topshow();
}
</script>
</head>

<body>
<!--login-->
 <jsp:include page="../common/headerLogin.jsp"/>
    <jsp:include page="../common/headerNav.jsp"/>
	<jsp:include page="common/personalHeader.jsp"/>
<!--login-->
<div class="container" style="margin-top:-40px; min-height:743px;">
  <div class="row">
     <jsp:include page="common/personalLeftNav.jsp"/>
      <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">编辑收货地址</div>
      <form id="add-address-form" onsubmit="return false;">
      <input name="cmd" value="4402" type="hidden">
      <input name="idKey" value="${param.id}" type="hidden">
      <input name="isDefault"  value="${bizAddrVO.isDefault}" type="hidden">
      <div id="information" class="marginl20 margint10">
        <p><i class="redstart">*</i>收货人:</p>
          <input type="text" name="userName" value="${bizAddrVO.userName}" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>联系电话:</p>
          <input type="text" name="phone" value="${bizAddrVO.phoneNum}" maxlength="100" class="border-ccc p08input"/>
        <p><i class="redstart">*</i>所在地区: ${bizAddrVO.provinceNm} ${bizAddrVO.cityNm} ${bizAddrVO.districtNm}</p>
        <select name="provinceId" class="areadownlist"></select>
        <select name="cityId" class="areadownlist"></select>
        <select name="districtId" class="areadownlist"></select>
        <p class="margint10"><i class="redstart">*</i>详细地址:</p>
        <input type="text" name="addrDetail" value="${bizAddrVO.addressDetail}" maxlength="100" class="border-ccc p08input" style="width:500px;"/>
        <div style="margin:20px 0 20px 15px;">
          <input type="submit" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="保存"/>   <span  style="width:120px; height:40px; line-height:40px;">
        	<c:if test="${bizAddrVO.isDefault==1}">
        		<i  class="isDefault-i cart-ico cart-ico-input" style="background-position:0 0; margin:5px 5px 0 80px;"></i>
        	</c:if>
        	<c:if test="${bizAddrVO.isDefault!=1}">
        		<i  class="isDefault-i cart-ico cart-ico-input" style="margin:5px 5px 0 80px;"></i>
        	</c:if>
        	设置为默认收货地址</span>
        <div class="clear"></div>
        </div>
      </div>
      </form>
    </div>
     <div class="col-xs-10 addlist" style="padding:0;"></div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<div id="provinceTemp" style="display: none;">
	<option value="-1">请选择</option>
	  {{for data}}
	    <option value="{{:idKey}}">{{:cdNm}}</option>
	 {{/for}}
</div>
<script type="text/javascript">
$().ready(function(){
	$(".isDefault-i").click(function(){
		var e = $("#add-address-form").find("[name='isDefault']");
		var isDefault = !(e.fieldValue()=="1");
		e.val(isDefault?1:0);
		if(isDefault){
			$(this).css("background-position","0 0");
		}else{
			$(this).css("background-position","-30px 0");
		}
	});
	//创建
	$("#add-address-form").submit(function(){
		var userName = $.trim($(this).find("[name='userName']").fieldValue())+"";
		var phone = $.trim($(this).find("[name='phone']").fieldValue())+"";
		var provinceId = $.trim($(this).find("[name='provinceId']").fieldValue())+"";
		var cityId = $.trim($(this).find("[name='cityId']").fieldValue())+"";
		var districtId = $.trim($(this).find("[name='districtId']").fieldValue())+"";
		var addrDetail = $.trim($(this).find("[name='addrDetail']").fieldValue())+"";
		if(!/.{2,}/.test(userName)){
			alert("请输入至少2位数的联系人");
		}else if(!/[0-9-]{7,}/.test(phone)){
			alert("请输入正确的手机号码");
		}else if(provinceId==""||provinceId=="-1"){
			alert("请选择省份");
		}else if(cityId==""||cityId=="-1"){
			alert("请选择城市");
		}else if(districtId==""||districtId=="-1"){
			alert("请选择区");
		}else if(!/.{5,}/.test(addrDetail)){
			alert("请输入至少5位数的详细地址");
		}else{
			$(this).ajaxSubmit({
				type:'post',
				url : "${base}/appservice/ehome",
				dataType : "json",
				success : function(data) {
					alert("创建成功");
					//location.reload();
				}
			});
		}
	});
	$(".setToDefault").click(function(){
		var id = $(this).attr("data-id");
		$.ajax({
			type:'post',
			url : "${base}/appservice/ehome",
			data: "cmd=4405&idKey="+id,
			dataType : "json",
			success : function(data) {
				alert("设置成功");
				location.reload();
			}
		});
	});
	$(".deleteAddress").click(function(){
		var id = $(this).attr("data-id");
		$.ajax({
			type:'post',
			url : "${base}/appservice/ehome",
			data: "cmd=4403&id="+id,
			dataType : "json",
			success : function(data) {
				alert("修改成功");
				location.href='${base}/user-center/update-address';
			}
		});
	});
	$.ajax({
		type:'post',
		url : "${base}/appservice/mobileLogin",
		data: "cmd=4601",
		dataType : "json",
		success : function(data) {
			$("#add-address-form").find("[name='provinceId']").html($("#provinceTemp").render(data));
		}
	});
	$("#add-address-form").find("[name='provinceId']").change(function(){
		var val = $(this).val();
		if(val!='-1'){
			$.ajax({
				type:'post',
				url : "${base}/appservice/mobileLogin",
				data: "cmd=4602&id="+val,
				dataType : "json",
				success : function(data) {
					$("#add-address-form").find("[name='cityId']").html($("#provinceTemp").render(data));
				}
			});
		}
	});
	$("#add-address-form").find("[name='cityId']").change(function(){
		var val = $(this).val();
		if(val!='-1'){
			$.ajax({
				type:'post',
				url : "${base}/appservice/mobileLogin",
				data: "cmd=4603&id="+val,
				dataType : "json",
				success : function(data) {
					$("#add-address-form").find("[name='districtId']").html($("#provinceTemp").render(data));
				}
			});
		}
	});
});
</script>
</body>
</html>
     