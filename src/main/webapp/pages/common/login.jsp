<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.dnake.utils.SessionBean"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	session.invalidate();
 
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
   Cookie[] cookies = request.getCookies();
   String them = "cupertino";
   if(null!=cookies){
       for(Cookie cookie : cookies){
           if(null != cookie.getName() && cookie.getName().equals("cs-skin")){
        	   them = cookie.getValue();
           }
       }
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="systemName" /></title>
<meta name="keywords" content="智慧社区,i尚社区,厦门狄耐克电子科技有限公司" />
<meta name="description"
	content="i尚社区是一个智慧社区运营、管理平台，同时也可以为智慧社区运营主体提供整体解决方案，包含软件、硬件、实施等。" />
<script src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
<script src="${base}/resources/javascripts/login.js"></script>
<script src="${base}/resources/javascripts/common.js"></script>
<link rel="stylesheet" href="${base}/resources/css/loginbase.css" />
<script type="text/javascript">
	var validateCodeIsRight = false;

	$(function() {
		//获取验证码图片
		getValidateCodeImg();
		getBg();
		bindChange();
		buttonSwitch();
		setMinHeight(1080);
	});
</script>

</head>
<body onload="getUser()">
	<div id="main" class="bg">
		<div class="pic-word">
			<div class="headBox">
				<div class="lgbefore"></div>
				<div class="lgafter"></div>
			</div>
			<div class="centent"></div>
		</div>
	</div>
	<div class="login" align="center">
		<div id="lcenter">
			<div class="pic-content" align="center">
				<div class="pic-center">
					<div class="pic-bord">

						<div class="ui-form-item ">
							<label class="ui-label"> <span
								class="ui-icon ui-icon-userDEF">用户名</span>
								<div class="ui-poptip"></div>
							</label> <input type="text" class="ui-input ui-input-normal"
								maxlength="100" placeholder="请输入用户名" autofocus name="userN0"
								onKeyDown="toPwd();" />
							<div class="clear"></div>
						</div>
						<div class="ui-form-item " style="padding-top: 17px;">
							<label class="ui-label"> <span
								class="ui-icon ui-icon-password">密码</span>
								<div class="ui-poptip"></div>
							</label> <input type="password" class="ui-input ui-input-normal"
								placeholder="请输入密码" name="password"
								onKeyDown="toValidateCode();" />
							<div class="ui-form-explain"></div>
							<div class="clear"></div>
						</div>
						<div class="ui-form-item " style="padding-top: 17px;">
							<input type="text" maxlength="4"
								class="ui-input ui-input-normal1" name="validateCode"
								id="validateCode" placeholder="请输入验证码" onKeyDown="toLogin()"
								oninput="codeOnput(this.value)" /> <img id="checkImg"
								style="width: 16px; height: 16px; float: left; margin-left: 5px; margin-top: 7px"
								src="${base}/resources/images/null.png" /> <img
								id="imgValidateCode" class="verification" src=""
								onclick="getValidateCodeImg()" alt="点击更换验证码"></img>
							<div class="clear"></div>
						</div>
						<div class="ui-form-btn ">
							<div class="btn-word">
								<a href="javascript:void(0)" class="submitBtn" onclick="check()">登
									录</a> &nbsp;&nbsp;<a href="javascript:void(0)" class="submitBtn1"
									onclick="resetForm()">重 置</a>
								<div class="notice">${msg}</div>
								<div class="clear"></div>
							</div>

						</div>
					</div>

					<div class="pic-app">
						<div class="appdown" id="mysteve"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="appcontent">
			<div id="add" class="appdown1">
				<div class="appcode">
					<ul>
						<li id="appBtn" class="appBtn cur"><a href="#">住户版</a></li>
						<li id="appBtn1" class="appBtn1"><a href="#">商铺版</a></li>
					</ul>
				</div>
				<div id="family">
					<ul style="padding: 0;">
						<li class="">
							<div class="slide">
								<div class="cols3 cols">
									<div class="pic" style="margin-bottom: 15px;">
										<img src="${base}/resources/images/code_zh_apk<spring:message code='qrcode.name' />" width="139" height="139" />
									</div>
									<div>
										<a class="downIco" href="${base}/d/zh"><span>下载</span><i></i></a>
									</div>
								</div>
							</div>
							<div class="handle select" style="margin-top: 10px;">
								<p class="rotate">i尚社区手机版</p>
							</div>
						</li>
						<li class="">
							<div class="slide">
								<div class="cols3 cols">
									<div class="pic" style="margin-bottom: 15px;">
										<img src="${base}/resources/images/code_yjt_apk<spring:message code='qrcode.name' />" width="139" height="139" />
									</div>
									<div>
										<a class="downIco" href="${base}/d/yjt"><span>下载</span><i></i></a>
									</div>
								</div>
							</div>
							<div class="handle" style="margin-top: 10px;">
								<p class="rotate">云家庭</p>
							</div>
						</li>
						<li class="">
							<div class="slide">
								<div class="cols3 cols">
									<div class="pic" style="margin-bottom: 15px;">
										<img src="${base}/resources/images/code_yytc_apk<spring:message code='qrcode.name' />" width="139" height="139" />
									</div>
									<div>
										<a class="downIco" href="${base}/d/yytc"><span>下载</span><i></i></a>
									</div>
								</div>
							</div>
							<div class="handle" style="margin-top: 10px;">
								<p class="rotate">摇摇停车</p>
							</div>
						</li>
						<li class="">
							<div class="slide">
								<div class="cols3 cols">
									<div class="pic" style="margin-bottom: 15px;">
										<img src="${base}/resources/images/code_znjj_apk<spring:message code='qrcode.name' />" width="139" height="139" />
									</div>
									<div>
										<a class="downIco" href="${base}/d/znjj"><span>下载</span><i></i></a>
									</div>
								</div>
							</div>
							<div class="handle" style="margin-top: 10px;">
								<p class="rotate">智能家居</p>
							</div>
						</li>
						<li class="">
							<div class="slide">
								<div class="cols3 cols">
									<div class="pic" style="margin-bottom: 15px;">
										<img src="${base}/resources/images/code_zh_hd_apk<spring:message code='qrcode.name' />" width="139" height="139" />
									</div>
									<div>
										<a class="downIco" href="${base}/d/pad"><span>下载</span><i></i></a>
									</div>
								</div>
							</div>
							<div class="handle" style="margin-top: 10px;">
								<p class="rotate">i尚社区<span style="margin-left:-3px; font-size:12px; float:left;">Pad</span>版</p>
							</div>
						</li>
					</ul>
				</div>
				<div id="store">
					<div class="cols3 cols">
						<div class="pic" style="margin-bottom: 15px;">
							<img src="${base}/resources/images/code_sp_apk<spring:message code='qrcode.name' />" />
						</div>
						<div>
							<a class="downIco" href="${base}/d/sp"><span>下载</span><i></i></a>
						</div>
					</div>
					<div class="cols1 cols" style="text-align: left;">
						<div class="pic">
							<p class="pic1">商铺APP</p>
							<ul style="padding: 0;">
								<li><i class="icon"></i><span>免费开店</span></li>
								<li><i class="icon"></i><span>送货上门</span></li>
								<li><i class="icon"></i><span>接受预订</span></li>
								<li><i class="icon"></i><span>轻松服务</span></li>
							</ul>
							<p style="padding-top: 33px;">
								<!-- 苹果版支持iOS7.0以上 -->
							</p>
							<p>安卓版支持4.0以上</p>
							<div class="black"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer" class="footer">
		<div align="center" style="padding-top: 15px;">
			<span>©<spring:message code="corpName" /></span>
		</div>
	</div>
	<form action="loginManage" method="post" id="logForm">
		<input type="hidden" name="loginNm" />
		<input type="hidden" name="userPwd" />
		<input type="hidden" name="validateCode" />
	</form>
</body>
</html>
