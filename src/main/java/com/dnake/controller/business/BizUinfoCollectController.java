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
import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizUinfoCollectService;
import com.dnake.service.common.CommonService;

/**
 * 住户信息采集控制层
 * @ClassName BizUinfoCollectController
 * @author zgj
 * 2014年9月12日 上午8:39:25
 */
@Controller
@RequestMapping("/business/BizUinfoCollect/*")
@Login
public class BizUinfoCollectController extends BaseController
{
	@Resource
	private BizUinfoCollectService bizUinfoCollectService;
	@Resource
	private CommonService commonService;
	
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizUinfoCollectVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam)
	{
		return bizUinfoCollectService.listPage(pageIndex, rows, searchParam);
	}
	/**
	 * 删除住户信息
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey)
	{
		outputString(response, bizUinfoCollectService.delete(idKey));
	}
	/**
	 * 多条删除
	 * @param response
	 * @param searchParam
	 */
	@RequestMapping(value="deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
		outputString(response, bizUinfoCollectService.deleteMulti(searchParam.getDeleteIds())); 
	}
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizUinfoCollect bizUinfoCollect)
	{
		outputString(response, bizUinfoCollectService.insert(bizUinfoCollect));
	}
}
