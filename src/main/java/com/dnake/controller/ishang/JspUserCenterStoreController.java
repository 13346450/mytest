package com.dnake.controller.ishang;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dnake.common.interceptor.Login;

@Controller()
@RequestMapping("/user-center-store/")
public class JspUserCenterStoreController {
	private static final String PATH = "ishang/user-center-store/";

	@RequestMapping("/home")
	public String home(Model attr) {// 
		return PATH + "home";
	}
	@RequestMapping("/order-all")
	public String order(Model attr) {// 
		return PATH + "order-all";
	}
	@RequestMapping("/activity")
	public String activity(Model attr) {// 
		return PATH + "activity";
	}
	@RequestMapping("/red-add")
	public String redAdd(Model attr) {// 
		return PATH + "red-add";
	}
	@RequestMapping("/evaluation")
	public String evaluation(Model attr) {// 
		return PATH + "evaluation";
	}
	@RequestMapping("/goods-add")
	public String goodsAdd(Model attr) {// 
		return PATH + "goods-add";
	}
	@RequestMapping("/my-goods")
	public String goods(Model attr) {// 
		return PATH + "my-goods";
	}
	@RequestMapping("/down-goods")
	public String down(Model attr) {// 
		return PATH + "down-goods";
	}
	@RequestMapping("/mng-store")
	public String mngStore(Model attr) {// 
		return PATH + "mng-store";
	}
	@RequestMapping("/add-store")
	public String addStore(Model attr) {// 
		return PATH + "add-store";
	}
	@RequestMapping("/refund")
	public String refund(Model attr) {// 
		return PATH + "refund";
	}
	@RequestMapping("/sales-report")
	public String aa(Model attr) {// 
		return PATH + "sales-report";
	}
}
