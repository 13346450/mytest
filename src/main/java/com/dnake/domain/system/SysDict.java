/**
 * 
 */
package com.dnake.domain.system;

import java.util.Date;

/**
 * 数据字典domain
* @ClassName: SysDict 
* @Description: 
* @author cqx
* @date 2013-10-28 上午11:27:10 
*
 */
public class SysDict  implements java.io.Serializable {
	
	private static final long serialVersionUID = 7532018307929787073L;
	
	private Long idKey;    //主键
	private String dictTypeId; //类型id
	private String dictTypeNm; //类型名
	private String dictValue;  //值
	private String remark;  //备注
	private Date chgDt;  //日期
	
	public Long getIdKey() {
		return idKey;
	}



	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}



	public String getDictTypeId() {
		return dictTypeId;
	}



	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}



	public String getDictTypeNm() {
		return dictTypeNm;
	}



	public void setDictTypeNm(String dictTypeNm) {
		this.dictTypeNm = dictTypeNm;
	}



	public String getDictValue() {
		return dictValue;
	}



	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Date getChgDt() {
		return chgDt;
	}



	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}	
}
