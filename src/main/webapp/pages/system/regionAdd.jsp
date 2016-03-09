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
	function back(){
		self.location = "${base}/system/SysRegion/edit/" + $("#parentId").val();
	}
	
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/system/SysRegion/insert",
	        		formId : "form",
	        		successJSFun : success
	        };
	        ajaxSubmitForm(json);
		}
	}

	function success(json){
		showMask(json.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysRegion/edit/" + json.idKey + "';", 600);
		window.parent.leftFrame.RefreshDTreeInAdd(json.parentId, json.idKey, json.cdNm);
	}
</script>
</head>
<body>
		 <div style="padding:5px;border:1px solid #ddd;">
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back', plain:true" onclick="back()">返回</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	    </div>
	    <form id="form">
	    	<table>
	    		<tr>
	    			<td>归属地类别:</td>
	    			<td>
	    			<tag:dictSelectTags id="regionTypeId" dictTypeId="REGION_TYPE_ID" headValue="0" headLabel="请选择">
	    			</tag:dictSelectTags>
	    				<input id="parentId" name="parentId" value="${parentId}" type="hidden">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>编码ID:</td>
	    			<td>
	    				<input id="regionNumber" name="regionNumber" type="text" class="easyui-validatebox" data-options="required:true">
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
	    			<td>
	    				<input id="regionName" name="regionName" type="text" class="easyui-validatebox" data-options="required:true"/>
    				</td>
	    		</tr>
	    		<tr>
	    			<td>排序:</td>
	    			<td>
	    				<input id="orderCd" name="orderCd" type="text" class="easyui-validatebox" data-options="required:true" value="0"/>
    				</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td>
	    				<textarea rows="4" id="memos" name="memos"></textarea>
    				</td>
	    		</tr>
	    	</table>
	    </form>
</body>
</html>