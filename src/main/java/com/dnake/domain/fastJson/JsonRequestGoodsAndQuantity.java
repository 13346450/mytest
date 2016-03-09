package com.dnake.domain.fastJson;

public class JsonRequestGoodsAndQuantity {
	private Long goodsId;// 商品id
	private Integer goodsQuantity;// 商品数量

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

}
