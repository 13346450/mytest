<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.lang.Exception"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>错误页面</title>
<table width="100%" height="100%" border="0">
	<tbody>
		<tr>
			<td>
				<h2>您所访问的网页不存在，请返回。</h2>
			</td>
		</tr>
		<tr>
			<td align="left">
			<%  
			Exception e = (Exception)request.getAttribute("exception");  
			out.print(e.getMessage());  
			%>  
			</td>
		</tr>
	</tbody>
</table>
