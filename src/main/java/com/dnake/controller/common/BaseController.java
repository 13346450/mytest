package com.dnake.controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;

/**
 * Controller的基类，所有Controller都继承该类，实现一些公共功能
* @ClassName: BaseController 
* @Description: 
* @author ts 
* @date 2013-10-9 上午10:29:57 
*
 */
public abstract class BaseController {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 输出字符串到客户端
	* @Title: outputString 
	* @author ts  2013-10-17
	* @Description: 
	* @param response
	* @param json    
	* @return void    
	* @throws
	 */
	protected void outputString(HttpServletResponse response,String json){
		try {
			response.setContentType("text/html;charset="+Constants.CHARSET);
			response.addHeader("Server-Time", DateTimeUtil.getDateToStrFullFormat(new Date()));
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.toString());
		}
	}
}
