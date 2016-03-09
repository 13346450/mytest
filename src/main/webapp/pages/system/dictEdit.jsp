<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ include file="../common/head.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${base}/resources/javascripts/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/resources/javascripts/jquery-easyui/themes/icon.css">
<script src="${base}/resources/javascripts/jquery-1.11.1.min.js" ></script>
<script src="${base}/resources/javascripts/jquery-easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="${base}/resources/javascripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${base}/resources/javascripts/app-common.js" ></script>
<style >
	body {
	margin-left: 5px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
 $(function(){
    $('#dg').datagrid({
    	fit:true,
    	collapsible:true,
    	rownumbers:true,
    	checkbox:true,
    	truefitcolumns:true,
    	pageSize : 20,//默认选择的分页是每页5行数据  
        pageList : [ 20, 40, 60, 80 ],
        url:'${base}/system/SysDict/editDataGrid/'+$("#dTypeId").val(),
        columns:[[
            {field:'idKey',title:'idKey',width:100,hidden:true},
            {field:'dictTypeNm',title:'字典名称',width:150},
            {field:'dictValue',title:'字典值',width:100},
            {field:'remark',title:'备注信息',width:200}
        ]],
        toolbar : [{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
            	add();
            }
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){
            	edit();
            	}
        },{
        	text:'删除',
        	iconCls:'icon-cut',
        	handler:function(){
            	//选中要删除的行
                var rows = $("#dg").datagrid('getSelections');
                if (rows.length > 0) {//选中几行的话触发事件
                    $.messager.confirm("提示", "您确定要删除这些数据吗？", function (res) {//提示是否删除
                        if (res) {
                           var idKeys = "";
                             for (var i = 0; i < rows.length; i++) {
                            	idKeys += rows[i].idKey+",";//拼接成数组                            	
                            } 
                            $.post("${base}/system/SysDict/delete",{idKey:idKeys},function(msg){
                            	var msg = eval('('+msg+')');
                            		showMask(msg.successMsg, "dlg"); 
                                	setTimeout("hideMask(); $('#dlg').dialog('close'); $('#dg').datagrid('reload');", 800);
                            });
                        }
                    });
                }
            }
        }]
    });
	
});
 
var url;
function add(){
	$('#dlg').dialog('open').dialog('setTitle','添加数据');
    $('#fm').form('clear');
    url = '${base}/system/SysDict/insert/'+$("#dTypeId").val();
}
//保存新增数据字典
function saveDict(){
	if(!$('#fm').form('validate')){
		return;
	}
    $('#fm').form('submit',{
        url: url,
        dataType:'json',
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            	showMask(result.successMsg, "dlg"); 
            	setTimeout("hideMask(); $('#dlg').dialog('close'); $('#dg').datagrid('reload');", 800);
        }
    });
}

function edit(){
	var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','编辑数据');
        $('#fm').form('load',row);
        url = '${base}/system/SysDict/update';
    }else{
    	$.messager.alert('提示信息',"请选中要编辑的行",'error');
    }
}
</script>
</head>
<body>
<!--    显示table -->
   <table id="dg"></table>
   
   <!-- 添加一个数据字典 -->
	<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-save'"  style="width:400px;height:250px;padding:10px 20px;" buttons="#dlg-buttons">
        <input id="dTypeId" value='${dTypeId}' type="hidden"/>
        <form id="fm" method="post" novalidate >
            <div style="margin-bottom:10px;">
            <input id="idKey" name="idKey" type="hidden">
                <label >字典值:</label>
                <input name="dictValue" class="easyui-validatebox" required="true">
            </div>
            <div>
                <label >  备 注:  </label>
                <textarea name="remark" style="width:150px;"></textarea>
            </div>
            </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveDict()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    </body>
</html>