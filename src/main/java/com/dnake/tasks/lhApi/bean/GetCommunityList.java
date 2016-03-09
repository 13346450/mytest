package com.dnake.tasks.lhApi.bean;

/**
 * CommunityName 小区名称<br>
 * CommunityCode 小区代码(唯一标识)<br>
 * Flag 1为默认小区0为普通小区<br>
 * Address 小区地址<br>
 * Binding 是否是绑定的小区<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月3日 下午4:18:54
 * @version 1.0
 */
public class GetCommunityList {

	private String CommunityName;
	private String CommunityCode;
	private String Flag;
	private String Address;
	private Boolean Binding;

	public String getCommunityName() {
		return CommunityName;
	}

	public void setCommunityName(String communityName) {
		CommunityName = communityName;
	}

	public String getCommunityCode() {
		return CommunityCode;
	}

	public void setCommunityCode(String communityCode) {
		CommunityCode = communityCode;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Boolean getBinding() {
		return Binding;
	}

	public void setBinding(Boolean binding) {
		Binding = binding;
	}

}
