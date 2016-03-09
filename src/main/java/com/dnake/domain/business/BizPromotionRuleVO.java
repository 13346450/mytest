package com.dnake.domain.business;
public class BizPromotionRuleVO {
private Long idKey;
private Long promotionId;
private String compareName;
private String compareType;
private String value;


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
public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}
}
