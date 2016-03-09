package com.dnake.domain.fastJson;

import java.util.List;
/**
 * 购物车提交时查询优惠
 * @author Administrator
 *
 */
public class JsonRequestShopAndGoods {
	private Long shopId;
	private List<JsonRequestGoodsAndQuantity> goods;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<JsonRequestGoodsAndQuantity> getGoods() {
		return goods;
	}

	public void setGoods(List<JsonRequestGoodsAndQuantity> goods) {
		this.goods = goods;
	}

	
}
