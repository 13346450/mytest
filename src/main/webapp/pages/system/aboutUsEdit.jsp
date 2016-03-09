<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<style>
body {
	margin-left: 5px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
	// $.extend($.fn.validatebox.defaults.rules, 
	// 		{equals: {validator: function(value,param)
	// 		{return value == $(param[0]).val();},
	// 		message: '两次密码输入不一至'}}
	// 		);

	function save() {
		if ($("#form").form('validate')) {
			var json = {
				url : "${base}/system/SysAboutUS/createOrUpdate",
				formId : "form",
				successJSFun : addSaveSuccess
			};
			ajaxSubmitForm(json);
		}
	}

	function addSaveSuccess(result) {
		if (result.flag) {
			showMask(result.Msg, 'form');
			setTimeout(
					"hideMask();document.location.reload();",
					800);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
		}
	}
	$().ready(function(e){
		$.ajax({
			url:'${base}/system/SysAboutUS/getInfo',
			dataType:'json',
			success:function(data){
				$("[name='aboutUSTitle']").val(data.aboutUSTitle);
				$("[name='aboutUSSubtitle']").val(data.aboutUSSubtitle);
				$("[name='aboutUSContent']").val(data.aboutUSContent);
			}
		})
	});
</script>
</head>
<body>
	<div style="padding: 5px; border: 1px solid #ddd;">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	</div>
	<form id="form">
		<table>
			<tr>
				<td>标题:</td>
				<td><input id="1" name="aboutUSTitle" type="text"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>副标题:</td>
				<td><input id="2" name="aboutUSSubtitle" type="text"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>正文:</td>
				<td><input id="3" name="aboutUSContent" type="text"
					class="easyui-validatebox" required="required" /></td>
			</tr>
		</table>
	</form>
</body>
</html>