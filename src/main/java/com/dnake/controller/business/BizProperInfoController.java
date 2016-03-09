package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizProperInfo;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizChainService;
import com.dnake.service.business.BizProperInfoService;
import com.dnake.service.common.CommonService;

/**
 * 物业信息控制层
 * @ClassName BizProperInfoController
 * @author zgj
 * 2014年9月5日 上午9:16:36
 */
@Controller
@RequestMapping("/business/BizProperInfo/*")
@Login
public class BizProperInfoController extends BaseController
{
	@Resource
	private BizProperInfoService bizProperInfoService;
	@Resource
	private BizChainService bizChainService;
	@Resource
	private CommonService commonSevice;
	
	/**
	 * 删除一条物业信息
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey)
	{
		outputString(response,bizProperInfoService.delete(response, idKey));
	}
	/**
	 * 插入一条物业信息
	 * @param response
	 * @param bizProperInfo
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizProperInfo bizProperInfo)
	{
		outputString(response, bizProperInfoService.insert(bizProperInfo));
	}
	/**
	 * 更新一条物业信息
	 * @param response
	 * @param bizProperInfo
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizProperInfo bizProperInfo)
	{
		outputString(response, bizProperInfoService.update(bizProperInfo));
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
	public Page<BizProperInfoVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam)
	{
		return bizProperInfoService.listPage(pageIndex, rows, searchParam);
	}
	/**
	 * 发布
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value="publishProperInfo/{idKey}")
	public void publishAdvert(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizProperInfoService.updateStatus(idKey, "publish"));
	}

	/**
	 * 取消发布
	 * @param response
	 * @param idKey
	 * 
	 */
	@RequestMapping(value="cancelPublishProperInfo/{idKey}")
	public void cancelPublishAdvert(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizProperInfoService.updateStatus(idKey, "cancelPublish"));
	}
	
	
	@RequestMapping(value="addProperInfo",method = RequestMethod.GET)
	public ModelAndView initForm(ModelMap model)
	{
		BizProperInfoVO bizProperInfo = new BizProperInfoVO(); //用于转换到form表单的对象
		return new ModelAndView("addCompany").addObject(bizProperInfo); //跳转到addCompany页面
	}
	
	@RequestMapping(value="deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
		outputString(response, bizProperInfoService.deleteMulti(searchParam.getDeleteIds())); 
	}
}
