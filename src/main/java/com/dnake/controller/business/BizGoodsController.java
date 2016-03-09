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
import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizGoodsService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;

/**
 * 商品管理控制层
*  BizGoodsController <br/> 
*  2014年9月11日 上午10:00:28 <br/> 
* @author chen qige 
* @version
 */
@Controller
@RequestMapping("/business/BizGoods/*")
@Login
public class BizGoodsController extends BaseController  {
	
	@Resource
	private BizGoodsService bizGoodsService;
	@Resource
	private CommonService commonService;
	
	/**
	 * 按条件分页查询
	 * @title      listPage
	 * @author  ts    
	 * @date      2014年3月1日 
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizGoodsVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
		return bizGoodsService.listPage(pageIndex ,rows, searchParam);
	}
	
	/**
	 * 插入一条记录
	 * @title      insert
	 * @author  ts    
	 * @date      2014年3月1日 
	 * @param response
	 * @param bizGoods
	 */
	@RequestMapping( value = "insert")
	public void insert(HttpServletResponse response, BizGoods bizGoods){
		outputString(response, bizGoodsService.insert(bizGoods)); 
	}
	
	/**
	 * 更新一条记录
	 * @title      update
	 * @author  ts    
	 * @date      2014年3月1日
	 * @param response
	 * @param bizGoods
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizGoods bizGoods){
		outputString(response, bizGoodsService.update(bizGoods)); 
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
		outputString(response, bizGoodsService.delete(idKey)); 
	}
	/**
	 * 删除多个商品
	* @title      deleteMulti 
	* @author  chen qige     
	* @date      2014年9月26日 
	* @param response
	* @param searchParam
	 */
	@RequestMapping(value="deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
		outputString(response, bizGoodsService.deleteMulti(searchParam.getDeleteIds())); 
	}
	
	/**
	 * 下架多个商品
	* @title      deleteMulti 
	* @author  chen qige     
	* @date      2014年9月26日 
	* @param response
	* @param searchParam
	 */
	@RequestMapping(value="pullOffMulti")
	public void pullOffMulti(HttpServletResponse response, SearchParam searchParam){
		outputString(response, bizGoodsService.pullOffMulti(searchParam.getDeleteIds(),searchParam.getStatus(),searchParam.getAutoPutOnTime())); 
	}
	
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile, String link){
		outputString(response,commonService.uploadFileAndCompress(mfile, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR+File.separator+"goods_images" ,
				 "goods_images"));
	}
	
	@RequestMapping(value="deleteImages")
	public void deleteImages(HttpServletResponse response, BizGoods bizGoods){
		bizGoodsService.deleteImages(bizGoods); 
		outputString(response, "{\"msg\":\"删除成功\"}");
	}
}
