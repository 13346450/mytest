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
	$(function(){
		tree = new dhtmlXTreeObject("treeboxbox_tree","100%","100%",-1);
		tree.setSkin('dhx_skyblue');
		tree.setImagePath("${base}/resources/javascripts/dhtmlxtree/codebase/imgs/csh_dhx_skyblue/");
		tree.enableDragAndDrop(true);
		//单击事件
	    tree.setOnClickHandler(function(id, state) {
			if(id != "0")
				parent.mainFrame.document.location = "${base}/system/SysPopedom/edit/"+ id;
		});
		//异步加载树
		tree.setXMLAutoLoading("${base}/system/SysPopedom/creatSysPopedomTree");
		tree.setDataMode("json");
		tree.loadJSON("${base}/system/SysPopedom/creatSysPopedomTree?id=-1");
	});
</script>
</head>
<body>
<table id="treeTable" style="height: 100%; width: 100%" border="0" bordercolor="#1B7EB7" cellpadding="0" cellspacing="0">
	<tr>
		<td style="height: 100%; width: 100%">
			<div id="treeboxbox_tree" style="width:220px; height:100%;background-color:#f5f5f5;border :1px solid Silver;overflow: auto"></div>
		</td>
	</tr>
</table>
</body>
</html>