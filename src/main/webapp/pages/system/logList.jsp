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
	function fun(value,row,index){
		return "<input name='checkbox' type='checkbox' disabled='true' checked='checked' value='" + row.idKey + "'/>";
	}
    function dele(){
        var row = $('#logGrid').datagrid('getSelected');
        if(!row){
        	$.messager.alert('提示信息',"请选中需要删除的行",'error');
        	return;
        }
		$.messager.confirm('确认','确定要删除选中行吗?',function(r){
		    if (!r){return;}
		    
	    	var json = {
	    		    url: "${base}/system/SysLogs/delete?idKey=" + row.idKey,
	    		    success: function(result){
		     	    	if (result.successMsg){ 
		     	    		showMask(result.successMsg);
		        	    	setTimeout("hideMask(); $('#logGrid').datagrid('reload'); ", 800);
	                    } else {
	                    	hideMask();
	                    	$.messager.alert('错误信息',result.errorMsg,'error');
	                    }
	     	    	}
	    		};
	    	ajax(json);
		});
    }
    function SearchMid() {
		$('#logGrid').datagrid('load', formToJson("searchlist"));
	}
</script>
</head>
<body scroll="no">
	<table id="logGrid" class="easyui-datagrid" 
		fit="true" 
		url="${base}/system/SysLogs/listPage"
	 	border="false" 
	 	striped="true" 
	 	rownumbers="true" 
	 	singleSelect="true" 
	 	pageSize="30"
	 	toolbar="#toolbar" 
	 	pagination="true"
	 	sortName="operDt" 
	 	sortOrder="desc" 
	 	>
	    <thead>
	        <tr>
	            <th field="funcMenuNm" width="160px" halign="center">功能菜单名</th>
	            <th field="funcOperNm" width="110px" halign="center">操作功能</th> 
	            <th field="operNm" width="110px" halign="center" align="center">操作人姓名</th>
	            <th field="operId" width="110px" halign="center" align="center">操作人ID</th>
	            <th field="operDt" width="110px" halign="center" sortable="true">操作时间</th>
	            <th field="operRemark" width="500px" halign="center" >操作说明</th>
	            <!-- <th data-options="field:'roleRemark',formatter:fun">备注</th> -->
	        </tr>
	    </thead>
	</table>
	<div id="toolbar" style=" padding-top: 10px">
	<form id="searchlist" method="post">
	    操作时间: <input class="easyui-datebox" style="width: 100px"
								name="dataTimeBegin" id="dataTimeBegin">
								到: <input class="easyui-datebox" style="width: 100px"
								name="dataTimeEnd" id="dataTimeEnd" >
								  姓名：<input style="width: 90px" name="operNm" class="TextBox">
								  <a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" onclick="SearchMid()">查询</a>
								</form>
	</div> 
</body>
</html>