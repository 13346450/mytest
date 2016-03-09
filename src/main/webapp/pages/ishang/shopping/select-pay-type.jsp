<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "biz");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付中心</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>

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
<div id="register">
  <div class="container box" id="box">
    <div class="row box2" align="center">
      <div class="col-xs-2 col-md-offset-9 close"><span title="关闭" style="margin:10px;"></span></div>
      <div class="col-xs-9 col-md-offset-2" id="logincont">
        <ul>
          <li id="lgstyle">
            <div class="login-ing login1 lgactive">住户</div>
            <div class="login-ing login2">商家</div>
            <div class="login-ing login3">物业</div>
          </li>
          <li  style="padding-top:45px;">
            <div class="ui-form-item" >
              <label class="ui-label"> <span class="ui-icon ui-icon-userDEF">用户名</span> </label>
              <input type="text" class="ui-input ui-input-normal" maxlength="100"  placeholder="请输入用户名" autofocus>
            </div>
          </li>
          <li  style="padding-top:45px;">
            <div class="ui-form-item ">
              <label class="ui-label"> <span class="ui-icon ui-icon-password">密码</span> </label>
              <input type="password" class="ui-input ui-input-normal" placeholder="请输入密码">
              <div class="clear"></div>
            </div>
          </li>
          <li style="padding-top:25px; font-size:14px;">
            <input type="checkbox" class="check">
            <span class="check">自动登录</span>
            <div class="right"><a class="loginbtn4" href="#" style=" color:#07b9d9;">忘记密码 |</a> <a class="loginbtn5" href="#" style=" color:#07b9d9;">新用户注册</a></div>
          </li>
          <li style="padding-top:35px; font-size:14px;">
            <input type="button" class="loginbtn" value="登 录">
          </li>
        </ul>
      </div>
      <div class="col-xs-9 col-md-offset-2"  id="logincont2">
        <ul>
          <li style="font-size:18px;">注  册 i 尚 社 区 账 号</li>
          <li id="lgstyle" style="padding-left:55px; height:39px;">
            <div class="login-ing login1 lgactive">住户</div>
            <div class="login-ing login3">商家</div>
          </li>
          <li  style=" margin-top:-8px;">
            <div class="ui-form-item" >
              <input type="text" class="ui-input1 ui-input-normal" maxlength="100"  placeholder="请输入手机号" autofocus>
            </div>
          </li>
          <li  style="padding-top:23px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请输入密码">
            </div>
          </li>
          <li  style="padding-top:23px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请再次输入密码">
            </div>
          </li>
          <li  style="padding-top:23px;">
            <div class="ui-form-item ">
              <input type="password" class="ui-input1 ui-input-normal1" placeholder="请输入验证码">
              <label class="verification ui-input-normal1">获取验证码</label>
              <div class="clear"></div>
            </div>
          </li>
          <li style="padding-top:10px; font-size:14px;">
            <input type="button" class="loginbtn" value="登 录">
          </li>
          <li style="padding-top:5px;font-size:14px;">已有账号？<a href="#"><span class="loginbtn3" style="color:#07b9d9;">点此登录</span></a> </li>
        </ul>
      </div>
      <div class="col-xs-9 col-md-offset-2"  id="logincont3">
        <ul>
          <li style="font-size:18px; margin-bottom:15px;">找 回 密 码</li>
          <li  style=" margin-top:-30px;">
            <div class="ui-form-item" >
              <input type="text" class="ui-input1 ui-input-normal" maxlength="100"  placeholder="请输入手机号" autofocus>
            </div>
          </li>
          <li  style="padding-top:32px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请输入密码">
            </div>
          </li>
          <li  style="padding-top:32px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请再次输入密码">
            </div>
          </li>
          <li  style="padding-top:32px;">
            <div class="ui-form-item ">
              <input type="password" class="ui-input1 ui-input-normal1" placeholder="请输入验证码">
              <label class="verification ui-input-normal1">获取验证码</label>
              <div class="clear"></div>
            </div>
          </li>
          <li style="padding-top:18px; font-size:14px;">
            <input type="button" class="loginbtn" value="确 定">
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="main-header" id="h1" align="center">
  <div class="container contentw">
    <div class="row">
      <div class="col-xs-2" id="top-left1"> <span class="top-font">金山国际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="ci-right"></i></span>
        <div id="areashow">
          <div id="borderbm" ><span >选择小区</span></div>
          <ul>
            <li style="margin:20px;">城市：
              <select style="margin-left:20px;">
                <option value ="1">福建省</option>
                <option value ="2">北京市</option>
                <option value="3">江西省</option>
                <option value="4">浙江省</option>
              </select>
              <select style="margin-left:20px;">
                <option value ="1">厦门市</option>
                <option value ="2">龙岩市</option>
                <option value="3">三明市</option>
                <option value="4">漳州市</option>
              </select>
              <select style="margin-left:20px;">
                <option value ="1">湖里区</option>
                <option value ="2">海沧区</option>
                <option value="3">集美区</option>
                <option value="4">思明区</option>
              </select>
            </li>
            <li id="area" style="margin:20px;"> <span>金山社区</span></li>
          </ul>
        </div>
      </div>
      <div class="col-xs-10">
        <div id="top-right1" class="right"><i class="icon-tel"></i><span class="top-font">客服热线：888888</span></div>
        <div id="top-center1" class="right"><span class="top-font">&nbsp;&nbsp;<span class="loginbtn2">注册</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span></div>
        <div id="top-center2" class="right"><span class="top-fontb">您好，请<span class="loginbtn1">登录</span>！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</span><i class="icon-man"></i><span class="top-fontb"></span></div>
        <div id="top-left2" class="right"> <a href="APPdown.html"><i class="icon-phone"></i><span class="top-font">手机APP&nbsp;&nbsp;<i class="ci-right"></i></span></a><span class="top-font">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</span>
          <div id="codeshow"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div id="h2" class="container">
  <div class="row contentw">
    <div class="col-xs-12 first">
      <div class="col-xs-2"><img src="${base}/resources/images/ishangimg/slogo.png" width="100%" height="100%"></div>
      <div class="col-xs-10 logo-after">
        <div class="row search1">
          <input type="text" class="col-xs-8 col-md-offset-3 search-text1">
          <div class="col-xs-1 search-btn1"><a href="#"><span class="btnsearch">搜 索</span></a></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container" style="margin-top:-30px; min-height:743px;">
  <div class="row">
    <div class="col-xs-12 second-navword">社区商圈 > 购物车 > 支付中心</div>
 
    <div class="col-xs-6 left-word" >i尚社区订单号：35664778922333<span class="downlist-ico">订单详情</span></div>
    <div class="col-xs-6 right-word" style="">应付总金额：<span class="red-word">333</span>元</div>
    <div class="col-xs-12 pay-border" style="">
    
    <ul>
    <li class="on-paytype">
    <em class="cart-ico pay-margin cart-ico-inputon" style=""></em><p class="left width70">支付宝</p><i class="paytype-ico"></i><span class="right marginr30 pay-money" style="display:block;">支付：<span class="red-word">89.00</span> 元</span>
    </li>
    <li>
    <em class="cart-ico pay-margin"></em><p class="left width70">银行卡</p><i class="paytype-ico paytype-icoy"></i><i class="paytype-ico paytype-icoy-type"></i><span class="color-gray">银行卡尾号****006</span><span class="right marginr30 pay-money">支付：<span class="red-word">89.00</span> 元</span>
    </li>
    <li>
    <em class="cart-ico pay-margin"></em><p class="left width70">货到付款</p><i class="paytype-ico paytype-icoh"></i><span class="color-gray">可现金支付或者POS机刷卡支付</span><span class="right marginr30 pay-money">支付：<span class="red-word">89.00</span> 元</span>
    </li>
    <li>
    <em class="cart-ico pay-margin"></em><p class="left width70">到店付款</p><i class="paytype-ico paytype-icod"></i><span class="color-gray">到店消费付款</span><span class="right marginr30 pay-money">支付：<span class="red-word">89.00</span> 元</span>
    </li>
    </ul>
    <div class="clear"></div>
   
    
    
    </div>
    
   
  </div>
<input class="left store-paybtn" type="button" value="添加银行卡" style="margin-left:-15px;">
          <input class="left store-paybtn" type="button" value="其他付款方式">
          <input class="right sure-paybtn" type="button" value="确认付款">
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
