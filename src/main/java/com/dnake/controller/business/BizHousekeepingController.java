package com.dnake.controller.business;

import java.io.File;

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
import com.dnake.domain.business.BizHousekeeping;
import com.dnake.domain.business.BizHousekeepingVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizHousekeepingService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

@Controller
@RequestMapping("/business/BizHousekeeping/*")
@Login
public class BizHousekeepingController extends BaseController {

	@Resource
	private BizHousekeepingService bizHoursemakingService;
	@Resource
	private CommonService commonService;

	/**
	 * 创建
	 * 
	 * @param response
	 * @param bizAboutUS
	 *            测试 http://localhost:8080/IShangWeb/system/SysAboutUS/
	 *            createOrUpdate
	 *            ?aboutUSTitle=1&aboutUSSubtitle=2&aboutUSContent=3
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizHousekeeping housekeeping) {
		this.outputString(response, bizHoursemakingService.insert(housekeeping));
	}
	/**
	 * 根据分类进行搜索
	 * 
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "search")
	@ResponseBody
	public Page<BizHousekeepingVO> search(HttpSession session ,@RequestParam(value="page",defaultValue="1") int pageIndex,@RequestParam(value="rows",defaultValue="10") int rows, String names) {
		SessionBean bean = (SessionBean) session.getAttribute("sessionBean");
		return bizHoursemakingService.search(pageIndex, rows, names,bean.getDeptId());
	}
	/**
	 * 分页查询
	 * 
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizHousekeepingVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam) {
		return bizHoursemakingService.listPage(pageIndex, rows, searchParam);
	}

	/**
	 * 更新
	 * 
	 * @param response
	 * @param bizAboutUS
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizHousekeeping housekeeping) {
		this.outputString(response, bizHoursemakingService.update(housekeeping));
	}

	/**
	 * 删除
	 * 
	 * @param idKey
	 * @param response
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(@PathVariable Long idKey, HttpServletResponse response) {
		this.outputString(response, bizHoursemakingService.delete(idKey));
	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, @RequestParam(value = "upFile") MultipartFile mfile, String link) {
		File file = new File(session.getServletContext().getRealPath("/"));
		File r = new File(file, Constants.HOUSEKEEPING_FILE);
		outputString(response, commonService.uploadFile(mfile, r.getAbsolutePath(), StringUtils.convertCharacter(link, Constants.CHARSET), "chain"));
	}
}
