/**
 * 
 */
package com.dnake.mapper.system;

import java.util.List;

import com.dnake.domain.system.SysDict;


/**
 * 数据字典
* @ClassName: SysDictMapper 
* @Description: 
* @author cqx
* @date 2013-10-28 上午11:43:07 
*
 */
public interface SysDictMapper {
	/**
	 * 获取到所有的sysDict信息
	* @Title: queryListSysDict 
	* @author cqx  2013-10-28
	* @Description: 
	* @param @return    
	* @return List<SysDict>    
	* @throws
	 */
	List<SysDict> queryListSysDict();
	/**
	 * 根据dictTypeId获取List<SysDict>信息
	* @Title: queryListSysDictByDictTypeId 
	* @author cqx  2013-10-29
	* @Description: 
	* @param @param dictTypeId
	* @param @return    
	* @return List<SysDict>    
	* @throws
	 */	
	List<SysDict> queryListSysDictByDictTypeId(String dictTypeId);
	/**
	 * 根据idKey删除单个dict信息
	* @Title: delete 
	* @author cqx  2013-10-29
	* @Description: 
	* @param @param idKey    
	* @return void    
	* @throws
	 */
	void delete(Long idKey);
	/**
	 * 插入数据字典
	* @Title: insert 
	* @author xzm  2013-11-13
	* @Description: 
	* @param @param sysDict
	* @return void    
	* @throws
	 */
	void insert(SysDict sysDict);
	/**
	 * 更新数据字典
	* @Title: update 
	* @author xzm  2013-11-12
	* @Description: 
	* @param @param sysDict    
	* @return void    
	* @throws
	 */
	void update(SysDict sysDict);
	/**
	 * 根据类别ID获取文本值
	* @Title: queryIdNmByTypeID 
	* @author xzm  2013-11-12
	* @Description: 
	* @param dictTypeId   
	* @return SysDict    
	* @throws
	 */
	SysDict queryIdNmByTypeID(String dictTypeId);
	/**
	 * 根据类别keyID获取文本值
	* @Title: queryDictByKeyID 
	* @author xzm  2013-11-13
	* @Description: 
	* @param @param idKey    
	* @return SysDict    
	* @throws
	 */
	SysDict queryDictByKeyID(Long idKey);
}
