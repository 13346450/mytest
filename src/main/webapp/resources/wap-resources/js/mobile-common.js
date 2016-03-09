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
		return;
	}
	
	if($('#'+json.formId).length == 0){
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
	if(undefined == json.msg || "" == json.msg || null == json.msg){
	}
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

function successFun(){
}
function handleChange()
{
	var content = $("textarea").val();
	content = content.replace(/\n/g,'<br/>');
		content = content.replace(/\s/g, '&nbsp;&nbsp;');
		$("textarea").val(content);
}
