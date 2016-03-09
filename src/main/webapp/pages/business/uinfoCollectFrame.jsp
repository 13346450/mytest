<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
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
</style>
<script type="text/javascript">
	var postUrl = "";
	var imgId = 1;
	$(function() {

		$('#dataList').datagrid({
			url : '${base}/business/BizUinfoCollect/listPage',
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
		 	            /* {field:'rcSn', title:'设备序号',  width:115,  halign:'center', sortable:true},*/
		 	           ]],
			columns : [[
						{field:'parentId', title:'父节点', hidden:true},
			            {field:'title', title:'标题', width:100,align:'left',formatter: function(value,row,index){
							if(row.parentId != -1)
								return "&nbsp;&nbsp;" + value;
							else
								return value;
						},styler: function(index,row){
							if (row.parentId != -1){
								return "color:blue";
							}
						}},
			            {field:'userContent', title:'咨询建议信息', width:200,align:'left'},
			            {field:'publishDt', title:'发布日期', width:100,align:'center'},
			            {field:'communityName', title:'小区名', width:100,align:'center'},
			            {field:'linkMan', title:'联系人', width:100,align:'center'},
			            {field:'tel', title:'联系电话', width:100,align:'center'},
			            {field:'address', title:'联系地址', width:100,align:'center'},
			            {field:'userBuilding', title:'用户楼栋', align:'center', width:85, sortable:true},
			            {field:'userUnit', title:'用户单元', align:'center', width:85, sortable:true},
			            {field:'userRoom', title:'用户房号', align:'center', width:85, sortable:true},
			            {field:'userFloor', title:'用户楼层', hidden:true},
			            {field:'imageString', title:'图片字串集合', hidden:true},
			            {field:'replyContent', title:'回复内容', align:'center', width:100, sortable:true},
			            {field:'replyDt', title:'回复时间', align:'center', width:100, sortable:true},
			            {field:'replyStatus', title:'回复状态', hidden:true},
			            {field:'statusName', title:'状态', width:100,align:'center',styler: function(index,row){
							if (row.replyStatus != 2){
								return "color:red";
							}
						}}
			          ]] 
		});		
		mainPageView();
		setInitDate();
		$('#bodyLayout').layout('collapse','east');
	});
	//设置初始日期
	function setInitDate(){
		$('#startDatetime').datebox('setValue', getStartDatetime());
		$('#endDatetime').datebox('setValue', getEndDatetime());
	}
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用

	}
	
	function dblClickRow(rowIndex, rowData) {
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
         if(!row){
         	$.messager.alert('提示信息',"请选中需要删除的留言。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要删除选中的留言吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizUinfoCollect/delete/" + row.idKey,
 	    		    success: function(result){
 		     	    	if (result.successMsg){ 
 		     	    		showMask(result.successMsg);
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
  			$.messager.alert('提示信息', "请选中需要删除的留言信息。", 'error');
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
  						'确定要删除选中的 ' + checkedItems.length + ' 个留言信息吗?',
  						function(r) {
  							if (!r)
  								return;
  							var json = {
  								url : "${base}/business/BizUinfoCollect/deleteMulti",
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
 		$('#dlg').dialog('open').dialog('setTitle','增加设备');
        $('#fm').form('clear');
        postUrl = "${base}/business/BizUinfoCollect/insert";
 	}
 	/* 编辑一条记录 */
 	function edit(){
 		var row = $('#dataList').datagrid('getSelected');
 		if(row.replyDt != null)
		{
 			$('#saveButton').linkbutton("disable");
 			$('#userContent').val(row.userContent);
		}
 		else
		{
 			$('#saveButton').linkbutton("enable");
		}
 		setContent(row);
         if (row){
             $('#dlg').dialog('open').dialog('setTitle','回复信息');
             $('#fm').form('load',row);
 			 $("#imageLists").empty();
			 generateImageEdit(row.imageString);
 			 $("#userContent").textbox('setValue','');
			 $("#parentId").val(row.idKey);
             postUrl = '${base}/business/BizUinfoCollect/insert';
         }else{
         	$.messager.alert('提示信息',"请选中要编辑的信息",'error');
         }

 	}
	//点击行时，显示图片列表
	function generateImageEdit(imagesUrl) {
		if (imagesUrl == "" || imagesUrl == null) {
			return;
		}
		var imgarr = imagesUrl.split("@@");
		for (var i = 0; i < imgarr.length; i++) {
			var rows = "<div style=\"float:left; width:100px; height:100px;\">" + "<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+imgarr[i]+"\" width=\"200px\" height=\"150px\"/>\n" + "</div>";
			$("#imageLists").append(rows);
			imgId++;
		}
	}
 	function setContent(rowData)
 	{
 		var title = rowData.title==null?"":rowData.title;
 		var contacts = rowData.linkMan==null?"":rowData.linkMan;
 		var publishDt = rowData.publishDt==null?"":rowData.publishDt;
 		var communityName = rowData.communityName==null?"":rowData.communityName;
 		var tel = rowData.tel==null?"":rowData.tel;
 		var statusName = rowData.statusName == null?"":rowData.statusName;
 		var userContent = rowData.userContent==null?"":rowData.userContent;
 		var userBuilding = rowData.userBuilding == null?"":rowData.userBuilding;
 		var userUnit = rowData.userUnit == null?"":rowData.userUnit;
 		var userRoom = rowData.userRoom == null?"":rowData.userFloor;
 		var userFloor = rowData.userFloor == null?"":rowData.userFloor;
 				
		var str = "&nbsp;&nbsp;&nbsp;标题：" + title + "<br \>&nbsp;&nbsp;&nbsp;联系人：" + contacts + "<br \>&nbsp;&nbsp;&nbsp;发布日期：" + publishDt+ "<br \>&nbsp;&nbsp;&nbsp;用户楼栋：" + userBuilding;
		var str1 = "所在小区：" + communityName + "<br \>联系电话：" + tel + "<br \>状态：" + statusName + "<br \>用户单元：" + userUnit;
		var str2 = "<br \><br \>&nbsp;&nbsp;&nbsp;房号：" + userFloor + "层 " + userRoom + "号" + "<br \>&nbsp;&nbsp;&nbsp;正文：" + userContent + "<br /><br />&nbsp;&nbsp;&nbsp;图片列表："
		document.getElementById('infoContents1').innerHTML = str;
		document.getElementById('infoContents2').innerHTML = str1;
		document.getElementById('infoContents3').innerHTML = str2;
	}
 	/* 保存数据 */
	function save() {
		if(!$('#fm').form('validate')){
			return;
		}
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
	       	setTimeout("hideMask(); $('#dlg').dialog('close'); $('#dataList').datagrid('reload');", DEFAULT_DELAY_MS);
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
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
		<!-- 上部按钮层 -->
		<div data-options="region:'north', title:'&nbsp;操作面板',closedTitle:'操作面板(点击展开)'"
			style="height: 96px;">
				<form id="queryForm" method="post">
					<table>
						<tr>
							<td colspan="9">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">回复</a> 
							    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMulti()">删除</a>
							</td>
						</tr>
						<tr>
							<td style="width:65px;padding-left: 8px">查询条件： </td>
							<td style="width:55px">制单日期: </td>
							<td><input class="easyui-datebox" style="width: 100px"
								name="startDatetime" id="startDatetime">
								&nbsp;至&nbsp;
								<input class="easyui-datebox" style="width: 100px"
								name="endDatetime" id="endDatetime">
							</td>
							<td style="padding-left: 10px">所在小区：<tag:popedomCommunitySelect
								id="community" width="120" name="communityId"
								editable="true"></tag:popedomCommunitySelect>
							</td>
							<td  style="width:100px" class="tdRight">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="queryDatas()">查询</a>
							</td>
						</tr>
					</table>
				</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;建议列表'">
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
   					<input id="parentId" name="parentId" type="hidden">
    				<label>回复内容:</label>
    				<input id="userContent" name="userContent" class="easyui-textbox" data-options="multiline:true" style="width:400px;height:100px">
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
</body>
</html>