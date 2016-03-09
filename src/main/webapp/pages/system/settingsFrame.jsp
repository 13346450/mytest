<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<script src="${base}/resources/javascripts/ajaxfileupload.js"></script>
<style type="text/css">
table {
	border:0px solid #F00;
	width: 400px;
	margin-top: 20px;
	cellspacing: "0px";
}

tr {
	width: 300px;
	height: 120px;
}

.textTd {
	width: 150px;
	font-size: 14px;
	text-align: right;
	vertical-align: top;
	margin-right: 5px;
	font-family: microsoft yahei;
}

.imageTd {
	width: 250px;
	padding-left: 10px;
}
</style>
<script type="text/javascript">
	var changeId;
	$(function() {
		$.ajax({
			type : "POST",
			url : "${base}/system/SysLogoSettings/queryAll",
			datatype : "json",
			success : function(data) {
				var myobj = eval(data);
				for (var i = 0; i < myobj.length; i++) {
					var addOrDel = "删除图片";
					var imageFlag = true;
					if (myobj[i].imageUrl == null) {
						addOrDel = "添加图片";
						imageFlag = false;
					}
					addRow(myobj[i].idKey, myobj[i].name, addOrDel, imageFlag);
					if (myobj[i].imageUrl != null) {
						var imageObj = "image" + myobj[i].idKey;
						$('#' + imageObj).attr("src",
								"${base}/" + myobj[i].imageUrl);
					}
				}
			}
		});
	});

	function addRow(id, name, addOrDel, imageFlag) {
		var imageTd = "";
		if (imageFlag) {
			imageTd = '<img width="200px" height="100px" style="margin-top:0px;" id="image'
					+ id
					+ '" alt="双击查看大图" src="" ondblclick="javascript:showImage(this)">';
		}
		var newRow = '<tr ><td class="textTd" id="'+id+'Td">'
				+ name
				+ '</br></br><a href="javascript:void(0)" onclick=del(this) id="text'
				+ id + '">' + addOrDel + '</a></td><td class="imageTd"><div style="border: 1px solid #BEBEBE;width:200px;height:100px;">'
				+ imageTd + '</div></td></tr>';
		$('#tb').append(newRow);
	}

	function del(obj) {
		changeId=obj.id.substring(4);
		if (obj.text == "添加图片") {
			addImage();
		} else if (obj.text == "删除图片") {
			deleteImage();
		}
	}
	function addImage() {
		$('#dlg').dialog('open').dialog('setTitle', '上传图片');
		$('#uploadFm').form('clear');
	}
	function deleteImage(){
		var json = {
	    		    url: "${base}/system/SysLogoSettings/delete/" + changeId,
	    		    success: function(result){
		     	    	if (result.successMsg){
		     	    		showMask(result.successMsg);
		        	    	setTimeout("hideMask(); window.location.reload();", DEFAULT_DELAY_MS);
	                    } else {
	                    	hideMask();
	                    	$.messager.alert('错误信息',result.errorMsg,'error');
	                    }
	     	    	}
	    		};
	    	ajax(json);
	}
	
	// 上传图片
	function uploadImage() {
		if ($("#upFile").val() == "") {
			$.messager.alert('错误信息', "上传文件不能为空", 'error');
			return;
		}
// 		var filepath=$("#upFile").find("[name='']").val();
//         var extStart=filepath.lastIndexOf(".");
//         var ext=filepath.substring(extStart,filepath.length).toUpperCase();
//         if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
//          alert("图片限于bmp,png,gif,jpeg,jpg格式");
//          return false;
//         }
		var json = {
			url : "${base}/system/SysLogoSettings/uploadFile?idKey="
					+ changeId,
			fileElementId : "upFile",
			maskId : "dlg",
			success : function(result) {
				var res = eval("(" + result + ")");
				if (res.successMsg) {
					showMask(res.successMsg);
					setTimeout(
							"hideMask(); $('#dlg').dialog('close');window.location.reload();",
							DEFAULT_DELAY_MS);
				} else {
					hideMask();
					$.messager.alert('错误信息', res.errorMsg, 'error');
				}
			}
		};
		ajaxFileUpload(json);
	}
	//双击预览大图
	function showImage(obj) {
		if (obj.src == null || obj.src == "") {
			return;
		}
		$('#imageSize').attr("src", obj.src);
		$('#showImage').dialog('open');
	}
</script>
</head>
<body>
	<div>
		<table id="tb">
		</table>
	</div>
	<div id="dlg" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 360px; height: 158px; padding: 20px 10px"
		buttons="#dlg-buttons">
		<form id="uploadFm" method="post" novalidate>
			<div class="fitem">
				<label style="width: 60px">上传图片:</label> <input type="file"
					id="upFile" name="upFile" class="easyui-validatebox"
					required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="uploadImage()">上传</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close');flag=0;">取消</a>
	</div>
	<div id="showImage" class="easyui-dialog"
		data-options="title:'图片预览',emodal:true,closed:true,width:800,height:500,resizable:true">
		<img id=imageSize src="" />
	</div>
</body>
</html>