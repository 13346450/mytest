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
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '两次密码输入不一至'
		}
	});

	function save() {
		if ($("#form").form('validate')) {
			var json = {
				url : "${base}/system/SysUser/modifyPwd",
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
					"hideMask();document.location.href='${base}/pages/system/userPwd.jsp'",
					800);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
		}
	}
</script>
</head>
<body>
	<div style="padding: 5px; border: 1px solid #ddd;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	</div>
	<form id="form">
		<table>
			<tr>
				<td>原密码:</td>
				<td><input id="userPwd" name="userPwd" type="password"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>新密码:</td>
				<td><input id="newPwd" name="newPwd" type="password"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>确认新密码:</td>
				<td><input id="surePwd" name="surePwd" type="password"
					class="easyui-validatebox" required="required"
					validType="equals['#newPwd']" /></td>
			</tr>
		</table>
	</form>
</body>
</html>