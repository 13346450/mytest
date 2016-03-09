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
		self.location = "${base}/system/SysDept/add/" + $("#idKey").val();
	}
	
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/system/SysDept/update",
	        		formId : "form",
	        		successJSFun : addSaveSuccess
	        };
	        ajaxSubmitForm(json);
		}
	}
	
	function addSaveSuccess(result){
		showMask(result.successMsg);
		setTimeout("hideMask();document.location.href='${base}/system/SysDept/edit/" + result.idKey + "';", 300);
		window.parent.leftFrame.RefreshDTreeInEdit(result.parentId, result.idKey, result.cdNm);
	}
	
	function dele(){
		var children = window.parent.leftFrame.hasChildren($("#idKey").val());
		if(children > 0 || children){
			$.messager.alert('拒绝删除','当前单位有下级单位，请先删除下级单位！','error');
			return;
		}
		$.messager.confirm('提示信息', '确定要删除单位吗?', function(r){
            if (r){
            	var json={
                		url : "${base}/system/SysDept/delete",
                		type: "GET",
                		data : {"deptId" : $("#idKey").val(), "parentId" : $("#parentId").val()},
                		success : deleteSuccess
                };
            	ajax(json);
            }
        });
	}
	
	function deleteSuccess(json){
	if (json.flag) {
			showMask(json.successMsg);
			setTimeout(
					"hideMask();document.location.href='${base}/system/SysDept/edit/"
							+ $("#parentId").val() + "';", 300);
			window.parent.leftFrame.RefreshDTreeInDelete(json.idKey);
		}else{
			hideMask();
			$.messager.alert('提示信息', json.successMsg, 'error');
		}
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
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="add()">增加</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="dele()">删除</a>
	    </div>
	 	<sf:form commandName="sysDept" id="form">
	    	<table>
	    		<%-- <tr>
	    			<td>单位代码:</td>
	    			<td><sf:input path="deptCode" cssClass="easyui-validatebox" data-options="required:true,validType:'digits'"/>
	    			</td>
	    		</tr> --%>
	    		<tr>
	    			<td style="text-align: right">单位名称:</td>
	    			<td>
	    				<sf:input path="cdNm" cssClass="easyui-validatebox" data-options="required:true"/>
	    				<sf:hidden id="idKey" path='idKey'/>
	    				<sf:hidden id="parentId" path= "parentId"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align: right">排序号:</td>
	    			<td><sf:input path="orderCd" cssClass="easyui-validatebox" data-options="required:true,validType:'digits'" /></td>
	    		</tr>
	    		<tr>
	    			<td style="text-align: right">备注:</td>
	    			<td><sf:textarea path="remarks" rows="4"/></tr>
	    		<tr>
					<td style="text-align: right">
						是否启用:
					</td>
					<td>
						<sf:radiobutton path="isuse" value="1"/>是 
               			<sf:radiobutton path="isuse" value="0"/>否
					</td>
				</tr>
			<!--  	 <tr>
					<td>
						是否为小区:
					</td>
					<td>
						<sf:radiobutton path="isBranch" value="1"/>是 
               			<sf:radiobutton path="isBranch" value="0"/>否
					</td>
				</tr> -->
	    	</table>
	    </sf:form>
</body>
</html>