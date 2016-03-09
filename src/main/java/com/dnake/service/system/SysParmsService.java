package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.system.SysParms;

/**
 * 
 * @ClassName:SysParmsService
 * @Description:
 * @author whm
 * @date 2013-10-22 上午11:01:38
 *
 */
public interface SysParmsService {
	/**
	 * 查询所有参数
	 * @Title: queryAllList
	 * @author whm 2013-10-22
	 * @Description:
	 * @return List<SysParms>
	 * @throws
	 */
	List<SysParms> queryAllList();
	
	/**
	 * 查询一个参数
	 * @Title: queryByIdKey
	 * @author whm 2013-10-22
	 * @Description:
	 * @param idKey
	 * @return
	 * @return SysParms
	 * @throws
	 */
	SysParms queryByIdKey(Long idKey);
	
	/**
	 * 新增参数
	 * @Title: insert
	 * @author whm 2013-10-22
	 * @Description:
	 * @param sysParms
	 * @return void
	 * @throws
	 */
	SysParms insert(SysParms sysParms);
	
	/**
	 * 删除一个参数
	 * @Title: delete
	 * @author whm 2013-10-22
	 * @Description:
	 * @param idKey
	 * @return void
	 * @throws
	 */
	String delete(Long idKey);
	
	/**
	 * 更新一个参数
	 * @Title: update
	 * @author whm 2013-10-22
	 * @Description:
	 * @param sysParms
	 * @return void
	 * @throws
	 */
	SysParms update(SysParms sysParms);

}
