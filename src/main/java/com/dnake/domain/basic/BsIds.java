package com.dnake.domain.basic;

/**
 * id数据映射
 */
public class BsIds {
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 对方（领航1，后续其他物业公司在扩展）
	 */
	
	private int nameType = NAME_TYPE_OF_LIHANG;
	public static final int NAME_TYPE_OF_LIHANG = 1;
	/**
	 * 我方数据ID
	 */
	private Long iId;
	/**
	 * 类型（公告1，报修2，投诉建议3，小区4）
	 */
	private int type;
	public static final int TYPE_OF_NOTIFY = 1;
	public static final int TYPE_OF_BX = 2;
	public static final int TYPE_OF_ADVICE = 3;
	public static final int TYPE_OF_COMMUNITY = 4;
	/**
	 * 对方数据Id
	 */
	private String oId;
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	
	public Long getiId() {
		return iId;
	}
	public void setiId(Long iId) {
		this.iId = iId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public int getNameType() {
		return nameType;
	}
	public void setNameType(int nameType) {
		this.nameType = nameType;
	}
	
}
