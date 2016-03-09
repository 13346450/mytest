<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<script src="${base}/resources/javascripts/ajaxfileupload.js" ></script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 10px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 100px;
	FONT-SIZE: 10pt;
	text-align: right;
	padding-right: 10px;
}
.datagrid-header-rownumber{
    width:48px;
}
.datagrid-cell-rownumber{
    width:48px;
}
</style>
<script type="text/javascript">
	var imgId = 1;
	var rownum = 0;
	var postUrl = "";
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizChain/listPage',
			queryParams : formToJson("queryForm"),
			border : false,
			fit : true,
			border : false,
		 	striped : true, 
		 	multiSort : true,
		 	//sortName: 'rcSn',
			//sortOrder: 'asc',
		 	rownumbers : true,
		 	singleSelect : true,
		 	selectOnCheck : false,
			checkOnSelect : false,
			pageSize : DEFAULT_PAGE_SIZE,
			pagination : true,
			fitColumns:false,
			onDblClickRow : dblClickRow,
		 	onLoadSuccess :loadSuccess,
		 	idField : 'idKey',
	 		frozenColumns:[[
		 	             /* {field:'ck',checkbox:true}, */
		 	             //{field:'id_key', title:'链接序号',  width:60,  halign:'center', sortable:true}
		 	           ]],
			columns : [[
						{field:'communityName', title:'小区名称', width:120,align:'left'},
			            {field:'chainName', title:'链接名称', width:120,align:'left'},
		 	            {field:'linksUrl', title:'链接地址', width:200, align:'left'},
			            {field:'imagesUrl', title:'图片地址', width:300, align:'center'},
			            {field:'clicksTotal', title:'总点击数', width:120, align:'right'}
			          ]] 
		});
		$('#bodyLayout').layout('collapse','east');
		mainPageView();
	});
	
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
	}
	//设置初始日期
	function setInitDate(){
		$('#startDatetime').datebox('setValue', getStartDatetime());
		$('#endDatetime').datebox('setValue', getEndDatetime());
	}
	function dblClickRow(rowIndex, rowData) {
		edit();
		//$('#fm').form('load', rowData);
	}
	 /*表格加载成功后调用的方法 */
      function loadSuccess(){
    	 var rows = $('#dataList').datagrid('getRows');
    	 if(rows.length > 0){
	    	$('#dataList').datagrid('selectRow', rownum);
    	 } 
    }
	 /* 删除一条记录  */
     function deleteOne(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要删除的链接。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要删除选中的链接吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizChain/delete/" + row.idKey,
 	    		    success: function(result){
 		     	    	if (result.successMsg){ 
 		     	    		showMask(result.successMsg);
 		        	    	setTimeout("hideMask(); $('#dataList').datagrid('reload'); ", DEFAULT_DELAY_MS);
 	                    } else {
 	                    	hideMask();
 	                    	$.messager.alert('错误信息',result.errorMsg,'error');
 	                    }
 	     	    	}
 	    		};
 	    	ajax(json);
 		});
     }
	/* 新增一条记录 */
 	function add(){
 		$('#dlg').dialog('open').dialog('setTitle','增加链接');
        $('#fm').form('clear');
        postUrl = "${base}/business/BizChain/insert";
 	}
	
	function queryById()
	{
		var row = $('#myId').val();
		$.messager.confirm('确认','确定要删除选中的链接吗?',function(r){
 		    if (!r){return;}
		var json = {
	    		    url: "${base}/business/BizChain/queryById/" + row ,
	    		    success: function(result){
		     	    	if (result.successMsg){ 
		     	    		showMask(result.successMsg);
		        	    	setTimeout("hideMask(); $('#dataList').datagrid('reload'); ", DEFAULT_DELAY_MS);
	                    } else {
	                    	hideMask();
	                    	$.messager.alert('错误信息',result.errorMsg,'error');
	                    }
	     	    	}
	    		};
		ajax(json);
		});
	}
 	/* 编辑一条记录 */
 	function edit(){
 		var row = $('#dataList').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('setTitle','编辑链接');
             $('#fm').form('load',row);
             postUrl = '${base}/business/BizChain/update';
         }else{
         	$.messager.alert('提示信息',"请选中要编辑的链接",'error');
         }
 	}
 	/* 保存数据 */
	function save() {
		if(!$('#fm').form('validate')){
			return;
		}
		var json = {
				url : postUrl,
				formId : "fm",
				successJSFun : saveSuccess,
				maskId : "dlg"
		};
		ajaxSubmitForm(json);
	}
 	
	function saveSuccess(result) {
		if (result.flag) {
			showMask(result.successMsg, "dlg"); 
	       	setTimeout("hideMask(); $('#dlg').dialog('close'); $('#dataList').datagrid('reload');", DEFAULT_DELAY_MS);
		} else {
			hideMask();
			$.messager.alert('提示信息', result.successMsg, "error");
		}
	}
	
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
	
	
	function addImage(){
		$('#dlg1').dialog('open').dialog('setTitle','上传图片');
        $('#uploadFm').form('clear');
	}
	// 上传图片
	function uploadImage(){
	    if($("#upFile").val() == ""){
	    	$.messager.alert('错误信息',"上传文件不能为空",'error');
	    	return;
	    }
		var json = {
	 		    url: "${base}/business/BizChain/uploadFile",
	 		    fileElementId: "upFile",
	 		    maskId : "dlg1",
	 		    success: function(result){
	 		    	 var res = eval ("(" + result + ")");
		     	      if (res.successMsg){
	     	    		showMask(res.successMsg);
	        	    	setTimeout("hideMask(); $('#dlg1').dialog('close'); $('#imagesUrl').val('"
	        	    					+res.addr+"');", DEFAULT_DELAY_MS);
	                 } else {
	                 	hideMask();
	                 	$.messager.alert('错误信息',res.errorMsg,'error');
	                 }
	  	    	}
	 		};
		ajaxFileUpload(json);
	}
	//显示tip
	function showTip(image_id,image_url){
		$('#'+image_id).tooltip({
			position: 'left',
		    content: '<img src="'+image_url+'">',
		    onShow: function(){
		        $(this).tooltip('tip').css({
		            backgroundColor: '#CCCCCC',
		            borderColor: '#666666'
		        });
		    }
		});
	}
/*  	//点击行时，显示图片列表
	function generateImageEdit(imagesUrl, linksUrl){
		var imgarr = imagesUrl.split("@@");
		var linkarr = linksUrl.split("@@");
		if($("#tableAdd").find("tr").length>1){
			$("#tableAdd tr:not(:first)").empty();
		}
		if(imagesUrl == "") {
			return;
		}
	    for(var i=0; i<imgarr.length; i++){
	    	var newRow = "<tr>\n"
				    +"<td class=\"tdLeft\" style=\"padding: 3px\">\n"
				    +"<a href=\"javascript:void(0)\" class=\"adv-linkbutton\" onclick=\"removeImage(this,'"+imgarr[i]+"','"+linkarr[i]+"')\">删除本图</a>\n"
				    +"</td>\n"				    
				    +"<td class=\"tdLeft\" style=\"padding: 3px\">\n"
				    +"<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+imgarr[i]+"\" width=\"300px\" height=\"50px\"/>"
					+"<br><a href=\""+linkarr[i]+"\" target=\"_blank\">"+linkarr[i]+"</a>\n"
					+"</td>\n"
					+"</tr>";
			$("#tableAdd tr:last").after(newRow);
			showTip("adv_img_"+imgId,"${base}/"+ imgarr[i]);
			imgId++;
		}
		$("#imagesUrl").val(imagesUrl+"@@");
		$("#linksUrl").val(linksUrl+"@@");
	}  */
	//添加一个图片行
	function addImagesTr(image_addr,image_link){
/*  	    var newRow = "<tr>\n"
	    +"<td class=\"tdLeft\" style=\"padding: 3px\">\n"
	    +"<a href=\"javascript:void(0)\" class=\"adv-linkbutton\" onclick=\"removeImage(this,'"+image_addr+"','"+image_link+"')\">删除本图</a>\n"
	    +"</td>\n"
	    +"<td class=\"tdLeft\" style=\"padding: 3px\">\n"
	    +"<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+image_addr+"\" width=\"300px\" height=\"50px\">\n"
		+"	<br><a href=\""+image_link+"\" target=\"_blank\">"+image_link+"</a>\n"
		+"</td>\n"
		+"</tr>\n";
		$("#tableAdd tr:last").after(newRow);  */
		$("#imagesUrl").val( $("#imagesUrl").val() + image_addr + "@@"  );
		$("#linksUrl").val( $("#linksUrl").val() + image_link + "@@"  );
		//showTip("adv_img_"+imgId,"${base}/"+image_addr);
		//imgId++;
	}
	//删除一个图片行
	function removeImage(obj,image_addr,image_link){
		$.messager.confirm('确认','删除图片后无法恢复，确定吗?',function(r){
 		    if (!r){return;}
			var json = {
	    		    url: "${base}/business/BizChain/deleteImage?imageAddr="+image_addr,
	    		    success: function(result){
		     	    	if (result.successMsg){
		     	    		showMask(result.successMsg);
		        	    	setTimeout("hideMask(); $('#dataList').datagrid('reload'); removeTr("
		        	    					+obj.parentNode.parentNode.rowIndex+",'"+image_addr+"','"+image_link+"');", 
		        	    			DEFAULT_DELAY_MS);
	                    } else {
	                    	hideMask();
	                    	$.messager.alert('错误信息',result.errorMsg,'error');
	                    }
	     	    	}
	    		};
	    	ajax(json);
		});
	}
	//删除行
	function removeTr(rowInd,image_addr,image_link){
		$("#tableAdd tr").eq(rowInd).remove();
		var imgurl = $("#imagesUrl").val();
		var linkurl = $("#linksUrl").val();
		$("#imagesUrl").val( imgurl.substring(0,imgurl.indexOf(image_addr))
				+imgurl.substring(imgurl.indexOf(image_addr)+image_addr.length+2) );
		$("#linksUrl").val( linkurl.substring(0,linkurl.indexOf(image_link))
				+linkurl.substring(linkurl.indexOf(image_link)+image_link.length+2) );
		save();
	}
</script>
</head>
<body>
	<div id="bodyLayout" class="easyui-layout" data-options="fit:true">
	<!-- 上部按钮层 -->
		<div data-options="region:'north', title:'&nbsp;查询条件',closedTitle:'查询条件(点击展开)'"
			style="height: 105px; padding-top: 6px;">
				<form id="queryForm" method="post">
					<table>
						<tr>
							<td colspan="9">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">增加</a>
							    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
							    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteOne()">删除</a>
							    <input name="idKey" id="myId" style="width: 140px" type="hidden">
							</td>
						</tr>
						<tr>
						<td> 所在小区：<tag:popedomCommunitySelect id="communityId" name="communityId"></tag:popedomCommunitySelect>
						</td>
						<td  style="width:100px" class="tdRight">
								<a href="javascript:void(0)" id="searchButton" class="easyui-linkbutton" iconCls="icon-search" onclick="queryDatas()">查询</a>
							</td>
						</tr>
					</table>
				</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;链接列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
		<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-edit'" 
				style="width:400px;height:330px;padding:10px 20px" buttons="#dlg-buttons">
	        <div class="ftitle">链接信息</div>
	        <form id="fm" method="post" novalidate>
	            <div class="fitem">
	            	<input name="idKey" type="hidden">
	            </div>
	            <div class="fitem">
	            <label>所在小区：</label><tag:popedomCommunitySelect id="addCommunityId" name="communityId"></tag:popedomCommunitySelect>
	            	<label>链接名称:</label>
					<input name="chainName" class="easyui-validatebox" required="true">
					<input name="imagesUrl" id="imagesUrl" type="hidden">
					<label>链接地址:</label>
					<input id="linksUrl" name="linksUrl" class="easyui-validatebox" required="true" placeholder="http://">
					
				</div>
				<div class="fitem">
	            	<a href="javascript:void(0)" style="margin-left:40px " class="easyui-linkbutton" onclick="addImage()">添加图片</a>
				</div>				
	        </form>
   		
	    	<div id="dlg-buttons">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
	        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	    	</div>
			<!-- 上传图片form -->	
			<div id="dlg1" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-save'" 
				style="width:380px;height:178px;padding:20px 10px" buttons="#dlg-buttons1">
		    	<form id="uploadFm" method="post" novalidate>
		        	<div class="fitem">
		            	<label style="width:60px">上传图片:</label>
		            	<input type="file" id="upFile" name="upFile" class="easyui-validatebox" required="true">
					</div>
		    	</form>
			</div>
			<!-- 按钮 -->
			<div id="dlg-buttons1">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadImage()">上传</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')">取消</a>
			</div>	    	  	
		</div>
	</div>
</body>
</html>