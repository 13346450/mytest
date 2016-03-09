<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js" ></script>
<style type="text/css">
    #fm{
        margin:0;
        padding:10px 10px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:100px;
        FONT-SIZE: 10pt;
        text-align: right;
        padding-right: 10px;
    }
</style>
<script type="text/javascript">
/*$(document).ready(function(){
	 $('#grade').combobox({
		 url:'${base}/system/SysRole/queryAllSysRole',
		 method:'get',
		 valueField:'grade',
		 textField:'gradeText'
	});
	}); */
	function fun(value,row,index){
		return "<input name='checkbox' type='checkbox' disabled='true' checked='checked' value='" + row.idKey + "'/>";
	}

	var url;
	function add(){
		$('#dlg').dialog('open').dialog('setTitle','添加角色');
        $('#fm').form('clear');
        //$('#roleRemark').val("");
        //$('#roleName').val("");
        url = '${base}/system/SysRole/insert';
	}
	
	function edit(){
		var row = $('#roleGrid').datagrid('getSelected');
        if (row){
            $('#dlg').dialog('open').dialog('setTitle','编辑角色');
            $('#fm').form('load',row);
            url = '${base}/system/SysRole/update';
        }else{
        	$.messager.alert('提示信息',"请选中要编辑的行",'error');
        }
	}
	
	function saveUser(){
		if(!$('#fm').form('validate')){
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
	
	function saveSuccess(result){
		showMask(result.successMsg, "dlg"); 
       	setTimeout("hideMask(); $('#dlg').dialog('close'); $('#roleGrid').datagrid('reload');", 800);
	}

    function dele(){
        var row = $('#roleGrid').datagrid('getSelected');
        if(!row){
        	$.messager.alert('提示信息',"请选中需要删除的行",'error');
        	return;
        }
		$.messager.confirm('确认','确定要删除选中行吗?',function(r){
		    if (!r){return;}
		    
	    	var json = {
	    		    url: "${base}/system/SysRole/delete?idKey=" + row.idKey,
	    		    success: function(result){
		     	    	if (result.successMsg){ 
		     	    		showMask(result.successMsg);
		        	    	setTimeout("hideMask(); $('#roleGrid').datagrid('reload'); ", 800);
	                    } else {
	                    	hideMask();
	                    	$.messager.alert('错误信息',result.errorMsg,'error');
	                    }
	     	    	}
	    		};
	    	ajax(json);
		});
    }
    
    function dblClickRow(rowIndex, rowData){
    	edit();
    }
</script>
</head>
<body scroll="no">
	<table id="roleGrid" class="easyui-datagrid" 
		fit="true" 
		url="${base}/system/SysRole/listPage"
	 	border="false" 
	 	striped="true" 
	 	rownumbers="true" 
	 	singleSelect="true" 
	 	pageSize="30"
	 	toolbar="#toolbar" 
	 	pagination="true"
	 	data-options="onDblClickRow:dblClickRow"
	 	>
	    <thead>
	        <tr>
	            <th field="roleName" width="110px" halign="center">角色名称</th>
	            <!-- <th field="roleType" width="110px" halign="center">角色等级</th>  -->
	            <th field="chgDt" width="110px" halign="center" align="center">修改时间</th>
	            <th field="roleRemark" width="210px" halign="center">备注</th>
	            <!-- <th data-options="field:'roleRemark',formatter:fun">备注</th> -->
	        </tr>
	    </thead>
	</table>
	<div id="toolbar" style=" padding-top: 10px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">增加</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dele()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:330px;padding:10px 20px" buttons="#dlg-buttons">
        <div class="ftitle">角色信息</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
            	<input id="idKey" name="idKey" type="hidden">
                <label>角色名称:</label>
                <input name="roleName" id="roleName" class="easyui-validatebox" required="true">
            </div>
			<!--     
            <div class="fitem">
            	<label>等级:</label>
				<input name="roleType" id="roleType" class="easyui-validatebox" required="true">
			</div> 
			-->
            <div class="fitem">
                <label>备注:</label>
               	<textarea rows="4"  name="roleRemark" id="roleRemark"></textarea>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
</body>
</html>