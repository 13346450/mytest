package com.dnake.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnake.controller.common.BaseController;
import com.dnake.service.business.BizAppService;
import com.dnake.service.common.CommonService;

/**
 * 手机app版本管理 BizAppController <br/>
 * 2014年2月19日 下午6:43:36 <br/>
 *
 * @author ts
 * @version
 */
@Controller
public class SysAppDownloadController extends BaseController {
	@Resource
	private BizAppService bizAppService;
	@Resource
	private CommonService commonService;


	/**
	 * 请求下载对应版本的app
	 * @param respone
	 * @param type
	 * @author cqg 2015年6月16日
	 */
	@RequestMapping(value = "d/{type}")
	public String downLoadLatestApp(HttpServletResponse respone,
			@PathVariable String type) {
		String s=bizAppService.downLoadLatestApp(type);
		if(s!=null){
			return "redirect:/"+s;//转发到该文件资源
		}else{
			return null;
		}
	}
	
	/**
	 * 下载对应版本的app
	 * @param respone
	 * @param type
	 * @author cqg 2015年6月16日
	 */
	@RequestMapping(value = "r/{type}")
	public String downLoadLatestApp(HttpServletResponse respone,Model model,
			@PathVariable String type) {
		model.addAttribute("type", type);
		return "system/appDownLoad";
	}
}
