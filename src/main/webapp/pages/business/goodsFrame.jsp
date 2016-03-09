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
	var imagesIsUsed = "0";
	var postUrl = "";
	var typeId = '1';
	var tempSalesId, tempShopId, tempCategoryId;
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizGoods/listPage',
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
			//onDblClickRow : dblClickRow,
			onLoadSuccess : loadSuccess,
			idField : 'idKey',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'goodsName',
				title : '商品名称',
				width : 150,
				align : 'left',
				halign : 'center',
				sortable : true
			}, {
				field : 'goodsType',
				title : '商品类型',
				width : 60,
				halign : 'center',
				align : 'left'
			}, {
				field : 'salesName',
				title : '商家名称',
				width : 70,
				align : 'left',
				halign : 'center',
				sortable : true
			}, {
				field : 'shopName',
				title : '商铺名称',
				width : 70,
				halign : 'center',
				align : 'left',
				sortable : true
			}, {
				field : 'unitPrice',
				title : '商品原价',
				width : 60,
				align : 'right',
				halign : 'center',
				sortable : true
			}, {
				field : 'discountPrice',
				title : '商品现价',
				width : 60,
				align : 'right',
				halign : 'center',
				sortable : true
			}, {
				field : 'quantity',
				title : '商品库存',
				width : 60,
				align : 'right',
				halign : 'center',
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'goodsOrder',
				title : '商品排序',
				width : 80,
				halign : 'center',
				hidden : true
			}, {
				field : 'goodsStatusName',
				title : '商品状态',
				width : 105,
				halign : 'center',
				align : 'center'
			}, {
				field : 'telephone',
				title : '联系方式',
				width : 80,
				halign : 'center',
				align : 'right'
			}, {
				field : 'goodsAddr',
				title : '商品地址',
				width : 115,
				halign : 'center',
				align : 'left'
			}, {
				field : 'goodsNote',
				title : '商品描述',
				width : 140,
				halign : 'center',
				align : 'left'
			}, {
				field : 'uploadDt',
				title : '上传日期',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				field : 'goodsStatus',
				hidden : true
			}, {
				field : 'autoPutOnTime',
				title : '自动上架时间',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			}

			] ]
		});
		/* $('#dlg').dialog({
		    onClosed:checkImagesIsUsed()
		}); */
		$('#bodyLayout').layout('collapse', 'east');
		mainPageView();

	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		//$("#searchButton").linkbutton('enable');//查找按钮可使用
	}

	function dblClickRow(rowIndex, rowData) {
		edit();
	}
	/*表格加载成功后调用的方法 */
	function loadSuccess() {
		var rows = $('#dataList').datagrid('getRows');
		if (rows.length > 0) {
			$('#dataList').datagrid('clearChecked');
		}
	}
	/* 删除一条记录  */
	function deleteOne() {
		var row = $('#dataList').datagrid('getSelected');
		if (!row) {
			$.messager.alert('提示信息', "请选中需要删除的商品。", 'error');
			return;
		}
		$.messager.confirm('确认', '确定要删除选中的商品吗?', function(r) {
			if (!r) {
				return;
			}
			var json = {
				url : "${base}/business/BizGoods/delete/" + row.idKey,
				success : function(result) {
					if (result.successMsg) {
						showMask(result.successMsg);
					} else {
						$.messager.alert('错误信息', result.successMsg, 'error');
					}
					setTimeout(
							"hideMask(); $('#dataList').datagrid('reload'); ",
							DEFAULT_DELAY_MS);
				}
			};
			ajax(json);
		});
	}
	//选择活动时间
	function chooseActiveTime() {
		var checkedIds = [];
		var checkedItems = $('#dataList').datagrid('getChecked');
		if (checkedItems.length == 0) {
			$.messager.alert('提示信息', "请在需要设为活动的商品前打钩！", 'error');
			return;
		}
		$('#chooseTime').dialog('open').dialog('setTitle', '选择活动时间');
		$('#activeTime').datebox('clear');
	}
	//删除多条
	function operateMulti(type) {
		var operateUrl = "${base}/business/BizGoods/deleteMulti";
		var alertStr = "删除";
		var goodsStatus;
		if (type == "pullOffForce") {
			operateUrl = "${base}/business/BizGoods/pullOffMulti";
			alertStr = "强制下架";
			goodsStatus = "3";//强制下架
		} else if (type == "pullOff") {
			operateUrl = "${base}/business/BizGoods/pullOffMulti";
			alertStr = "下架";
			goodsStatus = "1";//下架
		} else if (type == "pullOn") {
			operateUrl = "${base}/business/BizGoods/pullOffMulti";
			alertStr = "审核";
			goodsStatus = "2";//销售中
		} else if (type == "active") {//设为活动
			operateUrl = "${base}/business/BizGoods/pullOffMulti";
			alertStr = "设为活动";
			goodsStatus = $('input:radio[name="forever"]:checked').val();
			if (goodsStatus == 7) {
				if (!$('#chooseTimeF').form('validate')) {
					return;
				}
				var activeDate = $('#activeTime').datebox('getValue') + ":00";
				$('#autoPutOnTime').val(activeDate);
			} else {
				$('#autoPutOnTime').val("");
			}
			$('#chooseTime').dialog('close');
		} else {
			return;
		}
		var checkedIds = [];
		var checkedItems = $('#dataList').datagrid('getChecked');
		if (checkedItems.length == 0) {
			$.messager.alert('提示信息', "请选中需要" + alertStr + "的商品。", 'error');
			return;
		}
		$.each(checkedItems, function(index, item) {
			if (item.goodsStatus != goodsStatus || goodsStatus == 7) {//允许修改活动时间
				checkedIds.push(item.idKey);
			}
		});
		if (checkedIds.length == 0) {
			$.messager.alert('提示信息', "您选中的商品中没有需要" + alertStr + "的！", 'error');
			return;
		}
		var deleteIds = checkedIds.join(",");
		$("#deleteIds").val(deleteIds);
		$('#goodsStatus').val(goodsStatus);
		//console.log( $("#deleteIds").val());
		$.messager
				.confirm(
						'确认',
						'确定要' + alertStr + '选中的 ' + checkedItems.length
								+ ' 个商品吗?',
						function(r) {
							if (!r)
								return;
							var json = {
								url : operateUrl,
								formId : "fm1",
								successJSFun : function(result) {
									if (result.flag) {
										showMask(result.successMsg);
										setTimeout(
												"hideMask(); $('#dataList').datagrid('reload');",
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

	/* 新增一条记录 */
	function add() {
		$('#dlg').dialog('open').dialog('setTitle', '增加商品');
		$('#fm').form('clear');
		$('#editGoodsStatus').combobox('setValue', 1);//新增商品设为未上架
		//$('#goodsTypeId2').combotree('setValue', typeId);
		postUrl = "${base}/business/BizGoods/insert";
		changGoodsImages();
	}
	/* 编辑一条记录 */
	function edit() {
		var row = $('#dataList').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑商品');
			$('#fm').form('load', row);
			postUrl = '${base}/business/BizGoods/update';
			changGoodsImages();
			setValueByCommunity('#editSales', '#editCommunityId',
					'${base}/system/SysUser/querySalesByCommunityId/');
			setValueByCommunity('#editShop', '#editSales',
					'${base}/business/BizShop/queryShopByUserId/');
			setValue('#goodsTypeId2', '#editCommunityId',
					'${base}/business/BizCategory/getCategoryTree/');
		} else {
			$.messager.alert('提示信息', "请选择一个商品，或双击要编辑的商品", 'error');
		}
	}
	/* 保存数据 */
	function save() {
		var json = {
			url : postUrl,
			formId : "fm",
			successJSFun : saveSuccess,
			maskId : "dlg"
		};
		ajaxSubmitForm(json);
	}

	function saveSuccess(result) {
		if (result.flag) {
			showMask(result.successMsg, "dlg");
			setTimeout(function() {
				hideMask();
				imagesIsUsed = "0";
				$('#dlg').dialog('close');
				$('#dataList').datagrid('reload');
			}, DEFAULT_DELAY_MS);
		} else {
			hideMask();
			$.messager.alert('提示信息', result.successMsg, "error");
		}
	}
	/*
	function exportExcel() {
		$.messager.confirm('导出Excel', '确定要把数据导出到Excel吗?', function(r) {
			if (!r) {
				return;
			}
			showMask("正在处理，请稍候。。。", "dlg");
			var option=$("#dataList").datagrid("options");
			location.href = "${base}/business/BiztMidMonth/excel/export?"
					+ $('#queryForm').serialize()+"&order="+option.sortOrder+"&sort="+option.sortName;
			hideMask();
		});
	} */
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
	function changGoodsImages() {
		if ($('#showSimages').val()) {
			$('#goodsImages').attr("src", "${base}/" + $('#showSimages').val());
		} else {
			$('#goodsImages').attr("src", "");
		}
	}
	function addImage() {
		$('#dl').dialog('open').dialog('setTitle', '上传图片');
		$('#uploadFm').form('clear');
	}
	function uploadImage() {
		if ($("#upFile").val() == "") {
			$.messager.alert('错误信息', "上传文件不能为空", 'error');
			return;
		}
		var json = {
			url : "${base}/business/BizGoods/uploadFile",
			fileElementId : "upFile",
			maskId : "dl",
			success : function(result) {
				var res = eval("(" + result + ")");
				if (res.successMsg) {
					showMask(res.successMsg);
					setTimeout(function() {
						hideMask();
						$('#dl').dialog('close');
						setSimages(res.simagesAddr);
						setLimages(res.limagesAddr);
						setShowSimages(res.showSimagesAddr);
						setShowLimages(res.showLimagesAddr);
						changGoodsImages();
						imagesIsUsed = "1";
					}, DEFAULT_DELAY_MS);
				} else {
					hideMask();
					$.messager.alert('错误信息', res.errorMsg, 'error');
				}
			}
		};
		ajaxFileUpload(json);
	}

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
	function checkSave() {
		if (!$('#fm').form('validate')) {
			return;
		}
		typeId = $('#goodsTypeId2').combotree('getValue');
		var json1 = {
			url : '${base}/business/BizCategory/checkLeaf/' + typeId,
			success : function(result) {
				if (result.isLeaf == '0') {
					$.messager.alert('错误信息', '商品类型请选择小类！', 'error');
					hideMask();
				} else {
					save();
				}
			}
		};
		ajax(json1);
	}

	function checkImagesIsUsed() {
		if (imagesIsUsed == "1") {
			var json = {
				url : "${base}/business/BizGoods/deleteImages",
				formId : "fm",
				successJSFun : function() {
					hideMask();
				}
			}
			ajaxSubmitForm(json);
		}
	}

	function changeCommunity() {
		var communityId = $(this).combotree('getValue');
		$('#editSales').combobox('clear');
		$('#goodsTypeId2').combotree('clear');
		$('#editShop').combobox('clear');
		$('#editSales')
				.combobox(
						'reload',
						'${base}/system/SysUser/querySalesByCommunityId/'
								+ communityId);
		$('#goodsTypeId2').combotree('reload',
				'${base}/business/BizCategory/getCategoryTree/' + communityId);
	}
	//选择商家后，改变店铺
	function salesSelect() {
		var userId = $(this).combobox('getValue');
		$('#editShop').combobox('clear');
		$('#editShop').combobox('reload',
				'${base}/business/BizShop/queryShopByUserId/' + userId);
	}
	//编辑区的小区选择后重新加载商家名称和商品目录
	function setValueByCommunity(domId, parentId, url) {
		var tempId = $(parentId).combobox('getValue');
		$(domId).combobox('reload', url + tempId);
	}

	function setValue(domId, parentId, url) {
		var tempId = $(parentId).combobox('getValue');
		$(domId).combotree('reload', url + tempId);
	}

	//活动商品是否立即上架
	function checkForever() {
		if ($('input:radio[name="forever"]:checked').val() == '6') {
			$('#autoPutOnTimeDiv').slideUp();
			$('#activeTime').datebox('clear');
			$('#activeTime').datetimebox({
				required : false
			});
		} else {
			var time=getServerTime();
			$('#activeTime').datetimebox({
				required : true,
				value:time
			});
			console.info('当前服务器时间'+$('#activeTime').datetimebox('getValue'));
			$('#autoPutOnTimeDiv').slideDown();
		}
	}
	//datebox扩展验证框
	$.extend($.fn.validatebox.defaults.rules,
					{
						dateTimeCheck : {
							validator : function(value) {
								var str = value.replace(/-/g, "/");
								var activeLong = new Date(str).getTime();
								var str2=getServerTime();
								var afterLong = new Date(str2).getTime() + 1000 * 60 * 1;
								return activeLong > afterLong;
							},
							message : '自动上架时间至少比当前时间大1分钟！'
						}
					});
	//获取服务器时间
	function getServerTime() {
		var str;
		var json = {
			url : '${base}/appservice/mobileLogin',
			data : {
				'cmd' : 4802
			},
			async : false,
			cache : false,
			success : function(result) {
				hideMask();
				str = result.data.serverTime;
			}
		};
		ajax(json);
		return str.replace(/-/g, "/");
	}
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
		<!-- 上部按钮层 -->
		<div
			data-options="region:'north', title:'&nbsp;操作面板',closedTitle:'操作面板(点击展开)'"
			style="height: 66px;">
			<form id="queryForm" method="post">
				<table>
					<tr>
						<td colspan="9">
					</tr>
					<tr>
						<td style="padding-left: 10px">所在小区： <tag:popedomCommunitySelect
								id="communityId" name="communityId" headLabel="全选" width="110"
								headValue="-1"></tag:popedomCommunitySelect>
						</td>
						<td style="padding-left: 10px">商家名称： <input name="userName"
							class="easyui-textbox" data-options="width:100">
						</td>
						<td style="padding-left: 10px">商铺名称： <input name="shopName"
							class="easyui-textbox" data-options="width:100">
						</td>
						<td style="padding-left: 10px">销售状态： <selcect name="status"
								class="easyui-combobox"
								data-options="width:100,panelHeight:'auto'">
							<option value="-1">全部</option>
							<option value="1">未上架</option>
							<option value="2">销售中</option>
							<option value="3">强制下架</option>
							<option value='6'>活动商品上架</option>
							<option value='7'>活动商品定时上架</option>
							</selcect>
						</td>
						<%-- <td style="padding-left: 10px">商品类目 <tag:categorySelectTags1
								id="goodsTypeId" name="goodsTypeId" headLabel="全选" headValue="1" width="140"
								editable="true"></tag:categorySelectTags1>
						</td> --%>
						<td style="width: 100px" class="tdRight"><a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="queryDatas()">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;商品列表'">

			<table id="dataList"></table>
		</div>
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true" onclick="operateMulti('pullOn')">上架</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="operateMulti('pullOff')">下架</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true"
				onclick="operateMulti('pullOffForce')">强制下架</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="operateMulti('delete')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="add()">增加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="chooseActiveTime()">设置为活动商品</a>
		</div>
		<!-- 编辑表单界面 -->
		<div id="dlg" class="easyui-dialog"
			data-options="modal:true,closed:true,iconCls:'icon-edit',onClose:function(){checkImagesIsUsed();}"
			style="width: 600px; height: 550px; padding: 10px 20px"
			buttons="#dlg-buttons">
			<div class="ftitle">商品信息</div>
			<form id="fm" method="post" novalidate>
				<div style="width: 55%; float: left">

					<div class="fitem">
						所在小区:
						<tag:popedomCommunitySelect id="editCommunityId"
							name="communityId" width="175" onSelect="changeCommunity"
							editable="false" required="true"></tag:popedomCommunitySelect>
					</div>

					<div class="fitem">
						商家名称: <input class="easyui-combobox" id="editSales" name="salesId"
							data-options="
                    url:'',
                    method:'get',
                    valueField:'idKey',
                    textField:'cdNm',
                    panelHeight:'auto',
                    panelMaxHeight:200,
                    selectOnNavigation:true,editable:false,
                    onSelect:salesSelect,required:true,  
                    width:175
            ">
					</div>
					<div class="fitem">
						商铺名称: <input class="easyui-combobox" id="editShop" name="shopId"
							data-options="
                    url:'',
                    method:'get',
                    valueField:'idKey',
                    textField:'name',
                    panelHeight:'auto',required:true,
                    panelMaxHeight:200,editable:false,  
                    width:175
            ">
					</div>
					<div class="fitem">
						商品类目:
						<tag:categorySelectTags1 id="goodsTypeId2" name="goodsTypeId"
							communityId="" width="175" editable="false" required="true"></tag:categorySelectTags1>
					</div>
					<div class="fitem">
						<input name="idKey" type="hidden"><input id="simages"
							name="simages" type="hidden"> <input id="limages"
							name="limages" type="hidden"> <input id="showSimages"
							name="showSimages" type="hidden"> <input id="showLimages"
							name="showLimages" type="hidden"> 商品名称: <input
							name="goodsName" class="easyui-textbox"
							data-options="required:true">
					</div>


					<div class="fitem">
						商品原价: <input name="unitPrice" class="easyui-numberbox"
							data-options="required:true,min:0.01,max:99999,precision:2">
					</div>
					<div class="fitem">
						商品现价: <input name="discountPrice" class="easyui-numberbox"
							data-options="required:true,min:0.01,max:99999,precision:2">
					</div>
					<div class="fitem">
						商品库存: <input name="quantity" class="easyui-numberbox"
							data-options="required:true,min:0,max:99999,precision:0">
					</div>
					<div class="fitem" style="display: none">
						商品状态: <select id="editGoodsStatus" name="goodsStatus"
							class="easyui-combobox"
							data-options="required:true,panelHeight:'auto',panelMaxHeight:200">
							<option value='0'>未审核</option>
							<option value='1'>未上架</option>
							<option value='2'>销售中</option>
							<option value='3'>被管理员下架</option>
							<option value='6'>活动商品上架</option>
							<option value='7'>活动商品定时上架</option>
						</select>
					</div>
					<div class="fitem">
						联系电话: <input name="telephone" class="easyui-textbox"
							data-options="required:true">
					</div>
					<div class="fitem">
						商品地址: <input name="goodsAddr" class="easyui-textbox"
							data-options="required:true">
					</div>
					<div class="fitem" style="vertical-align: top">
						商品描述: <input class="easyui-textbox" name="goodsNote"
							data-options="multiline:true,missingMessage:'该选项不能为空',validType:'length[0,500]',missage:'发布信息请在500个字以内'"
							style="height: 90px">
					</div>
				</div>
				<div style="float: left;">
					<div class="fitem">商品图片:</div>
					<img id="goodsImages" src="" width='200px' height="160px">
					<div class="fitem">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="plain:true,iconCls:'icon-reload'"
							onclick="addImage()">更改图片</a>
					</div>
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" onclick="checkSave()">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div style="display: none;">
			<form id="fm1" method="post">
				<input name="deleteIds" id="deleteIds"> <input name="status"
					id="goodsStatus"><input name="autoPutOnTime"
					id="autoPutOnTime">
			</form>

		</div>
	</div>
	<!-- 上传图片form -->
	<div id="dl" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#dlg-buttons'"
		style="width: 300px; height: 158px; padding: 20px 10px">
		<form id="uploadFm" method="post" novalidate>
			<div class="fitem">
				<label style="width: 60px">上传图片:</label> <input type="file"
					id="upFile" name="upFile" class="easyui-validatebox"
					data-options="required:true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="uploadImage()">上传</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dl').dialog('close');flag=0;">取消</a>
	</div>
	<div id="chooseTime" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 280px; height: 170px; padding: 20px 10px"
		buttons="#chooseTime-buttons">
		<form id="chooseTimeF" method="post" novalidate>
			<div class="fitem">
				立即上架:&nbsp;&nbsp;&nbsp; 是&nbsp;<input type="radio" name="forever"
					value="6" checked="checked" onclick="checkForever()">&nbsp;&nbsp;否&nbsp;<input
					type="radio" name="forever" value="7" onclick="checkForever()">
			</div>
			<div class="fitem" id="autoPutOnTimeDiv" style="display: none">
				自动上架: <input class="easyui-datetimebox" id="activeTime"
					data-options="validType:'dateTimeCheck',showSeconds:false"
					style="width: 150px;" />
			</div>
		</form>
	</div>
	<div id="chooseTime-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="operateMulti('active')">选择</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#chooseTime').dialog('close');">取消</a>
	</div>
</body>
</html>