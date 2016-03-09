package com.dnake.domain.common;

import java.sql.Timestamp;

/**
 * 查询参数
* @ClassName: SearchParam 
* @Description: 
* @author ts
* @date 2013-11-26 下午4:49:20 
*
 */
public class SearchParam {

	private Long idKey;
	private String startDatetime;		//开始时间
	private String endDatetime;		//结束时间
	private String deptId;				//单位id
	private Long dictId;					//字典id
	private String dataTime;			//数据时间
	private String chgDt;					//修改时间
	private String sort; 				 	//排序的字段
	private String order;  				//排序的方式asc和desc
	private Integer advStatus; 		//广告状态
	private Integer appStatus; 		//广告状态
	private Integer infoStatus;		//物业信息状态
	private Integer repairStatus;	//维修状态
	
	/*商品管理参数*/
	private Long communityId; //社区id
	private String communityName;//社区名字
	private Long goodsTypeId;//商品类别id
	private String goodsType; //商品类别名称
	private String  deleteIds;  				//要删除的ids
	private Integer orderStatus;//订单状态
	private Long orderId;//订单Id
	private Timestamp autoPutOnTime;// 自动上架时间
	
	//商铺管理参数
	private  Integer status;//状态
	private String userName;//用户名
	private String shopName;//商铺名
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getDeleteIds() {
		return deleteIds;
	}
	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}
	public Long getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public Integer getInfoStatus()
	{
		return infoStatus;
	}
	public void setInfoStatus(Integer infoStatus)
	{
		this.infoStatus = infoStatus;
	}	
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}
	public String getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Long getDictId() {
		return dictId;
	}
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getAdvStatus() {
		return advStatus;
	}
	public void setAdvStatus(Integer advStatus) {
		this.advStatus = advStatus;
	}
	public Integer getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}
	
	public String getChgDt() {
		return chgDt;
	}
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String type) {
		this.goodsType = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getRepairStatus()
	{
		return repairStatus;
	}
	public void setRepairStatus(Integer repairStatus)
	{
		this.repairStatus = repairStatus;
	}
	public Timestamp getAutoPutOnTime() {
		return autoPutOnTime;
	}
	public void setAutoPutOnTime(Timestamp autoPutOnTime) {
		this.autoPutOnTime = autoPutOnTime;
	}
	
}
