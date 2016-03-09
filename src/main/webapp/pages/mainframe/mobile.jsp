<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>手机测试demo</title>
</head>
<body>
<div id="loading">
		  <form action="${base}/webservice/mobile" method="post">
		  		mac:
		  		<input name="mac" type="text" size="20">
		  		cmd:
		  		<input name="cmd" type="text" size="10">
		  		value:
		  		<input name="value" type="text" size="10">
		  		<input type="submit" name="提交">
		  </form>
</div>
</body>
</html>