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
		self.location = "${base}/system/SysUser/edit/" + $("#parentId").val();
	}
	
	function save(){
		if($("#form").form('validate')){
	        var json={
	        		url : "${base}/system/SysUser/insert",
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
	    			<td>登录名:</td>
	    			<td><input name="loginNm" type="text" class="easyui-validatebox" data-options="required:true"/>
	    			<input id="userId" name= "userId" type="hidden"/>
	    			</td>
	    			<td>用户姓名:</td>
	    			<td><input name="cdNm" class="easyui-validatebox" data-options="required:true" type="text"/>
	    			</td>
	    			<!-- <td>用户编码:</td>
	    			<td>
	    			<input name="userCd" type="text"/>
	    			</td> -->
	    		</tr>
	    		<!-- <tr>
	    			
	    			<td>性别:</td>
	    			<td>
	    			<input type="radio" name="gender" value="m" checked="checked"/>男
               		<input type="radio" name="gender" value="f"/>女
	    			</td> 
	    		</tr> -->
	    		<tr>
	    			<td>所属角色:</td>
	    			<td>
	    			<input name="roleId" type="text" type="text"/>
	    			</td>
	    			<td>排序码:</td>
	    			<td>
	    			<input name="orderCd" type="text"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>用户状态:</td>
	    			<td>
	    			<input type="radio" name="userStatus" value="1" checked="checked"/>在职
               		<input type="radio" name="userStatus" value="0" />离职
	    			</td>
	    			<td>启用标记:</td>
	    			<td>
	    			<input type="radio" name="useMark" value="1" checked="checked"/>启用
               		<input type="radio" name="useMark" value="0"/>停用
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td><textarea rows="4"  name="remark" /></textarea>
	    			</td>
	    			<td></td>
	    			<td></td>
	    		</tr>
	    	</table>
	    </form>
</body>
</html>