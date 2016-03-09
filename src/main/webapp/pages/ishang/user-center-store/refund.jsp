<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商铺版</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>
<script src="${base}/resources/javascripts/ishangjs/jquery.datetimepicker.js" charset="gbk"></script>
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
	<jsp:include page="storeTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="storeLeftNav.jsp"/>
      <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">退款管理</div>
      <div id="information" class="marginl20 margint10">
        <div style="margin-top:20px;">
        <p class="left"><i class="redstart">*</i>订单号:</p><p class="left" style="margin:7px 0 0 168px;">退款状态:</p>
        </div>
        <div class="clear"></div>
        <div>
        <input type="text" maxlength="100" class="border-ccc p08input" style="width:170px;"/>
        <select style="margin-left:38px; font-size:14px; min-width:80px; height:30px;">
                <option value ="1">请选择</option>
                <option value ="2">未退款</option>
                <option value="3">已退款</option>
                <option value="4">退款中</option>
        </select>
        </div>
        <div class="clear"></div>
        <p class="margint10"><i class="redstart">*</i>退款申请时间:</p>
        <input type="text" onclick="SelectDate(this,'yyyy-MM-dd')" class="begintime" placeholder="红包开始时间" style="width:170px;"/>
          <p class="firstword">至</p>
        <input type="text" onclick="SelectDate(this,'yyyy-MM-dd')" class="endtime" placeholder="红包结束时间" style="width:170px;"/>  
       <div class="clear"></div>
        <div style="margin:20px 0 20px 15px;">
          <input type="button" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="查询"/>
          
          <div class="clear"></div>
        </div>
      </div>
    </div>
    <div class="col-xs-10" style="margin:15px 0 0 15px; padding:0;">
     <div class="col-xs-12 topcart-bg" style="margin-top:0;">
      <span>买家</span>
      <span class="" style="margin-left:70px;">订单号</span>
      <span class="" style="margin-left:70px;">商品详情</span> 
      <span class="" style="margin-left:70px;">交易金额</span>
      <span class="" style="margin-left:70px;">退款金额</span>
      <span class="" style="margin-left:90px;">申请时间</span>
      <span class="" style="margin-left:100px;">状态</span> 
      <span class="" style="margin-left:100px;">操作</span> 
     </div>
       <ul class="reimburseitems">
              <li class="borderbt-ccc" style="text-align:center;">
                <p style="width:60px;">王小姐</p>
                <p class="" style="width:150px;">123456789</p>
                <p class="" style="width:80px; color:#00a0e9;">订单详情</p>
                <p class="" style="width:180px;">&yen;89.00</p>
                <p class=" " style="width:70px;"><span class="red-word12">&yen;89.00</span> </p>
                <p class=" " style="width:220px;"><span>2015-07-15 11:25:20</span></p>
                <p class=" " style="width:65px; color:#ef3d4a;">未退款</p>
                <p class=" " style="width:100px; margin-left:50px;color:#00a0e9;">立即退款</p>
                <div class="clear"></div>
              </li>
              <li class="borderbt-ccc" style="text-align:center;">
                <p style="width:60px;">王小姐</p>
                <p class="" style="width:150px;">123456789123456789</p>
                <p class="" style="width:80px; color:#00a0e9;">订单详情</p>
                <p class="" style="width:180px;">&yen;89.00</p>
                <p class=" " style="width:70px;"><span class="red-word12">&yen;89.00</span> </p>
                <p class=" " style="width:220px;"><span>2015-07-15 11:25:20</span></p>
                <p class=" " style="width:65px; color:#289b49;">退款中</p>
                <p class=" " style="width:100px; margin-left:50px;color:#9c9c9c;">立即退款</p>
                <div class="clear"></div>
              </li>
              <li class="borderbt-ccc" style="text-align:center;">
                <p style="width:60px;">王小姐</p>
                <p class="" style="width:150px;">123456789</p>
                <p class="" style="width:80px; color:#00a0e9;">订单详情</p>
                <p class="" style="width:180px;">&yen;89.00</p>
                <p class=" " style="width:70px;"><span class="red-word12">&yen;89.00</span> </p>
                <p class=" " style="width:220px;"><span>2015-07-15 11:25:20</span></p>
                <p class=" " style="width:65px;color:#9c9c9c;">已退款</p>
                <p class="" style="width:100px; margin-left:50px;color:#9c9c9c;">立即退款</p>
                <div class="clear"></div>
              </li>
            </ul>
      <div style="width:700px; float:right;margin-top: 30px;">
           <div id="kkpager"><div><span class="pageBtnWrap">
           <span class="disabled">首页</span>
           <span class="disabled">上一页</span><span class="curr">1</span>
           <a href="commodity01.html?pno=2" title="第2页">2</a>
           <a href="commodity01.html?pno=3" title="第3页">3</a>
           <a href="commodity01.html?pno=4" title="第4页">4</a>
           <a href="commodity01.html?pno=5" title="第5页">5</a>
           <a href="commodity01.html?pno=6" title="第6页">6</a>
           <a href="commodity01.html?pno=7" title="第7页">7</a>
           <span class="spanDot">...</span><a href="commodity01.html?pno=2" title="下一页">下一页</a>
           <a href="commodity01.html?pno=20" title="尾页">尾页</a></span>
           <span class="infoTextAndGoPageBtnWrap">
           <span class="totalText">当前第<span class="currPageNum">1</span>页<span class="totalInfoSplitStr">/</span>共<span class="totalPageNum">20</span>页</span>
           <span class="goPageBox">&nbsp;转到<span id="kkpager_gopage_wrap">
           <input type="button" id="kkpager_btn_go" onclick="kkpager.gopage()" value="确定">
           <input type="text" id="kkpager_btn_go_input" onfocus="kkpager.focus_gopage()" onkeypress="return kkpager.keypress_gopage(event);" onblur="kkpager.blur_gopage()" value="2">
           </span>页</span></span></div><div style="clear:both;"></div></div>
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
		hrefFormer : 'store05(评价管理)',
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
