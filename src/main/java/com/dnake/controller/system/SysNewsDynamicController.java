package com.dnake.controller.system;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.interceptor.ClearLogin;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysNewsDynamic;
import com.dnake.domain.system.SysNewsDynamicVO;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysNewsDynamicService;
import com.dnake.utils.Constants;
import com.dnake.utils.StringUtils;

@Controller
@RequestMapping("/system/SysNewsDynamic/*")
@Login
public class SysNewsDynamicController extends BaseController {

	@Resource
	private SysNewsDynamicService sysNewsDynamicService;
	@Resource
	private CommonService commonService;
	/**
	 * 进入关于我们的页面
	 * @param model
	 * @return
	 * 测试 http://localhost:8080/SmartCommunityWeb/system/SysNews/
	 */
	@RequestMapping("")
	@ClearLogin
	public String index(Model model,@RequestParam(value="page",defaultValue="1") int pageIndex,SysNewsDynamic bizNewsDynamic) {
		Page<SysNewsDynamicVO> pagesPage = sysNewsDynamicService.pageByType(pageIndex,3,bizNewsDynamic);
		model.addAttribute("sysNewsDynamicVOPage", pagesPage);
		return "system/pc/newsDynamic";
	}
	/**
	 * 进入新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add")
	public String add(Model model) {
		return "propertyManage/newsDynamicAdd";
	}
	
	/**
	 * 进入修改页面
	 * @param idKey
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String edit(@PathVariable Long idKey, Model model) {
		model.addAttribute("bizNewsDynamic", sysNewsDynamicService.queryByIdKey(idKey));
		return "propertyManage/newsDynamicEdit";
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
	public Page<SysNewsDynamicVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam) {
		return sysNewsDynamicService.listPage(pageIndex, rows, searchParam);
	}
	
	/**
	 * 查询列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public List<SysNewsDynamicVO> list(SearchParam searchParam) {
		return sysNewsDynamicService.list(searchParam);
	}

	/**
	 * 更新
	 * @param response
	 * @param bizNewsDynamic
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, SysNewsDynamic bizNewsDynamic) {
		this.outputString(response, sysNewsDynamicService.update(bizNewsDynamic));
	}

	/**
	 * 插入
	 * @param response
	 * @param bizNewsDynamic
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, SysNewsDynamic bizNewsDynamic) {
		this.outputString(response, sysNewsDynamicService.insert(bizNewsDynamic));
	}
	
	/**
	 * 删除
	 * @param idKey
	 * @param response
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(@PathVariable Long idKey,HttpServletResponse response){
		this.outputString(response, sysNewsDynamicService.delete(idKey));
	}

	// /**
	// * 批量删除
	// * @param response
	// * @param searchParam
	// */
	// @RequestMapping(value = "deleteMulti")
	// public void deleteMulti(HttpServletResponse response, SearchParam
	// searchParam) {
	// this.outputString(response,
	// sysNewsDynamicService.deleteMulti(searchParam.getDeleteIds()));
	// }
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, @RequestParam(value = "upFile") MultipartFile mfile, String link) {
		File file = new File(session.getServletContext().getRealPath("/"));
		File r = new File(file,Constants.NEWS_DYNAMIC_FILE);
		outputString(response, commonService.uploadFile(mfile,  r.getAbsolutePath() , StringUtils.convertCharacter(link, Constants.CHARSET), "chain"));
	}
	
}
