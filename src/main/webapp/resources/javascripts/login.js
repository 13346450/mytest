
/*
 *说明：检验必填项
 *@创建：作者:ts		创建时间：2009-6-15
 *@修改历史：
 */
function check(){
	checkValidateCode();
	if(!validateCodeIsRight){
		alert("验证码错误！");
		return false;
	}
	if ("" == document.getElementsByName("userN0")[0].value){
		alert("请填写用户名！");
		document.getElementsByName("userN0")[0].focus();
		return false;
	}
	
	if ("" == document.getElementsByName("password")[0].value){
		alert("请填写密码！");
		document.getElementsByName("password")[0].focus();
		return false;
	}
	setCookie("username", document.getElementsByName("userN0")[0].value, 30);
	submitForm();
	//openFrame();
}

function getUser(){
	if(getCookie("username")!=null){
		document.getElementsByName("userN0")[0].value = getCookie("username");
	}
}

/*
 *说明：回车
 *@创建：作者:ts		创建时间：2009-6-15
 *@修改历史：
 */
function toValidateCode(){
	event.cancelBubble = true;
    if (event.keyCode==13)  document.getElementsByName("validateCode")[0].focus();
}
/**
 * 回车键绑定
 */
function toLogin(){
	event.cancelBubble = true;
    if (event.keyCode==13)  check();
}
/*
 *说明：重置
 *@创建：作者:ts		创建时间：2009-6-15
 *@修改历史：
 */
function resetForm(){
	document.getElementsByName("userN0")[0].value="";
	document.getElementsByName("password")[0].value="";
}
/*
 *说明：提交表单
 *@创建：作者:ts		创建时间：2009-6-15
 *@修改历史：
 */
function submitForm(){
	logForm.loginNm.value = document.getElementsByName("userN0")[0].value;
	logForm.userPwd.value  = document.getElementsByName("password")[0].value;
	logForm.validateCode.value  = document.getElementsByName("validateCode")[0].value;
	logForm.submit(); 
}

/*
 *说明：回车
 *@创建：作者:ts		创建时间：2009-6-15
 *@修改历史：
 */
function toPwd(){
	if (event.keyCode==13) {
		document.getElementsByName("password")[0].focus();
	}
}
//绑定事件
function bindChange(){
	 var ms = document.getElementById("mysteve");
     var add = document.getElementById("add");
	 var lwidth = document.getElementById("lcenter");
     ms.onmouseover = function(){
       add.style.display = "block";
		ms.style.backgroundImage="url(resources/images/appdown-after.png)"; 
       };
		add.onmouseover = function(){
       add.style.display = "block";
		ms.style.backgroundImage="url(resources/images/appdown-after.png)"; 
       };
     ms.onmouseout = function(){
       add.style.display = "none";
		ms.style.backgroundImage="url(resources/images/appdown.png)"; 
       };
		 add.onmouseout = function(){
       add.style.display = "none";
		ms.style.backgroundImage="url(resources/images/appdown.png)"; 
       };
}
//获取背景图
function getBg(){
	$.ajax({
		type : "POST",
		url : "system/SysLogoSettings/queryAll",
		datatype : "json",
		success : function(data) {
			var myobj = eval(data);
			for (var i = 0; i < myobj.length; i++) {
				if (myobj[i].type == 'pcLoginLogo') {
					var imageUrl=myobj[i].imageUrl;
					if(imageUrl == null){
						imageUrl='resources/images/ishang_logo_login.png';
					}
					$('.lgbefore').css('background-image','url('+imageUrl+')');
				}
			}
		}
	});
}
//窗口缩放
function setMinHeight(height) {
	var bHeight = ($(window).height() <= height) ? 500 : '860px';
	$("#main").height(bHeight);
	// 窗口放大缩小，设置页面宽度
	$(window).resize(function() {
		var bHeight = ($(window).height() <= height) ? 500 : '860px';
		$("#main").height(bHeight);
	});
}

//appbtn
function buttonSwitch(){
	$("#appBtn").click(function() {
		appBtn1.style.borderBottom = "0px solid ";
		appBtn1.style.color = "#287599";
		appBtn.style.borderBottom = "2px solid ";
		appBtn.style.color = "#FFF";
		$("#family").show();
		$("#store").hide();
	});
	$("#appBtn1").click(function() {
		appBtn.style.borderBottom = "0px solid ";
		appBtn.style.color = "#287599"
		appBtn1.style.borderBottom = "2px solid ";
		appBtn1.style.color = "#FFF";
		$("#store").show();
		$("#family").hide();
	});
//二维码切换
	$(".slide").eq(0).show();
		var j = 1;
		
		$(".handle").click(function(){
			if(!$(this).siblings(".slide").is(":visible")){
				$(this).addClass("select");
				$(this).siblings(".slide").animate({width:"show"},300);
				$(this).parent().siblings().children(".slide").animate({width:"hide"},300);
				$(this).parent().siblings().children(".handle").removeClass("select");
			}else{
				$(this).siblings(".slide").animate({width:"hide"},300);
				$(this).removeClass("select");
			}
	});
}


//获取验证码图片
function getValidateCodeImg(){
	$('#imgValidateCode').attr('src','system/ValidateCode/getValidateCode/'+Math.random());
}
//检测验证码是否正确
function checkValidateCode(){
	$.ajax({
		type : "POST",
		url : "system/ValidateCode/checkValidateCode/"+$('#validateCode').val(),
		datatype : "json",
		async:false,
		success : function(data) {
			var result=eval("(" + data + ")");
			if(result.status==1){
				validateCodeIsRight=true;
				$('#checkImg').attr("src","resources/images/dsucess.gif");
			}else if(result.status==2){
				validateCodeIsRight=false;
				$('#checkImg').attr("src","resources/images/derr.gif");
			}
			
		}
	});
}
//验证码提示图标
function codeOnput(v){
	if(v.length==4){
		checkValidateCode();
	}else{
		$('#checkImg').attr("src","resources/images/null.png");
	}
}

/*
 *说明：未登录时，跳出子iframe，刷新整个windows页面 
 *@创建：作者:cqg		创建时间：2015-4-29
 *@修改历史：
 */
if (window != top)
	top.location.href = location.href;