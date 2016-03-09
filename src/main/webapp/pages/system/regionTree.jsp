<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/dhtmlxtreeHead.jsp"%>
<%@ include file="../common/head.jsp"%>
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
	var selectNodeId = null;
	var isContinue = true;
	$(function(){
		 /**window.onresize = autoResize;
		autoResize(); **/
		tree = new dhtmlXTreeObject("treeboxbox_tree","100%","100%",-1);
		tree.setSkin('dhx_skyblue');
		tree.setImagePath("${base}/resources/javascripts/dhtmlxtree/codebase/imgs/csh_dhx_skyblue/");
		tree.enableDragAndDrop(true);
		tree.enableDragAndDropScrolling(true);
		//单击事件
		tree.setOnClickHandler(function(id,state){parent.mainFrame.document.location="${base}/system/SysRegion/edit/" + id;});
		
		//拖拽事件
		tree.attachEvent("onBeforeDrag", onBeforeDrag);
		tree.attachEvent("onDrag", onDrag);
		tree.attachEvent("onDragIn", onDragIn);
		tree.attachEvent("onDrop", onDrop);
		tree.setXMLAutoLoading("${base}/system/SysRegion/creatSysRegionTree");
		tree.setDataMode("json");
		tree.loadJSON("${base}/system/SysRegion/creatSysRegionTree?id=" + (-1));
	});
	
	function autoResize(){
		var height = $(window.frameElement).height();
		$("#treeTable").height(height-10);
	}
	
	//occurs when item's dragging starts (the item is selected and the mouse is moving);
	function onBeforeDrag(currentNodeId){
		return true;
	}
	
	/*	occurs when the item was dragged and dropped on some other item, but before item's moving is processed;
	  	sourceId - id of the source item;
	  	targetId - id of the target item;
	  	siblingId - if the node is dropped as a sibling, id of the item before which the source node will be inserted;
		sObject - source tree object;
		tObject - target tree object. 
	*/
	function onDrag(sourceId, targetId, siblingId, sObject, tObject){
		if(-1 == targetId){
			return false;
		}

		var state = tree.getOpenState(targetId);
		if(!state){
			tree.openItem(targetId);
		}
		var beforeParentId = tree.getParentId(sourceId);
		$.ajax({
			url : "${base}/system/SysRegion/saveDragAndDrop",
			async : false,
			dataType : "json",
			data : {"sourceId" : sourceId, "parentId" : beforeParentId, "targetId" : targetId},
    		error : dragAndDropSaveErr
		});
		if(isContinue){
			return true;
		}else{
			return false;
		}
	}
	//occurs when the item is dragged over some target the item can be dropped to;
	function onDragIn(dId,lId,sObject,tObject){
		return true;
	}
	
	//occurs when drag-and-drop had already been processed; also occurs when the nodes are moved programmatically;
	function onDrop(sourceId, targetId, siblingId, sObject, tObject){
		
	}
	 
	function RefreshDTreeInEdit(parentId, nodeId, cdNm) {
		/* refreshItem(parentId);
		selectNodeId = nodeId;
		treeLoopInove = window.setInterval(loopSearchNode, 20); */
		tree.setItemText(nodeId, cdNm);
		tree.selectItem(nodeId); 
	}
	
	function RefreshDTreeInAdd(parentId, nodeId, cdNm) {
		/* refreshItem(parentId);
		selectNodeId = nodeId;
		treeLoopInove = window.setInterval(loopSearchNode, 20); */
		var state = tree.getOpenState(parentId);
		if(state){
			tree.insertNewChild(parentId, nodeId, cdNm, 0,0,0,0,"SELECT,CALL,CHECKED");
		}else{
			refreshItem(parentId);
			selectNodeId = nodeId;
			treeLoopInove = window.setInterval(loopSearchNode, 20);
		}
	}
	
	function RefreshDTreeInDelete(nodeId) {
		tree.deleteItem(nodeId, true);
	}
	
	function refreshItem(itemId){
		tree.refreshItem(itemId);
	}
	
	function loopSearchNode() {
		var item = tree.getItemText(selectNodeId);
		if (item != 0) {
			tree.selectItem(selectNodeId);
			window.clearInterval(treeLoopInove);
		}
	}
	
	function hasChildren(nodeId){
		 return tree.hasChildren(nodeId);
	}
	
	function dragAndDropSaveErr(request, msg, errObj){
		if(msg.indexOf("parsererror") > -1){
			return;
		}
		if(undefined != request && undefined != request.responseText && "" != request.responseText && null != request.responseText){
			alert("移动节点失败，无法保存数据！" + request.responseText);
		}else{
			alert("移动节点失败，无法保存数据！\n错误类型：" + msg);
		}
		isContinue = false;
	}
</script>
</head>
<body>
<table id="treeTable" style="height: 100%; width: 100%" border="0" bordercolor="#1B7EB7" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<div id="treeboxbox_tree" style="width:200px; height:100%;background-color:#f5f5f5;border :1px solid Silver;overflow: auto"></div>
		</td>
	</tr>
</table>
</body>
</html>