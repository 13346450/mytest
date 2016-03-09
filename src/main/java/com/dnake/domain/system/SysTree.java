/**
 * 
 */
package com.dnake.domain.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/** 
 * 树形结构,所有要实现树形结构的类都要继承此类
 * @ClassName: SysTree 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 下午2:48:34 
 *  
 */
public class SysTree {
	
	// XML树形结构,tree节点的id = -1; <p><tree id="-1"><p>
	public static final String TREE_ID = "-1";
	// 是最低子节点 1
	public static final long LAST_MARK_YES = 1;
	// 不是最低子节点 0
	public static final long LAST_MARK_NO = 0;
	// 启用 1
	public static final long IS_USE_YES = 1;
	// 不启用 0
	public static final long IS_USE_NO = 0;
	
	private Long idKey; // 主键
	private String cdNm; // 名称
	private Integer lastMark; // 是否是最低叶子节点,1:是,0:不是.(异步加载有用到)
	private Long parentId;// 父节点Id
	private Integer orderCd;// 排序号
	private String remarks;// 备注
	private Integer isuse;// 是否启用,1:启用,0:不启用
	private Date chgDt; // 最后修改日期
	
	private List<SysTree> childList = new ArrayList<SysTree>();// 子节点
	
	
	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
		if(!("".equals(cdNm)||cdNm==null)){
			this.cdNm = cdNm.trim();
		}
	}

	public Integer getLastMark() {
		return lastMark;
	}

	public void setLastMark(Integer lastMark) {
		this.lastMark = lastMark;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsuse() {
		return isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public SysTree() {
		super();
	}
	
	public SysTree(Long idKey, String cdNm) {
		super();
		this.idKey = idKey;
		this.cdNm = cdNm;
	}
	
	public SysTree(Long idKey, String cdNm, Long parentId) {
		super();
		this.idKey = idKey;
		this.cdNm = cdNm;
		this.parentId = parentId;
	}
	
	public List<SysTree> getChildList() {
		return childList;
	}

	public void setChildList(List<SysTree> childList) {
		this.childList = childList;
	}
	
}
