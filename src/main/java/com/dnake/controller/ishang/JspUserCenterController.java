package com.dnake.controller.ishang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dnake.common.interceptor.Login;
import com.dnake.domain.business.BizAddrVO;
import com.dnake.domain.business.BizCouponVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysUser;
import com.dnake.mapper.business.BizAddrMapper;
import com.dnake.mapper.business.BizCouponMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.utils.SessionBean;

@Controller()
@RequestMapping("/user-center/")
@Login
public class JspUserCenterController {
	private static final String PATH = "ishang/user-center/";

	@RequestMapping("/home")
	public String aa(Model attr) {// 订单-全部
		return PATH + "home";
	}

	@RequestMapping("/order-all")
	public String a(Model attr) {// 订单-全部
		return PATH + "order-all";
	}

	// @RequestMapping("/order-un")
	// public String b(Model attr) {// 订单-待接单
	// return PATH+"order-un";
	// }
	//
	// @RequestMapping("/order-already")
	// public String c(Model attr) {// 订单-已接单
	// return PATH+"order-already";
	// }
	//
	// @RequestMapping("/order-seeding")
	// public String d(Model attr) {// 订单-配送中
	// return PATH+"order-seeding";
	// }

	// @RequestMapping("/goods-history")
	// public String e(Model attr) {// 已买商品
	// return PATH+"goods-history";
	// }
	//
	// @RequestMapping("/my-comment")
	// public String f(Model attr) {// 我的评论
	// return PATH+"my-comment";
	// }
	@RequestMapping("/comment-goods")
	public String f1(Model attr) {// 评论商品
		return PATH + "comment-goods";
	}

	//
	// @RequestMapping("/see-history")
	// public String g(Model attr) {// 浏览历史
	// return PATH+"see-history";
	// }
	//
	// @RequestMapping("/pro-peyment")
	// public String h(Model attr) {// 物业缴费
	// return PATH+"pro-peyment";
	// }
	//
	// @RequestMapping("/store-collection")
	// public String i(Model attr) {// 商铺收藏
	// return PATH+"store-collection";
	// }
	@RequestMapping("/goods-collection")
	public String i(Model attr) {// 商品收藏
		return PATH + "goods-collection";
	}

	@RequestMapping("/store-attention")
	public String j(Model attr) {// 关注店铺
		return PATH + "store-attention";
	}

	//
	// @RequestMapping("/my-integration")
	// public String k(Model attr) {// 我的积分
	// return PATH+"my-integration";
	// }
	//
	@RequestMapping("/my-message")
	public String l(Model attr) {// 我的消息
		return PATH + "my-message";
	}

	@RequestMapping("/my-coupon")
	public String m(Model attr,@RequestParam(defaultValue="1")int pageIndex) {// 我的优惠卷
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizCouponVO> page = new Page<BizCouponVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(12);
		page.setParams(map);
		List<BizCouponVO> list = bizCouponMapper.queryPage(page);
		attr.addAttribute("list", list);
		return PATH + "my-coupon";
	}

	@RequestMapping("/my-info")
	public String n(Model attr,HttpSession session) {// 个人信息
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		SysUser sysUser = sysUserMapper.queryByBindTel(sessionBean.getBindTel());
		attr.addAttribute("sysUser", sysUser);
		return PATH + "my-info";
	}

	@RequestMapping("/update-pwd")
	public String o(Model attr) {// 修改密码
		return PATH + "update-pwd";
	}

	@RequestMapping("/update-pwd-2")
	public String o2(Model attr) {// 修改密码 第二步骤
		return PATH + "update-pwd-2";
	}

	@RequestMapping("/update-pwd-3")
	public String o3(Model attr) {// 修改密码 第三步骤 提示成功
		return PATH + "update-pwd-2";
	}

	@RequestMapping("/update-address")
	public String p(Model attr,HttpSession httpSession) {// 收货地址
		SessionBean sessionBean = (SessionBean) httpSession.getAttribute("sessionBean");
		List<BizAddrVO> list = bizAddrMapper.queryByUserId(sessionBean.getUserId());
		attr.addAttribute("list", list);
		return PATH + "update-address";
	}
	@RequestMapping("/update-address-2")
	public String p2(Model attr,Long id) {// 编辑收货地址
		BizAddrVO bizAddrVO = bizAddrMapper.queryByIdkey(id);
		attr.addAttribute("bizAddrVO", bizAddrVO);
		return PATH + "update-address-2";
	}
	@Resource
	private BizCouponMapper bizCouponMapper;
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private BizAddrMapper bizAddrMapper;
}
