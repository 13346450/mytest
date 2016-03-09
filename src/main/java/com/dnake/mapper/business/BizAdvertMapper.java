package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizAdvert;
import com.dnake.domain.business.BizAdvertVO;
/**
 * 产品广告管理
 *  BizAdvertMapper <br/>
 *  2014年3月19日 下午2:05:43 <br/>
 * @author ts
 * @version
 */
public interface BizAdvertMapper {
	
	/**
	 * 保存修改广告
	 * @title      update
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param bstDevice
	 */
	void update(BizAdvert bizAdvert);
	
	/**
	 * 更新广告状态
	 * @title      updateStatus
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param bizAdvert
	 */
	void updateStatus(Map<String,Object> map);
	
	/**
	 * 删除一条广告
	 * @title      delete
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param idKey
	 */
	void delete(Long idKey);
	
	/**
	 * 插入一个广告
	 * @title      insert
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param bstDevice
	 */
	void insert(BizAdvert bizAdvert);
	
	/**
	 * 按id查询单条广告
	 * @title      queryByIdkey
	 * @author  ts    
	 * @date      2014年3月28日 
	 * @param idKey
	 * @return
	 */
	BizAdvert queryByIdkey(Long idKey);

	/**
	 * 查询广告最新发布版本
	 * @title      queryMaxVersion
	 * @author  ts    
	 * @date      2014年4月8日 
	 * @return
	 */
	BizAdvert queryMaxPublishedVersion();

	/**
	 * 获得最大版本号
	 * @title      getMaxVersionNo
	 * @author  ts    
	 * @date      2014年4月12日 
	 * @return
	 */
	String getMaxVersionNo();
	
	/**
	 * 分页查询
	 * @title      queryPage
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param page
	 * @return
	 */
	List<BizAdvertVO> queryPage(Page<BizAdvertVO> page);
}
