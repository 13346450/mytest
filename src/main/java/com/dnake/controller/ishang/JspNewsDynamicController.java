package com.dnake.controller.ishang;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/news/*")
public class JspNewsDynamicController {
	private static final String PATH = "ishang/news-dynamic/";

	@RequestMapping("home")
	public String aa(Model attr) {//新闻分页
		return PATH + "home";
	}

}
