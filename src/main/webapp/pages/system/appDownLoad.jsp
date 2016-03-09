<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>APP下载</title>
<script src="${base}/resources/javascripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		bgResize();
		autoDownload();
	});

	function autoDownload() {
		if (isWeixn()) {
			//微信
			$('#wxdown').show();
		} else {
			$('#comdown').show();
			if (toJudgeVers()) { //IOS
				window.location.href = "";
			} else { //android
				setTimeout(function(){window.location.href = "${base}/d/${type}"},1000);
			}
		}
	}

	//调整宽度、变色
	function bgResize() {
		$("#picbg").height(($(window).height() / 10) * 10);
		$(window).resize(function() {
			$("#picbg").height(($(window).height() / 10) * 10);
		});
		$("#appdown_span").click(function(){ 
	          $('#appdown_span').css("color","#9dd1e9");
	          window.location.href = "${base}/d/${type}";
	         });
	}
	//判断是否是微信扫描
	function isWeixn() {
		var ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == "micromessenger") {
			return true;
		} else {
			return false;
		}
	}

	function toJudgeVers() {
		var browser = {
			versions : function() {
				var u = navigator.userAgent;
				return {
					//移动终端浏览器版本信息
					ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
					android : u.indexOf('Android') > -1
							|| u.indexOf('Linux') > -1, //android终端
					iPhone : u.indexOf('iPhone') > -1, //是否为iPhone
					iPad : u.indexOf('iPad') > -1
				//是否iPad
				};
			}()
		};
		/***
		 * 返回1 是ios 返回0 是安卓
		 */
		if (browser.versions.ios || browser.versions.iphone
				|| browser.versions.ipad) {
			return 1;
		} else {
			return 0;
		}
	}
</script>
<style>
html {
	color: #fff;
	font-family: "微软雅黑";
}

body, div {
	margin: 0 auto;
	padding: 0;
}

#div1 {
	background-color: #00a1e9;
	width: 100%;
	text-align: center;
}

.left {
	float: left;
}

.right {
	float: right;
}

#wxdown{position: absolute; top: 30%; left: 25%; color: #000; font-size: 30px; display:none;}
#comdown{position: absolute; top: 27%; left: 15%; color: #000; font-size: 60px;display:none;-webkit-tap-highlight-color:rgba(255,0,0,0);}
.appdown-btn{width:687px;height:300px; line-height:300px;color:#0081dc;}
#picbg {
	width: 100%;
	font-size:0;
}
</style>
</head>
<body>
	<div id="div1">
		<div id="picbg">
			<img src="${base}/resources/images/weixnappbg.png" width="100%"
				height="100%">
		</div>
		<div id="wxdown">
			<p style="float: left; margin-top: 40px;">1.点击右上角图标</p>
			<i
				style="background-image:url(${base}/resources/images/tubiao.png); width:69px; height:69px; display:block;float:left;margin:20px;"></i>
			<div style="clear: both;"></div>
			<p>2.选择"在浏览器中打开",即可下载APP</p>
		</div>
		<div id="comdown"><div class="appdown-btn" ><span id="appdown_span"><b>立即下载</b></span></div></div>
	</div>
</body>
</html>
