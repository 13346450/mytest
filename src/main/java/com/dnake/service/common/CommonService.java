package com.dnake.service.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.common.MobileParms;
import com.dnake.domain.system.SysUser;

/**
 * 公共服务接口 CommonService <br/>
 * 2014年3月13日 上午10:02:51 <br/>
 * 
 * @author ts
 * @version
 */
public interface CommonService {

	/**
	 * 登录验证
	 * 
	 * @Title: checkLogin
	 * @author ts 2013-12-4
	 * @Description:
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @return String
	 * @throws
	 */
	String checkLogin(HttpServletRequest request, Model model, SysUser user,
			String validateCode);

	/**
	 * 手机用户登录验证
	 * 
	 * @title checkLogin
	 * @author ts
	 * @date 2014年9月5日
	 * @param user
	 * @param passwd
	 * @return 1:成功 2密码错误 3用户不存在
	 */
	String checkMobileLogin(HttpServletRequest request, String userName,
			String userPasswd, Long roleId, String userCd);

	/**
	 * 退出登录
	 * 
	 * @Title: quitLogin
	 * @author ts 2013-12-4
	 * @Description:
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @return String
	 * @throws
	 */
	void quitLogin(HttpServletRequest request);

	/**
	 * 上传单个文件
	 * 
	 * @title uploadFile
	 * @author ts
	 * @date 2014年3月13日
	 * @param file
	 * @return
	 */
	String uploadFile(MultipartFile mfile, String path, String link,
			String prefix);

	/**
	 * 上传文件，可选是否要重命名，
	 * 
	 * @param mfile
	 *            文件
	 * @param path
	 *            目的地址
	 * @param rename
	 *            是否重命名
	 * @param prefix
	 *            重命名前缀，当不重命名时，参数无效
	 * @return 图片完整路径
	 * @author cqg 2015年6月16日
	 */
	String uploadFile(MultipartFile mfile, String path, boolean rename,
			String prefix);

	/**
	 * 手机端上传图片返回地址
	 * 
	 * @param mfile
	 * @param path
	 * @param prefix
	 * @return
	 */
	String mobileUploadFile(MultipartFile mfile, String path, String prefix);

	/**
	 * 上传图片，再进行压缩成多张规格
	 * 
	 * @title uploadFileAndCompress
	 * @author chen qige
	 * @date 2014年10月9日
	 * @return
	 */
	String uploadFileAndCompress(MultipartFile mfile, String path, String prefix);

	/**
	 * 上传多个文件
	 * 
	 * @title uploadFile
	 * @author ts
	 * @date 2014年3月13日
	 * @param mfile
	 * @param path
	 * @return
	 */
	String uploadFiles(MultipartFile[] mfiles, String path, String prefix);

	/**
	 * 下载文件
	 * 
	 * @title downloadFiles
	 * @author ts
	 * @date 2014年3月13日
	 * @param filename
	 * @param path
	 * @return
	 */
	void downloadFiles(HttpServletRequest request,
			HttpServletResponse response, String filename, String path);

	/**
	 * 删除文件
	 * 
	 * @title deleteFile
	 * @author ts
	 * @date 2014年3月27日
	 * @param response
	 * @param filename
	 * @param path
	 * @return
	 */
	String deleteFile(HttpServletResponse response, String filename, String path);

	/**
	 * 删除图片
	 * 
	 * @title deleteFile
	 * @author ts
	 * @date 2014年3月27日
	 * @param path
	 * @return
	 */
	String deleteImage(String path);

	/**
	 * 手机端验证随机码是否正确
	 * 
	 * @param parms
	 * @return
	 */
	String mobileVerifyCode(MobileParms parms);

	/**
	 * 手机端通过token进行登录
	 * 
	 * @param parms
	 * @return
	 * @author cqg 2015年9月25日
	 */
	String checkMobileLoginBytoken(MobileParms parms);
	
	/**
	 * 网页端请求获取验证码
	 *  @author zgj
	 *	日期：2015年10月14日下午7:32:49
	 *  描述：@param phone  电话号码
	 *  描述：@param zone   区号
	 *  描述：@return
	 */
	String webRequestSmsCode(MobileParms parms);
	/**
	 * 网页端请求校验验证码是否正确
	 *  @author zgj
	 *	日期：2015年10月14日下午7:39:58
	 *  描述：@param phone
	 *  描述：@param zone
	 *  描述：@param code
	 *  描述：@return
	 */
	String webRequestSmsCodeVerify(MobileParms parms);

}
