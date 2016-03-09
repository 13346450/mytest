<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/head.jsp"%>
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
		self.location = "${base}/system/SysMenu/add/" + $("#idKey").val();
	}
	
	function save(){
		if($("#form").form('validate')){
			var json={
					url : "${base}/system/SysMenu/update",
					formId : "form",
					successJSFun : addSaveSuccess
			};
			ajaxSubmitForm(json);
		}
	}
	
	function dele(){
		var children = window.parent.leftFrame.hasChildren($("#idKey").val());
		if(children > 0 || children){
			$.messager.alert('拒绝删除', '当前菜单有下级菜单，请先删除下级菜单！', 'error');
			return;
		}
		$.messager.confirm('确认','确定要删除菜单吗？', function(r){
			if(r){
				var json={
						url: "${base}/system/SysMenu/deleteMenuUpdateParentLastMark",
						data : {"menuId" : $("#idKey").val(), "parentId" : $("#parentId").val()},
						success : deleteSuccess
				};
				ajax(json);
			}
		});
	}
	
	function addSaveSuccess(json){
		showMask(json.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysMenu/edit/" + json.idKey + "';", 300);
		window.parent.leftFrame.RefreshDTreeInEdit(json.parentId, json.idKey, json.cdNm);
	}
	
	function deleteSuccess(json){
		showMask(json.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysDept/edit/" + $("#parentId").val() + "';", 300);
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
	<sf:form commandName="sysMenu" id="form">
		<table>
			<tr>
				<td>菜单名称：</td>
				<td><sf:input path="cdNm" cssClass="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td>菜单编号：</td>
				<td>
					<sf:input path="menuMarker" cssClass="easyui-validatebox" data-options="required:true"/>
					<sf:hidden id="idKey" path="idKey"/>
					<sf:hidden id="parentId" path="parentId"/>
				</td>
			</tr>
			<tr>
				<td>排序号：</td>
				<td><sf:input path="orderCd" cssClass="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
               <td>图标地址：</td>
               <td><sf:input path="iconAddress" cssClass="easyui-validatebox"/></td>
            </tr>
            <tr>
               <td>链接地址：</td>
               <td><sf:input path="linkAddress" cssClass="easyui-validatebox" /></td>
            </tr>
			<tr>
				<td>是否启用：</td>
				<td>
					<sf:radiobutton path="isuse" value="1"/>是
					<sf:radiobutton path="isuse" value="0"/>否
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><sf:textarea path="remarks"/></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>