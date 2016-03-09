package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizNote;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.domain.common.Page;

/**
 * 帖子映射接口
 * @ClassName BizNoteMapper
 * @author zgj
 * 2014年9月16日 下午1:40:22
 */
public interface BizNoteMapper
{
	/**
	 * 插入一条新帖子
	 * @param BizNote
	 */
	public void insert(BizNote bizNote);
	/**
	 * 更新一条帖子
	 * @param bizNote
	 */
	public void update(BizNote bizNote);
	/**
	 * 根据ID查询
	 * @param idKey
	 * @return
	 */
	public BizNoteVO queryById(Long idKey);
	/**
	 * 根据ID删除一条帖子
	 * @param idKey
	 */
	public void delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizNoteVO> queryPage(Page<BizNoteVO> page);
	/**
	 * 手机端查询信息
	 * @param idKey
	 * @return
	 */
	public List<BizNoteVO> queryForMobile(Page<BizNoteVO> page);
	/**
	 * 查询单条信息详细
	 * @param parentId
	 * @return
	 */
	public BizNoteVO queryForDetail(Long idKey);	
	
	/**
	 * 删除多条
	 * @param map
	 */
	public void deleteMulti(Map<String, Object> map);
	/**
	 * 客户端查询我的帖子
	 * @param idKey
	 * @return
	 */
	public List<BizNoteVO> queryForUserNote(Long idKey);
	/**
	 * 添加一条点击
	 * @param clicksCount
	 */
	public void updateStatus(Map<String,Object> map);

}
