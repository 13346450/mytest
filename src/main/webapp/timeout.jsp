<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<title>主页面</title>
<table width="100%" height="300" border="0">
<tbody>
<tr>
<td align="center"><br><br>
<fmt:message key="login.timeout" />
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="重新登录" onClick="relogin()">
</td>
</tr>
</tbody></table>

<script language="JavaScript">
function relogin(){
	if(parent.parent!=""||parent.parent!=undefined){
		parent.parent.location.href="<%=request.getContextPath()%>/login.jsp";
	}else if(parent!=""||parent!=undefined){
		parent.location.href="<%=request.getContextPath()%>/login.jsp";
	}
	
}
</script>
