/**
 * 
 */
package com.dnake.domain.system;
/** 
 * @ClassName: SysMenu 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 下午3:54:08 
 *  
 */
public class SysMenu extends SysTree implements java.io.Serializable {
	
	private static final long serialVersionUID = 7532018307929787073L;
	
	private String iconAddress;// 图片地址
	private String linkAddress;// 链接地址
	private String menuMarker;// 唯一标示
	
	public String getIconAddress() {
		return iconAddress;
	}
	public void setIconAddress(String iconAddress) {
		this.iconAddress = iconAddress;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getMenuMarker() {
		return menuMarker;
	}
	public void setMenuMarker(String menuMarker) {
		this.menuMarker = menuMarker;
	}
	

}
