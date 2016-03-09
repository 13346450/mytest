<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	function add(){
		self.location = "${base}/system/SysRegion/add/" + $("#idKey").val();
	}
	
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/system/SysRegion/update",
	        		formId : "form",
	        		successJSFun : addSaveSuccess
	        };
	        ajaxSubmitForm(json);
		}
	}
	
	function addSaveSuccess(result){
		showMask(result.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysRegion/edit/" + result.idKey + "';", 300);
		window.parent.leftFrame.RefreshDTreeInEdit(result.parentId, result.idKey, result.cdNm);
	}
	
	function dele(){
		var children = window.parent.leftFrame.hasChildren($("#idKey").val());
		if(children > 0 || children){
			$.messager.alert('拒绝删除','当前区域有下级区域，请先删除下级区域！','error');
			return;
		}
		$.messager.confirm('提示信息', '确定要删除区域吗?', function(r){
            if (r){
            	var json={
                		url : "${base}/system/SysRegion/delete",
                		type: "GET",
                		data : {"RegionId" : $("#idKey").val(), "parentId" : $("#parentId").val()},
                		success : deleteSuccess
                };
            	ajax(json);
            }
        });
	}
	
	function deleteSuccess(json){
		showMask(json.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysRegion/edit/" + $("#parentId").val() + "';", 300);
		window.parent.leftFrame.RefreshDTreeInDelete(json.idKey);
	}
</script>
</head>
<body>
		 <div style="padding:5px;border:1px solid #ddd;">
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="add()">增加</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="dele()">删除</a>
	    </div>
	 	<sf:form commandName="sysRegion" id="form">
	    	<table>
	    		<tr>
	    			<td>归属地类别:</td>
	    			<td>
	    			<tag:dictSelectTags id="regionTypeId" value="${sysRegion.regionTypeId}" name="regionTypeId" dictTypeId="REGION_TYPE_ID">
	    			</tag:dictSelectTags>
	    			<sf:hidden id="idKey" path='idKey'/>
	    			<sf:hidden id="parentId" path= "parentId"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>编码ID:</td>
	    			<td><sf:input path="regionNumber" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>上级归属地名称:</td>
	    			<td>
	    			<input name="parentName" value="${sysRegion.parentName}" disabled/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>编码名称:</td>
	    			<td><sf:input path="regionName" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>排序:</td>
	    			<td>
	    				<sf:input path="orderCd" cssClass="easyui-validatebox" data-options="required:true"/>
    				</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td><sf:textarea path="memos" rows="4"/></td>
	    		</tr>
	    		
	    	</table>
	    </sf:form>
</body>
</html>