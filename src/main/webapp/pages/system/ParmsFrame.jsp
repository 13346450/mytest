<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${base}/resources/javascripts/jquery-easyui/plugins/jquery.edatagrid.js"></script>
<body>
<table id="dg" fit="true" idField="idKey" style="height:420" toolbar="#toolbar" collapsible="true" rownumbers="true"
 fitColumns="true" fitcolumns="true" singleSelect="true" >
 	<thead>
 		<tr>
 			<!-- <th field="idKey" width="200" >编号</th> -->
 			<th field="parmKey" width="200" editor="{type:'validatebox',options:{required:true}}">参数名</th>
 			<th field="parmValue" width="200" editor="{type:'validatebox'}">参数值</th>
 			<th field="chgDt" width="200" >创建时间</th>
 		</tr>
 	</thead>
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a> 
	<a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">取消</a>
</div>
<script type="text/javascript">
	$(function(){
		$('#dg').edatagrid({
            url: '${base}/system/SysParms/listAll',
            saveUrl: '${base}/system/SysParms/insert',
            updateUrl: '${base}/system/SysParms/update', 
            destroyUrl: '${base}/system/SysParms/delete'
         }); 
	});
</script>
</body>
</html>