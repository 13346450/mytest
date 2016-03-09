package com.dnake.mapper.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysRole;

/**
 * 角色管理Mapper
 * 
 * @ClassName: SysRoleMapper
 * @Description:
 * @author cqx
 * @date 2013-10-8 下午4:14:28
 * 
 */
public interface SysRoleMapper {
	/**
	 * 查询所有用户
	 * 
	 * @Title: queryAll
	 * @author cqx 2013-10-8
	 * @Description:
	 * @param @return
	 * @return List<SysRole>
	 * @throws
	 */
	List<SysRole> queryAll();
	/**
	 * 根据角色名名查询角色Role
	* @Title: queryRoleByLoginName 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param loginName
	* @param @return    
	* @return SysRole    
	* @throws
	 */
	SysRole queryRoleByIdKey(Long idKey);
	/**
	 * 修改更新一个角色
	* @Title: update 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param sysRole    
	* @return void    
	* @throws
	 */
	void update(SysRole sysRole);
	/**
	 * 删除单个角色信息
	* @Title: delete 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param idKey    
	* @return void    
	* @throws
	 */
	void delete(Long idKey);
	/**
	 * 增加角色信息
	* @Title: insert 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param sysRole    
	* @return void    
	* @throws
	 */
	void insert(SysRole sysRole);

	/**
	 * 查询所有用户(分页)
	* @Title: queryPage 
	* @author tw  2013-10-29
	* @Description: 
	* @param @param page
	* @param @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysRole> queryPage(Page<SysRole> page);
	/**
	 * 
	* @Title: getRoleByGrade 
	* @author xzm  2013-12-31
	* @Description: 
	* @return    
	* @return List<SysRole>    
	* @throws
	 */
	List<SysRole> getRoleByGrade(Long grade);
	
	/**
	 * 根据登录名查询用户等级
	* @Title: queryGradeByLoginNm 
	* @author xzm  2013-12-31
	* @Description: 
	* @param loginNm
	* @return    
	* @return Long    
	* @throws
	 */
	Long queryGradeByLoginNm(String loginNm);
}
