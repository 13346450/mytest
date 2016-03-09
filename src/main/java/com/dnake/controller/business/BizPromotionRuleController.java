package com.dnake.controller.business;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPromotionRule;
import com.dnake.domain.business.BizPromotionRuleVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizPromotionRuleService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizPromotionRule/*")
@Login
public class BizPromotionRuleController extends BaseController {

@Resource
private BizPromotionRuleService bizPromotionRuleService;

@Resource
private CommonService commonSevice;

@RequestMapping(value = "listPage")
@ResponseBody
public Page<BizPromotionRuleVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
return bizPromotionRuleService.listPage(pageIndex, rows, searchParam);}

@RequestMapping(value = "update")
public void update(HttpServletResponse response,BizPromotionRule bizPromotionRule){
outputString(response, bizPromotionRuleService.update(bizPromotionRule));}

@RequestMapping(value = "insert")
public void insert(HttpServletResponse response,BizPromotionRule bizPromotionRule){
outputString(response, bizPromotionRuleService.insert(bizPromotionRule));}

@RequestMapping(value="deleteMulti")
public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
outputString(response, bizPromotionRuleService.deleteMulti(searchParam.getDeleteIds()));}

}
