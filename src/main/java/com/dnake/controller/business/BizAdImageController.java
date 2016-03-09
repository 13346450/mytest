package com.dnake.controller.business;

import java.io.File;
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
import com.dnake.domain.business.BizAdImage;
import com.dnake.domain.business.BizAdImageVO;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizAdImageService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;

@Controller
@RequestMapping("/business/BizAdImage/*")
@Login
public class BizAdImageController extends BaseController {

	@Resource
	private BizAdImageService bizAdImageService;

	@Resource
	private CommonService commonService;

	

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizAdImage bizAdImage) {
		outputString(response, bizAdImageService.update(bizAdImage));
	}

	/*@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizAdImage bizAdImage) {
		outputString(response, bizAdImageService.insert(bizAdImage));
	}*/

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam) {
		outputString(response,
				bizAdImageService.deleteMulti(searchParam.getDeleteIds()));
	}

	/**
	 * 根据类型查询广告图片
	 * 
	 * @param type
	 * @return
	 * @author cqg 2015年5月26日
	 */
	@RequestMapping(value = "queryByType/{type}/{communityId}")
	@ResponseBody
	public List<BizAdImageVO> queryByType(@PathVariable String type,@PathVariable String communityId) {
		return bizAdImageService.queryByType(type,communityId);
	}
	
	/**
	 * 
	 * @param response
	 * @param idKey
	 *@author cqg 
	 *2015年5月26日
	 */
	@RequestMapping(value="delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, bizAdImageService.delete(response, idKey)); 
	}
	
	@RequestMapping(value = "insert")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile,String categoryId, String dictKey,String communityId){
		String s=commonService.uploadFile(mfile, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR+File.separator+"ad_images",
				"", "ad_");
		if(s.indexOf("成功")!=-1){
			s=(s.substring(s.indexOf("addr")+6, s.indexOf("link")-3).replace("\"", ""));
		}
		outputString(response,bizAdImageService.insert(categoryId,dictKey, s, communityId));
	}
}
