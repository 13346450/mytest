package com.dnake.domain.business;
import java.util.Date;
public class BizPromotionGoodsVO {
private Long idKey;
private Long goodsId;
private Long promotionId;
private Long categoryId;
private Date chgDt;


public Long getIdKey() {
return idKey;
}

public void setIdKey(Long idKey) {
this.idKey = idKey;
}
public Long getGoodsId() {
return goodsId;
}

public void setGoodsId(Long goodsId) {
this.goodsId = goodsId;
}
public Long getPromotionId() {
return promotionId;
}

public void setPromotionId(Long promotionId) {
this.promotionId = promotionId;
}
public Long getCategoryId() {
return categoryId;
}

public void setCategoryId(Long categoryId) {
this.categoryId = categoryId;
}
public Date getChgDt() {
return chgDt;
}

public void setChgDt(Date chgDt) {
this.chgDt = chgDt;
}
}
