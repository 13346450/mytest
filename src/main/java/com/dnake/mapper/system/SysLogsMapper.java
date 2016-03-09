package com.dnake.mapper.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysLogs;

public interface SysLogsMapper {
	
	/**
	 * 根据主键查询单个日志信息
	* @Title: edit 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return SysLogs    
	* @throws
	 */
	SysLogs edit(Long idKey);
	
	/**
	 * 更新单个日志信息
	* @Title: update 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param sysLogs    
	* @return void    
	* @throws
	 */
	void update(SysLogs sysLogs);
	
	/**
	 * 删除单个日志信息
	* @Title: delete 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param idKey    
	* @return void    
	* @throws
	 */
	void delete(Long idKey);
	
	/**
	 * 增加单个日志信息
	* @Title: insert 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param sysLogs    
	* @return void    
	* @throws
	 */
	void insert(SysLogs sysLogs);
	/**
	 * 日志管理 分页
	* @Title: queryPage 
	* @author xzm  2014-1-2
	* @Description: 
	* @param page
	* @return    
	* @return List<SysLogs>    
	* @throws
	 */
	List<SysLogs> queryPage(Page<SysLogs> page);
}
