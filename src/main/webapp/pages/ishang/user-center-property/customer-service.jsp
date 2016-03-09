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
    <jsp:include page="../common/headerLogin.jsp"/>
    <jsp:include page="../common/headerNav.jsp"/>
	<jsp:include page="propertyTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="propertyLeftNav.jsp"/>
   <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">客服信息</div>
      <div id="information" class="marginl20 margint10">
      <div class="" style="padding-bottom:20px; margin-right:15px;border-bottom:1px dashed #ccc;">
      <div id="demo" class="demo">
            <form id="uploadForm" action="UploadAction" method="post" enctype="multipart/form-data">
              <div class="upload_box">
                <div class="upload_main">
                  <div class="upload_choose">
                    <div class="convent_choice left">
                      <div class="andArea">
                        <div class="filePicker" style="margin-left:15px;"></div>
                        <input id="fileImage" type="file" size="30" name="fileselect[]" style="display:none;">
                      </div>
                    </div>
                    <div style="float:left;">
                      <div id="preview" class="upload_preview"></div>
                    </div>
                    <span id="fileDragArea" class="upload_drag_area" style="margin-top:20px;">
                    <span>本地上传图片，图片大小限606*382。</span>
                    </span> </div>
                  <div class="status_bar"> </div>
                  <!--<div id="preview" class="upload_preview"></div>--> 
                </div>
              </div>
            </form>
          </div>
          <div class="clear"></div>
        </div>
        <div style="padding-bottom:20px; margin-right:15px;border-bottom:1px dashed #ccc;"> 
        <p style="margin:15px 0 10px 15px;">公司名称:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <div style="margin-top:10px;">
        <p class="left" style="margin-left:15px;">电话:</p><p class="left" style="margin-left:280px;">邮箱:</p>
        </div>
        <div class="clear"></div>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <input type="text" maxlength="100" class="border-ccc p08input" style="margin-left:55px;"/>
        <div class="clear"></div>
        <p style="margin:10px 0 10px 15px;">地址:</p>
        <input type="text" maxlength="100" class="border-ccc p08input" style="width:560px;"/>
        <p style="margin:20px 0 10px 15px;">物业介绍:</p>
        <textarea cols="87" rows="8" style=" margin-left:15px; padding-left:15px;" placeholder="描述详情"></textarea>
        </div>
        <p style="margin:15px 0 10px 15px;">小区名称:</p>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <div style="margin-top:10px;">
        <p class="left" style="margin-left:15px;">保安室电话:</p><p class="left" style="margin-left:280px;">办公室电话:</p>
        </div>
        <div class="clear"></div>
        <input type="text" maxlength="100" class="border-ccc p08input"/>
        <input type="text" maxlength="100" class="border-ccc p08input" style="margin-left:55px;"/>
        <div class="clear"></div>
        <p style="margin:10px 0 10px 15px;">地址:</p>
        <input type="text" maxlength="100" class="border-ccc p08input" style="width:560px;"/>
        
        <div style="margin:20px 0 20px 15px;">
          <input type="button" class="loginbtn left" style="width:120px; height:35px; line-height:30px;" value="修改"/>
          <div class="clear"></div>
        </div>
      </div>
    </div>
    </div>
  </div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>

</body>
</html>

