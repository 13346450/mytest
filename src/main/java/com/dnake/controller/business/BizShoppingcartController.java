package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizShoppingcart;
import com.dnake.domain.business.BizShoppingcartVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizShoppingcartService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizShoppingcart/*")
@Login
public class BizShoppingcartController extends BaseController
{

	@Resource
	private BizShoppingcartService bizShoppingcartService;

	@Resource
	private CommonService commonSevice;

	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizShoppingcartVO> listPage(
			@RequestParam("page") int pageIndex, int rows,
			SearchParam searchParam)
	{
		return bizShoppingcartService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response,
			BizShoppingcart bizShoopingcart)
	{
		outputString(response, bizShoppingcartService.update(bizShoopingcart));
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response,
			BizShoppingcart bizShoopingcart)
	{
		outputString(response, bizShoppingcartService.insert(bizShoopingcart));
	}

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam)
	{
		outputString(response,
				bizShoppingcartService.deleteMulti(searchParam.getDeleteIds()));
	}

}
