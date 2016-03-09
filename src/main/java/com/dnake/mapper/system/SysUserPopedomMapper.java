package com.dnake.mapper.system;
import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysUserPopedom;
import com.dnake.domain.system.SysUserPopedomVO;
public interface SysUserPopedomMapper {
void insert(SysUserPopedom sysUserPopedom);

void delete(long idKey);

void update(SysUserPopedom sysUserPopedom);

SysUserPopedom queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<SysUserPopedom> query(Map<String, Object> map);

void insertMulti(List<SysUserPopedom> list);

/**
 * 分页查询
 * @param page
 * @return
 *@author cqg 
 *2015年7月9日
 */
List<SysUserPopedomVO> queryPage(Page<SysUserPopedomVO> page);
/**
 * 获取该用户的最高权限id
 * @param userId
 * @return
 *@author cqg 
 *2015年7月10日
 */
Integer getMinType(Long userId); 
/**
 * 获取该用户用权限的省
 * @param userId
 * @return
 *@author cqg 
 *2015年7月11日
 */
List<SysUserPopedomVO> queryProvince(Long userId);
/**
 * 获取下拉表里的城市
 * @param depts
 * @return
 *@author cqg 
 *2015年7月11日
 */
List<SysUserPopedomVO> queryCity(Long userId,String depts);
/**
 * 获取下拉列表里的小区
 * @param userId
 * @param depts
 * @return
 *@author cqg 
 *2015年7月11日
 */
List<SysUserPopedomVO> queryCommunity(Long userId, String depts);

}
