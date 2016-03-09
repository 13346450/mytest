package com.dnake.service.common.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.GlobalLoader;
import com.dnake.common.error.Validate;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Parameter;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.domain.system.SysLogoSettings;
import com.dnake.domain.system.SysRole;
import com.dnake.domain.system.SysUser;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.service.business.BizMessageService;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysCommunityService;
import com.dnake.service.system.SysDeptService;
import com.dnake.service.system.SysLogoSettingsService;
import com.dnake.service.system.SysRoleService;
import com.dnake.service.system.SysUserService;
import com.dnake.utils.Base64;
import com.dnake.utils.Constants;
import com.dnake.utils.CryptoUtils;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.GenerateRandomStringUtil;
import com.dnake.utils.ImageUtil;
import com.dnake.utils.MobClient;
import com.dnake.utils.MobSmsVerifyKit;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 公共的服务类
 * 
 * @ClassName: CommonServiceImpl
 * @Description:
 * @author ts
 * @date 2013-10-30 上午9:37:23
 *
 */
@Service
public class CommonServiceImpl implements CommonService {
	protected final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	protected HttpSession session;
	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysUserMapper sysUserMapper;

	@Resource
	private SysDeptService sysDeptService;

	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysCommunityService sysCommunityService;

	@Resource
	private BizMessageService bizMessageService;

	@Resource
	private SysLogoSettingsService sysLogoSettingsService;

	private Parameter parameter = GlobalLoader.getInstance().getParameter();

	@Override
	public String checkLogin(HttpServletRequest request, Model model,
			SysUser user, String validateCode) {
		if (null == user.getLoginNm()) {
			model.addAttribute("msg", "不允许直接访问主界面的url，请在此登录。");
			return "forward:/loginPage";
		}
		String sessionValidateCode = (String) request.getSession()
				.getAttribute("validateCode");
		// 更新验证码
		request.getSession().setAttribute("validateCode",
				GenerateRandomStringUtil.getValidateCode(4));
		if (request.getSession().getAttribute("sessionBean") == null
				&& (sessionValidateCode == null || (!sessionValidateCode
						.equalsIgnoreCase(validateCode)))) {
			model.addAttribute("msg", "验证码错误！");
			return "forward:/loginPage";
		}
		SysUser userInfo = sysUserService.checkLogin(user.getLoginNm());
		if (null != userInfo) {
			// 拒绝住户及商户登录web后端
			if ((userInfo.getRoleId() == Constants.SALES_ROLE_ID || userInfo
					.getRoleId() == Constants.USER_ROLE_ID)) {
				model.addAttribute("msg", "登录失败，住户或商户账号请用手机app登录！");
				return "forward:/loginPage";
			}
			if (StringUtils.encrypt(user.getUserPwd()).equals(
					userInfo.getUserPwd())) {
				userInfo.setPopedomCommunityIds(sysCommunityService
						.queryPopedomCommunityString(userInfo.getIdKey()));
				request.getSession().setAttribute("sessionBean",
						this.getSessionBean(userInfo));
				/**
				 * 首页logo
				 */
				List<SysLogoSettings> list = sysLogoSettingsService
						.queryByType("pcIndexLogo");
				for (SysLogoSettings sysLogoSettings : list) {
					model.addAttribute("pcIndexLogo",
							sysLogoSettings.getImageUrl());
				}
				return "mainframe/mainframe";
			} else {
				model.addAttribute("msg", "登录失败，密码错误！");
				return "forward:/loginPage";
			}
		}
		SysUser sysUserInfo = sysUserService.queryByLoginName2Community(user
				.getLoginNm());
		if (sysUserInfo != null
				&& sysUserInfo.getRoleId() == Constants.PROPERTY_ROLE_ID) {
			if (StringUtils.encrypt(user.getUserPwd()).equals(
					sysUserInfo.getUserPwd())) {
				sysUserInfo.setPopedomCommunityIds(sysCommunityService
						.queryPopedomCommunityString(sysUserInfo.getIdKey()));
				request.getSession().setAttribute("sessionBean",
						this.getSessionBean(sysUserInfo));
				/**
				 * 首页logo
				 */
				List<SysLogoSettings> list = sysLogoSettingsService
						.queryByType("pcIndexLogo");
				for (SysLogoSettings sysLogoSettings : list) {
					model.addAttribute("pcIndexLogo",
							sysLogoSettings.getImageUrl());
				}
				return "mainframe/mainframe";
			} else {
				model.addAttribute("msg", "登录失败，密码错误！");
				return "forward:/loginPage";
			}
		}
		model.addAttribute("msg", "登录失败，用户不存在！");
		return "forward:/loginPage";
	}

	@Override
	public String checkMobileLogin(HttpServletRequest request,
			String loginName, String userPasswd, Long roleId, String userCd) {
		SysUser userInfo = sysUserService.mobileCheckLogin(loginName);
		if (null != userInfo) {
			if (userInfo.getRoleId() != roleId)
				return ResultBuilderUtil.statusMessage(5, "登录版本不对");
			if (StringUtils.encrypt(userPasswd).equals(userInfo.getUserPwd())) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("sessionBean",
						this.getSessionBean(userInfo));
				userInfo.setUserCd(userCd);// 登录时，更新用户识别码，用于推送
				sysUserService.mobileUpdateUserCd(userInfo);
				SysCommunityVO sysCommunityVO = sysCommunityService
						.queryCommunityVOById(userInfo.getDeptId());
				StringBuilder sb = new StringBuilder();
				sb.append("{\"status\":\"1\",\"message\":\"操作成功\",\"data\":{")
						.append("\"province\":\"")
						.append(sysCommunityVO.getProvince())
						.append("\",\"city\":\"")
						.append(sysCommunityVO.getCity())
						.append("\",\"district\":\"")
						.append(sysCommunityVO.getDistrict())
						.append("\",\"community\":\"")
						.append(sysCommunityVO.getCommunityName())
						.append("\",\"communityId\":\"")
						.append(userInfo.getDeptId())
						.append("\",\"buliding\":\"")
						.append(StringUtils.null2Blank(userInfo.getBuilding()))
						.append("\",\"unit\":\"")
						.append(StringUtils.null2Blank(userInfo.getUnit()))
						.append("\",\"floor\":\"")
						.append(StringUtils.null2Blank(userInfo.getFloor()))
						.append("\",\"room\":\"")
						.append(StringUtils.null2Blank(userInfo.getRoom()))
						.append("\",\"addr\":\"")
						.append(StringUtils.null2Blank(userInfo.getAddr()))
						.append("\",\"name\":\"")
						.append(StringUtils.null2Blank(userInfo.getCdNm()))
						.append("\",\"bindTel\":\"")
						.append(userInfo.getBindTel())
						.append("\",\"telephone\":\"")
						.append(StringUtils.null2Blank(userInfo.getTelephone()))
						.append("\",\"JSESSIONID\": \"")
						.append(StringUtils.null2Blank(httpSession.getId()))
						.append("\",\"token\": \"").append(getToken(userInfo))
						.append("\"").append("}");
				sb.append("}");
				return sb.toString();
			} else {
				return ResultBuilderUtil.statusMessage(2, "密码错误");
			}
		}
		return ResultBuilderUtil.statusMessage(3, "用户名不存在");
	}

	/**
	 * 生成加密token
	 * 
	 * @param user
	 * @return
	 * @author cqg 2015年9月25日
	 */
	private String getToken(SysUser user) {
		return CryptoUtils
				.encode("xiaolonghaoshuai",
						(user.getLoginNm() + ","
								+ user.getUserPwd().substring(0, 3) + "," + DateTimeUtil
								.getDateToStrFullFormat(new Date())));
	}

	@Override
	public void quitLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("sessionBean");
		session.invalidate();
		log.info("已退出登录。");
	}

	/**
	 * 给sessionBean赋值
	 * 
	 * @Title: getSessionBean
	 * @author ts 2013-12-4
	 * @Description:
	 * @param userInfo
	 * @return
	 * @return SessionBean
	 * @throws
	 */
	private SessionBean getSessionBean(SysUser userInfo) {
		SessionBean sessionBean = new SessionBean();
		sessionBean.setUserId(userInfo.getIdKey());
		sessionBean.setUserCd(userInfo.getUserCd());
		sessionBean.setDeptName(userInfo.getDeptName());
		sessionBean.setLoginNm(userInfo.getLoginNm());
		sessionBean.setUserName(userInfo.getCdNm());
		sessionBean.setIsModify(userInfo.getIsModify());
		sessionBean.setAddr(userInfo.getAddr());
		sessionBean.setTelephone(userInfo.getTelephone());
		sessionBean.setPopedomCommunityIds(userInfo.getPopedomCommunityIds());

		if (userInfo.getRoleId() == Constants.SYS_MENU_TREE_ROOT_ID) { // 如果是系统管理员则可以看到所有的树结构
			sessionBean.setDeptId(Constants.SYS_DEPT_TREE_ROOT_ID);// userInfo.getDeptId()
			// SysDept dept =
			// sysDeptService.edit(Constants.SYS_DEPT_TREE_ROOT_ID);//
			// userInfo.getDeptId()
			// sessionBean.setDeptName(dept.getCdNm());
		} else if (userInfo.getRoleId() == Constants.PROPERTY_ROLE_ID) { // 物业管理人员
			sessionBean.setDeptId(userInfo.getDeptId());
			// SysDept dept = sysDeptService.edit(userInfo.getDeptId());//
			// userInfo.getDeptId()
			// sessionBean.setDeptName(dept.getCdNm());
		} else if (userInfo.getRoleId() == Constants.SALES_ROLE_ID
				|| userInfo.getRoleId() == Constants.USER_ROLE_ID) {
			sessionBean.setDeptId(userInfo.getDeptId());
		}
		sessionBean.setRoleId(userInfo.getRoleId());
		SysRole role = sysRoleService.queryRoleByIdKey(userInfo.getRoleId());
		sessionBean.setRoleName(role.getRoleName());
		sessionBean.setRoleType(role.getRoleType());
		return sessionBean;
	}

	@Override
	public String uploadFile(MultipartFile mfile, String path, String link,
			String prefix) {
		if (null == mfile) {
			return "{\"successMsg\" : \"请选择要上传的文件！\"}";
		}
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		// File dstFile = new File(dir +"/"+ mfile.getOriginalFilename());
		String newFilename = prefix
				+ "_"
				+ System.currentTimeMillis()
				+ mfile.getOriginalFilename().substring(
						mfile.getOriginalFilename().lastIndexOf("."));
		File dstFile = new File(dir + File.separator + newFilename);
		if (dstFile.exists()) {
			dstFile.delete();
		}
		/*
		 * 测试ok try { FileOutputStream fos = new FileOutputStream(dstFile);
		 * FileCopyUtils.copy(mfile.getInputStream(), fos); return
		 * "{\"successMsg\":\"上传成功！\"}"; } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 */
		try {
			mfile.transferTo(dstFile);
			return "{\"successMsg\" : \"上传成功。\" , \"addr\":\""
					+ this.getSubFilePath(path) + "/" + newFilename + "\" ,"
					+ "\"link\" : \"" + link + "\"}";
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"successMsg\" : \"上传失败，请重试！\"}";
	}

	@Override
	public String uploadFile(MultipartFile mfile, String path, boolean rename,
			String prefix) {
		if (null == mfile) {
			return "error_no_file";
		}
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		String newFilename = File.separator + mfile.getOriginalFilename();
		if (rename) {// 如果要重命名
			newFilename = prefix
					+ "_"
					+ System.currentTimeMillis()
					+ mfile.getOriginalFilename().substring(
							mfile.getOriginalFilename().lastIndexOf("."));
		}
		File dstFile = new File(dir + File.separator + newFilename);
		if (dstFile.exists()) {
			dstFile.delete();
		}
		try {
			mfile.transferTo(dstFile);
			return this.getSubFilePath(path) + "/" + newFilename;// 返回文件名路径
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error_trans_fail";
	}

	@Override
	public String mobileUploadFile(MultipartFile mfile, String path,
			String prefix) {
		String s = new String();
		if (null == mfile) {
			return s;
		}
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		String newFilename = prefix
				+ "_"
				+ System.currentTimeMillis()
				+ mfile.getOriginalFilename().substring(
						mfile.getOriginalFilename().lastIndexOf("."));
		File dstFile = new File(dir + File.separator + newFilename);
		if (dstFile.exists()) {
			dstFile.delete();
		}
		try {
			mfile.transferTo(dstFile);
			s = this.getSubFilePath(path) + "/" + newFilename;
			return s;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public String uploadFileAndCompress(MultipartFile mfile, String path,
			String prefix) {
		if (null == mfile) {
			return "{\"successMsg\" : \"请选择要上传的文件！\"}";
		}
		String pid = path + File.separator + "phone_images";
		String sid = path + File.separator + "show_images";
		File phoneImagesDir = new File(pid);
		File ShowImagesDir = new File(sid);
		if (!phoneImagesDir.exists()) {
			mkDir(phoneImagesDir);
		}
		if (!ShowImagesDir.exists()) {
			mkDir(ShowImagesDir);
		}
		File dstFile = new File(path + File.separator
				+ mfile.getOriginalFilename());
		if (dstFile.exists()) {
			dstFile.delete();
		}

		try {
			mfile.transferTo(dstFile);
			String imageSrcPath = dstFile.getAbsolutePath();
			String simagesName = prefix
					+ "_"
					+ System.currentTimeMillis()
					+ mfile.getOriginalFilename().substring(
							mfile.getOriginalFilename().lastIndexOf("."));
			ImageUtil.compressWithFixWidthAndHeight(imageSrcPath,
					phoneImagesDir + File.separator + simagesName,
					parameter.getSimagesWidth(), parameter.getSimagesHeight());
			String limagesName = prefix
					+ "_"
					+ System.currentTimeMillis()
					+ mfile.getOriginalFilename().substring(
							mfile.getOriginalFilename().lastIndexOf("."));
			ImageUtil.compressWithFixWidthAndHeight(imageSrcPath,
					phoneImagesDir + File.separator + limagesName,
					parameter.getLimagesWidth(), parameter.getLimagesHeight());
			String showSimagesName = prefix
					+ "_"
					+ System.currentTimeMillis()
					+ mfile.getOriginalFilename().substring(
							mfile.getOriginalFilename().lastIndexOf("."));
			ImageUtil.compressWithFixWidthAndHeight(imageSrcPath, ShowImagesDir
					+ File.separator + showSimagesName,
					parameter.getShowSimagesWidth(),
					parameter.getShowSimagesHeight());
			String showLimagesName = prefix
					+ "_"
					+ System.currentTimeMillis()
					+ mfile.getOriginalFilename().substring(
							mfile.getOriginalFilename().lastIndexOf("."));

			ImageUtil.compressWithFixWidthAndHeight(imageSrcPath, ShowImagesDir
					+ File.separator + showLimagesName,
					parameter.getShowLimagesWidth(),
					parameter.getShowLimagesHeight());
			dstFile.delete();
			return new StringBuilder().append("{\"successMsg\" : \"上传成功。\" ,")
					.append(" \"simagesAddr\":\"")
					.append(this.getSubFilePath(pid)).append("/")
					.append(simagesName).append("\",")
					.append(" \"limagesAddr\":\"")
					.append(this.getSubFilePath(pid)).append("/")
					.append(limagesName).append("\",")
					.append(" \"showSimagesAddr\":\"")
					.append(this.getSubFilePath(sid)).append("/")
					.append(showSimagesName).append("\",")
					.append(" \"showLimagesAddr\":\"")
					.append(this.getSubFilePath(sid)).append("/")
					.append(showLimagesName).append("\"").append("}")
					.toString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"successMsg\" : \"上传失败，请重试！\"}";

	}

	@Override
	public String uploadFiles(MultipartFile[] mfiles, String path, String prefix) {
		for (MultipartFile mfile : mfiles) {
			if (!mfile.getOriginalFilename().trim().equals("")) {
				this.uploadFile(mfile, path, "", prefix);
			}
		}
		return "{\"successMsg\":\"上传完毕。\"}";
	}

	@Override
	public void downloadFiles(HttpServletRequest request,
			HttpServletResponse response, String filename, String path) {
		try {
			response.setContentType("text/html;charset=" + Constants.CHARSET);
			request.setCharacterEncoding(Constants.CHARSET);
			String downLoadPath = path + File.separator + filename;
			File downloadFile = new File(downLoadPath);
			if (!downloadFile.exists()) {
				PrintWriter out = response.getWriter();
				out.println("{\"successMsg\":\"下载的文件不存在！\"}");
				out.flush();
				out.close();
				return;
			}
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(filename, Constants.CHARSET));
			response.setHeader("Content-Length",
					String.valueOf(downloadFile.length()));
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(downLoadPath));
			BufferedOutputStream bos = new BufferedOutputStream(
					response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String deleteFile(HttpServletResponse response, String filename,
			String path) {
		String pathFile = path + File.separator + filename;
		File file = new File(pathFile);
		if (file.exists()) {
			file.delete();
			return "{\"successMsg\":\"已删除\"}";
		}
		return "{\"successMsg\":\"文件不存在！\"}";
	}

	/**
	 * 截取图片文件夹返回路径
	 * 
	 * @title getImagePath
	 * @author chen qige
	 * @date 2014年10月11日
	 * @param path
	 *
	 */
	private String getSubFilePath(String path) {
		if (path.indexOf(Constants.UPLOAD_DIR) != -1) {
			return path.substring(path.indexOf(Constants.UPLOAD_DIR)).replace(
					"\\", "/");
		} else if (path.indexOf(Constants.APK_DIR) != -1) {
			return path.substring(path.indexOf(Constants.APK_DIR)).replace(
					"\\", "/");
		} else if (path.indexOf(Constants.MOBILE_COMMENT_UPLOAD_FILE) != -1) {
			return path.substring(
					path.indexOf(Constants.MOBILE_COMMENT_UPLOAD_FILE))
					.replace("\\", "/");
		} else if (path.indexOf(Constants.MOBILE_GOODS_UPLOAD_FILE) != -1) {
			return path.substring(
					path.indexOf(Constants.MOBILE_GOODS_UPLOAD_FILE)).replace(
					"\\", "/");
		} else {
			return path.substring(path.indexOf(Constants.CHAIN_DIR)).replace(
					"\\", "/");
		}
	}

	/**
	 * 递归创建文件夹
	 * 
	 * @title mkDir
	 * @author chen qige
	 * @date 2014年10月13日
	 * @param file
	 */
	public void mkDir(File file) {
		if (file.getParentFile().exists()) {
			file.mkdir();
		} else {
			mkDir(file.getParentFile());
			file.mkdir();
		}
	}

	@Override
	public String mobileVerifyCode(MobileParms parms) {
		String status = "";
		Map<String, Object> map = new HashMap<String, Object>();
		JsonResult jr = new JsonResult();
		boolean type = parms.getType() == 1; // 判断是否是注册，1:用户注册 2：找回密码 3绑定团购手机
		List<SysUser> list = sysUserMapper.qByLoginNm(parms.getPhone());
		if (list.size() != 0 && parms.getType() == 1) {
			map.put("result", 7);
			return ResultBuilderUtil.statusMessage(7, "号码已经被使用");
		}
		if (list.size() == 0 && parms.getType() == 2) {
			return ResultBuilderUtil.statusMessage(8, "找回密码，查无此账户");
		}
		try {
			MobSmsVerifyKit mob = new MobSmsVerifyKit();
			String str = mob.smsVerify(parms.getPhone(), parms.getZone(),
					parms.getCode());
			if (str.indexOf("200") != -1) {
				status = ResultBuilderUtil.statusMessage(1, "验证码准确无误");
			} else {
				status = ResultBuilderUtil.statusMessage(2, "验证码错误");
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultBuilderUtil.statusMessage(6, "程序异常");
	}

	@Override
	public String deleteImage(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			return "{\"successMsg\":\"已删除\"}";
		}
		return "{\"successMsg\":\"文件不存在！\"}";
	}

	@Override
	public String checkMobileLoginBytoken(MobileParms parms) {
		// 数据校验
		if (parms.getToken() == null) {
			return ResultBuilderUtil.jsonTojsonp(parms.getCallback(),
					ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER);
		}

		SysUser userInfo = getUserByToken(parms.getToken());// 获取token用户
		if (userInfo != null) {// token 有效，将用户信息写入sessionBean中
			session.setAttribute("sessionBean", this.getSessionBean(userInfo));
			return ResultBuilderUtil.RESULT_SUCCESS;
		}
		return ResultBuilderUtil.statusMessage(2, "toekn无效!");
	}

	/**
	 * 通过令牌获取用户信息(token的解密过程),token在60分钟后(从登录开始算，不更新)失效
	 * 
	 * @param token
	 * @return
	 * @author cqg 2015年9月25日
	 */
	private SysUser getUserByToken(String token) {
		String tokenAndDate = CryptoUtils.decode("xiaolonghaoshuai", token);
		String[] tokenArr = tokenAndDate.split(",");
		if (tokenArr.length == 3
				&& DateTimeUtil.formatStrToDate(tokenArr[2]).compareTo(
						DateTimeUtil.nowAddTime(new Date(), 0, 0, 60, 0)) < 0) {// 60分钟内失效
			SysUser userInfo = sysUserService.mobileCheckLogin(tokenArr[0]);
			if (userInfo != null
					&& userInfo.getUserPwd().substring(0, 3)
							.equals(tokenArr[1])) {
				return userInfo;
			}
		}
		return null;
	}

	@Override
	public String webRequestSmsCode(MobileParms parms)
	{
		String phone = parms.getPhone();
		String zone = parms.getZone();
		
		MobSmsVerifyKit mob = new MobSmsVerifyKit();
		Validate.notNull(phone, "phone");
		Validate.notNull(zone, "zone");
		
		Validate.regex(phone, "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", "phone");
		
		String code = mob.webRequestSmsPost(phone);
		return ResultBuilderUtil.resultIncludeValue("returnState", code);
	}

	@Override
	public String webRequestSmsCodeVerify(MobileParms parms)
	{
		String phone = parms.getPhone();
		String code = parms.getCode();
		String zone = parms.getZone();
		
		MobSmsVerifyKit mob = new MobSmsVerifyKit();
		Validate.notNull(phone, "phone");
		Validate.notNull(code, "code");
		Validate.notNull(zone, "zone");
		
		Validate.regex(phone, "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", "phone");
		Validate.regex(code, "\\d{4}", "code");
		
		String codes = mob.webRequestSmsVerify(phone, zone, code);
		return ResultBuilderUtil.resultIncludeValue("returnState", codes);
	}
	
}
