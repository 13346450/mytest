<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商铺版-首页</title>
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
    <jsp:include page="../headerLogin.jsp"/>
    <jsp:include page="../headerNav.jsp"/>
	<jsp:include page="storeTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="storeLeftNav.jsp"/>
     <div class="col-xs-6 personal-center" style="padding-bottom:0; margin-bottom:0;">
      <div class="pcenter-top" style="height:224px;font-size:14px;">
      <div class="" style=" width:62px; height:62px; border-radius:31px; background-image:url(${base}/resources/images/ishangimg/personal-user01.png); margin:20px 30px; float:left;">
      
      </div>
        <div class="left">
          <p class="pcenter-topmargin1">周先生</p>
          <i class="manage-ico manage-icotel left" ></i> <span class="manage-tel">0592-1234567</span>
          <div class="clear"></div>
          <i class="manage-ico manage-icoadd"></i> <span class="manage-address">厦门市湖里区火炬园创新路2号</span> </div>
        <div class="clear"></div>
        <div class="whiteblock"> <span>0</span> <span class="left">元</span> <span class="right" style="margin-right:20px;">今日营业额</span> </div>
        <div class="whiteblock"> <span>0</span> <span class="left">元</span> <span class="right" style="margin-right:20px;">今日到账金额</span> </div>
      </div>
    </div>
    <div class="col-xs-2 personal-right" style="padding-bottom:0;">
      <div class="pcenter-border">
        <div class="pcenter-word2" style="background-color:#fafafa;">公告</div>
        <ul class="store-news" style="">
          <li>
            <p><a href="#">王小姐购买植物花生油500ml，待商家处理……</a></p>
            <p>2015-07-15 15:01:12</p>
          </li>
          <li>
            <p><a href="#">王小姐购买植物花生油500ml，待商家处理……</a></p>
            <p>2015-07-15 15:01:12</p>
          </li>
          <li>
            <p><a href="#">王小姐购买植物花生油500ml，待商家处理……</a></p>
            <p>2015-07-15 15:01:12</p>
          </li>
        </ul>
      </div>
    </div>
    <div class="col-xs-10" style="width:85%; padding:0;">
      <div class="pcenter-border" style=" margin-left:15px;">
        <div class="pcenter-word1" style="background-color:#fafafa;">今日订单</div>
        
         <div class="bigpinkblock">
            <div class="sellnumber"> 
              <span>0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div style="height:20px; margin-left:20px;">全部订单</div>
         </div>
         <div class="block230">
            <div class="sellnumber">  
              <span>0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div  style="height:20px; margin-left:20px;">配送中</div>
         </div>
          <div class="block285"> 
           <div class="sellnumber">  
              <span style="color:#5db3d1;">0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div  style="height:20px; margin-left:20px; color:#5db3d1;">货到付款</div> 
          </div>
          <div class="block185" style=" margin-left:15px;">
            <div class="sellnumber">  
              <span>0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div  style="height:20px; margin-left:20px; color:#ff6c00;">待接单</div> 
          </div>
          <div class="block185" style="background-color:#ffefe9;">
           <div class="sellnumber">  
              <span>0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div  style="height:20px; margin-left:20px;">已接单</div> 
           </div>
          <div class="block230" style="background-color:#e5f5dd;">
            <div class="sellnumber">  
              <span style="color:#679e4b;">0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div  style="height:20px; margin-left:20px;color:#679e4b;">退款中</div>
         </div>
           <div class="block285" style="background-color:#ffeefe;">
            <div class="sellnumber">  
              <span style="color:#d35ecc;">0</span>
              <p class="left" style="">笔</p>
            </div>
            <div class="clear"></div>
            <div  style="height:20px; margin-left:20px; color:#d35ecc;">货到付款</div> 
          </div>
       
        <div class="clear"></div>
      </div>
      <div class="pcenter-border" style=" margin-left:15px;">
        <div class="pcenter-word1" style="background-color:#fafafa;">近一周营业额</div>
        <div style=" height:304px;"> <img src="${base}/resources/images/ishangimg/01.jpg" width="100%" height="100%"> </div>
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
