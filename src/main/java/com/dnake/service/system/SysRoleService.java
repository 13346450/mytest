/**
 * 
 */
package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysRole;

/** 
 * @ClassName: SysRoleService 
 * @Description: 
 * @author cqx
 * @date 2013-10-8 下午5:19:18 
 *  
 */
public interface SysRoleService {
	/**
	 * 根据角色名查询角色信息
	* @Title: queryRoleByRoleName 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param roleName
	* @param @return    
	* @return SysRole    
	* @throws
	 */
	SysRole queryRoleByIdKey(Long idKey);
	/**
	 * 更新一个角色信息
	* @Title: update 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param sysRole    
	* @return String    
	* @throws
	 */
	String update(SysRole sysRole);
	
	/**
	 * 删除单个角色信息
	* @Title: delete 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return String    
	* @throws
	 */
	String delete(Long idKey);
	
	/**
	 * 增加角色信息
	* @Title: insert 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param sysRole    
	* @return String    
	* @throws
	 */
	String insert(SysRole sysRole);

	/**
	 * 分页查询所有
	* @Title: listPage 
	* @author tw  2013-10-29
	* @Description: 
	* @param @param page
	* @param @return    
	* @return List<SysRole>    
	* @throws
	 */
	List<SysRole> listPage(Page<SysRole> page);
	
	/**
	 * 查询所有角色
	* @Title: queryAllSysRole 
	* @author tw  2013-11-13
	* @Description: 
	* @param @return    
	* @return List<SysRole>    
	* @throws
	 */
	List<SysRole> queryAllSysRole();
	/**
	 * 根据用户等级查询该等级以下的所有角色
	* @Title: getRoleByGrade 
	* @author xzm  2013-12-31
	* @Description: 
	* @return    
	* @return List<SysRole>    
	* @throws
	 */
	List<SysRole> getRoleByGrade();
}
