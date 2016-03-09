<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 10px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 70px;
	FONT-SIZE: 10pt;
	text-align: center;
}
</style>
<script type="text/javascript">
	function fun(value, row, index) {
		return "<input name='checkbox' type='checkbox' disabled='true' checked='checked' value='" + row.idKey + "'/>";
	}

	var url;
	var orderCd;
	function add() {
		$('#dlg').dialog('open').dialog('setTitle', '添加小区信息');
		$('#fm').form('clear');
		$("#savebtn").linkbutton('enable');
		setRadio("gender", "m");
		setRadio("userStatus", "1");
		setRadio("useMark", "1");
		//$('#roleId').combobox('setValue',"0").combobox('setText',"请选择");
		$('#birthday').datebox('setValue', '2013-01-01');
		$('#inDate').datebox('setValue', '2013-01-01');
		$('#outDate').datebox('setValue', '2020-01-01');
		$('#orderCd').val("${orderCd}");
		//$("select #roleId").find("option:eq(0)").selectedIndex=1;//
		//$('#roleId')[0].selectedIndex=1;
		//select 下拉框的第二个元素为当前选中值
		//$('#select_id')[0].selectedIndex=1;
		//radio单选组的第二个元素为当前选中值
		//$('input[@name=items]').get(1).checked=true;
		//$("input[name='gender'][value='m']").attr("checked", "checked");
		//$("input[name='userStatus'][value='1']").attr("checked", "checked");
		//$("input[name='useMark'][value='1']").attr("checked", "checked");
		url = '${base}/system/SysCommunity/insert/'+${deptID};
	}

	function edit() {
		var row = $('#userGrid').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑小区信息');
			$('#fm').form('load', row);//"${sessionBean.roleType}"
			if (row.roleType < "${sessionBean.roleType}") {
				$('#roleId').combobox('setText', row.roleName);
				$("#roleId").combobox('readonly', true);
				$("#savebtn").linkbutton('disable');
			} else {
				$("#roleId").combobox('readonly', false);
				$("#savebtn").linkbutton('enable');
			}
			url = '${base}/system/SysCommunity/update';
		} else {
			$.messager.alert('提示信息', "请选中要编辑的行", 'error');
		}
	}

	function saveUser() {
		if (!$('#fm').form('validate')) {
			return;
		}
		var json = {
			url : url,
			formId : "fm",
			successJSFun : saveSuccess,
			maskId : "dlg"
		};
		ajaxSubmitForm(json);
	}

	function saveSuccess(result) {
		if (result.flag) {
			showMask(result.Msg, "dlg");
			/* setTimeout(
					"hideMask(); $('#dlg').dialog('close'); $('#userGrid').datagrid('reload');",
					800); */
			setTimeout(
					"hideMask(); $('#dlg').dialog('close');location.href='${base}/system/SysCommunity/userListOrEdit/"
							+ result.DeptId + "'", 800);
			if (result.ty == "update") {
				window.parent.leftFrame.RefreshDTreeInEdit(result.DeptId,
						result.idKey, result.communityName);
			} else {
				window.parent.leftFrame.RefreshDTreeInAdd(result.DeptId,
						result.idKey, result.communityName);
			}
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
			setTimeout("hideMask();", 800);
		}
	}

	function dele() {
		var row = $('#userGrid').datagrid('getSelected');
		if (!row) {
			$.messager.alert('提示信息', "请选中需要删除的行", 'error');
			return;
		}
		var tipmsg = "确定要删除选中行吗";
		$.messager
				.confirm(
						'确认',
						tipmsg,
						function(r) {
							if (!r) {
								return;
							}

							var json = {
								url : "${base}/system/SysCommunity/delete?userId="
										+ row.idKey,
								success : function(result) {
									if (result.flag) {
										showMask(result.Msg);
										setTimeout(
												"hideMask(); $('#userGrid').datagrid('reload'); ",
												800);
										window.parent.leftFrame
												.RefreshDTreeInDelete(result.idKey);
									} else {
										hideMask();
										$.messager.alert('错误信息', result.Msg,
												'error');
									}
								}
							};
							ajax(json);
						});
	}

	//通过值修改所选中的单选按钮
	function setRadio(name, sRadioValue) { //传入radio的name和选中项的值
		var oRadio = document.getElementsByName(name);
		for (var i = 0; i < oRadio.length; i++) //循环
		{
			if (oRadio[i].value == sRadioValue) //比较值
			{
				oRadio[i].checked = true; //修改选中状态
				break; //停止循环
			}
		}
	}
	function setField(val, row, index) {
		if (val == "1") {
			return "启动";
		} else {
			return "停用";
		}
	}
</script>
</head>
<body scroll="no">
	<%-- ${param.deptID} --%>
	<table id="userGrid" class="easyui-datagrid" fit="true"
		url="${base}/system/SysCommunity/listPage/${deptID}" border="false"
		striped="true" rownumbers="true" singleSelect="true" pageSize="30"
		toolbar="#toolbar" pagination="true" sortName="roleName"
		sortOrder="asc">
		<thead>
			<tr>
				<!-- <th field="userCd" width="110px" halign="center" sortable="true">小区编码</th> -->
				<th field="communityName" width="110px" halign="center"
					sortable="true">小区名称</th>
				<th field="address" width="110px" halign="center" sortable="true">地址</th>
				<th field="proName" width="110px" halign="center" sortable="true">物业单位</th>
				<th field="telephone" width="110px" halign="center" align="right" sortable="true">联系电话</th>
				<th field="useMark" width="110px" halign="center" sortable="true"
					data-options="formatter: setField" align="center">是否启动</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding-top: 10px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="add()">增加</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="dele()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 400px; height: 390px; padding: 10px 20px"
		buttons="#dlg-buttons">
		<div class="ftitle">小区信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<input id="dept" name="deptId" type="hidden" /> <input id="idKey"
					name="idKey" type="hidden" /> 
				<label>小区名称：</label> <input
					name="communityName" class="easyui-textbox" data-options="required:true,validType:'maxLength[20]'" />
			</div>
			<div class="fitem">
				<label>小区地址：</label> <input name="address"
					class="easyui-textbox" data-options="required:true,validType:'maxLength[50]'" /> 
			</div>
			<div class="fitem">
					<label>物业单位：</label>
				<input name="proName" class="easyui-textbox" data-options="required:true,validType:'maxLength[20]'" />
			</div>
			<div class="fitem">
				<label>物业电话:</label> <input name="telephone" id="telephone"
					class="easyui-textbox" data-options="required:true,validType:'phone'" />
			</div>
			<div class="fitem">
				<!--  <label>所属角色:</label>
				<tag:roleSelectTags id="roleId" name="roleId" required="true"></tag:roleSelectTags>-->
				<label>排序码:</label> <input name="orderCd" id="orderCd"
					class="easyui-textbox" data-options="required:true,validType:'numberLength[1,4]'" />
			</div>
			<div class="fitem">
				<label>小区状态:</label> <input type="radio" name="useMark" value="1" />启用
				<input type="radio" name="useMark" value="0" />停用
				<!-- <input type="radio" name="userStatus" value="1" />在职
				<input type="radio" name="userStatus" value="0" />离职 
				<label>启用标记:</label> -->
			</div>
			<div class="fitem">
				<label style="vertical-align: top">备注:</label>
				<input  id="remarks" name="remarks" class="easyui-textbox" data-options="height:70,multiline:true,validType:'maxLength[50]'">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()" id="savebtn">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>