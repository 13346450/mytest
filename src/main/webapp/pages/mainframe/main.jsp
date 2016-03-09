<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
  <head>
    <title><spring:message code="systemName"/></title>	
	<meta http-equiv="pragma" content="no-cache" />
 	<meta http-equiv="Cache-Control" content="no-cache" />
 	<meta http-equiv="expires" content="0" />
  </head>
  <body>
	  <div id="loading">
		    欢迎使用监测仪系统:   ${sessionScope.sessionBean.userName}
		 <br>登录日期：   ${sessionScope.sessionBean.loginDate}
	  </div>
	  
  </body>
</html>