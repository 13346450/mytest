/**
 *说明：通用JavaScript脚本函数库
 *@创建：作者:ts		创建时间：2007-8-3
 *@修改历史：
 *		    [序号](Administrator	2007-8-3)<修改说明>
 *@methode        trim(String sOldStr)
 *@methodNote     去除字符串的空格
 *@methode     	  XMLEncode(String sString)
 *@methodNote     对字符串进行XML编码
 *@methode        IsEmpty(OName)
 *@methodNote     验证控件是否为空
 *@methode        convert(sStr)
 *@methodNote     小写金额转换大写
 *@methode        converts(iLength,sStr,iPos,sPlus)
 *@methodNote     写金额按左到右转换为大写(整数部分支持到千亿)
 *@methode        convertXs(sStr,iPos)
 *@methodNote     写金额按左到右转换为大写(小数部分3位)
 *@methode        setCharCode(sStr)
 *@methodNote     对数字字符转换成中文
 *@methode        FormatNumber(sStrOld,sStrType)
 *@methodNote     格式化数字输出，四舍五入函数，格式：#.00等
 *@methode        FormatMoneyCW(sValue)
 *@methodNote     转换金额为"###,###.00";
 *@methode        getMonthDay(sDate,eDate)
 *@methodNote     获得两个日期间天数（yyyy-mm-dd） submits()
 *@methode        submits()
 *@methodNote     提交功能（检查单据是否需要提交审核组，需要则执行AutoSubmit()）
 *@methode        AutoSubmit()
 *@methodNote     提交审核组
 *@methode        findCount(sFatherStr,sChildStr)
 *@methodNote     查找子字符串在父字符串中的出现次数
 *@methode        replaceAll(sFatherStr,sChildStr,sRepStr)
 *@methodNote     替换所有的字符sChildStr为sRepStr
 *@methode        replaceAlls(sFatherStr,sChildStr,sRepStr)
 *@methodNote     替换所有的字符串sChildStr为sRepStr
 *@methode        sPageSet(sPage)
 *@methodNote     分页,将分页信息进行HTML转换
 *@methode        goPage(sUrl,iPage,iMaxPage)
 *@methodNote     分页输入页数跳转
 *@methode        isNumber(iNum)
 *@methode        isNumber2(iNum)
 *@methodNote     判断是否为数字
 *@methode        getBetweenMonth(sDate,eDate)
 *@methodNote     获得两个日期间月份数
 */

/**
 * 默认的分页大小：20条
 */
var DEFAULT_PAGE_SIZE = 20;
/**
 * 默认的ajax执行后延时刷新毫秒数：600
 */
var DEFAULT_DELAY_MS = 600;

/*
 *说明：去除字符串两边的空格
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sOldStr 要去除空格的字符串
 *@return String  返回去除空格后的字符串
 */
 function trim(sOldStr) {
        var sNewStr = sOldStr.match(/^\s*(\S+(\s+\S+)*)\s*$/); 
        return (sNewStr == null) ? "" : sNewStr[1];
 }
 
/**
  * 把字符串作为 URI 组件进行编码
  * 解决中文乱码问题
  * @param str
  * @returns
*/
function encodeURL(str){
	return encodeURIComponent(str);
}
/*
 *说明：对字符串进行XML编码
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](yqh	2007-8-3)<修改说明>
 *@param  sString 要进行XML编码的字符串
 *@return String  返回XML编码后的字符串
 *@func   trim(sOldStr)	去除空格
 */
function XMLEncode(sString)
{
	sString = trim(sString);
    sString = sString.replace("&","&");
    sString = sString.replace("<","<");
    sString = sString.replace(">",">");
    sString = sString.replace("’","'");
    sString = sString.replace("\"","\"");
    return sString;
}
/*
 *说明：验证控件是否为空
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](yqh	2007-8-3)<修改说明>
 *@param  OName  			验证控件名称
 *@return boolean   返回是否为空,true:为空;false:不为空
 */
function IsEmpty(OName)
{
	var    bReturn = false;    //返回的boolean值
    OName = document.getElementsByName[OName](0);
    if(Trim(OName.value) == "")
    {
        alert("字段不能为空。");
        bReturn = true;
        if(OName.disabled == false && OName.readOnly == false){
            OName.focus();
        }
    }
    return bReturn;
}
/*
 *说明：小写金额转换大写
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sStr    要去除空格的字符串
 *@return String  返回去除空格后的字符串
 *@func   FormatNumber(String sParameter,String sFormat)   对有小数的四舍五入并格式化 
 *@func   converts(int ilength,String sParameter,int iPos,String sPlus)  对整数部分逐个转换
 *@func   convertXs(String sParameter) 对小数部分逐个转换
 */
function convert(sStr){
	
	var    filter = /^\s*[0-9.-]{1,25}\s*$/;    // 对数量的正则表达式。
	var    sValue = sStr;                       // 传入的字符串
	//替代所有的","。
	sValue = sValue.replace(/,/g,"");
	//带有小数点的四舍五入到第2位并格式化。
	if(sValue.indexOf(".")!==-1){
		sValue = FormatNumber(sValue,"#.00");
	}
	//传入的字符串为空，返回空值
	if(sValue == ""){ //start if1
		return "";
	}else if(sValue !="" && !filter.test(sValue)){
		//传入的字符串不符合正则表达式，返回空值
		sValue = sValue.substring(0,sValue.length-1);
		return "";
	}else{
		//传入的字符串符合要求，进行金额转换
		var    sPositiveNegative = "";    // 正负符号
		var             sDecimal = "";	  // 小数部分
		var                sPlus = "";    // 正数部分
		if(sValue.indexOf("-")!= -1){ 
			sPositiveNegative = "负";
		}
		/*传入的值带有小数位，取出整数部分和小数部分，
		  没有则把整个字符串设给
		*/
		if(sValue.indexOf(".")!= -1){ // start if2 
			var    sDecimal = sValue.substring(sValue.indexOf(".")+1,sValue.length);
			var       sPlus = sValue.substring(0,sValue.indexOf("."));
		}else{
		 	sPlus = sValue;
		} //end if2
		//取绝对值并转换为字符串
	 	sPlus = Math.abs(Number(sPlus)).toString(); 
		var 		    ileng = sPlus.length; // 获得整数部分的长度
	 	var    sConvertString = "";			  // 定义接收转换后的字符串变量 
	 	for(var i = 0;i<ileng;i++){ //start for1
	 		// 循环每个字符转换成大写金额
			sConvertString = sConvertString+converts(ileng,sPlus.substring(i,i+1),i+1,sPlus);	
	 	} // end for1
	 	// 将转换后的字符加上正负符号。
	 	sConvertString = sPositiveNegative+sConvertString;
	 	// 对没有小数部分的处理 
	 	if(sDecimal == ""){ //start if3
	 		sConvertString = sConvertString+"整";
			if(sConvertString == "整"||sConvertString == "零元整"){
				sConvertString = "";
			}
		//对有小数的处理
	 	}else{
	 		var    sDecimalOne = "";    //小数部分的第一位
			var    sDecimalTwo = "";    //小数部分的第二位
			//转换小数第一位
			sDecimalOne = convertXs(sDecimal.substring(0,1),1);
			//转换小数第二位
			sDecimalTwo = convertXs(sDecimal.substring(1,2),2);
			if(sDecimalTwo == "零分"){
				sDecimalTwo = "整";
			}
			var    sDecimalAll = sDecimalOne+sDecimalTwo;    //合并小数部分
			if(sDecimalAll == "零整"){
				sDecimalAll = "整";
			}
			if(sConvertString == "零元"){
				sConvertString = sDecimalAll;
			}else{
				sConvertString = sConvertString+sDecimalAll;
			}
			if(sConvertString == "整"||sConvertString == "零元整"){
				sConvertString = "";
			}
		}//end if3
		return sConvertString ;
	} //end if1
}
/*
 *说明：写金额按左到右转换为大写(整数部分支持到千亿)
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  iLength 字符串长度
 *@param  sStr    要转换的字符
 *@param  iPos	  字符的位置
 *@param  sPlus	  整个字符串
 *@return String  返回转换后的字符
 *@func   setCharCode(String sStr)   对数字字符转换成中文
 */
function converts(iLength,sStr,iPos,sPlus){
	//转换数字字符成中文
	sStr = setCharCode(sStr);
	var    i = iLength-iPos;    //获得此字符在整个字符串的位置
	//个位不为零
	if(i == 0&&sStr != '零'){ //start setif
		sStr = sStr + "元";
	//个位为零并且字符串长度大于1
	}else if(i == 0&&sStr == '零'&&iLength>1){
		sStr = "元";
	//个位为零并且字符串长度等于于1
	}else if(sStr == '零'&&iLength == 1){
		sStr = '零元';
	//十位并且长度大于2，十万位并且长度大于6，十亿位上并且长度大于10，并且其不为零
	}else if(((i == 1&&iLength >= 2)||(i == 5&&iLength >= 6)||(i == 9&&iLength >= 10))&&sStr != '零'){
		sStr = sStr + "拾";
	//十位，十万位，十亿位，并且等于零，其上一位为0，则不显示此字符"零"
	}else if((i == 1||i == 5||i == 9)&&sPlus.substring(iPos,iPos+1) == '0'&&sStr == '零'){
		sStr = "";
	//百位并且长度大于3，百万位并且长度大于7，百亿位上并且长度大于11，并且其不为零	
	}else if(((i == 2&&iLength >= 3)||(i == 6&&iLength >= 7)||(i == 10&&iLength >= 11))&&sStr != '零'){
		sStr = sStr + "佰";
	//百位，百万位，百亿位，并且等于零，其上一位为0，则不显示此字符"零"
	}else if((i == 2||i == 6||i == 10)&&sPlus.substring(iPos,iPos+1) == '0' &&sStr == '零'){
		sStr = "";
	//仟位并且长度大于4，仟万位并且长度大于8，仟亿位上并且长度大于12，并且其不为零	
    }else if(((i == 3&&iLength >= 4)||(i == 7&&iLength >= 8)||(i == 11&&iLength >= 12))&&sStr != '零'){
    	sStr = sStr + "仟" ;
    //仟位，仟万位，仟亿位，并且等于零，其上一位为0，则不显示此字符"零"
   	}else if((i == 3||i == 7||i == 11 )&&sPlus.substring(iPos,iPos+1) == '0'&&sStr == '零'){
   		sStr = "" ;
   	}else{
   		//sStr = sStr ;
   	} //end setif
   	/*用于是否万位。
   	  万位上不为0，长度大于5，则显示
   	*/
    if(i == 4&&sStr != '零'&&iLength >= 5){ //start if2
    	sStr = sStr + "万";
    // 万位上为0,没有亿位，并且千万到万位不为0000，则显示
	}else if(i == 4&&sStr == '零'&&((iLength > 5&&iLength < 9)||(iLength >= 9&&sPlus.substring
	(sPlus.length-8,sPlus.length-4) != '0000'))){
		sStr = "万";
	// 万位上为零,有亿位，并且千万到万位为0000，则不显示	
 	}else if(i == 4&&sStr == '零'&&iLength >= 9&&sPlus.substring
 	(sPlus.length-8,sPlus.length-4) == '0000'){
 		sStr = "";
 	//亿位上不为零，长度大于等于9，则显示	
	}else if(i == 8&&sStr != '零'&&iLength >= 9){
		sStr = sStr + "亿";
	//亿位上为零，长度大于9，则显示
	}else if(i == 8&&sStr == '零'&&iLength>9){
		sStr = "亿";
	}else{
		sStr = sStr;
	}//end if2
	return sStr;
}
/*
 *说明：写金额按左到右转换为大写(小数部分3位)
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sStr    要转换的字符
 *@param  iPos	  字符的位置
 *@return String  返回转换后的字符
 *@func   setCharCode(String sStr)   对数字字符转换成中文
 */
function convertXs(sStr,iPos){
	//转换数字字符成中文
	sStr = setCharCode(sStr);
	if(iPos == 1){
		sStr = sStr +"角";
	}else if(iPos == 2){
		sStr = sStr +"分";
	}else if(iPos == 3){
		sStr = sStr + "豪";
	}else{
		sStr = sStr;
	}
	if(sStr == "零角"){
		sStr = "零";
	}
	return sStr;
}
/*
 *说明： 对数字字符转换成中文
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sStr    要转换的字符
 *@return String  返回转换后的字符
 */
function setCharCode(sStr){
	if(sStr == "1"){ //start setCharif
		sStr = '壹';
	}else if(sStr == "2"){
		sStr = '贰';
	}else if(sStr == "3"){
		sStr = '叁';
	}else if(sStr == "4"){
	    sStr = '肆';
	}else if(sStr == "5"){
		sStr = '伍';
	}else if(sStr == "6"){
		sStr = '陆';
	}else if(sStr == "7"){
		sStr = '柒';
	}else if(sStr == "8"){
		sStr = '捌';
	}else if(sStr == "9"){
		sStr = '玖';
	}else if(sStr == "0"){
		sStr = '零'
	}else{
		sStr = "";
	} // end setCharif
	return sStr;
}
/*
 *说明： 格式化数字输出，四舍五入函数，格式：#.00等，保留几位小数，就写几个零
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  strOld   要四舍五入并格式的数字字符
 *@param  strType  格式。
 *@return String  返回转换后的字符
 */
function FormatNumber(sStrOld,sStrType){
	var	       strVal = "";
	var	     strRType = "";
	var		 strNLval = "";
	var		 strNRval = "";
	var		intDotPos = "";    //数据的小数点位置
	var	intDotTypePos = "";	   //格式中的小数点位置
	//传入的字符为空，则返回"".
	if(sStrOld == undefined || sStrOld == ""||sStrType == null||sStrType == ""){
		return "";
	}
	//带有","的，去掉",".
	if(sStrOld != "" && sStrOld.indexOf(",") != -1){
		sStrOld = sStrOld.replace(/,/g,"");
	}
	var    n = 0;
	/*得到四舍五入的数据,并格式化小数部分
	  格式找到小数点 格式化成浮点数,原型不考虑小数点
	*/
	if(sStrType.indexOf(".") != -1){ 
		intDotPos = sStrOld.indexOf(".");
 		intDotTypePos = sStrType.indexOf(".");
    	var x ;
    	//取格式小数位数，四舍五入找这个值计算
    	strRType = sStrType.substr(intDotTypePos +1,sStrType.length - intDotTypePos -1);
	 	x = Math.pow(10,strRType.length);
    	x = Math.floor( parseFloat(sStrOld) * x + 0.5) / x;
    	strVal = ""+x;
    	
		var  ONumber = (strRType+"#").indexOf("#");    //查找格式中小数部分0值的个数
 		//计算小数部分需要补0的个数
 		if(ONumber>0){
      		//查找结果中小数点后的位数
	  		if(strVal.indexOf(".") != -1){ 
				intDotPos = strVal.indexOf(".");
            	//得到目前的结果的小数位值，如果 < ONumber ,则补足0
      			strNRval = strVal.substr(intDotPos +1 ,strVal.length - intDotPos -1);
            	for( n = 0 ; n < ONumber - strNRval.length; n++){
        			strVal = strVal + "0";
   				}
			//结果有没有小数，直接加上ONumber个0
    		}else{                         
        		strVal = strVal +".";
        		for( n = 0 ; n < ONumber; n++){
        			strVal = strVal + "0";
     			}
	 		}
 		}else{
      		;//不需要补0值，小数部分都是 0.####
 		}
 	//格式找不到小数点，肯定需要得到整数，直接四舍五入
 	}else{                          
    	var x ;
    	x = Math.floor( parseFloat(sStrOld) + 0.5) ;
    	strVal = "" + x;
  	}

 	   // 格式化整数部分
 	//  ###00 等价于 #00
 	//  #0#00 等价于 #0000
 	strNLval = "" + parseInt(strVal);
 	intDotPos = strVal.indexOf(".");
    //如果存在小数部分字符串,先保存下来
 	if (intDotPos != -1 ){
      	strNRval = strVal.substr(intDotPos ,strVal.length - intDotPos );
 	}else{
     	strNRval = "";
 	}
	var    strRtnVal = "";    //返回值
 	var    inti      = 0;     //
 	var    intCount  = 0;     //
	if((sStrType.indexOf("#,0") != -1 || sStrType.indexOf("#,#") != -1) && strNLval.length>3){
    	for(intCount = strNLval.length - 1 ;intCount > -1 ;intCount--){
        	strRtnVal = strNLval.substr(intCount,1) + strRtnVal;

        	inti++;
        	if(inti % 3 == 0 && inti != strNLval.length){
           		strRtnVal = "," + strRtnVal;
     		}
      	}
    }else{
     	 strRtnVal = strNLval;
    }
	strRtnVal = strRtnVal + strNRval;
  	return strRtnVal;
}
/*
 *说明： 转换金额为"###,###.00";
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sValue  要转换的金额
 *@return String  返回转换后的金额字符
 */
function FormatMoneyCW(sValue){
	var	   sReturnValue = "";	//返回的金额字符
	var        sDecimal = "";	//小数部分
	var         sValues = "";   //整数部分
	if(sValue == ""){ //start if1
		sReturnValue = "";
	}else{
		sValues = sValue;
		//如有小数部分，将整数和小数部分提取出来
		if(sValue.indexOf(".")!=-1) {
			sValues = sValue.substring(0,sValue.indexOf("."));
			sDecimal = sValue.substring(sValue.indexOf("."),sValue.length);
		}
		var        length = 0;			 //整数部分以3位分组的长度
		var    value_temp = new Array(); //保存整数部分的分组
		length = sValues.length/3;
		if(length.toString().indexOf(".")!=-1){
			length = length + 1;
		}
		//设置数组的长度
		value_temp.length = length;
		//循环加","
		for(var i=0;i<length;i++){ //start for1
			//字符串为空，则退出循环
			if(sValues == "") break;
			//3位加","，如果是第3位，取个位到千位的保存数组里。
			if(3-sValues.length>=0){
				value_temp[i] = "," + sValues.substring(0,sValues.length);
			//3位加","，如果不是第3位，取相应3位数的保存数组里。
			}else{
				value_temp[i] = "," + sValues.substring(sValues.length-3,sValues.length);
			}
			//重置字符串
			sValues=sValues.substring(0,sValues.length-3);
			
		} //end for1
		//拼接输出字符串
		for(var j = value_temp.length-1;j>-1;j--){ //start for2
			if(value_temp[j] == "")  continue;
			sReturnValue = sReturnValue + value_temp[j];
		} //end for2
		//去掉第一个","，并上小数部分。
		sReturnValue = sReturnValue.substring(1,sReturnValue.length) + sDecimal;
		if(sReturnValue.indexOf("-,")!=-1) sReturnValue="-"+sReturnValue.substring(2,sReturnValue.length);
	} //end if1
	return sReturnValue;
}
/*
 *说明： 获得两个日期间的天数
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sDate   开始日期 格式 yyyy-mm-dd
 *@param  eDate   结束日期 格式 yyyy-mm-dd
 *@return String  两个日期间的天数
 */
function getMonthDay(sDate,eDate){
	var    sRturnDays = 0;    				              //返回天数
	var           arr = sDate.split("-");			      //分割字符串 
    var         sTime = new Date(arr[0],arr[1],arr[2]);   //取得开始日期
	arr = eDate.split("-"); 
	var   		eTime = new Date(arr[0],arr[1],arr[2]);   //取得结束日期
    if(eTime < sTime){  //start if1
		//			
	}else{	
		//如果年月相同
		if(sDate.substring(0,7) == eDate.substring(0,7)){ //start if2
			sRturnDays = Number(eDate.substring(8,10)) - Number(sDate.substring(8,10));
     	 //如果不相同
     	 }else{
		 	var    sYearDays = 0;    //年份不同，获取相差年的天数
			if(Number(eDate.substring(0,4))-Number(sDate.substring(0,4))>1){ //start if3
				for(var j=1;j<(Number(eDate.substring(0,4)) - Number(sDate.substring(0,4)));j++){ //start for1
					if(new Date(Number(sDate.substring(0,4)) + 1, 2 , 0).getDate() == 29){ //start if5
						sYearDays = sYearDays + Number(366);
					}else{
						sYearDays = sYearDays + Number(365);
					} //enf if5
				} //end for1
			} //end if3
			var     			sYear = sDate.substring(0,4);                      //开始日期年
			var	    		   sMonth = sDate.substring(5,7);	                   //开始日期月
			var    sStartMonthLastDay = new Date( sYear, sMonth , 0).getDate();    //开始月最后天(月从0开始,其上月最后一天）
			var    			 sMaxDays = 0;								           //相隔天数
			//相同年份，先去月份差的天数。								 //
			if(sDate.substring(0,4) == eDate.substring(0,4)){ //start if4
				for(var h = (Number(sDate.substring(5,7))+1);h<Number(eDate.substring(5,7));h++){ //start for2
					sMaxDays = sMaxDays + new Date(sDate.substring(0,4),
				    (Number(sDate.substring(5,7)) + h),0).getDate();
				} //end for2
			//不同年份
			}else{
				var    cMs = 12-Number(sDate.substring(5,7));    //先取开始月份到12月的差
				//先取开始月份到12月的天数
				for(var y = (Number(sDate.substring(5,7))+1);y < 13;y ++){ //start for3
					sMaxDays = sMaxDays + new Date( sDate.substring(0,4), Number(sDate.substring(5,7))+y,0).getDate();
				} //end for3
				//再取次年到结束月的天数
				for(var g = 1;g < Number(eDate.substring(5,7));g++){ //start for4
					sMaxDays = sMaxDays +new Date( eDate.substring(0,4), Number(eDate.substring(5,7))+g,0).getDate();
				} //end for4
			} //end if4
			sRturnDays = Number(sStartMonthLastDay) - Number(sDate.substring(8,10)) + 
						 sMaxDays + Number(eDate.substring(8,10)) + sYearDays;
		} //enf if2
	} //end if1
	return sRturnDays;
}
/*
 *说明： 提交功能（检查单据是否需要提交审核组，需要则执行AutoSubmit()）,未定义变量为页面定义变量。
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@return    
 */
function submits(){ 
	if(xg!=true){ //start if1
		alert("此单据不是您做的，您没有提交的权限！");
		return false;
	} //end if1
	vouid  = document.getElementsByName("id_key")[0].value;               //单据IDK
	vou_tp = vou_nm.substring(0,trim(vou_nm).length-11).toLowerCase();    //单据业务类型
	//单据状态为未提交时，执行检查是否需要审核组
	if(audit_mark == ""||audit_mark == "0"){ //start if2
		hasAudit.src = path + "/workflow/submit/submitAction.do?method=isAudit&subsys_id=" + subsys_id
		+ "&vou_tp_cd=" + vou_tp + "&vou_id=" + vouid + param;
	}else{
	//单据状态为已提交，则反提交单据
		var rv = window.showModalDialog(path + "/workflow/submit/submitAction.do?method=qxAudit&subsys_id="
			   + subsys_id + "&vou_tp_cd=" + vou_tp + "&vou_id=" + vouid + param,window,
				"dialogWidth:400px;dialogHeight:250px;status:no;help:no;scroll:no;");
		//返回值不为空
		if(rv != ""&&rv != undefined){ //start if3
					//如果反提交成功，则重载页面
					if(rv.indexOf("反提交成功")!= -1) { //start if4
						var url = rv.substring(5,rv.length);
						window.location = url;
					} //end if4
		} //end if3
	} //end if2
}
/*
 *说明： 提交功能（提交审核组,未定义变量为页面定义变量)
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@return    
 */
 function AutoSubmit(){
	if(Audit_Flage == ""){ //start if1
			//
	}else{
		if(Audit_Flage == "yes"){ //start if2
			//窗口返回值，选择审核组
			var ReturnValue = window.showModalDialog(path + 
							  "/workflow/submit/selectAuditGroup.jsp?subsys_id=" + 
			                  subsys_id+"&vou_tp_cd="+vou_tp+"&vou_id="+vouid,window,
			                  "dialogWidth:530px;dialogHeight:600px;status:no;help:no;scroll:yes;"); 
			//窗口返回值不为空，提交到审核组
			if(ReturnValue != ""&&ReturnValue != undefined){ //start if3
				ReturnValue = ReturnValue + "&vou_nm=" + vou_nm;
				var rv = window.showModalDialog(ReturnValue + param,window,
				"dialogWidth:200px;dialogHeight:150px;status:no;help:no;scroll:no;");
				//窗口返回值不为空，提示信息，并重载页面
				if(rv != ""&&rv != undefined){ //start if4
					if(rv.indexOf("提交成功") != -1) { //start if5
						var url = rv.substring(4,rv.length);
						window.location=path+"/" + url;
					} //end if5
				} //end if4
			} //end if3
		} //end if2
	} //end if1
}

/*
 *说明： 查找子字符串在父字符串中的出现次数
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sFatherStr  	父字符串
 *@param  childStr  	子字符串
 *@return int  			子字符串在父字符串中的出现次数
 *@func trim(sStr)    	去除空格
 */
function findCount(sFatherStr,sChildStr){
	//去除空格
	sFatherStr = trim(sFatherStr);
	var    iFatherLen = 0;      //父字符串长度
	var    iFatherLenth = 0;    //父字符串长度
	var    iCount = 0;          //子字符串在父字符串中的出现次数
	iFatherLen = sFatherStr.length;
	iFatherLenth = sFatherStr.length;
	//替换子字符为空格,获得之后的字符串
	for( var k = 0;k < iFatherLenth;k++){ //start for1
		sFatherStr = sFatherStr.replace(sChildStr,"");
	 
	} //end for1
	//获得之后的字符串的长度
	iCount = sFatherStr.length;
	iCount = iFatherLen - iCount;
	return iCount;
	}
	
/*
 *说明： 替换所有的字符sChildStr为sRepStr
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param   sFatherStr  		父字符串
 *@param   sChildStr  		子字符
 *@param   sRepStr  		要替代成的字符
 *@return  sFatherStr       替代后的字符串
 */
function replaceAll(sFatherStr,sChildStr,sRepStr){
	var    iFatherLen = sFatherStr.length;    //父字符串长度
	for( var k = 0;k < iFatherLen;k++){ //start for1
		sFatherStr = sFatherStr.replace(sChildStr,sRepStr);
	
	} //end for1
	return 	sFatherStr;
}

/*
 *说明： 替换所有的字符串sChildStr为sRepStr
 *@创建：作者:tw		创建时间：2013-11-01
 *@param   sFatherStr  		父字符串
 *@param   sChildStr  		子字符串
 *@param   sRepStr  		要替代成的字符
 *@return  sFatherStr       替代后的字符
 */
function replaceAllStr(sFatherStr,sChildStr,sRepStr){
	return sFatherStr.replace(new RegExp(sChildStr, 'g'), sRepStr);
}

/*
 *说明： 替换所有的字符串sChildStr为sRepStr
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param   sFatherStr  		父字符串
 *@param   sChildStr  		子字符串
 *@param   sRepStr  		要替代成的字符
 *@return  sFatherStr       替代后的字符
 */
function replaceAlls(sFatherStr,sChildStr,sRepStr){
	var    iFatherLen = sFatherStr.length;    //父字符串长度
	var     iStartPos = 0;					  //替换字符的起点
	var       iEndPos = 0;                    //替换字符的结束点
	for( var k = 0;k < iFatherLen;k++){ //start for1
		sFatherStr = sFatherStr.replace(sChildStr,sRepStr);
		iStartPos = sFatherStr.indexOf(sChildStr);
		iEndPos = sFatherStr.indexOf(iStartPos,iStartPos);
		sFatherStr = sFatherStr.substring(0,iStartPos) + sFatherStr.substring(iEndPos + 1,sFatherStr.length());
	} //end for1
	return 	sFatherStr;
}

/*
 *说明： 分页,将分页信息进行HTML转换
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sPage  		传入的分页信息
 *@return  无
 */
function sPageSet(sPage){
	var    html = sPage;    //传入的分页信息
	if(html != ""){ //start if1
		html = html.replace(/&#039;/g,"'");
		html = html.replace(/&amp;/g,"&");
		html = html.replace(/&nbsp;/g,"  ");
		html = html.replace(/&lt;/g,"<");
        html = html.replace(/&gt;/g,">");
		html = html.replace(/&#034;/g,'"')
		html = html.replace(/%/g,"%25");
		//page 为页面定义的ID
		if(page!=undefined){
			page.innerHTML=html; 
		}else if(main.page!=undefined){

			main.page.innerHTML=html; 
		}else if(main.show.page!=undefined){
			main.show.page.innerHTML=html; 
		}else if(main.con.page!=undefined){
			main.con.page.innerHTML=html; 
		}
	} //end if1
	

}
/*
 *说明： 分页输入页数跳转
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sUrl  		   传入的分页url
 *@param  iPage            输入的页数
 *@return                  跳转到指定页
 *@func   isNumber(iNum)   判断是否为数字
 */
function goPage(sUrl,iPage,iMaxPage){
	//判断是否为空
	if(sUrl==undefined||sUrl==""||iPage==undefined||iPage==""){// start if1
		return false;
	//判断是否为无效页码
	}else if(!isNumber(iPage)){
		alert("系统提示\n 请输入数字");
		return false;
	}else if(iPage>iMaxPage){
		alert("系统提示\n 无效页码");
		return false;
	}else{
		if(parent.sql!=undefined){
			var formtev= document.createElement("form");
			formtev.method="post";
			formtev.innerHTML="<input type='text' name='sql'>";
			document.body.insertBefore(formtev);
			formtev.sql.value=parent.sql;
			
			formtev.action=sUrl+iPage;
			
			formtev.submit();
		}else{
			window.location=sUrl+iPage;
		}
	} // end if1
}
/*
 *说明： 判断是否为数字
 *@创建：作者:yqh		创建时间：2007-8-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  iNum  		   传入的要判断的输入
 *@return                  true:是数字，flase:不是数字
 */
function isNumber(iNum){
	if(!iNum){
		return false;
	}
	var    strP = /^\d+(\.\d+)?$/;    //数字的正则表达
	//不符合正则表达式，返回false
	if(!strP.test(iNum)){
		return false;
	}
	try{
  		if(parseFloat(iNum)!=iNum){
  			return false;
  		}
  	}catch(ex){
   		return false;
  	}
  	return true;
 }

/*
 *说明： 判断是否数值是否在合理范围内（0~63）
 *@创建：作者:whm		创建时间：2013-9-6
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  iNum  		   传入的要判断的输入
 *@return                  true:是数字，flase:不是数字
 */
function isReasonable(iNum){
	if(iNum>63){
		return false;
	}
	try{
  		if(parseFloat(iNum)!=iNum){
  			return false;
  		}
  	}catch(ex){
   		return false;
  	}
  	return true;
	
}
 /*
 *说明： 判断是否为数字 不含小数点
 *@创建：作者:zbs		
   
 *@param  iNum  		   传入的要判断的输入
 *@return                  true:是数字，flase:不是数字
 */
function isInteger(iNum){ 
   if(""==iNum){ 
     return false; 
   } 
  var reg = /\D/; 
  return iNum.match(reg)==null; 
 }
 /*
 *说明： 获得间隔月
 *@创建：作者:yqh		创建时间：2007-10-3
 *@修改历史：
 *		[序号](Administrator	2007-8-3)<修改说明>
 *@param  sDate   开始日期 格式 yyyy-mm-dd
 *@param  eDate   结束日期 格式 yyyy-mm-dd
 *@return String  两个日期间的间隔月
 */

function getBetweenMonth(sDate,eDate){
    var sRturnMonths = 0;	//返回间隔月数
	var arr = sDate.split("-");		//分割字符串 
    var sTime = new Date(arr[0],arr[1],arr[2]);   //取得开始日期
	arr = eDate.split("-"); 
	var eTime = new Date(arr[0],arr[1],arr[2]);   //取得结束日期
    if(eTime < sTime){  //start if1
		//不操作，返回相差月为0			
	}else{	
		//如果年相同,
		if(sDate.substring(0,4) == eDate.substring(0,4)){ //start if2
			//月份直接相减
			sRturnMonths = Number(eDate.substring(5,7)) - Number(sDate.substring(5,7));
     	 //如果年不相同
     	 }else{	
		 	if(Number(eDate.substring(0,4))-Number(sDate.substring(0,4))==1){ //相差1年 //start f3
				var cMs = 12 - Number(sDate.substring(5,7));    //先取开始月份到12月的差
				sRturnMonths = Number(cMs) + Number(eDate.substring(5,7))	//加上第二年月份
			}else{
				var cMs = 12 - Number(sDate.substring(5,7));    //先取开始月份到12月的差		
				var cYs = eDate.substring(0,4) - sDate.substring(0,4);	//相差年数
				sRturnMonths = Number(cYs) * 12 - 12 + Number(cMs) + Number(eDate.substring(5,7));
			}//end f3
		} //enf if2
	} //end if1
	return sRturnMonths;
}
//四舍五入函数
// Rounds 'num' to 'point'  the decimal point
function Round(num,decPoint) {
  pointPower = Math.pow(10,decPoint);
  num = Math.round(parseFloat(num)*(pointPower))/(pointPower);
  return num;
}
function winOpen(kind,vou_id,type_id,vou_cd){
	var paths = parent.path;
    if(paths==undefined){
	paths = parent.parent.path;
    }
    if(paths==undefined){
	paths = parent.parent.parent.path;
    }
    if(paths==undefined){
	paths = parent.parent.parent.parent.path;
    }
    if(paths==undefined){
	paths = opener.parent.parent.path;
    }
    if(paths==undefined){
	paths = opener.parent.parent.parent.path;
    }
     if(paths==undefined){
	paths = opener.parent.parent.parent.parent.path;
       }
	var editable="";
	var index = type_id.indexOf("*");
	if(index>0){
		editable=type_id.substr(index+1,11);
	}
	window.open(paths+"/comm/upload/UploadAction.do?method=init&kind="+kind+"&vou_id="+vou_id+"&type_ids="+type_id+"&vou_cd="+vou_cd+"&msg="+"&editable="+editable,"upload","width=500,height=550,toolbar=no,location=no,menubar=yes,scrollbars=no,resizable=yes,top=130,left=220");
}
function inf_alert(info){
	var tips = parent.tip;
	if(tips==undefined){
		tips = parent.parent.tip;
   	 }
    	if(tips==undefined){
		tips = parent.parent.parent.tip;
    	}
    	if(tips==undefined){
		tips = parent.parent.parent.parent.tip;
    	}
    	if(tips==undefined){
		tips = opener.parent.parent.tip;
    	}
    	if(tips==undefined){
		tips = opener.parent.parent.parent.tip;
    	}
     	if(tips==undefined){
		tips = opener.parent.parent.parent.parent.tip;
    	}
	alert(tips+'\n'+info);
}
function warn_alert(info){
	var warns = parent.warn;
	if(warns==undefined){
		warns = parent.parent.warn;
   	 }
    	if(warns==undefined){
		warns = parent.parent.parent.warn;
    	}
    	if(warns==undefined){
		warns = parent.parent.parent.parent.warn;
    	}
    	if(warns==undefined){
		warns = opener.parent.parent.warn;
    	}
    	if(warns==undefined){
		warns = opener.parent.parent.parent.warn;
    	}
     	if(warns==undefined){
		warns = opener.parent.parent.parent.parent.warn;
    	}
	alert(warns+'\n'+info);
}
function err_alert(info){
	var errs = parent.err;
	if(errs==undefined){
		errs = parent.parent.err;
   	 }
    	if(errs==undefined){
		errs = parent.parent.parent.err;
    	}
    	if(errs==undefined){
		errs = parent.parent.parent.parent.err;
    	}
    	if(errs==undefined){
		errs = opener.parent.parent.err;
    	}
    	if(errs==undefined){
		errs = opener.parent.parent.parent.err;
    	}
     	if(errs==undefined){
		errs = opener.parent.parent.parent.parent.err;
    	}
	alert(errs+'\n'+info);
}

function mouseX(evt) {
	
	if (evt.pageX){
		return evt.pageX;
	}else if (evt.clientX){
		return evt.clientX + (document.documentElement.scrollLeft ?document.documentElement.scrollLeft :document.body.scrollLeft);
	}else{
	 return null;
	}
}
function mouseY(evt) {
	if (evt.pageY){
		return evt.pageY;
	}else if (evt.clientY){
	   return evt.clientY + (document.documentElement.scrollTop ?
	   document.documentElement.scrollTop :
	   document.body.scrollTop);
	}else{
		return null;
	}
}
function valieFiledLength(obj,length){
	var filter1 = /^\s*[0-9a-zA-Z!@#$%^&*()_+~]{1,10000}\s*$/;	//对数量的正则表达
	var lengthg=0;
	var j = 0;
	if(obj.value!=""){
		for(var i =0;i<obj.value.length;i++){
			if(i<obj.value.length) j=i+1;
			else j=i;
			if(!filter1.test(obj.value.substring(i,j))){
				lengthg=lengthg+2;
			}else{
				lengthg= lengthg+1;
			}
		}
		if(lengthg>length){
			parent.inf_alert("字段长度只能输入"+length+"位字符,一个汉字或全角字符等于2个长度");
			obj.focus();
			obj.select();
			return false;
		}
	
	}
	return true;
}


function getCookieVal(offset){
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
	endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

function setCookie(name, value ,expiresDate){
	var expdate = new Date();
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	if(expiresDate!=null) expdate.setTime(expdate.getTime() + ( expiresDate * 1000 * 3600 * 24));
	document.cookie = name + "=" + escape (value) +((expiresDate == null) ? "" : ("; expires="+ expdate.toGMTString()))
	+((path == null) ? "" : ("; path=" + path)) +((domain == null) ? "" : ("; domain=" + domain))
	+((secure == true) ? "; secure" : "");
}

function delCookie(name){
	var exp = new Date();
	exp.setTime (exp.getTime() - 1);
	var cval = getCookie (name);
	document.cookie = name + "=" + cval + "; expires="+ exp.toGMTString();
}

function getCookie(name){
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen){
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
		return getCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}
//获取当前日期 yyyy-MM-dd
function getEndDatetime(){
	var now=new Date();
	y=now.getFullYear();
	m=now.getMonth()+1;
	d=now.getDate();
	m=m<10?"0"+m:m;
	d=d<10?"0"+d:d;
	var da = y+"-"+m+"-"+d;
	return da;
	}
//获取一个月以前 yyyy-MM-dd
function getStartDatetime(){
	var date=new Date();
	var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);  
	var strYear = date.getFullYear();    
	var strDay = date.getDate();    
	var strMonth = date.getMonth()+1;  
	if(strYear%4 == 0 && strYear%100 != 0){  
	   daysInMonth[2] = 29;  
	}  
	if(strMonth - 1 == 0)  
	{  
	    strYear -= 1;  
	    strMonth = 12;  
	}  
	else  
	{  
	    strMonth -= 1;  
	}  
	strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];  
	if(strMonth<10)    
	{    
	    strMonth="0"+strMonth;    
	}  
	if(strDay<10)    
	{    
	    strDay="0"+strDay;    
	 }  
	var datastr = strYear+"-"+strMonth+"-"+strDay;  
	     return datastr;  
}

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}
