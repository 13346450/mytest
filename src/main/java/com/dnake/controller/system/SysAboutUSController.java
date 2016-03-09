package com.dnake.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysAboutUS;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSService;

@Controller
@RequestMapping("/system/SysAboutUS/*")
@Login
public class SysAboutUSController extends BaseController {

	@Resource
	private SysAboutUSService bizAboutUSService;
	@Resource
	private CommonService commonSevice;
	/**
	 * 获得数据
	 * @return
	 */
	@RequestMapping("getInfo")
	@ResponseBody
	public SysAboutUS getInfo() {
		SysAboutUS aboutUS = bizAboutUSService.queryFirst();
		if(aboutUS==null){
			aboutUS = new SysAboutUS();
		}
		return aboutUS;
	}

	/**
	 * 创建更新
	 * @param response
	 * @param bizAboutUS
	 * 测试 http://localhost:8080/IShangWeb/system/SysAboutUS/createOrUpdate?aboutUSTitle=1&aboutUSSubtitle=2&aboutUSContent=3
	 */
	@RequestMapping(value = "createOrUpdate")
	public void createOrUpdate(HttpServletResponse response, SysAboutUS sysAboutUS) {
		this.outputString(response, bizAboutUSService.createOrUpdate(sysAboutUS));
	}
//	/**
//	 * 进入新增页面
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "add")
//	public String add(Model model) {
//		return "propertyManage/aboutUSAdd";
//	}
	
//	/**
//	 * 进入修改页面
//	 * @param idKey
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "edit")
//	public String edit(@PathVariable Long idKey, Model model) {
//		model.addAttribute("bizAboutUS", bizAboutUSService.queryByIdKey(idKey));
//		return "propertyManage/aboutUSEdit";
//	}
//
//	/**
//	 * 分页查询
//	 * @param pageIndex
//	 * @param rows
//	 * @param searchParam
//	 * @return
//	 */
//	@RequestMapping(value = "listPage")
//	@ResponseBody
//	public Page<SysAboutUSVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam) {
//		return bizAboutUSService.listPage(pageIndex, rows, searchParam);
//	}
//	
//	/**
//	 * 查询列表
//	 * @param searchParam
//	 * @return
//	 */
//	@RequestMapping(value = "list")
//	@ResponseBody
//	public List<SysAboutUSVO> list(SearchParam searchParam) {
//		return bizAboutUSService.list(searchParam);
//	}
//	/**
//	 * 插入
//	 * @param response
//	 * @param bizAboutUS
//	 */
//	@RequestMapping(value = "insert")
//	public void insert(HttpServletResponse response, SysAboutUS bizAboutUS) {
//		this.outputString(response, bizAboutUSService.insert(bizAboutUS));
//	}

//	/**
//	 * 删除
//	 * @param idKey
//	 * @param response
//	 */
//	@RequestMapping(value = "delete")
//	public void delete(@RequestParam Long idKey,HttpServletResponse response){
//		this.outputString(response, bizAboutUSService.delete(idKey));
//	}
//	
//	/**
//	 * 批量删除
//	 * @param response
//	 * @param searchParam
//	 */
//	@RequestMapping(value = "deleteMulti")
//	public void deleteMulti(HttpServletResponse response, SearchParam searchParam) {
//		this.outputString(response, bizAboutUSService.deleteMulti(searchParam.getDeleteIds()));
//	}
	
}
