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
		self.location = "${base}/business/BizCategory/edit/" + $("#parentId").val();
	}
	
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/business/BizCategory/insert",
	        		formId : "form",
	        		successJSFun : success
	        };
	        ajaxSubmitForm(json);
		}
	}

	function success(json){
		showMask(json.successMsg);
		setTimeout("hideMask();document.location.href='${base}/business/BizCategory/edit/" + json.idKey + "';", 600);
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
	    		<tr>
	    			<td style="text-align: right">单位名称:</td>
	    			<td>
	    				<input id="categoryName" name="categoryName" type="text" class="easyui-validatebox" data-options="required:true">
	    				<input id="communityId" name="communityId" value="${communityId}" type="hidden">
	    				<input id="parentId" name="parentId" value="${parentId}" type="hidden">
	    				<%-- <sf:input path="categoryName" cssClass="easyui-validatebox" data-options="required:true"/> --%>
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
	    				<textarea rows="4" id="remark" name="remark"></textarea>
    				</td>
	    		</tr>
	    		<tr>
					<td style="text-align: right">
						是否启用:
					</td>
					<td>
						<input type="radio" name="isUse" checked="checked" value="1">是
						<input type="radio" name="isUse"  value="0">否
					</td>
				</tr>
	    	</table>
	    </form>
</body>
</html>