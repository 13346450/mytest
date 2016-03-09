<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ attribute name="name" type="java.lang.String" required="false" description="标签的名称"%>
<%@ attribute name="width" type="java.lang.String" required="false" description="width"%>
<select class="easyui-combobox"
	<c:if test="${name ne null}">name='${name}'</c:if>
 	<c:if test="${name eq null}">name='voltageLeaveExam'</c:if>
 	<c:if test="${width ne null}">style="width:${width}"</c:if>
>
								<option value="0" selected>全选</option>
								<option value="0.22kV(+5%~-5%)">0.22kV(+5%~-5%)</option>
								<option value="0.22kV(+7%~-10%)">0.22kV(+7%~-10%)</option>
								<option value="0.38kV(+7%~-7%)">0.38kV(+7%~-7%)</option>
								<option value="0.38kV(+7%~-10%)">0.38kV(+7%~-10%)</option>
								<option value="10kV(+7%~-7%)">10kV(+7%~-7%)</option>
								<option value="10kV(+7%~-10%)">10kV(+7%~-10%)</option>
								<option value="35kV(+7%~-5%)">35kV(+7%~-5%)</option>
								<option value="35kV(+7%~-10%)">35kV(+7%~-10%)</option>
								<option value="110kV(+7%~-10%)">110kV(+7%~-10%)</option>
								<option value="110kV(+10%~-0%)">110kV(+10%~-0%)</option>
								<option value="330kV(+7%~-10%)">330kV(+7%~-10%)</option>
								<option value="330kV(+10%~-0%)">330kV(+10%~-0%)</option>
								<option value="550kV(+7%~-10%)">550kV(+7%~-10%)</option>
								<option value="550kV(+10%~-0%)">550kV(+10%~-0%)</option>
							</select>
