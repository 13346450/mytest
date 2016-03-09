<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心13</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/pages.css" >
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
	<jsp:include page="../personalHeader.jsp"/>
<!--login-->
<div class="container" style="margin-top:-40px; min-height:743px;">
  <div class="row">
     <jsp:include page="personalLeftNav.jsp"/>
     <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">商品评价</div>
      <div id="" class="" style="width:70px; height:70px; float:left;margin:30px 10px 0 30px;">
      <img src="${base}/resources/images/ishangimg/goods-item.jpg" width="100%" height="100%">
      </div>
      <span style="margin-top:30px; width:200px; display:block; float:left;">沃尔玛 端午节粽子礼盒 肉粽10只/篮台湾风味</span>
      <span style="margin-top:85px; width:200px; display:block;">2015-06-15</span>
      <div id="information" class="marginl20 margint10">
        <p class="left"><i class="redstart">*</i>评分:</p><span class="star-ico1" style="margin:5px;"></span>
        <div class="clear"></div>
        <p class="left"><i class="redstart">*</i>评价:</p>
          <form action="" method="" style="margin-top:5px;">
          <label class="marginl15">
            <input name="Fruit" type="radio" value=""/>
            好 </label>
          <label class="marginl30">
            <input name="Fruit" type="radio" value=""/>
            中 </label>
          <label class="marginl30">
            <input name="Fruit" type="radio" value=""/>
            差 </label>
        </form>
        
        <p class="margint10"><i class="redstart">*</i>评论:</p>
        <textarea cols="80" rows="10" class="border-ccc" placeholder="亲，写点评论吧，对其他客户有很大帮助哦！"></textarea>
        <p class="margint10"><i class="redstart">*</i>晒照:</p>
         <div id="demo" class="demo">
            <form id="uploadForm" action="UploadAction" method="post" enctype="multipart/form-data">
              <div class="upload_box">
                <div class="upload_main">
                  <div class="upload_choose">
                    <div class="convent_choice" style="float:left;">
                      <div class="andArea">
                        <div class="filePicker" style="margin-left:15px;"></div>
                        <input id="fileImage" type="file" size="30" name="fileselect[]" multiple="" style="display:none;">
                      </div>
                    </div>
                    <div style="float:left;">
                      <div id="preview" class="upload_preview"></div>
                    </div>
                    <span id="fileDragArea" class="upload_drag_area" style="margin-top:20px;">
                    <div id="status_info" class="info">0/5</div>
                    </span> </div>
                  <div class="status_bar"> </div>
                  <!--<div id="preview" class="upload_preview"></div>--> 
                </div>
              </div>
            </form>
          </div>
          <div class="clear"></div>
        <div style="margin:30px 0 30px 15px;">
          <input type="button" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="发表评论"/>   <span  style="width:120px; height:40px; line-height:40px;"><i id="cart-check" class="cart-ico cart-ico-input" style="margin:5px 5px 0 80px;"></i>匿名评论</span>
        <div class="clear"></div>
        </div>
      </div>
    </div>
     
     
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
     