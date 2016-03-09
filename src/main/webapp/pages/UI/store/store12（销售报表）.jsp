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
<script src="${base}/resources/javascripts/ishangjs/datetimepicker.js"></script>
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
      <div class="col-xs-10" style="margin:15px 0 0 15px; padding:0;">
     <div class="col-xs-12 topcart-bg" style="margin-top:0;">
      <select style="margin:10px 15px; font-size:14px; min-width:80px; height:30px; float:left;">
                <option value ="1">类别</option>
                <option value ="2">蔬菜水果</option>
                <option value="3">水产海鲜</option>
                <option value="4">酒水茶饮</option>
        </select>
      <select style="margin:10px 15px; font-size:14px; min-width:80px; height:30px; float:left;">
                <option value ="1">排序</option>
                <option value ="2">单价升序</option>
                <option value="3">单价降序</option>
                <option value="4">销量升序</option>
                <option value ="5">销量降序</option>
                <option value="6">总价升序</option>
                <option value="7">总价降序</option>
        </select>
     <input type="text" onclick="SelectDate(this,'yyyy-MM-dd')" class="begintime" placeholder="起始时间" style="margin-top:10px;"/>
          <p class="firstword" style="margin:10px 15px;">—</p>
        <input type="text" onclick="SelectDate(this,'yyyy-MM-dd')" class="endtime" placeholder="结束时间" style="margin:10px 0;"/>  
      <input type="button" class="loginbtn left" style="width:70px; height:25px; line-height:20px;margin:13px 50px;" value="查询"/> 
     </div>
     <table id="sellform" border="1" style="text-align:center; border:1px solid #ccc;">
          <tr style="background-color:#cbcbcb;">
            <th style="width:80px;">序号</th>
            <th style="width:300px;">缴费项目</th>
            <th style="width:200px;">单价（元）</th>
            <th style="width:200px;">销量（件）</th>
            <th style="width:200px;">总价（元）</th>
          </tr>
          <tr>
            <td>1</td>
            <td>加拿大大龙虾</td>
            <td>128</td>
            <td>50</td>
            <td>630</td>
          </tr>
          <tr>
            <td>2</td>
            <td>加拿大大龙虾</td>
            <td>128</td>
            <td>50</td>
            <td>630</td>
          </tr>
          <tr>
            <td colspan="5" style="text-align:left; padding-left:20px; font-size:14px;">总计：销量<span class="red-word14"><b>100</b></span>件，总额<span class="red-word14"><b>12600</b></span>元</td>
          </tr>
      </table>
    </div>
    </div>
  </div>
<div class="footer">厦门狄耐克电子科技有限公司  版权所有</div>
<style>
#sellform tr{height:50px; line-height:50px;}
</style>
</body>
</html>
