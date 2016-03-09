package com.dnake.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.dnake.domain.common.Parameter;
import com.dnake.service.business.BizMessageService;


/**
 * 全局引导器
 *  GlobalLoader <br/>
 *  2014年9月27日 上午11:15:49 <br/>
 * @author ts
 * @version
 */
public class GlobalLoader {
	
	private final Logger log = Logger.getLogger(GlobalLoader.class);
	
	/**
	 * 全局的loader
	 */
	private static GlobalLoader globalLoader;
	/**
	 * 访问androidpn的服务端地址,没有用，保留
	 */
	private String androidpnAddr;
	
	private Parameter parameter=new Parameter();
	/**
	 * 获得SocketLoader的全局实例
	 * @title      getInstance
	 * @author  ts    
	 * @date      2014年3月5日 
	 * @return
	 */
	public static GlobalLoader getInstance(){
		if(null == globalLoader){
			globalLoader = new GlobalLoader();
		}
		return globalLoader;
	}
	
	/**
	 * 初始化监听
	 * @title      initListener
	 * @author  ts    
	 * @date      2014年3月5日 
	 * @param servletContext
	 */
	public void initListener(ServletContext servletContext){
		//1.获得参数
		getParms();
		
		}
	
	/**
	 * 关闭监听
	* @Title: shutdownListener 
	* @author ts  2013-6-13
	* @Description:     
	* @return void    
	* @throws
	 */
	public void shutdownListener(){
		
	}
	
	
	/**
	 * 获取配置参数
	 */
	private void getParms(){
		Properties props = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().replaceAll("%20", " ");
		path = path.concat("config/parms.properties");
		InputStream in=null;
		try {
			 in = new BufferedInputStream(new FileInputStream(path));
			props.load(in);
			this.parameter.setSimagesWidth(Integer.parseInt(props.getProperty("simages.width")));
			this.parameter.setSimagesHeight(Integer.parseInt(props.getProperty("simages.height")));
			this.parameter.setLimagesWidth(Integer.parseInt(props.getProperty("limages.width")));
			this.parameter.setLimagesHeight(Integer.parseInt(props.getProperty("limages.height")));
			this.parameter.setShowSimagesWidth(Integer.parseInt(props.getProperty("showSimages.width")));
			this.parameter.setShowSimagesHeight(Integer.parseInt(props.getProperty("showSimages.height")));
			this.parameter.setShowLimagesWidth(Integer.parseInt(props.getProperty("showLimages.width")));
			this.parameter.setShowLimagesHeight(Integer.parseInt(props.getProperty("showLimages.height")));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getAndroidpnAddr() {
		return androidpnAddr;
	}

	public void setAndroidpnAddr(String androidpnAddr) {
		this.androidpnAddr = androidpnAddr;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	
}
