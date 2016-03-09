<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.dnake.utils.SessionBean"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
  
   /*  登录拦截判断 */   
   if (null == session.getAttribute("sessionBean")) {
		response.sendRedirect(request.getContextPath() + "/");
		return;
	}   
   
   Cookie[] cookies = request.getCookies();
   String them = "default";
   if(null!=cookies){
       for(Cookie cookie : cookies){
           if(null != cookie.getName() && cookie.getName().equals("cs-skin")){
        	   them = cookie.getValue();
           }
       }
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="systemName" /></title>
<script src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
<link id="easyuiThemes" rel="stylesheet" type="text/css"
	href="${base}/resources/javascripts/jquery-easyui/themes/<%=them%>/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${base}/resources/javascripts/jquery-easyui/themes/icon.css">
<script
	src="${base}/resources/javascripts/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<script src="${base}/resources/javascripts/app-common.js"></script>
<script type="text/javascript"
	src="${base}/resources/javascripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<style type="text/css">
.cs-north-logo {
	height: 50px;
	margin: 20px 0px 0px 5px;
	display: inline-block;
	font-size: 25px;
	font-weight: bold;
	text-decoration: none;
	float: left;
}

.cs-north-GWlogo {
	height: 36px;
	margin: 10px 5px 20px 10px;
	display: inline-block;
	font-size: 25px;
	font-weight: bold;
	text-decoration: none;
	float: left;
}

.cs-north-content {
	margin: 0px 1px 1px 1px;
	display: inline-block;
	font-size: 12px;
	text-decoration: none;
	float: right;
}

.ui-skin-nav {
	float: right;
	margin-right: 2px;
	margin-top: 10px;
	list-style: none outside none;
}

.ui-skin-nav .li-skinitem {
	float: left;
	font-size: 12px;
	margin-left: 10px;
	text-align: center;
}

.ui-skin-nav .li-skinitem span {
	cursor: pointer;
	width: 10px;
	height: 10px;
	display: inline-block;
}

.ui-skin-nav .li-skinitem span.cs-skin-on {
	border: 1px solid #FFFFFF;
}

.ui-skin-nav .li-skinitem span.gray {
	background-color: gray;
}

.ui-skin-nav .li-skinitem span.default {
	background-color: blue
}
/* .ui-skin-nav .li-skinitem span.bootstrap{background-color:gray;} */
.ui-skin-nav .li-skinitem span.black {
	background-color: black;
}
/* .ui-skin-nav .li-skinitem span.metro{background-color:#FFE57E;} */
.ui-skin-nav .li-skinitem span.cupertino {
	background-color: #d7ebf9
}

.ui-skin-nav .li-skinitem span.dark-hive {
	background-color: #333
}

.ui-skin-nav .li-skinitem span.pepper-grinder {
	background-color: #eceadf
}

.ui-skin-nav .li-skinitem span.sunny {
	background-color: #D19405
}
</style>
<script type="text/javascript">
	var testTask;
	var themes = {
		'gray' : '${base}/resources/javascripts/jquery-easyui/themes/gray/easyui.css',
		'black' : '${base}/resources/javascripts/jquery-easyui/themes/black/easyui.css',
		'bootstrap' : '${base}/resources/javascripts/jquery-easyui/themes/bootstrap/easyui.css',
		'default' : '${base}/resources/javascripts/jquery-easyui/themes/default/easyui.css',
		'metro' : '${base}/resources/javascripts/jquery-easyui/themes/metro/easyui.css',
		'cupertino' : '${base}/resources/javascripts/jquery-easyui/themes/cupertino/easyui.css',
		'dark-hive' : '${base}/resources/javascripts/jquery-easyui/themes/dark-hive/easyui.css',
		'pepper-grinder' : '${base}/resources/javascripts/jquery-easyui/themes/pepper-grinder/easyui.css',
		'sunny' : '${base}/resources/javascripts/jquery-easyui/themes/sunny/easyui.css'
	};
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '两次密码输入不一样'
		}
	});
	$(function() {
		//更换首页logo
		if ('${pcIndexLogo}' == '') {
			$('#indexTopLogo').attr("src",
					'${base}/resources/images/index_logo.png');
		} else {
			$('#indexTopLogo').attr("src", '${base}/${pcIndexLogo}');
		}

		if ("${sessionBean.isModify}" == "0") {
			$('#ModifyPassWordDlg').dialog('open').dialog('setTitle', '修改密码');
		}
		$
				.ajax({
					type : 'GET',
					dataType : "text",
					url : '${base}/system/SysMenu/listAccordionMenu',
					success : function(data) {
						var jsonData = eval('(' + data + ')');
						$
								.each(
										jsonData,
										function(key, obj) {
											var markup = '<ul id="'
													+ obj.idKey
													+ '" class="easyui-tree" data-options="lines:true, dnd:true, animate:true, onClick:function(node){fun(node, \''
													+ obj.idKey
													+ '\');}" url="${base}/system/SysMenu/getMenuTree/'
													+ obj.idKey + '"></ul>';
											//var markup = '<ul id="' + obj.id + '" class="easyui-tree" data-options="lines:true, onClick:function(node){fun(node, \'' + obj.id + '\');}" url="${base}/system/SysUser/getMenuTree"></ul>';
											var json = {
												title : obj.cdNm,
												content : markup,
												selected : false
											};
											if (key == 0) {
												json.selected = true;
											}
											$('#menu').accordion('add', json);
										});
					}
				});

		$('#tt').datagrid({
			onLoadSuccess : function(data) {
				$.messager.show({
					title : 'Info',
					msg : ' 成功加载 ' + data.rows.length + ' 条数据！'
				});
			}
		});
		var skins = $('.li-skinitem span').click(function() {
			var $this = $(this);
			if ($this.hasClass('cs-skin-on'))
				return;
			$.messager.alert('提示信息', '主题设置成功，部分页面将在刷新后生效！', 'warning');
			skins.removeClass('cs-skin-on');
			$this.addClass('cs-skin-on');
			var skin = $this.attr('rel');
			$('#easyuiThemes').attr('href', themes[skin]);
			setCookie('cs-skin', skin, 30);
		});
		$('#quitLogin').click(function() {
			window.location.href = "${base}/loginPage?msg=";
		});
		$('#switchUser').click(function() {
			$('#switchUserDlg').dialog('open').dialog('setTitle', '切换登陆用户');
			$('#switchForm').form('clear');
		});
		$('#switchForm').submit(function(e) {
		});
		$('#switchSubmit').click(function() {
			if (!$('#switchForm').form('validate')) {
				return;
			}
			$('#switchForm').submit();
		});
		$('#ModifyPassWordSubmit').click(function() {
			ModifyPassWordsave();
		});
		$('#file_upload').click(function() {
			addTab("文件上传测试demo", "${base}/pages/mainframe/fileupload.jsp");
		});

		//$('#box').combobox('setValue',0).combobox('setText',"sss");		

		/*
		refreshStatus();
		setInterval(refreshStatus, 5 * 60 * 1000);

		window.moveTo(0, 0);
		window.resizeTo(screen.availWidth, screen.availHeight); */
		//绑定tabs的右键菜单
		$("#tabs").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				$('#tabsMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});

		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				CloseTab(this, item.name);
			}
		});

		//几个关闭事件的实现
		function CloseTab(menu, type) {
			var curTabTitle = $(menu).data("tabTitle");
			var tabs = $("#tabs");

			if (type === "close") {
				tabs.tabs("close", curTabTitle);
				return;
			}

			var allTabs = tabs.tabs("tabs");
			var closeTabsTitle = [];
			$.each(allTabs, function() {
				var opt = $(this).panel("options");
				if (opt.closable && opt.title != curTabTitle
						&& type === "Other") {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === "All") {
					closeTabsTitle.push(opt.title);
				}
			});

			for (var i = 0; i < closeTabsTitle.length; i++) {
				tabs.tabs("close", closeTabsTitle[i]);
			}
		}

	});

	function ModifyPassWordsave() {
		if ($("#ModifyPassWordForm").form('validate')) {
			var json = {
				url : "${base}/system/SysUser/modifyPwd",
				formId : "ModifyPassWordForm",
				successJSFun : ModifyPassWordSuccess,
				maskId : "ModifyPassWordDlg"
			};
			ajaxSubmitForm(json);
		}
	}
	function ModifyPassWordSuccess(result) {
		if (result.flag) {
			showMask(result.Msg, 'ModifyPassWordDlg');
			setTimeout("hideMask();$('#ModifyPassWordDlg').dialog('close')",
					800);
		} else {
			$.messager.alert('提示信息', result.Msg, 'error');
		}
	}
	function fun(node, treeid) {
		if (!$("#" + treeid).tree('isLeaf', node.target))
			return;
		addTab(node.text, node.attributes);
	}

	function addTab(title, url) {
		if ($('#tabs').tabs('exists', title)) {
			$('#tabs').tabs('select', title);//选中
			//刷新
			/* var currTab = $('#tabs').tabs('getSelected');
			var url = $(currTab.panel('options').content).attr('src');
			if(url != undefined && currTab.panel('options').title != '首页') {
				$('#tabs').tabs('update',{
					tab:currTab,
					options:{
						content:createFrame(url)
					}
				})
			} */
		} else {
			var content = createFrame(url);
			$('#tabs').tabs('add', {
				title : title,
				content : content,
				closable : true
			});
		}
		tabClose();
	}

	function addTabAndReflesh(title, url) {
		if ($('#tabs').tabs('exists', title)) {
			$('#tabs').tabs('select', title);//选中
			//刷新
			var currTab = $('#tabs').tabs('getSelected');
			if (url != undefined && currTab.panel('options').title != '首页') {
				$('#tabs').tabs('update', {
					tab : currTab,
					options : {
						content : createFrame(url)
					}
				});
			}
		} else {
			$('#tabs').tabs('add', {
				title : title,
				content : createFrame(url),
				closable : true
			});
		}
		tabClose();
	}

	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
		return s;
	}

	function tabClose() {
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function() {
			var subtitle = $(this).children(".tabs-closable").text();
			$('#tabs').tabs('close', subtitle);
		});
	}
	function test() {
		alert($("#aa").combotree("getValue") + $("#aa").combotree("getText"));
		$("#aa").combotree('setValue', 519722).combotree('setText', "会所");
		alert($("#aa").combotree("getValue") + $("#aa").combotree("getText"));
	}

	function switchUser() {
		event.cancelBubble = true;
		if (event.keyCode == 13) {
			$("#switchSubmit").click();
		}
	}

	function testSend() {
		var json = {
			url : "${base}/sendTest",
			success : function(json) {
				$.messager.alert('提示信息', json.successMsg, 'warning');
				hideMask();
			}
		};
		ajax(json);
	}

	function loadFilter(data) {
		var fil = [ {} ];
		fil[0] = {
			"id" : "0",
			"text" : "请选择"
		};
		fil[1] = data[0];
		return fil;
	}

	/*  清零 added by ts 2013-11-27*/
	function resetTotal() {
		$.ajax({
			type : 'GET',
			dataType : "json",
			cache : false,
			url : "${base}/resetReceivedTotalSize",
			success : function(json) {
				$("#centerPrompt").html(json.successMsg);
			}
		});
	}
	//测试极光推送
	function startTestJPush() {
		testTask = setInterval("pushTask()", ($('#second').val()) * 1000);
	}
	function pushTask() {
		$.post("${base}/appservice/mobileLogin", {
			cmd : 98,
		}, function(data, status) {

		});
	}
	function stopTestJPush() {
		clearInterval(testTask);
	}
	function myshow() {
		document.getElementById('change-color').style.display = "block";
		document.getElementById('skincolor').style.background = "#eaf2ff";
		document.getElementById('skincolor').style.color = "#000000";
		document.getElementById('change-color').onmouseover = function() {
			document.getElementById('change-color').style.display = "block";
			document.getElementById('skincolor').style.background = "#eaf2ff";
			document.getElementById('skincolor').style.color = "#000000";
		};
	}
	function myhide() {
		document.getElementById('change-color').style.display = "none";
		document.getElementById('change-color').onmouseout = function() {
			document.getElementById('change-color').style.display = "none";
			document.getElementById('skincolor').style.background = "#4ba0c8";
			document.getElementById('skincolor').style.color = "#FFFFFF";
		};
		document.getElementById('skincolor').onmouseout = function() {
			document.getElementById('change-color').style.display = "none";
			document.getElementById('skincolor').style.background = "#4ba0c8";
			document.getElementById('skincolor').style.color = "#FFFFFF";
		};
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'north',split:true,border:false"
		style="height: 80px; background-color: #4ba0c8;">
		<div class="cs-north-GWlogo" style="width: 420px">
			<table>
				<tr>
					<td><img id="indexTopLogo" src=""></td>
					<td style="color: #FFFFFF">后端管理平台</td>
				</tr>
			</table>
		</div>
		<div class="cs-north-content">
			<table width="100%">
				<tr>
					<td align="right">
						<ul class="ui-skin-nav" style="width: 100%">
							<li style="float: left; margin: 7px 0 0 100px;"><a
								href="javascript:void(0)"  style="color: #FFFFFF"
								class="eui-lable" plain="true">${sessionBean.deptName}
									：${sessionBean.userName}&nbsp;(${sessionBean.roleName})</a></li>
							<li id="skin"
								style="float: left; margin-left: 30px; width: 100px;"><a
								id="skincolor" href="javascript:void(0)" id="switchUser"
								class="easyui-linkbutton afont" iconCls="icon-change"
								plain="true" onmousemove="myshow()" onmouseout="myhide()">换肤</a>
								<div id="change-color"
									style="width: 100px; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px; display: none; padding-top: 5px;">
									<div style="width: 100px; height: 15px; margin-left: 55px;">
										<ul>
											<li class="li-skinitem"
												style="text-decoration: none; list-style: none; float: left;"
												title="水晶蓝（默认）"><span class="cupertino" rel="cupertino"></span></li>
											<li class="li-skinitem"
												style="text-decoration: none; list-style: none; float: left; margin-left: -25px;"
												title="蓝"><span class="default" rel="default"></span></li>
											<!--<li class="li-skinitem"  style="text-decoration:none;list-style:none;float:left;margin-left:-38px;" title="灰"><span class="gray"
								rel="gray"></span></li>-->
											<!-- <li class="li-skinitem"  style="float:left;text-decoration:none;list-style:none;" title="bootstrap"><span class="bootstrap" rel="bootstrap"></span></li> -->
											<li class="li-skinitem"
												style="text-decoration: none; list-style: none; float: left; margin-left: -40px;"
												title="黑"><span class="black" rel="black"></span></li>
											<!-- <li class="li-skinitem"  style="float:left;text-decoration:none;list-style:none;" title="metro"><span class="metro" rel="metro"></span></li> -->
											<li class="li-skinitem"
												style="text-decoration: none; list-style: none; float: left; margin-left: -55px;"
												title="纹理黑"><span class="dark-hive" rel="dark-hive"></span></li>
											<li class="li-skinitem"
												style="text-decoration: none; list-style: none; float: left; margin-left: -70px;"
												title="磨砂"><span class="pepper-grinder"
												rel="pepper-grinder"></span></li>
											<li class="li-skinitem"
												style="text-decoration: none; list-style: none; float: left; margin-left: -85px;"
												title="黄"><span class="sunny" rel="sunny"></span></li>
										</ul>
									</div>
								</div></li>
							<li style="float: left; margin-left: 20px;"><a
								href="javascript:void(0)" id="switchUser"
								class="easyui-linkbutton afont" iconCls="icon-reload"
								plain="true">切换用户</a></li>
							<li style="float: left; margin-left: 10px;"><a
								href="javascript:void(0)" id="quitLogin"
								class="easyui-linkbutton afont" iconCls="icon-redo" plain="true">退出</a></li>
						</ul>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div region="west" border="true" split="true" title="导航栏"
		class="cs-west" style="width: 150px">
		<div id="menu" class="easyui-accordion" border="false" fit="true">
		</div>
	</div>
	<!-- <div data-options="region:'east',split:true,title:'East'" style="width: 200px; padding: 10px;"></div> -->
	<div data-options="region:'south',split:true,border:true"
		style="height: 36px; padding: 0px; text-align: center;background-image:url('${base}/resources/images/index_bottom.png');">
		<ul
			style="list-style: none; margin: 0; text-align: center; line-height: 28px;">
			<li
				style="display: inline; text-align: right; float: left; margin-right: 1px"></li>
			<li style="display: inline; text-align: center; float: left;"
				id="centerPrompt"></li>
			<li
				style="display: inline; text-align: right; float: right; margin-right: 10px">
				服务端版本：<spring:message code="server.version" />
			</li>
		</ul>
	</div>

	<div data-options="region:'center',border:true"
		style="background-image:url('${base}/resources/images/index_bg.png');">
		<div data-options="fit:true, border:false" id="tabs"
			class="easyui-tabs">
			<div title="首&nbsp;&nbsp;&nbsp;&nbsp;页"
				style="padding: 0px 0px 0px 0px; display: none;">
				<!-- <table id="tt" fit="true" url="datagrid1.html" border="false" striped="true"
		        	title="Load Data" rownumbers="true" singleSelect="true" pageSize="50" pagination="true">
				    <thead>
				        <tr>
				            <th field="id" width="80">Item ID</th>
				            <th field="text" width="80">Product ID</th>
				            <th field="value" width="80">Value</th>
				            <th field="key" width="80">Key ID</th>
				        </tr>
				    </thead>
				</table> -->
				<%-- <input class="easyui-combotree" data-options="url:'${base}/system/SysTree/getDeptTree',method:'get',required:true,editable:true,selectOnNavigation:true,onChange:ev"> --%>
				<%-- <select id="cc" class="easyui-combotree" data-options="url:'${base}/system/SysTree/getDeptTree',method:'get'" multiple style="width:200px;"></select> --%>
				<tag:deptSelectTags id="aa" editable="true" headValue="0"
					headLabel="请选择1"></tag:deptSelectTags>
				<tag:dictSelectTags id="cc" dictTypeId="SET_STATUS_ID" headValue="0"
					headLabel="请选择2"></tag:dictSelectTags>
				<tag:roleSelectTags id="dd" headValue="0" headLabel="请选择3"
					width="100px" panelHeight="200"></tag:roleSelectTags>
				<%-- <tag:dictSelectTags name="aa" dictTypeId="SET_STATUS_ID"></tag:dictSelectTags> --%>
				<%-- <input 
					name='bbc' id="bbc"
					class="easyui-combotree"
					data-options="url:'${base}/system/SysTree/getDeptTree',
								  method:'get',
								  lines:true,
								  editable:true,
								  selectOnNavigation:true
								  ">
				<input id="box"
					class="easyui-combobox" 
					data-options="
									url:'${base}/system/SysDict/loadSelectTagData?dictTypeId=SET_STATUS_ID',
									method:'get',
									valueField:'idKey',
									textField:'dictValue',
									panelHeight:'auto',
									selectOnNavigation:true
							"> --%>
				<input id="" type="button" onclick="test()" value="sss"> <input
					id="" type="button" onclick="loadFilter2()" value="aa"> <input
					id="" type="button" onclick="testSend()" value="发数据"> <input
					id="file_upload" type="button" value="文件上传测试"> 定时推送时间：秒<input
					id="second"> <input type="button"
					onclick="startTestJPush()" value="开始测试推送"> <input
					type="button" onclick="stopTestJPush()" value="关闭测试推送">

			</div>

		</div>
	</div>

	<div id="switchUserDlg" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 320px; height: 200px; padding: 10px 20px"
		buttons="#switchUserDlg-buttons">
		<form id="switchForm" method="post" action="${base}/loginManage" novalidate>
			<div style="margin: 16px;">
				<label>用&nbsp;&nbsp;户:</label> <input name="loginNm"
					class="easyui-validatebox" type="text" required="true">
			</div>
			<div style="margin: 16px;">
				<label>密&nbsp;&nbsp;码:</label> <input name="userPwd"
					class="easyui-validatebox" type="password" required="true"
					onKeyDown="switchUser()">
			</div>
		</form>
	</div>
	<div id="switchUserDlg-buttons" style="text-align: center;">
		<a href="javascript:void(0)" id="switchSubmit"
			class="easyui-linkbutton" iconCls="icon-ok">确 定</a>
	</div>

	<div id="ModifyPassWordDlg" class="easyui-dialog"
		data-options="modal:true,closed:true,iconCls:'icon-save',closable:false"
		style="width: 320px; height: 200px; padding: 10px 20px"
		buttons="#ModifyPassWordDlg-buttons">
		<form id="ModifyPassWordForm" method="post" novalidate>
			<div style="margin: 16px;">
				<div><label>&nbsp;&nbsp;&nbsp;&nbsp;新密码:</label> </div>
				<div>
				<input id="newPwd"
					name="newPwd" type="password" class="easyui-validatebox"
					data-options="required:true" />
					</div>
			</div>
			<div style="margin: 16px;">
				<div><label>确认密码:</label></div>
				<div>
				 <input id="types" name="types" type="hidden"
					value="1" /> <input id="surePwd" name="surePwd" type="password"
					class="easyui-validatebox" required="required"
					validType="equals['#newPwd']" />
					</div>
			</div>
		</form>
	</div>
	<div id="ModifyPassWordDlg-buttons" style="text-align: center;">
		<a href="javascript:void(0)" id="ModifyPassWordSubmit"
			class="easyui-linkbutton" iconCls="icon-ok">修改</a>
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
	<style>
.afont {
	color: #FFFFFF;
}

.afont:hover {
	background: #eaf2ff;
	color: #000000;
}

#skincolor:hover {
	background: #eaf2ff;
	color: #000000;
}

.eui-lable {
	list-style: none;
	text-decoration: none;
}
</style>
</body>
</html>