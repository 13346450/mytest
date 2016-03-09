package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/** 
 * 参数设置的domain
 * @ClassName: SysParms 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 上午10:43:57 
 *  
 */

public class SysParms implements java.io.Serializable {

	private static final long serialVersionUID = 7532018307929787073L;
	
	private Long idKey;// 主键
	private String parmKey;// 键
	private String parmValue;// 值
	private Date chgDt;// 日期	
	
	private Long typeId;// 类型ID		
	private String typeName;// 类型名称
	
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getParmKey() {
		return parmKey;
	}
	public void setParmKey(String parmKey) {
		this.parmKey = parmKey;
	}
	public String getParmValue() {
		return parmValue;
	}
	public void setParmValue(String parmValue) {
		this.parmValue = parmValue;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getChgDt() {
		return chgDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}