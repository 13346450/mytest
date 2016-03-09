package com.dnake.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysAboutUSCulture;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSCultureService;

@Controller
@RequestMapping("/system/SysAboutUSCulture/*")
@Login
public class SysAboutUSCultureController extends BaseController {

	@Resource
	private SysAboutUSCultureService bizAboutUSCultureService;
	@Resource
	private CommonService commonSevice;
	/**
	 * 获得数据
	 * @return SysAboutUSCulture
	 */
	@RequestMapping("getInfo")
	@ResponseBody
	public SysAboutUSCulture getInfo() {
		SysAboutUSCulture sysAboutUSCulture = bizAboutUSCultureService.queryFirst();
		if(sysAboutUSCulture==null){
			sysAboutUSCulture = new SysAboutUSCulture();
		}
		return sysAboutUSCulture;
	}
	/**
	 * 创建或更新
	 * @param response
	 * @param sysAboutUSCulture
	 * 测试 system/SysAboutUSCulture/createOrUpdate?cultureTitle=1&cultureSubtitle=2&cultureContent=3
	 */
	@RequestMapping(value = "createOrUpdate")
	public void createOrUpdate(HttpServletResponse response, SysAboutUSCulture sysAboutUSCulture) {
		this.outputString(response, bizAboutUSCultureService.createOrUpdate(sysAboutUSCulture));
	}
//	/**
//	 * 进入新增页面
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "add")
//	public String add(Model model) {
//		return "propertyManage/aboutUSCultureAdd";
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
//		model.addAttribute("bizAboutUSCulture", bizAboutUSCultureService.queryByIdKey(idKey));
//		return "propertyManage/aboutUSCultureEdit";
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
//	public Page<SysAboutUSCultureVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam) {
//		return bizAboutUSCultureService.listPage(pageIndex, rows, searchParam);
//	}
//	
//	/**
//	 * 查询列表
//	 * @param searchParam
//	 * @return
//	 */
//	@RequestMapping(value = "list")
//	@ResponseBody
//	public List<SysAboutUSCultureVO> list(SearchParam searchParam) {
//		return bizAboutUSCultureService.list(searchParam);
//	}
//
//	/**
//	 * 更新
//	 * @param response
//	 * @param bizAboutUSCulture
//	 */
//	@RequestMapping(value = "update")
//	public void update(HttpServletResponse response, SysAboutUSCulture bizAboutUSCulture) {
//		this.outputString(response, bizAboutUSCultureService.update(bizAboutUSCulture));
//	}
//
//	/**
//	 * 插入
//	 * @param response
//	 * @param bizAboutUSCulture
//	 */
//	@RequestMapping(value = "insert")
//	public void insert(HttpServletResponse response, SysAboutUSCulture bizAboutUSCulture) {
//		this.outputString(response, bizAboutUSCultureService.insert(bizAboutUSCulture));
//	}
//
//	/**
//	 * 删除
//	 * @param idKey
//	 * @param response
//	 */
//	@RequestMapping(value = "delete")
//	public void delete(@RequestParam Long idKey,HttpServletResponse response){
//		this.outputString(response, bizAboutUSCultureService.delete(idKey));
//	}
//	
//	/**
//	 * 批量删除
//	 * @param response
//	 * @param searchParam
//	 */
//	@RequestMapping(value = "deleteMulti")
//	public void deleteMulti(HttpServletResponse response, SearchParam searchParam) {
//		this.outputString(response, bizAboutUSCultureService.deleteMulti(searchParam.getDeleteIds()));
//	}
	
}
