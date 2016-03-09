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
import com.dnake.domain.business.BizOrder;
import com.dnake.domain.business.BizOrderVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizOrderService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.StringUtils;

/**
 * 设备管理控制层
 *  BstDeviceController <br/>
 *  2014年2月19日 下午6:43:36 <br/>
 *
 * @author ts
 * @version
 */
@Controller
@RequestMapping("/business/BizOrder/*")
@Login
public class BizOrderController extends BaseController  {
	
	@Resource
	private BizOrderService bizOrderService;
	@Resource
	private CommonService commonService;
	
	/**
	 * 按条件分页查询
	* @title      listPage 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param pageIndex
	* @param rows
	* @param searchParam
	* @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizOrderVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
		return bizOrderService.listPage(pageIndex ,rows, searchParam);
	}
	
	/**
	 * 插入一条记录
	 * @title      insert
	 * @author  ts    
	 * @date      2014年3月1日 
	 * @param response
	 * @param bstDevice
	 */
	@RequestMapping( value = "insert")
	public void insert(HttpServletResponse response, BizOrder bizOrder){
		outputString(response, bizOrderService.insert(bizOrder)); 
	}
	
	/**
	 * 更新一条记录
	 * @title      update
	 * @author  ts    
	 * @date      2014年3月1日
	 * @param response
	 * @param bstDevice
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizOrder bizOrder){
		outputString(response, bizOrderService.update(bizOrder)); 
	}
	
	/**
	 * 删除一条记录
	 * @title      delete
	 * @author  ts    
	 * @date      2014年3月1日 
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value="delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizOrderService.delete(response, idKey)); 
	}
	
	/**
	 * 上传文件
	 * @title      uploadFile
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param session
	 * @param response
	 * @param mfile
	 * @param link
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile, String link){
		outputString(response,commonService.uploadFile(mfile, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR ,
				StringUtils.convertCharacter(link,Constants.CHARSET), "adv"));
	}
	
	/**
	 * 删除图片
	 * @title      deleteImage
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param session
	 * @param response
	 * @param imageAddr
	 */
	@RequestMapping(value="deleteImage")
	public void deleteImage(HttpSession session, HttpServletResponse response, String imageAddr){
		outputString(response,commonService.deleteFile(response, 
				imageAddr.substring(imageAddr.indexOf("/")+1) , 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR) ); 
	}
	
	/**
	 * 审核
	 * @title      auditAdvert
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value="auditAdvert/{idKey}")
	public void auditAdvert(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizOrderService.updateStatus(idKey, "audit")); 
	}

	/**
	 * 取消审核
	 * @title      cancelAuditAdvert
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value="cancelAuditAdvert/{idKey}")
	public void cancelAuditAdvert(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizOrderService.updateStatus(idKey, "cancelAudit"));
	}

	/**
	 * 发布
	 * @title      publishAdvert
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value="publishAdvert/{idKey}")
	public void publishAdvert(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizOrderService.updateStatus(idKey, "publish"));
	}

	/**
	 * 取消发布
	 * @title      auditAdvert
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value="cancelPublishAdvert/{idKey}")
	public void cancelPublishAdvert(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizOrderService.updateStatus(idKey, "cancelPublish"));
	}
	
	@RequestMapping(value="deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
		outputString(response, bizOrderService.deleteMulti(searchParam.getDeleteIds())); 
	}
}
