<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			url : '${base}/business/BizProperInfo/listPage',
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
		 	             /* {field:'ck',checkbox:true}, */
		 	             //{field:'advVersion', title:'版本号',  width:60,  halign:'center', sortable:true}
		 	           ]],
			columns : [[
						
			            {field:'infoTitle', title:'信息标题', align:'left', width:200},
			            {field:'typeName', title:'信息类型', align:'center', width:85},
			            {field:'infoContent', title:'信息正文', align:'left', width:300},
			            {field:'communityName', title:'社区名称', align:'center', width:85},
			            {field:'createName', title:'创建人',align:'center', width:85},
			            {field:'createDt', title:'创建日期', align:'center', width:85, sortable:true},
			            {field:'publishName', title:'发布人',align:'center', width:85},
			            {field:'publishDt', title:'发布日期', align:'center', width:85, sortable:true},
			            {field:'infoStatus', title:'状态',  align:'center',   width:70, hidden:true},
			            {field:'statusName', title:'发布状态', align:'center', width:70, sortable:true},
			            {field:'imagesUrl', title:'图片地址', width:180, hidden:true},
		 	            {field:'linksUrl', title:'链接地址', width:180, hidden:true}
			          ]]
		});
		mainPageView();
		setInitDate();
		$('#bodyLayout').layout('collapse','east');
		
		
	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
	}
	function dblClickRow(rowIndex, rowData) {
		edit();
	}
	//设置初始日期
	function setInitDate(){
		$('#startDatetime').datebox('setValue', getStartDatetime());
		$('#endDatetime').datebox('setValue', getEndDatetime());
	}
	 /*表格加载成功后调用的方法 */
     function loadSuccess(){
    	 var rows = $('#dataList').datagrid('getRows');
    	 if(rows.length > 0){
	    	var row = rows[0];
	    	$('#dataList').datagrid('selectRow', 0);
    	 } 
    }

	 /* 发布  */
     function publish(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要发布的信息。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要发布选中的信息吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizProperInfo/publishProperInfo/" + row.idKey,
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
         	$.messager.alert('提示信息',"请选中需要取消发布的信息。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要取消发布选中的信息吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizProperInfo/cancelPublishProperInfo/" + row.idKey,
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
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
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
								<a id="publishButton" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="publish()">发布</a>
								<a id="cancelPublishButton" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="cancelPublish()">取消发布</a>
							</td>
						</tr>
						<tr>
							<td style="width:75px" class="tdRight">查询条件： </td>
							<td style="width:55px">创建日期: </td>
							<td><input class="easyui-datebox" style="width: 100px"
								name="startDatetime" id="startDatetime">
								&nbsp;至&nbsp;
								<input class="easyui-datebox" style="width: 100px"
								name="endDatetime" id="endDatetime"></td>
							<td style="width:50px" class="tdRight">状态</td>
							<td>
								<select class="easyui-combobox" name="infoStatus"  data-options="panelHeight:'auto'" style="width:80px">
									<option value="-1">全选</option>
									<option value="1">已发布</option>
									<option value="2">未发布</option>
								</select>
							</td>
							<td style="padding-left: 10px">所在小区： <tag:popedomCommunitySelect
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
		<div data-options="region:'center',title:'&nbsp;物业信息列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
<!-- 		<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-edit'" 
				style="width:600px;height:500px;padding:10px 20px" buttons="#dlg-buttons">
	      <div class="ftitle">物业信息</div>
			<form id="fm" method="post" novalidate>
	            <div class="fitem">
	            	<input name="idKey" type="hidden">
	            	<input name="regionId" type="hidden">
	                <label>设备序号:</label>
	                <input name="rcSn" class="easyui-validatebox" required="true">
	            </div>
	         </form>
	    	<div id="dlg-buttons">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">发布</a>
	        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	    	</div>
	</div> -->
</div>
</body>
</html>