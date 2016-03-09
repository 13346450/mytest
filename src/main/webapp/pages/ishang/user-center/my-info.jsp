<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息修改</title>
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
$(function(){	
	 $(".pdiscount-btn01").click(function(){
		   $(".pdiscount-btn01").addClass("pdiscount-btnon");
	       $(".pdiscount-btn02").removeClass("pdiscount-btnon");
	       $("#information").show();
		   $("#head-portrait").hide();
	    });
	  $(".pdiscount-btn02").click(function(){
		   $(".pdiscount-btn02").addClass("pdiscount-btnon");
	       $(".pdiscount-btn01").removeClass("pdiscount-btnon");
	       $("#information").hide();
		   $("#head-portrait").show();
	    });
});
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
      <div class="col-xs-10" style="margin:15px 0 0 15px; padding:0;">
      <div class="pdiscount-btn pdiscount-btn01 pdiscount-btnon" style="width:487px;">基本资料</div>
      <div class="pdiscount-btn pdiscount-btn02" style="width:487px;">头像编辑</div>
      <div class="clear"></div>
      <div id="information" class="marginl20 margint10">
        <p><i class="redstart">*</i>!!!当前头像:</p>
        <p class="border-ccc marginl15" style="width:64px;"> 
        	<img src="${base}/resources/images/ishangimg/personal-user.png"></p>
        <p><i class="redstart">*</i>!!!昵称:</p>
        <input type="text" value="" maxlength="100" class="border-ccc p08input"/>
        <p class="marginl15" style="height:35px; line-height:35px;">!!!真实姓名:</p>
        <input type="text" maxlength="100" class="border-ccc p08input" />
        <p class=""><i class="redstart">*</i>性别:</p>
          <label class="marginl15">
            <input name="Fruit" type="radio" <c:if test="${sysUser.gender=='M'}">checked="checked"</c:if> value=""/>
            男 </label>
          <label class="marginl30">
            <input name="Fruit" type="radio" <c:if test="${sysUser.gender=='F'}">checked="checked"</c:if> value=""/>
            女 </label>
          <label class="marginl30">
            <input name="Fruit" type="radio" <c:if test="${empty sysUser.gender}">checked="checked"</c:if> value=""/>
            保密 </label>
        <p class="margint10"><i class="redstart">*</i>所在地:</p>
        <select  class="areadownlist">
          <option value ="1">福建省</option>
          <option value ="2">北京市</option>
          <option value="3">江西省</option>
          <option value="4">浙江省</option>
        </select>
        <select class="areadownlist">
          <option value ="1">厦门市</option>
          <option value ="2">龙岩市</option>
          <option value="3">三明市</option>
          <option value="4">漳州市</option>
        </select>
        <select class="areadownlist">
          <option value ="1">湖里区</option>
          <option value ="2">海沧区</option>
          <option value="3">集美区</option>
          <option value="4">思明区</option>
        </select>
        <p class="margint10"><i class="redstart">*</i>所属小区:</p>
        <input type="text" value="" maxlength="100" class="border-ccc" style="width:250px;height:30px; line-height:30px;margin-left:15px; margin-bottom:10px;"/>
        <div style="margin:20px 0 0 15px;">
          <input type="button" class="loginbtn" style="width:120px; height:35px; line-height:35px;" value="保存"/>
        </div>
      </div>
      <div id="head-portrait" style="margin:35px; display:none;">
        <div class="left">
          <p>当前头像</p>
          <div class="border-ccc" style="width:200px; height:200px;"> <img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"> </div>
          <div class="margint20">
            <input type="button" class="p08-upbtn" style="" value="选择要上传的头像"/>
          </div>
          <p class="color-gray margint20" style="line-height:30px;width:200px;"><i class="marginall5">*</i>支持JPG 、 PNG 、 JPEG 、 BMP图片格式，文件不要超过4M。</p>
          <div class="margint30">
            <input type="button" class="loginbtn" style="width:120px; height:35px; line-height:35px;" value="保存"/>
          </div>
        </div>
        <div class="left margin100 ">
          <p>效果预览<span class="color-gray">（请注意小尺寸头像是否清晰）</span></p>
          <div class="border-ccc pic80"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%">
            <p class="color-gray" style="width:80px; text-align:center;">80*80</p>
          </div>
          <div class="border-ccc pic50" style="margin:30px 0 0 50px;"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%">
            <p class="color-gray" style="width:50px; text-align:center;">50*50</p>
          </div>
          <div class="clear"></div>
          <div class="margint50">
            <p>可选头像</p>
            <ul>
            <li style="width:240px; height:60px;">
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
            </li>
            <li style="width:240px; height:60px;">
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
              <div class="border-ccc smalllistpic"><img src="${base}/resources/images/ishangimg/personal-user.png" width="100%" height="100%"></div>
            </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
     