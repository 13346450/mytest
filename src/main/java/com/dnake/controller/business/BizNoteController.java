package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizNoteService;
import com.dnake.service.common.CommonService;

/**
 * 帖子管理控制层
 * @ClassName BizNoteController
 * @author zgj
 * 2014年9月16日 下午5:23:26
 */
@Controller
@RequestMapping("")
@Login
public class BizNoteController extends BaseController
{
	@Resource
	private BizNoteService bizNoteService;
	@Resource
	private CommonService commonSevice;
	
	/**
	 * 删除一条帖子
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "D00010001")
	public void delete(HttpServletResponse response, @PathVariable Long idKey)
	{
		outputString(response,bizNoteService.delete(idKey));
	}
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizNoteVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam)
	{
		return bizNoteService.listPage(pageIndex, rows, searchParam);
	}
	/**
	 * 多条删除
	 * @param response
	 * @param searchParam
	 */
	@RequestMapping(value="deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
		outputString(response, bizNoteService.deleteMulti(searchParam.getDeleteIds())); 
	}
}
