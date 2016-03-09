<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物业版</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css">
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/pages.js"></script>
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
	<jsp:include page="propertyTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="propertyLeftNav.jsp"/>
   <div class="col-xs-10" style="margin:15px 0 0 15px; padding:0;">
     <div class="col-xs-12 topcart-bg" style="margin-top:0;">
      <span><i id="cart-check" class="cart-ico cart-ico-input"></i>全选</span>
      <a href="#"><span class="topbluebtn">批量删除</span></a>
     </div>
        <ul>
           <li class="col-xs-12" style="border:1px solid #ccc; margin-top:10px;">
             <i id="cart-check" class="cart-ico" style="margin:18px 0;"></i>
             <a href="#"><img src="${base}/resources/images/ishangimg/property-textpic.png" class="left" style="margin:15px"></a>
             <div class="left" style="width:600px;">
             <p style="margin:12px 0 3px 0; font-size:14px;"><a href="#">沃尔玛端午节粽子礼</a></p>
             <p class="color-gray">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味……</p>
             </div>
             <p class="left" style="margin-top:23px;">2015-07-15 12:12:12</p>
             <p class="right" style="margin-right:30px; margin-top:23px; color:#00a0e9;">删除</p>
              <div class="clear"></div>
           </li>
           <li class="col-xs-12" style="border:1px solid #ccc; margin-top:10px;">
             <i id="cart-check" class="cart-ico" style="margin:18px 0;"></i>
             <a href="#"><img src="${base}/resources/images/ishangimg/property-textpic.png" class="left" style="margin:15px"></a>
             <div class="left" style="width:600px;">
             <p style="margin:12px 0 3px 0; font-size:14px;"><a href="#">沃尔玛端午节粽子礼</a></p>
             <p class="color-gray">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味……</p>
             </div>
             <p class="left" style="margin-top:23px;">2015-07-15 12:12:12</p>
             <p class="right" style="margin-right:30px; margin-top:23px; color:#00a0e9;">删除</p>
              <div class="clear"></div>
           </li>
           <li class="col-xs-12" style="border:1px solid #ccc; margin-top:10px;">
             <i id="cart-check" class="cart-ico" style="margin:18px 0;"></i>
             <a href="#"><img src="${base}/resources/images/ishangimg/property-textpic.png" class="left" style="margin:15px"></a>
             <div class="left" style="width:600px;">
             <p style="margin:12px 0 3px 0; font-size:14px;"><a href="#">沃尔玛端午节粽子礼</a></p>
             <p class="color-gray">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味……</p>
             </div>
             <p class="left" style="margin-top:23px;">2015-07-15 12:12:12</p>
             <p class="right" style="margin-right:30px; margin-top:23px; color:#00a0e9;">删除</p>
              <div class="clear"></div>
           </li>
           <li class="col-xs-12" style="border:1px solid #ccc; margin-top:10px;">
             <i id="cart-check" class="cart-ico" style="margin:18px 0;"></i>
             <a href="#"><img src="${base}/resources/images/ishangimg/property-textpic.png" class="left" style="margin:15px"></a>
             <div class="left" style="width:600px;">
             <p style="margin:12px 0 3px 0; font-size:14px;"><a href="#">沃尔玛端午节粽子礼</a></p>
             <p class="color-gray">沃尔玛 端午节粽子礼盒 肉粽10只/盒,台湾风味……</p>
             </div>
             <p class="left" style="margin-top:23px;">2015-07-15 12:12:12</p>
             <p class="right" style="margin-right:30px; margin-top:23px; color:#00a0e9;">删除</p>
              <div class="clear"></div>
           </li>
         </ul>
        
      <div style="width:700px; float:right;margin-top: 30px;">
           <div id="kkpager"><div><span class="pageBtnWrap"><span class="disabled">首页</span><span class="disabled">上一页</span><span class="curr">1</span><a href="commodity01.html?pno=2" title="第2页">2</a><a href="commodity01.html?pno=3" title="第3页">3</a><a href="commodity01.html?pno=4" title="第4页">4</a><a href="commodity01.html?pno=5" title="第5页">5</a><a href="commodity01.html?pno=6" title="第6页">6</a><a href="commodity01.html?pno=7" title="第7页">7</a><span class="spanDot">...</span><a href="commodity01.html?pno=2" title="下一页">下一页</a><a href="commodity01.html?pno=20" title="尾页">尾页</a></span><span class="infoTextAndGoPageBtnWrap"><span class="totalText">当前第<span class="currPageNum">1</span>页<span class="totalInfoSplitStr">/</span>共<span class="totalPageNum">20</span>页</span><span class="goPageBox">&nbsp;转到<span id="kkpager_gopage_wrap"><input type="button" id="kkpager_btn_go" onclick="kkpager.gopage()" value="确定"><input type="text" id="kkpager_btn_go_input" onfocus="kkpager.focus_gopage()" onkeypress="return kkpager.keypress_gopage(event);" onblur="kkpager.blur_gopage()" value="2"></span>页</span></span></div><div style="clear:both;"></div></div>
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
