<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商铺版</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script src="${base}/resources/javascripts/ishangjs/zyFile.js"></script>
<script src="${base}/resources/javascripts/ishangjs/zyUpload.js"></script>
<script src="${base}/resources/javascripts/ishangjs/demo.js"></script>
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
      <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">添加商品</div>
      <div id="information" class="marginl20 margint10">
      <div id="demo" class="demo">
            <form id="uploadForm" action="UploadAction" method="post" enctype="multipart/form-data">
              <div class="upload_box">
                <div class="upload_main">
                  <div class="upload_choose">
                    <div class="convent_choice left">
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
        <p style="margin:30px 0 10px 15px;">商品名称:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <div style="margin-top:10px;">
        <p class="left" style="margin-left:15px;">原价:</p><p class="left" style="margin-left:280px;">现价:</p>
        </div>
        <div class="clear"></div>
        
        <input type="text" class="border-ccc input60" style="margin-left:15px; width:220px;" placeholder="0.01-99999"/>
        <div class="secondword" style="border-left:0;">元</div>
        <input type="text" class="border-ccc input60" style="margin-left:55px; width:220px;" placeholder="0.01-99999"/>
        <div class="secondword" style="border-left:0;">元</div>
       
        <div class="clear"></div>
         <div style="margin-top:20px;">
        <p class="left" style="margin-left:15px;">库存:</p><p class="left" style="margin-left:280px;">商品类别:</p>
        </div>
        <div class="clear"></div>
        <div>
        <input type="text" class="border-ccc input60" style="margin-left:15px; width:220px;" placeholder="0"/>
        <div class="secondword" style="border-left:0;">件</div>
        <select style="margin-left:55px; font-size:14px; min-width:80px; height:30px;">
                <option value ="1">请选择</option>
                <option value ="2">甜味</option>
                <option value="3">咸味</option>
                <option value="4">酸味</option>
        </select>
        </div>
        <div class="clear"></div>
        <p style="margin:20px 0 10px 15px;">商品名称:</p>
        <textarea cols="92" rows="8" style=" margin-left:15px; padding-left:15px;" placeholder="描述商品详情"></textarea>
        <div style="margin:20px 0 20px 15px;">
          <input type="button" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="提交"/>
          <div class="clear"></div>
        </div>
      </div>
    </div>
  </div>
 </div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>

</body>
</html>