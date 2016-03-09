package com.dnake.domain.system;

/** 
 * 区域管理
 * @ClassName: SysRegion 
 * @Description: 
 * @author xzm
 * @date 2013-11-14 
 *  
 */
public class SysRegion implements java.io.Serializable {
	
	private static final long serialVersionUID = 7532018307929787073L;
	
	private Long idKey;		//主键
	private String regionNumber;// 归属地编码
	private Long parentId; // 上级归属地编码ID
	private String regionName;//归属地名称
	private String memos;//备注
	private Long regionTypeId;//归属地类别
	private Integer lastMark;//是否为末节点
	private Integer orderCd;//排序号
	private String parentName;//上一级归属地名称
	
	public Long getIdKey() {
		return this.idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getRegionNumber() {
		return this.regionNumber;
	}

	public void setRegionNumber(String regionNumber) {
		this.regionNumber = regionNumber;
	}
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public String getMemos() {
		return this.memos;
	}

	public void setMemos(String memos) {
		this.memos = memos;
	}
	
	public Long getRegionTypeId() {
		return this.regionTypeId;
	}

	public void setRegionTypeId(Long regionTypeId) {
		this.regionTypeId = regionTypeId;
	}
	
	public Integer getLastMark() {
		return lastMark;
	}

	public void setLastMark(Integer lastMark) {
		this.lastMark = lastMark;
	}
	
	public Integer getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}
	
	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
