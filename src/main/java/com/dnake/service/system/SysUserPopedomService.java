package com.dnake.service.system;
import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysUserPopedom;
import com.dnake.domain.system.SysUserPopedomVO;

public interface SysUserPopedomService {

String update(SysUserPopedom sysUserPopedom);

String insert(SysUserPopedom sysUserPopedom);

String deleteMulti(String deleteIds);
/**
 * 查询该角色的权限小区id
 * @return
 *@author cqg 
 *2015年7月3日
 */
String queryCommunityIds(Integer userId);
/**
 * 分页查询
 * @title      listPage
 * @author  ts    
 * @date      2014年2月20日 
 * @param pageIndex
 * @param rows
 * @param searchParam
 * @return
 */
Page<SysUserPopedomVO> listPage(int pageIndex, int rows, SearchParam searchParam);
/**
 * 获取该用户可以添加其他账户的最高权限类型
 * @return
 *@author cqg 
 *2015年7月10日
 */
 Integer getMinType();
/**
 * 获取省级的权限范围
 * @return
 *@author cqg 
 *2015年7月11日
 */
List<SysUserPopedomVO> getProvince();
/**
 * 批量插入
 * @param depts
 * @param userId
 * @param type
 * @return
 *@author cqg 
 *2015年7月11日
 */
String insert(String depts, long userId, int type);
/**
 * 根据前端传过来的省份id获取选中的城市
 * @param depts
 * @return
 *@author cqg 
 *2015年7月11日
 */
List<SysUserPopedomVO> getCity(String depts);
/**
 * 根据前端传过来的省份id获取选中的城市
 * @param depts
 * @return
 *@author cqg 
 *2015年7月11日
 */
List<SysUserPopedomVO> getCommunity(String depts);


}
