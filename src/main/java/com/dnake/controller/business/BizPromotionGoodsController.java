package com.dnake.controller.business;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPromotionGoods;
import com.dnake.domain.business.BizPromotionGoodsVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizPromotionGoodsService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizPromotionGoods/*")
@Login
public class BizPromotionGoodsController extends BaseController {

@Resource
private BizPromotionGoodsService bizPromotionGoodsService;

@Resource
private CommonService commonSevice;

@RequestMapping(value = "listPage")
@ResponseBody
public Page<BizPromotionGoodsVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
return bizPromotionGoodsService.listPage(pageIndex, rows, searchParam);}

@RequestMapping(value = "update")
public void update(HttpServletResponse response,BizPromotionGoods bizPromotionGoods){
outputString(response, bizPromotionGoodsService.update(bizPromotionGoods));}

@RequestMapping(value = "insert")
public void insert(HttpServletResponse response,BizPromotionGoods bizPromotionGoods){
outputString(response, bizPromotionGoodsService.insert(bizPromotionGoods));}

@RequestMapping(value="deleteMulti")
public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
outputString(response, bizPromotionGoodsService.deleteMulti(searchParam.getDeleteIds()));}

}
