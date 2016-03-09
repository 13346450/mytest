<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script
	src="${base}/resources/javascripts/dhtmlxtree/codebase/dhtmlxcommon.js"></script>
<script
	src="${base}/resources/javascripts/dhtmlxtree/codebase/dhtmlxtree.js"></script>
<script
	src="${base}/resources/javascripts/dhtmlxtree/codebase/ext/dhtmlxtree_json.js"></script>
<link rel="STYLESHEET" type="text/css"
	href="${base}/resources/javascripts/dhtmlxtree/codebase/dhtmlxtree.css">
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
	width: 100px;
	FONT-SIZE: 10pt;
	text-align: center;
}

.popdom {
	margin-top: 10px;
}
</style>
<script type="text/javascript">
	var url;
	var orderCd;
	$(function() {
		changePopedomAdd();
	});
	//更改添加的等级
	function changePopedomAdd() {
		$.ajax({
			type : "POST",
			url : "${base}/system/SysUserPopedom/getMinType",
			datatype : "text",
			success : function(type) {
				changePopType(type);
				hidePop(type);
			}
		});
	}

	function fun(value, row, index) {
		return "<input name='checkbox' type='checkbox' disabled='true' checked='checked' value='" + row.idKey + "'/>";
	}

	function add() {
		$('#dlg').dialog('open').dialog('setTitle', '添加人员信息');
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
		url = '${base}/system/SysUser/insert/' + ${deptID};
	}

	function edit() {
		var row = $('#userGrid').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑人员信息');
			$('#fm').form('load', row);//"${sessionBean.roleType}"
			if (row.roleType < "${sessionBean.roleType}") {
				$('#roleId').combobox('setText', row.roleName);
				$("#roleId").combobox('readonly', true);
				$("#savebtn").linkbutton('disable');
			} else {
				$("#roleId").combobox('readonly', false);
				$("#savebtn").linkbutton('enable');
			}
			url = '${base}/system/SysUser/update';
		} else {
			$.messager.alert('提示信息', "请选中要编辑的行", 'error');
		}
	}

	function saveUser() {

		if (!$('#fm').form('validate')) {
			return;
		}
		var addr = 	$.trim($('#fm').find("[name='addr']").val());
		var building = 	$.trim($('#fm').find("[name='building']").val());	
		var cdNm = 	$.trim($('#fm').find("[name='cdNm']").val());	
		var deptId = 	$.trim($('#fm').find("[name='deptId']").val());	
		var floor = 	$.trim($('#fm').find("[name='floor']").val());	
		var idKey = 	$.trim($('#fm').find("[name='idKey']").val());	
		var loginNm	 = 	$.trim($('#fm').find("[name='loginNm']").val());
		var orderCd	 = 	$.trim($('#fm').find("[name='orderCd']").val());
		var remark	 = 	$.trim($('#fm').find("[name='remark']").val());
		var roleId	 = 	$.trim($('#fm').find("[name='roleId']").val());
		var roleType	 = 	$.trim($('#fm').find("[name='roleType']").val());
		var room	 = 	$.trim($('#fm').find("[name='room']").val());
		var telephone = 	$.trim($('#fm').find("[name='telephone']").val());	
		var unit	 = 	$.trim($('#fm').find("[name='unit']").val());
		var useMark	 = 	$.trim($('#fm').find("[name='useMark']").val());
		if(cdNm==''){
			alert("登录名不能为空");return;
		}
		if(loginNm==''){
			alert("姓名不能为空");return;
		}
		if(!orderCd.match(/[0-9]{1,5}/)){
			alert("排序码应为数字（0-99999）");return;
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
					"hideMask(); $('#dlg').dialog('close');location.href='${base}/system/SysUser/userListOrEdit/"
							+ result.DeptId + "'", 800);
			if (result.ty == "update") {
				window.parent.leftFrame.RefreshDTreeInEdit(result.DeptId,
						result.idKey, result.cdNm);
			} else {
				window.parent.leftFrame.RefreshDTreeInAdd(result.DeptId,
						result.idKey, result.cdNm);
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
		if (row.loginNm == "${sessionBean.loginNm}") {
			tipmsg = "确认要删除您自己的账号吗";
		}
		$.messager
				.confirm(
						'确认',
						tipmsg,
						function(r) {
							if (!r) {
								return;
							}

							var json = {
								url : "${base}/system/SysUser/delete?userId="
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
	function initPwd() {
		var row = $('#userGrid').datagrid('getSelected');
		if (!row) {
			$.messager.alert('提示信息', "请选中需要重置密码的行", 'error');
			return;
		}
		var loginNm = row.loginNm;
		$.messager.confirm('确认', '您确定要重置账号' + loginNm + '密码吗?', function(r) {
			if (!r) {
				return;
			}
			var json = {
				url : "${base}/system/SysUser/initPwd/" + row.idKey,
				success : function(result) {
					if (result.flag) {
						showMask(result.Msg);
						setTimeout("hideMask();", 800);
					} else {
						hideMask();
						$.messager.alert('错误信息', result.Msg, 'error');
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

	//显示权限范围
	function showPopedomGrid() {
		var row = $('#userGrid').datagrid('getSelected');
		if (!row) {
			return;
		}
		var userId = row.idKey;
		$("#idKey").val(userId);
		$('#popedomGrid').datagrid({
			url : '${base}/system/SysUserPopedom/listPage',
			queryParams : formToJson("queryForm"),
			toolbar : '#toolbar2',
			border : false,
			fit : true,
			border : false,
			striped : true,
			multiSort : true,
			rownumbers : true,
			singleSelect : false,
			selectOnCheck : false,
			checkOnSelect : false,
			pageSize : DEFAULT_PAGE_SIZE,
			pagination : false,
			fitColumns : false,
			idField : 'idKey',
			frozenColumns : [ [ {
				field : 'cdNm',
				title : '地域',
				width : 100,
				align : 'left',
				halign : 'center',
				formatter : function(value, row, index) {
					if (row.deptId == 1) {
						return '全国';
					} else {
						return value;
					}
				}
			}, {
				field : 'type',
				title : '级别',
				width : 60,
				halign : 'center',
				align : 'left',
				formatter : function(value, row, index) {
					if (value == 1) {
						return '全国';
					} else if (value == 2) {
						return '省级';
					} else if (value == 3) {
						return '市级';
					} else if (value == 5) {
						return '小区级';
					}
				}
			} ] ]
		});
		removePopOfSomeRole(row.roleId);
	}
	//普通用户和商户无权限
	function removePopOfSomeRole(roleId){
		if (roleId==7||roleId==8) {
			$('#popAdd').linkbutton('disable');
			$('#popDele').linkbutton('disable');
			return;
		}else{
			$('#popAdd').linkbutton('enable');
			$('#popDele').linkbutton('enable');
		}
	}
	
	//移除权限区域
	function popedomDele() {
		var rows = $('#popedomGrid').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示信息', "请选中需要移除的权限范围！", 'error');
			return;
		}
		var checkedIds = [];
		$.each(rows, function(index, item) {
			checkedIds.push(item.idKey);
		});
		var deleteIds = checkedIds.join(",");
		$('#deleteIds').val(deleteIds);
		$.messager
				.confirm(
						'确认',
						'确定要移除选中的权限?',
						function(r) {
							if (!r)
								return;
							var json = {
								url : '${base}/system/SysUserPopedom/deleteMulti',
								formId : "fm1",
								successJSFun : function(result) {
									if (result.flag) {
										showMask(result.successMsg);
										setTimeout(
												"hideMask(); $('#popedomGrid').datagrid('reload');",
												DEFAULT_DELAY_MS);
									} else {
										$.messager.alert('错误信息',
												result.successMsg, 'error');
										hideMask();
										$('#dataList').datagrid('reload');
									}

								}
							};
							ajaxSubmitForm(json);
						});
	}

	//增加权限区域
	function popeDomadd() {
		var row = $('#userGrid').datagrid('getSelected');
		
		$('#popedomDlg').dialog('open');
		$('#popAddFm').form('clear');
		disOrEnable('enable','enable','enable');
	}
	//改变权限等级实现
	function changePopType(type) {
		var typeArr = [];
		if (type < 2) {
			typeArr.push({
				"text" : "全国",
				"idKey" : "1"
			});
			$('#province').combobox('reload','${base}/system/SysDept/getDeptChildren/1');
		}
		if (type < 3) {
			typeArr.push({
				"text" : "省级",
				"idKey" : "2"
			});
		}
		if (type < 4) {
			typeArr.push({
				"text" : "市级",
				"idKey" : "3"
			});
		}
		if (type < 6) {
			typeArr.push({
				"text" : "小区级",
				"idKey" : "5"
			});
		}
		$("#poptype").combobox('loadData', typeArr);
	}
	//隐藏选择框
	function hidePop(type){
		if(type==5){
			$('#provinceDiv').hide();
			$('#cityDiv').hide();
		}else if(type==3){
			$('#provinceDiv').hide();
		}
	}
	//改变范围
	function changeDisable() {
		var v = $(this).combobox('getValue');
		var en = "enable";
		var dis = "disable";
		if (v == 1) {
			disOrEnable(dis, dis, dis);
		} else if (v == 2) {
			disOrEnable(en, dis, dis);
		} else if (v == 3) {
			disOrEnable(en, en, dis);
		} else if (v == 5) {
			disOrEnable(en, en, en);
		}
	}
	//改变combobox的可用
	function disOrEnable(a, b, c) {
		$('#province').combobox(a);
		$('#city').combobox(b);
		$('#community').combobox(c);
	}
	//保存增加的区域
	function savePop(){
		 if (!$('#popAddFm').form('validate')) {
			return;
		} 
		var row = $('#userGrid').datagrid('getSelected');
		$('#saveIdKey').val(row.idKey);
		var v = $('#poptype').combobox('getValue');
		$('#saveType').val(v);
		if(v==1){
			$('#saveDepts').val(1);
		}else if(v==2){
			$('#saveDepts').val($('#province').combobox('getValues'));
		}else if(v==3){
			$('#saveDepts').val($('#city').combobox('getValues'));
		}else if(v==5){
			$('#saveDepts').val($('#community').combobox('getValues'));
		}
		console.info($('#saveDepts').val());
		var json = {
			url : '${base}/system/SysUserPopedom/insert',
			formId : "savePopForm",
			successJSFun : popSaveSuccess,
			maskId : "popedomDlg"
		};
		ajaxSubmitForm(json);
	}
	
	//增加权限成功
	function popSaveSuccess(result){
		if (result.flag) {
			showMask(result.Msg, "popedomDlg");
			setTimeout(
					"hideMask(); $('#popedomDlg').dialog('close'); $('#popedomGrid').datagrid('reload');", 800);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
			setTimeout("hideMask();", 800);
		}
	}
	//改变选择的城市
	function changeCity(){
		var type=$('#poptype').combobox('getValue');
		if(!(type==3||type==5)){
			return;
		}
		var v=$(this).combobox('getValues');
		if(v.length==0){
			v=-2;
		}
		$('#city').combobox('reload','${base}/system/SysUserPopedom/getCity?depts='+v);
		$('#city').combobox('clear');
	}
	//改变选择的城市
	function changeCommunity(){
		if($('#poptype').combobox('getValue')!=5){
			return;
		}
		var v=$(this).combobox('getValues');
		if(v.length==0){
			v=-2;
		}
		$('#community').combobox('reload','${base}/system/SysUserPopedom/getCommunity?depts='+v);
		$('#community').combobox('clear');
	}
</script>
</head>
<body class="easyui-layout" scroll="no">
	<%-- ${param.deptID} --%>
	<div data-options="region:'center',title:'用户资料'">
		<table id="userGrid" class="easyui-datagrid" fit="true"
			url="${base}/system/SysUser/listPage/${deptID}" border="false"
			striped="true" rownumbers="true" singleSelect="true" pageSize="30"
			toolbar="#toolbar" pagination="true" sortName="roleName"
			data-options="onClickRow:showPopedomGrid" sortOrder="asc">
			<thead>
				<tr>
					<!-- <th field="userCd" width="110px" halign="center" sortable="true">用户编码</th> -->
					<th field="loginNm" width="110px" halign="center" sortable="true">登录名</th>
					<th field="cdNm" width="110px" halign="center" sortable="true">用户名称</th>
					<th field="roleId" width="110px" halign="center" sortable="true" hidden="true">用户名称</th>
					<th field="roleName" width="110px" halign="center" align="center"
						sortable="true">所属角色</th>
					<th field="building" width="50px" halign="center" align="right"
						sortable="true">楼栋号</th>
					<th field="unit" width="50px" halign="center" align="right"
						sortable="true">单元号</th>
					<th field="floor" width="50px" halign="center" align="right"
						sortable="true">楼层号</th>
					<th field="room" width="50px" halign="center" align="right"
						sortable="true">房号</th>
					<th field="addr" width="110px" halign="center" sortable="true">联系地址</th>
					<th field="telephone" width="110px" halign="center" align="right"
						sortable="true">手机号</th>
					<th field="useMark" width="110px" halign="center" sortable="true"
						align="center" data-options="formatter: setField">是否启动</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- 右侧的小区权限树 -->
	<!-- <div data-options="region:'east',title:'小区范围',split:true"
		style="width: 230px;">
		<div>
			<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
		</div>
		<div id="treeboxbox_tree"
			style="width: 98%; height: 94%; background-color: #f5f5f5; border: 1px solid Silver; overflow: auto"></div>
	</div> -->
	<div data-options="region:'east',title:'管辖范围',split:true"
		style="width: 230px;">
		<table id="popedomGrid" class="easyui-datagrid"></table>
		<div style="display: none">
			<form id="queryForm">
				<input type="hidden" name="idKey" id="idKey">
			</form>
			<form id="fm1">
				<input type="hidden" name="deleteIds" id="deleteIds">
			</form>
		</div>

		<div id="toolbar2" style="display: none">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="popAdd"
				iconCls="icon-add" plain="true" onclick="popeDomadd()">增加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="popedomDele()" id="popDele">删除</a>
		</div>
		<div id="dlg-buttons2">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="savePop()" id="savebtn">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#popedomDlg').dialog('close')">取消</a>
		</div>
		<div id="popedomDlg" class="easyui-dialog"
			data-options="modal:true,closed:true,iconCls:'icon-save',title:'增加管辖范围'"
			style="width: 650px; height: 460px; padding: 10px 20px"
			buttons="#dlg-buttons2">
			<form id="popAddFm" method="post" novalidate>
				<div class="fitem">
					<input id="addUserId" name="userId" type="hidden" /> <label>权限等级:</label>
					<input class="easyui-combobox" name="type" id="poptype"
						data-options="url:'',
                    method:'get',
                    panelHeight:'auto',
                    editable:false,
                    valueField:'idKey',
                    textField:'text',
                    onSelect:changeDisable,
                    required:true"
						style="width: 200px;">
				</div>
				<div class="fitem" id="provinceDiv">
					<label>省份:</label> <input class="easyui-combobox" name="state"
						id="province"
						data-options="url:'${base}/system/SysUserPopedom/getProvince',
                    method:'get',
                    height:100,
                    valueField:'idKey',
                    textField:'cdNm',multiple:true,multiline:true,onSelect:changeCity"
						style="width: 200px; heigth: 200px">
				</div>
				<div class="fitem" id="cityDiv">
					<label>城市:</label> <input class="easyui-combobox" name="state"
						id="city"
						data-options="url:'${base}/system/SysUserPopedom/getCity?depts=-2',
                    method:'get',
                    height:100,
                    valueField:'idKey',
                    textField:'cdNm',multiple:true,multiline:true,onSelect:changeCommunity"
						style="width: 200px; heigth: 200px">
				</div>
				<div class="fitem">
					<label>小区:</label> <input class="easyui-combobox" name="state"
						id="community"
						data-options="url:'${base}/system/SysUserPopedom/getCommunity?depts=-2',
                    method:'get',
                    height:100,
                    valueField:'idKey',
                    textField:'cdNm',multiple:true,multiline:true"
						style="width: 200px; heigth: 200px">
				</div>

			</form>
			<div style="display:none">
				<form id="savePopForm">
					<input type="hidden" name="userId" id="saveIdKey">
					<input type="hidden" name="type" id="saveType">
					<input type="hidden" name="depts" id="saveDepts">
				</form>
			</div>
		</div>

	</div>
	<div id="toolbar" style="padding-top: 10px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="add()">增加</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="dele()">删除</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="initPwd()">密码重置</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 650px; height: 420px; padding: 10px 20px"
		buttons="#dlg-buttons">
		<div class="ftitle">用户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<input id="roleType" name="roleType" type="hidden" /> <input
					id="deptId" name="deptId" type="hidden" /> <input id="idKey"
					name="idKey" type="hidden" /> <label>登录名:</label> <input
					name="loginNm" class="easyui-validatebox" required="true" />
			</div>
			<div class="fitem">
				<label>用户姓名:</label> <input name="cdNm" class="easyui-validatebox"
					required="true" />
			</div>
			<div class="fitem">
				<label>所属角色:</label>
				<c:choose>
					<c:when test="${sessionScope.sessionBean.roleId == 1}">
						<input class="easyui-combobox" name="roleId" id="roleId"
							data-options="url:'${base}/system/SysRole/queryAllSysRole',
                    method:'get',
                    width:170,
                    required:true,
                    panelHeight:'auto',
                    valueField:'idKey',
                    textField:'roleName'"
							style="width: 200px; heigth: 200px">
					</c:when>
					<c:otherwise>
						<select id="roleId" class="easyui-combobox" name="roleId" required="true",
							panelHeight="auto" style="width: 170px;">
							<option value="7">普通住户</option>
							<option value="8">普通商户</option>
						</select>
					</c:otherwise>
				</c:choose>

				<label>排序码:</label> <input name="orderCd" id="orderCd"
					class="easyui-validatebox" size="5" required="true" />
			</div>
			<div class="fitem">
				<label>用户状态:</label> <input type="radio" name="useMark" value="1" />启用
				<input type="radio" name="useMark" value="0" />停用
				<!-- <input type="radio" name="userStatus" value="1" />在职
				<input type="radio" name="userStatus" value="0" />离职 
				<label>启用标记:</label> -->
			</div>

			<div class="fitem">
				<label>手机号:</label> <input name="telephone"
					class="easyui-validatebox" />
			</div>
			<div class="fitem">
				<label>联系地址:</label> <input name="addr" class="easyui-validatebox" />
			</div>
			<div class="fitem" style="width: 500px; padding-left: 20px;">
				<fieldset>
					<legend>住房信息：</legend>
					<div
						style="padding-left: 60px; margin-bottom: 10px; margin-top: 5px">
						楼栋号:<input name="building" class="easyui-validatebox" size="2"
							style="margin-right: 10px" /> 单元号:<input name="unit"
							class="easyui-validatebox" size="2" style="margin-right: 10px" />
						楼层号:<input name="floor" class="easyui-validatebox" size="2"
							style="margin-right: 10px" /> 房号:<input name="room"
							class="easyui-validatebox" size="2" style="margin-right: 10px" />
					</div>
				</fieldset>
			</div>
			<div class="fitem">
				<label>备注:</label>
				<textarea rows="4" name="remark" style="width: 400px"></textarea>
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