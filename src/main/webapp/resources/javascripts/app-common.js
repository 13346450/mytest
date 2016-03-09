/**
 * @author:tw
 * @param json={
             type : 请求方式 ("POST" 或 "GET")， 默认为 "POST"。注意：其它 HTTP 请求方法，如 PUT 和 DELETE 也可以使用，但仅部分浏览器支持。
             dataType : 类型：String,可用值:
										"xml": 返回 XML 文档，可用 jQuery 处理。
										"html": 返回纯文本 HTML 信息；包含的 script 标签会在插入 dom 时执行。
										"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了 "cache" 参数。注意：在远程请求时(不在同一个域下)，所有 POST 请求都将转为 GET 请求。（因为将使用 DOM 的 script标签来加载）
										"json": 返回 JSON 数据 。
										"jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
										"text": 返回纯文本字符串
             url : 地址，不为空
             formId : form表单id
             beforeSend:function(XHR){...}发送前触发，如果返回 false 可以取消本次 ajax 请求。
             failureJSFun:function(json){...}失败的方法
             successJSFun:function(json){...}异步执行完后的动作方法名称,必须指定)
        	 msg:等待提示信息内容
        	 maskId: 遮罩标签id
        }
 */
function ajaxSubmitForm(json){
	if(undefined == json.url || "" == json.url || null == json.url){
		$.messager.alert('错误信息','没有指定url地址','error');
		return;
	}
	
	if($('#'+json.formId).length == 0){
		$.messager.alert('错误信息','没有正确指定formId或表单不存在 ','error');
		return;
	}
    
	if(undefined == json.type || "" == json.type || null == json.type){
		json.type = "POST";
	}
	/*added by ts 设置成为不要缓存 2014-2-20*/
	if(undefined == json.cache || "" == json.cache || null == json.cache){
		json.cache = false;
	}
	
	if(undefined == json.dataType || "" == json.dataType || null == json.dataType){
		json.dataType = "json";
	}
	var jsFun = json.beforeSend;
	if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
    	json.beforeSend = function(xhr){return true;};
    }
	/*
    	判断成功的方法是不是有指定，可以缺省
    */
    jsFun=json.successJSFun;
    if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
    	json.successJSFun = successFun;
    }
    /*
     	判断失败的方法是不是有指定，可以缺省
    */
    jsFun=json.failureJSFun;
    if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
		json.failureJSFun = errorFun;
    }
	
	if(undefined == json.msg || "" == json.msg || null == json.msg){
		json.msg = "正在处理，请稍候。。。";
	}
	showMask(json.msg, json.maskId);
    $.ajax({
	    type: json.type,
	    dataType:json.dataType,
	    cache:json.cache,
	    url:json.url,
	    data:$('#'+json.formId).serialize(),
	    beforeSend:json.beforeSend,
	    error: json.failureJSFun,
	    success: json.successJSFun
	});
}

function errorFun(request, errorMsg, obj){
	hideMask();
	//session超时则直接退出 added by ts 2015-09-10
	if("sessiontimeout"==trim(request.responseText)){
		return;
	}
	if(errorMsg.indexOf("parsererror") > -1){
		$.messager.alert('警告',"操作成功，返回信息解析异常！",'warning');
		return;
	}
	if(undefined != request && undefined != request.responseText && "" != request.responseText && null != request.responseText){
		$.messager.alert('失败',request.responseText,'error');
	}else{
		$.messager.alert('失败', errorMsg ,'error');
	}
}

function successFun(){
	hideMask();
}

/**
 * 在不同的元素上显示等待信息，遮罩相应层，id：对应元素对象
 */
function showMask(title, id){
	var maskTarget;
	if(undefined != id && null != id && "" != id){
		maskTarget = "#" + id;
	}else{
		maskTarget = document.body;
	}
	if(0 == $("#mask").length && 0 == $("#message").length){
		$("<div id=\"mask\" class=\"datagrid-mask\"></div>").css({
			display:"block",
			width:"100%",
			height:"100%"
		 }).appendTo(maskTarget); 
		$("<div id=\"message\" class=\"datagrid-mask-msg\"></div>")
			.html(title)
			.appendTo(maskTarget)
			.css({
				display:"block",
				left:($(maskTarget).outerWidth(true) - 190) / 2
		}); 
	}else{
		$("#mask").css("display","block");
		$("#message").html(title).css("display","block");
	}
}

/**
 * 隐藏等待信息
 */
function hideMask(){
	if(0 < $("#mask").length && 0 < $("#message").length){
		$("#mask").remove();
		$("#message").remove();
		/*$("#mask").css("display","none");
		$("#message").css("display","none");*/
	}
}

/**
 * ajax提交非表单
 */
function ajax(json){
	if(undefined == json.url || "" == json.url || null == json.url){
		$.messager.alert('错 误','没有指定url地址','error');
		return;
	}
	
	if(undefined == json.dataType || "" == json.dataType || null == json.dataType){
		json.dataType = "json";
	}
	
	if(undefined == json.cache || "" == json.cache || null == json.cache){
		json.cache = false;
	}
	
	/*
		判断成功的方法是不是有指定，可以缺省
	*/
	var jsFun=json.success;
	if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
		json.success = successFun;
	}
	/*
	 	判断失败的方法是不是有指定，可以缺省
	*/
	jsFun=json.error;
	if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
		json.error = errorFun;
	}else{
		json.error = function(request, errorMsg, obj){jsFun();errorFun(request, errorMsg, obj);};
	}
	if(undefined == json.msg || null == json.msg){
		json.msg = "正在处理，请稍候。。。";
	}
	if("" != json.msg){
		showMask(json.msg, json.maskId);
	}
    $.ajax(json);
}

/**
 * ajax上传文件
 * @param json
 */
function ajaxFileUpload(json){
	if(undefined == json.url || "" == json.url || null == json.url){
		$.messager.alert('错 误','没有指定url地址','error');
		return;
	}
	if(undefined == json.fileElementId || "" == json.fileElementId || null == json.fileElementId){
		$.messager.alert('错 误','没有指定文件的id','error');
		return;
	}
	if(undefined == json.dataType || "" == json.dataType || null == json.dataType){
		json.dataType = "JSON";
	}
	/*
		判断成功的方法是不是有指定，可以缺省
	*/
	var jsFun=json.success;
	if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
		json.success = successFun;
	}
	/*
	 	判断失败的方法是不是有指定，可以缺省
	*/
	jsFun=json.error;
	if("function" != typeof(jsFun) || undefined == jsFun || null == jsFun){
		json.error = errorFun;
	}else{
		json.error = function(request, errorMsg, obj){jsFun();errorFun(request, errorMsg, obj);};
	}
	if(undefined == json.msg || null == json.msg){
		json.msg = "正在处理，请稍候。。。";
	}
	showMask(json.msg, json.maskId);
	$.ajaxFileUpload({
		url:json.url,
		type:"post",
		secureuri:false,
		fileElementId:json.fileElementId,
		dataType:json.dataType,
		success:json.success,
		error:json.error
	});
}

/**
 * 将表单数据转为json
 */
function formToJson(id) {
    var arr = $("#" + id).serializeArray();
    var jsonStr = "";
    jsonStr += '{';
    for (var i = 0; i < arr.length; i++) {
        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}';
    var json = eval ("(" + jsonStr + ")");
    return json;
}

function formToJson2(id) {
    var arr = $("#" + id).serializeArray();
    var jsonStr = "";
    jsonStr += '{';
    $.each(arr, function(i, field){
    	 jsonStr += '"' + field.name + '":"' + field.value + '",';
    });
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}';
    var json = eval ("(" + jsonStr + ")");
    return json;
}

/**
 * 将表单数据转为json字符串
 */
function formToJsonString(id) {
    var arr = $("#" + id).serializeArray();
    var jsonStr = "";
    jsonStr += '{';
    for (var i = 0; i < arr.length; i++) {
        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}';
    return jsonStr;
}

/**
 * 是否允许编辑设置
 * @param id
 * @param IsEnable
 */
function SetDivAllControlEnable(id, IsEnable) {
    $("#" + id + " input").prop("disabled", !IsEnable);
    $("#" + id + " select").prop("disabled", !IsEnable);
    $("#" + id + " textarea").prop("disabled", !IsEnable);
    $("#" + id + " .easyui-datebox").each(function(){
    	$(this).datebox('readonly', !IsEnable);
    });
    $("#" + id + " .easyui-combotree").each(function(){
    	$(this).combotree('readonly', !IsEnable);
    });
    $("#" + id + " .easyui-combobox").each(function(){
    	$(this).combobox('readonly', !IsEnable);
    });
    
    $("#" + id + " .easyui-linkbutton").each(function(){
    	if(IsEnable){
    		$(this).linkbutton('enable');
    	}
    	else{
    		$(this).linkbutton('disable');
    	}
    });
}

function setContainerControlReadOnly(id, isReadOnly){
	$("#" + id + " input").prop("readonly", isReadOnly);
    $("#" + id + " select").prop("readonly", isReadOnly);
    $("#" + id + " textarea").prop("readonly", isReadOnly);
    $("#" + id + " .easyui-datebox").each(function(){
    	$(this).datebox('readonly', isReadOnly);
    });
    $("#" + id + " .easyui-combotree").each(function(){
    	$(this).combotree('readonly', isReadOnly);
    });
    $("#" + id + " .easyui-combobox").each(function(){
    	$(this).combobox('readonly', isReadOnly);
    });
    
    $("#" + id + " .easyui-linkbutton").each(function(){
    	if(!isReadOnly){
    		$(this).linkbutton('enable');
    	}
    	else{
    		$(this).linkbutton('disable');
    	}
    });
}

function setBtnEnable(id,IsEnable){
	if(IsEnable){
		$("#" + id).linkbutton('enable');
	}
	else{
		$("#" + id).linkbutton('disable');
	}
}

/** 
 * 改变表格的行和checkbox是否可点击
 * @param id       //表格id
 * @param rowEnable   //行是否可点击，true为可点击，false为不可点击
 */
function bindDatagrid(id, rowEnable){
	var s= $("#" + id).datagrid('getPanel');
    var rows = s.find('tr.datagrid-row');
    rows.unbind('click').bind('click',function(e){
            return rowEnable;
	});
    rows.unbind('dblclick').bind('dblclick',function(e){return rowEnable;});
    $(".datagrid-header-check input").prop("disabled", !rowEnable);//表header部分
}