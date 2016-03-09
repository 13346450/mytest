package com.dnake.controller.ishang;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.business.BizHousekeepingVO;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.service.business.BizHousekeepingService;
import com.dnake.service.business.BizProperInfoService;
import com.dnake.service.business.BizPropertiesRepairService;
import com.dnake.service.system.SysCommunityInfoService;
import com.dnake.service.system.SysCommunityService;
import com.dnake.utils.SessionBean;

@Controller
@RequestMapping("/property/*")
public class JspPropertyController {
	private static final String PATH = "ishang/property/";
	@RequestMapping("home")
	public String a(Model model, HttpSession session) {// 物业首页
		SessionBean bean = (SessionBean) session.getAttribute("sessionBean");
		if (null != bean) {
			SearchParam searchParam = new SearchParam();
			searchParam.setCommunityId(bean.getDeptId());
			Page<BizProperInfoVO> bizProperInfoVOs = bizProperInfoService.listPage(1, 8, searchParam);
			Page<BizPropertiesRepairVO> bizPropertiesRepairVOs = bizPropertiesRepairService.listPage(1, 8, searchParam);

			List<SysCommunityVO> sysCommunities = sysCommunityMapper.mobileQueryCommunityInfo(bean.getDeptId());
			String[] catatorys = bizHousekeepingService.queryCategorysByCommuntityId(bean.getDeptId());
			//
			model.addAttribute("bizProperInfoVOs", bizProperInfoVOs);
			model.addAttribute("bizPropertiesRepairVOs", bizPropertiesRepairVOs);
			model.addAttribute("sysCommunities", sysCommunities);
			model.addAttribute("housekeepingCatatorys", catatorys);
		}
		return PATH + "home";
	}

	@RequestMapping("housekeeping")
	public String b(Model model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int pageIndex, @RequestParam(value = "rows", defaultValue = "12") int rows, String names) {// 家政
		SessionBean bean = (SessionBean) session.getAttribute("sessionBean");
		if (null != bean) {
			String[] categorys = bizHousekeepingService.queryCategorysByCommuntityId(bean.getDeptId());
			Page<BizHousekeepingVO> bizHousekeepingVOsPage = bizHousekeepingService.search(pageIndex, rows, names, bean.getDeptId());
			model.addAttribute("housekeepingCategorys", categorys);
			model.addAttribute("bizHousekeepingVOsPage", bizHousekeepingVOsPage);
		}
		return PATH + "housekeeping";
	}

	@RequestMapping("repair")
	public String c(Model model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") Integer pageIndex) {// 维修
		SessionBean bean = (SessionBean) session.getAttribute("sessionBean");
		if (null != bean) {
			MobileParms parms = new MobileParms();
			parms.setCount(10);
			parms.setPage(pageIndex);
			parms.setRepairStatus(0);
			String result = bizPropertiesRepairService.mobileQueryList(parms);
			model.addAttribute("bizPropertiesRepairVOs", JSON.parse(result));
		}
		return PATH + "repair";
	}

	@Resource
	private BizProperInfoService bizProperInfoService;
	@Resource
	private BizPropertiesRepairService bizPropertiesRepairService;
	@Resource
	private BizHousekeepingService bizHousekeepingService;
	@Resource
	private SysCommunityInfoService sysCommunityInfoService;
	@Resource
	private SysCommunityService sysCommunityService;
	@Resource
	private SysCommunityMapper sysCommunityMapper;
}
