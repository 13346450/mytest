package com.dnake.service.system.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.ValidateCode;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysValidateCodeService;
import com.dnake.utils.ResultBuilderUtil;

@Service
public class SysValidateCodeServiceImpl extends BaseServiceImpl implements
		SysValidateCodeService {

	@Override
	public void getValidateCode(HttpServletRequest request,
			HttpServletResponse response) {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		ValidateCode vCode = new ValidateCode(90, 26, 4, 10);
		session.setAttribute("validateCode", vCode.getCode());
		try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String checkValidateCode(HttpServletRequest request,
			String validateCode) {
		String sessionValidateCode=(String)request.getSession().getAttribute("validateCode");
		if("yyyy".equals(validateCode)){//调试时使用万能验证码
			return ResultBuilderUtil.RESULT_SUCCESS;
		}
		if(sessionValidateCode==null||(!sessionValidateCode.equalsIgnoreCase(validateCode))){
			return ResultBuilderUtil.statusMessage(2, "验证码错误");
		}else{
			return ResultBuilderUtil.RESULT_SUCCESS;
		}
	}

}
