package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizPropertiesRepairService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizPropertiesRepair/*")
@Login
public class BizPropertiesRepairController extends BaseController
{

	@Resource
	private BizPropertiesRepairService bizPropertyRepairService;

	@Resource
	private CommonService commonSevice;

	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizPropertiesRepairVO> listPage(
			@RequestParam("page") int pageIndex, int rows,
			SearchParam searchParam)
	{
		return bizPropertyRepairService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response,
			BizPropertiesRepair bizPropertyRepair)
	{
		outputString(response,
				bizPropertyRepairService.update(bizPropertyRepair));
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response,
			BizPropertiesRepair bizPropertyRepair)
	{
		outputString(response,
				bizPropertyRepairService.insert(bizPropertyRepair));
	}

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam)
	{
		outputString(response, bizPropertyRepairService.deleteMulti(searchParam
				.getDeleteIds()));
	}

}
