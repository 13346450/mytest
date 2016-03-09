package com.dnake.controller.business;

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
import com.dnake.domain.business.BizApp;
import com.dnake.domain.business.BizAppVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizAppService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;

/**
 * 手机app版本管理 BizAppController <br/>
 * 2014年2月19日 下午6:43:36 <br/>
 *
 * @author ts
 * @version
 */
@Controller
@RequestMapping("/business/BizApp/*")
@Login
public class BizAppController extends BaseController {
	@Resource
	private BizAppService bizAppService;
	@Resource
	private CommonService commonService;

	/**
	 * 按条件分页查询
	 * 
	 * @title listPage
	 * @author ts
	 * @date 2014年3月1日
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizAppVO> listPage(@RequestParam("page") int pageIndex,
			int rows, SearchParam searchParam) {
		return bizAppService.listPage(pageIndex, rows, searchParam);
	}

	/**
	 * 插入一条记录
	 * 
	 * @title insert
	 * @author ts
	 * @date 2014年3月1日
	 * @param response
	 * @param bstDevice
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizApp bizApp) {
		outputString(response, bizAppService.insert(bizApp));
	}

	/**
	 * 更新一条记录
	 * 
	 * @title update
	 * @author ts
	 * @date 2014年3月1日
	 * @param response
	 * @param bstDevice
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizApp bizApp) {
		outputString(response, bizAppService.update(bizApp));
	}

	/**
	 * 删除一条记录
	 * 
	 * @title delete
	 * @author ts
	 * @date 2014年3月1日
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey) {
		outputString(response, bizAppService.delete(response, idKey));
	}

	/**
	 * 上传文件
	 * 
	 * @title uploadFile
	 * @author ts
	 * @date 2014年3月31日
	 * @param session
	 * @param response
	 * @param mfile
	 * @param link
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "upFile") MultipartFile mfile) {
		outputString(
				response,
				bizAppService.uploadFile(mfile, session.getServletContext()
						.getRealPath("/") + Constants.APK_DIR));
	}

	/**
	 * 删除图片
	 * 
	 * @title deleteImage
	 * @author ts
	 * @date 2014年3月31日
	 * @param session
	 * @param response
	 * @param imageAddr
	 */
	@RequestMapping(value = "deleteFile")
	public void deleteFile(HttpSession session, HttpServletResponse response,
			String apkAddr) {
		outputString(response, commonService.deleteFile(response, apkAddr
				.substring(apkAddr.indexOf("/") + 1), session
				.getServletContext().getRealPath("/") + Constants.APK_DIR));
	}

	/**
	 * 审核
	 * 
	 * @title auditAdvert
	 * @author ts
	 * @date 2014年3月31日
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "auditApp/{idKey}")
	public void auditAdvert(HttpServletResponse response,
			@PathVariable Long idKey) {
		outputString(response, bizAppService.updateStatus(idKey, "audit"));
	}

	/**
	 * 取消审核
	 * 
	 * @title cancelAuditAdvert
	 * @author ts
	 * @date 2014年3月31日
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "cancelAuditApp/{idKey}")
	public void cancelAuditAdvert(HttpServletResponse response,
			@PathVariable Long idKey) {
		outputString(response, bizAppService.updateStatus(idKey, "cancelAudit"));
	}

	/**
	 * 发布
	 * 
	 * @title publishAdvert
	 * @author ts
	 * @date 2014年3月31日
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "publishApp/{idKey}")
	public void publishAdvert(HttpServletResponse response,
			@PathVariable Long idKey) {
		outputString(response, bizAppService.updateStatus(idKey, "publish"));
	}

	/**
	 * 取消发布
	 * 
	 * @title auditAdvert
	 * @author ts
	 * @date 2014年3月31日
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "cancelPublishApp/{idKey}")
	public void cancelPublishAdvert(HttpServletResponse response,
			@PathVariable Long idKey) {
		outputString(response,
				bizAppService.updateStatus(idKey, "cancelPublish"));
	}

	
}
