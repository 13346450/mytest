<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/dhtmlxtreeHead.jsp"%>
<%@ include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${base}/resources/javascripts/app-common.js" ></script>
</head>
<body onload="loadTree();" scroll=no>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north', title:'保存操作'"
			style="height: 60px;">
			<table class="tablehead2" cellSpacing="1" align="center"
				cellPadding="0" width="100%" border="0">
				<tr>
					<td><a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-save', plain:true" onclick="save()">保存</a>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',title:'权限列表'">
			<form id="form">
				<input id="menuIds" type="hidden" name="menuIds"> <input
					id="roleId" type="hidden" name="roleId" value="${roleId}">
			</form>
			<table width="100%" height="90%" border="0" bgcolor="#ffffff"
				cellpadding="0" cellspacing="0">
				<tr valign="top" align="left">
					<td align="left">
						<div id="treeboxbox_tree"
							style="width: 100%; height: 100%; background-color: #ffffff; border: 0px solid Silver;; overflow: auto;">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
  <script>
    //加载树形结构
  var ROOT_PARENT_ID="-1";  //默认的根节点id
  var init = "start";//初始化,完了之后,就不要再初始化了
  function loadTree(){	 
	    showMask("权限列表正在加载中。。。");
    	tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",ROOT_PARENT_ID);
    	tree.setSkin('dhx_skyblue');
		tree.setImagePath("${base}/resources/javascripts/dhtmlxtree/codebase/imgs/csh_dhx_skyblue/");
     	tree.enableCheckBoxes(1);
     	tree.enableThreeStateCheckboxes(1);
     	tree.setOnOpenStartHandler(tonopen);
     	tree.setOnOpenEndHandler(tonopen2);
		//随机数,去除缓存
		var random = Math.random();
     	tree.loadJSON("${base}/system/SysPopedom/loadTreeAsXML?roleId=${roleId}&random="+random,function(aftercall){
     		//打开根结点
     		tree.openItem(1);
     		//取得所有子节点
     		var array = tree.getAllChildless();
     		if(array.length != null && array.length != 0) {
     			var arrayList = array.split(",");
		       for(var i=0;i<arrayList.length;i++){
		       	  //触发以下子节点也展开以下
		          tree.attachEvent("onOpenStart", delplus(arrayList[i]));
		       }
     		}
     		init = "end";//最后加载完成
     		tree.setOnCheckHandler(checkhandler);
     	});
     	setTimeout("hideMask();", 100);
  }
  //选择tree的checkbox时要做的动作,要同时选择/不选择crud的checkbox
  function checkhandler(id,state) {
    var array = tree.getSubItems(id);
    if(array.length != null && array.length != 0 && init == "end") {
       var arrayList = array.split(",");
       for(var i=0;i<arrayList.length;i++){
       	 checkhandler(arrayList[i],state);
       }
    }else {
      //nothing
    }
  }
  //开始展开
  function tonopen(id,mode){
	    var rcud = openPathExamples(id);
	    //设置权限
	    setRCUD(id, rcud);
		return true;
 }
 //openEnd结束展开
 function tonopen2(id,mode){
    var array = tree.getSubItems(id);
    if(array.length != null && array.length != 0 && init == "start") {
       var arrayList = array.split(",");
       for(var i=0;i<arrayList.length;i++){
       	  //触发以下子节点也展开以下
       	  tree.openItem(arrayList[i]);
       }
    }else {
      //nothing;
    }
	return true;
 }
 //打开子节点
 function delplus(id) {
	//调用dhtmlxtree的内部,tree._globalIdStorageFind(id):获得当前节点
	tree._correctPlus(tree._globalIdStorageFind(id));
	var rcud = openPathExamples(id);
	 //设置权限
	setRCUD(id,rcud);
 }
  //设置权限
 function setRCUD(id, rcud) {
 	if(rcud != "" && init == "start") {
    		if(rcud == 0) {//如果无读取权限,那么设置tree的checkbox为false
    			tree.setCheck(id,false);
    		}
    		else {//如果有读取权限,那么设置tree的checkbox为false
    			tree.setCheck(id,true);
    		}
    }else if(rcud == "" && init == "start") {//如果权限的记录为空,那么设置tree的checkbox为false
	    	tree.setCheck(id,false);
	}
 }
  //取得当前节点的权限
 function openPathExamples(itemId) {
	var rcud = tree.getUserData(itemId,"rcud");
	return rcud;
 }
 //选中CUD的时候一起现在tree的checkbox
 function checkR(check, id) {
 	if(check){
 		tree.setCheck(id,true);
 	}
 }
</script>
<script language="javascript">
function save(){
	var result = "";
	var allIds = tree.getAllCheckedBranches();
	if(allIds != "") {
		var array = allIds.split(",");
		for(var i=0; i<array.length; i++) {
			var id = array[i];
			if(id != "") {
				var r = document.getElementById(id+"_r");
				result += id + "@" + "true" +"#";
			}
		}
		result = result.substring(0, result.length-1);
	}
	document.getElementById("menuIds").value=result;
        var json={
        		url : "${base}/system/SysPopedom/update",
        		formId : "form",
        		successJSFun : addSaveSuccess
        };
        ajaxSubmitForm(json);
}
function addSaveSuccess(result){
	showMask(result.msg);
	setTimeout("hideMask();document.location.href='${base}/system/SysPopedom/edit/" + result.roleId + "';", 800);
}
</script>