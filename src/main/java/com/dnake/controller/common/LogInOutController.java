package com.dnake.controller.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.system.SysUser;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.StringUtils;

@Controller
public class LogInOutController extends BaseController{
	
	@Resource
	private CommonService commonService;

	/**
	 * 登录验证
	* @Title: login 
	* @author ts  2013-12-4
	* @Description: 
	* @param request
	* @param model
	* @param user
	* @return    
	* @return String    
	* @throws
	 */
	@RequestMapping(value = "loginManage")
	public String login(HttpServletRequest request,
						Model model,
						SysUser user,String validateCode){
		return commonService.checkLogin(request, model, user,validateCode);
	}
	
	/**
	 * 退出登录，清空session
	* @Title: quitLogin 
	* @author ts  2013-12-4
	* @Description: 
	* @param request
	* @return    
	* @return String    
	* @throws
	 */
	@RequestMapping(value = "quitLogin")
	public String quitLogin(HttpServletRequest request, Model model){
		commonService.quitLogin(request);
		model.addAttribute("msg", "您已安全退出。");
		return "forward:/";
	}
		
	/**
	 * 上传单个文件
	 * @title      uploadFile
	 * @author  ts    
	 * @date      2014年3月13日 
	 * @param session
	 * @param response
	 * @param mfile
	 * @param rcName
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile, String rcName){
        /*MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;     
        MultipartFile file = multipartRequest.getFile("upFile"); */
		outputString(response,commonService.uploadFile(mfile, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR ,"","test"));
	}
	
	/**
	 * 上传多个文件
	 * @title      uploadFiles
	 * @author  ts    
	 * @date      2014年3月13日 
	 * @param session
	 * @param response
	 * @param mfile
	 * @param rcName
	 */
	@RequestMapping(value = "uploadFiles")
	public void uploadFiles(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile[] mfiles, String rcName){
		outputString(response,commonService.uploadFiles(mfiles, 
				session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR , "test"));
	}
	
	/**
	 * 下载指定文件，用流输出 <br>
	 * 当设了tomcat的URIEncoding="UTF-8"后，则不需要转换convertCharacter
	 * @title      downloadFile
	 * @author  ts    
	 * @date      2014年3月17日 
	 * @param request
	 * @param response
	 * @param filename
	 */
	@RequestMapping(value = "downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, 
						@RequestParam String filename){
		if( null == filename || filename.equals("")){
			outputString(response, "{\"successMsg\":\"请输入要下载的文件名！\"}");
			return;
		}
		commonService.downloadFiles(request, response,	
				//filename,
				StringUtils.convertCharacter(filename, Constants.CHARSET),
				request.getSession().getServletContext().getRealPath("/")+Constants.UPLOAD_DIR );
	}
	
}
