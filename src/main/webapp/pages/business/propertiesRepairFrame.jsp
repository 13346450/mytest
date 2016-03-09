<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/tags/taglibs.jsp"%>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<script src="${base}/resources/javascripts/ajaxfileupload.js" ></script>
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

.datagrid-header-rownumber{
    width:48px;
}
.datagrid-cell-rownumber{
    width:48px;
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
			url : '${base}/business/BizPropertiesRepair/listPage',
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
			fitColumns:false,
			onDblClickRow : dblClickRow,
		 	onLoadSuccess :loadSuccess,
		 	idField : 'idKey',
		 	frozenColumns:[[
		 	             {field:'ck',checkbox:true}
		 	             //{field:'advVersion', title:'版本号',  width:60,  halign:'center', sortable:true}
		 	           ]],
			columns : [[
						{field:'parentId', title:'父节点', hidden:true},
			            {field:'title', title:'申告信息标题', align:'left', width:100,formatter: function(value,row,index){
							if(row.parentId != -1)
								return "&nbsp;&nbsp;" + value;
							else
								return value;
						},styler: function(index,row){
							if (row.parentId != -1){
								return "color:blue";
							}
						}},
			            {field:'typeNames', title:'申告类型', align:'center', width:85},
			            {field:'content', title:'申告描述', align:'left', width:180},
			            {field:'contacts', title:'申告人姓名', align:'center', width:85},
			            {field:'contactsTel', title:'联系方式',align:'center', width:85},
			            {field:'contactsAddr', title:'联系地址', align:'center', width:85, sortable:true},
			            {field:'userBuilding', title:'用户楼栋', align:'center', width:85, sortable:true},
			            {field:'userUnit', title:'用户单元', align:'center', width:85, sortable:true},
			            {field:'userRoom', title:'用户房号', align:'center', width:85, sortable:true},
			            {field:'userFloor', title:'用户楼层', hidden:true},
			            {field:'publishDt', title:'发布日期', align:'center', width:85, sortable:true},
			            {field:'communityName', title:'小区名称', align:'center', width:85, sortable:true},
			            {field:'replyContent', title:'回复内容', align:'center', width:100, sortable:true},
			            {field:'replyDt', title:'回复时间', align:'center', width:100, sortable:true},
			            {field:'repairStatus', title:'状态',  align:'center', hidden:true},
			            {field:'imageString', title:'图片字串集合', hidden:true},
 			            {field:'statusName', title:'状态',  align:'center', width:70,styler: function(index,row){
							if (row.repairStatus != 2){
								return "color:red";
							}
						}}
			          ]]
		});
		mainPageView();
		setInitDate();
		$('#bodyLayout').layout('collapse','east');
	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
		$("#toolbar2").hide();
	}
	//设置初始日期
	function setInitDate(){
		$('#startDatetime').datebox('setValue', getStartDatetime());
		$('#endDatetime').datebox('setValue', getEndDatetime());
	}
	function dblClickRow(rowIndex, rowData) 
	{
		//rownum = rowIndex;
		edit();
	}
	 /*表格加载成功后调用的方法 */
     function loadSuccess(){
    	 var rows = $('#dataList').datagrid('getRows');
    	 if(rows.length > 0){
	    	var row = rows[0];
	    	$('#dataList').datagrid('clearChecked');
    	 } 
    }
	 /* 删除一条记录  */
     function deleteOne(){
         var row = $('#dataList').datagrid('getSelected');
         alert(row.idKey);
         if(!row){
         	$.messager.alert('提示信息',"请选中需要删除的信息。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要删除选中的信息吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizPropertiesRepair/delete/" + row.idKey,
 	    		    success: function(result){
 		     	    	if (result.successMsg){
 		     	    		showMask(result.successMsg);
 		     	    		rownum = 0;
 		        	    	setTimeout("hideMask(); $('#dataList').datagrid('reload'); ", DEFAULT_DELAY_MS);
 	                    } else {
 	                    	hideMask();
 	                    	$.messager.alert('错误信息',result.errorMsg,'error');
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
 		if(checkedItems.length==0){
 			$.messager.alert('提示信息', "请选中需要删除的物业报修信息。", 'error');
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
 						'确定要删除选中的 ' + checkedItems.length + ' 个物业报修信息吗?',
 						function(r) {
 							if (!r)
 								return;
 							var json = {
 								url : "${base}/business/BizPropertiesRepair/deleteMulti",
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
 	function add(){
 		$('#dlg').dialog('open').dialog('setTitle','新建信息');
        $('#fm').form('clear');
		postUrl = "${base}/business/BizPropertiesRepair/insert";
 	}
 	/* 编辑一条记录 */
 	function edit()
 	{
 		var row = $('#dataList').datagrid('getSelected');
 		if(row.replyDt != null)
		{
 			$('#saveButton').linkbutton("disable");
 			$('#replyContent').val(row.replyContent);
		}
 		else
		{
 			$('#saveButton').linkbutton("enable");
		}
 		setContent(row);
        if (row){
        	$('#dlg').dialog('open').dialog('setTitle', '回复信息');
			$('#fm').form('load', row);
			$("#imageLists").empty();
			generateImageEdit(row.imageString);
            postUrl = '${base}/business/BizPropertiesRepair/update';
        } else {
			$.messager.alert('提示信息', "一次回复只能选择一条物业报修信息", 'error');
			
		}
 	}
 	function setContent(rowData)
 	{
 		var title = rowData.title==null?"":rowData.title;
 		var contacts = rowData.contacts==null?"":rowData.contacts;
 		var publishDt = rowData.publishDt==null?"":rowData.publishDt;
 		var communityName = rowData.communityName==null?"":rowData.communityName;
 		var typeNames = rowData.typeNames==null?"":rowData.typeNames;
 		var contactsTel = rowData.contactsTel==null?"":rowData.contactsTel;
 		var statusName = rowData.statusName == null?"":rowData.statusName;
 		var content = rowData.content==null?"":rowData.content;
 		var userBuilding = rowData.userBuilding == null?"":rowData.userBuilding;
 		var userUnit = rowData.userUnit == null?"":rowData.userUnit;
 		var userRoom = rowData.userRoom == null?"":rowData.userRoom;
 		var userFloor = rowData.userFloor == null?"":rowData.userFloor;
 		
 		
		var str = "&nbsp;&nbsp;&nbsp;标题：" + title + "<br \>&nbsp;&nbsp;&nbsp;联系人：" + contacts  + "<br \>&nbsp;&nbsp;&nbsp;所在小区：" + communityName + "<br \>&nbsp;&nbsp;&nbsp;用户楼栋：" + userBuilding;
		var str1 = "类型：" + typeNames + "<br \>联系电话：" + contactsTel + "<br \>发布时间：" + publishDt + "<br \>用户单元：" + userUnit;
		var str2 = "<br \><br \>&nbsp;&nbsp;&nbsp;房号：" + userFloor + "层 " + userRoom + "号" + "<br \>&nbsp;&nbsp;&nbsp;正文：" + content + "<br /><br />&nbsp;&nbsp;&nbsp;图片列表："
		document.getElementById('infoContents1').innerHTML = str;
		document.getElementById('infoContents2').innerHTML = str1;
		document.getElementById('infoContents3').innerHTML = str2;
	}
	/* 更改输入的格式设置 */
	function change() {
		var content = $('#infoContent').val();
		content = content.replace(/\n/g, '<br/>');
		content = content.replace(/\s/g, '&nbsp;&nbsp;');
		$('#infoContent').val(content);
	}
	/* 保存数据 */
	function save() {
		if (!$('#fm').form('validate')) {
			return;
		}
		//cxl start
		var replyContent = $("#fm").find("[name='replyContent']").val();
		replyContent = $.trim(replyContent);
		if(replyContent==""){
			alert("回复不能为空");
			return;
		}
		//cxl end
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
			setTimeout(
					"hideMask(); $('#dlg').dialog('close');$('#dataList').datagrid('reload');",
					DEFAULT_DELAY_MS);
		} else {
			hideMask();
			$.messager.alert('提示信息', result.successMsg, "error");
		}
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
			url : "${base}/business/BizPropertiesRepair/uploadFile?link="
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
	function generateImageEdit(imagesUrl) {
		if (imagesUrl == "" || imagesUrl == null) {
			return;
		}
		var imgarr = imagesUrl.split("@@");
		for (var i = 0; i < imgarr.length; i++) {
			var rows = "<div style=\"float:left; width:100px; height:100px;\">"
					+ "<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+imgarr[i]+"\" width=\"200px\" height=\"150px\"/>\n"
					+ "</div>";
			$("#imageLists").append(rows);
			imgId++;
		}
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
								url : "${base}/business/BizPropertiesRepair/deleteImage?imageAddr="
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
	$.extend($.fn.validatebox.defaults.rules, {
		faceNumber : {
			validator: function(value, param){
	        	var reg = /(^\d{1,3}$)/;  
	        	       if( reg.test(value)){
	        	    	   return value>=65&&value<=120;
	        	       }  
	        },
	        message: '请输入65~120范围的数字！'
		}
	});
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
		<!-- 上部按钮层 -->
		<div data-options="region:'north', title:'&nbsp;查询条件',closedTitle:'查询条件(点击展开)'"
			style="height: 96px; padding-top: 6px;">
				<form id="queryForm" method="post">
					<table>
						<tr>
							<td colspan="9">
<!-- 									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">增加</a> -->
							    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">回复</a> 
							    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMulti()">删除</a>
							</td>
						</tr>
						<tr>
							<td style="width:75px" class="tdRight">查询条件： </td>
							<td style="width:55px">制单日期: </td>
							<td><input class="easyui-datebox" style="width: 100px"
								name="startDatetime" id="startDatetime">
								&nbsp;至&nbsp;
								<input class="easyui-datebox" style="width: 100px"
								name="endDatetime" id="endDatetime"></td>
							<td style="width:50px" class="tdRight">状态</td>
							<td>
								<select class="easyui-combobox" name="repairStatus"  data-options="panelHeight:'auto'" style="width:80px">
									<option value="-1">全选</option>
									<option value="1">未处理</option>
									<option value="2">已处理</option>
								</select>
							</td>
							<td style="padding-left: 10px">所在小区： 
								 <tag:popedomCommunitySelect
								id="communityId" width="120" name="communityId"
								editable="true"></tag:popedomCommunitySelect>
							</td>
							<td  style="width:100px" class="tdRight">
								<a href="javascript:void(0)" id="searchButton" class="easyui-linkbutton" iconCls="icon-search" onclick="queryDatas()">查询</a>
							</td>
						</tr>
					</table>
				</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;信息列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
		<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-edit'" 
				style="width:600px;height:550px;padding:10px 20px" buttons="#dlg-buttons1">
			<div style="width:560px;height:150px;">
				<div style="float:left;width:280px;height:50px;">
    				<p id="infoContents1" style="font-size:14px;"> </p>
    			</div>
    			<div style="float:left;width:280px;height:50px;">
    				<p id="infoContents2" style="font-size:14px;"> </p>
    			</div>
    			<div style="float:left;width:500px;height:50px;">
    				<p id="infoContents3" style="font-size:14px;"> </p>
    			</div>
			</div>
			<div id="imageLists" style="width:400px;height:150px;">
			</div>
	    	<form id="fm" method="post" novalidate>	
	    		<div class="fitem">
	    			<hr /> <br />
   					<input name="idKey" type="hidden">
    				<label>回复内容:</label>
    				<input id="replyContent" name="replyContent" class="easyui-textbox" data-options="multiline:true"  required="true" style="width:400px;height:100px">
	    		</div>				
	    	</form>
	            
		<!-- 上传图片form -->	
		<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-save'" 
			style="width:380px;height:178px;padding:20px 10px" buttons="#dlg-buttons">
		    <form id="uploadFm" method="post" novalidate>
		        <div class="fitem">
		            <label style="width:60px">上传图片:</label>
		            <input type="file" id="upFile" name="upFile" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label style="width:60px">链接地址:</label>
					<input type="text" id="imageUrl" name="imageUrl" style="width: 220px" placeholder="http://">
				</div>
		    </form>
		</div>
		<!-- 按钮 -->
		<div id="dlg-buttons">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadImage()">上传</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div id="dlg-buttons1">
	        <a href="javascript:void(0)" id="saveButton" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	    </div>
	    <div style="display: none;">
			<form id="fm1" method="post">
				<input name="deleteIds" id="deleteIds">
			</form>
		</div>
	</div>
</div>
</body>
</html>