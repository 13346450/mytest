package com.dnake.controller.ishang;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/user-center-property/")
public class JspUserCenterPropertyController {
	private static final String PATH = "ishang/user-center-property/";

	@RequestMapping("/home")
	public String aa(Model attr) {//物业中心 -首页
		return PATH + "home";
	}

	@RequestMapping("/householdsHaveToPay")
	public String a(Model attr) {//物业中心 -已缴费用户
		return PATH + "householdsHaveToPay";
	}
	@RequestMapping("/notify")
	public String b(Model attr) {//物业中心 -通知公告
		return PATH + "notify";
	}
	@RequestMapping("/notify-add")
	public String c(Model attr) {//物业中心 -新增公告
		return PATH + "notify-add";
	}
	@RequestMapping("/repair")
	public String d(Model attr) {//物业中心 -维修
		return PATH + "repair";
	}
	@RequestMapping("/suggest")
	public String suggest(Model attr) {//物业中心 -维修
		return PATH + "suggest";
	}
	@RequestMapping("/customer-service")
	public String e(Model attr) {//物业中心 -客服信息
		return PATH + "customer-service";
	}
	@RequestMapping("/update-pwd")
	public String pwd(Model attr) {//物业中心 -修改密码
		return PATH + "update-pwd";
	}
	@RequestMapping("/update-pwd-2")
	public String pwd2(Model attr) {//物业中心 -修改密码2
		return PATH + "update-pwd-2";
	}
}
