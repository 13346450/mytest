<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<script src="${base}/resources/javascripts/ajaxfileupload.js"></script>
<style>
body {
	margin-left: 5px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
	var postUrl = "";
	var flag = 0;
	$(function() {
		changeIcon();
		changeSimages();
	});
	function changeIcon() {
		if ($('#iconUrl').val()) {
			$('#iconCategory').attr("src", "${base}/" + $('#iconUrl').val());
		}else{
			$('#iconCategory').attr("src", "");
		}
	}
	function changeSimages() {
		if ($('#showSimages').val()) {
			$('#simagesCategory').attr("src", "${base}/" + $('#showSimages').val());
		}else{
			$('#simagesCategory').attr("src", "");
		}
	}
	function add() {
		self.location = "${base}/business/BizCategory/add/" + $("#idKey").val();
	}

	function save() {
		if ($("#form").form('validate')) {
			var json = {
				url : "${base}/business/BizCategory/update",
				formId : "form",
				successJSFun : addSaveSuccess
			};
			ajaxSubmitForm(json);
		}
	}

	function addSaveSuccess(result) {
		showMask(result.successMsg);
		setTimeout(
				"hideMask();document.location.href='${base}/business/BizCategory/edit/"
						+ result.idKey + "';", 300);
		window.parent.leftFrame.RefreshDTreeInEdit(result.parentId,
				result.idKey, result.cdNm);
	}

	function dele() {
		var children = window.parent.leftFrame.hasChildren($("#idKey").val());
		if (children > 0 || children) {
			$.messager.alert('拒绝删除', '当前类目有下级类目，请先删除下级类目！', 'error');
			return;
		}
		$.messager.confirm('提示信息', '确定要删除该目录吗?', function(r) {
			if (r) {
				var json = {
					url : "${base}/business/BizCategory/delete",
					type : "GET",
					data : {
						"deptId" : $("#idKey").val(),
						"parentId" : $("#parentId").val()
					},
					success : deleteSuccess
				};
				ajax(json);
			}
		});
	}
	/*增加一个图片*/
	function addImage1() {
		flag = 1;
		$('#dlg').dialog('open').dialog('setTitle', '上传图标');
		$('#uploadFm').form('clear');
	}
	function addImage2() {
		flag = 2;
		$('#dlg').dialog('open').dialog('setTitle', '上传小图');
		$('#uploadFm').form('clear');
	}
	function addImage3() {
		flag = 3;
		$('#dlg').dialog('open').dialog('setTitle', '上传大图');
		$('#uploadFm').form('clear');
	}
	// 上传图片
	function uploadImage() {
		if ($("#upFile").val() == "") {
			$.messager.alert('错误信息', "上传文件不能为空", 'error');
			return;
		}
		if (flag == 1) {
			postUrl = "${base}/business/BizCategory/uploadFile";
		} else if (flag == 2) {
			postUrl = "${base}/business/BizCategory/uploadFileAndCompress";
		}
		var json = {
			url : postUrl,
			fileElementId : "upFile",
			maskId : "dlg",
			success : function(result) {
				var res = eval("(" + result + ")");
				if (res.successMsg) {
					showMask(res.successMsg);
					setTimeout(function() {
						hideMask();
						$('#dlg').dialog('close');
						if (flag == 1) {
							$('#iconUrl').val(res.addr);
							changeIcon();
						} else if (flag == 2) {
							setSimages(res.simagesAddr);
							setLimages(res.limagesAddr);
							setShowSimages(res.showSimagesAddr);
							setShowLimages(res.showLimagesAddr);
							changeSimages();
						}
						flag = 0;
						;
					}, DEFAULT_DELAY_MS);
				} else {
					hideMask();
					$.messager.alert('错误信息', res.errorMsg, 'error');
				}
			}
		};
		ajaxFileUpload(json);
	}
	function switchUploadFile(res) {
		if (flag == 1) {
			$('#iconUrl').val(res);
			changeIcon();
		} else if (flag == 2) {
			$('#simages').val(res);
			changeSimages();
		}
		flag = 0;
	}
	function deleteSuccess(json) {
		if (json.flag) {
			showMask(json.successMsg);
			setTimeout(
					"hideMask();document.location.href='${base}/business/BizCategory/edit/"
							+ $("#parentId").val() + "';", 300);
			window.parent.leftFrame.RefreshDTreeInDelete(json.idKey);
		} else {
			hideMask();
			$.messager.alert('提示信息', json.successMsg, 'error');
		}
	}

	$.extend($.fn.validatebox.defaults.rules, {
		digits : {
			validator : function(value) {
				return isInteger(value);
			},
			message : '此项必须为数字！'
		}
	});
	function setSimages(res) {
		$('#simages').val(res);
	}
	function setLimages(res) {
		$('#limages').val(res);
	}
	function setShowSimages(res) {
		$('#showSimages').val(res);
	}
	function setShowLimages(res) {
		$('#showLimages').val(res);
	}
</script>
</head>
<body>
	<div style="padding: 5px; border: 1px solid #ddd;">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add', plain:true" onclick="add()">增加</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove', plain:true" onclick="dele()">删除</a>
	</div>
	<sf:form commandName="sysDept" id="form">
		<table>
			<%-- <tr>
	    			<td>类目代码:</td>
	    			<td><sf:input path="deptCode" cssClass="easyui-validatebox" data-options="required:true,validType:'digits'"/>
	    			</td>
	    		</tr> --%>
			<tr>
				<td style="text-align: right">类目名称:</td>
				<td><sf:input path="categoryName" cssClass="easyui-validatebox"
						data-options="required:true" /> <sf:hidden id="idKey"
						path='idKey' /> 
						<sf:hidden id="simages" path='simages' /> 
						<sf:hidden id="limages" path='limages' /> 
						<sf:hidden id="showSimages"	path='showSimages' /> 
						<sf:hidden id="showLimages" path='showLimages' /> 
						<sf:hidden id="parentId" path="parentId" /></td>
			</tr>
			<tr>
				<td style="text-align: right">排序号:</td>
				<td><sf:input path="orderCd" cssClass="easyui-validatebox"
						data-options="required:true,validType:'digits'" /></td>
			</tr>
			<tr>
				<td style="text-align: right">是否启用:</td>
				<td><sf:radiobutton path="isUse" value="1" />是 <sf:radiobutton
						path="isUse" value="0" />否</td>
			</tr>
			<tr>
				<td style="text-align: right">备注:</td>
				<td><sf:textarea path="remark" rows="4" /></td>
			</tr>
			<tr>
				<td style="text-align: right">类目图标:</td>
				<td style="text-align:center"><img id=iconCategory src="" width="68px" height="64px" /></td>
				<td><sf:hidden id="iconUrl" path="iconUrl"
						 /><a
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-reload'"
					onclick="addImage1()">更改图标</a></td>
			</tr>
			<tr>
				<td style="text-align: right">示例图片:</td>
				<td><img id=simagesCategory src=""  width="200px" height="160px"/></td>
			<td>
				<a
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-reload'"
					onclick="addImage2()">更改图片</a></td>
			</tr>

		</table>
	</sf:form>
	<!-- 上传图片form -->
	<div id="dlg" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 300px; height: 158px; padding: 20px 10px"
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
</body>
</html>