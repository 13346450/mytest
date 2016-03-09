package com.dnake.controller.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnake.domain.system.SysLogoSettings;
import com.dnake.service.system.SysLogoSettingsService;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{
	@Resource
	private SysLogoSettingsService sysLogoSettingsService;
	@RequestMapping("")
	public String home(Model model,HttpServletRequest request) {
		List<SysLogoSettings> sysLogoSettings = sysLogoSettingsService.queryByType("residentAppGuide");
		model.addAttribute("sysLogoSettings", sysLogoSettings);
		return "common/login";
	}
	@RequestMapping("loginPage")
	public String login(Model model) {
		return "common/login";
	}
	@RequestMapping("appdown")
	public String appdown(Model model) {
		return "ishang/APPdown";
	}
}
