package com.dnake.controller.business;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizChain;
import com.dnake.domain.business.BizHitRecord;
import com.dnake.domain.business.BizHitRecordVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizChainService;
import com.dnake.service.business.BizHitRecordService;
import com.dnake.service.common.CommonService;

/**
 * 控制层
 * @ClassName BizHitRecordController
 * @author zgj
 * 2014年9月3日 下午2:31:23
 */

@Controller
@RequestMapping("/business/BizHitRecord/*")
@Login
public class BizHitRecordController extends BaseController
{
	@Resource
	private BizHitRecordService bizHitRecordService;
	@Resource
	private BizChainService bizChainService;
	@Resource
	private CommonService commonService;
	
	/**
	 * 按条件分页查询
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizHitRecordVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
		return bizHitRecordService.listPage(pageIndex ,rows, searchParam);
	}
	/**
	 * 插入一条数据
	 * @param response
	 * @param bizHitRecord
	 */
	@RequestMapping(value = "view/{idKey}")
	public void insert(HttpServletResponse response,@PathVariable Long idKey)
	{
		bizHitRecordService.insertMobileClicksInfo(idKey);
		BizChain bizChain = bizChainService.queryById(idKey);
		try
		{
			response.sendRedirect(bizChain.getLinksUrl());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新一条数据
	 * @param response
	 * @param bizHitRecord
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizHitRecord bizHitRecord)
	{
		outputString(response,bizHitRecordService.update(bizHitRecord)); 
	}
	
	/**
	 * 删除一条数据
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey)
	{
			outputString(response, bizHitRecordService.delete(response, idKey)); 
		}
	
	
	
}
