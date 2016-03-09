<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/ajaxfileupload.js"></script>
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
	text-align: right;
	padding-right: 10px;
}

.datagrid-header-rownumber {
	width: 48px;
}

.datagrid-cell-rownumber {
	width: 48px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizShop/listPage',
			queryParams : formToJson("queryForm"),
			toolbar : '#toolbar',
			border : false,
			fit : true,
			border : false,
			striped : true,
			multiSort : true,
			//sortName: 'rcSn',
			//sortOrder: 'asc',
			rownumbers : true,
			singleSelect : true,
			selectOnCheck : false,
			checkOnSelect : false,
			pageSize : DEFAULT_PAGE_SIZE,
			pagination : true,
			fitColumns : false,
			onLoadSuccess : loadSuccess,
			idField : 'idKey',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'name',
				title : '商铺名称',
				width : 150,
				align : 'left',
				halign : 'center',
				halign : 'center',
				sortable : false
			}, {
				field : 'communityName',
				title : '所在小区',
				width : 100,
				halign : 'center',
				align : 'left'
			}, {
				field : 'userName',
				title : '商家名称',
				width : 110,
				halign : 'center',
				align : 'left',
				sortable : true
			}] ],
			columns : [ [{
				field : 'status',
				title : '商铺状态',
				width : 80,
				halign : 'center',
				align : 'center',
				formatter: function(value,row,index){
					if (value=='2'){
						return "正常营业";
					} else if(value=="1"){
						return "未审核";
					}else if(value=="3"){
						return "停止营业";
					}else if(value=="4"){
						return "强制关闭";
					}
				}
			}, {
				field : 'telephone',
				title : '联系方式',
				width : 90,
				halign : 'center',
				align : 'right'
			}, {
				field : 'addr',
				title : '商铺地址',
				width : 115,
				halign : 'center',
				align : 'left'
			}, {
				field : 'chgDt',
				title : '创建日期',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			} ] ]
		});
		$('#bodyLayout').layout('collapse', 'east');
		mainPageView();

	});
	function mainPageView() {
		bindDatagrid("dataList", true);
	}
	/*表格加载成功后调用的方法 */
	function loadSuccess() {
		var rows = $('#dataList').datagrid('getRows');
		if (rows.length > 0) {
			$('#dataList').datagrid('clearChecked');
		}
	}
	//查询按钮函数
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
	//审核或关停多个商铺
	function changeStatus(type) {//type 3为审核通过  4为强制关停
		var alertMessage="审核通过";
		if(type==4){
			alertMessage="强制关闭";
		}
		var checkedIds = [];
		var checkedItems = $('#dataList').datagrid('getChecked');
		if (checkedItems.length == 0) {
			$.messager.alert('提示信息', "请选中需要操作的商铺", 'error');
			return;
		}
		$.each(checkedItems, function(index, item) {
			checkedIds.push(item.idKey);
		});
		var deleteIds = checkedIds.join(",");
		$("#deleteIds").val(deleteIds);
		$('#status').val(type);
		//console.log( $("#deleteIds").val());
		$.messager
				.confirm(
						'确认',
						'确定'+alertMessage + checkedItems.length + ' 个商铺吗?',
						function(r) {
							if (!r)
								return;
							var json = {
								url : "${base}/business/BizShop/updateMulti",
								formId : "fm1",
								successJSFun : function(result) {
									if (result.successMsg) {
										showMask(result.successMsg);
										setTimeout(
												"hideMask(); $('#dataList').datagrid('reload'); ",
												DEFAULT_DELAY_MS);
									} else {
										hideMask();
										$.messager.alert('错误信息',
												result.errorMsg, 'error');
									}
								}
							};
							ajaxSubmitForm(json);
						});
	}
	//删除多个商铺
	function removeShop() {
		var checkedIds = [];
		var checkedItems = $('#dataList').datagrid('getChecked');
		if (checkedItems.length == 0) {
			$.messager.alert('提示信息', "请选中需要删除的商铺", 'error');
			return;
		}
		$.each(checkedItems, function(index, item) {
			checkedIds.push(item.idKey);
		});
		var deleteIds = checkedIds.join(",");
		$("#deleteIds").val(deleteIds);
		$.messager
				.confirm(
						'确认',
						'确定删除商铺吗?',
						function(r) {
							if (!r)
								return;
							var json = {
								url : "${base}/business/BizShop/deleteMulti",
								formId : "fm1",
								successJSFun : function(result) {
									if (result.successMsg) {
										showMask(result.successMsg);
										setTimeout(
												"hideMask(); $('#dataList').datagrid('reload'); ",
												DEFAULT_DELAY_MS);
									} else {
										hideMask();
										$.messager.alert('错误信息',
												result.errorMsg, 'error');
									}
								}
							};
							ajaxSubmitForm(json);
						});
	}
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
		<!-- 上部按钮层 -->
		<div
			data-options="region:'north', title:'&nbsp;操作面板',closedTitle:'操作面板(点击展开)'"
			style="height: 66px;margin-top:5px;">
			<form id="queryForm" method="post">
				<table>
					<tr>
						<td style="width: 65px; padding-left: 8px">查询条件：</td>
						<td style="padding-left: 10px">所在小区： 
								<tag:popedomCommunitySelect
								id="communityId" name="communityId" headLabel="全选" width="110"
								headValue="-1" editable="true"></tag:popedomCommunitySelect>
						</td>
						<td style="padding-left: 10px">
						商户姓名：
						<input class="easyui-textbox" name="userName" data-options="width:60">
						</td>
						<td style="padding-left: 10px">商铺状态 ：
						<select class="easyui-combobox" name="status" id="queryStatus" data-options="width:100,panelHeight:'auto',editable:false">
						<option value="-1" selected>全部</option>
						<option value="1">未审核</option>
						<option value="2">正常营业</option>
						<option value="3">停止营业</option>
						<option value="4">强制关闭</option>
						</select>
						</td>
						<td style="width: 100px" class="tdRight"><a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="queryDatas()">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;商铺列表'">
			<div id="toolbar" >
			&nbsp;&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true" onclick="changeStatus(2)">审核通过</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="changeStatus(4)">强制关闭</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="removeShop()">删除</a>
			</div>
			<table id="dataList"></table>
		</div>
		
		<div style="display: none;">
			<form id="fm1" method="post">
				<input name="deleteIds" id="deleteIds">
				<input name="status" id="status">
			</form>
			
		</div>
	</div>
	
</body>
</html>