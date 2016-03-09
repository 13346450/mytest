package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizComment;
import com.dnake.domain.business.BizCommentVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizCommentService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizComment/*")
@Login
public class BizCommentController extends BaseController
{

	@Resource
	private BizCommentService bizCommentService;

	@Resource
	private CommonService commonSevice;

	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizCommentVO> listPage(@RequestParam("page") int pageIndex,
			int rows, SearchParam searchParam)
	{
		return bizCommentService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizComment bizComment)
	{
		outputString(response, bizCommentService.update(bizComment));
	}


	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam)
	{
		outputString(response,
				bizCommentService.deleteMulti(searchParam.getDeleteIds()));
	}

}
