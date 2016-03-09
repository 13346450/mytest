/**
 * Project Name:SmartHomeWeb
 * File Name:MobileParms.java
 * Package Name:com.dnake.domain.common
 * Date:2014年2月21日下午5:02:43
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.dnake.domain.common;

import java.sql.Timestamp;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.JsonDateTimeSerializer;

/**
 * 手机端参数提交
 * ClassName:MobileParms <br/>
 * Date:     2014年2月21日 下午5:02:43 <br/>
 * @author   ts
 * @version  
 * @see 	 
 */
public class MobileParms {
	
	private Integer cmd;				//命令，用来告诉服务器需要做什么
	//安卓端暂时不能支持
	private MultipartFile fileImage1;
	private MultipartFile fileImage2;
	private MultipartFile fileImage3;
	private MultipartFile fileImage4;
	private MultipartFile fileImage5;
	private String  userCdNm;	//用户名
	private String  loginName;//登录名，即为账号
	private String  userPasswd; 	//密码
	private Long roleId;//角色id 8为商户，7为用户
	private String telephone;
	private String addr;
	private String userCd;//用户唯一识别码，用于消息推送
	private String building; // 楼栋号
	private String unit;// 单元号
	private String floor;// 楼层号
	private String room;// 房号
	
	/**/
	private Long id; //id 标示 爱尚社区第一版的idKey
	private Long idKey;	//id标志， 爱尚社区第二版的idkey
	private Long proInfoType;//物业信息Id
	private Long noteType;	//帖子类别
	private Long noteId;    //帖子id
	private Long communityId; //用于提交注册信息的小区名
	private Long modelId;//用于标示商城大类
	private Integer page;//页码
	private Integer count;//每页的条数
	private String newPassWord;//新密码
	private String oldPassWord;//旧密码
	private String name;//名称
	private Integer status;//状态通用
	private String deleteIds;	//多条删除的ID
	/**/
	
	/*商品订单提交*/
	private String idString;//商品id字符串，如1-32-43
	private String countString;//商品数量字符串，如1-1-2
	private String orderTelephone;
	private String orderAddr;
	private String receiver;//收货人
	private Integer orderStatus;
	private Timestamp deliveryTime;//送货时间
	//通用的
	private String ids;//通用的ids，以逗号隔开
	private Integer type;//类型通用的
	private String data;//通用的数据
	private String remarks;//备注
	private String callback;//jsonp回调参数
	private String token;//用令牌登录
	/*商品提交字段*/
	private String goodsName;
	private Long goodsTypeId;//商品的类型id
	private Double unitPrice;	//原价
	private Double discountPrice;	//现价
	private String goodsNote;//商品描述
	private Integer goodsStatus;//商品状态id
	private Integer goodsOrder;//商品排序码
	private Long imagesId;
	private Long shopId;//商品id
	private Integer quantity;//商品数量
	private Timestamp autoPutOnTime;//自动上架时间
	//手机端地址修改
	private Long provinceId;
	private Long cityId;
	private Long districtId;
	private String addrDetail;
	private String phone;
	private String postalcode;
	private String userName;
	private Integer isDefault; 
	
	//用户评论手机参数
	private String content;
	private Integer goodsScore;
	private Long parentId;
	private Long goodsId;
	private Long orderId;
	private Integer commentType;
	private Integer isNullUser;
	private Integer isReply;
	private Integer commentLevel;//好评差评

	//活动促销尝试
	private String shopIds;
	private	Timestamp startDt;
	private Timestamp endDt;
	private Double minSum;
	private String gift;
	
	//报表参数
	private String dateTime;	//每日报表查询时间
	
	//手机端短信验证
	private String zone;	//区号
	private String code;	//验证码
	
	private Integer appType;	//APP版本 1用户版 2商户版
	private Integer sortType;	//排序类型 1按照销售总价排序  2按照销量进行排序 3按照名称进行排序
	
	//图片属性
	private String imageSize;//图片大小 格式：200_250
	public Integer getIsDefault() {
		return isDefault;
	}
	
	//物业报修参数
	private String title;
	private Integer repairStatus;
	private String contacts;
	private String contactsTel;
	private String contactsAddr;
	
	
	//领航接口参数
	private String communityCode;
	private String pageIndex;
	private String pageSize;
	private String ID;
	
	//手机论坛参数
	private String noteContent;
	private String noteTitle;
	private String replyContent;
	
	//手机退货款参数
	private Integer refundType;		//退款类型 1.我需要退款  2.我需要退货
	private Integer refundReason;	//退款原因  1收到商品破损  2商品错发  3商品需要维修  4商品与描述不符  5商品质量问题  6未按规定时间发货
	private Double refundMoney;		//退款金额
	private String refundExplain;	//退款说明
	
	//返回订单号 支付宝用
	private String orderIds;


	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	
	
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
		System.out.println(deliveryTime);
	}


	public Long getShopId() {
		return shopId;
	}


	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}


	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public Long getImagesId() {
		return imagesId;
	}

	public void setImagesId(Long imagesId) {
		this.imagesId = imagesId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(Integer goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public String getOrderTelephone() {
		return orderTelephone;
	}

	public void setOrderTelephone(String orderTelephone) {
		this.orderTelephone = orderTelephone;
	}

	public String getOrderAddr() {
		return orderAddr;
	}

	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getGoodsNote() {
		return goodsNote;
	}

	public void setGoodsNote(String goodsNote) {
		this.goodsNote = goodsNote;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public String getCountString() {
		return countString;
	}

	public void setCountString(String countString) {
		this.countString = countString;
	}

	public Integer getPage() {
		return page;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public Integer getCmd() {
		return cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}
	public String getUserCdNm() {
		return userCdNm;
	}

	public void setUserCdNm(String userCdNm) {
		this.userCdNm = userCdNm;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProInfoType()
	{
		return proInfoType;
	}

	public void setProInfoType(Long proInfoType)
	{
		this.proInfoType = proInfoType;
	}

	public Long getNoteType()
	{
		return noteType;
	}

	public void setNoteType(Long noteType)
	{
		this.noteType = noteType;
	}

	public Long getNoteId()
	{
		return noteId;
	}

	public void setNoteId(Long noteId)
	{
		this.noteId = noteId;
	}

	public Long getProvinceId()
	{
		return provinceId;
	}


	public void setProvinceId(Long provinceId)
	{
		this.provinceId = provinceId;
	}


	public Long getCityId()
	{
		return cityId;
	}


	public void setCityId(Long cityId)
	{
		this.cityId = cityId;
	}


	public Long getDistrictId()
	{
		return districtId;
	}


	public void setDistrictId(Long districtId)
	{
		this.districtId = districtId;
	}


	public String getAddrDetail()
	{
		return addrDetail;
	}


	public void setAddrDetail(String addrDetail)
	{
		this.addrDetail = addrDetail;
	}


	public String getPhone()
	{
		return phone;
	}


	public void setPhone(String phone)
	{
		this.phone = phone;
	}


	public String getPostalcode()
	{
		return postalcode;
	}


	public void setPostalcode(String postalcode)
	{
		this.postalcode = postalcode;
	}


	public String getUserName()
	{
		return userName;
	}


	public void setUserName(String userName)
	{
		this.userName = userName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContent()
	{
		return content;
	}


	public void setContent(String content)
	{
		this.content = content;
	}

	public Long getParentId()
	{
		return parentId;
	}


	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}


	public Long getGoodsId()
	{
		return goodsId;
	}


	public void setGoodsId(Long goodsId)
	{
		this.goodsId = goodsId;
	}


	public Long getOrderId()
	{
		return orderId;
	}


	public void setOrderId(Long orderId)
	{
		this.orderId = orderId;
	}

	public Long getIdKey()
	{
		return idKey;
	}


	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}


	public Integer getGoodsScore()
	{
		return goodsScore;
	}


	public void setGoodsScore(Integer goodsScore)
	{
		this.goodsScore = goodsScore;
	}


	public Integer getCommentType()
	{
		return commentType;
	}


	public void setCommentType(Integer commentType)
	{
		this.commentType = commentType;
	}


	public Integer getIsNullUser()
	{
		return isNullUser;
	}


	public void setIsNullUser(Integer isNullUser)
	{
		this.isNullUser = isNullUser;
	}


	public Integer getIsReply()
	{
		return isReply;
	}


	public void setIsReply(Integer isReply)
	{
		this.isReply = isReply;
	}


	public Integer getCommentLevel()
	{
		return commentLevel;
	}


	public void setCommentLevel(Integer commentLevel)
	{
		this.commentLevel = commentLevel;
	}

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getShopIds() {
		return shopIds;
	}


	public void setShopIds(String shopIds) {
		this.shopIds = shopIds;
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


	public String getGift() {
		return gift;
	}


	public void setGift(String gift) {
		this.gift = gift;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getZone()
	{
		return zone;
	}


	public void setZone(String zone)
	{
		this.zone = zone;
	}


	public String getCode()
	{
		return code;
	}


	public void setCode(String code)
	{
		this.code = code;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getDiscountPrice()
	{
		return discountPrice;
	}


	public void setDiscountPrice(Double discountPrice)
	{
		this.discountPrice = discountPrice;
	}


	public MultipartFile getFileImage1()
	{
		return fileImage1;
	}


	public void setFileImage1(MultipartFile fileImage1)
	{
		this.fileImage1 = fileImage1;
	}


	public MultipartFile getFileImage2()
	{
		return fileImage2;
	}


	public void setFileImage2(MultipartFile fileImage2)
	{
		this.fileImage2 = fileImage2;
	}


	public MultipartFile getFileImage3()
	{
		return fileImage3;
	}


	public void setFileImage3(MultipartFile fileImage3)
	{
		this.fileImage3 = fileImage3;
	}


	public MultipartFile getFileImage5()
	{
		return fileImage5;
	}


	public void setFileImage5(MultipartFile fileImage5)
	{
		this.fileImage5 = fileImage5;
	}


	public MultipartFile getFileImage4()
	{
		return fileImage4;
	}


	public void setFileImage4(MultipartFile fileImage4)
	{
		this.fileImage4 = fileImage4;
	}


	public String getDeleteIds()
	{
		return deleteIds;
	}


	public void setDeleteIds(String deleteIds)
	{
		this.deleteIds = deleteIds;
	}


	public String getImageSize() {
		return imageSize;
	}


	public void setImageSize(String size) {
		this.imageSize = size;
	}


	public String getDateTime()
	{
		return dateTime;
	}


	public void setDateTime(String dateTime)
	{
		this.dateTime = dateTime;
	}


	public Integer getAppType()
	{
		return appType;
	}


	public void setAppType(Integer appType)
	{
		this.appType = appType;
	}


	public Integer getSortType()
	{
		return sortType;
	}


	public void setSortType(Integer sortType)
	{
		this.sortType = sortType;
	}


	public Integer getRepairStatus()
	{
		return repairStatus;
	}


	public void setRepairStatus(Integer repairStatus)
	{
		this.repairStatus = repairStatus;
	}


	public String getContacts()
	{
		return contacts;
	}


	public void setContacts(String contacts)
	{
		this.contacts = contacts;
	}


	public String getContactsTel()
	{
		return contactsTel;
	}


	public void setContactsTel(String contactsTel)
	{
		this.contactsTel = contactsTel;
	}


	public String getContactsAddr()
	{
		return contactsAddr;
	}


	public void setContactsAddr(String contactsAddr)
	{
		this.contactsAddr = contactsAddr;
	}


	public String getTitle()
	{
		return title;
	}


	public void setTitle(String title)
	{
		this.title = title;
	}


	public String getCommunityCode()
	{
		return communityCode;
	}


	public void setCommunityCode(String communityCode)
	{
		this.communityCode = communityCode;
	}


	public String getPageIndex()
	{
		return pageIndex;
	}


	public void setPageIndex(String pageIndex)
	{
		this.pageIndex = pageIndex;
	}


	public String getPageSize()
	{
		return pageSize;
	}


	public void setPageSize(String pageSize)
	{
		this.pageSize = pageSize;
	}


	public String getID()
	{
		return ID;
	}


	public void setID(String iD)
	{
		ID = iD;
	}


	public String getNoteContent()
	{
		return noteContent;
	}


	public void setNoteContent(String noteContent)
	{
		this.noteContent = noteContent;
	}


	public String getNoteTitle()
	{
		return noteTitle;
	}


	public void setNoteTitle(String noteTitle)
	{
		this.noteTitle = noteTitle;
	}


	public String getReplyContent()
	{
		return replyContent;
	}


	public void setReplyContent(String replyContent)
	{
		this.replyContent = replyContent;
	}


	public String getBuilding()
	{
		return building;
	}


	public void setBuilding(String building)
	{
		this.building = building;
	}


	public String getUnit()
	{
		return unit;
	}


	public void setUnit(String unit)
	{
		this.unit = unit;
	}


	public String getFloor()
	{
		return floor;
	}


	public void setFloor(String floor)
	{
		this.floor = floor;
	}


	public String getRoom()
	{
		return room;
	}


	public void setRoom(String room)
	{
		this.room = room;
	}

	public Integer getRefundType()
	{
		return refundType;
	}


	public void setRefundType(Integer refundType)
	{
		this.refundType = refundType;
	}


	public Integer getRefundReason()
	{
		return refundReason;
	}


	public void setRefundReason(Integer refundReason)
	{
		this.refundReason = refundReason;
	}
	
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public Timestamp getAutoPutOnTime() {
		return autoPutOnTime;
	}


	public void setAutoPutOnTime(Timestamp autoPutOnTime) {
		this.autoPutOnTime = autoPutOnTime;
	}


	public Double getRefundMoney()
	{
		return refundMoney;
	}


	public void setRefundMoney(Double refundMoney)
	{
		this.refundMoney = refundMoney;
	}


	public String getRefundExplain()
	{
		return refundExplain;
	}


	public void setRefundExplain(String refundExplain)
	{
		this.refundExplain = refundExplain;
	}


	public String getOrderIds()
	{
		return orderIds;
	}


	public void setOrderIds(String orderIds)
	{
		this.orderIds = orderIds;
	}


	public String getCallback() {
		return callback;
	}


	public void setCallback(String callback) {
		this.callback = callback;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


}

