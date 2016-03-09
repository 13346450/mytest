<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ attribute name="separator" type="java.lang.String" required="false" description="分割符号，默认为‘,’"%>
<script type="text/javascript">
	var allValue = "";
	var separator = ",";
	function getStationTypeB(){
		allValue = "";
		if(null != '${separator}' && "" != '${separator}'){
			separator = '${separator}';
		}
		$("[name='stationTypebValue']").each(function(){
			if($(this).prop("checked")){
				allValue += $(this).val() + separator;
			}
	    });
		if("" != allValue){
			allValue = allValue.substring(0,allValue.length-1);
		}
		$("#stationTypebId").val(allValue);
	}
	
	function getStationTypeC(){
		allValue = "";
		if(null != '${separator}' && "" != '${separator}'){
			separator = '${separator}';
		}
		$("[name='stationTypecValue']").each(function(){
			if($(this).prop("checked")){
				allValue += $(this).val() + separator;
			}
	    });
		if("" != allValue){
			allValue = allValue.substring(0,allValue.length-1);
		}
		$("#stationTypecId").val(allValue);
	}
	
	function getStationRunState(){
		allValue = "";
		if(null != '${separator}' && "" != '${separator}'){
			separator = '${separator}';
		}
		$("[name='stationRunStateValue']").each(function(){
			if($(this).prop("checked")){
				allValue += $(this).val() + separator;
			}
	    });
		if("" != allValue){
			allValue = allValue.substring(0,allValue.length-1);
		}
		$("#stationRunStateId").val(allValue);
	}
</script>
<table style="width: 100%">
   <tr>
       <td class="tdLeft">
           <fieldset>
               <legend>监测点类型</legend>
               <input id="stationTypebId" name="stationTypebId" type="hidden">
               <input id="stationTypebValueA" name="stationTypebValue" type="checkbox" value="A" onclick="getStationTypeB()"><label for="stationTypebValueA">A类</label>
               <input id="stationTypebValueB" name="stationTypebValue" type="checkbox" value="B" onclick="getStationTypeB()"><label for="stationTypebValueB">B类</label>
               <input id="stationTypebValueC" name="stationTypebValue" type="checkbox" value="C" onclick="getStationTypeB()"><label for="stationTypebValueC">C类</label>
               <input id="stationTypebValueD" name="stationTypebValue" type="checkbox" value="D" onclick="getStationTypeB()"><label for="stationTypebValueD">D类</label>
           </fieldset>
       </td>
       <td class="tdLeft">
           <fieldset>
               <legend>考核类型</legend>
               <input id="stationTypecId" name="stationTypecId" type="hidden">
               <input id="stationTypecValue1" name="stationTypecValue" type="checkbox" value="一级" onclick="getStationTypeC()"><label for="stationTypecValue1">一级</label>
               <input id="stationTypecValue2"  name="stationTypecValue" type="checkbox" value="二级" onclick="getStationTypeC()"><label for="stationTypecValue2">二级</label>
               <input id="stationTypecValue3"  name="stationTypecValue" type="checkbox" value="三级" onclick="getStationTypeC()"><label for="stationTypecValue3">三级</label>
               <input id="stationTypecValue4"  name="stationTypecValue" type="checkbox" value="四级" onclick="getStationTypeC()"><label for="stationTypecValue4">四级</label>
               <input id="stationTypecValueNone"  name="stationTypecValue" type="checkbox" value="不考核" onclick="getStationTypeC()"><label for="stationTypecValueNone">不考核</label>
           </fieldset>
       </td>
       <td class="tdLeft">
           <fieldset>
                <legend>运行状态</legend>
                <input id="stationRunStateId" name="stationRunStateId" type="hidden">
                <input id="stationRun" name="stationRunStateValue" type="checkbox" onclick="getStationRunState()" value="运行"><label for="stationRun">运行</label>
                <input id="stationFault" name="stationRunStateValue" type="checkbox" onclick="getStationRunState()" value="故障"><label for="stationFault">故障</label>
                <input id="stationStop" name="stationRunStateValue" type="checkbox" onclick="getStationRunState()" value="停运"><label for="stationStop">停运</label>
            </fieldset>
        </td>
    </tr>
</table>
                                        
