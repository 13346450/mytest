<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
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
.adv-linkbutton {
	font-size: 12px;
}
</style>
<script type="text/javascript">
	var postUrl = "";
	var imgId = 1;
	var rownum = 0;
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizAdvert/listPage',
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
			onClickRow : clickRow,
		 	onLoadSuccess :loadSuccess,
		 	idField : 'idKey',
		 	frozenColumns:[[
		 	             /* {field:'ck',checkbox:true}, */
		 	             {field:'advVersion', title:'版本号',  width:60,  halign:'center', sortable:true}
		 	           ]],
			columns : [[
			            {field:'makerId', title:'制单人', align:'center', width:60, hidden:true},
			            {field:'makerNm', title:'制单人', align:'center', width:65},
			            {field:'makerDt', title:'制单日期', width:75, align:'center', sortable:true},
			            {field:'auditId', title:'审核人', width:60,align:'center', hidden:true},
			            {field:'auditNm', title:'审核人', width:65,align:'center'},
			            {field:'auditDt', title:'审核日期', width:75, align:'center', sortable:true},
			            {field:'publishId', title:'发布人', width:60,align:'center', hidden:true},
			            {field:'publishNm', title:'发布人', width:65,align:'center'},
			            {field:'publishDt', title:'发布日期', width:75, align:'center', sortable:true},
			            {field:'advStatus', title:'状态', width:60, align:'center', hidden:true},
			            {field:'advStatusNm', title:'状态', width:60, align:'center', sortable:true},
			            {field:'imagesUrl', title:'图片地址', width:180, sortable:true},
		 	            {field:'linksUrl', title:'链接地址', width:180, sortable:true}
			          ]]
		});
		$('#bodyLayout').layout('collapse','east');
		mainPageView();
		setInitDate();
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
	function clickRow(rowIndex, rowData) {
		rownum = rowIndex;
		view();
		if(!$('#bodyLayout').layout('panel','north').panel("options").collapsed){//折叠north面板
			$('#bodyLayout').layout('collapse','north');
		}; 
		if($('#bodyLayout').layout('panel','east').panel("options").collapsed){//展开east面板
			$('#bodyLayout').layout('expand','east');
		}
	}
	 /*表格加载成功后调用的方法 */
     function loadSuccess(){
    	 var rows = $('#dataList').datagrid('getRows');
    	 if(rows.length > 0){
	    	$('#dataList').datagrid('selectRow', rownum);
	    	view();
    	 }else{
    		$('#fm').hide();
    	 }
    }
	 /* 审核  */
     function audit(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要审核的广告。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要审核选中的广告吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizAdvert/auditAdvert/" + row.idKey,
 	    		    success: function(result){
 		     	    	if (result.successMsg){
 		     	    		showMask(result.successMsg);
 		     	    		rownum = 0;
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
	 /*取消审核*/
     function cancelAudit(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要取消审核的广告。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要取消审核选中的广告吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizAdvert/cancelAuditAdvert/" + row.idKey,
 	    		    success: function(result){
 		     	    	if (result.successMsg){
 		     	    		showMask(result.successMsg);
 		     	    		rownum = 0;
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
 	function view(){
 		var row = $('#dataList').datagrid('getSelected');
         if (row){
        	 $('#fm').form('load',row);
        	 generateImageEdit(row.imagesUrl, row.linksUrl);
        	 editFormEnable(false, row.publishId==null ? true:false);
             postUrl = '${base}/business/BizAdvert/update';
         }else{
         	$.messager.alert('提示信息',"请选中要编辑的广告",'error');
         }
 	}
 	//设置表单是否可编辑
 	function editFormEnable(bool,pbool) {
		bindDatagrid("dataList", !bool);
		if(bool){
			$("#toolbar1").hide();
 			$("#searchButton").linkbutton('disable');
 			$("a.adv-linkbutton").show();
 		}else{
			$("#toolbar1").show();
 			$("#searchButton").linkbutton();
 			$("a.adv-linkbutton").hide();
 			if( $("#auditId").val() == "" ){
 				$("#auditButton").linkbutton("enable");
 				$("#cancelAuditButton").linkbutton("disable");
 			}else{
 				$("#auditButton").linkbutton("disable");
 				if(pbool){
 					$("#cancelAuditButton").linkbutton("enable");
 				}else{
 					$("#cancelAuditButton").linkbutton("disable");
 				}
 			}
		}
	}
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
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
	//点击行时，显示图片列表
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
	}
	//添加一个图片行
	function addImagesTr(image_addr,image_link){
	    var newRow = "<tr>\n"
	    +"<td class=\"tdLeft\" style=\"padding: 3px\">\n"
	    +"<a href=\"javascript:void(0)\" class=\"adv-linkbutton\" onclick=\"removeImage(this,'"+image_addr+"','"+image_link+"')\">删除本图</a>\n"
	    +"</td>\n"
	    +"<td class=\"tdLeft\" style=\"padding: 3px\">\n"
	    +"<img id=\"adv_img_"+imgId+"\" src=\"${base}/"+image_addr+"\" width=\"300px\" height=\"50px\">\n"
		+"	<br><a href=\""+image_link+"\" target=\"_blank\">"+image_link+"</a>\n"
		+"</td>\n"
		+"</tr>\n";
		$("#tableAdd tr:last").after(newRow);
		$("#imagesUrl").val( $("#imagesUrl").val() + image_addr + "@@"  );
		$("#linksUrl").val( $("#linksUrl").val() + image_link + "@@"  );
		showTip("adv_img_"+imgId,"${base}/"+image_addr);
		imgId++;
	}
	//删除一个图片行
	function removeImage(obj,image_addr,image_link){
		$.messager.confirm('确认','删除图片后无法恢复，确定吗?',function(r){
 		    if (!r){return;}
			var json = {
	    		    url: "${base}/business/BizAdvert/deleteImage?imageAddr="+image_addr,
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
			style="height: 70px; padding-top: 6px;">
				<form id="queryForm" method="post">
					<table>
						<tr>
							<td style="width:75px" class="tdRight">查询条件： </td>
							<td style="width:55px">制单日期: </td>
							<td><input class="easyui-datebox" style="width: 100px"
								name="startDatetime" id="startDatetime">
								&nbsp;至&nbsp;
								<input class="easyui-datebox" style="width: 100px"
								name="endDatetime" id="endDatetime"></td>
							<td style="width:50px" class="tdRight">状态</td>
							<td>
								<select class="easyui-combobox" name="advStatus"  data-options="panelHeight:'auto'" style="width:80px">
									<option value="-1">全选</option>
									<option value="0">未审核</option>
									<option value="1">已审核</option>
									<option value="2">已发布</option>
								</select>
							</td>
							<td  style="width:100px" class="tdRight">
								<a href="javascript:void(0)" id="searchButton" class="easyui-linkbutton" iconCls="icon-search" onclick="queryDatas()">查询</a>
							</td>
						</tr>
					</table>
				</form>
		</div>
		<!-- 下部列表层 -->
		<div data-options="region:'center',title:'&nbsp;广告列表'">
			<table id="dataList"></table>
		</div>
		<!-- 编辑表单界面 -->
		<div data-options="region:'east',title:'广告详细信息',split:false, collapsed:true"
			style="width:450px;">
			<div id="ff" class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'">
					<form id="fm" method="post" novalidate>
						<table class="tableDetail" id="tableAdd" style="width:99%;">
							<tr>
								<td class="tdLeft" style="width: 50px;padding: 3px">版本号:</td>
								<td class="tdLeft" style="padding: 3px">
									<input name="idKey" id="idKey" type="hidden">
									<input name="imagesUrl" id="imagesUrl" type="hidden">
									<input name="linksUrl" id="linksUrl" type="hidden">
									<input name="auditId" id="auditId" type="hidden">
									<input name="advStatus" type="hidden">
									<input name="advVersion" id="advVersion" class="easyui-validatebox" required="true" readonly="readonly">
								</td>
							</tr>
						</table>
				<div style="margin-top: 8px;margin-left: 10px">
				</div>
	        </form>
   			</div>
		    <div data-options="region:'south'" style="height: 34px;padding-top: 3px">
			    <div id="toolbar1" style="text-align: center;">
					<a id="auditButton" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="audit()">审核</a>
					<a id="cancelAuditButton" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="cancelAudit()">取消审核</a>
			    </div>
			</div>
		</div>
		<!-- 上传图片form -->	
		<div id="dlg" class="easyui-dialog"  data-options="modal:true,closed:true,iconCls:'icon-save'" 
			style="width:380px;height:178px;padding:20px 10px" buttons="#dlg-buttons">
		    <form id="uploadFm" method="post" novalidate>
		        <div class="fitem">
		            <label style="width:60px">上传图片:</label>
		            <input type="file" id="upFile" name="upFile" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label style="width:60px">链接地址:</label>
					<input type="text" id="imageUrl" name="imageUrl" style="width: 220px" placeholder="http://">
				</div>
		    </form>
		</div>
		<div id="dlg-buttons">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok">上传</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
	</div>
</div>
</body>
</html>