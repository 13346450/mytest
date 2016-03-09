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
		if ("${sysUser.roleType}" < "${sessionBean.roleType}") {
			$('#roleId').combobox('setText', "${sysUser.roleName}");
			$("#roleId").combobox('readonly', true);
			$("#savebnt").linkbutton('disable');
			$("#delbnt").linkbutton('disable');
		} else {
			$("#roleId").combobox('readonly', false);
			$("#savebnt").linkbutton('enable');
			$("#delbnt").linkbutton('enable');
		}
	});
	function save() {
		if ($("#form").form('validate')) {
			var json = {
				url : "${base}/system/SysUser/update",
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
					"hideMask();location.href='${base}/system/SysUser/userListOrEdit/"
							+ result.DeptId + "'", 500); */
			setTimeout(
					"hideMask();location.href='${base}/system/SysUser/userListOrEdit/0"
							+result.idKey+ "'", 500); 
			window.parent.leftFrame.RefreshDTreeInEdit(result.DeptId,
					result.idKey, result.cdNm);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
			setTimeout("hideMask();", 500);
		}
	}

	function dele() {
		var tipmsg="确定要删除人员吗?";
		 if ("${sysUser.loginNm}" =="${sessionBean.loginNm}")
		 {
		 tipmsg="确认要删除您自己的账号吗?";
		 }
		$.messager.confirm('提示信息', tipmsg, function(r) {
			if (r) {
				var json = {
					url : "${base}/system/SysUser/delete",
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
		/* setTimeout("hideMask();document.location.href='${base}/system/SysUser/edit/" + $("#deptId").val() + "';", 800); */
		setTimeout(
				"hideMask();document.location.href='${base}/system/SysUser/userListOrEdit/"
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
	 	<sf:form commandName="sysUser" id="form">
	    	<table>
	    		<tr>
	    			<td>登录名:</td>
	    			<td><sf:input path="loginNm" cssClass="easyui-validatebox" data-options="required:true"/>
	    			<sf:hidden id="idKey" path='idKey'/>
	    			<sf:hidden id="deptId" path= "deptId"/>
	    			<sf:hidden id="roleType" path= 'roleType'/>
	    			</td>
	    			<td>用户姓名:</td>
	    			<td><sf:input path="cdNm" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>所属角色:</td>
	    			<td>
	    			<c:if test="${sysUser.roleType < sessionBean.roleType}">
	    			<sf:input path="roleName" />
	    			</c:if>
	    			<c:if test="${sysUser.roleType >=sessionBean.roleType}">
	    			<tag:roleSelectTags id="roleId" name="roleId" value="${sysUser.roleId}" readonly="true"></tag:roleSelectTags>
	    			</c:if>
	    			</td>
	    			<td>排序码:</td>
	    			<td>
	    			<sf:input path="orderCd" cssClass="easyui-validatebox" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>手机号:</td>
	    			<td>
	    			<sf:input path="telephone" cssClass="easyui-validatebox" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>地址:</td>
	    			<td>
	    			<sf:input path="addr" cssClass="easyui-validatebox" />
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
	    		<td colspan="3" >
	    			<fieldset > 
					<legend>住房信息：</legend>
					<div style="margin:5px 10px 10px 10px;">
	    			楼栋号:
	    			
	    			<sf:input path="building" cssClass="easyui-validatebox" size="2"/>
	    			
	    			单元号:
	    			
	    			<sf:input path="unit" cssClass="easyui-validatebox" size="2"/>
	    			
	    			楼层号:
	    			
	    			<sf:input path="floor" cssClass="easyui-validatebox" size="2"/>
	    			
	    			房号:
	    			
	    			<sf:input path="room" cssClass="easyui-validatebox" size="2" />
	    			</div>
	    			</fieldset > 
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
