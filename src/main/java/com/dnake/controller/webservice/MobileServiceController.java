package com.dnake.controller.webservice;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dnake.common.interceptor.ClearLogin;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizChain;
import com.dnake.domain.common.MobileParms;
import com.dnake.service.business.BizChainService;
import com.dnake.service.business.BizHitRecordService;
import com.dnake.service.business.BizMobileService;

/**
 * 手机端对外的服务 <br/>
 *  MobileServiceController <br/>
 *  2014年3月4日 上午10:02:12 <br/>
 * @author ts
 * @version
 */
@Controller
@RequestMapping("/appservice/*")
@Login
public class MobileServiceController extends BaseController
{
	@Resource
	private BizMobileService bizMobileControlService;
	@Resource
	private BizChainService bizChainService;
	@Resource
	private BizHitRecordService bizHitRecordService;
	
	/**
	 * 手机访问，以 Json 形式返回的接口，需要登录
	 * @title      mobileRequest
	 * @author  ts    
	 * @date      2014年9月11日 
	 * @param response
	 * @param parms
	 */
	@RequestMapping(value = "ehome")
	public void mobileRequest(HttpServletRequest request, 
													HttpServletResponse response, 
													MobileParms parms) 
	{
		outputString(response,bizMobileControlService.mobileRequest(request, parms));
	}
	
	/**
	 * 手机访问的接口，无登录时调用（没有session）
	 * @title      mobileLogin
	 * @author  ts    
	 * @date      2014年9月26日 
	 * @param request
	 * @param response
	 * @param parms
	 */
	@RequestMapping(value = "mobileLogin")
	@ClearLogin
	public void mobileLogin(HttpServletRequest request, 
													HttpServletResponse response, 
													MobileParms parms) 
	{
		outputString(response,bizMobileControlService.mobileRequestNoLogin(request, parms));
	}
	/**
	 * 打开一个链接，并写入点击记录表
	 * @param response
	 * @param idKey
	 * 
	 * @param model
	 */
	@RequestMapping(value = "viewChain/{idKey}")
	public void getChain(HttpServletResponse response,@PathVariable Long idKey)
	{
		bizHitRecordService.insertMobileClicksInfo(idKey);
		BizChain bizChain = bizChainService.queryById(idKey);
		try
		{
			response.sendRedirect(bizChain.getLinksUrl());
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("访问错误："+ e.toString());
		}
	}

}
