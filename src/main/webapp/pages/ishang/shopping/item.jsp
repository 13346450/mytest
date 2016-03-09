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
<title>商品详情</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/pages.css" />
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/commodity.js"></script>
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
<!--导入：登入注册忘记密码 弹窗模块-->
<jsp:include page="../common/headerLogin.jsp"/>
<!--导入：头部nav-->
<jsp:include page="../common/headerNav.jsp"/>
<!--导入：搜索和菜單-->
<jsp:include page="../common/headerSearchAndMenu.jsp"/>
<div class="container" style=" min-height:743px;">
  <div class="row">
    <div class="col-xs-12 nav-tree">社区商圈 > 
    	<a href="${base}/shopping/store?idKey=${bizShop.idKey}">${bizShop.name}</a> > 
    	${bizCategory.categoryName} 
    	</div>
    <div class="col-xs-12 comodity-bord1">
      <div class="left">
        <div class="pic-big"></div>
        <div class="list-goods" style="">
          <ul>
            <c:forEach items="${bizGoodsVO.listGoodsImage}" var="item">
            	<li id="goods01" class="goods00 on-goods"><a><img src="${base}/${item.imgUrl}" width="100%" height="100%"></a></li>
            </c:forEach>
          </ul>
        </div>
        <div class="clear"></div>
        <div> <i class="cart-ico cart-ico-col" style="margin:0 5px 0 30px;"></i><span class="left margin5">移进收藏夹</span> <i class="cart-ico cart-ico-s" style="margin:0 5px 0 60px;"></i><span class="left margin5">分享</span> </div>
      </div>
      <div class="left">
        <h4 class="margin20-10">${bizGoodsVO.goodsName}</h4>
        <h5 class="margin20-10 red-word14">${bizGoodsVO.goodsNote}</h5>
        <div class="graybg"><span style="margin:0 20px 0 10px;">售价:</span>
        	<span class="red-word">
        	<b>&yen;${bizGoodsVO.discountPrice}</b>
        	</span>
        	<span class="through-price margin10-30">&yen;${bizGoodsVO.unitPrice}</span>
        </div>
        <h6 class="margin20-10">促销：!端午节优惠购，端午节期间购买此产品赠送粽子挂件</h6>
        <h6 class="margin20-10">重量：!0.5kg</h6>
        <h6 class="margin20-10">配送：!商品需上门自提。若店铺每笔订单满88元，可免费送货上门。</h6>
        <h6 class="margin20-10">服务：由<span style="color:#00a0e9;">${bizShop.name}</span>负责发货，并提供售后服务。</h6>
        <h6 style="margin:7px 10px;" class="left">数量：</h6>
        <div class="left reduce" style="margin:0 3px;">-</div>
        <div class="left reduce number" style="margin:0 3px;">!1</div>
        <div class="left reduce increat" style="margin:0 3px;">+</div>
        <div class="clear"></div>
        <div id="putToCart" class="cart margin20-10 left" style="width:145px;background-color:#ef3d4a;"> 
        	<i class="ico-cart" style="margin:5px 15px;"></i> 
        	<span >加入购物车</span>
        </div>
        <input class="cart margin20-10 left" style="width:145px;"  type="button" value="立即结算">
      </div>
      <div class="right width208">
        <div class="store-logo"><img src="xx" alt="数据库无字段" width="208" height="83"></div>
        <p class="store-name" >${bizShop.name}</p>
        <p class="left store-evaluate">综合评分：&nbsp;</p>
        <span class="evaluate-ico1"><em class="evaluate-ico1 evaluate-ico2"></em></span>
        <p class="right store-evaluate">4.8 分</p>
        <div class="clear"></div>
        <span class="store-evaluate">${bizGoodsVO.goodsAddr}</span>
        <p class="store-evaluate">电话：${bizGoodsVO.telephone}</p>
        <div style="border-top:1px solid #eeeeee;">
          <input class="left store-btn" type="button" value="进入店铺">
          <input class="left store-btn" type="button" value="关注店铺">
        </div>
      </div>
    </div>
    <div class="col-xs-2" style="width:23%;">
      <div class="left-goods">
        <div class="left-goodstop"><span class="left-goodsword">相似产品推荐</span></div>
        <div class="left-goodspic"> <img src="${base}/resources/images/ishangimg/recommend-goods.jpg" width="223" height="223" class="margin20-10">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
        <div class="left-goodspic"> <img src="${base}/resources/images/ishangimg/recommend-goods.jpg" width="223" height="223" class="margin20-10">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
        <div class="left-goodspic"> <img src="${base}/resources/images/ishangimg/recommend-goods.jpg" width="223" height="223" class="margin20-10">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
        <div class="left-goodspic"> <img src="${base}/resources/images/ishangimg/recommend-goods.jpg" width="223" height="223" class="margin20-10">
          <p class="left-goodsword">【新鲜到货】端午节粽子礼盒 红豆馅粽子 12只/篮 板栗肉粽 嘉兴粽子等等……</p>
          <p class="red-word margin10-30">&yen;89.00</p>
        </div>
      </div>
    </div>
    <div class="col-xs-10" style="width:77%;">
      <div id="record" class="right-introduce">
        <div id="record01" class="data on-data">商品详情</div>
        <div id="record02" class="data">商品评价（165）</div>
        <div id="record03" class="data">月成交记录（100）</div>
        <div id="record04" class="data">商品问答</div>
      </div>
      <div id="commodity-first">
        <div class="right-introducecenter">
          <div class="margin20-30">
            <div class="store-evaluate"> <span class="width25 left">商品名称：沃尔玛台湾风味肉粽</span> <span class="width25 left">商品编号：123456779</span> <span class="width25 left">品牌：沃尔玛</span> <span class="width25 left">商品产地：浙江嘉兴</span> </div>
            <div class="store-evaluate"> <span class="width25 left">商品重量：1500g</span> <span class="width25 left">商品规格：1500g（10只）</span> <span class="width25 left">保质期：15天</span> <span class="width25 left">商品口味：咸味</span> </div>
            <div class="more-para store-evaluate"> <span class="width25 left">上架时间：2015-06-08 14:00:02</span> </div>
            <div class="store-evaluate"> <span id="more-parameter" class="width25 right downlist-ico">订单详情</span> </div>
          </div>
        </div>
        <div class="right-introduce">
          <div style="margin:10px;"><img src="${base}/resources/images/ishangimg/01.jpg" width="100%" height="100%"></div>
          <div style="margin:10px;"><img src="${base}/resources/images/ishangimg/02.jpg" width="100%" height="100%"></div>
          <div style="margin:10px;"><img src="${base}/resources/images/ishangimg/03.jpg" width="100%" height="100%"></div>
        </div>
      </div>
      <div id="commodity-second" style="display:none;">
        <div class="right-introducecenter" style="height:120px;">
          <div class="left-grade">4.8<span class="left-sgrade">分</span></div>
          <div style="margin:40px 0 0 50px;width:500px; float:left;">
            <div class="goodsevaluate-ico"> <span class="goodsevaluate-up"> <i class="goodsevaluate-ico goodsevaluate-word">4.8</i> </span> </div>
            <ol class="center-grade">
              <li class="left width20" style="text-align:left;">非常不满意</li>
              <li class="left width20">不满意</li>
              <li class="left width20">一般</li>
              <li class="left width20">满意</li>
              <li class="left width20" style="text-align:right;">非常满意</li>
            </ol>
          </div>
          <div class="right-grade">
            <dl>
              <dt class="left width70">好评 <span class="color-gray">(85%)</span></dt>
              <dd class="grade-dd"><i class="grade-ddone"></i></dd>
            </dl>
            <dl class="clear">
              <dt class="left width70">中评<span class="color-gray">(10%)</span></dt>
              <dd class="grade-dd"><i class="grade-ddtwo"></i></dd>
            </dl>
            <dl class="clear">
              <dt class="left width70">差评<span class="color-gray">(5%)</span></dt>
              <dd class="grade-dd"><i class="grade-ddthree"></i></dd>
            </dl>
          </div>
        </div>
        <div id="introduce" class="right-introduce" style="margin-top:0; border-top:0;">
          <div id="all-introduce" class="data on-data">全部</div>
          <div id="good" class="data">好评（130）</div>
          <div id="common" class="data">中评（30）</div>
          <div id="bad" class="data">差评（5）</div>
        </div>
        <div class="all-introduce">
          <ul>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2"></em></span><span class="left b-sgrade">5.0</span>
                    <p class="clear" style="text-align:left;">味道很好哦！已经买了很多次了~</p>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic" style=""> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小马</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2" style="width:90px;"></em></span><span class="left b-sgrade" >4.5</span>
                    <p class="clear" style="text-align:left;">味道很好哦！已经买了很多次了~</p>
                      <div id="" class="viewphoto viewphoto01"><img src="${base}/resources/images/ishangimg/goods-item.jpg" width="100%" height="100%"></div>
                      <div id="" class="viewphoto viewphoto02"><img src="${base}/resources/images/ishangimg/goods02.jpg" width="100%" height="100%"></div>
                    <div class="item-picbig"></div>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
          </ul>
          <div style="width:800px;margin:0 auto;">
           <div id="kkpager"></div>
          </div>
        </div>
        <div class="good" style="display:none;">
          <ul>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2"></em></span><span class="left b-sgrade">5.0</span>
                    <p class="clear" style="text-align:left;">味道很好哦！已经买了很多次了~</p>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2" style="width:90px;"></em></span><span class="left b-sgrade">4.5</span>
                    <p class="clear" style="text-align:left;">味道很好哦！已经买了很多次了~</p>
                     <div id="" class="viewphoto viewphoto01"><img src="${base}/resources/images/ishangimg/goods-item.jpg" width="100%" height="100%"></div>
                      <div id="" class="viewphoto viewphoto02"><img src="${base}/resources/images/ishangimg/goods02.jpg" width="100%" height="100%"></div>
                    <div class="item-picbig"></div>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
          </ul>
        </div>
        <div class="common" style="display:none;">
          <ul>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2" style="width:60px;"></em></span><span class="left b-sgrade">3.0</span>
                    <p class="clear">味道很好哦！已经买了很多次了~</p>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2" style="width:70px;"></em></span><span class="left b-sgrade">3.5</span>
                    <p class="clear">味道很好哦！已经买了很多次了~</p>
                    <div id="" class="viewphoto viewphoto01"><img src="${base}/resources/images/ishangimg/goods-item.jpg" width="100%" height="100%"></div>
                      <div id="" class="viewphoto viewphoto02"><img src="${base}/resources/images/ishangimg/goods02.jpg" width="100%" height="100%"></div>
                    <div class="item-picbig"></div>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
          </ul>
        </div>
        <div class="bad" style="display:none;">
          <ul>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2" style="width:20px;"></em></span><span class="left b-sgrade">1.0</span>
                    <p class="clear">味道很好哦！已经买了很多次了~</p>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
            <li>
              <div class="padding20-10">
                <div class="left">
                  <div class="user-pic"> <img src="${base}/resources/images/ishangimg/user-pic.png" width="100%" height="100%"> </div>
                  <div class="store-evaluate">小毛驴</div>
                </div>
                <div class="left">
                  <div class="left margin8-30"> <span class="left margin-b">评价：</span><span class="star-ico1"><em class="star-ico1 star-ico2" style="width:30px;"></em></span><span class="left b-sgrade">1.5</span>
                    <p class="clear">味道很好哦！已经买了很多次了~</p>
                    <div id="" class="viewphoto viewphoto01"><img src="${base}/resources/images/ishangimg/goods-item.jpg" width="100%" height="100%"></div>
                      <div id="" class="viewphoto viewphoto02"><img src="${base}/resources/images/ishangimg/goods02.jpg" width="100%" height="100%"></div>
                    <div class="item-picbig"></div>
                  </div>
                </div>
                <div class="right">2015-7-1 10:10:10</div>
                <div class="clear"></div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div id="commodity-third" style="display:none;">
        <div class="right-introducecenter commodity-data">
          <div id="" class="commodity-dataitem">买家</div>
          <div id="" class="commodity-dataitem">商品名称</div>
          <div id="" class="commodity-dataitem">数量</div>
          <div id="" class="commodity-dataitem">购买日期</div>
          <div class="clear"></div>
          <ul>
            <li>
              <div id="" class="commodity-dataitem">小毛驴</div>
              <div id="" class="commodity-dataitem">粽子</div>
              <div id="" class="commodity-dataitem">3</div>
              <div id="" class="commodity-dataitem">2015-7-1 10:10:10</div>
              <div class="clear"></div>
            </li>
            <li>
              <div id="" class="commodity-dataitem">小毛驴</div>
              <div id="" class="commodity-dataitem">粽子</div>
              <div id="" class="commodity-dataitem">3</div>
              <div id="" class="commodity-dataitem">2015-7-1 10:10:10</div>
              <div class="clear"></div>
            </li>
          </ul>
        </div>
      </div>
      <div id="commodity-fourth" style="display:none;">
        <ul>
          <li class="margin-pic">
            <p class="left" style="color:#00a0e9;">小毛驴</p>
            <p class="left">提问：</p>
            <p class="left">这个粽子保质期是多长时间？</p>
            <p class="right">2015-7-1  10:10:10</p>
            <p class="clear left" style="color:#ef3d4a;">商家回答：常温储存，保质期60天。</p>
            <p class="right">2015-7-1  10:15:10</p>
            <div class="clear"></div>
          </li>
          <li class="margin-pic">
             <p class="left" style="color:#00a0e9;">小马</p>
            <p class="left">提问：</p>
            <p class="left">这个粽子保质期是多长时间？</p>
            <p class="right">2015-7-1  10:10:10</p>
            <p class="clear left" style="color:#ef3d4a;">商家回答：常温储存，保质期60天。</p>
            <p class="right">2015-7-1  10:15:10</p>
            <div class="clear"></div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<script type="text/javascript">
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

//init
$(function(){
	$("#putToCart").click(function(){
		$.ajax({
			type:'post',
			url:'${base}/appservice/ehome',
			data:{cmd:'3501',data:"[{shopId:${bizShop.idKey},goodsId:${param.goodId},goodsCount:1}]"},
			success:function(data){
				alert("商品加入购物车");
			}
		});
	});
	$.ajax({
		type:'post',
		url:'${base}/appservice/ehome',
		dataType:'json',
		data:{cmd:3302,goodsId:'${goodId}',page:1,count:10},
		success:function(data){
			if(data.data.length>0){
				alert("找到评论大于1的商品:"+data.data.length);
			}
		}
	})
	 $('#kkpager').click(function(){
    $('.all-introduce').load(this.href);
    return false;
  });
  
  
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
		hrefFormer : 'commodity01',
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
