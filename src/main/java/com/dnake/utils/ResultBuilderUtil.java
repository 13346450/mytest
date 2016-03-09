package com.dnake.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.dnake.domain.common.JsonResult;
/**
 * 返回值拼装类
*  ResultBuilderUtil <br/> 
*  2015年3月27日 下午6:31:39 <br/> 
* @author zhou guojian 
* @version
 */
public class ResultBuilderUtil {
	/**
	 * 返回成功 "{\"status\":\"1\",\"message\":\"操作成功\",\"data\":{}}"
	 */
	public static String RESULT_SUCCESS = "{\"status\":\"1\",\"message\":\"操作成功\",\"data\":{}}";
	/**
	 * 返回session为空  "{\"status\":\"101\",\"message\":\"未登录,或登录超时\",\"data\":{}}"
	 */
	public static String RESULT_ERROR_SESSION_IS_NULL = "{\"status\":\"101\",\"message\":\"未登录,或登录超时\",\"data\":{}}";
	/**
	 * 返回参数缺少  "{\"status\":\"102\",\"message\":\"参数缺少或错误\",\"data\":{}}"
	 */
	public static String RESULT_ERROR_MISSING_PARAMETER = "{\"status\":\"102\",\"message\":\"参数缺少或错误\",\"data\":{}}";
	/**
	 * 返回无此cmd命令  "{\"status\":\"103\",\"message\":\"无此cmd命令\",\"data\":{}}"
	 */
	public static String RESULT_ERROR_INVALID_CMD = "{\"status\":\"103\",\"message\":\"无此cmd命令\",\"data\":{}}";
	
	/**
	 * 短信验证返回Json 格式："{\"status\":"+ status +"}"
	 * @param status
	 * @return
	 */
	public static String smsVerifyJson(String status)
	{
		return "{\"status\":"+ status +"}";
	}
	
	/**
	 * 返回操作成功，但出现其他状态的Json字串
	 * @param result	返回状态
	 * @param note		描述
	 * @return
	 */
	public static String statusMessage(int status, String message){
		 return "{\"status\":"+status+",\"message\":\""+message+"\",\"data\":{}}";
	}
	/**
	 * 返回包含返回值的字段值
	 * @param key
	 * @param value
	 * @return
	 */
	public static String resultIncludeValue(String key, Object value)
	{
		return "{\"status\":\"1\",\"message\":\"操作成功\",\"data\":{\"" + key + "\":"+ value +"}}";
	}
	/**
	 * json拼装
	* @title      jsonBuild 
	* @author  chen qige     
	* @date      2015年3月31日 
	*  @param jsonRs
	*  @return
	 */
	public static String jsonBuild(JsonResult jsonRs) {
		String json=JSON.toJSONString(jsonRs, filter);
		return json;
	}
	/**
	 * 返回相应的json字串
	 * @param jsonRs
	 * @param filters
	 * @return
	 */
	public static String jsonBuild(JsonResult jsonRs, PropertyPreFilterHaveNull filters)
	{
		String json=JSON.toJSONString(jsonRs, filters);
		return json;
	}
	
	public static String jsonTojsonp(String callback,String json){
		return callback+"("+json+")";
	}
	
	/**
	 * 过滤器，将null转化成""
	 */
	private static ValueFilter filter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    if(v==null)
	        return "";
	    return v;
	    }
	};
	
	
}
