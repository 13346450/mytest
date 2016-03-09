//扩展验证框
	$.extend($.fn.validatebox.defaults.rules, {
	    idCard: {
	        validator: function(value, param){
	        	var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	        	       return  reg.test(value) ;  
	        },
	        message: '请输入有效身份证！'
	    },
	    age:{
	    	 validator: function(value, param){
		        	var reg = /(^\d{1,3}$)/;  
		        	       if( reg.test(value)){
		        	    	   return value<=120;
		        	       }  
		        },
		        message: '请输入年龄0~120！'
	    },
	    phone:{
	    	validator: function(value, param){
	        	var reg = /(^\d+-{0,1}\d+$)/;  
	        	       if( reg.test(value)){
	        	    	   return value.length<=15&&value.length>=7;
	        	       }  
	        },
	        message: '请输入有效电话！'
	    },
	    number:{
	    	validator: function(value, param){
	        	var reg = /(^\d*$)/;  
	        	if( reg.test(value)){
	        		return value.length ==param[0];
	        	}
	        	return false;
	        },
	        message: '请输入{0}位数字!'
	    },	
	    numberLength:{
	    	validator: function(value, param){
	        	var reg = /(^\d*$)/;  
	        	if( reg.test(value)){
	        		return value.length>=param[0]&&value.length<=param[1];
	        	}
	        	return false;
	        },
	        message: '请输入{0}~{1}位数字!'
	    },
	    minLength: {
	            validator: function(value, param){
	                return value.length >= param[0];
	            },
	            message: '请输入不少于{0}个字'
	     },
	     maxLength: {
	            validator: function(value, param){
	                return value.length <= param[0];
	            },
	            message: '请至多输入{0}个字'
	     },
	     deviceNo : {
				validator : function(value, param) {
					var reg = /(^\d{2,8}$)/;
					return reg.test(value);
				},
				message : '请输入2至8位数字!'
		}, 
		name:{
	    	validator: function(value, param){
	        	var reg = /(^[\u4e00-\u9fa5]{2,5}$)/;  
	        	return reg.test(value);
	        },
	        message: '请输入有效的名字!'
	    }
	    
	});
	
	

	$.extend($.fn.combobox.methods, {
		//combobox选择选项，从0开始，使用示例：$('#comboboxID').combobox('selectedIndex',0) //选中第一个
	    selectedIndex: function (jq, index) {
	        if (!index)
	            index = 0;
	        var data = $(jq).combobox('options').data;
	        var vf = $(jq).combobox('options').valueField;
	        $(jq).combobox('setValue', eval('data[index].' + vf));
	    },
	    //在首选项增加"全部"选项，值为-1
	    addOptionAll:function(jq, index){
	        var data=$(jq).combobox('getData');
	        if( eval(data)[0].idKey!=-1){
	        	 var vf = $(jq).combobox('options').valueField;
	        	 var tf = $(jq).combobox('options').textField;
	            data.unshift({tf:"全部",vf:"-1"});
	            $(jq).combobox('loadData',data);
	        }
	    }
	});