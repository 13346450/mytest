package com.dnake.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static boolean isDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.format(df.parse(str)).equals(str);
			//return (df.format(str)).equals(str);
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * 获取"/"符号的位置
	 * 可以指指定"/"符号在第N位出现的位置
	* @Title: getCharacterPosition 
	* @author xzm  2013-12-12
	* @Description: 
	* @param string
	* @param startIndex 
	* @return    
	* @return int    
	* @throws
	 */
	public static int getCharacterPosition(String string,int startIndex){
	    //这里是获取"/"符号的位置
	    Matcher slashMatcher = Pattern.compile("/").matcher(string);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	       //当"/"符号第N次出现的位置
	       if(mIdx == startIndex){
	          break;
	       }
	    }
	    return slashMatcher.start();
	 }
	
	/**
	 * 判断是否为Double类型
	* @Title: isDouble 
	* @author xql  2014-1-8
	* @Description: 
	* @param @param str
	* @param @return    
	* @return boolean    
	* @throws
	 */
	public static boolean isDouble(String str) {
		try{
			 	Double b = Double.parseDouble(str);
			 	return true;
			}catch(Exception e){
			   return false;
			}
	}
	
	/**
	 * 检验是否为yyyy-MM-dd HH:mm:ss的Date格式
	* @Title: isDateyyyyMMddHHmmss 
	* @author xql  2014-1-8
	* @Description: 
	* @param @param str
	* @param @return    
	* @return boolean    
	* @throws
	 */
	public static boolean isDateyyyyMMddHHmmss(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.format(df.parse(str)).equals(str);
			//return (df.format(str)).equals(str);
		} catch (Exception ex) {
			return false;
		}
	}
	/**
	 * 判断参数是否含有空,可传多个参数，用逗号隔开
	* @title      checkHaveNull 
	* @author  chen qige     
	* @date      2015年3月26日 
	*  @param objArray
	*  @return
	 */
	public static boolean checkHaveNull(Object... objArray) {
		for(Object obj:objArray){
			if(obj==null){
				return true;
			}
		}
		return false;
	}
}
