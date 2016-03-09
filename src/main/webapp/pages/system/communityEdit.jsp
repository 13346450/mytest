<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/tags/taglibs.jsp"%>
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
	$(function() {
			$("#savebnt").linkbutton('enable');
			$("#delbnt").linkbutton('enable');
	});
	function save() {
		if ($("#form").form('validate')) {
			//cxl start
			var communityName = $("#form").find("[name='communityName']").val();
			communityName = $.trim(communityName);
			if(communityName==""||communityName.length>50){
				alert("小区名称不能为空且在50个字符以内");
				return;
			}
			if(!$("#form").find("[name='telephone']").val().match(/[0-9-]{3,20}/)){
				alert("联系电话应为3-20位的数字组成");
				return;
			}
			if(!$("#form").find("[name='orderCd']").val().match(/[0-9]{1,5}/)){
				alert("排序码应数字组成(0-99999)");
				return;
			}
			//cxl end
			var json = {
				url : "${base}/system/SysCommunity/update",
				formId : "form",
				successJSFun : addSaveSuccess
			};
			ajaxSubmitForm(json);
		}
	}

	function addSaveSuccess(result) {
		if (result.flag) {
			showMask(result.Msg);
			/* setTimeout(
					"hideMask();location.href='${base}/system/SysCommunity/userListOrEdit/"
							+ result.DeptId + "'", 500); */
			setTimeout(
					"hideMask();location.href='${base}/system/SysCommunity/userListOrEdit/0"
							+result.idKey+ "'", 500); 
			window.parent.leftFrame.RefreshDTreeInEdit(result.DeptId,
					result.idKey, result.communityName);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
			setTimeout("hideMask();", 500);
		}
	}

	function dele() {
		var tipmsg="确定要删除该小区吗?";
		$.messager.confirm('提示信息', tipmsg, function(r) {
			if (r) {
				var json = {
					url : "${base}/system/SysCommunity/delete",
					type : "GET",
					data : {
						"userId" : $("#idKey").val()
					},
					success : deleteSuccess
				};
				ajax(json);
			}
		});
	}

	function deleteSuccess(json) {
		if (json.flag) {
		showMask(json.Msg);
		/* setTimeout("hideMask();document.location.href='${base}/system/SysCommunity/edit/" + $("#deptId").val() + "';", 800); */
		setTimeout(
				"hideMask();document.location.href='${base}/system/SysCommunity/userListOrEdit/"
						+ $("#deptId").val() + "';", 500);
		window.parent.leftFrame.RefreshDTreeInDelete(json.idKey);
		}else{
			hideMask();
			$.messager.alert('错误信息',
					json.Msg, 'error');
		}
	}
</script>
</head>
<body>
		 <div style="padding:5px;border:1px solid #ddd;">
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true" onclick="save()" id="savebnt">保存</a>
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:true" onclick="dele()" id="delbnt">删除</a>
	    </div>
	 	<sf:form commandName="sysCommunity" id="form">
	    	<table>
	    		<tr>
	    			<td>小区名称:</td>
	    			<td><sf:input path="communityName" cssClass="easyui-validatebox" data-options="required:true"/>
	    			<sf:hidden id="idKey" path='idKey'/>
	    			<sf:hidden id="deptId" path= "deptId"/>
	    			</td>
	    		<tr>
	    			<td>小区地址:</td>
	    			<td><sf:input path="address" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    			<td>物业单位:</td>
	    			<td><sf:input path="proName" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td><sf:input path="telephone" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>排序码:</td>
	    			<td>
	    			<sf:input path="orderCd" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>用户状态:</td>
	    			<td>
	    			<sf:radiobutton path="useMark" value="1"/>启用
               		<sf:radiobutton path="useMark" value="0"/>停用
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td><sf:textarea rows="4"  path="remark" />
	    			</td>
	    			<td></td><td></td>
	    		</tr>
	    	</table>
	    </sf:form>
</body>
</html>
