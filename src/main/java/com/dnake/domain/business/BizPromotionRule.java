package com.dnake.domain.business;
public class BizPromotionRule{
private Long idKey;
private Long promotionId;
private String compareName;
private String compareType;
private Double value;

public Long getIdKey() {
return idKey;
}

public void setIdKey(Long idKey) {
this.idKey = idKey;
}
public Long getPromotionId() {
return promotionId;
}

public void setPromotionId(Long promotionId) {
this.promotionId = promotionId;
}
public String getCompareName() {
return compareName;
}

public void setCompareName(String compareName) {
this.compareName = compareName;
}
public String getCompareType() {
return compareType;
}

public void setCompareType(String compareType) {
this.compareType = compareType;
}
public Double getValue() {
return value;
}

public void setValue(Double value) {
this.value = value;
}
}
