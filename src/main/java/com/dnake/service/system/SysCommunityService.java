package com.dnake.service.system;

import java.util.List;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.domain.system.SysDept;

public interface SysCommunityService {
	/**
	 * 查询所有小区
	* @Title: list 
	* @author ts  2013-9-25
	* @Description: 
	* @return    
	* @return List<SysCommunity>    
	* @throws
	 */
	List<SysCommunity> listAll();
	
	/**
	 * 查询所有小区
	 * @Title: list 
	 * @author ts  2013-9-25
	 * @Description: 
	 * @return    
	 * @return List<SysCommunity>    
	 * @throws
	 */
	Page<SysCommunity> listPage(int pageIndex, int rows,Long deptID);

//	/**
//	 * 检查登录
//	* @Title: checkLogin 
//	* @author ts  2013-9-25
//	* @Description: 
//	* @param loginNm
//	* @return    
//	* @return SysCommunity    
//	* @throws
//	 */
//	SysCommunity checkLogin(String loginNm);
	
	/**
	 * 编辑一个小区
	* @Title: edit 
	* @author ts  2013-9-25
	* @Description: 
	* @param userId
	* @return    
	* @return SysCommunity    
	* @throws
	 */
	SysCommunity edit(Long userId);
	SysCommunity queryList(Long userId);
	/**
	 * 删除一个小区
	* @Title: delete 
	* @author ts  2013-11-11
	* @Description: 
	* @param userId    
	* @return void    
	* @throws
	 */
	String delete(Long userId);
	
	/**
	 * 修改一个小区
	* @Title: update 
	* @author xzm  2013-11-1
	* @Description: 
	* @param sysCommunity    
	* @return string    
	* @throws
	 */
	String update(SysCommunity sysCommunity);
	/**
	 * 
	* @Title: queryBydeptID 
	* @author xzm  2013-11-13
	* @Description: 根据部门ID查询信息
	* @param deptId
	* @return    
	* @return List<SysCommunity>    
	* @throws
	 */
	List<SysCommunity> queryBydeptID(Long deptId);
	/**
	 * 
	* @Title: updateDeptIDByIdKey 
	* @author xzm  2013-11-13
	* @Description: 根据主键KEY更新部门ID
	* @param idKey
	* @param oldParentId
	* @param targetId
	* @return    
	* @return String    
	* @throws
	 */
	String updateDeptIDByIdKey(Long idKey, Long oldParentId, Long targetId);
	/**
	 * 保存新小区
	* @Title: insert 
	* @author xzm  2013-11-11
	* @Description: 
	* @param sysCommunity    
	* @return void    
	* @throws
	 */
	String insert(SysCommunity sysCommunity);
	/**
	 * 
//	* @Title: modifyPwd 
//	* @author xzm  2013-11-13
//	* @Description: 修改密码
//	* @param userPwd
//	* @param newPwd
//	* @param surePwd
//	* @return    
//	* @return String    
//	* @throws
//	 */
//	String modifyPwd(String userPwd,String newPwd,String surePwd,String types);
	/**
	 * 
	* @Title: creatSysDeptOrCommunityTree 
	* @author xzm  2013-11-13
	* @Description: 加载左则树节点
	* @param nodeId
	* @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	DhtmlxTreeModel creatSysDeptOrCommunityTree(Long nodeId);
//	/**
//	 * 
//	* @Title: initPwd 
//	* @author xzm  2013-11-13
//	* @Description: 在小区列表上操作初始化密码
//	* @param userId
//	* @return    
//	* @return String    
//	* @throws
//	 */
//	String initPwd(Long userId);
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
	 * 判断是否由子节点
	* @title      haveChildren 
	* @author  chen qige     
	* @date      2014年9月4日 
	* @param parentId
	* @return
	 */
	boolean haveChildren(Long parentId);
	
	/**
	 * 根据小区id查找小区的详细信息
	* @title      queryCommunityVOById 
	* @author  chen qige     
	* @date      2014年9月22日 
	* @param Id
	* @return
	 */
	SysCommunityVO queryCommunityVOById(Long Id);
	/**
	 * 手机客户端请求，返回json
	* @title      mobileQueryByDeptId 
	* @author  chen qige     
	* @date      2014年9月9日 
	* @param parentId
	* @return
	 */
	String mobileQueryByDeptId(Long parentId);
	/**
	 * 获取整棵权限树
	 * @param nodeId
	 * @return
	 *@author cqg 
	 *2015年7月3日
	 */
	DhtmlxTreeModel creatSysPopedomTree(Long nodeId);
	/**
	 * 获取该用户权限的小区
	 * @param userId
	 * @return
	 *@author cqg 
	 *2015年7月7日
	 */
	List<SysCommunity> queryPopedomCommunity();
	/**
	 * 获取小区权限用逗号隔开，例如
	 * @return
	 *@author cqg 
	 *2015年7月13日
	 */
	String queryPopedomCommunityString(Long userId);
}
