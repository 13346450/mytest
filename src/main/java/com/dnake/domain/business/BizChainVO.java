package com.dnake.domain.business;

/**
 * 外链类的升级版
 * @ClassName BizChainVO
 * @author zgj
 * 2014年9月10日 上午9:47:35
 */
public class BizChainVO
{
	private Long idKey;  //链接主键
	private String chainName; //链接名称
	private String linksUrl; //链接地址
	private String imagesUrl; //图片地址
	private Long communityId;//小区id
	
	private String publishNm; //发布人姓名
	private Long clicksTotal;//点击总数
	private String communityName;//小区名称
	
	public Long getClicksTotal()
	{
		return clicksTotal;
	}

	public void setClicksTotal(Long clicksTotal)
	{
		this.clicksTotal = clicksTotal;
	}

	public Long getIdKey()
	{
		return idKey;
	}

	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	public String getChainName()
	{
		return chainName;
	}

	public void setChainName(String chainName)
	{
		this.chainName = chainName;
	}

	public String getLinksUrl()
	{
		return linksUrl;
	}

	public void setLinksUrl(String linksUrl)
	{
		this.linksUrl = linksUrl;
	}

	public String getImagesUrl()
	{
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl)
	{
		this.imagesUrl = imagesUrl;
	}

	public String getPublishNm()
	{
		return publishNm;
	}

	public void setPublishNm(String publishNm)
	{
		this.publishNm = publishNm;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
}
