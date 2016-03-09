package com.dnake.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnake.controller.common.BaseController;
import com.dnake.service.system.SysValidateCodeService;

/**
 * 
 * 验证码控制器 
 */
@Controller
@RequestMapping("/system/ValidateCode/*")
public class SysValidateCodeController extends BaseController {
	@Resource
	private SysValidateCodeService sysValidateCodeService;
	/**
	 * 获得验证码
	 */
	@RequestMapping(value = "getValidateCode/{random}")
	public void validateCode(HttpServletRequest reqeust, HttpServletResponse response) {
		sysValidateCodeService.getValidateCode(reqeust, response);
	}
	/**
	 * 验证码检查
	 */
	@RequestMapping(value = "checkValidateCode/{validateCode}")
	public void checkValidateCode(@PathVariable String validateCode, HttpServletRequest reqeust, HttpServletResponse response) {
		outputString(response, sysValidateCodeService.checkValidateCode(reqeust, validateCode));
	}
}
