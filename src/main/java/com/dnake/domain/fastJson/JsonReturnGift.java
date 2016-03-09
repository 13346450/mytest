package com.dnake.domain.fastJson;

import java.util.ArrayList;
import java.util.List;

public class JsonReturnGift {
	private Long shopId;
	private List<Gift> gift=new ArrayList<JsonReturnGift.Gift>();

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<Gift> getGift() {
		return gift;
	}

	public void setGift(List<Gift> gift) {
		this.gift = gift;
	}

	public class Gift {
		private Long giftId;
		private String name;
		private Double Value;

		public Long getGiftId() {
			return giftId;
		}

		public void setGiftId(Long giftId) {
			this.giftId = giftId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getValue() {
			return Value;
		}

		public void setValue(Double value) {
			Value = value;
		}

	}
}
