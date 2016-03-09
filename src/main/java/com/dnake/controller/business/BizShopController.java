package com.dnake.controller.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.ClearLogin;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizGoodsImage;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.business.BizPromotionVO;
import com.dnake.domain.business.BizShop;
import com.dnake.domain.business.BizShopVO;
import com.dnake.domain.business.BizShoppingcartVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizCategoryMapper;
import com.dnake.mapper.business.BizGoodsImageMapper;
import com.dnake.mapper.business.BizGoodsMapper;
import com.dnake.mapper.business.BizPromotionMapper;
import com.dnake.mapper.business.BizShopMapper;
import com.dnake.mapper.business.BizShoppingcartMapper;
import com.dnake.service.business.BizShopService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.SessionBean;

@Controller
@RequestMapping("/business/BizShop/*")
@Login
public class BizShopController extends BaseController {

	@Resource
	private BizShopService bizShopService;

	@Resource
	private CommonService commonSevice;
	@Resource
	private BizCategoryMapper bizCategoryMapper;
	@Resource
	private BizGoodsMapper bizGoodsMapper;
	@Resource
	private BizGoodsImageMapper bizGoodsImageMapper;
	@Resource
	private BizShopMapper bizShopMapper;
	@Resource
	private BizPromotionMapper bizPromotionMapper;
	@Resource
	private BizShoppingcartMapper bizShoppingcartMapper;
	@RequestMapping("")
	@ClearLogin
	public String index(Model model, @CookieValue(value="ishang-cdata",defaultValue="") String cdata ) {
		if(!cdata.equals("")){
			Long cId = Long.parseLong(cdata.split(":")[0]);
			List<BizCategory> c1 = bizCategoryMapper.queryByParentNameAndCommunity(cId,"社区商城");
			List<BizCategory> c2 = bizCategoryMapper.queryByParentNameAndCommunity(cId,"餐饮美食");
			List<BizCategory> c3 = bizCategoryMapper.queryByParentNameAndCommunity(cId,"休闲健身");
			Page<BizGoodsVO> page1 = new Page<BizGoodsVO>();
			page1.setPageSize(8);
			page1.setPageNo(1);
			page1.getParams().put("modelId", Constants.BIZ_CATEGORY_COMMUNITYMALL_ID);
			List<BizGoodsVO> bizGoodsVOs1 = bizGoodsMapper.queryPage(page1);
			
			Page<BizGoodsVO> page2 = new Page<BizGoodsVO>();
			page2.setPageSize(8);
			page2.setPageNo(1);
			page2.getParams().put("modelId", Constants.BIZ_CATEGORY_FOODS_ID);
			List<BizGoodsVO> bizGoodsVOs2 = bizGoodsMapper.queryPage(page2);
			
			Page<BizGoodsVO> page3 = new Page<BizGoodsVO>();
			page3.setPageSize(20);
			page3.setPageNo(1);
			page3.getParams().put("modelId", Constants.BIZ_CATEGORY_LEISURE_ID);
			List<BizGoodsVO> bizGoodsVOs3 = bizGoodsMapper.queryPage(page3);
			
			
			
			model.addAttribute("BizCategory1", c1);
			model.addAttribute("BizCategory2", c2);
			model.addAttribute("BizCategory3", c3);
			model.addAttribute("bizGoodsVOs1", bizGoodsVOs1);
			model.addAttribute("bizGoodsVOs2", bizGoodsVOs2);
			model.addAttribute("bizGoodsVOs3", bizGoodsVOs3);
			
		}
		return "business/pc/business-index";
	}
	@RequestMapping("item")
	@ClearLogin
	public String item(Model model, Long goodId) {
		
		BizGoodsVO bizGoodsVO = bizGoodsMapper.mobileQueryGoodsDetails(goodId);
		//查询图片
		List<BizGoodsImage> list1 = bizGoodsImageMapper.queryByGoodsId(bizGoodsVO.getIdKey());
		bizGoodsVO.setListGoodsImage(list1);
		
		BizShop bizShop = bizShopMapper.queryByIdkey(bizGoodsVO.getShopId());
		
		BizCategory bizCategory = bizCategoryMapper.selectById(bizGoodsVO.getGoodsTypeId());
		
		model.addAttribute("bizGoodsVO",bizGoodsVO);
		model.addAttribute("bizShop", bizShop);
		model.addAttribute("bizCategory", bizCategory);
		return "business/pc/commodity01";
	}
	@RequestMapping("search")
	@ClearLogin
	public String search(Model model, Long goodId) {
		return "business/pc/search";
	}
	@RequestMapping("store")
	@ClearLogin
	public String store(Model model, @RequestParam Long idKey,@CookieValue(value="ishang-cdata",defaultValue="") String cdata,
			@RequestParam(value="pageIndex",defaultValue="1") int pageIndex) {
		BizShop bizShop = bizShopMapper.queryByIdkey(idKey);
		
		Page<BizGoodsVO> page = new Page<BizGoodsVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(20);
		page.getParams().put("communityId", Long.parseLong(cdata.split(":")[0]));
		page.getParams().put("shopId", idKey);
		page.setRows(bizGoodsMapper.queryPage(page));
		
		
		Page<BizPromotionVO> page2 = new Page<BizPromotionVO>();
		page2.setPageNo(1);
		page2.setPageSize(Integer.MAX_VALUE);
		page2.getParams().put("shopId", idKey);
		page2.setRows(bizPromotionMapper.queryPage(page2));
		//店长推荐
		Page<BizGoodsVO> page3 = new Page<BizGoodsVO>();
		page3.setPageNo(1);
		page3.setPageSize(4);
		page3.getParams().put("shopId", idKey);
		page3.setRows(bizGoodsMapper.queryPage(page3));
		
		
		
		model.addAttribute("goodPage",page);
		model.addAttribute("bizPromotionPage",page2);
		model.addAttribute("recommendedGoodPage",page3);
		model.addAttribute("bizShop",bizShop);
		return "business/pc/store";
	}
	@RequestMapping("cart")
	@ClearLogin
	public String cart(Model model, HttpSession session) {
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		List<BizShoppingcartVO> bizShoppingcartVOs = bizShoppingcartMapper.mobileQueryList(sessionBean.getUserId());
		Map<Long, List<BizShoppingcartVO>> map = new HashMap<Long, List<BizShoppingcartVO>>();
		for(BizShoppingcartVO vo:bizShoppingcartVOs){
			List<BizShoppingcartVO> shoppingcartVOs = map.get(vo.getShopId());
			if(shoppingcartVOs==null){
				shoppingcartVOs = new ArrayList<BizShoppingcartVO>();
			}
			shoppingcartVOs.add(vo);
			map.put(vo.getShopId(), shoppingcartVOs);
		}
		model.addAttribute("bizShoppingcartVOMap", map);
		model.addAttribute("size", bizShoppingcartVOs.size());
		return "business/pc/cart01";
	}
	@RequestMapping("confirmCart")
	@ClearLogin
	public String confirmCart(Model model, HttpSession session,Long[] id,Long[] count) {
		Map<Long, List<BizGoodsVO>> map = new HashMap<Long, List<BizGoodsVO>>();
		Map<Long,Long> countMap = new HashMap<Long, Long>();
		int m = 0;
		for(Long  i:id){
			countMap.put(i, count[m]);
			m++;
			BizGoodsVO bizGoodsVO = bizGoodsMapper.mobileQueryGoodsDetails(i);
			List<BizGoodsVO> shoppingcartVOs = map.get(bizGoodsVO.getShopId());
			if(shoppingcartVOs==null){
				shoppingcartVOs = new ArrayList<BizGoodsVO>();
			}
			shoppingcartVOs.add(bizGoodsVO);
			map.put(bizGoodsVO.getShopId(), shoppingcartVOs);
		}
		model.addAttribute("goodsMap", map);
		model.addAttribute("countMap", countMap);
		model.addAttribute("size", id.length);
		return "business/pc/cart02";
	}
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizShopVO> listPage(@RequestParam("page") int pageIndex,
			int rows, SearchParam searchParam) {
		return bizShopService.listPage(pageIndex, rows, searchParam);
	}

	@RequestMapping(value = "queryShopByUserId/{userId}")
	@ResponseBody
	public List<BizShopVO> queryShopByUserId(@PathVariable Long userId) {
		return bizShopService.queryShopByuserId(userId);
	}

	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizShop bizShop) {
		outputString(response, bizShopService.update(bizShop));
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizShop bizShop) {
		outputString(response, bizShopService.insert(bizShop));
	}

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response,
			SearchParam searchParam) {
		outputString(response,
				bizShopService.deleteMulti(searchParam.getDeleteIds()));
	}

	@RequestMapping(value = "updateMulti")
	public void updateMulti(HttpServletResponse response,
			SearchParam searchParam) {
		outputString(response,
				bizShopService.updateMulti(searchParam.getDeleteIds(),searchParam.getStatus()));
	}
}
