<%@ page language="java" pageEncoding="UTF-8"%>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
   
   /*  登录拦截判断 */   
   if (null == session.getAttribute("sessionBean")) {
		response.sendRedirect(request.getContextPath() + "/");
		return;
	}   
   
   Cookie[] cookies = request.getCookies();
   String them = "default";
   if(null!=cookies){
       for(Cookie cookie : cookies){
           if(null != cookie.getName() && cookie.getName().equals("cs-skin")){
        	   them = cookie.getValue();
           }
       }
   }
%>
<link href="${base}/resources/css/main.css" type="text/css" rel="stylesheet">
<link href="${base}/resources/css/style.css" type="text/css" rel="stylesheet">
<link id="easyuiThemes" rel="stylesheet" type="text/css" href="${base}/resources/javascripts/jquery-easyui/themes/<%=them%>/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/resources/javascripts/jquery-easyui/themes/icon.css">
<script src="${base}/resources/javascripts/jquery-1.11.1.min.js" ></script>
<script src="${base}/resources/javascripts/jquery-easyui/jquery.easyui.min.js" ></script>
<script src="${base}/resources/javascripts/jquery-easyui/jquery.easyui.patch.js" ></script>
<script type="text/javascript" src="${base}/resources/javascripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${base}/resources/javascripts/common.js" ></script>
<script src="${base}/resources/javascripts/easyui-extend.js" ></script>
 <script type="text/javascript">
/* added by ts 2015-9-9
 * 设置未来(全局)的AJAX请求默认选项
 * 主要设置了AJAX请求遇到Session过期的情况
 */
$.ajaxSetup({
    complete: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus == 'timeout') {
            alert("您的登录已过期，请重新登录！");
            var top = getTopWinow();
            top.location.href = "${base}/";
        }
    }
});
</script>