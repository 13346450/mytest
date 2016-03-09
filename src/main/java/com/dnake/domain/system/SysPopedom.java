/**
 * 
 */
package com.dnake.domain.system;

import java.util.Date;


/** 
 * 权限管理的domain
 * @ClassName: SysPopedom 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 上午10:38:10 
 *  
 */
public class SysPopedom implements java.io.Serializable {
	
	private static final long serialVersionUID = 7532018307929787073L;
	/**
	 * 授权允许
	 */
	public static final int ACL_YES = 1;
	
	/**
	 * 授权不允许
	 */
	public static final int ACL_NO = 0;
	
	private Long idKey;		//主键
	private Long roleId; //角色id
	private Long menuId; //菜单id
	private Long popedom; //授权状态,用其后四位(bit)来标识CRUD操作 
	private Date chgDt; //最后修改日期  
	
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getPopedom() {
		return popedom;
	}
	public void setPopedom(Long popedom) {
		this.popedom = popedom;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	/**
	 * acl实例跟主体和资源关联
	 * 针对此实例进行授权：某种操作是否允许
	 * @param permission 只可以取值0,1,2,3
	 * @param yes true表示允许，false表示不允许
	 */
	public void setPermission(int permission, boolean yes) {
		/*
		 * 整型 1 可以表示成二进制 0001 
		 * permission取值 0,1,2,3表示CRUD的操作
		 * 当permission为0时,将 0001 左移 0 位 -> 0001
		 * 当permission为1时,将 0001 左移 1 位 -> 0010
		 * 当permission为2时,将 0001 左移 2 位 -> 0100
		 * 当permission为3时,将 0001 左移 3 位 -> 1000
		 * 如果yes为true的话, 用 aclState 或 移动后的tmp 如: 0000 | 0001 = 0001
		 * 如果crud都允许的话,那么最终 aclState 会等于15, 即 1111 , 所以有15种组合
		 * 如果yes为false的话,先将tmp取反,再用 aclState 与 取反后的tmp 如: 0101 & 1101 = 0101  
		 */
		int tmp = 1;
		tmp = tmp << permission;
		
		if(popedom == null) popedom = 0l;
		
		if(yes) {
			popedom |= tmp;
		} else {
			popedom &= ~tmp;
		}
	}
	
	/**
	 * 获得ACL授权
	 * @param permission C/R/U/D权限
	 * @return 授权标识：允许/不允许/不确定
	 */
	public int getPermission(int permission) {
		/*
		 * 如果返回ACL_YES,表示传入的授权是允许的
		 * 如果返回ACL_NO,表示传入的授权是不允许的
		 * 如果返回ACL_NEUTRAL,表示传入的授权是不确定的
		 */
		//如果继承，则返回未定义的授权信息
		
		int tmp = 1;
		
		tmp = tmp << permission;
		
		tmp &= (popedom ==null ? 0 : popedom);
		
		if(tmp != 0) {
			return ACL_YES;
		}
		
		return ACL_NO;
	}

}
