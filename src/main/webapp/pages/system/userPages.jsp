<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<table>
         <tr>
         	<td colspan="7">
         	    共${pageData.totalPage}页，${pageData.totalRecord}条记录，当前页${pageData.pageNo}
         		<a href="first_page()">首页</a>
         		<a href="#" onclick="javascript:next_page()" title="${pageData.pageNo-1}">上页</a>
         		<a title="${pageData.pageNo+1}">下页</a>
         		<a title="${pageData.totalPage}">末页</a>
         	</td>
         </tr>
         <tr>
            <th>学号12</th>
            <th>姓名2</th>
            <th>密码3</th>
            <th>专业3</th>
            <th>年龄</th>
            <th>备注</th>
            <th>角色名称</th>
            <th>单位名称</th>
            <th>操作</th>
         </tr>
         <c:forEach items="${pageData.results}" var="list">
            <tr>
               <td>${list.idKey}</td>
               <td>${list.cdNm}</td>
               <td>${list.gender}</td>
               <td>${list.deptId}</td>
               <td>${list.userCd}</td>
               <td>${list.addr}</td>
               <td>${list.roleName}</td>
               <td>${list.deptName}</td>
               <td>
               <a href="${base}/system/SysUser/${list.idKey}" target="_blank">查看</a> 
               <a href="${base}/system/SysUser/${list.idKey}/edit" target="_blank">编辑</a> <a
                  href="${base}/system/SysUser/${list.idKey}/delete" target="_blank">删除</a>
               </td>
            </tr>
         </c:forEach>
      </table>
<form action="${base}/system/SysUser/listPage" method="post" id="page_form">
     <input type="hidden" name="pageNo">
     <input type="hidden" name="pageSize">
</form>
<script type="text/javascript">

function next_page(){
	$("#page_form").pageNo.value = "2";
	
}
</script>      