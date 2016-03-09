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
	function back(){
		self.location = "${base}/system/SysMenu/edit/" + $("#parentId").val();
	}
	
	function save(){
		if($("#form").form('validate')){
			var json={
					url:"${base}/system/SysMenu/insertMenuUpdateParentLastMark",
					formId : "form",
					successJSFun : success
			};
			ajaxSubmitForm(json);
		}
	}
	
	function success(json){
		showMask(json.successMsg);
		setTimeout("hideMask(); document.location.href='${base}/system/SysMenu/edit/" + json.idKey + "';", 600);
		window.parent.leftFrame.RefreshDTreeInAdd(json.parentId, json.idKey, json.cdNm);
	}
</script>
</head>
<body>
	<div style="padding:5px;border:1px solid #ddd;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back', plain:true" onClick="back()">返回</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onClick="save()">保存</a>
	</div>
	 <form id="form">
         <table>
            <tr>
               <td>菜单名称：</td>
               <td>
               		<input id="cdNm" name="cdNm" type="text" class="easyui-validatebox" data-options="required:true"/>
               		<input id="parentId" name="parentId" value="${parentId}" type="hidden" />
               </td>
            </tr>
            <tr>
            	<td>菜单编号：</td>
            	<td><input id="menuMarker" name="menuMarker"  type="text" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
               <td>排序号：</td>
               <td><input id="orderCd" name="orderCd" type="text" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
               <td>图标地址：</td>
               <td><input id="iconAddress" name="iconAddress" type="text" class="easyui-validatebox"/></td>
            </tr>
            <tr>
               <td>链接地址：</td>
               <td><input id="linkAddress" name="linkAddress" type="text" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
               <td>是否使用：</td>
               <td>
               		<input type="radio" name="isuse" checked="checked" value="1"/>是
             		<input type="radio" name="isuse" value="0">否
               </td>
            </tr>
            <tr>
               <td>备注：</td>
               <td><textarea  rows="4" id="remarks" name="remarks"></textarea></td>
            </tr>
         </table>
      </form>
</body>
</html>