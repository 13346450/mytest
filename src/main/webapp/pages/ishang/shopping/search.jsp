<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "biz");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>搜索</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/pages.css" />
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyFile.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyUpload.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/demo.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/pages.js"></script>

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
          <li style="padding-top:45px; font-size:14px;">
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
      <div class="clear"></div>
      <div class="clear"></div>
      </li>
      <li style="padding-top:10px;">
        <div class="left mg-btnbg1" ><a href="javascript:void(0)" style="color:#FFF;" onclick="">确 认</a></div>
        <div id="manage-reset" class="left mg-btnbg2"><a href="javascript:void(0)" style="color:#FFF;" onclick="">取 消</a></div>
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
        <div id="top-left2" class="right"><a href="APPdown.html"> <i class="icon-phone"></i><span class="top-font">手机APP&nbsp;&nbsp;<i class="ci-right"></i></span></a><span class="top-font">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</span>
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
        <div class="row search">
          <input type="text" class="col-xs-8 search-text">
          <div class="col-xs-1 search-btn"><a href="#"><span class="btnsearch">搜 索</span></a></div>
          <div class="col-xs-2 cart"><a href="cart01.html"> <i class="ico-cart"></i>
            <p style="color:#FFF;">购物车（<span style="color:#F00;">25</span>）</p>
            </a> </div>
        </div>
      </div>
    </div>
    <div class="col-xs-12 second">
      <div class="navbar-collapse collapse nav">
        <ul id="nav">
          <li><a href="#">首页</a></li>
          <li class="navpadding"><a href="#">物业管理</a></li>
          <li class="navpadding"><a href="#" class="active">社区商圈</a></li>
          <li class="navpadding"><a href="#">智能控制</a></li>
          <li class="navpadding"><a href="#">产品与解决方案</a></li>
          <li class="navpadding"><a href="#">新闻动态</a></li>
          <li class="navpadding"><a href="#">关于我们</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!--login end-->
<div class="container">
  <div class="row">
    <div class="col-xs-12 nav-tree">全部结果 > 零食</div>
    <div class="col-xs-12" style="text-align:center;">
      <div class="left-goods">
        <div class="left-goodstop" style="padding:0 38px;">
          <div class="goodstop-item"> <span class="left" style="margin:0 5px;">价格：20~39</span><i class="right delete-pic"></i> </div>
          <div class="goodstop-item"> <span class="left" style="margin:0 5px;">口味：咸味</span><i class="right delete-pic"></i> </div>
          <div class="right">共200家相关商品</div>
        </div>
        <div class="searchrow-all" style="">
          <div class="left searchl-side"> <b>品牌：</b> </div>
           <div class="left search-center" style="">
          <div class="right searchr-rside"> <span id="more-add01" class="downlist-ico">更多</span> </div>
            <div class="left width15">旺旺</div>
            <div class="left width15">乐事</div>
            <div class="left width15">费列罗</div>
            <div class="left width15">好丽友</div>
            <div class="left width15">奥利奥奥利奥</div>
            <div class="left width15">好吃点</div>
            <div class="left width15">米老头</div>
            <div class="left width15">和路雪</div>
            <div class="left width15">百联</div>
            <div class="left width15">百奇</div>
            <div class="left width15">喜之郎</div>
            <div class="left width15">好吃点</div>
            <div id="more-addshow01" style="display:none;">
              <div class="left width15">香飘飘</div>
              <div class="left width15">和路雪和路雪</div>
              <div class="left width15">百联</div>
              <div class="left width15">百奇</div>
              <div class="left width15">喜之郎</div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
        
         <div class="searchrow-all" style="">
          <div class="left searchl-side"> <b>价格：</b> </div>
          <div class="left search-center" style="">
          <div class="right searchr-rside"> <span id="more-add02" class="downlist-ico">更多</span> </div>
            <div class="left width15">旺旺</div>
            <div class="left width15">乐事</div>
            <div class="left width15">费列罗</div>
            <div class="left width15">好丽友</div>
            <div class="left width15">奥利奥奥利奥</div>
            <div class="left width15">好吃点</div>
            <div class="left width15">米老头</div>
            <div class="left width15">和路雪</div>
            <div class="left width15">百联</div>
            <div class="left width15">百奇</div>
            <div class="left width15">喜之郎</div>
            <div class="left width15">好吃点</div>
            <div id="more-addshow02" style="display:none;">
              <div class="left width15">香飘飘</div>
              <div class="left width15">和路雪和路雪</div>
              <div class="left width15">百联</div>
              <div class="left width15">百奇</div>
              <div class="left width15">喜之郎</div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
        
         <div class="searchrow-all" style="">
          <div class="left searchl-side"> <b>口味：</b> </div>
          <div class="left search-center" style="">
          <div class="right searchr-rside"> <span id="more-add03" class="downlist-ico">更多</span> </div>
            <div class="left width15">旺旺</div>
            <div class="left width15">乐事</div>
            <div class="left width15">费列罗</div>
            <div class="left width15">好丽友</div>
            <div class="left width15">奥利奥奥利奥</div>
            <div class="left width15">好吃点</div>
            <div class="left width15">米老头</div>
            <div class="left width15">和路雪</div>
            <div class="left width15">百联</div>
            <div class="left width15">百奇</div>
            <div class="left width15">喜之郎</div>
            <div class="left width15">好吃点</div>
            <div id="more-addshow03" style="display:none;">
              <div class="left width15">香飘飘</div>
              <div class="left width15">和路雪和路雪</div>
              <div class="left width15">百联</div>
              <div class="left width15">百奇</div>
              <div class="left width15">喜之郎</div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
        
        <div class="searchrow-all" style="">
          <div class="left searchl-side"> <b>包装：</b> </div>
          <div class="left search-center" style="">
          <div class="right searchr-rside"> <span id="more-add04" class="downlist-ico">更多</span> </div>
            <div class="left width15">旺旺</div>
            <div class="left width15">乐事</div>
            <div class="left width15">费列罗</div>
            <div class="left width15">好丽友</div>
            <div class="left width15">奥利奥奥利奥</div>
            <div class="left width15">好吃点</div>
            <div class="left width15">米老头</div>
            <div class="left width15">和路雪</div>
            <div class="left width15">百联</div>
            <div class="left width15">百奇</div>
            <div class="left width15">喜之郎</div>
            <div class="left width15">好吃点</div>
            <div id="more-addshow04" style="display:none;">
              <div class="left width15">香飘飘</div>
              <div class="left width15">和路雪和路雪</div>
              <div class="left width15">百联</div>
              <div class="left width15">百奇</div>
              <div class="left width15">喜之郎</div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
        
      </div>
    </div>
    <div class="col-xs-2" style="width:23%;">
      <div class="left-goods">
        <div class="left-goodstop"><span class="left-goodsword">相似产品推荐</span></div>
        <div class="left-goodspic" style="background:url(images/adpic-hotico.png) no-repeat left top;"><img src="${base}/resources/images/ishangimg/adpic-01.png" width="223" height="223" class="margin20-10" style="position:relative; z-index:-1;">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
        <div class="left-goodspic" style="background:url(images/adpic-hotico.png) no-repeat left top;"><img src="${base}/resources/images/ishangimg/adpic-02.png" width="223" height="223" class="margin20-10" style="position:relative; z-index:-1;">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
        <div class="left-goodspic" style="background:url(images/adpic-hotico.png) no-repeat left top;"><img src="${base}/resources/images/ishangimg/adpic-03.png" width="223" height="223" class="margin20-10" style="position:relative; z-index:-1;">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
        <div class="left-goodspic" style="background:url(images/adpic-hotico.png) no-repeat left top;"><img src="${base}/resources/images/ishangimg/adpic-04.png" width="223" height="223" class="margin20-10" style="position:relative; z-index:-1;">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
      </div>
    </div>
    <div class="col-xs-10" style="width:77%;">
      <div class="storerow">
        <div class="left w215-h260">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic01.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic02.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic03.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic04.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="storerow">
        <div class="left w215-h260">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic05.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic06.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic07.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic08.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="storerow">
        <div class="left w215-h260">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic09.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic10.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic11.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic12.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="storerow">
        <div class="left w215-h260">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic13.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic14.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic15.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic16.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="storerow">
        <div class="left w215-h260">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic17.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic18.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic01.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
        <div class="left w215-h260 marginl10">
          <div class="w213-h160"><img src="${base}/resources/images/ishangimg/search-pic19.png" width="100%" height="100%"></div>
          <div class="margint20"><span class="store-grayname color-gray">沃尔玛</span></div>
          <span class="goodsitem-name">越南进口曲奇饼干</span>
          <div class="store-evaluate"><span class="left red-word14 marginl20" style="font-size:16px;"><b>&yen;39</b></span><span class="right through-price marginr20">&yen;69</span></div>
        </div>
      </div>
      <div class="clear"></div>
      <div style="width:700px;margin:45px 0 0 180px;">
        <div id="kkpager"></div>
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司 版权所有</div>
<script type="text/javascript">
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

//init
$(function(){
	var totalPage = 20;
	var totalRecords = 390;
	var pageNo = getParameter('pno');
	if(!pageNo){
		pageNo = 1;
	}
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		//链接前部
		hrefFormer : 'store',
		//链接尾部
		hrefLatter : '.html',
		getLink : function(n){
			return this.hrefFormer + this.hrefLatter + "?pno="+n;
		}
		/*
		,lang				: {
			firstPageText			: '首页',
			firstPageTipText		: '首页',
			lastPageText			: '尾页',
			lastPageTipText			: '尾页',
			prePageText				: '上一页',
			prePageTipText			: '上一页',
			nextPageText			: '下一页',
			nextPageTipText			: '下一页',
			totalPageBeforeText		: '共',
			totalPageAfterText		: '页',
			currPageBeforeText		: '当前第',
			currPageAfterText		: '页',
			totalInfoSplitStr		: '/',
			totalRecordsBeforeText	: '共',
			totalRecordsAfterText	: '条数据',
			gopageBeforeText		: '&nbsp;转到',
			gopageButtonOkText		: '确定',
			gopageAfterText			: '页',
			buttonTipBeforeText		: '第',
			buttonTipAfterText		: '页'
		}*/
		
		//,
		//mode : 'click',//默认值是link，可选link或者click
		//click : function(n){
		//	this.selectPage(n);
		//  return false;
		//}
	});
});
</script>
</body>
</html>
