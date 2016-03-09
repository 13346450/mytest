/**
 * 
 */
package com.dnake.domain.system;

/** 
 * 部门管理信息
 * @ClassName: SysDept 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 下午2:57:09 
 *  
 */
public class SysDept extends SysTree implements java.io.Serializable {
	
	private static final long serialVersionUID = 7532018307929787073L;
	
	private String deptCode;// 部门编号
	private Integer isBranch; // 是否为小区
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public Integer getIsBranch() {
		return isBranch;
	}
	public void setIsBranch(Integer isBranch) {
		this.isBranch = isBranch;
	}
	
	
}
