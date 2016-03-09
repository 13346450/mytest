package com.dnake.domain.system;

import java.util.Date;



import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 角色的domain
 * 
 * @ClassName: SysRole
 * @Description:
 * @author cqx
 * @date 2013-10-8 下午5:02:26
 * 
 */

public class SysRole implements java.io.Serializable {

	private static final long serialVersionUID = 7532018307929787073L;
	
	private Long idKey;// 主键
	//键值对文件在message_zh_CN.properties
	@NotEmpty(message = "{roleName.not.empty}")
	@Length( message="{user.name.length.error}",min=0,max=40)
	private String roleName;// 角色名字
	
	@Length( message="{user.name.length.error}",min=0,max=1)
	private String roleType;// 角色类型
    
    @Length( message="{user.name.length.error",min=0,max=100)
	private String roleRemark;// 备注

	private Date chgDt;// 日期

	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRoleRemark() {
		return roleRemark;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getChgDt() {
		return chgDt;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}