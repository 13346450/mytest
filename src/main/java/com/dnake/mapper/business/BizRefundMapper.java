package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizRefund;
import com.dnake.domain.business.BizRefundVO;

public interface BizRefundMapper
{
	void insert(BizRefund bizRefund);

	void delete(long idKey);

	void update(BizRefund bizRefund);

	BizRefund queryByIdkey(long idKey);

	void deleteMulti(Map<String, Object> map);

	List<BizRefundVO> queryPage(Page<BizRefundVO> page);

	void insertMulti(List<BizRefund> list);
	
	/**
	 * 根据订单ID查询
	 *  @author zgj
	 *	日期：2015年9月16日下午7:42:13
	 *  描述：@param orderId
	 *  描述：@return
	 */
	BizRefund queryByOrderId(long orderId);
}
