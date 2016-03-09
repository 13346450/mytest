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
a:hover{ text-decoration:none;}
</style>
<script type="text/javascript">
	var postUrl = "";
	var imgId = 1;
	var rownum = 0;
	$(function() {
		$('#dataList').datagrid({
			url : '${base}/business/BizOrder/listPage',
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
		 	              {field:'ck',checkbox:true}, 
		 	             {field:'orderNumber', title:'订单号',  width:120,align:'right',  halign:'center', sortable:true}
		 	           ]],
			columns : [[
			            {field:'userName', title:'用户名称', align:'left', width:80},
			            {field:'salesName', title:'商家名称', width:80, align:'left', sortable:true},
			            {field:'shopName', title:'商铺名称', width:80, align:'left', sortable:true},
			            {field:'orderCost', title:'订单金额', width:65,align:'right'},
			            {field:'paymentTypeName', title:'付款方式', width:80,align:'center'},
			            {field:'orderStatusName', title:'订单状态', width:80,align:'center', },
			            {field:'orderDt', title:'下单日期', width:125, align:'center', sortable:true}
			            
			          ]]
		});
		$('#bodyLayout').layout('collapse','east');
		mainPageView();
		setInitDate();
	});
	function mainPageView() {
		bindDatagrid("dataList", true);
		$("#searchButton").linkbutton('enable');//查找按钮可使用
		$("#toolbar2").hide();
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
	    	$('#dataList').datagrid('clearChecked');
    	 }
    }
	 /* 删除一条记录  */
     function deleteOne(){
         var row = $('#dataList').datagrid('getSelected');
         if(!row){
         	$.messager.alert('提示信息',"请选中需要删除的广告。",'error');
         	return;
         }
 		$.messager.confirm('确认','确定要删除选中的广告吗?',function(r){
 		    if (!r){return;}
 	    	var json = {
 	    		    url: "${base}/business/BizAdvert/delete/" + row.idKey,
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
   //删除多条
 	function deleteMulti() {
 		var checkedIds = [];
 		var checkedItems = $('#dataList').datagrid('getChecked');
 		if(checkedItems.length==0){
 			$.messager.alert('提示信息', "请选中需要删除的订单。", 'error');
 			return;
 		}
 		$.each(checkedItems, function(index, item) {
 			checkedIds.push(item.idKey);
 		});
 		var deleteIds = checkedIds.join(",");
 		$("#deleteIds").val(deleteIds);
 		//console.log( $("#deleteIds").val());
 		$.messager
 				.confirm(
 						'确认',
 						'确定要删除选中的 ' + checkedItems.length + ' 个商品吗?',
 						function(r) {
 							if (!r)
 								return;
 							var json = {
 								url : "${base}/business/BizOrder/deleteMulti",
 								formId : "fm2",
 								successJSFun : function(result) {
 									if (result.successMsg) {
 										showMask(result.successMsg);
 										setTimeout(
 												"hideMask(); $('#dataList').datagrid('reload'); ",
 												DEFAULT_DELAY_MS);
 									} else {
 										hideMask();
 										$.messager.alert('错误信息',
 												result.errorMsg, 'error');
 									}
 								}
 							};
 							ajaxSubmitForm(json);
 						});
 	}
	/* 新增一条记录 */
 	function add(){
 		$('#fm').show();
 		if($("#tableAdd").find("tr").length>1){
			$("#tableAdd tr:not(:first)").empty();
		}
 		$("#idKey").val("");
		$("#imagesUrl").val("");
		$("#linksUrl").val("");
		$("#advVersion").val("");
		editFormEnable(true);
		$('#fm').form('enableValidation');
		postUrl = "${base}/business/BizAdvert/insert";
 	}
 	/*显示订单详情 */
 	function view(){
 		var row = $('#dataList').datagrid('getSelected');
         if (row){
        	 var orderId=row.idKey;
        	 $("#orderId").val(orderId);
        	 $('#detailsDataList').datagrid({
     			url : '${base}/business/BizOrderDetails/listPage',
     			queryParams : formToJson("fml"),
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
     			/* onClickRow : clickRow,
     		 	onLoadSuccess :loadSuccess, */
     		 	idField : 'idKey',
     		 	/* frozenColumns:[[
     		 	              {field:'ck',checkbox:true}, 
     		 	             {field:'orderNumber', title:'订单号',  width:120,  halign:'center', sortable:true}
     		 	           ]], */
     			columns : [[
     			            {field:'goodsName', title:'商品名称', align:'center', width:80},
     			            {field:'goodsUnitPrice', title:'商家单价', width:80, align:'center', sortable:true},
     			            {field:'goodsQuantity', title:'商品数量', width:80,align:'center'},
     			            {field:'goodsCost', title:'商品总额', width:65,align:'center'},
     			            
     			          ]]
     		});
        	 /* $('#fm').form('load',row);
        	 generateImageEdit(row.imagesUrl, row.linksUrl);
        	 editFormEnable(false);
             postUrl = '${base}/business/BizAdvert/update'; */
         }else{
         	$.messager.alert('提示信息',"请选中要编辑的广告",'error');
         }
 	}
 	/* 保存数据 */
	function save() {
		if (!$('#fm').form('validate')) {
			return;
		}
		if( "" == $("#imagesUrl").val() ){
			$.messager.alert('提示信息', "请添加图片！", "error");
			return;
		}
		var json = {
				url : postUrl,
				formId : "fm",
				maskId : "ff",
				successJSFun :  function(result) {
					if (result.flag) {
						showMask(result.successMsg, "fm"); 
				       	setTimeout("hideMask(); $('#dataList').datagrid('reload'); editFormEnable(false);", DEFAULT_DELAY_MS);
					} else {
						hideMask();
						$.messager.alert('提示信息', result.successMsg, "error");
					}
				}
		};
		ajaxSubmitForm(json);
	}
 	function cancel(){
 		editFormEnable(false);
 	}
 	//设置表单是否可编辑
 	function editFormEnable(bool) { 			
		bindDatagrid("dataList", !bool);
		if(bool){
			$("#toolbar1").hide();
 			$("#toolbar2").show();
 			$("#searchButton").linkbutton('disable');
 			$("a.adv-linkbutton").show();
 		}else{
			$("#toolbar1").show();
 			$("#toolbar2").hide();
 			$("#searchButton").linkbutton();
 			$("a.adv-linkbutton").hide();
 			if( $("#auditId").val() == "" ){
 				$("#modifyButton").linkbutton("enable");
 				$("#deleteButton").linkbutton("enable");
 			}else{
 				$("#modifyButton").linkbutton("disable");
 				$("#deleteButton").linkbutton("disable");
 			}
		}
	}
	function queryDatas() {
		$('#dataList').datagrid('load', formToJson("queryForm"));
	}
	function addImage(){
		$('#dlg').dialog('open').dialog('setTitle','上传图片');
        $('#uploadFm').form('clear');
	}
	// 上传图片
	function uploadImage(){
	    if($("#imageUrl").val() == ""){
	    	$.messager.alert('错误信息',"链接地址不能为空",'error');
	    	return;
	    }
	    if($("#upFile").val() == ""){
	    	$.messager.alert('错误信息',"上传文件不能为空",'error');
	    	return;
	    }
		var json = {
	 		    url: "${base}/business/BizAdvert/uploadFile?link="+$("#imageUrl").val(),
	 		    fileElementId: "upFile",
	 		    maskId : "dlg",
	 		    success: function(result){
	 		    	 var res = eval ("(" + result + ")");
		     	      if (res.successMsg){
	     	    		showMask(res.successMsg);
	        	    	setTimeout("hideMask(); $('#dlg').dialog('close'); addImagesTr('"
	        	    					+res.addr+"','"+res.link+"');", DEFAULT_DELAY_MS);
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
							<td style="width:55px">订单日期: </td>
							<td><input class="easyui-datebox" style="width: 100px"
								name="startDatetime" id="startDatetime">
								&nbsp;至&nbsp;
								<input class="easyui-datebox" style="width: 100px"
								name="endDatetime" id="endDatetime"></td>
								<td style="padding-left: 10px">商家名称： <input name="userName" class="easyui-textbox" data-options="width:100">
							</td>
							<td class="tdRight">&nbsp;商铺名称:</td>
							<td><input name="shopName"  class="easyui-textbox" data-options="width:100"></td>
							<td class="tdRight">&nbsp;状态:</td>
							<td>
								<select class="easyui-combobox" name="orderStatus"  data-options="panelHeight:'auto'" style="width:103px">
									<option value="-1">全选</option>
									<option value="0">等待商家接单</option>
									<option value="1">买家取消订单</option>
									<option value="2">商家已接单</option>
									<option value="3">商家拒绝该单</option>
									<option value="4">正在配送中</option>
									<option value="5">交易完成</option>
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
		<div data-options="region:'center',title:'&nbsp;订单列表&nbsp;&nbsp;&nbsp;<a href=\'#\'  class=\'easyui-linkbutton\' onclick=\'deleteMulti()\'><img src=\'${base}/resources/images/edit_remove.png\' style=\'border: 0px\'  height=\'12px\'/><span style=\'font-weight:normal\'>&nbsp;删除</span></a>'">
			<table id="dataList"></table>
		</div>
		<!-- 订单详情界面 -->
		 <div data-options="region:'east',title:'订单详细信息',split:false, collapsed:true"
			style="width:380px;">
			<div id="ff" class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'">
				 <table id="detailsDataList"></table> 
   			</div>
		</div> 
	</div>
	<div style="display: none;">
			<form id="fml" method="post">
				<input name="orderId" id="orderId">
			</form>
	</div>
	<div style="display: none;">
			<form id="fm2" method="post">
				<input name="deleteIds" id="deleteIds">
			</form>
	</div>
	<div id="tt"  >  
        <a href="#" class="easyui-linkbutton"  plain="true" iconCls="icon-cancel" onclick="deleteMulti()">删除</a>   
    </div> 
</div>
</body>
</html>