<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="./taglibs.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="id ,默认跟name相同"%>
<%@ attribute name="name" type="java.lang.String" required="false" description="标签的名称"%>
<%@ attribute name="onChange" type="java.lang.String" required="false" description="onChange事件"%>
<%@ attribute name="editable" type="java.lang.Boolean" required="false" description="是否允许输入"%>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false" description="是否只读"%>
<%@ attribute name="required" type="java.lang.Boolean" required="false" description="是否必填"%>
<%@ attribute name="headLabel" type="java.lang.String" required="false" description="自定义显示值"%>
<%@ attribute name="headValue" type="java.lang.String" required="false" description="自定义value值"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="value"%>
<%@ attribute name="width" type="java.lang.String" required="false" description="width"%>
<%@ attribute name="communityId" type="java.lang.String" required="true" description="小区id"%>
<%@ attribute name="panelHeight" type="java.lang.String" required="false" description="下拉列高度，默认为自适应"%>
<!-- ${base}/business/BizCategory/getCategoryTree/'+'${communityId} -->
<input 
	id='${id}' value="${value}"
	<c:if test="${name ne null}">name='${name}'</c:if>
 	<c:if test="${name eq null}">name='${id}'</c:if>
 	<c:if test="${width ne null}">style="width:${width}"</c:if>
 	<c:if test="${width eq null}">style="width:160px"</c:if>
	class="easyui-combotree"
	data-options="url:'',
				  method:'get',
				  valueField:'idKey',
				  lines:true,
				  onShowPanel:function(){
				  	$('.tree.tree-lines').css({'background-color':'#ffffff','border':'0px'});
				  },
				  panelHeight:200,
				  onLoadSuccess:function(){
						
					},
					<c:if test="${required ne null}">required:${required},</c:if>
					<c:if test="${readonly ne null}">readonly:${readonly},</c:if>
					<c:if test="${editable ne null}">editable:${editable},</c:if>
					<c:if test="${onChange ne null}">onChange:${onChange},</c:if>
				  	selectOnNavigation:true
				  ">