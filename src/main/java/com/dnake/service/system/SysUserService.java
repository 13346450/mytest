package com.dnake.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysUser;

public interface SysUserService {
	/**
	 * 查询所有用户
	* @Title: list 
	* @author ts  2013-9-25
	* @Description: 
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> listAll();
	
	/**
	 * 查询所有用户
	 * @Title: list 
	 * @author ts  2013-9-25
	 * @Description: 
	 * @return    
	 * @return List<SysUser>    
	 * @throws
	 */
	Page<SysUser> listPage(int pageIndex, int rows,Long deptID);

	/**
	 * 检查登录
	* @Title: checkLogin 
	* @author ts  2013-9-25
	* @Description: 
	* @param loginNm
	* @return    
	* @return SysUser    
	* @throws
	 */
	SysUser checkLogin(String loginNm);
	/**
	 * 手机端检查登录
	* @Title: checkLogin 
	* @author ts  2013-9-25
	* @Description: 
	* @param loginNm
	* @return    
	* @return SysUser    
	* @throws
	 */
	SysUser mobileCheckLogin(String loginNm);
	/**
	 * 编辑一个用户
	* @Title: edit 
	* @author ts  2013-9-25
	* @Description: 
	* @param userId
	* @return    
	* @return SysUser    
	* @throws
	 */
	SysUser edit(Long userId);
	SysUser queryList(Long userId);
	/**
	 * 删除一个用户
	* @Title: delete 
	* @author ts  2013-11-11
	* @Description: 
	* @param userId    
	* @return void    
	* @throws
	 */
	String delete(Long userId);
	
	/**
	 * 修改一个用户
	* @Title: update 
	* @author xzm  2013-11-1
	* @Description: 
	* @param sysUser    
	* @return string    
	* @throws
	 */
	String update(SysUser sysUser);
	/**
	 * 
	* @Title: queryBydeptID 
	* @author xzm  2013-11-13
	* @Description: 根据部门ID查询信息
	* @param deptId
	* @return    
	* @return List<SysUser>    
	* @throws
	 */
	List<SysUser> queryBydeptID(Long deptId);
	/**
	 * 根据小区查找商家id
	 * @param community
	 * @return
	 *@author cqg 
	 *2015年9月24日
	 */
	List<SysUser> querySalesBydeptID(Long community);
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
	 * 保存新用户
	* @Title: insert 
	* @author xzm  2013-11-11
	* @Description: 
	* @param sysUser    
	* @return void    
	* @throws
	 */
	String insert(SysUser sysUser);
	/**
	 * 
	* @Title: modifyPwd 
	* @author xzm  2013-11-13
	* @Description: 修改密码
	* @param userPwd
	* @param newPwd
	* @param surePwd
	* @return    
	* @return String    
	* @throws
	 */
	String modifyPwd(String userPwd,String newPwd,String surePwd,String types);
	/**
	 * 
	* @Title: creatSysDeptOrUserTree 
	* @author xzm  2013-11-13
	* @Description: 加载左则树节点
	* @param nodeId
	* @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	DhtmlxTreeModel creatSysDeptOrUserTree(Long nodeId);
	/**
	 * 
	* @Title: initPwd 
	* @author xzm  2013-11-13
	* @Description: 在用户列表上操作初始化密码
	* @param userId
	* @return    
	* @return String    
	* @throws
	 */
	String initPwd(Long userId);
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
	 * 手机端注册用户
	* @title      mobileInsertUser 
	* @author  chen qige     
	* @date      2014年9月10日 
	* @param userName
	* @param PassWord
	* @param CommunityID
	* @return
	 */
	String mobileInsertUser(MobileParms parms);
	/**
	 * 手机端修改密码
	* @title      mobileModifyPassWord 
	* @author  chen qige     
	* @date      2014年9月12日 
	* @param sysUser
	* @return
	 */
	String mobileModifyPassWord(String newPassWord,String oldPassWord);
	/**
	 * 手机端修改手机入口
	* @title      mobileUpdateUser 
	* @author  chen qige     
	* @date      2014年9月12日 
	* @param communityId
	* @return
	 */
	String mobileUpdateUser(MobileParms parms);
	/**
	 * 修改设备码
	* @title      mobileUpdateUserCd 
	* @author  chen qige     
	* @date      2014年10月24日 
	*  @param user
	 */
	void mobileUpdateUserCd(SysUser sysUser);

	/**
	 * 手机端找回密码
	 * @param parms
	 * @return
	 */
	String mobileGetPassword(MobileParms parms);
	
	/**
	 * 手机端绑定电话
	 * @param parms
	 * @return
	 */
	String mobileBindTel(MobileParms parms);
	
	/**
	 * 查询登录名，根据社区ID关联查询
	 * @param loginNm
	 * @return
	 */
	SysUser queryByLoginName2Community(String loginNm);
	/**
	 * 按手机号码登入到系统
	 * @return
	 */
	String cmdLoginByPhoneNumber(HttpServletRequest request, SysUser user);

}
