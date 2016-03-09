package com.dnake.controller.system;

import java.io.File;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysCommunityInfo;
import com.dnake.domain.system.SysCommunityInfoVO;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysCommunityInfoService;
import com.dnake.utils.Constants;

@Controller
@RequestMapping("/system/SysCommunityInfo/*")
public class SysCommunityInfoController extends BaseController
{

	@Resource
	private SysCommunityInfoService sysCommunityInfoService;

	@Resource
	private CommonService commonSevice;

	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<SysCommunityInfoVO> listPage(
			@RequestParam("page") int pageIndex, int rows,
			SearchParam searchParam)
	{
		return sysCommunityInfoService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response,
			SysCommunityInfo sysCommunityInfo)
	{
		outputString(response, sysCommunityInfoService.update(sysCommunityInfo));
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response,
			SysCommunityInfo sysCommunityInfo)
	{
		outputString(response, sysCommunityInfoService.insert(sysCommunityInfo));
	}

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam)
	{
		outputString(response,
				sysCommunityInfoService.deleteMulti(searchParam.getDeleteIds()));
	}
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile, String link){
		outputString(response,commonSevice.uploadFile(mfile, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR+File.separator+"logo_images" ,
				"null_link", "logo_images"));
	}
}
