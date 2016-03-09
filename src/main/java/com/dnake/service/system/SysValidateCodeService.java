package com.dnake.service.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SysValidateCodeService {
	/**
	 * 获取验证码图片
	 * @param request
	 * @param respone
	 *@author cqg 
	 *2015年6月8日
	 */
	void getValidateCode(HttpServletRequest request,HttpServletResponse respone);
	/**
	 * 检测验证码是否正确
	 * @param request
	 * @param validateCode
	 * @return
	 *@author cqg 
	 *2015年6月9日
	 */
	String checkValidateCode(HttpServletRequest request,String validateCode);
}
