package com.dnake.controller.business;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizUserGift;
import com.dnake.domain.business.BizUserGiftVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizUserGiftService;
import com.dnake.service.common.CommonService;

@Controller
@RequestMapping("/business/BizUserGift/*")
@Login
public class BizUserGiftController extends BaseController {

@Resource
private BizUserGiftService bizUserGiftService;

@Resource
private CommonService commonSevice;

@RequestMapping(value = "listPage")
@ResponseBody
public Page<BizUserGiftVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
return bizUserGiftService.listPage(pageIndex, rows, searchParam);}

@RequestMapping(value = "update")
public void update(HttpServletResponse response,BizUserGift bizUserGift){
outputString(response, bizUserGiftService.update(bizUserGift));}

@RequestMapping(value = "insert")
public void insert(HttpServletResponse response,BizUserGift bizUserGift){
outputString(response, bizUserGiftService.insert(bizUserGift));}

@RequestMapping(value="deleteMulti")
public void deleteMulti(HttpServletResponse response, SearchParam searchParam){
outputString(response, bizUserGiftService.deleteMulti(searchParam.getDeleteIds()));}

}
