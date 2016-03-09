<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
</head>
<body>
<table>
  <tr>
    <td valign="middle" align="center">
    	通用页面，返回值：  <b> ${msg}</b>	
    <br>
      <%-- 等同于用jsp标签：<%= request.getAttribute("msg") %> --%> 
    </td>
  </tr>
</table>

</body>
</html>