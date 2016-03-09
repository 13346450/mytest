package com.dnake.domain.business;
import java.util.Date;
public class BizPromotionGift{
//
private Long idKey;
//促销活动id
private Long promotionId;
//优惠数量
private Long total;
//优惠券生效时间
private Date startDt;
//优惠券结束时间
private Date endDt;
//优惠类型，1.红包，2.兑换券(兑换物品) 3.满立减；4满立送；5.满返红包 6满返兑换券
private Integer type;
//对应type的值
private String value;
//
private Long goodsId;
//
private Long couponCategory;
//
private Double couponMinMoney;
//
private Integer perMaxHave;
//
private Integer oneMaxUse;
//10.与任何活动都可以共用(优先级最高)；20.与本次(活动id一致)共用；30.不与任何活动共用；
private String useWithOthers;
//优惠券使用备注
private String remarks;
private Long remain;//剩下的优惠次数

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Long getIdKey() {
return idKey;
}

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setIdKey(Long idKey) {
this.idKey = idKey;
}
/**
*促销活动id
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Long getPromotionId() {
return promotionId;
}

/**
*促销活动id
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setPromotionId(Long promotionId) {
this.promotionId = promotionId;
}
/**
*优惠数量
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Long getTotal() {
return total;
}

/**
*优惠数量
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setTotal(Long total) {
this.total = total;
}
/**
*优惠券生效时间
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Date getStartDt() {
return startDt;
}

/**
*优惠券生效时间
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setStartDt(Date startDt) {
this.startDt = startDt;
}
/**
*优惠券结束时间
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Date getEndDt() {
return endDt;
}

/**
*优惠券结束时间
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setEndDt(Date endDt) {
this.endDt = endDt;
}
/**
*优惠类型，1.红包，2.兑换券(兑换物品) 3.满立减；4满立送；5.满返红包 6满返兑换券
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Integer getType() {
return type;
}

/**
*优惠类型，1.红包，2.兑换券(兑换物品) 3.满立减；4满立送；5.满返红包 6满返兑换券
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setType(Integer type) {
this.type = type;
}
/**
*对应type的值
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public String getValue() {
return value;
}

/**
*对应type的值
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setValue(String value) {
this.value = value;
}
/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Long getGoodsId() {
return goodsId;
}

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setGoodsId(Long goodsId) {
this.goodsId = goodsId;
}
/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Long getCouponCategory() {
return couponCategory;
}

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setCouponCategory(Long couponCategory) {
this.couponCategory = couponCategory;
}
/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Double getCouponMinMoney() {
return couponMinMoney;
}

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setCouponMinMoney(Double couponMinMoney) {
this.couponMinMoney = couponMinMoney;
}
/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Integer getPerMaxHave() {
return perMaxHave;
}

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setPerMaxHave(Integer perMaxHave) {
this.perMaxHave = perMaxHave;
}
/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public Integer getOneMaxUse() {
return oneMaxUse;
}

/**
*
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setOneMaxUse(Integer oneMaxUse) {
this.oneMaxUse = oneMaxUse;
}
/**
*10.与任何活动都可以共用(优先级最高)；20.与本次(活动id一致)共用；30.不与任何活动共用；
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public String getUseWithOthers() {
return useWithOthers;
}

/**
*10.与任何活动都可以共用(优先级最高)；20.与本次(活动id一致)共用；30.不与任何活动共用；
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setUseWithOthers(String useWithOthers) {
this.useWithOthers = useWithOthers;
}
/**
*优惠券使用备注
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public String getRemarks() {
return remarks;
}

/**
*优惠券使用备注
* @author 云服务工具
* @date 2015-04-02 08:59:03
*/
public void setRemarks(String remarks) {
this.remarks = remarks;
}

@Override
public String toString() {
	return "BizPromotionGift [idKey=" + idKey + ", promotionId=" + promotionId
			+ ", total=" + total + ", startDt=" + startDt + ", endDt=" + endDt
			+ ", type=" + type + ", value=" + value + ", goodsId=" + goodsId
			+ ", couponCategory=" + couponCategory + ", couponMinMoney="
			+ couponMinMoney + ", perMaxHave=" + perMaxHave + ", oneMaxUse="
			+ oneMaxUse + ", useWithOther=" + useWithOthers + ", remarks="
			+ remarks + "]";
}

public Long getRemain() {
	return remain;
}

public void setRemain(Long remain) {
	this.remain = remain;
}

}
