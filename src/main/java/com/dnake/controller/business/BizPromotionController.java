package com.dnake.controller.business;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPromotion;
import com.dnake.domain.business.BizPromotionVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizPromotionService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizPromotion/*")
@Login
public class BizPromotionController extends BaseController {

@Resource
private BizPromotionService bizPromotionService;

@Resource
private CommonService commonSevice;

@RequestMapping(value = "listPage")
@ResponseBody

public Page<BizPromotionVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
return bizPromotionService.listPage(pageIndex, rows, searchParam);}

@RequestMapping(value = "update")
public void update(HttpServletResponse response,BizPromotion bizPromotion){
outputString(response, bizPromotionService.update(bizPromotion));}

@RequestMapping(value = "insert")
public void insert(HttpServletResponse response,BizPromotion bizPromotion){
outputString(response, bizPromotionService.insert(bizPromotion));}

@RequestMapping(value="deleteMulti")
public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
outputString(response, bizPromotionService.deleteMulti(searchParam.getDeleteIds()));}

}
