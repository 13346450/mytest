package com.dnake.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 继承spring framework的dispatcherServlet，增加字符集编码转换<br/>
 * ClassName:EncodingDispatcherServlet <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014年3月17日 上午10:15:06 <br/>
 * @author   ts
 * @version  
 * @see 	 
 */
@SuppressWarnings("serial")
public class EncodingDispatcherServlet extends DispatcherServlet {
	private String encoding;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		super.init(config);
	}
	
	@Override
	protected void doService(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		arg0.setCharacterEncoding(encoding); 
		super.doService(arg0, arg1);
	}

}
