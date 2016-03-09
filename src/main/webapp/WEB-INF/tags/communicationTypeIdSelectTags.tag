<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./taglibs.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="id ,默认跟name相同"%>
<%@ attribute name="name" type="java.lang.String" required="false" description="标签的名称"%>
<%@ attribute name="onChange" type="java.lang.String" required="false" description="onChange事件"%>
<%@ attribute name="editable" type="java.lang.Boolean" required="false" description="是否允许输入"%>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false" description="是否只读"%>
<%@ attribute name="required" type="java.lang.Boolean" required="false" description="是否必填"%>
<%@ attribute name="headLabel" type="java.lang.String" required="false" description="自定义显示值"%>
<%@ attribute name="headValue" type="java.lang.String" required="false" description="自定义value值"%>
<input
	id='${id}' 
	style="width:80px"
	<c:if test="${name ne null}">name='${name}'</c:if>
 	<c:if test="${name eq null}">name='${id}'</c:if>
	class="easyui-combobox"
	data-options="
					url:'${base}/basic/BstMonitor/loadCommunicationTypeIdSelectTagData',
					method:'get',
					valueField:'dictValue',
					textField:'dictValue',
					onLoadSuccess:function(){
						var value = '${headValue}';
						var text = '${headLabel}';
						if('' != value && '' != text && '' != '${id}'){
							$('#${id}').combobox('setValue',value).combobox('setText',text);
						}
					},
					<c:if test="${required ne null}">required:${required},</c:if>
					<c:if test="${readonly ne null}">readonly:${readonly},</c:if>
					<c:if test="${editable eq null}">editable:false,</c:if>
					<c:if test="${editable ne null}">editable:${editable},</c:if>
					<c:if test="${onChange ne null}">onChange:${onChange},</c:if>
					panelHeight:'auto',
					selectOnNavigation:true
				">
