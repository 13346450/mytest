<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js" ></script>
<style >
	body {
	margin-left: 5px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/system/SysCommunication/insert",
	        		formId : "form",
	        		successJSFun : addSaveSuccess
	        };
	        ajaxSubmitForm(json);
		}
	}
	function addSaveSuccess(result) {
		//showMask(result.successMsg);
		if (result.flag) {
			showMask(result.Msg);
			setTimeout("hideMask();document.location.href='${base}/system/SysCommunication/edit/';", 300);
			//$.messager.alert('提示信息', result.Msg, 'success');
			//setTimeout("document.location.href='${base}/system/SysCommunication/edit/';", 800);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
			setTimeout("hideMask();", 1);
		}
	}
</script>
</head>
<body>
	<div style="padding: 5px; border: 1px solid #ddd;">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	</div>
	<form id="form">
		<input type="hidden" name="intervals" id="intervals" value="0">
		<table class="tableDetail" style="width: 100%">
			<tr>
				<td class="tdLeft" style="width: 90px">超时时长(秒):</td>
				<td class="tdLeft" style="width: 70px"><input id="overTime"
					name="overTime" class="easyui-validatebox"
					data-options="required:true" /></td>
				<td class="tdLeft" style="width: 70px">重发次数:</td>
				<td class="tdLeft" style="width: 70px"><input id="repertCount"
					name="repertCount" class="easyui-validatebox"
					data-options="required:true" /></td>
				<td class="tdLeft" style="width: 70px">区域地址码:</td>
				<td class="tdLeft" style="width: 70px"><input id="areaCode"
					name="areaCode" class="easyui-validatebox"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="tdLeft" style="width: 90px">主站编号:</td>
				<td class="tdLeft" style="width: 70px"><input id="mStationNum"
					name="mStationNum" class="easyui-validatebox"
					data-options="required:true" /></td>
				<td class="tdLeft" style="width: 70px">网关密码:</td>
				<td class="tdLeft" style="width: 70px"><input
					id="gatewayPasswords" name="gatewayPasswords"
					class="easyui-validatebox" data-options="required:true" /></td>
				<td class="tdLeft" style="width: 70px">IP地址:</td>
				<td class="tdLeft" style="width: 70px"><input id="ip" name="ip"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="tdLeft" style="width: 70px">端口地址:</td>
				<td class="tdLeft" style="width: 70px"><input id="port" name="port"
					class="easyui-validatebox" data-options="required:true" /></td>
				<td class="tdLeft" style="width: 70px">月统计起始日期:</td>
				<td class="tdLeft" style="width: 90px"><input
					class="easyui-combobox" value="1" name="stationMonthStartDate"
					id="stationMonthStartDate"
					data-options="
		valueField: 'label',
		textField: 'value',
		data: [{
			label: '1',
			value: '1'
		},{
			label: '21',
			value: '21'
		},{
			label: '22',
			value: '22'
		},{
			label: '23',
			value: '23'
		},{
			label: '24',
			value: '24'
		},{
			label: '25',
			value: '25'
		},{
			label: '26',
			value: '26'
		},{
			label: '27',
			value: '27'
		},{
			label: '28',
			value: '28'
		}]" />
				</td>
				<!-- <td class="tdLeft" style="width: 160px"><input type="checkbox"
					name="yesnoIdOnlinestation" value="Yes" /> 在线监测点是否可见</td> -->
					<input type="hidden" name="yesnoIdOnlinestation" id="yesnoIdOnlinestation" value="Yes" />
				<td class="tdLeft" style="width: 160px"><input type="checkbox"
					name="yesnoIdCallchild" value="Yes" /> 是否可召测下级</td>
			</tr>

		</table>
	</form>
</body>
</html>