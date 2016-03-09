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
</style>
<script type="text/javascript">
	var postUrl = "";
	var imgId = 1;
	var rownum = 0;
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizNote/listPage',
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
			//onClickRow : clickRow,
			onLoadSuccess : loadSuccess,
			idField : 'idKey',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'orderId',
				title : '排序号',
				width : 60,
				halign : 'center',
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'noteTitle',
				title : '帖子标题',
				align : 'left',
				width : 90
			}, {
				field : 'noteTypeName',
				title : '帖子类型',
				align : 'center',
				width : 90
			}, {
				field : 'noteContent',
				title : '帖子内容',
				align : 'left',
				width : 200
			}, {
				field : 'userName',
				title : '发布人',
				align : 'center',
				width : 90
			}, {
				field : 'communityName',
				title : '所属小区',
				align : 'center',
				width : 90
			}, {
				field : 'publishDt',
				title : '发布日期',
				align : 'center',
				width : 100
			}, {
				field : 'lastReplyDt',
				title : '最后回复时间',
				align : 'center',
				width : 100
			}, {
				field : 'replyCount',
				title : '回复数',
				align : 'right',
				width : 60
			}, {
				field : 'clicksCount',
				title : '点击数',
				align : 'right',
				width : 60
			},

			{
				field : 'isTop',
				title : '是否置顶',
				align : 'center',
				width : 60,
				hidden : true
			}, {
				field : 'isElite',
				title : '是否精华',
				align : 'center',
				width : 60,
				hidden : true
			}, {
				field : 'noReply',
				title : '是否禁止回复',
				align : 'center',
				width : 60,
				hidden : true
			}, ] ]
		});
		$('#bodyLayout').layout('collapse', 'east');
		mainPageView();
		setInitDate();
	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
		$("#toolbar2").hide();
	}
	//设置初始日期
	function setInitDate() {
		$('#startDatetime').datebox('setValue', getStartDatetime());
		$('#endDatetime').datebox('setValue', getEndDatetime());
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
			$('#dataList').datagrid('selectRow', rownum);
		} else {
			$('#fm').hide();
		}
	}
	/* 删除一条记录  */
	function deleteOne() {
		var row = $('#dataList').datagrid('getSelected');
		if (!row) {
			$.messager.alert('提示信息', "请选中需要删除的帖子。", 'error');
			return;
		}
		$.messager
				.confirm(
						'确认',
						'确定要删除选中帖子吗?',
						function(r) {
							if (!r) {
								return;
							}
							var json = {
								url : "${base}/business/BizNote/delete/"
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
	//删除多条
	function deleteMulti() {
		var checkedIds = [];
		var checkedItems = $('#dataList').datagrid('getChecked');
		if (checkedItems.length == 0) {
			$.messager.alert('提示信息', "请选中需要删除的商品。", 'error');
			return;
		}
		$.each(checkedItems, function(index, item) {
			checkedIds.push(item.idKey);
		});
		var deleteIds = checkedIds.join(",");
		$("#deleteIds").val(deleteIds);
		//console.log( $("#deleteIds").val());
		$.messager
				.confirm(
						'确认',
						'确定要删除选中的 ' + checkedItems.length + ' 条帖子吗?',
						function(r) {
							if (!r)
								return;
							var json = {
								url : "${base}/business/BizNote/deleteMulti",
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
		postUrl = "${base}/business/BizNote/insert";
	}
	/* 编辑一条记录 */
	function view() {
		var row = $('#dataList').datagrid('getSelected');
		if (row) {
			$('#fm').form('load', row);
			generateImageEdit(row.imagesUrl, row.linksUrl);
			editFormEnable(false);
			postUrl = '${base}/business/BizNote/update';
		} else {
			$.messager.alert('提示信息', "请选中要编辑的帖子", 'error');
		}
	}
	/* 保存数据 */
	function save() {
		if (!$('#fm').form('validate')) {
			return;
		}
		if ("" == $("#imagesUrl").val()) {
			$.messager.alert('提示信息', "请添加图片！", "error");
			return;
		}
		var json = {
			url : postUrl,
			formId : "fm",
			maskId : "ff",
			successJSFun : function(result) {
				if (result.flag) {
					showMask(result.successMsg, "fm");
					setTimeout(
							"hideMask(); $('#dataList').datagrid('reload'); editFormEnable(false);",
							DEFAULT_DELAY_MS);
				} else {
					hideMask();
					$.messager.alert('提示信息', result.successMsg, "error");
				}
			}
		};
		ajaxSubmitForm(json);
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
			url : "${base}/business/BizNote/uploadFile?link="
					+ $("#imageUrl").val(),
			fileElementId : "upFile",
			maskId : "dlg",
			success : function(result) {
				var res = eval("(" + result + ")");
				if (res.successMsg) {
					showMask(res.successMsg);
					setTimeout(
							"hideMask(); $('#dlg').dialog('close'); addImagesTr('"
									+ res.addr + "','" + res.link + "');",
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
	//添加一个图片行
	function addImagesTr(image_addr, image_link) {
		var newRow = "<tr>\n"
				+ "<td class=\"tdLeft\" style=\"padding: 3px\">\n"
				+ "<a href=\"javascript:void(0)\" class=\"adv-linkbutton\" onclick=\"removeImage(this,'"
				+ image_addr
				+ "','"
				+ image_link
				+ "')\">删除本图</a>\n"
				+ "</td>\n"
				+ "<td class=\"tdLeft\" style=\"padding: 3px\">\n"
				+ "<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+image_addr+"\" width=\"300px\" height=\"50px\">\n"
				+ "	<br><a href=\""+image_link+"\" target=\"_blank\">"
				+ image_link + "</a>\n" + "</td>\n" + "</tr>\n";
		$("#tableAdd tr:last").after(newRow);
		$("#imagesUrl").val($("#imagesUrl").val() + image_addr + "@@");
		$("#linksUrl").val($("#linksUrl").val() + image_link + "@@");
		showTip("adv_img_" + imgId, "${base}/" + image_addr);
		imgId++;
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
								url : "${base}/business/BizNote/deleteImage?imageAddr="
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
	//删除行
	function removeTr(rowInd, image_addr, image_link) {
		$("#tableAdd tr").eq(rowInd).remove();
		var imgurl = $("#imagesUrl").val();
		var linkurl = $("#linksUrl").val();
		$("#imagesUrl").val(
				imgurl.substring(0, imgurl.indexOf(image_addr))
						+ imgurl.substring(imgurl.indexOf(image_addr)
								+ image_addr.length + 2));
		$("#linksUrl").val(
				linkurl.substring(0, linkurl.indexOf(image_link))
						+ linkurl.substring(linkurl.indexOf(image_link)
								+ image_link.length + 2));
		save();
	}
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
		<!-- 上部按钮层 -->
		<div
			data-options="region:'north', title:'&nbsp;查询条件',closedTitle:'查询条件(点击展开)'"
			style="height: 96px; padding-top: 6px;">
			<form id="queryForm" method="post">
				<table>
					<tr>
						<td colspan="9"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-remove" plain="true"
							onclick="deleteMulti()">删除</a></td>
					</tr>
					<tr>
						<td style="width: 75px" class="tdRight">查询条件：</td>
						<td style="width: 55px">发布日期:</td>
						<td><input class="easyui-datebox" style="width: 100px"
							name="startDatetime" id="startDatetime"> &nbsp;至&nbsp; <input
							class="easyui-datebox" style="width: 100px" name="endDatetime"
							id="endDatetime"></td>
						<td style="padding-left: 10px">所在小区： <tag:popedomCommunitySelect
								id="community" width="120" name="communityId"
								editable="true"></tag:popedomCommunitySelect>
						</td>
						<td style="width: 100px" class="tdRight"><a
							href="javascript:void(0)" id="searchButton"
							class="easyui-linkbutton" iconCls="icon-search"
							onclick="queryDatas()">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;帖子列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
		<div style="display: none;">
			<form id="fm1" method="post">
				<input name="deleteIds" id="deleteIds">
			</form>
		</div>
	</div>
</body>
</html>