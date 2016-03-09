package com.dnake.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSHonor;
import com.dnake.domain.system.SysAboutUSHonorVO;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSHonorService;
import com.dnake.utils.Constants;
import com.dnake.utils.StringUtils;

@Controller
@RequestMapping("/system/SysAboutUSHonor/*")
@Login
public class SysAboutUSHonorController extends BaseController {

	@Resource
	private SysAboutUSHonorService bizAboutUSHonorService;
	@Resource
	private CommonService commonService;
//	/**
//	 * 进入新增页面
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "add")
//	public String add(Model model) {
//		return "system/aboutUSHonorAdd";
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
//		model.addAttribute("bizAboutHonorUS", bizAboutUSHonorService.queryByIdKey(idKey));
//		return "system/aboutUSHonorEdit";
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
	public Page<SysAboutUSHonorVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam) {
		return bizAboutUSHonorService.listPage(pageIndex, rows, searchParam);
	}
	
	/**
	 * 查询列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public List<SysAboutUSHonorVO> list(SearchParam searchParam) {
		return bizAboutUSHonorService.list(searchParam);
	}

	/**
	 * 更新
	 * @param response
	 * @param bizAboutUSHonor
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, SysAboutUSHonor bizAboutUSHonor) {
		this.outputString(response, bizAboutUSHonorService.update(bizAboutUSHonor));
	}

	/**
	 * 插入
	 * @param response
	 * @param sysAboutUSHonor
	 * 测试 http://localhost:8080/IShangWeb/system/SysAboutUSHonor/insert?honorDate=2015-07-07&honorName=bighonor&honorBigImageUrl=bigurl&honorSmallImageUrl=honorSmallImageUrl&awardOrg=awardOrg&remark=remark
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, SysAboutUSHonor sysAboutUSHonor) {
		this.outputString(response, bizAboutUSHonorService.insert(sysAboutUSHonor));
	}
	
	/**
	 * 删除
	 * @param idKey
	 * @param response
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(@PathVariable Long idKey,HttpServletResponse response){
		this.outputString(response, bizAboutUSHonorService.delete(idKey));
	}

	/**
	 * 批量删除
	 * @param response
	 * @param searchParam
	 */
	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam) {
		this.outputString(response, bizAboutUSHonorService.deleteMulti(searchParam.getDeleteIds()));
	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, @RequestParam(value = "upFile") MultipartFile mfile, String link) {
		outputString(response, commonService.uploadFile(mfile, session.getServletContext().getRealPath("/") + Constants.ABOUTUS_CULTURE_FILE, StringUtils.convertCharacter(link, Constants.CHARSET), "chain"));
	}
}
