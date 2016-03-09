package com.dnake.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 设置jsp页面中的base
* @ClassName: ApplicationServlet 
* @Description: 
* @author ts 
* @date 2013-9-26 下午2:19:13 
*
 */
public class ApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ServletContext application;
	
	public void init(ServletConfig config) throws ServletException {
		application = config.getServletContext();
		application.setAttribute("base", application.getContextPath());
		application.setAttribute("staticBaseUrl", application.getContextPath());
	}

	public void destroy() {
		application.removeAttribute("base");
		application.removeAttribute("staticBaseUrl");
		super.destroy();
	}

}
