<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<%@ attribute name="passrateId" type="java.lang.String" required="true" description="合格率Id"%>
<%@ attribute name="runTimeId" type="java.lang.String" required="true" description="总运行时间Id"%>
<%@ attribute name="overTimeId" type="java.lang.String" required="true" description="总超限时间Id"%>
<%@ attribute name="btnStationId" type="java.lang.String" required="true" description="选择点Id"%>
<%@ attribute name="btnSelectedId" type="java.lang.String" required="true" description="已选点Id"%>
<%@ attribute name="passrate" type="java.lang.Double" required="false" description="合格率"%>
<%@ attribute name="totalRunTime" type="java.lang.Long" required="false" description="总运行时间"%>
<%@ attribute name="totalOverTime" type="java.lang.Long" required="false" description="总超限时间"%>
<%@ attribute name="stationTypeB" type="java.lang.String" required="false" description="监测点类型"%>
<%@ attribute name="btnSelectedIds" type="java.lang.String" required="true" description="选中点后的操作方法"%>
<%@ attribute name="txtStationIds" type="java.lang.String" required="true" description="选中点ID"%>

<table class="tableDetail" style="width: 698px">
	<tr>
		<td class="tdLeft" style="width: 70px">
			<label>${stationTypeB}</label>类合格率：
		</td>
		<td class="tdLeft" style="width: 85px">
			<input id="${passrateId}" value="${passrate}" maxlength="7" type="text" class="TextBox" style="width: 80px" />
		</td>
		<td class="tdLeft" style="width: 78px">
			总运行时间：
		</td>
		<td class="tdLeft" style="width: 85px">
			<input id="${runTimeId}" value="${totalRunTime}" maxlength="10" type="text" class="TextBox" style="width: 80px" />
		</td>
		<td class="tdLeft" style="width: 78px">
			总超限时间：
		</td>
		<td class="tdLeft" style="width: 85px">
			<input id="${overTimeId}" value="${totalOverTime}" maxlength="10" type="text" class="TextBox" style="width: 80px" />
		</td>
		<td class="tdLeft" style="width: 80px">
			<a id="${btnStationId}" href="javascript:void(0)" class="easyui-linkbutton">选择点</a>
			<a id="${btnSelectedIds}" href="javascript:void(0)" class="easyui-linkbutton"></a>
			<input id="${txtStationIds}" maxlength="10" type="text" class="TextBox"/>
		</td>
		<td class="tdLeft">
			<a id="${btnSelectedId}" href="javascript:void(0)" class="easyui-linkbutton">已选点</a>
		</td>
	</tr>
</table>
<div id="dlg" class="easyui-dialog" data-options="modal:true,closed:true,iconCls:'icon-search'" 
     style="width: 730px; height: 490px; padding: 5px 8px" buttons="#dlg-buttons">
<iframe frameborder="no" scrolling="no" id="ifrmUrl" width="100%" height="80%"></iframe> 
</div>

<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById("${btnSelectedIds}").style.display = "none";
		document.getElementById("${txtStationIds}").style.display = "none";
	});

	$("#${passrateId}").change(function() {
		if (!isNumber($("#${passrateId}").val())) {
			alert("${stationTypeB}" + "类合格率格式错误，请重新输入。");
			$("#${passrateId}").val("");
		} else {
			var mRunTime = $("#${runTimeId}").val();
			if ("0" == mRunTime) {
				$("#${overTimeId}").val("0");
				$("#${passrateId}").val("0");
				return;
			}
			var mPassrate = $("#${passrateId}").val();
			var mOverTime = (parseFloat(mRunTime) * (1 - parseFloat(mPassrate) / 100)).toFixed(0);
			$("#${overTimeId}").val(mOverTime);
			mPassrate = ((parseFloat(mRunTime) - parseFloat(mOverTime)) / parseFloat(mRunTime) * 100).toFixed(3);
			$("#${passrateId}").val(mPassrate);
		}
	});

	$("#${runTimeId}").change(function() {
		if (!isInteger($("#${runTimeId}").val())) {
			alert("${stationTypeB}" + "类总运行时间格式错误，请重新输入。");
			$("#${runTimeId}").val("");
		} else {
			var mRunTime = $("#${runTimeId}").val();
			if ("0" == mRunTime) {
				$("#${overTimeId}").val("0");
				$("#${passrateId}").val("0");
				return;
			}
			var mPassrate = $("#${passrateId}").val();
			var mOverTime = (parseFloat(mRunTime) * (1 - parseFloat(mPassrate) / 100)).toFixed(0);
			$("#${overTimeId}").val(mOverTime);
			mPassrate = ((parseFloat(mRunTime) - parseFloat(mOverTime)) / parseFloat(mRunTime) * 100).toFixed(3);
			$("#${passrateId}").val(mPassrate);
		}
	});

	$("#${overTimeId}").change(function() {
		if (!isInteger($("#${overTimeId}").val())) {
			alert("${stationTypeB}" + "类总超限时间错误，请重新输入。");
			$("#${overTimeId}").val("");
		} else {
			var mOverTime = $("#${overTimeId}").val();
			if ("0" == mOverTime) {
				$("#${passrateId}").val("100");
				return;
			}
			var mRunTime = $("#${runTimeId}").val();
			var mPassrate = ((parseFloat(mRunTime) - parseFloat(mOverTime)) / parseFloat(mRunTime) * 100).toFixed(3);
			$("#${passrateId}").val(mPassrate);
		}
	});
	
	$("#${btnStationId}").click(function(){
		$('#ifrmUrl').prop("src", "${base}/pages/business/selectStation.jsp?stationTypebId=" + "${stationTypeB}");
		$('#dlg').dialog('open').dialog('setTitle','选择监测点');
	})
	
	$("#${btnSelectedId}").click(function(){
		$('#ifrmUrl').prop("src", "${base}/pages/business/selectStation.jsp?stationTypebId=" + "${stationTypeB}");
		$('#dlg').dialog('open').dialog('setTitle','已选监测点');
	})
	
	function closeDialog(){
		$('#dlg').dialog('close');
	}

	$("#${btnSelectedIds}").click(function(){
		//无法取到${txtStationIds}这对象的值
		//$("#${txtStationIds}").val("1,3,4");
		var stationIds = $("#${txtStationIds}").val();
		closeDialog();
		var idList = stationIds.split(',');
		if(null != idList){
			var maxRunTime = '${maxRunTime}';
			if(isInteger(maxRunTime)){
				$("#${runTimeId}").val(parseFloat(maxRunTime) * parseFloat(idList.length));
				return;
			}
		}
		else
			$("#${runTimeId}").val("");	
	})
	
	function getAStationIds(stationIds){
		$("#${txtStationIds}").val(stationIds);
		alert($("#${txtStationIds}").val());
	}
	
	function getBStationIds(stationIds){
		//alert(stationIds);
		closeDialog();
	}

	function getCStationIds(stationIds){
		$("#${txtStationIds}").val(stationIds);
		alert($("#${txtStationIds}").val());
	}

	function getDStationIds(stationIds){
		//alert(stationIds);
		closeDialog();
	}
	
</script>