package com.dnake.service.business;

import javax.servlet.http.HttpServletRequest;

import com.dnake.domain.common.MobileParms;

/**
 * 设备控制业务接口
 *  BizDeviceControlService <br/>
 *  2014年3月4日 上午9:09:13 <br/>
 * @author ts
 * @version
 */
public interface BizMobileService {

	/**
	 * 手机调用的请求，必须登录（有session）
	 * @title      mobileRequest
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param   MobileParms 
	 * @return
	 */
	String mobileRequest(HttpServletRequest request, MobileParms parms);

	/**
	 * 手机调用的请求，没有登录时（无session）
	 * @title      mobileRequestNoLogin
	 * @author  ts    
	 * @date      2014年9月26日 
	 * @param request
	 * @param parms
	 * @return
	 */
	String mobileRequestNoLogin(HttpServletRequest request, MobileParms parms);
	
	
	
}
