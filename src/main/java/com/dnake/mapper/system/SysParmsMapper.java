/**
 * 
 */
package com.dnake.mapper.system;

import java.util.List;

import com.dnake.domain.system.SysParms;

/** 参数设置的mapper.interface
 * @ClassName: SysParms 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 上午10:43:57 
 *  
 */
public interface SysParmsMapper {
	/**
	 * 查询所有参数设置
	* @Title: queryAll 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @return    
	* @return List<SysParms>    
	* @throws
	 */
	List<SysParms> queryAll();
	/**
	 * 根据主键查询参数信息
	* @Title: queryByIdKey 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return SysParms    
	* @throws
	 */
	SysParms queryByIdKey(Long idKey);
	/**
	 * 更新参数信息
	* @Title: update 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysParms    
	* @return void    
	* @throws
	 */
	void update(SysParms sysParms);
	/**
	 * 根据主键idKey删除参数信息
	* @Title: delete 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey    
	* @return void    
	* @throws
	 */
	void delete(Long idKey);
	/**
	 * 新增参数设置信息
	* @Title: insert 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysParms    
	* @return void    
	* @throws
	 */
	void insert(SysParms sysParms);

}
