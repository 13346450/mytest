package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysDept;
import com.dnake.domain.system.SysUser;

/**
 * 用户管理mapper
* @ClassName: SysUserMapper 
* @Description: 
* @author ts 
* @date 2013-9-25 上午11:23:33 
*
 */
public interface SysUserMapper {
	
	/**
	 * 查询所有用户
	* @Title: selectAll 
	* @author ts  2013-9-25
	* @Description: 
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> queryAll();
	
	/**
	 * 查询所有用户(分页)
	 * @Title: selectAll 
	 * @author ts  2013-9-25
	 * @Description: 
	 * @return    
	 * @return List<SysUser>    
	 * @throws
	 */
	List<SysUser> queryPage(Page page);
	
	/**
	 * 按登录名查询单个用户
	* @Title: queryByLoginNm 
	* @author ts  2013-9-25
	* @Description: 
	* @param loginNm
	* @return    
	* @return SysUser    
	* @throws
	 */
	SysUser queryByLoginNm(String loginNm);
	
	/**
	 * 按登录名查询单个用户
	* @Title: queryByLoginNm 
	* @author ts  2013-9-25
	* @Description: 
	* @param loginNm
	* @return    
	* @return SysUser    
	* @throws
	 */
	SysUser mobileQueryByLoginNm(String loginNm);

	/**
	 * 按id查询单个用户
	* @Title: queryById 
	* @author ts  2013-9-25
	* @Description: 
	* @param userId
	* @return    
	* @return SysUser    
	* @throws
	 */
	SysUser queryById(Long  userId);
	
	/**
	 * 插入一个用户
	* @Title: insert 
	* @author ts  2013-9-25
	* @Description: 
	* @param sysUser
	* @return    
	* @return int    
	* @throws
	 */
	int insert(SysUser sysUser);

	/**
	 * 删除一个用户
	* @Title: delete 
	* @author ts  2013-9-25
	* @Description: 
	* @param userId
	* @return    
	* @return int    
	* @throws
	 */
	int delete(Long  userId);

	/**
	 * 修改一个用户
	* @Title: update 
	* @author ts  2013-9-25
	* @Description: 
	* @param sysUser
	* @return    
	* @return int    
	* @throws
	 */
	void update(SysUser sysUser);
	/**
	 * 按deptID查询用户
	* @Title: queryById 
	* @author xzm  2013-11-8
	* @Description: 
	* @param userId
	* @return    
	* @return SysUser    
	* @throws
	 */
	List<SysUser> queryBydeptID(Long deptId);
	void updateDeptIDByIdKey(Map<String, Object> map);
	/**
	 * 根据主键查询单个用户信息
	* @Title: querySingleDept
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return SysDept    
	* @throws
	 */
	SysUser edit(Long idKey);
	/**
	 * 修密码
	 * 
	 * @Title: modifyPwd
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param sysUser
	 * @return
	 * @return void
	 * @throws
	 */
	void modifyPwd(SysUser sysUser);
	/**
	 * 
	* @Title: queryByUserCd 
	* @author xzm  2013-11-14
	* @Description: 插入用户信息时判断用户编号是否已添加过，防止编号重复
	* @param userCd
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> queryByUserCd(String userCd);
	/**
	 * 
	* @Title: qByLoginNm 
	* @author xzm  2013-11-14
	* @Description: 插入用户信息时判断登录名是否已添加过，防止登录名重复
	* @param loginNm
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> qByLoginNm(String loginNm);
	/**
	 * 
	* @Title: queryByUserCdUp 
	* @author xzm  2013-11-14
	* @Description: 更新用户信息时判断用户编号是否已添加过，防止编号重复
	* @param sysUser
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> queryByUserCdUp(SysUser sysUser);
	/**
	 * 
	* @Title: queryByLoginNmUp 
	* @author xzm  2013-11-14
	* @Description: 更新用户信息时判断登录名是否已添加过，防止登录名重复
	* @param sysUser
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> queryByLoginNmUp(SysUser sysUser);
	/**
	 * 查询同级中排序码最大值加10
	* @Title: queryMaxOrderCdByPId 
	* @author xzm  2013-12-31
	* @Description: 
	* @param parentId
	* @return    
	* @return Long    
	* @throws
	 */
	Long queryMaxOrderCdByPId(Long parentId);
	/**
	 * 通过id查找用户
	* @title      queryList 
	* @author  chen qige     
	* @date      2014年9月12日 
	* @param userId
	* @return
	 */
	SysUser queryList(Long userId);
	/**
	 * 通过用户名称查找
	* @title      queryBycdNm 
	* @author  chen qige     
	* @date      2014年9月19日 
	* @param cdNm
	* @return
	 */
	List<SysUser> queryBycdNm(String cdNm);
	/**
	 * 根据店铺id查询用户（即商户）
	 * @param shopId
	 * @return
	 */
	SysUser queryByShopId(Long shopId);
	/**
	 * 根据小区查和登录名找该账户，区别于之前的账户名查询。
	 * @param loginNm
	 * @return
	 */
	SysUser queryByLoginName2Community(String loginNm);
	/**
	 * 按已绑定的手机号码查询
	 * @param loginNm
	 * @return
	 */
	SysUser queryByBindTel(String bindTel);
	/**
	 * 按类型查找用户
	 * @param loginNm
	 * @return
	 */
	List<SysUser> listByRoleId(long roleId);

}
