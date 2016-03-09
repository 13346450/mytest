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
<title>cart04</title>
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
     <div class="col-xs-3 second-navword"><span>社区商圈 > 社区商圈 > 个人中心</span><br/><span>>已买商品</span></div>
    <div class="col-xs-9" style="padding-left:57px;">
      <div class="progress-pic"></div>
      <div class="progress-pic progress-pic2on"></div>
      <div class="progress-pic progress-pic3on"></div>
      <div class="progress-pic progress-pic4on"></div>
      <div class="progress-pic progress-pic5"></div>
    </div>
    </div>
    <div class="row">
    <div class="col-xs-2 topcart-font" id="cart04">确认收货</div>
    <div class="col-xs-10" style="padding-left:155px;">
      <div class="topcart-word"><span style="color:#000; margin:5px;">我的购物车</span><br/>
        <p class="color-gray" >2015-06-12 17:15:00</p>
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">填写核对订单</span><br/>
        <p class="color-gray" >2015-06-12 17:20:00</p>
      </div>
      <div class="topcart-word"><span style="color:#000; margin:5px;">订单提交成功</span><br/>
       <p class="color-gray" >2015-06-12 17:15:00</p>
      </div>
      <div class="topcart-word"><span style="color:#00a0e9; margin:5px;">确认收货</span><br/>
        <p class="color-gray">2015-06-12 17:15:00</p> 
      </div>
      <div class="topcart-word1"><span style="color:#000; margin:5px;">评价</span><br/>
       <!--  <p class="color-gray" >2015-06-12 17:15:00</p>-->
      </div>
    </div>
    </div>
    <div class="row">
     <div class="col-xs-12 left-word03">i尚社区订单号：35664778922333<span class="margin100">状态：<span style="color:#eb7f0c;"><b>已收货</b></span></span>
     <button class="right cart-nextbtn" id="cart-nextbtn1" type="button" onclick="myFunction()">商品评价</button>
     </div>
    <div class="col-xs-12 topcart-bg border-ccc" style="text-align:center;"> <div class="left" style="width:25%; text-align:left; padding-left:20px;">订单信息</div><div class="left" style="width:15%;">状态</div> <div class="left" style="width:15%;">单价（元）</div><div class="left" style="width:15%;">数量</div> <div class="left" style="width:15%;">小计（元）</div><div class="left" style="width:15%; text-align:left; padding-left:5%;">配送</div> </div>
    
    <div class="col-xs-12 border-ccc">
      <div class="col-xs-12 borderbt-ccc"><div class="left width25"><div class="cart-goodspic"><img src="${base}/resources/images/ishangimg/goods.jpg" width="100%" height="100%" class="left"></div>
        <div class="left cart-word-width" style="width:150px;"> <span>沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味<br/>
          </span></div></div>
        <div class="left cart-goodsitem">已支付</div>
        <div class="left cart-goodsitem">&yen;168.00</span> </div>
        <div class="left cart-goodsitem">1</div>
        <div class="left cart-goodsitem"><span class="red-word" style="font-size:14px;">&yen;89.00</span> </div>
        <div class="left cart-goodsitem"> <span>自提</span> </div>
      </div>
      <div class="col-xs-12 borderbt-ccc"><div class="left width25"><div class="cart-goodspic"><img src="${base}/resources/images/ishangimg/goods.jpg" width="100%" height="100%" class="left"></div>
        <div class="left cart-word-width" style="width:150px;"> <span>沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味<br/>
          </span></div></div>
        <div class="left cart-goodsitem">已支付</div>
        <div class="left cart-goodsitem">&yen;168.00</span> </div>
        <div class="left cart-goodsitem">1</div>
        <div class="left cart-goodsitem"><span class="red-word" style="font-size:14px;">&yen;89.00</span> </div>
        <div class="left cart-goodsitem"> <span>自提</span> </div>
      </div>
      <div class="col-xs-12 cart00-bt cart00-price"> 
     <span>实付款 : </span></span><span class="red-word marginr30"><b>&yen;178.00</b></span>
    </div>
    </div>
    <div class="col-xs-12 border-ccc" style="margin-top:20px; padding:20px 30px;"> 
     <h4>卖家信息</span></h4>
     <div class="left cartbtm-word">沃尔玛</div>
     <div class="left cartbtm-word">联系电话：0592-5569903</div>
     <div class="left cartbtm-word">成交时间：2015-7-1 10-20-10</div>
     <div class="left cartbtm-word">确认时间：2015-7-11 10-20-10</div>
     
    <div class="clear borderbt-ccc" style="padding:10px; margin:20px 0;"></div>
     <h4>卖家信息</span></h4>
     <div class="left cartbtm-word">收货地址：福建省厦门市湖里区</div>
     <div class="left cartbtm-word">联系电话：18778956213</div>
     <div class="left cartbtm-word">配送方式：自提</div>
     <div class="left cartbtm-word">买家留言：请快递在周末送上门。</div>
    </div>

</div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
