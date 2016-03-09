/**
 * 
 */
package com.dnake.service.system.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysRole;
import com.dnake.mapper.system.SysRoleMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysRoleService;
import com.dnake.utils.Constants;
import com.dnake.utils.SessionBean;

/**
 * @ClassName: SysRoleServiceImpl
 * @Description:
 * @author cqx
 * @date 2013-10-8 下午5:21:37
 * 
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {

	@Resource
	private SysRoleMapper sysRoleMapper;

	/**
	 * 根据角色idKey查询角色信息
	 * 
	 * @Title: queryRoleByRoleName
	 * @author cqx 2013-10-10
	 * @Description:
	 * @param @param roleName
	 * @param @return
	 * @return SysRole
	 * @throws
	 */
	@Override
	public SysRole queryRoleByIdKey(Long idKey) {
		return sysRoleMapper.queryRoleByIdKey(idKey);
	}
	
	/**
	 * 
	* @Title: update 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param sysRole    
	* @return String    
	* @throws
	 */
	@Override
	public String update(SysRole sysRole) {
		sysRole.setChgDt(new Date());
		sysRole.setRoleType("1");
		sysRoleMapper.update(sysRole);
		writeLog(Constants.FUNC_MENU_NM_ROLE, Constants.FUNC_OPER_NM_UPDATE, 
				"idKey:" + sysRole.getIdKey() + ",roleName:" + sysRole.getRoleName());
		return "{\"successMsg\":\"更新角色成功！\"}";
	}
	
	@Override
	public String delete(Long idKey) {
		if(null == idKey){
			return "{\"errorMsg\":\"请选中需要删除的行！\"}";
		}
		SysRole sysRole = sysRoleMapper.queryRoleByIdKey(idKey);
		sysRoleMapper.delete(idKey);
		writeLog(Constants.FUNC_MENU_NM_ROLE, Constants.FUNC_OPER_NM_DELETE, 
				"idKey:" + sysRole.getIdKey() + ",roleName:" + sysRole.getRoleName());
		return "{\"successMsg\":\"删除角色成功！\"}";
	}
	/**
	 * 增加角色信息
	* @Title: insert 
	* @author cqx  2013-10-10
	* @Description: 
	* @param @param sysRole    
	* @return String    
	* @throws
	 */
	@Override
	public String insert(SysRole sysRole) {
		sysRole.setChgDt(new Date());
		sysRole.setRoleType("1");
		sysRoleMapper.insert(sysRole);
		writeLog(Constants.FUNC_MENU_NM_ROLE, Constants.FUNC_OPER_NM_CREATE, 
				"idKey:" + sysRole.getIdKey() + ",roleName:" + sysRole.getRoleName());
		return "{\"successMsg\":\"添加角色成功！\"}";
	}

	public List<SysRole> listPage(Page<SysRole> page){
		return sysRoleMapper.queryPage(page);
	}
	
	public List<SysRole> queryAllSysRole(){
		return sysRoleMapper.queryAll();
	}
	public List<SysRole> getRoleByGrade()
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		Long grade=sysRoleMapper.queryGradeByLoginNm(sessionBean.getLoginNm());
		return sysRoleMapper.getRoleByGrade(grade);
	}
}
