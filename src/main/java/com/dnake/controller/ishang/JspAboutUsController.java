package com.dnake.controller.ishang;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUS;
import com.dnake.domain.system.SysAboutUSCulture;
import com.dnake.domain.system.SysAboutUSEventVO;
import com.dnake.domain.system.SysAboutUSHonorVO;
import com.dnake.service.system.SysAboutUSCultureService;
import com.dnake.service.system.SysAboutUSEventService;
import com.dnake.service.system.SysAboutUSHonorService;
import com.dnake.service.system.SysAboutUSService;

@Controller
@RequestMapping("/about-us/*")
public class JspAboutUsController {
	private static final String PATH = "ishang/about-us/";

	@RequestMapping("home")
	public String a(Model model) {// 关于我们
		SysAboutUS sysAboutUS  =  bizAboutUSService.queryFirst();
		model.addAttribute("sysAboutUS", sysAboutUS);
		return PATH + "home";
	}

	@RequestMapping("event")
	public String b(Model model) {// 企业事件
		List<SysAboutUSEventVO>  sysAboutUSEventVOs = sysAboutUSEventService.list(new SearchParam());
		model.addAttribute("sysAboutUSEventVOs", sysAboutUSEventVOs);
		return PATH + "event";
	}

	@RequestMapping("honor")
	public String c(Model model) {// 企业荣誉
		List<SysAboutUSHonorVO> sysAboutUSHonorVOs = bizAboutUSHonorService.list(new SearchParam());
		model.addAttribute("sysAboutUSHonorVOs", sysAboutUSHonorVOs);
		return PATH + "honor";
	}

	@RequestMapping("culture")
	public String d(Model model) {// 企业文化
		SysAboutUSCulture sysAboutUSCulture = bizAboutUSCultureService.queryFirst();
		model.addAttribute("sysAboutUSCulture", sysAboutUSCulture);
		return PATH + "culture";
	}
	@Resource
	private SysAboutUSHonorService bizAboutUSHonorService;
	@Resource
	private SysAboutUSEventService sysAboutUSEventService;
	@Resource
	private SysAboutUSCultureService bizAboutUSCultureService;
	@Resource
	private SysAboutUSService bizAboutUSService;
}
