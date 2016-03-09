<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物业版</title>
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
<div class="box-register">
<div class="box-manage">
  <div class="box2-drafts"> 
    <!--manage-btn-->
    <div class="col-xs-12" id="logincont-manage" style="padding:0; text-align:center; width:462px;height:420px;">
      
       <div class="borderbt-ccc" style="background-color:#f3f3f3;">
        <span style="margin:10px 20px; font-size:14px; font-weight:bold; float:left;">读取草稿箱</span> 
        <div class="col-xs-2 close" style="display: block; float:right;">
        <span title="关闭" style="margin:10px;"></span>       
        </div>
        <div class="clear"></div>
        </div>
        <p style="text-align:left; margin:5px 0 5px 20px;">请选择一篇草稿：（点击标题即读取）</p>
     <ul class="border-ccc" style="margin:0 20px; max-height:290px; overflow-y:auto;">
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; width:230px; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
       <li class="borderbt-ccc" style="margin:10px 10px 0;">
        <p style="text-align:left; float:left">dfsjdkfhsdh</p>
        <p style="text-align:left;margin-left:10px; float:right">删除</p>
        <p style="text-align:left;float:right">2015-07-12 21:21:12</p>
        <div class="clear"></div>
       </li>
      </ul>
    </div>
  </div>
</div>
</div>
<!--login-->
    <jsp:include page="../common/headerLogin.jsp"/>
    <jsp:include page="../common/headerNav.jsp"/>
	<jsp:include page="propertyTopNav.jsp"/>
<div class="container" style="min-height:743px;">
  <div class="row"> 
    <jsp:include page="propertyLeftNav.jsp"/>
   <div class="col-xs-10 border-ccc" style="margin:15px 0 0 15px; padding:0;">
      <div class="attention-top borderbt-ccc">新建通知</div>
      <input style="border:1px dashed #ccc;margin:15px; height:40px; width:97%; text-align:center; font-size:16px;" placeholder="请在这里输入公告标题">
      <textarea style="width:973px; height:431px; font-size:14px; border-top-style:dashed; border-left:0; border-right:0; padding:8px;" placeholder="请在这里输入内容"></textarea>
      
       <input type="button" class="p09-submitbtn" style="background-color:#ffbb41; margin:20px 20px 20px 50px;"value="发表"/>
       <input type="button" class="p09-submitbtn" style="background-color:#e9e9e9; color:#6c6c6c; margin:0;" value="取消"/>
       <input type="button" class="p09-submitbtn property-draftsbtn" style="background-color:#e9e9e9; color:#6c6c6c; margin:20px 50px 20px 20px; float:right;" value="草稿箱"/>
       <input type="button" class="p09-submitbtn property-savebtn" style="background-color:#e9e9e9; color:#6c6c6c; margin:20px 0; float:right;" value="保存草稿"/>
       
    </div>
    </div>
  </div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
</body>
</html>
