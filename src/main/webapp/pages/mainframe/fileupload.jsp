<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>文件上传/下载demo</title>
<script src="${base}/resources/javascripts/ajaxfileupload.js" ></script>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script type="text/javascript">
function downFile(){
	this.location.href = "${base}/downloadFile?filename="+document.getElementsByName("filename")[0].value;
}

function upload(){
	$('#formM').submit();
}

function uploadImage(){
	var json = {
 		    url: "${base}/uploadFile",
 		    fileElementId: "upFile",
 		    success: function(result){
 		    	 var res = eval ("(" + result + ")");
	     	      if (res.successMsg){
     	    		showMask(res.successMsg);
     	    		$("#uploadImage").attr("src", "${base}/"+res.addr);
        	    	setTimeout("hideMask();", DEFAULT_DELAY_MS);
                 } else {
                 	hideMask();
                 	$.messager.alert('错误信息',res.errorMsg,'error');
                 }
  	    	}
 		};
	ajaxFileUpload(json);
}
</script>
</head>
<body>
<%-- 
	<div id="loading">
		  <form action="${base}/uploadFile" method="post" enctype="multipart/form-data">
		  		<label>单个文件上传</label><br>
		  		名称：<input name="rcName" size="4">
		  		文件：
		  		<input type="file" name="upFile">
		  		<input type="submit" value="上传单个文件">
		  </form>
	</div> 

<div id="loading2">
		  <form id="formM" action="${base}/uploadFiles" method="post" enctype="multipart/form-data">
		  		<hr>
		  		<label>多个文件上传</label><br>
		  		名称：<input name="rcName" size="4">
		  		文件：
		  		<input type="file" name="upFile"><br>
		  		<input type="file" name="upFile"><br>
		  		<input type="file" name="upFile"><br>
		  		<input type="button" value="上传多个文件" onclick="upload()">
		  </form>
</div>
--%>
<div id="loading3">
		  		<hr>
		  		<label>下载用get方法</label><br>
		  		文件：
		  		<input type="text" name="filename"><br>
		  		<input type="button" value="下载文件" onclick="downFile()">
</div>
<hr><hr>
<div id="result">上传单个文件，ajax无刷新</div>
<img id="uploadImage" src="http://www.firefox.com.cn/favicon.ico">
<input type="file" id="upFile" name="upFile"/>
<input type="button" value="上传图片文件" onclick="uploadImage()"/>
<hr><hr>

</body>
</html>