package com.dnake.controller.business;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @ClassName: JUnitActionBase
 * @Description: 这里用一句话描述这个类的作用
 *
 */
public class JUnitActionBase {
	private static HandlerMapping handlerMapping;
	private static HandlerAdapter handlerAdapter;

	/**
	 * 读取spring3 MVC配置文件
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@BeforeClass
	@Ignore
	public static void setUp() throws UnsupportedEncodingException {
		if (handlerMapping == null) {
			String[] configs = { "classpath:config/springmvc-servlet.xml", "classpath:config/applicationContext.xml" };
			XmlWebApplicationContext context = new XmlWebApplicationContext();
			context.setConfigLocations(configs);
			MockServletContext msc = new MockServletContext();
			context.setServletContext(msc);
			context.refresh();
			msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
			handlerMapping = (HandlerMapping) context.getBean(RequestMappingHandlerMapping.class);
			handlerAdapter = (HandlerAdapter) context.getBean(context.getBeanNamesForType(RequestMappingHandlerAdapter.class)[0]);
		}
	}

	/**
	 * 执行request对象请求的action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView excuteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		final ModelAndView model = handlerAdapter.handle(request, response, chain.getHandler());
		return model;
	}
}
