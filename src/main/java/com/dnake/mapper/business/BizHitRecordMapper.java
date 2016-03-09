package com.dnake.mapper.business;

import java.util.List;
import com.dnake.domain.business.BizHitRecord;
import com.dnake.domain.business.BizHitRecordVO;
import com.dnake.domain.common.Page;

/**
 * 点击次数表的映射关系
 * @ClassName BizHitRecordMapper
 * @author zgj
 * 2014年9月3日 下午2:05:48
 */
public interface BizHitRecordMapper
{
	/**
	 * 保存修改连接记录
	 */
	public void update(BizHitRecord bizHitRecord);
	
	/**
	 * 删除一条链接记录
	 */
	public void delete(Long idKey);
	
	/**
	 * 插入一个链接
	 */
	public void insert(BizHitRecord bizHitRecord);
	
	/**
	 * 按id查询单条链接
	 */
	public BizHitRecord queryByIdkey(Long idKey);

	/**
	 * 分页查询
	 */
	public List<BizHitRecordVO> queryPage(Page<BizHitRecordVO> page);
}
