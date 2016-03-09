package com.dnake.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSEvent;
import com.dnake.domain.system.SysAboutUSEventVO;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSEventService;

@Controller
@RequestMapping("/system/SysAboutUSEvent/*")
@Login
public class SysAboutUSEventController extends BaseController {

	@Resource
	private SysAboutUSEventService sysAboutUSEventService;
	@Resource
	private CommonService commonSevice;
//	/**
//	 * 进入新增页面
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "add")
//	public String add(Model model) {
//		return "system/aboutUsEventAdd";
//	}
//	
//	/**
//	 * 进入修改页面
//	 * @param idKey
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "edit")
//	public String edit(@PathVariable Long idKey, Model model) {
//		model.addAttribute("bizAboutUSEvent", sysAboutUSEventService.queryByIdKey(idKey));
//		return "system/aboutUsEventEdit";
//	}

	/**
	 * 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<SysAboutUSEventVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam) {
		return sysAboutUSEventService.listPage(pageIndex, rows, searchParam);
	}
	
	/**
	 * 查询列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public List<SysAboutUSEventVO> list(SearchParam searchParam) {
		return sysAboutUSEventService.list(searchParam);
	}

	/**
	 * 更新
	 * @param response
	 * @param bizAboutUSEvent
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, SysAboutUSEvent bizAboutUSEvent) {
		this.outputString(response, sysAboutUSEventService.update(bizAboutUSEvent));
	}

	/**
	 * 插入
	 * @param response
	 * @param bizAboutUSEvent
	 * 测试 http://localhost:8080/IShangWeb/system/SysAboutUSEvent/insert?eventTitle=1&eventSubtitle=2&eventContent=3&happenTime=2015-7-7
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, SysAboutUSEvent sysAboutUSEvent) {
		this.outputString(response, sysAboutUSEventService.insert(sysAboutUSEvent));
	}
	
	/**
	 * 删除
	 * @param idKey
	 * @param response
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(@PathVariable Long idKey,HttpServletResponse response){
		this.outputString(response, sysAboutUSEventService.delete(idKey));
	}

//	/**
//	 * 批量删除
//	 * @param response
//	 * @param searchParam
//	 */
//	@RequestMapping(value = "deleteMulti")
//	public void deleteMulti(HttpServletResponse response, SearchParam searchParam) {
//		this.outputString(response, sysAboutUSEventService.deleteMulti(searchParam.getDeleteIds()));
//	}
	
}
