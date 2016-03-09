<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<script language="JavaScript">
if("2">"90"){
	alert(1);
}else{
	alert(2);
}
</script>
</head>
<body>
未登录
<form action="appservice/mobileLogin" method="post">
<input name="cmd" value="6"/>
<input name="loginName" value="mdl"/> 
<input name="roleId" value="8"/> 
<input name="userPasswd" value="1234"/>
<button type="submit" >提交</button>
</form>
<br/>
已登录1
<form action="appservice/ehome" method="post">
<input name="cmd" value="3406"/> 
<input name="idKey" value="18"/> 
<input name="status" value="2"/> 
<input name="page" value="1">
<input name="count" value="69">
<input name="roleId" value="7"/> 
<input name="deviceId" value="1"/>
<input name="data" value=""/>
<input name="code" value="11"/>
<input name="type" value="59"/>
<input name="imageSize" value="200_201"/>
<button type="submit" >提交</button>
</form>
</body>
</html>