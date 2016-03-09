package com.dnake.common.error;

public class Validate {
	public static final int ERROR_CODE_FOR_REGEX  = 101;
	public static final int ERROR_CODE_FOR_NOT_NULL  = 102;
	public static final int ERROR_CODE_FOR_RANGE_LENGTH  = 103;
	public static final int ERROR_CODE_FOR_MAX_LENGTH  = 104;
	public static final int ERROR_CODE_FOR_MIN_LENGTH  = 105;
	/**
	 * 正在表达式验证
	 * @param value
	 * @param regex
	 * @param data
	 */
	public static void regex(String value,String regex,Object data){
		if(value!=null&&!value.matches(regex)){
			String errorMessage = TempStringUtils.temp("regex error{regex::0,value::1}", regex,value);
			throw new ValidateError(ERROR_CODE_FOR_REGEX,errorMessage , data);
		}
	}
	/**
	 * 空判断
	 * @param value
	 * @param data
	 */
	public static void notNull(Object value,Object data){
		if(value==null){
			String errorMessage = "notNull error";
			throw new ValidateError(ERROR_CODE_FOR_NOT_NULL,errorMessage , data);
		}
	}
	/**
	 * 参数为空判断
	 * @param objArray
	 *@author cqg 
	 *2015年9月23日
	 */
	public static void checkHaveNull(Object... objArray) {
		for(Object obj:objArray){
			if(obj==null){
				throw new ValidateError(ERROR_CODE_FOR_NOT_NULL, "miss parameter");
			}
		}
	}
	/**
	 * 字符串长度判定
	 * @param value
	 * @param data
	 */
	public static void rangeLength(String value,int min,int max,Object data){
		if(value!=null){
			if(value.length()<min||value.length()>max){
				String errorMessage = TempStringUtils.temp("rangeLength error{min:{x},max:{x},value:{x}}", min,max,value);
				throw new ValidateError(ERROR_CODE_FOR_RANGE_LENGTH,errorMessage , data);
			}
		}
	}
	/**
	 * 字符串最大长度判定
	 * @param value
	 * @param data
	 */
	public static void maxLength(String value,int max,Object data){
		if(value!=null){
			if(value.length()>max){
				String errorMessage = TempStringUtils.temp("maxLength error{max:{x},value:{x}}",max,value);
				throw new ValidateError(ERROR_CODE_FOR_MAX_LENGTH,errorMessage , data);
			}
		}
	}
	/**
	 * 字符串最小长度判定
	 * @param value
	 * @param data
	 */
	public static void minLength(String value,int min,Object data){
		if(value!=null){
			if(value.length()<min){
				String errorMessage = TempStringUtils.temp("minLength error{min:{x},value:{x}}",min,value);
				throw new ValidateError(ERROR_CODE_FOR_MIN_LENGTH,errorMessage , data);
			}
		}
	}
	
	
}
