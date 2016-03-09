package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizComment;
import com.dnake.domain.business.BizCommentVO;
import com.dnake.domain.business.BizGoodsVO;

public interface BizCommentMapper
{
	/**
	 * 插入数据
	 * @param bizComment
	 */
	void insert(BizComment bizComment);

	/**
	 * 删除数据
	 * @param idKey
	 */
	void delete(long idKey);

	/**
	 * 更新数据
	 * @param bizComment
	 */
	void update(BizComment bizComment);

	/**
	 * 根据Id查询
	 * @param idKey
	 * @return
	 */
	BizComment queryByIdkey(long idKey);

	/**
	 * 多条删除
	 * @param map
	 */
	void deleteMulti(Map<String, Object> map);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	List<BizCommentVO> queryPage(Page<BizCommentVO> page);

	/**
	 * 多条插入
	 * @param list
	 */
	void insertMulti(List<BizComment> list);
	
	/**
	 * 查看该商品下评论
	 * @param goodsId
	 * @return
	 */
	List<BizCommentVO> mobileQueryCommentByGoodsId(Page<BizCommentVO> page);
	/**
	 * 根据店铺查询
	 * @param shopId
	 * @return
	 */
	List<BizCommentVO> mobileQueryCommentByShopId(Page<BizCommentVO> page);
	/**
	 * 商品盘平均分
	 * @param goodsId
	 * @return
	 */
	BizCommentVO mobileGoodsScoreDetail(Long goodsId);
	
	/**
	 * 查询评论总数
	 * @param goodsId
	 * @return
	 */
	BizCommentVO queryCommentCount(Long goodsId);
	
	/**
	 * 查询是否回复，根据商品ID和订单ID
	 * @param map
	 * @return
	 */
	List<BizCommentVO> queryCommentIsReply(Map<String, Object> map);
}
