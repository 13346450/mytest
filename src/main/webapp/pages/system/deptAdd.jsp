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
		self.location = "${base}/system/SysDept/edit/" + $("#parentId").val();
	}
	
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/system/SysDept/insert",
	        		formId : "form",
	        		successJSFun : success
	        };
	        ajaxSubmitForm(json);
		}
	}

	function success(json){
		showMask(json.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysDept/edit/" + json.idKey + "';", 600);
		window.parent.leftFrame.RefreshDTreeInAdd(json.parentId, json.idKey, json.cdNm);
	}
	
	 $.extend($.fn.validatebox.defaults.rules, {
			digits: {
		        validator: function(value){
		        	return isInteger(value);
		        },
		        message: '此项必须为数字！'
		    }
	 });
</script>
</head>
<body>
		 <div style="padding:5px;border:1px solid #ddd;">
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back', plain:true" onclick="back()">返回</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	    </div>
	    <form id="form">
	    	<table>
	    		<%-- <tr>
	    			<td>单位代码:</td>
	    			<td>
	    				<input id="deptCode" name="deptCode" type="text" class="easyui-validatebox" data-options="required:true,validType:'digits'">
	    			</td>
	    		</tr> --%>
	    		<tr>
	    			<td style="text-align: right">单位名称:</td>
	    			<td>
	    				<input id="cdNm" name="cdNm" type="text" class="easyui-validatebox" data-options="required:true">
	    				<input id="parentId" name="parentId" value="${parentId}" type="hidden">
	    				<%-- <sf:input path="cdNm" cssClass="easyui-validatebox" data-options="required:true"/> --%>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align: right">排序号:</td>
	    			<td>
	    				<input id="orderCd" name="orderCd" type="text" class="easyui-validatebox" data-options="required:true,validType:'digits'" value="${orderCd}">
	    				<%-- <sf:input path="orderCd" cssClass="easyui-validatebox" data-options="required:true"/> --%>
    				</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align: right">备注:</td>
	    			<td>
	    				<textarea rows="4" id="remarks" name="remarks"></textarea>
    				</td>
	    		</tr>
	    		<tr>
					<td style="text-align: right">
						是否启用:
					</td>
					<td>
						<input type="radio" name="isuse" checked="checked" value="1">是
						<input type="radio" name="isuse"  value="0">否
					</td>
				</tr>
			<!-- 	 <tr>
					<td>
						是否为小区:
					</td>
					<td>
						<input type="radio" name="isBranch"  value="1">是
						<input type="radio" name="isBranch" checked="checked" value="0">否
					</td>
				</tr> -->
	    	</table>
	    </form>
</body>
</html>