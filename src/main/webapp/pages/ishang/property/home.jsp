<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("headerMenu", "pm");
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>物业管理</title>
<link rel="stylesheet"  href="${base}/resources/css/ishangcss/bootstrap.min.css">
<link rel="stylesheet" href="${base}/resources/css/ishangcss/common.css" >
<link rel="stylesheet" href="${base}/resources/css/ishangcss/normal.css" >
<script src="${base}/resources/javascripts/ishangjs/jquery-1.8.3.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/bootstrap.min.js"></script>
<script src="${base}/resources/javascripts/ishangjs/header.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/sroll.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyFile.js"></script>
<script type="text/javascript" src="${base}/resources/javascripts/ishangjs/zyUpload.js"></script>

<script src="${base}/resources/javascripts/jsviews.min.js"></script>
<script src="${base}/resources/javascripts/jquery.form.js"></script>
<script>
window.onload = function() {
	topshow();
}
</script>

</head>

<body>

<div class="box-register">
<div class="box-manage">
	<form id="repairs-form">
	<input name="cmd" value="5004" type="hidden">
	
  <div class="box2-manage"> 
    <!--manage-btn-->
    <div class="col-xs-12" id="logincont-manage" style="padding:0; text-align:center; width:480px;height:420px;">
      <ul>
        <li id="lgstyle-manage" style="height:50px; line-height:50px; background-color:#e4f0f6; font-size:14px;">
<!--           <div class="">您当前报修的小区：<span style="color:#00a0e9;">金山小区</span></div> -->
        </li>
        <li  style="padding-top:10px;">
          <select name="type" style="width:386px; height:40px; border-radius:5px;padding-left:10px;">
            <option value ="0">请选择报修类型</option>
            <option value ="1">个人报修</option>
            <option value="2">公共报修</option>
          </select>
        </li>
        <li  style="padding-top:10px;">
          <input type="text" name="title" placeholder="报修标题" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li style="padding-top:10px; font-size:14px;">
          <textarea id="txt"  name="content" cols="50%" rows="4%" placeholder="请描述需要报修的状况" style="border-radius:5px;padding-left:15px;"></textarea>
        </li>
        <li  style="padding-top:5px;">
          <input type="text" name="contacts" placeholder="联系人" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li  style="padding-top:10px;">
          <input type="text" name="contactsTel" placeholder="联系电话" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li  style="padding-top:10px;">
          <input type="text" name="contactsAddr" placeholder="联系地址" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li style="padding-top:15px; font-size:14px;">
          <div id="demo" class="demo">
            <form id="uploadForm" action="UploadAction" method="post" enctype="multipart/form-data">
              <div class="upload_box">
                <div class="upload_main">
                  <div class="upload_choose">
                    <div class="convent_choice" style="float:left;">
                      <div class="andArea">
                        <div class="filePicker"></div>
                        <input id="fileImage" type="file" size="30" name="fileselect[]" multiple="" style="display:none;">
                      </div>
                    </div>
                    <div style="float:left;">
                      <div id="preview" class="upload_preview"></div>
                    </div>
                    <span id="fileDragArea" class="upload_drag_area">
                    <div id="status_info" class="info">0/5</div>
                    </span> </div>
                  <div class="status_bar"> </div>
                  <!--<div id="preview" class="upload_preview"></div>--> 
                </div>
              </div>
            </form>
          </div>
          <!--manage-btn-->
          <div class="clear"></div>
        </li>
        <li style="padding-top:10px;">
          <div class="left mg-btnbg1" ><a id="repairs-btn" href="javascript:void(0)" style="color:#FFF;">确 认</a></div>
          <div id="manage-reset" class="left mg-btnbg2"><a href="javascript:void(0)" style="color:#FFF;" onclick="">取 消</a></div>
        </li>
      </ul>
    </div>
  </div>
  </form>
</div>
</div>
<!--complain-->
<div class="box-register">
<div class="box-complain">
  <div class="box2-complain"> 
    <div class="col-xs-12" style="padding:0; text-align:center; width:462px;height:420px;">
      <ul>
        <li id="lgstyle-manage" style="height:50px; line-height:50px; background-color:#e4f0f6; font-size:14px;">
          <div class="">投诉建议</div>
        </li>
        <li  style="padding-top:10px;">
          <input type="text" class="" placeholder="投诉建议标题" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li style="padding-top:10px; font-size:14px;">
          <textarea id="txt"  name="replyContent" cols="50%" rows="4%" placeholder="请描述您的投诉建议" style="border-radius:5px;padding-left:15px;"></textarea>
        </li>
        <li  style="padding-top:5px;">
          <input type="text" class="" placeholder="联系人" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li  style="padding-top:10px;">
          <input type="text" class="" placeholder="联系电话" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li  style="padding-top:10px;">
          <input type="text" class="" placeholder="联系地址" style=" border:1px solid #b8b8b8; width:386px; height:40px; border-radius:5px; padding-left:15px;">
        </li>
        <li style="padding-top:15px; font-size:14px;">
          <div id="demo02" class="demo">
            <form id="uploadForm02" action="UploadAction" method="post" enctype="multipart/form-data">
              <div class="upload_box">
                <div class="upload_main">
                  <div class="upload_choose">
                    <div class="convent_choice" style="float:left;">
                      <div class="andArea">
                        <div class="filePickertwo"></div>
                        <input id="fileImagetwo" type="file" size="30" name="fileselect[]" multiple="" style="display:none;">
                      </div>
                    </div>
                    <div style="float:left;">
                      <div id="preview02" class="upload_preview"></div>
                    </div>
                    <span id="fileDragArea02" class="upload_drag_area">
                    <div id="status_info02" class="info">0/5</div>
                    </span> </div>
                  <div class="status_bar"> </div>
                </div>
              </div>
            </form>
          </div>
          <div class="clear"></div>
        </li>
        <li style="padding-top:10px;">
          <div class="left mg-btnbg1" ><a href="javascript:void(0)" style="color:#FFF;" onclick="">确 认</a></div>
          <div id="complain-reset" class="left mg-btnbg2"><a href="javascript:void(0)" style="color:#FFF;" onclick="">取 消</a></div>
        </li>
      </ul>
    </div>
  </div>
</div>
</div>
<!--end complain-->

<!--导入：登入注册忘记密码 弹窗模块-->
<jsp:include page="../common/headerLogin.jsp"/>
<!--导入：头部nav-->
<jsp:include page="../common/headerNav.jsp"/>
<!--导入：搜索和菜單-->
<jsp:include page="../common/headerSearchAndMenu.jsp"/>

<div class="container" style="padding: 0;">
  <div class="row">
    <div class="left manage-lt" id="manage-lt1" style="margin-top:0;">
      <p style="font-size:45px;">物业管理</p>
      <h3>一键缴费</h3>
    </div>
    <div class="left" id="manage-rt" style="margin-top:0;">
      <div class="manage-tb1">
        <div class="left wd150"><span><b>缴费项目</b></span></div>
        <div class="left wd350"><span><b>金额</b></span></div>
        <div class="left wd167"><span><b>时间</b></span></div>
      </div>
      <div class="manage-tb2">
        <div class="left wd150"><span>物业服务费</span></div>
        <div class="left wd350"><span>1200</span></div>
        <div class="left wd167"><span>2014.6-2015.5</span></div>
      </div>
      <div class="manage-tb2">
        <div class="left wd150"><span>房屋公共维修金</span></div>
        <div class="left wd350"><span>1200</span></div>
        <div class="left wd167"><span>2014.6-2015.5</span></div>
      </div>
      <div class="manage-tb3">
        <div class="right wd150"><span><b>总计：&yen;2400.00</b></span></div>
      </div>
      <div class="manage-search">
        <select id="sel" style="float:left; height:38px; width:100px;border-radius:5px; margin-left:15px; border:2px solid #ffc75b; font-size:18px; ">
          <option value="manage-pay">物业费</option>
          <option value="car-pay">停车费</option>
        </select>
        <input type="text" class="left manage-text" placeholder="请输入查询号码">
        <div class="left manage-searchbtn"><a href="#"><span class="btnsearch">查 询</span></a></div>
        <input type="button" value="缴 费" class="left pay-btn">
      </div>
    </div>
    <div class="left manage-news" style="margin-top:0;width:240px;"> <span class="manage-newstop">通知公告</span>
      <hr style="margin-top:-1px; ">
     	<c:forEach items="${bizProperInfoVOs.rows}" var="item">
     		<h6 class="margin20-10"><a href="#">
     		【公告】${fn:substring(item.infoTitle,0,10)}...</a></h6>
     	</c:forEach>
      <a href="#" class="right more-color margin10-30"> >更多</a> 
      </div>
    <div class="clear"></div>
    <div class="left manage-lt" id="manage-lt2">
      <p style="font-size:45px;">物业报修</p>
      <h3>实时处理</h3>
      <input id="repairs" type="button" style="" value="我要报修">
    </div>
    <div class="left Div1" id="manage-rt">
      <div class="manage23">
        <div class="manage2-tb onmanage2-tb">全部</div>
        <div class="manage2-tb">我的</div>
      </div>
      <div class="Div1_manage">
      	<c:forEach items="${bizPropertiesRepairVOs.rows}" var="item">
      		<div class="repairs-news"> 
      			<span class="left">${item.contacts}</span>
      			<span class="right color-ff5b00"><b>
      				<c:if test="${item.repairStatus==1}">未处理</c:if>
      				<c:if test="${item.repairStatus==2}">已处理</c:if>
      			</b></span><br/>
	          <h5>${item.title}</h5>
	          <div style="width:140px;height:136px;"> 
	          <img src="${base}/${item.listImage[0]}" width="100%" height="100%"> </div>
	          <h5>${item.publishDt}</h5>
	        </div>
     	</c:forEach>
      </div>
      <div class="margintop13"><a  class="manage-ico Div1_prev01 Div1_prev1" href="javascript:void(0)"></a></div>
      <div class="marginright"><a class="manage-ico manage-icor Div1_next01 Div1_next1" href="javascript:void(0)"></a></div>
      <div style="position:absolute; bottom:0; width:916px;">
      <a href="${base}/property/repair"  class="right more-color margin10-30">>更多</a></div>
    </div>
    <div class="clear"></div>
    <div class="left manage-lt" id="manage-lt3">
      <p style="font-size:45px;">家政服务</p>
      <h3>方便快捷</h3>
    </div>
    <div class="left Div1" id="manage-rt">
      <div class="manage23">
      	<c:forEach items="${housekeepingCatatorys}" end="6" varStatus="i" var="item">
      		<div class="manage3-tb <c:if test="${i.index==0}">onmanage3-tb</c:if>">${item}</div>
      	</c:forEach>
      </div>
      <div class="Div1_home" id="housekeepingBody">
      </div>
      <div class="margintop13"><a  class="manage-ico Div1_prev02 Div1_prev1" href="javascript:void(0)"></a></div>
      <div class="marginright"><a class="manage-ico manage-icor Div1_next02 Div1_next1" href="javascript:void(0)"></a></div>
      <div style="position:absolute;bottom:0; width:916px;">
      	<a href="${base}/property/housekeeping" class="right more-color margin10-30">>更多</a></div>
    </div>
    <div class="clear"></div>
    <div class="left manage-lt" id="manage-lt4">
      <p style="font-size:45px;">物业客服</p>
      <h3>随时随地</h3>
    </div>
    <div class="left" id="manage-rt"> <img src="${base}/resources/images/ishangimg/service-rt.jpg" width="606" height="382" style="float:left;">
      <div class="servicetb">
        <div class="service-tel"><span class="left">
        	保安室电话：
        	<c:if test="${empty sysCommunities}">请先登录</c:if>
        	<c:if test="${not empty sysCommunities[0].securityRoomPhone}">${sysCommunities[0].securityRoomPhone}</c:if>
        	</span><i class="manage-ico service-telico"></i></div>
        <div  class="service-tel">
          <p class="left">办公室电话：
         	<c:if test="${empty sysCommunities}">请先登录</c:if>
        	<c:if test="${not empty sysCommunities[0].officePhone}">${sysCommunities[0].officePhone}</c:if>
          </p>
          <i class="manage-ico service-telico"></i></div>
        <div  class="service-tel">
          <p class="left">物业电话：
          	<c:if test="${empty sysCommunities}">请先登录</c:if>
        	<c:if test="${not empty sysCommunities[0].cellphone}">${sysCommunities[0].cellphone}</c:if>
          </p>
          <i class="manage-ico service-telico"></i></div>
        <div class="service-name">
          <p>小区名称：
          	<c:if test="${empty sysCommunities}">请先登录</c:if>
        	<c:if test="${not empty sysCommunities[0].communityName}">${sysCommunities[0].communityName}</c:if>
          </p>
          <p>服务物业：
          	<c:if test="${empty sysCommunities}">请先登录</c:if>
        	<c:if test="${not empty sysCommunities[0].proName}">${sysCommunities[0].proName}</c:if>
          </p>
        </div>
      </div>
    </div>
    <!--complain-->
    <div class="complain">
    <div style="height:50px; padding:5px 10px; font-size:14px; background-color:#00a0e9;"><a href="#" style="color:#FFF;">投诉建议</a></div>
    <div><a href="#"><img src="${base}/resources/images/ishangimg/complain.png" width="30" height="30" style="margin:7px 9px;"></a></div>
    </div>
    <!--complain-->
  </div>
</div>
<div class="footer">厦门狄耐克电子科技有限公司 版权所有</div>

<div id="housekeeping-temp" style="display: none;">
	<div class="left home-news Div1_home_span1">
	    <div class="home-newspic"> <span class="home-company">{{:name}}</span> </div>
	       <p style="margin:5px 0 0 15px;">介绍:{{:detail}}</p>
	        <i class="manage-ico manage-icotel" ></i><span class="manage-tel">{{:phoneNumber}}</span>
	        <div class="clear"></div>
	        <i class="manage-ico manage-icoadd"></i><span class="manage-address">{{:address}}</span> 
      </div>
</div>
<script type="text/javascript">
$().ready(function(){
	$(".manage3-tb").click(function(){
		var catatory = $(this).html();
		$.ajax({
			type:'post',
			url:'${base}/business/BizHousekeeping/search',
			data:{page:1,rows:6,names:catatory},
			dataType:'json',
			success:function(d){
				var html = $("#housekeeping-temp").render(d.rows);
				$("#housekeepingBody").html(html);
			}
		})
	});
	$(".manage3-tb").eq(0).click();
	$("#repairs-btn").click(function(e){
		var title = $.trim($("#repairs-form [name='title']").fieldValue())+"";
		var type =$.trim($("#repairs-form [name='type']").fieldValue())+"";
		var content = $.trim($("#repairs-form [name='content']").fieldValue())+"";
		var contacts = $.trim($("#repairs-form [name='contacts']").fieldValue())+"";
		var contactsTel = $.trim($("#repairs-form [name='contactsTel']").fieldValue())+"";
		var contactsAddr = $.trim($("#repairs-form [name='contactsAddr']").fieldValue())+"";
		if(title==""){
			alert("请输入标题！");return;
		}
		if(type=="0"){
			alert("请选择报修类型");return;
		}
		if(content==""){
			alert("请描述报修问题！");return;
		}
		if(contacts==""){
			alert("请输入联系人！");return;
		}
		if(contactsTel==""){
			alert("请输入联系人电话！");return;
		}if(contactsAddr==""){
			alert("请输入联系人地址！");return;
		}
		$("#repairs-form").ajaxSubmit({
			type:'POST',
			url:"${base}/appservice/ehome",
			dataType:'json',
			success:function(data){
				if(data.status==1){
					alert("发布成功");
				}else{
					alert(data.message);
				}
			}
		});
	});
	$("#demo").zyUpload({
		itemWidth        :   "80px",                 // 文件项的宽度
		itemHeight       :   "60px",                 // 文件项的高度
		url              :   "/upload/UploadAction",  // 上传文件的路径
		multiple         :   true,                    // 是否可以多个文件上传
		dragDrop         :   true,                    // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){                    // 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);
		},
		onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);
		},
		onSuccess: function(file){                    // 文件上传成功的回调方法
			console.info("此文件上传成功：");
			console.info(file);
		},
		onFailure: function(file){                    // 文件上传失败的回调方法
			console.info("此文件上传失败：");
			console.info(file);
		},
		onComplete: function(responseInfo){           // 上传完成的回调方法
			console.info("文件上传完成");
			console.info(responseInfo);
		}
	});

/////////02/////////
	$("#demo02").zyUpload02({
		itemWidth        :   "80px",                 // 文件项的宽度
		itemHeight       :   "60px",                 // 文件项的高度
		url              :   "/upload/UploadAction",  // 上传文件的路径
		multiple         :   true,                    // 是否可以多个文件上传
		dragDrop         :   true,                    // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){                    // 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);
		},
		onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);
		},
		onSuccess: function(file){                    // 文件上传成功的回调方法
			console.info("此文件上传成功：");
			console.info(file);
		},
		onFailure: function(file){                    // 文件上传失败的回调方法
			console.info("此文件上传失败：");
			console.info(file);
		},
		onComplete: function(responseInfo){           // 上传完成的回调方法
			console.info("文件上传完成");
			console.info(responseInfo);
		}
	});
});        
</script>
</body>
</html>
