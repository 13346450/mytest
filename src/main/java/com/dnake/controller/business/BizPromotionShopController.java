package com.dnake.controller.business;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPromotionShop;
import com.dnake.domain.business.BizPromotionShopVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizPromotionShopService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizPromotionShop/*")
@Login
public class BizPromotionShopController extends BaseController {

@Resource
private BizPromotionShopService bizPromotionShopService;

@Resource
private CommonService commonSevice;

@RequestMapping(value = "listPage")
@ResponseBody
public Page<BizPromotionShopVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
return bizPromotionShopService.listPage(pageIndex, rows, searchParam);}

@RequestMapping(value = "update")
public void update(HttpServletResponse response,BizPromotionShop bizPromotionShop){
outputString(response, bizPromotionShopService.update(bizPromotionShop));}

@RequestMapping(value = "insert")
public void insert(HttpServletResponse response,BizPromotionShop bizPromotionShop){
outputString(response, bizPromotionShopService.insert(bizPromotionShop));}

@RequestMapping(value="deleteMulti")
public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
outputString(response, bizPromotionShopService.deleteMulti(searchParam.getDeleteIds()));}

}
