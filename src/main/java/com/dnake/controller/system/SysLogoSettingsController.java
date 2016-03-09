package com.dnake.controller.system;

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

import com.dnake.common.interceptor.ClearLogin;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysLogoSettings;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysLogoSettingsService;
import com.dnake.utils.Constants;

@Controller
@RequestMapping("/system/SysLogoSettings/*")
@Login
public class SysLogoSettingsController extends BaseController {

	@Resource
	private SysLogoSettingsService sysLogoSettingsService;

	@Resource
	private CommonService commonService;

	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<SysLogoSettings> listPage(@RequestParam("page") int pageIndex,
			int rows, SearchParam searchParam) {
		return sysLogoSettingsService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response,
			SysLogoSettings sysLogoSettings) {
		outputString(response, sysLogoSettingsService.update(sysLogoSettings));
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response,
			SysLogoSettings sysLogoSettings) {
		outputString(response, sysLogoSettingsService.insert(sysLogoSettings));
	}

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam) {
		outputString(response,
				sysLogoSettingsService.deleteMulti(searchParam.getDeleteIds()));
	}

	@RequestMapping(value = "queryAll")
	@ResponseBody
	@ClearLogin
	public List<SysLogoSettings> queryAll() {
		return sysLogoSettingsService.queryAll();
	}
	
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile, Long idKey){
		String s=commonService.uploadFile(mfile, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR+File.separator+"system_images",
				"", "sys_");
		if(s.indexOf("成功")!=-1){
			SysLogoSettings sls=new SysLogoSettings();
			sls.setIdKey(idKey);
			sls.setImageUrl(s.substring(s.indexOf("addr")+6, s.indexOf("link")-3).replace("\"", ""));
			sysLogoSettingsService.update(sls);
		}
		outputString(response,s);
	}
	
	@RequestMapping(value="delete/{idKey}")
	public void auditAdvert(HttpSession session,HttpServletResponse response, @PathVariable Long idKey){
		outputString(response, sysLogoSettingsService.delete(idKey,session.getServletContext().getRealPath("/"))); 
	}
	
}
