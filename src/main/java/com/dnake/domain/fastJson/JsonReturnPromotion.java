package com.dnake.domain.fastJson;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回促销的信息
 * 
 * @ClassName: JsonReturnPromotion
 * @Description: TODO
 * @author cqg
 * @date 2015年4月8日 上午11:31:29
 *
 */
public class JsonReturnPromotion {
	private Long idKey;
	private List<Long> shopIds = new ArrayList<Long>();
	private String name;
	private Integer type;
	private Timestamp startDt;
	private Timestamp endDt;
	private Double minSum;
	private Integer status;
	private List<JsonRequestGift> gift = new ArrayList<JsonRequestGift>();

	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getStartDt() {
		return startDt;
	}

	public void setStartDt(Timestamp startDt) {
		this.startDt = startDt;
	}

	public Timestamp getEndDt() {
		return endDt;
	}

	public void setEndDt(Timestamp endDt) {
		this.endDt = endDt;
	}

	public Double getMinSum() {
		return minSum;
	}

	public void setMinSum(Double minSum) {
		this.minSum = minSum;
	}

	public List<JsonRequestGift> getGift() {
		return gift;
	}

	public void setGift(List<JsonRequestGift> gift) {
		this.gift = gift;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

}
