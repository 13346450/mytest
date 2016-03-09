package com.dnake.controller.business;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPromotionGift;
import com.dnake.domain.business.BizPromotionGiftVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizPromotionGiftService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizPromotionGift/*")
@Login
public class BizPromotionGiftController extends BaseController {

@Resource
private BizPromotionGiftService bizPromotionGiftService;

@Resource
private CommonService commonSevice;

@RequestMapping(value = "listPage")
@ResponseBody
public Page<BizPromotionGiftVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
return bizPromotionGiftService.listPage(pageIndex, rows, searchParam);}

@RequestMapping(value = "update")
public void update(HttpServletResponse response,BizPromotionGift bizPromotionGift){
outputString(response, bizPromotionGiftService.update(bizPromotionGift));}

@RequestMapping(value = "insert")
public void insert(HttpServletResponse response,BizPromotionGift bizPromotionGift){
outputString(response, bizPromotionGiftService.insert(bizPromotionGift));}

@RequestMapping(value="deleteMulti")
public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
outputString(response, bizPromotionGiftService.deleteMulti(searchParam.getDeleteIds()));}

}
