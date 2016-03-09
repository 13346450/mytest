<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script>
window.onload = function() {
	topshow();
}
</script>
</head>

<body>
<!--login-->
    <jsp:include page="headerLogin.jsp"/>
    <jsp:include page="headerNav.jsp"/>
	<jsp:include page="personalHeader.jsp"/>
<div class="container" style="margin-top:-40px; min-height:743px;">
  <div class="row"> 
    <div class="col-xs-12 second-navword">首页 > 个人中心</div>
    <div class="col-xs-2 personal-right">
      <div class="personal-itemb onpersonal-itemb" style=" "><i class="persaonal-ico personal-all"></i><span class="left">首页</span></div>
      <div style="">
        <div class="personal-itemb" style=""><i class="persaonal-ico personal01"></i><span class="left">订单详情</span></div>
        <div class="personal-items">全部</div>
        <div class="personal-items">待接单</div>
        <div class="personal-items">已接单</div>
        <div class="personal-items">配送中</div>
      </div>
      <div style="">
        <div class="personal-itemb" style=""><i class="persaonal-ico personal02"></i><span class="left">历史记录</span></div>
        <div class="personal-items">已买商品</div>
        <div class="personal-items">我的评论</div>
        <div class="personal-items">浏览历史</div>
        <div class="personal-items">物业缴费</div>
      </div>
      <div style="">
        <div class="personal-itemb" style=""><i class="persaonal-ico personal03"></i><span class="left">我的收藏</span></div>
        <div class="personal-items">商品收藏</div>
        <div class="personal-items">关注店铺</div>
      </div>
      <div class="personal-itemb" style=""><i class="persaonal-ico personal04"></i><span class="left">我的积分</span></div>
      <div class="personal-itemb" style=""><i class="persaonal-ico personal05"></i><span class="left">我的消息</span></div>
      <div class="personal-itemb" style=""><i class="persaonal-ico personal06"></i><span class="left">我的优惠券</span></div>
      <div style="">
        <div class="personal-itemb" style=""><i class="persaonal-ico personal07"></i><span class="left">设置管理</span></div>
        <div class="personal-items">个人信息</div>
        <div class="personal-items">修改密码</div>
        <div class="personal-items">收货地址</div>
      </div>
    </div>
    <div class="col-xs-6 personal-center">
    <div>
      <div class="pcenter-top">
       <img src="${base}/resources/images/ishangimg/personal-user.png" class="left margin20-30">
        <div class="left">
          <p class="pcenter-topmargin1">昵称：周先生</p>
          <i class="manage-ico manage-icotel" ></i><span class="manage-tel">0592-1234567</span>
          <div class="clear"></div>
          <i class="manage-ico manage-icoadd"></i><span class="manage-address">厦门市湖里区火炬园创新路2号</span></div>
        <div class="right pcenter-topmargin2"><span>金山国际</span> </div>
      </div>
    </div>
    
    <div class="pcenter-border">
    <div class="pcenter-word1">猜你喜欢<i class="persaonal-ico personal08 right" style="float:right;"></i></div>
   <div style="margin:12px 25px;" class="">
          <div  class="margin20-30 rwidth139">
          <img src="${base}/resources/images/ishangimg/foot-goods.jpg" width="139" height="168">
            <p  class="margin5">维良 低筋面粉 500g/袋 蛋糕粉 低绩效面粉</p>
            <span class="through-price">&yen;168.00</span><span class="right red-word">&yen;89.00</span>
            <input type="button" value="添加到购物车" class="addcart-btn">
          </div>
         <div  class="margin20-30 rwidth139">
          <img src="${base}/resources/images/ishangimg/foot-goods.jpg" width="139" height="168">
            <p  class="margin5">维良 低筋面粉 500g/袋 蛋糕粉 低绩效面粉</p>
            <span class="through-price">&yen;168.00</span><span class="right red-word">&yen;89.00</span>
            <input type="button" value="添加到购物车" class="addcart-btn">
          </div>
          <div  class="margin20-30 rwidth139">
          <img src="${base}/resources/images/ishangimg/foot-goods.jpg" width="139" height="168">
            <p  class="margin5">维良 低筋面粉 500g/袋 蛋糕粉 低绩效面粉</p>
            <span class="through-price">&yen;168.00</span><span class="right red-word">&yen;89.00</span>
            <input type="button" value="添加到购物车" class="addcart-btn">
          </div>
          <div  class="margin20-30 rwidth139">
          <img src="${base}/resources/images/ishangimg/foot-goods.jpg" width="139" height="168">
            <p  class="margin5">维良 低筋面粉 500g/袋 蛋糕粉 低绩效面粉</p>
            <span class="through-price">&yen;168.00</span><span class="right red-word">&yen;89.00</span>
            <input type="button" value="添加到购物车" class="addcart-btn">
          </div>
          <div  class="margin20-30 rwidth139">
          <img src="${base}/resources/images/ishangimg/foot-goods.jpg" width="139" height="168">
            <p  class="margin5">维良 低筋面粉 500g/袋 蛋糕粉 低绩效面粉</p>
            <span class="through-price">&yen;168.00</span><span class="right red-word">&yen;89.00</span>
            <input type="button" value="添加到购物车" class="addcart-btn">
          </div>
          <div  class="margin20-30 rwidth139">
          <img src="${base}/resources/images/ishangimg/foot-goods.jpg" width="139" height="168">
            <p  class="margin5">维良 低筋面粉 500g/袋 蛋糕粉 低绩效面粉</p>
            <span class="through-price">&yen;168.00</span><span class="right red-word">&yen;89.00</span>
            <input type="button" value="添加到购物车" class="addcart-btn">
          </div>
        </div>
        <div class="clear"></div>
    </div>
    </div>
    
    <div class="col-xs-2 personal-left">
    <div class="pcenter-border"> 
    <div class="pcenter-word2">便民服务</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i class="personal-rightico"></i></div>实时路况</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i class="personal-rightico personal-rightico2"></i></div>手机充值</div>
    <div class="pright-ico"><div class="padding20-25"><i class="personal-rightico personal-rightico3"></i></div>汽车租赁</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i  class="personal-rightico personal-rightico4"></i></div>公交查询</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i class="personal-rightico personal-rightico5"></i></div>我的门诊</div>
    <div class="pright-ico"><div class="padding20-25"><i class="personal-rightico personal-rightico6"></i></div>快  递</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i  class="personal-rightico personal-rightico7"></i></div>缴水费</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i class="personal-rightico personal-rightico8"></i></div>缴电费</div>
    <div class="pright-ico"><div class="padding20-25"><i class="personal-rightico personal-rightico9"></i></div>缴煤气费</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i  class="personal-rightico personal-rightico10"></i></div>订机票</div>
    <div class="pright-ico boder-rightccc"><div class="padding20-25"><i  class="personal-rightico personal-rightico11"></i></div>订火车票</div>
    <div class="pright-ico"><div class="padding20-25"><i class="personal-rightico personal-rightico12"></i></div>订电影票</div>
     <div class="pright-ico boder-rightccc"><div class="padding20-25"><i class="personal-rightico personal-rightico13"></i></div>订景区门票</div>
    <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <div class="pcenter-border" style="padding-bottom:60px;">
    <div class="pcenter-word3">新品推荐</div>
    <div class="pright-bottompic"> <img src="${base}/resources/images/ishangimg/search-pic18.png" width="100%" height="100%">
    <div class="margin5"><p>维良 低筋面粉袋订景区门票……</p>
    <p class="red-word14" style="text-align:center;">&yen;89</p></div></div>
    <div class="pright-bottompic"> <img src="${base}/resources/images/ishangimg/search-pic18.png" width="100%" height="100%">
    <div class="margin5"><p>维良 低筋面粉袋订景区门票……</p>
    <p class="red-word14" style="text-align:center;">&yen;89</p></div>
    </div>
   
    <div class="clear"></div>
    </div>
    </div>
  </div>
</div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
