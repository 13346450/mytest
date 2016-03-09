package com.dnake.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 全局socket监听器
* @ClassName: SocketListener 
* @Description: 
* @author ts 
* @date 2013-6-8 上午11:24:50 
*
 */
public class GlobalListener implements ServletContextListener{
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		GlobalLoader.getInstance().shutdownListener();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		GlobalLoader.getInstance().initListener(event.getServletContext());
	}
	
}
