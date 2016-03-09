<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
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

.adv-linkbutton {
	font-size: 12px;
}

.imageTd {
	width: 200px;
}

.textTd {
	width: 100px;
}
</style>
<script type="text/javascript">
	var changeId;
	var dictKey, categoryId, communityId;
	var postUrl = "";
	var imgId = 1;
	var rownum = 0;
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizCategory/listPage',
			queryParams : formToJson("queryForm"),
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
			onClickRow : clickRow,
			onLoadSuccess : loadSuccess,
			idField : 'idKey',
			frozenColumns : [ [
			/* {field:'ck',checkbox:true}, */
			{
				field : 'communityId',
				title : '小区id',
				width : 120,
				halign : 'center',
				sortable : true,
				hidden : true
			}, {
				field : 'parentId',
				title : '小区id',
				width : 120,
				halign : 'center',
				sortable : true,
				hidden : true
			},
			{
				field : 'communityName',
				title : '小区名称',
				width : 120,
				halign : 'center',
				sortable : true
			}, {
				field : 'categoryName',
				title : '广告位置',
				width : 120,
				halign : 'center',
				sortable : true,
				formatter : function(value, row, index) {
					if (value == '云平台类目') {
						return '首页广告';
					} else {
						return value + '广告';
					}
				}
			} ] ],
			columns : [ [

			] ]
		});
		$('#bodyLayout').layout('collapse', 'east');
		mainPageView();
	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
		$("#toolbar2").hide();
	}

	function clickRow(rowIndex, rowData) {
		rownum = rowIndex;
		view();
		if (!$('#bodyLayout').layout('panel', 'north').panel("options").collapsed) {//折叠north面板
			$('#bodyLayout').layout('collapse', 'north');
		}
		;
		if ($('#bodyLayout').layout('panel', 'east').panel("options").collapsed) {//展开east面板
			$('#bodyLayout').layout('expand', 'east');
		}
	}
	/*表格加载成功后调用的方法 */
	function loadSuccess() {
		var rows = $('#dataList').datagrid('getRows');
		if (rows.length > 0) {
			$('#dataList').datagrid('clearChecked');
			$('#dataList').datagrid('clearSelections');
		} else {
			$('#fm').hide();
		}
	}

	/* 点击一条记录 */
	function view() {
		var row = $('#dataList').datagrid('getSelected');
		if (row) {
			//对类别id做设置
			dictKey = 60;
			if (row.parentId == -1) {
				dictKey = 59;
			}
			communityId = row.communityId;
			categoryId = row.idKey;
			$.ajax({
				type : "get",
				url : "${base}/business/BizAdImage/queryByType/" + categoryId
						+ "/" + communityId,
				datatype : "json",
				success : function(data) {
					var myobj = eval(data);
					$('#tableAdd').empty();
					for (var i = 0; i < myobj.length; i++) {
						addRow(myobj[i]);
					}
				}
			});
		} else {
			$.messager.alert('提示信息', "请选中要编辑的广告", 'error');
		}
	}
	//增加一张图片
	function addRow(adImage) {
		imageTd = '<img width="200px" height="100px" style="margin-top:0px;" id="image'
				+ adImage.idKey
				+ '" alt="双击查看大图" src="${base}/'
				+ adImage.imageUrl
				+ '" ondblclick="javascript:showImage(this)">';
		var newRow = '<tr ><td class="imageTd"><div style="border: 1px solid #BEBEBE;width:200px;height:100px;">'
				+ imageTd
				+ '</div> </td><td class="textTd" id="'+adImage.idKey+'Td"><a href="javascript:void(0)" onclick=del(this) id="text'
				+ adImage.idKey + '">删除图片</a></td></tr>';
		$('#tableAdd').append(newRow);
	}
	//删除图片
	function del(obj) {
		changeId = obj.id.substring(4);
		$.messager.confirm('确认', '确定要删除选中的广告吗?', function(r) {
			if (!r) {
				return;
			}
			showMask("正在处理……");
			var json = {
				url : "${base}/business/BizAdImage/delete/" + changeId,
				success : function(result) {
					if (result.successMsg) {
						setTimeout("hideMask(); view(); ", DEFAULT_DELAY_MS);
					} else {
						hideMask();
						$.messager.alert('错误信息', result.errorMsg, 'error');
					}
				}
			};
			ajax(json);
		});
	}
	/* 删除一条记录  */
	function deleteOne() {
		var row = $('#dataList').datagrid('getSelected');
		if (!row) {
			$.messager.alert('提示信息', "请选中需要删除的广告。", 'error');
			return;
		}
		$.messager
				.confirm(
						'确认',
						'确定要删除选中的广告吗?',
						function(r) {
							if (!r) {
								return;
							}
							var json = {
								url : "${base}/business/BizAdImage/delete/"
										+ row.idKey,
								success : function(result) {
									if (result.successMsg) {
										showMask(result.successMsg);
										rownum = 0;
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
							ajax(json);
						});
	}
	/* 新增一条记录 */
	function add() {
		$('#fm').show();
		if ($("#tableAdd").find("tr").length > 1) {
			$("#tableAdd tr:not(:first)").empty();
		}
		$("#idKey").val("");
		$("#imagesUrl").val("");
		$("#linksUrl").val("");
		$("#advVersion").val("");
		editFormEnable(true);
		$('#fm').form('enableValidation');
		postUrl = "${base}/business/BizAdImage/insert";
	}

	function cancel() {
		editFormEnable(false);
	}
	//设置表单是否可编辑
	function editFormEnable(bool) {
		bindDatagrid("dataList", !bool);
		if (bool) {
			$("#toolbar1").hide();
			$("#toolbar2").show();
			$("#searchButton").linkbutton('disable');
			$("a.adv-linkbutton").show();
		} else {
			$("#toolbar1").show();
			$("#toolbar2").hide();
			$("#searchButton").linkbutton();
			$("a.adv-linkbutton").hide();
			if ($("#auditId").val() == "") {
				$("#modifyButton").linkbutton("enable");
				$("#deleteButton").linkbutton("enable");
			} else {
				$("#modifyButton").linkbutton("disable");
				$("#deleteButton").linkbutton("disable");
			}
		}
	}
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
	function addImage() {
		$('#dlg').dialog('open').dialog('setTitle', '上传图片');
		$('#uploadFm').form('clear');
	}
	// 上传图片
	function uploadImage() {
		if ($("#imageUrl").val() == "") {
			$.messager.alert('错误信息', "链接地址不能为空", 'error');
			return;
		}
		if ($("#upFile").val() == "") {
			$.messager.alert('错误信息', "上传文件不能为空", 'error');
			return;
		}
		var json = {
			url : "${base}/business/BizAdImage/insert?dictKey=" + dictKey
					+ '&categoryId=' + categoryId + '&communityId='
					+ communityId,
			fileElementId : "upFile",
			maskId : "dlg",
			success : function(result) {
				var res = eval("(" + result + ")");
				if (res.successMsg) {
					showMask(res.successMsg);
					setTimeout(
							"hideMask(); $('#dlg').dialog('close'); view();",
							DEFAULT_DELAY_MS);
				} else {
					hideMask();
					$.messager.alert('错误信息', res.errorMsg, 'error');
				}
			}
		};
		ajaxFileUpload(json);
	}
	//显示tip
	function showTip(image_id, image_url) {
		$('#' + image_id).tooltip({
			position : 'left',
			content : '<img src="'+image_url+'">',
			onShow : function() {
				$(this).tooltip('tip').css({
					backgroundColor : '#CCCCCC',
					borderColor : '#666666'
				});
			}
		});
	}
	//点击行时，显示图片列表
	function generateImageEdit(imagesUrl, linksUrl) {
		var imgarr = imagesUrl.split("@@");
		var linkarr = linksUrl.split("@@");
		if ($("#tableAdd").find("tr").length > 1) {
			$("#tableAdd tr:not(:first)").empty();
		}
		if (imagesUrl == "") {
			return;
		}
		for (var i = 0; i < imgarr.length; i++) {
			var newRow = "<tr>\n"
					+ "<td class=\"tdLeft\" style=\"padding: 3px\">\n"
					+ "<a href=\"javascript:void(0)\" class=\"adv-linkbutton\" onclick=\"removeImage(this,'"
					+ imgarr[i]
					+ "','"
					+ linkarr[i]
					+ "')\">删除本图</a>\n"
					+ "</td>\n"
					+ "<td class=\"tdLeft\" style=\"padding: 3px\">\n"
					+ "<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+imgarr[i]+"\" width=\"300px\" height=\"50px\"/>"
					+ "<br><a href=\""+linkarr[i]+"\" target=\"_blank\">"
					+ linkarr[i] + "</a>\n" + "</td>\n" + "</tr>";
			$("#tableAdd tr:last").after(newRow);
			showTip("adv_img_" + imgId, "${base}/" + imgarr[i]);
			imgId++;
		}
		$("#imagesUrl").val(imagesUrl + "@@");
		$("#linksUrl").val(linksUrl + "@@");
	}

	//删除一个图片行
	function removeImage(obj, image_addr, image_link) {
		$.messager
				.confirm(
						'确认',
						'删除图片后无法恢复，确定吗?',
						function(r) {
							if (!r) {
								return;
							}
							var json = {
								url : "${base}/business/BizAdImage/deleteImage?imageAddr="
										+ image_addr,
								success : function(result) {
									if (result.successMsg) {
										showMask(result.successMsg);
										setTimeout(
												"hideMask(); $('#dataList').datagrid('reload'); removeTr("
														+ obj.parentNode.parentNode.rowIndex
														+ ",'" + image_addr
														+ "','" + image_link
														+ "');",
												DEFAULT_DELAY_MS);
									} else {
										hideMask();
										$.messager.alert('错误信息',
												result.errorMsg, 'error');
									}
								}
							};
							ajax(json);
						});
	}
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
		<!-- 上部按钮层 -->
		<div
			data-options="region:'north', title:'&nbsp;查询条件',closedTitle:'查询条件(点击展开)'"
			style="height: 70px; padding-top: 6px;">
			<form id="queryForm" method="post">
				<table>
					<tr>
						<td style="padding-left: 10px">所在小区： <tag:popedomCommunitySelect id="communityId" name="communityId"></tag:popedomCommunitySelect>
						</td>
						<!-- <td style="width:55px">制单日期: </td>
							<td><input class="easyui-datebox" style="width: 100px"
								name="startDatetime" id="startDatetime">
								&nbsp;至&nbsp;
								<input class="easyui-datebox" style="width: 100px"
								name="endDatetime" id="endDatetime"></td>
							<td style="width:50px" class="tdRight">状态</td>
							<td>
								<select class="easyui-combobox" name="advStatus"  data-options="panelHeight:'auto'" style="width:80px">
									<option value="-1">全选</option>
									<option value="0">未审核</option>
									<option value="1">已审核</option>
									<option value="2">已发布</option>
								</select>
							</td> -->

						<td style="width: 100px" class="tdRight">&nbsp;&nbsp;<a
							href="javascript:void(0)" id="searchButton"
							class="easyui-linkbutton" iconCls="icon-search"
							onclick="queryDatas()">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;广告列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
		<div
			data-options="region:'east',title:'广告详细信息',split:false, collapsed:true"
			style="width: 450px;">
			<div id="ff" class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'">
					<form id="fm" method="post" novalidate>
						<table class="tableDetail" id="tableAdd"">
							<!-- <tr>
								<td class="tdLeft" style="width: 50px;padding: 3px">版本号:</td>
								<td class="tdLeft" style="padding: 3px">
									<input name="idKey" id="idKey" type="hidden">
									<input name="imagesUrl" id="imagesUrl" type="hidden">
									<input name="linksUrl" id="linksUrl" type="hidden">
									<input name="auditId" id="auditId" type="hidden">
									<input name="advStatus" type="hidden">
									<input name="advVersion" id="advVersion" class="easyui-numberbox" required="true" 
										min="1.0" precision="1" missingMessage="必须填写1.0以上的数字" >
								</td>
							</tr> -->
						</table>
						<div style="margin-top: 8px; margin-left: 10px">
							<a href="javascript:void(0)" class="adv-linkbutton"
								onclick="addImage()">添加图片</a>
						</div>
					</form>
				</div>
			</div>
			<!-- 上传图片form -->
			<div id="dlg" class="easyui-dialog"
				data-options="modal:true,closed:true,iconCls:'icon-save'"
				style="width: 380px; height: 178px; padding: 20px 10px"
				buttons="#dlg-buttons">
				<form id="uploadFm" method="post" novalidate>
					<div class="fitem">
						<label style="width: 60px">上传图片:</label> <input type="file"
							id="upFile" name="upFile" class="easyui-validatebox"
							required="true">
					</div>
					<!-- <div class="fitem">
					<label style="width:60px">链接地址:</label>
					<input type="text" id="imageUrl" name="imageUrl" style="width: 220px" placeholder="http://">
				</div> -->
				</form>
			</div>
			<!-- 按钮 -->
			<div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick="uploadImage()">上传</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel"
					onclick="javascript:$('#dlg').dialog('close')">取消</a>
			</div>
		</div>
	</div>
</body>
</html>