package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.ClearLogin;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizAddr;
import com.dnake.domain.business.BizAddrVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizAddrService;
import com.dnake.service.common.CommonService;

/**
 *  @author zgj
 *	日期：2015年3月24日下午7:02:27
 *  描述：地址处理控制层
 */
@Controller
@RequestMapping("/business/BsIds/*")
@Login

public class BizAddrController extends BaseController
{

	@Resource
	private BizAddrService bizAddrService;

	@Resource
	private CommonService commonSevice;

	@RequestMapping(value = "listPage")
	@ResponseBody
	@ClearLogin
	public Page<BizAddrVO> listPage(@RequestParam("page") int pageIndex,
			int rows, SearchParam searchParam)
	{
		return bizAddrService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizAddr bizAddr)
	{
		outputString(response, bizAddrService.insert(bizAddr));
	}

}
