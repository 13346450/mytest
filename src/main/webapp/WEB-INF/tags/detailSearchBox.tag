<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8" %>
<style>
	.tableDetail label{
        
    }
    
    .tdLeft{
    	padding-right: 10px;
    }
    
    .TextBox {
    	width: 100px;
    }
</style>
<table>
	<tr>
		<td><label>监测点编号：</label></td><td class="tdLeft"><input name="stationNumber" class="TextBox" style="width: 150px"></td>
		<td><label>监测点名称：</label></td><td class="tdLeft"><input name="stationName" class="TextBox"></td>
		<td><label>维护单位：</label></td><td class="tdLeft"><input name="stationMaintenanceDepName" class="TextBox"></td>
		<!-- <td><label>终端地址码：</label></td><td class="tdLeft"><input name="terminalCode" class="TextBox"></td> -->
		<td><label>SIM卡号：</label></td><td class="tdLeft"><input name="machineSimCardNumber" class="TextBox"></td>
	</tr>
</table>