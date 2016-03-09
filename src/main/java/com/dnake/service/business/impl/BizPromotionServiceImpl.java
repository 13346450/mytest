package com.dnake.service.business.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dnake.domain.business.BizPromotionGift;
import com.dnake.domain.business.BizPromotionRule;
import com.dnake.domain.business.BizPromotionShop;
import com.dnake.domain.business.BizPromotionVO;
import com.dnake.domain.business.BizPromotion;
import com.dnake.mapper.business.BizPromotionGiftMapper;
import com.dnake.mapper.business.BizPromotionMapper;
import com.dnake.mapper.business.BizPromotionRuleMapper;
import com.dnake.mapper.business.BizPromotionShopMapper;
import com.dnake.service.business.BizPromotionService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonRequestGift;
import com.dnake.domain.fastJson.JsonReturnPromotion;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.BeanCopy;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.ValidationUtil;

@Service
public class BizPromotionServiceImpl extends BaseServiceImpl implements
		BizPromotionService {
	@Resource
	private BizPromotionMapper bizPromotionMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizPromotionRuleMapper bizPromotionRuleMapper;
	@Resource
	private BizPromotionShopMapper bizPromotionShopMapper;
	@Resource
	private BizPromotionGiftMapper bizPromotionGiftMapper;
	@Override
	public Page<BizPromotionVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<BizPromotionVO> page = new Page<BizPromotionVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizPromotionMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizPromotion bizPromotion) {
		// bizPromotion.setChgDt(new Date());
		bizPromotionMapper.update(bizPromotion);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizPromotion bizPromotion) {
		// bizPromotion.setChgDt(new Date());
		bizPromotionMapper.insert(bizPromotion);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizPromotionMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	@Transactional
	public String mobileInsert(MobileParms parms) {
		if (ValidationUtil.checkHaveNull(parms.getShopIds(), parms.getName(), 
	            parms.getType(), parms.getStartDt(), parms.getEndDt(), 
	            parms.getMinSum(), parms.getGift())) { 
	         return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER; 
	      } 
	      // 增加到促销活动表 
	      BizPromotion bizPromotion = new BizPromotion(); 
	      bizPromotion.setName(parms.getName()); 
	      bizPromotion.setStartDt(parms.getStartDt()); 
	      bizPromotion.setEndDt(parms.getEndDt()); 
	      bizPromotion.setRemarks(parms.getRemarks()); 
	      bizPromotion.setChgDt(new Date()); 
	      bizPromotion.setType(Integer.valueOf(parms.getType())); 
	      bizPromotion.setStatus(1); 
	      bizPromotionMapper.insert(bizPromotion); 
	      // 增加到促销活动店铺表 
	      List<BizPromotionShop> list = new ArrayList<BizPromotionShop>(); 
	      for (String shopId : parms.getShopIds().split(",")) { 
	         BizPromotionShop bizPromotionShop = new BizPromotionShop(); 
	         bizPromotionShop.setShopId(Long.valueOf(shopId)); 
	         bizPromotionShop.setPromotionId(bizPromotion.getIdKey()); 
	         list.add(bizPromotionShop); 
	      } 
	      bizPromotionShopMapper.insertMulti(list); 
	      // 添加规则表 
	      BizPromotionRule bizPromotionRule = new BizPromotionRule(); 
	      bizPromotionRule.setPromotionId(bizPromotion.getIdKey()); 
	      bizPromotionRule.setCompareName("sum");// 总价 
	      bizPromotionRule.setCompareType("get");// 大于等于 
	      bizPromotionRule.setValue(parms.getMinSum()); 
	      bizPromotionRuleMapper.insert(bizPromotionRule); 
	      // 添加优惠信息 
	      List<BizPromotionGift> bizPromotionGiftList = new ArrayList<BizPromotionGift>(); 
	      List<JsonRequestGift> giftList = null; 
	      try { 
	         giftList = JSON.parseObject(parms.getGift(), 
	               new TypeReference<List<JsonRequestGift>>() { 
	               }); 
	      } catch (Exception e) { 
	         return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER; 
	      } 
	      for (JsonRequestGift gift : giftList) { 
	         try { 
	            BizPromotionGift bizPromotionGift = new BizPromotionGift(); 
	            BeanCopy.copyBeanAllSimpleProperties(bizPromotionGift, 
	                  gift, false); 
	            bizPromotionGift.setPromotionId(bizPromotion.getIdKey()); 
	            bizPromotionGift.setType(gift.getGiftType());
	            bizPromotionGift.setRemain(Long.valueOf(gift.getTotal()));
	            System.out.println("cqg >"+bizPromotionGift);
	            bizPromotionGiftList.add(bizPromotionGift); 
	         } catch (IllegalAccessException | InvocationTargetException e) { 
	            e.printStackTrace(); 
	         } 
	      } 
	      bizPromotionGiftMapper.insertMulti(bizPromotionGiftList); 
	      return ResultBuilderUtil.RESULT_SUCCESS;
	   }
	

	@Override
	public String mobileQueryByuserId(MobileParms parms) {
		List<JsonReturnPromotion> returnList=new ArrayList<JsonReturnPromotion>();
		Page<BizPromotionVO> page = new Page<BizPromotionVO>();
		page.setPageNo(1);
		page.setPageSize(999);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesId", getUserBean().getUserId());
		map.put("shopId", parms.getShopId());
		page.setParams(map);
		List<BizPromotionVO> list=bizPromotionMapper.queryPage(page);
		for(BizPromotionVO bizPromotionVO:list){
			JsonReturnPromotion jsonReturnPromotion=new JsonReturnPromotion();
			jsonReturnPromotion.setIdKey(bizPromotionVO.getIdKey());
			jsonReturnPromotion.setStartDt(bizPromotionVO.getStartDt());
			jsonReturnPromotion.setEndDt(bizPromotionVO.getEndDt());
			jsonReturnPromotion.setType(bizPromotionVO.getType());
			jsonReturnPromotion.setName(bizPromotionVO.getName());
			jsonReturnPromotion.setStatus(bizPromotionVO.getStatus());
			//活动规则
			List<BizPromotionRule> bizPromotionRuleList=bizPromotionRuleMapper.queryByPromotionId(bizPromotionVO.getIdKey());
			jsonReturnPromotion.setMinSum(Double.valueOf(bizPromotionRuleList.get(0).getValue()));//这里先做一个规则的
			
			//优惠店铺,放在list里
			List<BizPromotionShop> bizPromotionShopList=bizPromotionShopMapper.queryByPromotionId(bizPromotionVO.getIdKey());
			for(BizPromotionShop bizPromotionShop:bizPromotionShopList){
				jsonReturnPromotion.getShopIds().add(bizPromotionShop.getIdKey());
			}
			//优惠条件
			List<BizPromotionGift> bizPromotionGiftList=bizPromotionGiftMapper.queryByPromotionId(bizPromotionVO.getIdKey());
			for (BizPromotionGift bizPromotionGift : bizPromotionGiftList) {
				JsonRequestGift jrg=new JsonRequestGift();
				try {
					BeanCopy.copyBeanSimpleProperties(jrg, bizPromotionGift, false);
					jrg.setGiftType(bizPromotionGift.getType());
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				jsonReturnPromotion.getGift().add(jrg);
			}
			returnList.add(jsonReturnPromotion);
		}
		//构建返回json
		JsonResult jr=new JsonResult();
		jr.setData(returnList);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileUpdate(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getIdKey(),parms.getStatus())){
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		BizPromotion bizPromotion=new BizPromotion();
		bizPromotion.setIdKey(parms.getIdKey());
		bizPromotion.setStatus(parms.getStatus());
		bizPromotionMapper.update(bizPromotion);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
}
