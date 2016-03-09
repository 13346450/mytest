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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizPromotionGiftVO;
import com.dnake.domain.business.BizUserGiftVO;
import com.dnake.domain.business.BizUserGift;
import com.dnake.mapper.business.BizGoodsMapper;
import com.dnake.mapper.business.BizPromotionGiftMapper;
import com.dnake.mapper.business.BizUserGiftMapper;
import com.dnake.service.business.BizUserGiftService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonRequestShopAndGoods;
import com.dnake.domain.fastJson.JsonReturnGift;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.BeanCopy;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

@Service
public class BizUserGiftServiceImpl extends BaseServiceImpl implements
		BizUserGiftService {
	@Resource
	private BizUserGiftMapper bizUserGiftMapper;
	@Resource
	private BizGoodsMapper bizGoodsMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizPromotionGiftMapper bizPromotionGiftMapper;

	@Override
	public Page<BizUserGiftVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		if (StringUtils.isNull(searchParam.getStartDatetime())) {
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(),
							Constants.START_PERIOD), "yyyy-mm-dd")
					+ " 00:00:00");
		} else {
			searchParam.setStartDatetime(searchParam.getStartDatetime()
					+ " 00:00:00");
		}
		if (StringUtils.isNull(searchParam.getEndDatetime())) {
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(),
					"yyyy-mm-dd") + " 23:59:59");
		} else {
			searchParam.setEndDatetime(searchParam.getEndDatetime()
					+ " 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<BizUserGiftVO> page = new Page<BizUserGiftVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizUserGiftMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizUserGift bizUserGift) {
		bizUserGift.setChgDt(new Date());
		bizUserGiftMapper.update(bizUserGift);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizUserGift bizUserGift) {
		bizUserGift.setChgDt(new Date());
		bizUserGiftMapper.insert(bizUserGift);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizUserGiftMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileQuery(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getPage(),parms.getCount())){
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", getUserBean().getUserId());
		map.put("type",parms.getType());
		Page<BizUserGiftVO> page = new Page<BizUserGiftVO>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		page.setParams(map);
		List<BizUserGiftVO> list =bizUserGiftMapper.queryPage(page);
		JsonResult jr=new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileQueryIsHaveGift(MobileParms parms) {
		List<JsonRequestShopAndGoods> jsonShopAndGoodsList = null;
		System.out.println("cqg >"+parms.getData());
		try {
			jsonShopAndGoodsList = JSON.parseObject(parms.getData(),
					new TypeReference<List<JsonRequestShopAndGoods>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		/**
		 * 按商品id查询价格
		 */
		Map<String,Object> queryDiscountPriceMap=new HashMap<String,Object>();//存放折扣后(现价)的map
		Map<Long,Double> discountPriceMap=new HashMap<Long, Double>();
		List<Long> idKeys=new ArrayList<Long>();
		for(JsonRequestShopAndGoods jsonShopAndGoods:jsonShopAndGoodsList){
			for(int i=0;i<jsonShopAndGoods.getGoods().size();i++){
				idKeys.add(jsonShopAndGoods.getGoods().get(i).getGoodsId());
			}
		}
		if(idKeys.size()!=0){
			queryDiscountPriceMap.put("idKeys", idKeys);
		}
		List<BizGoods> bizGoodsVOList=bizGoodsMapper.queryDiscountPriceByIds(queryDiscountPriceMap);
		for(BizGoods bizGoods:bizGoodsVOList){
			discountPriceMap.put(bizGoods.getIdKey(), bizGoods.getDiscountPrice());
		}
		
		/**
		 * 查询该用户优惠券信息
		 */
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", getUserBean().getUserId());
		queryMap.put("type",parms.getType());
		queryMap.put("nowTime",new Date());
		Page<BizUserGiftVO> page = new Page<BizUserGiftVO>();
		page.setPageNo(1);
		page.setPageSize(9999);
		page.setParams(queryMap);
		List<BizUserGiftVO> bizUserGiftVOlist =bizUserGiftMapper.queryPage(page);
		/**
		 * 按照每个店的购物价格查询是否满足优惠
		 */
		List<JsonReturnGift> resultList=new ArrayList<JsonReturnGift>(); //返回值list
		for(JsonRequestShopAndGoods jsonShopAndGoods:jsonShopAndGoodsList){
			JsonReturnGift jsonReturnGift=new JsonReturnGift();
			jsonReturnGift.setShopId(jsonShopAndGoods.getShopId());
			int sum=0;
			for(int i=0;i<jsonShopAndGoods.getGoods().size();i++){
				System.out.println("cqg >"+jsonShopAndGoods.getGoods().get(i)+discountPriceMap);
				 sum+=discountPriceMap.get(jsonShopAndGoods.getGoods().get(i).getGoodsId())*jsonShopAndGoods.getGoods().get(i).getGoodsQuantity();
			}
			for(BizUserGiftVO bizUserGiftVO :bizUserGiftVOlist){
				if(bizUserGiftVO.getShopId().equals(jsonShopAndGoods.getShopId())){
					if(sum>=bizUserGiftVO.getCouponMinMoney()){
						JsonReturnGift.Gift gift=jsonReturnGift.new Gift();
						gift.setGiftId(bizUserGiftVO.getIdKey());
						gift.setName(bizUserGiftVO.getName());
						gift.setValue(bizUserGiftVO.getValue());
						jsonReturnGift.getGift().add(gift);
					}
				}
			}
			resultList.add(jsonReturnGift);
		}
		JsonResult jr=new JsonResult();
		jr.setData(resultList);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileInsert(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getIdKey())){
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizPromotionGiftVO> page = new Page<BizPromotionGiftVO>();
		page.setPageSize(9999);
		map.put("idKey", parms.getIdKey());
		page.setParams(map);
		List<BizPromotionGiftVO> promotionGiftList=bizPromotionGiftMapper.queryPage(page);
		BizUserGift bizUserGift=new BizUserGift();
		try {
			System.out.println("cqg >"+promotionGiftList.get(0));
			BeanCopy.copyBeanAllSimpleProperties(bizUserGift, promotionGiftList.get(0), false);
			System.err.println(bizUserGift);
			bizUserGift.setUserId(getUserBean().getUserId());
			bizUserGift.setIdKey(null);
			bizUserGift.setGiftId( promotionGiftList.get(0).getIdKey());
			bizUserGift.setStatus(1);//未使用
			bizUserGift.setChgDt(new Date());
			if(bizUserGift.getType()==1){//预留其他优惠类型
				
			}else{
				
			}
			bizUserGiftMapper.insert(bizUserGift);
			bizPromotionGiftMapper.remainMinusOne(promotionGiftList.get(0).getIdKey());//优惠数量减1
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		return ResultBuilderUtil.statusMessage(1, "操作成功");
	}


}
