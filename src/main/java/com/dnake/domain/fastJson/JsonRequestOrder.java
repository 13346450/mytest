package com.dnake.domain.fastJson;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于解析订单请求的json解析
* @ClassName: JsonRequestOrder 
* @Description: TODO
* @author cqg
* @date 2015年4月7日 上午10:40:19 
*
 */
public class JsonRequestOrder {
	/**
	 * 商铺id
	 */
	private Long shopId;
	private Long[] giftId;//优惠券id
	private List<JsonRequestGoodsAndQuantity> goods = new ArrayList<JsonRequestGoodsAndQuantity>();
	private String remarks;

	



	public Long getShopId() {
		return shopId;
	}



	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}



	public Long[] getGiftId() {
		return giftId;
	}



	public void setGiftId(Long[] giftId) {
		this.giftId = giftId;
	}


	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public List<JsonRequestGoodsAndQuantity> getGoods() {
		return goods;
	}



	public void setGoods(List<JsonRequestGoodsAndQuantity> goods) {
		this.goods = goods;
	}
	
	

}
