<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
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
	var downloadLink = "";
	var rownum = 0;
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizApp/listPage',
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
			onClickRow : clickRow,
		 	onLoadSuccess :loadSuccess,
		 	idField : 'idKey',
		 	frozenColumns:[[
		 	             /* {field:'ck',checkbox:true}, */
		 	            {field:'name', title:'版本名称',  width:60,  halign:'center', sortable:true},
		 	             {field:'appVersion', title:'序列号',  width:60, align:'right', halign:'center', sortable:true}
		 	            ]],
			columns : [[
						 {
				field : 'typeName',
				title : 'APP类型',
				halign : 'center',
				align : 'center',
				width : 60
			},{
				field : 'type',
				title : 'APP类型',
				halign : 'center',
				align : 'center',
				width : 60,
				hidden:true
			} ,
			            {field:'makerId', title:'制单人', align:'center', width:60, hidden:true},
			            {field:'makerNm', title:'制单人', align:'center', width:65},
			            {field:'makerDt', title:'制单日期', width:75, align:'center', sortable:true},
			            {field:'auditId', title:'审核人', width:60,align:'center', hidden:true},
			            {field:'auditNm', title:'审核人', width:65,align:'center'},
			            {field:'auditDt', title:'审核日期', width:75, align:'center', sortable:true},
			            {field:'publishId', title:'发布人', width:60,align:'center', hidden:true},
			            {field:'publishNm', title:'发布人', width:65,align:'center'},
			            {field:'publishDt', title:'发布日期', width:75, align:'center', sortable:true},
			            {field:'appStatus', title:'状态', width:60, align:'center', hidden:true},
			            {field:'appStatusNm', title:'状态', width:60, align:'center', sortable:true},
			            {field:'downloadUrl', title:'下载地址', width:180, sortable:true},
			            {field:'improvement', title:'更新信息', width:180, sortable:true}
			          ]]
		});
		$('#bodyLayout').layout('collapse','east');
		mainPageView();
		setInitDate();
	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
	}
	//设置初始日期
	function setInitDate(){
		$('#startDatetime').datebox('setValue', getStartDatetime());
		$('#endDatetime').datebox('setValue', getEndDatetime());
	}
	function clickRow(rowIndex, rowData) {
		rownum = rowIndex;
		view();
		if(!$('#bodyLayout').layout('panel','north').panel("options").collapsed){//折叠north面板
			$('#bodyLayout').layout('collapse','north');
		}; 
		if($('#bodyLayout').layout('panel','east').panel("options").collapsed){//展开east面板
			$('#bodyLayout').layout('expand','east');
		}
	}
	 /*表格加载成功后调用的方法 */
     function loadSuccess(){
    	 var rows = $('#dataList').datagrid('getRows');
    	 if(rows.length > 0){
	    	$('#dataList').datagrid('selectRow', rownum);
	    	view();
    	 }else{
    		$('#fm').hide();
    	 }
    }
     function publish(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要发布的版本。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要发布选中的版本吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizApp/publishApp/" + row.idKey,
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
	 /*取消发布*/
     function cancelPublish(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要取消发布的版本。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要取消发布选中的版本吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizApp/cancelPublishApp/" + row.idKey,
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
 	/* 编辑一条记录 */
 	function view(){
 		var row = $('#dataList').datagrid('getSelected');
         if (row){
        	 $('#fm').form('load',row);
        	 editFormEnable(false);
             postUrl = '${base}/business/BizApp/update';
             downloadLink = '${base}/'+row.downloadUrl;
         }else{
         	$.messager.alert('提示信息',"请选中要编辑的版本",'error');
         }
 	}
 	//设置表单是否可编辑
 	function editFormEnable(bool) {
		bindDatagrid("dataList", !bool);
		if(bool){
			$("#toolbar1").hide();
 			$("#searchButton").linkbutton('disable');
 			$("a.adv-linkbutton").show();
 		}else{
			$("#toolbar1").show();
 			$("#searchButton").linkbutton();
 			$("a.adv-linkbutton").hide();
 			if( $("#publishId").val() == "" &&  $("#auditId").val() != "" ){
 				$("#publishButton").linkbutton("enable");
 				$("#cancelPublishButton").linkbutton("disable");
 			}else{
 				$("#publishButton").linkbutton("disable");
 				if( $("#publishId").val() == "" ){
 					$("#cancelPublishButton").linkbutton("disable");
 				}else{
 					$("#cancelPublishButton").linkbutton("enable");
 				}
 			}
		}
	}
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
	function addFile(){
		$('#dlg').dialog('open').dialog('setTitle','上传apk');
        $('#uploadFm').form('clear');
	}
	// 上传
	function uploadApk(){
	    if($("#upFile").val() == ""){
	    	$.messager.alert('错误信息',"上传的apk文件不能为空",'error');
	    	return;
	    }
		var json = {
	 		    url: "${base}/business/BizApp/uploadFile?link=",
	 		    fileElementId: "upFile",
	 		    maskId : "dlg",
	 		    success: function(result){
	 		    	 var res = eval ("(" + result + ")");
		     	      if (res.successMsg){
	     	    		showMask(res.successMsg);
	        	    	setTimeout("hideMask(); $('#dlg').dialog('close'); addImagesTr('"
	        	    					+res.addr+"');", DEFAULT_DELAY_MS);
	                 } else {
	                 	hideMask();
	                 	$.messager.alert('错误信息',res.errorMsg,'error');
	                 }
	  	    	}
	 		};
		ajaxFileUpload(json);
	}
	//添加一个图片行
	function addImagesTr(image_addr){
		$("#downloadUrl").val(image_addr);
	}
	function downloadApk(){
		this.location.href = downloadLink;
	}
	//删除apk
	function removeApk(){
		$.messager.confirm('确认','删除apk后无法恢复，确定吗?',function(r){
 		    if (!r){return;}
			var json = {
	    		    url: "${base}/business/BizAdvert/deleteFile?apkAddr="+$("#downloadUrl").val(),
	    		    success: function(result){
		     	    	if (result.successMsg){
		     	    		showMask(result.successMsg);
		        	    	setTimeout("hideMask(); $('#dataList').datagrid('reload');", 
		        	    			DEFAULT_DELAY_MS);
	                    } else {
	                    	hideMask();
	                    	$.messager.alert('错误信息',result.errorMsg,'error');
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
		<div data-options="region:'north', title:'&nbsp;查询条件',closedTitle:'查询条件(点击展开)'"
			style="height: 70px; padding-top: 6px;">
				<form id="queryForm" method="post">
					<table>
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
								<select class="easyui-combobox" name="appStatus"  data-options="panelHeight:'auto'" style="width:80px">
									<option value="-1">全选</option>
									<option value="0">未审核</option>
									<option value="1">已审核</option>
									<option value="2">已发布</option>
								</select>
							</td>
							<td  style="width:100px" class="tdRight">
								<a href="javascript:void(0)" id="searchButton" class="easyui-linkbutton" iconCls="icon-search" onclick="queryDatas()">查询</a>
							</td>
						</tr>
					</table>
				</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;版本列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
		<div data-options="region:'east',title:'版本详细信息',split:false, collapsed:true"
			style="width:450px;">
			<div id="ff" class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'">
					<form id="fm" method="post" novalidate>
						<table class="tableDetail" id="tableAdd" style="width:99%;">
						<tr>
								<td class="tdLeft" style="width: 60px;padding: 3px">版本名称:</td>
								<td class="tdLeft" style="padding: 3px">
								<input name="name" id="name" class="easyui-textbox" required="true" readonly="readonly">
								</td>
						</tr>
							<tr>
								<td class="tdLeft" style="width: 50px;padding: 3px">序列号:</td>
								<td class="tdLeft" style="padding: 3px">
									<input name="idKey" id="idKey" type="hidden">
									<input name="auditId" id="auditId" type="hidden">
									<input name="publishId" id="publishId" type="hidden">
									<input name="appStatus" type="hidden">
									<input name="appVersion" id="appVersion" class="easyui-validatebox" required="true" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class="tdLeft" style="width: 50px;padding: 3px">apk地址:</td>
								<td class="tdLeft" style="padding: 3px">
									<input name="downloadUrl" id="downloadUrl" class="easyui-validatebox" required="true" readonly="readonly">
									&nbsp;&nbsp;
									<a href="javascript:void(0)" onclick="downloadApk()">下载</a>
									<a href="javascript:void(0)" class="adv-linkbutton" onclick="addFile()">添加apk文件</a>
								</td>
							</tr>
						</table>
	        </form>
   			</div>
		    <div data-options="region:'south'" style="height: 34px;padding-top: 3px">
			    <div id="toolbar1" style="text-align: center;">
			    	<a id="publishButton" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="publish()">发布</a>
					<a id="cancelPublishButton" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="cancelPublish()">取消发布</a>
			   </div>
			</div>
		</div>
		<!-- 上传图片form -->	
		<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-save'" 
			style="width:400px;height:145px;padding:20px 10px" buttons="#dlg-buttons">
		    <form id="uploadFm" method="post" novalidate>
		        <div class="fitem">
		            <label style="width:120px">上传android安装包:</label>
		            <input type="file" id="upFile" name="upFile" class="easyui-validatebox" required="true">
				</div>
		    </form>
		</div>
		<div id="dlg-buttons">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok">上传</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
	</div>
</div>
</body>
</html>