package com.dnake.domain.business;

/**
 * 外链类，负责便民服务的外链属性
 * @ClassName BizChain
 * @author zgj
 * 2014年9月1日 上午11:34:38
 */
public class BizChain 
{
	private Long idKey;  //链接主键
	private String chainName; //链接名称
	private String linksUrl; //链接地址
	private String imagesUrl; //图片地址
	private Long communityId;//小区id
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
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
	
}
