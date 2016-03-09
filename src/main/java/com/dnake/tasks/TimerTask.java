package com.dnake.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizOrder;
import com.dnake.domain.business.BizOrderDetails;
import com.dnake.mapper.business.BizGoodsMapper;
import com.dnake.mapper.business.BizOrderDetailsMapper;
import com.dnake.mapper.business.BizOrderMapper;
import com.dnake.mapper.business.BizOrderNumMapper;
import com.dnake.utils.DateTimeUtil;

@Component
public class TimerTask {
	@Resource
	private BizGoodsMapper bizGoodsMapper;
	@Resource
	private BizOrderMapper bizOrderMapper;
	@Resource
	private BizOrderDetailsMapper bizOrderDetailsMapper;
	/**
	 * 商品自动上架
	 * 
	 *@author cqg 
	 *2015年9月23日
	 */
	@Scheduled(cron="${scheduled.corn.goodsAutoPutOn}")
	public void goodsAutoPutOn(){
		bizGoodsMapper.autoPutOn(DateTimeUtil.nowAddTime(new Date(), 0, 0, -1, 0), DateTimeUtil.nowAddTime(new Date(), 0, 0, 0, 1));
	}
	/**
	 * 每隔一分钟自动关闭未付款的商品
	 *  @author zgj
	 *	日期：2015年9月24日上午10:18:32
	 *  描述：
	 */
	@Scheduled(cron="${scheduled.corn.orderAutoUpdate}")
	public void orderAutoUpdate(){
		List<BizOrder> list = bizOrderMapper.queryOrderForTimeTask(new Date());
		String idKeys = "";
		for (BizOrder bizOrder : list)
		{
			//给退款商品数量加回
			List<BizOrderDetails> listTemp = bizOrderDetailsMapper.queryByOrderId(bizOrder.getIdKey());
			for (BizOrderDetails bizOrderDetails : listTemp)
			{
				BizGoods bizTemp = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
				BizGoods bizGoodsTemp = new BizGoods();
				bizGoodsTemp.setIdKey(bizOrderDetails.getGoodsId());
				bizGoodsTemp.setQuantity(bizTemp.getQuantity() + bizOrderDetails.getGoodsQuantity());
				bizGoodsMapper.update(bizGoodsTemp);
			}
			idKeys += bizOrder.getIdKey() + ",";
		}
		if(!idKeys.equals(""))
		{
			idKeys = idKeys.substring(0, idKeys.length()-1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKeys", Arrays.asList(idKeys.split(",")));
			bizOrderMapper.updateOrderForTimeTask(map);
		}
	}
}
